package org.informatika.sirekap.support.security.pki;

import android.os.Build;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.CertificateStatus;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPReq;
import org.bouncycastle.cert.ocsp.OCSPReqBuilder;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RevokedStatus;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.informatika.sirekap.repository.PKIOperationException;
import org.informatika.sirekap.repository.PKIRepository;

/* compiled from: OCSPUtility.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rJ\u001c\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u001c\u0010\u0017\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J7\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J(\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u001e2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fJ\u0010\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/support/security/pki/OCSPUtility;", "", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Lorg/informatika/sirekap/repository/PKIRepository;)V", "checkNonce", "", "basicResponse", "Lorg/bouncycastle/cert/ocsp/BasicOCSPResp;", "request", "Lorg/bouncycastle/cert/ocsp/OCSPReq;", "checkSignerCertificate", "certificate", "Ljava/security/cert/X509Certificate;", "trustAnchor", "", "Ljava/security/cert/TrustAnchor;", "generateOcspRequest", "issuerCertificate", "getCertificate", "ocspResp", "keyhash", "", "getCertificateByName", "name", "Lorg/bouncycastle/asn1/x500/X500Name;", "getKeyHashFromCertHolder", "certHolder", "Lorg/bouncycastle/cert/X509CertificateHolder;", "getOcspResponse", "Lorg/bouncycastle/cert/ocsp/OCSPResp;", "certificateChain", "oscpTrustAnchor", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOcspResponse", "ocspReq", "verifyOcspStatus", "resp", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OCSPUtility {
    private final PKIRepository pkiRepository;

    public OCSPUtility(PKIRepository pkiRepository) {
        Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
        this.pkiRepository = pkiRepository;
    }

    public final OCSPReq generateOcspRequest(X509Certificate certificate, X509Certificate issuerCertificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        Intrinsics.checkNotNullParameter(issuerCertificate, "issuerCertificate");
        CertificateID certificateID = new CertificateID(new JcaDigestCalculatorProviderBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build().get(CertificateID.HASH_SHA1), new JcaX509CertificateHolder(issuerCertificate), certificate.getSerialNumber());
        Extension extension = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_response, false, new DLSequence(OCSPObjectIdentifiers.id_pkix_ocsp_basic).getEncoded());
        byte[] bArr = new byte[16];
        if (Build.VERSION.SDK_INT >= 26) {
            SecureRandom.getInstanceStrong().nextBytes(bArr);
        } else {
            new SecureRandom().nextBytes(bArr);
        }
        Extension extension2 = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, false, (ASN1OctetString) new DEROctetString(new DEROctetString(bArr)));
        OCSPReqBuilder oCSPReqBuilder = new OCSPReqBuilder();
        oCSPReqBuilder.setRequestExtensions(new Extensions(new Extension[]{extension, extension2}));
        oCSPReqBuilder.addRequest(certificateID);
        OCSPReq build = oCSPReqBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getOcspResponse$default(OCSPUtility oCSPUtility, List list, List list2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            list2 = null;
        }
        return oCSPUtility.getOcspResponse(list, list2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00aa A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0078 -> B:50:0x00a8). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0094 -> B:49:0x009c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getOcspResponse(java.util.List<? extends java.security.cert.X509Certificate> r12, java.util.List<? extends java.security.cert.TrustAnchor> r13, kotlin.coroutines.Continuation<? super java.util.List<? extends org.bouncycastle.cert.ocsp.OCSPResp>> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof org.informatika.sirekap.support.security.pki.OCSPUtility$getOcspResponse$1
            if (r0 == 0) goto L14
            r0 = r14
            org.informatika.sirekap.support.security.pki.OCSPUtility$getOcspResponse$1 r0 = (org.informatika.sirekap.support.security.pki.OCSPUtility$getOcspResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            org.informatika.sirekap.support.security.pki.OCSPUtility$getOcspResponse$1 r0 = new org.informatika.sirekap.support.security.pki.OCSPUtility$getOcspResponse$1
            r0.<init>(r11, r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4a
            if (r2 != r3) goto L42
            int r12 = r0.I$1
            int r13 = r0.I$0
            java.lang.Object r2 = r0.L$4
            org.bouncycastle.cert.ocsp.OCSPReq r2 = (org.bouncycastle.cert.ocsp.OCSPReq) r2
            java.lang.Object r4 = r0.L$3
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            java.lang.Object r5 = r0.L$2
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r0.L$0
            org.informatika.sirekap.support.security.pki.OCSPUtility r7 = (org.informatika.sirekap.support.security.pki.OCSPUtility) r7
            kotlin.ResultKt.throwOnFailure(r14)
            goto L9c
        L42:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L4a:
            kotlin.ResultKt.throwOnFailure(r14)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            int r2 = r12.size()
            int r2 = r2 - r3
            r4 = 0
            r7 = r11
            r9 = r13
            r13 = r12
            r12 = r2
            r2 = r14
            r14 = r9
        L5e:
            if (r4 >= r12) goto Laa
            java.lang.Object r5 = r13.get(r4)
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5
            int r6 = r4 + 1
            java.lang.Object r6 = r13.get(r6)
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6
            org.bouncycastle.cert.ocsp.OCSPReq r6 = r7.generateOcspRequest(r5, r6)
            org.informatika.sirekap.support.security.PKIFacade r8 = org.informatika.sirekap.support.security.PKIFacade.INSTANCE
            java.lang.String r5 = r8.getOCSPUrl(r5)
            if (r5 != 0) goto L7b
            goto La8
        L7b:
            org.informatika.sirekap.repository.PKIRepository r8 = r7.pkiRepository
            r0.L$0 = r7
            r0.L$1 = r13
            r0.L$2 = r14
            r0.L$3 = r2
            r0.L$4 = r6
            r0.I$0 = r4
            r0.I$1 = r12
            r0.label = r3
            java.lang.Object r5 = r8.getOcspResponse(r5, r6, r0)
            if (r5 != r1) goto L94
            return r1
        L94:
            r9 = r6
            r6 = r13
            r13 = r4
            r4 = r2
            r2 = r9
            r10 = r5
            r5 = r14
            r14 = r10
        L9c:
            org.bouncycastle.cert.ocsp.OCSPResp r14 = (org.bouncycastle.cert.ocsp.OCSPResp) r14
            r7.verifyOcspResponse(r2, r14, r5)
            r4.add(r14)
            r2 = r4
            r14 = r5
            r4 = r13
            r13 = r6
        La8:
            int r4 = r4 + r3
            goto L5e
        Laa:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.pki.OCSPUtility.getOcspResponse(java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void verifyOcspResponse$default(OCSPUtility oCSPUtility, OCSPReq oCSPReq, OCSPResp oCSPResp, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = null;
        }
        oCSPUtility.verifyOcspResponse(oCSPReq, oCSPResp, list);
    }

    public final void verifyOcspResponse(OCSPReq ocspReq, OCSPResp ocspResp, List<? extends TrustAnchor> list) {
        Intrinsics.checkNotNullParameter(ocspReq, "ocspReq");
        Intrinsics.checkNotNullParameter(ocspResp, "ocspResp");
        verifyOcspStatus(ocspResp);
        Object responseObject = ocspResp.getResponseObject();
        Intrinsics.checkNotNull(responseObject, "null cannot be cast to non-null type org.bouncycastle.cert.ocsp.BasicOCSPResp");
        BasicOCSPResp basicOCSPResp = (BasicOCSPResp) responseObject;
        ResponderID aSN1Primitive = basicOCSPResp.getResponderId().toASN1Primitive();
        X500Name name = aSN1Primitive.getName();
        byte[] keyHash = aSN1Primitive.getKeyHash();
        X509Certificate certificateByName = getCertificateByName(basicOCSPResp, name);
        if (certificateByName == null) {
            certificateByName = getCertificate(basicOCSPResp, keyHash);
        }
        if (certificateByName != null) {
            if (list != null) {
                checkSignerCertificate(certificateByName, list);
            }
            if (!basicOCSPResp.isSignatureValid(new JcaContentVerifierProviderBuilder().setProvider(SecurityProvider.getProvider()).build(certificateByName))) {
                throw new PKIOperationException("OCSP response signature is not valid");
            }
        }
        checkNonce(basicOCSPResp, ocspReq);
        CertificateStatus certStatus = basicOCSPResp.getResponses()[0].getCertStatus();
        if (Intrinsics.areEqual(certStatus, CertificateStatus.GOOD)) {
            return;
        }
        if (certStatus instanceof RevokedStatus) {
            throw new PKIOperationException("Certificate has been revoked");
        }
        throw new PKIOperationException("Certificate status is not unknown");
    }

    private final void checkNonce(BasicOCSPResp basicOCSPResp, OCSPReq oCSPReq) {
        Extension extension = basicOCSPResp.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
        if (extension == null) {
            return;
        }
        ASN1OctetString extnValue = oCSPReq.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce).getExtnValue();
        ASN1OctetString extnValue2 = extension.getExtnValue();
        Intrinsics.checkNotNull(extnValue2, "null cannot be cast to non-null type org.bouncycastle.asn1.DEROctetString");
        if (!((DEROctetString) extnValue2).equals((ASN1Primitive) extnValue)) {
            throw new PKIOperationException("OCSP response nonce is different");
        }
    }

    private final void checkSignerCertificate(X509Certificate x509Certificate, List<? extends TrustAnchor> list) {
        List<String> extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
        Intrinsics.checkNotNullExpressionValue(extendedKeyUsage, "certificate.extendedKeyUsage");
        if (!extendedKeyUsage.contains(KeyPurposeId.id_kp_OCSPSigning.toString())) {
            throw new PKIOperationException("OCSP response certificate does not have OCSP signing extended key usage");
        }
        CertPath generateCertPath = CertificateFactory.getInstance("X.509").generateCertPath(CollectionsKt.listOf(x509Certificate));
        PKIXBuilderParameters pKIXBuilderParameters = new PKIXBuilderParameters(new HashSet(list), new X509CertSelector());
        pKIXBuilderParameters.setRevocationEnabled(false);
        CertPathValidator.getInstance("PKIX").validate(generateCertPath, pKIXBuilderParameters);
    }

    private final X509Certificate getCertificate(BasicOCSPResp basicOCSPResp, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        X509CertificateHolder[] certHolders = basicOCSPResp.getCerts();
        Intrinsics.checkNotNullExpressionValue(certHolders, "certHolders");
        for (X509CertificateHolder x509CertificateHolder : certHolders) {
            Intrinsics.checkNotNull(x509CertificateHolder, "null cannot be cast to non-null type org.bouncycastle.cert.X509CertificateHolder");
            if (Arrays.equals(bArr, getKeyHashFromCertHolder(x509CertificateHolder))) {
                return new JcaX509CertificateConverter().getCertificate(x509CertificateHolder);
            }
        }
        return null;
    }

    private final X509Certificate getCertificateByName(BasicOCSPResp basicOCSPResp, X500Name x500Name) {
        if (x500Name == null) {
            return null;
        }
        X509CertificateHolder[] certHolders = basicOCSPResp.getCerts();
        Intrinsics.checkNotNullExpressionValue(certHolders, "certHolders");
        for (X509CertificateHolder x509CertificateHolder : certHolders) {
            Intrinsics.checkNotNull(x509CertificateHolder, "null cannot be cast to non-null type org.bouncycastle.cert.X509CertificateHolder");
            if (Intrinsics.areEqual(x509CertificateHolder.getSubject(), x500Name)) {
                return new JcaX509CertificateConverter().getCertificate(x509CertificateHolder);
            }
        }
        return null;
    }

    private final byte[] getKeyHashFromCertHolder(X509CertificateHolder x509CertificateHolder) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(x509CertificateHolder.getSubjectPublicKeyInfo().getPublicKeyData().getBytes());
        } catch (NoSuchAlgorithmException unused) {
            return new byte[0];
        }
    }

    private final void verifyOcspStatus(OCSPResp oCSPResp) {
        int status = oCSPResp.getStatus();
        if (status != 0) {
            if (status == 1) {
                throw new PKIOperationException("Your request did not fit the RFC 2560 syntax!");
            }
            if (status == 2) {
                throw new PKIOperationException("An internal error occurred in the OCSP Server!");
            }
            if (status == 3) {
                throw new PKIOperationException("The server was too busy to answer you!");
            }
            if (status == 5) {
                throw new PKIOperationException("Your request was not signed!");
            }
            if (status == 6) {
                throw new PKIOperationException("The server could not authenticate you!");
            }
            throw new OCSPException("OCSP response unsuccessful, status code: " + oCSPResp.getStatus());
        }
    }
}
