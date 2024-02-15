package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdfTimestampSigner.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfTimestampSigner;", "", "signer", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/SignatureInterface;", "(Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/SignatureInterface;)V", "buildDocumentAndCheck", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "documentStream", "Ljava/io/InputStream;", "buildSignature", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/PDSignature;", "sign", "", "output", "Ljava/io/OutputStream;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfTimestampSigner {
    private final SignatureInterface signer;

    public PdfTimestampSigner(SignatureInterface signer) {
        Intrinsics.checkNotNullParameter(signer, "signer");
        this.signer = signer;
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

    private final PDSignature buildSignature() {
        PDSignature pDSignature = new PDSignature();
        pDSignature.setType(COSName.DOC_TIME_STAMP);
        pDSignature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        pDSignature.setSubFilter(COSName.getPDFName("ETSI.RFC3161"));
        return pDSignature;
    }

    public final void sign(InputStream documentStream, OutputStream output) {
        Intrinsics.checkNotNullParameter(documentStream, "documentStream");
        Intrinsics.checkNotNullParameter(output, "output");
        PDDocument buildDocumentAndCheck = buildDocumentAndCheck(documentStream);
        buildDocumentAndCheck.addSignature(buildSignature(), this.signer);
        buildDocumentAndCheck.saveIncremental(output);
    }
}
