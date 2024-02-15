package org.informatika.sirekap.support.security.pki;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.informatika.sirekap.repository.PKIOperationException;
import org.informatika.sirekap.repository.PKIRepository;

/* compiled from: CRLUtility.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/support/security/pki/CRLUtility;", "", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Lorg/informatika/sirekap/repository/PKIRepository;)V", "checkCertivicateRevoked", "", "crl", "Ljava/security/cert/X509CRL;", "certificate", "Ljava/security/cert/X509Certificate;", "getCRL", "", "certificateChain", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateCRL", "issuer", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CRLUtility {
    private final PKIRepository pkiRepository;

    public CRLUtility(PKIRepository pkiRepository) {
        Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
        this.pkiRepository = pkiRepository;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00af A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0078 -> B:48:0x00ad). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x0099 -> B:47:0x009c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getCRL(java.util.List<? extends java.security.cert.X509Certificate> r12, kotlin.coroutines.Continuation<? super java.util.List<? extends java.security.cert.X509CRL>> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof org.informatika.sirekap.support.security.pki.CRLUtility$getCRL$1
            if (r0 == 0) goto L14
            r0 = r13
            org.informatika.sirekap.support.security.pki.CRLUtility$getCRL$1 r0 = (org.informatika.sirekap.support.security.pki.CRLUtility$getCRL$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            org.informatika.sirekap.support.security.pki.CRLUtility$getCRL$1 r0 = new org.informatika.sirekap.support.security.pki.CRLUtility$getCRL$1
            r0.<init>(r11, r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4a
            if (r2 != r3) goto L42
            int r12 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$4
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            java.lang.Object r5 = r0.L$3
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5
            java.lang.Object r6 = r0.L$2
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r7 = r0.L$1
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r0.L$0
            org.informatika.sirekap.support.security.pki.CRLUtility r8 = (org.informatika.sirekap.support.security.pki.CRLUtility) r8
            kotlin.ResultKt.throwOnFailure(r13)
            goto L9c
        L42:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L4a:
            kotlin.ResultKt.throwOnFailure(r13)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            int r2 = r12.size()
            int r2 = r2 - r3
            r4 = 0
            r8 = r11
            r6 = r13
            r13 = r12
            r12 = r2
            r2 = r4
        L5d:
            if (r2 >= r12) goto Laf
            java.lang.Object r4 = r13.get(r2)
            r5 = r4
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5
            int r4 = r2 + 1
            java.lang.Object r4 = r13.get(r4)
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            org.informatika.sirekap.support.security.PKIFacade r7 = org.informatika.sirekap.support.security.PKIFacade.INSTANCE
            java.util.List r7 = r7.getCRLUrls(r5)
            boolean r9 = r7.isEmpty()
            if (r9 != 0) goto Lad
            org.informatika.sirekap.repository.PKIRepository r9 = r8.pkiRepository
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            java.lang.String r7 = (java.lang.String) r7
            r0.L$0 = r8
            r0.L$1 = r13
            r0.L$2 = r6
            r0.L$3 = r5
            r0.L$4 = r4
            r0.I$0 = r2
            r0.I$1 = r12
            r0.label = r3
            java.lang.Object r7 = r9.getCRL(r7, r0)
            if (r7 != r1) goto L99
            return r1
        L99:
            r10 = r7
            r7 = r13
            r13 = r10
        L9c:
            java.lang.String r9 = "null cannot be cast to non-null type java.security.cert.X509CRL"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13, r9)
            java.security.cert.X509CRL r13 = (java.security.cert.X509CRL) r13
            r8.validateCRL(r13, r4)
            r8.checkCertivicateRevoked(r13, r5)
            r6.add(r13)
            r13 = r7
        Lad:
            int r2 = r2 + r3
            goto L5d
        Laf:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.pki.CRLUtility.getCRL(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void validateCRL(X509CRL crl, X509Certificate issuer) {
        Intrinsics.checkNotNullParameter(crl, "crl");
        Intrinsics.checkNotNullParameter(issuer, "issuer");
        crl.verify(issuer.getPublicKey(), BouncyCastleProvider.PROVIDER_NAME);
    }

    public final void checkCertivicateRevoked(X509CRL crl, X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(crl, "crl");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        if (crl.getRevokedCertificate(certificate.getSerialNumber()) != null) {
            throw new PKIOperationException("Certificate is revoked");
        }
    }
}
