package org.informatika.sirekap.support.security.encrypt;

import android.util.Base64;
import com.google.firebase.messaging.Constants;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.List;
import java.util.ListIterator;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.informatika.sirekap.BuildConfig;

/* compiled from: AsymmetricECIESEncrypt.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\u0007R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/support/security/encrypt/AsymmetricECIESEncrypt;", "", "()V", "derivationVector", "", "kotlin.jvm.PlatformType", "getDerivationVector", "()[B", "encodeVector", "getEncodeVector", "decrypt", "privateKey", "Ljava/security/PrivateKey;", "rawEncrypted", "", "encrypt", "publicKey", "Ljava/security/PublicKey;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AsymmetricECIESEncrypt {
    private final byte[] derivationVector = Base64.decode(BuildConfig.ECIES_DERIVATION_VECTOR, 2);
    private final byte[] encodeVector = Base64.decode(BuildConfig.ECIES_ENCODE_VECTOR, 2);

    public final byte[] getDerivationVector() {
        return this.derivationVector;
    }

    public final byte[] getEncodeVector() {
        return this.encodeVector;
    }

    public final byte[] decrypt(PrivateKey privateKey, String rawEncrypted) {
        List emptyList;
        boolean z;
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(rawEncrypted, "rawEncrypted");
        List<String> split = new Regex("\\.").split(rawEncrypted, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        String[] strArr = (String[]) emptyList.toArray(new String[0]);
        if (strArr.length != 2) {
            throw new Exception("ciphertext is not valid");
        }
        byte[] decode = Base64.decode(strArr[0], 2);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(splittedRaw[0], Base64.NO_WRAP)");
        byte[] decode2 = Base64.decode(strArr[1], 2);
        Intrinsics.checkNotNullExpressionValue(decode2, "decode(splittedRaw[1], Base64.NO_WRAP)");
        IESParameterSpec iESParameterSpec = new IESParameterSpec(this.derivationVector, this.encodeVector, 256, 256, decode, true);
        Cipher cipher = Cipher.getInstance("ECIES");
        cipher.init(2, privateKey, iESParameterSpec);
        byte[] doFinal = cipher.doFinal(decode2);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(encryptedData)");
        return doFinal;
    }

    public final String encrypt(PublicKey publicKey, byte[] data) {
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] bArr = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(bArr);
        IESParameterSpec iESParameterSpec = new IESParameterSpec(this.derivationVector, this.encodeVector, 256, 256, bArr, true);
        Cipher cipher = Cipher.getInstance("ECIES");
        cipher.init(1, publicKey, iESParameterSpec, SecureRandom.getInstanceStrong());
        byte[] doFinal = cipher.doFinal(data);
        String encodeToString = Base64.encodeToString(bArr, 2);
        return encodeToString + "." + Base64.encodeToString(doFinal, 2);
    }
}
