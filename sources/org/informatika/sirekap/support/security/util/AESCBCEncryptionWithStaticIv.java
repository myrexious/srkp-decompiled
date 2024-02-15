package org.informatika.sirekap.support.security.util;

import com.google.firebase.messaging.Constants;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* compiled from: AESCBCEncryptionWithStaticIv.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\b\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/util/AESCBCEncryptionWithStaticIv;", "", "key", "", "iv", "([B[B)V", "decrypt", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "encrypt", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AESCBCEncryptionWithStaticIv {
    private final byte[] iv;
    private final byte[] key;

    public AESCBCEncryptionWithStaticIv(byte[] key, byte[] iv) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(iv, "iv");
        this.key = key;
        this.iv = iv;
    }

    public final byte[] encrypt(byte[] bArr) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(1, new SecretKeySpec(this.key, "AES"), new IvParameterSpec(this.iv));
        byte[] doFinal = cipher.doFinal(bArr);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(data)");
        return doFinal;
    }

    public final byte[] decrypt(byte[] bArr) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(2, new SecretKeySpec(this.key, "AES"), new IvParameterSpec(this.iv));
        byte[] doFinal = cipher.doFinal(bArr);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(data)");
        return doFinal;
    }
}
