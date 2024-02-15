package com.tom_roush.pdfbox.pdmodel.encryption;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public abstract class SecurityHandler {
    private static final byte[] AES_SALT = {BuiltinOptions.BucketizeOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.CosOptions};
    private static final short DEFAULT_KEY_LENGTH = 40;
    private SecureRandom customSecureRandom;
    private boolean decryptMetadata;
    protected byte[] encryptionKey;
    private COSName streamFilterName;
    private COSName stringFilterName;
    private boolean useAES;
    protected short keyLength = DEFAULT_KEY_LENGTH;
    private final RC4Cipher rc4 = new RC4Cipher();
    private final Set<COSBase> objects = Collections.newSetFromMap(new IdentityHashMap());
    private ProtectionPolicy protectionPolicy = null;
    private AccessPermission currentAccessPermission = null;

    public abstract void prepareDocumentForEncryption(PDDocument pDDocument) throws IOException;

    public abstract void prepareForDecryption(PDEncryption pDEncryption, COSArray cOSArray, DecryptionMaterial decryptionMaterial) throws IOException;

    public void setDecryptMetadata(boolean z) {
        this.decryptMetadata = z;
    }

    public boolean isDecryptMetadata() {
        return this.decryptMetadata;
    }

    public void setStringFilterName(COSName cOSName) {
        this.stringFilterName = cOSName;
    }

    public void setStreamFilterName(COSName cOSName) {
        this.streamFilterName = cOSName;
    }

    public void setCustomSecureRandom(SecureRandom secureRandom) {
        this.customSecureRandom = secureRandom;
    }

    private void encryptData(long j, long j2, InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        if (this.useAES && this.encryptionKey.length == 32) {
            encryptDataAES256(inputStream, outputStream, z);
        } else {
            byte[] calcFinalKey = calcFinalKey(j, j2);
            if (this.useAES) {
                encryptDataAESother(calcFinalKey, inputStream, outputStream, z);
            } else {
                encryptDataRC4(calcFinalKey, inputStream, outputStream);
            }
        }
        outputStream.flush();
    }

    private byte[] calcFinalKey(long j, long j2) {
        byte[] bArr = this.encryptionKey;
        int length = bArr.length + 5;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[length - 5] = (byte) (j & 255);
        bArr2[length - 4] = (byte) ((j >> 8) & 255);
        bArr2[length - 3] = (byte) ((j >> 16) & 255);
        bArr2[length - 2] = (byte) (j2 & 255);
        bArr2[length - 1] = (byte) ((j2 >> 8) & 255);
        MessageDigest md5 = MessageDigests.getMD5();
        md5.update(bArr2);
        if (this.useAES) {
            md5.update(AES_SALT);
        }
        byte[] digest = md5.digest();
        int min = Math.min(length, 16);
        byte[] bArr3 = new byte[min];
        System.arraycopy(digest, 0, bArr3, 0, min);
        return bArr3;
    }

    public void encryptDataRC4(byte[] bArr, InputStream inputStream, OutputStream outputStream) throws IOException {
        this.rc4.setKey(bArr);
        this.rc4.write(inputStream, outputStream);
    }

    public void encryptDataRC4(byte[] bArr, byte[] bArr2, OutputStream outputStream) throws IOException {
        this.rc4.setKey(bArr);
        this.rc4.write(bArr2, outputStream);
    }

    private void encryptDataAESother(byte[] bArr, InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        byte[] bArr2 = new byte[16];
        if (!prepareAESInitializationVector(z, bArr2, inputStream, outputStream)) {
            return;
        }
        try {
            Cipher createCipher = createCipher(bArr, bArr2, z);
            byte[] bArr3 = new byte[256];
            while (true) {
                int read = inputStream.read(bArr3);
                if (read != -1) {
                    byte[] update = createCipher.update(bArr3, 0, read);
                    if (update != null) {
                        outputStream.write(update);
                    }
                } else {
                    outputStream.write(createCipher.doFinal());
                    return;
                }
            }
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        }
    }

    private void encryptDataAES256(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        byte[] bArr = new byte[16];
        if (prepareAESInitializationVector(z, bArr, inputStream, outputStream)) {
            try {
                CipherInputStream cipherInputStream = new CipherInputStream(inputStream, createCipher(this.encryptionKey, bArr, z));
                try {
                    try {
                        IOUtils.copy(cipherInputStream, outputStream);
                    } catch (IOException e) {
                        if (!(e.getCause() instanceof GeneralSecurityException)) {
                            throw e;
                        }
                        Log.d("PdfBox-Android", "A GeneralSecurityException occurred when decrypting some stream data", e);
                    }
                } finally {
                    cipherInputStream.close();
                }
            } catch (GeneralSecurityException e2) {
                throw new IOException(e2);
            }
        }
    }

    private Cipher createCipher(byte[] bArr, byte[] bArr2, boolean z) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(z ? 2 : 1, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
        return cipher;
    }

    private boolean prepareAESInitializationVector(boolean z, byte[] bArr, InputStream inputStream, OutputStream outputStream) throws IOException {
        if (z) {
            int populateBuffer = (int) IOUtils.populateBuffer(inputStream, bArr);
            if (populateBuffer == 0) {
                return false;
            }
            if (populateBuffer == bArr.length) {
                return true;
            }
            throw new IOException("AES initialization vector not fully read: only " + populateBuffer + " bytes read instead of " + bArr.length);
        }
        getSecureRandom().nextBytes(bArr);
        outputStream.write(bArr);
        return true;
    }

    private SecureRandom getSecureRandom() {
        SecureRandom secureRandom = this.customSecureRandom;
        return secureRandom != null ? secureRandom : new SecureRandom();
    }

    public void decrypt(COSBase cOSBase, long j, long j2) throws IOException {
        if (cOSBase instanceof COSString) {
            if (this.objects.contains(cOSBase)) {
                return;
            }
            this.objects.add(cOSBase);
            decryptString((COSString) cOSBase, j, j2);
        } else if (cOSBase instanceof COSStream) {
            if (this.objects.contains(cOSBase)) {
                return;
            }
            this.objects.add(cOSBase);
            decryptStream((COSStream) cOSBase, j, j2);
        } else if (cOSBase instanceof COSDictionary) {
            decryptDictionary((COSDictionary) cOSBase, j, j2);
        } else if (cOSBase instanceof COSArray) {
            decryptArray((COSArray) cOSBase, j, j2);
        }
    }

    public void decryptStream(COSStream cOSStream, long j, long j2) throws IOException {
        if (COSName.IDENTITY.equals(this.streamFilterName)) {
            return;
        }
        COSName cOSName = cOSStream.getCOSName(COSName.TYPE);
        if ((this.decryptMetadata || !COSName.METADATA.equals(cOSName)) && !COSName.XREF.equals(cOSName)) {
            if (COSName.METADATA.equals(cOSName)) {
                InputStream createRawInputStream = cOSStream.createRawInputStream();
                byte[] bArr = new byte[10];
                IOUtils.populateBuffer(createRawInputStream, bArr);
                createRawInputStream.close();
                if (Arrays.equals(bArr, "<?xpacket ".getBytes(Charsets.ISO_8859_1))) {
                    Log.w("PdfBox-Android", "Metadata is not encrypted, but was expected to be");
                    Log.w("PdfBox-Android", "Read PDF specification about EncryptMetadata (default value: true)");
                    return;
                }
            }
            decryptDictionary(cOSStream, j, j2);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(cOSStream.createRawInputStream()));
            OutputStream createRawOutputStream = cOSStream.createRawOutputStream();
            try {
                try {
                    encryptData(j, j2, byteArrayInputStream, createRawOutputStream, true);
                } catch (IOException e) {
                    Log.e("PdfBox-Android", e.getClass().getSimpleName() + " thrown when decrypting object " + j + StringUtils.SPACE + j2 + " obj");
                    throw e;
                }
            } finally {
                createRawOutputStream.close();
            }
        }
    }

    public void encryptStream(COSStream cOSStream, long j, int i) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(cOSStream.createRawInputStream()));
        OutputStream createRawOutputStream = cOSStream.createRawOutputStream();
        try {
            encryptData(j, i, byteArrayInputStream, createRawOutputStream, false);
        } finally {
            createRawOutputStream.close();
        }
    }

    private void decryptDictionary(COSDictionary cOSDictionary, long j, long j2) throws IOException {
        if (cOSDictionary.getItem(COSName.CF) != null) {
            return;
        }
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TYPE);
        boolean z = COSName.SIG.equals(dictionaryObject) || COSName.DOC_TIME_STAMP.equals(dictionaryObject) || ((cOSDictionary.getDictionaryObject(COSName.CONTENTS) instanceof COSString) && (cOSDictionary.getDictionaryObject(COSName.BYTERANGE) instanceof COSArray));
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            if (!z || !COSName.CONTENTS.equals(entry.getKey())) {
                COSBase value = entry.getValue();
                if ((value instanceof COSString) || (value instanceof COSArray) || (value instanceof COSDictionary)) {
                    decrypt(value, j, j2);
                }
            }
        }
    }

    private void decryptString(COSString cOSString, long j, long j2) {
        if (COSName.IDENTITY.equals(this.stringFilterName)) {
            return;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(cOSString.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encryptData(j, j2, byteArrayInputStream, byteArrayOutputStream, true);
            cOSString.setValue(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Failed to decrypt COSString of length " + cOSString.getBytes().length + " in object " + j + ": " + e.getMessage());
        }
    }

    public void encryptString(COSString cOSString, long j, int i) throws IOException {
        InputStream byteArrayInputStream = new ByteArrayInputStream(cOSString.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encryptData(j, i, byteArrayInputStream, byteArrayOutputStream, false);
        cOSString.setValue(byteArrayOutputStream.toByteArray());
    }

    private void decryptArray(COSArray cOSArray, long j, long j2) throws IOException {
        for (int i = 0; i < cOSArray.size(); i++) {
            decrypt(cOSArray.get(i), j, j2);
        }
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    public void setKeyLength(int i) {
        this.keyLength = (short) i;
    }

    public void setCurrentAccessPermission(AccessPermission accessPermission) {
        this.currentAccessPermission = accessPermission;
    }

    public AccessPermission getCurrentAccessPermission() {
        return this.currentAccessPermission;
    }

    public boolean isAES() {
        return this.useAES;
    }

    public void setAES(boolean z) {
        this.useAES = z;
    }

    public boolean hasProtectionPolicy() {
        return this.protectionPolicy != null;
    }

    public ProtectionPolicy getProtectionPolicy() {
        return this.protectionPolicy;
    }

    public void setProtectionPolicy(ProtectionPolicy protectionPolicy) {
        this.protectionPolicy = protectionPolicy;
    }

    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }

    public void setEncryptionKey(byte[] bArr) {
        this.encryptionKey = bArr;
    }

    public int computeVersionNumber() {
        short s = this.keyLength;
        if (s == 40) {
            return 1;
        }
        if (s == 128 && this.protectionPolicy.isPreferAES()) {
            return 4;
        }
        return this.keyLength == 256 ? 5 : 2;
    }
}
