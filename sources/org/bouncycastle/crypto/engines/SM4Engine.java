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
import org.bouncycastle.util.Pack;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private final int[] X = new int[4];
    private int[] rk;
    private static final byte[] Sbox = {-42, -112, -23, -2, -52, -31, 61, -73, BuiltinOptions.PadOptions, -74, BuiltinOptions.EmbeddingLookupSparseOptions, -62, BuiltinOptions.ArgMaxOptions, -5, BuiltinOptions.GreaterOptions, 5, BuiltinOptions.PadV2Options, BuiltinOptions.CallOnceOptions, -102, 118, BuiltinOptions.NegOptions, -66, 4, -61, -86, BuiltinOptions.FillOptions, BuiltinOptions.SpaceToDepthOptions, BuiltinOptions.DequantizeOptions, BuiltinOptions.RangeOptions, -122, 6, -103, -100, BuiltinOptions.SquareOptions, 80, -12, -111, ByteSourceJsonBootstrapper.UTF8_BOM_1, -104, 122, 51, BuiltinOptions.CosOptions, 11, BuiltinOptions.ZerosLikeOptions, -19, -49, -84, BuiltinOptions.SelectV2Options, -28, -77, BuiltinOptions.SubOptions, -87, -55, 8, -24, -107, ByteCompanionObject.MIN_VALUE, -33, -108, -6, BuiltinOptions.DynamicUpdateSliceOptions, -113, BuiltinOptions.LogicalNotOptions, -90, BuiltinOptions.UnidirectionalSequenceLSTMOptions, 7, -89, -4, -13, BuiltinOptions.BucketizeOptions, BuiltinOptions.GatherOptions, -70, -125, BuiltinOptions.QuantizeOptions, BuiltinOptions.LogicalOrOptions, BuiltinOptions.SpaceToBatchNDOptions, -26, -123, BuiltinOptions.SplitVOptions, -88, BuiltinOptions.BroadcastToOptions, BuiltinOptions.HashtableOptions, -127, -78, BuiltinOptions.AssignVariableOptions, BuiltinOptions.SegmentSumOptions, -38, -117, -8, -21, 15, BuiltinOptions.LeakyReluOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.RankOptions, -99, 53, BuiltinOptions.SqueezeOptions, BuiltinOptions.LogSoftmaxOptions, 14, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.MatrixDiagOptions, -47, -94, BuiltinOptions.CastOptions, BuiltinOptions.TopKV2Options, 124, BuiltinOptions.PackOptions, 1, BuiltinOptions.ExpOptions, 120, -121, -44, 0, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.ReverseSequenceOptions, -97, -45, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.AddNOptions, BuiltinOptions.SquaredDifferenceOptions, 54, 2, -25, -96, -60, -56, -98, -22, ByteSourceJsonBootstrapper.UTF8_BOM_3, -118, -46, BuiltinOptions.UnpackOptions, -57, BuiltinOptions.PowOptions, -75, -93, -9, -14, -50, -7, BuiltinOptions.ScatterNdOptions, BuiltinOptions.MulOptions, -95, -32, -82, BuiltinOptions.WhileOptions, -92, -101, 52, BuiltinOptions.TransposeOptions, BuiltinOptions.WhereOptions, -83, -109, 50, BuiltinOptions.SliceOptions, -11, -116, -79, -29, BuiltinOptions.DivOptions, -10, -30, BuiltinOptions.LessEqualOptions, -126, BuiltinOptions.CumsumOptions, -54, BuiltinOptions.NonMaxSuppressionV5Options, -64, BuiltinOptions.LessOptions, BuiltinOptions.SplitOptions, -85, 13, BuiltinOptions.GatherNdOptions, BuiltinOptions.AbsOptions, BuiltinOptions.VarHandleOptions, -43, -37, 55, BuiltinOptions.BidirectionalSequenceLSTMOptions, -34, -3, -114, BuiltinOptions.SelectOptions, 3, -1, BuiltinOptions.Conv3DOptions, BuiltinOptions.RandomOptions, BuiltinOptions.HashtableImportOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.HardSwishOptions, BuiltinOptions.ReverseV2Options, -115, BuiltinOptions.ReducerOptions, -81, -110, ByteSourceJsonBootstrapper.UTF8_BOM_2, -35, PSSSigner.TRAILER_IMPLICIT, ByteCompanionObject.MAX_VALUE, BuiltinOptions.ReshapeOptions, -39, BuiltinOptions.IfOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.SequenceRNNOptions, 16, BuiltinOptions.MatrixSetDiagOptions, -40, 10, -63, 49, -120, -91, -51, 123, -67, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.GeluOptions, -48, BuiltinOptions.SkipGramOptions, -72, -27, -76, -80, -119, BuiltinOptions.Rfft2dOptions, -105, BuiltinOptions.ResizeNearestNeighborOptions, 12, -106, 119, 126, BuiltinOptions.BatchMatMulOptions, -71, -15, 9, -59, BuiltinOptions.HashtableSizeOptions, -58, -124, BuiltinOptions.BatchToSpaceNDOptions, -16, 125, -20, BuiltinOptions.FakeQuantOptions, -36, BuiltinOptions.MirrorPadOptions, 32, 121, -18, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.LogicalAndOptions, -41, -53, BuiltinOptions.ArgMinOptions, BuiltinOptions.FloorModOptions};
    private static final int[] CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    private static final int[] FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    private int F0(int[] iArr, int i) {
        return T((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    private int F1(int[] iArr, int i) {
        return T((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    private int F2(int[] iArr, int i) {
        return T((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    private int F3(int[] iArr, int i) {
        return T((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    private int L(int i) {
        return rotateLeft(i, 24) ^ (((rotateLeft(i, 2) ^ i) ^ rotateLeft(i, 10)) ^ rotateLeft(i, 18));
    }

    private int L_ap(int i) {
        return rotateLeft(i, 23) ^ (rotateLeft(i, 13) ^ i);
    }

    private int T(int i) {
        return L(tau(i));
    }

    private int T_ap(int i) {
        return L_ap(tau(i));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int bigEndianToInt = Pack.bigEndianToInt(bArr, 12);
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), bigEndianToInt};
        int i = iArr2[0];
        int[] iArr3 = FK;
        int i2 = i ^ iArr3[0];
        int i3 = iArr2[1] ^ iArr3[1];
        int i4 = iArr2[2] ^ iArr3[2];
        int i5 = bigEndianToInt ^ iArr3[3];
        int[] iArr4 = {i2, i3, i4, i5};
        if (z) {
            int i6 = (i3 ^ i4) ^ i5;
            int[] iArr5 = CK;
            int T_ap = T_ap(i6 ^ iArr5[0]) ^ i2;
            iArr[0] = T_ap;
            int T_ap2 = T_ap((T_ap ^ (iArr4[2] ^ iArr4[3])) ^ iArr5[1]) ^ iArr4[1];
            iArr[1] = T_ap2;
            int T_ap3 = T_ap((T_ap2 ^ (iArr4[3] ^ iArr[0])) ^ iArr5[2]) ^ iArr4[2];
            iArr[2] = T_ap3;
            iArr[3] = T_ap((T_ap3 ^ (iArr[0] ^ iArr[1])) ^ iArr5[3]) ^ iArr4[3];
            for (int i7 = 4; i7 < 32; i7++) {
                iArr[i7] = iArr[i7 - 4] ^ T_ap(((iArr[i7 - 3] ^ iArr[i7 - 2]) ^ iArr[i7 - 1]) ^ CK[i7]);
            }
        } else {
            int i8 = (i3 ^ i4) ^ i5;
            int[] iArr6 = CK;
            int T_ap4 = T_ap(i8 ^ iArr6[0]) ^ i2;
            iArr[31] = T_ap4;
            int T_ap5 = T_ap((T_ap4 ^ (iArr4[2] ^ iArr4[3])) ^ iArr6[1]) ^ iArr4[1];
            iArr[30] = T_ap5;
            int T_ap6 = T_ap((T_ap5 ^ (iArr4[3] ^ iArr[31])) ^ iArr6[2]) ^ iArr4[2];
            iArr[29] = T_ap6;
            iArr[28] = T_ap((T_ap6 ^ (iArr[31] ^ iArr[30])) ^ iArr6[3]) ^ iArr4[3];
            for (int i9 = 27; i9 >= 0; i9--) {
                iArr[i9] = iArr[i9 + 4] ^ T_ap(((iArr[i9 + 3] ^ iArr[i9 + 2]) ^ iArr[i9 + 1]) ^ CK[31 - i9]);
            }
        }
        return iArr;
    }

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int tau(int i) {
        byte[] bArr = Sbox;
        return (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 24) & 255] & UByte.MAX_VALUE) << 24) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.rk = expandKey(z, key);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.rk != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    this.X[0] = Pack.bigEndianToInt(bArr, i);
                    this.X[1] = Pack.bigEndianToInt(bArr, i + 4);
                    this.X[2] = Pack.bigEndianToInt(bArr, i + 8);
                    this.X[3] = Pack.bigEndianToInt(bArr, i + 12);
                    for (int i3 = 0; i3 < 32; i3 += 4) {
                        int[] iArr = this.X;
                        iArr[0] = F0(iArr, this.rk[i3]);
                        int[] iArr2 = this.X;
                        iArr2[1] = F1(iArr2, this.rk[i3 + 1]);
                        int[] iArr3 = this.X;
                        iArr3[2] = F2(iArr3, this.rk[i3 + 2]);
                        int[] iArr4 = this.X;
                        iArr4[3] = F3(iArr4, this.rk[i3 + 3]);
                    }
                    Pack.intToBigEndian(this.X[3], bArr2, i2);
                    Pack.intToBigEndian(this.X[2], bArr2, i2 + 4);
                    Pack.intToBigEndian(this.X[1], bArr2, i2 + 8);
                    Pack.intToBigEndian(this.X[0], bArr2, i2 + 12);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("SM4 not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
