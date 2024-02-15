package org.bouncycastle.crypto.prng;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class VMPCRandomGenerator implements RandomGenerator {
    private byte n = 0;
    private byte[] P = {ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.GreaterOptions, BuiltinOptions.SelectV2Options, ByteCompanionObject.MAX_VALUE, -75, -86, -44, 13, -127, -2, -78, -126, -53, -96, -95, 8, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.RankOptions, -24, BuiltinOptions.RangeOptions, 2, 16, -60, -34, 53, -91, -20, ByteCompanionObject.MIN_VALUE, BuiltinOptions.SkipGramOptions, -72, BuiltinOptions.Rfft2dOptions, -38, BuiltinOptions.SelectOptions, BuiltinOptions.DynamicUpdateSliceOptions, -52, -94, 9, 54, 3, BuiltinOptions.ScatterNdOptions, BuiltinOptions.GreaterEqualOptions, -3, -32, -35, 5, BuiltinOptions.ZerosLikeOptions, -112, -83, -56, -31, -81, BuiltinOptions.ReverseSequenceOptions, -101, BuiltinOptions.SquaredDifferenceOptions, -40, BuiltinOptions.ReverseV2Options, -82, 80, -123, BuiltinOptions.LogicalOrOptions, 10, -28, -13, -100, BuiltinOptions.DequantizeOptions, BuiltinOptions.SplitOptions, BuiltinOptions.GatherNdOptions, -55, -125, -105, BuiltinOptions.BidirectionalSequenceRNNOptions, -79, -103, BuiltinOptions.SegmentSumOptions, 49, 119, -43, BuiltinOptions.DivOptions, -42, 120, -67, BuiltinOptions.DepthToSpaceOptions, -80, -118, BuiltinOptions.TopKV2Options, BuiltinOptions.PowOptions, -8, BuiltinOptions.BroadcastToOptions, BuiltinOptions.PadV2Options, BuiltinOptions.NegOptions, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, BuiltinOptions.VarHandleOptions, -33, 4, -27, -107, BuiltinOptions.LogicalAndOptions, BuiltinOptions.CastOptions, -122, -90, 11, -113, -15, BuiltinOptions.LogSoftmaxOptions, 14, -41, BuiltinOptions.UnpackOptions, -77, -49, 126, 6, BuiltinOptions.MulOptions, -102, BuiltinOptions.MirrorPadOptions, BuiltinOptions.SubOptions, -93, -37, 50, -110, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.ReshapeOptions, BuiltinOptions.MaximumMinimumOptions, -12, BuiltinOptions.QuantizeOptions, -48, BuiltinOptions.AbsOptions, BuiltinOptions.Conv3DOptions, BuiltinOptions.GatherOptions, BuiltinOptions.HardSwishOptions, -84, -1, 7, -64, BuiltinOptions.BatchMatMulOptions, 121, -4, -57, -51, 118, BuiltinOptions.SquareOptions, BuiltinOptions.WhileOptions, -25, BuiltinOptions.FakeQuantOptions, 52, 122, BuiltinOptions.SliceOptions, BuiltinOptions.ArgMaxOptions, 15, BuiltinOptions.BucketizeOptions, 1, -7, -47, -46, BuiltinOptions.SpaceToBatchNDOptions, -23, -111, -71, BuiltinOptions.MatrixSetDiagOptions, -19, BuiltinOptions.FloorDivOptions, BuiltinOptions.HashtableImportOptions, -76, -61, -98, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.DensifyOptions, -6, BuiltinOptions.SequenceRNNOptions, 51, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -119, -16, -106, BuiltinOptions.TransposeOptions, BuiltinOptions.NonMaxSuppressionV4Options, -109, 61, 55, BuiltinOptions.LeakyReluOptions, -39, -88, -63, BuiltinOptions.ReducerOptions, -10, BuiltinOptions.ArgMinOptions, -117, -73, 12, 32, -50, -120, BuiltinOptions.HashtableSizeOptions, -74, BuiltinOptions.GeluOptions, -114, -115, BuiltinOptions.PadOptions, BuiltinOptions.LessOptions, -14, -121, -11, -21, BuiltinOptions.ReadVariableOptions, -29, -5, BuiltinOptions.WhereOptions, -97, -58, BuiltinOptions.FillOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, 125, -30, BuiltinOptions.HashtableOptions, BuiltinOptions.IfOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.CumsumOptions, -87, -116, -18, -124, BuiltinOptions.SpaceToDepthOptions, -89, BuiltinOptions.SqueezeOptions, -99, -36, BuiltinOptions.CallOnceOptions, BuiltinOptions.FloorModOptions, -70, BuiltinOptions.LessEqualOptions, -26, -92, -85, 124, -108, 0, BuiltinOptions.ExpOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, -22, -66, -54, BuiltinOptions.RandomOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.AddNOptions, -104, BuiltinOptions.LogicalNotOptions, -62, BuiltinOptions.EmbeddingLookupSparseOptions, 123, BuiltinOptions.PackOptions, BuiltinOptions.CosOptions};
    private byte s = -66;

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.P;
            byte b2 = this.s;
            byte b3 = this.n;
            byte b4 = bArr2[(b2 + bArr2[b3 & UByte.MAX_VALUE] + b) & 255];
            this.s = b4;
            byte b5 = bArr2[b3 & UByte.MAX_VALUE];
            bArr2[b3 & UByte.MAX_VALUE] = bArr2[b4 & UByte.MAX_VALUE];
            bArr2[b4 & UByte.MAX_VALUE] = b5;
            this.n = (byte) ((b3 + 1) & 255);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.P) {
            int i3 = i2 + i;
            while (i != i3) {
                byte[] bArr2 = this.P;
                byte b = this.s;
                byte b2 = this.n;
                byte b3 = bArr2[(b + bArr2[b2 & UByte.MAX_VALUE]) & 255];
                this.s = b3;
                bArr[i] = bArr2[(bArr2[bArr2[b3 & UByte.MAX_VALUE] & UByte.MAX_VALUE] + 1) & 255];
                byte b4 = bArr2[b2 & UByte.MAX_VALUE];
                bArr2[b2 & UByte.MAX_VALUE] = bArr2[b3 & UByte.MAX_VALUE];
                bArr2[b3 & UByte.MAX_VALUE] = b4;
                this.n = (byte) ((b2 + 1) & 255);
                i++;
            }
        }
    }
}
