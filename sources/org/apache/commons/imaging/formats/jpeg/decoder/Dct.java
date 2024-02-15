package org.apache.commons.imaging.formats.jpeg.decoder;

/* loaded from: classes2.dex */
final class Dct {
    private static final float A1;
    private static final float A2;
    private static final float A3;
    private static final float A4;
    private static final float A5;
    private static final float C2;
    private static final float C4;
    private static final float C6;
    private static final float[] DCT_SCALING_FACTORS = {(float) (0.5d / Math.sqrt(2.0d)), (float) (0.25d / Math.cos(0.19634954084936207d)), (float) (0.25d / Math.cos(0.39269908169872414d)), (float) (0.25d / Math.cos(0.5890486225480862d)), (float) (0.25d / Math.cos(0.7853981633974483d)), (float) (0.25d / Math.cos(0.9817477042468103d)), (float) (0.25d / Math.cos(1.1780972450961724d)), (float) (0.25d / Math.cos(1.3744467859455345d))};
    private static final float[] IDCT_SCALING_FACTORS = {(float) ((8.0d / Math.sqrt(2.0d)) * 0.0625d), (float) ((Math.cos(0.19634954084936207d) * 4.0d) * 0.125d), (float) ((Math.cos(0.39269908169872414d) * 4.0d) * 0.125d), (float) ((Math.cos(0.5890486225480862d) * 4.0d) * 0.125d), (float) ((Math.cos(0.7853981633974483d) * 4.0d) * 0.125d), (float) ((Math.cos(0.9817477042468103d) * 4.0d) * 0.125d), (float) ((Math.cos(1.1780972450961724d) * 4.0d) * 0.125d), (float) ((Math.cos(1.3744467859455345d) * 4.0d) * 0.125d)};
    private static final float Q;
    private static final float R;

    static {
        float cos = (float) Math.cos(0.7853981633974483d);
        A1 = cos;
        A2 = (float) (Math.cos(0.39269908169872414d) - Math.cos(1.1780972450961724d));
        A3 = cos;
        A4 = (float) (Math.cos(0.39269908169872414d) + Math.cos(1.1780972450961724d));
        A5 = (float) Math.cos(1.1780972450961724d);
        float cos2 = (float) (Math.cos(0.39269908169872414d) * 2.0d);
        C2 = cos2;
        C4 = (float) (Math.cos(0.7853981633974483d) * 2.0d);
        float cos3 = (float) (Math.cos(1.1780972450961724d) * 2.0d);
        C6 = cos3;
        Q = cos2 - cos3;
        R = cos2 + cos3;
    }

    private Dct() {
    }

