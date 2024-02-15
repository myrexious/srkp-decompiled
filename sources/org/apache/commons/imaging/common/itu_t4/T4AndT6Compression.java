package org.apache.commons.imaging.common.itu_t4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.itu_t4.T4_T6_Tables;

/* loaded from: classes2.dex */
public final class T4AndT6Compression {
    public static final int BLACK = 1;
    public static final int WHITE = 0;
    private static final HuffmanTree<Integer> WHITE_RUN_LENGTHS = new HuffmanTree<>();
    private static final HuffmanTree<Integer> BLACK_RUN_LENGTHS = new HuffmanTree<>();
    private static final HuffmanTree<T4_T6_Tables.Entry> CONTROL_CODES = new HuffmanTree<>();

    static {
        T4_T6_Tables.Entry[] entryArr;
        T4_T6_Tables.Entry[] entryArr2;
        T4_T6_Tables.Entry[] entryArr3;
        T4_T6_Tables.Entry[] entryArr4;
        T4_T6_Tables.Entry[] entryArr5;
        try {
            for (T4_T6_Tables.Entry entry : T4_T6_Tables.WHITE_TERMINATING_CODES) {
                WHITE_RUN_LENGTHS.insert(entry.bitString, entry.value);
            }
            for (T4_T6_Tables.Entry entry2 : T4_T6_Tables.WHITE_MAKE_UP_CODES) {
                WHITE_RUN_LENGTHS.insert(entry2.bitString, entry2.value);
            }
            for (T4_T6_Tables.Entry entry3 : T4_T6_Tables.BLACK_TERMINATING_CODES) {
                BLACK_RUN_LENGTHS.insert(entry3.bitString, entry3.value);
            }
            for (T4_T6_Tables.Entry entry4 : T4_T6_Tables.BLACK_MAKE_UP_CODES) {
                BLACK_RUN_LENGTHS.insert(entry4.bitString, entry4.value);
            }
            for (T4_T6_Tables.Entry entry5 : T4_T6_Tables.ADDITIONAL_MAKE_UP_CODES) {
                WHITE_RUN_LENGTHS.insert(entry5.bitString, entry5.value);
                BLACK_RUN_LENGTHS.insert(entry5.bitString, entry5.value);
            }
            HuffmanTree<T4_T6_Tables.Entry> huffmanTree = CONTROL_CODES;
            huffmanTree.insert(T4_T6_Tables.EOL.bitString, T4_T6_Tables.EOL);
            huffmanTree.insert(T4_T6_Tables.EOL13.bitString, T4_T6_Tables.EOL13);
            huffmanTree.insert(T4_T6_Tables.EOL14.bitString, T4_T6_Tables.EOL14);
            huffmanTree.insert(T4_T6_Tables.EOL15.bitString, T4_T6_Tables.EOL15);
            huffmanTree.insert(T4_T6_Tables.EOL16.bitString, T4_T6_Tables.EOL16);
            huffmanTree.insert(T4_T6_Tables.EOL17.bitString, T4_T6_Tables.EOL17);
            huffmanTree.insert(T4_T6_Tables.EOL18.bitString, T4_T6_Tables.EOL18);
            huffmanTree.insert(T4_T6_Tables.EOL19.bitString, T4_T6_Tables.EOL19);
            huffmanTree.insert(T4_T6_Tables.P.bitString, T4_T6_Tables.P);
            huffmanTree.insert(T4_T6_Tables.H.bitString, T4_T6_Tables.H);
            huffmanTree.insert(T4_T6_Tables.V0.bitString, T4_T6_Tables.V0);
            huffmanTree.insert(T4_T6_Tables.VL1.bitString, T4_T6_Tables.VL1);
            huffmanTree.insert(T4_T6_Tables.VL2.bitString, T4_T6_Tables.VL2);
            huffmanTree.insert(T4_T6_Tables.VL3.bitString, T4_T6_Tables.VL3);
            huffmanTree.insert(T4_T6_Tables.VR1.bitString, T4_T6_Tables.VR1);
            huffmanTree.insert(T4_T6_Tables.VR2.bitString, T4_T6_Tables.VR2);
            huffmanTree.insert(T4_T6_Tables.VR3.bitString, T4_T6_Tables.VR3);
        } catch (HuffmanTreeException e) {
            throw new Error(e);
        }
    }

