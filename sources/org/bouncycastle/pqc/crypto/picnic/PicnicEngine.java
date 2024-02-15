package org.bouncycastle.pqc.crypto.picnic;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.logging.Logger;
import kotlin.UByte;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.picnic.Signature;
import org.bouncycastle.pqc.crypto.picnic.Signature2;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes2.dex */
public class PicnicEngine {
    private static final Logger LOG = Logger.getLogger(PicnicEngine.class.getName());
    protected static final int LOWMC_MAX_AND_GATES = 1144;
    protected static final int LOWMC_MAX_KEY_BITS = 256;
    private static final int LOWMC_MAX_STATE_SIZE = 64;
    protected static final int LOWMC_MAX_WORDS = 16;
    private static final int MAX_AUX_BYTES = 176;
    private static final int MAX_DIGEST_SIZE = 64;
    private static final int PICNIC_MAX_LOWMC_BLOCK_SIZE = 32;
    private static final int PICNIC_MAX_PRIVATEKEY_SIZE = 98;
    private static final int PICNIC_MAX_PUBLICKEY_SIZE = 65;
    private static final int PICNIC_MAX_SIGNATURE_SIZE = 209522;
    private static final int TRANSFORM_FS = 0;
    private static final int TRANSFORM_INVALID = 255;
    private static final int TRANSFORM_UR = 1;
    private static final int WORD_SIZE_BITS = 32;
    protected static final int saltSizeBytes = 32;
    private final int CRYPTO_BYTES;
    private final int CRYPTO_PUBLICKEYBYTES;
    private final int CRYPTO_SECRETKEYBYTES;
    protected final int UnruhGWithInputBytes;
    protected final int UnruhGWithoutInputBytes;
    protected final int andSizeBytes;
    protected final Xof digest;
    protected final int digestSizeBytes;
    protected final int numMPCParties;
    protected final int numMPCRounds;
    protected final int numOpenedRounds;
    protected final int numRounds;
    protected final int numSboxes;
    private final int parameters;
    protected final int pqSecurityLevel;
    protected final int seedSizeBytes;
    private int signatureLength;
    protected final int stateSizeBits;
    protected final int stateSizeBytes;
    protected final int stateSizeWords;
    private final int transform;

