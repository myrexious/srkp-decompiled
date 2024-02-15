package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.UByte;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public class CmapSubtable implements CmapLookup {
    private static final long LEAD_OFFSET = 55232;
    private static final long SURROGATE_OFFSET = -56613888;
    private int[] glyphIdToCharacterCode;
    private int platformEncodingId;
    private int platformId;
    private long subTableOffset;
    private final Map<Integer, List<Integer>> glyphIdToCharacterCodeMultiple = new HashMap();
    private Map<Integer, Integer> characterCodeToGlyphId = new HashMap();

    public void initData(TTFDataStream tTFDataStream) throws IOException {
        this.platformId = tTFDataStream.readUnsignedShort();
        this.platformEncodingId = tTFDataStream.readUnsignedShort();
        this.subTableOffset = tTFDataStream.readUnsignedInt();
    }

    public void initSubtable(CmapTable cmapTable, int i, TTFDataStream tTFDataStream) throws IOException {
        tTFDataStream.seek(cmapTable.getOffset() + this.subTableOffset);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort < 8) {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
        } else {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedInt();
            tTFDataStream.readUnsignedInt();
        }
        if (readUnsignedShort == 0) {
            processSubtype0(tTFDataStream);
        } else if (readUnsignedShort == 2) {
            processSubtype2(tTFDataStream, i);
        } else if (readUnsignedShort == 4) {
            processSubtype4(tTFDataStream, i);
        } else if (readUnsignedShort == 6) {
            processSubtype6(tTFDataStream, i);
        } else if (readUnsignedShort == 8) {
            processSubtype8(tTFDataStream, i);
        } else if (readUnsignedShort == 10) {
            processSubtype10(tTFDataStream, i);
        } else {
            switch (readUnsignedShort) {
                case 12:
                    processSubtype12(tTFDataStream, i);
                    return;
                case 13:
                    processSubtype13(tTFDataStream, i);
                    return;
                case 14:
                    processSubtype14(tTFDataStream, i);
                    return;
                default:
                    throw new IOException("Unknown cmap format:" + readUnsignedShort);
            }
        }
    }

    void processSubtype8(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] readUnsignedByteArray = tTFDataStream.readUnsignedByteArray(8192);
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        if (readUnsignedInt > 65536) {
            throw new IOException("CMap ( Subtype8 ) is invalid");
        }
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        this.characterCodeToGlyphId = new HashMap(i);
        if (i == 0) {
            Log.w("PdfBox-Android", "subtable has no glyphs");
            return;
        }
        long j = 0;
        long j2 = 0;
        while (j2 < readUnsignedInt) {
            long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt3 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt4 = tTFDataStream.readUnsignedInt();
            if (readUnsignedInt2 > readUnsignedInt3 || j > readUnsignedInt2) {
                throw new IOException("Range invalid");
            }
            long j3 = readUnsignedInt2;
            while (j3 <= readUnsignedInt3) {
                if (j3 > 2147483647L) {
                    throw new IOException("[Sub Format 8] Invalid character code " + j3);
                }
                long j4 = readUnsignedInt;
                int i2 = (int) j3;
                int i3 = i2 / 8;
                long j5 = readUnsignedInt3;
                if (i3 >= readUnsignedByteArray.length) {
                    throw new IOException("[Sub Format 8] Invalid character code " + j3);
                }
                if ((readUnsignedByteArray[i3] & (1 << (i2 % 8))) != 0) {
                    long j6 = (((j3 >> 10) + LEAD_OFFSET) << 10) + (1023 & j3) + 56320 + SURROGATE_OFFSET;
                    if (j6 > 2147483647L) {
                        throw new IOException("[Sub Format 8] Invalid character code " + j6);
                    }
                    i2 = (int) j6;
                }
                long j7 = (j3 - readUnsignedInt2) + readUnsignedInt4;
                int[] iArr = readUnsignedByteArray;
                if (j7 > i || j7 > 2147483647L) {
                    throw new IOException("CMap contains an invalid glyph index");
                }
                int i4 = (int) j7;
                this.glyphIdToCharacterCode[i4] = i2;
                this.characterCodeToGlyphId.put(Integer.valueOf(i2), Integer.valueOf(i4));
                j3++;
                readUnsignedByteArray = iArr;
                readUnsignedInt = j4;
                readUnsignedInt3 = j5;
            }
            j2++;
            readUnsignedInt = readUnsignedInt;
            j = 0;
        }
    }

    void processSubtype10(TTFDataStream tTFDataStream, int i) throws IOException {
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
        if (readUnsignedInt2 > 2147483647L) {
            throw new IOException("Invalid number of Characters");
        }
        if (readUnsignedInt >= 0 && readUnsignedInt <= 1114111) {
            long j = readUnsignedInt + readUnsignedInt2;
            if (j <= 1114111 && (j < 55296 || j > 57343)) {
                return;
            }
        }
        throw new IOException("Invalid character codes, " + String.format("startCode: 0x%X, numChars: %d", Long.valueOf(readUnsignedInt), Long.valueOf(readUnsignedInt2)));
    }

    void processSubtype12(TTFDataStream tTFDataStream, int i) throws IOException {
        long j;
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        this.characterCodeToGlyphId = new HashMap(i);
        if (i == 0) {
            Log.w("PdfBox-Android", "subtable has no glyphs");
            return;
        }
        long j2 = 0;
        long j3 = 0;
        int i2 = 0;
        while (j3 < readUnsignedInt) {
            long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt3 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt4 = tTFDataStream.readUnsignedInt();
            if (readUnsignedInt2 < j2 || readUnsignedInt2 > 1114111 || (readUnsignedInt2 >= 55296 && readUnsignedInt2 <= 57343)) {
                throw new IOException("Invalid character code " + String.format("0x%X", Long.valueOf(readUnsignedInt2)));
            }
            if ((readUnsignedInt3 > 0 && readUnsignedInt3 < readUnsignedInt2) || readUnsignedInt3 > 1114111 || (readUnsignedInt3 >= 55296 && readUnsignedInt3 <= 57343)) {
                throw new IOException("Invalid character code " + String.format("0x%X", Long.valueOf(readUnsignedInt3)));
            }
            long j4 = 0;
            while (true) {
                j = readUnsignedInt;
                if (j4 <= readUnsignedInt3 - readUnsignedInt2) {
                    long j5 = readUnsignedInt4 + j4;
                    long j6 = readUnsignedInt3;
                    if (j5 >= i) {
                        Log.w("PdfBox-Android", "Format 12 cmap contains an invalid glyph index");
                        break;
                    }
                    long j7 = readUnsignedInt2 + j4;
                    if (j7 > 1114111) {
                        Log.w("PdfBox-Android", "Format 12 cmap contains character beyond UCS-4");
                    }
                    int i3 = (int) j5;
                    i2 = Math.max(i2, i3);
                    this.characterCodeToGlyphId.put(Integer.valueOf((int) j7), Integer.valueOf(i3));
                    j4++;
                    readUnsignedInt = j;
                    readUnsignedInt3 = j6;
                }
            }
            j3++;
            j2 = 0;
            readUnsignedInt = j;
        }
        buildGlyphIdToCharacterCodeLookup(i2);
    }

    void processSubtype13(TTFDataStream tTFDataStream, int i) throws IOException {
        int i2 = i;
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i2);
        this.characterCodeToGlyphId = new HashMap(i2);
        if (i2 == 0) {
            Log.w("PdfBox-Android", "subtable has no glyphs");
            return;
        }
        long j = 0;
        while (j < readUnsignedInt) {
            long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt3 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt4 = tTFDataStream.readUnsignedInt();
            if (readUnsignedInt4 > i2) {
                Log.w("PdfBox-Android", "Format 13 cmap contains an invalid glyph index");
                return;
            } else if (readUnsignedInt2 < 0 || readUnsignedInt2 > 1114111 || (readUnsignedInt2 >= 55296 && readUnsignedInt2 <= 57343)) {
                throw new IOException("Invalid character code " + String.format("0x%X", Long.valueOf(readUnsignedInt2)));
            } else {
                if ((readUnsignedInt3 > 0 && readUnsignedInt3 < readUnsignedInt2) || readUnsignedInt3 > 1114111 || (readUnsignedInt3 >= 55296 && readUnsignedInt3 <= 57343)) {
                    throw new IOException("Invalid character code " + String.format("0x%X", Long.valueOf(readUnsignedInt3)));
                }
                long j2 = 0;
                while (j2 <= readUnsignedInt3 - readUnsignedInt2) {
                    long j3 = readUnsignedInt;
                    long j4 = readUnsignedInt2 + j2;
                    if (j4 > 2147483647L) {
                        throw new IOException("Character Code greater than Integer.MAX_VALUE");
                    }
                    if (j4 > 1114111) {
                        Log.w("PdfBox-Android", "Format 13 cmap contains character beyond UCS-4");
                    }
                    int i3 = (int) readUnsignedInt4;
                    int i4 = (int) j4;
                    this.glyphIdToCharacterCode[i3] = i4;
                    this.characterCodeToGlyphId.put(Integer.valueOf(i4), Integer.valueOf(i3));
                    j2++;
                    readUnsignedInt = j3;
                }
                j++;
                i2 = i;
            }
        }
    }

    void processSubtype14(TTFDataStream tTFDataStream, int i) throws IOException {
        Log.w("PdfBox-Android", "Format 14 cmap table is not supported and will be ignored");
    }

    void processSubtype6(TTFDataStream tTFDataStream, int i) throws IOException {
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort2 == 0) {
            return;
        }
        this.characterCodeToGlyphId = new HashMap(i);
        int[] readUnsignedShortArray = tTFDataStream.readUnsignedShortArray(readUnsignedShort2);
        int i2 = 0;
        for (int i3 = 0; i3 < readUnsignedShort2; i3++) {
            i2 = Math.max(i2, readUnsignedShortArray[i3]);
            this.characterCodeToGlyphId.put(Integer.valueOf(readUnsignedShort + i3), Integer.valueOf(readUnsignedShortArray[i3]));
        }
        buildGlyphIdToCharacterCodeLookup(i2);
    }

    void processSubtype4(TTFDataStream tTFDataStream, int i) throws IOException {
        long j;
        int max;
        int readUnsignedShort = tTFDataStream.readUnsignedShort() / 2;
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        int[] readUnsignedShortArray = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        tTFDataStream.readUnsignedShort();
        int[] readUnsignedShortArray2 = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        int[] readUnsignedShortArray3 = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        long currentPosition = tTFDataStream.getCurrentPosition();
        int[] readUnsignedShortArray4 = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        this.characterCodeToGlyphId = new HashMap(i);
        int i2 = 0;
        int i3 = 0;
        while (i2 < readUnsignedShort) {
            int i4 = readUnsignedShortArray2[i2];
            int i5 = readUnsignedShortArray[i2];
            int i6 = readUnsignedShortArray3[i2];
            int i7 = readUnsignedShortArray4[i2];
            int i8 = readUnsignedShort;
            int[] iArr = readUnsignedShortArray;
            int[] iArr2 = readUnsignedShortArray2;
            int[] iArr3 = readUnsignedShortArray3;
            long j2 = (i2 * 2) + currentPosition + i7;
            int i9 = 65535;
            if (i4 != 65535 && i5 != 65535) {
                int i10 = i4;
                while (i10 <= i5) {
                    if (i7 == 0) {
                        j = currentPosition;
                        int i11 = (i10 + i6) & i9;
                        max = Math.max(i11, i3);
                        this.characterCodeToGlyphId.put(Integer.valueOf(i10), Integer.valueOf(i11));
                    } else {
                        j = currentPosition;
                        tTFDataStream.seek(((i10 - i4) * 2) + j2);
                        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
                        if (readUnsignedShort2 != 0) {
                            int i12 = (readUnsignedShort2 + i6) & 65535;
                            max = Math.max(i12, i3);
                            this.characterCodeToGlyphId.put(Integer.valueOf(i10), Integer.valueOf(i12));
                        } else {
                            i10++;
                            currentPosition = j;
                            i9 = 65535;
                        }
                    }
                    i3 = max;
                    i10++;
                    currentPosition = j;
                    i9 = 65535;
                }
            }
            i2++;
            readUnsignedShort = i8;
            readUnsignedShortArray = iArr;
            readUnsignedShortArray2 = iArr2;
            readUnsignedShortArray3 = iArr3;
            currentPosition = currentPosition;
        }
        if (this.characterCodeToGlyphId.isEmpty()) {
            Log.w("PdfBox-Android", "cmap format 4 subtable is empty");
        } else {
            buildGlyphIdToCharacterCodeLookup(i3);
        }
    }

    private void buildGlyphIdToCharacterCodeLookup(int i) {
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i + 1);
        for (Map.Entry<Integer, Integer> entry : this.characterCodeToGlyphId.entrySet()) {
            if (this.glyphIdToCharacterCode[entry.getValue().intValue()] == -1) {
                this.glyphIdToCharacterCode[entry.getValue().intValue()] = entry.getKey().intValue();
            } else {
                List<Integer> list = this.glyphIdToCharacterCodeMultiple.get(entry.getValue());
                if (list == null) {
                    list = new ArrayList<>();
                    this.glyphIdToCharacterCodeMultiple.put(entry.getValue(), list);
                    list.add(Integer.valueOf(this.glyphIdToCharacterCode[entry.getValue().intValue()]));
                    this.glyphIdToCharacterCode[entry.getValue().intValue()] = Integer.MIN_VALUE;
                }
                list.add(entry.getKey());
            }
        }
    }

    void processSubtype2(TTFDataStream tTFDataStream, int i) throws IOException {
        SubHeader[] subHeaderArr;
        int[] iArr = new int[256];
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            int readUnsignedShort = tTFDataStream.readUnsignedShort();
            iArr[i3] = readUnsignedShort;
            i2 = Math.max(i2, readUnsignedShort / 8);
        }
        int i4 = i2 + 1;
        SubHeader[] subHeaderArr2 = new SubHeader[i4];
        for (int i5 = 0; i5 <= i2; i5++) {
            subHeaderArr2[i5] = new SubHeader(tTFDataStream.readUnsignedShort(), tTFDataStream.readUnsignedShort(), tTFDataStream.readSignedShort(), (tTFDataStream.readUnsignedShort() - (((i4 - i5) - 1) * 8)) - 2);
        }
        long currentPosition = tTFDataStream.getCurrentPosition();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        this.characterCodeToGlyphId = new HashMap(i);
        if (i == 0) {
            Log.w("PdfBox-Android", "subtable has no glyphs");
            return;
        }
        for (int i6 = 0; i6 <= i2; i6++) {
            SubHeader subHeader = subHeaderArr2[i6];
            int firstCode = subHeader.getFirstCode();
            int idRangeOffset = subHeader.getIdRangeOffset();
            short idDelta = subHeader.getIdDelta();
            int entryCount = subHeader.getEntryCount();
            tTFDataStream.seek(idRangeOffset + currentPosition);
            int i7 = 0;
            while (i7 < entryCount) {
                int i8 = (i6 << 8) + firstCode + i7;
                int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
                if (readUnsignedShort2 > 0 && (readUnsignedShort2 = (readUnsignedShort2 + idDelta) % 65536) < 0) {
                    readUnsignedShort2 += 65536;
                }
                if (readUnsignedShort2 >= i) {
                    subHeaderArr = subHeaderArr2;
                    Log.w("PdfBox-Android", "glyphId " + readUnsignedShort2 + " for charcode " + i8 + " ignored, numGlyphs is " + i);
                } else {
                    subHeaderArr = subHeaderArr2;
                    this.glyphIdToCharacterCode[readUnsignedShort2] = i8;
                    this.characterCodeToGlyphId.put(Integer.valueOf(i8), Integer.valueOf(readUnsignedShort2));
                }
                i7++;
                subHeaderArr2 = subHeaderArr;
            }
        }
    }

    void processSubtype0(TTFDataStream tTFDataStream) throws IOException {
        byte[] read = tTFDataStream.read(256);
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(256);
        this.characterCodeToGlyphId = new HashMap(read.length);
        for (int i = 0; i < read.length; i++) {
            int i2 = read[i] & UByte.MAX_VALUE;
            this.glyphIdToCharacterCode[i2] = i;
            this.characterCodeToGlyphId.put(Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    private int[] newGlyphIdToCharacterCode(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public int getPlatformEncodingId() {
        return this.platformEncodingId;
    }

    public void setPlatformEncodingId(int i) {
        this.platformEncodingId = i;
    }

    public int getPlatformId() {
        return this.platformId;
    }

    public void setPlatformId(int i) {
        this.platformId = i;
    }

    @Override // com.tom_roush.fontbox.ttf.CmapLookup
    public int getGlyphId(int i) {
        Integer num = this.characterCodeToGlyphId.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    @Deprecated
    public Integer getCharacterCode(int i) {
        List<Integer> list;
        int charCode = getCharCode(i);
        if (charCode == -1) {
            return null;
        }
        if (charCode == Integer.MIN_VALUE && (list = this.glyphIdToCharacterCodeMultiple.get(Integer.valueOf(i))) != null) {
            return list.get(0);
        }
        return Integer.valueOf(charCode);
    }

    private int getCharCode(int i) {
        int[] iArr;
        if (i < 0 || (iArr = this.glyphIdToCharacterCode) == null || i >= iArr.length) {
            return -1;
        }
        return iArr[i];
    }

    @Override // com.tom_roush.fontbox.ttf.CmapLookup
    public List<Integer> getCharCodes(int i) {
        int charCode = getCharCode(i);
        if (charCode == -1) {
            return null;
        }
        if (charCode == Integer.MIN_VALUE) {
            List<Integer> list = this.glyphIdToCharacterCodeMultiple.get(Integer.valueOf(i));
            if (list != null) {
                ArrayList arrayList = new ArrayList(list);
                Collections.sort(arrayList);
                return arrayList;
            }
            return null;
        }
        ArrayList arrayList2 = new ArrayList(1);
        arrayList2.add(Integer.valueOf(charCode));
        return arrayList2;
    }

    public String toString() {
        return "{" + getPlatformId() + StringUtils.SPACE + getPlatformEncodingId() + StringSubstitutor.DEFAULT_VAR_END;
    }

    /* loaded from: classes3.dex */
    public static class SubHeader {
        private final int entryCount;
        private final int firstCode;
        private final short idDelta;
        private final int idRangeOffset;

        private SubHeader(int i, int i2, short s, int i3) {
            this.firstCode = i;
            this.entryCount = i2;
            this.idDelta = s;
            this.idRangeOffset = i3;
        }

        public int getFirstCode() {
            return this.firstCode;
        }

        public int getEntryCount() {
            return this.entryCount;
        }

        public short getIdDelta() {
            return this.idDelta;
        }

        public int getIdRangeOffset() {
            return this.idRangeOffset;
        }
    }
}
