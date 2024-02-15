package org.apache.commons.imaging.formats.pnm;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.palette.PaletteFactory;

/* loaded from: classes2.dex */
public class PnmImageParser extends ImageParser<PnmImagingParameters> {
    private static final String DEFAULT_EXTENSION = ImageFormats.PNM.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = {ImageFormats.PAM.getDefaultExtension(), ImageFormats.PBM.getDefaultExtension(), ImageFormats.PGM.getDefaultExtension(), ImageFormats.PNM.getDefaultExtension(), ImageFormats.PPM.getDefaultExtension()};

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, PnmImagingParameters pnmImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, PnmImagingParameters pnmImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Pbm-Custom";
    }

    public PnmImageParser() {
        super.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public PnmImagingParameters getDefaultParameters() {
        return new PnmImagingParameters();
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
        return new ImageFormat[]{ImageFormats.PBM, ImageFormats.PGM, ImageFormats.PPM, ImageFormats.PNM, ImageFormats.PAM};
    }

    /* JADX WARN: Code restructure failed: missing block: B:194:0x012c, code lost:
        if (r2 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x012e, code lost:
        if (r3 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0130, code lost:
        if (r4 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0132, code lost:
        if (r5 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0134, code lost:
        if (r6 == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0140, code lost:
        return new org.apache.commons.imaging.formats.pnm.PamFileInfo(r11, r12, r13, r14, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0148, code lost:
        throw new org.apache.commons.imaging.ImageReadException("PAM header has no TUPLTYPE");
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0150, code lost:
        throw new org.apache.commons.imaging.ImageReadException("PAM header has no MAXVAL");
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0158, code lost:
        throw new org.apache.commons.imaging.ImageReadException("PAM header has no DEPTH");
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0160, code lost:
        throw new org.apache.commons.imaging.ImageReadException("PAM header has no HEIGHT");
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0168, code lost:
        throw new org.apache.commons.imaging.ImageReadException("PAM header has no WIDTH");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.commons.imaging.formats.pnm.FileInfo readHeader(java.io.InputStream r17) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.pnm.PnmImageParser.readHeader(java.io.InputStream):org.apache.commons.imaging.formats.pnm.FileInfo");
    }

    private FileInfo readHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            FileInfo readHeader = readHeader(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return readHeader;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, PnmImagingParameters pnmImagingParameters) throws ImageReadException, IOException {
        FileInfo readHeader = readHeader(byteSource);
        return new Dimension(readHeader.width, readHeader.height);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, PnmImagingParameters pnmImagingParameters) throws ImageReadException, IOException {
        FileInfo readHeader = readHeader(byteSource);
        ArrayList arrayList = new ArrayList();
        int bitDepth = readHeader.getBitDepth() * readHeader.getNumComponents();
        ImageFormat imageType = readHeader.getImageType();
        String imageTypeDescription = readHeader.getImageTypeDescription();
        String mIMEType = readHeader.getMIMEType();
        float f = (float) (readHeader.width / 72.0d);
        String imageTypeDescription2 = readHeader.getImageTypeDescription();
        boolean hasAlpha = readHeader.hasAlpha();
        ImageInfo.ColorType colorType = readHeader.getColorType();
        ImageInfo.CompressionAlgorithm compressionAlgorithm = ImageInfo.CompressionAlgorithm.NONE;
        return new ImageInfo(imageTypeDescription2, bitDepth, arrayList, imageType, imageTypeDescription, readHeader.height, mIMEType, 1, 72, (float) (readHeader.height / 72.0d), 72, f, readHeader.width, false, hasAlpha, false, colorType, compressionAlgorithm);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        printWriter.println("pnm.dumpImageFile");
        ImageInfo imageInfo = getImageInfo(byteSource);
        if (imageInfo == null) {
            return false;
        }
        imageInfo.toString(printWriter, "");
        printWriter.println("");
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public BufferedImage getBufferedImage(ByteSource byteSource, PnmImagingParameters pnmImagingParameters) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            FileInfo readHeader = readHeader(inputStream);
            ImageBuilder imageBuilder = new ImageBuilder(readHeader.width, readHeader.height, readHeader.hasAlpha());
            readHeader.readImage(imageBuilder, inputStream);
            BufferedImage bufferedImage = imageBuilder.getBufferedImage();
            if (inputStream != null) {
                inputStream.close();
            }
            return bufferedImage;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PnmImagingParameters pnmImagingParameters) throws ImageWriteException, IOException {
        boolean z;
        PnmWriter pnmWriter = null;
        if (pnmImagingParameters != null) {
            z = pnmImagingParameters.isRawBits();
            ImageFormats subtype = pnmImagingParameters.getSubtype();
            if (subtype != null) {
                if (subtype.equals(ImageFormats.PBM)) {
                    pnmWriter = new PbmWriter(z);
                } else if (subtype.equals(ImageFormats.PGM)) {
                    pnmWriter = new PgmWriter(z);
                } else if (subtype.equals(ImageFormats.PPM)) {
                    pnmWriter = new PpmWriter(z);
                } else if (subtype.equals(ImageFormats.PAM)) {
                    pnmWriter = new PamWriter();
                }
            }
        } else {
            z = true;
        }
        if (pnmWriter == null) {
            if (new PaletteFactory().hasTransparency(bufferedImage)) {
                pnmWriter = new PamWriter();
            } else {
                pnmWriter = new PpmWriter(z);
            }
        }
        pnmWriter.writeImage(bufferedImage, outputStream, pnmImagingParameters);
    }
}
