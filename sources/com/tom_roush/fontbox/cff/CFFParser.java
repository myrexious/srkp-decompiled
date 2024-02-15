package com.tom_roush.fontbox.cff;

import android.util.Log;
import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.fontbox.cff.CFFOperator;
import com.tom_roush.fontbox.ttf.CFFTable;
import com.tom_roush.fontbox.util.Charsets;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.xmpbox.type.VersionType;

/* loaded from: classes3.dex */
public class CFFParser {
    private static final String TAG_OTTO = "OTTO";
    private static final String TAG_TTCF = "ttcf";
    private static final String TAG_TTFONLY = "\u0000\u0001\u0000\u0000";
    private String debugFontName;
    private ByteSource source;
    private String[] stringIndex = null;

    /* loaded from: classes3.dex */
    public interface ByteSource {
        byte[] getBytes() throws IOException;
    }

    public List<CFFFont> parse(byte[] bArr, ByteSource byteSource) throws IOException {
        this.source = byteSource;
        return parse(bArr);
    }

    public List<CFFFont> parse(byte[] bArr) throws IOException {
        CFFDataInput cFFDataInput = new CFFDataInput(bArr);
        String readTagName = readTagName(cFFDataInput);
        if (TAG_OTTO.equals(readTagName)) {
            cFFDataInput = createTaggedCFFDataInput(cFFDataInput, bArr);
        } else if (TAG_TTCF.equals(readTagName)) {
            throw new IOException("True Type Collection fonts are not supported.");
        } else {
            if (TAG_TTFONLY.equals(readTagName)) {
                throw new IOException("OpenType fonts containing a true type font are not supported.");
            }
            cFFDataInput.setPosition(0);
        }
        readHeader(cFFDataInput);
        String[] readStringIndexData = readStringIndexData(cFFDataInput);
        if (readStringIndexData == null) {
            throw new IOException("Name index missing in CFF font");
        }
        byte[][] readIndexData = readIndexData(cFFDataInput);
        this.stringIndex = readStringIndexData(cFFDataInput);
        byte[][] readIndexData2 = readIndexData(cFFDataInput);
        ArrayList arrayList = new ArrayList(readStringIndexData.length);
        for (int i = 0; i < readStringIndexData.length; i++) {
            CFFFont parseFont = parseFont(cFFDataInput, readStringIndexData[i], readIndexData[i]);
            parseFont.setGlobalSubrIndex(readIndexData2);
            parseFont.setData(this.source);
            arrayList.add(parseFont);
        }
        return arrayList;
    }

    private CFFDataInput createTaggedCFFDataInput(CFFDataInput cFFDataInput, byte[] bArr) throws IOException {
        short readShort = cFFDataInput.readShort();
        cFFDataInput.readShort();
        cFFDataInput.readShort();
        cFFDataInput.readShort();
        for (int i = 0; i < readShort; i++) {
            String readTagName = readTagName(cFFDataInput);
            readLong(cFFDataInput);
            long readLong = readLong(cFFDataInput);
            long readLong2 = readLong(cFFDataInput);
            if (CFFTable.TAG.equals(readTagName)) {
                return new CFFDataInput(Arrays.copyOfRange(bArr, (int) readLong, (int) (readLong + readLong2)));
            }
        }
        throw new IOException("CFF tag not found in this OpenType font.");
    }

    private static String readTagName(CFFDataInput cFFDataInput) throws IOException {
        return new String(cFFDataInput.readBytes(4), Charsets.ISO_8859_1);
    }

    private static long readLong(CFFDataInput cFFDataInput) throws IOException {
        return cFFDataInput.readCard16() | (cFFDataInput.readCard16() << 16);
    }

    private static Header readHeader(CFFDataInput cFFDataInput) throws IOException {
        Header header = new Header();
        header.major = cFFDataInput.readCard8();
        header.minor = cFFDataInput.readCard8();
        header.hdrSize = cFFDataInput.readCard8();
        header.offSize = cFFDataInput.readOffSize();
        return header;
    }

    private static int[] readIndexDataOffsets(CFFDataInput cFFDataInput) throws IOException {
        int readCard16 = cFFDataInput.readCard16();
        if (readCard16 == 0) {
            return null;
        }
        int readOffSize = cFFDataInput.readOffSize();
        int[] iArr = new int[readCard16 + 1];
        for (int i = 0; i <= readCard16; i++) {
            int readOffset = cFFDataInput.readOffset(readOffSize);
            if (readOffset > cFFDataInput.length()) {
                throw new IOException("illegal offset value " + readOffset + " in CFF font");
            }
            iArr[i] = readOffset;
        }
        return iArr;
    }