    public static void scaleQuantizationVector(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            fArr[i] = fArr[i] * DCT_SCALING_FACTORS[i];
        }
    }

    public static void scaleDequantizationVector(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            fArr[i] = fArr[i] * IDCT_SCALING_FACTORS[i];
        }
    }

    public static void scaleQuantizationMatrix(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                int i3 = (i * 8) + i2;
                float f = fArr[i3];
                float[] fArr2 = DCT_SCALING_FACTORS;
                fArr[i3] = f * fArr2[i] * fArr2[i2];
            }
        }
    }

    public static void scaleDequantizationMatrix(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                int i3 = (i * 8) + i2;
                float f = fArr[i3];
                float[] fArr2 = IDCT_SCALING_FACTORS;
                fArr[i3] = f * fArr2[i] * fArr2[i2];
            }
        }
    }

    public static void forwardDCT8(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[7];
        float f3 = f + f2;
        float f4 = fArr[1];
        float f5 = fArr[6];
        float f6 = f4 + f5;
        float f7 = fArr[2];
        float f8 = fArr[5];
        float f9 = f7 + f8;
        float f10 = fArr[3];
        float f11 = fArr[4];
        float f12 = f10 + f11;
        float f13 = f7 - f8;
        float f14 = f4 - f5;
        float f15 = f - f2;
        float f16 = f3 + f12;
        float f17 = f6 + f9;
        float f18 = f3 - f12;
        float f19 = (f10 - f11) + f13;
        float f20 = f13 + f14;
        float f21 = f14 + f15;
        float f22 = ((f6 - f9) + f18) * A1;
        float f23 = (f21 - f19) * A5;
        float f24 = (f19 * A2) - f23;
        float f25 = f20 * A3;
        float f26 = (f21 * A4) - f23;
        float f27 = f15 + f25;
        float f28 = f15 - f25;
        fArr[0] = f16 + f17;
        fArr[4] = f16 - f17;
        fArr[2] = f18 + f22;
        fArr[6] = f18 - f22;
        fArr[5] = f28 + f24;
        fArr[1] = f27 + f26;
        fArr[7] = f27 - f26;
        fArr[3] = f28 - f24;
    }

    public static void forwardDCT8x8(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            int i2 = i * 8;
            float f = fArr[i2];
            int i3 = i2 + 7;
            float f2 = fArr[i3];
            float f3 = f + f2;
            int i4 = i2 + 1;
            float f4 = fArr[i4];
            int i5 = i2 + 6;
            float f5 = fArr[i5];
            float f6 = f4 + f5;
            int i6 = i2 + 2;
            float f7 = fArr[i6];
            int i7 = i2 + 5;
            float f8 = fArr[i7];
            float f9 = f7 + f8;
            int i8 = i2 + 3;
            float f10 = fArr[i8];
            int i9 = i2 + 4;
            float f11 = fArr[i9];
            float f12 = f10 + f11;
            float f13 = f7 - f8;
            float f14 = f4 - f5;
            float f15 = f - f2;
            float f16 = f3 + f12;
            float f17 = f6 + f9;
            float f18 = f3 - f12;
            float f19 = (f10 - f11) + f13;
            float f20 = f13 + f14;
            float f21 = f14 + f15;
            float f22 = ((f6 - f9) + f18) * A1;
            float f23 = (f21 - f19) * A5;
            float f24 = (f19 * A2) - f23;
            float f25 = f20 * A3;
            float f26 = (f21 * A4) - f23;
            float f27 = f15 + f25;
            float f28 = f15 - f25;
            fArr[i2] = f16 + f17;
            fArr[i9] = f16 - f17;
            fArr[i6] = f18 + f22;
            fArr[i5] = f18 - f22;
            fArr[i7] = f28 + f24;
            fArr[i4] = f27 + f26;
            fArr[i3] = f27 - f26;
            fArr[i8] = f28 - f24;
        }
        for (int i10 = 0; i10 < 8; i10++) {
            float f29 = fArr[i10];
            int i11 = i10 + 56;
            float f30 = fArr[i11];
            float f31 = f29 + f30;
            int i12 = i10 + 8;
            float f32 = fArr[i12];
            int i13 = i10 + 48;
            float f33 = fArr[i13];
            float f34 = f32 + f33;
            int i14 = i10 + 16;
            float f35 = fArr[i14];
            int i15 = i10 + 40;
            float f36 = fArr[i15];
            float f37 = f35 + f36;
            int i16 = i10 + 24;
            float f38 = fArr[i16];
            int i17 = i10 + 32;
            float f39 = fArr[i17];
            float f40 = f38 + f39;
            float f41 = f35 - f36;
            float f42 = f32 - f33;
            float f43 = f29 - f30;
            float f44 = f31 + f40;
            float f45 = f34 + f37;
            float f46 = f31 - f40;
            float f47 = (f38 - f39) + f41;
            float f48 = f41 + f42;
            float f49 = f42 + f43;
            float f50 = ((f34 - f37) + f46) * A1;
            float f51 = (f49 - f47) * A5;
            float f52 = (f47 * A2) - f51;
            float f53 = f48 * A3;
            float f54 = (f49 * A4) - f51;
            float f55 = f43 + f53;
            float f56 = f43 - f53;
            fArr[i10] = f44 + f45;
            fArr[i17] = f44 - f45;
            fArr[i14] = f46 + f50;
            fArr[i13] = f46 - f50;
            fArr[i15] = f56 + f52;
            fArr[i12] = f55 + f54;
            fArr[i11] = f55 - f54;
            fArr[i16] = f56 - f52;
        }
    }

    public static void inverseDCT8(float[] fArr) {
        float f = fArr[2];
        float f2 = fArr[6];
        float f3 = f - f2;
        float f4 = f + f2;
        float f5 = fArr[5];
        float f6 = fArr[3];
        float f7 = f5 - f6;
        float f8 = fArr[1];
        float f9 = fArr[7];
        float f10 = f8 + f9;
        float f11 = f6 + f5;
        float f12 = f10 - f11;
        float f13 = f8 - f9;
        float f14 = f10 + f11;
        float f15 = C6 * (f7 + f13);
        float f16 = (Q * f7) + f15;
        float f17 = (R * f13) - f15;
        float f18 = C4;
        float f19 = f17 - f14;
        float f20 = f19 - (f12 * f18);
        float f21 = fArr[0];
        float f22 = fArr[4];
        float f23 = f21 - f22;
        float f24 = (f3 * f18) - f4;
        float f25 = f21 + f22;
        float f26 = f23 + f24;
        float f27 = f25 + f4;
        float f28 = f23 - f24;
        float f29 = f25 - f4;
        float f30 = f16 + f20;
        fArr[0] = f27 + f14;
        fArr[1] = f26 + f19;
        fArr[2] = f28 - f20;
        fArr[3] = f29 + f30;
        fArr[4] = f29 - f30;
        fArr[5] = f28 + f20;
        fArr[6] = f26 - f19;
        fArr[7] = f27 - f14;
    }

    public static void inverseDCT8x8(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            int i2 = i * 8;
            int i3 = i2 + 2;
            float f = fArr[i3];
            int i4 = i2 + 6;
            float f2 = fArr[i4];
            float f3 = f - f2;
            float f4 = f + f2;
            int i5 = i2 + 5;
            float f5 = fArr[i5];
            int i6 = i2 + 3;
            float f6 = fArr[i6];
            float f7 = f5 - f6;
            int i7 = i2 + 1;
            float f8 = fArr[i7];
            int i8 = i2 + 7;
            float f9 = fArr[i8];
            float f10 = f8 + f9;
            float f11 = f6 + f5;
            float f12 = f10 - f11;
            float f13 = f8 - f9;
            float f14 = f10 + f11;
            float f15 = C6 * (f7 + f13);
            float f16 = (Q * f7) + f15;
            float f17 = (R * f13) - f15;
            float f18 = C4;
            float f19 = f3 * f18;
            float f20 = f17 - f14;
            float f21 = f20 - (f12 * f18);
            float f22 = fArr[i2];
            int i9 = i2 + 4;
            float f23 = fArr[i9];
            float f24 = f22 - f23;
            float f25 = f19 - f4;
            float f26 = f22 + f23;
            float f27 = f24 + f25;
            float f28 = f26 + f4;
            float f29 = f24 - f25;
            float f30 = f26 - f4;
            float f31 = f16 + f21;
            fArr[i2] = f28 + f14;
            fArr[i7] = f27 + f20;
            fArr[i3] = f29 - f21;
            fArr[i6] = f30 + f31;
            fArr[i9] = f30 - f31;
            fArr[i5] = f29 + f21;
            fArr[i4] = f27 - f20;
            fArr[i8] = f28 - f14;
        }
        for (int i10 = 0; i10 < 8; i10++) {
            int i11 = i10 + 16;
            float f32 = fArr[i11];
            int i12 = i10 + 48;
            float f33 = fArr[i12];
            float f34 = f32 - f33;
            float f35 = f32 + f33;
            int i13 = i10 + 40;
            float f36 = fArr[i13];
            int i14 = i10 + 24;
            float f37 = fArr[i14];
            float f38 = f36 - f37;
            int i15 = i10 + 8;
            float f39 = fArr[i15];
            int i16 = i10 + 56;
            float f40 = fArr[i16];
            float f41 = f39 + f40;
            float f42 = f37 + f36;
            float f43 = f41 - f42;
            float f44 = f39 - f40;
            float f45 = f41 + f42;
            float f46 = C6 * (f38 + f44);
            float f47 = (Q * f38) + f46;
            float f48 = (R * f44) - f46;
            float f49 = C4;
            float f50 = f34 * f49;
            float f51 = f48 - f45;
            float f52 = f51 - (f43 * f49);
            float f53 = fArr[i10];
            int i17 = i10 + 32;
            float f54 = fArr[i17];
            float f55 = f53 - f54;
            float f56 = f50 - f35;
            float f57 = f53 + f54;
            float f58 = f55 + f56;
            float f59 = f57 + f35;
            float f60 = f55 - f56;
            float f61 = f57 - f35;
            float f62 = f47 + f52;
            fArr[i10] = f59 + f45;
            fArr[i15] = f58 + f51;
            fArr[i11] = f60 - f52;
            fArr[i14] = f61 + f62;
            fArr[i17] = f61 - f62;
            fArr[i13] = f60 + f52;
            fArr[i12] = f58 - f51;
            fArr[i16] = f59 - f45;
        }
    }
}
