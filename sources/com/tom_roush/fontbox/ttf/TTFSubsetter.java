package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.UByte;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes3.dex */
public final class TTFSubsetter {
    private static final byte[] PAD_BUF = {0, 0, 0};
    private final SortedSet<Integer> glyphIds;
    private boolean hasAddedCompoundReferences;
    private final List<String> keepTables;
    private String prefix;
    private final TrueTypeFont ttf;
    private final SortedMap<Integer, Integer> uniToGID;
    private final CmapLookup unicodeCmap;

    private long toUInt32(int i, int i2) {
        return (i2 & 65535) | ((i & 65535) << 16);
    }

    public TTFSubsetter(TrueTypeFont trueTypeFont) throws IOException {
        this(trueTypeFont, null);
    }

    public TTFSubsetter(TrueTypeFont trueTypeFont, List<String> list) throws IOException {
        this.ttf = trueTypeFont;
        this.keepTables = list;
        this.uniToGID = new TreeMap();
        TreeSet treeSet = new TreeSet();
        this.glyphIds = treeSet;
        this.unicodeCmap = trueTypeFont.getUnicodeCmapLookup();
        treeSet.add(0);
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void add(int i) {
        int glyphId = this.unicodeCmap.getGlyphId(i);
        if (glyphId != 0) {
            this.uniToGID.put(Integer.valueOf(i), Integer.valueOf(glyphId));
            this.glyphIds.add(Integer.valueOf(glyphId));
        }
    }

    public void addAll(Set<Integer> set) {
        for (Integer num : set) {
            add(num.intValue());
        }
    }

    public Map<Integer, Integer> getGIDMap() throws IOException {
        addCompoundReferences();
        HashMap hashMap = new HashMap();
        int i = 0;
        for (Integer num : this.glyphIds) {
            hashMap.put(Integer.valueOf(i), Integer.valueOf(num.intValue()));
            i++;
        }
        return hashMap;
    }

    private long writeFileHeader(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeInt(65536);
        dataOutputStream.writeShort(i);
        int highestOneBit = Integer.highestOneBit(i);
        int i2 = highestOneBit * 16;
        dataOutputStream.writeShort(i2);
        int log2 = log2(highestOneBit);
        dataOutputStream.writeShort(log2);
        int i3 = (i * 16) - i2;
        dataOutputStream.writeShort(i3);
        return toUInt32(i, i2) + 65536 + toUInt32(log2, i3);
    }

    private long writeTableHeader(DataOutputStream dataOutputStream, String str, long j, byte[] bArr) throws IOException {
        long j2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            j2 += (bArr[i] & 255) << (24 - ((i % 4) * 8));
        }
        long j3 = j2 & BodyPartID.bodyIdMax;
        byte[] bytes = str.getBytes("US-ASCII");
        dataOutputStream.write(bytes, 0, 4);
        dataOutputStream.writeInt((int) j3);
        dataOutputStream.writeInt((int) j);
        dataOutputStream.writeInt(bArr.length);
        return toUInt32(bytes) + j3 + j3 + j + bArr.length;
    }

    private void writeTableBody(OutputStream outputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        outputStream.write(bArr);
        int i = length % 4;
        if (i != 0) {
            outputStream.write(PAD_BUF, 0, 4 - i);
        }
    }

