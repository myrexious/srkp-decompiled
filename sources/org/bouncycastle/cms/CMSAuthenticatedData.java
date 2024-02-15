package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.AuthenticatedData;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEnvelopedHelper;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;

/* loaded from: classes2.dex */
public class CMSAuthenticatedData implements Encodable {
    private ASN1Set authAttrs;
    ContentInfo contentInfo;
    private byte[] mac;
    private AlgorithmIdentifier macAlg;
    private OriginatorInformation originatorInfo;
    RecipientInformationStore recipientInfoStore;
    private ASN1Set unauthAttrs;

    public CMSAuthenticatedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSAuthenticatedData(InputStream inputStream, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream), digestCalculatorProvider);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo) throws CMSException {
        this(contentInfo, (DigestCalculatorProvider) null);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this.contentInfo = contentInfo;
        AuthenticatedData authenticatedData = AuthenticatedData.getInstance(contentInfo.getContent());
        if (authenticatedData.getOriginatorInfo() != null) {
            this.originatorInfo = new OriginatorInformation(authenticatedData.getOriginatorInfo());
        }
        ASN1Set recipientInfos = authenticatedData.getRecipientInfos();
        this.macAlg = authenticatedData.getMacAlgorithm();
        this.authAttrs = authenticatedData.getAuthAttrs();
        this.mac = authenticatedData.getMac().getOctets();
        this.unauthAttrs = authenticatedData.getUnauthAttrs();
        ContentInfo encapsulatedContentInfo = authenticatedData.getEncapsulatedContentInfo();
        CMSProcessableByteArray cMSProcessableByteArray = new CMSProcessableByteArray(encapsulatedContentInfo.getContentType(), ASN1OctetString.getInstance(encapsulatedContentInfo.getContent()).getOctets());
        if (this.authAttrs == null) {
            this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, this.macAlg, new CMSEnvelopedHelper.CMSAuthenticatedSecureReadable(this.macAlg, encapsulatedContentInfo.getContentType(), cMSProcessableByteArray));
        } else if (digestCalculatorProvider == null) {
            throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
        } else {
            ASN1EncodableVector all = new AttributeTable(this.authAttrs).getAll(CMSAttributes.cmsAlgorithmProtect);
            if (all.size() > 1) {
                throw new CMSException("Only one instance of a cmsAlgorithmProtect attribute can be present");
            }
            if (all.size() > 0) {
                Attribute attribute = Attribute.getInstance(all.get(0));
                if (attribute.getAttrValues().size() != 1) {
                    throw new CMSException("A cmsAlgorithmProtect attribute MUST contain exactly one value");
                }
                CMSAlgorithmProtection cMSAlgorithmProtection = CMSAlgorithmProtection.getInstance(attribute.getAttributeValues()[0]);
                if (!CMSUtils.isEquivalent(cMSAlgorithmProtection.getDigestAlgorithm(), authenticatedData.getDigestAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for digestAlgorithm");
                }
                if (!CMSUtils.isEquivalent(cMSAlgorithmProtection.getMacAlgorithm(), this.macAlg)) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for macAlgorithm");
                }
            }
            try {
                this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, this.macAlg, new CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable(digestCalculatorProvider.get(authenticatedData.getDigestAlgorithm()), encapsulatedContentInfo.getContentType(), cMSProcessableByteArray), new AuthAttributesProvider() { // from class: org.bouncycastle.cms.CMSAuthenticatedData.1
                    @Override // org.bouncycastle.cms.AuthAttributesProvider
                    public ASN1Set getAuthAttributes() {
                        return CMSAuthenticatedData.this.authAttrs;
                    }

                    @Override // org.bouncycastle.cms.AuthAttributesProvider
                    public boolean isAead() {
                        return false;
                    }
                });
            } catch (OperatorCreationException e) {
                throw new CMSException("unable to create digest calculator: " + e.getMessage(), e);
            }
        }
    }

    public CMSAuthenticatedData(byte[] bArr) throws CMSException {
        this(CMSUtils.readContentInfo(bArr));
    }

    public CMSAuthenticatedData(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this(CMSUtils.readContentInfo(bArr), digestCalculatorProvider);
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AttributeTable getAuthAttrs() {
        if (this.authAttrs == null) {
            return null;
        }
        return new AttributeTable(this.authAttrs);
    }

    public byte[] getContentDigest() {
        if (this.authAttrs != null) {
            return ASN1OctetString.getInstance(getAuthAttrs().get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        }
        return null;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    public String getMacAlgOID() {
        return this.macAlg.getAlgorithm().getId();
    }

    public byte[] getMacAlgParams() {
        try {
            return encodeObj(this.macAlg.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.macAlg;
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.originatorInfo;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.recipientInfoStore;
    }

    public AttributeTable getUnauthAttrs() {
        if (this.unauthAttrs == null) {
            return null;
        }
        return new AttributeTable(this.unauthAttrs);
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }
}
