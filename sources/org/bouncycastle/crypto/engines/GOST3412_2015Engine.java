package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[] PI = {-4, -18, -35, BuiltinOptions.ReshapeOptions, -49, BuiltinOptions.HashtableSizeOptions, 49, BuiltinOptions.PadOptions, -5, -60, -6, -38, BuiltinOptions.SplitOptions, -59, 4, BuiltinOptions.MirrorPadOptions, -23, 119, -16, -37, -109, BuiltinOptions.LessEqualOptions, -103, -70, BuiltinOptions.GatherOptions, 54, -15, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.EmbeddingLookupSparseOptions, -51, BuiltinOptions.NonMaxSuppressionV4Options, -63, -7, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.MatrixSetDiagOptions, -30, BuiltinOptions.IfOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.ExpOptions, -127, BuiltinOptions.SubOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.SquareOptions, -117, 1, -114, BuiltinOptions.SplitVOptions, 5, -124, 2, -82, -29, BuiltinOptions.Conv3DOptions, -113, -96, 6, 11, -19, -104, ByteCompanionObject.MAX_VALUE, -44, -45, BuiltinOptions.SequenceRNNOptions, -21, 52, BuiltinOptions.GreaterOptions, BuiltinOptions.ReverseV2Options, -22, -56, BuiltinOptions.FloorModOptions, -85, -14, BuiltinOptions.NegOptions, BuiltinOptions.BroadcastToOptions, -94, -3, BuiltinOptions.FakeQuantOptions, -50, -52, -75, BuiltinOptions.ReadVariableOptions, 14, BuiltinOptions.RankOptions, 8, 12, 118, BuiltinOptions.SkipGramOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.RandomOptions, BuiltinOptions.SpaceToDepthOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -100, -73, BuiltinOptions.WhileOptions, -121, BuiltinOptions.MulOptions, -95, -106, BuiltinOptions.LessOptions, 16, 123, -102, -57, -13, -111, 120, BuiltinOptions.VarHandleOptions, -99, -98, -78, -79, 50, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.SpaceToBatchNDOptions, 61, -1, 53, -118, 126, BuiltinOptions.HashtableImportOptions, BuiltinOptions.CosOptions, -58, ByteCompanionObject.MIN_VALUE, -61, -67, 13, BuiltinOptions.ReverseSequenceOptions, -33, -11, BuiltinOptions.LogSoftmaxOptions, -87, BuiltinOptions.LogicalAndOptions, -88, BuiltinOptions.ZerosLikeOptions, -55, -41, 121, -42, -10, 124, BuiltinOptions.TopKV2Options, -71, 3, -32, 15, -20, -34, 122, -108, -80, PSSSigner.TRAILER_IMPLICIT, -36, -24, BuiltinOptions.ArgMaxOptions, 80, BuiltinOptions.AbsOptions, 51, 10, BuiltinOptions.ResizeNearestNeighborOptions, -89, -105, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.BucketizeOptions, BuiltinOptions.SqueezeOptions, 0, BuiltinOptions.SelectV2Options, BuiltinOptions.FillOptions, BuiltinOptions.TransposeOptions, -72, BuiltinOptions.PowOptions, -126, BuiltinOptions.SegmentSumOptions, -97, BuiltinOptions.DequantizeOptions, BuiltinOptions.FloorDivOptions, -83, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, -110, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.WhereOptions, BuiltinOptions.SelectOptions, -116, -93, -91, 125, BuiltinOptions.Rfft2dOptions, -43, -107, BuiltinOptions.PackOptions, 7, BuiltinOptions.MatrixDiagOptions, -77, BuiltinOptions.UnpackOptions, -122, -84, BuiltinOptions.DivOptions, -9, BuiltinOptions.SliceOptions, 55, BuiltinOptions.HashtableOptions, -28, -120, -39, -25, -119, -31, BuiltinOptions.ReducerOptions, -125, BuiltinOptions.RangeOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.LogicalNotOptions, -8, -2, -115, BuiltinOptions.GatherNdOptions, -86, -112, -54, -40, -123, BuiltinOptions.ScatterNdOptions, 32, BuiltinOptions.AssignVariableOptions, BuiltinOptions.CallOnceOptions, -92, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.PadV2Options, 9, BuiltinOptions.HardSwishOptions, -53, -101, BuiltinOptions.CastOptions, -48, -66, -27, BuiltinOptions.HashtableFindOptions, BuiltinOptions.AddNOptions, BuiltinOptions.QuantizeOptions, -90, BuiltinOptions.GeluOptions, -46, -26, -12, -76, -64, -47, BuiltinOptions.CumsumOptions, -81, -62, BuiltinOptions.ArgMinOptions, BuiltinOptions.LeakyReluOptions, BuiltinOptions.DensifyOptions, -74};
    private static final byte[] inversePI = {-91, BuiltinOptions.GreaterEqualOptions, 50, -113, 14, BuiltinOptions.SliceOptions, BuiltinOptions.PowOptions, -64, BuiltinOptions.CosOptions, -26, -98, BuiltinOptions.ArgMinOptions, BuiltinOptions.WhereOptions, 126, BuiltinOptions.AddNOptions, -111, BuiltinOptions.SegmentSumOptions, 3, BuiltinOptions.ReverseSequenceOptions, BuiltinOptions.MatrixSetDiagOptions, BuiltinOptions.SubOptions, BuiltinOptions.NonMaxSuppressionV5Options, 7, BuiltinOptions.BatchToSpaceNDOptions, BuiltinOptions.ExpOptions, BuiltinOptions.RandomOptions, -88, -47, BuiltinOptions.LessOptions, -58, -92, BuiltinOptions.LogicalNotOptions, -32, BuiltinOptions.MaximumMinimumOptions, -115, 12, -126, -22, -82, -76, -102, BuiltinOptions.DensifyOptions, BuiltinOptions.RangeOptions, -27, BuiltinOptions.SquareOptions, -28, BuiltinOptions.MulOptions, -73, -56, 6, BuiltinOptions.ReadVariableOptions, -99, BuiltinOptions.FloorDivOptions, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.SpaceToBatchNDOptions, -55, -86, -4, BuiltinOptions.MirrorPadOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.NegOptions, BuiltinOptions.BucketizeOptions, -124, -43, -61, -81, BuiltinOptions.PadV2Options, -122, -89, -79, -78, BuiltinOptions.HardSwishOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, -45, -97, -3, -44, 15, -100, BuiltinOptions.SelectOptions, -101, BuiltinOptions.ZerosLikeOptions, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 121, -74, BuiltinOptions.GatherNdOptions, ByteCompanionObject.MAX_VALUE, -63, -16, BuiltinOptions.SplitOptions, -25, BuiltinOptions.CastOptions, BuiltinOptions.DepthToSpaceOptions, -75, BuiltinOptions.SqueezeOptions, -94, -33, -90, -2, -84, BuiltinOptions.TopKV2Options, -7, -30, BuiltinOptions.ResizeNearestNeighborOptions, PSSSigner.TRAILER_IMPLICIT, 53, -54, -18, 120, 5, BuiltinOptions.HashtableOptions, BuiltinOptions.ReverseV2Options, -31, BuiltinOptions.QuantizeOptions, -93, -14, BuiltinOptions.AssignVariableOptions, BuiltinOptions.RankOptions, BuiltinOptions.ReshapeOptions, BuiltinOptions.Conv3DOptions, -119, -108, BuiltinOptions.BatchMatMulOptions, -116, ByteSourceJsonBootstrapper.UTF8_BOM_2, 119, BuiltinOptions.LogicalOrOptions, 123, BuiltinOptions.ArgMaxOptions, -85, -46, 49, -34, -60, BuiltinOptions.NonMaxSuppressionV4Options, -52, -49, 118, BuiltinOptions.GreaterOptions, -72, -40, BuiltinOptions.LessEqualOptions, 54, -37, BuiltinOptions.Rfft2dOptions, -77, BuiltinOptions.EmbeddingLookupSparseOptions, -107, -66, BuiltinOptions.SelectV2Options, -95, BuiltinOptions.PackOptions, BuiltinOptions.PadOptions, BuiltinOptions.CumsumOptions, -23, BuiltinOptions.IfOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.HashtableImportOptions, -83, 55, BuiltinOptions.ScatterNdOptions, BuiltinOptions.LeakyReluOptions, -71, -29, -70, -15, -96, -123, -125, -38, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -59, -80, 51, -6, -106, BuiltinOptions.VarHandleOptions, BuiltinOptions.HashtableSizeOptions, -62, -10, 80, -1, BuiltinOptions.WhileOptions, -87, -114, BuiltinOptions.GatherOptions, BuiltinOptions.ReducerOptions, -105, 125, -20, BuiltinOptions.MatrixDiagOptions, -9, BuiltinOptions.SequenceRNNOptions, -5, 124, 9, 13, 122, BuiltinOptions.CallOnceOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, -121, -36, -24, BuiltinOptions.SplitVOptions, BuiltinOptions.DivOptions, BuiltinOptions.AbsOptions, 4, -21, -8, -13, BuiltinOptions.LogicalAndOptions, 61, -67, -118, -120, -35, -51, 11, BuiltinOptions.SpaceToDepthOptions, -104, 2, -109, ByteCompanionObject.MIN_VALUE, -112, -48, BuiltinOptions.LogSoftmaxOptions, 52, -53, -19, -12, -50, -103, 16, BuiltinOptions.FillOptions, BuiltinOptions.UnpackOptions, -110, BuiltinOptions.FakeQuantOptions, 1, BuiltinOptions.DequantizeOptions, BuiltinOptions.SkipGramOptions, BuiltinOptions.TransposeOptions, BuiltinOptions.FloorModOptions, BuiltinOptions.BroadcastToOptions, -11, -127, -117, -57, -42, 32, 10, 8, 0, BuiltinOptions.SquaredDifferenceOptions, -41, BuiltinOptions.GeluOptions};
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, 16, -62, -64, 1, -5, 1, -64, -62, 16, -123, 32, -108, 1};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = 32 / 2;
    private byte[][] subKeys = null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    private void C(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        L(bArr);
    }

    private void F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] LSX = LSX(bArr, bArr2);
        X(LSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(LSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i, bArr4, 0, 16);
        int i3 = 9;
        if (this.forEncryption) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArr4 = Arrays.copyOf(LSX(this.subKeys[i4], bArr4), 16);
            }
            X(bArr4, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i3 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(XSL(bArr3[i3], bArr4), 16);
                i3--;
            }
            X(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, i2, 16);
    }

    private void L(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        S(copyOf);
        L(copyOf);
        return copyOf;
    }

    private void R(byte[] bArr) {
        byte l = l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = l;
    }

    private void S(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = PI[unsignedByte(bArr[i])];
        }
    }

    private void X(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        inverseL(copyOf);
        inverseS(copyOf);
        return copyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10];
        for (int i2 = 0; i2 < 10; i2++) {
            this.subKeys[i2] = new byte[this.SUB_LENGTH];
        }
        int i3 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        while (true) {
            i = this.SUB_LENGTH;
            if (i4 >= i) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[i4];
            bArr2[i4] = b;
            bArr5[i4] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[i + i4];
            bArr3[i4] = b2;
            bArr6[i4] = b2;
            i4++;
        }
        byte[] bArr7 = new byte[i];
        for (int i5 = 1; i5 < 5; i5++) {
            for (int i6 = 1; i6 <= 8; i6++) {
                C(bArr7, ((i5 - 1) * 8) + i6);
                F(bArr7, bArr2, bArr3);
            }
            int i7 = i5 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[i7], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[i7 + 1], 0, this.SUB_LENGTH);
        }
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = kuz_mul_gf256_slow((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte l = l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = l;
    }

    private void inverseS(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = inversePI[unsignedByte(bArr[i])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & ByteCompanionObject.MIN_VALUE);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    private byte l(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[i])][unsignedByte(this.lFactors[i])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & UByte.MAX_VALUE;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters != null) {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.subKeys != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    GOST3412_2015Func(bArr, i, bArr2, i2);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("GOST3412_2015 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
