package org.informatika.sirekap.repository;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.informatika.sirekap.api.response.BsreKeystoreResponsePayload;
import org.informatika.sirekap.api.response.BsreV2ChallengeResponsePayload;

/* compiled from: CertmanRepository.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ5\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ1\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ)\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ)\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001b\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ'\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH¦@ø\u0001\u0000¢\u0006\u0002\u0010!J'\u0010\"\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH¦@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0011\u0010#\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0019\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'H¦@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0011\u0010)\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0011\u0010*\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0011\u0010+\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/repository/CertmanRepository;", "", "downloadKeystore", "Lorg/informatika/sirekap/api/response/BsreKeystoreResponsePayload;", "transactionId", "", "challengeToken", "publicKey", "signature", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadKeystoreV2", "Lkotlin/Pair;", "Ljava/security/KeyStore;", "ip", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueueCertificateRequest", "firebaseId", "csr", "deviceId", "gsfId", "getBsreToken", "getBsreTokenV2", "Lorg/informatika/sirekap/api/response/BsreV2ChallengeResponsePayload;", "getCertificate", "Ljava/security/cert/X509Certificate;", "bucketUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCertificateGenerationUrl", "registerBsre", "", "certificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerBsreV2", "reportFailed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setUserDashboardStatus", "userStatus", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "successInsertKeystore", "userUnobtainedCert", "userVisitDashboard", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CertmanRepository {
    Object downloadKeystore(String str, String str2, String str3, String str4, Continuation<? super BsreKeystoreResponsePayload> continuation);

    Object downloadKeystoreV2(String str, String str2, String str3, Continuation<? super Pair<? extends KeyStore, String>> continuation);

    Object enqueueCertificateRequest(String str, String str2, String str3, String str4, Continuation<? super String> continuation);

    Object getBsreToken(String str, String str2, String str3, Continuation<? super String> continuation);

    Object getBsreTokenV2(String str, String str2, String str3, Continuation<? super BsreV2ChallengeResponsePayload> continuation);

    Object getCertificate(String str, Continuation<? super X509Certificate> continuation);

    Object getCertificateGenerationUrl(String str, Continuation<? super String> continuation);

    Object registerBsre(String str, List<? extends Certificate> list, Continuation<? super Unit> continuation);

    Object registerBsreV2(String str, List<? extends Certificate> list, Continuation<? super Unit> continuation);

    Object reportFailed(Continuation<? super Unit> continuation);

    Object setUserDashboardStatus(int i, Continuation<? super Unit> continuation);

    Object successInsertKeystore(Continuation<? super Unit> continuation);

    Object userUnobtainedCert(Continuation<? super Unit> continuation);

    Object userVisitDashboard(Continuation<? super Unit> continuation);
}
