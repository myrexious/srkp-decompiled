package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.lang.reflect.Array;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class Haraka512Digest extends HarakaBase {
    private static byte[][] RC = {new byte[]{6, -124, BuiltinOptions.ReadVariableOptions, BuiltinOptions.SquaredDifferenceOptions, -26, 32, -64, 10, -78, -59, -2, -16, BuiltinOptions.DynamicUpdateSliceOptions, -127, 123, -99}, new byte[]{-117, BuiltinOptions.CumsumOptions, -76, -31, -120, -13, -96, BuiltinOptions.HashtableOptions, BuiltinOptions.SegmentSumOptions, 15, BuiltinOptions.HashtableOptions, -92, BuiltinOptions.SelectOptions, 8, -9, BuiltinOptions.GatherOptions}, new byte[]{52, 2, -34, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.GatherNdOptions, -14, -124, -104, -49, 2, -99, BuiltinOptions.NonMaxSuppressionV5Options, -97, 2, -111, BuiltinOptions.EmbeddingLookupSparseOptions}, new byte[]{14, -42, -22, -26, BuiltinOptions.LessEqualOptions, 123, BuiltinOptions.SplitVOptions, 8, ByteSourceJsonBootstrapper.UTF8_BOM_2, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, BuiltinOptions.HardSwishOptions, BuiltinOptions.SplitVOptions, 121}, new byte[]{-53, -49, -80, -53, BuiltinOptions.FloorModOptions, BuiltinOptions.RandomOptions, BuiltinOptions.FillOptions, -117, 121, -18, -51, BuiltinOptions.SubOptions, -66, BuiltinOptions.ArgMinOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.FillOptions}, new byte[]{126, -22, -51, -18, BuiltinOptions.HashtableSizeOptions, -112, 50, -73, -115, BuiltinOptions.GatherNdOptions, 53, -19, BuiltinOptions.PadV2Options, -118, 5, 123}, new byte[]{BuiltinOptions.CallOnceOptions, -62, -113, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.LessEqualOptions, 124, -48, -30, BuiltinOptions.FloorDivOptions, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.ScatterNdOptions, -38, BuiltinOptions.SplitVOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.ReducerOptions}, new byte[]{BuiltinOptions.LessOptions, BuiltinOptions.LogSoftmaxOptions, -39, -80, -81, -54, -52, 7, BuiltinOptions.CallOnceOptions, BuiltinOptions.NonMaxSuppressionV4Options, -3, -30, BuiltinOptions.SequenceRNNOptions, -57, 11, BuiltinOptions.PackOptions}, new byte[]{-85, BuiltinOptions.MirrorPadOptions, BuiltinOptions.DensifyOptions, -15, -26, -122, ByteCompanionObject.MAX_VALUE, -23, -20, -37, -113, -54, -71, -44, BuiltinOptions.BatchMatMulOptions, -18}, new byte[]{BuiltinOptions.SubOptions, BuiltinOptions.SliceOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, -124, -44, -73, -51, BuiltinOptions.SegmentSumOptions, BuiltinOptions.HardSwishOptions, BuiltinOptions.NegOptions, BuiltinOptions.UnpackOptions, BuiltinOptions.SplitVOptions, -83, 3, 126, 51}, new byte[]{-78, -52, 11, -71, -108, BuiltinOptions.GatherOptions, BuiltinOptions.SplitOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.Rfft2dOptions, 2, -117, BuiltinOptions.LessEqualOptions, -115, -10, -104, 0}, new byte[]{-6, 4, 120, -90, -34, BuiltinOptions.VarHandleOptions, BuiltinOptions.WhereOptions, BuiltinOptions.RandomOptions, BuiltinOptions.ResizeNearestNeighborOptions, -86, -98, -56, BuiltinOptions.IfOptions, -99, BuiltinOptions.GreaterEqualOptions, -118}, new byte[]{-33, -76, -97, BuiltinOptions.PadV2Options, BuiltinOptions.HashtableOptions, 119, BuiltinOptions.NegOptions, BuiltinOptions.SkipGramOptions, 14, -6, BuiltinOptions.SplitVOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.LessOptions, BuiltinOptions.SkipGramOptions, -97, -44}, new byte[]{BuiltinOptions.SqueezeOptions, -95, 3, BuiltinOptions.FillOptions, -12, BuiltinOptions.RangeOptions, -94, 54, 50, -42, BuiltinOptions.ReshapeOptions, -82, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.Conv3DOptions, BuiltinOptions.SkipGramOptions, -18}, new byte[]{-81, 4, BuiltinOptions.RangeOptions, -120, BuiltinOptions.LeakyReluOptions, 5, 0, -124, BuiltinOptions.NonMaxSuppressionV4Options, -106, 0, -55, -100, -88, -20, -90}, new byte[]{BuiltinOptions.ExpOptions, 2, BuiltinOptions.DepthToSpaceOptions, -40, -99, BuiltinOptions.SpaceToBatchNDOptions, -100, BuiltinOptions.SplitVOptions, 120, -94, -57, -29, BuiltinOptions.MaximumMinimumOptions, -27, -109, -20}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.FakeQuantOptions, -86, -8, -89, BuiltinOptions.QuantizeOptions, -55, -73, -71, BuiltinOptions.ArgMaxOptions, BuiltinOptions.LessEqualOptions, -51, -126, -44, 1, BuiltinOptions.BucketizeOptions}, new byte[]{BuiltinOptions.SelectV2Options, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.ReadVariableOptions, 13, BuiltinOptions.ScatterNdOptions, -122, -80, BuiltinOptions.GatherOptions, 55, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 16, BuiltinOptions.SliceOptions, 125, BuiltinOptions.HashtableOptions}, new byte[]{BuiltinOptions.MatrixSetDiagOptions, -54, BuiltinOptions.BidirectionalSequenceLSTMOptions, -62, BuiltinOptions.ExpOptions, BuiltinOptions.SliceOptions, 4, BuiltinOptions.ZerosLikeOptions, -127, -62, -111, BuiltinOptions.GatherNdOptions, -10, -4, -102, -58}, new byte[]{-110, BuiltinOptions.SplitOptions, -105, BuiltinOptions.LogicalOrOptions, BuiltinOptions.TopKV2Options, BuiltinOptions.HashtableOptions, BuiltinOptions.BroadcastToOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.GreaterOptions, -81, -110, -24, 54, -47, -108, BuiltinOptions.FakeQuantOptions}, new byte[]{-45, ByteSourceJsonBootstrapper.UTF8_BOM_3, -110, BuiltinOptions.PowOptions, BuiltinOptions.TopKV2Options, BuiltinOptions.MatrixDiagOptions, -122, -21, BuiltinOptions.HashtableFindOptions, -70, -71, BuiltinOptions.MatrixDiagOptions, -27, 16, BuiltinOptions.AssignVariableOptions, -76}, new byte[]{-37, -122, BuiltinOptions.LogicalOrOptions, -27, -82, -16, -58, 119, -109, 61, -3, -35, BuiltinOptions.LogSoftmaxOptions, -31, BuiltinOptions.SkipGramOptions, -115}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.SelectV2Options, BuiltinOptions.BroadcastToOptions, -1, -21, -96, -100, -125, -28, -115, -29, -53, BuiltinOptions.TopKV2Options, BuiltinOptions.SkipGramOptions, -79}, new byte[]{BuiltinOptions.BucketizeOptions, BuiltinOptions.LeakyReluOptions, -45, -36, -30, -28, -47, -100, BuiltinOptions.GreaterEqualOptions, -71, BuiltinOptions.TransposeOptions, BuiltinOptions.AbsOptions, -57, BuiltinOptions.PadV2Options, -9, 125}, new byte[]{BuiltinOptions.ZerosLikeOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -61, BuiltinOptions.ScatterNdOptions, BuiltinOptions.SliceOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.LeakyReluOptions, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.MulOptions, -60, BuiltinOptions.GreaterOptions, -77, -110, BuiltinOptions.AbsOptions}, new byte[]{-37, -89, BuiltinOptions.DynamicUpdateSliceOptions, -88, -25, 7, ByteSourceJsonBootstrapper.UTF8_BOM_1, -10, 3, -78, 49, -35, BuiltinOptions.PadOptions, -21, BuiltinOptions.BroadcastToOptions, -103}, new byte[]{BuiltinOptions.HashtableImportOptions, -13, BuiltinOptions.ScatterNdOptions, BuiltinOptions.LeakyReluOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.QuantizeOptions, 119, -114, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.SplitOptions, 2, 126, -54, BuiltinOptions.UnidirectionalSequenceLSTMOptions, BuiltinOptions.GreaterOptions}, new byte[]{-51, -89, BuiltinOptions.MatrixSetDiagOptions, BuiltinOptions.GatherOptions, -42, -34, 125, 119, BuiltinOptions.HashtableImportOptions, BuiltinOptions.ReducerOptions, -27, -71, -72, -122, BuiltinOptions.GatherOptions, -7}, new byte[]{-20, BuiltinOptions.HashtableOptions, BuiltinOptions.ZerosLikeOptions, -16, BuiltinOptions.HashtableOptions, -88, -23, -86, -99, BuiltinOptions.HashtableFindOptions, 6, -99, -87, BuiltinOptions.BidirectionalSequenceRNNOptions, -18, BuiltinOptions.WhileOptions}, new byte[]{-53, BuiltinOptions.SqueezeOptions, BuiltinOptions.Rfft2dOptions, 80, -7, BuiltinOptions.ReverseSequenceOptions, 51, BuiltinOptions.PadV2Options, -94, BuiltinOptions.GatherNdOptions, BuiltinOptions.ReshapeOptions, BuiltinOptions.QuantizeOptions, BuiltinOptions.PackOptions, -13, BuiltinOptions.MaximumMinimumOptions, -63}, new byte[]{BuiltinOptions.GreaterOptions, -18, 12, BuiltinOptions.DynamicUpdateSliceOptions, 0, -38, BuiltinOptions.ScatterNdOptions, -100, -28, -19, 3, BuiltinOptions.GatherNdOptions, BuiltinOptions.NonMaxSuppressionV5Options, 14, -48, -39}, new byte[]{-16, -79, -91, -95, -106, -23, 12, -85, ByteCompanionObject.MIN_VALUE, ByteSourceJsonBootstrapper.UTF8_BOM_2, -70, PSSSigner.TRAILER_IMPLICIT, BuiltinOptions.DensifyOptions, -92, -93, 80}, new byte[]{-82, 61, -79, 2, BuiltinOptions.DepthToSpaceOptions, -106, BuiltinOptions.LessOptions, -120, -85, 13, -34, BuiltinOptions.SliceOptions, -109, -115, -54, BuiltinOptions.ArgMinOptions}, new byte[]{BuiltinOptions.GatherOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, -113, BuiltinOptions.PowOptions, -43, BuiltinOptions.CosOptions, -92, 11, -120, BuiltinOptions.EmbeddingLookupSparseOptions, -13, -88, BuiltinOptions.LessEqualOptions, BuiltinOptions.DynamicUpdateSliceOptions, -76, BuiltinOptions.SquareOptions}, new byte[]{52, ByteSourceJsonBootstrapper.UTF8_BOM_2, -118, BuiltinOptions.HardSwishOptions, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.SquareOptions, ByteCompanionObject.MAX_VALUE, -41, -82, -74, -73, 121, 54, 10, BuiltinOptions.PadOptions, -10}, new byte[]{BuiltinOptions.DequantizeOptions, -10, BuiltinOptions.AddNOptions, BuiltinOptions.FloorDivOptions, -53, -27, BuiltinOptions.CosOptions, BuiltinOptions.PowOptions, BuiltinOptions.ZerosLikeOptions, -50, BuiltinOptions.QuantizeOptions, BuiltinOptions.BatchToSpaceNDOptions, -1, -70, -81, -34}, new byte[]{BuiltinOptions.SquaredDifferenceOptions, -23, -102, BuiltinOptions.CosOptions, -71, -13, 2, BuiltinOptions.Conv3DOptions, -94, -54, -100, -9, -125, -98, -55, 120}, new byte[]{-82, BuiltinOptions.ReverseV2Options, -91, BuiltinOptions.TransposeOptions, BuiltinOptions.ReducerOptions, -33, -9, -66, BuiltinOptions.UnpackOptions, -64, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.TopKV2Options, -112, BuiltinOptions.SkipGramOptions, 53}, new byte[]{-96, -63, BuiltinOptions.ScatterNdOptions, BuiltinOptions.LogicalOrOptions, -70, 126, -46, BuiltinOptions.PadV2Options, -63, BuiltinOptions.BucketizeOptions, PSSSigner.TRAILER_IMPLICIT, 15, BuiltinOptions.FloorModOptions, -90, BuiltinOptions.QuantizeOptions, -49}, new byte[]{BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.Conv3DOptions, -52, 3, 2, BuiltinOptions.ArgMaxOptions, -126, -120, BuiltinOptions.ResizeNearestNeighborOptions, -42, -67, -3, -23, -59, -99, -95}};
    private final byte[] buffer;
    private int off;
    private final CryptoServicePurpose purpose;

    public Haraka512Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public Haraka512Digest(CryptoServicePurpose cryptoServicePurpose) {
        this.purpose = cryptoServicePurpose;
        this.buffer = new byte[64];
    }

    public Haraka512Digest(Haraka512Digest haraka512Digest) {
        this.purpose = haraka512Digest.purpose;
        this.buffer = Arrays.clone(haraka512Digest.buffer);
        this.off = haraka512Digest.off;
    }

    private int haraka512256(byte[] bArr, byte[] bArr2, int i) {
        byte[][] bArr3 = (byte[][]) Array.newInstance(Byte.TYPE, 4, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance(Byte.TYPE, 4, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        System.arraycopy(bArr, 32, bArr3[2], 0, 16);
        System.arraycopy(bArr, 48, bArr3[3], 0, 16);
        bArr3[0] = aesEnc(bArr3[0], RC[0]);
        bArr3[1] = aesEnc(bArr3[1], RC[1]);
        bArr3[2] = aesEnc(bArr3[2], RC[2]);
        bArr3[3] = aesEnc(bArr3[3], RC[3]);
        bArr3[0] = aesEnc(bArr3[0], RC[4]);
        bArr3[1] = aesEnc(bArr3[1], RC[5]);
        bArr3[2] = aesEnc(bArr3[2], RC[6]);
        bArr3[3] = aesEnc(bArr3[3], RC[7]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[8]);
        bArr3[1] = aesEnc(bArr4[1], RC[9]);
        bArr3[2] = aesEnc(bArr4[2], RC[10]);
        bArr3[3] = aesEnc(bArr4[3], RC[11]);
        bArr3[0] = aesEnc(bArr3[0], RC[12]);
        bArr3[1] = aesEnc(bArr3[1], RC[13]);
        bArr3[2] = aesEnc(bArr3[2], RC[14]);
        bArr3[3] = aesEnc(bArr3[3], RC[15]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[16]);
        bArr3[1] = aesEnc(bArr4[1], RC[17]);
        bArr3[2] = aesEnc(bArr4[2], RC[18]);
        bArr3[3] = aesEnc(bArr4[3], RC[19]);
        bArr3[0] = aesEnc(bArr3[0], RC[20]);
        bArr3[1] = aesEnc(bArr3[1], RC[21]);
        bArr3[2] = aesEnc(bArr3[2], RC[22]);
        bArr3[3] = aesEnc(bArr3[3], RC[23]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[24]);
        bArr3[1] = aesEnc(bArr4[1], RC[25]);
        bArr3[2] = aesEnc(bArr4[2], RC[26]);
        bArr3[3] = aesEnc(bArr4[3], RC[27]);
        bArr3[0] = aesEnc(bArr3[0], RC[28]);
        bArr3[1] = aesEnc(bArr3[1], RC[29]);
        bArr3[2] = aesEnc(bArr3[2], RC[30]);
        bArr3[3] = aesEnc(bArr3[3], RC[31]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[32]);
        bArr3[1] = aesEnc(bArr4[1], RC[33]);
        bArr3[2] = aesEnc(bArr4[2], RC[34]);
        bArr3[3] = aesEnc(bArr4[3], RC[35]);
        bArr3[0] = aesEnc(bArr3[0], RC[36]);
        bArr3[1] = aesEnc(bArr3[1], RC[37]);
        bArr3[2] = aesEnc(bArr3[2], RC[38]);
        bArr3[3] = aesEnc(bArr3[3], RC[39]);
        mix512(bArr3, bArr4);
        bArr3[0] = xor(bArr4[0], bArr, 0);
        bArr3[1] = xor(bArr4[1], bArr, 16);
        bArr3[2] = xor(bArr4[2], bArr, 32);
        bArr3[3] = xor(bArr4[3], bArr, 48);
        System.arraycopy(bArr3[0], 8, bArr2, i, 8);
        System.arraycopy(bArr3[1], 8, bArr2, i + 8, 8);
        System.arraycopy(bArr3[2], 0, bArr2, i + 16, 8);
        System.arraycopy(bArr3[3], 0, bArr2, i + 24, 8);
        return 32;
    }

    private void mix512(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 12, bArr2[0], 0, 4);
        System.arraycopy(bArr[2], 12, bArr2[0], 4, 4);
        System.arraycopy(bArr[1], 12, bArr2[0], 8, 4);
        System.arraycopy(bArr[3], 12, bArr2[0], 12, 4);
        System.arraycopy(bArr[2], 0, bArr2[1], 0, 4);
        System.arraycopy(bArr[0], 0, bArr2[1], 4, 4);
        System.arraycopy(bArr[3], 0, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 0, bArr2[1], 12, 4);
        System.arraycopy(bArr[2], 4, bArr2[2], 0, 4);
        System.arraycopy(bArr[0], 4, bArr2[2], 4, 4);
        System.arraycopy(bArr[3], 4, bArr2[2], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[2], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[3], 0, 4);
        System.arraycopy(bArr[2], 8, bArr2[3], 4, 4);
        System.arraycopy(bArr[1], 8, bArr2[3], 8, 4);
        System.arraycopy(bArr[3], 8, bArr2[3], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (this.off == 64) {
            if (bArr.length - i >= 32) {
                int haraka512256 = haraka512256(this.buffer, bArr, i);
                reset();
                return haraka512256;
            }
            throw new IllegalArgumentException("output too short to receive digest");
        }
        throw new IllegalStateException("input must be exactly 64 bytes");
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-512";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.off;
        if (i + 1 > 64) {
            throw new IllegalArgumentException("total input cannot be more than 64 bytes");
        }
        byte[] bArr = this.buffer;
        this.off = i + 1;
        bArr[i] = b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 + i2 > 64) {
            throw new IllegalArgumentException("total input cannot be more than 64 bytes");
        }
        System.arraycopy(bArr, i, this.buffer, i3, i2);
        this.off += i2;
    }
}
