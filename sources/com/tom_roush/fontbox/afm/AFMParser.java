package com.tom_roush.fontbox.afm;

import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.fontbox.util.Charsets;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* loaded from: classes3.dex */
public class AFMParser {
    public static final String ASCENDER = "Ascender";
    private static final int BITS_IN_HEX = 16;
    public static final String CAP_HEIGHT = "CapHeight";
    public static final String CC = "CC";
    public static final String CHARACTERS = "Characters";
    public static final String CHARACTER_SET = "CharacterSet";
    public static final String CHARMETRICS_B = "B";
    public static final String CHARMETRICS_C = "C";
    public static final String CHARMETRICS_CH = "CH";
    public static final String CHARMETRICS_L = "L";
    public static final String CHARMETRICS_N = "N";
    public static final String CHARMETRICS_VV = "VV";
    public static final String CHARMETRICS_W = "W";
    public static final String CHARMETRICS_W0 = "W0";
    public static final String CHARMETRICS_W0X = "W0X";
    public static final String CHARMETRICS_W0Y = "W0Y";
    public static final String CHARMETRICS_W1 = "W1";
    public static final String CHARMETRICS_W1X = "W1X";
    public static final String CHARMETRICS_W1Y = "W1Y";
    public static final String CHARMETRICS_WX = "WX";
    public static final String CHARMETRICS_WY = "WY";
    public static final String CHAR_WIDTH = "CharWidth";
    public static final String COMMENT = "Comment";
    public static final String DESCENDER = "Descender";
    public static final String ENCODING_SCHEME = "EncodingScheme";
    public static final String END_CHAR_METRICS = "EndCharMetrics";
    public static final String END_COMPOSITES = "EndComposites";
    public static final String END_FONT_METRICS = "EndFontMetrics";
    public static final String END_KERN_DATA = "EndKernData";
    public static final String END_KERN_PAIRS = "EndKernPairs";
    public static final String END_TRACK_KERN = "EndTrackKern";
    public static final String ESC_CHAR = "EscChar";
    public static final String FAMILY_NAME = "FamilyName";
    public static final String FONT_BBOX = "FontBBox";
    public static final String FONT_NAME = "FontName";
    public static final String FULL_NAME = "FullName";
    public static final String IS_BASE_FONT = "IsBaseFont";
    public static final String IS_FIXED_PITCH = "IsFixedPitch";
    public static final String IS_FIXED_V = "IsFixedV";
    public static final String ITALIC_ANGLE = "ItalicAngle";
    public static final String KERN_PAIR_KP = "KP";
    public static final String KERN_PAIR_KPH = "KPH";
    public static final String KERN_PAIR_KPX = "KPX";
    public static final String KERN_PAIR_KPY = "KPY";
    public static final String MAPPING_SCHEME = "MappingScheme";
    public static final String NOTICE = "Notice";
    public static final String PCC = "PCC";
    public static final String START_CHAR_METRICS = "StartCharMetrics";
    public static final String START_COMPOSITES = "StartComposites";
    public static final String START_FONT_METRICS = "StartFontMetrics";
    public static final String START_KERN_DATA = "StartKernData";
    public static final String START_KERN_PAIRS = "StartKernPairs";
    public static final String START_KERN_PAIRS0 = "StartKernPairs0";
    public static final String START_KERN_PAIRS1 = "StartKernPairs1";
    public static final String START_TRACK_KERN = "StartTrackKern";
    public static final String STD_HW = "StdHW";
    public static final String STD_VW = "StdVW";
    public static final String UNDERLINE_POSITION = "UnderlinePosition";
    public static final String UNDERLINE_THICKNESS = "UnderlineThickness";
    public static final String VERSION = "Version";
    public static final String V_VECTOR = "VVector";
    public static final String WEIGHT = "Weight";
    public static final String X_HEIGHT = "XHeight";
    private final InputStream input;

    private boolean isEOL(int i) {
        return i == 13 || i == 10;
    }

