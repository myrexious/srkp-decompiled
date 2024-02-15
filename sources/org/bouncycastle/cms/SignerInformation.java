package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.SignerIdentifier;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RawContentVerifier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.TeeOutputStream;

/* loaded from: classes2.dex */
public class SignerInformation {
    private final CMSProcessable content;
    private final ASN1ObjectIdentifier contentType;
    protected final AlgorithmIdentifier digestAlgorithm;
    protected final AlgorithmIdentifier encryptionAlgorithm;
    protected final SignerInfo info;
    private final boolean isCounterSignature;
    private byte[] resultDigest;
    private final SignerId sid;
    private final byte[] signature;
    protected final ASN1Set signedAttributeSet;
    private AttributeTable signedAttributeValues;
    protected final ASN1Set unsignedAttributeSet;
    private AttributeTable unsignedAttributeValues;

    public SignerInformation(SignerInfo signerInfo, ASN1ObjectIdentifier aSN1ObjectIdentifier, CMSProcessable cMSProcessable, byte[] bArr) {
        SignerId signerId;
        this.info = signerInfo;
        this.contentType = aSN1ObjectIdentifier;
        this.isCounterSignature = aSN1ObjectIdentifier == null;
        SignerIdentifier sid = signerInfo.getSID();
        boolean isTagged = sid.isTagged();
        ASN1Encodable id2 = sid.getId();
        if (isTagged) {
            signerId = new SignerId(ASN1OctetString.getInstance(id2).getOctets());
        } else {
            IssuerAndSerialNumber issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(id2);
            signerId = new SignerId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        }
        this.sid = signerId;
        this.digestAlgorithm = signerInfo.getDigestAlgorithm();
        this.signedAttributeSet = signerInfo.getAuthenticatedAttributes();
        this.unsignedAttributeSet = signerInfo.getUnauthenticatedAttributes();
        this.encryptionAlgorithm = signerInfo.getDigestEncryptionAlgorithm();
        this.signature = signerInfo.getEncryptedDigest().getOctets();
        this.content = cMSProcessable;
        this.resultDigest = bArr;
    }

    public SignerInformation(SignerInformation signerInformation) {
        this(signerInformation, signerInformation.info);
    }

    public SignerInformation(SignerInformation signerInformation, SignerInfo signerInfo) {
        this.info = signerInfo;
        this.contentType = signerInformation.contentType;
        this.isCounterSignature = signerInformation.isCounterSignature();
        this.sid = signerInformation.getSID();
        this.digestAlgorithm = signerInfo.getDigestAlgorithm();
        this.signedAttributeSet = signerInfo.getAuthenticatedAttributes();
        this.unsignedAttributeSet = signerInfo.getUnauthenticatedAttributes();
        this.encryptionAlgorithm = signerInfo.getDigestEncryptionAlgorithm();
        this.signature = signerInfo.getEncryptedDigest().getOctets();
        this.content = signerInformation.content;
        this.resultDigest = signerInformation.resultDigest;
        this.signedAttributeValues = getSignedAttributes();
        this.unsignedAttributeValues = getUnsignedAttributes();
    }

    public static SignerInformation addCounterSigners(SignerInformation signerInformation, SignerInformationStore signerInformationStore) {
        SignerInfo signerInfo = signerInformation.info;
        AttributeTable unsignedAttributes = signerInformation.getUnsignedAttributes();
        ASN1EncodableVector aSN1EncodableVector = unsignedAttributes != null ? unsignedAttributes.toASN1EncodableVector() : new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (SignerInformation signerInformation2 : signerInformationStore.getSigners()) {
            aSN1EncodableVector2.add(signerInformation2.toASN1Structure());
        }
        aSN1EncodableVector.add(new Attribute(CMSAttributes.counterSignature, new DERSet(aSN1EncodableVector2)));
        return new SignerInformation(new SignerInfo(signerInfo.getSID(), signerInfo.getDigestAlgorithm(), signerInfo.getAuthenticatedAttributes(), signerInfo.getDigestEncryptionAlgorithm(), signerInfo.getEncryptedDigest(), new DERSet(aSN1EncodableVector)), signerInformation.contentType, signerInformation.content, null);
    }

