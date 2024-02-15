package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class DSTU7564Digest implements ExtendedDigest, Memoable {
    private static final int NB_1024 = 16;
    private static final int NB_512 = 8;
    private static final int NR_1024 = 14;
    private static final int NR_512 = 10;
    private static final byte[] S0 = {-88, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.NonMaxSuppressionV4Options, 6, BuiltinOptions.HashtableOptions, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.QuantizeOptions, BuiltinOptions.AssignVariableOptions, -33, -121, -107, BuiltinOptions.GatherOptions, -16, -40, 9, BuiltinOptions.HashtableImportOptions, -13, BuiltinOptions.DivOptions, -53, -55, BuiltinOptions.MirrorPadOptions, BuiltinOptions.GreaterOptions, -81, 121, -32, -105, -3, BuiltinOptions.VarHandleOptions, BuiltinOptions.LeakyReluOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.ArgMinOptions, BuiltinOptions.LogicalAndOptions, -35, -93, BuiltinOptions.SplitVOptions, -76, -74, -102, 14, BuiltinOptions.SequenceRNNOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.MulOptions, -31, BuiltinOptions.RangeOptions, -46, -109, -58, -110, BuiltinOptions.RandomOptions, -98, BuiltinOptions.ScatterNdOptions, -47, BuiltinOptions.DensifyOptions, -6, -18, -12, BuiltinOptions.SpaceToBatchNDOptions, -43, -83, BuiltinOptions.MatrixDiagOptions, -92, ByteSourceJsonBootstrapper.UTF8_BOM_2, -95, -36, -14, -125, 55, BuiltinOptions.SquareOptions, -28, 122, 50, -100, -52, -85, BuiltinOptions.ResizeNearestNeighborOptions, -113, BuiltinOptions.HashtableSizeOptions, 4, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.LessEqualOptions, -25, -30, BuiltinOptions.MatrixSetDiagOptions, -106, BuiltinOptions.PadOptions, BuiltinOptions.SplitOptions, BuiltinOptions.PadV2Options, -62, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.CumsumOptions, 15, PSSSigner.TRAILER_IMPLICIT, -87, BuiltinOptions.UnidirectionalSequenceLSTMOptions, BuiltinOptions.FloorDivOptions, 52, BuiltinOptions.FloorModOptions, -4, -73, BuiltinOptions.Conv3DOptions, -120, -91, BuiltinOptions.GatherNdOptions, -122, -7, BuiltinOptions.HardSwishOptions, -37, BuiltinOptions.PowOptions, 123, -61, BuiltinOptions.SqueezeOptions, BuiltinOptions.TopKV2Options, 51, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.ArgMaxOptions, 54, -57, -78, BuiltinOptions.PackOptions, -114, 119, -70, -11, BuiltinOptions.EmbeddingLookupSparseOptions, -97, 8, BuiltinOptions.WhereOptions, -101, BuiltinOptions.SquaredDifferenceOptions, -2, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.IfOptions, -38, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, -51, 125, BuiltinOptions.ExpOptions, -80, BuiltinOptions.LogicalNotOptions, BuiltinOptions.ReducerOptions, -119, -1, -21, -124, BuiltinOptions.Rfft2dOptions, BuiltinOptions.FakeQuantOptions, -99, -41, -45, BuiltinOptions.ReadVariableOptions, BuiltinOptions.CallOnceOptions, BuiltinOptions.UnpackOptions, -75, -34, BuiltinOptions.WhileOptions, BuiltinOptions.SliceOptions, -111, -79, 120, BuiltinOptions.ReshapeOptions, 1, -27, 0, BuiltinOptions.BroadcastToOptions, -104, -96, -59, 2, -90, BuiltinOptions.GeluOptions, BuiltinOptions.GreaterEqualOptions, 11, -94, 118, -77, -66, -50, -67, -82, -23, -118, 49, BuiltinOptions.SubOptions, -20, -15, -103, -108, -86, -10, BuiltinOptions.DequantizeOptions, BuiltinOptions.SelectOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, -24, -116, 53, 3, -44, ByteCompanionObject.MAX_VALUE, -5, 5, -63, BuiltinOptions.DepthToSpaceOptions, -112, 32, 61, -126, -9, -22, 10, 13, 126, -8, 80, BuiltinOptions.TransposeOptions, -60, 7, BuiltinOptions.ReverseSequenceOptions, -72, BuiltinOptions.LogicalOrOptions, BuiltinOptions.SelectV2Options, -29, -56, -84, BuiltinOptions.AddNOptions, BuiltinOptions.SegmentSumOptions, 16, -48, -39, BuiltinOptions.SpaceToDepthOptions, 12, BuiltinOptions.SkipGramOptions, BuiltinOptions.LessOptions, BuiltinOptions.ReverseV2Options, -71, -49, -42, BuiltinOptions.BucketizeOptions, -115, -127, BuiltinOptions.CosOptions, -64, -19, BuiltinOptions.AbsOptions, BuiltinOptions.FillOptions, -89, BuiltinOptions.NegOptions, -123, BuiltinOptions.CastOptions, -26, -54, 124, -117, BuiltinOptions.RankOptions, ByteCompanionObject.MIN_VALUE};
    private static final byte[] S1 = {-50, ByteSourceJsonBootstrapper.UTF8_BOM_2, -21, -110, -22, -53, BuiltinOptions.SpaceToDepthOptions, -63, -23, BuiltinOptions.FakeQuantOptions, -42, -78, -46, -112, BuiltinOptions.GatherOptions, -8, BuiltinOptions.SquareOptions, BuiltinOptions.MulOptions, BuiltinOptions.RankOptions, -76, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.SubOptions, -120, BuiltinOptions.ZerosLikeOptions, -59, BuiltinOptions.IfOptions, 54, -70, -11, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.CallOnceOptions, -115, 49, -10, BuiltinOptions.SegmentSumOptions, BuiltinOptions.MatrixDiagOptions, -98, -12, BuiltinOptions.TopKV2Options, -86, BuiltinOptions.DynamicUpdateSliceOptions, 15, 2, -79, -33, BuiltinOptions.HashtableImportOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.MirrorPadOptions, 124, BuiltinOptions.DequantizeOptions, BuiltinOptions.LessEqualOptions, -9, 8, BuiltinOptions.WhileOptions, BuiltinOptions.FillOptions, BuiltinOptions.LogicalAndOptions, -97, BuiltinOptions.EmbeddingLookupSparseOptions, -56, -82, BuiltinOptions.CosOptions, 16, -40, PSSSigner.TRAILER_IMPLICIT, BuiltinOptions.TransposeOptions, BuiltinOptions.HashtableOptions, BuiltinOptions.Rfft2dOptions, -13, -67, 51, -85, -6, -47, -101, BuiltinOptions.BroadcastToOptions, BuiltinOptions.AbsOptions, BuiltinOptions.PadOptions, -107, -111, -18, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.DensifyOptions, -114, BuiltinOptions.HardSwishOptions, -52, BuiltinOptions.LogicalOrOptions, BuiltinOptions.SpaceToBatchNDOptions, -95, -127, BuiltinOptions.RangeOptions, 123, -39, BuiltinOptions.VarHandleOptions, 55, BuiltinOptions.NonMaxSuppressionV5Options, -54, -25, BuiltinOptions.PadV2Options, BuiltinOptions.FloorModOptions, -3, -106, BuiltinOptions.BidirectionalSequenceLSTMOptions, -4, BuiltinOptions.FloorDivOptions, BuiltinOptions.SkipGramOptions, 13, 121, -27, -119, -116, -29, 32, BuiltinOptions.SliceOptions, -36, -73, BuiltinOptions.HashtableFindOptions, BuiltinOptions.ResizeNearestNeighborOptions, -75, BuiltinOptions.LogicalNotOptions, -105, -44, BuiltinOptions.SelectV2Options, BuiltinOptions.GreaterEqualOptions, 6, -92, -91, -125, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.NegOptions, -38, -55, 0, 126, -94, BuiltinOptions.WhereOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.ReshapeOptions, -43, -100, -49, 14, 10, 61, BuiltinOptions.ReverseV2Options, 125, -109, BuiltinOptions.ReducerOptions, -2, -60, BuiltinOptions.UnidirectionalSequenceLSTMOptions, 9, -122, 11, -113, -99, BuiltinOptions.Conv3DOptions, 7, -71, -80, -104, BuiltinOptions.BatchToSpaceNDOptions, 50, BuiltinOptions.AssignVariableOptions, BuiltinOptions.LeakyReluOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.PackOptions, BuiltinOptions.ReadVariableOptions, -96, -28, BuiltinOptions.UnpackOptions, -1, -61, -87, -26, 120, -7, -117, BuiltinOptions.BidirectionalSequenceRNNOptions, ByteCompanionObject.MIN_VALUE, BuiltinOptions.SqueezeOptions, BuiltinOptions.PowOptions, -31, -72, -88, -32, 12, BuiltinOptions.SplitOptions, 118, BuiltinOptions.DivOptions, BuiltinOptions.CastOptions, BuiltinOptions.LogSoftmaxOptions, 5, -15, BuiltinOptions.HashtableSizeOptions, -108, BuiltinOptions.ArgMaxOptions, -102, -124, -24, -93, BuiltinOptions.SplitVOptions, 119, -45, -123, -30, BuiltinOptions.AddNOptions, -14, -126, 80, 122, BuiltinOptions.SelectOptions, BuiltinOptions.GeluOptions, BuiltinOptions.GatherNdOptions, -77, BuiltinOptions.ScatterNdOptions, -81, BuiltinOptions.ArgMinOptions, 53, -34, -51, BuiltinOptions.SequenceRNNOptions, -103, -84, -83, BuiltinOptions.RandomOptions, BuiltinOptions.GreaterOptions, -35, -48, -121, -66, BuiltinOptions.DepthToSpaceOptions, -90, -20, 4, -58, 3, 52, -5, -37, BuiltinOptions.QuantizeOptions, -74, -62, 1, -16, BuiltinOptions.MatrixSetDiagOptions, -19, -89, BuiltinOptions.CumsumOptions, BuiltinOptions.ExpOptions, ByteCompanionObject.MAX_VALUE, -118, BuiltinOptions.MaximumMinimumOptions, -57, -64, BuiltinOptions.LessOptions, -41};
    private static final byte[] S2 = {-109, -39, -102, -75, -104, BuiltinOptions.TopKV2Options, BuiltinOptions.BidirectionalSequenceLSTMOptions, -4, -70, BuiltinOptions.Conv3DOptions, -33, 2, -97, -36, BuiltinOptions.ReverseV2Options, BuiltinOptions.QuantizeOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.GatherOptions, BuiltinOptions.PadV2Options, -62, -108, -12, ByteSourceJsonBootstrapper.UTF8_BOM_2, -93, BuiltinOptions.SelectV2Options, -28, BuiltinOptions.AssignVariableOptions, -44, -51, BuiltinOptions.ReadVariableOptions, BuiltinOptions.PadOptions, -31, BuiltinOptions.RangeOptions, BuiltinOptions.LogicalOrOptions, -64, -40, BuiltinOptions.IfOptions, -101, -83, -123, BuiltinOptions.GatherNdOptions, -95, 122, -56, BuiltinOptions.GreaterEqualOptions, -32, -47, BuiltinOptions.RandomOptions, -90, BuiltinOptions.GreaterOptions, -60, -29, 118, 120, -73, -76, 9, BuiltinOptions.PackOptions, 14, BuiltinOptions.FloorDivOptions, BuiltinOptions.SquaredDifferenceOptions, -34, -78, -112, BuiltinOptions.CastOptions, -91, -41, 3, BuiltinOptions.ReshapeOptions, 0, -61, BuiltinOptions.LessEqualOptions, -110, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.AbsOptions, BuiltinOptions.SkipGramOptions, -99, 125, -53, 53, 16, -43, BuiltinOptions.SplitVOptions, -98, BuiltinOptions.MirrorPadOptions, -87, BuiltinOptions.WhereOptions, -58, -48, 123, BuiltinOptions.BatchToSpaceNDOptions, -105, -45, 54, -26, BuiltinOptions.FloorModOptions, BuiltinOptions.RankOptions, -127, -113, 119, -52, -100, -71, -30, -84, -72, BuiltinOptions.SelectOptions, BuiltinOptions.MulOptions, -92, 124, -38, BuiltinOptions.PowOptions, BuiltinOptions.SqueezeOptions, 11, 5, -42, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.HashtableFindOptions, 126, BuiltinOptions.CumsumOptions, -3, -79, -27, BuiltinOptions.NonMaxSuppressionV5Options, -81, BuiltinOptions.DepthToSpaceOptions, 51, -121, -55, -16, BuiltinOptions.WhileOptions, BuiltinOptions.HashtableImportOptions, BuiltinOptions.LogicalNotOptions, -120, -115, -57, -9, BuiltinOptions.DivOptions, -23, -20, -19, ByteCompanionObject.MIN_VALUE, BuiltinOptions.LessOptions, BuiltinOptions.MaximumMinimumOptions, -49, -103, -88, 80, 15, 55, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.SliceOptions, -107, -46, BuiltinOptions.LogicalAndOptions, BuiltinOptions.HardSwishOptions, BuiltinOptions.UnpackOptions, -125, -77, BuiltinOptions.Rfft2dOptions, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.SequenceRNNOptions, 7, BuiltinOptions.SubOptions, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, -18, 49, -94, BuiltinOptions.BucketizeOptions, -7, -54, BuiltinOptions.FakeQuantOptions, BuiltinOptions.TransposeOptions, -5, 13, -63, -2, -6, -14, BuiltinOptions.VarHandleOptions, -67, -106, -35, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.AddNOptions, -74, 8, -13, -82, -66, BuiltinOptions.SpaceToBatchNDOptions, -119, 50, BuiltinOptions.DequantizeOptions, -80, -22, BuiltinOptions.LeakyReluOptions, BuiltinOptions.SegmentSumOptions, -124, -126, BuiltinOptions.HashtableOptions, -11, 121, ByteSourceJsonBootstrapper.UTF8_BOM_3, 1, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.SplitOptions, 61, BuiltinOptions.BroadcastToOptions, BuiltinOptions.NegOptions, BuiltinOptions.BatchMatMulOptions, -24, -111, -10, -1, BuiltinOptions.SpaceToDepthOptions, BuiltinOptions.MatrixDiagOptions, -15, BuiltinOptions.UnidirectionalSequenceLSTMOptions, 10, ByteCompanionObject.MAX_VALUE, -59, -89, -25, BuiltinOptions.ScatterNdOptions, BuiltinOptions.MatrixSetDiagOptions, 6, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.FillOptions, BuiltinOptions.SquareOptions, 4, -96, -37, BuiltinOptions.ArgMinOptions, -122, BuiltinOptions.CosOptions, -86, -116, 52, BuiltinOptions.ExpOptions, -117, -8, 12, BuiltinOptions.GeluOptions, BuiltinOptions.CallOnceOptions};
    private static final byte[] S3 = {BuiltinOptions.BroadcastToOptions, -115, -54, BuiltinOptions.MirrorPadOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.LeakyReluOptions, BuiltinOptions.AbsOptions, BuiltinOptions.NegOptions, -44, BuiltinOptions.AddNOptions, BuiltinOptions.DequantizeOptions, -77, BuiltinOptions.CosOptions, BuiltinOptions.SqueezeOptions, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.TopKV2Options, 3, BuiltinOptions.BidirectionalSequenceRNNOptions, 61, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.GatherNdOptions, -125, BuiltinOptions.SpaceToDepthOptions, -118, -73, -43, BuiltinOptions.CastOptions, 121, -11, -67, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.SelectOptions, 13, 2, -19, BuiltinOptions.ReverseV2Options, -98, BuiltinOptions.ReshapeOptions, -14, BuiltinOptions.LogicalAndOptions, BuiltinOptions.WhereOptions, BuiltinOptions.DepthToSpaceOptions, -47, BuiltinOptions.PadOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.CumsumOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.WhileOptions, -13, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.UnpackOptions, -52, -24, -108, BuiltinOptions.RankOptions, 8, -50, BuiltinOptions.TransposeOptions, BuiltinOptions.FakeQuantOptions, -46, -31, -33, -75, BuiltinOptions.PowOptions, BuiltinOptions.HashtableSizeOptions, 14, -27, -12, -7, -122, -23, BuiltinOptions.SplitVOptions, -42, -123, BuiltinOptions.SplitOptions, -49, 50, -103, 49, BuiltinOptions.EmbeddingLookupSparseOptions, -82, -18, -56, BuiltinOptions.FloorModOptions, -45, BuiltinOptions.SliceOptions, -95, -110, BuiltinOptions.FloorDivOptions, -79, BuiltinOptions.BatchToSpaceNDOptions, -60, BuiltinOptions.GreaterOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.RandomOptions, BuiltinOptions.FillOptions, BuiltinOptions.MulOptions, -3, 55, -66, BuiltinOptions.NonMaxSuppressionV4Options, -86, -101, -120, -40, -85, -119, -100, -6, BuiltinOptions.NonMaxSuppressionV5Options, -22, PSSSigner.TRAILER_IMPLICIT, BuiltinOptions.SelectV2Options, 12, BuiltinOptions.LogSoftmaxOptions, -90, -88, -20, BuiltinOptions.CallOnceOptions, 32, -37, 124, BuiltinOptions.ArgMaxOptions, -35, -84, BuiltinOptions.HardSwishOptions, 52, 126, 16, -15, 123, -113, BuiltinOptions.DensifyOptions, -96, 5, -102, BuiltinOptions.ZerosLikeOptions, 119, BuiltinOptions.ExpOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.MaximumMinimumOptions, 9, -61, -97, -74, -41, BuiltinOptions.LessOptions, -62, -21, -64, -92, -117, -116, BuiltinOptions.DivOptions, -5, -1, -63, -78, -105, BuiltinOptions.LessEqualOptions, -8, BuiltinOptions.BatchMatMulOptions, -10, BuiltinOptions.DynamicUpdateSliceOptions, 7, 4, BuiltinOptions.RangeOptions, 51, -28, -39, -71, -48, BuiltinOptions.SquareOptions, -57, BuiltinOptions.HashtableFindOptions, -112, 0, -114, BuiltinOptions.VarHandleOptions, 80, 1, -59, -38, BuiltinOptions.UnidirectionalSequenceLSTMOptions, BuiltinOptions.LogicalNotOptions, -51, BuiltinOptions.Rfft2dOptions, -94, -30, 122, -89, -58, -109, 15, 10, 6, -26, BuiltinOptions.PadV2Options, -106, -93, BuiltinOptions.SubOptions, -81, BuiltinOptions.Conv3DOptions, BuiltinOptions.SkipGramOptions, -124, BuiltinOptions.ArgMinOptions, -25, -80, -126, -9, -2, -99, -121, BuiltinOptions.IfOptions, -127, 53, -34, -76, -91, -4, ByteCompanionObject.MIN_VALUE, ByteSourceJsonBootstrapper.UTF8_BOM_1, -53, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.HashtableOptions, 118, -70, BuiltinOptions.MatrixSetDiagOptions, 125, 120, 11, -107, -29, -83, BuiltinOptions.GeluOptions, -104, BuiltinOptions.PackOptions, 54, BuiltinOptions.SegmentSumOptions, BuiltinOptions.HashtableImportOptions, -36, -16, BuiltinOptions.QuantizeOptions, -87, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.GatherOptions, ByteCompanionObject.MAX_VALUE, -111, -72, -55, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.ReducerOptions, -32, BuiltinOptions.ScatterNdOptions};
    private int blockSize;
    private byte[] buf;
    private int bufOff;
    private int columns;
    private int hashSize;
    private long inputBlocks;
    private final CryptoServicePurpose purpose;
    private int rounds;
    private long[] state;
    private long[] tempState1;
    private long[] tempState2;

    public DSTU7564Digest(int i) {
        this(i, CryptoServicePurpose.ANY);
    }

    public DSTU7564Digest(int i, CryptoServicePurpose cryptoServicePurpose) {
        int i2;
        this.purpose = cryptoServicePurpose;
        if (i != 256 && i != 384 && i != 512) {
            throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
        }
        this.hashSize = i >>> 3;
        if (i > 256) {
            this.columns = 16;
            i2 = 14;
        } else {
            this.columns = 8;
            i2 = 10;
        }
        this.rounds = i2;
        int i3 = this.columns;
        int i4 = i3 << 3;
        this.blockSize = i4;
        long[] jArr = new long[i3];
        this.state = jArr;
        jArr[0] = i4;
        this.tempState1 = new long[i3];
        this.tempState2 = new long[i3];
        this.buf = new byte[i4];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        this.purpose = dSTU7564Digest.purpose;
        copyIn(dSTU7564Digest);
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    private void P(long[] jArr) {
        for (int i = 0; i < this.rounds; i++) {
            long j = i;
            for (int i2 = 0; i2 < this.columns; i2++) {
                jArr[i2] = jArr[i2] ^ j;
                j += 16;
            }
            shiftRows(jArr);
            subBytes(jArr);
            mixColumns(jArr);
        }
    }

    private void Q(long[] jArr) {
        for (int i = 0; i < this.rounds; i++) {
            long j = ((((this.columns - 1) << 4) ^ i) << 56) | 67818912035696883L;
            for (int i2 = 0; i2 < this.columns; i2++) {
                jArr[i2] = jArr[i2] + j;
                j -= 1152921504606846976L;
            }
            shiftRows(jArr);
            subBytes(jArr);
            mixColumns(jArr);
        }
    }

    private void copyIn(DSTU7564Digest dSTU7564Digest) {
        this.hashSize = dSTU7564Digest.hashSize;
        this.blockSize = dSTU7564Digest.blockSize;
        this.rounds = dSTU7564Digest.rounds;
        int i = this.columns;
        if (i <= 0 || i != dSTU7564Digest.columns) {
            this.columns = dSTU7564Digest.columns;
            this.state = Arrays.clone(dSTU7564Digest.state);
            int i2 = this.columns;
            this.tempState1 = new long[i2];
            this.tempState2 = new long[i2];
            this.buf = Arrays.clone(dSTU7564Digest.buf);
        } else {
            System.arraycopy(dSTU7564Digest.state, 0, this.state, 0, i);
            System.arraycopy(dSTU7564Digest.buf, 0, this.buf, 0, this.blockSize);
        }
        this.inputBlocks = dSTU7564Digest.inputBlocks;
        this.bufOff = dSTU7564Digest.bufOff;
    }

    private static long mixColumn(long j) {
        long j2 = ((9187201950435737471L & j) << 1) ^ (((j & (-9187201950435737472L)) >>> 7) * 29);
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(16, rotate)) ^ rotate(48, j);
        long j3 = (j ^ rotate2) ^ j2;
        long j4 = (((-9187201950435737472L) & j3) >>> 6) * 29;
        return ((rotate(32, (((j3 & 4629771061636907072L) >>> 6) * 29) ^ (j4 ^ ((4557430888798830399L & j3) << 2))) ^ rotate2) ^ rotate(40, j2)) ^ rotate(48, j2);
    }

    private void mixColumns(long[] jArr) {
        for (int i = 0; i < this.columns; i++) {
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void processBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.columns; i2++) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
            i += 8;
            this.tempState1[i2] = this.state[i2] ^ littleEndianToLong;
            this.tempState2[i2] = littleEndianToLong;
        }
        P(this.tempState1);
        Q(this.tempState2);
        for (int i3 = 0; i3 < this.columns; i3++) {
            long[] jArr = this.state;
            jArr[i3] = jArr[i3] ^ (this.tempState1[i3] ^ this.tempState2[i3]);
        }
    }

    private static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void shiftRows(long[] jArr) {
        int i = this.columns;
        if (i == 8) {
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = (j ^ j5) & (-4294967296L);
            long j10 = j ^ j9;
            long j11 = j5 ^ j9;
            long j12 = (j2 ^ j6) & 72057594021150720L;
            long j13 = j2 ^ j12;
            long j14 = j6 ^ j12;
            long j15 = (j3 ^ j7) & 281474976645120L;
            long j16 = j3 ^ j15;
            long j17 = j7 ^ j15;
            long j18 = (j4 ^ j8) & 1099511627520L;
            long j19 = j4 ^ j18;
            long j20 = j8 ^ j18;
            long j21 = (j10 ^ j16) & (-281470681808896L);
            long j22 = j10 ^ j21;
            long j23 = j16 ^ j21;
            long j24 = (j13 ^ j19) & 72056494543077120L;
            long j25 = j13 ^ j24;
            long j26 = j19 ^ j24;
            long j27 = (j11 ^ j17) & (-281470681808896L);
            long j28 = j11 ^ j27;
            long j29 = j17 ^ j27;
            long j30 = (j14 ^ j20) & 72056494543077120L;
            long j31 = j14 ^ j30;
            long j32 = j20 ^ j30;
            long j33 = (j22 ^ j25) & (-71777214294589696L);
            long j34 = j22 ^ j33;
            long j35 = j25 ^ j33;
            long j36 = (j23 ^ j26) & (-71777214294589696L);
            long j37 = j23 ^ j36;
            long j38 = j26 ^ j36;
            long j39 = (j28 ^ j31) & (-71777214294589696L);
            long j40 = (j29 ^ j32) & (-71777214294589696L);
            jArr[0] = j34;
            jArr[1] = j35;
            jArr[2] = j37;
            jArr[3] = j38;
            jArr[4] = j28 ^ j39;
            jArr[5] = j31 ^ j39;
            jArr[6] = j29 ^ j40;
            jArr[7] = j32 ^ j40;
        } else if (i != 16) {
            throw new IllegalStateException("unsupported state size: only 512/1024 are allowed");
        } else {
            long j41 = jArr[0];
            long j42 = jArr[1];
            long j43 = jArr[2];
            long j44 = jArr[3];
            long j45 = jArr[4];
            long j46 = jArr[5];
            long j47 = jArr[6];
            long j48 = jArr[7];
            long j49 = jArr[8];
            long j50 = jArr[9];
            long j51 = jArr[10];
            long j52 = jArr[11];
            long j53 = jArr[12];
            long j54 = jArr[13];
            long j55 = jArr[14];
            long j56 = jArr[15];
            long j57 = (j41 ^ j49) & (-72057594037927936L);
            long j58 = j41 ^ j57;
            long j59 = j49 ^ j57;
            long j60 = (j42 ^ j50) & (-72057594037927936L);
            long j61 = j42 ^ j60;
            long j62 = j50 ^ j60;
            long j63 = (j43 ^ j51) & (-281474976710656L);
            long j64 = j43 ^ j63;
            long j65 = j51 ^ j63;
            long j66 = (j44 ^ j52) & (-1099511627776L);
            long j67 = j44 ^ j66;
            long j68 = j52 ^ j66;
            long j69 = (j45 ^ j53) & (-4294967296L);
            long j70 = j45 ^ j69;
            long j71 = j53 ^ j69;
            long j72 = (j46 ^ j54) & 72057594021150720L;
            long j73 = j46 ^ j72;
            long j74 = j54 ^ j72;
            long j75 = (j47 ^ j55) & 72057594037862400L;
            long j76 = j47 ^ j75;
            long j77 = j55 ^ j75;
            long j78 = (j48 ^ j56) & 72057594037927680L;
            long j79 = j48 ^ j78;
            long j80 = j56 ^ j78;
            long j81 = (j58 ^ j70) & 72057589742960640L;
            long j82 = j58 ^ j81;
            long j83 = j70 ^ j81;
            long j84 = (j61 ^ j73) & (-16777216);
            long j85 = j61 ^ j84;
            long j86 = j73 ^ j84;
            long j87 = (j64 ^ j76) & (-71776119061282816L);
            long j88 = j64 ^ j87;
            long j89 = j76 ^ j87;
            long j90 = (j67 ^ j79) & (-72056494526300416L);
            long j91 = j67 ^ j90;
            long j92 = j79 ^ j90;
            long j93 = (j59 ^ j71) & 72057589742960640L;
            long j94 = j59 ^ j93;
            long j95 = j71 ^ j93;
            long j96 = (j62 ^ j74) & (-16777216);
            long j97 = j62 ^ j96;
            long j98 = j74 ^ j96;
            long j99 = (j65 ^ j77) & (-71776119061282816L);
            long j100 = j65 ^ j99;
            long j101 = j77 ^ j99;
            long j102 = (j68 ^ j80) & (-72056494526300416L);
            long j103 = j68 ^ j102;
            long j104 = j80 ^ j102;
            long j105 = (j82 ^ j88) & (-281470681808896L);
            long j106 = j82 ^ j105;
            long j107 = j88 ^ j105;
            long j108 = (j85 ^ j91) & 72056494543077120L;
            long j109 = j85 ^ j108;
            long j110 = j91 ^ j108;
            long j111 = (j83 ^ j89) & (-281470681808896L);
            long j112 = j83 ^ j111;
            long j113 = j89 ^ j111;
            long j114 = (j86 ^ j92) & 72056494543077120L;
            long j115 = j86 ^ j114;
            long j116 = j92 ^ j114;
            long j117 = (j94 ^ j100) & (-281470681808896L);
            long j118 = j94 ^ j117;
            long j119 = j100 ^ j117;
            long j120 = (j97 ^ j103) & 72056494543077120L;
            long j121 = j97 ^ j120;
            long j122 = j103 ^ j120;
            long j123 = (j95 ^ j101) & (-281470681808896L);
            long j124 = j95 ^ j123;
            long j125 = j101 ^ j123;
            long j126 = (j98 ^ j104) & 72056494543077120L;
            long j127 = j98 ^ j126;
            long j128 = j104 ^ j126;
            long j129 = (j106 ^ j109) & (-71777214294589696L);
            long j130 = j106 ^ j129;
            long j131 = j109 ^ j129;
            long j132 = (j107 ^ j110) & (-71777214294589696L);
            long j133 = j107 ^ j132;
            long j134 = j110 ^ j132;
            long j135 = (j112 ^ j115) & (-71777214294589696L);
            long j136 = j112 ^ j135;
            long j137 = j115 ^ j135;
            long j138 = (j113 ^ j116) & (-71777214294589696L);
            long j139 = j113 ^ j138;
            long j140 = j116 ^ j138;
            long j141 = (j118 ^ j121) & (-71777214294589696L);
            long j142 = j118 ^ j141;
            long j143 = j121 ^ j141;
            long j144 = (j119 ^ j122) & (-71777214294589696L);
            long j145 = j119 ^ j144;
            long j146 = j122 ^ j144;
            long j147 = (j124 ^ j127) & (-71777214294589696L);
            long j148 = (j125 ^ j128) & (-71777214294589696L);
            jArr[0] = j130;
            jArr[1] = j131;
            jArr[2] = j133;
            jArr[3] = j134;
            jArr[4] = j136;
            jArr[5] = j137;
            jArr[6] = j139;
            jArr[7] = j140;
            jArr[8] = j142;
            jArr[9] = j143;
            jArr[10] = j145;
            jArr[11] = j146;
            jArr[12] = j124 ^ j147;
            jArr[13] = j127 ^ j147;
            jArr[14] = j125 ^ j148;
            jArr[15] = j128 ^ j148;
        }
    }

    private void subBytes(long[] jArr) {
        for (int i = 0; i < this.columns; i++) {
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = S0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = S1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = S2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            byte[] bArr4 = S3;
            int i4 = (bArr4[i2 >>> 24] << BuiltinOptions.BatchToSpaceNDOptions) | (b & UByte.MAX_VALUE) | ((b2 & UByte.MAX_VALUE) << 8) | ((b3 & UByte.MAX_VALUE) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & BodyPartID.bodyIdMax) | (((bArr4[i3 >>> 24] << BuiltinOptions.BatchToSpaceNDOptions) | (((b4 & UByte.MAX_VALUE) | ((b5 & UByte.MAX_VALUE) << 8)) | ((b6 & UByte.MAX_VALUE) << 16))) << 32);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, 256, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = this.bufOff;
        byte[] bArr2 = this.buf;
        int i5 = i4 + 1;
        this.bufOff = i5;
        bArr2[i4] = ByteCompanionObject.MIN_VALUE;
        int i6 = this.blockSize - 12;
        int i7 = 0;
        if (i5 > i6) {
            while (true) {
                int i8 = this.bufOff;
                if (i8 >= this.blockSize) {
                    break;
                }
                byte[] bArr3 = this.buf;
                this.bufOff = i8 + 1;
                bArr3[i8] = 0;
            }
            this.bufOff = 0;
            processBlock(this.buf, 0);
        }
        while (true) {
            i2 = this.bufOff;
            if (i2 >= i6) {
                break;
            }
            byte[] bArr4 = this.buf;
            this.bufOff = i2 + 1;
            bArr4[i2] = 0;
        }
        long j = (((this.inputBlocks & BodyPartID.bodyIdMax) * this.blockSize) + i4) << 3;
        Pack.intToLittleEndian((int) j, this.buf, i2);
        int i9 = this.bufOff + 4;
        this.bufOff = i9;
        Pack.longToLittleEndian((j >>> 32) + (((this.inputBlocks >>> 32) * this.blockSize) << 3), this.buf, i9);
        processBlock(this.buf, 0);
        System.arraycopy(this.state, 0, this.tempState1, 0, this.columns);
        P(this.tempState1);
        while (true) {
            i3 = this.columns;
            if (i7 >= i3) {
                break;
            }
            long[] jArr = this.state;
            jArr[i7] = jArr[i7] ^ this.tempState1[i7];
            i7++;
        }
        for (int i10 = i3 - (this.hashSize >>> 3); i10 < this.columns; i10++) {
            Pack.longToLittleEndian(this.state[i10], bArr, i);
            i += 8;
        }
        reset();
        return this.hashSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "DSTU7564";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.hashSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        Arrays.fill(this.state, 0L);
        this.state[0] = this.blockSize;
        this.inputBlocks = 0L;
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((DSTU7564Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.buf;
        int i = this.bufOff;
        int i2 = i + 1;
        this.bufOff = i2;
        bArr[i] = b;
        if (i2 == this.blockSize) {
            processBlock(bArr, 0);
            this.bufOff = 0;
            this.inputBlocks++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.bufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i2--;
            i++;
        }
        if (i2 > 0) {
            while (i2 >= this.blockSize) {
                processBlock(bArr, i);
                int i3 = this.blockSize;
                i += i3;
                i2 -= i3;
                this.inputBlocks++;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i2--;
                i++;
            }
        }
    }
}
