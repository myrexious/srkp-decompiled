package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;
    private final int[] F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;
    private static final byte[] S0 = {BuiltinOptions.LogicalAndOptions, BuiltinOptions.RandomOptions, BuiltinOptions.HardSwishOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -54, -32, 0, 51, 4, -47, BuiltinOptions.CosOptions, -104, 9, -71, BuiltinOptions.HashtableImportOptions, -53, 123, BuiltinOptions.ReducerOptions, -7, 50, -81, -99, BuiltinOptions.Conv3DOptions, -91, -72, BuiltinOptions.GreaterEqualOptions, -4, BuiltinOptions.DivOptions, 8, BuiltinOptions.GatherNdOptions, 3, -112, BuiltinOptions.MirrorPadOptions, BuiltinOptions.AbsOptions, -124, -103, -28, -50, -39, -111, -35, -74, -123, BuiltinOptions.FloorModOptions, -117, BuiltinOptions.LessOptions, BuiltinOptions.HashtableSizeOptions, -84, -51, -63, -8, BuiltinOptions.SqueezeOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.Rfft2dOptions, -58, -75, -67, -3, BuiltinOptions.ArgMinOptions, BuiltinOptions.DensifyOptions, 32, -44, BuiltinOptions.PowOptions, 118, 125, -78, -89, -49, -19, BuiltinOptions.ReverseSequenceOptions, -59, -13, BuiltinOptions.GreaterOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.ExpOptions, 6, BuiltinOptions.WhereOptions, -101, -29, ByteSourceJsonBootstrapper.UTF8_BOM_1, BuiltinOptions.DepthToSpaceOptions, 49, BuiltinOptions.SplitVOptions, ByteCompanionObject.MAX_VALUE, BuiltinOptions.MatrixSetDiagOptions, -92, 13, -126, BuiltinOptions.ReverseV2Options, BuiltinOptions.RangeOptions, BuiltinOptions.NonMaxSuppressionV4Options, -70, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.SubOptions, BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.PadOptions, -43, BuiltinOptions.GatherOptions, -88, -110, BuiltinOptions.LogSoftmaxOptions, BuiltinOptions.SequenceRNNOptions, -116, -1, -40, -82, BuiltinOptions.LessEqualOptions, 1, -45, -83, BuiltinOptions.PackOptions, BuiltinOptions.LeakyReluOptions, -38, BuiltinOptions.BidirectionalSequenceRNNOptions, -21, -55, -34, -102, -113, -121, -41, BuiltinOptions.FakeQuantOptions, ByteCompanionObject.MIN_VALUE, BuiltinOptions.VarHandleOptions, BuiltinOptions.SelectOptions, -56, -79, -76, 55, -9, 10, BuiltinOptions.TopKV2Options, BuiltinOptions.SpaceToDepthOptions, BuiltinOptions.ArgMaxOptions, 124, -52, BuiltinOptions.LogicalOrOptions, -119, -57, -61, -106, BuiltinOptions.RankOptions, 7, ByteSourceJsonBootstrapper.UTF8_BOM_3, 126, -16, 11, BuiltinOptions.PadV2Options, -105, BuiltinOptions.AddNOptions, 53, BuiltinOptions.FloorDivOptions, 121, BuiltinOptions.ScatterNdOptions, -90, BuiltinOptions.SquaredDifferenceOptions, 16, -2, PSSSigner.TRAILER_IMPLICIT, BuiltinOptions.DequantizeOptions, -107, -120, -118, -80, -93, -5, -64, BuiltinOptions.BatchToSpaceNDOptions, -108, -14, -31, -27, -23, BuiltinOptions.WhileOptions, -48, -36, BuiltinOptions.ReshapeOptions, BuiltinOptions.CumsumOptions, BuiltinOptions.SegmentSumOptions, BuiltinOptions.IfOptions, -20, BuiltinOptions.QuantizeOptions, BuiltinOptions.SquareOptions, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.SkipGramOptions, -11, BuiltinOptions.GeluOptions, -100, -86, BuiltinOptions.SplitOptions, 14, -122, -85, -66, BuiltinOptions.NegOptions, 2, -25, BuiltinOptions.CallOnceOptions, -26, BuiltinOptions.FillOptions, -94, BuiltinOptions.HashtableFindOptions, -62, -109, -97, -15, -10, -6, 54, -46, 80, BuiltinOptions.BroadcastToOptions, -98, BuiltinOptions.SelectV2Options, BuiltinOptions.AssignVariableOptions, BuiltinOptions.MulOptions, 61, -42, BuiltinOptions.UnpackOptions, -60, -30, 15, -114, -125, 119, BuiltinOptions.HashtableOptions, BuiltinOptions.CastOptions, 5, BuiltinOptions.LogicalNotOptions, 12, BuiltinOptions.SliceOptions, -22, BuiltinOptions.ReadVariableOptions, -73, -95, -24, -87, BuiltinOptions.BatchMatMulOptions, -115, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.TransposeOptions, -37, -127, -77, -96, -12, BuiltinOptions.BidirectionalSequenceLSTMOptions, 122, BuiltinOptions.SpaceToBatchNDOptions, -33, -18, 120, 52, BuiltinOptions.NonMaxSuppressionV5Options};
    private static final byte[] S1 = {BuiltinOptions.WhereOptions, -62, BuiltinOptions.DensifyOptions, BuiltinOptions.AssignVariableOptions, BuiltinOptions.PackOptions, -56, BuiltinOptions.UnidirectionalSequenceLSTMOptions, -122, -97, BuiltinOptions.LogicalOrOptions, -38, BuiltinOptions.HardSwishOptions, BuiltinOptions.LessOptions, -86, -3, 119, -116, -59, -108, 12, -90, BuiltinOptions.TransposeOptions, BuiltinOptions.SpaceToDepthOptions, 0, -29, -88, BuiltinOptions.PadOptions, BuiltinOptions.RandomOptions, BuiltinOptions.UnpackOptions, -7, -8, BuiltinOptions.SquareOptions, BuiltinOptions.FillOptions, BuiltinOptions.DequantizeOptions, BuiltinOptions.BroadcastToOptions, -106, -127, -39, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.LogicalAndOptions, 16, 118, -58, -89, -117, BuiltinOptions.ArgMinOptions, BuiltinOptions.ZerosLikeOptions, -31, BuiltinOptions.FakeQuantOptions, -75, BuiltinOptions.RankOptions, BuiltinOptions.NegOptions, -64, BuiltinOptions.HashtableImportOptions, -77, 5, BuiltinOptions.TopKV2Options, BuiltinOptions.CumsumOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, -36, 11, -6, BuiltinOptions.SelectV2Options, BuiltinOptions.FloorModOptions, -35, 32, BuiltinOptions.ReshapeOptions, 6, 54, -55, -63, -49, -10, BuiltinOptions.MaximumMinimumOptions, BuiltinOptions.AddNOptions, ByteSourceJsonBootstrapper.UTF8_BOM_2, BuiltinOptions.Rfft2dOptions, -11, -44, -121, ByteCompanionObject.MAX_VALUE, -124, BuiltinOptions.SquaredDifferenceOptions, -46, -100, BuiltinOptions.ReverseSequenceOptions, -92, PSSSigner.TRAILER_IMPLICIT, BuiltinOptions.SplitVOptions, -102, -33, -2, -42, -115, 122, -21, BuiltinOptions.PadV2Options, BuiltinOptions.GatherNdOptions, -40, BuiltinOptions.IfOptions, -95, BuiltinOptions.EmbeddingLookupSparseOptions, BuiltinOptions.GatherOptions, -5, BuiltinOptions.SplitOptions, -43, 125, BuiltinOptions.SliceOptions, BuiltinOptions.CallOnceOptions, BuiltinOptions.BucketizeOptions, 8, 9, -18, -73, BuiltinOptions.ReadVariableOptions, BuiltinOptions.LogicalNotOptions, BuiltinOptions.ScatterNdOptions, -78, BuiltinOptions.SpaceToBatchNDOptions, -114, BuiltinOptions.AbsOptions, -27, BuiltinOptions.LeakyReluOptions, -109, -113, BuiltinOptions.WhileOptions, -37, -87, -83, -15, -82, BuiltinOptions.LessEqualOptions, -53, 13, -4, -12, BuiltinOptions.GreaterEqualOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.DivOptions, -105, -24, -47, -23, BuiltinOptions.MirrorPadOptions, 55, -91, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.DepthToSpaceOptions, -125, -98, -85, -126, -99, -71, BuiltinOptions.SubOptions, -32, -51, BuiltinOptions.RangeOptions, -119, 1, -74, -67, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.LogSoftmaxOptions, -94, BuiltinOptions.NonMaxSuppressionV4Options, BuiltinOptions.PowOptions, 120, -103, BuiltinOptions.MulOptions, -112, 80, -72, -107, -28, -48, -111, -57, -50, -19, 15, -76, BuiltinOptions.VarHandleOptions, -96, -52, -16, 2, BuiltinOptions.ResizeNearestNeighborOptions, 121, -61, -34, -93, ByteSourceJsonBootstrapper.UTF8_BOM_1, -22, BuiltinOptions.ReverseV2Options, -26, BuiltinOptions.HashtableOptions, BuiltinOptions.BatchToSpaceNDOptions, -20, BuiltinOptions.ReducerOptions, BuiltinOptions.GreaterOptions, ByteCompanionObject.MIN_VALUE, -9, BuiltinOptions.GeluOptions, -25, -1, BuiltinOptions.ExpOptions, BuiltinOptions.MatrixSetDiagOptions, BuiltinOptions.Conv3DOptions, BuiltinOptions.CosOptions, BuiltinOptions.SqueezeOptions, BuiltinOptions.FloorDivOptions, 49, -110, 53, -60, 51, 7, 10, -70, 126, 14, 52, -120, -79, -104, 124, -13, 61, BuiltinOptions.NonMaxSuppressionV5Options, BuiltinOptions.HashtableFindOptions, 123, -54, -45, BuiltinOptions.SequenceRNNOptions, 50, BuiltinOptions.BatchMatMulOptions, 4, BuiltinOptions.ArgMaxOptions, BuiltinOptions.SegmentSumOptions, -66, -123, -101, BuiltinOptions.SelectOptions, BuiltinOptions.QuantizeOptions, -118, -41, -80, BuiltinOptions.CastOptions, -84, -81, BuiltinOptions.SkipGramOptions, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    public Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    public Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int i, int i2) {
        int i3 = i + i2;
        return (Integer.MAX_VALUE & i3) + (i3 >>> 31);
    }

    private void BitReorganization() {
        int[] iArr = this.BRC;
        int[] iArr2 = this.LFSR;
        iArr[0] = ((iArr2[15] & 2147450880) << 1) | (iArr2[14] & 65535);
        iArr[1] = ((iArr2[11] & 65535) << 16) | (iArr2[9] >>> 15);
        iArr[2] = ((iArr2[7] & 65535) << 16) | (iArr2[5] >>> 15);
        iArr[3] = (iArr2[0] >>> 15) | ((iArr2[2] & 65535) << 16);
    }

    private static int L1(int i) {
        return ROT(i, 24) ^ (((ROT(i, 2) ^ i) ^ ROT(i, 10)) ^ ROT(i, 18));
    }

    private static int L2(int i) {
        return ROT(i, 30) ^ (((ROT(i, 8) ^ i) ^ ROT(i, 14)) ^ ROT(i, 22));
    }

    private void LFSRWithInitialisationMode(int i) {
        int i2 = this.LFSR[0];
        int AddM = AddM(AddM(AddM(AddM(AddM(AddM(i2, MulByPow2(i2, 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), i);
        int[] iArr = this.LFSR;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = iArr[8];
        iArr[8] = iArr[9];
        iArr[9] = iArr[10];
        iArr[10] = iArr[11];
        iArr[11] = iArr[12];
        iArr[12] = iArr[13];
        iArr[13] = iArr[14];
        iArr[14] = iArr[15];
        iArr[15] = AddM;
    }

    private void LFSRWithWorkMode() {
        int i = this.LFSR[0];
        int AddM = AddM(AddM(AddM(AddM(AddM(i, MulByPow2(i, 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] iArr = this.LFSR;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = iArr[8];
        iArr[8] = iArr[9];
        iArr[9] = iArr[10];
        iArr[10] = iArr[11];
        iArr[11] = iArr[12];
        iArr[12] = iArr[13];
        iArr[13] = iArr[14];
        iArr[14] = iArr[15];
        iArr[15] = AddM;
    }

    private static int MAKEU31(byte b, short s, byte b2) {
        return ((b & UByte.MAX_VALUE) << 23) | ((s & UShort.MAX_VALUE) << 8) | (b2 & UByte.MAX_VALUE);
    }

    private static int MAKEU32(byte b, byte b2, byte b3, byte b4) {
        return ((b & UByte.MAX_VALUE) << 24) | ((b2 & UByte.MAX_VALUE) << 16) | ((b3 & UByte.MAX_VALUE) << 8) | (b4 & UByte.MAX_VALUE);
    }

    private static int MulByPow2(int i, int i2) {
        return ((i >>> (31 - i2)) | (i << i2)) & Integer.MAX_VALUE;
    }

    static int ROT(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static void encode32be(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    private void makeKeyStream() {
        encode32be(makeKeyStreamWord(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        setKeyAndIV(this.LFSR, bArr, bArr2);
        int[] iArr = this.F;
        iArr[0] = 0;
        iArr[1] = 0;
        int i = 32;
        while (true) {
            BitReorganization();
            if (i <= 0) {
                F();
                LFSRWithWorkMode();
                return;
            }
            LFSRWithInitialisationMode(F() >>> 1);
            i--;
        }
    }

    int F() {
        int[] iArr = this.BRC;
        int i = iArr[0];
        int[] iArr2 = this.F;
        int i2 = iArr2[0];
        int i3 = iArr2[1];
        int i4 = (i ^ i2) + i3;
        int i5 = i2 + iArr[1];
        int i6 = iArr[2] ^ i3;
        int L1 = L1((i5 << 16) | (i6 >>> 16));
        int L2 = L2((i6 << 16) | (i5 >>> 16));
        int[] iArr3 = this.F;
        byte[] bArr = S0;
        byte b = bArr[L1 >>> 24];
        byte[] bArr2 = S1;
        iArr3[0] = MAKEU32(b, bArr2[(L1 >>> 16) & 255], bArr[(L1 >>> 8) & 255], bArr2[L1 & 255]);
        this.F[1] = MAKEU32(bArr[L2 >>> 24], bArr2[(L2 >>> 16) & 255], bArr[(L2 >>> 8) & 255], bArr2[L2 & 255]);
        return i4;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    protected int getMaxIterations() {
        return 2047;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            cipherParameters2 = cipherParameters;
            bArr = null;
        }
        byte[] key = cipherParameters2 instanceof KeyParameter ? ((KeyParameter) cipherParameters2).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, bArr);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, z ? CryptoServicePurpose.ENCRYPTION : CryptoServicePurpose.DECRYPTION));
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    public int makeKeyStreamWord() {
        int i = this.theIterations;
        this.theIterations = i + 1;
        if (i < getMaxIterations()) {
            BitReorganization();
            int F = F() ^ this.BRC[3];
            LFSRWithWorkMode();
            return F;
        }
        throw new IllegalStateException("Too much data processed by singleKey/IV");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.theResetState != null) {
            if (i + i2 <= bArr.length) {
                if (i3 + i2 <= bArr2.length) {
                    for (int i4 = 0; i4 < i2; i4++) {
                        bArr2[i4 + i3] = returnByte(bArr[i4 + i]);
                    }
                    return i2;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException(getAlgorithmName() + " not initialised");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] iArr = zuc128CoreEngine.LFSR;
        int[] iArr2 = this.LFSR;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = zuc128CoreEngine.F;
        int[] iArr4 = this.F;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = zuc128CoreEngine.BRC;
        int[] iArr6 = this.BRC;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int i = this.theIndex;
        byte b2 = (byte) (b ^ bArr[i]);
        this.theIndex = (i + 1) % 4;
        return b2;
    }

    protected void setKeyAndIV(int[] iArr, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        int[] iArr2 = this.LFSR;
        byte b = bArr[0];
        short[] sArr = EK_d;
        iArr2[0] = MAKEU31(b, sArr[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], sArr[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], sArr[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], sArr[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], sArr[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], sArr[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], sArr[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], sArr[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], sArr[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], sArr[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], sArr[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], sArr[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], sArr[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], sArr[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], sArr[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], sArr[15], bArr2[15]);
    }
}
