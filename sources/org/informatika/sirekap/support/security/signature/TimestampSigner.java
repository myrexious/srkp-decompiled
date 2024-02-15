package org.informatika.sirekap.support.security.signature;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.bouncycastle.tsp.TimeStampToken;
import org.informatika.sirekap.support.security.tsa.TimestampAuthority;

/* compiled from: TimestampSigner.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/signature/TimestampSigner;", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/SignatureInterface;", "timestamp", "Lorg/informatika/sirekap/support/security/tsa/TimestampAuthority;", "(Lorg/informatika/sirekap/support/security/tsa/TimestampAuthority;)V", "sign", "", FirebaseAnalytics.Param.CONTENT, "Ljava/io/InputStream;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TimestampSigner implements SignatureInterface {
    private final TimestampAuthority timestamp;

    public TimestampSigner(TimestampAuthority timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        this.timestamp = timestamp;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface
    public byte[] sign(InputStream content) {
        Object runBlocking$default;
        Intrinsics.checkNotNullParameter(content, "content");
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new TimestampSigner$sign$result$1(this, content, null), 1, null);
        byte[] encoded = ((TimeStampToken) runBlocking$default).getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "result.encoded");
        return encoded;
    }
}
