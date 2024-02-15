package org.informatika.sirekap.support.security.encrypt;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.openssl.EncryptionException;

/* compiled from: AsymmetricRSAEncrypt.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/support/security/encrypt/AsymmetricRSAEncrypt;", "Lorg/informatika/sirekap/support/security/encrypt/Encryptor;", "peerPublicKey", "Ljava/security/PublicKey;", "ownerPrivateKey", "Ljava/security/PrivateKey;", "(Ljava/security/PublicKey;Ljava/security/PrivateKey;)V", "getOwnerPrivateKey", "()Ljava/security/PrivateKey;", "setOwnerPrivateKey", "(Ljava/security/PrivateKey;)V", "getPeerPublicKey", "()Ljava/security/PublicKey;", "setPeerPublicKey", "(Ljava/security/PublicKey;)V", "decrypt", "", "payload", "encrypt", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AsymmetricRSAEncrypt implements Encryptor {
    private PrivateKey ownerPrivateKey;
    private PublicKey peerPublicKey;

    public AsymmetricRSAEncrypt() {
        this(null, null, 3, null);
    }

    public AsymmetricRSAEncrypt(PublicKey publicKey, PrivateKey privateKey) {
        this.peerPublicKey = publicKey;
        this.ownerPrivateKey = privateKey;
    }

    public /* synthetic */ AsymmetricRSAEncrypt(PublicKey publicKey, PrivateKey privateKey, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : publicKey, (i & 2) != 0 ? null : privateKey);
    }

    public final PublicKey getPeerPublicKey() {
        return this.peerPublicKey;
    }

    public final void setPeerPublicKey(PublicKey publicKey) {
        this.peerPublicKey = publicKey;
    }

    public final PrivateKey getOwnerPrivateKey() {
        return this.ownerPrivateKey;
    }

    public final void setOwnerPrivateKey(PrivateKey privateKey) {
        this.ownerPrivateKey = privateKey;
    }

    @Override // org.informatika.sirekap.support.security.encrypt.Encryptor
    public byte[] encrypt(byte[] payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (this.peerPublicKey == null) {
            throw new EncryptionException("Peer public key is required");
        }
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, this.peerPublicKey);
        byte[] doFinal = cipher.doFinal(payload);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(payload)");
        return doFinal;
    }

    @Override // org.informatika.sirekap.support.security.encrypt.Encryptor
    public byte[] decrypt(byte[] payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (this.ownerPrivateKey == null) {
            throw new EncryptionException("Owner private key is required");
        }
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, this.ownerPrivateKey);
        byte[] doFinal = cipher.doFinal(payload);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(payload)");
        return doFinal;
    }
}
