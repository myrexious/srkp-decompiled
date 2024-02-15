package org.informatika.sirekap.api;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.informatika.sirekap.api.request.BsreChallengeRequestPayload;
import org.informatika.sirekap.api.request.BsreKeystoreRequestPayload;
import org.informatika.sirekap.api.request.BsreRegisterCertificateRequest;
import org.informatika.sirekap.api.request.BsreV2KeystoreRequestPayload;
import org.informatika.sirekap.api.request.CertificateGenerationRequestPayload;
import org.informatika.sirekap.api.request.UserDashboardStatusRequestPayload;
import org.informatika.sirekap.api.response.BsreChallengeResponsePayload;
import org.informatika.sirekap.api.response.BsreKeystoreResponsePayload;
import org.informatika.sirekap.api.response.BsreV2ChallengeResponsePayload;
import org.informatika.sirekap.api.response.BsreV2KeystoreResponsePayload;
import org.informatika.sirekap.api.response.CertificateBucketUrlResponsePayload;
import org.informatika.sirekap.api.response.CertificateGenerationResponsePayload;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/* compiled from: CertmanAPIInterface.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ'\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J'\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u0017H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J'\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u001bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ'\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u001bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ'\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020 H§@ø\u0001\u0000¢\u0006\u0002\u0010!J'\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020 H§@ø\u0001\u0000¢\u0006\u0002\u0010!J'\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020$H§@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001d\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lorg/informatika/sirekap/api/CertmanAPIInterface;", "", "certificateResult", "Lretrofit2/Response;", "Lorg/informatika/sirekap/api/SecurityApiResponse;", "Lorg/informatika/sirekap/api/response/CertificateBucketUrlResponsePayload;", "transactionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadKeystore", "Lorg/informatika/sirekap/api/response/BsreKeystoreResponsePayload;", "payload", "Lorg/informatika/sirekap/api/request/BsreKeystoreRequestPayload;", "(Lorg/informatika/sirekap/api/request/BsreKeystoreRequestPayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadKeystoreV2", "Lorg/informatika/sirekap/api/response/BsreV2KeystoreResponsePayload;", "Lorg/informatika/sirekap/api/request/BsreV2KeystoreRequestPayload;", "(Lorg/informatika/sirekap/api/request/BsreV2KeystoreRequestPayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "failedGenerationReport", "Ljava/util/Objects;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateCertificate", "Lorg/informatika/sirekap/api/response/CertificateGenerationResponsePayload;", "Lorg/informatika/sirekap/api/request/CertificateGenerationRequestPayload;", "(Lorg/informatika/sirekap/api/request/CertificateGenerationRequestPayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBsreChallengeToken", "Lorg/informatika/sirekap/api/response/BsreChallengeResponsePayload;", "Lorg/informatika/sirekap/api/request/BsreChallengeRequestPayload;", "(Lorg/informatika/sirekap/api/request/BsreChallengeRequestPayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBsreV2ChallengeToken", "Lorg/informatika/sirekap/api/response/BsreV2ChallengeResponsePayload;", "registerBsre", "Lorg/informatika/sirekap/api/request/BsreRegisterCertificateRequest;", "(Lorg/informatika/sirekap/api/request/BsreRegisterCertificateRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerBsreV2", "setUserDashboardStatus", "Lorg/informatika/sirekap/api/request/UserDashboardStatusRequestPayload;", "(Lorg/informatika/sirekap/api/request/UserDashboardStatusRequestPayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "successInsertKeystore", "userUnobtainedCert", "userVisitDashboard", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CertmanAPIInterface {
    @POST("/certman/certificate/{transactionId}")
    Object certificateResult(@Path("transactionId") String str, Continuation<? super Response<SecurityApiResponse<CertificateBucketUrlResponsePayload>>> continuation);

    @POST("/certman/bsre/download")
    Object downloadKeystore(@Body BsreKeystoreRequestPayload bsreKeystoreRequestPayload, Continuation<? super Response<SecurityApiResponse<BsreKeystoreResponsePayload>>> continuation);

    @POST("/certman/v2/bsre/download")
    Object downloadKeystoreV2(@Body BsreV2KeystoreRequestPayload bsreV2KeystoreRequestPayload, Continuation<? super Response<SecurityApiResponse<BsreV2KeystoreResponsePayload>>> continuation);

    @POST("/certman/initialization/failed")
    Object failedGenerationReport(Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);

    @POST("/certman/certificate")
    Object generateCertificate(@Body CertificateGenerationRequestPayload certificateGenerationRequestPayload, Continuation<? super Response<SecurityApiResponse<CertificateGenerationResponsePayload>>> continuation);

    @POST("/certman/bsre/token")
    Object getBsreChallengeToken(@Body BsreChallengeRequestPayload bsreChallengeRequestPayload, Continuation<? super Response<SecurityApiResponse<BsreChallengeResponsePayload>>> continuation);

    @POST("/certman/v2/bsre/token")
    Object getBsreV2ChallengeToken(@Body BsreChallengeRequestPayload bsreChallengeRequestPayload, Continuation<? super Response<SecurityApiResponse<BsreV2ChallengeResponsePayload>>> continuation);

    @POST("/certman/bsre/register")
    Object registerBsre(@Body BsreRegisterCertificateRequest bsreRegisterCertificateRequest, Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);

    @POST("/certman/v2/bsre/register")
    Object registerBsreV2(@Body BsreRegisterCertificateRequest bsreRegisterCertificateRequest, Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);

    @POST("/certman/initialization/set-user-dashboard-status")
    Object setUserDashboardStatus(@Body UserDashboardStatusRequestPayload userDashboardStatusRequestPayload, Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);

    @POST("/certman/initialization/success-insert-keystore")
    Object successInsertKeystore(Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);

    @POST("/certman/initialization/user-unobtained-cert")
    Object userUnobtainedCert(Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);

    @GET("/certman/initialization/user-visit-dashboard")
    Object userVisitDashboard(Continuation<? super Response<SecurityApiResponse<Objects>>> continuation);
}
