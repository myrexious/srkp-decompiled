package com.tom_roush.pdfbox.pdmodel.encryption;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public final class StandardSecurityHandler extends SecurityHandler {
    public static final String FILTER = "Standard";
    public static final Class<?> PROTECTION_POLICY_CLASS = StandardProtectionPolicy.class;
    private static final byte[] ENCRYPT_PADDING = {BuiltinOptions.ArgMaxOptions, ByteSourceJsonBootstrapper.UTF8_BOM_3, BuiltinOptions.AbsOptions, BuiltinOptions.DepthToSpaceOptions, BuiltinOptions.AbsOptions, BuiltinOptions.DynamicUpdateSliceOptions, -118, BuiltinOptions.FloorDivOptions, BuiltinOptions.SegmentSumOptions, 0, BuiltinOptions.AbsOptions, BuiltinOptions.RankOptions, -1, -6, 1, 8, BuiltinOptions.LessEqualOptions, BuiltinOptions.LessEqualOptions, 0, -74, -48, BuiltinOptions.BroadcastToOptions, BuiltinOptions.LogicalAndOptions, ByteCompanionObject.MIN_VALUE, BuiltinOptions.SelectOptions, 12, -87, -2, BuiltinOptions.SegmentSumOptions, BuiltinOptions.GatherNdOptions, BuiltinOptions.Rfft2dOptions, 122};
    private static final String[] HASHES_2B = {"SHA-256", "SHA-384", "SHA-512"};

    public StandardSecurityHandler() {
    }

    public StandardSecurityHandler(StandardProtectionPolicy standardProtectionPolicy) {
        setProtectionPolicy(standardProtectionPolicy);
        setKeyLength(standardProtectionPolicy.getEncryptionKeyLength());
    }

    private int computeRevisionNumber(int i) {
        AccessPermission permissions = ((StandardProtectionPolicy) getProtectionPolicy()).getPermissions();
        if (i >= 2 || permissions.hasAnyRevision3PermissionSet()) {
            if (i == 5) {
                return 6;
            }
            if (i == 4) {
                return 4;
            }
            return (i == 2 || i == 3 || permissions.hasAnyRevision3PermissionSet()) ? 3 : 4;
        }
        return 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016b  */
    @Override // com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void prepareForDecryption(com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption r23, com.tom_roush.pdfbox.cos.COSArray r24, com.tom_roush.pdfbox.pdmodel.encryption.DecryptionMaterial r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.encryption.StandardSecurityHandler.prepareForDecryption(com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption, com.tom_roush.pdfbox.cos.COSArray, com.tom_roush.pdfbox.pdmodel.encryption.DecryptionMaterial):void");
    }

    private byte[] getDocumentIDBytes(COSArray cOSArray) {
        return (cOSArray == null || cOSArray.size() < 1) ? new byte[0] : ((COSString) cOSArray.getObject(0)).getBytes();
    }

    private void validatePerms(PDEncryption pDEncryption, int i, boolean z) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, new SecretKeySpec(getEncryptionKey(), "AES"));
            byte[] doFinal = cipher.doFinal(pDEncryption.getPerms());
            if (doFinal[9] != 97 || doFinal[10] != 100 || doFinal[11] != 98) {
                Log.w("PdfBox-Android", "Verification of permissions failed (constant)");
            }
            int i2 = (doFinal[0] & UByte.MAX_VALUE) | ((doFinal[1] & UByte.MAX_VALUE) << 8) | ((doFinal[2] & UByte.MAX_VALUE) << 16) | ((doFinal[3] & UByte.MAX_VALUE) << 24);
            if (i2 != i) {
                Log.w("PdfBox-Android", "Verification of permissions failed (" + String.format("%08X", Integer.valueOf(i2)) + " != " + String.format("%08X", Integer.valueOf(i)) + ")");
            }
            if ((!z || doFinal[8] == 84) && (z || doFinal[8] == 70)) {
                return;
            }
            Log.w("PdfBox-Android", "Verification of permissions failed (EncryptMetadata)");
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler
    public void prepareDocumentForEncryption(PDDocument pDDocument) throws IOException {
        PDEncryption encryption = pDDocument.getEncryption();
        if (encryption == null) {
            encryption = new PDEncryption();
        }
        int computeVersionNumber = computeVersionNumber();
        int computeRevisionNumber = computeRevisionNumber(computeVersionNumber);
        encryption.setFilter("Standard");
        encryption.setVersion(computeVersionNumber);
        if (computeVersionNumber != 4 && computeVersionNumber != 5) {
            encryption.removeV45filters();
        }
        encryption.setRevision(computeRevisionNumber);
        encryption.setLength(getKeyLength());
        StandardProtectionPolicy standardProtectionPolicy = (StandardProtectionPolicy) getProtectionPolicy();
        String ownerPassword = standardProtectionPolicy.getOwnerPassword();
        String userPassword = standardProtectionPolicy.getUserPassword();
        if (ownerPassword == null) {
            ownerPassword = "";
        }
        if (userPassword == null) {
            userPassword = "";
        }
        if (ownerPassword.isEmpty()) {
            ownerPassword = userPassword;
        }
        int permissionBytes = standardProtectionPolicy.getPermissions().getPermissionBytes();
        encryption.setPermissions(permissionBytes);
        int keyLength = getKeyLength() / 8;
        if (computeRevisionNumber == 6) {
            prepareEncryptionDictRev6(SaslPrep.saslPrepStored(ownerPassword), SaslPrep.saslPrepStored(userPassword), encryption, permissionBytes);
        } else {
            prepareEncryptionDictRev2345(ownerPassword, userPassword, encryption, permissionBytes, pDDocument, computeRevisionNumber, keyLength);
        }
        pDDocument.setEncryptionDictionary(encryption);
        pDDocument.getDocument().setEncryptionDictionary(encryption.getCOSObject());
    }

    private void prepareEncryptionDictRev6(String str, String str2, PDEncryption pDEncryption, int i) throws IOException {
        try {
            SecureRandom secureRandom = new SecureRandom();
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            setEncryptionKey(new byte[32]);
            secureRandom.nextBytes(getEncryptionKey());
            byte[] truncate127 = truncate127(str2.getBytes(Charsets.UTF_8));
            byte[] bArr = new byte[8];
            byte[] bArr2 = new byte[8];
            secureRandom.nextBytes(bArr);
            secureRandom.nextBytes(bArr2);
            byte[] concat = concat(computeHash2B(concat(truncate127, bArr), truncate127, null), bArr, bArr2);
            cipher.init(1, new SecretKeySpec(computeHash2B(concat(truncate127, bArr2), truncate127, null), "AES"), new IvParameterSpec(new byte[16]));
            byte[] doFinal = cipher.doFinal(getEncryptionKey());
            byte[] truncate1272 = truncate127(str.getBytes(Charsets.UTF_8));
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = new byte[8];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr4);
            byte[] concat2 = concat(computeHash2B(concat(truncate1272, bArr3, concat), truncate1272, concat), bArr3, bArr4);
            cipher.init(1, new SecretKeySpec(computeHash2B(concat(truncate1272, bArr4, concat), truncate1272, concat), "AES"), new IvParameterSpec(new byte[16]));
            byte[] doFinal2 = cipher.doFinal(getEncryptionKey());
            pDEncryption.setUserKey(concat);
            pDEncryption.setUserEncryptionKey(doFinal);
            pDEncryption.setOwnerKey(concat2);
            pDEncryption.setOwnerEncryptionKey(doFinal2);
            prepareEncryptionDictAES(pDEncryption, COSName.AESV3);
            byte[] bArr5 = new byte[16];
            bArr5[0] = (byte) i;
            bArr5[1] = (byte) (i >>> 8);
            bArr5[2] = (byte) (i >>> 16);
            bArr5[3] = (byte) (i >>> 24);
            bArr5[4] = -1;
            bArr5[5] = -1;
            bArr5[6] = -1;
            bArr5[7] = -1;
            bArr5[8] = BuiltinOptions.CosOptions;
            bArr5[9] = BuiltinOptions.ScatterNdOptions;
            bArr5[10] = BuiltinOptions.SegmentSumOptions;
            bArr5[11] = BuiltinOptions.SelectV2Options;
            for (int i2 = 12; i2 <= 15; i2++) {
                bArr5[i2] = (byte) secureRandom.nextInt();
            }
            cipher.init(1, new SecretKeySpec(getEncryptionKey(), "AES"), new IvParameterSpec(new byte[16]));
            pDEncryption.setPerms(cipher.doFinal(bArr5));
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    private void prepareEncryptionDictRev2345(String str, String str2, PDEncryption pDEncryption, int i, PDDocument pDDocument, int i2, int i3) throws IOException {
        COSArray documentID = pDDocument.getDocument().getDocumentID();
        if (documentID == null || documentID.size() < 2) {
            MessageDigest md5 = MessageDigests.getMD5();
            md5.update(BigInteger.valueOf(System.currentTimeMillis()).toByteArray());
            md5.update(str.getBytes(Charsets.ISO_8859_1));
            md5.update(str2.getBytes(Charsets.ISO_8859_1));
            md5.update(pDDocument.getDocument().toString().getBytes(Charsets.ISO_8859_1));
            COSString cOSString = new COSString(md5.digest(toString().getBytes(Charsets.ISO_8859_1)));
            documentID = new COSArray();
            documentID.add((COSBase) cOSString);
            documentID.add((COSBase) cOSString);
            pDDocument.getDocument().setDocumentID(documentID);
        }
        COSString cOSString2 = (COSString) documentID.getObject(0);
        byte[] computeOwnerPassword = computeOwnerPassword(str.getBytes(Charsets.ISO_8859_1), str2.getBytes(Charsets.ISO_8859_1), i2, i3);
        byte[] computeUserPassword = computeUserPassword(str2.getBytes(Charsets.ISO_8859_1), computeOwnerPassword, i, cOSString2.getBytes(), i2, i3, true);
        setEncryptionKey(computeEncryptedKey(str2.getBytes(Charsets.ISO_8859_1), computeOwnerPassword, null, null, null, i, cOSString2.getBytes(), i2, i3, true, false));
        pDEncryption.setOwnerKey(computeOwnerPassword);
        pDEncryption.setUserKey(computeUserPassword);
        if (i2 == 4) {
            prepareEncryptionDictAES(pDEncryption, COSName.AESV2);
        }
    }

    private void prepareEncryptionDictAES(PDEncryption pDEncryption, COSName cOSName) {
        PDCryptFilterDictionary pDCryptFilterDictionary = new PDCryptFilterDictionary();
        pDCryptFilterDictionary.setCryptFilterMethod(cOSName);
        pDCryptFilterDictionary.setLength(getKeyLength());
        pDEncryption.setStdCryptFilterDictionary(pDCryptFilterDictionary);
        pDEncryption.setStreamFilterName(COSName.STD_CF);
        pDEncryption.setStringFilterName(COSName.STD_CF);
        setAES(true);
    }

    public boolean isOwnerPassword(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) throws IOException {
        byte[] computeHash2A;
        if (i2 == 6 || i2 == 5) {
            byte[] truncate127 = truncate127(bArr);
            byte[] bArr5 = new byte[32];
            byte[] bArr6 = new byte[8];
            if (bArr3.length < 40) {
                throw new IOException("Owner password is too short");
            }
            System.arraycopy(bArr3, 0, bArr5, 0, 32);
            System.arraycopy(bArr3, 32, bArr6, 0, 8);
            if (i2 == 5) {
                computeHash2A = computeSHA256(truncate127, bArr6, bArr2);
            } else {
                computeHash2A = computeHash2A(truncate127, bArr6, bArr2);
            }
            return Arrays.equals(computeHash2A, bArr5);
        }
        return isUserPassword(getUserPassword(bArr, bArr3, i2, i3), bArr2, bArr3, i, bArr4, i2, i3, z);
    }

    public byte[] getUserPassword(byte[] bArr, byte[] bArr2, int i, int i2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] computeRC4key = computeRC4key(bArr, i, i2);
        if (i == 2) {
            encryptDataRC4(computeRC4key, bArr2, byteArrayOutputStream);
        } else if (i == 3 || i == 4) {
            int length = computeRC4key.length;
            byte[] bArr3 = new byte[length];
            byte[] bArr4 = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
            for (int i3 = 19; i3 >= 0; i3--) {
                System.arraycopy(computeRC4key, 0, bArr3, 0, computeRC4key.length);
                for (int i4 = 0; i4 < length; i4++) {
                    bArr3[i4] = (byte) (bArr3[i4] ^ ((byte) i3));
                }
                byteArrayOutputStream.reset();
                encryptDataRC4(bArr3, bArr4, byteArrayOutputStream);
                bArr4 = byteArrayOutputStream.toByteArray();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] computeEncryptedKey(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i, byte[] bArr6, int i2, int i3, boolean z, boolean z2) throws IOException {
        if (i2 == 6 || i2 == 5) {
            return computeEncryptedKeyRev56(bArr, z2, bArr2, bArr3, bArr4, bArr5, i2);
        }
        return computeEncryptedKeyRev234(bArr, bArr2, i, bArr6, z, i3, i2);
    }

    private byte[] computeEncryptedKeyRev234(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, boolean z, int i2, int i3) {
        byte[] truncateOrPad = truncateOrPad(bArr);
        MessageDigest md5 = MessageDigests.getMD5();
        md5.update(truncateOrPad);
        md5.update(bArr2);
        md5.update((byte) i);
        md5.update((byte) (i >>> 8));
        md5.update((byte) (i >>> 16));
        md5.update((byte) (i >>> 24));
        md5.update(bArr3);
        if (i3 == 4 && !z) {
            md5.update(new byte[]{-1, -1, -1, -1});
        }
        byte[] digest = md5.digest();
        if (i3 == 3 || i3 == 4) {
            for (int i4 = 0; i4 < 50; i4++) {
                md5.update(digest, 0, i2);
                digest = md5.digest();
            }
        }
        byte[] bArr4 = new byte[i2];
        System.arraycopy(digest, 0, bArr4, 0, i2);
        return bArr4;
    }

    private byte[] computeEncryptedKeyRev56(byte[] bArr, boolean z, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i) throws IOException {
        byte[] computeHash2A;
        if (z) {
            if (bArr4 == null) {
                throw new IOException("/Encrypt/OE entry is missing");
            }
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr2, 40, bArr6, 0, 8);
            if (i == 5) {
                computeHash2A = computeSHA256(bArr, bArr6, bArr3);
            } else {
                computeHash2A = computeHash2A(bArr, bArr6, bArr3);
            }
        } else if (bArr5 == null) {
            throw new IOException("/Encrypt/UE entry is missing");
        } else {
            byte[] bArr7 = new byte[8];
            System.arraycopy(bArr3, 40, bArr7, 0, 8);
            if (i == 5) {
                computeHash2A = computeSHA256(bArr, bArr7, null);
            } else {
                computeHash2A = computeHash2A(bArr, bArr7, null);
            }
            bArr4 = bArr5;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, new SecretKeySpec(computeHash2A, "AES"), new IvParameterSpec(new byte[16]));
            return cipher.doFinal(bArr4);
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    public byte[] computeUserPassword(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] computeEncryptedKey = computeEncryptedKey(bArr, bArr2, null, null, null, i, bArr3, i2, i3, z, true);
        if (i2 == 2) {
            encryptDataRC4(computeEncryptedKey, ENCRYPT_PADDING, byteArrayOutputStream);
        } else if (i2 == 3 || i2 == 4) {
            MessageDigest md5 = MessageDigests.getMD5();
            md5.update(ENCRYPT_PADDING);
            md5.update(bArr3);
            byteArrayOutputStream.write(md5.digest());
            int length = computeEncryptedKey.length;
            byte[] bArr4 = new byte[length];
            for (int i4 = 0; i4 < 20; i4++) {
                System.arraycopy(computeEncryptedKey, 0, bArr4, 0, length);
                for (int i5 = 0; i5 < length; i5++) {
                    bArr4[i5] = (byte) (bArr4[i5] ^ i4);
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                encryptDataRC4(bArr4, byteArrayInputStream, byteArrayOutputStream);
            }
            byte[] bArr5 = new byte[32];
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr5, 0, 16);
            System.arraycopy(ENCRYPT_PADDING, 0, bArr5, 16, 16);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(bArr5);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] computeOwnerPassword(byte[] bArr, byte[] bArr2, int i, int i2) throws IOException {
        if (i == 2 && i2 != 5) {
            throw new IOException("Expected length=5 actual=" + i2);
        }
        byte[] computeRC4key = computeRC4key(bArr, i, i2);
        byte[] truncateOrPad = truncateOrPad(bArr2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encryptDataRC4(computeRC4key, new ByteArrayInputStream(truncateOrPad), byteArrayOutputStream);
        if (i == 3 || i == 4) {
            int length = computeRC4key.length;
            byte[] bArr3 = new byte[length];
            for (int i3 = 1; i3 < 20; i3++) {
                System.arraycopy(computeRC4key, 0, bArr3, 0, computeRC4key.length);
                for (int i4 = 0; i4 < length; i4++) {
                    bArr3[i4] = (byte) (bArr3[i4] ^ ((byte) i3));
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                encryptDataRC4(bArr3, byteArrayInputStream, byteArrayOutputStream);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] computeRC4key(byte[] bArr, int i, int i2) {
        MessageDigest md5 = MessageDigests.getMD5();
        byte[] digest = md5.digest(truncateOrPad(bArr));
        if (i == 3 || i == 4) {
            for (int i3 = 0; i3 < 50; i3++) {
                md5.update(digest, 0, i2);
                digest = md5.digest();
            }
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(digest, 0, bArr2, 0, i2);
        return bArr2;
    }

    private byte[] truncateOrPad(byte[] bArr) {
        byte[] bArr2 = ENCRYPT_PADDING;
        int length = bArr2.length;
        byte[] bArr3 = new byte[length];
        int min = Math.min(bArr.length, length);
        System.arraycopy(bArr, 0, bArr3, 0, min);
        System.arraycopy(bArr2, 0, bArr3, min, bArr2.length - min);
        return bArr3;
    }

    public boolean isUserPassword(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) throws IOException {
        if (i2 == 2 || i2 == 3 || i2 == 4) {
            return isUserPassword234(bArr, bArr2, bArr3, i, bArr4, i2, i3, z);
        }
        if (i2 == 5 || i2 == 6) {
            return isUserPassword56(bArr, bArr2, i2);
        }
        throw new IOException("Unknown Encryption Revision " + i2);
    }

    private boolean isUserPassword234(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) throws IOException {
        byte[] computeUserPassword = computeUserPassword(bArr, bArr3, i, bArr4, i2, i3, z);
        if (i2 == 2) {
            return Arrays.equals(bArr2, computeUserPassword);
        }
        return Arrays.equals(Arrays.copyOf(bArr2, 16), Arrays.copyOf(computeUserPassword, 16));
    }

    private boolean isUserPassword56(byte[] bArr, byte[] bArr2, int i) throws IOException {
        byte[] computeHash2A;
        byte[] truncate127 = truncate127(bArr);
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[8];
        System.arraycopy(bArr2, 0, bArr3, 0, 32);
        System.arraycopy(bArr2, 32, bArr4, 0, 8);
        if (i == 5) {
            computeHash2A = computeSHA256(truncate127, bArr4, null);
        } else {
            computeHash2A = computeHash2A(truncate127, bArr4, null);
        }
        return Arrays.equals(computeHash2A, bArr3);
    }

    public boolean isUserPassword(String str, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) throws IOException {
        if (i2 == 6 || i2 == 5) {
            return isUserPassword(str.getBytes(Charsets.UTF_8), bArr, bArr2, i, bArr3, i2, i3, z);
        }
        return isUserPassword(str.getBytes(Charsets.ISO_8859_1), bArr, bArr2, i, bArr3, i2, i3, z);
    }

    public boolean isOwnerPassword(String str, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) throws IOException {
        return isOwnerPassword(str.getBytes(Charsets.ISO_8859_1), bArr, bArr2, i, bArr3, i2, i3, z);
    }

    private byte[] computeHash2A(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IOException {
        if (bArr3 == null) {
            bArr3 = new byte[0];
        } else if (bArr3.length < 48) {
            throw new IOException("Bad U length");
        } else {
            if (bArr3.length > 48) {
                byte[] bArr4 = new byte[48];
                System.arraycopy(bArr3, 0, bArr4, 0, 48);
                bArr3 = bArr4;
            }
        }
        byte[] truncate127 = truncate127(bArr);
        return computeHash2B(concat(truncate127, bArr2, bArr3), truncate127, bArr3);
    }

    private static byte[] computeHash2B(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IOException {
        byte[] bArr4;
        try {
            byte[] digest = MessageDigests.getSHA256().digest(bArr);
            byte[] bArr5 = null;
            int i = 0;
            while (true) {
                if (i >= 64 && (bArr5[bArr5.length - 1] & UByte.MAX_VALUE) <= i - 32) {
                    break;
                }
                if (bArr3 != null && bArr3.length >= 48) {
                    bArr4 = new byte[(bArr2.length + digest.length + 48) * 64];
                } else {
                    bArr4 = new byte[(bArr2.length + digest.length) * 64];
                }
                int i2 = 0;
                for (int i3 = 0; i3 < 64; i3++) {
                    System.arraycopy(bArr2, 0, bArr4, i2, bArr2.length);
                    int length = i2 + bArr2.length;
                    System.arraycopy(digest, 0, bArr4, length, digest.length);
                    i2 = length + digest.length;
                    if (bArr3 != null && bArr3.length >= 48) {
                        System.arraycopy(bArr3, 0, bArr4, i2, 48);
                        i2 += 48;
                    }
                }
                byte[] bArr6 = new byte[16];
                byte[] bArr7 = new byte[16];
                System.arraycopy(digest, 0, bArr6, 0, 16);
                System.arraycopy(digest, 16, bArr7, 0, 16);
                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                cipher.init(1, new SecretKeySpec(bArr6, "AES"), new IvParameterSpec(bArr7));
                byte[] doFinal = cipher.doFinal(bArr4);
                byte[] bArr8 = new byte[16];
                System.arraycopy(doFinal, 0, bArr8, 0, 16);
                i++;
                bArr5 = doFinal;
                digest = MessageDigest.getInstance(HASHES_2B[new BigInteger(1, bArr8).mod(new BigInteger(ExifInterface.GPS_MEASUREMENT_3D)).intValue()]).digest(doFinal);
            }
            if (digest.length > 32) {
                byte[] bArr9 = new byte[32];
                System.arraycopy(digest, 0, bArr9, 0, 32);
                return bArr9;
            }
            return digest;
        } catch (GeneralSecurityException e) {
            logIfStrongEncryptionMissing();
            throw new IOException(e);
        }
    }

    private static byte[] computeSHA256(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        MessageDigest sha256 = MessageDigests.getSHA256();
        sha256.update(bArr);
        sha256.update(bArr2);
        return bArr3 == null ? sha256.digest() : sha256.digest(bArr3);
    }

    private static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static byte[] concat(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    private static byte[] truncate127(byte[] bArr) {
        if (bArr.length <= 127) {
            return bArr;
        }
        byte[] bArr2 = new byte[127];
        System.arraycopy(bArr, 0, bArr2, 0, 127);
        return bArr2;
    }

    private static void logIfStrongEncryptionMissing() {
        try {
            if (Cipher.getMaxAllowedKeyLength("AES") != Integer.MAX_VALUE) {
                Log.w("PdfBox-Android", "JCE unlimited strength jurisdiction policy files are not installed");
            }
        } catch (NoSuchAlgorithmException unused) {
        }
    }
}
