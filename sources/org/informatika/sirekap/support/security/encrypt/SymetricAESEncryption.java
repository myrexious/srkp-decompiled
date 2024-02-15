package org.informatika.sirekap.support.security.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SymetricAESEncryption.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/support/security/encrypt/SymetricAESEncryption;", "Lorg/informatika/sirekap/support/security/encrypt/Encryptor;", "secret", "Ljavax/crypto/SecretKey;", "ivSpec", "Ljavax/crypto/spec/IvParameterSpec;", "(Ljavax/crypto/SecretKey;Ljavax/crypto/spec/IvParameterSpec;)V", "decrypt", "", "payload", "encrypt", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SymetricAESEncryption implements Encryptor {
    private final IvParameterSpec ivSpec;
    private final SecretKey secret;

    public SymetricAESEncryption(SecretKey secret, IvParameterSpec ivSpec) {
        Intrinsics.checkNotNullParameter(secret, "secret");
        Intrinsics.checkNotNullParameter(ivSpec, "ivSpec");
        this.secret = secret;
        this.ivSpec = ivSpec;
    }

    @Override // org.informatika.sirekap.support.security.encrypt.Encryptor
    public byte[] encrypt(byte[] payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, this.secret, this.ivSpec);
        byte[] doFinal = cipher.doFinal(payload);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(payload)");
        return doFinal;
    }

    @Override // org.informatika.sirekap.support.security.encrypt.Encryptor
    public byte[] decrypt(byte[] payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, this.secret, this.ivSpec);
        byte[] doFinal = cipher.doFinal(payload);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(payload)");
        return doFinal;
    }
}
