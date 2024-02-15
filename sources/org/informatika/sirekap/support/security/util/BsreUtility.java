package org.informatika.sirekap.support.security.util;

import android.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.informatika.sirekap.BuildConfig;

/* compiled from: BsreUtility.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J.\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/support/security/util/BsreUtility;", "", "()V", "convertKodeWilayah", "", "kodeWilayah", "getEncryptedUserInfoForDownload", "name", "uid", "ip", "challengeToken", "getPKCSPassword", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreUtility {
    public static final BsreUtility INSTANCE = new BsreUtility();

    private BsreUtility() {
    }

    public final String convertKodeWilayah(String kodeWilayah) {
        Intrinsics.checkNotNullParameter(kodeWilayah, "kodeWilayah");
        if (kodeWilayah.length() == 6) {
            return "Kec-" + kodeWilayah;
        }
        return "TPS-" + kodeWilayah;
    }

    public final String getPKCSPassword(String kodeWilayah, String uid) {
        Intrinsics.checkNotNullParameter(kodeWilayah, "kodeWilayah");
        Intrinsics.checkNotNullParameter(uid, "uid");
        byte[] bytes = convertKodeWilayah(kodeWilayah).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        HmacSHA256 hmacSHA256 = new HmacSHA256(bytes);
        byte[] bytes2 = uid.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        return hmacSHA256.generateHex(bytes2);
    }

    public final String getEncryptedUserInfoForDownload(String name, String kodeWilayah, String uid, String ip, String challengeToken) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(kodeWilayah, "kodeWilayah");
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(challengeToken, "challengeToken");
        byte[] bytes = (name + "+" + convertKodeWilayah(kodeWilayah) + "+" + uid + "+" + ip).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] bytes2 = (convertKodeWilayah(kodeWilayah) + uid).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        byte[] generate = new HmacSHA256(bytes2).generate(Base64.decode(challengeToken, 2));
        byte[] bytes3 = BuildConfig.BSRE_IV.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
        String encodeToString = Base64.encodeToString(new AESCBCEncryptionWithStaticIv(generate, bytes3).encrypt(bytes), 2);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(result, Base64.NO_WRAP)");
        return encodeToString;
    }
}
