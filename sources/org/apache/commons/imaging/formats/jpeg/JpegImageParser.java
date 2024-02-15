package org.apache.commons.imaging.formats.jpeg;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.jpeg.JpegUtils;
import org.apache.commons.imaging.formats.jpeg.decoder.JpegDecoder;
import org.apache.commons.imaging.formats.jpeg.iptc.IptcParser;
import org.apache.commons.imaging.formats.jpeg.iptc.PhotoshopApp13Data;
import org.apache.commons.imaging.formats.jpeg.segments.App13Segment;
import org.apache.commons.imaging.formats.jpeg.segments.App14Segment;
import org.apache.commons.imaging.formats.jpeg.segments.App2Segment;
import org.apache.commons.imaging.formats.jpeg.segments.ComSegment;
import org.apache.commons.imaging.formats.jpeg.segments.DqtSegment;
import org.apache.commons.imaging.formats.jpeg.segments.GenericSegment;
import org.apache.commons.imaging.formats.jpeg.segments.JfifSegment;
import org.apache.commons.imaging.formats.jpeg.segments.Segment;
import org.apache.commons.imaging.formats.jpeg.segments.SofnSegment;
import org.apache.commons.imaging.formats.jpeg.segments.UnknownSegment;
import org.apache.commons.imaging.formats.jpeg.xmp.JpegXmpParser;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.internal.Debug;

