package org.apache.commons.imaging.common.mylzw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public final class MyLzwDecompressor {
    private static final int MAX_TABLE_SIZE = 4096;
    private final ByteOrder byteOrder;
    private final int clearCode;
    private int codeSize;
    private int codes;
    private final int eoiCode;
    private final int initialCodeSize;
    private final Listener listener;
    private final byte[][] table;
    private boolean tiffLZWMode;
    private int written;

    /* loaded from: classes2.dex */
    public interface Listener {
        void code(int i);

        void init(int i, int i2);
    }

    public MyLzwDecompressor(int i, ByteOrder byteOrder) throws ImageReadException {
        this(i, byteOrder, null);
    }

    public MyLzwDecompressor(int i, ByteOrder byteOrder, Listener listener) throws ImageReadException {
        this.codes = -1;
        this.listener = listener;
        this.byteOrder = byteOrder;
        this.initialCodeSize = i;
        this.table = new byte[4096];
        int i2 = 1 << i;
        this.clearCode = i2;
        int i3 = i2 + 1;
        this.eoiCode = i3;
        if (listener != null) {
            listener.init(i2, i3);
        }
        initializeTable();
    }

    private void initializeTable() throws ImageReadException {
        int i = this.initialCodeSize;
        this.codeSize = i;
        int i2 = 1 << (i + 2);
        if (i2 > this.table.length) {
            throw new ImageReadException(String.format("Invalid Lzw table length [%d]; entries count is [%d]", Integer.valueOf(this.table.length), Integer.valueOf(i2)));
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte[][] bArr = this.table;
            byte[] bArr2 = new byte[1];
            bArr2[0] = (byte) i3;
            bArr[i3] = bArr2;
        }
    }

    private void clearTable() {
        int i = this.initialCodeSize;
        this.codes = (1 << i) + 2;
        this.codeSize = i;
        incrementCodeSize();
    }

    private int getNextCode(MyBitInputStream myBitInputStream) throws IOException {
        int readBits = myBitInputStream.readBits(this.codeSize);
        Listener listener = this.listener;
        if (listener != null) {
            listener.code(readBits);
        }
        return readBits;
    }

    private byte[] stringFromCode(int i) throws IOException {
        if (i >= this.codes || i < 0) {
            throw new IOException("Bad Code: " + i + " codes: " + this.codes + " code_size: " + this.codeSize + ", table: " + this.table.length);
        }
        return this.table[i];
    }

    private boolean isInTable(int i) {
        return i < this.codes;
    }

    private byte firstChar(byte[] bArr) {
        return bArr[0];
    }

    private void addStringToTable(byte[] bArr) {
        int i = this.codes;
        if (i < (1 << this.codeSize)) {
            this.table[i] = bArr;
            this.codes = i + 1;
        }
        checkCodeSize();
    }

    private byte[] appendBytes(byte[] bArr, byte b) {
        int length = bArr.length + 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[length - 1] = b;
        return bArr2;
    }

    private void writeToResult(OutputStream outputStream, byte[] bArr) throws IOException {
        outputStream.write(bArr);
        this.written += bArr.length;
    }

    public void setTiffLZWMode() {
        this.tiffLZWMode = true;
    }

    public byte[] decompress(InputStream inputStream, int i) throws IOException {
        MyBitInputStream myBitInputStream = new MyBitInputStream(inputStream, this.byteOrder);
        if (this.tiffLZWMode) {
            myBitInputStream.setTiffLZWMode();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        clearTable();
        int i2 = -1;
        do {
            int nextCode = getNextCode(myBitInputStream);
            if (nextCode == this.eoiCode) {
                break;
            } else if (nextCode == this.clearCode) {
                clearTable();
                if (this.written >= i || (i2 = getNextCode(myBitInputStream)) == this.eoiCode) {
                    break;
                }
                writeToResult(byteArrayOutputStream, stringFromCode(i2));
            } else {
                if (isInTable(nextCode)) {
                    writeToResult(byteArrayOutputStream, stringFromCode(nextCode));
                    addStringToTable(appendBytes(stringFromCode(i2), firstChar(stringFromCode(nextCode))));
                } else {
                    byte[] appendBytes = appendBytes(stringFromCode(i2), firstChar(stringFromCode(i2)));
                    writeToResult(byteArrayOutputStream, appendBytes);
                    addStringToTable(appendBytes);
                }
                i2 = nextCode;
            }
        } while (this.written < i);
        return byteArrayOutputStream.toByteArray();
    }

    private void checkCodeSize() {
        int i = 1 << this.codeSize;
        if (this.tiffLZWMode) {
            i--;
        }
        if (this.codes == i) {
            incrementCodeSize();
        }
    }

    private void incrementCodeSize() {
        int i = this.codeSize;
        if (i != 12) {
            this.codeSize = i + 1;
        }
    }
}
