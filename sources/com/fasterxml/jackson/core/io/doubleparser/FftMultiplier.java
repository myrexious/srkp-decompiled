package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;

/* loaded from: classes.dex */
class FftMultiplier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FFT_THRESHOLD = 33220;
    private static final int MAX_MAG_LENGTH = 67108864;
    private static final int ROOTS3_CACHE_SIZE = 20;
    private static final int ROOTS_CACHE2_SIZE = 20;
    private static final int TOOM_COOK_THRESHOLD = 1920;
    public static final double COS_0_25 = Math.cos(0.7853981633974483d);
    public static final double SIN_0_25 = Math.sin(0.7853981633974483d);
    private static volatile ComplexVector[] ROOTS2_CACHE = new ComplexVector[20];
    private static volatile ComplexVector[] ROOTS3_CACHE = new ComplexVector[20];

    static int bitsPerFftPoint(int i) {
        if (i <= 9728) {
            return 19;
        }
        if (i <= 18432) {
            return 18;
        }
        if (i <= 69632) {
            return 17;
        }
        if (i <= 262144) {
            return 16;
        }
        if (i <= 983040) {
            return 15;
        }
        if (i <= 3670016) {
            return 14;
        }
        if (i <= 13631488) {
            return 13;
        }
        if (i <= 25165824) {
            return 12;
        }
        if (i <= 92274688) {
            return 11;
        }
        if (i <= 335544320) {
            return 10;
        }
        return i <= 1207959552 ? 9 : 8;
    }

    FftMultiplier() {
    }

    private static ComplexVector calculateRootsOfUnity(int i) {
        if (i == 1) {
            ComplexVector complexVector = new ComplexVector(1);
            complexVector.real(0, 1.0d);
            complexVector.imag(0, 0.0d);
            return complexVector;
        }
        ComplexVector complexVector2 = new ComplexVector(i);
        complexVector2.set(0, 1.0d, 0.0d);
        int i2 = i / 2;
        complexVector2.set(i2, COS_0_25, SIN_0_25);
        double d = 1.5707963267948966d / i;
        for (int i3 = 1; i3 < i2; i3++) {
            double d2 = i3 * d;
            double cos = Math.cos(d2);
            double sin = Math.sin(d2);
            complexVector2.set(i3, cos, sin);
            complexVector2.set(i - i3, sin, cos);
        }
        return complexVector2;
    }

    private static void fft(ComplexVector complexVector, ComplexVector[] complexVectorArr) {
        int i = complexVector.length;
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i);
        MutableComplex mutableComplex = new MutableComplex();
        MutableComplex mutableComplex2 = new MutableComplex();
        MutableComplex mutableComplex3 = new MutableComplex();
        MutableComplex mutableComplex4 = new MutableComplex();
        MutableComplex mutableComplex5 = new MutableComplex();
        MutableComplex mutableComplex6 = new MutableComplex();
        while (numberOfLeadingZeros >= 2) {
            ComplexVector complexVector2 = complexVectorArr[numberOfLeadingZeros - 2];
            int i2 = 1 << numberOfLeadingZeros;
            for (int i3 = 0; i3 < i; i3 += i2) {
                int i4 = 0;
                while (true) {
                    int i5 = i2 / 4;
                    if (i4 < i5) {
                        mutableComplex5.set(complexVector2, i4);
                        mutableComplex5.squareInto(mutableComplex6);
                        int i6 = i3 + i4;
                        int i7 = i5 + i6;
                        int i8 = i6 + (i2 / 2);
                        ComplexVector complexVector3 = complexVector2;
                        int i9 = i6 + ((i2 * 3) / 4);
                        complexVector.addInto(i6, complexVector, i7, mutableComplex);
                        mutableComplex.add(complexVector, i8);
                        mutableComplex.add(complexVector, i9);
                        complexVector.subtractTimesIInto(i6, complexVector, i7, mutableComplex2);
                        mutableComplex2.subtract(complexVector, i8);
                        mutableComplex2.addTimesI(complexVector, i9);
                        mutableComplex2.multiplyConjugate(mutableComplex5);
                        complexVector.subtractInto(i6, complexVector, i7, mutableComplex3);
                        mutableComplex3.add(complexVector, i8);
                        mutableComplex3.subtract(complexVector, i9);
                        mutableComplex3.multiplyConjugate(mutableComplex6);
                        complexVector.addTimesIInto(i6, complexVector, i7, mutableComplex4);
                        mutableComplex4.subtract(complexVector, i8);
                        mutableComplex4.subtractTimesI(complexVector, i9);
                        mutableComplex4.multiply(mutableComplex5);
                        mutableComplex.copyInto(complexVector, i6);
                        mutableComplex2.copyInto(complexVector, i7);
                        mutableComplex3.copyInto(complexVector, i8);
                        mutableComplex4.copyInto(complexVector, i9);
                        i4++;
                        complexVector2 = complexVector3;
                    }
                }
            }
            numberOfLeadingZeros -= 2;
        }
        if (numberOfLeadingZeros > 0) {
            for (int i10 = 0; i10 < i; i10 += 2) {
                complexVector.copyInto(i10, mutableComplex);
                int i11 = i10 + 1;
                complexVector.copyInto(i11, mutableComplex2);
                complexVector.add(i10, mutableComplex2);
                mutableComplex.subtractInto(mutableComplex2, complexVector, i11);
            }
        }
    }

    private static void fft3(ComplexVector complexVector, ComplexVector complexVector2, ComplexVector complexVector3, int i, double d) {
        double sqrt = i * (-0.5d) * Math.sqrt(3.0d);
        for (int i2 = 0; i2 < complexVector.length; i2++) {
            double real = complexVector.real(i2) + complexVector2.real(i2) + complexVector3.real(i2);
            double imag = complexVector.imag(i2) + complexVector2.imag(i2) + complexVector3.imag(i2);
            double imag2 = (complexVector3.imag(i2) - complexVector2.imag(i2)) * sqrt;
            double real2 = (complexVector2.real(i2) - complexVector3.real(i2)) * sqrt;
            double real3 = (complexVector2.real(i2) + complexVector3.real(i2)) * 0.5d;
            double imag3 = (complexVector2.imag(i2) + complexVector3.imag(i2)) * 0.5d;
            double real4 = (complexVector.real(i2) - real3) + imag2;
            double real5 = (complexVector.real(i2) - real3) - imag2;
            complexVector.real(i2, real * d);
            complexVector.imag(i2, imag * d);
            complexVector2.real(i2, real4 * d);
            complexVector2.imag(i2, ((complexVector.imag(i2) + real2) - imag3) * d);
            complexVector3.real(i2, real5 * d);
            complexVector3.imag(i2, ((complexVector.imag(i2) - real2) - imag3) * d);
        }
    }

    private static void fftMixedRadix(ComplexVector complexVector, ComplexVector[] complexVectorArr, ComplexVector complexVector2) {
        int i = complexVector.length / 3;
        ComplexVector complexVector3 = new ComplexVector(complexVector, 0, i);
        int i2 = i * 2;
        ComplexVector complexVector4 = new ComplexVector(complexVector, i, i2);
        ComplexVector complexVector5 = new ComplexVector(complexVector, i2, complexVector.length);
        fft3(complexVector3, complexVector4, complexVector5, 1, 1.0d);
        MutableComplex mutableComplex = new MutableComplex();
        for (int i3 = 0; i3 < complexVector.length / 4; i3++) {
            mutableComplex.set(complexVector2, i3);
            complexVector4.multiplyConjugate(i3, mutableComplex);
            complexVector5.multiplyConjugate(i3, mutableComplex);
            complexVector5.multiplyConjugate(i3, mutableComplex);
        }
        for (int i4 = complexVector.length / 4; i4 < i; i4++) {
            mutableComplex.set(complexVector2, i4 - (complexVector.length / 4));
            complexVector4.multiplyConjugateTimesI(i4, mutableComplex);
            complexVector5.multiplyConjugateTimesI(i4, mutableComplex);
            complexVector5.multiplyConjugateTimesI(i4, mutableComplex);
        }
        fft(complexVector3, complexVectorArr);
        fft(complexVector4, complexVectorArr);
        fft(complexVector5, complexVectorArr);
    }

    static BigInteger fromFftVector(ComplexVector complexVector, int i, int i2) {
        long j = i2;
        int min = (int) Math.min(complexVector.length, (2147483648L / j) + 1);
        int i3 = (int) ((((min * j) + 31) * 8) / 32);
        byte[] bArr = new byte[i3];
        int i4 = 1;
        int i5 = (1 << i2) - 1;
        int i6 = 32 - i2;
        int i7 = (i3 * 8) - i2;
        int i8 = 0;
        int i9 = i3 - 4;
        int min2 = Math.min(Math.max(0, i7 >> 3), i9);
        long j2 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i10 <= i4) {
            int i12 = i8;
            while (i12 < min) {
                long round = Math.round(complexVector.part(i12, i10)) + j2;
                int min3 = Math.min(Math.max(i8, i7 >> 3), i9);
                int i13 = min;
                i11 = (int) (((round & i5) << ((i6 - i7) + (min3 << 3))) | (i11 >>> ((min2 - min3) << 3)));
                FastDoubleSwar.writeIntBE(bArr, min3, i11);
                i7 -= i2;
                i12++;
                min2 = min3;
                j2 = round >> i2;
                min = i13;
                i9 = i9;
                i8 = 0;
            }
            i10++;
            i4 = 1;
            i8 = 0;
        }
        return new BigInteger(i, bArr);
    }

    private static ComplexVector[] getRootsOfUnity2(int i) {
        ComplexVector[] complexVectorArr = new ComplexVector[i + 1];
        while (i >= 0) {
            if (i < 20) {
                if (ROOTS2_CACHE[i] == null) {
                    ROOTS2_CACHE[i] = calculateRootsOfUnity(1 << i);
                }
                complexVectorArr[i] = ROOTS2_CACHE[i];
            } else {
                complexVectorArr[i] = calculateRootsOfUnity(1 << i);
            }
            i -= 2;
        }
        return complexVectorArr;
    }

    private static ComplexVector getRootsOfUnity3(int i) {
        if (i < 20) {
            if (ROOTS3_CACHE[i] == null) {
                ROOTS3_CACHE[i] = calculateRootsOfUnity(3 << i);
            }
            return ROOTS3_CACHE[i];
        }
        return calculateRootsOfUnity(3 << i);
    }

    private static void ifft(ComplexVector complexVector, ComplexVector[] complexVectorArr) {
        int i;
        int i2;
        int i3 = complexVector.length;
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i3);
        MutableComplex mutableComplex = new MutableComplex();
        MutableComplex mutableComplex2 = new MutableComplex();
        MutableComplex mutableComplex3 = new MutableComplex();
        MutableComplex mutableComplex4 = new MutableComplex();
        MutableComplex mutableComplex5 = new MutableComplex();
        MutableComplex mutableComplex6 = new MutableComplex();
        MutableComplex mutableComplex7 = new MutableComplex();
        MutableComplex mutableComplex8 = new MutableComplex();
        int i4 = 1;
        if (numberOfLeadingZeros % 2 != 0) {
            for (int i5 = 0; i5 < i3; i5 += 2) {
                int i6 = i5 + 1;
                complexVector.copyInto(i6, mutableComplex3);
                complexVector.copyInto(i5, mutableComplex);
                complexVector.add(i5, mutableComplex3);
                mutableComplex.subtractInto(mutableComplex3, complexVector, i6);
            }
            i = 2;
        } else {
            i = 1;
        }
        MutableComplex mutableComplex9 = new MutableComplex();
        MutableComplex mutableComplex10 = new MutableComplex();
        while (i <= numberOfLeadingZeros) {
            ComplexVector complexVector2 = complexVectorArr[i - 1];
            int i7 = i4 << (i + 1);
            int i8 = 0;
            while (i8 < i3) {
                int i9 = numberOfLeadingZeros;
                int i10 = 0;
                while (true) {
                    i2 = i3;
                    int i11 = i7 / 4;
                    if (i10 < i11) {
                        mutableComplex9.set(complexVector2, i10);
                        mutableComplex9.squareInto(mutableComplex10);
                        ComplexVector complexVector3 = complexVector2;
                        int i12 = i8 + i10;
                        int i13 = i11 + i12;
                        int i14 = i;
                        int i15 = i12 + (i7 / 2);
                        int i16 = i8;
                        int i17 = i12 + ((i7 * 3) / 4);
                        complexVector.copyInto(i12, mutableComplex);
                        complexVector.multiplyInto(i13, mutableComplex9, mutableComplex2);
                        complexVector.multiplyInto(i15, mutableComplex10, mutableComplex3);
                        complexVector.multiplyConjugateInto(i17, mutableComplex9, mutableComplex4);
                        mutableComplex.addInto(mutableComplex2, mutableComplex5);
                        mutableComplex5.add(mutableComplex3);
                        mutableComplex5.add(mutableComplex4);
                        mutableComplex.addTimesIInto(mutableComplex2, mutableComplex6);
                        mutableComplex6.subtract(mutableComplex3);
                        mutableComplex6.subtractTimesI(mutableComplex4);
                        mutableComplex.subtractInto(mutableComplex2, mutableComplex7);
                        mutableComplex7.add(mutableComplex3);
                        mutableComplex7.subtract(mutableComplex4);
                        mutableComplex.subtractTimesIInto(mutableComplex2, mutableComplex8);
                        mutableComplex8.subtract(mutableComplex3);
                        mutableComplex8.addTimesI(mutableComplex4);
                        mutableComplex5.copyInto(complexVector, i12);
                        mutableComplex6.copyInto(complexVector, i13);
                        mutableComplex7.copyInto(complexVector, i15);
                        mutableComplex8.copyInto(complexVector, i17);
                        i10++;
                        i3 = i2;
                        complexVector2 = complexVector3;
                        i = i14;
                        i8 = i16;
                    }
                }
                i8 += i7;
                numberOfLeadingZeros = i9;
                i3 = i2;
            }
            i += 2;
            i4 = 1;
        }
        int i18 = numberOfLeadingZeros;
        for (int i19 = 0; i19 < i3; i19++) {
            complexVector.timesTwoToThe(i19, -i18);
        }
    }

    private static void ifftMixedRadix(ComplexVector complexVector, ComplexVector[] complexVectorArr, ComplexVector complexVector2) {
        int i = complexVector.length / 3;
        ComplexVector complexVector3 = new ComplexVector(complexVector, 0, i);
        int i2 = i * 2;
        ComplexVector complexVector4 = new ComplexVector(complexVector, i, i2);
        ComplexVector complexVector5 = new ComplexVector(complexVector, i2, complexVector.length);
        ifft(complexVector3, complexVectorArr);
        ifft(complexVector4, complexVectorArr);
        ifft(complexVector5, complexVectorArr);
        MutableComplex mutableComplex = new MutableComplex();
        for (int i3 = 0; i3 < complexVector.length / 4; i3++) {
            mutableComplex.set(complexVector2, i3);
            complexVector4.multiply(i3, mutableComplex);
            complexVector5.multiply(i3, mutableComplex);
            complexVector5.multiply(i3, mutableComplex);
        }
        for (int i4 = complexVector.length / 4; i4 < i; i4++) {
            mutableComplex.set(complexVector2, i4 - (complexVector.length / 4));
            complexVector4.multiplyByIAnd(i4, mutableComplex);
            complexVector5.multiplyByIAnd(i4, mutableComplex);
            complexVector5.multiplyByIAnd(i4, mutableComplex);
        }
        fft3(complexVector3, complexVector4, complexVector5, -1, 0.3333333333333333d);
    }

    public static BigInteger multiply(BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger2.signum() == 0 || bigInteger.signum() == 0) {
            return BigInteger.ZERO;
        }
        if (bigInteger2 == bigInteger) {
            return square(bigInteger2);
        }
        int bitLength = bigInteger.bitLength();
        int bitLength2 = bigInteger2.bitLength();
        if (bitLength + bitLength2 <= 2147483648L) {
            if (bitLength > TOOM_COOK_THRESHOLD && bitLength2 > TOOM_COOK_THRESHOLD && (bitLength > FFT_THRESHOLD || bitLength2 > FFT_THRESHOLD)) {
                return multiplyFft(bigInteger, bigInteger2);
            }
            return bigInteger.multiply(bigInteger2);
        }
        throw new ArithmeticException("BigInteger would overflow supported range");
    }

    static BigInteger multiplyFft(BigInteger bigInteger, BigInteger bigInteger2) {
        int signum = bigInteger.signum() * bigInteger2.signum();
        if (bigInteger.signum() < 0) {
            bigInteger = bigInteger.negate();
        }
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger2.signum() < 0) {
            bigInteger2 = bigInteger2.negate();
        }
        byte[] byteArray2 = bigInteger2.toByteArray();
        int max = Math.max(byteArray.length, byteArray2.length) * 8;
        int bitsPerFftPoint = bitsPerFftPoint(max);
        int i = (((max + bitsPerFftPoint) - 1) / bitsPerFftPoint) + 1;
        int numberOfLeadingZeros = 32 - Integer.numberOfLeadingZeros(i - 1);
        int i2 = 1 << numberOfLeadingZeros;
        int i3 = (i2 * 3) / 4;
        if (i < i3 && numberOfLeadingZeros > 3) {
            int i4 = numberOfLeadingZeros - 2;
            ComplexVector[] rootsOfUnity2 = getRootsOfUnity2(i4);
            ComplexVector rootsOfUnity3 = getRootsOfUnity3(i4);
            ComplexVector rootsOfUnity32 = getRootsOfUnity3(numberOfLeadingZeros - 4);
            ComplexVector fftVector = toFftVector(byteArray, i3, bitsPerFftPoint);
            fftVector.applyWeights(rootsOfUnity3);
            fftMixedRadix(fftVector, rootsOfUnity2, rootsOfUnity32);
            ComplexVector fftVector2 = toFftVector(byteArray2, i3, bitsPerFftPoint);
            fftVector2.applyWeights(rootsOfUnity3);
            fftMixedRadix(fftVector2, rootsOfUnity2, rootsOfUnity32);
            fftVector.multiplyPointwise(fftVector2);
            ifftMixedRadix(fftVector, rootsOfUnity2, rootsOfUnity32);
            fftVector.applyInverseWeights(rootsOfUnity3);
            return fromFftVector(fftVector, signum, bitsPerFftPoint);
        }
        ComplexVector[] rootsOfUnity22 = getRootsOfUnity2(numberOfLeadingZeros);
        ComplexVector fftVector3 = toFftVector(byteArray, i2, bitsPerFftPoint);
        fftVector3.applyWeights(rootsOfUnity22[numberOfLeadingZeros]);
        fft(fftVector3, rootsOfUnity22);
        ComplexVector fftVector4 = toFftVector(byteArray2, i2, bitsPerFftPoint);
        fftVector4.applyWeights(rootsOfUnity22[numberOfLeadingZeros]);
        fft(fftVector4, rootsOfUnity22);
        fftVector3.multiplyPointwise(fftVector4);
        ifft(fftVector3, rootsOfUnity22);
        fftVector3.applyInverseWeights(rootsOfUnity22[numberOfLeadingZeros]);
        return fromFftVector(fftVector3, signum, bitsPerFftPoint);
    }

    static BigInteger square(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return BigInteger.ZERO;
        }
        return bigInteger.bitLength() < FFT_THRESHOLD ? bigInteger.multiply(bigInteger) : squareFft(bigInteger);
    }

    static BigInteger squareFft(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length * 8;
        int bitsPerFftPoint = bitsPerFftPoint(length);
        int i = (((length + bitsPerFftPoint) - 1) / bitsPerFftPoint) + 1;
        int numberOfLeadingZeros = 32 - Integer.numberOfLeadingZeros(i - 1);
        int i2 = 1 << numberOfLeadingZeros;
        int i3 = (i2 * 3) / 4;
        if (i < i3) {
            ComplexVector fftVector = toFftVector(byteArray, i3, bitsPerFftPoint);
            int i4 = numberOfLeadingZeros - 2;
            ComplexVector[] rootsOfUnity2 = getRootsOfUnity2(i4);
            ComplexVector rootsOfUnity3 = getRootsOfUnity3(i4);
            ComplexVector rootsOfUnity32 = getRootsOfUnity3(numberOfLeadingZeros - 4);
            fftVector.applyWeights(rootsOfUnity3);
            fftMixedRadix(fftVector, rootsOfUnity2, rootsOfUnity32);
            fftVector.squarePointwise();
            ifftMixedRadix(fftVector, rootsOfUnity2, rootsOfUnity32);
            fftVector.applyInverseWeights(rootsOfUnity3);
            return fromFftVector(fftVector, 1, bitsPerFftPoint);
        }
        ComplexVector fftVector2 = toFftVector(byteArray, i2, bitsPerFftPoint);
        ComplexVector[] rootsOfUnity22 = getRootsOfUnity2(numberOfLeadingZeros);
        fftVector2.applyWeights(rootsOfUnity22[numberOfLeadingZeros]);
        fft(fftVector2, rootsOfUnity22);
        fftVector2.squarePointwise();
        ifft(fftVector2, rootsOfUnity22);
        fftVector2.applyInverseWeights(rootsOfUnity22[numberOfLeadingZeros]);
        return fromFftVector(fftVector2, 1, bitsPerFftPoint);
    }

    static ComplexVector toFftVector(byte[] bArr, int i, int i2) {
        int readIntBE;
        ComplexVector complexVector = new ComplexVector(i);
        if (bArr.length < 4) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, 0, bArr2, 4 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        int i3 = 1 << i2;
        int i4 = i3 / 2;
        int i5 = i3 - 1;
        int i6 = 32 - i2;
        int length = (bArr.length * 8) - i2;
        int i7 = 0;
        int i8 = 0;
        while (length > (-i2)) {
            int min = Math.min(Math.max(0, length >> 3), bArr.length - 4);
            i7 = (i4 - (((FastDoubleSwar.readIntBE(bArr, min) >>> ((i6 - length) + (min << 3))) & i5) + i7)) >>> 31;
            complexVector.real(i8, readIntBE - ((-i7) & i3));
            i8++;
            length -= i2;
        }
        if (i7 > 0) {
            complexVector.real(i8, i7);
        }
        return complexVector;
    }

    /* loaded from: classes.dex */
    public static final class ComplexVector {
        private static final int COMPLEX_SIZE_SHIFT = 1;
        private static final int IMAG = 1;
        private static final int REAL = 0;
        private final double[] a;
        private final int length;
        private final int offset;

        ComplexVector(int i) {
            this.a = new double[i << 1];
            this.length = i;
            this.offset = 0;
        }

        ComplexVector(ComplexVector complexVector, int i, int i2) {
            this.length = i2 - i;
            this.a = complexVector.a;
            this.offset = i << 1;
        }

        void add(int i, MutableComplex mutableComplex) {
            double[] dArr = this.a;
            int realIdx = realIdx(i);
            dArr[realIdx] = dArr[realIdx] + mutableComplex.real;
            double[] dArr2 = this.a;
            int imagIdx = imagIdx(i);
            dArr2[imagIdx] = dArr2[imagIdx] + mutableComplex.imag;
        }

        void addInto(int i, ComplexVector complexVector, int i2, MutableComplex mutableComplex) {
            mutableComplex.real = this.a[realIdx(i)] + complexVector.real(i2);
            mutableComplex.imag = this.a[imagIdx(i)] + complexVector.imag(i2);
        }

        void addTimesIInto(int i, ComplexVector complexVector, int i2, MutableComplex mutableComplex) {
            mutableComplex.real = this.a[realIdx(i)] - complexVector.imag(i2);
            mutableComplex.imag = this.a[imagIdx(i)] + complexVector.real(i2);
        }

        void applyInverseWeights(ComplexVector complexVector) {
            int i = this.offset;
            int i2 = complexVector.offset;
            double[] dArr = complexVector.a;
            for (int i3 = 0; i3 < this.length; i3++) {
                double[] dArr2 = this.a;
                double d = dArr2[i + 0];
                int i4 = i + 1;
                double d2 = dArr2[i4];
                int i5 = i2 + 0;
                int i6 = i2 + 1;
                dArr2[i] = FastDoubleSwar.fma(d, dArr[i5], d2 * dArr[i6]);
                this.a[i4] = FastDoubleSwar.fma(-d, dArr[i6], dArr[i5] * d2);
                i += 2;
                i2 += 2;
            }
        }

        void applyWeights(ComplexVector complexVector) {
            int i = complexVector.offset;
            double[] dArr = complexVector.a;
            int i2 = this.offset;
            int i3 = (this.length + i2) << 1;
            while (i2 < i3) {
                double[] dArr2 = this.a;
                int i4 = i2 + 0;
                double d = dArr2[i4];
                dArr2[i4] = dArr[i + 0] * d;
                dArr2[i2 + 1] = d * dArr[i + 1];
                i += 2;
                i2 += 2;
            }
        }

        void copyInto(int i, MutableComplex mutableComplex) {
            mutableComplex.real = this.a[realIdx(i)];
            mutableComplex.imag = this.a[imagIdx(i)];
        }

        double imag(int i) {
            return this.a[(i << 1) + this.offset + 1];
        }

        void imag(int i, double d) {
            this.a[(i << 1) + this.offset + 1] = d;
        }

        private int imagIdx(int i) {
            return (i << 1) + this.offset + 1;
        }

        void multiply(int i, MutableComplex mutableComplex) {
            int realIdx = realIdx(i);
            int imagIdx = imagIdx(i);
            double[] dArr = this.a;
            double d = dArr[realIdx];
            double d2 = dArr[imagIdx];
            dArr[realIdx] = FastDoubleSwar.fma(d, mutableComplex.real, mutableComplex.imag * (-d2));
            this.a[imagIdx] = FastDoubleSwar.fma(d, mutableComplex.imag, d2 * mutableComplex.real);
        }

        void multiplyByIAnd(int i, MutableComplex mutableComplex) {
            int realIdx = realIdx(i);
            int imagIdx = imagIdx(i);
            double[] dArr = this.a;
            double d = dArr[realIdx];
            double d2 = -dArr[imagIdx];
            dArr[realIdx] = FastDoubleSwar.fma(-d, mutableComplex.imag, mutableComplex.real * d2);
            this.a[imagIdx] = FastDoubleSwar.fma(d, mutableComplex.real, mutableComplex.imag * d2);
        }

        void multiplyConjugate(int i, MutableComplex mutableComplex) {
            int realIdx = realIdx(i);
            int imagIdx = imagIdx(i);
            double[] dArr = this.a;
            double d = dArr[realIdx];
            double d2 = dArr[imagIdx];
            dArr[realIdx] = FastDoubleSwar.fma(d, mutableComplex.real, d2 * mutableComplex.imag);
            this.a[imagIdx] = FastDoubleSwar.fma(-d, mutableComplex.imag, mutableComplex.real * d2);
        }

        void multiplyConjugateInto(int i, MutableComplex mutableComplex, MutableComplex mutableComplex2) {
            double d = this.a[realIdx(i)];
            double d2 = this.a[imagIdx(i)];
            mutableComplex2.real = FastDoubleSwar.fma(d, mutableComplex.real, d2 * mutableComplex.imag);
            mutableComplex2.imag = FastDoubleSwar.fma(-d, mutableComplex.imag, d2 * mutableComplex.real);
        }

        void multiplyConjugateTimesI(int i, MutableComplex mutableComplex) {
            int realIdx = realIdx(i);
            int imagIdx = imagIdx(i);
            double[] dArr = this.a;
            double d = dArr[realIdx];
            double d2 = dArr[imagIdx];
            double d3 = -d;
            dArr[realIdx] = FastDoubleSwar.fma(d3, mutableComplex.imag, d2 * mutableComplex.real);
            this.a[imagIdx] = FastDoubleSwar.fma(d3, mutableComplex.real, (-d2) * mutableComplex.imag);
        }

        void multiplyInto(int i, MutableComplex mutableComplex, MutableComplex mutableComplex2) {
            double d = this.a[realIdx(i)];
            double d2 = this.a[imagIdx(i)];
            mutableComplex2.real = FastDoubleSwar.fma(d, mutableComplex.real, (-d2) * mutableComplex.imag);
            mutableComplex2.imag = FastDoubleSwar.fma(d, mutableComplex.imag, d2 * mutableComplex.real);
        }

        void multiplyPointwise(ComplexVector complexVector) {
            int i = complexVector.offset;
            double[] dArr = complexVector.a;
            int i2 = this.offset;
            int i3 = (this.length + i2) << 1;
            while (i2 < i3) {
                double[] dArr2 = this.a;
                int i4 = i2 + 0;
                double d = dArr2[i4];
                int i5 = i2 + 1;
                double d2 = dArr2[i5];
                double d3 = dArr[i + 0];
                double d4 = dArr[i + 1];
                dArr2[i4] = FastDoubleSwar.fma(d, d3, (-d2) * d4);
                this.a[i5] = FastDoubleSwar.fma(d, d4, d2 * d3);
                i += 2;
                i2 += 2;
            }
        }

        double part(int i, int i2) {
            return this.a[(i << 1) + i2];
        }

        double real(int i) {
            return this.a[(i << 1) + this.offset];
        }

        void real(int i, double d) {
            this.a[(i << 1) + this.offset] = d;
        }

        private int realIdx(int i) {
            return (i << 1) + this.offset;
        }

        void set(int i, double d, double d2) {
            int realIdx = realIdx(i);
            double[] dArr = this.a;
            dArr[realIdx] = d;
            dArr[realIdx + 1] = d2;
        }

        void squarePointwise() {
            int i = this.offset;
            int i2 = (this.length + i) << 1;
            while (i < i2) {
                double[] dArr = this.a;
                int i3 = i + 0;
                double d = dArr[i3];
                int i4 = i + 1;
                double d2 = dArr[i4];
                dArr[i3] = FastDoubleSwar.fma(d, d, (-d2) * d2);
                this.a[i4] = d * 2.0d * d2;
                i += 2;
            }
        }

        void subtractInto(int i, ComplexVector complexVector, int i2, MutableComplex mutableComplex) {
            mutableComplex.real = this.a[realIdx(i)] - complexVector.real(i2);
            mutableComplex.imag = this.a[imagIdx(i)] - complexVector.imag(i2);
        }

        void subtractTimesIInto(int i, ComplexVector complexVector, int i2, MutableComplex mutableComplex) {
            mutableComplex.real = this.a[realIdx(i)] + complexVector.imag(i2);
            mutableComplex.imag = this.a[imagIdx(i)] - complexVector.real(i2);
        }

        void timesTwoToThe(int i, int i2) {
            int realIdx = realIdx(i);
            int imagIdx = imagIdx(i);
            double[] dArr = this.a;
            double d = dArr[realIdx];
            double d2 = dArr[imagIdx];
            dArr[realIdx] = Math.scalb(d, i2);
            this.a[imagIdx] = Math.scalb(d2, i2);
        }
    }

    /* loaded from: classes.dex */
    public static final class MutableComplex {
        double imag;
        double real;

        MutableComplex() {
        }

        void add(MutableComplex mutableComplex) {
            this.real += mutableComplex.real;
            this.imag += mutableComplex.imag;
        }

        void add(ComplexVector complexVector, int i) {
            this.real += complexVector.real(i);
            this.imag += complexVector.imag(i);
        }

        void addInto(MutableComplex mutableComplex, MutableComplex mutableComplex2) {
            mutableComplex2.real = this.real + mutableComplex.real;
            mutableComplex2.imag = this.imag + mutableComplex.imag;
        }

        void addTimesI(MutableComplex mutableComplex) {
            this.real -= mutableComplex.imag;
            this.imag += mutableComplex.real;
        }

        void addTimesI(ComplexVector complexVector, int i) {
            this.real -= complexVector.imag(i);
            this.imag += complexVector.real(i);
        }

        void addTimesIInto(MutableComplex mutableComplex, MutableComplex mutableComplex2) {
            mutableComplex2.real = this.real - mutableComplex.imag;
            mutableComplex2.imag = this.imag + mutableComplex.real;
        }

        void copyInto(ComplexVector complexVector, int i) {
            complexVector.real(i, this.real);
            complexVector.imag(i, this.imag);
        }

        void multiply(MutableComplex mutableComplex) {
            double d = this.real;
            this.real = FastDoubleSwar.fma(d, mutableComplex.real, mutableComplex.imag * (-this.imag));
            this.imag = FastDoubleSwar.fma(d, mutableComplex.imag, mutableComplex.real * this.imag);
        }

        void multiplyConjugate(MutableComplex mutableComplex) {
            double d = this.real;
            this.real = FastDoubleSwar.fma(d, mutableComplex.real, mutableComplex.imag * this.imag);
            this.imag = FastDoubleSwar.fma(-d, mutableComplex.imag, this.imag * mutableComplex.real);
        }

        void set(ComplexVector complexVector, int i) {
            this.real = complexVector.real(i);
            this.imag = complexVector.imag(i);
        }

        void squareInto(MutableComplex mutableComplex) {
            double d = this.real;
            double d2 = this.imag;
            mutableComplex.real = FastDoubleSwar.fma(d, d, (-d2) * d2);
            mutableComplex.imag = this.real * 2.0d * this.imag;
        }

        void subtract(MutableComplex mutableComplex) {
            this.real -= mutableComplex.real;
            this.imag -= mutableComplex.imag;
        }

        void subtract(ComplexVector complexVector, int i) {
            this.real -= complexVector.real(i);
            this.imag -= complexVector.imag(i);
        }

        void subtractInto(MutableComplex mutableComplex, MutableComplex mutableComplex2) {
            mutableComplex2.real = this.real - mutableComplex.real;
            mutableComplex2.imag = this.imag - mutableComplex.imag;
        }

        void subtractInto(MutableComplex mutableComplex, ComplexVector complexVector, int i) {
            complexVector.real(i, this.real - mutableComplex.real);
            complexVector.imag(i, this.imag - mutableComplex.imag);
        }

        void subtractTimesI(MutableComplex mutableComplex) {
            this.real += mutableComplex.imag;
            this.imag -= mutableComplex.real;
        }

        void subtractTimesI(ComplexVector complexVector, int i) {
            this.real += complexVector.imag(i);
            this.imag -= complexVector.real(i);
        }

        void subtractTimesIInto(MutableComplex mutableComplex, MutableComplex mutableComplex2) {
            mutableComplex2.real = this.real + mutableComplex.imag;
            mutableComplex2.imag = this.imag - mutableComplex.real;
        }
    }
}
