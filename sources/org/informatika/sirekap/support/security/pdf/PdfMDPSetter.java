package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import kotlin.Metadata;

/* compiled from: PdfMDPSetter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfMDPSetter;", "", "setPermission", "", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "signature", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/PDSignature;", "permission", "Lorg/informatika/sirekap/support/security/pdf/PdfMDPPermission;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PdfMDPSetter {
    void setPermission(PDDocument pDDocument, PDSignature pDSignature, PdfMDPPermission pdfMDPPermission);
}