    private boolean doVerify(SignerInformationVerifier signerInformationVerifier) throws CMSException {
        String encryptionAlgName = CMSSignedHelper.INSTANCE.getEncryptionAlgName(getEncryptionAlgOID());
        try {
            ContentVerifier contentVerifier = signerInformationVerifier.getContentVerifier(this.encryptionAlgorithm, this.info.getDigestAlgorithm());
            try {
                OutputStream outputStream = contentVerifier.getOutputStream();
                if (this.resultDigest == null) {
                    DigestCalculator digestCalculator = signerInformationVerifier.getDigestCalculator(getDigestAlgorithmID());
                    if (this.content != null) {
                        OutputStream outputStream2 = digestCalculator.getOutputStream();
                        if (this.signedAttributeSet != null) {
                            this.content.write(outputStream2);
                            outputStream.write(getEncodedSignedAttributes());
                        } else if (contentVerifier instanceof RawContentVerifier) {
                            this.content.write(outputStream2);
                        } else {
                            TeeOutputStream teeOutputStream = new TeeOutputStream(outputStream2, outputStream);
                            this.content.write(teeOutputStream);
                            teeOutputStream.close();
                        }
                        outputStream2.close();
                    } else if (this.signedAttributeSet == null) {
                        throw new CMSException("data not encapsulated in signature - use detached constructor.");
                    } else {
                        outputStream.write(getEncodedSignedAttributes());
                    }
                    this.resultDigest = digestCalculator.getDigest();
                } else if (this.signedAttributeSet == null) {
                    CMSProcessable cMSProcessable = this.content;
                    if (cMSProcessable != null) {
                        cMSProcessable.write(outputStream);
                    }
                } else {
                    outputStream.write(getEncodedSignedAttributes());
                }
                outputStream.close();
                verifyContentTypeAttributeValue();
                AttributeTable signedAttributes = getSignedAttributes();
                verifyAlgorithmIdentifierProtectionAttribute(signedAttributes);
                verifyMessageDigestAttribute();
                verifyCounterSignatureAttribute(signedAttributes);
                try {
                    if (this.signedAttributeSet == null && this.resultDigest != null && (contentVerifier instanceof RawContentVerifier)) {
                        RawContentVerifier rawContentVerifier = (RawContentVerifier) contentVerifier;
                        return encryptionAlgName.equals("RSA") ? rawContentVerifier.verify(new DigestInfo(new AlgorithmIdentifier(this.digestAlgorithm.getAlgorithm(), DERNull.INSTANCE), this.resultDigest).getEncoded(ASN1Encoding.DER), getSignature()) : rawContentVerifier.verify(this.resultDigest, getSignature());
                    }
                    return contentVerifier.verify(getSignature());
                } catch (IOException e) {
                    throw new CMSException("can't process mime object to create signature.", e);
                }
            } catch (IOException e2) {
                throw new CMSException("can't process mime object to create signature.", e2);
            } catch (OperatorCreationException e3) {
                throw new CMSException("can't create digest calculator: " + e3.getMessage(), e3);
            }
        } catch (OperatorCreationException e4) {
            throw new CMSException("can't create content verifier: " + e4.getMessage(), e4);
        }
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    private Time getSigningTime() throws CMSException {
        ASN1Primitive singleValuedSignedAttribute = getSingleValuedSignedAttribute(CMSAttributes.signingTime, "signing-time");
        if (singleValuedSignedAttribute == null) {
            return null;
        }
        try {
            return Time.getInstance(singleValuedSignedAttribute);
        } catch (IllegalArgumentException unused) {
            throw new CMSException("signing-time attribute value not a valid 'Time' structure");
        }
    }

    private ASN1Primitive getSingleValuedSignedAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) throws CMSException {
        ASN1EncodableVector all;
        int size;
        AttributeTable unsignedAttributes = getUnsignedAttributes();
        if (unsignedAttributes == null || unsignedAttributes.getAll(aSN1ObjectIdentifier).size() <= 0) {
            AttributeTable signedAttributes = getSignedAttributes();
            if (signedAttributes == null || (size = (all = signedAttributes.getAll(aSN1ObjectIdentifier)).size()) == 0) {
                return null;
            }
            if (size == 1) {
                ASN1Set attrValues = ((Attribute) all.get(0)).getAttrValues();
                if (attrValues.size() == 1) {
                    return attrValues.getObjectAt(0).toASN1Primitive();
                }
                throw new CMSException("A " + str + " attribute MUST have a single attribute value");
            }
            throw new CMSException("The SignedAttributes in a signerInfo MUST NOT include multiple instances of the " + str + " attribute");
        }
        throw new CMSException("The " + str + " attribute MUST NOT be an unsigned attribute");
    }

