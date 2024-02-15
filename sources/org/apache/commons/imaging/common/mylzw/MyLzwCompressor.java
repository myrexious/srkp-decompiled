package org.apache.commons.imaging.common.mylzw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class MyLzwCompressor {
    private final ByteOrder byteOrder;
    private final int clearCode;
    private int codeSize;
    private int codes;
    private final boolean earlyLimit;
    private final int eoiCode;
    private final int initialCodeSize;
    private final Listener listener;
    private final Map<ByteArray, Integer> map;

    /* loaded from: classes2.dex */
    public interface Listener {
        void clearCode(int i);

        void dataCode(int i);

        void eoiCode(int i);

        void init(int i, int i2);
    }

    public MyLzwCompressor(int i, ByteOrder byteOrder, boolean z) {
        this(i, byteOrder, z, null);
    }

    public MyLzwCompressor(int i, ByteOrder byteOrder, boolean z, Listener listener) {
        this.codes = -1;
        this.map = new HashMap();
        this.listener = listener;
        this.byteOrder = byteOrder;
        this.earlyLimit = z;
        this.initialCodeSize = i;
        int i2 = 1 << i;
        this.clearCode = i2;
        int i3 = i2 + 1;
        this.eoiCode = i3;
        if (listener != null) {
            listener.init(i2, i3);
        }
        initializeStringTable();
    }

    private void initializeStringTable() {
        int i = this.initialCodeSize;
        this.codeSize = i;
        int i2 = (1 << i) + 2;
        this.map.clear();
        int i3 = 0;
        while (true) {
            this.codes = i3;
            int i4 = this.codes;
            if (i4 >= i2) {
                return;
            }
            if (i4 != this.clearCode && i4 != this.eoiCode) {
                this.map.put(arrayToKey((byte) i4), Integer.valueOf(this.codes));
            }
            i3 = this.codes + 1;
        }
    }

    private void clearTable() {
        initializeStringTable();
        incrementCodeSize();
    }

    private void incrementCodeSize() {
        int i = this.codeSize;
        if (i != 12) {
            this.codeSize = i + 1;
        }
    }

    private ByteArray arrayToKey(byte b) {
        return arrayToKey(new byte[]{b}, 0, 1);
    }

    /* loaded from: classes2.dex */
    public static final class ByteArray {
        private final byte[] bytes;
        private final int hash;
        private final int length;
        private final int start;

        ByteArray(byte[] bArr, int i, int i2) {
            this.bytes = bArr;
            this.start = i;
            this.length = i2;
            int i3 = i2;
            for (int i4 = 0; i4 < i2; i4++) {
                i3 = ((i3 + (i3 << 8)) ^ (bArr[i4 + i] & 255)) ^ i4;
            }
            this.hash = i3;
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ByteArray) {
                ByteArray byteArray = (ByteArray) obj;
                if (byteArray.hash == this.hash && byteArray.length == this.length) {
                    for (int i = 0; i < this.length; i++) {
                        if (byteArray.bytes[byteArray.start + i] != this.bytes[this.start + i]) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    private ByteArray arrayToKey(byte[] bArr, int i, int i2) {
        return new ByteArray(bArr, i, i2);
    }

    private void writeDataCode(MyBitOutputStream myBitOutputStream, int i) throws IOException {
        Listener listener = this.listener;
        if (listener != null) {
            listener.dataCode(i);
        }
        writeCode(myBitOutputStream, i);
    }

    private void writeClearCode(MyBitOutputStream myBitOutputStream) throws IOException {
        Listener listener = this.listener;
        if (listener != null) {
            listener.dataCode(this.clearCode);
        }
        writeCode(myBitOutputStream, this.clearCode);
    }

    private void writeEoiCode(MyBitOutputStream myBitOutputStream) throws IOException {
        Listener listener = this.listener;
        if (listener != null) {
            listener.eoiCode(this.eoiCode);
        }
        writeCode(myBitOutputStream, this.eoiCode);
    }

    private void writeCode(MyBitOutputStream myBitOutputStream, int i) throws IOException {
        myBitOutputStream.writeBits(i, this.codeSize);
    }

    private boolean isInTable(byte[] bArr, int i, int i2) {
        return this.map.containsKey(arrayToKey(bArr, i, i2));
    }

    private int codeFromString(byte[] bArr, int i, int i2) throws IOException {
        Integer num = this.map.get(arrayToKey(bArr, i, i2));
        if (num == null) {
            throw new IOException("CodeFromString");
        }
        return num.intValue();
    }

    private boolean addTableEntry(MyBitOutputStream myBitOutputStream, byte[] bArr, int i, int i2) throws IOException {
        return addTableEntry(myBitOutputStream, arrayToKey(bArr, i, i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean addTableEntry(org.apache.commons.imaging.common.mylzw.MyBitOutputStream r5, org.apache.commons.imaging.common.mylzw.MyLzwCompressor.ByteArray r6) throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4.codeSize
            r1 = 1
            int r2 = r1 << r0
            boolean r3 = r4.earlyLimit
            if (r3 == 0) goto Lb
            int r2 = r2 + (-1)
        Lb:
            int r3 = r4.codes
            if (r3 != r2) goto L1f
            r2 = 12
            if (r0 >= r2) goto L17
            r4.incrementCodeSize()
            goto L1f
        L17:
            r4.writeClearCode(r5)
            r4.clearTable()
            r5 = r1
            goto L20
        L1f:
            r5 = 0
        L20:
            if (r5 != 0) goto L32
            java.util.Map<org.apache.commons.imaging.common.mylzw.MyLzwCompressor$ByteArray, java.lang.Integer> r0 = r4.map
            int r2 = r4.codes
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r6, r2)
            int r6 = r4.codes
            int r6 = r6 + r1
            r4.codes = r6
        L32:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.common.mylzw.MyLzwCompressor.addTableEntry(org.apache.commons.imaging.common.mylzw.MyBitOutputStream, org.apache.commons.imaging.common.mylzw.MyLzwCompressor$ByteArray):boolean");
    }

    public byte[] compress(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        MyBitOutputStream myBitOutputStream = new MyBitOutputStream(byteArrayOutputStream, this.byteOrder);
        initializeStringTable();
        clearTable();
        writeClearCode(myBitOutputStream);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            int i4 = i2 + 1;
            if (isInTable(bArr, i, i4)) {
                i2 = i4;
            } else {
                writeDataCode(myBitOutputStream, codeFromString(bArr, i, i2));
                addTableEntry(myBitOutputStream, bArr, i, i4);
                i2 = 1;
                i = i3;
            }
        }
        writeDataCode(myBitOutputStream, codeFromString(bArr, i, i2));
        writeEoiCode(myBitOutputStream);
        myBitOutputStream.flushCache();
        return byteArrayOutputStream.toByteArray();
    }
}
