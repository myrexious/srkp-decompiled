package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public abstract class HarakaBase implements Digest {
    protected static final int DIGEST_SIZE = 32;
    private static final byte[][] S = {new byte[]{BuiltinOptions.DensifyOptions, 124, 119, 123, -14, BuiltinOptions.HashtableOptions, BuiltinOptions.VarHandleOptions, -59, BuiltinOptions.SliceOptions, 1, BuiltinOptions.CallOnceOptions, BuiltinOptions.PadV2Options, -2, -41, -85, 118}, new byte[]{-54, -126, -55, 125, -6, BuiltinOptions.QuantizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -16, -83, -44, -94, -81, -100, -92, BuiltinOptions.RandomOptions, -64}, new byte[]{-73, -3, -109, BuiltinOptions.DequantizeOptions, 54, BuiltinOptions.LogicalNotOptions, -9, -52, 52, -91, -27, -15, BuiltinOptions.AssignVariableOptions, -40, 49, BuiltinOptions.MulOptions}, new byte[]{4, -57, BuiltinOptions.SplitOptions, -61, BuiltinOptions.BatchToSpaceNDOptions, -106, 5, -102, 7, BuiltinOptions.SkipGramOptions, ByteCompanionObject.MIN_VALUE, -30, -21, BuiltinOptions.MaximumMinimumOptions, -78, BuiltinOptions.DynamicUpdateSliceOptions}, new byte[]{9, -125, BuiltinOptions.GreaterOptions, BuiltinOptions.TransposeOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.MatrixSetDiagOptions, -96, BuiltinOptions.AddNOptions, BuiltinOptions.PackOptions, -42, -77, BuiltinOptions.LessOptions, -29, BuiltinOptions.SelectOptions, -124}, new byte[]{BuiltinOptions.GatherNdOptions, -47, 0, -19, 32, -4, -79, BuiltinOptions.HardSwishOptions, BuiltinOptions.Conv3DOptions, -53, -66, BuiltinOptions.ArgMinOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.MatrixDiagOptions, -49}, new byte[]{-48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.MirrorPadOptions, 51, -123, BuiltinOptions.BidirectionalSequenceLSTMOptions, -7, 2, ByteCompanionObject.MAX_VALUE, 80, BuiltinOptions.LogicalOrOptions, -97, -88}, new byte[]{BuiltinOptions.ReverseV2Options, -93, BuiltinOptions.UnpackOptions, -113, -110, -99, BuiltinOptions.PowOptions, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BuiltinOptions.ExpOptions, 16, -1, -13, -46}, new byte[]{-51, 12, BuiltinOptions.SpaceToDepthOptions, -20, BuiltinOptions.NonMaxSuppressionV4Options, -105, BuiltinOptions.FillOptions, BuiltinOptions.GatherOptions, -60, -89, 126, 61, BuiltinOptions.SegmentSumOptions, BuiltinOptions.WhileOptions, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.BucketizeOptions}, new byte[]{BuiltinOptions.NonMaxSuppressionV5Options, -127, BuiltinOptions.SplitVOptions, -36, BuiltinOptions.TopKV2Options, BuiltinOptions.NegOptions, -112, -120, BuiltinOptions.BidirectionalSequenceRNNOptions, -18, -72, BuiltinOptions.EmbeddingLookupSparseOptions, -34, BuiltinOptions.DepthToSpaceOptions, 11, -37}, new byte[]{-32, 50, BuiltinOptions.FakeQuantOptions, 10, BuiltinOptions.RangeOptions, 6, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.IfOptions, -62, -45, -84, BuiltinOptions.SelectV2Options, -111, -107, -28, 121}, new byte[]{-25, -56, 55, BuiltinOptions.HashtableImportOptions, -115, -43, BuiltinOptions.AbsOptions, -87, BuiltinOptions.HashtableFindOptions, BuiltinOptions.RankOptions, -12, -22, BuiltinOptions.BatchMatMulOptions, 122, -82, 8}, new byte[]{-70, 120, BuiltinOptions.CastOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.SubOptions, -90, -76, -58, -24, -35, BuiltinOptions.GeluOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.LeakyReluOptions, -67, -117, -118}, new byte[]{BuiltinOptions.ReadVariableOptions, BuiltinOptions.LogicalAndOptions, -75, BuiltinOptions.CumsumOptions, BuiltinOptions.FloorModOptions, 3, -10, 14, BuiltinOptions.ScatterNdOptions, 53, BuiltinOptions.ReverseSequenceOptions, -71, -122, -63, BuiltinOptions.DivOptions, -98}, new byte[]{-31, -8, -104, BuiltinOptions.ReshapeOptions, BuiltinOptions.Rfft2dOptions, -39, -114, -108, -101, BuiltinOptions.SqueezeOptions, -121, -23, -50, BuiltinOptions.WhereOptions, BuiltinOptions.ArgMaxOptions, -33}, new byte[]{-116, -95, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, BuiltinOptions.SquareOptions, BuiltinOptions.BroadcastToOptions, BuiltinOptions.FloorDivOptions, -103, BuiltinOptions.GreaterEqualOptions, 15, -80, BuiltinOptions.CosOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.PadOptions}};

    public static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] mixColumns = mixColumns(shiftRows(subBytes(bArr)));
        xorReverse(mixColumns, bArr2);
        return mixColumns;
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i + 1;
            int i4 = i2 * 4;
            int i5 = i4 + 1;
            int i6 = i4 + 2;
            int i7 = i4 + 3;
            bArr2[i] = (byte) ((((xTime(bArr[i4]) ^ xTime(bArr[i5])) ^ bArr[i5]) ^ bArr[i6]) ^ bArr[i7]);
            int i8 = i3 + 1;
            bArr2[i3] = (byte) ((((bArr[i4] ^ xTime(bArr[i5])) ^ xTime(bArr[i6])) ^ bArr[i6]) ^ bArr[i7]);
            int i9 = i8 + 1;
            bArr2[i8] = (byte) ((((bArr[i4] ^ bArr[i5]) ^ xTime(bArr[i6])) ^ xTime(bArr[i7])) ^ bArr[i7]);
            i = i9 + 1;
            bArr2[i9] = (byte) ((((bArr[i4] ^ xTime(bArr[i4])) ^ bArr[i5]) ^ bArr[i6]) ^ xTime(bArr[i7]));
        }
        return bArr2;
    }

    static byte sBox(byte b) {
        return S[(b & UByte.MAX_VALUE) >>> 4][b & 15];
    }

    static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    static byte xTime(byte b) {
        int i = b >>> 7;
        int i2 = b << 1;
        if (i > 0) {
            i2 ^= 27;
        }
        return (byte) (i2 & 255);
    }

    public static byte[] xor(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[16];
        int i2 = 0;
        while (i2 < 16) {
            bArr3[i2] = (byte) (bArr2[i] ^ bArr[i2]);
            i2++;
            i++;
        }
        return bArr3;
    }

    static void xorReverse(byte[] bArr, byte[] bArr2) {
        bArr[0] = (byte) (bArr[0] ^ bArr2[15]);
        bArr[1] = (byte) (bArr[1] ^ bArr2[14]);
        bArr[2] = (byte) (bArr[2] ^ bArr2[13]);
        bArr[3] = (byte) (bArr[3] ^ bArr2[12]);
        bArr[4] = (byte) (bArr[4] ^ bArr2[11]);
        bArr[5] = (byte) (bArr[5] ^ bArr2[10]);
        bArr[6] = (byte) (bArr[6] ^ bArr2[9]);
        bArr[7] = (byte) (bArr[7] ^ bArr2[8]);
        bArr[8] = (byte) (bArr2[7] ^ bArr[8]);
        bArr[9] = (byte) (bArr2[6] ^ bArr[9]);
        bArr[10] = (byte) (bArr2[5] ^ bArr[10]);
        bArr[11] = (byte) (bArr2[4] ^ bArr[11]);
        bArr[12] = (byte) (bArr2[3] ^ bArr[12]);
        bArr[13] = (byte) (bArr2[2] ^ bArr[13]);
        bArr[14] = (byte) (bArr2[1] ^ bArr[14]);
        bArr[15] = (byte) (bArr2[0] ^ bArr[15]);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }
}