    private T4AndT6Compression() {
    }

    private static void compress1DLine(BitInputStreamFlexible bitInputStreamFlexible, BitArrayOutputStream bitArrayOutputStream, int[] iArr, int i) throws ImageWriteException {
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            try {
                int readBits = bitInputStreamFlexible.readBits(1);
                if (iArr != null) {
                    iArr[i4] = readBits;
                }
                if (i3 == readBits) {
                    i2++;
                } else {
                    writeRunLength(bitArrayOutputStream, i2, i3);
                    i2 = 1;
                    i3 = readBits;
                }
            } catch (IOException e) {
                throw new ImageWriteException("Error reading image to compress", (Throwable) e);
            }
        }
        writeRunLength(bitArrayOutputStream, i2, i3);
    }

    public static byte[] compressModifiedHuffman(byte[] bArr, int i, int i2) throws ImageWriteException {
        BitInputStreamFlexible bitInputStreamFlexible = new BitInputStreamFlexible(new ByteArrayInputStream(bArr));
        BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream();
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                compress1DLine(bitInputStreamFlexible, bitArrayOutputStream, null, i);
                bitInputStreamFlexible.flushCache();
                bitArrayOutputStream.flush();
            } catch (Throwable th) {
                try {
                    bitArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = bitArrayOutputStream.toByteArray();
        bitArrayOutputStream.close();
        return byteArray;
    }

    public static byte[] decompressModifiedHuffman(byte[] bArr, int i, int i2) throws ImageReadException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            BitInputStreamFlexible bitInputStreamFlexible = new BitInputStreamFlexible(byteArrayInputStream);
            BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream();
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < i) {
                    try {
                        int readTotalRunLength = readTotalRunLength(bitInputStreamFlexible, i5);
                        for (int i6 = 0; i6 < readTotalRunLength; i6++) {
                            bitArrayOutputStream.writeBit(i5);
                        }
                        i5 = 1 - i5;
                        i4 += readTotalRunLength;
                    } catch (Throwable th) {
                        try {
                            bitArrayOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                if (i4 == i) {
                    bitInputStreamFlexible.flushCache();
                    bitArrayOutputStream.flush();
                } else if (i4 > i) {
                    throw new ImageReadException("Unrecoverable row length error in image row " + i3);
                }
            }
            byte[] byteArray = bitArrayOutputStream.toByteArray();
            bitArrayOutputStream.close();
            bitInputStreamFlexible.close();
            byteArrayInputStream.close();
            return byteArray;
        } catch (IOException e) {
            throw new ImageReadException("Error reading image to decompress", e);
        }
    }

    public static byte[] compressT4_1D(byte[] bArr, int i, int i2, boolean z) throws ImageWriteException {
        BitInputStreamFlexible bitInputStreamFlexible = new BitInputStreamFlexible(new ByteArrayInputStream(bArr));
        BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream();
        try {
            if (z) {
                T4_T6_Tables.EOL16.writeBits(bitArrayOutputStream);
            } else {
                T4_T6_Tables.EOL.writeBits(bitArrayOutputStream);
            }
            for (int i3 = 0; i3 < i2; i3++) {
                compress1DLine(bitInputStreamFlexible, bitArrayOutputStream, null, i);
                if (z) {
                    int bitsAvailableInCurrentByte = bitArrayOutputStream.getBitsAvailableInCurrentByte();
                    if (bitsAvailableInCurrentByte < 4) {
                        bitArrayOutputStream.flush();
                        bitsAvailableInCurrentByte = 8;
                    }
                    while (bitsAvailableInCurrentByte > 4) {
                        bitArrayOutputStream.writeBit(0);
                        bitsAvailableInCurrentByte--;
                    }
                }
                T4_T6_Tables.EOL.writeBits(bitArrayOutputStream);
                bitInputStreamFlexible.flushCache();
            }
            byte[] byteArray = bitArrayOutputStream.toByteArray();
            bitArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                bitArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static byte[] decompressT4_1D(byte[] bArr, int i, int i2, boolean z) throws ImageReadException {
        BitInputStreamFlexible bitInputStreamFlexible = new BitInputStreamFlexible(new ByteArrayInputStream(bArr));
        BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream();
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                try {
                    if (!isEOL(CONTROL_CODES.decode(bitInputStreamFlexible), z)) {
                        throw new ImageReadException("Expected EOL not found");
                    }
                    int i4 = 0;
                    int i5 = 0;
                    while (i4 < i) {
                        int readTotalRunLength = readTotalRunLength(bitInputStreamFlexible, i5);
                        for (int i6 = 0; i6 < readTotalRunLength; i6++) {
                            bitArrayOutputStream.writeBit(i5);
                        }
                        i5 = 1 - i5;
                        i4 += readTotalRunLength;
                    }
                    if (i4 == i) {
                        bitArrayOutputStream.flush();
                    } else if (i4 > i) {
                        throw new ImageReadException("Unrecoverable row length error in image row " + i3);
                    }
                } catch (HuffmanTreeException e) {
                    throw new ImageReadException("Decompression error", e);
                }
            } catch (Throwable th) {
                try {
                    bitArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = bitArrayOutputStream.toByteArray();
        bitArrayOutputStream.close();
        return byteArray;
    }

    private static int compressT(int i, int i2, int i3, BitArrayOutputStream bitArrayOutputStream, int i4, int[] iArr) {
        T4_T6_Tables.Entry entry;
        int i5 = i2 - i3;
        if (-3 <= i5 && i5 <= 3) {
            if (i5 == -3) {
                entry = T4_T6_Tables.VL3;
            } else if (i5 == -2) {
                entry = T4_T6_Tables.VL2;
            } else if (i5 == -1) {
                entry = T4_T6_Tables.VL1;
            } else if (i5 == 0) {
                entry = T4_T6_Tables.V0;
            } else if (i5 == 1) {
                entry = T4_T6_Tables.VR1;
            } else if (i5 == 2) {
                entry = T4_T6_Tables.VR2;
            } else {
                entry = T4_T6_Tables.VR3;
            }
            entry.writeBits(bitArrayOutputStream);
            return i2;
        }
        int i6 = 1 - i4;
        int nextChangingElement = nextChangingElement(iArr, i6, i2 + 1);
        T4_T6_Tables.H.writeBits(bitArrayOutputStream);
        writeRunLength(bitArrayOutputStream, i2 - i, i4);
        writeRunLength(bitArrayOutputStream, nextChangingElement - i2, i6);
        return nextChangingElement;
    }

    public static byte[] compressT4_2D(byte[] bArr, int i, int i2, boolean z, int i3) throws ImageWriteException {
        int nextChangingElement;
        BitInputStreamFlexible bitInputStreamFlexible = new BitInputStreamFlexible(new ByteArrayInputStream(bArr));
        BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream();
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        if (z) {
            T4_T6_Tables.EOL16.writeBits(bitArrayOutputStream);
        } else {
            T4_T6_Tables.EOL.writeBits(bitArrayOutputStream);
        }
        int i4 = 0;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i2) {
            if (i6 > 0) {
                bitArrayOutputStream.writeBit(i4);
                for (int i7 = i4; i7 < i; i7++) {
                    try {
                        iArr4[i7] = bitInputStreamFlexible.readBits(1);
                    } catch (IOException e) {
                        throw new ImageWriteException("Error reading image to compress", (Throwable) e);
                    }
                }
                int nextChangingElement2 = nextChangingElement(iArr4, i4, i4);
                int nextChangingElement3 = nextChangingElement(iArr3, i4, i4);
                int nextChangingElement4 = nextChangingElement(iArr3, 1, nextChangingElement3 + 1);
                int i8 = nextChangingElement2;
                int i9 = nextChangingElement3;
                int i10 = i4;
                int i11 = i10;
                while (i10 < i) {
                    if (nextChangingElement4 < i8) {
                        T4_T6_Tables.P.writeBits(bitArrayOutputStream);
                        i10 = nextChangingElement4;
                    } else {
                        int i12 = i8;
                        i10 = compressT(i10, i8, i9, bitArrayOutputStream, i11, iArr4);
                        if (i10 == i12) {
                            i11 = 1 - i11;
                        }
                    }
                    int i13 = i11;
                    int changingElementAt = changingElementAt(iArr3, i10);
                    int i14 = i10 + 1;
                    i8 = nextChangingElement(iArr4, i13, i14);
                    if (i13 == changingElementAt) {
                        nextChangingElement = nextChangingElement(iArr3, changingElementAt, i14);
                    } else {
                        nextChangingElement = nextChangingElement(iArr3, 1 - changingElementAt, nextChangingElement(iArr3, changingElementAt, i14) + 1);
                    }
                    i9 = nextChangingElement;
                    nextChangingElement4 = nextChangingElement(iArr3, 1 - i13, i9 + 1);
                    i11 = i13;
                }
                int[] iArr5 = iArr4;
                iArr4 = iArr3;
                iArr3 = iArr5;
            } else {
                bitArrayOutputStream.writeBit(1);
                compress1DLine(bitInputStreamFlexible, bitArrayOutputStream, iArr3, i);
            }
            if (z) {
                int bitsAvailableInCurrentByte = bitArrayOutputStream.getBitsAvailableInCurrentByte();
                if (bitsAvailableInCurrentByte < 4) {
                    bitArrayOutputStream.flush();
                    bitsAvailableInCurrentByte = 8;
                }
                while (bitsAvailableInCurrentByte > 4) {
                    bitArrayOutputStream.writeBit(0);
                    bitsAvailableInCurrentByte--;
                }
            }
            T4_T6_Tables.EOL.writeBits(bitArrayOutputStream);
            i6++;
            if (i6 == i3) {
                i6 = 0;
            }
            bitInputStreamFlexible.flushCache();
            i5++;
            i4 = 0;
        }
        return bitArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x0098 A[Catch: HuffmanTreeException -> 0x0117, IOException | HuffmanTreeException -> 0x0119, all -> 0x012a, TryCatch #2 {all -> 0x012a, blocks: (B:95:0x000f, B:98:0x0015, B:100:0x0023, B:102:0x002a, B:104:0x0039, B:106:0x0045, B:133:0x0092, B:135:0x0098, B:137:0x00ac, B:136:0x009f, B:108:0x004a, B:110:0x004e, B:111:0x0062, B:132:0x008b, B:114:0x0068, B:117:0x006e, B:120:0x0074, B:123:0x007a, B:126:0x0080, B:129:0x0086, B:138:0x00b6, B:139:0x00d0, B:148:0x00ec, B:150:0x00f2, B:151:0x00f6, B:152:0x010e, B:142:0x00d5, B:144:0x00dc, B:153:0x010f, B:154:0x0116, B:160:0x0122, B:158:0x011a, B:159:0x0121), top: B:169:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x009f A[Catch: HuffmanTreeException -> 0x0117, IOException | HuffmanTreeException -> 0x0119, all -> 0x012a, TryCatch #2 {all -> 0x012a, blocks: (B:95:0x000f, B:98:0x0015, B:100:0x0023, B:102:0x002a, B:104:0x0039, B:106:0x0045, B:133:0x0092, B:135:0x0098, B:137:0x00ac, B:136:0x009f, B:108:0x004a, B:110:0x004e, B:111:0x0062, B:132:0x008b, B:114:0x0068, B:117:0x006e, B:120:0x0074, B:123:0x007a, B:126:0x0080, B:129:0x0086, B:138:0x00b6, B:139:0x00d0, B:148:0x00ec, B:150:0x00f2, B:151:0x00f6, B:152:0x010e, B:142:0x00d5, B:144:0x00dc, B:153:0x010f, B:154:0x0116, B:160:0x0122, B:158:0x011a, B:159:0x0121), top: B:169:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decompressT4_2D(byte[] r11, int r12, int r13, boolean r14) throws org.apache.commons.imaging.ImageReadException {
        /*
            Method dump skipped, instructions count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.common.itu_t4.T4AndT6Compression.decompressT4_2D(byte[], int, int, boolean):byte[]");
    }

    public static byte[] compressT6(byte[] bArr, int i, int i2) throws ImageWriteException {
        int nextChangingElement;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            BitInputStreamFlexible bitInputStreamFlexible = new BitInputStreamFlexible(byteArrayInputStream);
            try {
                BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream();
                int i3 = 0;
                int[] iArr = new int[i];
                int[] iArr2 = new int[i];
                int i4 = 0;
                while (i4 < i2) {
                    for (int i5 = i3; i5 < i; i5++) {
                        try {
                            iArr2[i5] = bitInputStreamFlexible.readBits(1);
                        } catch (IOException e) {
                            throw new ImageWriteException("Error reading image to compress", (Throwable) e);
                        }
                    }
                    int nextChangingElement2 = nextChangingElement(iArr2, i3, i3);
                    int nextChangingElement3 = nextChangingElement(iArr, i3, i3);
                    int nextChangingElement4 = nextChangingElement(iArr, 1, nextChangingElement3 + 1);
                    int i6 = nextChangingElement2;
                    int i7 = nextChangingElement3;
                    int i8 = i3;
                    int i9 = i8;
                    while (i8 < i) {
                        if (nextChangingElement4 < i6) {
                            T4_T6_Tables.P.writeBits(bitArrayOutputStream);
                            i8 = nextChangingElement4;
                        } else {
                            int i10 = i6;
                            i8 = compressT(i8, i6, i7, bitArrayOutputStream, i9, iArr2);
                            if (i8 == i10) {
                                i9 = 1 - i9;
                            }
                        }
                        int i11 = i9;
                        int changingElementAt = changingElementAt(iArr, i8);
                        int i12 = i8 + 1;
                        i6 = nextChangingElement(iArr2, i11, i12);
                        if (i11 == changingElementAt) {
                            nextChangingElement = nextChangingElement(iArr, changingElementAt, i12);
                        } else {
                            nextChangingElement = nextChangingElement(iArr, 1 - changingElementAt, nextChangingElement(iArr, changingElementAt, i12) + 1);
                        }
                        i7 = nextChangingElement;
                        nextChangingElement4 = nextChangingElement(iArr, 1 - i11, i7 + 1);
                        i9 = i11;
                    }
                    bitInputStreamFlexible.flushCache();
                    i4++;
                    i3 = 0;
                    int[] iArr3 = iArr2;
                    iArr2 = iArr;
                    iArr = iArr3;
                }
                T4_T6_Tables.EOL.writeBits(bitArrayOutputStream);
                T4_T6_Tables.EOL.writeBits(bitArrayOutputStream);
                byte[] byteArray = bitArrayOutputStream.toByteArray();
                bitInputStreamFlexible.close();
                byteArrayInputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    bitInputStreamFlexible.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e2) {
            throw new ImageWriteException("I/O error", (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0084 A[Catch: HuffmanTreeException -> 0x00de, TryCatch #0 {HuffmanTreeException -> 0x00de, blocks: (B:67:0x0015, B:69:0x0025, B:71:0x0031, B:98:0x007e, B:100:0x0084, B:102:0x0098, B:101:0x008b, B:73:0x0036, B:75:0x003a, B:76:0x004e, B:97:0x0077, B:79:0x0054, B:82:0x005a, B:85:0x0060, B:88:0x0066, B:91:0x006c, B:94:0x0072, B:103:0x00a2, B:104:0x00bc), top: B:116:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x008b A[Catch: HuffmanTreeException -> 0x00de, TryCatch #0 {HuffmanTreeException -> 0x00de, blocks: (B:67:0x0015, B:69:0x0025, B:71:0x0031, B:98:0x007e, B:100:0x0084, B:102:0x0098, B:101:0x008b, B:73:0x0036, B:75:0x003a, B:76:0x004e, B:97:0x0077, B:79:0x0054, B:82:0x005a, B:85:0x0060, B:88:0x0066, B:91:0x006c, B:94:0x0072, B:103:0x00a2, B:104:0x00bc), top: B:116:0x0015 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decompressT6(byte[] r11, int r12, int r13) throws org.apache.commons.imaging.ImageReadException {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.common.itu_t4.T4AndT6Compression.decompressT6(byte[], int, int):byte[]");
    }

    private static boolean isEOL(T4_T6_Tables.Entry entry, boolean z) {
        if (entry == T4_T6_Tables.EOL) {
            return true;
        }
        if (z) {
            return entry == T4_T6_Tables.EOL13 || entry == T4_T6_Tables.EOL14 || entry == T4_T6_Tables.EOL15 || entry == T4_T6_Tables.EOL16 || entry == T4_T6_Tables.EOL17 || entry == T4_T6_Tables.EOL18 || entry == T4_T6_Tables.EOL19;
        }
        return false;
    }

    private static void writeRunLength(BitArrayOutputStream bitArrayOutputStream, int i, int i2) {
        T4_T6_Tables.Entry[] entryArr;
        T4_T6_Tables.Entry[] entryArr2;
        if (i2 == 0) {
            entryArr = T4_T6_Tables.WHITE_MAKE_UP_CODES;
            entryArr2 = T4_T6_Tables.WHITE_TERMINATING_CODES;
        } else {
            entryArr = T4_T6_Tables.BLACK_MAKE_UP_CODES;
            entryArr2 = T4_T6_Tables.BLACK_TERMINATING_CODES;
        }
        while (i >= 1792) {
            T4_T6_Tables.Entry lowerBound = lowerBound(T4_T6_Tables.ADDITIONAL_MAKE_UP_CODES, i);
            lowerBound.writeBits(bitArrayOutputStream);
            i -= lowerBound.value.intValue();
        }
        while (i >= 64) {
            T4_T6_Tables.Entry lowerBound2 = lowerBound(entryArr, i);
            lowerBound2.writeBits(bitArrayOutputStream);
            i -= lowerBound2.value.intValue();
        }
        entryArr2[i].writeBits(bitArrayOutputStream);
    }

    private static T4_T6_Tables.Entry lowerBound(T4_T6_Tables.Entry[] entryArr, int i) {
        int i2;
        int length = entryArr.length - 1;
        int i3 = 0;
        do {
            int i4 = (i3 + length) >>> 1;
            if (entryArr[i4].value.intValue() <= i && ((i2 = i4 + 1) >= entryArr.length || i < entryArr[i2].value.intValue())) {
                return entryArr[i4];
            }
            if (entryArr[i4].value.intValue() > i) {
                length = i4 - 1;
                continue;
            } else {
                i3 = i4 + 1;
                continue;
            }
        } while (i3 < length);
        return entryArr[i3];
    }

    private static int readTotalRunLength(BitInputStreamFlexible bitInputStreamFlexible, int i) throws ImageReadException {
        Integer decode;
        int i2 = 0;
        do {
            if (i == 0) {
                try {
                    decode = WHITE_RUN_LENGTHS.decode(bitInputStreamFlexible);
                } catch (HuffmanTreeException e) {
                    throw new ImageReadException("Decompression error", e);
                }
            } else {
                decode = BLACK_RUN_LENGTHS.decode(bitInputStreamFlexible);
            }
            i2 += decode.intValue();
        } while (decode.intValue() > 63);
        return i2;
    }

    private static int changingElementAt(int[] iArr, int i) {
        if (i < 0 || i >= iArr.length) {
            return 0;
        }
        return iArr[i];
    }

    private static int nextChangingElement(int[] iArr, int i, int i2) {
        while (i2 < iArr.length && iArr[i2] == i) {
            i2++;
        }
        return Math.min(i2, iArr.length);
    }

    private static void fillRange(BitArrayOutputStream bitArrayOutputStream, int[] iArr, int i, int i2, int i3) {
        while (i < i2) {
            iArr[i] = i3;
            bitArrayOutputStream.writeBit(i3);
            i++;
        }
    }
}
