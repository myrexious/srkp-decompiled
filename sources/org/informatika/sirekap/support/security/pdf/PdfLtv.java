package org.informatika.sirekap.support.security.pdf;

import android.content.Context;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import java.security.cert.TrustAnchor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.security.PKIFacade;
import org.informatika.sirekap.support.security.pki.CRLUtility;
import org.informatika.sirekap.support.security.pki.OCSPUtility;
import org.informatika.sirekap.support.security.signature.TimestampSigner;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* compiled from: PdfLtv.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfLtv;", "", "validationInfo", "Lorg/informatika/sirekap/support/security/pdf/PdfValidationInformation;", "timestampSigner", "Lorg/informatika/sirekap/support/security/pdf/PdfTimestampSigner;", "(Lorg/informatika/sirekap/support/security/pdf/PdfValidationInformation;Lorg/informatika/sirekap/support/security/pdf/PdfTimestampSigner;)V", "addLtv", "", "documentFile", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isDocumentHasSignTimestamp", "", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "Factory", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfLtv {
    private final PdfTimestampSigner timestampSigner;
    private final PdfValidationInformation validationInfo;

    public PdfLtv(PdfValidationInformation validationInfo, PdfTimestampSigner timestampSigner) {
        Intrinsics.checkNotNullParameter(validationInfo, "validationInfo");
        Intrinsics.checkNotNullParameter(timestampSigner, "timestampSigner");
        this.validationInfo = validationInfo;
        this.timestampSigner = timestampSigner;
    }

    public final boolean isDocumentHasSignTimestamp(PDDocument document) {
        Intrinsics.checkNotNullParameter(document, "document");
        for (PDSignature pDSignature : document.getSignatureDictionaries()) {
            if (Intrinsics.areEqual(pDSignature.getSubFilter(), "ETSI.RFC3161")) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addLtv(java.io.File r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof org.informatika.sirekap.support.security.pdf.PdfLtv$addLtv$1
            if (r0 == 0) goto L14
            r0 = r13
            org.informatika.sirekap.support.security.pdf.PdfLtv$addLtv$1 r0 = (org.informatika.sirekap.support.security.pdf.PdfLtv$addLtv$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            org.informatika.sirekap.support.security.pdf.PdfLtv$addLtv$1 r0 = new org.informatika.sirekap.support.security.pdf.PdfLtv$addLtv$1
            r0.<init>(r11, r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            java.lang.String r4 = "tmp"
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r12 = r0.L$1
            java.io.File r12 = (java.io.File) r12
            java.lang.Object r0 = r0.L$0
            java.io.File r0 = (java.io.File) r0
            kotlin.ResultKt.throwOnFailure(r13)
            r5 = r12
            r6 = r0
            goto L8b
        L36:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L3e:
            kotlin.ResultKt.throwOnFailure(r13)
            com.tom_roush.pdfbox.pdmodel.PDDocument r13 = com.tom_roush.pdfbox.pdmodel.PDDocument.load(r12)
            java.lang.String r2 = ".pdf"
            java.io.File r2 = java.io.File.createTempFile(r4, r2)
            java.lang.String r5 = "document"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r5)
            boolean r5 = r11.isDocumentHasSignTimestamp(r13)
            if (r5 != 0) goto L6d
            org.informatika.sirekap.support.security.pdf.PdfTimestampSigner r5 = r11.timestampSigner
            java.io.FileInputStream r6 = new java.io.FileInputStream
            r6.<init>(r12)
            java.io.InputStream r6 = (java.io.InputStream) r6
            java.io.FileOutputStream r7 = new java.io.FileOutputStream
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r7.<init>(r2)
            java.io.OutputStream r7 = (java.io.OutputStream) r7
            r5.sign(r6, r7)
            goto L77
        L6d:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            byte[] r5 = kotlin.io.FilesKt.readBytes(r12)
            kotlin.io.FilesKt.writeBytes(r2, r5)
        L77:
            r13.close()
            org.informatika.sirekap.support.security.pdf.PdfValidationInformation r13 = r11.validationInfo
            r0.L$0 = r12
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r13 = r13.add(r2, r0)
            if (r13 != r1) goto L89
            return r1
        L89:
            r6 = r12
            r5 = r2
        L8b:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
            r7 = 1
            r8 = 0
            r9 = 4
            r10 = 0
            kotlin.io.FilesKt.copyTo$default(r5, r6, r7, r8, r9, r10)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.pdf.PdfLtv.addLtv(java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: PdfLtv.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "", "timestampFactory", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;", "crlUtility", "Lorg/informatika/sirekap/support/security/pki/CRLUtility;", "ocspUtility", "Lorg/informatika/sirekap/support/security/pki/OCSPUtility;", "trustAnchor", "", "Ljava/security/cert/TrustAnchor;", "(Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;Lorg/informatika/sirekap/support/security/pki/CRLUtility;Lorg/informatika/sirekap/support/security/pki/OCSPUtility;Ljava/util/List;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lorg/informatika/sirekap/support/security/pdf/PdfLtv;", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Factory {
        private final CRLUtility crlUtility;
        private final OCSPUtility ocspUtility;
        private final BasicTimestampAuthority.Factory timestampFactory;
        private final List<TrustAnchor> trustAnchor;

        /* JADX WARN: Multi-variable type inference failed */
        public Factory(BasicTimestampAuthority.Factory timestampFactory, CRLUtility crlUtility, OCSPUtility ocspUtility, List<? extends TrustAnchor> list) {
            Intrinsics.checkNotNullParameter(timestampFactory, "timestampFactory");
            Intrinsics.checkNotNullParameter(crlUtility, "crlUtility");
            Intrinsics.checkNotNullParameter(ocspUtility, "ocspUtility");
            this.timestampFactory = timestampFactory;
            this.crlUtility = crlUtility;
            this.ocspUtility = ocspUtility;
            this.trustAnchor = list;
        }

        public /* synthetic */ Factory(BasicTimestampAuthority.Factory factory, CRLUtility cRLUtility, OCSPUtility oCSPUtility, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(factory, cRLUtility, oCSPUtility, (i & 8) != 0 ? null : list);
        }

        public final PdfLtv build(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PdfTimestampSigner pdfTimestampSigner = new PdfTimestampSigner(new TimestampSigner(this.timestampFactory.build(context)));
            List<TrustAnchor> list = this.trustAnchor;
            if (list == null) {
                list = PKIFacade.INSTANCE.getTrustAnchor(context);
            }
            return new PdfLtv(new PdfValidationInformation(this.crlUtility, this.ocspUtility, list), pdfTimestampSigner);
        }
    }
}
