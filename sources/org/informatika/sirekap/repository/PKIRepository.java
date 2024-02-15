package org.informatika.sirekap.repository;

import java.security.cert.CRL;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.bouncycastle.cert.ocsp.OCSPReq;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampResponse;

/* compiled from: PKIRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u000eH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/repository/PKIRepository;", "", "getCRL", "Ljava/security/cert/CRL;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOcspResponse", "Lorg/bouncycastle/cert/ocsp/OCSPResp;", "request", "Lorg/bouncycastle/cert/ocsp/OCSPReq;", "(Ljava/lang/String;Lorg/bouncycastle/cert/ocsp/OCSPReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTSAResponse", "Lorg/bouncycastle/tsp/TimeStampResponse;", "Lorg/bouncycastle/tsp/TimeStampRequest;", "(Ljava/lang/String;Lorg/bouncycastle/tsp/TimeStampRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PKIRepository {
    Object getCRL(String str, Continuation<? super CRL> continuation);

    Object getOcspResponse(String str, OCSPReq oCSPReq, Continuation<? super OCSPResp> continuation);

    Object getTSAResponse(String str, TimeStampRequest timeStampRequest, Continuation<? super TimeStampResponse> continuation);
}
