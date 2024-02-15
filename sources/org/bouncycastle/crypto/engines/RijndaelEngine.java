package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.lang.reflect.Array;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.tensorflow.lite.schema.BuiltinOperator;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;
    private long A0;
    private long A1;
    private long A2;
    private long A3;
    private int BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, BuiltinOptions.SpaceToBatchNDOptions, 1, 50, 2, BuiltinOptions.TransposeOptions, -58, BuiltinOptions.LeakyReluOptions, -57, BuiltinOptions.ReducerOptions, BuiltinOptions.BroadcastToOptions, 51, -18, -33, 3, BuiltinOptions.SegmentSumOptions, 4, -32, 14, 52, -115, -127, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.AssignVariableOptions, 8, -56, -8, BuiltinOptions.Rfft2dOptions, BuiltinOptions.SubOptions, -63, 125, -62, BuiltinOptions.DivOptions, -75, -7, -71, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.Conv3DOptions, BuiltinOptions.MirrorPadOptions, -28, -90, BuiltinOptions.RandomOptions, -102, -55, 9, 120, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.SelectOptions, -118, 5, BuiltinOptions.ExpOptions, 15, -31, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.SkipGramOptions, -16, -126, BuiltinOptions.BidirectionalSequenceLSTMOptions, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, BuiltinOptions.SpaceToDepthOptions, BuiltinOptions.IfOptions, -46, -15, BuiltinOptions.UnpackOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, -125, BuiltinOptions.PowOptions, BuiltinOptions.CumsumOptions, -35, -3, BuiltinOptions.SliceOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, 6, -117, BuiltinOptions.SelectV2Options, -77, BuiltinOptions.CastOptions, -30, -104, BuiltinOptions.TopKV2Options, -120, -111, 16, 126, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.FloorModOptions, -61, -93, -74, BuiltinOptions.SqueezeOptions, BuiltinOptions.SquareOptions, BuiltinOptions.FakeQuantOptions, BuiltinOptions.HashtableOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.CosOptions, -6, -123, 61, -70, BuiltinOptions.PadV2Options, 121, 10, BuiltinOptions.MulOptions, -101, -97, BuiltinOptions.DepthToSpaceOptions, -54, BuiltinOptions.AbsOptions, -44, -84, -27, -13, BuiltinOptions.BucketizeOptions, -89, BuiltinOptions.ReverseSequenceOptions, -81, BuiltinOptions.MatrixDiagOptions, -88, 80, -12, -22, -42, BuiltinOptions.GeluOptions, BuiltinOptions.SplitVOptions, -82, -23, -43, -25, -26, -83, -24, BuiltinOptions.GreaterOptions, -41, BuiltinOptions.DynamicUpdateSliceOptions, 122, -21, BuiltinOptions.PadOptions, 11, -11, BuiltinOptions.QuantizeOptions, -53, BuiltinOptions.NonMaxSuppressionV4Options, -80, -100, -87, BuiltinOptions.ReverseV2Options, -96, ByteCompanionObject.MAX_VALUE, 12, -10, BuiltinOptions.VarHandleOptions, BuiltinOptions.GatherOptions, -60, BuiltinOptions.RangeOptions, -20, -40, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.GreaterEqualOptions, -92, 118, 123, -73, -52, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.LogicalAndOptions, BuiltinOptions.MatrixSetDiagOptions, -5, BuiltinOptions.NonMaxSuppressionV5Options, -79, -122, BuiltinOptions.PackOptions, BuiltinOptions.AddNOptions, -95, BuiltinOptions.HashtableFindOptions, -86, BuiltinOptions.WhereOptions, BuiltinOptions.LessOptions, -99, -105, -78, -121, -112, BuiltinOptions.ScatterNdOptions, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, BuiltinOptions.LogicalNotOptions, BuiltinOptions.HardSwishOptions, -47, BuiltinOptions.GatherNdOptions, BuiltinOptions.ArgMinOptions, -124, BuiltinOptions.LogicalOrOptions, BuiltinOptions.FloorDivOptions, -94, BuiltinOptions.HashtableImportOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.NegOptions, -98, BuiltinOptions.WhileOptions, BuiltinOptions.RankOptions, -14, -45, -85, BuiltinOptions.FillOptions, BuiltinOptions.ReshapeOptions, -110, -39, BuiltinOptions.SplitOptions, 32, BuiltinOptions.LessEqualOptions, -119, -76, 124, -72, BuiltinOptions.DequantizeOptions, 119, -103, -29, -91, BuiltinOptions.CallOnceOptions, BuiltinOptions.ResizeNearestNeighborOptions, -19, -34, -59, 49, -2, BuiltinOptions.BatchToSpaceNDOptions, 13, BuiltinOptions.DensifyOptions, -116, ByteCompanionObject.MIN_VALUE, -64, -9, BuiltinOptions.ReadVariableOptions, 7};
    private static final byte[] aLogtable = {0, 3, 5, 15, BuiltinOptions.ReshapeOptions, 51, BuiltinOptions.WhereOptions, -1, BuiltinOptions.TransposeOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.RandomOptions, -106, -95, -8, BuiltinOptions.SpaceToDepthOptions, 53, BuiltinOptions.NonMaxSuppressionV4Options, -31, BuiltinOptions.PowOptions, BuiltinOptions.FloorModOptions, -40, BuiltinOptions.BucketizeOptions, -107, -92, -9, 2, 6, 10, BuiltinOptions.SqueezeOptions, BuiltinOptions.TopKV2Options, BuiltinOptions.CumsumOptions, -86, -27, 52, BuiltinOptions.IfOptions, -28, 55, BuiltinOptions.QuantizeOptions, -21, BuiltinOptions.DequantizeOptions, BuiltinOptions.Conv3DOptions, -66, -39, BuiltinOptions.ReadVariableOptions, -112, -85, -26, 49, BuiltinOptions.GatherNdOptions, -11, 4, 12, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.FillOptions, -52, BuiltinOptions.SplitVOptions, -47, BuiltinOptions.BroadcastToOptions, -72, -45, BuiltinOptions.HashtableSizeOptions, -78, -51, BuiltinOptions.SquaredDifferenceOptions, -44, BuiltinOptions.CallOnceOptions, -87, -32, BuiltinOptions.PackOptions, BuiltinOptions.MirrorPadOptions, -41, BuiltinOptions.SelectV2Options, -90, -15, 8, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.ArgMaxOptions, 120, -120, -125, -98, -71, -48, BuiltinOptions.HashtableOptions, -67, -36, ByteCompanionObject.MAX_VALUE, -127, -104, -77, -50, BuiltinOptions.RangeOptions, -37, 118, -102, -75, -60, BuiltinOptions.ReverseSequenceOptions, -7, 16, BuiltinOptions.SliceOptions, 80, -16, 11, BuiltinOptions.DivOptions, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.Rfft2dOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, -42, BuiltinOptions.ScatterNdOptions, -93, -2, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.PadV2Options, 125, -121, -110, -83, -20, BuiltinOptions.SelectOptions, BuiltinOptions.AssignVariableOptions, -109, -82, -23, 32, BuiltinOptions.NonMaxSuppressionV5Options, -96, -5, BuiltinOptions.PadOptions, BuiltinOptions.FakeQuantOptions, BuiltinOptions.AbsOptions, -46, BuiltinOptions.HashtableImportOptions, -73, -62, BuiltinOptions.WhileOptions, -25, 50, BuiltinOptions.RankOptions, -6, BuiltinOptions.MulOptions, BuiltinOptions.LogicalNotOptions, BuiltinOptions.FloorDivOptions, -61, BuiltinOptions.DepthToSpaceOptions, -30, 61, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -55, BuiltinOptions.UnpackOptions, -64, BuiltinOptions.HardSwishOptions, -19, BuiltinOptions.GreaterOptions, BuiltinOptions.GeluOptions, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, -38, BuiltinOptions.DynamicUpdateSliceOptions, -97, -70, -43, BuiltinOptions.SegmentSumOptions, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.NegOptions, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, ByteCompanionObject.MIN_VALUE, -101, -74, -63, BuiltinOptions.MatrixDiagOptions, -24, BuiltinOptions.SplitOptions, BuiltinOptions.BatchMatMulOptions, -81, -22, BuiltinOptions.CastOptions, BuiltinOptions.VarHandleOptions, -79, -56, BuiltinOptions.ZerosLikeOptions, -59, BuiltinOptions.CosOptions, -4, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.ExpOptions, BuiltinOptions.DensifyOptions, -91, -12, 7, 9, BuiltinOptions.ReducerOptions, BuiltinOptions.GreaterEqualOptions, 119, -103, -80, -53, BuiltinOptions.BidirectionalSequenceRNNOptions, -54, BuiltinOptions.BidirectionalSequenceLSTMOptions, -49, BuiltinOptions.ResizeNearestNeighborOptions, -34, 121, -117, -122, -111, -88, -29, BuiltinOptions.LogicalAndOptions, BuiltinOptions.SquareOptions, -58, BuiltinOptions.ReverseV2Options, -13, 14, BuiltinOptions.SkipGramOptions, 54, BuiltinOptions.MatrixSetDiagOptions, -18, BuiltinOptions.LessOptions, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, BuiltinOptions.GatherOptions, BuiltinOptions.ArgMinOptions, BuiltinOptions.LeakyReluOptions, -35, 124, -124, -105, -94, -3, BuiltinOptions.SubOptions, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.HashtableFindOptions, -76, -57, BuiltinOptions.AddNOptions, -10, 1, 3, 5, 15, BuiltinOptions.ReshapeOptions, 51, BuiltinOptions.WhereOptions, -1, BuiltinOptions.TransposeOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.RandomOptions, -106, -95, -8, BuiltinOptions.SpaceToDepthOptions, 53, BuiltinOptions.NonMaxSuppressionV4Options, -31, BuiltinOptions.PowOptions, BuiltinOptions.FloorModOptions, -40, BuiltinOptions.BucketizeOptions, -107, -92, -9, 2, 6, 10, BuiltinOptions.SqueezeOptions, BuiltinOptions.TopKV2Options, BuiltinOptions.CumsumOptions, -86, -27, 52, BuiltinOptions.IfOptions, -28, 55, BuiltinOptions.QuantizeOptions, -21, BuiltinOptions.DequantizeOptions, BuiltinOptions.Conv3DOptions, -66, -39, BuiltinOptions.ReadVariableOptions, -112, -85, -26, 49, BuiltinOptions.GatherNdOptions, -11, 4, 12, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.FillOptions, -52, BuiltinOptions.SplitVOptions, -47, BuiltinOptions.BroadcastToOptions, -72, -45, BuiltinOptions.HashtableSizeOptions, -78, -51, BuiltinOptions.SquaredDifferenceOptions, -44, BuiltinOptions.CallOnceOptions, -87, -32, BuiltinOptions.PackOptions, BuiltinOptions.MirrorPadOptions, -41, BuiltinOptions.SelectV2Options, -90, -15, 8, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.ArgMaxOptions, 120, -120, -125, -98, -71, -48, BuiltinOptions.HashtableOptions, -67, -36, ByteCompanionObject.MAX_VALUE, -127, -104, -77, -50, BuiltinOptions.RangeOptions, -37, 118, -102, -75, -60, BuiltinOptions.ReverseSequenceOptions, -7, 16, BuiltinOptions.SliceOptions, 80, -16, 11, BuiltinOptions.DivOptions, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.Rfft2dOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, -42, BuiltinOptions.ScatterNdOptions, -93, -2, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.PadV2Options, 125, -121, -110, -83, -20, BuiltinOptions.SelectOptions, BuiltinOptions.AssignVariableOptions, -109, -82, -23, 32, BuiltinOptions.NonMaxSuppressionV5Options, -96, -5, BuiltinOptions.PadOptions, BuiltinOptions.FakeQuantOptions, BuiltinOptions.AbsOptions, -46, BuiltinOptions.HashtableImportOptions, -73, -62, BuiltinOptions.WhileOptions, -25, 50, BuiltinOptions.RankOptions, -6, BuiltinOptions.MulOptions, BuiltinOptions.LogicalNotOptions, BuiltinOptions.FloorDivOptions, -61, BuiltinOptions.DepthToSpaceOptions, -30, 61, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -55, BuiltinOptions.UnpackOptions, -64, BuiltinOptions.HardSwishOptions, -19, BuiltinOptions.GreaterOptions, BuiltinOptions.GeluOptions, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, -38, BuiltinOptions.DynamicUpdateSliceOptions, -97, -70, -43, BuiltinOptions.SegmentSumOptions, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.NegOptions, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, ByteCompanionObject.MIN_VALUE, -101, -74, -63, BuiltinOptions.MatrixDiagOptions, -24, BuiltinOptions.SplitOptions, BuiltinOptions.BatchMatMulOptions, -81, -22, BuiltinOptions.CastOptions, BuiltinOptions.VarHandleOptions, -79, -56, BuiltinOptions.ZerosLikeOptions, -59, BuiltinOptions.CosOptions, -4, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.ExpOptions, BuiltinOptions.DensifyOptions, -91, -12, 7, 9, BuiltinOptions.ReducerOptions, BuiltinOptions.GreaterEqualOptions, 119, -103, -80, -53, BuiltinOptions.BidirectionalSequenceRNNOptions, -54, BuiltinOptions.BidirectionalSequenceLSTMOptions, -49, BuiltinOptions.ResizeNearestNeighborOptions, -34, 121, -117, -122, -111, -88, -29, BuiltinOptions.LogicalAndOptions, BuiltinOptions.SquareOptions, -58, BuiltinOptions.ReverseV2Options, -13, 14, BuiltinOptions.SkipGramOptions, 54, BuiltinOptions.MatrixSetDiagOptions, -18, BuiltinOptions.LessOptions, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, BuiltinOptions.GatherOptions, BuiltinOptions.ArgMinOptions, BuiltinOptions.LeakyReluOptions, -35, 124, -124, -105, -94, -3, BuiltinOptions.SubOptions, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.HashtableFindOptions, -76, -57, BuiltinOptions.AddNOptions, -10, 1};
    private static final byte[] S = {BuiltinOptions.DensifyOptions, 124, 119, 123, -14, BuiltinOptions.HashtableOptions, BuiltinOptions.VarHandleOptions, -59, BuiltinOptions.SliceOptions, 1, BuiltinOptions.CallOnceOptions, BuiltinOptions.PadV2Options, -2, -41, -85, 118, -54, -126, -55, 125, -6, BuiltinOptions.QuantizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -16, -83, -44, -94, -81, -100, -92, BuiltinOptions.RandomOptions, -64, -73, -3, -109, BuiltinOptions.DequantizeOptions, 54, BuiltinOptions.LogicalNotOptions, -9, -52, 52, -91, -27, -15, BuiltinOptions.AssignVariableOptions, -40, 49, BuiltinOptions.MulOptions, 4, -57, BuiltinOptions.SplitOptions, -61, BuiltinOptions.BatchToSpaceNDOptions, -106, 5, -102, 7, BuiltinOptions.SkipGramOptions, ByteCompanionObject.MIN_VALUE, -30, -21, BuiltinOptions.MaximumMinimumOptions, -78, BuiltinOptions.DynamicUpdateSliceOptions, 9, -125, BuiltinOptions.GreaterOptions, BuiltinOptions.TransposeOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.MatrixSetDiagOptions, -96, BuiltinOptions.AddNOptions, BuiltinOptions.PackOptions, -42, -77, BuiltinOptions.LessOptions, -29, BuiltinOptions.SelectOptions, -124, BuiltinOptions.GatherNdOptions, -47, 0, -19, 32, -4, -79, BuiltinOptions.HardSwishOptions, BuiltinOptions.Conv3DOptions, -53, -66, BuiltinOptions.ArgMinOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.MatrixDiagOptions, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.MirrorPadOptions, 51, -123, BuiltinOptions.BidirectionalSequenceLSTMOptions, -7, 2, ByteCompanionObject.MAX_VALUE, 80, BuiltinOptions.LogicalOrOptions, -97, -88, BuiltinOptions.ReverseV2Options, -93, BuiltinOptions.UnpackOptions, -113, -110, -99, BuiltinOptions.PowOptions, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BuiltinOptions.ExpOptions, 16, -1, -13, -46, -51, 12, BuiltinOptions.SpaceToDepthOptions, -20, BuiltinOptions.NonMaxSuppressionV4Options, -105, BuiltinOptions.FillOptions, BuiltinOptions.GatherOptions, -60, -89, 126, 61, BuiltinOptions.SegmentSumOptions, BuiltinOptions.WhileOptions, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.NonMaxSuppressionV5Options, -127, BuiltinOptions.SplitVOptions, -36, BuiltinOptions.TopKV2Options, BuiltinOptions.NegOptions, -112, -120, BuiltinOptions.BidirectionalSequenceRNNOptions, -18, -72, BuiltinOptions.EmbeddingLookupSparseOptions, -34, BuiltinOptions.DepthToSpaceOptions, 11, -37, -32, 50, BuiltinOptions.FakeQuantOptions, 10, BuiltinOptions.RangeOptions, 6, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.IfOptions, -62, -45, -84, BuiltinOptions.SelectV2Options, -111, -107, -28, 121, -25, -56, 55, BuiltinOptions.HashtableImportOptions, -115, -43, BuiltinOptions.AbsOptions, -87, BuiltinOptions.HashtableFindOptions, BuiltinOptions.RankOptions, -12, -22, BuiltinOptions.BatchMatMulOptions, 122, -82, 8, -70, 120, BuiltinOptions.CastOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.SubOptions, -90, -76, -58, -24, -35, BuiltinOptions.GeluOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.LeakyReluOptions, -67, -117, -118, BuiltinOptions.ReadVariableOptions, BuiltinOptions.LogicalAndOptions, -75, BuiltinOptions.CumsumOptions, BuiltinOptions.FloorModOptions, 3, -10, 14, BuiltinOptions.ScatterNdOptions, 53, BuiltinOptions.ReverseSequenceOptions, -71, -122, -63, BuiltinOptions.DivOptions, -98, -31, -8, -104, BuiltinOptions.ReshapeOptions, BuiltinOptions.Rfft2dOptions, -39, -114, -108, -101, BuiltinOptions.SqueezeOptions, -121, -23, -50, BuiltinOptions.WhereOptions, BuiltinOptions.ArgMaxOptions, -33, -116, -95, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, BuiltinOptions.SquareOptions, BuiltinOptions.BroadcastToOptions, BuiltinOptions.FloorDivOptions, -103, BuiltinOptions.GreaterEqualOptions, 15, -80, BuiltinOptions.CosOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.PadOptions};
    private static final byte[] Si = {BuiltinOptions.AddNOptions, 9, BuiltinOptions.Conv3DOptions, -43, BuiltinOptions.SliceOptions, 54, -91, BuiltinOptions.PowOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.UnpackOptions, -93, -98, -127, -13, -41, -5, 124, -29, BuiltinOptions.ArgMinOptions, -126, -101, BuiltinOptions.SelectOptions, -1, -121, 52, -114, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.FillOptions, -60, -34, -23, -53, BuiltinOptions.CosOptions, 123, -108, 50, -90, -62, BuiltinOptions.SplitOptions, 61, -18, BuiltinOptions.SquaredDifferenceOptions, -107, 11, BuiltinOptions.SquareOptions, -6, -61, BuiltinOptions.AbsOptions, 8, BuiltinOptions.LessEqualOptions, -95, BuiltinOptions.CumsumOptions, BuiltinOptions.ArgMaxOptions, -39, BuiltinOptions.LogSoftmaxOptions, -78, 118, BuiltinOptions.HardSwishOptions, -94, BuiltinOptions.RangeOptions, BuiltinOptions.HashtableImportOptions, -117, -47, BuiltinOptions.CastOptions, BuiltinOptions.RandomOptions, -8, -10, BuiltinOptions.SegmentSumOptions, -122, BuiltinOptions.BroadcastToOptions, -104, BuiltinOptions.PadOptions, -44, -92, BuiltinOptions.IfOptions, -52, BuiltinOptions.WhileOptions, BuiltinOptions.BatchMatMulOptions, -74, -110, BuiltinOptions.HashtableFindOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.FloorModOptions, 80, -3, -19, -71, -38, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.MulOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.ReverseSequenceOptions, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, BuiltinOptions.MatrixDiagOptions, 5, -72, -77, BuiltinOptions.BidirectionalSequenceLSTMOptions, 6, -48, BuiltinOptions.GreaterOptions, BuiltinOptions.SqueezeOptions, -113, -54, BuiltinOptions.LogicalNotOptions, 15, 2, -63, -81, -67, 3, 1, BuiltinOptions.SpaceToDepthOptions, -118, BuiltinOptions.HashtableOptions, BuiltinOptions.FakeQuantOptions, -111, BuiltinOptions.ReshapeOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.CallOnceOptions, -36, -22, -105, -14, -49, -50, -16, -76, -26, BuiltinOptions.BucketizeOptions, -106, -84, BuiltinOptions.GeluOptions, BuiltinOptions.TopKV2Options, -25, -83, 53, -123, -30, -7, 55, -24, BuiltinOptions.SubOptions, BuiltinOptions.DynamicUpdateSliceOptions, -33, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -15, BuiltinOptions.TransposeOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.DivOptions, BuiltinOptions.LessOptions, -59, -119, BuiltinOptions.VarHandleOptions, -73, BuiltinOptions.SelectV2Options, 14, -86, BuiltinOptions.BatchToSpaceNDOptions, -66, BuiltinOptions.ReducerOptions, -4, BuiltinOptions.RankOptions, BuiltinOptions.LogicalAndOptions, BuiltinOptions.LeakyReluOptions, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, BuiltinOptions.MatrixSetDiagOptions, -12, BuiltinOptions.SequenceRNNOptions, -35, -88, 51, -120, 7, -57, 49, -79, BuiltinOptions.SkipGramOptions, 16, BuiltinOptions.QuantizeOptions, BuiltinOptions.MaximumMinimumOptions, ByteCompanionObject.MIN_VALUE, -20, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.ReverseV2Options, ByteCompanionObject.MAX_VALUE, -87, BuiltinOptions.SpaceToBatchNDOptions, -75, BuiltinOptions.ResizeNearestNeighborOptions, 13, BuiltinOptions.GreaterEqualOptions, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, BuiltinOptions.PackOptions, BuiltinOptions.MirrorPadOptions, -82, BuiltinOptions.NegOptions, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.LogicalOrOptions, -125, BuiltinOptions.GatherNdOptions, -103, BuiltinOptions.ScatterNdOptions, BuiltinOptions.GatherOptions, BuiltinOptions.PadV2Options, 4, 126, -70, 119, -42, BuiltinOptions.DequantizeOptions, -31, BuiltinOptions.Rfft2dOptions, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.WhereOptions, BuiltinOptions.ExpOptions, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, BuiltinOperator.DYNAMIC_UPDATE_SLICE, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, BuiltinOperator.BROADCAST_ARGS};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, BuiltinOptions.BatchToSpaceNDOptions}, new byte[]{0, 8, 16, BuiltinOptions.BatchToSpaceNDOptions}, new byte[]{0, 8, 16, BuiltinOptions.BatchToSpaceNDOptions}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, BuiltinOptions.BatchToSpaceNDOptions, 32}};
    static byte[][] shifts1 = {new byte[]{0, BuiltinOptions.BatchToSpaceNDOptions, 16, 8}, new byte[]{0, 32, BuiltinOptions.BatchToSpaceNDOptions, 16}, new byte[]{0, BuiltinOptions.ArgMaxOptions, 32, BuiltinOptions.BatchToSpaceNDOptions}, new byte[]{0, BuiltinOptions.SliceOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.BatchToSpaceNDOptions}, new byte[]{0, BuiltinOptions.PowOptions, BuiltinOptions.ArgMaxOptions, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.BC = 32;
            this.BC_MASK = BodyPartID.bodyIdMax;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else if (i != 256) {
            throw new IllegalArgumentException("unknown blocksize to Rijndael");
        } else {
            this.BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = i;
    }

    private void InvMixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            long j5 = j3;
            int i5 = (int) ((this.A3 >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? logtable[i2 & 255] & UByte.MAX_VALUE : -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & UByte.MAX_VALUE : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & UByte.MAX_VALUE : -1;
            if (i5 != 0) {
                i6 = logtable[i5 & 255] & UByte.MAX_VALUE;
            }
            j |= ((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i6)) & 255) << i;
            j4 |= ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i6)) ^ mul0x9(i7)) & 255) << i;
            j2 |= ((((mul0xe(i9) ^ mul0xb(i6)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255) << i;
            j3 = (((((mul0xe(i6) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255) << i) | j5;
        }
        this.A0 = j;
        this.A1 = j4;
        this.A2 = j2;
        this.A3 = j3;
    }

    private void KeyAddition(long[] jArr) {
        this.A0 ^= jArr[0];
        this.A1 ^= jArr[1];
        this.A2 ^= jArr[2];
        this.A3 ^= jArr[3];
    }

    private void MixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            long j5 = j3;
            int i5 = (int) ((this.A3 >> i) & 255);
            j |= ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255) << i;
            j4 |= ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255) << i;
            j2 |= ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255) << i;
            j3 = (((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255) << i) | j5;
        }
        this.A0 = j;
        this.A1 = j4;
        this.A2 = j2;
        this.A3 = j3;
    }

    private void ShiftRow(byte[] bArr) {
        this.A1 = shift(this.A1, bArr[1]);
        this.A2 = shift(this.A2, bArr[2]);
        this.A3 = shift(this.A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.A0 = applyS(this.A0, bArr);
        this.A1 = applyS(this.A1, bArr);
        this.A2 = applyS(this.A2, bArr);
        this.A3 = applyS(this.A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            j2 |= (bArr[(int) ((j >> i) & 255)] & UByte.MAX_VALUE) << i;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2;
        int i3 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 15, 4);
        int i4 = 4;
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else if (length != 256) {
            throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        } else {
            i = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i + 6 : (this.BC / 8) + 6;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < bArr.length) {
            bArr2[i6 % 4][i6 / 4] = bArr[i7];
            i6++;
            i7++;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < i && i9 < (this.ROUNDS + 1) * (this.BC / 8)) {
            int i10 = 0;
            while (i10 < i4) {
                int i11 = this.BC;
                long[] jArr2 = jArr[i9 / (i11 / 8)];
                jArr2[i10] = ((bArr2[i10][i8] & UByte.MAX_VALUE) << ((i9 * 8) % i11)) | jArr2[i10];
                i10++;
                i4 = 4;
            }
            i8++;
            i9++;
            i4 = 4;
        }
        int i12 = 0;
        while (i9 < (this.ROUNDS + 1) * (this.BC / i3)) {
            int i13 = i5;
            while (i13 < 4) {
                byte[] bArr3 = bArr2[i13];
                i13++;
                bArr3[i5] = (byte) (bArr3[i5] ^ S[bArr2[i13 % 4][i - 1] & UByte.MAX_VALUE]);
            }
            byte[] bArr4 = bArr2[i5];
            int i14 = i12 + 1;
            bArr4[i5] = (byte) (rcon[i12] ^ bArr4[i5]);
            int i15 = 1;
            if (i <= 6) {
                while (i15 < i) {
                    for (int i16 = i5; i16 < 4; i16++) {
                        byte[] bArr5 = bArr2[i16];
                        bArr5[i15] = (byte) (bArr5[i15] ^ bArr5[i15 - 1]);
                    }
                    i15++;
                }
            } else {
                while (true) {
                    i2 = 4;
                    if (i15 >= 4) {
                        break;
                    }
                    int i17 = i5;
                    while (i17 < i2) {
                        byte[] bArr6 = bArr2[i17];
                        bArr6[i15] = (byte) (bArr6[i15] ^ bArr6[i15 - 1]);
                        i17++;
                        i2 = 4;
                    }
                    i15++;
                }
                for (int i18 = i5; i18 < 4; i18++) {
                    byte[] bArr7 = bArr2[i18];
                    bArr7[4] = (byte) (bArr7[4] ^ S[bArr7[3] & UByte.MAX_VALUE]);
                }
                int i19 = 5;
                while (i19 < i) {
                    int i20 = i5;
                    while (i20 < i2) {
                        byte[] bArr8 = bArr2[i20];
                        bArr8[i19] = (byte) (bArr8[i19] ^ bArr8[i19 - 1]);
                        i20++;
                        i2 = 4;
                    }
                    i19++;
                    i2 = 4;
                }
            }
            int i21 = i5;
            while (i21 < i && i9 < (this.ROUNDS + 1) * (this.BC / i3)) {
                for (int i22 = i5; i22 < 4; i22++) {
                    int i23 = this.BC;
                    long[] jArr3 = jArr[i9 / (i23 / 8)];
                    jArr3[i22] = ((bArr2[i22][i21] & UByte.MAX_VALUE) << ((i9 * 8) % i23)) | jArr3[i22];
                }
                i21++;
                i9++;
                i5 = 0;
                i3 = 8;
            }
            i12 = i14;
            i5 = 0;
            i3 = 8;
        }
        return jArr;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & UByte.MAX_VALUE) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & UByte.MAX_VALUE) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + 199];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.BC; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) (this.A0 >> i2);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (this.A1 >> i2);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.A2 >> i2);
            i = i5 + 1;
            bArr[i5] = (byte) (this.A3 >> i2);
        }
    }

    private long shift(long j, int i) {
        return ((j << (this.BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        this.A0 = bArr[i] & UByte.MAX_VALUE;
        this.A1 = bArr[i2] & UByte.MAX_VALUE;
        this.A2 = bArr[i3] & UByte.MAX_VALUE;
        int i8 = i + 1 + 1 + 1 + 1;
        this.A3 = bArr[i4] & UByte.MAX_VALUE;
        for (int i9 = 8; i9 != this.BC; i9 += 8) {
            this.A0 |= (bArr[i8] & UByte.MAX_VALUE) << i9;
            this.A1 |= (bArr[i5] & UByte.MAX_VALUE) << i9;
            this.A2 |= (bArr[i6] & UByte.MAX_VALUE) << i9;
            i8 = i8 + 1 + 1 + 1 + 1;
            this.A3 |= (bArr[i7] & UByte.MAX_VALUE) << i9;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = generateWorkingKey(key);
        this.forEncryption = z;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey != null) {
            int i3 = this.BC;
            if ((i3 / 2) + i <= bArr.length) {
                if ((i3 / 2) + i2 <= bArr2.length) {
                    boolean z = this.forEncryption;
                    unpackBlock(bArr, i);
                    long[][] jArr = this.workingKey;
                    if (z) {
                        encryptBlock(jArr);
                    } else {
                        decryptBlock(jArr);
                    }
                    packBlock(bArr2, i2);
                    return this.BC / 2;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Rijndael engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
