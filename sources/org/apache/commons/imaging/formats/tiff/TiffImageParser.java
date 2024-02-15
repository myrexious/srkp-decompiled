package org.apache.commons.imaging.formats.tiff;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import kotlin.UShort;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.TiffEpTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterBiLevel;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterCieLab;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterCmyk;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterLogLuv;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterPalette;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterRgb;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterYCbCr;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.write.TiffImageWriterLossy;

/* loaded from: classes2.dex */
public class TiffImageParser extends ImageParser<TiffImagingParameters> implements XmpEmbeddable {
    private static final String DEFAULT_EXTENSION = ImageFormats.TIFF.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.TIFF.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Tiff-Custom";
    }

    @Override // org.apache.commons.imaging.ImageParser
    public TiffImagingParameters getDefaultParameters() {
        return new TiffImagingParameters();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String[] getAcceptedExtensions() {
        return ACCEPTED_EXTENSIONS;
    }

    @Override // org.apache.commons.imaging.ImageParser
    protected ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormats.TIFF};
    }

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        return new TiffReader(tiffImagingParameters != null && tiffImagingParameters.isStrict()).readFirstDirectory(byteSource, false, FormatCompliance.getDefault()).directories.get(0).getFieldValue((TagInfoBytes) TiffEpTagConstants.EXIF_TAG_INTER_COLOR_PROFILE, false);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        TiffDirectory tiffDirectory = new TiffReader(tiffImagingParameters != null && tiffImagingParameters.isStrict()).readFirstDirectory(byteSource, false, FormatCompliance.getDefault()).directories.get(0);
        TiffField findField = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH, true);
        TiffField findField2 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH, true);
        if (findField == null || findField2 == null) {
            throw new ImageReadException("TIFF image missing size info.");
        }
        return new Dimension(findField.getIntValue(), findField2.getIntValue());
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        if (tiffImagingParameters == null) {
            tiffImagingParameters = getDefaultParameters();
        }
        FormatCompliance formatCompliance = FormatCompliance.getDefault();
        TiffReader tiffReader = new TiffReader(tiffImagingParameters.isStrict());
        TiffContents readContents = tiffReader.readContents(byteSource, tiffImagingParameters, formatCompliance);
        List<TiffDirectory> list = readContents.directories;
        TiffImageMetadata tiffImageMetadata = new TiffImageMetadata(readContents);
        for (TiffDirectory tiffDirectory : list) {
            TiffImageMetadata.Directory directory = new TiffImageMetadata.Directory(tiffReader.getByteOrder(), tiffDirectory);
            for (TiffField tiffField : tiffDirectory.getDirectoryEntries()) {
                directory.add(tiffField);
            }
            tiffImageMetadata.add(directory);
        }
        return tiffImageMetadata;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        int i;
        int i2;
        float f;
        float f2;
        ImageInfo.CompressionAlgorithm compressionAlgorithm;
        int i3;
        float f3;
        TiffContents readDirectories = new TiffReader(tiffImagingParameters != null && tiffImagingParameters.isStrict()).readDirectories(byteSource, false, FormatCompliance.getDefault());
        TiffDirectory tiffDirectory = readDirectories.directories.get(0);
        TiffField findField = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH, true);
        TiffField findField2 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH, true);
        if (findField == null || findField2 == null) {
            throw new ImageReadException("TIFF image missing size info.");
        }
        int intValue = findField2.getIntValue();
        int intValue2 = findField.getIntValue();
        TiffField findField3 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_RESOLUTION_UNIT);
        int intValue3 = (findField3 == null || findField3.getValue() == null) ? 2 : findField3.getIntValue();
        double d = intValue3 != 2 ? intValue3 != 3 ? -1.0d : 2.54d : 1.0d;
        if (d > 0.0d) {
            TiffField findField4 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_XRESOLUTION);
            TiffField findField5 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_YRESOLUTION);
            if (findField4 == null || findField4.getValue() == null) {
                i3 = -1;
                f3 = -1.0f;
            } else {
                double doubleValue = findField4.getDoubleValue() * d;
                i3 = (int) Math.round(doubleValue);
                f3 = (float) (intValue2 / doubleValue);
            }
            if (findField5 == null || findField5.getValue() == null) {
                i2 = i3;
                f2 = f3;
                i = -1;
                f = -1.0f;
            } else {
                double doubleValue2 = findField5.getDoubleValue() * d;
                i2 = i3;
                f2 = f3;
                i = (int) Math.round(doubleValue2);
                f = (float) (intValue / doubleValue2);
            }
        } else {
            i = -1;
            i2 = -1;
            f = -1.0f;
            f2 = -1.0f;
        }
        TiffField findField6 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_BITS_PER_SAMPLE);
        int intValueOrArraySum = (findField6 == null || findField6.getValue() == null) ? 1 : findField6.getIntValueOrArraySum();
        List<TiffField> list = tiffDirectory.entries;
        ArrayList arrayList = new ArrayList(list.size());
        for (TiffField tiffField : list) {
            arrayList.add(tiffField.toString());
        }
        ImageFormats imageFormats = ImageFormats.TIFF;
        int size = readDirectories.directories.size();
        String str = "Tiff v." + readDirectories.header.tiffVersion;
        boolean z = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_COLOR_MAP) != null;
        ImageInfo.ColorType colorType = ImageInfo.ColorType.RGB;
        int fieldValue = 65535 & (tiffDirectory.findField(TiffTagConstants.TIFF_TAG_COMPRESSION) != null ? tiffDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_COMPRESSION) : (short) 1);
        if (fieldValue == 32771) {
            compressionAlgorithm = ImageInfo.CompressionAlgorithm.NONE;
        } else if (fieldValue != 32773) {
            switch (fieldValue) {
                case 1:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.NONE;
                    break;
                case 2:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.CCITT_1D;
                    break;
                case 3:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.CCITT_GROUP_3;
                    break;
                case 4:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.CCITT_GROUP_4;
                    break;
                case 5:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.LZW;
                    break;
                case 6:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.JPEG;
                    break;
                default:
                    compressionAlgorithm = ImageInfo.CompressionAlgorithm.UNKNOWN;
                    break;
            }
        } else {
            compressionAlgorithm = ImageInfo.CompressionAlgorithm.PACKBITS;
        }
        return new ImageInfo(str, intValueOrArraySum, arrayList, imageFormats, "TIFF Tag-based Image File Format", intValue, "image/tiff", size, i, f, i2, f2, intValue2, false, false, z, colorType, compressionAlgorithm);
    }

    @Override // org.apache.commons.imaging.common.XmpEmbeddable
    public String getXmpXml(ByteSource byteSource, XmpImagingParameters xmpImagingParameters) throws ImageReadException, IOException {
        if (xmpImagingParameters == null) {
            xmpImagingParameters = new XmpImagingParameters();
        }
        byte[] fieldValue = new TiffReader(xmpImagingParameters.isStrict()).readDirectories(byteSource, false, FormatCompliance.getDefault()).directories.get(0).getFieldValue(TiffTagConstants.TIFF_TAG_XMP, false);
        if (fieldValue == null) {
            return null;
        }
        return new String(fieldValue, StandardCharsets.UTF_8);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        try {
            printWriter.println("tiff.dumpImageFile");
            ImageInfo imageInfo = getImageInfo(byteSource);
            if (imageInfo == null) {
                return false;
            }
            imageInfo.toString(printWriter, "");
            printWriter.println("");
            List<TiffDirectory> list = new TiffReader(true).readContents(byteSource, new TiffImagingParameters(), FormatCompliance.getDefault()).directories;
            if (list == null) {
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                List<TiffField> list2 = list.get(i).entries;
                if (list2 == null) {
                    return false;
                }
                for (TiffField tiffField : list2) {
                    tiffField.dump(printWriter, Integer.toString(i));
                }
            }
            printWriter.println("");
            return true;
        } finally {
            printWriter.println("");
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public FormatCompliance getFormatCompliance(ByteSource byteSource) throws ImageReadException, IOException {
        FormatCompliance formatCompliance = FormatCompliance.getDefault();
        TiffImagingParameters tiffImagingParameters = new TiffImagingParameters();
        new TiffReader(tiffImagingParameters.isStrict()).readContents(byteSource, tiffImagingParameters, formatCompliance);
        return formatCompliance;
    }

    public List<byte[]> collectRawImageData(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        TiffContents readDirectories = new TiffReader(tiffImagingParameters != null && tiffImagingParameters.isStrict()).readDirectories(byteSource, true, FormatCompliance.getDefault());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readDirectories.directories.size(); i++) {
            for (TiffDirectory.ImageDataElement imageDataElement : readDirectories.directories.get(i).getTiffRawImageDataElements()) {
                arrayList.add(byteSource.getBlock(imageDataElement.offset, imageDataElement.length));
            }
        }
        return arrayList;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public BufferedImage getBufferedImage(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        if (tiffImagingParameters == null) {
            tiffImagingParameters = new TiffImagingParameters();
        }
        FormatCompliance formatCompliance = FormatCompliance.getDefault();
        TiffReader tiffReader = new TiffReader(tiffImagingParameters.isStrict());
        TiffContents readFirstDirectory = tiffReader.readFirstDirectory(byteSource, true, formatCompliance);
        BufferedImage tiffImage = readFirstDirectory.directories.get(0).getTiffImage(tiffReader.getByteOrder(), tiffImagingParameters);
        if (tiffImage != null) {
            return tiffImage;
        }
        throw new ImageReadException("TIFF does not contain an image.");
    }

    @Override // org.apache.commons.imaging.ImageParser
    public List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        FormatCompliance formatCompliance = FormatCompliance.getDefault();
        TiffReader tiffReader = new TiffReader(true);
        TiffContents readDirectories = tiffReader.readDirectories(byteSource, true, formatCompliance);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readDirectories.directories.size(); i++) {
            BufferedImage tiffImage = readDirectories.directories.get(i).getTiffImage(tiffReader.getByteOrder(), null);
            if (tiffImage != null) {
                arrayList.add(tiffImage);
            }
        }
        return arrayList;
    }

    private Rectangle checkForSubImage(TiffImagingParameters tiffImagingParameters) throws ImageReadException {
        if (tiffImagingParameters.isSubImageSet()) {
            return new Rectangle(tiffImagingParameters.getSubImageX(), tiffImagingParameters.getSubImageY(), tiffImagingParameters.getSubImageWidth(), tiffImagingParameters.getSubImageHeight());
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:155:0x00e4, code lost:
        if (r2 != 2) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:161:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0155  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.awt.image.BufferedImage getBufferedImage(org.apache.commons.imaging.formats.tiff.TiffDirectory r22, java.nio.ByteOrder r23, org.apache.commons.imaging.formats.tiff.TiffImagingParameters r24) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.tiff.TiffImageParser.getBufferedImage(org.apache.commons.imaging.formats.tiff.TiffDirectory, java.nio.ByteOrder, org.apache.commons.imaging.formats.tiff.TiffImagingParameters):java.awt.image.BufferedImage");
    }

    private PhotometricInterpreter getPhotometricInterpreter(TiffDirectory tiffDirectory, int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) throws ImageReadException {
        if (i == 0 || i == 1) {
            return new PhotometricInterpreterBiLevel(i4, iArr, i3, i5, i6, i == 0);
        } else if (i != 2) {
            if (i == 3) {
                int[] intArrayValue = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_COLOR_MAP, true).getIntArrayValue();
                int i7 = (1 << i2) * 3;
                if (intArrayValue.length != i7) {
                    throw new ImageReadException("Tiff: fColorMap.length (" + intArrayValue.length + ")!=expectedColormapSize (" + i7 + ")");
                }
                return new PhotometricInterpreterPalette(i4, iArr, i3, i5, i6, intArrayValue);
            } else if (i != 5) {
                if (i != 6) {
                    if (i == 8) {
                        return new PhotometricInterpreterCieLab(i4, iArr, i3, i5, i6);
                    }
                    switch (i) {
                        case TiffTagConstants.PHOTOMETRIC_INTERPRETATION_VALUE_PIXAR_LOG_L /* 32844 */:
                        case TiffTagConstants.PHOTOMETRIC_INTERPRETATION_VALUE_PIXAR_LOG_LUV /* 32845 */:
                            return new PhotometricInterpreterLogLuv(i4, iArr, i3, i5, i6);
                        default:
                            throw new ImageReadException("TIFF: Unknown fPhotometricInterpretation: " + i);
                    }
                }
                return new PhotometricInterpreterYCbCr(i4, iArr, i3, i5, i6);
            } else {
                return new PhotometricInterpreterCmyk(i4, iArr, i3, i5, i6);
            }
        } else {
            return new PhotometricInterpreterRgb(i4, iArr, i3, i5, i6);
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, TiffImagingParameters tiffImagingParameters) throws ImageWriteException, IOException {
        if (tiffImagingParameters == null) {
            tiffImagingParameters = new TiffImagingParameters();
        }
        new TiffImageWriterLossy().writeImage(bufferedImage, outputStream, tiffImagingParameters);
    }

    public TiffRasterData getRasterData(TiffDirectory tiffDirectory, ByteOrder byteOrder, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        int[] iArr;
        int i;
        TiffPlanarConfiguration lenientValueOf;
        if (tiffDirectory.entries == null) {
            throw new ImageReadException("TIFF missing entries");
        }
        TiffImagingParameters defaultParameters = tiffImagingParameters == null ? getDefaultParameters() : tiffImagingParameters;
        short[] fieldValue = tiffDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, true);
        if (fieldValue == null || fieldValue.length < 1) {
            throw new ImageReadException("Directory does not specify numeric raster data");
        }
        TiffField findField = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_SAMPLES_PER_PIXEL);
        int intValue = findField != null ? findField.getIntValue() : 1;
        int[] iArr2 = {1};
        TiffField findField2 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_BITS_PER_SAMPLE);
        if (findField2 != null) {
            iArr = findField2.getIntArrayValue();
            i = findField2.getIntValueOrArraySum();
        } else {
            iArr = iArr2;
            i = intValue;
        }
        int fieldValue2 = (tiffDirectory.findField(TiffTagConstants.TIFF_TAG_COMPRESSION) != null ? tiffDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_COMPRESSION) : (short) 1) & UShort.MAX_VALUE;
        int singleFieldValue = tiffDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH);
        int singleFieldValue2 = tiffDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH);
        Rectangle checkForSubImage = checkForSubImage(defaultParameters);
        if (checkForSubImage != null) {
            if (checkForSubImage.width <= 0) {
                throw new ImageReadException("negative or zero subimage width");
            }
            if (checkForSubImage.height <= 0) {
                throw new ImageReadException("negative or zero subimage height");
            }
            if (checkForSubImage.x < 0 || checkForSubImage.x >= singleFieldValue) {
                throw new ImageReadException("subimage x is outside raster");
            }
            if (checkForSubImage.x + checkForSubImage.width > singleFieldValue) {
                throw new ImageReadException("subimage (x+width) is outside raster");
            }
            if (checkForSubImage.y < 0 || checkForSubImage.y >= singleFieldValue2) {
                throw new ImageReadException("subimage y is outside raster");
            }
            if (checkForSubImage.y + checkForSubImage.height > singleFieldValue2) {
                throw new ImageReadException("subimage (y+height) is outside raster");
            }
            if (checkForSubImage.x == 0 && checkForSubImage.y == 0 && checkForSubImage.width == singleFieldValue && checkForSubImage.height == singleFieldValue2) {
                checkForSubImage = null;
            }
        }
        TiffField findField3 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_PREDICTOR);
        int intValueOrArraySum = findField3 != null ? findField3.getIntValueOrArraySum() : -1;
        TiffField findField4 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_PLANAR_CONFIGURATION);
        if (findField4 == null) {
            lenientValueOf = TiffPlanarConfiguration.CHUNKY;
        } else {
            lenientValueOf = TiffPlanarConfiguration.lenientValueOf(findField4.getIntValue());
        }
        TiffPlanarConfiguration tiffPlanarConfiguration = lenientValueOf;
        short s = fieldValue[0];
        if (s == 3) {
            int i2 = iArr[0];
            if (i2 != 32 && i2 != 64) {
                throw new ImageReadException("TIFF floating-point data uses unsupported bits-per-sample: " + iArr[0]);
            }
            if (intValueOrArraySum != -1 && intValueOrArraySum != 1 && intValueOrArraySum != 3) {
                throw new ImageReadException("TIFF floating-point data uses unsupported horizontal-differencing predictor");
            }
        } else if (s != 2) {
            throw new ImageReadException("TIFF does not provide a supported raster-data format");
        } else {
            if (intValue != 1) {
                throw new ImageReadException("TIFF integer data uses unsupported samples per pixel: " + intValue);
            }
            if (i != 16 && i != 32) {
                throw new ImageReadException("TIFF integer data uses unsupported bits-per-pixel: " + i);
            }
            if (intValueOrArraySum != -1 && intValueOrArraySum != 1 && intValueOrArraySum != 2) {
                throw new ImageReadException("TIFF integer data uses unsupported horizontal-differencing predictor");
            }
        }
        int i3 = intValue;
        return tiffDirectory.getTiffImageData().getDataReader(tiffDirectory, new PhotometricInterpreterBiLevel(i3, iArr, intValueOrArraySum, singleFieldValue, singleFieldValue2, false), i, iArr, intValueOrArraySum, i3, singleFieldValue, singleFieldValue2, fieldValue2, tiffPlanarConfiguration, byteOrder).readRasterData(checkForSubImage);
    }
}
