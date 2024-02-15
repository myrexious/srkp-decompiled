package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class TimeStampResponse {
    TimeStampResp resp;
    TimeStampToken timeStampToken;

    public TimeStampResponse(InputStream inputStream) throws TSPException, IOException {
        this(readTimeStampResp(inputStream));
    }

    public TimeStampResponse(DLSequence dLSequence) throws TSPException, IOException {
        try {
            this.resp = TimeStampResp.getInstance(dLSequence);
            this.timeStampToken = new TimeStampToken(ContentInfo.getInstance(dLSequence.getObjectAt(1)));
        } catch (ClassCastException e) {
            throw new TSPException("malformed timestamp response: " + e, e);
        } catch (IllegalArgumentException e2) {
            throw new TSPException("malformed timestamp response: " + e2, e2);
        }
    }

    public TimeStampResponse(TimeStampResp timeStampResp) throws TSPException, IOException {
        this.resp = timeStampResp;
        if (timeStampResp.getTimeStampToken() != null) {
            this.timeStampToken = new TimeStampToken(timeStampResp.getTimeStampToken());
        }
    }

    public TimeStampResponse(byte[] bArr) throws TSPException, IOException {
        this(new ByteArrayInputStream(bArr));
    }

    private static TimeStampResp readTimeStampResp(InputStream inputStream) throws IOException, TSPException {
        try {
            return TimeStampResp.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (ClassCastException e) {
            throw new TSPException("malformed timestamp response: " + e, e);
        } catch (IllegalArgumentException e2) {
            throw new TSPException("malformed timestamp response: " + e2, e2);
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.resp.getEncoded();
    }

    public byte[] getEncoded(String str) throws IOException {
        return ASN1Encoding.DL.equals(str) ? this.timeStampToken == null ? new DLSequence(this.resp.getStatus()).getEncoded(str) : new DLSequence(new ASN1Encodable[]{this.resp.getStatus(), this.timeStampToken.toCMSSignedData().toASN1Structure()}).getEncoded(str) : this.resp.getEncoded(str);
    }

    public PKIFailureInfo getFailInfo() {
        if (this.resp.getStatus().getFailInfo() != null) {
            return new PKIFailureInfo(this.resp.getStatus().getFailInfo());
        }
        return null;
    }

    public int getStatus() {
        return this.resp.getStatus().getStatus().intValue();
    }

    public String getStatusString() {
        if (this.resp.getStatus().getStatusString() != null) {
            StringBuffer stringBuffer = new StringBuffer();
            PKIFreeText statusString = this.resp.getStatus().getStatusString();
            for (int i = 0; i != statusString.size(); i++) {
                stringBuffer.append(statusString.getStringAtUTF8(i).getString());
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public TimeStampToken getTimeStampToken() {
        return this.timeStampToken;
    }

    public void validate(TimeStampRequest timeStampRequest) throws TSPException {
        TimeStampToken timeStampToken = getTimeStampToken();
        if (timeStampToken == null) {
            if (getStatus() == 0 || getStatus() == 1) {
                throw new TSPValidationException("no time stamp token found and one expected.");
            }
            return;
        }
        TimeStampTokenInfo timeStampInfo = timeStampToken.getTimeStampInfo();
        if (timeStampRequest.getNonce() != null && !timeStampRequest.getNonce().equals(timeStampInfo.getNonce())) {
            throw new TSPValidationException("response contains wrong nonce value.");
        }
        if (getStatus() != 0 && getStatus() != 1) {
            throw new TSPValidationException("time stamp token found in failed request.");
        }
        if (!Arrays.constantTimeAreEqual(timeStampRequest.getMessageImprintDigest(), timeStampInfo.getMessageImprintDigest())) {
            throw new TSPValidationException("response for different message imprint digest.");
        }
        if (!timeStampInfo.getMessageImprintAlgOID().equals((ASN1Primitive) timeStampRequest.getMessageImprintAlgOID())) {
            throw new TSPValidationException("response for different message imprint algorithm.");
        }
        Attribute attribute = timeStampToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
        Attribute attribute2 = timeStampToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
        if (attribute == null && attribute2 == null) {
            throw new TSPValidationException("no signing certificate attribute present.");
        }
        if (timeStampRequest.getReqPolicy() != null && !timeStampRequest.getReqPolicy().equals((ASN1Primitive) timeStampInfo.getPolicy())) {
            throw new TSPValidationException("TSA policy wrong for request.");
        }
    }
}