    private static byte[][] readIndexData(CFFDataInput cFFDataInput) throws IOException {
        int[] readIndexDataOffsets = readIndexDataOffsets(cFFDataInput);
        if (readIndexDataOffsets == null) {
            return null;
        }
        int length = readIndexDataOffsets.length - 1;
        byte[][] bArr = new byte[length];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            bArr[i] = cFFDataInput.readBytes(readIndexDataOffsets[i2] - readIndexDataOffsets[i]);
            i = i2;
        }
        return bArr;
    }

    private static String[] readStringIndexData(CFFDataInput cFFDataInput) throws IOException {
        int[] readIndexDataOffsets = readIndexDataOffsets(cFFDataInput);
        if (readIndexDataOffsets == null) {
            return null;
        }
        int length = readIndexDataOffsets.length - 1;
        String[] strArr = new String[length];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = readIndexDataOffsets[i2] - readIndexDataOffsets[i];
            if (i3 < 0) {
                throw new IOException("Negative index data length + " + i3 + " at " + i + ": offsets[" + i2 + "]=" + readIndexDataOffsets[i2] + ", offsets[" + i + "]=" + readIndexDataOffsets[i]);
            }
            strArr[i] = new String(cFFDataInput.readBytes(i3), Charsets.ISO_8859_1);
            i = i2;
        }
        return strArr;
    }

    private static DictData readDictData(CFFDataInput cFFDataInput) throws IOException {
        DictData dictData = new DictData();
        while (cFFDataInput.hasRemaining()) {
            dictData.add(readEntry(cFFDataInput));
        }
        return dictData;
    }

    private static DictData readDictData(CFFDataInput cFFDataInput, int i) throws IOException {
        DictData dictData = new DictData();
        int position = cFFDataInput.getPosition() + i;
        while (cFFDataInput.getPosition() < position) {
            dictData.add(readEntry(cFFDataInput));
        }
        return dictData;
    }

    private static DictData.Entry readEntry(CFFDataInput cFFDataInput) throws IOException {
        int readUnsignedByte;
        DictData.Entry entry = new DictData.Entry();
        while (true) {
            readUnsignedByte = cFFDataInput.readUnsignedByte();
            if (readUnsignedByte >= 0 && readUnsignedByte <= 21) {
                entry.operator = readOperator(cFFDataInput, readUnsignedByte);
                return entry;
            } else if (readUnsignedByte == 28 || readUnsignedByte == 29) {
                entry.operands.add(readIntegerNumber(cFFDataInput, readUnsignedByte));
            } else if (readUnsignedByte == 30) {
                entry.operands.add(readRealNumber(cFFDataInput));
            } else if (readUnsignedByte < 32 || readUnsignedByte > 254) {
                break;
            } else {
                entry.operands.add(readIntegerNumber(cFFDataInput, readUnsignedByte));
            }
        }
        throw new IOException("invalid DICT data b0 byte: " + readUnsignedByte);
    }

    private static CFFOperator readOperator(CFFDataInput cFFDataInput, int i) throws IOException {
        return CFFOperator.getOperator(readOperatorKey(cFFDataInput, i));
    }

    private static CFFOperator.Key readOperatorKey(CFFDataInput cFFDataInput, int i) throws IOException {
        if (i == 12) {
            return new CFFOperator.Key(i, cFFDataInput.readUnsignedByte());
        }
        return new CFFOperator.Key(i);
    }

    private static Integer readIntegerNumber(CFFDataInput cFFDataInput, int i) throws IOException {
        if (i == 28) {
            return Integer.valueOf(cFFDataInput.readShort());
        }
        if (i == 29) {
            return Integer.valueOf(cFFDataInput.readInt());
        }
        if (i < 32 || i > 246) {
            if (i >= 247 && i <= 250) {
                return Integer.valueOf(((i - 247) * 256) + cFFDataInput.readUnsignedByte() + 108);
            } else if (i >= 251 && i <= 254) {
                return Integer.valueOf((((-(i - 251)) * 256) - cFFDataInput.readUnsignedByte()) - 108);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return Integer.valueOf(i - 139);
    }

    private static Double readRealNumber(CFFDataInput cFFDataInput) throws IOException {
        StringBuilder sb = new StringBuilder();
        int[] iArr = new int[2];
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (!z) {
            int readUnsignedByte = cFFDataInput.readUnsignedByte();
            iArr[0] = readUnsignedByte / 16;
            iArr[1] = readUnsignedByte % 16;
            for (int i = 0; i < 2; i++) {
                int i2 = iArr[i];
                switch (i2) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        sb.append(i2);
                        z2 = false;
                        break;
                    case 10:
                        sb.append(".");
                        break;
                    case 11:
                        if (z3) {
                            Log.w("PdfBox-Android", "duplicate 'E' ignored after " + ((Object) sb));
                            break;
                        } else {
                            sb.append("E");
                            z2 = true;
                            z3 = true;
                            break;
                        }
                    case 12:
                        if (z3) {
                            Log.w("PdfBox-Android", "duplicate 'E-' ignored after " + ((Object) sb));
                            break;
                        } else {
                            sb.append("E-");
                            z2 = true;
                            z3 = true;
                            break;
                        }
                    case 13:
                        break;
                    case 14:
                        sb.append("-");
                        break;
                    case 15:
                        z = true;
                        break;
                    default:
                        throw new IllegalArgumentException("illegal nibble " + i2);
                }
            }
        }
        if (z2) {
            sb.append("0");
        }
        if (sb.length() == 0) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
    }

    private CFFFont parseFont(CFFDataInput cFFDataInput, String str, byte[] bArr) throws IOException {
        CFFCIDFont cFFCIDFont;
        CFFCharset cFFISOAdobeCharset;
        DictData readDictData = readDictData(new CFFDataInput(bArr));
        if (readDictData.getEntry("SyntheticBase") != null) {
            throw new IOException("Synthetic Fonts are not supported");
        }
        boolean z = readDictData.getEntry("ROS") != null;
        if (z) {
            CFFCIDFont cFFCIDFont2 = new CFFCIDFont();
            DictData.Entry entry = readDictData.getEntry("ROS");
            if (entry == null || entry.size() < 3) {
                throw new IOException("ROS entry must have 3 elements");
            }
            cFFCIDFont2.setRegistry(readString(entry.getNumber(0).intValue()));
            cFFCIDFont2.setOrdering(readString(entry.getNumber(1).intValue()));
            cFFCIDFont2.setSupplement(entry.getNumber(2).intValue());
            cFFCIDFont = cFFCIDFont2;
        } else {
            cFFCIDFont = new CFFType1Font();
        }
        this.debugFontName = str;
        cFFCIDFont.setName(str);
        cFFCIDFont.addValueToTopDict(VersionType.VERSION, getString(readDictData, VersionType.VERSION));
        cFFCIDFont.addValueToTopDict(AFMParser.NOTICE, getString(readDictData, AFMParser.NOTICE));
        cFFCIDFont.addValueToTopDict("Copyright", getString(readDictData, "Copyright"));
        cFFCIDFont.addValueToTopDict(AFMParser.FULL_NAME, getString(readDictData, AFMParser.FULL_NAME));
        cFFCIDFont.addValueToTopDict(AFMParser.FAMILY_NAME, getString(readDictData, AFMParser.FAMILY_NAME));
        cFFCIDFont.addValueToTopDict(AFMParser.WEIGHT, getString(readDictData, AFMParser.WEIGHT));
        cFFCIDFont.addValueToTopDict("isFixedPitch", readDictData.getBoolean("isFixedPitch", false));
        cFFCIDFont.addValueToTopDict(AFMParser.ITALIC_ANGLE, readDictData.getNumber(AFMParser.ITALIC_ANGLE, 0));
        cFFCIDFont.addValueToTopDict(AFMParser.UNDERLINE_POSITION, readDictData.getNumber(AFMParser.UNDERLINE_POSITION, -100));
        cFFCIDFont.addValueToTopDict(AFMParser.UNDERLINE_THICKNESS, readDictData.getNumber(AFMParser.UNDERLINE_THICKNESS, 50));
        cFFCIDFont.addValueToTopDict("PaintType", readDictData.getNumber("PaintType", 0));
        cFFCIDFont.addValueToTopDict("CharstringType", readDictData.getNumber("CharstringType", 2));
        cFFCIDFont.addValueToTopDict("FontMatrix", readDictData.getArray("FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
        cFFCIDFont.addValueToTopDict("UniqueID", readDictData.getNumber("UniqueID", null));
        cFFCIDFont.addValueToTopDict(AFMParser.FONT_BBOX, readDictData.getArray(AFMParser.FONT_BBOX, Arrays.asList(0, 0, 0, 0)));
        cFFCIDFont.addValueToTopDict("StrokeWidth", readDictData.getNumber("StrokeWidth", 0));
        cFFCIDFont.addValueToTopDict("XUID", readDictData.getArray("XUID", null));
        DictData.Entry entry2 = readDictData.getEntry("CharStrings");
        if (entry2 == null || !entry2.hasOperands()) {
            throw new IOException("CharStrings is missing or empty");
        }
        cFFDataInput.setPosition(entry2.getNumber(0).intValue());
        byte[][] readIndexData = readIndexData(cFFDataInput);
        if (readIndexData == null) {
            throw new IOException("CharStringsIndex is missing");
        }
        DictData.Entry entry3 = readDictData.getEntry("charset");
        if (entry3 != null && entry3.hasOperands()) {
            int intValue = entry3.getNumber(0).intValue();
            if (!z && intValue == 0) {
                cFFISOAdobeCharset = CFFISOAdobeCharset.getInstance();
            } else if (!z && intValue == 1) {
                cFFISOAdobeCharset = CFFExpertCharset.getInstance();
            } else if (!z && intValue == 2) {
                cFFISOAdobeCharset = CFFExpertSubsetCharset.getInstance();
            } else {
                cFFDataInput.setPosition(intValue);
                cFFISOAdobeCharset = readCharset(cFFDataInput, readIndexData.length, z);
            }
        } else if (z) {
            cFFISOAdobeCharset = new EmptyCharset(readIndexData.length);
        } else {
            cFFISOAdobeCharset = CFFISOAdobeCharset.getInstance();
        }
        cFFCIDFont.setCharset(cFFISOAdobeCharset);
        cFFCIDFont.charStrings = readIndexData;
        if (z) {
            CFFCIDFont cFFCIDFont3 = (CFFCIDFont) cFFCIDFont;
            parseCIDFontDicts(cFFDataInput, readDictData, cFFCIDFont3, readIndexData.length);
            List<Map<String, Object>> fontDicts = cFFCIDFont3.getFontDicts();
            List<Number> list = (fontDicts.isEmpty() || !fontDicts.get(0).containsKey("FontMatrix")) ? null : (List) fontDicts.get(0).get("FontMatrix");
            List<Number> array = readDictData.getArray("FontMatrix", null);
            if (array == null) {
                if (list != null) {
                    cFFCIDFont.addValueToTopDict("FontMatrix", list);
                } else {
                    cFFCIDFont.addValueToTopDict("FontMatrix", readDictData.getArray("FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
                }
            } else if (list != null) {
                concatenateMatrix(array, list);
            }
        } else {
            parseType1Dicts(cFFDataInput, readDictData, cFFCIDFont, cFFISOAdobeCharset);
        }
        return cFFCIDFont;
    }

    private void concatenateMatrix(List<Number> list, List<Number> list2) {
        double doubleValue = list.get(0).doubleValue();
        double doubleValue2 = list.get(1).doubleValue();
        double doubleValue3 = list.get(2).doubleValue();
        double doubleValue4 = list.get(3).doubleValue();
        double doubleValue5 = list.get(4).doubleValue();
        double doubleValue6 = list.get(5).doubleValue();
        double doubleValue7 = list2.get(0).doubleValue();
        double doubleValue8 = list2.get(1).doubleValue();
        double doubleValue9 = list2.get(2).doubleValue();
        double doubleValue10 = list2.get(3).doubleValue();
        double doubleValue11 = list2.get(4).doubleValue();
        double doubleValue12 = list2.get(5).doubleValue();
        list.set(0, Double.valueOf((doubleValue * doubleValue7) + (doubleValue2 * doubleValue9)));
        list.set(1, Double.valueOf((doubleValue * doubleValue8) + (doubleValue2 * doubleValue4)));
        list.set(2, Double.valueOf((doubleValue3 * doubleValue7) + (doubleValue4 * doubleValue9)));
        list.set(3, Double.valueOf((doubleValue3 * doubleValue8) + (doubleValue4 * doubleValue10)));
        list.set(4, Double.valueOf((doubleValue7 * doubleValue5) + (doubleValue9 * doubleValue6) + doubleValue11));
        list.set(5, Double.valueOf((doubleValue5 * doubleValue8) + (doubleValue6 * doubleValue10) + doubleValue12));
    }

    private void parseCIDFontDicts(CFFDataInput cFFDataInput, DictData dictData, CFFCIDFont cFFCIDFont, int i) throws IOException {
        DictData.Entry entry = dictData.getEntry("FDArray");
        if (entry == null || !entry.hasOperands()) {
            throw new IOException("FDArray is missing for a CIDKeyed Font.");
        }
        cFFDataInput.setPosition(entry.getNumber(0).intValue());
        byte[][] readIndexData = readIndexData(cFFDataInput);
        if (readIndexData == null) {
            throw new IOException("Font dict index is missing for a CIDKeyed Font");
        }
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        for (byte[] bArr : readIndexData) {
            DictData readDictData = readDictData(new CFFDataInput(bArr));
            DictData.Entry entry2 = readDictData.getEntry(StandardStructureTypes.PRIVATE);
            if (entry2 == null || entry2.size() < 2) {
                throw new IOException("Font DICT invalid without \"Private\" entry");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(AFMParser.FONT_NAME, getString(readDictData, AFMParser.FONT_NAME));
            linkedHashMap.put("FontType", readDictData.getNumber("FontType", 0));
            linkedHashMap.put(AFMParser.FONT_BBOX, readDictData.getArray(AFMParser.FONT_BBOX, null));
            linkedHashMap.put("FontMatrix", readDictData.getArray("FontMatrix", null));
            linkedList2.add(linkedHashMap);
            int intValue = entry2.getNumber(1).intValue();
            cFFDataInput.setPosition(intValue);
            DictData readDictData2 = readDictData(cFFDataInput, entry2.getNumber(0).intValue());
            Map<String, Object> readPrivateDict = readPrivateDict(readDictData2);
            linkedList.add(readPrivateDict);
            Number number = readDictData2.getNumber("Subrs", 0);
            if (number instanceof Integer) {
                Integer num = (Integer) number;
                if (num.intValue() > 0) {
                    cFFDataInput.setPosition(intValue + num.intValue());
                    readPrivateDict.put("Subrs", readIndexData(cFFDataInput));
                }
            }
        }
        DictData.Entry entry3 = dictData.getEntry("FDSelect");
        if (entry3 == null || !entry3.hasOperands()) {
            throw new IOException("FDSelect is missing or empty");
        }
        cFFDataInput.setPosition(entry3.getNumber(0).intValue());
        FDSelect readFDSelect = readFDSelect(cFFDataInput, i, cFFCIDFont);
        cFFCIDFont.setFontDict(linkedList2);
        cFFCIDFont.setPrivDict(linkedList);
        cFFCIDFont.setFdSelect(readFDSelect);
    }

    private Map<String, Object> readPrivateDict(DictData dictData) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(17);
        linkedHashMap.put("BlueValues", dictData.getDelta("BlueValues", null));
        linkedHashMap.put("OtherBlues", dictData.getDelta("OtherBlues", null));
        linkedHashMap.put("FamilyBlues", dictData.getDelta("FamilyBlues", null));
        linkedHashMap.put("FamilyOtherBlues", dictData.getDelta("FamilyOtherBlues", null));
        linkedHashMap.put("BlueScale", dictData.getNumber("BlueScale", Double.valueOf(0.039625d)));
        linkedHashMap.put("BlueShift", dictData.getNumber("BlueShift", 7));
        linkedHashMap.put("BlueFuzz", dictData.getNumber("BlueFuzz", 1));
        linkedHashMap.put(AFMParser.STD_HW, dictData.getNumber(AFMParser.STD_HW, null));
        linkedHashMap.put(AFMParser.STD_VW, dictData.getNumber(AFMParser.STD_VW, null));
        linkedHashMap.put("StemSnapH", dictData.getDelta("StemSnapH", null));
        linkedHashMap.put("StemSnapV", dictData.getDelta("StemSnapV", null));
        linkedHashMap.put("ForceBold", dictData.getBoolean("ForceBold", false));
        linkedHashMap.put("LanguageGroup", dictData.getNumber("LanguageGroup", 0));
        linkedHashMap.put("ExpansionFactor", dictData.getNumber("ExpansionFactor", Double.valueOf(0.06d)));
        linkedHashMap.put("initialRandomSeed", dictData.getNumber("initialRandomSeed", 0));
        linkedHashMap.put("defaultWidthX", dictData.getNumber("defaultWidthX", 0));
        linkedHashMap.put("nominalWidthX", dictData.getNumber("nominalWidthX", 0));
        return linkedHashMap;
    }

    private void parseType1Dicts(CFFDataInput cFFDataInput, DictData dictData, CFFType1Font cFFType1Font, CFFCharset cFFCharset) throws IOException {
        CFFEncoding cFFStandardEncoding;
        DictData.Entry entry = dictData.getEntry("Encoding");
        int intValue = (entry == null || !entry.hasOperands()) ? 0 : entry.getNumber(0).intValue();
        if (intValue == 0) {
            cFFStandardEncoding = CFFStandardEncoding.getInstance();
        } else if (intValue == 1) {
            cFFStandardEncoding = CFFExpertEncoding.getInstance();
        } else {
            cFFDataInput.setPosition(intValue);
            cFFStandardEncoding = readEncoding(cFFDataInput, cFFCharset);
        }
        cFFType1Font.setEncoding(cFFStandardEncoding);
        DictData.Entry entry2 = dictData.getEntry(StandardStructureTypes.PRIVATE);
        if (entry2 == null || entry2.size() < 2) {
            throw new IOException("Private dictionary entry missing for font " + cFFType1Font.fontName);
        }
        int intValue2 = entry2.getNumber(1).intValue();
        cFFDataInput.setPosition(intValue2);
        DictData readDictData = readDictData(cFFDataInput, entry2.getNumber(0).intValue());
        for (Map.Entry<String, Object> entry3 : readPrivateDict(readDictData).entrySet()) {
            cFFType1Font.addToPrivateDict(entry3.getKey(), entry3.getValue());
        }
        Number number = readDictData.getNumber("Subrs", 0);
        if (number instanceof Integer) {
            Integer num = (Integer) number;
            if (num.intValue() > 0) {
                cFFDataInput.setPosition(intValue2 + num.intValue());
                cFFType1Font.addToPrivateDict("Subrs", readIndexData(cFFDataInput));
            }
        }
    }

    private String readString(int i) throws IOException {
        int i2;
        if (i >= 0) {
            if (i <= 390) {
                return CFFStandardString.getName(i);
            }
            String[] strArr = this.stringIndex;
            if (strArr != null && i - 391 < strArr.length) {
                return strArr[i2];
            }
            return "SID" + i;
        }
        throw new IOException("Invalid negative index when reading a string");
    }

    private String getString(DictData dictData, String str) throws IOException {
        DictData.Entry entry = dictData.getEntry(str);
        if (entry == null || !entry.hasOperands()) {
            return null;
        }
        return readString(entry.getNumber(0).intValue());
    }

    private CFFEncoding readEncoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset) throws IOException {
        int readCard8 = cFFDataInput.readCard8();
        int i = readCard8 & 127;
        if (i != 0) {
            if (i == 1) {
                return readFormat1Encoding(cFFDataInput, cFFCharset, readCard8);
            }
            throw new IOException("Invalid encoding base format " + i);
        }
        return readFormat0Encoding(cFFDataInput, cFFCharset, readCard8);
    }

    private Format0Encoding readFormat0Encoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset, int i) throws IOException {
        Format0Encoding format0Encoding = new Format0Encoding();
        format0Encoding.format = i;
        format0Encoding.nCodes = cFFDataInput.readCard8();
        format0Encoding.add(0, 0, ".notdef");
        for (int i2 = 1; i2 <= format0Encoding.nCodes; i2++) {
            int readCard8 = cFFDataInput.readCard8();
            int sIDForGID = cFFCharset.getSIDForGID(i2);
            format0Encoding.add(readCard8, sIDForGID, readString(sIDForGID));
        }
        if ((i & 128) != 0) {
            readSupplement(cFFDataInput, format0Encoding);
        }
        return format0Encoding;
    }

    private Format1Encoding readFormat1Encoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset, int i) throws IOException {
        Format1Encoding format1Encoding = new Format1Encoding();
        format1Encoding.format = i;
        format1Encoding.nRanges = cFFDataInput.readCard8();
        format1Encoding.add(0, 0, ".notdef");
        int i2 = 1;
        for (int i3 = 0; i3 < format1Encoding.nRanges; i3++) {
            int readCard8 = cFFDataInput.readCard8();
            int readCard82 = cFFDataInput.readCard8();
            for (int i4 = 0; i4 <= readCard82; i4++) {
                int sIDForGID = cFFCharset.getSIDForGID(i2);
                format1Encoding.add(readCard8 + i4, sIDForGID, readString(sIDForGID));
                i2++;
            }
        }
        if ((i & 128) != 0) {
            readSupplement(cFFDataInput, format1Encoding);
        }
        return format1Encoding;
    }

    private void readSupplement(CFFDataInput cFFDataInput, CFFBuiltInEncoding cFFBuiltInEncoding) throws IOException {
        cFFBuiltInEncoding.nSups = cFFDataInput.readCard8();
        cFFBuiltInEncoding.supplement = new CFFBuiltInEncoding.Supplement[cFFBuiltInEncoding.nSups];
        for (int i = 0; i < cFFBuiltInEncoding.supplement.length; i++) {
            CFFBuiltInEncoding.Supplement supplement = new CFFBuiltInEncoding.Supplement();
            supplement.code = cFFDataInput.readCard8();
            supplement.sid = cFFDataInput.readSID();
            supplement.name = readString(supplement.sid);
            cFFBuiltInEncoding.supplement[i] = supplement;
            cFFBuiltInEncoding.add(supplement.code, supplement.sid, readString(supplement.sid));
        }
    }

    private static FDSelect readFDSelect(CFFDataInput cFFDataInput, int i, CFFCIDFont cFFCIDFont) throws IOException {
        int readCard8 = cFFDataInput.readCard8();
        if (readCard8 != 0) {
            if (readCard8 == 3) {
                return readFormat3FDSelect(cFFDataInput, readCard8, i, cFFCIDFont);
            }
            throw new IllegalArgumentException();
        }
        return readFormat0FDSelect(cFFDataInput, readCard8, i, cFFCIDFont);
    }

    private static Format0FDSelect readFormat0FDSelect(CFFDataInput cFFDataInput, int i, int i2, CFFCIDFont cFFCIDFont) throws IOException {
        Format0FDSelect format0FDSelect = new Format0FDSelect(cFFCIDFont);
        format0FDSelect.format = i;
        format0FDSelect.fds = new int[i2];
        for (int i3 = 0; i3 < format0FDSelect.fds.length; i3++) {
            format0FDSelect.fds[i3] = cFFDataInput.readCard8();
        }
        return format0FDSelect;
    }

    private static Format3FDSelect readFormat3FDSelect(CFFDataInput cFFDataInput, int i, int i2, CFFCIDFont cFFCIDFont) throws IOException {
        Format3FDSelect format3FDSelect = new Format3FDSelect(cFFCIDFont);
        format3FDSelect.format = i;
        format3FDSelect.nbRanges = cFFDataInput.readCard16();
        format3FDSelect.range3 = new Range3[format3FDSelect.nbRanges];
        for (int i3 = 0; i3 < format3FDSelect.nbRanges; i3++) {
            Range3 range3 = new Range3();
            range3.first = cFFDataInput.readCard16();
            range3.fd = cFFDataInput.readCard8();
            format3FDSelect.range3[i3] = range3;
        }
        format3FDSelect.sentinel = cFFDataInput.readCard16();
        return format3FDSelect;
    }

    /* loaded from: classes3.dex */
    public static final class Format3FDSelect extends FDSelect {
        private int format;
        private int nbRanges;
        private Range3[] range3;
        private int sentinel;

        private Format3FDSelect(CFFCIDFont cFFCIDFont) {
            super(cFFCIDFont);
        }

        @Override // com.tom_roush.fontbox.cff.FDSelect
        public int getFDIndex(int i) {
            for (int i2 = 0; i2 < this.nbRanges; i2++) {
                if (this.range3[i2].first <= i) {
                    int i3 = i2 + 1;
                    if (i3 >= this.nbRanges) {
                        if (this.sentinel > i) {
                            return this.range3[i2].fd;
                        }
                        return -1;
                    } else if (this.range3[i3].first > i) {
                        return this.range3[i2].fd;
                    }
                }
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + " nbRanges=" + this.nbRanges + ", range3=" + Arrays.toString(this.range3) + " sentinel=" + this.sentinel + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static final class Range3 {
        private int fd;
        private int first;

        private Range3() {
        }

        public String toString() {
            return getClass().getName() + "[first=" + this.first + ", fd=" + this.fd + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static class Format0FDSelect extends FDSelect {
        private int[] fds;
        private int format;

        private Format0FDSelect(CFFCIDFont cFFCIDFont) {
            super(cFFCIDFont);
        }

        @Override // com.tom_roush.fontbox.cff.FDSelect
        public int getFDIndex(int i) {
            int[] iArr = this.fds;
            if (i < iArr.length) {
                return iArr[i];
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[fds=" + Arrays.toString(this.fds) + "]";
        }
    }

    private CFFCharset readCharset(CFFDataInput cFFDataInput, int i, boolean z) throws IOException {
        int readCard8 = cFFDataInput.readCard8();
        if (readCard8 != 0) {
            if (readCard8 != 1) {
                if (readCard8 == 2) {
                    return readFormat2Charset(cFFDataInput, readCard8, i, z);
                }
                throw new IOException("Incorrect charset format " + readCard8);
            }
            return readFormat1Charset(cFFDataInput, readCard8, i, z);
        }
        return readFormat0Charset(cFFDataInput, readCard8, i, z);
    }

    private Format0Charset readFormat0Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format0Charset format0Charset = new Format0Charset(z);
        format0Charset.format = i;
        if (z) {
            format0Charset.addCID(0, 0);
        } else {
            format0Charset.addSID(0, 0, ".notdef");
        }
        for (int i3 = 1; i3 < i2; i3++) {
            int readSID = cFFDataInput.readSID();
            if (z) {
                format0Charset.addCID(i3, readSID);
            } else {
                format0Charset.addSID(i3, readSID, readString(readSID));
            }
        }
        return format0Charset;
    }

    private Format1Charset readFormat1Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format1Charset format1Charset = new Format1Charset(z);
        format1Charset.format = i;
        if (z) {
            format1Charset.addCID(0, 0);
            format1Charset.rangesCID2GID = new ArrayList();
        } else {
            format1Charset.addSID(0, 0, ".notdef");
        }
        int i3 = 1;
        while (i3 < i2) {
            int readSID = cFFDataInput.readSID();
            int readCard8 = cFFDataInput.readCard8();
            if (z) {
                format1Charset.rangesCID2GID.add(new RangeMapping(i3, readSID, readCard8));
            } else {
                for (int i4 = 0; i4 < readCard8 + 1; i4++) {
                    int i5 = readSID + i4;
                    format1Charset.addSID(i3 + i4, i5, readString(i5));
                }
            }
            i3 = i3 + readCard8 + 1;
        }
        return format1Charset;
    }

    private Format2Charset readFormat2Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format2Charset format2Charset = new Format2Charset(z);
        format2Charset.format = i;
        if (z) {
            format2Charset.addCID(0, 0);
            format2Charset.rangesCID2GID = new ArrayList();
        } else {
            format2Charset.addSID(0, 0, ".notdef");
        }
        int i3 = 1;
        while (i3 < i2) {
            int readSID = cFFDataInput.readSID();
            int readCard16 = cFFDataInput.readCard16();
            if (z) {
                format2Charset.rangesCID2GID.add(new RangeMapping(i3, readSID, readCard16));
            } else {
                for (int i4 = 0; i4 < readCard16 + 1; i4++) {
                    int i5 = readSID + i4;
                    format2Charset.addSID(i3 + i4, i5, readString(i5));
                }
            }
            i3 = i3 + readCard16 + 1;
        }
        return format2Charset;
    }

    /* loaded from: classes3.dex */
    public static class Header {
        private int hdrSize;
        private int major;
        private int minor;
        private int offSize;

        private Header() {
        }

        public String toString() {
            return getClass().getName() + "[major=" + this.major + ", minor=" + this.minor + ", hdrSize=" + this.hdrSize + ", offSize=" + this.offSize + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static class DictData {
        private final Map<String, Entry> entries;

        private DictData() {
            this.entries = new HashMap();
        }

        public void add(Entry entry) {
            if (entry.operator != null) {
                this.entries.put(entry.operator.getName(), entry);
            }
        }

        public Entry getEntry(String str) {
            return this.entries.get(str);
        }

        public Boolean getBoolean(String str, boolean z) {
            Entry entry = getEntry(str);
            if (entry != null && !entry.getArray().isEmpty()) {
                z = entry.getBoolean(0, Boolean.valueOf(z)).booleanValue();
            }
            return Boolean.valueOf(z);
        }

        public List<Number> getArray(String str, List<Number> list) {
            Entry entry = getEntry(str);
            return (entry == null || entry.getArray().isEmpty()) ? list : entry.getArray();
        }

        public Number getNumber(String str, Number number) {
            Entry entry = getEntry(str);
            return (entry == null || entry.getArray().isEmpty()) ? number : entry.getNumber(0);
        }

        public List<Number> getDelta(String str, List<Number> list) {
            Entry entry = getEntry(str);
            return (entry == null || entry.getArray().isEmpty()) ? list : entry.getDelta();
        }

        public String toString() {
            return getClass().getName() + "[entries=" + this.entries + "]";
        }

        /* loaded from: classes3.dex */
        public static class Entry {
            private List<Number> operands;
            private CFFOperator operator;

            private Entry() {
                this.operands = new ArrayList();
                this.operator = null;
            }

            public Number getNumber(int i) {
                return this.operands.get(i);
            }

            public int size() {
                return this.operands.size();
            }

            public Boolean getBoolean(int i, Boolean bool) {
                Number number = this.operands.get(i);
                if (number instanceof Integer) {
                    int intValue = number.intValue();
                    if (intValue == 0) {
                        return Boolean.FALSE;
                    }
                    if (intValue == 1) {
                        return Boolean.TRUE;
                    }
                }
                Log.w("PdfBox-Android", "Expected boolean, got " + number + ", returning default " + bool);
                return bool;
            }

            @Deprecated
            public Boolean getBoolean(int i) {
                return getBoolean(i, Boolean.FALSE);
            }

            public boolean hasOperands() {
                return !this.operands.isEmpty();
            }

            public List<Number> getArray() {
                return this.operands;
            }

            public List<Number> getDelta() {
                ArrayList arrayList = new ArrayList(this.operands);
                for (int i = 1; i < arrayList.size(); i++) {
                    arrayList.set(i, Integer.valueOf(((Number) arrayList.get(i - 1)).intValue() + ((Number) arrayList.get(i)).intValue()));
                }
                return arrayList;
            }

            public String toString() {
                return getClass().getName() + "[operands=" + this.operands + ", operator=" + this.operator + "]";
            }
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class CFFBuiltInEncoding extends CFFEncoding {
        private int nSups;
        private Supplement[] supplement;

        CFFBuiltInEncoding() {
        }

        /* loaded from: classes3.dex */
        public static class Supplement {
            private int code;
            private String name;
            private int sid;

            Supplement() {
            }

            public int getCode() {
                return this.code;
            }

            public int getSID() {
                return this.sid;
            }

            public String getName() {
                return this.name;
            }

            public String toString() {
                return getClass().getName() + "[code=" + this.code + ", sid=" + this.sid + "]";
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class Format0Encoding extends CFFBuiltInEncoding {
        private int format;
        private int nCodes;

        private Format0Encoding() {
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", nCodes=" + this.nCodes + ", supplement=" + Arrays.toString(((CFFBuiltInEncoding) this).supplement) + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static class Format1Encoding extends CFFBuiltInEncoding {
        private int format;
        private int nRanges;

        private Format1Encoding() {
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", nRanges=" + this.nRanges + ", supplement=" + Arrays.toString(((CFFBuiltInEncoding) this).supplement) + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class EmbeddedCharset extends CFFCharset {
        protected EmbeddedCharset(boolean z) {
            super(z);
        }
    }

    /* loaded from: classes3.dex */
    public static class EmptyCharset extends EmbeddedCharset {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        protected EmptyCharset(int i) {
            super(true);
            addCID(0, 0);
            for (int i2 = 1; i2 <= i; i2++) {
                addCID(i2, i2);
            }
        }

        public String toString() {
            return getClass().getName();
        }
    }

    /* loaded from: classes3.dex */
    public static class Format0Charset extends EmbeddedCharset {
        private int format;

        protected Format0Charset(boolean z) {
            super(z);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static class Format1Charset extends EmbeddedCharset {
        private int format;
        private List<RangeMapping> rangesCID2GID;

        protected Format1Charset(boolean z) {
            super(z);
        }

        @Override // com.tom_roush.fontbox.cff.CFFCharset
        public int getCIDForGID(int i) {
            if (isCIDFont()) {
                for (RangeMapping rangeMapping : this.rangesCID2GID) {
                    if (rangeMapping.isInRange(i)) {
                        return rangeMapping.mapValue(i);
                    }
                }
            }
            return super.getCIDForGID(i);
        }

        @Override // com.tom_roush.fontbox.cff.CFFCharset
        public int getGIDForCID(int i) {
            if (isCIDFont()) {
                for (RangeMapping rangeMapping : this.rangesCID2GID) {
                    if (rangeMapping.isInReverseRange(i)) {
                        return rangeMapping.mapReverseValue(i);
                    }
                }
            }
            return super.getGIDForCID(i);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static class Format2Charset extends EmbeddedCharset {
        private int format;
        private List<RangeMapping> rangesCID2GID;

        protected Format2Charset(boolean z) {
            super(z);
        }

        @Override // com.tom_roush.fontbox.cff.CFFCharset
        public int getCIDForGID(int i) {
            for (RangeMapping rangeMapping : this.rangesCID2GID) {
                if (rangeMapping.isInRange(i)) {
                    return rangeMapping.mapValue(i);
                }
            }
            return super.getCIDForGID(i);
        }

        @Override // com.tom_roush.fontbox.cff.CFFCharset
        public int getGIDForCID(int i) {
            for (RangeMapping rangeMapping : this.rangesCID2GID) {
                if (rangeMapping.isInReverseRange(i)) {
                    return rangeMapping.mapReverseValue(i);
                }
            }
            return super.getGIDForCID(i);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static final class RangeMapping {
        private final int endMappedValue;
        private final int endValue;
        private final int startMappedValue;
        private final int startValue;

        private RangeMapping(int i, int i2, int i3) {
            this.startValue = i;
            this.endValue = i + i3;
            this.startMappedValue = i2;
            this.endMappedValue = i2 + i3;
        }

        boolean isInRange(int i) {
            return i >= this.startValue && i <= this.endValue;
        }

        boolean isInReverseRange(int i) {
            return i >= this.startMappedValue && i <= this.endMappedValue;
        }

        int mapValue(int i) {
            if (isInRange(i)) {
                return this.startMappedValue + (i - this.startValue);
            }
            return 0;
        }

        int mapReverseValue(int i) {
            if (isInReverseRange(i)) {
                return this.startValue + (i - this.startMappedValue);
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[start value=" + this.startValue + ", end value=" + this.endValue + ", start mapped-value=" + this.startMappedValue + ", end mapped-value=" + this.endMappedValue + "]";
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.debugFontName + "]";
    }
}