/* loaded from: classes2.dex */
public class JpegImageParser extends ImageParser<JpegImagingParameters> implements XmpEmbeddable {
    private static final Logger LOGGER = Logger.getLogger(JpegImageParser.class.getName());
    private static final String DEFAULT_EXTENSION = ImageFormats.JPEG.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.JPEG.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Jpeg-Custom";
    }

    public JpegImageParser() {
        setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    protected ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormats.JPEG};
    }

    @Override // org.apache.commons.imaging.ImageParser
    public JpegImagingParameters getDefaultParameters() {
        return new JpegImagingParameters();
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
    public final BufferedImage getBufferedImage(ByteSource byteSource, JpegImagingParameters jpegImagingParameters) throws ImageReadException, IOException {
        return new JpegDecoder().decode(byteSource);
    }

    public boolean keepMarker(int i, int[] iArr) {
        if (iArr == null) {
            return true;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public List<Segment> readSegments(ByteSource byteSource, final int[] iArr, final boolean z) throws ImageReadException, IOException {
        final ArrayList arrayList = new ArrayList();
        final int[] iArr2 = {JpegConstants.SOF0_MARKER, JpegConstants.SOF1_MARKER, JpegConstants.SOF2_MARKER, JpegConstants.SOF3_MARKER, JpegConstants.SOF5_MARKER, JpegConstants.SOF6_MARKER, JpegConstants.SOF7_MARKER, JpegConstants.SOF9_MARKER, JpegConstants.SOF10_MARKER, JpegConstants.SOF11_MARKER, JpegConstants.SOF13_MARKER, JpegConstants.SOF14_MARKER, JpegConstants.SOF15_MARKER};
        new JpegUtils().traverseJFIF(byteSource, new JpegUtils.Visitor() { // from class: org.apache.commons.imaging.formats.jpeg.JpegImageParser.1
            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean beginSOS() {
                return false;
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException {
                if (i == 65497) {
                    return false;
                }
                if (JpegImageParser.this.keepMarker(i, iArr)) {
                    switch (i) {
                        case 65504:
                            arrayList.add(new JfifSegment(i, bArr3));
                            break;
                        case JpegConstants.JPEG_APP2_MARKER /* 65506 */:
                            arrayList.add(new App2Segment(i, bArr3));
                            break;
                        case JpegConstants.JPEG_APP13_MARKER /* 65517 */:
                            arrayList.add(new App13Segment(i, bArr3));
                            break;
                        case JpegConstants.JPEG_APP14_MARKER /* 65518 */:
                            arrayList.add(new App14Segment(i, bArr3));
                            break;
                        default:
                            if (Arrays.binarySearch(iArr2, i) < 0) {
                                if (i != 65499) {
                                    if (i >= 65505 && i <= 65519) {
                                        arrayList.add(new UnknownSegment(i, bArr3));
                                        break;
                                    } else if (i == 65534) {
                                        arrayList.add(new ComSegment(i, bArr3));
                                        break;
                                    }
                                } else {
                                    arrayList.add(new DqtSegment(i, bArr3));
                                    break;
                                }
                            } else {
                                arrayList.add(new SofnSegment(i, bArr3));
                                break;
                            }
                            break;
                    }
                    return !z;
                }
                return true;
            }
        });
        return arrayList;
    }

    private byte[] assembleSegments(List<App2Segment> list) throws ImageReadException {
        try {
            return assembleSegments(list, false);
        } catch (ImageReadException unused) {
            return assembleSegments(list, true);
        }
    }

    private byte[] assembleSegments(List<App2Segment> list, boolean z) throws ImageReadException {
        if (list.isEmpty()) {
            throw new ImageReadException("No App2 Segments Found.");
        }
        int i = list.get(0).numMarkers;
        if (list.size() != i) {
            throw new ImageReadException("App2 Segments Missing.  Found: " + list.size() + ", Expected: " + i + ".");
        }
        list.sort(null);
        int i2 = !z ? 1 : 0;
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            App2Segment app2Segment = list.get(i4);
            if (i4 + i2 != app2Segment.curMarker) {
                dumpSegments(list);
                throw new ImageReadException("Incoherent App2 Segment Ordering.  i: " + i4 + ", segment[" + i4 + "].curMarker: " + app2Segment.curMarker + ".");
            } else if (i != app2Segment.numMarkers) {
                dumpSegments(list);
                throw new ImageReadException("Inconsistent App2 Segment Count info.  markerCount: " + i + ", segment[" + i4 + "].numMarkers: " + app2Segment.numMarkers + ".");
            } else {
                i3 += app2Segment.getIccBytes().length;
            }
        }
        byte[] bArr = new byte[i3];
        int i5 = 0;
        for (App2Segment app2Segment2 : list) {
            System.arraycopy(app2Segment2.getIccBytes(), 0, bArr, i5, app2Segment2.getIccBytes().length);
            i5 += app2Segment2.getIccBytes().length;
        }
        return bArr;
    }

    private void dumpSegments(List<? extends Segment> list) {
        Debug.debug();
        Debug.debug("dumpSegments: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            App2Segment app2Segment = (App2Segment) list.get(i);
            Debug.debug(i + ": " + app2Segment.curMarker + " / " + app2Segment.numMarkers);
        }
        Debug.debug();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, JpegImagingParameters jpegImagingParameters) throws ImageReadException, IOException {
        List<Segment> readSegments = readSegments(byteSource, new int[]{JpegConstants.JPEG_APP2_MARKER}, false);
        ArrayList arrayList = new ArrayList();
        if (readSegments != null) {
            Iterator<Segment> it = readSegments.iterator();
            while (it.hasNext()) {
                App2Segment app2Segment = (App2Segment) it.next();
                if (app2Segment.getIccBytes() != null) {
                    arrayList.add(app2Segment);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        byte[] assembleSegments = assembleSegments(arrayList);
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("bytes: " + assembleSegments.length);
        }
        return assembleSegments;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, JpegImagingParameters jpegImagingParameters) throws ImageReadException, IOException {
        if (jpegImagingParameters == null) {
            jpegImagingParameters = new JpegImagingParameters();
        }
        TiffImageMetadata exifMetadata = getExifMetadata(byteSource, new TiffImagingParameters());
        JpegPhotoshopMetadata photoshopMetadata = getPhotoshopMetadata(byteSource, jpegImagingParameters);
        if (exifMetadata == null && photoshopMetadata == null) {
            return null;
        }
        return new JpegImageMetadata(photoshopMetadata, exifMetadata);
    }

    public static boolean isExifAPP1Segment(GenericSegment genericSegment) {
        return BinaryFunctions.startsWith(genericSegment.getSegmentData(), JpegConstants.EXIF_IDENTIFIER_CODE);
    }

    private List<Segment> filterAPP1Segments(List<Segment> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Segment> it = list.iterator();
        while (it.hasNext()) {
            GenericSegment genericSegment = (GenericSegment) it.next();
            if (isExifAPP1Segment(genericSegment)) {
                arrayList.add(genericSegment);
            }
        }
        return arrayList;
    }

    public TiffImageMetadata getExifMetadata(ByteSource byteSource, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        byte[] exifRawData = getExifRawData(byteSource);
        if (exifRawData == null) {
            return null;
        }
        if (tiffImagingParameters == null) {
            tiffImagingParameters = new TiffImagingParameters();
        }
        tiffImagingParameters.setReadThumbnails(Boolean.TRUE.booleanValue());
        return (TiffImageMetadata) new TiffImageParser().getMetadata(exifRawData, (byte[]) tiffImagingParameters);
    }

    public byte[] getExifRawData(ByteSource byteSource) throws ImageReadException, IOException {
        List<Segment> readSegments = readSegments(byteSource, new int[]{JpegConstants.JPEG_APP1_MARKER}, false);
        if (readSegments == null || readSegments.isEmpty()) {
            return null;
        }
        List<Segment> filterAPP1Segments = filterAPP1Segments(readSegments);
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("exif_segments.size: " + filterAPP1Segments.size());
        }
        if (filterAPP1Segments.isEmpty()) {
            return null;
        }
        if (filterAPP1Segments.size() > 1) {
            throw new ImageReadException("Imaging currently can't parse EXIF metadata split across multiple APP1 segments.  Please send this image to the Imaging project.");
        }
        return BinaryFunctions.remainingBytes("trimmed exif bytes", ((GenericSegment) filterAPP1Segments.get(0)).getSegmentData(), 6);
    }

    public boolean hasExifSegment(ByteSource byteSource) throws ImageReadException, IOException {
        final boolean[] zArr = {false};
        new JpegUtils().traverseJFIF(byteSource, new JpegUtils.Visitor() { // from class: org.apache.commons.imaging.formats.jpeg.JpegImageParser.2
            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean beginSOS() {
                return false;
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
                if (i == 65497) {
                    return false;
                }
                if (i == 65505 && BinaryFunctions.startsWith(bArr3, JpegConstants.EXIF_IDENTIFIER_CODE)) {
                    zArr[0] = true;
                    return false;
                }
                return true;
            }
        });
        return zArr[0];
    }

    public boolean hasIptcSegment(ByteSource byteSource) throws ImageReadException, IOException {
        final boolean[] zArr = {false};
        new JpegUtils().traverseJFIF(byteSource, new JpegUtils.Visitor() { // from class: org.apache.commons.imaging.formats.jpeg.JpegImageParser.3
            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean beginSOS() {
                return false;
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
                if (i == 65497) {
                    return false;
                }
                if (i == 65517 && new IptcParser().isPhotoshopJpegSegment(bArr3)) {
                    zArr[0] = true;
                    return false;
                }
                return true;
            }
        });
        return zArr[0];
    }

    public boolean hasXmpSegment(ByteSource byteSource) throws ImageReadException, IOException {
        final boolean[] zArr = {false};
        new JpegUtils().traverseJFIF(byteSource, new JpegUtils.Visitor() { // from class: org.apache.commons.imaging.formats.jpeg.JpegImageParser.4
            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean beginSOS() {
                return false;
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
                if (i == 65497) {
                    return false;
                }
                if (i == 65505 && new JpegXmpParser().isXmpJpegSegment(bArr3)) {
                    zArr[0] = true;
                    return false;
                }
                return true;
            }
        });
        return zArr[0];
    }

    @Override // org.apache.commons.imaging.common.XmpEmbeddable
    public String getXmpXml(ByteSource byteSource, XmpImagingParameters xmpImagingParameters) throws ImageReadException, IOException {
        final ArrayList arrayList = new ArrayList();
        new JpegUtils().traverseJFIF(byteSource, new JpegUtils.Visitor() { // from class: org.apache.commons.imaging.formats.jpeg.JpegImageParser.5
            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean beginSOS() {
                return false;
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException {
                if (i == 65497) {
                    return false;
                }
                if (i == 65505 && new JpegXmpParser().isXmpJpegSegment(bArr3)) {
                    arrayList.add(new JpegXmpParser().parseXmpJpegSegment(bArr3));
                    return false;
                }
                return true;
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1) {
            throw new ImageReadException("Jpeg file contains more than one XMP segment.");
        }
        return (String) arrayList.get(0);
    }

    public JpegPhotoshopMetadata getPhotoshopMetadata(ByteSource byteSource, JpegImagingParameters jpegImagingParameters) throws ImageReadException, IOException {
        List<Segment> readSegments = readSegments(byteSource, new int[]{JpegConstants.JPEG_APP13_MARKER}, false);
        if (readSegments == null || readSegments.isEmpty()) {
            return null;
        }
        Iterator<Segment> it = readSegments.iterator();
        PhotoshopApp13Data photoshopApp13Data = null;
        while (it.hasNext()) {
            PhotoshopApp13Data parsePhotoshopSegment = ((App13Segment) it.next()).parsePhotoshopSegment(jpegImagingParameters);
            if (parsePhotoshopSegment != null) {
                if (photoshopApp13Data != null) {
                    throw new ImageReadException("Jpeg contains more than one Photoshop App13 segment.");
                }
                photoshopApp13Data = parsePhotoshopSegment;
            }
        }
        if (photoshopApp13Data == null) {
            return null;
        }
        return new JpegPhotoshopMetadata(photoshopApp13Data);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, JpegImagingParameters jpegImagingParameters) throws ImageReadException, IOException {
        List<Segment> readSegments = readSegments(byteSource, new int[]{JpegConstants.SOF0_MARKER, JpegConstants.SOF1_MARKER, JpegConstants.SOF2_MARKER, JpegConstants.SOF3_MARKER, JpegConstants.SOF5_MARKER, JpegConstants.SOF6_MARKER, JpegConstants.SOF7_MARKER, JpegConstants.SOF9_MARKER, JpegConstants.SOF10_MARKER, JpegConstants.SOF11_MARKER, JpegConstants.SOF13_MARKER, JpegConstants.SOF14_MARKER, JpegConstants.SOF15_MARKER}, true);
        if (readSegments == null || readSegments.isEmpty()) {
            throw new ImageReadException("No JFIF Data Found.");
        }
        if (readSegments.size() > 1) {
            throw new ImageReadException("Redundant JFIF Data Found.");
        }
        SofnSegment sofnSegment = (SofnSegment) readSegments.get(0);
        return new Dimension(sofnSegment.width, sofnSegment.height);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, JpegImagingParameters jpegImagingParameters) throws ImageReadException, IOException {
        double d;
        double d2;
        double d3;
        String str;
        double d4;
        double d5;
        JfifSegment jfifSegment;
        int i;
        float f;
        float f2;
        int i2;
        int i3;
        ImageInfo.ColorType colorType;
        ImageInfo.ColorType colorType2;
        boolean z;
        ImageInfo.ColorType colorType3;
        int i4;
        boolean z2;
        ImageInfo.ColorType colorType4;
        boolean z3;
        ImageInfo.ColorType colorType5;
        List<Segment> readSegments = readSegments(byteSource, new int[]{JpegConstants.SOF0_MARKER, JpegConstants.SOF1_MARKER, JpegConstants.SOF2_MARKER, JpegConstants.SOF3_MARKER, JpegConstants.SOF5_MARKER, JpegConstants.SOF6_MARKER, JpegConstants.SOF7_MARKER, JpegConstants.SOF9_MARKER, JpegConstants.SOF10_MARKER, JpegConstants.SOF11_MARKER, JpegConstants.SOF13_MARKER, JpegConstants.SOF14_MARKER, JpegConstants.SOF15_MARKER}, false);
        if (readSegments == null) {
            throw new ImageReadException("No SOFN Data Found.");
        }
        List<Segment> readSegments2 = readSegments(byteSource, new int[]{65504}, true);
        SofnSegment sofnSegment = (SofnSegment) readSegments.get(0);
        if (sofnSegment == null) {
            throw new ImageReadException("No SOFN Data Found.");
        }
        int i5 = sofnSegment.width;
        int i6 = sofnSegment.height;
        App14Segment app14Segment = null;
        JfifSegment jfifSegment2 = (readSegments2 == null || readSegments2.isEmpty()) ? null : (JfifSegment) readSegments2.get(0);
        List<Segment> readSegments3 = readSegments(byteSource, new int[]{JpegConstants.JPEG_APP14_MARKER}, true);
        if (readSegments3 != null && !readSegments3.isEmpty()) {
            app14Segment = (App14Segment) readSegments3.get(0);
        }
        if (jfifSegment2 != null) {
            d4 = jfifSegment2.xDensity;
            d5 = jfifSegment2.yDensity;
            int i7 = jfifSegment2.densityUnits;
            str = "Jpeg/JFIF v." + jfifSegment2.jfifMajorVersion + "." + jfifSegment2.jfifMinorVersion;
            d = i7 != 1 ? i7 != 2 ? -1.0d : 2.54d : 1.0d;
        } else {
            JpegImageMetadata jpegImageMetadata = (JpegImageMetadata) getMetadata(byteSource, jpegImagingParameters);
            if (jpegImageMetadata != null) {
                TiffField findEXIFValue = jpegImageMetadata.findEXIFValue(TiffTagConstants.TIFF_TAG_XRESOLUTION);
                double doubleValue = findEXIFValue != null ? ((Number) findEXIFValue.getValue()).doubleValue() : -1.0d;
                TiffField findEXIFValue2 = jpegImageMetadata.findEXIFValue(TiffTagConstants.TIFF_TAG_YRESOLUTION);
                d2 = findEXIFValue2 != null ? ((Number) findEXIFValue2.getValue()).doubleValue() : -1.0d;
                TiffField findEXIFValue3 = jpegImageMetadata.findEXIFValue(TiffTagConstants.TIFF_TAG_RESOLUTION_UNIT);
                if (findEXIFValue3 != null) {
                    int intValue = ((Number) findEXIFValue3.getValue()).intValue();
                    if (intValue == 2) {
                        d3 = doubleValue;
                        d = 1.0d;
                    } else if (intValue == 3) {
                        d3 = doubleValue;
                        d = 2.54d;
                    }
                }
                d3 = doubleValue;
                d = -1.0d;
            } else {
                d = -1.0d;
                d2 = -1.0d;
                d3 = -1.0d;
            }
            str = "Jpeg/DCM";
            double d6 = d2;
            d4 = d3;
            d5 = d6;
        }
        if (d > 0.0d) {
            double d7 = d4 * d;
            jfifSegment = jfifSegment2;
            i = i6;
            double d8 = d5 * d;
            i3 = (int) Math.round(d7);
            i2 = (int) Math.round(d8);
            f = (float) (i / d8);
            f2 = (float) (i5 / d7);
        } else {
            jfifSegment = jfifSegment2;
            i = i6;
            f = -1.0f;
            f2 = -1.0f;
            i2 = -1;
            i3 = -1;
        }
        List<Segment> readSegments4 = readSegments(byteSource, new int[]{JpegConstants.COM_MARKER}, false);
        ArrayList arrayList = new ArrayList(readSegments4.size());
        Iterator<Segment> it = readSegments4.iterator();
        while (it.hasNext()) {
            arrayList.add(new String(((ComSegment) it.next()).getComment(), StandardCharsets.UTF_8));
        }
        int i8 = sofnSegment.numberOfComponents;
        int i9 = i8 * sofnSegment.precision;
        ImageFormats imageFormats = ImageFormats.JPEG;
        boolean z4 = sofnSegment.marker == 65474;
        ImageInfo.ColorType colorType6 = ImageInfo.ColorType.UNKNOWN;
        if (app14Segment == null || !app14Segment.isAdobeJpegSegment()) {
            if (jfifSegment != null) {
                if (i8 == 1) {
                    colorType5 = ImageInfo.ColorType.GRAYSCALE;
                } else if (i8 == 3) {
                    colorType5 = ImageInfo.ColorType.YCbCr;
                }
                colorType2 = colorType5;
                z = false;
                return new ImageInfo(str, i9, arrayList, imageFormats, "JPEG (Joint Photographic Experts Group) Format", i, "image/jpeg", 1, i2, f, i3, f2, i5, z4, z, false, colorType2, ImageInfo.CompressionAlgorithm.JPEG);
            }
            if (i8 == 1) {
                colorType = ImageInfo.ColorType.GRAYSCALE;
            } else {
                if (i8 == 2) {
                    colorType3 = ImageInfo.ColorType.GRAYSCALE;
                } else if (i8 == 3 || i8 == 4) {
                    boolean z5 = false;
                    boolean z6 = false;
                    boolean z7 = false;
                    boolean z8 = false;
                    boolean z9 = false;
                    for (SofnSegment.Component component : sofnSegment.getComponents()) {
                        int i10 = component.componentIdentifier;
                        if (i10 == 1) {
                            z5 = true;
                        } else if (i10 == 2) {
                            z6 = true;
                        } else if (i10 == 3) {
                            z7 = true;
                        } else if (i10 == 4) {
                            z8 = true;
                        } else {
                            z9 = true;
                        }
                    }
                    if (i8 == 3 && z5 && z6 && z7 && !z8 && !z9) {
                        colorType = ImageInfo.ColorType.YCbCr;
                    } else if (i8 == 4 && z5 && z6 && z7 && z8 && !z9) {
                        colorType3 = ImageInfo.ColorType.YCbCr;
                    } else {
                        SofnSegment.Component[] components = sofnSegment.getComponents();
                        int length = components.length;
                        int i11 = 0;
                        boolean z10 = false;
                        boolean z11 = false;
                        boolean z12 = false;
                        boolean z13 = false;
                        boolean z14 = false;
                        boolean z15 = false;
                        boolean z16 = false;
                        while (i11 < length) {
                            int i12 = length;
                            int i13 = components[i11].componentIdentifier;
                            SofnSegment.Component[] componentArr = components;
                            if (i13 == 82) {
                                z10 = true;
                            } else if (i13 == 71) {
                                z11 = true;
                            } else if (i13 == 66) {
                                z12 = true;
                            } else if (i13 == 65) {
                                z13 = true;
                            } else if (i13 == 67) {
                                z14 = true;
                            } else if (i13 == 99) {
                                z15 = true;
                            } else if (i13 == 89) {
                                z16 = true;
                            }
                            i11++;
                            length = i12;
                            components = componentArr;
                        }
                        if (z10 && z11 && z12 && !z13 && !z14 && !z15 && !z16) {
                            colorType4 = ImageInfo.ColorType.RGB;
                        } else {
                            if (z10 && z11 && z12 && z13 && !z14 && !z15 && !z16) {
                                colorType4 = ImageInfo.ColorType.RGB;
                            } else if (z16 && z14 && z15 && !z10 && !z11 && !z12 && !z13) {
                                colorType4 = ImageInfo.ColorType.YCC;
                            } else if (z16 && z14 && z15 && z13 && !z10 && !z11 && !z12) {
                                colorType4 = ImageInfo.ColorType.YCC;
                            } else {
                                SofnSegment.Component[] components2 = sofnSegment.getComponents();
                                int length2 = components2.length;
                                int i14 = Integer.MIN_VALUE;
                                int i15 = Integer.MAX_VALUE;
                                int i16 = Integer.MAX_VALUE;
                                int i17 = 0;
                                int i18 = Integer.MIN_VALUE;
                                while (i17 < length2) {
                                    SofnSegment.Component component2 = components2[i17];
                                    SofnSegment.Component[] componentArr2 = components2;
                                    if (i15 > component2.horizontalSamplingFactor) {
                                        i15 = component2.horizontalSamplingFactor;
                                    }
                                    if (i14 < component2.horizontalSamplingFactor) {
                                        i14 = component2.horizontalSamplingFactor;
                                    }
                                    if (i16 > component2.verticalSamplingFactor) {
                                        i16 = component2.verticalSamplingFactor;
                                    }
                                    if (i18 < component2.verticalSamplingFactor) {
                                        i18 = component2.verticalSamplingFactor;
                                    }
                                    i17++;
                                    components2 = componentArr2;
                                }
                                if (i15 == i14 && i16 == i18) {
                                    i4 = 3;
                                    z2 = false;
                                } else {
                                    i4 = 3;
                                    z2 = true;
                                }
                                if (i8 == i4) {
                                    if (z2) {
                                        colorType4 = ImageInfo.ColorType.YCbCr;
                                    } else {
                                        colorType4 = ImageInfo.ColorType.RGB;
                                    }
                                } else if (i8 != 4) {
                                    colorType4 = colorType6;
                                } else if (z2) {
                                    colorType4 = ImageInfo.ColorType.YCCK;
                                } else {
                                    colorType4 = ImageInfo.ColorType.CMYK;
                                }
                            }
                            z3 = true;
                            colorType2 = colorType4;
                            z = z3;
                            return new ImageInfo(str, i9, arrayList, imageFormats, "JPEG (Joint Photographic Experts Group) Format", i, "image/jpeg", 1, i2, f, i3, f2, i5, z4, z, false, colorType2, ImageInfo.CompressionAlgorithm.JPEG);
                        }
                        z3 = false;
                        colorType2 = colorType4;
                        z = z3;
                        return new ImageInfo(str, i9, arrayList, imageFormats, "JPEG (Joint Photographic Experts Group) Format", i, "image/jpeg", 1, i2, f, i3, f2, i5, z4, z, false, colorType2, ImageInfo.CompressionAlgorithm.JPEG);
                    }
                }
                colorType2 = colorType3;
                z = true;
                return new ImageInfo(str, i9, arrayList, imageFormats, "JPEG (Joint Photographic Experts Group) Format", i, "image/jpeg", 1, i2, f, i3, f2, i5, z4, z, false, colorType2, ImageInfo.CompressionAlgorithm.JPEG);
            }
            colorType2 = colorType;
            z = false;
            return new ImageInfo(str, i9, arrayList, imageFormats, "JPEG (Joint Photographic Experts Group) Format", i, "image/jpeg", 1, i2, f, i3, f2, i5, z4, z, false, colorType2, ImageInfo.CompressionAlgorithm.JPEG);
        }
        int adobeColorTransform = app14Segment.getAdobeColorTransform();
        if (adobeColorTransform != 0) {
            if (adobeColorTransform == 1) {
                colorType6 = ImageInfo.ColorType.YCbCr;
            } else if (adobeColorTransform == 2) {
                colorType6 = ImageInfo.ColorType.YCCK;
            }
        } else if (i8 == 3) {
            colorType6 = ImageInfo.ColorType.RGB;
        } else if (i8 == 4) {
            colorType6 = ImageInfo.ColorType.CMYK;
        }
        colorType2 = colorType6;
        z = false;
        return new ImageInfo(str, i9, arrayList, imageFormats, "JPEG (Joint Photographic Experts Group) Format", i, "image/jpeg", 1, i2, f, i3, f2, i5, z4, z, false, colorType2, ImageInfo.CompressionAlgorithm.JPEG);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        printWriter.println("jpeg.dumpImageFile");
        ImageInfo imageInfo = getImageInfo(byteSource);
        if (imageInfo == null) {
            return false;
        }
        imageInfo.toString(printWriter, "");
        printWriter.println("");
        List<Segment> readSegments = readSegments(byteSource, null, false);
        if (readSegments == null) {
            throw new ImageReadException("No Segments Found.");
        }
        for (int i = 0; i < readSegments.size(); i++) {
            Segment segment = readSegments.get(i);
            printWriter.println(i + ": marker: " + Integer.toHexString(segment.marker) + ", " + segment.getDescription() + " (length: " + NumberFormat.getIntegerInstance().format(segment.length) + ")");
            segment.dump(printWriter);
        }
        printWriter.println("");
        return true;
    }
}