    private boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 13 || i == 10;
    }

    public AFMParser(InputStream inputStream) {
        this.input = inputStream;
    }

    public FontMetrics parse() throws IOException {
        return parseFontMetric(false);
    }

    public FontMetrics parse(boolean z) throws IOException {
        return parseFontMetric(z);
    }

    private FontMetrics parseFontMetric(boolean z) throws IOException {
        String readString;
        FontMetrics fontMetrics = new FontMetrics();
        String readString2 = readString();
        if (!START_FONT_METRICS.equals(readString2)) {
            throw new IOException("Error: The AFM file should start with StartFontMetrics and not '" + readString2 + OperatorName.SHOW_TEXT_LINE);
        }
        fontMetrics.setAFMVersion(readFloat());
        boolean z2 = false;
        while (true) {
            readString = readString();
            if (END_FONT_METRICS.equals(readString)) {
                break;
            } else if (FONT_NAME.equals(readString)) {
                fontMetrics.setFontName(readLine());
            } else if (FULL_NAME.equals(readString)) {
                fontMetrics.setFullName(readLine());
            } else if (FAMILY_NAME.equals(readString)) {
                fontMetrics.setFamilyName(readLine());
            } else if (WEIGHT.equals(readString)) {
                fontMetrics.setWeight(readLine());
            } else if (FONT_BBOX.equals(readString)) {
                BoundingBox boundingBox = new BoundingBox();
                boundingBox.setLowerLeftX(readFloat());
                boundingBox.setLowerLeftY(readFloat());
                boundingBox.setUpperRightX(readFloat());
                boundingBox.setUpperRightY(readFloat());
                fontMetrics.setFontBBox(boundingBox);
            } else if (VERSION.equals(readString)) {
                fontMetrics.setFontVersion(readLine());
            } else if (NOTICE.equals(readString)) {
                fontMetrics.setNotice(readLine());
            } else if (ENCODING_SCHEME.equals(readString)) {
                fontMetrics.setEncodingScheme(readLine());
            } else if (MAPPING_SCHEME.equals(readString)) {
                fontMetrics.setMappingScheme(readInt());
            } else if (ESC_CHAR.equals(readString)) {
                fontMetrics.setEscChar(readInt());
            } else if (CHARACTER_SET.equals(readString)) {
                fontMetrics.setCharacterSet(readLine());
            } else if (CHARACTERS.equals(readString)) {
                fontMetrics.setCharacters(readInt());
            } else if (IS_BASE_FONT.equals(readString)) {
                fontMetrics.setIsBaseFont(readBoolean());
            } else if (V_VECTOR.equals(readString)) {
                fontMetrics.setVVector(new float[]{readFloat(), readFloat()});
            } else if (IS_FIXED_V.equals(readString)) {
                fontMetrics.setIsFixedV(readBoolean());
            } else if (CAP_HEIGHT.equals(readString)) {
                fontMetrics.setCapHeight(readFloat());
            } else if (X_HEIGHT.equals(readString)) {
                fontMetrics.setXHeight(readFloat());
            } else if (ASCENDER.equals(readString)) {
                fontMetrics.setAscender(readFloat());
            } else if (DESCENDER.equals(readString)) {
                fontMetrics.setDescender(readFloat());
            } else if (STD_HW.equals(readString)) {
                fontMetrics.setStandardHorizontalWidth(readFloat());
            } else if (STD_VW.equals(readString)) {
                fontMetrics.setStandardVerticalWidth(readFloat());
            } else if ("Comment".equals(readString)) {
                fontMetrics.addComment(readLine());
            } else if (UNDERLINE_POSITION.equals(readString)) {
                fontMetrics.setUnderlinePosition(readFloat());
            } else if (UNDERLINE_THICKNESS.equals(readString)) {
                fontMetrics.setUnderlineThickness(readFloat());
            } else if (ITALIC_ANGLE.equals(readString)) {
                fontMetrics.setItalicAngle(readFloat());
            } else if (CHAR_WIDTH.equals(readString)) {
                fontMetrics.setCharWidth(new float[]{readFloat(), readFloat()});
            } else if (IS_FIXED_PITCH.equals(readString)) {
                fontMetrics.setFixedPitch(readBoolean());
            } else if (START_CHAR_METRICS.equals(readString)) {
                int readInt = readInt();
                ArrayList arrayList = new ArrayList(readInt);
                for (int i = 0; i < readInt; i++) {
                    arrayList.add(parseCharMetric());
                }
                String readString3 = readString();
                if (!readString3.equals(END_CHAR_METRICS)) {
                    throw new IOException("Error: Expected 'EndCharMetrics' actual '" + readString3 + OperatorName.SHOW_TEXT_LINE);
                }
                fontMetrics.setCharMetrics(arrayList);
                z2 = true;
            } else if (!z && START_COMPOSITES.equals(readString)) {
                int readInt2 = readInt();
                for (int i2 = 0; i2 < readInt2; i2++) {
                    fontMetrics.addComposite(parseComposite());
                }
                String readString4 = readString();
                if (!readString4.equals(END_COMPOSITES)) {
                    throw new IOException("Error: Expected 'EndComposites' actual '" + readString4 + OperatorName.SHOW_TEXT_LINE);
                }
            } else if (z || !START_KERN_DATA.equals(readString)) {
                break;
            } else {
                parseKernData(fontMetrics);
            }
        }
        if (!z || !z2) {
            throw new IOException("Unknown AFM key '" + readString + OperatorName.SHOW_TEXT_LINE);
        }
        return fontMetrics;
    }

    private void parseKernData(FontMetrics fontMetrics) throws IOException {
        while (true) {
            String readString = readString();
            if (readString.equals(END_KERN_DATA)) {
                return;
            }
            int i = 0;
            if (START_TRACK_KERN.equals(readString)) {
                int readInt = readInt();
                while (i < readInt) {
                    TrackKern trackKern = new TrackKern();
                    trackKern.setDegree(readInt());
                    trackKern.setMinPointSize(readFloat());
                    trackKern.setMinKern(readFloat());
                    trackKern.setMaxPointSize(readFloat());
                    trackKern.setMaxKern(readFloat());
                    fontMetrics.addTrackKern(trackKern);
                    i++;
                }
                String readString2 = readString();
                if (!readString2.equals(END_TRACK_KERN)) {
                    throw new IOException("Error: Expected 'EndTrackKern' actual '" + readString2 + OperatorName.SHOW_TEXT_LINE);
                }
            } else if (START_KERN_PAIRS.equals(readString)) {
                int readInt2 = readInt();
                while (i < readInt2) {
                    fontMetrics.addKernPair(parseKernPair());
                    i++;
                }
                String readString3 = readString();
                if (!readString3.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + readString3 + OperatorName.SHOW_TEXT_LINE);
                }
            } else if (START_KERN_PAIRS0.equals(readString)) {
                int readInt3 = readInt();
                while (i < readInt3) {
                    fontMetrics.addKernPair0(parseKernPair());
                    i++;
                }
                String readString4 = readString();
                if (!readString4.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + readString4 + OperatorName.SHOW_TEXT_LINE);
                }
            } else if (START_KERN_PAIRS1.equals(readString)) {
                int readInt4 = readInt();
                while (i < readInt4) {
                    fontMetrics.addKernPair1(parseKernPair());
                    i++;
                }
                String readString5 = readString();
                if (!readString5.equals(END_KERN_PAIRS)) {
                    throw new IOException("Error: Expected 'EndKernPairs' actual '" + readString5 + OperatorName.SHOW_TEXT_LINE);
                }
            } else {
                throw new IOException("Unknown kerning data type '" + readString + OperatorName.SHOW_TEXT_LINE);
            }
        }
    }

    private KernPair parseKernPair() throws IOException {
        KernPair kernPair = new KernPair();
        String readString = readString();
        if (KERN_PAIR_KP.equals(readString)) {
            kernPair.setFirstKernCharacter(readString());
            kernPair.setSecondKernCharacter(readString());
            kernPair.setX(readFloat());
            kernPair.setY(readFloat());
        } else if (KERN_PAIR_KPH.equals(readString)) {
            kernPair.setFirstKernCharacter(hexToString(readString()));
            kernPair.setSecondKernCharacter(hexToString(readString()));
            kernPair.setX(readFloat());
            kernPair.setY(readFloat());
        } else if (KERN_PAIR_KPX.equals(readString)) {
            kernPair.setFirstKernCharacter(readString());
            kernPair.setSecondKernCharacter(readString());
            kernPair.setX(readFloat());
            kernPair.setY(0.0f);
        } else if (KERN_PAIR_KPY.equals(readString)) {
            kernPair.setFirstKernCharacter(readString());
            kernPair.setSecondKernCharacter(readString());
            kernPair.setX(0.0f);
            kernPair.setY(readFloat());
        } else {
            throw new IOException("Error expected kern pair command actual='" + readString + OperatorName.SHOW_TEXT_LINE);
        }
        return kernPair;
    }

    private String hexToString(String str) throws IOException {
        if (str.length() < 2) {
            throw new IOException("Error: Expected hex string of length >= 2 not='" + str);
        }
        if (str.charAt(0) != '<' || str.charAt(str.length() - 1) != '>') {
            throw new IOException("String should be enclosed by angle brackets '" + str + OperatorName.SHOW_TEXT_LINE);
        }
        String substring = str.substring(1, str.length() - 1);
        byte[] bArr = new byte[substring.length() / 2];
        for (int i = 0; i < substring.length(); i += 2) {
            try {
                bArr[i / 2] = (byte) Integer.parseInt(Character.toString(substring.charAt(i)) + substring.charAt(i + 1), 16);
            } catch (NumberFormatException e) {
                throw new IOException("Error parsing AFM file:" + e);
            }
        }
        return new String(bArr, Charsets.ISO_8859_1);
    }

    private Composite parseComposite() throws IOException {
        Composite composite = new Composite();
        StringTokenizer stringTokenizer = new StringTokenizer(readLine(), " ;");
        String nextToken = stringTokenizer.nextToken();
        if (!nextToken.equals(CC)) {
            throw new IOException("Expected 'CC' actual='" + nextToken + OperatorName.SHOW_TEXT_LINE);
        }
        composite.setName(stringTokenizer.nextToken());
        try {
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = 0; i < parseInt; i++) {
                CompositePart compositePart = new CompositePart();
                String nextToken2 = stringTokenizer.nextToken();
                if (!nextToken2.equals(PCC)) {
                    throw new IOException("Expected 'PCC' actual='" + nextToken2 + OperatorName.SHOW_TEXT_LINE);
                }
                String nextToken3 = stringTokenizer.nextToken();
                try {
                    int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
                    int parseInt3 = Integer.parseInt(stringTokenizer.nextToken());
                    compositePart.setName(nextToken3);
                    compositePart.setXDisplacement(parseInt2);
                    compositePart.setYDisplacement(parseInt3);
                    composite.addPart(compositePart);
                } catch (NumberFormatException e) {
                    throw new IOException("Error parsing AFM document:" + e);
                }
            }
            return composite;
        } catch (NumberFormatException e2) {
            throw new IOException("Error parsing AFM document:" + e2);
        }
    }

    private CharMetric parseCharMetric() throws IOException {
        CharMetric charMetric = new CharMetric();
        StringTokenizer stringTokenizer = new StringTokenizer(readLine());
        while (stringTokenizer.hasMoreTokens()) {
            try {
                String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("C")) {
                    charMetric.setCharacterCode(Integer.parseInt(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_CH)) {
                    charMetric.setCharacterCode(Integer.parseInt(stringTokenizer.nextToken(), 16));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_WX)) {
                    charMetric.setWx(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W0X)) {
                    charMetric.setW0x(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W1X)) {
                    charMetric.setW1x(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_WY)) {
                    charMetric.setWy(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W0Y)) {
                    charMetric.setW0y(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W1Y)) {
                    charMetric.setW1y(Float.parseFloat(stringTokenizer.nextToken()));
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("W")) {
                    charMetric.setW(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W0)) {
                    charMetric.setW0(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_W1)) {
                    charMetric.setW1(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals(CHARMETRICS_VV)) {
                    charMetric.setVv(new float[]{Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())});
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("N")) {
                    charMetric.setName(stringTokenizer.nextToken());
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("B")) {
                    BoundingBox boundingBox = new BoundingBox();
                    boundingBox.setLowerLeftX(Float.parseFloat(stringTokenizer.nextToken()));
                    boundingBox.setLowerLeftY(Float.parseFloat(stringTokenizer.nextToken()));
                    boundingBox.setUpperRightX(Float.parseFloat(stringTokenizer.nextToken()));
                    boundingBox.setUpperRightY(Float.parseFloat(stringTokenizer.nextToken()));
                    charMetric.setBoundingBox(boundingBox);
                    verifySemicolon(stringTokenizer);
                } else if (nextToken.equals("L")) {
                    Ligature ligature = new Ligature();
                    ligature.setSuccessor(stringTokenizer.nextToken());
                    ligature.setLigature(stringTokenizer.nextToken());
                    charMetric.addLigature(ligature);
                    verifySemicolon(stringTokenizer);
                } else {
                    throw new IOException("Unknown CharMetrics command '" + nextToken + OperatorName.SHOW_TEXT_LINE);
                }
            } catch (NumberFormatException e) {
                throw new IOException("Error: Corrupt AFM document:" + e);
            }
        }
        return charMetric;
    }

    private void verifySemicolon(StringTokenizer stringTokenizer) throws IOException {
        if (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!";".equals(nextToken)) {
                throw new IOException("Error: Expected semicolon in stream actual='" + nextToken + OperatorName.SHOW_TEXT_LINE);
            }
            return;
        }
        throw new IOException("CharMetrics is missing a semicolon after a command");
    }

    private boolean readBoolean() throws IOException {
        return Boolean.parseBoolean(readString());
    }

    private int readInt() throws IOException {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing AFM document:" + e);
        }
    }

    private float readFloat() throws IOException {
        return Float.parseFloat(readString());
    }

    private String readLine() throws IOException {
        StringBuilder sb = new StringBuilder(60);
        int read = this.input.read();
        while (isWhitespace(read)) {
            read = this.input.read();
        }
        sb.append((char) read);
        int read2 = this.input.read();
        while (read2 != -1 && !isEOL(read2)) {
            sb.append((char) read2);
            read2 = this.input.read();
        }
        return sb.toString();
    }

    private String readString() throws IOException {
        StringBuilder sb = new StringBuilder(24);
        int read = this.input.read();
        while (isWhitespace(read)) {
            read = this.input.read();
        }
        sb.append((char) read);
        int read2 = this.input.read();
        while (read2 != -1 && !isWhitespace(read2)) {
            sb.append((char) read2);
            read2 = this.input.read();
        }
        return sb.toString();
    }
}
