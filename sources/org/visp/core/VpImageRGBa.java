package org.visp.core;

/* loaded from: classes4.dex */
public class VpImageRGBa {
    public final long nativeObj;

    private static native long n_VpImageRGBa();

    private static native long n_VpImageRGBa(int i, int i2);

    private static native long n_VpImageRGBa(int i, int i2, char c, char c2, char c3, char c4);

    private static native long n_VpImageRGBa(byte[] bArr, int i, int i2, boolean z);

    private static native int n_cols(long j);

    private static native String n_dump(long j);

    private static native byte[] n_getPixel(long j, int i, int i2);

    private static native byte[] n_getPixels(long j);

    private static native int n_rows(long j);

    public VpImageRGBa(long j) {
        if (j == 0) {
            throw new UnsupportedOperationException("Native object address is NULL");
        }
        this.nativeObj = j;
    }

    public VpImageRGBa() {
        this.nativeObj = n_VpImageRGBa();
    }

    public VpImageRGBa(int i, int i2) {
        this.nativeObj = n_VpImageRGBa(i, i2);
    }

    public VpImageRGBa(int i, int i2, VpRGBa vpRGBa) {
        this.nativeObj = n_VpImageRGBa(i, i2, vpRGBa.R, vpRGBa.G, vpRGBa.B, vpRGBa.A);
    }

    public VpImageRGBa(byte[] bArr, int i, int i2, boolean z) {
        this.nativeObj = n_VpImageRGBa(bArr, i, i2, z);
    }

    public int cols() {
        return n_cols(this.nativeObj);
    }

    public int rows() {
        return n_rows(this.nativeObj);
    }

    public VpRGBa getPixel(int i, int i2) {
        byte[] n_getPixel = n_getPixel(this.nativeObj, i, i2);
        int i3 = n_getPixel[0];
        if (i3 < 0) {
            i3 += 256;
        }
        char c = (char) i3;
        int i4 = n_getPixel[1];
        if (i4 < 0) {
            i4 += 256;
        }
        char c2 = (char) i4;
        int i5 = n_getPixel[2];
        if (i5 < 0) {
            i5 += 256;
        }
        char c3 = (char) i5;
        int i6 = n_getPixel[3];
        if (i6 < 0) {
            i6 += 256;
        }
        return new VpRGBa(c, c2, c3, (char) i6);
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
