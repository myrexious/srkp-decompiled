package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RC2Parameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class RC2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 8;
    private static byte[] piTable = {-39, 120, -7, -60, BuiltinOptions.SpaceToBatchNDOptions, -35, -75, -19, BuiltinOptions.ArgMaxOptions, -23, -3, 121, BuiltinOptions.ResizeNearestNeighborOptions, -96, -40, -99, -58, 126, 55, -125, BuiltinOptions.PadV2Options, 118, BuiltinOptions.GatherNdOptions, -114, BuiltinOptions.SelectV2Options, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.SegmentSumOptions, -120, BuiltinOptions.FillOptions, -117, -5, -94, BuiltinOptions.GatherOptions, -102, BuiltinOptions.QuantizeOptions, -11, -121, -77, BuiltinOptions.SplitVOptions, BuiltinOptions.SpaceToDepthOptions, BuiltinOptions.ScatterNdOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.HashtableImportOptions, -115, 9, -127, 125, 50, -67, -113, BuiltinOptions.UnpackOptions, -21, -122, -73, 123, 11, -16, -107, BuiltinOptions.ExpOptions, BuiltinOptions.TopKV2Options, BuiltinOptions.IfOptions, BuiltinOptions.HashtableOptions, BuiltinOptions.AbsOptions, -126, BuiltinOptions.CosOptions, -42, BuiltinOptions.BatchMatMulOptions, -109, -50, BuiltinOptions.NonMaxSuppressionV5Options, -78, BuiltinOptions.SubOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.RankOptions, -64, BuiltinOptions.EmbeddingLookupSparseOptions, -89, -116, -15, -36, BuiltinOptions.SkipGramOptions, BuiltinOptions.DynamicUpdateSliceOptions, -54, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.PackOptions, -66, -28, -47, BuiltinOptions.SquareOptions, 61, -44, BuiltinOptions.SliceOptions, -93, BuiltinOptions.LogicalOrOptions, -74, BuiltinOptions.DequantizeOptions, BuiltinOptions.VarHandleOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, 14, -38, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.Rfft2dOptions, 7, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.MaximumMinimumOptions, -14, BuiltinOptions.DivOptions, -101, PSSSigner.TRAILER_IMPLICIT, -108, BuiltinOptions.ZerosLikeOptions, 3, -8, BuiltinOptions.ReshapeOptions, -57, -10, -112, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.LogicalAndOptions, -25, 6, -61, -43, BuiltinOptions.SelectOptions, -56, BuiltinOptions.CumsumOptions, BuiltinOptions.SqueezeOptions, -41, 8, -24, -22, -34, ByteCompanionObject.MIN_VALUE, BuiltinOptions.AddNOptions, -18, -9, -124, -86, BuiltinOptions.RandomOptions, -84, 53, BuiltinOptions.MirrorPadOptions, BuiltinOptions.Conv3DOptions, BuiltinOptions.NegOptions, -106, BuiltinOptions.TransposeOptions, -46, BuiltinOptions.AssignVariableOptions, BuiltinOptions.MatrixSetDiagOptions, BuiltinOptions.MulOptions, BuiltinOptions.RangeOptions, BuiltinOptions.GeluOptions, BuiltinOptions.LeakyReluOptions, -97, -48, BuiltinOptions.DepthToSpaceOptions, 4, BuiltinOptions.BatchToSpaceNDOptions, -92, -20, -62, -32, BuiltinOptions.FloorDivOptions, BuiltinOptions.HashtableSizeOptions, 15, BuiltinOptions.ReverseV2Options, -53, -52, BuiltinOptions.LogSoftmaxOptions, -111, -81, 80, -95, -12, BuiltinOptions.ReadVariableOptions, BuiltinOptions.ArgMinOptions, -103, 124, BuiltinOptions.FakeQuantOptions, -123, BuiltinOptions.SplitOptions, -72, -76, 122, -4, 2, 54, BuiltinOptions.HardSwishOptions, BuiltinOptions.CastOptions, BuiltinOptions.WhereOptions, -105, 49, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.WhileOptions, -6, -104, -29, -118, -110, -82, 5, -33, BuiltinOptions.LessOptions, 16, BuiltinOptions.CallOnceOptions, BuiltinOptions.HashtableFindOptions, -70, -55, -45, 0, -26, -49, -31, -98, -88, BuiltinOptions.GreaterOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.PadOptions, 1, BuiltinOptions.LogicalNotOptions, BuiltinOptions.MatrixDiagOptions, -30, -119, -87, 13, BuiltinOptions.PowOptions, 52, BuiltinOptions.ReducerOptions, -85, 51, -1, -80, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.FloorModOptions, 12, BuiltinOptions.NonMaxSuppressionV4Options, -71, -79, -51, BuiltinOptions.LessEqualOptions, -59, -13, -37, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -27, -91, -100, 119, 10, -90, 32, BuiltinOptions.BroadcastToOptions, -2, ByteCompanionObject.MAX_VALUE, -63, -83};
    private boolean encrypting;
    private int[] workingKey;

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = ((bArr[i + 7] & UByte.MAX_VALUE) << 8) + (bArr[i + 6] & UByte.MAX_VALUE);
        int i4 = ((bArr[i + 5] & UByte.MAX_VALUE) << 8) + (bArr[i + 4] & UByte.MAX_VALUE);
        int i5 = ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + (bArr[i + 2] & UByte.MAX_VALUE);
        int i6 = ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + (bArr[i + 0] & UByte.MAX_VALUE);
        for (int i7 = 60; i7 >= 44; i7 -= 4) {
            i3 = rotateWordLeft(i3, 11) - ((((~i4) & i6) + (i5 & i4)) + this.workingKey[i7 + 3]);
            i4 = rotateWordLeft(i4, 13) - ((((~i5) & i3) + (i6 & i5)) + this.workingKey[i7 + 2]);
            i5 = rotateWordLeft(i5, 14) - ((((~i6) & i4) + (i3 & i6)) + this.workingKey[i7 + 1]);
            i6 = rotateWordLeft(i6, 15) - ((((~i3) & i5) + (i4 & i3)) + this.workingKey[i7]);
        }
        int[] iArr = this.workingKey;
        int i8 = i3 - iArr[i4 & 63];
        int i9 = i4 - iArr[i5 & 63];
        int i10 = i5 - iArr[i6 & 63];
        int i11 = i6 - iArr[i8 & 63];
        for (int i12 = 40; i12 >= 20; i12 -= 4) {
            i8 = rotateWordLeft(i8, 11) - ((((~i9) & i11) + (i10 & i9)) + this.workingKey[i12 + 3]);
            i9 = rotateWordLeft(i9, 13) - ((((~i10) & i8) + (i11 & i10)) + this.workingKey[i12 + 2]);
            i10 = rotateWordLeft(i10, 14) - ((((~i11) & i9) + (i8 & i11)) + this.workingKey[i12 + 1]);
            i11 = rotateWordLeft(i11, 15) - ((((~i8) & i10) + (i9 & i8)) + this.workingKey[i12]);
        }
        int[] iArr2 = this.workingKey;
        int i13 = i8 - iArr2[i9 & 63];
        int i14 = i9 - iArr2[i10 & 63];
        int i15 = i10 - iArr2[i11 & 63];
        int i16 = i11 - iArr2[i13 & 63];
        for (int i17 = 16; i17 >= 0; i17 -= 4) {
            i13 = rotateWordLeft(i13, 11) - ((((~i14) & i16) + (i15 & i14)) + this.workingKey[i17 + 3]);
            i14 = rotateWordLeft(i14, 13) - ((((~i15) & i13) + (i16 & i15)) + this.workingKey[i17 + 2]);
            i15 = rotateWordLeft(i15, 14) - ((((~i16) & i14) + (i13 & i16)) + this.workingKey[i17 + 1]);
            i16 = rotateWordLeft(i16, 15) - ((((~i13) & i15) + (i14 & i13)) + this.workingKey[i17]);
        }
        bArr2[i2 + 0] = (byte) i16;
        bArr2[i2 + 1] = (byte) (i16 >> 8);
        bArr2[i2 + 2] = (byte) i15;
        bArr2[i2 + 3] = (byte) (i15 >> 8);
        bArr2[i2 + 4] = (byte) i14;
        bArr2[i2 + 5] = (byte) (i14 >> 8);
        bArr2[i2 + 6] = (byte) i13;
        bArr2[i2 + 7] = (byte) (i13 >> 8);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = ((bArr[i + 7] & UByte.MAX_VALUE) << 8) + (bArr[i + 6] & UByte.MAX_VALUE);
        int i4 = ((bArr[i + 5] & UByte.MAX_VALUE) << 8) + (bArr[i + 4] & UByte.MAX_VALUE);
        int i5 = ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + (bArr[i + 2] & UByte.MAX_VALUE);
        int i6 = ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + (bArr[i + 0] & UByte.MAX_VALUE);
        for (int i7 = 0; i7 <= 16; i7 += 4) {
            i6 = rotateWordLeft(i6 + ((~i3) & i5) + (i4 & i3) + this.workingKey[i7], 1);
            i5 = rotateWordLeft(i5 + ((~i6) & i4) + (i3 & i6) + this.workingKey[i7 + 1], 2);
            i4 = rotateWordLeft(i4 + ((~i5) & i3) + (i6 & i5) + this.workingKey[i7 + 2], 3);
            i3 = rotateWordLeft(i3 + ((~i4) & i6) + (i5 & i4) + this.workingKey[i7 + 3], 5);
        }
        int[] iArr = this.workingKey;
        int i8 = i6 + iArr[i3 & 63];
        int i9 = i5 + iArr[i8 & 63];
        int i10 = i4 + iArr[i9 & 63];
        int i11 = i3 + iArr[i10 & 63];
        for (int i12 = 20; i12 <= 40; i12 += 4) {
            i8 = rotateWordLeft(i8 + ((~i11) & i9) + (i10 & i11) + this.workingKey[i12], 1);
            i9 = rotateWordLeft(i9 + ((~i8) & i10) + (i11 & i8) + this.workingKey[i12 + 1], 2);
            i10 = rotateWordLeft(i10 + ((~i9) & i11) + (i8 & i9) + this.workingKey[i12 + 2], 3);
            i11 = rotateWordLeft(i11 + ((~i10) & i8) + (i9 & i10) + this.workingKey[i12 + 3], 5);
        }
        int[] iArr2 = this.workingKey;
        int i13 = i8 + iArr2[i11 & 63];
        int i14 = i9 + iArr2[i13 & 63];
        int i15 = i10 + iArr2[i14 & 63];
        int i16 = i11 + iArr2[i15 & 63];
        for (int i17 = 44; i17 < 64; i17 += 4) {
            i13 = rotateWordLeft(i13 + ((~i16) & i14) + (i15 & i16) + this.workingKey[i17], 1);
            i14 = rotateWordLeft(i14 + ((~i13) & i15) + (i16 & i13) + this.workingKey[i17 + 1], 2);
            i15 = rotateWordLeft(i15 + ((~i14) & i16) + (i13 & i14) + this.workingKey[i17 + 2], 3);
            i16 = rotateWordLeft(i16 + ((~i15) & i13) + (i14 & i15) + this.workingKey[i17 + 3], 5);
        }
        bArr2[i2 + 0] = (byte) i13;
        bArr2[i2 + 1] = (byte) (i13 >> 8);
        bArr2[i2 + 2] = (byte) i14;
        bArr2[i2 + 3] = (byte) (i14 >> 8);
        bArr2[i2 + 4] = (byte) i15;
        bArr2[i2 + 5] = (byte) (i15 >> 8);
        bArr2[i2 + 6] = (byte) i16;
        bArr2[i2 + 7] = (byte) (i16 >> 8);
    }

    private int[] generateWorkingKey(byte[] bArr, int i) {
        int[] iArr = new int[128];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        int length = bArr.length;
        if (length < 128) {
            int i3 = iArr[length - 1];
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                i3 = piTable[(i3 + iArr[i4]) & 255] & UByte.MAX_VALUE;
                int i6 = length + 1;
                iArr[length] = i3;
                if (i6 >= 128) {
                    break;
                }
                length = i6;
                i4 = i5;
            }
        }
        int i7 = (i + 7) >> 3;
        int i8 = 128 - i7;
        int i9 = piTable[(255 >> ((-i) & 7)) & iArr[i8]] & UByte.MAX_VALUE;
        iArr[i8] = i9;
        for (int i10 = i8 - 1; i10 >= 0; i10--) {
            i9 = piTable[i9 ^ iArr[i10 + i7]] & UByte.MAX_VALUE;
            iArr[i10] = i9;
        }
        int[] iArr2 = new int[64];
        for (int i11 = 0; i11 != 64; i11++) {
            int i12 = i11 * 2;
            iArr2[i11] = iArr[i12] + (iArr[i12 + 1] << 8);
        }
        return iArr2;
    }

    private int rotateWordLeft(int i, int i2) {
        int i3 = i & 65535;
        return (i3 >> (16 - i2)) | (i3 << i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] key;
        this.encrypting = z;
        if (cipherParameters instanceof RC2Parameters) {
            RC2Parameters rC2Parameters = (RC2Parameters) cipherParameters;
            this.workingKey = generateWorkingKey(rC2Parameters.getKey(), rC2Parameters.getEffectiveKeyBits());
            key = rC2Parameters.getKey();
        } else if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to RC2 init - " + cipherParameters.getClass().getName());
        } else {
            key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = generateWorkingKey(key, key.length * 8);
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey != null) {
            if (i + 8 <= bArr.length) {
                if (i2 + 8 <= bArr2.length) {
                    if (this.encrypting) {
                        encryptBlock(bArr, i, bArr2, i2);
                        return 8;
                    }
                    decryptBlock(bArr, i, bArr2, i2);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("RC2 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
