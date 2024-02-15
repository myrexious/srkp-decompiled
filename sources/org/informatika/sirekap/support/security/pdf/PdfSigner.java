package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.informatika.sirekap.support.security.signature.Signer;

/* compiled from: PdfSigner.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u000eJ(\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfSigner;", "", "signer", "Lorg/informatika/sirekap/support/security/signature/Signer;", "(Lorg/informatika/sirekap/support/security/signature/Signer;)V", "buildDocumentAndCheck", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "documentStream", "Ljava/io/InputStream;", "buildSignature", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/PDSignature;", "sign", "", "output", "Ljava/io/OutputStream;", "permission", "Lorg/informatika/sirekap/support/security/pdf/PdfMDPPermission;", "mdpSetter", "Lorg/informatika/sirekap/support/security/pdf/PdfMDPSetter;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfSigner {
    private final Signer signer;

    public PdfSigner(Signer signer) {
        Intrinsics.checkNotNullParameter(signer, "signer");
        this.signer = signer;
    }

    private final PDSignature buildSignature() {
        X500Name x500Name = new X500Name(this.signer.getSignerCertificate().getSubjectX500Principal().toString());
        String obj = x500Name.getRDNs(BCStyle.CN)[0].getFirst().getValue().toString();
        String obj2 = x500Name.getRDNs(BCStyle.L)[0].getFirst().getValue().toString();
        PDSignature pDSignature = new PDSignature();
        pDSignature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        pDSignature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
        pDSignature.setName(obj);
        pDSignature.setLocation(obj2);
        pDSignature.setReason("Legit Document signed by Sirekap Application");
        pDSignature.setSignDate(Calendar.getInstance());
        return pDSignature;
    }

    private final PDDocument buildDocumentAndCheck(InputStream inputStream) {
        PDDocument document = PDDocument.load(inputStream);
        PdfMDP pdfMDP = new PdfMDP();
        Intrinsics.checkNotNullExpressionValue(document, "document");
        if (pdfMDP.getPermission(document) != PdfMDPPermission.NoChange) {
            return document;
        }
        throw new IllegalStateException("document is not allowed to change");
    }

    public final void sign(InputStream documentStream, OutputStream output) {
        Intrinsics.checkNotNullParameter(documentStream, "documentStream");
        Intrinsics.checkNotNullParameter(output, "output");
        PDDocument buildDocumentAndCheck = buildDocumentAndCheck(documentStream);
        SignatureOptions signatureOptions = new SignatureOptions();
        signatureOptions.setPreferredSignatureSize(18944);
        buildDocumentAndCheck.addSignature(buildSignature(), this.signer, signatureOptions);
        buildDocumentAndCheck.saveIncremental(output);
    }

    public final void sign(InputStream documentStream, PdfMDPPermission pdfMDPPermission, OutputStream output) {
        Intrinsics.checkNotNullParameter(documentStream, "documentStream");
        Intrinsics.checkNotNullParameter(output, "output");
        sign(documentStream, pdfMDPPermission, new PdfMDP(), output);
    }

    public final void sign(InputStream documentStream, PdfMDPPermission pdfMDPPermission, PdfMDPSetter mdpSetter, OutputStream output) {
        Intrinsics.checkNotNullParameter(documentStream, "documentStream");
        Intrinsics.checkNotNullParameter(mdpSetter, "mdpSetter");
        Intrinsics.checkNotNullParameter(output, "output");
        PDSignature buildSignature = buildSignature();
        PDDocument buildDocumentAndCheck = buildDocumentAndCheck(documentStream);
        SignatureOptions signatureOptions = new SignatureOptions();
        signatureOptions.setPreferredSignatureSize(18944);
        if (pdfMDPPermission != null) {
            mdpSetter.setPermission(buildDocumentAndCheck, buildSignature, pdfMDPPermission);
        } else {
            buildDocumentAndCheck.getDocumentCatalog().getCOSObject().setNeedToBeUpdated(true);
            buildSignature.getCOSObject().setNeedToBeUpdated(true);
        }
        buildDocumentAndCheck.addSignature(buildSignature, this.signer, signatureOptions);
        buildDocumentAndCheck.saveIncremental(output);
        buildDocumentAndCheck.close();
    }
}