    public static SignerInformation replaceUnsignedAttributes(SignerInformation signerInformation, AttributeTable attributeTable) {
        SignerInfo signerInfo = signerInformation.info;
        return new SignerInformation(new SignerInfo(signerInfo.getSID(), signerInfo.getDigestAlgorithm(), signerInfo.getAuthenticatedAttributes(), signerInfo.getDigestEncryptionAlgorithm(), signerInfo.getEncryptedDigest(), attributeTable != null ? new DERSet(attributeTable.toASN1EncodableVector()) : null), signerInformation.contentType, signerInformation.content, null);
    }

    private void verifyAlgorithmIdentifierProtectionAttribute(AttributeTable attributeTable) throws CMSException {
        AttributeTable unsignedAttributes = getUnsignedAttributes();
        if (unsignedAttributes != null && unsignedAttributes.getAll(CMSAttributes.cmsAlgorithmProtect).size() > 0) {
            throw new CMSException("A cmsAlgorithmProtect attribute MUST be a signed attribute");
        }
        if (attributeTable != null) {
            ASN1EncodableVector all = attributeTable.getAll(CMSAttributes.cmsAlgorithmProtect);
            if (all.size() > 1) {
                throw new CMSException("Only one instance of a cmsAlgorithmProtect attribute can be present");
            }
            if (all.size() > 0) {
                Attribute attribute = Attribute.getInstance(all.get(0));
                if (attribute.getAttrValues().size() != 1) {
                    throw new CMSException("A cmsAlgorithmProtect attribute MUST contain exactly one value");
                }
                CMSAlgorithmProtection cMSAlgorithmProtection = CMSAlgorithmProtection.getInstance(attribute.getAttributeValues()[0]);
                if (!CMSUtils.isEquivalent(cMSAlgorithmProtection.getDigestAlgorithm(), this.info.getDigestAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for digestAlgorithm");
                }
                if (!CMSUtils.isEquivalent(cMSAlgorithmProtection.getSignatureAlgorithm(), this.info.getDigestEncryptionAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for signatureAlgorithm");
                }
            }
        }
    }

    private void verifyContentTypeAttributeValue() throws CMSException {
        ASN1Primitive singleValuedSignedAttribute = getSingleValuedSignedAttribute(CMSAttributes.contentType, "content-type");
        if (singleValuedSignedAttribute == null) {
            if (!this.isCounterSignature && this.signedAttributeSet != null) {
                throw new CMSException("The content-type attribute type MUST be present whenever signed attributes are present in signed-data");
            }
        } else if (this.isCounterSignature) {
            throw new CMSException("[For counter signatures,] the signedAttributes field MUST NOT contain a content-type attribute");
        } else {
            if (!(singleValuedSignedAttribute instanceof ASN1ObjectIdentifier)) {
                throw new CMSException("content-type attribute value not of ASN.1 type 'OBJECT IDENTIFIER'");
            }
            if (!((ASN1ObjectIdentifier) singleValuedSignedAttribute).equals((ASN1Primitive) this.contentType)) {
                throw new CMSException("content-type attribute value does not match eContentType");
            }
        }
    }

    private void verifyCounterSignatureAttribute(AttributeTable attributeTable) throws CMSException {
        if (attributeTable != null && attributeTable.getAll(CMSAttributes.counterSignature).size() > 0) {
            throw new CMSException("A countersignature attribute MUST NOT be a signed attribute");
        }
        AttributeTable unsignedAttributes = getUnsignedAttributes();
        if (unsignedAttributes != null) {
            ASN1EncodableVector all = unsignedAttributes.getAll(CMSAttributes.counterSignature);
            for (int i = 0; i < all.size(); i++) {
                if (Attribute.getInstance(all.get(i)).getAttrValues().size() < 1) {
                    throw new CMSException("A countersignature attribute MUST contain at least one AttributeValue");
                }
            }
        }
    }

    private void verifyMessageDigestAttribute() throws CMSException {
        ASN1Primitive singleValuedSignedAttribute = getSingleValuedSignedAttribute(CMSAttributes.messageDigest, "message-digest");
        if (singleValuedSignedAttribute == null) {
            if (this.signedAttributeSet != null) {
                throw new CMSException("the message-digest signed attribute type MUST be present when there are any signed attributes present");
            }
        } else if (!(singleValuedSignedAttribute instanceof ASN1OctetString)) {
            throw new CMSException("message-digest attribute value not of ASN.1 type 'OCTET STRING'");
        } else {
            if (!Arrays.constantTimeAreEqual(this.resultDigest, ((ASN1OctetString) singleValuedSignedAttribute).getOctets())) {
                throw new CMSSignerDigestMismatchException("message-digest attribute value does not match calculated value");
            }
        }
    }

    public byte[] getContentDigest() {
        byte[] bArr = this.resultDigest;
        if (bArr != null) {
            return Arrays.clone(bArr);
        }
        throw new IllegalStateException("method can only be called after verify.");
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public SignerInformationStore getCounterSignatures() {
        AttributeTable unsignedAttributes = getUnsignedAttributes();
        if (unsignedAttributes == null) {
            return new SignerInformationStore(new ArrayList(0));
        }
        ArrayList arrayList = new ArrayList();
        ASN1EncodableVector all = unsignedAttributes.getAll(CMSAttributes.counterSignature);
        for (int i = 0; i < all.size(); i++) {
            ASN1Set attrValues = ((Attribute) all.get(i)).getAttrValues();
            attrValues.size();
            Enumeration objects = attrValues.getObjects();
            while (objects.hasMoreElements()) {
                arrayList.add(new SignerInformation(SignerInfo.getInstance(objects.nextElement()), null, new CMSProcessableByteArray(getSignature()), null));
            }
        }
        return new SignerInformationStore(arrayList);
    }

    public String getDigestAlgOID() {
        return this.digestAlgorithm.getAlgorithm().getId();
    }

    public byte[] getDigestAlgParams() {
        try {
            return encodeObj(this.digestAlgorithm.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting digest parameters " + e);
        }
    }

    public AlgorithmIdentifier getDigestAlgorithmID() {
        return this.digestAlgorithm;
    }

    public byte[] getEncodedSignedAttributes() throws IOException {
        ASN1Set aSN1Set = this.signedAttributeSet;
        if (aSN1Set != null) {
            return aSN1Set.getEncoded(ASN1Encoding.DER);
        }
        return null;
    }

    public String getEncryptionAlgOID() {
        return this.encryptionAlgorithm.getAlgorithm().getId();
    }

    public byte[] getEncryptionAlgParams() {
        try {
            return encodeObj(this.encryptionAlgorithm.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public SignerId getSID() {
        return this.sid;
    }

    public byte[] getSignature() {
        return Arrays.clone(this.signature);
    }

    public AttributeTable getSignedAttributes() {
        if (this.signedAttributeSet != null && this.signedAttributeValues == null) {
            this.signedAttributeValues = new AttributeTable(this.signedAttributeSet);
        }
        return this.signedAttributeValues;
    }

    public AttributeTable getUnsignedAttributes() {
        if (this.unsignedAttributeSet != null && this.unsignedAttributeValues == null) {
            this.unsignedAttributeValues = new AttributeTable(this.unsignedAttributeSet);
        }
        return this.unsignedAttributeValues;
    }

    public int getVersion() {
        return this.info.getVersion().intValueExact();
    }

    public boolean isCounterSignature() {
        return this.isCounterSignature;
    }

    public SignerInfo toASN1Structure() {
        return this.info;
    }

    public boolean verify(SignerInformationVerifier signerInformationVerifier) throws CMSException {
        Time signingTime = getSigningTime();
        if (!signerInformationVerifier.hasAssociatedCertificate() || signingTime == null || signerInformationVerifier.getAssociatedCertificate().isValidOn(signingTime.getDate())) {
            return doVerify(signerInformationVerifier);
        }
        throw new CMSVerifierCertificateNotValidException("verifier not valid at signingTime");
    }
}
