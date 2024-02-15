package org.visp.core;

/* loaded from: classes4.dex */
public class VpImageUChar {
    public final long nativeObj;

    private static native long n_VpImageUChar();

    private static native long n_VpImageUChar(int i, int i2);

    private static native long n_VpImageUChar(int i, int i2, byte b);

    private static native long n_VpImageUChar(byte[] bArr, int i, int i2, boolean z);

    private static native int n_cols(long j);

    private static native String n_dump(long j);

    private static native int n_getPixel(long j, int i, int i2);

    private static native byte[] n_getPixels(long j);

    private static native int n_rows(long j);

    public VpImageUChar(long j) {
        if (j == 0) {
            throw new UnsupportedOperationException("Native object address is NULL");
        }
        this.nativeObj = j;
    }

    public VpImageUChar() {
        this.nativeObj = n_VpImageUChar();
    }

    public VpImageUChar(int i, int i2) {
        this.nativeObj = n_VpImageUChar(i, i2);
    }

    public VpImageUChar(int i, int i2, byte b) {
        this.nativeObj = n_VpImageUChar(i, i2, b);
    }

    public VpImageUChar(byte[] bArr, int i, int i2, boolean z) {
        this.nativeObj = n_VpImageUChar(bArr, i, i2, z);
    }

    public int cols() {
        return n_cols(this.nativeObj);
    }

    public int rows() {
        return n_rows(this.nativeObj);
    }

    public int getPixel(int i, int i2) {
        return n_getPixel(this.nativeObj, i, i2);
    }

    public byte[] getPixels() {
        return n_getPixels(this.nativeObj);
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public String toString() {
        return n_dump(this.nativeObj);
    }
}
