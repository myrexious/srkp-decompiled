package org.informatika.sirekap.support.security.tsa;

import android.content.Context;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import net.openid.appauth.ResponseTypeValues;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.tsp.TimeStampToken;
import org.informatika.sirekap.repository.PKIRepository;

/* compiled from: BasicTimestampAuthority.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H$R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority;", "Lorg/informatika/sirekap/support/security/tsa/TimestampAuthority;", "requestUrl", "", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Ljava/lang/String;Lorg/informatika/sirekap/repository/PKIRepository;)V", "ALGORITHM_OID_FINDER", "Lorg/bouncycastle/operator/DigestAlgorithmIdentifierFinder;", "getTimestampToken", "Lorg/bouncycastle/tsp/TimeStampToken;", ResponseTypeValues.TOKEN, "Ljava/io/InputStream;", "(Ljava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateCertificateChain", "", "certificate", "Ljava/security/cert/X509Certificate;", "Factory", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BasicTimestampAuthority implements TimestampAuthority {
    private final DigestAlgorithmIdentifierFinder ALGORITHM_OID_FINDER;
    private final PKIRepository pkiRepository;
    private final String requestUrl;

    @Override // org.informatika.sirekap.support.security.tsa.TimestampAuthority
    public Object getTimestampToken(InputStream inputStream, Continuation<? super TimeStampToken> continuation) {
        return getTimestampToken$suspendImpl(this, inputStream, continuation);
    }

    protected abstract void validateCertificateChain(X509Certificate x509Certificate);

    public BasicTimestampAuthority(String requestUrl, PKIRepository pkiRepository) {
        Intrinsics.checkNotNullParameter(requestUrl, "requestUrl");
        Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
        this.requestUrl = requestUrl;
        this.pkiRepository = pkiRepository;
        this.ALGORITHM_OID_FINDER = new DefaultDigestAlgorithmIdentifierFinder();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x012b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object getTimestampToken$suspendImpl(org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority r8, java.io.InputStream r9, kotlin.coroutines.Continuation<? super org.bouncycastle.tsp.TimeStampToken> r10) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority.getTimestampToken$suspendImpl(org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority, java.io.InputStream, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BasicTimestampAuthority.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;", "", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Lorg/informatika/sirekap/repository/PKIRepository;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority;", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static abstract class Factory {
        private final PKIRepository pkiRepository;

        public abstract BasicTimestampAuthority build(Context context);

        public Factory(PKIRepository pkiRepository) {
            Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
            this.pkiRepository = pkiRepository;
        }
    }
}
