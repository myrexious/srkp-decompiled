package org.informatika.sirekap.support.security.signature;

import com.google.firebase.messaging.Constants;
import java.security.PrivateKey;
import java.security.Signature;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ECDSADataSigner.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/security/signature/ECDSADataSigner;", "", "()V", "sign", "", "privateKey", "Ljava/security/PrivateKey;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ECDSADataSigner {
    public final byte[] sign(PrivateKey privateKey, byte[] data) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(data, "data");
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] sign = signature.sign();
        Intrinsics.checkNotNullExpressionValue(sign, "signature.sign()");
        return sign;
    }
}
