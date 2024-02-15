package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import java.io.ByteArrayOutputStream;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class Grain128AEADEngine implements AEADCipher {
    private static final int STATE_SIZE = 4;
    private int[] authAcc;
    private int[] authSr;
    private int[] lfsr;
    private byte[] mac;
    private int[] nfsr;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;
    private boolean initialised = false;
    private boolean isEven = true;
    private boolean aadFinished = false;
    private ErasableOutputStream aadData = new ErasableOutputStream();

    /* loaded from: classes2.dex */
    public static final class ErasableOutputStream extends ByteArrayOutputStream {
        public void erase() {
            Arrays.fill(this.buf, (byte) 0);
            reset();
        }

        public byte[] getBuf() {
            return this.buf;
        }
    }

    private void accumulate() {
        int[] iArr = this.authAcc;
        int i = iArr[0];
        int[] iArr2 = this.authSr;
        iArr[0] = i ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
    }

    private void authShift(int i) {
        int[] iArr = this.authSr;
        int i2 = iArr[1];
        iArr[0] = (iArr[0] >>> 1) | (i2 << 31);
        iArr[1] = (i << 31) | (i2 >>> 1);
    }

    private void doProcessAADBytes(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2;
        if (i2 < 128) {
            bArr2 = new byte[i2 + 1];
            bArr2[0] = (byte) reverseByte(i2);
            i3 = 0;
        } else {
            int len_length = len_length(i2);
            byte[] bArr3 = new byte[len_length + 1 + i2];
            bArr3[0] = (byte) reverseByte(len_length | 128);
            int i4 = i2;
            int i5 = 0;
            while (i5 < len_length) {
                i5++;
                bArr3[i5] = (byte) reverseByte(i4 & 255);
                i4 >>>= 8;
            }
            i3 = len_length;
            bArr2 = bArr3;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            bArr2[1 + i3 + i6] = (byte) reverseByte(bArr[i + i6]);
        }
        int i7 = 0;
        for (int i8 = 0; i8 < bArr2.length; i8++) {
            for (int i9 = 0; i9 < 16; i9++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                if ((i9 & 1) == 1) {
                    if (((byte) (bArr2[i7 >> 3] & (1 << (7 - (i7 & 7))))) != 0) {
                        accumulate();
                    }
                    authShift(this.output);
                    i7++;
                }
            }
        }
    }

    private byte[] getKeyStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr3[i4] = (byte) reverseByte(bArr[i + i4]);
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            byte b = 0;
            for (int i9 = 0; i9 < 16; i9++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                if (this.isEven) {
                    b = (byte) (b | ((((bArr3[i5 >> 3] >>> (7 - (i5 & 7))) & 1) ^ this.output) << (i7 & 7)));
                    i5++;
                    i7++;
                    this.isEven = false;
                } else {
                    if ((bArr3[i6 >> 3] & (1 << (7 - (i6 & 7)))) != 0) {
                        accumulate();
                    }
                    authShift(this.output);
                    i6++;
                    this.isEven = true;
                }
            }
            bArr2[i3 + i8] = b;
        }
        return bArr2;
    }

    private int getOutput() {
        int[] iArr = this.nfsr;
        int i = iArr[0];
        int i2 = i >>> 12;
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = i4 >>> 9;
        int i6 = i4 >>> 25;
        int i7 = i4 >>> 31;
        int[] iArr2 = this.lfsr;
        int i8 = iArr2[0];
        int i9 = iArr2[1];
        int i10 = iArr2[2];
        int i11 = i10 >>> 29;
        int i12 = (i8 >>> 20) & (i8 >>> 13);
        return (((i4 ^ (((((((((i12 ^ ((i8 >>> 8) & i2)) ^ (i7 & (i9 >>> 10))) ^ ((i9 >>> 28) & (i10 >>> 15))) ^ ((i2 & i7) & (i10 >>> 30))) ^ i11) ^ (i >>> 2)) ^ (i >>> 15)) ^ (i3 >>> 4)) ^ (i3 >>> 13))) ^ i5) ^ i6) & 1;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        int i = iArr[0];
        int i2 = iArr[2];
        return (iArr[3] ^ ((((i ^ (i >>> 7)) ^ (iArr[1] >>> 6)) ^ (i2 >>> 6)) ^ (i2 >>> 17))) & 1;
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        int i = iArr[0];
        int i2 = i >>> 25;
        int i3 = i >>> 27;
        int i4 = iArr[1];
        int i5 = i4 >>> 8;
        int i6 = i4 >>> 16;
        int i7 = i4 >>> 24;
        int i8 = i4 >>> 27;
        int i9 = iArr[2];
        int i10 = i9 >>> 1;
        int i11 = i9 >>> 3;
        int i12 = i9 >>> 4;
        int i13 = i9 >>> 18;
        int i14 = i9 >>> 20;
        int i15 = i9 >>> 27;
        int i16 = i9 >>> 29;
        return (((((((((((iArr[3] ^ (((i ^ (i >>> 26)) ^ i7) ^ i15)) ^ ((i >>> 3) & i11)) ^ ((i >>> 11) & (i >>> 13))) ^ ((i >>> 17) & (i >>> 18))) ^ (i3 & i8)) ^ (i5 & i6)) ^ ((i4 >>> 29) & i10)) ^ (i12 & i14)) ^ (((i >>> 22) & (i >>> 24)) & i2)) ^ (((i9 >>> 6) & (i9 >>> 14)) & i13)) ^ ((((i9 >>> 24) & (i9 >>> 28)) & i16) & (i9 >>> 31))) & 1;
    }

    private void initGrain() {
        for (int i = 0; i < 320; i++) {
            this.output = getOutput();
            this.nfsr = shift(this.nfsr, ((getOutputNFSR() ^ this.lfsr[0]) ^ this.output) & 1);
            this.lfsr = shift(this.lfsr, 1 & (getOutputLFSR() ^ this.output));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (((getOutputNFSR() ^ this.lfsr[0]) ^ this.output) ^ (this.workingKey[i2] >> i3)) & 1);
                this.lfsr = shift(this.lfsr, ((getOutputLFSR() ^ this.output) ^ (this.workingKey[i2 + 8] >> i3)) & 1);
            }
        }
        for (int i4 = 0; i4 < 2; i4++) {
            for (int i5 = 0; i5 < 32; i5++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int[] iArr = this.authAcc;
                iArr[i4] = iArr[i4] | (this.output << i5);
            }
        }
        for (int i6 = 0; i6 < 2; i6++) {
            for (int i7 = 0; i7 < 32; i7++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int[] iArr2 = this.authSr;
                iArr2[i6] = iArr2[i6] | (this.output << i7);
            }
        }
        this.initialised = true;
    }

    private int len_length(int i) {
        if ((i & 255) == i) {
            return 1;
        }
        if ((65535 & i) == i) {
            return 2;
        }
        return (16777215 & i) == i ? 3 : 4;
    }

    private int reverseByte(int i) {
        int i2 = (((i & 170) >>> 1) | ((i & 85) << 1)) & 255;
        int i3 = (((i2 & 204) >>> 2) | ((i2 & 51) << 2)) & 255;
        return (((i3 & 240) >>> 4) | ((i3 & 15) << 4)) & 255;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = ByteCompanionObject.MAX_VALUE;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.nfsr;
            if (i >= iArr.length) {
                return;
            }
            byte[] bArr3 = this.workingKey;
            int i3 = i2 + 3;
            int i4 = i2 + 2;
            int i5 = i2 + 1;
            iArr[i] = (bArr3[i2] & UByte.MAX_VALUE) | (bArr3[i3] << BuiltinOptions.BatchToSpaceNDOptions) | ((bArr3[i4] << 16) & 16711680) | ((bArr3[i5] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            int[] iArr2 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            iArr2[i] = (bArr4[i2] & UByte.MAX_VALUE) | (bArr4[i3] << BuiltinOptions.BatchToSpaceNDOptions) | ((bArr4[i4] << 16) & 16711680) | ((bArr4[i5] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            i2 += 4;
            i++;
        }
    }

    private int[] shift(int[] iArr, int i) {
        int i2 = iArr[1];
        iArr[0] = (iArr[0] >>> 1) | (i2 << 31);
        int i3 = i2 >>> 1;
        int i4 = iArr[2];
        iArr[1] = i3 | (i4 << 31);
        int i5 = iArr[3];
        iArr[2] = (i4 >>> 1) | (i5 << 31);
        iArr[3] = (i << 31) | (i5 >>> 1);
        return iArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        if (!this.aadFinished) {
            doProcessAADBytes(this.aadData.getBuf(), 0, this.aadData.size());
            this.aadFinished = true;
        }
        this.mac = new byte[8];
        this.output = getOutput();
        this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
        this.lfsr = shift(this.lfsr, 1 & getOutputLFSR());
        accumulate();
        int i2 = 0;
        for (int i3 = 0; i3 < 2; i3++) {
            int i4 = 0;
            while (i4 < 4) {
                this.mac[i2] = (byte) ((this.authAcc[i3] >>> (i4 << 3)) & 255);
                i4++;
                i2++;
            }
        }
        byte[] bArr2 = this.mac;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        try {
            return this.mac.length;
        } finally {
            reset();
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return "Grain-128AEAD";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return this.mac;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        return i + 8;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain-128AEAD init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != 12) {
            throw new IllegalArgumentException("Grain-128AEAD requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain-128AEAD init parameters must include a key");
        }
        byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("Grain-128AEAD key must be 128 bits long");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
        byte[] bArr = new byte[16];
        this.workingIV = bArr;
        this.workingKey = new byte[16];
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.authAcc = new int[2];
        this.authSr = new int[2];
        System.arraycopy(iv, 0, bArr, 0, iv.length);
        System.arraycopy(key, 0, this.workingKey, 0, key.length);
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        if (this.aadFinished) {
            throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
        }
        this.aadData.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (this.aadFinished) {
            throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
        }
        this.aadData.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (this.initialised) {
            if (!this.aadFinished) {
                doProcessAADBytes(this.aadData.getBuf(), 0, this.aadData.size());
                this.aadFinished = true;
            }
            if (i + i2 <= bArr.length) {
                if (i3 + i2 <= bArr2.length) {
                    getKeyStream(bArr, i, i2, bArr2, i3);
                    return i2;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException(getAlgorithmName() + " not initialised");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        this.isEven = true;
        this.mac = null;
        this.aadData.reset();
        this.aadFinished = false;
        setKey(this.workingKey, this.workingIV);
        initGrain();
    }
}
