package org.informatika.sirekap.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.informatika.sirekap.api.CertmanAPIInterface;
import org.informatika.sirekap.api.CertmanCertificateBucketAPIInterface;
import org.informatika.sirekap.api.SecurityApiResponse;
import org.informatika.sirekap.api.response.BsreKeystoreResponsePayload;
import org.informatika.sirekap.api.response.BsreV2ChallengeResponsePayload;
import retrofit2.Response;

/* compiled from: CertmanRepository.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J5\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\f0\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J1\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\"\u0010\u001b\u001a\u00020\f\"\u0004\b\u0000\u0010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u001f0\u001eH\u0002J)\u0010 \u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J)\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&J\u001b\u0010'\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&J'\u0010(\u001a\u00020)2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010-J'\u0010.\u001a\u00020)2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0011\u0010/\u001a\u00020)H\u0096@ø\u0001\u0000¢\u0006\u0002\u00100J\u0019\u00101\u001a\u00020)2\u0006\u00102\u001a\u000203H\u0096@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00105\u001a\u00020)H\u0096@ø\u0001\u0000¢\u0006\u0002\u00100J\u0011\u00106\u001a\u00020)H\u0096@ø\u0001\u0000¢\u0006\u0002\u00100J\u0011\u00107\u001a\u00020)H\u0096@ø\u0001\u0000¢\u0006\u0002\u00100R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultCertmanRepository;", "Lorg/informatika/sirekap/repository/CertmanRepository;", "certmanApi", "Lorg/informatika/sirekap/api/CertmanAPIInterface;", "certmanBucketApi", "Lorg/informatika/sirekap/api/CertmanCertificateBucketAPIInterface;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "(Lorg/informatika/sirekap/api/CertmanAPIInterface;Lorg/informatika/sirekap/api/CertmanCertificateBucketAPIInterface;Lorg/informatika/sirekap/repository/AuthRepository;)V", "downloadKeystore", "Lorg/informatika/sirekap/api/response/BsreKeystoreResponsePayload;", "transactionId", "", "challengeToken", "publicKey", "signature", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadKeystoreV2", "Lkotlin/Pair;", "Ljava/security/KeyStore;", "ip", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueueCertificateRequest", "firebaseId", "csr", "deviceId", "gsfId", "extractError", "T", "result", "Lretrofit2/Response;", "Lorg/informatika/sirekap/api/SecurityApiResponse;", "getBsreToken", "getBsreTokenV2", "Lorg/informatika/sirekap/api/response/BsreV2ChallengeResponsePayload;", "getCertificate", "Ljava/security/cert/X509Certificate;", "bucketUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCertificateGenerationUrl", "registerBsre", "", "certificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerBsreV2", "reportFailed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setUserDashboardStatus", "userStatus", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "successInsertKeystore", "userUnobtainedCert", "userVisitDashboard", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultCertmanRepository implements CertmanRepository {
    private final AuthRepository authRepository;
    private final CertmanAPIInterface certmanApi;
    private final CertmanCertificateBucketAPIInterface certmanBucketApi;

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object downloadKeystore(String str, String str2, String str3, String str4, Continuation<? super BsreKeystoreResponsePayload> continuation) {
        return downloadKeystore$suspendImpl(this, str, str2, str3, str4, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object downloadKeystoreV2(String str, String str2, String str3, Continuation<? super Pair<? extends KeyStore, String>> continuation) {
        return downloadKeystoreV2$suspendImpl(this, str, str2, str3, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object enqueueCertificateRequest(String str, String str2, String str3, String str4, Continuation<? super String> continuation) {
        return enqueueCertificateRequest$suspendImpl(this, str, str2, str3, str4, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object getBsreToken(String str, String str2, String str3, Continuation<? super String> continuation) {
        return getBsreToken$suspendImpl(this, str, str2, str3, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object getBsreTokenV2(String str, String str2, String str3, Continuation<? super BsreV2ChallengeResponsePayload> continuation) {
        return getBsreTokenV2$suspendImpl(this, str, str2, str3, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object getCertificate(String str, Continuation<? super X509Certificate> continuation) {
        return getCertificate$suspendImpl(this, str, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object getCertificateGenerationUrl(String str, Continuation<? super String> continuation) {
        return getCertificateGenerationUrl$suspendImpl(this, str, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object registerBsre(String str, List<? extends Certificate> list, Continuation<? super Unit> continuation) {
        return registerBsre$suspendImpl(this, str, list, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object registerBsreV2(String str, List<? extends Certificate> list, Continuation<? super Unit> continuation) {
        return registerBsreV2$suspendImpl(this, str, list, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object reportFailed(Continuation<? super Unit> continuation) {
        return reportFailed$suspendImpl(this, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object setUserDashboardStatus(int i, Continuation<? super Unit> continuation) {
        return setUserDashboardStatus$suspendImpl(this, i, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object successInsertKeystore(Continuation<? super Unit> continuation) {
        return successInsertKeystore$suspendImpl(this, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object userUnobtainedCert(Continuation<? super Unit> continuation) {
        return userUnobtainedCert$suspendImpl(this, continuation);
    }

    @Override // org.informatika.sirekap.repository.CertmanRepository
    public Object userVisitDashboard(Continuation<? super Unit> continuation) {
        return userVisitDashboard$suspendImpl(this, continuation);
    }

    @Inject
    public DefaultCertmanRepository(CertmanAPIInterface certmanApi, CertmanCertificateBucketAPIInterface certmanBucketApi, AuthRepository authRepository) {
        Intrinsics.checkNotNullParameter(certmanApi, "certmanApi");
        Intrinsics.checkNotNullParameter(certmanBucketApi, "certmanBucketApi");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        this.certmanApi = certmanApi;
        this.certmanBucketApi = certmanBucketApi;
        this.authRepository = authRepository;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object enqueueCertificateRequest$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, kotlin.coroutines.Continuation<? super java.lang.String> r9) {
        /*
            boolean r0 = r9 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$enqueueCertificateRequest$1
            if (r0 == 0) goto L14
            r0 = r9
            org.informatika.sirekap.repository.DefaultCertmanRepository$enqueueCertificateRequest$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$enqueueCertificateRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$enqueueCertificateRequest$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$enqueueCertificateRequest$1
            r0.<init>(r4, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4b
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            org.informatika.sirekap.api.CertmanAPIInterface r9 = r4.certmanApi
            org.informatika.sirekap.api.request.CertificateGenerationRequestPayload r2 = new org.informatika.sirekap.api.request.CertificateGenerationRequestPayload
            r2.<init>(r5, r8, r7, r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r9 = r9.generateCertificate(r2, r0)
            if (r9 != r1) goto L4b
            return r1
        L4b:
            retrofit2.Response r9 = (retrofit2.Response) r9
            boolean r5 = r9.isSuccessful()
            if (r5 == 0) goto L80
            java.lang.Object r4 = r9.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L72
            java.lang.String r5 = r4.getStatus()
            java.lang.String r6 = "success"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L72
            java.lang.Object r4 = r4.getData()
            org.informatika.sirekap.api.response.CertificateGenerationResponsePayload r4 = (org.informatika.sirekap.api.response.CertificateGenerationResponsePayload) r4
            java.lang.String r4 = r4.getRequestId()
            return r4
        L72:
            java.lang.Exception r5 = new java.lang.Exception
            if (r4 == 0) goto L7b
            java.lang.String r4 = r4.getMessage()
            goto L7c
        L7b:
            r4 = 0
        L7c:
            r5.<init>(r4)
            throw r5
        L80:
            int r5 = r9.code()
            r6 = 502(0x1f6, float:7.03E-43)
            if (r5 != r6) goto L90
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L90:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r9)
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.enqueueCertificateRequest$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object getCertificateGenerationUrl$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, java.lang.String r5, kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            boolean r0 = r6 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificateGenerationUrl$1
            if (r0 == 0) goto L14
            r0 = r6
            org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificateGenerationUrl$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificateGenerationUrl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificateGenerationUrl$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificateGenerationUrl$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            org.informatika.sirekap.api.CertmanAPIInterface r6 = r4.certmanApi
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r6.certificateResult(r5, r0)
            if (r6 != r1) goto L46
            return r1
        L46:
            retrofit2.Response r6 = (retrofit2.Response) r6
            boolean r5 = r6.isSuccessful()
            r0 = 0
            if (r5 == 0) goto L7a
            java.lang.Object r4 = r6.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L6e
            java.lang.String r5 = r4.getStatus()
            java.lang.String r6 = "success"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L6e
            java.lang.Object r4 = r4.getData()
            org.informatika.sirekap.api.response.CertificateBucketUrlResponsePayload r4 = (org.informatika.sirekap.api.response.CertificateBucketUrlResponsePayload) r4
            java.lang.String r4 = r4.getCertificateUrl()
            return r4
        L6e:
            java.lang.Exception r5 = new java.lang.Exception
            if (r4 == 0) goto L76
            java.lang.String r0 = r4.getMessage()
        L76:
            r5.<init>(r0)
            throw r5
        L7a:
            int r5 = r6.code()
            r1 = 425(0x1a9, float:5.96E-43)
            if (r5 != r1) goto L83
            return r0
        L83:
            int r5 = r6.code()
            r0 = 502(0x1f6, float:7.03E-43)
            if (r5 != r0) goto L93
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L93:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r6)
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.getCertificateGenerationUrl$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object getCertificate$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, java.lang.String r5, kotlin.coroutines.Continuation<? super java.security.cert.X509Certificate> r6) {
        /*
            boolean r0 = r6 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificate$1
            if (r0 == 0) goto L14
            r0 = r6
            org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificate$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificate$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$getCertificate$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L40
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            org.informatika.sirekap.api.CertmanCertificateBucketAPIInterface r4 = r4.certmanBucketApi
            r0.label = r3
            java.lang.Object r6 = r4.getCertificate(r5, r0)
            if (r6 != r1) goto L40
            return r1
        L40:
            retrofit2.Response r6 = (retrofit2.Response) r6
            boolean r4 = r6.isSuccessful()
            if (r4 == 0) goto L6e
            java.lang.Object r4 = r6.body()
            okhttp3.ResponseBody r4 = (okhttp3.ResponseBody) r4
            if (r4 == 0) goto L66
            java.lang.String r5 = "X.509"
            java.security.cert.CertificateFactory r5 = java.security.cert.CertificateFactory.getInstance(r5)
            java.io.InputStream r4 = r4.byteStream()
            java.security.cert.Certificate r4 = r5.generateCertificate(r4)
            java.lang.String r5 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            return r4
        L66:
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "error while fetching certificate"
            r4.<init>(r5)
            throw r4
        L6e:
            java.lang.Exception r4 = new java.lang.Exception
            okhttp3.ResponseBody r5 = r6.errorBody()
            if (r5 == 0) goto L7c
            java.lang.String r5 = r5.string()
            if (r5 != 0) goto L80
        L7c:
            java.lang.String r5 = r6.message()
        L80:
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.getCertificate$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object getBsreToken$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super java.lang.String> r8) {
        /*
            boolean r0 = r8 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreToken$1
            if (r0 == 0) goto L14
            r0 = r8
            org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreToken$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreToken$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreToken$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreToken$1
            r0.<init>(r4, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4b
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            org.informatika.sirekap.api.CertmanAPIInterface r8 = r4.certmanApi
            org.informatika.sirekap.api.request.BsreChallengeRequestPayload r2 = new org.informatika.sirekap.api.request.BsreChallengeRequestPayload
            r2.<init>(r6, r5, r7)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r8 = r8.getBsreChallengeToken(r2, r0)
            if (r8 != r1) goto L4b
            return r1
        L4b:
            retrofit2.Response r8 = (retrofit2.Response) r8
            boolean r5 = r8.isSuccessful()
            if (r5 == 0) goto L80
            java.lang.Object r4 = r8.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L72
            java.lang.String r5 = r4.getStatus()
            java.lang.String r6 = "success"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L72
            java.lang.Object r4 = r4.getData()
            org.informatika.sirekap.api.response.BsreChallengeResponsePayload r4 = (org.informatika.sirekap.api.response.BsreChallengeResponsePayload) r4
            java.lang.String r4 = r4.getChallenge()
            return r4
        L72:
            java.lang.Exception r5 = new java.lang.Exception
            if (r4 == 0) goto L7b
            java.lang.String r4 = r4.getMessage()
            goto L7c
        L7b:
            r4 = 0
        L7c:
            r5.<init>(r4)
            throw r5
        L80:
            int r5 = r8.code()
            r6 = 502(0x1f6, float:7.03E-43)
            if (r5 != r6) goto L90
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L90:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r8)
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.getBsreToken$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object registerBsre$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r5, java.lang.String r6, java.util.List<? extends java.security.cert.Certificate> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsre$1
            if (r0 == 0) goto L14
            r0 = r8
            org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsre$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsre$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsre$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsre$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r5 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L51
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            org.informatika.sirekap.api.CertmanAPIInterface r8 = r5.certmanApi
            org.informatika.sirekap.api.request.BsreRegisterCertificateRequest r2 = new org.informatika.sirekap.api.request.BsreRegisterCertificateRequest
            org.informatika.sirekap.support.security.PKIFacade r4 = org.informatika.sirekap.support.security.PKIFacade.INSTANCE
            java.util.List r7 = r4.decodeToPEM(r7)
            r2.<init>(r6, r7)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = r8.registerBsre(r2, r0)
            if (r8 != r1) goto L51
            return r1
        L51:
            retrofit2.Response r8 = (retrofit2.Response) r8
            boolean r6 = r8.isSuccessful()
            if (r6 == 0) goto L7e
            java.lang.Object r5 = r8.body()
            org.informatika.sirekap.api.SecurityApiResponse r5 = (org.informatika.sirekap.api.SecurityApiResponse) r5
            if (r5 == 0) goto L70
            java.lang.String r6 = r5.getStatus()
            java.lang.String r7 = "success"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 == 0) goto L70
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L70:
            java.lang.Exception r6 = new java.lang.Exception
            if (r5 == 0) goto L79
            java.lang.String r5 = r5.getMessage()
            goto L7a
        L79:
            r5 = 0
        L7a:
            r6.<init>(r5)
            throw r6
        L7e:
            int r6 = r8.code()
            r7 = 502(0x1f6, float:7.03E-43)
            if (r6 != r7) goto L8e
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r6 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r5.<init>(r6)
            throw r5
        L8e:
            java.lang.Exception r6 = new java.lang.Exception
            java.lang.String r5 = r5.extractError(r8)
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.registerBsre$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object downloadKeystore$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, kotlin.coroutines.Continuation<? super org.informatika.sirekap.api.response.BsreKeystoreResponsePayload> r9) {
        /*
            boolean r0 = r9 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$downloadKeystore$1
            if (r0 == 0) goto L14
            r0 = r9
            org.informatika.sirekap.repository.DefaultCertmanRepository$downloadKeystore$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$downloadKeystore$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$downloadKeystore$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$downloadKeystore$1
            r0.<init>(r4, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4b
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            org.informatika.sirekap.api.CertmanAPIInterface r9 = r4.certmanApi
            org.informatika.sirekap.api.request.BsreKeystoreRequestPayload r2 = new org.informatika.sirekap.api.request.BsreKeystoreRequestPayload
            r2.<init>(r5, r6, r7, r8)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r9 = r9.downloadKeystore(r2, r0)
            if (r9 != r1) goto L4b
            return r1
        L4b:
            retrofit2.Response r9 = (retrofit2.Response) r9
            boolean r5 = r9.isSuccessful()
            if (r5 == 0) goto L7a
            java.lang.Object r4 = r9.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L6c
            java.lang.String r5 = r4.getStatus()
            java.lang.String r6 = "success"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L6c
            java.lang.Object r4 = r4.getData()
            return r4
        L6c:
            java.lang.Exception r5 = new java.lang.Exception
            if (r4 == 0) goto L75
            java.lang.String r4 = r4.getMessage()
            goto L76
        L75:
            r4 = 0
        L76:
            r5.<init>(r4)
            throw r5
        L7a:
            int r5 = r9.code()
            r6 = 502(0x1f6, float:7.03E-43)
            if (r5 != r6) goto L8a
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L8a:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r9)
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.downloadKeystore$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object getBsreTokenV2$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super org.informatika.sirekap.api.response.BsreV2ChallengeResponsePayload> r8) {
        /*
            boolean r0 = r8 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreTokenV2$1
            if (r0 == 0) goto L14
            r0 = r8
            org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreTokenV2$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreTokenV2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreTokenV2$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$getBsreTokenV2$1
            r0.<init>(r4, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4b
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            org.informatika.sirekap.api.CertmanAPIInterface r8 = r4.certmanApi
            org.informatika.sirekap.api.request.BsreChallengeRequestPayload r2 = new org.informatika.sirekap.api.request.BsreChallengeRequestPayload
            r2.<init>(r6, r5, r7)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r8 = r8.getBsreV2ChallengeToken(r2, r0)
            if (r8 != r1) goto L4b
            return r1
        L4b:
            retrofit2.Response r8 = (retrofit2.Response) r8
            boolean r5 = r8.isSuccessful()
            if (r5 == 0) goto L7a
            java.lang.Object r4 = r8.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L6c
            java.lang.String r5 = r4.getStatus()
            java.lang.String r6 = "success"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L6c
            java.lang.Object r4 = r4.getData()
            return r4
        L6c:
            java.lang.Exception r5 = new java.lang.Exception
            if (r4 == 0) goto L75
            java.lang.String r4 = r4.getMessage()
            goto L76
        L75:
            r4 = 0
        L76:
            r5.<init>(r4)
            throw r5
        L7a:
            int r5 = r8.code()
            r6 = 502(0x1f6, float:7.03E-43)
            if (r5 != r6) goto L8a
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L8a:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r8)
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.getBsreTokenV2$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object registerBsreV2$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r5, java.lang.String r6, java.util.List<? extends java.security.cert.Certificate> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsreV2$1
            if (r0 == 0) goto L14
            r0 = r8
            org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsreV2$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsreV2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsreV2$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$registerBsreV2$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r5 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L51
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            org.informatika.sirekap.api.CertmanAPIInterface r8 = r5.certmanApi
            org.informatika.sirekap.api.request.BsreRegisterCertificateRequest r2 = new org.informatika.sirekap.api.request.BsreRegisterCertificateRequest
            org.informatika.sirekap.support.security.PKIFacade r4 = org.informatika.sirekap.support.security.PKIFacade.INSTANCE
            java.util.List r7 = r4.decodeToPEM(r7)
            r2.<init>(r6, r7)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = r8.registerBsreV2(r2, r0)
            if (r8 != r1) goto L51
            return r1
        L51:
            retrofit2.Response r8 = (retrofit2.Response) r8
            boolean r6 = r8.isSuccessful()
            if (r6 == 0) goto L7e
            java.lang.Object r5 = r8.body()
            org.informatika.sirekap.api.SecurityApiResponse r5 = (org.informatika.sirekap.api.SecurityApiResponse) r5
            if (r5 == 0) goto L70
            java.lang.String r6 = r5.getStatus()
            java.lang.String r7 = "success"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 == 0) goto L70
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L70:
            java.lang.Exception r6 = new java.lang.Exception
            if (r5 == 0) goto L79
            java.lang.String r5 = r5.getMessage()
            goto L7a
        L79:
            r5 = 0
        L7a:
            r6.<init>(r5)
            throw r6
        L7e:
            int r6 = r8.code()
            r7 = 502(0x1f6, float:7.03E-43)
            if (r6 != r7) goto L8e
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r6 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r5.<init>(r6)
            throw r5
        L8e:
            java.lang.Exception r6 = new java.lang.Exception
            java.lang.String r5 = r5.extractError(r8)
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.registerBsreV2$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object downloadKeystoreV2$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.security.KeyStore, java.lang.String>> r17) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.downloadKeystoreV2$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object reportFailed$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$reportFailed$1
            if (r0 == 0) goto L14
            r0 = r5
            org.informatika.sirekap.repository.DefaultCertmanRepository$reportFailed$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$reportFailed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$reportFailed$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$reportFailed$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r5)
            org.informatika.sirekap.api.CertmanAPIInterface r5 = r4.certmanApi
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.failedGenerationReport(r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            retrofit2.Response r5 = (retrofit2.Response) r5
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto L60
            java.lang.Object r4 = r5.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L59
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L59:
            java.lang.Exception r4 = new java.lang.Exception
            r5 = 0
            r4.<init>(r5)
            throw r4
        L60:
            int r0 = r5.code()
            r1 = 502(0x1f6, float:7.03E-43)
            if (r0 != r1) goto L70
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L70:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r5)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.reportFailed$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object successInsertKeystore$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$successInsertKeystore$1
            if (r0 == 0) goto L14
            r0 = r5
            org.informatika.sirekap.repository.DefaultCertmanRepository$successInsertKeystore$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$successInsertKeystore$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$successInsertKeystore$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$successInsertKeystore$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r5)
            org.informatika.sirekap.api.CertmanAPIInterface r5 = r4.certmanApi
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.successInsertKeystore(r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            retrofit2.Response r5 = (retrofit2.Response) r5
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto L60
            java.lang.Object r4 = r5.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L59
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L59:
            java.lang.Exception r4 = new java.lang.Exception
            r5 = 0
            r4.<init>(r5)
            throw r4
        L60:
            int r0 = r5.code()
            r1 = 502(0x1f6, float:7.03E-43)
            if (r0 != r1) goto L70
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L70:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r5)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.successInsertKeystore$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object setUserDashboardStatus$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, int r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$setUserDashboardStatus$1
            if (r0 == 0) goto L14
            r0 = r6
            org.informatika.sirekap.repository.DefaultCertmanRepository$setUserDashboardStatus$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$setUserDashboardStatus$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$setUserDashboardStatus$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$setUserDashboardStatus$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            org.informatika.sirekap.api.CertmanAPIInterface r6 = r4.certmanApi
            org.informatika.sirekap.api.request.UserDashboardStatusRequestPayload r2 = new org.informatika.sirekap.api.request.UserDashboardStatusRequestPayload
            r2.<init>(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r6.setUserDashboardStatus(r2, r0)
            if (r6 != r1) goto L4b
            return r1
        L4b:
            retrofit2.Response r6 = (retrofit2.Response) r6
            boolean r5 = r6.isSuccessful()
            if (r5 == 0) goto L65
            java.lang.Object r4 = r6.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L5e
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L5e:
            java.lang.Exception r4 = new java.lang.Exception
            r5 = 0
            r4.<init>(r5)
            throw r4
        L65:
            int r5 = r6.code()
            r0 = 502(0x1f6, float:7.03E-43)
            if (r5 != r0) goto L75
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L75:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r6)
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.setUserDashboardStatus$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object userUnobtainedCert$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$userUnobtainedCert$1
            if (r0 == 0) goto L14
            r0 = r5
            org.informatika.sirekap.repository.DefaultCertmanRepository$userUnobtainedCert$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$userUnobtainedCert$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$userUnobtainedCert$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$userUnobtainedCert$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r5)
            org.informatika.sirekap.api.CertmanAPIInterface r5 = r4.certmanApi
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.userUnobtainedCert(r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            retrofit2.Response r5 = (retrofit2.Response) r5
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto L60
            java.lang.Object r4 = r5.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L59
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L59:
            java.lang.Exception r4 = new java.lang.Exception
            r5 = 0
            r4.<init>(r5)
            throw r4
        L60:
            int r0 = r5.code()
            r1 = 502(0x1f6, float:7.03E-43)
            if (r0 != r1) goto L70
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L70:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r5)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.userUnobtainedCert$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object userVisitDashboard$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof org.informatika.sirekap.repository.DefaultCertmanRepository$userVisitDashboard$1
            if (r0 == 0) goto L14
            r0 = r5
            org.informatika.sirekap.repository.DefaultCertmanRepository$userVisitDashboard$1 r0 = (org.informatika.sirekap.repository.DefaultCertmanRepository$userVisitDashboard$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultCertmanRepository$userVisitDashboard$1 r0 = new org.informatika.sirekap.repository.DefaultCertmanRepository$userVisitDashboard$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r4 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r5)
            org.informatika.sirekap.api.CertmanAPIInterface r5 = r4.certmanApi
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.userVisitDashboard(r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            retrofit2.Response r5 = (retrofit2.Response) r5
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto L60
            java.lang.Object r4 = r5.body()
            org.informatika.sirekap.api.SecurityApiResponse r4 = (org.informatika.sirekap.api.SecurityApiResponse) r4
            if (r4 == 0) goto L59
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L59:
            java.lang.Exception r4 = new java.lang.Exception
            r5 = 0
            r4.<init>(r5)
            throw r4
        L60:
            int r0 = r5.code()
            r1 = 502(0x1f6, float:7.03E-43)
            if (r0 != r1) goto L70
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r5 = "Saat ini server sedang sibuk, silahkan coba beberapa saat lagi"
            r4.<init>(r5)
            throw r4
        L70:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r4 = r4.extractError(r5)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultCertmanRepository.userVisitDashboard$suspendImpl(org.informatika.sirekap.repository.DefaultCertmanRepository, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final <T> String extractError(Response<SecurityApiResponse<T>> response) {
        String string;
        Gson create = new GsonBuilder().setLenient().create();
        try {
            ResponseBody errorBody = response.errorBody();
            return ((SecurityApiResponse) create.fromJson(errorBody != null ? errorBody.charStream() : null, (Class<Object>) SecurityApiResponse.class)).getMessage();
        } catch (Exception unused) {
            ResponseBody errorBody2 = response.errorBody();
            String message = (errorBody2 == null || (string = errorBody2.string()) == null) ? response.message() : string;
            Intrinsics.checkNotNullExpressionValue(message, "{\n            result.err…esult.message()\n        }");
            return message;
        }
    }
}
