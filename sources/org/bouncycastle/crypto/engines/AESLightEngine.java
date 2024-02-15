package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
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
import org.bouncycastle.util.Pack;
import org.tensorflow.lite.schema.BuiltinOperator;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private static final byte[] S = {BuiltinOptions.DensifyOptions, 124, 119, 123, -14, BuiltinOptions.HashtableOptions, BuiltinOptions.VarHandleOptions, -59, BuiltinOptions.SliceOptions, 1, BuiltinOptions.CallOnceOptions, BuiltinOptions.PadV2Options, -2, -41, -85, 118, -54, -126, -55, 125, -6, BuiltinOptions.QuantizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -16, -83, -44, -94, -81, -100, -92, BuiltinOptions.RandomOptions, -64, -73, -3, -109, BuiltinOptions.DequantizeOptions, 54, BuiltinOptions.LogicalNotOptions, -9, -52, 52, -91, -27, -15, BuiltinOptions.AssignVariableOptions, -40, 49, BuiltinOptions.MulOptions, 4, -57, BuiltinOptions.SplitOptions, -61, BuiltinOptions.BatchToSpaceNDOptions, -106, 5, -102, 7, BuiltinOptions.SkipGramOptions, ByteCompanionObject.MIN_VALUE, -30, -21, BuiltinOptions.MaximumMinimumOptions, -78, BuiltinOptions.DynamicUpdateSliceOptions, 9, -125, BuiltinOptions.GreaterOptions, BuiltinOptions.TransposeOptions, BuiltinOptions.ReducerOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.MatrixSetDiagOptions, -96, BuiltinOptions.AddNOptions, BuiltinOptions.PackOptions, -42, -77, BuiltinOptions.LessOptions, -29, BuiltinOptions.SelectOptions, -124, BuiltinOptions.GatherNdOptions, -47, 0, -19, 32, -4, -79, BuiltinOptions.HardSwishOptions, BuiltinOptions.Conv3DOptions, -53, -66, BuiltinOptions.ArgMinOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.MatrixDiagOptions, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.MirrorPadOptions, 51, -123, BuiltinOptions.BidirectionalSequenceLSTMOptions, -7, 2, ByteCompanionObject.MAX_VALUE, 80, BuiltinOptions.LogicalOrOptions, -97, -88, BuiltinOptions.ReverseV2Options, -93, BuiltinOptions.UnpackOptions, -113, -110, -99, BuiltinOptions.PowOptions, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BuiltinOptions.ExpOptions, 16, -1, -13, -46, -51, 12, BuiltinOptions.SpaceToDepthOptions, -20, BuiltinOptions.NonMaxSuppressionV4Options, -105, BuiltinOptions.FillOptions, BuiltinOptions.GatherOptions, -60, -89, 126, 61, BuiltinOptions.SegmentSumOptions, BuiltinOptions.WhileOptions, BuiltinOptions.SpaceToBatchNDOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.NonMaxSuppressionV5Options, -127, BuiltinOptions.SplitVOptions, -36, BuiltinOptions.TopKV2Options, BuiltinOptions.NegOptions, -112, -120, BuiltinOptions.BidirectionalSequenceRNNOptions, -18, -72, BuiltinOptions.EmbeddingLookupSparseOptions, -34, BuiltinOptions.DepthToSpaceOptions, 11, -37, -32, 50, BuiltinOptions.FakeQuantOptions, 10, BuiltinOptions.RangeOptions, 6, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.IfOptions, -62, -45, -84, BuiltinOptions.SelectV2Options, -111, -107, -28, 121, -25, -56, 55, BuiltinOptions.HashtableImportOptions, -115, -43, BuiltinOptions.AbsOptions, -87, BuiltinOptions.HashtableFindOptions, BuiltinOptions.RankOptions, -12, -22, BuiltinOptions.BatchMatMulOptions, 122, -82, 8, -70, 120, BuiltinOptions.CastOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.SubOptions, -90, -76, -58, -24, -35, BuiltinOptions.GeluOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.LeakyReluOptions, -67, -117, -118, BuiltinOptions.ReadVariableOptions, BuiltinOptions.LogicalAndOptions, -75, BuiltinOptions.CumsumOptions, BuiltinOptions.FloorModOptions, 3, -10, 14, BuiltinOptions.ScatterNdOptions, 53, BuiltinOptions.ReverseSequenceOptions, -71, -122, -63, BuiltinOptions.DivOptions, -98, -31, -8, -104, BuiltinOptions.ReshapeOptions, BuiltinOptions.Rfft2dOptions, -39, -114, -108, -101, BuiltinOptions.SqueezeOptions, -121, -23, -50, BuiltinOptions.WhereOptions, BuiltinOptions.ArgMaxOptions, -33, -116, -95, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, BuiltinOptions.SquareOptions, BuiltinOptions.BroadcastToOptions, BuiltinOptions.FloorDivOptions, -103, BuiltinOptions.GreaterEqualOptions, 15, -80, BuiltinOptions.CosOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.PadOptions};
    private static final byte[] Si = {BuiltinOptions.AddNOptions, 9, BuiltinOptions.Conv3DOptions, -43, BuiltinOptions.SliceOptions, 54, -91, BuiltinOptions.PowOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.UnpackOptions, -93, -98, -127, -13, -41, -5, 124, -29, BuiltinOptions.ArgMinOptions, -126, -101, BuiltinOptions.SelectOptions, -1, -121, 52, -114, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.FillOptions, -60, -34, -23, -53, BuiltinOptions.CosOptions, 123, -108, 50, -90, -62, BuiltinOptions.SplitOptions, 61, -18, BuiltinOptions.SquaredDifferenceOptions, -107, 11, BuiltinOptions.SquareOptions, -6, -61, BuiltinOptions.AbsOptions, 8, BuiltinOptions.LessEqualOptions, -95, BuiltinOptions.CumsumOptions, BuiltinOptions.ArgMaxOptions, -39, BuiltinOptions.LogSoftmaxOptions, -78, 118, BuiltinOptions.HardSwishOptions, -94, BuiltinOptions.RangeOptions, BuiltinOptions.HashtableImportOptions, -117, -47, BuiltinOptions.CastOptions, BuiltinOptions.RandomOptions, -8, -10, BuiltinOptions.SegmentSumOptions, -122, BuiltinOptions.BroadcastToOptions, -104, BuiltinOptions.PadOptions, -44, -92, BuiltinOptions.IfOptions, -52, BuiltinOptions.WhileOptions, BuiltinOptions.BatchMatMulOptions, -74, -110, BuiltinOptions.HashtableFindOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.FloorModOptions, 80, -3, -19, -71, -38, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.MulOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.ReverseSequenceOptions, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, BuiltinOptions.MatrixDiagOptions, 5, -72, -77, BuiltinOptions.BidirectionalSequenceLSTMOptions, 6, -48, BuiltinOptions.GreaterOptions, BuiltinOptions.SqueezeOptions, -113, -54, BuiltinOptions.LogicalNotOptions, 15, 2, -63, -81, -67, 3, 1, BuiltinOptions.SpaceToDepthOptions, -118, BuiltinOptions.HashtableOptions, BuiltinOptions.FakeQuantOptions, -111, BuiltinOptions.ReshapeOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.CallOnceOptions, -36, -22, -105, -14, -49, -50, -16, -76, -26, BuiltinOptions.BucketizeOptions, -106, -84, BuiltinOptions.GeluOptions, BuiltinOptions.TopKV2Options, -25, -83, 53, -123, -30, -7, 55, -24, BuiltinOptions.SubOptions, BuiltinOptions.DynamicUpdateSliceOptions, -33, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -15, BuiltinOptions.TransposeOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.DivOptions, BuiltinOptions.LessOptions, -59, -119, BuiltinOptions.VarHandleOptions, -73, BuiltinOptions.SelectV2Options, 14, -86, BuiltinOptions.BatchToSpaceNDOptions, -66, BuiltinOptions.ReducerOptions, -4, BuiltinOptions.RankOptions, BuiltinOptions.LogicalAndOptions, BuiltinOptions.LeakyReluOptions, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, BuiltinOptions.MatrixSetDiagOptions, -12, BuiltinOptions.SequenceRNNOptions, -35, -88, 51, -120, 7, -57, 49, -79, BuiltinOptions.SkipGramOptions, 16, BuiltinOptions.QuantizeOptions, BuiltinOptions.MaximumMinimumOptions, ByteCompanionObject.MIN_VALUE, -20, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.ReverseV2Options, ByteCompanionObject.MAX_VALUE, -87, BuiltinOptions.SpaceToBatchNDOptions, -75, BuiltinOptions.ResizeNearestNeighborOptions, 13, BuiltinOptions.GreaterEqualOptions, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, BuiltinOptions.PackOptions, BuiltinOptions.MirrorPadOptions, -82, BuiltinOptions.NegOptions, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.LogicalOrOptions, -125, BuiltinOptions.GatherNdOptions, -103, BuiltinOptions.ScatterNdOptions, BuiltinOptions.GatherOptions, BuiltinOptions.PadV2Options, 4, 126, -70, 119, -42, BuiltinOptions.DequantizeOptions, -31, BuiltinOptions.Rfft2dOptions, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.WhereOptions, BuiltinOptions.ExpOptions, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, BuiltinOperator.DYNAMIC_UPDATE_SLICE, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, BuiltinOperator.BROADCAST_ARGS};

    public AESLightEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity()));
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = i & m4;
        int i3 = i2 ^ (i2 >>> 1);
        return (i3 >>> 5) ^ (((m5 & i) << 2) ^ (i3 >>> 2));
    }

    private int bitsOfSecurity() {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            return 256;
        }
        return (iArr.length - 7) << 5;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = this.ROUNDS;
        int[] iArr2 = iArr[i3];
        int i4 = littleEndianToInt ^ iArr2[0];
        int i5 = littleEndianToInt2 ^ iArr2[1];
        int i6 = littleEndianToInt3 ^ iArr2[2];
        int i7 = i3 - 1;
        int i8 = littleEndianToInt4 ^ iArr2[3];
        while (true) {
            byte[] bArr3 = Si;
            int i9 = i4 & 255;
            if (i7 <= 1) {
                int inv_mcol = inv_mcol((((bArr3[i9] & UByte.MAX_VALUE) ^ ((bArr3[(i8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][0];
                int inv_mcol2 = inv_mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][1];
                int inv_mcol3 = inv_mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i8 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][2];
                int inv_mcol4 = inv_mcol((((bArr3[i8 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][3];
                int i10 = (((bArr3[inv_mcol & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol2 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions);
                int[] iArr3 = iArr[0];
                int i11 = i10 ^ iArr3[0];
                int i12 = ((((bArr3[inv_mcol2 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol3 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[1];
                int i13 = ((((bArr3[inv_mcol3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol2 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol4 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[2];
                int i14 = ((((bArr3[inv_mcol4 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol2 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[3];
                Pack.intToLittleEndian(i11, bArr2, i2 + 0);
                Pack.intToLittleEndian(i12, bArr2, i2 + 4);
                Pack.intToLittleEndian(i13, bArr2, i2 + 8);
                Pack.intToLittleEndian(i14, bArr2, i2 + 12);
                return;
            }
            int inv_mcol5 = inv_mcol((((bArr3[i9] & UByte.MAX_VALUE) ^ ((bArr3[(i8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][0];
            int inv_mcol6 = inv_mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][1];
            int inv_mcol7 = inv_mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i8 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][2];
            int i15 = i7 - 1;
            int inv_mcol8 = inv_mcol((((bArr3[i8 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][3];
            int inv_mcol9 = inv_mcol((((bArr3[inv_mcol5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol6 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i15][0];
            int inv_mcol10 = inv_mcol((((bArr3[inv_mcol6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol7 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i15][1];
            int i16 = i15 - 1;
            i8 = inv_mcol((((bArr3[inv_mcol8 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol7 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol5 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i15][3];
            i4 = inv_mcol9;
            i5 = inv_mcol10;
            i6 = inv_mcol((((bArr3[inv_mcol7 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol8 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i15][2];
            i7 = i16;
        }
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int[] iArr2 = iArr[0];
        int i3 = littleEndianToInt ^ iArr2[0];
        int i4 = littleEndianToInt2 ^ iArr2[1];
        int i5 = littleEndianToInt3 ^ iArr2[2];
        int i6 = littleEndianToInt4 ^ iArr2[3];
        int i7 = 1;
        while (i7 < this.ROUNDS - 1) {
            byte[] bArr3 = S;
            int mcol = mcol((((bArr3[i3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][0];
            int mcol2 = mcol((((bArr3[i4 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i3 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][1];
            int mcol3 = mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][2];
            int i8 = i7 + 1;
            int mcol4 = mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][3];
            int mcol5 = mcol((((bArr3[mcol & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol2 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(mcol3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol4 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i8][0];
            int mcol6 = mcol((((bArr3[mcol2 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(mcol4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i8][1];
            int i9 = i8 + 1;
            i6 = mcol((((bArr3[mcol4 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(mcol2 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol3 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i8][3];
            i3 = mcol5;
            i4 = mcol6;
            i5 = mcol((((bArr3[mcol3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(mcol >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol2 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i8][2];
            i7 = i9;
        }
        byte[] bArr4 = S;
        int mcol7 = mcol((((bArr4[i3 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i6 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][0];
        int mcol8 = mcol((((bArr4[i4 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i3 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][1];
        int mcol9 = mcol((((bArr4[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i4 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][2];
        int mcol10 = mcol((((bArr4[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i5 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr[i7][3];
        int[] iArr3 = iArr[i7 + 1];
        int i10 = ((((bArr4[mcol7 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol10 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[0];
        int i11 = ((((bArr4[mcol8 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol7 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[1];
        int i12 = ((((bArr4[mcol9 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol10 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol8 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[2];
        int i13 = ((((bArr4[mcol10 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol7 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol9 >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions)) ^ iArr3[3];
        Pack.intToLittleEndian(i10, bArr2, i2 + 0);
        Pack.intToLittleEndian(i11, bArr2, i2 + 4);
        Pack.intToLittleEndian(i12, bArr2, i2 + 8);
        Pack.intToLittleEndian(i13, bArr2, i2 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        int i2 = i + 6;
        this.ROUNDS = i2;
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, i2 + 1, 4);
        int i3 = 8;
        char c = 3;
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i4 = 1; i4 <= 10; i4++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i4 - 1];
                int[] iArr2 = iArr[i4];
                iArr2[0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr2[1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr2[2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr2[3] = littleEndianToInt4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i5 = 1;
            int i6 = 1;
            while (true) {
                int[] iArr3 = iArr[i5];
                iArr3[0] = littleEndianToInt9;
                iArr3[1] = littleEndianToInt10;
                int subWord = subWord(shift(littleEndianToInt10, 8)) ^ i6;
                int i7 = i6 << 1;
                int i8 = littleEndianToInt5 ^ subWord;
                int[] iArr4 = iArr[i5];
                iArr4[2] = i8;
                int i9 = littleEndianToInt6 ^ i8;
                iArr4[3] = i9;
                int i10 = littleEndianToInt7 ^ i9;
                int[] iArr5 = iArr[i5 + 1];
                iArr5[0] = i10;
                int i11 = littleEndianToInt8 ^ i10;
                iArr5[1] = i11;
                int i12 = littleEndianToInt9 ^ i11;
                iArr5[2] = i12;
                int i13 = littleEndianToInt10 ^ i12;
                iArr5[3] = i13;
                int subWord2 = subWord(shift(i13, 8)) ^ i7;
                i6 = i7 << 1;
                littleEndianToInt5 = i8 ^ subWord2;
                int[] iArr6 = iArr[i5 + 2];
                iArr6[0] = littleEndianToInt5;
                littleEndianToInt6 = i9 ^ littleEndianToInt5;
                iArr6[1] = littleEndianToInt6;
                littleEndianToInt7 = i10 ^ littleEndianToInt6;
                iArr6[2] = littleEndianToInt7;
                littleEndianToInt8 = i11 ^ littleEndianToInt7;
                iArr6[3] = littleEndianToInt8;
                i5 += 3;
                if (i5 >= 13) {
                    break;
                }
                littleEndianToInt9 = i12 ^ littleEndianToInt8;
                littleEndianToInt10 = i13 ^ littleEndianToInt9;
            }
        } else if (i != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i14 = 2;
            int i15 = 1;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, i3)) ^ i15;
                i15 <<= 1;
                littleEndianToInt11 ^= subWord3;
                int[] iArr7 = iArr[i14];
                iArr7[0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr7[1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr7[2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr7[c] = littleEndianToInt14;
                int i16 = i14 + 1;
                if (i16 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int[] iArr8 = iArr[i16];
                iArr8[0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr8[1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr8[2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr8[3] = littleEndianToInt18;
                i14 = i16 + 1;
                i3 = 8;
                c = 3;
            }
        }
        if (!z) {
            for (int i17 = 1; i17 < this.ROUNDS; i17++) {
                for (int i18 = 0; i18 < 4; i18++) {
                    int[] iArr9 = iArr[i17];
                    iArr9[i18] = inv_mcol(iArr9[i18]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << BuiltinOptions.BatchToSpaceNDOptions) | (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
        this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
        this.forEncryption = z;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr != null) {
            if (i <= bArr.length - 16) {
                if (i2 <= bArr2.length - 16) {
                    if (this.forEncryption) {
                        encryptBlock(bArr, i, bArr2, i2, iArr);
                    } else {
                        decryptBlock(bArr, i, bArr2, i2, iArr);
                    }
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
