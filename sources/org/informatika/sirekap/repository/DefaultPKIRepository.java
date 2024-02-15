package org.informatika.sirekap.repository;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.PKIApiInterface;

/* compiled from: PKIRepository.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0011H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultPKIRepository;", "Lorg/informatika/sirekap/repository/PKIRepository;", "api", "Lorg/informatika/sirekap/api/PKIApiInterface;", "(Lorg/informatika/sirekap/api/PKIApiInterface;)V", "getCRL", "Ljava/security/cert/CRL;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOcspResponse", "Lorg/bouncycastle/cert/ocsp/OCSPResp;", "request", "Lorg/bouncycastle/cert/ocsp/OCSPReq;", "(Ljava/lang/String;Lorg/bouncycastle/cert/ocsp/OCSPReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTSAResponse", "Lorg/bouncycastle/tsp/TimeStampResponse;", "Lorg/bouncycastle/tsp/TimeStampRequest;", "(Ljava/lang/String;Lorg/bouncycastle/tsp/TimeStampRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultPKIRepository implements PKIRepository {
    private final PKIApiInterface api;

    public DefaultPKIRepository(PKIApiInterface api) {
        Intrinsics.checkNotNullParameter(api, "api");
        this.api = api;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007a  */
    @Override // org.informatika.sirekap.repository.PKIRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getTSAResponse(java.lang.String r6, org.bouncycastle.tsp.TimeStampRequest r7, kotlin.coroutines.Continuation<? super org.bouncycastle.tsp.TimeStampResponse> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof org.informatika.sirekap.repository.DefaultPKIRepository$getTSAResponse$1
            if (r0 == 0) goto L14
            r0 = r8
            org.informatika.sirekap.repository.DefaultPKIRepository$getTSAResponse$1 r0 = (org.informatika.sirekap.repository.DefaultPKIRepository$getTSAResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultPKIRepository$getTSAResponse$1 r0 = new org.informatika.sirekap.repository.DefaultPKIRepository$getTSAResponse$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r6 = r0.L$0
            r7 = r6
            org.bouncycastle.tsp.TimeStampRequest r7 = (org.bouncycastle.tsp.TimeStampRequest) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r8 = "application/timestamp-query"
            okhttp3.MediaType r8 = okhttp3.MediaType.parse(r8)
            byte[] r2 = r7.getEncoded()
            okhttp3.RequestBody r8 = okhttp3.RequestBody.create(r8, r2)
            org.informatika.sirekap.api.PKIApiInterface r2 = r5.api
            java.lang.String r4 = "requestBody"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r4)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r2.postTSARequest(r6, r8, r0)
            if (r8 != r1) goto L5a
            return r1
        L5a:
            retrofit2.Response r8 = (retrofit2.Response) r8
            int r6 = r8.code()
            r0 = 200(0xc8, float:2.8E-43)
            if (r6 != r0) goto L7a
            org.bouncycastle.tsp.TimeStampResponse r6 = new org.bouncycastle.tsp.TimeStampResponse
            java.lang.Object r8 = r8.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            okhttp3.ResponseBody r8 = (okhttp3.ResponseBody) r8
            byte[] r8 = r8.bytes()
            r6.<init>(r8)
            r6.validate(r7)
            return r6
        L7a:
            org.informatika.sirekap.repository.PKIOperationException r6 = new org.informatika.sirekap.repository.PKIOperationException
            int r7 = r8.code()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "TSA request failed with error code "
            r8.<init>(r0)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultPKIRepository.getTSAResponse(java.lang.String, org.bouncycastle.tsp.TimeStampRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0070  */
    @Override // org.informatika.sirekap.repository.PKIRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getOcspResponse(java.lang.String r5, org.bouncycastle.cert.ocsp.OCSPReq r6, kotlin.coroutines.Continuation<? super org.bouncycastle.cert.ocsp.OCSPResp> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof org.informatika.sirekap.repository.DefaultPKIRepository$getOcspResponse$1
            if (r0 == 0) goto L14
            r0 = r7
            org.informatika.sirekap.repository.DefaultPKIRepository$getOcspResponse$1 r0 = (org.informatika.sirekap.repository.DefaultPKIRepository$getOcspResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultPKIRepository$getOcspResponse$1 r0 = new org.informatika.sirekap.repository.DefaultPKIRepository$getOcspResponse$1
            r0.<init>(r4, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L53
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = "application/ocsp-request"
            okhttp3.MediaType r7 = okhttp3.MediaType.parse(r7)
            byte[] r6 = r6.getEncoded()
            okhttp3.RequestBody r6 = okhttp3.RequestBody.create(r7, r6)
            org.informatika.sirekap.api.PKIApiInterface r7 = r4.api
            java.lang.String r2 = "requestBody"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            r0.label = r3
            java.lang.Object r7 = r7.postOcspRequest(r5, r6, r0)
            if (r7 != r1) goto L53
            return r1
        L53:
            retrofit2.Response r7 = (retrofit2.Response) r7
            int r5 = r7.code()
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L70
            org.bouncycastle.cert.ocsp.OCSPResp r5 = new org.bouncycastle.cert.ocsp.OCSPResp
            java.lang.Object r6 = r7.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            okhttp3.ResponseBody r6 = (okhttp3.ResponseBody) r6
            byte[] r6 = r6.bytes()
            r5.<init>(r6)
            return r5
        L70:
            org.informatika.sirekap.repository.PKIOperationException r5 = new org.informatika.sirekap.repository.PKIOperationException
            int r6 = r7.code()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "OCSP request failed with error code "
            r7.<init>(r0)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultPKIRepository.getOcspResponse(java.lang.String, org.bouncycastle.cert.ocsp.OCSPReq, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0067  */
    @Override // org.informatika.sirekap.repository.PKIRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getCRL(java.lang.String r5, kotlin.coroutines.Continuation<? super java.security.cert.CRL> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.informatika.sirekap.repository.DefaultPKIRepository$getCRL$1
            if (r0 == 0) goto L14
            r0 = r6
            org.informatika.sirekap.repository.DefaultPKIRepository$getCRL$1 r0 = (org.informatika.sirekap.repository.DefaultPKIRepository$getCRL$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            org.informatika.sirekap.repository.DefaultPKIRepository$getCRL$1 r0 = new org.informatika.sirekap.repository.DefaultPKIRepository$getCRL$1
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
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            org.informatika.sirekap.api.PKIApiInterface r6 = r4.api
            r0.label = r3
            java.lang.Object r6 = r6.getCRL(r5, r0)
            if (r6 != r1) goto L40
            return r1
        L40:
            retrofit2.Response r6 = (retrofit2.Response) r6
            int r5 = r6.code()
            r0 = 200(0xc8, float:2.8E-43)
            if (r5 != r0) goto L67
            java.lang.String r5 = "X.509"
            java.security.cert.CertificateFactory r5 = java.security.cert.CertificateFactory.getInstance(r5)
            java.lang.Object r6 = r6.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            okhttp3.ResponseBody r6 = (okhttp3.ResponseBody) r6
            java.io.InputStream r6 = r6.byteStream()
            java.security.cert.CRL r5 = r5.generateCRL(r6)
            java.lang.String r6 = "getInstance(\"X.509\").gen…se.body()!!.byteStream())"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            return r5
        L67:
            org.informatika.sirekap.repository.PKIOperationException r5 = new org.informatika.sirekap.repository.PKIOperationException
            int r6 = r6.code()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "CRL Download failed with error code "
            r0.<init>(r1)
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultPKIRepository.getCRL(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
