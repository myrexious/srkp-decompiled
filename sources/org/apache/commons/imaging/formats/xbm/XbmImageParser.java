package org.apache.commons.imaging.formats.xbm;

import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BasicCParser;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;

/* loaded from: classes2.dex */
public class XbmImageParser extends ImageParser<XbmImagingParameters> {
    private static final String DEFAULT_EXTENSION = ImageFormats.XBM.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.XBM.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, XbmImagingParameters xbmImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, XbmImagingParameters xbmImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "X BitMap";
    }

    @Override // org.apache.commons.imaging.ImageParser
    public XbmImagingParameters getDefaultParameters() {
        return new XbmImagingParameters();
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
        return new ImageFormat[]{ImageFormats.XBM};
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, XbmImagingParameters xbmImagingParameters) throws ImageReadException, IOException {
        XbmHeader readXbmHeader = readXbmHeader(byteSource);
        return new ImageInfo("XBM", 1, new ArrayList(), ImageFormats.XBM, "X BitMap", readXbmHeader.height, "image/x-xbitmap", 1, 0, 0.0f, 0, 0.0f, readXbmHeader.width, false, false, false, ImageInfo.ColorType.BW, ImageInfo.CompressionAlgorithm.NONE);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, XbmImagingParameters xbmImagingParameters) throws ImageReadException, IOException {
        XbmHeader readXbmHeader = readXbmHeader(byteSource);
        return new Dimension(readXbmHeader.width, readXbmHeader.height);
    }

    /* loaded from: classes2.dex */
    public static class XbmHeader {
        final int height;
        final int width;
        int xHot;
        int yHot;

        XbmHeader(int i, int i2, int i3, int i4) {
            this.width = i;
            this.height = i2;
            this.xHot = i3;
            this.yHot = i4;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("XbmHeader");
            printWriter.println("Width: " + this.width);
            printWriter.println("Height: " + this.height);
            if (this.xHot == -1 || this.yHot == -1) {
                return;
            }
            printWriter.println("X hot: " + this.xHot);
            printWriter.println("Y hot: " + this.yHot);
        }
    }

    /* loaded from: classes2.dex */
    public static class XbmParseResult {
        BasicCParser cParser;
        XbmHeader xbmHeader;

        private XbmParseResult() {
        }
    }

    private XbmHeader readXbmHeader(ByteSource byteSource) throws ImageReadException, IOException {
        return parseXbmHeader(byteSource).xbmHeader;
    }

    private XbmParseResult parseXbmHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            HashMap hashMap = new HashMap();
            ByteArrayOutputStream preprocess = BasicCParser.preprocess(inputStream, null, hashMap);
            int i = -1;
            int i2 = -1;
            int i3 = -1;
            int i4 = -1;
            for (Map.Entry entry : hashMap.entrySet()) {
                String str = (String) entry.getKey();
                if (str.endsWith("_width")) {
                    i = parseCIntegerLiteral((String) entry.getValue());
                } else if (str.endsWith("_height")) {
                    i2 = parseCIntegerLiteral((String) entry.getValue());
                } else if (str.endsWith("_x_hot")) {
                    i3 = parseCIntegerLiteral((String) entry.getValue());
                } else if (str.endsWith("_y_hot")) {
                    i4 = parseCIntegerLiteral((String) entry.getValue());
                }
            }
            if (i != -1) {
                if (i2 == -1) {
                    throw new ImageReadException("height not found");
                }
                XbmParseResult xbmParseResult = new XbmParseResult();
                xbmParseResult.cParser = new BasicCParser(new ByteArrayInputStream(preprocess.toByteArray()));
                xbmParseResult.xbmHeader = new XbmHeader(i, i2, i3, i4);
                if (inputStream != null) {
                    inputStream.close();
                }
                return xbmParseResult;
            }
            throw new ImageReadException("width not found");
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

    private static int parseCIntegerLiteral(String str) {
        if (str.startsWith("0")) {
            if (str.length() >= 2) {
                if (str.charAt(1) == 'x' || str.charAt(1) == 'X') {
                    return Integer.parseInt(str.substring(2), 16);
                }
                return Integer.parseInt(str.substring(1), 8);
            }
            return 0;
        }
        return Integer.parseInt(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0121 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.awt.image.BufferedImage readXbmImage(org.apache.commons.imaging.formats.xbm.XbmImageParser.XbmHeader r18, org.apache.commons.imaging.common.BasicCParser r19) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.xbm.XbmImageParser.readXbmImage(org.apache.commons.imaging.formats.xbm.XbmImageParser$XbmHeader, org.apache.commons.imaging.common.BasicCParser):java.awt.image.BufferedImage");
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        readXbmHeader(byteSource).dump(printWriter);
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public final BufferedImage getBufferedImage(ByteSource byteSource, XbmImagingParameters xbmImagingParameters) throws ImageReadException, IOException {
        XbmParseResult parseXbmHeader = parseXbmHeader(byteSource);
        return readXbmImage(parseXbmHeader.xbmHeader, parseXbmHeader.cParser);
    }

    private static String randomName() {
        UUID randomUUID = UUID.randomUUID();
        StringBuilder sb = new StringBuilder(PDPageLabelRange.STYLE_LETTERS_LOWER);
        long mostSignificantBits = randomUUID.getMostSignificantBits();
        for (int i = 56; i >= 0; i -= 8) {
            sb.append(Integer.toHexString((int) (255 & (mostSignificantBits >> i))));
        }
        long leastSignificantBits = randomUUID.getLeastSignificantBits();
        for (int i2 = 56; i2 >= 0; i2 -= 8) {
            sb.append(Integer.toHexString((int) ((leastSignificantBits >> i2) & 255)));
        }
        return sb.toString();
    }

    private static String toPrettyHex(int i) {
        String hexString = Integer.toHexString(i & 255);
        if (hexString.length() == 2) {
            return "0x" + hexString;
        }
        return "0x0" + hexString;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, XbmImagingParameters xbmImagingParameters) throws ImageWriteException, IOException {
        String randomName = randomName();
        outputStream.write(("#define " + randomName + "_width " + bufferedImage.getWidth() + "\n").getBytes(StandardCharsets.US_ASCII));
        outputStream.write(("#define " + randomName + "_height " + bufferedImage.getHeight() + "\n").getBytes(StandardCharsets.US_ASCII));
        outputStream.write(("static unsigned char " + randomName + "_bits[] = {").getBytes(StandardCharsets.US_ASCII));
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        String str = "\n  ";
        for (int i4 = 0; i4 < bufferedImage.getHeight(); i4++) {
            for (int i5 = 0; i5 < bufferedImage.getWidth(); i5++) {
                int rgb = bufferedImage.getRGB(i5, i4);
                i |= (((((rgb >> 16) & 255) + ((rgb >> 8) & 255)) + ((rgb >> 0) & 255)) / 3 > 127 ? 0 : 1) << i2;
                i2++;
                if (i2 == 8) {
                    outputStream.write(str.getBytes(StandardCharsets.US_ASCII));
                    if (i3 == 12) {
                        outputStream.write("\n  ".getBytes(StandardCharsets.US_ASCII));
                        i3 = 0;
                    }
                    outputStream.write(toPrettyHex(i).getBytes(StandardCharsets.US_ASCII));
                    i3++;
                    i = 0;
                    i2 = 0;
                    str = ",";
                }
            }
            if (i2 != 0) {
                outputStream.write(str.getBytes(StandardCharsets.US_ASCII));
                if (i3 == 12) {
                    outputStream.write("\n  ".getBytes(StandardCharsets.US_ASCII));
                    i3 = 0;
                }
                outputStream.write(toPrettyHex(i).getBytes(StandardCharsets.US_ASCII));
                i3++;
                i = 0;
                i2 = 0;
                str = ",";
            }
        }
        outputStream.write("\n};\n".getBytes(StandardCharsets.US_ASCII));
    }
}
