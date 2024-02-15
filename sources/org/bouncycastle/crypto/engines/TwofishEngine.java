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
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;
    private static final byte[][] P = {new byte[]{-87, BuiltinOptions.CallOnceOptions, -77, -24, 4, -3, -93, 118, -102, -110, ByteCompanionObject.MIN_VALUE, 120, -28, -35, -47, BuiltinOptions.PowOptions, 13, -58, 53, -104, BuiltinOptions.BatchToSpaceNDOptions, -9, -20, BuiltinOptions.HashtableFindOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.DynamicUpdateSliceOptions, 55, BuiltinOptions.DequantizeOptions, -6, BuiltinOptions.SpaceToDepthOptions, -108, BuiltinOptions.FloorModOptions, -14, -48, -117, BuiltinOptions.SliceOptions, -124, BuiltinOptions.CosOptions, -33, BuiltinOptions.SplitOptions, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.HardSwishOptions, 61, BuiltinOptions.QuantizeOptions, -13, -82, -94, -126, BuiltinOptions.DensifyOptions, 1, -125, BuiltinOptions.LessEqualOptions, -39, BuiltinOptions.ReverseV2Options, -101, 124, -90, -21, -91, -66, BuiltinOptions.PadOptions, 12, -29, BuiltinOptions.ScatterNdOptions, -64, -116, BuiltinOptions.FakeQuantOptions, -11, BuiltinOptions.BucketizeOptions, BuiltinOptions.GreaterOptions, BuiltinOptions.CastOptions, 11, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.AbsOptions, -119, BuiltinOptions.HashtableOptions, BuiltinOptions.GatherNdOptions, BuiltinOptions.Conv3DOptions, -76, -15, -31, -26, -67, BuiltinOptions.BidirectionalSequenceLSTMOptions, -30, -12, -74, BuiltinOptions.CumsumOptions, -52, -107, 3, BuiltinOptions.RankOptions, -44, BuiltinOptions.SubOptions, BuiltinOptions.SqueezeOptions, -41, -5, -61, -114, -75, -23, -49, ByteSourceJsonBootstrapper.UTF8_BOM_3, -70, -22, 119, BuiltinOptions.ArgMinOptions, -81, 51, -55, BuiltinOptions.SelectV2Options, BuiltinOptions.AssignVariableOptions, -127, 121, 9, -83, BuiltinOptions.LogSoftmaxOptions, -51, -7, -40, -27, -59, -71, BuiltinOptions.MirrorPadOptions, BuiltinOptions.FillOptions, 8, -122, -25, -95, BuiltinOptions.DivOptions, -86, -19, 6, BuiltinOptions.ReadVariableOptions, -78, -46, BuiltinOptions.FloorDivOptions, 123, -96, BuiltinOptions.ReshapeOptions, 49, -62, BuiltinOptions.MaximumMinimumOptions, -112, 32, -10, BuiltinOptions.NonMaxSuppressionV5Options, -1, -106, BuiltinOptions.IfOptions, -79, -85, -98, -100, BuiltinOptions.AddNOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.NonMaxSuppressionV4Options, -109, 10, ByteSourceJsonBootstrapper.UTF8_BOM_1, -111, -123, BuiltinOptions.RangeOptions, -18, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.SplitVOptions, -113, BuiltinOptions.PackOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -121, BuiltinOptions.HashtableImportOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, -42, BuiltinOptions.LogicalAndOptions, BuiltinOptions.Rfft2dOptions, BuiltinOptions.SegmentSumOptions, BuiltinOptions.NegOptions, -50, -53, BuiltinOptions.SelectOptions, -4, -105, 5, 122, -84, ByteCompanionObject.MAX_VALUE, -43, BuiltinOptions.TransposeOptions, BuiltinOptions.LeakyReluOptions, 14, -89, BuiltinOptions.MatrixSetDiagOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.LogicalNotOptions, BuiltinOptions.LessOptions, -120, BuiltinOptions.LogicalOrOptions, BuiltinOptions.SquaredDifferenceOptions, 2, -72, -38, -80, BuiltinOptions.GatherOptions, BuiltinOptions.WhereOptions, BuiltinOptions.SequenceRNNOptions, -118, 125, BuiltinOptions.ReverseSequenceOptions, -57, -115, BuiltinOptions.GeluOptions, -73, -60, -97, BuiltinOptions.RandomOptions, 126, BuiltinOptions.MulOptions, BuiltinOptions.TopKV2Options, BuiltinOptions.SkipGramOptions, BuiltinOptions.MatrixDiagOptions, 7, -103, 52, BuiltinOptions.HashtableSizeOptions, 80, -34, BuiltinOptions.BroadcastToOptions, BuiltinOptions.BatchMatMulOptions, PSSSigner.TRAILER_IMPLICIT, -37, -8, -56, -88, BuiltinOptions.PadV2Options, BuiltinOptions.UnpackOptions, -36, -2, 50, -92, -54, 16, BuiltinOptions.ExpOptions, -16, -45, BuiltinOptions.WhileOptions, 15, 0, BuiltinOptions.VarHandleOptions, -99, 54, BuiltinOptions.SquareOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.DepthToSpaceOptions, -63, -32}, new byte[]{BuiltinOptions.DynamicUpdateSliceOptions, -13, -58, -12, -37, 123, -5, -56, BuiltinOptions.ResizeNearestNeighborOptions, -45, -26, BuiltinOptions.HashtableOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, 125, -24, BuiltinOptions.LeakyReluOptions, -42, 50, -40, -3, 55, BuiltinOptions.AssignVariableOptions, -15, -31, BuiltinOptions.SliceOptions, 15, -8, BuiltinOptions.ReducerOptions, -121, -6, 6, BuiltinOptions.LogicalNotOptions, BuiltinOptions.DepthToSpaceOptions, -70, -82, BuiltinOptions.HardSwishOptions, -118, 0, PSSSigner.TRAILER_IMPLICIT, -99, BuiltinOptions.HashtableImportOptions, -63, -79, 14, ByteCompanionObject.MIN_VALUE, BuiltinOptions.WhileOptions, -46, -43, -96, -124, 7, BuiltinOptions.EmbeddingLookupSparseOptions, -75, -112, BuiltinOptions.GreaterOptions, -93, -78, BuiltinOptions.BucketizeOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.CosOptions, -110, BuiltinOptions.GeluOptions, 54, BuiltinOptions.ReverseV2Options, BuiltinOptions.PowOptions, -80, -67, BuiltinOptions.MatrixSetDiagOptions, -4, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.SelectV2Options, -106, BuiltinOptions.HashtableFindOptions, BuiltinOptions.SquareOptions, -9, 16, 124, BuiltinOptions.ArgMaxOptions, BuiltinOptions.MaximumMinimumOptions, -116, BuiltinOptions.SpaceToDepthOptions, -107, -100, -57, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.PackOptions, BuiltinOptions.ReadVariableOptions, -54, -29, -123, -53, BuiltinOptions.ReshapeOptions, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, BuiltinOptions.VarHandleOptions, 8, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.UnpackOptions, -25, BuiltinOptions.PadV2Options, -30, 121, 12, -86, -126, BuiltinOptions.FloorDivOptions, BuiltinOptions.FakeQuantOptions, -22, -71, -28, -102, -92, -105, 126, -38, 122, BuiltinOptions.GatherOptions, BuiltinOptions.CumsumOptions, -108, -95, BuiltinOptions.DivOptions, 61, -16, -34, -77, 11, BuiltinOptions.RandomOptions, -89, BuiltinOptions.SubOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, -47, BuiltinOptions.GatherNdOptions, BuiltinOptions.LogicalAndOptions, -113, 51, BuiltinOptions.DequantizeOptions, BuiltinOptions.NonMaxSuppressionV4Options, -20, 118, BuiltinOptions.NegOptions, BuiltinOptions.RangeOptions, -127, -120, -18, BuiltinOptions.ExpOptions, -60, BuiltinOptions.TransposeOptions, -21, -39, -59, BuiltinOptions.ArgMinOptions, -103, -51, -83, 49, -117, 1, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.SplitOptions, -35, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.AbsOptions, BuiltinOptions.GreaterEqualOptions, -7, BuiltinOptions.FloorModOptions, BuiltinOptions.SplitVOptions, -14, BuiltinOptions.BatchMatMulOptions, -114, 120, BuiltinOptions.IfOptions, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.SpaceToBatchNDOptions, -115, -27, -104, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.CallOnceOptions, ByteCompanionObject.MAX_VALUE, 5, BuiltinOptions.SegmentSumOptions, -81, BuiltinOptions.DensifyOptions, -74, -2, -11, -73, BuiltinOptions.LogicalOrOptions, -91, -50, -23, BuiltinOptions.BroadcastToOptions, BuiltinOptions.FillOptions, -32, BuiltinOptions.MirrorPadOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.Rfft2dOptions, BuiltinOptions.LessOptions, BuiltinOptions.LessEqualOptions, -84, BuiltinOptions.MulOptions, BuiltinOptions.QuantizeOptions, -88, 10, -98, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -33, 52, 53, BuiltinOptions.Conv3DOptions, -49, -36, BuiltinOptions.TopKV2Options, -55, -64, -101, -119, -44, -19, -85, BuiltinOptions.SkipGramOptions, -94, 13, BuiltinOptions.AddNOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, 2, BuiltinOptions.SelectOptions, -87, -41, BuiltinOptions.ScatterNdOptions, BuiltinOptions.SqueezeOptions, -76, 80, 4, -10, -62, BuiltinOptions.PadOptions, BuiltinOptions.CastOptions, -122, BuiltinOptions.RankOptions, BuiltinOptions.WhereOptions, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i = 0; i < 256; i++) {
            byte[][] bArr = P;
            int i2 = bArr[0][i] & UByte.MAX_VALUE;
            iArr[0] = i2;
            iArr2[0] = Mx_X(i2) & 255;
            iArr3[0] = Mx_Y(i2) & 255;
            int i3 = bArr[1][i] & UByte.MAX_VALUE;
            iArr[1] = i3;
            iArr2[1] = Mx_X(i3) & 255;
            int Mx_Y = Mx_Y(i3) & 255;
            iArr3[1] = Mx_Y;
            this.gMDS0[i] = (Mx_Y << 24) | iArr[1] | (iArr2[1] << 8) | (Mx_Y << 16);
            int[] iArr4 = this.gMDS1;
            int i4 = iArr3[0];
            iArr4[i] = i4 | (i4 << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            int[] iArr5 = this.gMDS2;
            int i5 = iArr2[1];
            int i6 = iArr3[1];
            iArr5[i] = (iArr[1] << 16) | i5 | (i6 << 8) | (i6 << 24);
            int[] iArr6 = this.gMDS3;
            int i7 = iArr2[0];
            iArr6[i] = (i7 << 24) | (iArr[0] << 8) | i7 | (iArr3[0] << 16);
        }
    }

    private int F32(int i, int[] iArr) {
        int i2;
        int i3;
        int b0 = b0(i);
        int b1 = b1(i);
        int b2 = b2(i);
        int b3 = b3(i);
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = this.k64Cnt & 3;
        if (i8 != 0) {
            if (i8 == 1) {
                int[] iArr2 = this.gMDS0;
                byte[][] bArr = P;
                i2 = (iArr2[(bArr[0][b0] & UByte.MAX_VALUE) ^ b0(i4)] ^ this.gMDS1[(bArr[0][b1] & UByte.MAX_VALUE) ^ b1(i4)]) ^ this.gMDS2[(bArr[1][b2] & UByte.MAX_VALUE) ^ b2(i4)];
                i3 = this.gMDS3[(bArr[1][b3] & UByte.MAX_VALUE) ^ b3(i4)];
                return i2 ^ i3;
            }
            if (i8 != 2) {
                if (i8 != 3) {
                    return 0;
                }
            }
            int[] iArr3 = this.gMDS0;
            byte[][] bArr2 = P;
            byte[] bArr3 = bArr2[0];
            i2 = (iArr3[(bArr3[(bArr3[b0] & UByte.MAX_VALUE) ^ b0(i5)] & UByte.MAX_VALUE) ^ b0(i4)] ^ this.gMDS1[(bArr2[0][(bArr2[1][b1] & UByte.MAX_VALUE) ^ b1(i5)] & UByte.MAX_VALUE) ^ b1(i4)]) ^ this.gMDS2[(bArr2[1][(bArr2[0][b2] & UByte.MAX_VALUE) ^ b2(i5)] & UByte.MAX_VALUE) ^ b2(i4)];
            int[] iArr4 = this.gMDS3;
            byte[] bArr4 = bArr2[1];
            i3 = iArr4[(bArr4[(bArr4[b3] & UByte.MAX_VALUE) ^ b3(i5)] & UByte.MAX_VALUE) ^ b3(i4)];
            return i2 ^ i3;
        }
        byte[][] bArr5 = P;
        b0 = (bArr5[1][b0] & UByte.MAX_VALUE) ^ b0(i7);
        b1 = (bArr5[0][b1] & UByte.MAX_VALUE) ^ b1(i7);
        b2 = (bArr5[0][b2] & UByte.MAX_VALUE) ^ b2(i7);
        b3 = (bArr5[1][b3] & UByte.MAX_VALUE) ^ b3(i7);
        byte[][] bArr6 = P;
        b0 = (bArr6[1][b0] & UByte.MAX_VALUE) ^ b0(i6);
        b1 = (bArr6[1][b1] & UByte.MAX_VALUE) ^ b1(i6);
        b2 = (bArr6[0][b2] & UByte.MAX_VALUE) ^ b2(i6);
        b3 = (bArr6[0][b3] & UByte.MAX_VALUE) ^ b3(i6);
        int[] iArr32 = this.gMDS0;
        byte[][] bArr22 = P;
        byte[] bArr32 = bArr22[0];
        i2 = (iArr32[(bArr32[(bArr32[b0] & UByte.MAX_VALUE) ^ b0(i5)] & UByte.MAX_VALUE) ^ b0(i4)] ^ this.gMDS1[(bArr22[0][(bArr22[1][b1] & UByte.MAX_VALUE) ^ b1(i5)] & UByte.MAX_VALUE) ^ b1(i4)]) ^ this.gMDS2[(bArr22[1][(bArr22[0][b2] & UByte.MAX_VALUE) ^ b2(i5)] & UByte.MAX_VALUE) ^ b2(i4)];
        int[] iArr42 = this.gMDS3;
        byte[] bArr42 = bArr22[1];
        i3 = iArr42[(bArr42[(bArr42[b3] & UByte.MAX_VALUE) ^ b3(i5)] & UByte.MAX_VALUE) ^ b3(i4)];
        return i2 ^ i3;
    }

    private int Fe32_0(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 24) & 255) * 2) + 513] ^ ((iArr[((i & 255) * 2) + 0] ^ iArr[(((i >>> 8) & 255) * 2) + 1]) ^ iArr[(((i >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 16) & 255) * 2) + 513] ^ ((iArr[(((i >>> 24) & 255) * 2) + 0] ^ iArr[((i & 255) * 2) + 1]) ^ iArr[(((i >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int i) {
        return ((i & 1) != 0 ? GF256_FDBK_2 : 0) ^ (i >> 1);
    }

    private int LFSR2(int i) {
        return ((i >> 2) ^ ((i & 2) != 0 ? GF256_FDBK_2 : 0)) ^ ((i & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int i) {
        return i ^ LFSR2(i);
    }

    private int Mx_Y(int i) {
        return LFSR2(i) ^ (LFSR1(i) ^ i);
    }

    private int RS_MDS_Encode(int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            i2 = RS_rem(i2);
        }
        int i4 = i ^ i2;
        for (int i5 = 0; i5 < 4; i5++) {
            i4 = RS_rem(i4);
        }
        return i4;
    }

    private int RS_rem(int i) {
        int i2 = (i >>> 24) & 255;
        int i3 = ((i2 << 1) ^ ((i2 & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int i4 = ((i2 >>> 1) ^ ((i2 & 1) != 0 ? 166 : 0)) ^ i3;
        return ((((i << 8) ^ (i4 << 24)) ^ (i3 << 16)) ^ (i4 << 8)) ^ i2;
    }

    private int b0(int i) {
        return i & 255;
    }

    private int b1(int i) {
        return (i >>> 8) & 255;
    }

    private int b2(int i) {
        return (i >>> 16) & 255;
    }

    private int b3(int i) {
        return (i >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i) ^ this.gSubKeys[4];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4) ^ this.gSubKeys[5];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8) ^ this.gSubKeys[6];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12) ^ this.gSubKeys[7];
        int i3 = 39;
        int i4 = 0;
        while (i4 < 16) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            int i5 = i3 - 1;
            int i6 = Fe32_0 + Fe32_3;
            int i7 = i5 - 1;
            littleEndianToInt3 = Integers.rotateLeft(littleEndianToInt3, 1) ^ (i6 + this.gSubKeys[i5]);
            littleEndianToInt4 = Integers.rotateRight(littleEndianToInt4 ^ (((Fe32_3 * 2) + Fe32_0) + this.gSubKeys[i3]), 1);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int i8 = i7 - 1;
            littleEndianToInt = Integers.rotateLeft(littleEndianToInt, 1) ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[i8]);
            littleEndianToInt2 = Integers.rotateRight(littleEndianToInt2 ^ (((Fe32_32 * 2) + Fe32_02) + this.gSubKeys[i7]), 1);
            i4 += 2;
            i3 = i8 - 1;
        }
        Pack.intToLittleEndian(this.gSubKeys[0] ^ littleEndianToInt3, bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[1], bArr2, i2 + 4);
        Pack.intToLittleEndian(this.gSubKeys[2] ^ littleEndianToInt, bArr2, i2 + 8);
        Pack.intToLittleEndian(this.gSubKeys[3] ^ littleEndianToInt2, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i) ^ this.gSubKeys[0];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4) ^ this.gSubKeys[1];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8) ^ this.gSubKeys[2];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12) ^ this.gSubKeys[3];
        int i4 = 8;
        while (i3 < 16) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            int i5 = i4 + 1;
            littleEndianToInt3 = Integers.rotateRight(littleEndianToInt3 ^ ((Fe32_0 + Fe32_3) + this.gSubKeys[i4]), 1);
            int i6 = Fe32_0 + (Fe32_3 * 2);
            int i7 = i5 + 1;
            littleEndianToInt4 = Integers.rotateLeft(littleEndianToInt4, 1) ^ (i6 + this.gSubKeys[i5]);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int i8 = i7 + 1;
            littleEndianToInt = Integers.rotateRight(littleEndianToInt ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[i7]), 1);
            littleEndianToInt2 = Integers.rotateLeft(littleEndianToInt2, 1) ^ ((Fe32_02 + (Fe32_32 * 2)) + this.gSubKeys[i8]);
            i3 += 2;
            i4 = i8 + 1;
        }
        Pack.intToLittleEndian(this.gSubKeys[4] ^ littleEndianToInt3, bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[5], bArr2, i2 + 4);
        Pack.intToLittleEndian(this.gSubKeys[6] ^ littleEndianToInt, bArr2, i2 + 8);
        Pack.intToLittleEndian(this.gSubKeys[7] ^ littleEndianToInt2, bArr2, i2 + 12);
    }

    private void setKey(byte[] bArr) {
        int b0;
        int b1;
        int b2;
        int b3;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.gSubKeys = new int[40];
        for (int i5 = 0; i5 < this.k64Cnt; i5++) {
            int i6 = i5 * 8;
            iArr[i5] = Pack.littleEndianToInt(bArr, i6);
            int littleEndianToInt = Pack.littleEndianToInt(bArr, i6 + 4);
            iArr2[i5] = littleEndianToInt;
            iArr3[(this.k64Cnt - 1) - i5] = RS_MDS_Encode(iArr[i5], littleEndianToInt);
        }
        for (int i7 = 0; i7 < 20; i7++) {
            int i8 = SK_STEP * i7;
            int F32 = F32(i8, iArr);
            int rotateLeft = Integers.rotateLeft(F32(i8 + SK_BUMP, iArr2), 8);
            int i9 = F32 + rotateLeft;
            int[] iArr4 = this.gSubKeys;
            int i10 = i7 * 2;
            iArr4[i10] = i9;
            int i11 = i9 + rotateLeft;
            iArr4[i10 + 1] = (i11 << 9) | (i11 >>> 23);
        }
        int i12 = iArr3[0];
        int i13 = iArr3[1];
        int i14 = 2;
        int i15 = iArr3[2];
        int i16 = iArr3[3];
        this.gSBox = new int[1024];
        int i17 = 0;
        while (i17 < 256) {
            int i18 = this.k64Cnt & 3;
            if (i18 != 0) {
                if (i18 == 1) {
                    int[] iArr5 = this.gSBox;
                    int i19 = i17 * 2;
                    int[] iArr6 = this.gMDS0;
                    byte[][] bArr2 = P;
                    iArr5[i19] = iArr6[(bArr2[0][i17] & UByte.MAX_VALUE) ^ b0(i12)];
                    this.gSBox[i19 + 1] = this.gMDS1[(bArr2[0][i17] & UByte.MAX_VALUE) ^ b1(i12)];
                    this.gSBox[i19 + 512] = this.gMDS2[(bArr2[1][i17] & UByte.MAX_VALUE) ^ b2(i12)];
                    this.gSBox[i19 + 513] = this.gMDS3[(bArr2[1][i17] & UByte.MAX_VALUE) ^ b3(i12)];
                } else if (i18 == i14) {
                    i4 = i17;
                    i3 = i4;
                    i2 = i3;
                    i = i2;
                    int[] iArr7 = this.gSBox;
                    int i20 = i17 * 2;
                    int[] iArr8 = this.gMDS0;
                    byte[][] bArr3 = P;
                    byte[] bArr4 = bArr3[0];
                    iArr7[i20] = iArr8[(bArr4[(bArr4[i3] & UByte.MAX_VALUE) ^ b0(i13)] & UByte.MAX_VALUE) ^ b0(i12)];
                    this.gSBox[i20 + 1] = this.gMDS1[(bArr3[0][(bArr3[1][i2] & UByte.MAX_VALUE) ^ b1(i13)] & UByte.MAX_VALUE) ^ b1(i12)];
                    this.gSBox[i20 + 512] = this.gMDS2[(bArr3[1][(bArr3[0][i] & UByte.MAX_VALUE) ^ b2(i13)] & UByte.MAX_VALUE) ^ b2(i12)];
                    int[] iArr9 = this.gMDS3;
                    byte[] bArr5 = bArr3[1];
                    this.gSBox[i20 + 513] = iArr9[(bArr5[(bArr5[i4] & UByte.MAX_VALUE) ^ b3(i13)] & UByte.MAX_VALUE) ^ b3(i12)];
                } else if (i18 == 3) {
                    b3 = i17;
                    b0 = b3;
                    b1 = b0;
                    b2 = b1;
                }
                i17++;
                i14 = 2;
            } else {
                byte[][] bArr6 = P;
                b0 = (bArr6[1][i17] & UByte.MAX_VALUE) ^ b0(i16);
                b1 = (bArr6[0][i17] & UByte.MAX_VALUE) ^ b1(i16);
                b2 = (bArr6[0][i17] & UByte.MAX_VALUE) ^ b2(i16);
                b3 = (bArr6[1][i17] & UByte.MAX_VALUE) ^ b3(i16);
            }
            byte[][] bArr7 = P;
            i3 = (bArr7[1][b0] & UByte.MAX_VALUE) ^ b0(i15);
            i2 = (bArr7[1][b1] & UByte.MAX_VALUE) ^ b1(i15);
            i = (bArr7[0][b2] & UByte.MAX_VALUE) ^ b2(i15);
            i4 = (bArr7[0][b3] & UByte.MAX_VALUE) ^ b3(i15);
            int[] iArr72 = this.gSBox;
            int i202 = i17 * 2;
            int[] iArr82 = this.gMDS0;
            byte[][] bArr32 = P;
            byte[] bArr42 = bArr32[0];
            iArr72[i202] = iArr82[(bArr42[(bArr42[i3] & UByte.MAX_VALUE) ^ b0(i13)] & UByte.MAX_VALUE) ^ b0(i12)];
            this.gSBox[i202 + 1] = this.gMDS1[(bArr32[0][(bArr32[1][i2] & UByte.MAX_VALUE) ^ b1(i13)] & UByte.MAX_VALUE) ^ b1(i12)];
            this.gSBox[i202 + 512] = this.gMDS2[(bArr32[1][(bArr32[0][i] & UByte.MAX_VALUE) ^ b2(i13)] & UByte.MAX_VALUE) ^ b2(i12)];
            int[] iArr92 = this.gMDS3;
            byte[] bArr52 = bArr32[1];
            this.gSBox[i202 + 513] = iArr92[(bArr52[(bArr52[i4] & UByte.MAX_VALUE) ^ b3(i13)] & UByte.MAX_VALUE) ^ b3(i12)];
            i17++;
            i14 = 2;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = key;
        int length = key.length * 8;
        if (length != 128 && length != 192 && length != 256) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), length, cipherParameters, Utils.getPurpose(z)));
        byte[] bArr = this.workingKey;
        this.k64Cnt = bArr.length / 8;
        setKey(bArr);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    if (this.encrypting) {
                        encryptBlock(bArr, i, bArr2, i2);
                        return 16;
                    }
                    decryptBlock(bArr, i, bArr2, i2);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Twofish not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