    public PicnicEngine(int i) {
        int i2;
        this.parameters = i;
        switch (i) {
            case 1:
            case 2:
                this.pqSecurityLevel = 64;
                this.stateSizeBits = 128;
                this.numMPCRounds = 219;
                this.numMPCParties = 3;
                this.numSboxes = 10;
                this.numRounds = 20;
                this.digestSizeBytes = 32;
                this.numOpenedRounds = 0;
                break;
            case 3:
            case 4:
                this.pqSecurityLevel = 96;
                this.stateSizeBits = 192;
                this.numMPCRounds = 329;
                this.numMPCParties = 3;
                this.numSboxes = 10;
                this.numRounds = 30;
                this.digestSizeBytes = 48;
                this.numOpenedRounds = 0;
                break;
            case 5:
            case 6:
                this.pqSecurityLevel = 128;
                this.stateSizeBits = 256;
                this.numMPCRounds = 438;
                this.numMPCParties = 3;
                this.numSboxes = 10;
                this.numRounds = 38;
                this.digestSizeBytes = 64;
                this.numOpenedRounds = 0;
                break;
            case 7:
                this.pqSecurityLevel = 64;
                this.stateSizeBits = 129;
                this.numMPCRounds = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
                this.numOpenedRounds = 36;
                this.numMPCParties = 16;
                this.numSboxes = 43;
                this.numRounds = 4;
                this.digestSizeBytes = 32;
                break;
            case 8:
                this.pqSecurityLevel = 96;
                this.stateSizeBits = 192;
                this.numMPCRounds = 419;
                this.numOpenedRounds = 52;
                this.numMPCParties = 16;
                this.numSboxes = 64;
                this.numRounds = 4;
                this.digestSizeBytes = 48;
                break;
            case 9:
                this.pqSecurityLevel = 128;
                this.stateSizeBits = 255;
                this.numMPCRounds = 601;
                this.numOpenedRounds = 68;
                this.numMPCParties = 16;
                this.numSboxes = 85;
                this.numRounds = 4;
                this.digestSizeBytes = 64;
                break;
            case 10:
                this.pqSecurityLevel = 64;
                this.stateSizeBits = 129;
                this.numMPCRounds = 219;
                this.numMPCParties = 3;
                this.numSboxes = 43;
                this.numRounds = 4;
                this.digestSizeBytes = 32;
                this.numOpenedRounds = 0;
                break;
            case 11:
                this.pqSecurityLevel = 96;
                this.stateSizeBits = 192;
                this.numMPCRounds = 329;
                this.numMPCParties = 3;
                this.numSboxes = 64;
                this.numRounds = 4;
                this.digestSizeBytes = 48;
                this.numOpenedRounds = 0;
                break;
            case 12:
                this.pqSecurityLevel = 128;
                this.stateSizeBits = 255;
                this.numMPCRounds = 438;
                this.numMPCParties = 3;
                this.numSboxes = 85;
                this.numRounds = 4;
                this.digestSizeBytes = 64;
                this.numOpenedRounds = 0;
                break;
            default:
                throw new IllegalArgumentException("unknown parameter set " + i);
        }
        switch (i) {
            case 1:
                this.CRYPTO_SECRETKEYBYTES = 49;
                this.CRYPTO_PUBLICKEYBYTES = 33;
                i2 = 34036;
                break;
            case 2:
                this.CRYPTO_SECRETKEYBYTES = 49;
                this.CRYPTO_PUBLICKEYBYTES = 33;
                i2 = 53965;
                break;
            case 3:
                this.CRYPTO_SECRETKEYBYTES = 73;
                this.CRYPTO_PUBLICKEYBYTES = 49;
                i2 = 76784;
                break;
            case 4:
                this.CRYPTO_SECRETKEYBYTES = 73;
                this.CRYPTO_PUBLICKEYBYTES = 49;
                i2 = 121857;
                break;
            case 5:
                this.CRYPTO_SECRETKEYBYTES = 97;
                this.CRYPTO_PUBLICKEYBYTES = 65;
                i2 = 132876;
                break;
            case 6:
                this.CRYPTO_SECRETKEYBYTES = 97;
                this.CRYPTO_PUBLICKEYBYTES = 65;
                i2 = 209526;
                break;
            case 7:
                this.CRYPTO_SECRETKEYBYTES = 52;
                this.CRYPTO_PUBLICKEYBYTES = 35;
                i2 = 14612;
                break;
            case 8:
                this.CRYPTO_SECRETKEYBYTES = 73;
                this.CRYPTO_PUBLICKEYBYTES = 49;
                i2 = 35028;
                break;
            case 9:
                this.CRYPTO_SECRETKEYBYTES = 97;
                this.CRYPTO_PUBLICKEYBYTES = 65;
                i2 = 61028;
                break;
            case 10:
                this.CRYPTO_SECRETKEYBYTES = 52;
                this.CRYPTO_PUBLICKEYBYTES = 35;
                i2 = 32061;
                break;
            case 11:
                this.CRYPTO_SECRETKEYBYTES = 73;
                this.CRYPTO_PUBLICKEYBYTES = 49;
                i2 = 71179;
                break;
            case 12:
                this.CRYPTO_SECRETKEYBYTES = 97;
                this.CRYPTO_PUBLICKEYBYTES = 65;
                i2 = 126286;
                break;
            default:
                i2 = -1;
                this.CRYPTO_SECRETKEYBYTES = -1;
                this.CRYPTO_PUBLICKEYBYTES = -1;
                break;
        }
        this.CRYPTO_BYTES = i2;
        int numBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
        this.andSizeBytes = numBytes;
        int numBytes2 = Utils.numBytes(this.stateSizeBits);
        this.stateSizeBytes = numBytes2;
        int numBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
        this.seedSizeBytes = numBytes3;
        int i3 = this.stateSizeBits;
        this.stateSizeWords = ((i3 + 32) - 1) / 32;
        switch (i) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                this.transform = 0;
                break;
            case 2:
            case 4:
            case 6:
                this.transform = 1;
                break;
            default:
                this.transform = 255;
                break;
        }
        if (this.transform == 1) {
            int i4 = numBytes3 + numBytes;
            this.UnruhGWithoutInputBytes = i4;
            this.UnruhGWithInputBytes = i4 + numBytes2;
        } else {
            this.UnruhGWithoutInputBytes = 0;
            this.UnruhGWithInputBytes = 0;
        }
        this.digest = (i3 == 128 || i3 == 129) ? new SHAKEDigest(128) : new SHAKEDigest(256);
    }

    private void Commit(byte[] bArr, int i, View view, byte[] bArr2) {
        this.digest.update((byte) 4);
        this.digest.update(bArr, i, this.seedSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        this.digest.update((byte) 0);
        this.digest.update(bArr2, 0, this.digestSizeBytes);
        this.digest.update(Pack.intToLittleEndian(view.inputShare), 0, this.stateSizeBytes);
        this.digest.update(view.communicatedBits, 0, this.andSizeBytes);
        this.digest.update(Pack.intToLittleEndian(view.outputShare), 0, this.stateSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
    }

    private void G(int i, byte[] bArr, int i2, View view, byte[] bArr2) {
        int i3 = this.seedSizeBytes + this.andSizeBytes;
        this.digest.update((byte) 5);
        this.digest.update(bArr, i2, this.seedSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        this.digest.update(bArr2, 0, this.digestSizeBytes);
        if (i == 2) {
            this.digest.update(Pack.intToLittleEndian(view.inputShare), 0, this.stateSizeBytes);
            i3 += this.stateSizeBytes;
        }
        this.digest.update(view.communicatedBits, 0, this.andSizeBytes);
        this.digest.update(Pack.intToLittleEndian(i3), 0, 2);
        this.digest.doFinal(bArr2, 0, i3);
    }

    private void HCP(byte[] bArr, int[] iArr, int[] iArr2, byte[][] bArr2, byte[] bArr3, byte[] bArr4, int[] iArr3, int[] iArr4, byte[] bArr5) {
        for (int i = 0; i < this.numMPCRounds; i++) {
            this.digest.update(bArr2[i], 0, this.digestSizeBytes);
        }
        this.digest.update(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr4, 0, 32);
        this.digest.update(Pack.intToLittleEndian(iArr3), 0, this.stateSizeBytes);
        this.digest.update(Pack.intToLittleEndian(iArr4), 0, this.stateSizeBytes);
        this.digest.update(bArr5, 0, bArr5.length);
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
        if (iArr == null || iArr2 == null) {
            return;
        }
        expandChallengeHash(bArr, iArr, iArr2);
    }

    private void LowMCEnc(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[16];
        if (iArr != iArr2) {
            System.arraycopy(iArr, 0, iArr2, 0, this.stateSizeWords);
        }
        KMatricesWithPointer KMatrix = LowmcConstants.KMatrix(this, 0);
        matrix_mul(iArr4, iArr3, KMatrix.getData(), KMatrix.getMatrixPointer());
        xor_array(iArr2, iArr2, iArr4, 0, this.stateSizeWords);
        for (int i = 1; i <= this.numRounds; i++) {
            KMatricesWithPointer KMatrix2 = LowmcConstants.KMatrix(this, i);
            matrix_mul(iArr4, iArr3, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            substitution(iArr2);
            int i2 = i - 1;
            KMatricesWithPointer LMatrix = LowmcConstants.LMatrix(this, i2);
            matrix_mul(iArr2, iArr2, LMatrix.getData(), LMatrix.getMatrixPointer());
            KMatricesWithPointer RConstant = LowmcConstants.RConstant(this, i2);
            xor_array(iArr2, iArr2, RConstant.getData(), RConstant.getMatrixPointer(), this.stateSizeWords);
            xor_array(iArr2, iArr2, iArr4, 0, this.stateSizeWords);
        }
    }

    static int appendUnique(int[] iArr, int i, int i2) {
        if (i2 == 0) {
            iArr[i2] = i;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                if (iArr[i3] == i) {
                    return i2;
                }
            }
            iArr[i2] = i;
        }
        return i2 + 1;
    }

    private boolean arePaddingBitsZero(byte[] bArr, int i) {
        int numBytes = Utils.numBytes(i);
        while (i < numBytes * 8) {
            if (Utils.getBit(bArr, i) != 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    private void aux_mpc_AND(int i, int i2, int i3, Tape tape) {
        int i4 = this.numMPCParties - 1;
        Utils.setBit(tape.tapes[i4], tape.pos - 1, (byte) ((((i & i2) ^ (Utils.parity16(tape.tapesToWord()) ^ Utils.getBit(tape.tapes[i4], tape.pos - 1))) ^ i3) & 255));
    }

    static int bitsToChunks(int i, byte[] bArr, int i2, int[] iArr) {
        int i3 = i2 * 8;
        if (i > i3) {
            return 0;
        }
        int i4 = i3 / i;
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i5] = 0;
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i5] = iArr[i5] + (Utils.getBit(bArr, (i5 * i) + i6) << i6);
            }
        }
        return i4;
    }

    private void commit(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2) {
        this.digest.update(bArr2, 0, this.seedSizeBytes);
        if (bArr3 != null) {
            this.digest.update(bArr3, 0, this.andSizeBytes);
        }
        this.digest.update(bArr4, 0, 32);
        this.digest.update(Pack.intToLittleEndian(i), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void commit_h(byte[] bArr, byte[][] bArr2) {
        for (int i = 0; i < this.numMPCParties; i++) {
            this.digest.update(bArr2[i], 0, this.digestSizeBytes);
        }
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void commit_v(byte[] bArr, byte[] bArr2, Msg msg) {
        this.digest.update(bArr2, 0, this.stateSizeBytes);
        for (int i = 0; i < this.numMPCParties; i++) {
            this.digest.update(msg.msgs[i], 0, Utils.numBytes(msg.pos));
        }
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private int computeInputShareSize(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
            int challenge = getChallenge(bArr, i3);
            if (challenge == 1 || challenge == 2) {
                i2 += i;
            }
        }
        return i2;
    }

    private void computeSaltAndRootSeed(byte[] bArr, int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr2) {
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[32];
        Pack.intToLittleEndian(iArr, bArr3, 0);
        Pack.intToLittleEndian(iArr2, bArr4, 0);
        Pack.intToLittleEndian(iArr3, bArr5, 0);
        byte[] copyOfRange = Arrays.copyOfRange(bArr3, 0, this.stateSizeBytes);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr4, 0, this.stateSizeBytes);
        byte[] copyOfRange3 = Arrays.copyOfRange(bArr5, 0, this.stateSizeBytes);
        this.digest.update(copyOfRange, 0, this.stateSizeBytes);
        this.digest.update(bArr2, 0, bArr2.length);
        this.digest.update(copyOfRange2, 0, this.stateSizeBytes);
        this.digest.update(copyOfRange3, 0, this.stateSizeBytes);
        this.digest.update(Pack.shortToLittleEndian((short) (this.stateSizeBits & 65535)), 0, 2);
        this.digest.doFinal(bArr, 0, bArr.length);
    }

    private byte[] computeSeeds(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr) {
        byte[] bArr2 = new byte[(this.seedSizeBytes * this.numMPCParties * this.numMPCRounds) + 32];
        this.digest.update(Pack.intToLittleEndian(iArr), 0, this.stateSizeBytes);
        this.digest.update(bArr, 0, bArr.length);
        this.digest.update(Pack.intToLittleEndian(iArr2), 0, this.stateSizeBytes);
        this.digest.update(Pack.intToLittleEndian(iArr3), 0, this.stateSizeBytes);
        this.digest.update(Pack.intToLittleEndian(this.stateSizeBits), 0, 2);
        this.digest.doFinal(bArr2, 0, (this.seedSizeBytes * this.numMPCParties * this.numMPCRounds) + 32);
        return bArr2;
    }

    private boolean contains(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean createRandomTape(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        if (i4 < this.digestSizeBytes) {
            return false;
        }
        this.digest.update((byte) 2);
        this.digest.update(bArr, i, this.seedSizeBytes);
        this.digest.doFinal(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr2, 0, 32);
        this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i3), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i4), 0, 2);
        this.digest.doFinal(bArr3, 0, i4);
        return true;
    }

    private void createRandomTapes(Tape tape, byte[][] bArr, int i, byte[] bArr2, int i2) {
        int i3 = this.andSizeBytes * 2;
        for (int i4 = 0; i4 < this.numMPCParties; i4++) {
            this.digest.update(bArr[i4 + i], 0, this.seedSizeBytes);
            this.digest.update(bArr2, 0, 32);
            this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
            this.digest.update(Pack.intToLittleEndian(i4), 0, 2);
            this.digest.doFinal(tape.tapes[i4], 0, i3);
        }
    }

    private int deserializeSignature(Signature signature, byte[] bArr, int i, int i2) {
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr2 = signature.challengeBits;
        if (i < Utils.numBytes(this.numMPCRounds * 2)) {
            return -1;
        }
        int computeInputShareSize = computeInputShareSize(bArr, this.stateSizeBytes);
        int i3 = this.numMPCRounds;
        int numBytes = Utils.numBytes(this.numMPCRounds * 2) + 32 + (((this.seedSizeBytes * 2) + this.andSizeBytes + this.digestSizeBytes) * i3) + computeInputShareSize;
        if (this.transform == 1) {
            numBytes += this.UnruhGWithoutInputBytes * i3;
        }
        if (i < numBytes) {
            return -1;
        }
        System.arraycopy(bArr, i2, bArr2, 0, Utils.numBytes(i3 * 2));
        int numBytes2 = i2 + Utils.numBytes(this.numMPCRounds * 2);
        if (isChallengeValid(bArr2)) {
            System.arraycopy(bArr, numBytes2, signature.salt, 0, 32);
            int i4 = numBytes2 + 32;
            for (int i5 = 0; i5 < this.numMPCRounds; i5++) {
                int challenge = getChallenge(bArr2, i5);
                System.arraycopy(bArr, i4, proofArr[i5].view3Commitment, 0, this.digestSizeBytes);
                int i6 = i4 + this.digestSizeBytes;
                if (this.transform == 1) {
                    int i7 = challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes;
                    System.arraycopy(bArr, i6, proofArr[i5].view3UnruhG, 0, i7);
                    i6 += i7;
                }
                System.arraycopy(bArr, i6, proofArr[i5].communicatedBits, 0, this.andSizeBytes);
                int i8 = i6 + this.andSizeBytes;
                System.arraycopy(bArr, i8, proofArr[i5].seed1, 0, this.seedSizeBytes);
                int i9 = i8 + this.seedSizeBytes;
                System.arraycopy(bArr, i9, proofArr[i5].seed2, 0, this.seedSizeBytes);
                i4 = i9 + this.seedSizeBytes;
                if (challenge == 1 || challenge == 2) {
                    Pack.littleEndianToInt(bArr, i4, proofArr[i5].inputShare, 0, this.stateSizeBytes / 4);
                    if (this.stateSizeBits == 129) {
                        proofArr[i5].inputShare[this.stateSizeWords - 1] = bArr[(this.stateSizeBytes + i4) - 1] & UByte.MAX_VALUE;
                    }
                    i4 += this.stateSizeBytes;
                    if (!arePaddingBitsZero(Pack.intToLittleEndian(proofArr[i5].inputShare), this.stateSizeBits)) {
                        return -1;
                    }
                }
            }
            return 0;
        }
        return -1;
    }

    private int deserializeSignature2(Signature2 signature2, byte[] bArr, int i, int i2) {
        Logger logger;
        String str;
        int i3 = this.digestSizeBytes + 32;
        if (bArr.length < i3) {
            return -1;
        }
        System.arraycopy(bArr, i2, signature2.challengeHash, 0, this.digestSizeBytes);
        int i4 = i2 + this.digestSizeBytes;
        System.arraycopy(bArr, i4, signature2.salt, 0, 32);
        int i5 = i4 + 32;
        expandChallengeHash(signature2.challengeHash, signature2.challengeC, signature2.challengeP);
        signature2.iSeedInfoLen = new Tree(this, this.numMPCRounds, this.seedSizeBytes).revealSeedsSize(signature2.challengeC, this.numOpenedRounds);
        int i6 = i3 + signature2.iSeedInfoLen;
        signature2.cvInfoLen = new Tree(this, this.numMPCRounds, this.digestSizeBytes).openMerkleTreeSize(getMissingLeavesList(signature2.challengeC), this.numMPCRounds - this.numOpenedRounds);
        int i7 = i6 + signature2.cvInfoLen;
        int revealSeedsSize = new Tree(this, this.numMPCParties, this.seedSizeBytes).revealSeedsSize(new int[1], 1);
        for (int i8 = 0; i8 < this.numMPCRounds; i8++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i8)) {
                if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i8)] != this.numMPCParties - 1) {
                    i7 += this.andSizeBytes;
                }
                i7 = i7 + revealSeedsSize + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes;
            }
        }
        if (i == i7) {
            signature2.iSeedInfo = new byte[signature2.iSeedInfoLen];
            System.arraycopy(bArr, i5, signature2.iSeedInfo, 0, signature2.iSeedInfoLen);
            int i9 = i5 + signature2.iSeedInfoLen;
            signature2.cvInfo = new byte[signature2.cvInfoLen];
            System.arraycopy(bArr, i9, signature2.cvInfo, 0, signature2.cvInfoLen);
            int i10 = i9 + signature2.cvInfoLen;
            for (int i11 = 0; i11 < this.numMPCRounds; i11++) {
                if (contains(signature2.challengeC, this.numOpenedRounds, i11)) {
                    signature2.proofs[i11] = new Signature2.Proof2(this);
                    signature2.proofs[i11].seedInfoLen = revealSeedsSize;
                    signature2.proofs[i11].seedInfo = new byte[signature2.proofs[i11].seedInfoLen];
                    System.arraycopy(bArr, i10, signature2.proofs[i11].seedInfo, 0, signature2.proofs[i11].seedInfoLen);
                    int i12 = i10 + signature2.proofs[i11].seedInfoLen;
                    if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i11)] != this.numMPCParties - 1) {
                        System.arraycopy(bArr, i12, signature2.proofs[i11].aux, 0, this.andSizeBytes);
                        i12 += this.andSizeBytes;
                        if (!arePaddingBitsZero(signature2.proofs[i11].aux, this.numRounds * 3 * this.numSboxes)) {
                            logger = LOG;
                            str = "failed while deserializing aux bits";
                        }
                    }
                    System.arraycopy(bArr, i12, signature2.proofs[i11].input, 0, this.stateSizeBytes);
                    int i13 = i12 + this.stateSizeBytes;
                    int i14 = this.andSizeBytes;
                    System.arraycopy(bArr, i13, signature2.proofs[i11].msgs, 0, i14);
                    int i15 = i13 + i14;
                    if (arePaddingBitsZero(signature2.proofs[i11].msgs, this.numRounds * 3 * this.numSboxes)) {
                        System.arraycopy(bArr, i15, signature2.proofs[i11].C, 0, this.digestSizeBytes);
                        i10 = i15 + this.digestSizeBytes;
                    } else {
                        logger = LOG;
                        str = "failed while deserializing msgs bits";
                    }
                }
            }
            return 0;
        }
        logger = LOG;
        str = "sigBytesLen = " + bArr.length + ", expected bytesRequired = " + i7;
        logger.fine(str);
        return -1;
    }

    private void expandChallengeHash(byte[] bArr, int[] iArr, int[] iArr2) {
        int ceil_log2 = Utils.ceil_log2(this.numMPCRounds);
        int ceil_log22 = Utils.ceil_log2(this.numMPCParties);
        int[] iArr3 = new int[(this.digestSizeBytes * 8) / Math.min(ceil_log2, ceil_log22)];
        byte[] bArr2 = new byte[64];
        System.arraycopy(bArr, 0, bArr2, 0, this.digestSizeBytes);
        int i = 0;
        while (i < this.numOpenedRounds) {
            int bitsToChunks = bitsToChunks(ceil_log2, bArr2, this.digestSizeBytes, iArr3);
            for (int i2 = 0; i2 < bitsToChunks; i2++) {
                int i3 = iArr3[i2];
                if (i3 < this.numMPCRounds) {
                    i = appendUnique(iArr, i3, i);
                }
                if (i == this.numOpenedRounds) {
                    break;
                }
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr2, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        }
        int i4 = 0;
        while (i4 < this.numOpenedRounds) {
            int bitsToChunks2 = bitsToChunks(ceil_log22, bArr2, this.digestSizeBytes, iArr3);
            for (int i5 = 0; i5 < bitsToChunks2; i5++) {
                int i6 = iArr3[i5];
                if (i6 < this.numMPCParties) {
                    iArr2[i4] = i6;
                    i4++;
                }
                if (i4 == this.numOpenedRounds) {
                    break;
                }
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr2, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        }
    }

    static int extend(int i) {
        return ~(i - 1);
    }

    private void getAuxBits(byte[] bArr, Tape tape) {
        int i = this.numMPCParties - 1;
        int i2 = this.stateSizeBits;
        int i3 = 0;
        for (int i4 = 0; i4 < this.numRounds; i4++) {
            int i5 = 0;
            while (i5 < i2) {
                Utils.setBit(bArr, i3, Utils.getBit(tape.tapes[i], (i2 * 2 * i4) + i2 + i5));
                i5++;
                i3++;
            }
        }
    }

    private int[] getMissingLeavesList(int[] iArr) {
        int[] iArr2 = new int[this.numMPCRounds - this.numOpenedRounds];
        int i = 0;
        for (int i2 = 0; i2 < this.numMPCRounds; i2++) {
            if (!contains(iArr, this.numOpenedRounds, i2)) {
                iArr2[i] = i2;
                i++;
            }
        }
        return iArr2;
    }

    static int indexOf(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    private boolean isChallengeValid(byte[] bArr) {
        for (int i = 0; i < this.numMPCRounds; i++) {
            if (getChallenge(bArr, i) > 2) {
                return false;
            }
        }
        return true;
    }

    static boolean is_picnic3(int i) {
        return i == 7 || i == 8 || i == 9;
    }

    private int mpc_AND(int i, int i2, int i3, int i4, Tape tape, Msg msg) {
        int extend = ((i3 & extend(i2)) ^ (i4 & extend(i))) ^ tape.tapesToWord();
        byte[] intToLittleEndian = Pack.intToLittleEndian(extend);
        if (msg.unopened >= 0) {
            Utils.setBit(intToLittleEndian, msg.unopened, (byte) (Utils.getBit(msg.msgs[msg.unopened], msg.pos) & UByte.MAX_VALUE));
            extend = Pack.littleEndianToInt(intToLittleEndian, 0);
        }
        wordToMsgs(extend, msg);
        return (i & i2) ^ Utils.parity16(extend);
    }

    private void mpc_AND(int[] iArr, int[] iArr2, int[] iArr3, Tape tape, View[] viewArr) {
        int i = 0;
        int[] iArr4 = {Utils.getBit(tape.tapes[0], tape.pos), Utils.getBit(tape.tapes[1], tape.pos), Utils.getBit(tape.tapes[2], tape.pos)};
        while (i < 3) {
            int i2 = iArr[i];
            int i3 = i + 1;
            int i4 = i3 % 3;
            int i5 = iArr[i4];
            int i6 = iArr2[i];
            iArr3[i] = (((i2 & i6) ^ ((iArr2[i4] & i2) ^ (i5 & i6))) ^ iArr4[i]) ^ iArr4[i4];
            Utils.setBit(viewArr[i].communicatedBits, tape.pos, (byte) (iArr3[i] & 255));
            i = i3;
        }
        tape.pos++;
    }

    private void mpc_LowMC(Tape tape, View[] viewArr, int[] iArr, int[] iArr2) {
        int i;
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i2 = this.stateSizeWords;
        mpc_xor_constant(iArr2, i2 * 3, iArr, 0, i2);
        KMatricesWithPointer KMatrix = LowmcConstants.KMatrix(this, 0);
        int i3 = 0;
        while (true) {
            i = this.stateSizeWords;
            if (i3 >= 3) {
                break;
            }
            matrix_mul_offset(iArr2, i3 * i, viewArr[i3].inputShare, 0, KMatrix.getData(), KMatrix.getMatrixPointer());
            i3++;
        }
        mpc_xor(iArr2, iArr2, i, 3);
        for (int i4 = 1; i4 <= this.numRounds; i4++) {
            KMatricesWithPointer KMatrix2 = LowmcConstants.KMatrix(this, i4);
            for (int i5 = 0; i5 < 3; i5++) {
                matrix_mul_offset(iArr2, i5 * this.stateSizeWords, viewArr[i5].inputShare, 0, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            }
            mpc_substitution(iArr2, tape, viewArr);
            int i6 = i4 - 1;
            KMatricesWithPointer LMatrix = LowmcConstants.LMatrix(this, i6);
            int i7 = this.stateSizeWords;
            mpc_matrix_mul(iArr2, i7 * 3, iArr2, i7 * 3, LMatrix.getData(), LMatrix.getMatrixPointer(), 3);
            KMatricesWithPointer RConstant = LowmcConstants.RConstant(this, i6);
            mpc_xor_constant(iArr2, this.stateSizeWords * 3, RConstant.getData(), RConstant.getMatrixPointer(), this.stateSizeWords);
            mpc_xor(iArr2, iArr2, this.stateSizeWords, 3);
        }
        for (int i8 = 0; i8 < 3; i8++) {
            System.arraycopy(iArr2, (i8 + 3) * this.stateSizeWords, viewArr[i8].outputShare, 0, this.stateSizeWords);
        }
    }

    private void mpc_matrix_mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = this.stateSizeWords;
            matrix_mul_offset(iArr, i + (i5 * i6), iArr2, i2 + (i6 * i5), iArr3, i3);
        }
    }

    private void mpc_sbox(int[] iArr, int[] iArr2, Tape tape, Msg msg) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = iArr2[i2];
            int i4 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i4);
            int i5 = iArr2[i4];
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            int i6 = iArr2[i];
            int mpc_AND = mpc_AND(bitFromWordArray, bitFromWordArray2, i3, i5, tape, msg);
            int mpc_AND2 = mpc_AND(bitFromWordArray2, bitFromWordArray3, i5, i6, tape, msg);
            int i7 = bitFromWordArray ^ bitFromWordArray2;
            int mpc_AND3 = mpc_AND(bitFromWordArray3, bitFromWordArray, i6, i3, tape, msg) ^ i7;
            Utils.setBitInWordArray(iArr, i2, bitFromWordArray ^ mpc_AND2);
            Utils.setBitInWordArray(iArr, i4, mpc_AND3);
            Utils.setBitInWordArray(iArr, i, (i7 ^ bitFromWordArray3) ^ mpc_AND);
        }
    }

    private void mpc_substitution(int[] iArr, Tape tape, View[] viewArr) {
        int[] iArr2 = new int[3];
        int[] iArr3 = new int[3];
        int[] iArr4 = new int[3];
        int[] iArr5 = new int[3];
        int[] iArr6 = new int[3];
        int[] iArr7 = new int[3];
        int i = 0;
        while (i < this.numSboxes * 3) {
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = ((i2 + 3) * this.stateSizeWords * 32) + i;
                iArr2[i2] = Utils.getBitFromWordArray(iArr, i3 + 2);
                iArr3[i2] = Utils.getBitFromWordArray(iArr, i3 + 1);
                iArr4[i2] = Utils.getBitFromWordArray(iArr, i3);
            }
            int i4 = i;
            mpc_AND(iArr2, iArr3, iArr5, tape, viewArr);
            mpc_AND(iArr3, iArr4, iArr6, tape, viewArr);
            mpc_AND(iArr4, iArr2, iArr7, tape, viewArr);
            for (int i5 = 0; i5 < 3; i5++) {
                int i6 = ((i5 + 3) * this.stateSizeWords * 32) + i4;
                Utils.setBitInWordArray(iArr, i6 + 2, iArr2[i5] ^ iArr6[i5]);
                Utils.setBitInWordArray(iArr, i6 + 1, (iArr2[i5] ^ iArr3[i5]) ^ iArr7[i5]);
                Utils.setBitInWordArray(iArr, i6, ((iArr2[i5] ^ iArr3[i5]) ^ iArr4[i5]) ^ iArr5[i5]);
            }
            i = i4 + 3;
        }
    }

    private void mpc_xor(int[] iArr, int[] iArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = i2 + i3;
                int i6 = this.stateSizeWords;
                iArr[(i5 * i6) + i4] = iArr[(i5 * i6) + i4] ^ iArr2[(i6 * i3) + i4];
            }
        }
    }

    private void mpc_xor_constant(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 + i;
            iArr[i5] = iArr[i5] ^ iArr2[i4 + i2];
        }
    }

    private void mpc_xor_constant_verify(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4;
        if (i3 == 0) {
            i4 = this.stateSizeWords * 2;
        } else if (i3 != 2) {
            return;
        } else {
            i4 = this.stateSizeWords * 3;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 + i4;
            iArr[i6] = iArr[i6] ^ iArr2[i5 + i];
        }
    }

    private void picnic_keygen(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        int length = bArr3.length / 4;
        int[] iArr = new int[length];
        int length2 = bArr.length / 4;
        int[] iArr2 = new int[length2];
        int[] iArr3 = new int[bArr2.length / 4];
        int i = this.stateSizeBytes;
        byte[] bArr4 = new byte[i];
        secureRandom.nextBytes(bArr4);
        zeroTrailingBits(bArr4, this.stateSizeBits);
        System.arraycopy(bArr4, 0, bArr3, 0, i);
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = Pack.littleEndianToInt(bArr3, i2 * 4);
        }
        secureRandom.nextBytes(bArr4);
        zeroTrailingBits(bArr4, this.stateSizeBits);
        System.arraycopy(bArr4, 0, bArr, 0, i);
        for (int i3 = 0; i3 < length2; i3++) {
            iArr2[i3] = Pack.littleEndianToInt(bArr, i3 * 4);
        }
        LowMCEnc(iArr2, iArr3, iArr);
        Pack.intToLittleEndian(iArr, bArr3, 0);
        Pack.intToLittleEndian(iArr2, bArr, 0);
        Pack.intToLittleEndian(iArr3, bArr2, 0);
    }

    private int picnic_read_public_key(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        System.arraycopy(bArr3, 1, bArr, 0, this.stateSizeBytes);
        int i = this.stateSizeBytes;
        System.arraycopy(bArr3, i + 1, bArr2, 0, i);
        return 0;
    }

    private boolean picnic_sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int serializeSignature2;
        byte[] bArr4 = new byte[32];
        System.arraycopy(bArr, 1, bArr4, 0, this.stateSizeBytes);
        byte[] bArr5 = new byte[32];
        int i = this.stateSizeBytes;
        System.arraycopy(bArr, i + 1, bArr5, 0, i);
        byte[] bArr6 = new byte[32];
        int i2 = this.stateSizeBytes;
        System.arraycopy(bArr, (i2 * 2) + 1, bArr6, 0, i2);
        int i3 = this.stateSizeWords;
        int[] iArr = new int[i3];
        int[] iArr2 = new int[i3];
        int[] iArr3 = new int[i3];
        Pack.littleEndianToInt(bArr4, 0, iArr);
        Pack.littleEndianToInt(bArr6, 0, iArr3);
        Pack.littleEndianToInt(bArr5, 0, iArr2);
        if (is_picnic3(this.parameters)) {
            Signature2 signature2 = new Signature2(this);
            if (!sign_picnic3(iArr, iArr2, iArr3, bArr2, signature2)) {
                LOG.fine("Failed to create signature");
                return false;
            }
            serializeSignature2 = serializeSignature2(signature2, bArr3, bArr2.length + 4);
            if (serializeSignature2 == -1) {
                LOG.fine("Failed to serialize signature");
                return false;
            }
        } else {
            Signature signature = new Signature(this);
            if (sign_picnic1(iArr, iArr2, iArr3, bArr2, signature) != 0) {
                LOG.fine("Failed to create signature");
                return false;
            }
            serializeSignature2 = serializeSignature(signature, bArr3, bArr2.length + 4);
            if (serializeSignature2 == -1) {
                LOG.fine("Failed to serialize signature");
                return false;
            }
        }
        this.signatureLength = serializeSignature2;
        Pack.intToLittleEndian(serializeSignature2, bArr3, 0);
        return true;
    }

    private int picnic_verify(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[32];
        picnic_read_public_key(bArr5, bArr4, bArr);
        int i2 = this.stateSizeWords;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        Pack.littleEndianToInt(bArr4, 0, iArr2);
        Pack.littleEndianToInt(bArr5, 0, iArr);
        if (is_picnic3(this.parameters)) {
            Signature2 signature2 = new Signature2(this);
            deserializeSignature2(signature2, bArr3, i, bArr2.length + 4);
            return verify_picnic3(signature2, iArr, iArr2, bArr2);
        }
        Signature signature = new Signature(this);
        if (deserializeSignature(signature, bArr3, i, bArr2.length + 4) != 0) {
            LOG.fine("Error couldn't deserialize signature!");
            return -1;
        }
        return verify(signature, iArr, iArr2, bArr2);
    }

    private int picnic_write_private_key(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int i = this.stateSizeBytes;
        int i2 = (i * 3) + 1;
        if (bArr4.length < i2) {
            LOG.fine("Failed writing private key!");
            return -1;
        }
        bArr4[0] = (byte) this.parameters;
        System.arraycopy(bArr, 0, bArr4, 1, i);
        int i3 = this.stateSizeBytes;
        System.arraycopy(bArr2, 0, bArr4, i3 + 1, i3);
        int i4 = this.stateSizeBytes;
        System.arraycopy(bArr3, 0, bArr4, (i4 * 2) + 1, i4);
        return i2;
    }

    private int picnic_write_public_key(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.stateSizeBytes;
        int i2 = (i * 2) + 1;
        if (bArr3.length < i2) {
            LOG.fine("Failed writing public key!");
            return -1;
        }
        bArr3[0] = (byte) this.parameters;
        System.arraycopy(bArr, 0, bArr3, 1, i);
        int i3 = this.stateSizeBytes;
        System.arraycopy(bArr2, 0, bArr3, i3 + 1, i3);
        return i2;
    }

    private int serializeSignature2(Signature2 signature2, byte[] bArr, int i) {
        int i2 = this.digestSizeBytes + 32 + signature2.iSeedInfoLen + signature2.cvInfoLen;
        for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i3)) {
                int i4 = signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i3)];
                int i5 = i2 + signature2.proofs[i3].seedInfoLen;
                if (i4 != this.numMPCParties - 1) {
                    i5 += this.andSizeBytes;
                }
                i2 = i5 + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes;
            }
        }
        if (bArr.length < i2) {
            return -1;
        }
        System.arraycopy(signature2.challengeHash, 0, bArr, i, this.digestSizeBytes);
        int i6 = this.digestSizeBytes + i;
        System.arraycopy(signature2.salt, 0, bArr, i6, 32);
        int i7 = i6 + 32;
        System.arraycopy(signature2.iSeedInfo, 0, bArr, i7, signature2.iSeedInfoLen);
        int i8 = i7 + signature2.iSeedInfoLen;
        System.arraycopy(signature2.cvInfo, 0, bArr, i8, signature2.cvInfoLen);
        int i9 = i8 + signature2.cvInfoLen;
        for (int i10 = 0; i10 < this.numMPCRounds; i10++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i10)) {
                System.arraycopy(signature2.proofs[i10].seedInfo, 0, bArr, i9, signature2.proofs[i10].seedInfoLen);
                int i11 = i9 + signature2.proofs[i10].seedInfoLen;
                if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i10)] != this.numMPCParties - 1) {
                    System.arraycopy(signature2.proofs[i10].aux, 0, bArr, i11, this.andSizeBytes);
                    i11 += this.andSizeBytes;
                }
                System.arraycopy(signature2.proofs[i10].input, 0, bArr, i11, this.stateSizeBytes);
                int i12 = i11 + this.stateSizeBytes;
                System.arraycopy(signature2.proofs[i10].msgs, 0, bArr, i12, this.andSizeBytes);
                int i13 = i12 + this.andSizeBytes;
                System.arraycopy(signature2.proofs[i10].C, 0, bArr, i13, this.digestSizeBytes);
                i9 = i13 + this.digestSizeBytes;
            }
        }
        return i9 - i;
    }

    private void setChallenge(byte[] bArr, int i, int i2) {
        int i3 = i * 2;
        Utils.setBit(bArr, i3, (byte) (i2 & 1));
        Utils.setBit(bArr, i3 + 1, (byte) ((i2 >>> 1) & 1));
    }

    private int sign_picnic1(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr, Signature signature) {
        int i = 2;
        char c = 1;
        int i2 = 0;
        View[][] viewArr = (View[][]) Array.newInstance(View.class, this.numMPCRounds, 3);
        byte[][][] bArr2 = (byte[][][]) Array.newInstance(Byte.TYPE, this.numMPCRounds, this.numMPCParties, this.digestSizeBytes);
        byte[][][] bArr3 = (byte[][][]) Array.newInstance(Byte.TYPE, this.numMPCRounds, 3, this.UnruhGWithInputBytes);
        byte[] computeSeeds = computeSeeds(iArr, iArr2, iArr3, bArr);
        int i3 = this.numMPCParties * this.seedSizeBytes;
        System.arraycopy(computeSeeds, this.numMPCRounds * i3, signature.salt, 0, 32);
        Tape tape = new Tape(this);
        int i4 = this.stateSizeBytes;
        int max = Math.max(i4 * 9, i4 + this.andSizeBytes);
        byte[] bArr4 = new byte[max];
        byte[] bArr5 = new byte[this.stateSizeBytes * 4];
        int i5 = 0;
        while (true) {
            int i6 = this.numMPCRounds;
            if (i5 >= i6) {
                byte[][][] bArr6 = bArr2;
                int[][][] iArr4 = (int[][][]) Array.newInstance(Integer.TYPE, i6, 3, this.stateSizeBytes);
                for (int i7 = 0; i7 < this.numMPCRounds; i7++) {
                    for (int i8 = 0; i8 < 3; i8++) {
                        iArr4[i7][i8] = viewArr[i7][i8].outputShare;
                    }
                }
                byte[] bArr7 = computeSeeds;
                H3(iArr2, iArr3, iArr4, bArr6, signature.challengeBits, signature.salt, bArr, bArr3);
                for (int i9 = 0; i9 < this.numMPCRounds; i9++) {
                    prove(signature.proofs[i9], getChallenge(signature.challengeBits, i9), bArr7, i3 * i9, viewArr[i9], bArr6[i9], this.transform != 1 ? null : bArr3[i9]);
                }
                return 0;
            }
            viewArr[i5][i2] = new View(this);
            viewArr[i5][c] = new View(this);
            viewArr[i5][i] = new View(this);
            int i10 = i2;
            while (i10 < i) {
                int i11 = i10;
                int i12 = i5;
                byte[] bArr8 = bArr5;
                byte[] bArr9 = bArr4;
                int i13 = max;
                byte[][][] bArr10 = bArr2;
                Tape tape2 = tape;
                byte[] bArr11 = computeSeeds;
                if (!createRandomTape(computeSeeds, (i3 * i5) + (this.seedSizeBytes * i10), signature.salt, i12, i11, bArr9, this.stateSizeBytes + this.andSizeBytes)) {
                    LOG.fine("createRandomTape failed");
                    return -1;
                }
                System.arraycopy(bArr9, 0, bArr8, 0, this.stateSizeBytes);
                zeroTrailingBits(bArr8, this.stateSizeBits);
                Pack.littleEndianToInt(bArr8, 0, viewArr[i12][i11].inputShare);
                System.arraycopy(bArr9, this.stateSizeBytes, tape2.tapes[i11], 0, this.andSizeBytes);
                i10 = i11 + 1;
                i5 = i12;
                bArr4 = bArr9;
                bArr5 = bArr8;
                tape = tape2;
                max = i13;
                bArr2 = bArr10;
                computeSeeds = bArr11;
                i = 2;
            }
            int i14 = i5;
            byte[] bArr12 = bArr5;
            byte[] bArr13 = bArr4;
            int i15 = max;
            byte[] bArr14 = computeSeeds;
            byte[][][] bArr15 = bArr2;
            Tape tape3 = tape;
            int i16 = i3 * i14;
            if (!createRandomTape(bArr14, i16 + (this.seedSizeBytes * 2), signature.salt, i14, 2, tape3.tapes[2], this.andSizeBytes)) {
                LOG.fine("createRandomTape failed");
                return -1;
            }
            xor_three(viewArr[i14][2].inputShare, iArr, viewArr[i14][0].inputShare, viewArr[i14][1].inputShare, this.stateSizeBytes);
            tape3.pos = 0;
            int[] littleEndianToInt = Pack.littleEndianToInt(bArr13, 0, i15 / 4);
            mpc_LowMC(tape3, viewArr[i14], iArr3, littleEndianToInt);
            Pack.intToLittleEndian(littleEndianToInt, bArr13, 0);
            int[] iArr5 = new int[16];
            xor_three(iArr5, viewArr[i14][0].outputShare, viewArr[i14][1].outputShare, viewArr[i14][2].outputShare, this.stateSizeBytes);
            if (!subarrayEquals(iArr5, iArr2, this.stateSizeWords)) {
                LOG.fine("Simulation failed; output does not match public key (round = " + i14 + ")");
                return -1;
            }
            computeSeeds = bArr14;
            Commit(computeSeeds, i16 + (this.seedSizeBytes * 0), viewArr[i14][0], bArr15[i14][0]);
            Commit(computeSeeds, i16 + (this.seedSizeBytes * 1), viewArr[i14][1], bArr15[i14][1]);
            Commit(computeSeeds, i16 + (this.seedSizeBytes * 2), viewArr[i14][2], bArr15[i14][2]);
            if (this.transform == 1) {
                G(0, computeSeeds, i16 + (this.seedSizeBytes * 0), viewArr[i14][0], bArr3[i14][0]);
                G(1, computeSeeds, i16 + (this.seedSizeBytes * 1), viewArr[i14][1], bArr3[i14][1]);
                G(2, computeSeeds, i16 + (this.seedSizeBytes * 2), viewArr[i14][2], bArr3[i14][2]);
            }
            i5 = i14 + 1;
            bArr4 = bArr13;
            bArr5 = bArr12;
            tape = tape3;
            max = i15;
            bArr2 = bArr15;
            i = 2;
            c = 1;
            i2 = 0;
        }
    }

    private boolean sign_picnic3(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr, Signature2 signature2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.seedSizeBytes + 32;
        byte[] bArr2 = new byte[i5];
        computeSaltAndRootSeed(bArr2, iArr, iArr2, iArr3, bArr);
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 32, i5);
        signature2.salt = Arrays.copyOfRange(bArr2, 0, 32);
        Tree tree = new Tree(this, this.numMPCRounds, this.seedSizeBytes);
        tree.generateSeeds(copyOfRange, signature2.salt, 0);
        byte[][] leaves = tree.getLeaves();
        int leavesOffset = tree.getLeavesOffset();
        int i6 = this.numMPCRounds;
        Tape[] tapeArr = new Tape[i6];
        Tree[] treeArr = new Tree[i6];
        int i7 = 0;
        while (true) {
            i = this.numMPCRounds;
            if (i7 >= i) {
                break;
            }
            tapeArr[i7] = new Tape(this);
            Tree tree2 = new Tree(this, this.numMPCParties, this.seedSizeBytes);
            treeArr[i7] = tree2;
            tree2.generateSeeds(leaves[i7 + leavesOffset], signature2.salt, i7);
            createRandomTapes(tapeArr[i7], treeArr[i7].getLeaves(), treeArr[i7].getLeavesOffset(), signature2.salt, i7);
            i7++;
        }
        byte[][] bArr3 = (byte[][]) Array.newInstance(Byte.TYPE, i, this.stateSizeWords * 4);
        byte[] bArr4 = new byte[MAX_AUX_BYTES];
        int i8 = 0;
        while (true) {
            i2 = this.numMPCRounds;
            if (i8 >= i2) {
                break;
            }
            tapeArr[i8].computeAuxTape(bArr3[i8]);
            i8++;
        }
        byte[][][] bArr5 = (byte[][][]) Array.newInstance(Byte.TYPE, i2, this.numMPCParties, this.digestSizeBytes);
        int i9 = 0;
        while (true) {
            i3 = this.numMPCRounds;
            if (i9 >= i3) {
                break;
            }
            int i10 = 0;
            while (true) {
                i4 = this.numMPCParties;
                if (i10 < i4 - 1) {
                    int i11 = i10;
                    commit(bArr5[i9][i10], treeArr[i9].getLeaf(i10), null, signature2.salt, i9, i11);
                    i10 = i11 + 1;
                    i9 = i9;
                }
            }
            int i12 = i9;
            int i13 = i4 - 1;
            getAuxBits(bArr4, tapeArr[i12]);
            commit(bArr5[i12][i13], treeArr[i12].getLeaf(i13), bArr4, signature2.salt, i12, i13);
            i9 = i12 + 1;
        }
        Msg[] msgArr = new Msg[i3];
        int[] iArr4 = new int[this.stateSizeBits];
        int i14 = 0;
        while (true) {
            int i15 = this.numMPCRounds;
            if (i14 >= i15) {
                byte[][] bArr6 = (byte[][]) Array.newInstance(Byte.TYPE, i15, this.digestSizeBytes);
                byte[][] bArr7 = (byte[][]) Array.newInstance(Byte.TYPE, this.numMPCRounds, this.digestSizeBytes);
                for (int i16 = 0; i16 < this.numMPCRounds; i16++) {
                    commit_h(bArr6[i16], bArr5[i16]);
                    commit_v(bArr7[i16], bArr3[i16], msgArr[i16]);
                }
                Tree tree3 = new Tree(this, this.numMPCRounds, this.digestSizeBytes);
                tree3.buildMerkleTree(bArr7, signature2.salt);
                signature2.challengeC = new int[this.numOpenedRounds];
                signature2.challengeP = new int[this.numOpenedRounds];
                signature2.challengeHash = new byte[this.digestSizeBytes];
                HCP(signature2.challengeHash, signature2.challengeC, signature2.challengeP, bArr6, tree3.nodes[0], signature2.salt, iArr2, iArr3, bArr);
                int[] iArr5 = new int[1];
                signature2.cvInfo = tree3.openMerkleTree(getMissingLeavesList(signature2.challengeC), this.numMPCRounds - this.numOpenedRounds, iArr5);
                signature2.cvInfoLen = iArr5[0];
                signature2.iSeedInfo = new byte[this.numMPCRounds * this.seedSizeBytes];
                signature2.iSeedInfoLen = tree.revealSeeds(signature2.challengeC, this.numOpenedRounds, signature2.iSeedInfo, this.numMPCRounds * this.seedSizeBytes);
                signature2.proofs = new Signature2.Proof2[this.numMPCRounds];
                for (int i17 = 0; i17 < this.numMPCRounds; i17++) {
                    if (contains(signature2.challengeC, this.numOpenedRounds, i17)) {
                        signature2.proofs[i17] = new Signature2.Proof2(this);
                        int indexOf = indexOf(signature2.challengeC, this.numOpenedRounds, i17);
                        int[] iArr6 = {signature2.challengeP[indexOf]};
                        signature2.proofs[i17].seedInfo = new byte[this.numMPCParties * this.seedSizeBytes];
                        signature2.proofs[i17].seedInfoLen = treeArr[i17].revealSeeds(iArr6, 1, signature2.proofs[i17].seedInfo, this.numMPCParties * this.seedSizeBytes);
                        if (signature2.challengeP[indexOf] != this.numMPCParties - 1) {
                            getAuxBits(signature2.proofs[i17].aux, tapeArr[i17]);
                        }
                        System.arraycopy(bArr3[i17], 0, signature2.proofs[i17].input, 0, this.stateSizeBytes);
                        System.arraycopy(msgArr[i17].msgs[signature2.challengeP[indexOf]], 0, signature2.proofs[i17].msgs, 0, this.andSizeBytes);
                        System.arraycopy(bArr5[i17][signature2.challengeP[indexOf]], 0, signature2.proofs[i17].C, 0, this.digestSizeBytes);
                    }
                }
                return true;
            }
            msgArr[i14] = new Msg(this);
            int[] littleEndianToInt = Pack.littleEndianToInt(bArr3[i14], 0, this.stateSizeWords);
            int i18 = i14;
            xor_array(littleEndianToInt, littleEndianToInt, iArr, 0, this.stateSizeWords);
            int[] iArr7 = iArr4;
            if (simulateOnline(littleEndianToInt, tapeArr[i18], iArr4, msgArr[i18], iArr3, iArr2) != 0) {
                LOG.fine("MPC simulation failed, aborting signature");
                return false;
            }
            Pack.intToLittleEndian(littleEndianToInt, bArr3[i18], 0);
            i14 = i18 + 1;
            iArr4 = iArr7;
        }
    }

    private int simulateOnline(int[] iArr, Tape tape, int[] iArr2, Msg msg, int[] iArr3, int[] iArr4) {
        int[] iArr5 = new int[16];
        int[] iArr6 = new int[16];
        KMatricesWithPointer KMatrix = LowmcConstants.KMatrix(this, 0);
        matrix_mul(iArr5, iArr, KMatrix.getData(), KMatrix.getMatrixPointer());
        xor_array(iArr6, iArr5, iArr3, 0, this.stateSizeWords);
        for (int i = 1; i <= this.numRounds; i++) {
            tapesToWords(iArr2, tape);
            mpc_sbox(iArr6, iArr2, tape, msg);
            int i2 = i - 1;
            KMatricesWithPointer LMatrix = LowmcConstants.LMatrix(this, i2);
            matrix_mul(iArr6, iArr6, LMatrix.getData(), LMatrix.getMatrixPointer());
            KMatricesWithPointer RConstant = LowmcConstants.RConstant(this, i2);
            xor_array(iArr6, iArr6, RConstant.getData(), RConstant.getMatrixPointer(), this.stateSizeWords);
            KMatricesWithPointer KMatrix2 = LowmcConstants.KMatrix(this, i);
            matrix_mul(iArr5, iArr, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            xor_array(iArr6, iArr5, iArr6, 0, this.stateSizeWords);
        }
        return !subarrayEquals(iArr6, iArr4, this.stateSizeWords) ? -1 : 0;
    }

    private static boolean subarrayEquals(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length < i || bArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private static boolean subarrayEquals(int[] iArr, int[] iArr2, int i) {
        if (iArr.length < i || iArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void substitution(int[] iArr) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i3);
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            Utils.setBitInWordArray(iArr, i2, (bitFromWordArray2 & bitFromWordArray3) ^ bitFromWordArray);
            int i4 = bitFromWordArray ^ bitFromWordArray2;
            Utils.setBitInWordArray(iArr, i3, (bitFromWordArray & bitFromWordArray3) ^ i4);
            Utils.setBitInWordArray(iArr, i, (i4 ^ bitFromWordArray3) ^ (bitFromWordArray & bitFromWordArray2));
        }
    }

    private void tapesToWords(int[] iArr, Tape tape) {
        for (int i = 0; i < this.stateSizeBits; i++) {
            iArr[i] = tape.tapesToWord();
        }
    }

    private int verify(Signature signature, int[] iArr, int[] iArr2, byte[] bArr) {
        int i;
        int i2 = 2;
        byte[][][] bArr2 = (byte[][][]) Array.newInstance(Byte.TYPE, this.numMPCRounds, this.numMPCParties, this.digestSizeBytes);
        byte[][][] bArr3 = (byte[][][]) Array.newInstance(Byte.TYPE, this.numMPCRounds, 3, this.UnruhGWithInputBytes);
        int[][][] iArr3 = (int[][][]) Array.newInstance(Integer.TYPE, this.numMPCRounds, 3, this.stateSizeBytes);
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr4 = signature.challengeBits;
        int i3 = this.stateSizeBytes;
        byte[] bArr5 = new byte[Math.max(i3 * 6, i3 + this.andSizeBytes)];
        Tape tape = new Tape(this);
        int i4 = this.numMPCRounds;
        View[] viewArr = new View[i4];
        View[] viewArr2 = new View[i4];
        int i5 = 0;
        while (true) {
            i = this.numMPCRounds;
            if (i5 >= i) {
                break;
            }
            viewArr[i5] = new View(this);
            View view = new View(this);
            viewArr2[i5] = view;
            int i6 = i5;
            View[] viewArr3 = viewArr2;
            View[] viewArr4 = viewArr;
            Tape tape2 = tape;
            byte[] bArr6 = bArr5;
            byte[] bArr7 = bArr4;
            Signature.Proof[] proofArr2 = proofArr;
            verifyProof(proofArr[i5], viewArr[i5], view, getChallenge(bArr4, i5), signature.salt, i6, bArr5, iArr2, tape2);
            int challenge = getChallenge(bArr7, i6);
            Commit(proofArr2[i6].seed1, 0, viewArr4[i6], bArr2[i6][challenge]);
            int i7 = (challenge + 1) % 3;
            Commit(proofArr2[i6].seed2, 0, viewArr3[i6], bArr2[i6][i7]);
            int i8 = (challenge + 2) % 3;
            System.arraycopy(proofArr2[i6].view3Commitment, 0, bArr2[i6][i8], 0, this.digestSizeBytes);
            if (this.transform == 1) {
                G(challenge, proofArr2[i6].seed1, 0, viewArr4[i6], bArr3[i6][challenge]);
                G(i7, proofArr2[i6].seed2, 0, viewArr3[i6], bArr3[i6][i7]);
                System.arraycopy(proofArr2[i6].view3UnruhG, 0, bArr3[i6][i8], 0, challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
            }
            iArr3[i6][challenge] = viewArr4[i6].outputShare;
            iArr3[i6][i7] = viewArr3[i6].outputShare;
            int[] iArr4 = new int[this.stateSizeWords];
            xor_three(iArr4, viewArr4[i6].outputShare, viewArr3[i6].outputShare, iArr, this.stateSizeBytes);
            iArr3[i6][i8] = iArr4;
            i5 = i6 + 1;
            bArr4 = bArr7;
            viewArr = viewArr4;
            tape = tape2;
            viewArr2 = viewArr3;
            bArr5 = bArr6;
            proofArr = proofArr2;
            i2 = 2;
        }
        byte[] bArr8 = new byte[Utils.numBytes(i * i2)];
        H3(iArr, iArr2, iArr3, bArr2, bArr8, signature.salt, bArr, bArr3);
        if (subarrayEquals(bArr4, bArr8, Utils.numBytes(this.numMPCRounds * 2))) {
            return 0;
        }
        LOG.fine("Invalid signature. Did not verify");
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x00f6, code lost:
        r1 = r1.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int verify_picnic3(org.bouncycastle.pqc.crypto.picnic.Signature2 r32, int[] r33, int[] r34, byte[] r35) {
        /*
            Method dump skipped, instructions count: 785
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.picnic.PicnicEngine.verify_picnic3(org.bouncycastle.pqc.crypto.picnic.Signature2, int[], int[], byte[]):int");
    }

    private void wordToMsgs(int i, Msg msg) {
        for (int i2 = 0; i2 < this.numMPCParties; i2++) {
            Utils.setBit(msg.msgs[i2], msg.pos, (byte) (Utils.getBit(Pack.intToLittleEndian(i), i2) & UByte.MAX_VALUE));
        }
        msg.pos++;
    }

    private void xor_three(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i) {
        int i2 = this.stateSizeWords;
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = (iArr2[i3] ^ iArr3[i3]) ^ iArr4[i3];
        }
    }

    private void zeroTrailingBits(byte[] bArr, int i) {
        int numBytes = Utils.numBytes(i);
        while (i < numBytes * 8) {
            Utils.setBit(bArr, i, (byte) 0);
            i++;
        }
    }

    void H3(int[] iArr, int[] iArr2, int[][][] iArr3, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        byte[] bArr6 = new byte[this.digestSizeBytes];
        bArr2[Utils.numBytes(this.numMPCRounds * 2) - 1] = 0;
        this.digest.update((byte) 1);
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.digest.update(Pack.intToLittleEndian(iArr3[i][i2]), 0, this.stateSizeBytes);
            }
        }
        for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
            for (int i4 = 0; i4 < 3; i4++) {
                this.digest.update(bArr[i3][i4], 0, this.digestSizeBytes);
            }
        }
        if (this.transform == 1) {
            for (int i5 = 0; i5 < this.numMPCRounds; i5++) {
                int i6 = 0;
                while (i6 < 3) {
                    this.digest.update(bArr5[i5][i6], 0, i6 == 2 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
                    i6++;
                }
            }
        }
        this.digest.update(Pack.intToLittleEndian(iArr), 0, this.stateSizeBytes);
        this.digest.update(Pack.intToLittleEndian(iArr2), 0, this.stateSizeBytes);
        this.digest.update(bArr3, 0, 32);
        this.digest.update(bArr4, 0, bArr4.length);
        this.digest.doFinal(bArr6, 0, this.digestSizeBytes);
        boolean z = true;
        int i7 = 0;
        while (z) {
            for (int i8 = 0; i8 < this.digestSizeBytes; i8++) {
                byte b = bArr6[i8];
                int i9 = 0;
                while (true) {
                    if (i9 >= 8) {
                        break;
                    }
                    int i10 = (b >>> (6 - i9)) & 3;
                    if (i10 < 3) {
                        setChallenge(bArr2, i7, i10);
                        i7++;
                        if (i7 == this.numMPCRounds) {
                            z = false;
                            break;
                        }
                    }
                    i9 += 2;
                }
                if (!z) {
                    break;
                }
            }
            if (!z) {
                return;
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr6, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr6, 0, this.digestSizeBytes);
        }
    }

    public void aux_mpc_sbox(int[] iArr, int[] iArr2, Tape tape) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i3);
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            int bitFromWordArray4 = Utils.getBitFromWordArray(iArr2, i2);
            int bitFromWordArray5 = Utils.getBitFromWordArray(iArr2, i3);
            aux_mpc_AND(bitFromWordArray, bitFromWordArray2, ((Utils.getBitFromWordArray(iArr2, i) ^ bitFromWordArray) ^ bitFromWordArray2) ^ bitFromWordArray3, tape);
            aux_mpc_AND(bitFromWordArray2, bitFromWordArray3, bitFromWordArray4 ^ bitFromWordArray, tape);
            aux_mpc_AND(bitFromWordArray3, bitFromWordArray, (bitFromWordArray5 ^ bitFromWordArray) ^ bitFromWordArray2, tape);
        }
    }

    public void crypto_sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (picnic_sign(bArr3, bArr2, bArr)) {
            System.arraycopy(bArr2, 0, bArr, 4, bArr2.length);
        }
    }

    public void crypto_sign_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[32];
        picnic_keygen(bArr3, bArr4, bArr5, secureRandom);
        picnic_write_public_key(bArr4, bArr3, bArr);
        picnic_write_private_key(bArr5, bArr4, bArr3, bArr2);
    }

    public boolean crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (picnic_verify(bArr3, Arrays.copyOfRange(bArr2, 4, bArr.length + 4), bArr2, Pack.littleEndianToInt(bArr2, 0)) == -1) {
            return false;
        }
        System.arraycopy(bArr2, 4, bArr, 0, bArr.length);
        return true;
    }

    int getChallenge(byte[] bArr, int i) {
        int i2 = i * 2;
        return Utils.getBit(bArr, i2) | (Utils.getBit(bArr, i2 + 1) << 1);
    }

    public int getPublicKeySize() {
        return this.CRYPTO_PUBLICKEYBYTES;
    }

    public int getSecretKeySize() {
        return this.CRYPTO_SECRETKEYBYTES;
    }

    public int getSignatureSize(int i) {
        return this.CRYPTO_BYTES + i;
    }

    public int getTrueSignatureSize() {
        return this.signatureLength;
    }

    public void matrix_mul(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        matrix_mul_offset(iArr, 0, iArr2, 0, iArr3, i);
    }

    protected void matrix_mul_offset(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int[] iArr4 = new int[16];
        iArr4[this.stateSizeWords - 1] = 0;
        int i4 = this.stateSizeBits / 32;
        for (int i5 = 0; i5 < this.stateSizeBits; i5++) {
            int i6 = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                i6 ^= iArr3[((this.stateSizeWords * i5) + i7) + i3] & iArr2[i7 + i2];
            }
            for (int i8 = i4 * 32; i8 < this.stateSizeBits; i8++) {
                i6 ^= Utils.getBitFromWordArray(iArr3, (i3 * 32) + (((this.stateSizeWords * i5) * 32) + i8)) & Utils.getBitFromWordArray(iArr2, (i2 * 32) + i8);
            }
            Utils.setBit(iArr4, i5, Utils.parity32(i6));
        }
        System.arraycopy(iArr4, 0, iArr, i, this.stateSizeWords);
    }

    void mpc_AND_verify(int[] iArr, int[] iArr2, int[] iArr3, Tape tape, View view, View view2) {
        byte bit = Utils.getBit(tape.tapes[1], tape.pos);
        int[] iArr4 = {Utils.getBit(tape.tapes[0], tape.pos), bit};
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr2[0];
        iArr3[0] = ((((i2 & i3) ^ (iArr2[1] & i)) ^ (i3 & i)) ^ iArr4[0]) ^ bit;
        Utils.setBit(view.communicatedBits, tape.pos, (byte) (iArr3[0] & 255));
        iArr3[1] = Utils.getBit(view2.communicatedBits, tape.pos);
        tape.pos++;
    }

    void mpc_LowMC_verify(View view, View view2, Tape tape, int[] iArr, int[] iArr2, int i) {
        Arrays.fill(iArr, 0, iArr.length, 0);
        mpc_xor_constant_verify(iArr, iArr2, 0, this.stateSizeWords, i);
        KMatricesWithPointer KMatrix = LowmcConstants.KMatrix(this, 0);
        matrix_mul_offset(iArr, 0, view.inputShare, 0, KMatrix.getData(), KMatrix.getMatrixPointer());
        matrix_mul_offset(iArr, this.stateSizeWords, view2.inputShare, 0, KMatrix.getData(), KMatrix.getMatrixPointer());
        mpc_xor(iArr, iArr, this.stateSizeWords, 2);
        for (int i2 = 1; i2 <= this.numRounds; i2++) {
            KMatricesWithPointer KMatrix2 = LowmcConstants.KMatrix(this, i2);
            matrix_mul_offset(iArr, 0, view.inputShare, 0, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            matrix_mul_offset(iArr, this.stateSizeWords, view2.inputShare, 0, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            mpc_substitution_verify(iArr, tape, view, view2);
            int i3 = i2 - 1;
            KMatricesWithPointer LMatrix = LowmcConstants.LMatrix(this, i3);
            int i4 = this.stateSizeWords;
            mpc_matrix_mul(iArr, i4 * 2, iArr, i4 * 2, LMatrix.getData(), LMatrix.getMatrixPointer(), 2);
            KMatricesWithPointer RConstant = LowmcConstants.RConstant(this, i3);
            mpc_xor_constant_verify(iArr, RConstant.getData(), RConstant.getMatrixPointer(), this.stateSizeWords, i);
            mpc_xor(iArr, iArr, this.stateSizeWords, 2);
        }
        System.arraycopy(iArr, this.stateSizeWords * 2, view.outputShare, 0, this.stateSizeWords);
        System.arraycopy(iArr, this.stateSizeWords * 3, view2.outputShare, 0, this.stateSizeWords);
    }

    void mpc_substitution_verify(int[] iArr, Tape tape, View view, View view2) {
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        int[] iArr4 = new int[2];
        int[] iArr5 = new int[2];
        int[] iArr6 = new int[2];
        int[] iArr7 = new int[2];
        int i = 0;
        while (i < this.numSboxes * 3) {
            for (int i2 = 0; i2 < 2; i2++) {
                int i3 = ((i2 + 2) * this.stateSizeWords * 32) + i;
                iArr2[i2] = Utils.getBitFromWordArray(iArr, i3 + 2);
                iArr3[i2] = Utils.getBitFromWordArray(iArr, i3 + 1);
                iArr4[i2] = Utils.getBitFromWordArray(iArr, i3);
            }
            int i4 = i;
            mpc_AND_verify(iArr2, iArr3, iArr5, tape, view, view2);
            mpc_AND_verify(iArr3, iArr4, iArr6, tape, view, view2);
            mpc_AND_verify(iArr4, iArr2, iArr7, tape, view, view2);
            for (int i5 = 0; i5 < 2; i5++) {
                int i6 = ((i5 + 2) * this.stateSizeWords * 32) + i4;
                Utils.setBitInWordArray(iArr, i6 + 2, iArr2[i5] ^ iArr6[i5]);
                Utils.setBitInWordArray(iArr, i6 + 1, (iArr2[i5] ^ iArr3[i5]) ^ iArr7[i5]);
                Utils.setBitInWordArray(iArr, i6, ((iArr2[i5] ^ iArr3[i5]) ^ iArr4[i5]) ^ iArr5[i5]);
            }
            i = i4 + 3;
        }
    }

    void prove(Signature.Proof proof, int i, byte[] bArr, int i2, View[] viewArr, byte[][] bArr2, byte[][] bArr3) {
        if (i == 0) {
            System.arraycopy(bArr, (this.seedSizeBytes * 0) + i2, proof.seed1, 0, this.seedSizeBytes);
            System.arraycopy(bArr, i2 + (this.seedSizeBytes * 1), proof.seed2, 0, this.seedSizeBytes);
        } else if (i == 1) {
            System.arraycopy(bArr, (this.seedSizeBytes * 1) + i2, proof.seed1, 0, this.seedSizeBytes);
            System.arraycopy(bArr, i2 + (this.seedSizeBytes * 2), proof.seed2, 0, this.seedSizeBytes);
        } else if (i == 2) {
            System.arraycopy(bArr, (this.seedSizeBytes * 2) + i2, proof.seed1, 0, this.seedSizeBytes);
            System.arraycopy(bArr, i2 + (this.seedSizeBytes * 0), proof.seed2, 0, this.seedSizeBytes);
        } else {
            LOG.fine("Invalid challenge");
        }
        if (i == 1 || i == 2) {
            System.arraycopy(viewArr[2].inputShare, 0, proof.inputShare, 0, this.stateSizeBytes);
        }
        System.arraycopy(viewArr[(i + 1) % 3].communicatedBits, 0, proof.communicatedBits, 0, this.andSizeBytes);
        int i3 = (i + 2) % 3;
        System.arraycopy(bArr2[i3], 0, proof.view3Commitment, 0, this.digestSizeBytes);
        if (this.transform == 1) {
            System.arraycopy(bArr3[i3], 0, proof.view3UnruhG, 0, i == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
        }
    }

    int serializeSignature(Signature signature, byte[] bArr, int i) {
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr2 = signature.challengeBits;
        int i2 = this.numMPCRounds;
        int numBytes = Utils.numBytes(this.numMPCRounds * 2) + 32 + (((this.seedSizeBytes * 2) + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes) * i2);
        if (this.transform == 1) {
            numBytes += this.UnruhGWithoutInputBytes * i2;
        }
        if (this.CRYPTO_BYTES < numBytes) {
            return -1;
        }
        System.arraycopy(bArr2, 0, bArr, i, Utils.numBytes(i2 * 2));
        int numBytes2 = Utils.numBytes(this.numMPCRounds * 2) + i;
        System.arraycopy(signature.salt, 0, bArr, numBytes2, 32);
        int i3 = numBytes2 + 32;
        for (int i4 = 0; i4 < this.numMPCRounds; i4++) {
            int challenge = getChallenge(bArr2, i4);
            System.arraycopy(proofArr[i4].view3Commitment, 0, bArr, i3, this.digestSizeBytes);
            int i5 = i3 + this.digestSizeBytes;
            if (this.transform == 1) {
                int i6 = challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes;
                System.arraycopy(proofArr[i4].view3UnruhG, 0, bArr, i5, i6);
                i5 += i6;
            }
            System.arraycopy(proofArr[i4].communicatedBits, 0, bArr, i5, this.andSizeBytes);
            int i7 = i5 + this.andSizeBytes;
            System.arraycopy(proofArr[i4].seed1, 0, bArr, i7, this.seedSizeBytes);
            int i8 = i7 + this.seedSizeBytes;
            System.arraycopy(proofArr[i4].seed2, 0, bArr, i8, this.seedSizeBytes);
            i3 = i8 + this.seedSizeBytes;
            if (challenge == 1 || challenge == 2) {
                Pack.intToLittleEndian(proofArr[i4].inputShare, 0, this.stateSizeWords, bArr, i3);
                i3 += this.stateSizeBytes;
            }
        }
        return i3 - i;
    }

    boolean verifyProof(Signature.Proof proof, View view, View view2, int i, byte[] bArr, int i2, byte[] bArr2, int[] iArr, Tape tape) {
        boolean z;
        boolean z2;
        System.arraycopy(proof.communicatedBits, 0, view2.communicatedBits, 0, this.andSizeBytes);
        tape.pos = 0;
        if (i == 0) {
            z = true;
            boolean createRandomTape = createRandomTape(proof.seed1, 0, bArr, i2, 0, bArr2, this.stateSizeBytes + this.andSizeBytes);
            Pack.littleEndianToInt(bArr2, 0, view.inputShare);
            System.arraycopy(bArr2, this.stateSizeBytes, tape.tapes[0], 0, this.andSizeBytes);
            z2 = createRandomTape && createRandomTape(proof.seed2, 0, bArr, i2, 1, bArr2, this.stateSizeBytes + this.andSizeBytes);
            if (z2) {
                Pack.littleEndianToInt(bArr2, 0, view2.inputShare);
                System.arraycopy(bArr2, this.stateSizeBytes, tape.tapes[1], 0, this.andSizeBytes);
            }
        } else if (i == 1) {
            z = true;
            boolean createRandomTape2 = createRandomTape(proof.seed1, 0, bArr, i2, 1, bArr2, this.stateSizeBytes + this.andSizeBytes);
            Pack.littleEndianToInt(bArr2, 0, view.inputShare);
            System.arraycopy(bArr2, this.stateSizeBytes, tape.tapes[0], 0, this.andSizeBytes);
            z2 = createRandomTape2 && createRandomTape(proof.seed2, 0, bArr, i2, 2, tape.tapes[1], this.andSizeBytes);
            if (z2) {
                System.arraycopy(proof.inputShare, 0, view2.inputShare, 0, this.stateSizeBytes);
            }
        } else if (i != 2) {
            LOG.fine("Invalid Challenge!");
            z = true;
            z2 = false;
        } else {
            z = true;
            boolean createRandomTape3 = createRandomTape(proof.seed1, 0, bArr, i2, 2, tape.tapes[0], this.andSizeBytes);
            System.arraycopy(proof.inputShare, 0, view.inputShare, 0, this.stateSizeBytes);
            z2 = createRandomTape3 && createRandomTape(proof.seed2, 0, bArr, i2, 0, bArr2, this.stateSizeBytes + this.andSizeBytes);
            if (z2) {
                Pack.littleEndianToInt(bArr2, 0, view2.inputShare);
                System.arraycopy(bArr2, this.stateSizeBytes, tape.tapes[1], 0, this.andSizeBytes);
            }
        }
        if (!z2) {
            LOG.fine("Failed to generate random tapes, signature verification will fail (but signature may actually be valid)");
            return false;
        }
        int i3 = this.stateSizeBytes * 4;
        byte[] bArr3 = new byte[i3];
        Pack.intToLittleEndian(view.inputShare, bArr3, 0);
        Arrays.fill(bArr3, this.stateSizeBytes, i3, (byte) 0);
        zeroTrailingBits(bArr3, this.stateSizeBits);
        Pack.littleEndianToInt(bArr3, 0, view.inputShare);
        Pack.intToLittleEndian(view2.inputShare, bArr3, 0);
        Arrays.fill(bArr3, this.stateSizeBytes, i3, (byte) 0);
        zeroTrailingBits(bArr3, this.stateSizeBits);
        Pack.littleEndianToInt(bArr3, 0, view2.inputShare);
        mpc_LowMC_verify(view, view2, tape, Pack.littleEndianToInt(bArr2, 0, bArr2.length / 4), iArr, i);
        return z;
    }

    public void xor_array(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = iArr2[i3] ^ iArr3[i3 + i];
        }
    }
}
