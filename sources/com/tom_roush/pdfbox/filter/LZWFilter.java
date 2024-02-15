package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageOutputStream;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class LZWFilter extends Filter {
    public static final long CLEAR_TABLE = 256;
    public static final long EOD = 257;

    private int calculateChunk(int i, int i2) {
        if (i >= 2048 - i2) {
            return 12;
        }
        if (i >= 1024 - i2) {
            return 11;
        }
        return i >= 512 - i2 ? 10 : 9;
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        COSDictionary decodeParams = getDecodeParams(cOSDictionary, i);
        int i2 = 1;
        int i3 = decodeParams.getInt(COSName.EARLY_CHANGE, 1);
        if (i3 == 0 || i3 == 1) {
            i2 = i3;
        }
        doLZWDecode(inputStream, Predictor.wrapPredictor(outputStream, decodeParams), i2);
        return new DecodeResult(cOSDictionary);
    }

    private void doLZWDecode(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        List<byte[]> arrayList = new ArrayList<>();
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(inputStream);
        loop0: while (true) {
            int i2 = 9;
            long j = -1;
            while (true) {
                try {
                    long readBits = memoryCacheImageInputStream.readBits(i2);
                    if (readBits == 257) {
                        break loop0;
                    } else if (readBits == 256) {
                        break;
                    } else {
                        if (readBits < arrayList.size()) {
                            byte[] bArr = arrayList.get((int) readBits);
                            byte b = bArr[0];
                            outputStream.write(bArr);
                            if (j != -1) {
                                checkIndexBounds(arrayList, j, memoryCacheImageInputStream);
                                byte[] bArr2 = arrayList.get((int) j);
                                byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length + 1);
                                copyOf[bArr2.length] = b;
                                arrayList.add(copyOf);
                            }
                        } else {
                            checkIndexBounds(arrayList, j, memoryCacheImageInputStream);
                            byte[] bArr3 = arrayList.get((int) j);
                            byte[] copyOf2 = Arrays.copyOf(bArr3, bArr3.length + 1);
                            copyOf2[bArr3.length] = bArr3[0];
                            outputStream.write(copyOf2);
                            arrayList.add(copyOf2);
                        }
                        i2 = calculateChunk(arrayList.size(), i);
                        j = readBits;
                    }
                } catch (EOFException unused) {
                    Log.w("PdfBox-Android", "Premature EOF in LZW stream, EOD code missing");
                }
            }
            arrayList = createCodeTable();
        }
        outputStream.flush();
    }

    private void checkIndexBounds(List<byte[]> list, long j, MemoryCacheImageInputStream memoryCacheImageInputStream) throws IOException {
        if (j < 0) {
            throw new IOException("negative array index: " + j + " near offset " + memoryCacheImageInputStream.getStreamPosition());
        }
        if (j >= list.size()) {
            throw new IOException("array index overflow: " + j + " >= " + list.size() + " near offset " + memoryCacheImageInputStream.getStreamPosition());
        }
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        List<byte[]> createCodeTable = createCodeTable();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(outputStream);
        memoryCacheImageOutputStream.writeBits(256L, 9);
        byte[] bArr = null;
        int i = -1;
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            }
            byte b = (byte) read;
            if (bArr == null) {
                bArr = new byte[]{b};
            } else {
                bArr = Arrays.copyOf(bArr, bArr.length + 1);
                bArr[bArr.length - 1] = b;
                int findPatternCode = findPatternCode(createCodeTable, bArr);
                if (findPatternCode == -1) {
                    int calculateChunk = calculateChunk(createCodeTable.size() - 1, 1);
                    memoryCacheImageOutputStream.writeBits(i, calculateChunk);
                    createCodeTable.add(bArr);
                    if (createCodeTable.size() == 4096) {
                        memoryCacheImageOutputStream.writeBits(256L, calculateChunk);
                        createCodeTable = createCodeTable();
                    }
                    bArr = new byte[]{b};
                } else {
                    i = findPatternCode;
                }
            }
            i = b & UByte.MAX_VALUE;
        }
        if (i != -1) {
            memoryCacheImageOutputStream.writeBits(i, calculateChunk(createCodeTable.size() - 1, 1));
        }
        memoryCacheImageOutputStream.writeBits(257L, calculateChunk(createCodeTable.size(), 1));
        memoryCacheImageOutputStream.writeBits(0L, 7);
        memoryCacheImageOutputStream.flush();
        memoryCacheImageOutputStream.close();
    }

    private int findPatternCode(List<byte[]> list, byte[] bArr) {
        int i = 0;
        int i2 = -1;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (size <= 257) {
                if (i2 != -1) {
                    return i2;
                }
                if (bArr.length > 1) {
                    return -1;
                }
            }
            byte[] bArr2 = list.get(size);
            if ((i2 != -1 || bArr2.length > i) && Arrays.equals(bArr2, bArr)) {
                i = bArr2.length;
                i2 = size;
            }
        }
        return i2;
    }

    private List<byte[]> createCodeTable() {
        ArrayList arrayList = new ArrayList(4096);
        for (int i = 0; i < 256; i++) {
            arrayList.add(new byte[]{(byte) (i & 255)});
        }
        arrayList.add(null);
        arrayList.add(null);
        return arrayList;
    }
}
