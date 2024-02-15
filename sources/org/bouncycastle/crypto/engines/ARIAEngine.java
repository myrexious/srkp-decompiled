package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.lang.reflect.Array;
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
import org.bouncycastle.util.encoders.Hex;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class ARIAEngine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[][] C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = {BuiltinOptions.DensifyOptions, 124, 119, 123, -14, BuiltinOptions.HashtableOptions, BuiltinOptions.VarHandleOptions, -59, BuiltinOptions.SliceOptions, 1, BuiltinOptions.CallOnceOptions, BuiltinOptions.PadV2Options, -2, -41, -85, 118, -54, -126, -55, 125, -6, BuiltinOptions.QuantizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -16, -83, -44, -94, -81, -100, -92, BuiltinOptions.RandomOptions, -64, -73, -3, -109, BuiltinOptions.DequantizeOptions, 54, BuiltinOptions.LogicalNotOptions, -9, -52, 52, -91, -27, -15, BuiltinOptions.AssignVariableOptions, -40, 49, BuiltinOptions.MulOptions, 4, -57, BuiltinOptions.SplitOptions, -61, BuiltinOptions.BatchToSpaceNDOptions, -106, 5, -102, 7, BuiltinOptions.SkipGramOptions, ByteCompanionObject.MIN_VALUE, -30, -21, BuiltinOptions.MaximumMinimumOptions, -78, BuiltinOptions.DynamicUpdateSliceOptions, 9, -125, BuiltinOptions.GreaterOptions, BuiltinOptions.TransposeOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.MatrixSetDiagOptions, -96, BuiltinOptions.AddNOptions, BuiltinOptions.PackOptions, -42, -77, BuiltinOptions.LessOptions, -29, BuiltinOptions.SelectOptions, -124, BuiltinOptions.GatherNdOptions, -47, 0, -19, 32, -4, -79, BuiltinOptions.HardSwishOptions, BuiltinOptions.Conv3DOptions, -53, -66, BuiltinOptions.ArgMinOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.MatrixDiagOptions, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.MirrorPadOptions, 51, -123, BuiltinOptions.BidirectionalSequenceLSTMOptions, -7, 2, ByteCompanionObject.MAX_VALUE, 80, BuiltinOptions.LogicalOrOptions, -97, -88, BuiltinOptions.ReverseV2Options, -93, BuiltinOptions.UnpackOptions, -113, -110, -99, BuiltinOptions.PowOptions, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BuiltinOptions.ExpOptions, 16, -1, -13, -46, -51, 12, BuiltinOptions.SpaceToDepthOptions, -20, BuiltinOptions.NonMaxSuppressionV4Options, -105, BuiltinOptions.FillOptions, BuiltinOptions.GatherOptions, -60, -89, 126, 61, BuiltinOptions.SegmentSumOptions, BuiltinOptions.WhileOptions, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.NonMaxSuppressionV5Options, -127, BuiltinOptions.SplitVOptions, -36, BuiltinOptions.TopKV2Options, BuiltinOptions.NegOptions, -112, -120, BuiltinOptions.BidirectionalSequenceRNNOptions, -18, -72, BuiltinOptions.EmbeddingLookupSparseOptions, -34, BuiltinOptions.DepthToSpaceOptions, 11, -37, -32, 50, BuiltinOptions.FakeQuantOptions, 10, BuiltinOptions.RangeOptions, 6, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.IfOptions, -62, -45, -84, BuiltinOptions.SelectV2Options, -111, -107, -28, 121, -25, -56, 55, BuiltinOptions.HashtableImportOptions, -115, -43, BuiltinOptions.AbsOptions, -87, BuiltinOptions.HashtableFindOptions, BuiltinOptions.RankOptions, -12, -22, BuiltinOptions.BatchMatMulOptions, 122, -82, 8, -70, 120, BuiltinOptions.CastOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.SubOptions, -90, -76, -58, -24, -35, BuiltinOptions.GeluOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.LeakyReluOptions, -67, -117, -118, BuiltinOptions.ReadVariableOptions, BuiltinOptions.LogicalAndOptions, -75, BuiltinOptions.CumsumOptions, BuiltinOptions.FloorModOptions, 3, -10, 14, BuiltinOptions.ScatterNdOptions, 53, BuiltinOptions.ReverseSequenceOptions, -71, -122, -63, BuiltinOptions.DivOptions, -98, -31, -8, -104, BuiltinOptions.ReshapeOptions, BuiltinOptions.Rfft2dOptions, -39, -114, -108, -101, BuiltinOptions.SqueezeOptions, -121, -23, -50, BuiltinOptions.WhereOptions, BuiltinOptions.ArgMaxOptions, -33, -116, -95, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, BuiltinOptions.SquareOptions, BuiltinOptions.BroadcastToOptions, BuiltinOptions.FloorDivOptions, -103, BuiltinOptions.GreaterEqualOptions, 15, -80, BuiltinOptions.CosOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.PadOptions};
    private static final byte[] SB2_sbox = {-30, BuiltinOptions.AbsOptions, BuiltinOptions.CosOptions, -4, -108, -62, BuiltinOptions.ResizeNearestNeighborOptions, -52, BuiltinOptions.SelectV2Options, 13, BuiltinOptions.Conv3DOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.MirrorPadOptions, -117, -47, BuiltinOptions.DepthToSpaceOptions, -6, BuiltinOptions.SegmentSumOptions, -53, -76, -105, -66, BuiltinOptions.PadV2Options, PSSSigner.TRAILER_IMPLICIT, 119, BuiltinOptions.LessEqualOptions, 3, -45, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.QuantizeOptions, -63, BuiltinOptions.DivOptions, 6, BuiltinOptions.FloorDivOptions, BuiltinOptions.HashtableOptions, BuiltinOptions.WhereOptions, -16, -103, BuiltinOptions.Rfft2dOptions, -22, -100, BuiltinOptions.BatchToSpaceNDOptions, -82, BuiltinOptions.DensifyOptions, -33, -25, ByteSourceJsonBootstrapper.UTF8_BOM_2, 0, BuiltinOptions.BucketizeOptions, BuiltinOptions.CumsumOptions, -5, -106, BuiltinOptions.SquaredDifferenceOptions, -123, -28, BuiltinOptions.FakeQuantOptions, 9, BuiltinOptions.BidirectionalSequenceLSTMOptions, -86, 15, -18, 16, -21, BuiltinOptions.GreaterEqualOptions, ByteCompanionObject.MAX_VALUE, -12, BuiltinOptions.LessOptions, -84, -49, -83, -111, -115, 120, -56, -107, -7, BuiltinOptions.SelectOptions, -50, -51, 8, 122, -120, BuiltinOptions.PowOptions, BuiltinOptions.IfOptions, -125, BuiltinOptions.NegOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -37, -72, -57, -109, -92, BuiltinOptions.SkipGramOptions, BuiltinOptions.GatherNdOptions, -1, -121, 14, 49, 54, BuiltinOptions.ExpOptions, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.FloorModOptions, 1, -114, 55, BuiltinOptions.GeluOptions, 50, -54, -23, -79, -73, -85, 12, -41, -60, BuiltinOptions.RankOptions, BuiltinOptions.SquareOptions, BuiltinOptions.DequantizeOptions, 7, -104, BuiltinOptions.NonMaxSuppressionV5Options, -39, -74, -71, BuiltinOptions.ReshapeOptions, BuiltinOptions.UnpackOptions, -20, 32, -116, -67, -96, -55, -124, 4, BuiltinOptions.RangeOptions, BuiltinOptions.SplitOptions, -15, BuiltinOptions.SplitVOptions, 80, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.SpaceToDepthOptions, -36, -40, -64, -98, BuiltinOptions.ReverseSequenceOptions, -29, -61, 123, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.PackOptions, 2, -113, BuiltinOptions.LogicalAndOptions, -24, BuiltinOptions.CastOptions, -110, -27, BuiltinOptions.MulOptions, -35, -3, BuiltinOptions.GatherOptions, -87, ByteSourceJsonBootstrapper.UTF8_BOM_3, -44, -102, 126, -59, BuiltinOptions.ArgMinOptions, BuiltinOptions.CallOnceOptions, -2, 118, -99, BuiltinOptions.ZerosLikeOptions, -89, -31, -48, -11, BuiltinOptions.BroadcastToOptions, -14, BuiltinOptions.ReducerOptions, 52, BuiltinOptions.ReadVariableOptions, 5, -93, -118, -43, 121, -122, -88, BuiltinOptions.SliceOptions, -58, BuiltinOptions.ReverseV2Options, BuiltinOptions.LeakyReluOptions, BuiltinOptions.SqueezeOptions, -90, BuiltinOptions.MaximumMinimumOptions, -10, 53, -46, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.PadOptions, -126, BuiltinOptions.NonMaxSuppressionV4Options, -38, -26, BuiltinOptions.DynamicUpdateSliceOptions, -94, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.GreaterOptions, -78, BuiltinOptions.SubOptions, -97, BuiltinOptions.WhileOptions, BuiltinOptions.VarHandleOptions, ByteCompanionObject.MIN_VALUE, 10, BuiltinOptions.RandomOptions, BuiltinOptions.FillOptions, -101, BuiltinOptions.HashtableFindOptions, -112, 11, BuiltinOptions.HardSwishOptions, 51, 125, BuiltinOptions.MatrixSetDiagOptions, BuiltinOptions.AddNOptions, -13, BuiltinOptions.ScatterNdOptions, -95, -9, -80, -42, BuiltinOptions.LogicalNotOptions, 124, BuiltinOptions.HashtableImportOptions, -19, BuiltinOptions.EmbeddingLookupSparseOptions, -32, -91, 61, BuiltinOptions.TopKV2Options, -77, -8, -119, -34, BuiltinOptions.AssignVariableOptions, BuiltinOptions.TransposeOptions, -81, -70, -75, -127};
    private static final byte[] SB3_sbox = {BuiltinOptions.AddNOptions, 9, BuiltinOptions.Conv3DOptions, -43, BuiltinOptions.SliceOptions, 54, -91, BuiltinOptions.PowOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.UnpackOptions, -93, -98, -127, -13, -41, -5, 124, -29, BuiltinOptions.ArgMinOptions, -126, -101, BuiltinOptions.SelectOptions, -1, -121, 52, -114, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.FillOptions, -60, -34, -23, -53, BuiltinOptions.CosOptions, 123, -108, 50, -90, -62, BuiltinOptions.SplitOptions, 61, -18, BuiltinOptions.SquaredDifferenceOptions, -107, 11, BuiltinOptions.SquareOptions, -6, -61, BuiltinOptions.AbsOptions, 8, BuiltinOptions.LessEqualOptions, -95, BuiltinOptions.CumsumOptions, BuiltinOptions.ArgMaxOptions, -39, BuiltinOptions.LogSoftmaxOptions, -78, 118, BuiltinOptions.HardSwishOptions, -94, BuiltinOptions.RangeOptions, BuiltinOptions.HashtableImportOptions, -117, -47, BuiltinOptions.CastOptions, BuiltinOptions.RandomOptions, -8, -10, BuiltinOptions.SegmentSumOptions, -122, BuiltinOptions.BroadcastToOptions, -104, BuiltinOptions.PadOptions, -44, -92, BuiltinOptions.IfOptions, -52, BuiltinOptions.WhileOptions, BuiltinOptions.BatchMatMulOptions, -74, -110, BuiltinOptions.HashtableFindOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.FloorModOptions, 80, -3, -19, -71, -38, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.MulOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.ReverseSequenceOptions, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, BuiltinOptions.MatrixDiagOptions, 5, -72, -77, BuiltinOptions.BidirectionalSequenceLSTMOptions, 6, -48, BuiltinOptions.GreaterOptions, BuiltinOptions.SqueezeOptions, -113, -54, BuiltinOptions.LogicalNotOptions, 15, 2, -63, -81, -67, 3, 1, BuiltinOptions.SpaceToDepthOptions, -118, BuiltinOptions.HashtableOptions, BuiltinOptions.FakeQuantOptions, -111, BuiltinOptions.ReshapeOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.CallOnceOptions, -36, -22, -105, -14, -49, -50, -16, -76, -26, BuiltinOptions.BucketizeOptions, -106, -84, BuiltinOptions.GeluOptions, BuiltinOptions.TopKV2Options, -25, -83, 53, -123, -30, -7, 55, -24, BuiltinOptions.SubOptions, BuiltinOptions.DynamicUpdateSliceOptions, -33, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -15, BuiltinOptions.TransposeOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.DivOptions, BuiltinOptions.LessOptions, -59, -119, BuiltinOptions.VarHandleOptions, -73, BuiltinOptions.SelectV2Options, 14, -86, BuiltinOptions.BatchToSpaceNDOptions, -66, BuiltinOptions.ReducerOptions, -4, BuiltinOptions.RankOptions, BuiltinOptions.LogicalAndOptions, BuiltinOptions.LeakyReluOptions, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, BuiltinOptions.MatrixSetDiagOptions, -12, BuiltinOptions.SequenceRNNOptions, -35, -88, 51, -120, 7, -57, 49, -79, BuiltinOptions.SkipGramOptions, 16, BuiltinOptions.QuantizeOptions, BuiltinOptions.MaximumMinimumOptions, ByteCompanionObject.MIN_VALUE, -20, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.ReverseV2Options, ByteCompanionObject.MAX_VALUE, -87, BuiltinOptions.SpaceToBatchNDOptions, -75, BuiltinOptions.ResizeNearestNeighborOptions, 13, BuiltinOptions.GreaterEqualOptions, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, BuiltinOptions.PackOptions, BuiltinOptions.MirrorPadOptions, -82, BuiltinOptions.NegOptions, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.LogicalOrOptions, -125, BuiltinOptions.GatherNdOptions, -103, BuiltinOptions.ScatterNdOptions, BuiltinOptions.GatherOptions, BuiltinOptions.PadV2Options, 4, 126, -70, 119, -42, BuiltinOptions.DequantizeOptions, -31, BuiltinOptions.Rfft2dOptions, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.WhereOptions, BuiltinOptions.ExpOptions, 12, 125};
    private static final byte[] SB4_sbox = {BuiltinOptions.SliceOptions, BuiltinOptions.BroadcastToOptions, -103, BuiltinOptions.ReducerOptions, -121, -71, BuiltinOptions.ExpOptions, 120, 80, BuiltinOptions.ArgMinOptions, -37, -31, BuiltinOptions.RandomOptions, 9, BuiltinOptions.SelectV2Options, BuiltinOptions.LogicalOrOptions, BuiltinOptions.LogicalAndOptions, 126, BuiltinOptions.DepthToSpaceOptions, -114, -15, -96, -52, -93, BuiltinOptions.NegOptions, BuiltinOptions.DivOptions, -5, -74, -42, 32, -60, -115, -127, BuiltinOptions.BatchMatMulOptions, -11, -119, -53, -99, 119, -58, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.RankOptions, BuiltinOptions.GatherOptions, -44, BuiltinOptions.UnpackOptions, BuiltinOptions.TransposeOptions, BuiltinOptions.MirrorPadOptions, -64, BuiltinOptions.DensifyOptions, BuiltinOptions.HashtableFindOptions, -29, -73, -56, BuiltinOptions.SegmentSumOptions, BuiltinOptions.Conv3DOptions, BuiltinOptions.GatherNdOptions, -86, BuiltinOptions.PowOptions, -104, 12, -12, -101, -19, ByteCompanionObject.MAX_VALUE, BuiltinOptions.TopKV2Options, 118, -81, -35, BuiltinOptions.FakeQuantOptions, 11, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.CallOnceOptions, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, BuiltinOptions.NonMaxSuppressionV4Options, 2, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.DynamicUpdateSliceOptions, -109, BuiltinOptions.CumsumOptions, BuiltinOptions.SqueezeOptions, -27, -30, BuiltinOptions.CosOptions, -40, 16, -50, 122, -24, 8, BuiltinOptions.GreaterOptions, BuiltinOptions.SkipGramOptions, -105, 50, -85, -76, BuiltinOptions.MaximumMinimumOptions, 10, BuiltinOptions.SplitOptions, -33, ByteSourceJsonBootstrapper.UTF8_BOM_1, -54, -39, -72, -6, -36, 49, BuiltinOptions.HashtableOptions, -47, -83, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.RangeOptions, -67, BuiltinOptions.ReverseV2Options, -106, -18, -28, -88, BuiltinOptions.FloorDivOptions, -38, -1, -51, BuiltinOptions.WhereOptions, -122, 54, -66, BuiltinOptions.ScatterNdOptions, BuiltinOptions.AddNOptions, -8, ByteSourceJsonBootstrapper.UTF8_BOM_2, 14, -126, BuiltinOptions.FloorModOptions, BuiltinOptions.Rfft2dOptions, -102, -32, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -98, BuiltinOptions.IfOptions, 4, BuiltinOptions.LeakyReluOptions, 52, BuiltinOptions.MulOptions, 121, BuiltinOptions.DequantizeOptions, -89, -34, BuiltinOptions.LessOptions, -82, -110, -41, -124, -23, -46, -70, BuiltinOptions.WhileOptions, -13, -59, -80, ByteSourceJsonBootstrapper.UTF8_BOM_3, -92, BuiltinOptions.PackOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.FillOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.PadV2Options, -4, -21, BuiltinOptions.VarHandleOptions, -43, -10, BuiltinOptions.EmbeddingLookupSparseOptions, -2, 124, BuiltinOptions.ReadVariableOptions, BuiltinOptions.MatrixSetDiagOptions, 125, -3, BuiltinOptions.SelectOptions, BuiltinOptions.BatchToSpaceNDOptions, -125, BuiltinOptions.PadOptions, -91, -111, BuiltinOptions.SequenceRNNOptions, 5, -107, BuiltinOptions.GeluOptions, -87, -63, BuiltinOptions.HardSwishOptions, BuiltinOptions.ResizeNearestNeighborOptions, -123, BuiltinOptions.HashtableImportOptions, BuiltinOptions.SpaceToDepthOptions, 7, BuiltinOptions.SplitVOptions, BuiltinOptions.AbsOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, -78, 15, -55, BuiltinOptions.SubOptions, -90, PSSSigner.TRAILER_IMPLICIT, -20, BuiltinOptions.BucketizeOptions, -112, 123, -49, BuiltinOptions.QuantizeOptions, -113, -95, -7, BuiltinOptions.GreaterEqualOptions, -14, -79, 0, -108, 55, -97, -48, BuiltinOptions.LessEqualOptions, -100, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.ArgMaxOptions, BuiltinOptions.LogicalNotOptions, ByteCompanionObject.MIN_VALUE, -16, 61, -45, BuiltinOptions.CastOptions, -118, -75, -25, BuiltinOptions.SquareOptions, -77, -57, -22, -9, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.ReshapeOptions, 51, 3, -94, -84, BuiltinOptions.NonMaxSuppressionV5Options};
    boolean forEncryption;
    private byte[][] roundKeys;

    public ARIAEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
    }

    protected static void A(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        byte b7 = bArr[6];
        byte b8 = bArr[7];
        byte b9 = bArr[8];
        byte b10 = bArr[9];
        byte b11 = bArr[10];
        byte b12 = bArr[11];
        byte b13 = bArr[12];
        byte b14 = bArr[13];
        byte b15 = bArr[14];
        byte b16 = bArr[15];
        bArr[0] = (byte) ((((((b4 ^ b5) ^ b7) ^ b9) ^ b10) ^ b14) ^ b15);
        bArr[1] = (byte) ((((((b3 ^ b6) ^ b8) ^ b9) ^ b10) ^ b13) ^ b16);
        bArr[2] = (byte) ((((((b2 ^ b5) ^ b7) ^ b11) ^ b12) ^ b13) ^ b16);
        bArr[3] = (byte) ((((((b ^ b6) ^ b8) ^ b11) ^ b12) ^ b14) ^ b15);
        int i = b ^ b3;
        bArr[4] = (byte) (((((i ^ b6) ^ b9) ^ b12) ^ b15) ^ b16);
        int i2 = b2 ^ b4;
        bArr[5] = (byte) (((((i2 ^ b5) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[6] = (byte) (((((i ^ b8) ^ b10) ^ b11) ^ b13) ^ b14);
        bArr[7] = (byte) (((((i2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b14);
        int i3 = b ^ b2;
        bArr[8] = (byte) (((((i3 ^ b5) ^ b8) ^ b11) ^ b14) ^ b16);
        bArr[9] = (byte) (((((i3 ^ b6) ^ b7) ^ b12) ^ b13) ^ b15);
        int i4 = b3 ^ b4;
        bArr[10] = (byte) (((((i4 ^ b6) ^ b7) ^ b9) ^ b14) ^ b16);
        bArr[11] = (byte) (((((i4 ^ b5) ^ b8) ^ b10) ^ b13) ^ b15);
        int i5 = b2 ^ b3;
        bArr[12] = (byte) (((((i5 ^ b7) ^ b8) ^ b10) ^ b12) ^ b13);
        int i6 = b ^ b4;
        bArr[13] = (byte) (((((i6 ^ b7) ^ b8) ^ b9) ^ b11) ^ b14);
        bArr[14] = (byte) (((((i6 ^ b5) ^ b6) ^ b10) ^ b12) ^ b15);
        bArr[15] = (byte) (((((i5 ^ b5) ^ b6) ^ b9) ^ b11) ^ b16);
    }

    protected static void FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        A(bArr);
    }

    protected static void FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        A(bArr);
    }

    protected static byte SB1(byte b) {
        return SB1_sbox[b & UByte.MAX_VALUE];
    }

    protected static byte SB2(byte b) {
        return SB2_sbox[b & UByte.MAX_VALUE];
    }

    protected static byte SB3(byte b) {
        return SB3_sbox[b & UByte.MAX_VALUE];
    }

    protected static byte SB4(byte b) {
        return SB4_sbox[b & UByte.MAX_VALUE];
    }

    protected static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    protected static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    private int bitsOfSecurity() {
        byte[][] bArr = this.roundKeys;
        if (bArr.length > 13) {
            return bArr.length > 15 ? 256 : 192;
        }
        return 128;
    }

    protected static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = (length >>> 3) - 2;
        byte[][] bArr2 = C;
        byte[] bArr3 = bArr2[i];
        byte[] bArr4 = bArr2[(i + 1) % 3];
        byte[] bArr5 = bArr2[(i + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int i2 = (i * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance(Byte.TYPE, i2 + 1, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (i2 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (i2 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int i3 = 1; i3 < i2; i3++) {
                A(bArr12[i3]);
            }
        }
        return bArr12;
    }

    protected static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        int i4 = 8 - i3;
        int i5 = bArr3[15 - i2] & 255;
        int i6 = 0;
        while (i6 < 16) {
            int i7 = bArr3[(i6 - i2) & 15] & 255;
            bArr[i6] = (byte) (((i5 << i4) | (i7 >>> i3)) ^ (bArr2[i6] & 255));
            i6++;
            i5 = i7;
        }
    }

    protected static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int i = length / 2;
        int i2 = length - 1;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2 - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = bArr2;
        }
    }

    protected static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
        }
        this.forEncryption = z;
        this.roundKeys = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.roundKeys != null) {
            if (i <= bArr.length - 16) {
                if (i2 <= bArr2.length - 16) {
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(bArr, i, bArr3, 0, 16);
                    int length = this.roundKeys.length - 3;
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = i3 + 1;
                        FO(bArr3, this.roundKeys[i3]);
                        FE(bArr3, this.roundKeys[i4]);
                        i3 = i4 + 1;
                    }
                    int i5 = i3 + 1;
                    FO(bArr3, this.roundKeys[i3]);
                    xor(bArr3, this.roundKeys[i5]);
                    SL2(bArr3);
                    xor(bArr3, this.roundKeys[i5 + 1]);
                    System.arraycopy(bArr3, 0, bArr2, i2, 16);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("ARIA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
