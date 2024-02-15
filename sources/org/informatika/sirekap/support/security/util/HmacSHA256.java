package org.informatika.sirekap.support.security.util;

import com.google.firebase.messaging.Constants;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.util.encoders.Hex;

/* compiled from: HmacSHA256.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/util/HmacSHA256;", "", "key", "", "([B)V", "generate", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "generateHex", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HmacSHA256 {
    private final byte[] key;

    public HmacSHA256(byte[] key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.key = key;
    }

    public final byte[] generate(byte[] bArr) {
        Mac mac = Mac.getInstance("HmacSHA256");
        Intrinsics.checkNotNullExpressionValue(mac, "getInstance(\"HmacSHA256\")");
        mac.init(new SecretKeySpec(this.key, "HmacSHA256"));
        byte[] doFinal = mac.doFinal(bArr);
        Intrinsics.checkNotNullExpressionValue(doFinal, "hmac.doFinal(data)");
        return doFinal;
    }

    public final String generateHex(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        String hexString = Hex.toHexString(generate(data));
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(generate(data))");
        return hexString;
    }
}
