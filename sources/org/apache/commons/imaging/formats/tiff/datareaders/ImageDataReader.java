package org.apache.commons.imaging.formats.tiff.datareaders;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.PackBits;
import org.apache.commons.imaging.common.ZlibDeflate;
import org.apache.commons.imaging.common.itu_t4.T4AndT6Compression;
import org.apache.commons.imaging.common.mylzw.MyLzwDecompressor;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffRasterData;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public abstract class ImageDataReader {
    private final int[] bitsPerSample;
    protected final int bitsPerSampleLength;
    protected final TiffDirectory directory;
    protected final int height;
    private final int[] last;
    protected final PhotometricInterpreter photometricInterpreter;
    protected final TiffPlanarConfiguration planarConfiguration;
    protected final int predictor;
    protected final int sampleFormat;
    protected final int samplesPerPixel;
    protected final int width;

    public abstract ImageBuilder readImageData(Rectangle rectangle, boolean z, boolean z2) throws ImageReadException, IOException;

    public abstract TiffRasterData readRasterData(Rectangle rectangle) throws ImageReadException, IOException;

    public ImageDataReader(TiffDirectory tiffDirectory, PhotometricInterpreter photometricInterpreter, int[] iArr, int i, int i2, int i3, int i4, int i5, TiffPlanarConfiguration tiffPlanarConfiguration) {
        this.directory = tiffDirectory;
        this.photometricInterpreter = photometricInterpreter;
        this.bitsPerSample = iArr;
        this.bitsPerSampleLength = iArr.length;
        this.samplesPerPixel = i2;
        this.sampleFormat = i3;
        this.predictor = i;
        this.width = i4;
        this.height = i5;
        this.planarConfiguration = tiffPlanarConfiguration;
        this.last = new int[i2];
    }

    public boolean isHomogenous(int i) {
        for (int i2 : this.bitsPerSample) {
            if (i2 != i) {
                return false;
            }
        }
        return true;
    }

    public void getSamplesAsBytes(BitInputStream bitInputStream, int[] iArr) throws IOException {
        int i = 0;
        while (true) {
            int[] iArr2 = this.bitsPerSample;
            if (i >= iArr2.length) {
                return;
            }
            int i2 = iArr2[i];
            int readBits = bitInputStream.readBits(i2);
            if (i2 < 8) {
                int i3 = readBits & 1;
                int i4 = 8 - i2;
                readBits <<= i4;
                if (i3 > 0) {
                    readBits = ((1 << i4) - 1) | readBits;
                }
            } else if (i2 > 8) {
                readBits >>= i2 - 8;
            }
            iArr[i] = readBits;
            i++;
        }
    }

    public void resetPredictor() {
        Arrays.fill(this.last, 0);
    }

    public int[] applyPredictor(int[] iArr) {
        if (this.predictor == 2) {
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                int[] iArr2 = this.last;
                int i3 = (i2 + iArr2[i]) & 255;
                iArr[i] = i3;
                iArr2[i] = i3;
            }
        }
        return iArr;
    }

    public void applyPredictorToBlock(int i, int i2, int i3, byte[] bArr) {
        int i4 = i * i3;
        int i5 = 0;
        while (i5 < i2) {
            i5++;
            int i6 = i5 * i4;
            for (int i7 = (i5 * i4) + i3; i7 < i6; i7++) {
                bArr[i7] = (byte) (bArr[i7] + bArr[i7 - i3]);
            }
        }
    }

    public byte[] decompress(byte[] bArr, int i, int i2, int i3, int i4) throws ImageReadException, IOException {
        byte[] bArr2;
        TiffField findField = this.directory.findField(TiffTagConstants.TIFF_TAG_FILL_ORDER);
        int intValue = findField != null ? findField.getIntValue() : 1;
        if (intValue == 1) {
            bArr2 = bArr;
        } else if (intValue == 2) {
            bArr2 = new byte[bArr.length];
            for (int i5 = 0; i5 < bArr.length; i5++) {
                bArr2[i5] = (byte) (Integer.reverse(bArr[i5] & UByte.MAX_VALUE) >>> 24);
            }
        } else {
            throw new ImageReadException("TIFF FillOrder=" + intValue + " is invalid");
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    TiffField findField2 = this.directory.findField(TiffTagConstants.TIFF_TAG_T4_OPTIONS);
                    int intValue2 = findField2 != null ? findField2.getIntValue() : 0;
                    boolean z = (intValue2 & 1) != 0;
                    if ((intValue2 & 2) != 0) {
                        throw new ImageReadException("T.4 compression with the uncompressed mode extension is not yet supported");
                    }
                    boolean z2 = (intValue2 & 4) != 0;
                    if (z) {
                        return T4AndT6Compression.decompressT4_2D(bArr2, i3, i4, z2);
                    }
                    return T4AndT6Compression.decompressT4_1D(bArr2, i3, i4, z2);
                } else if (i == 4) {
                    TiffField findField3 = this.directory.findField(TiffTagConstants.TIFF_TAG_T6_OPTIONS);
                    if (((findField3 != null ? findField3.getIntValue() : 0) & 2) != 0) {
                        throw new ImageReadException("T.6 compression with the uncompressed mode extension is not yet supported");
                    }
                    return T4AndT6Compression.decompressT6(bArr2, i3, i4);
                } else if (i == 5) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
                    MyLzwDecompressor myLzwDecompressor = new MyLzwDecompressor(8, ByteOrder.BIG_ENDIAN);
                    myLzwDecompressor.setTiffLZWMode();
                    return myLzwDecompressor.decompress(byteArrayInputStream, i2);
                } else {
                    if (i != 8) {
                        if (i == 32773) {
                            return new PackBits().decompress(bArr2, i2);
                        }
                        if (i != 32946) {
                            throw new ImageReadException("Tiff: unknown/unsupported compression: " + i);
                        }
                    }
                    return ZlibDeflate.decompress(bArr, i2);
                }
            }
            return T4AndT6Compression.decompressModifiedHuffman(bArr2, i3, i4);
        }
        return bArr2;
    }

    public int[] unpackFloatingPointSamples(int i, int i2, int i3, byte[] bArr, int i4, ByteOrder byteOrder) throws ImageReadException {
        int[] iArr;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12 = i;
        byte[] bArr2 = bArr;
        int i13 = this.samplesPerPixel;
        int i14 = i4 / i13;
        int i15 = i3 * i13 * (i14 / 8);
        int i16 = i15 * i2;
        int i17 = bArr2.length < i16 ? i16 / i15 : i2;
        int[] iArr2 = new int[i3 * i13 * i2];
        if (this.predictor == 3) {
            if (i4 / i13 != 32) {
                throw new ImageReadException("Imaging does not yet support floating-point data with predictor type 3 for " + i4 + " bits per sample");
            }
            if (this.planarConfiguration == TiffPlanarConfiguration.CHUNKY) {
                int i18 = i3 * 4 * this.samplesPerPixel;
                for (int i19 = 0; i19 < i17; i19++) {
                    int i20 = i19 * i18;
                    int i21 = this.samplesPerPixel;
                    int i22 = (i3 * i21) + i20;
                    int i23 = (i3 * i21) + i22;
                    int i24 = (i21 * i3) + i23;
                    for (int i25 = 1; i25 < i18; i25++) {
                        int i26 = i20 + i25;
                        bArr2[i26] = (byte) (bArr2[i26] + bArr2[i26 - 1]);
                    }
                    int i27 = i19 * i3;
                    int i28 = 0;
                    while (i28 < this.samplesPerPixel * i12) {
                        iArr2[i27] = ((bArr2[i23 + i28] & UByte.MAX_VALUE) << 8) | ((bArr2[i20 + i28] & UByte.MAX_VALUE) << 24) | ((bArr2[i22 + i28] & UByte.MAX_VALUE) << 16) | (bArr2[i24 + i28] & UByte.MAX_VALUE);
                        i28++;
                        i18 = i18;
                        i20 = i20;
                        i27++;
                    }
                }
            } else {
                int i29 = i3 * 4;
                int i30 = 0;
                while (i30 < this.samplesPerPixel) {
                    int i31 = i30 * i17 * i3;
                    int i32 = i31 * 4;
                    int i33 = 0;
                    while (i33 < i17) {
                        int i34 = (i33 * i29) + i32;
                        int i35 = i34 + i3;
                        int i36 = i35 + i3;
                        int i37 = i36 + i3;
                        int i38 = 1;
                        while (i38 < i29) {
                            int i39 = i34 + i38;
                            bArr2[i39] = (byte) (bArr2[i39] + bArr2[i39 - 1]);
                            i38++;
                            i29 = i29;
                        }
                        int i40 = i29;
                        int i41 = (i33 * i3) + i31;
                        int i42 = 0;
                        while (i42 < i12) {
                            iArr2[i41] = ((bArr2[i34 + i42] & UByte.MAX_VALUE) << 24) | ((bArr2[i35 + i42] & UByte.MAX_VALUE) << 16) | ((bArr2[i36 + i42] & UByte.MAX_VALUE) << 8) | (bArr2[i37 + i42] & UByte.MAX_VALUE);
                            i42++;
                            i12 = i;
                            i31 = i31;
                            i41++;
                            i32 = i32;
                            i34 = i34;
                        }
                        i33++;
                        i12 = i;
                        i29 = i40;
                    }
                    i30++;
                    i12 = i;
                }
            }
            return iArr2;
        }
        if (i14 == 64) {
            int i43 = 0;
            int i44 = 0;
            int i45 = 0;
            while (i43 < i17) {
                int i46 = 0;
                while (i46 < i3) {
                    long j = bArr2[i44] & 255;
                    int i47 = i43;
                    long j2 = bArr2[i5] & 255;
                    int i48 = i46;
                    int i49 = i17;
                    long j3 = bArr2[i6] & 255;
                    int[] iArr3 = iArr2;
                    long j4 = bArr2[i7] & 255;
                    long j5 = bArr2[i8] & 255;
                    long j6 = bArr2[i9] & 255;
                    int i50 = i45;
                    long j7 = bArr2[i10] & 255;
                    int i51 = i44 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
                    long j8 = bArr2[i11] & 255;
                    long j9 = byteOrder == ByteOrder.LITTLE_ENDIAN ? (j6 << 40) | (j8 << 56) | (j7 << 48) | (j5 << 32) | (j4 << 24) | (j3 << 16) | (j2 << 8) | j : (j6 << 16) | (j << 56) | (j2 << 48) | (j3 << 40) | (j4 << 32) | (j5 << 24) | (j7 << 8) | j8;
                    i45 = i50 + 1;
                    iArr3[i50] = Float.floatToRawIntBits((float) Double.longBitsToDouble(j9));
                    i46 = i48 + 1;
                    iArr2 = iArr3;
                    bArr2 = bArr;
                    i44 = i51;
                    i43 = i47;
                    i17 = i49;
                }
                i43++;
                bArr2 = bArr;
            }
            iArr = iArr2;
        } else {
            int i52 = i17;
            iArr = iArr2;
            if (i14 != 32) {
                throw new ImageReadException("Imaging does not support floating-point samples with " + i4 + " bits per sample");
            }
            int i53 = 0;
            int i54 = 0;
            for (int i55 = 0; i55 < i52; i55++) {
                int i56 = 0;
                while (i56 < this.samplesPerPixel * i3) {
                    int i57 = i53 + 1;
                    int i58 = bArr[i53] & UByte.MAX_VALUE;
                    int i59 = i57 + 1;
                    int i60 = bArr[i57] & UByte.MAX_VALUE;
                    int i61 = i59 + 1;
                    int i62 = bArr[i59] & UByte.MAX_VALUE;
                    int i63 = i61 + 1;
                    int i64 = bArr[i61] & UByte.MAX_VALUE;
                    iArr[i54] = byteOrder == ByteOrder.LITTLE_ENDIAN ? i58 | (i60 << 8) | (i62 << 16) | (i64 << 24) : (i58 << 24) | (i60 << 16) | (i62 << 8) | i64;
                    i56++;
                    i54++;
                    i53 = i63;
                }
            }
        }
        return iArr;
    }

    public int[] unpackIntSamples(int i, int i2, int i3, byte[] bArr, int i4, int i5, ByteOrder byteOrder) {
        int i6 = i5 / 8;
        int i7 = i6 * i3 * i2;
        int i8 = bArr.length < i7 ? i7 / i3 : i2;
        int[] iArr = new int[i3 * i2];
        int i9 = 2;
        boolean z = i4 == 2;
        int i10 = 0;
        while (i10 < i8) {
            int i11 = i10 * i3;
            int i12 = i11 * i6;
            if (i5 == 16) {
                if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                    int i13 = 0;
                    while (i13 < i) {
                        iArr[i11 + i13] = (bArr[i12 + 1] << 8) | (bArr[i12] & UByte.MAX_VALUE);
                        i13++;
                        i12 += i9;
                    }
                } else {
                    int i14 = 0;
                    while (i14 < i) {
                        iArr[i11 + i14] = (bArr[i12 + 1] & UByte.MAX_VALUE) | (bArr[i12] << 8);
                        i14++;
                        i12 += i9;
                    }
                }
            } else if (i5 == 32) {
                if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                    int i15 = 0;
                    while (i15 < i) {
                        iArr[i11 + i15] = ((bArr[i12 + 2] & UByte.MAX_VALUE) << 16) | (bArr[i12 + 3] << BuiltinOptions.BatchToSpaceNDOptions) | ((bArr[i12 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i12] & UByte.MAX_VALUE);
                        i15++;
                        i12 += 4;
                    }
                } else {
                    int i16 = 0;
                    while (i16 < i) {
                        iArr[i11 + i16] = (bArr[i12] << BuiltinOptions.BatchToSpaceNDOptions) | ((bArr[i12 + 1] & UByte.MAX_VALUE) << 16) | ((bArr[i12 + 2] & UByte.MAX_VALUE) << 8) | (bArr[i12 + 3] & UByte.MAX_VALUE);
                        i16++;
                        i12 += 4;
                    }
                }
            }
            if (z) {
                for (int i17 = 1; i17 < i; i17++) {
                    int i18 = i11 + i17;
                    iArr[i18] = iArr[i18] + iArr[i18 - 1];
                }
            }
            i10++;
            i9 = 2;
        }
        return iArr;
    }

    public void transferBlockToRaster(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6, int i7, int i8, int i9, float[] fArr) {
        int i10 = i3;
        int i11 = i - i5;
        int i12 = i2 - i6;
        int i13 = i11 + i10;
        int i14 = i12 + i4;
        if (i11 < 0) {
            i11 = 0;
        }
        if (i12 < 0) {
            i12 = 0;
        }
        if (i13 > i7) {
            i13 = i7;
        }
        if (i14 > i8) {
            i14 = i8;
        }
        int i15 = (i11 + i5) - i;
        int i16 = (i12 + i6) - i2;
        if (i15 < 0) {
            i11 -= i15;
            i15 = 0;
        }
        if (i16 < 0) {
            i12 -= i16;
            i16 = 0;
        }
        int i17 = i13 - i11;
        int i18 = i14 - i12;
        if (i17 <= 0 || i18 <= 0) {
            return;
        }
        if (i17 > i10) {
            i17 = i10;
        }
        if (i18 > i4) {
            i18 = i4;
        }
        if (i9 == 1) {
            for (int i19 = 0; i19 < i18; i19++) {
                int i20 = ((i12 + i19) * i7) + i11;
                int i21 = ((i16 + i19) * i10) + i15;
                for (int i22 = 0; i22 < i17; i22++) {
                    fArr[i20 + i22] = Float.intBitsToFloat(iArr[i21 + i22]);
                }
            }
        } else if (this.planarConfiguration == TiffPlanarConfiguration.CHUNKY) {
            int i23 = i7 * i8;
            for (int i24 = 0; i24 < i18; i24++) {
                int i25 = ((i12 + i24) * i7) + i11;
                int i26 = ((i16 + i24) * i10) + i15;
                for (int i27 = 0; i27 < i17; i27++) {
                    for (int i28 = 0; i28 < i9; i28++) {
                        fArr[(i28 * i23) + i25 + i27] = Float.intBitsToFloat(iArr[((i26 + i27) * i9) + i28]);
                    }
                }
            }
        } else {
            int i29 = 0;
            while (i29 < i9) {
                int i30 = i29 * i7 * i8;
                int i31 = i29 * i10 * i4;
                int i32 = 0;
                while (i32 < i18) {
                    int i33 = i30 + ((i12 + i32) * i7) + i11;
                    int i34 = i31 + ((i16 + i32) * i10) + i15;
                    for (int i35 = 0; i35 < i17; i35++) {
                        fArr[i33 + i35] = Float.intBitsToFloat(iArr[i34 + i35]);
                    }
                    i32++;
                    i10 = i3;
                }
                i29++;
                i10 = i3;
            }
        }
    }

    public void transferBlockToRaster(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6, int i7, int i8, int[] iArr2) {
        int i9 = i - i5;
        int i10 = i2 - i6;
        int i11 = i9 + i3;
        int i12 = i10 + i4;
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        if (i11 > i7) {
            i11 = i7;
        }
        if (i12 <= i8) {
            i8 = i12;
        }
        int i13 = (i5 + i9) - i;
        int i14 = (i6 + i10) - i2;
        if (i13 < 0) {
            i9 -= i13;
            i13 = 0;
        }
        if (i14 < 0) {
            i10 -= i14;
            i14 = 0;
        }
        int i15 = i11 - i9;
        int i16 = i8 - i10;
        if (i15 <= 0 || i16 <= 0) {
            return;
        }
        if (i15 > i3) {
            i15 = i3;
        }
        if (i16 <= i4) {
            i4 = i16;
        }
        for (int i17 = 0; i17 < i4; i17++) {
            System.arraycopy(iArr, ((i14 + i17) * i3) + i13, iArr2, ((i10 + i17) * i7) + i9, i15);
        }
    }
}
