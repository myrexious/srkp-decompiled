package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.encryption.ProtectionPolicy;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdfEncrypt.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfEncrypt;", "", "policy", "Lcom/tom_roush/pdfbox/pdmodel/encryption/ProtectionPolicy;", "(Lcom/tom_roush/pdfbox/pdmodel/encryption/ProtectionPolicy;)V", "encrypt", "", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "docStream", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfEncrypt {
    private final ProtectionPolicy policy;

    public PdfEncrypt(ProtectionPolicy policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        this.policy = policy;
    }

    public final void encrypt(PDDocument document) {
        Intrinsics.checkNotNullParameter(document, "document");
        document.protect(this.policy);
    }

    public final void encrypt(InputStream docStream, OutputStream output) {
        Intrinsics.checkNotNullParameter(docStream, "docStream");
        Intrinsics.checkNotNullParameter(output, "output");
        PDDocument document = PDDocument.load(docStream);
        Intrinsics.checkNotNullExpressionValue(document, "document");
        encrypt(document);
        document.save(output);
    }
}
