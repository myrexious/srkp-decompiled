package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.CertStatus;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes2.dex */
public class BasicOCSPRespBuilder {
    private RespID responderID;
    private List list = new ArrayList();
    private Extensions responseExtensions = null;

    /* loaded from: classes2.dex */
    public static class ResponseObject {
        CertificateID certId;
        CertStatus certStatus;
        Extensions extensions;
        ASN1GeneralizedTime nextUpdate;
        ASN1GeneralizedTime thisUpdate;

        /* JADX WARN: Removed duplicated region for block: B:35:0x005f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public ResponseObject(org.bouncycastle.cert.ocsp.CertificateID r5, org.bouncycastle.cert.ocsp.CertificateStatus r6, java.util.Date r7, java.util.Date r8, org.bouncycastle.asn1.x509.Extensions r9) {
            /*
                r4 = this;
                r4.<init>()
                r4.certId = r5
                r5 = 0
                if (r6 != 0) goto L10
                org.bouncycastle.asn1.ocsp.CertStatus r6 = new org.bouncycastle.asn1.ocsp.CertStatus
                r6.<init>()
            Ld:
                r4.certStatus = r6
                goto L56
            L10:
                boolean r0 = r6 instanceof org.bouncycastle.cert.ocsp.UnknownStatus
                if (r0 == 0) goto L1d
                org.bouncycastle.asn1.ocsp.CertStatus r6 = new org.bouncycastle.asn1.ocsp.CertStatus
                r0 = 2
                org.bouncycastle.asn1.DERNull r1 = org.bouncycastle.asn1.DERNull.INSTANCE
                r6.<init>(r0, r1)
                goto Ld
            L1d:
                org.bouncycastle.cert.ocsp.RevokedStatus r6 = (org.bouncycastle.cert.ocsp.RevokedStatus) r6
                boolean r0 = r6.hasRevocationReason()
                if (r0 == 0) goto L41
                org.bouncycastle.asn1.ocsp.CertStatus r0 = new org.bouncycastle.asn1.ocsp.CertStatus
                org.bouncycastle.asn1.ocsp.RevokedInfo r1 = new org.bouncycastle.asn1.ocsp.RevokedInfo
                org.bouncycastle.asn1.ASN1GeneralizedTime r2 = new org.bouncycastle.asn1.ASN1GeneralizedTime
                java.util.Date r3 = r6.getRevocationTime()
                r2.<init>(r3)
                int r6 = r6.getRevocationReason()
                org.bouncycastle.asn1.x509.CRLReason r6 = org.bouncycastle.asn1.x509.CRLReason.lookup(r6)
                r1.<init>(r2, r6)
                r0.<init>(r1)
                goto L54
            L41:
                org.bouncycastle.asn1.ocsp.CertStatus r0 = new org.bouncycastle.asn1.ocsp.CertStatus
                org.bouncycastle.asn1.ocsp.RevokedInfo r1 = new org.bouncycastle.asn1.ocsp.RevokedInfo
                org.bouncycastle.asn1.ASN1GeneralizedTime r2 = new org.bouncycastle.asn1.ASN1GeneralizedTime
                java.util.Date r6 = r6.getRevocationTime()
                r2.<init>(r6)
                r1.<init>(r2, r5)
                r0.<init>(r1)
            L54:
                r4.certStatus = r0
            L56:
                org.bouncycastle.asn1.DERGeneralizedTime r6 = new org.bouncycastle.asn1.DERGeneralizedTime
                r6.<init>(r7)
                r4.thisUpdate = r6
                if (r8 == 0) goto L64
                org.bouncycastle.asn1.DERGeneralizedTime r5 = new org.bouncycastle.asn1.DERGeneralizedTime
                r5.<init>(r8)
            L64:
                r4.nextUpdate = r5
                r4.extensions = r9
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.ocsp.BasicOCSPRespBuilder.ResponseObject.<init>(org.bouncycastle.cert.ocsp.CertificateID, org.bouncycastle.cert.ocsp.CertificateStatus, java.util.Date, java.util.Date, org.bouncycastle.asn1.x509.Extensions):void");
        }

        public SingleResponse toResponse() throws Exception {
            return new SingleResponse(this.certId.toASN1Primitive(), this.certStatus, this.thisUpdate, this.nextUpdate, this.extensions);
        }
    }

    public BasicOCSPRespBuilder(SubjectPublicKeyInfo subjectPublicKeyInfo, DigestCalculator digestCalculator) throws OCSPException {
        this.responderID = new RespID(subjectPublicKeyInfo, digestCalculator);
    }

    public BasicOCSPRespBuilder(RespID respID) {
        this.responderID = respID;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus) {
        addResponse(certificateID, certificateStatus, new Date(), null, null);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Date date2) {
        addResponse(certificateID, certificateStatus, date, date2, null);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Date date2, Extensions extensions) {
        this.list.add(new ResponseObject(certificateID, certificateStatus, date, date2, extensions));
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Extensions extensions) {
        addResponse(certificateID, certificateStatus, new Date(), date, extensions);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Extensions extensions) {
        addResponse(certificateID, certificateStatus, new Date(), null, extensions);
        return this;
    }

    public BasicOCSPResp build(ContentSigner contentSigner, X509CertificateHolder[] x509CertificateHolderArr, Date date) throws OCSPException {
        DERSequence dERSequence;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (ResponseObject responseObject : this.list) {
            try {
                aSN1EncodableVector.add(responseObject.toResponse());
            } catch (Exception e) {
                throw new OCSPException("exception creating Request", e);
            }
        }
        ResponseData responseData = new ResponseData(this.responderID.toASN1Primitive(), new ASN1GeneralizedTime(date), new DERSequence(aSN1EncodableVector), this.responseExtensions);
        try {
            OutputStream outputStream = contentSigner.getOutputStream();
            outputStream.write(responseData.getEncoded(ASN1Encoding.DER));
            outputStream.close();
            DERBitString dERBitString = new DERBitString(contentSigner.getSignature());
            AlgorithmIdentifier algorithmIdentifier = contentSigner.getAlgorithmIdentifier();
            if (x509CertificateHolderArr == null || x509CertificateHolderArr.length <= 0) {
                dERSequence = null;
            } else {
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                for (int i = 0; i != x509CertificateHolderArr.length; i++) {
                    aSN1EncodableVector2.add(x509CertificateHolderArr[i].toASN1Structure());
                }
                dERSequence = new DERSequence(aSN1EncodableVector2);
            }
            return new BasicOCSPResp(new BasicOCSPResponse(responseData, algorithmIdentifier, dERBitString, dERSequence));
        } catch (Exception e2) {
            throw new OCSPException("exception processing TBSRequest: " + e2.getMessage(), e2);
        }
    }

    public BasicOCSPRespBuilder setResponseExtensions(Extensions extensions) {
        this.responseExtensions = extensions;
        return this;
    }
}