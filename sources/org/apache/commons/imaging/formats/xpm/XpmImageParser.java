package org.apache.commons.imaging.formats.xpm;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BasicCParser;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.SimplePalette;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public class XpmImageParser extends ImageParser<XpmImagingParameters> {
    private static Map<String, Integer> colorNames;
    private static final String DEFAULT_EXTENSION = ImageFormats.XPM.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.XPM.getExtensions();
    private static final char[] WRITE_PALETTE = {' ', '.', 'X', 'o', 'O', '+', '@', '#', '$', '%', Typography.amp, '*', '=', Soundex.SILENT_MARKER, ';', ':', Typography.greater, ',', Typography.less, '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'M', 'N', 'B', 'V', 'C', 'Z', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'P', 'I', 'U', 'Y', 'T', 'R', 'E', 'W', 'Q', '!', '~', '^', '/', '(', ')', '_', '`', '\'', ']', '[', '{', '}', '|'};

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, XpmImagingParameters xpmImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, XpmImagingParameters xpmImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "X PixMap";
    }

    private static void loadColorNames() throws ImageReadException {
        synchronized (XpmImageParser.class) {
            if (colorNames != null) {
                return;
            }
            try {
                InputStream resourceAsStream = XpmImageParser.class.getResourceAsStream("rgb.txt");
                if (resourceAsStream == null) {
                    throw new ImageReadException("Couldn't find rgb.txt in our resources");
                }
                HashMap hashMap = new HashMap();
                InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, StandardCharsets.US_ASCII);
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (readLine.charAt(0) != '!') {
                                try {
                                    int parseInt = Integer.parseInt(readLine.substring(0, 3).trim());
                                    int parseInt2 = Integer.parseInt(readLine.substring(4, 7).trim());
                                    hashMap.put(readLine.substring(11).trim().toLowerCase(Locale.ENGLISH), Integer.valueOf((parseInt << 16) | ViewCompat.MEASURED_STATE_MASK | (parseInt2 << 8) | Integer.parseInt(readLine.substring(8, 11).trim())));
                                } catch (NumberFormatException e) {
                                    throw new ImageReadException("Couldn't parse color in rgb.txt", e);
                                }
                            }
                        } else {
                            bufferedReader.close();
                            inputStreamReader.close();
                            colorNames = hashMap;
                            return;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        inputStreamReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                throw new ImageReadException("Could not parse rgb.txt", e2);
            }
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public XpmImagingParameters getDefaultParameters() {
        return new XpmImagingParameters();
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
        return new ImageFormat[]{ImageFormats.XPM};
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, XpmImagingParameters xpmImagingParameters) throws ImageReadException, IOException {
        XpmHeader readXpmHeader = readXpmHeader(byteSource);
        ImageInfo.ColorType colorType = ImageInfo.ColorType.BW;
        boolean z = false;
        for (Map.Entry<Object, PaletteEntry> entry : readXpmHeader.palette.entrySet()) {
            PaletteEntry value = entry.getValue();
            if ((value.getBestARGB() & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
                z = true;
            }
            if (value.haveColor) {
                colorType = ImageInfo.ColorType.RGB;
            } else if (colorType != ImageInfo.ColorType.RGB && (value.haveGray || value.haveGray4Level)) {
                colorType = ImageInfo.ColorType.GRAYSCALE;
            }
        }
        return new ImageInfo("XPM version 3", readXpmHeader.numCharsPerPixel * 8, new ArrayList(), ImageFormats.XPM, "X PixMap", readXpmHeader.height, "image/x-xpixmap", 1, 0, 0.0f, 0, 0.0f, readXpmHeader.width, false, z, true, colorType, ImageInfo.CompressionAlgorithm.NONE);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, XpmImagingParameters xpmImagingParameters) throws ImageReadException, IOException {
        XpmHeader readXpmHeader = readXpmHeader(byteSource);
        return new Dimension(readXpmHeader.width, readXpmHeader.height);
    }

    /* loaded from: classes2.dex */
    public static class XpmHeader {
        final int height;
        final int numCharsPerPixel;
        final int numColors;
        final Map<Object, PaletteEntry> palette = new HashMap();
        final int width;
        int xHotSpot;
        final boolean xpmExt;
        int yHotSpot;

        XpmHeader(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
            this.xHotSpot = -1;
            this.yHotSpot = -1;
            this.width = i;
            this.height = i2;
            this.numColors = i3;
            this.numCharsPerPixel = i4;
            this.xHotSpot = i5;
            this.yHotSpot = i6;
            this.xpmExt = z;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("XpmHeader");
            printWriter.println("Width: " + this.width);
            printWriter.println("Height: " + this.height);
            printWriter.println("NumColors: " + this.numColors);
            printWriter.println("NumCharsPerPixel: " + this.numCharsPerPixel);
            if (this.xHotSpot != -1 && this.yHotSpot != -1) {
                printWriter.println("X hotspot: " + this.xHotSpot);
                printWriter.println("Y hotspot: " + this.yHotSpot);
            }
            printWriter.println("XpmExt: " + this.xpmExt);
        }
    }

    /* loaded from: classes2.dex */
    public static class PaletteEntry {
        int colorArgb;
        int gray4LevelArgb;
        int grayArgb;
        boolean haveColor;
        boolean haveGray;
        boolean haveGray4Level;
        boolean haveMono;
        int index;
        int monoArgb;

        private PaletteEntry() {
            this.haveColor = false;
            this.haveGray = false;
            this.haveGray4Level = false;
            this.haveMono = false;
        }

        int getBestARGB() {
            if (this.haveColor) {
                return this.colorArgb;
            }
            if (this.haveGray) {
                return this.grayArgb;
            }
            if (this.haveGray4Level) {
                return this.gray4LevelArgb;
            }
            if (this.haveMono) {
                return this.monoArgb;
            }
            return 0;
        }
    }

    /* loaded from: classes2.dex */
    public static class XpmParseResult {
        BasicCParser cParser;
        XpmHeader xpmHeader;

        private XpmParseResult() {
        }
    }

    private XpmHeader readXpmHeader(ByteSource byteSource) throws ImageReadException, IOException {
        return parseXpmHeader(byteSource).xpmHeader;
    }

    private XpmParseResult parseXpmHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            StringBuilder sb = new StringBuilder();
            ByteArrayOutputStream preprocess = BasicCParser.preprocess(inputStream, sb, null);
            if (!"XPM".equals(sb.toString().trim())) {
                throw new ImageReadException("Parsing XPM file failed, signature isn't '/* XPM */'");
            }
            XpmParseResult xpmParseResult = new XpmParseResult();
            xpmParseResult.cParser = new BasicCParser(new ByteArrayInputStream(preprocess.toByteArray()));
            xpmParseResult.xpmHeader = parseXpmHeader(xpmParseResult.cParser);
            if (inputStream != null) {
                inputStream.close();
            }
            return xpmParseResult;
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

    private boolean parseNextString(BasicCParser basicCParser, StringBuilder sb) throws IOException, ImageReadException {
        String nextToken;
        sb.setLength(0);
        String nextToken2 = basicCParser.nextToken();
        if (nextToken2.charAt(0) != '\"') {
            throw new ImageReadException("Parsing XPM file failed, no string found where expected");
        }
        BasicCParser.unescapeString(sb, nextToken2);
        while (true) {
            nextToken = basicCParser.nextToken();
            if (nextToken.charAt(0) != '\"') {
                break;
            }
            BasicCParser.unescapeString(sb, nextToken);
        }
        if (",".equals(nextToken)) {
            return true;
        }
        if (StringSubstitutor.DEFAULT_VAR_END.equals(nextToken)) {
            return false;
        }
        throw new ImageReadException("Parsing XPM file failed, no ',' or '}' found where expected");
    }

    private XpmHeader parseXpmValuesSection(String str) throws ImageReadException {
        int i;
        int i2;
        boolean z;
        String[] strArr = BasicCParser.tokenizeRow(str);
        if (strArr.length < 4 || strArr.length > 7) {
            throw new ImageReadException("Parsing XPM file failed, <Values> section has incorrect tokens");
        }
        try {
            int parseInt = Integer.parseInt(strArr[0]);
            int parseInt2 = Integer.parseInt(strArr[1]);
            int parseInt3 = Integer.parseInt(strArr[2]);
            int parseInt4 = Integer.parseInt(strArr[3]);
            if (strArr.length >= 6) {
                i2 = Integer.parseInt(strArr[4]);
                i = Integer.parseInt(strArr[5]);
            } else {
                i = -1;
                i2 = -1;
            }
            if (strArr.length != 5 && strArr.length != 7) {
                z = false;
                return new XpmHeader(parseInt, parseInt2, parseInt3, parseInt4, i2, i, z);
            }
            if (!"XPMEXT".equals(strArr[strArr.length - 1])) {
                throw new ImageReadException("Parsing XPM file failed, can't parse <Values> section XPMEXT");
            }
            z = true;
            return new XpmHeader(parseInt, parseInt2, parseInt3, parseInt4, i2, i, z);
        } catch (NumberFormatException e) {
            throw new ImageReadException("Parsing XPM file failed, error parsing <Values> section", e);
        }
    }

    private int parseColor(String str) throws ImageReadException {
        if (str.charAt(0) == '#') {
            String substring = str.substring(1);
            if (substring.length() == 3) {
                int parseInt = Integer.parseInt(substring.substring(0, 1), 16);
                int parseInt2 = Integer.parseInt(substring.substring(1, 2), 16);
                return (Integer.parseInt(substring.substring(2, 3), 16) << 4) | (parseInt << 20) | ViewCompat.MEASURED_STATE_MASK | (parseInt2 << 12);
            } else if (substring.length() == 6) {
                return Integer.parseInt(substring, 16) | ViewCompat.MEASURED_STATE_MASK;
            } else {
                if (substring.length() == 9) {
                    return Integer.parseInt(substring.substring(6, 7), 16) | (Integer.parseInt(substring.substring(0, 1), 16) << 16) | ViewCompat.MEASURED_STATE_MASK | (Integer.parseInt(substring.substring(3, 4), 16) << 8);
                } else if (substring.length() == 12) {
                    return Integer.parseInt(substring.substring(8, 9), 16) | (Integer.parseInt(substring.substring(0, 1), 16) << 16) | ViewCompat.MEASURED_STATE_MASK | (Integer.parseInt(substring.substring(4, 5), 16) << 8);
                } else if (substring.length() == 24) {
                    return Integer.parseInt(substring.substring(16, 17), 16) | (Integer.parseInt(substring.substring(0, 1), 16) << 16) | ViewCompat.MEASURED_STATE_MASK | (Integer.parseInt(substring.substring(8, 9), 16) << 8);
                } else {
                    return 0;
                }
            }
        } else if (str.charAt(0) == '%') {
            throw new ImageReadException("HSV colors are not implemented even in the XPM specification!");
        } else {
            if ("None".equals(str)) {
                return 0;
            }
            loadColorNames();
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            if (colorNames.containsKey(lowerCase)) {
                return colorNames.get(lowerCase).intValue();
            }
            return 0;
        }
    }

    private void populatePaletteEntry(PaletteEntry paletteEntry, String str, String str2) throws ImageReadException {
        if (OperatorName.MOVE_TO.equals(str)) {
            paletteEntry.monoArgb = parseColor(str2);
            paletteEntry.haveMono = true;
        } else if ("g4".equals(str)) {
            paletteEntry.gray4LevelArgb = parseColor(str2);
            paletteEntry.haveGray4Level = true;
        } else if (OperatorName.NON_STROKING_GRAY.equals(str)) {
            paletteEntry.grayArgb = parseColor(str2);
            paletteEntry.haveGray = true;
        } else if (OperatorName.CLOSE_AND_STROKE.equals(str)) {
            paletteEntry.colorArgb = parseColor(str2);
            paletteEntry.haveColor = true;
        } else if (OperatorName.CURVE_TO.equals(str)) {
            paletteEntry.colorArgb = parseColor(str2);
            paletteEntry.haveColor = true;
        }
    }

    private void parsePaletteEntries(XpmHeader xpmHeader, BasicCParser basicCParser) throws IOException, ImageReadException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < xpmHeader.numColors; i++) {
            sb.setLength(0);
            if (!parseNextString(basicCParser, sb)) {
                throw new ImageReadException("Parsing XPM file failed, file ended while reading palette");
            }
            String substring = sb.substring(0, xpmHeader.numCharsPerPixel);
            String[] strArr = BasicCParser.tokenizeRow(sb.substring(xpmHeader.numCharsPerPixel));
            PaletteEntry paletteEntry = new PaletteEntry();
            paletteEntry.index = i;
            StringBuilder sb2 = new StringBuilder();
            int i2 = Integer.MIN_VALUE;
            for (int i3 = 0; i3 < strArr.length; i3++) {
                String str = strArr[i3];
                if ((i2 < i3 + (-1) && OperatorName.MOVE_TO.equals(str)) || "g4".equals(str) || OperatorName.NON_STROKING_GRAY.equals(str) || OperatorName.CURVE_TO.equals(str) || OperatorName.CLOSE_AND_STROKE.equals(str)) {
                    if (i2 >= 0) {
                        String str2 = strArr[i2];
                        String sb3 = sb2.toString();
                        sb2.setLength(0);
                        populatePaletteEntry(paletteEntry, str2, sb3);
                    }
                    i2 = i3;
                } else if (i2 < 0) {
                    break;
                } else {
                    if (sb2.length() > 0) {
                        sb2.append(' ');
                    }
                    sb2.append(str);
                }
            }
            if (i2 >= 0 && sb2.length() > 0) {
                String str3 = strArr[i2];
                String sb4 = sb2.toString();
                sb2.setLength(0);
                populatePaletteEntry(paletteEntry, str3, sb4);
            }
            xpmHeader.palette.put(substring, paletteEntry);
        }
    }

    private XpmHeader parseXpmHeader(BasicCParser basicCParser) throws ImageReadException, IOException {
        if (!"static".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no 'static' token");
        }
        if (!"char".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no 'char' token");
        }
        if (!"*".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no '*' token");
        }
        String nextToken = basicCParser.nextToken();
        if (nextToken == null) {
            throw new ImageReadException("Parsing XPM file failed, no variable name");
        }
        if (nextToken.charAt(0) != '_' && !Character.isLetter(nextToken.charAt(0))) {
            throw new ImageReadException("Parsing XPM file failed, variable name doesn't start with letter or underscore");
        }
        for (int i = 0; i < nextToken.length(); i++) {
            char charAt = nextToken.charAt(i);
            if (!Character.isLetterOrDigit(charAt) && charAt != '_') {
                throw new ImageReadException("Parsing XPM file failed, variable name contains non-letter non-digit non-underscore");
            }
        }
        if (!"[".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no '[' token");
        }
        if (!"]".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no ']' token");
        }
        if (!"=".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no '=' token");
        }
        if (!"{".equals(basicCParser.nextToken())) {
            throw new ImageReadException("Parsing XPM file failed, no '{' token");
        }
        StringBuilder sb = new StringBuilder();
        if (!parseNextString(basicCParser, sb)) {
            throw new ImageReadException("Parsing XPM file failed, file too short");
        }
        XpmHeader parseXpmValuesSection = parseXpmValuesSection(sb.toString());
        parsePaletteEntries(parseXpmValuesSection, basicCParser);
        return parseXpmValuesSection;
    }

    private BufferedImage readXpmImage(XpmHeader xpmHeader, BasicCParser basicCParser) throws ImageReadException, IOException {
        IndexColorModel directColorModel;
        WritableRaster createPackedRaster;
        char c;
        int i = 1;
        if (xpmHeader.palette.size() <= 256) {
            int[] iArr = new int[xpmHeader.palette.size()];
            for (Map.Entry<Object, PaletteEntry> entry : xpmHeader.palette.entrySet()) {
                PaletteEntry value = entry.getValue();
                iArr[value.index] = value.getBestARGB();
            }
            directColorModel = new IndexColorModel(8, xpmHeader.palette.size(), iArr, 0, true, -1, 0);
            createPackedRaster = Raster.createInterleavedRaster(0, xpmHeader.width, xpmHeader.height, 1, (Point) null);
            c = '\b';
        } else if (xpmHeader.palette.size() <= 65536) {
            int[] iArr2 = new int[xpmHeader.palette.size()];
            for (Map.Entry<Object, PaletteEntry> entry2 : xpmHeader.palette.entrySet()) {
                PaletteEntry value2 = entry2.getValue();
                iArr2[value2.index] = value2.getBestARGB();
            }
            directColorModel = new IndexColorModel(16, xpmHeader.palette.size(), iArr2, 0, true, -1, 1);
            createPackedRaster = Raster.createInterleavedRaster(1, xpmHeader.width, xpmHeader.height, 1, (Point) null);
            c = 16;
        } else {
            directColorModel = new DirectColorModel(32, 16711680, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, (int) ViewCompat.MEASURED_STATE_MASK);
            createPackedRaster = Raster.createPackedRaster(3, xpmHeader.width, xpmHeader.height, new int[]{16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, ViewCompat.MEASURED_STATE_MASK}, (Point) null);
            c = ' ';
        }
        BufferedImage bufferedImage = new BufferedImage(directColorModel, createPackedRaster, directColorModel.isAlphaPremultiplied(), new Properties());
        DataBuffer dataBuffer = createPackedRaster.getDataBuffer();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        boolean z = true;
        while (i2 < xpmHeader.height) {
            sb.setLength(0);
            z = parseNextString(basicCParser, sb);
            if (i2 < xpmHeader.height - i && !z) {
                throw new ImageReadException("Parsing XPM file failed, insufficient image rows in file");
            }
            int i3 = xpmHeader.width * i2;
            int i4 = 0;
            while (i4 < xpmHeader.width) {
                int i5 = i4 + 1;
                String substring = sb.substring(xpmHeader.numCharsPerPixel * i4, xpmHeader.numCharsPerPixel * i5);
                PaletteEntry paletteEntry = xpmHeader.palette.get(substring);
                if (paletteEntry == null) {
                    throw new ImageReadException("No palette entry was defined for " + substring);
                }
                if (c <= 16) {
                    dataBuffer.setElem(i4 + i3, paletteEntry.index);
                } else {
                    dataBuffer.setElem(i4 + i3, paletteEntry.getBestARGB());
                }
                i4 = i5;
            }
            i2++;
            i = 1;
        }
        while (z) {
            sb.setLength(0);
            z = parseNextString(basicCParser, sb);
        }
        if (";".equals(basicCParser.nextToken())) {
            return bufferedImage;
        }
        throw new ImageReadException("Last token wasn't ';'");
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        readXpmHeader(byteSource).dump(printWriter);
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public final BufferedImage getBufferedImage(ByteSource byteSource, XpmImagingParameters xpmImagingParameters) throws ImageReadException, IOException {
        XpmParseResult parseXpmHeader = parseXpmHeader(byteSource);
        return readXpmImage(parseXpmHeader.xpmHeader, parseXpmHeader.cParser);
    }

    private String randomName() {
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

    private String pixelsForIndex(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = 1;
        for (int i4 = 1; i4 < i2; i4++) {
            i3 *= WRITE_PALETTE.length;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i / i3;
            i -= i6 * i3;
            char[] cArr = WRITE_PALETTE;
            i3 /= cArr.length;
            sb.append(cArr[i6]);
        }
        return sb.toString();
    }

    private String toColor(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() < 6) {
            char[] cArr = new char[6 - hexString.length()];
            Arrays.fill(cArr, '0');
            return "#" + new String(cArr) + hexString;
        }
        return "#" + hexString;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, XpmImagingParameters xpmImagingParameters) throws ImageWriteException, IOException {
        String pixelsForIndex;
        PaletteFactory paletteFactory = new PaletteFactory();
        int i = 1;
        boolean hasTransparency = paletteFactory.hasTransparency(bufferedImage, 1);
        int length = WRITE_PALETTE.length;
        SimplePalette simplePalette = null;
        while (simplePalette == null) {
            simplePalette = paletteFactory.makeExactRgbPaletteSimple(bufferedImage, hasTransparency ? length - 1 : length);
            char[] cArr = WRITE_PALETTE;
            int i2 = i + 1;
            long j = i2;
            if (cArr.length * length > 2147483647L) {
                throw new ImageWriteException("Xpm: Can't write images with more than Integer.MAX_VALUE colors.");
            }
            if (j > 2147483647L) {
                throw new ImageWriteException("Xpm: Can't write images with more than Integer.MAX_VALUE chars per pixel.");
            }
            if (simplePalette == null) {
                length *= cArr.length;
                i = i2;
            }
        }
        int length2 = simplePalette.length();
        if (hasTransparency) {
            length2++;
        }
        outputStream.write("/* XPM */\n".getBytes(StandardCharsets.US_ASCII));
        outputStream.write(("static char *" + randomName() + "[] = {\n").getBytes(StandardCharsets.US_ASCII));
        outputStream.write((OperatorName.SHOW_TEXT_LINE_AND_SPACE + bufferedImage.getWidth() + StringUtils.SPACE + bufferedImage.getHeight() + StringUtils.SPACE + length2 + StringUtils.SPACE + i + "\",\n").getBytes(StandardCharsets.US_ASCII));
        int i3 = 0;
        while (i3 < length2) {
            outputStream.write((OperatorName.SHOW_TEXT_LINE_AND_SPACE + pixelsForIndex(i3, i) + " c " + (i3 < simplePalette.length() ? toColor(simplePalette.getEntry(i3)) : "None") + "\",\n").getBytes(StandardCharsets.US_ASCII));
            i3++;
        }
        String str = "";
        int i4 = 0;
        while (i4 < bufferedImage.getHeight()) {
            outputStream.write(str.getBytes(StandardCharsets.US_ASCII));
            outputStream.write(OperatorName.SHOW_TEXT_LINE_AND_SPACE.getBytes(StandardCharsets.US_ASCII));
            for (int i5 = 0; i5 < bufferedImage.getWidth(); i5++) {
                int rgb = bufferedImage.getRGB(i5, i4);
                if (((-16777216) & rgb) == 0) {
                    pixelsForIndex = pixelsForIndex(simplePalette.length(), i);
                } else {
                    pixelsForIndex = pixelsForIndex(simplePalette.getPaletteIndex(rgb & ViewCompat.MEASURED_SIZE_MASK), i);
                }
                outputStream.write(pixelsForIndex.getBytes(StandardCharsets.US_ASCII));
            }
            outputStream.write(OperatorName.SHOW_TEXT_LINE_AND_SPACE.getBytes(StandardCharsets.US_ASCII));
            i4++;
            str = ",\n";
        }
        outputStream.write("\n};\n".getBytes(StandardCharsets.US_ASCII));
    }
}