    private byte[] buildHeadTable() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        HeaderTable header = this.ttf.getHeader();
        writeFixed(dataOutputStream, header.getVersion());
        writeFixed(dataOutputStream, header.getFontRevision());
        writeUint32(dataOutputStream, 0L);
        writeUint32(dataOutputStream, header.getMagicNumber());
        writeUint16(dataOutputStream, header.getFlags());
        writeUint16(dataOutputStream, header.getUnitsPerEm());
        writeLongDateTime(dataOutputStream, header.getCreated());
        writeLongDateTime(dataOutputStream, header.getModified());
        writeSInt16(dataOutputStream, header.getXMin());
        writeSInt16(dataOutputStream, header.getYMin());
        writeSInt16(dataOutputStream, header.getXMax());
        writeSInt16(dataOutputStream, header.getYMax());
        writeUint16(dataOutputStream, header.getMacStyle());
        writeUint16(dataOutputStream, header.getLowestRecPPEM());
        writeSInt16(dataOutputStream, header.getFontDirectionHint());
        writeSInt16(dataOutputStream, (short) 1);
        writeSInt16(dataOutputStream, header.getGlyphDataFormat());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildHheaTable() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        HorizontalHeaderTable horizontalHeader = this.ttf.getHorizontalHeader();
        writeFixed(dataOutputStream, horizontalHeader.getVersion());
        writeSInt16(dataOutputStream, horizontalHeader.getAscender());
        writeSInt16(dataOutputStream, horizontalHeader.getDescender());
        writeSInt16(dataOutputStream, horizontalHeader.getLineGap());
        writeUint16(dataOutputStream, horizontalHeader.getAdvanceWidthMax());
        writeSInt16(dataOutputStream, horizontalHeader.getMinLeftSideBearing());
        writeSInt16(dataOutputStream, horizontalHeader.getMinRightSideBearing());
        writeSInt16(dataOutputStream, horizontalHeader.getXMaxExtent());
        writeSInt16(dataOutputStream, horizontalHeader.getCaretSlopeRise());
        writeSInt16(dataOutputStream, horizontalHeader.getCaretSlopeRun());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved1());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved2());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved3());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved4());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved5());
        writeSInt16(dataOutputStream, horizontalHeader.getMetricDataFormat());
        int size = this.glyphIds.subSet(0, Integer.valueOf(horizontalHeader.getNumberOfHMetrics())).size();
        if (this.glyphIds.last().intValue() >= horizontalHeader.getNumberOfHMetrics() && !this.glyphIds.contains(Integer.valueOf(horizontalHeader.getNumberOfHMetrics() - 1))) {
            size++;
        }
        writeUint16(dataOutputStream, size);
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private boolean shouldCopyNameRecord(NameRecord nameRecord) {
        return nameRecord.getPlatformId() == 3 && nameRecord.getPlatformEncodingId() == 1 && nameRecord.getLanguageId() == 1033 && nameRecord.getNameId() >= 0 && nameRecord.getNameId() < 7;
    }

    private byte[] buildNameTable() throws IOException {
        List<String> list;
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        NamingTable naming = this.ttf.getNaming();
        if (naming == null || !((list = this.keepTables) == null || list.contains("name"))) {
            return null;
        }
        List<NameRecord> nameRecords = naming.getNameRecords();
        int i = 0;
        for (NameRecord nameRecord : nameRecords) {
            if (shouldCopyNameRecord(nameRecord)) {
                i++;
            }
        }
        writeUint16(dataOutputStream, 0);
        writeUint16(dataOutputStream, i);
        writeUint16(dataOutputStream, (i * 12) + 6);
        if (i == 0) {
            return null;
        }
        byte[][] bArr = new byte[i];
        int i2 = 0;
        for (NameRecord nameRecord2 : nameRecords) {
            if (shouldCopyNameRecord(nameRecord2)) {
                int platformId = nameRecord2.getPlatformId();
                int platformEncodingId = nameRecord2.getPlatformEncodingId();
                if (platformId == 3 && platformEncodingId == 1) {
                    str = "UTF-16BE";
                } else {
                    if (platformId == 2) {
                        if (platformEncodingId == 0) {
                            str = "US-ASCII";
                        } else if (platformEncodingId == 1) {
                            str = "UTF16-BE";
                        }
                    }
                    str = "ISO-8859-1";
                }
                String string = nameRecord2.getString();
                if (nameRecord2.getNameId() == 6 && this.prefix != null) {
                    string = this.prefix + string;
                }
                bArr[i2] = string.getBytes(str);
                i2++;
            }
        }
        int i3 = 0;
        int i4 = 0;
        for (NameRecord nameRecord3 : nameRecords) {
            if (shouldCopyNameRecord(nameRecord3)) {
                writeUint16(dataOutputStream, nameRecord3.getPlatformId());
                writeUint16(dataOutputStream, nameRecord3.getPlatformEncodingId());
                writeUint16(dataOutputStream, nameRecord3.getLanguageId());
                writeUint16(dataOutputStream, nameRecord3.getNameId());
                writeUint16(dataOutputStream, bArr[i3].length);
                writeUint16(dataOutputStream, i4);
                i4 += bArr[i3].length;
                i3++;
            }
        }
        for (int i5 = 0; i5 < i; i5++) {
            dataOutputStream.write(bArr[i5]);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildMaxpTable() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        MaximumProfileTable maximumProfile = this.ttf.getMaximumProfile();
        writeFixed(dataOutputStream, 1.0d);
        writeUint16(dataOutputStream, this.glyphIds.size());
        writeUint16(dataOutputStream, maximumProfile.getMaxPoints());
        writeUint16(dataOutputStream, maximumProfile.getMaxContours());
        writeUint16(dataOutputStream, maximumProfile.getMaxCompositePoints());
        writeUint16(dataOutputStream, maximumProfile.getMaxCompositeContours());
        writeUint16(dataOutputStream, maximumProfile.getMaxZones());
        writeUint16(dataOutputStream, maximumProfile.getMaxTwilightPoints());
        writeUint16(dataOutputStream, maximumProfile.getMaxStorage());
        writeUint16(dataOutputStream, maximumProfile.getMaxFunctionDefs());
        writeUint16(dataOutputStream, maximumProfile.getMaxInstructionDefs());
        writeUint16(dataOutputStream, maximumProfile.getMaxStackElements());
        writeUint16(dataOutputStream, maximumProfile.getMaxSizeOfInstructions());
        writeUint16(dataOutputStream, maximumProfile.getMaxComponentElements());
        writeUint16(dataOutputStream, maximumProfile.getMaxComponentDepth());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildOS2Table() throws IOException {
        OS2WindowsMetricsTable oS2Windows = this.ttf.getOS2Windows();
        if (oS2Windows == null || this.uniToGID.isEmpty()) {
            return null;
        }
        List<String> list = this.keepTables;
        if (list == null || list.contains(OS2WindowsMetricsTable.TAG)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            writeUint16(dataOutputStream, oS2Windows.getVersion());
            writeSInt16(dataOutputStream, oS2Windows.getAverageCharWidth());
            writeUint16(dataOutputStream, oS2Windows.getWeightClass());
            writeUint16(dataOutputStream, oS2Windows.getWidthClass());
            writeSInt16(dataOutputStream, oS2Windows.getFsType());
            writeSInt16(dataOutputStream, oS2Windows.getSubscriptXSize());
            writeSInt16(dataOutputStream, oS2Windows.getSubscriptYSize());
            writeSInt16(dataOutputStream, oS2Windows.getSubscriptXOffset());
            writeSInt16(dataOutputStream, oS2Windows.getSubscriptYOffset());
            writeSInt16(dataOutputStream, oS2Windows.getSuperscriptXSize());
            writeSInt16(dataOutputStream, oS2Windows.getSuperscriptYSize());
            writeSInt16(dataOutputStream, oS2Windows.getSuperscriptXOffset());
            writeSInt16(dataOutputStream, oS2Windows.getSuperscriptYOffset());
            writeSInt16(dataOutputStream, oS2Windows.getStrikeoutSize());
            writeSInt16(dataOutputStream, oS2Windows.getStrikeoutPosition());
            writeSInt16(dataOutputStream, (short) oS2Windows.getFamilyClass());
            dataOutputStream.write(oS2Windows.getPanose());
            writeUint32(dataOutputStream, 0L);
            writeUint32(dataOutputStream, 0L);
            writeUint32(dataOutputStream, 0L);
            writeUint32(dataOutputStream, 0L);
            dataOutputStream.write(oS2Windows.getAchVendId().getBytes("US-ASCII"));
            writeUint16(dataOutputStream, oS2Windows.getFsSelection());
            writeUint16(dataOutputStream, this.uniToGID.firstKey().intValue());
            writeUint16(dataOutputStream, this.uniToGID.lastKey().intValue());
            writeUint16(dataOutputStream, oS2Windows.getTypoAscender());
            writeUint16(dataOutputStream, oS2Windows.getTypoDescender());
            writeUint16(dataOutputStream, oS2Windows.getTypoLineGap());
            writeUint16(dataOutputStream, oS2Windows.getWinAscent());
            writeUint16(dataOutputStream, oS2Windows.getWinDescent());
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    private byte[] buildLocaTable(long[] jArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (long j : jArr) {
            writeUint32(dataOutputStream, j);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private void addCompoundReferences() throws IOException {
        boolean z;
        int i;
        if (this.hasAddedCompoundReferences) {
            return;
        }
        this.hasAddedCompoundReferences = true;
        GlyphTable glyph = this.ttf.getGlyph();
        long[] offsets = this.ttf.getIndexToLocation().getOffsets();
        do {
            InputStream originalData = this.ttf.getOriginalData();
            try {
                originalData.skip(glyph.getOffset());
                Iterator<Integer> it = this.glyphIds.iterator();
                TreeSet treeSet = null;
                long j = 0;
                while (true) {
                    z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    Integer next = it.next();
                    long j2 = offsets[next.intValue()];
                    long j3 = offsets[next.intValue() + 1] - j2;
                    originalData.skip(j2 - j);
                    int i2 = (int) j3;
                    byte[] bArr = new byte[i2];
                    originalData.read(bArr);
                    if (i2 < 2 || bArr[0] != -1 || bArr[1] != -1) {
                        j = offsets[next.intValue() + 1];
                    } else {
                        int i3 = 10;
                        do {
                            i = ((bArr[i3] & UByte.MAX_VALUE) << 8) | (bArr[i3 + 1] & UByte.MAX_VALUE);
                            int i4 = i3 + 2;
                            int i5 = ((bArr[i4] & UByte.MAX_VALUE) << 8) | (bArr[i4 + 1] & UByte.MAX_VALUE);
                            if (!this.glyphIds.contains(Integer.valueOf(i5))) {
                                if (treeSet == null) {
                                    treeSet = new TreeSet();
                                }
                                treeSet.add(Integer.valueOf(i5));
                            }
                            int i6 = i4 + 2;
                            i3 = (i & 1) != 0 ? i6 + 4 : i6 + 2;
                            if ((i & 128) != 0) {
                                i3 += 8;
                            } else if ((i & 64) != 0) {
                                i3 += 4;
                            } else if ((i & 8) != 0) {
                                i3 += 2;
                            }
                        } while ((i & 32) != 0);
                        j = offsets[next.intValue() + 1];
                    }
                }
                if (treeSet != null) {
                    this.glyphIds.addAll(treeSet);
                }
                if (treeSet != null) {
                    z = true;
                    continue;
                }
            } finally {
                originalData.close();
            }
        } while (z);
    }

    /* JADX WARN: Removed duplicated region for block: B:93:0x00fe A[Catch: all -> 0x0121, TryCatch #0 {all -> 0x0121, blocks: (B:57:0x001d, B:58:0x0030, B:60:0x0036, B:62:0x0060, B:64:0x0065, B:67:0x006b, B:69:0x00aa, B:71:0x00af, B:73:0x00b3, B:80:0x00c3, B:82:0x00c7, B:84:0x00cd, B:85:0x00dd, B:90:0x00f3, B:91:0x00f4, B:93:0x00fe, B:95:0x010d, B:74:0x00b6, B:76:0x00ba, B:77:0x00bd, B:79:0x00c1, B:70:0x00ad, B:89:0x00ee, B:96:0x0117), top: B:102:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] buildGlyfTable(long[] r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.fontbox.ttf.TTFSubsetter.buildGlyfTable(long[]):byte[]");
    }

    private int getNewGlyphId(Integer num) {
        return this.glyphIds.headSet(num).size();
    }

    private byte[] buildCmapTable() throws IOException {
        if (this.ttf.getCmap() == null || this.uniToGID.isEmpty()) {
            return null;
        }
        List<String> list = this.keepTables;
        if (list == null || list.contains(CmapTable.TAG)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            writeUint16(dataOutputStream, 0);
            writeUint16(dataOutputStream, 1);
            writeUint16(dataOutputStream, 3);
            writeUint16(dataOutputStream, 1);
            writeUint32(dataOutputStream, 12L);
            Iterator<Map.Entry<Integer, Integer>> it = this.uniToGID.entrySet().iterator();
            Map.Entry<Integer, Integer> next = it.next();
            int newGlyphId = getNewGlyphId(next.getValue());
            int size = this.uniToGID.size() + 1;
            int[] iArr = new int[size];
            int[] iArr2 = new int[size];
            int[] iArr3 = new int[size];
            int i = 0;
            int i2 = newGlyphId;
            Map.Entry<Integer, Integer> entry = next;
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> next2 = it.next();
                int newGlyphId2 = getNewGlyphId(next2.getValue());
                if (next2.getKey().intValue() > 65535) {
                    throw new UnsupportedOperationException("non-BMP Unicode character");
                }
                if (next2.getKey().intValue() != entry.getKey().intValue() + 1 || newGlyphId2 - i2 != next2.getKey().intValue() - next.getKey().intValue()) {
                    if (i2 != 0) {
                        iArr[i] = next.getKey().intValue();
                        iArr2[i] = entry.getKey().intValue();
                        iArr3[i] = i2 - next.getKey().intValue();
                    } else {
                        if (!next.getKey().equals(entry.getKey())) {
                            iArr[i] = next.getKey().intValue() + 1;
                            iArr2[i] = entry.getKey().intValue();
                            iArr3[i] = i2 - next.getKey().intValue();
                        }
                        next = next2;
                        i2 = newGlyphId2;
                    }
                    i++;
                    next = next2;
                    i2 = newGlyphId2;
                }
                entry = next2;
            }
            iArr[i] = next.getKey().intValue();
            iArr2[i] = entry.getKey().intValue();
            iArr3[i] = i2 - next.getKey().intValue();
            int i3 = i + 1;
            iArr[i3] = 65535;
            iArr2[i3] = 65535;
            iArr3[i3] = 1;
            int i4 = i3 + 1;
            int pow = ((int) Math.pow(2.0d, log2(i4))) * 2;
            writeUint16(dataOutputStream, 4);
            writeUint16(dataOutputStream, (i4 * 4 * 2) + 16);
            writeUint16(dataOutputStream, 0);
            int i5 = i4 * 2;
            writeUint16(dataOutputStream, i5);
            writeUint16(dataOutputStream, pow);
            writeUint16(dataOutputStream, log2(pow / 2));
            writeUint16(dataOutputStream, i5 - pow);
            for (int i6 = 0; i6 < i4; i6++) {
                writeUint16(dataOutputStream, iArr2[i6]);
            }
            writeUint16(dataOutputStream, 0);
            for (int i7 = 0; i7 < i4; i7++) {
                writeUint16(dataOutputStream, iArr[i7]);
            }
            for (int i8 = 0; i8 < i4; i8++) {
                writeUint16(dataOutputStream, iArr3[i8]);
            }
            for (int i9 = 0; i9 < i4; i9++) {
                writeUint16(dataOutputStream, 0);
            }
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    private byte[] buildPostTable() throws IOException {
        PostScriptTable postScript = this.ttf.getPostScript();
        if (postScript != null) {
            List<String> list = this.keepTables;
            if (list == null || list.contains(PostScriptTable.TAG)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                writeFixed(dataOutputStream, 2.0d);
                writeFixed(dataOutputStream, postScript.getItalicAngle());
                writeSInt16(dataOutputStream, postScript.getUnderlinePosition());
                writeSInt16(dataOutputStream, postScript.getUnderlineThickness());
                writeUint32(dataOutputStream, postScript.getIsFixedPitch());
                writeUint32(dataOutputStream, postScript.getMinMemType42());
                writeUint32(dataOutputStream, postScript.getMaxMemType42());
                writeUint32(dataOutputStream, postScript.getMinMemType1());
                writeUint32(dataOutputStream, postScript.getMaxMemType1());
                writeUint16(dataOutputStream, this.glyphIds.size());
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Integer num : this.glyphIds) {
                    String name = postScript.getName(num.intValue());
                    Integer num2 = WGL4Names.MAC_GLYPH_NAMES_INDICES.get(name);
                    if (num2 != null) {
                        writeUint16(dataOutputStream, num2.intValue());
                    } else {
                        Integer num3 = (Integer) linkedHashMap.get(name);
                        if (num3 == null) {
                            num3 = Integer.valueOf(linkedHashMap.size());
                            linkedHashMap.put(name, num3);
                        }
                        writeUint16(dataOutputStream, num3.intValue() + WGL4Names.NUMBER_OF_MAC_GLYPHS);
                    }
                }
                for (String str : linkedHashMap.keySet()) {
                    byte[] bytes = str.getBytes(Charset.forName("US-ASCII"));
                    writeUint8(dataOutputStream, bytes.length);
                    dataOutputStream.write(bytes);
                }
                dataOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            return null;
        }
        return null;
    }

    private byte[] buildHmtxTable() throws IOException {
        long copyBytes;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HorizontalHeaderTable horizontalHeader = this.ttf.getHorizontalHeader();
        HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
        InputStream originalData = this.ttf.getOriginalData();
        boolean z = true;
        int numberOfHMetrics = horizontalHeader.getNumberOfHMetrics() - 1;
        z = (this.glyphIds.last().intValue() <= numberOfHMetrics || this.glyphIds.contains(Integer.valueOf(numberOfHMetrics))) ? false : false;
        try {
            originalData.skip(horizontalMetrics.getOffset());
            long j = 0;
            boolean z2 = z;
            for (Integer num : this.glyphIds) {
                if (num.intValue() <= numberOfHMetrics) {
                    copyBytes = copyBytes(originalData, byteArrayOutputStream, num.intValue() * 4, j, 4);
                } else {
                    if (z2) {
                        j = copyBytes(originalData, byteArrayOutputStream, numberOfHMetrics * 4, j, 2);
                        z2 = false;
                    }
                    copyBytes = copyBytes(originalData, byteArrayOutputStream, (horizontalHeader.getNumberOfHMetrics() * 4) + ((num.intValue() - horizontalHeader.getNumberOfHMetrics()) * 2), j, 2);
                }
                j = copyBytes;
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            originalData.close();
        }
    }

    private long copyBytes(InputStream inputStream, OutputStream outputStream, long j, long j2, int i) throws IOException {
        long j3 = j - j2;
        if (j3 != inputStream.skip(j3)) {
            throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
        }
        byte[] bArr = new byte[i];
        if (i != inputStream.read(bArr, 0, i)) {
            throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
        }
        outputStream.write(bArr, 0, i);
        return j + i;
    }

    public void writeToStream(OutputStream outputStream) throws IOException {
        List<String> list;
        if (this.glyphIds.isEmpty() || this.uniToGID.isEmpty()) {
            Log.i("PdfBox-Android", "font subset is empty");
        }
        addCompoundReferences();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            long[] jArr = new long[this.glyphIds.size() + 1];
            byte[] buildHeadTable = buildHeadTable();
            byte[] buildHheaTable = buildHheaTable();
            byte[] buildMaxpTable = buildMaxpTable();
            byte[] buildNameTable = buildNameTable();
            byte[] buildOS2Table = buildOS2Table();
            byte[] buildGlyfTable = buildGlyfTable(jArr);
            byte[] buildLocaTable = buildLocaTable(jArr);
            byte[] buildCmapTable = buildCmapTable();
            byte[] buildHmtxTable = buildHmtxTable();
            byte[] buildPostTable = buildPostTable();
            TreeMap treeMap = new TreeMap();
            if (buildOS2Table != null) {
                treeMap.put(OS2WindowsMetricsTable.TAG, buildOS2Table);
            }
            if (buildCmapTable != null) {
                treeMap.put(CmapTable.TAG, buildCmapTable);
            }
            treeMap.put(GlyphTable.TAG, buildGlyfTable);
            treeMap.put(HeaderTable.TAG, buildHeadTable);
            treeMap.put(HorizontalHeaderTable.TAG, buildHheaTable);
            treeMap.put(HorizontalMetricsTable.TAG, buildHmtxTable);
            treeMap.put(IndexToLocationTable.TAG, buildLocaTable);
            treeMap.put(MaximumProfileTable.TAG, buildMaxpTable);
            if (buildNameTable != null) {
                treeMap.put("name", buildNameTable);
            }
            if (buildPostTable != null) {
                treeMap.put(PostScriptTable.TAG, buildPostTable);
            }
            for (Map.Entry<String, TTFTable> entry : this.ttf.getTableMap().entrySet()) {
                String key = entry.getKey();
                TTFTable value = entry.getValue();
                if (!treeMap.containsKey(key) && ((list = this.keepTables) == null || list.contains(key))) {
                    treeMap.put(key, this.ttf.getTableBytes(value));
                }
            }
            long writeFileHeader = writeFileHeader(dataOutputStream, treeMap.size());
            long j = writeFileHeader;
            long size = (treeMap.size() * 16) + 12;
            for (Map.Entry entry2 : treeMap.entrySet()) {
                j += writeTableHeader(dataOutputStream, (String) entry2.getKey(), size, (byte[]) entry2.getValue());
                size += ((((byte[]) entry2.getValue()).length + 3) / 4) * 4;
            }
            long j2 = 2981146554L - (BodyPartID.bodyIdMax & j);
            buildHeadTable[8] = (byte) (j2 >>> 24);
            buildHeadTable[9] = (byte) (j2 >>> 16);
            buildHeadTable[10] = (byte) (j2 >>> 8);
            buildHeadTable[11] = (byte) j2;
            for (byte[] bArr : treeMap.values()) {
                writeTableBody(dataOutputStream, bArr);
            }
        } finally {
            dataOutputStream.close();
        }
    }

    private void writeFixed(DataOutputStream dataOutputStream, double d) throws IOException {
        double floor = Math.floor(d);
        dataOutputStream.writeShort((int) floor);
        dataOutputStream.writeShort((int) ((d - floor) * 65536.0d));
    }

    private void writeUint32(DataOutputStream dataOutputStream, long j) throws IOException {
        dataOutputStream.writeInt((int) j);
    }

    private void writeUint16(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeShort(i);
    }

    private void writeSInt16(DataOutputStream dataOutputStream, short s) throws IOException {
        dataOutputStream.writeShort(s);
    }

    private void writeUint8(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i);
    }

    private void writeLongDateTime(DataOutputStream dataOutputStream, Calendar calendar) throws IOException {
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar2.set(1904, 0, 1, 0, 0, 0);
        calendar2.set(14, 0);
        dataOutputStream.writeLong((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / 1000);
    }

    private long toUInt32(byte[] bArr) {
        return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (255 & bArr[3]);
    }

    private int log2(int i) {
        return (int) Math.floor(Math.log(i) / Math.log(2.0d));
    }
}
