package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDMetadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.apache.xmpbox.XMPMetadata;

/* compiled from: PdfMetadata.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfMetadata;", "", "()V", "addStringMetadata", "", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "metadata", "", "Ljava/io/File;", "docStream", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "getStringMetadata", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfMetadata {
    public final void addStringMetadata(PDDocument document, String metadata) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        document.getDocumentCatalog();
        XMPMetadata.createXMPMetadata().createAndAddDublinCoreSchema().setDescription(metadata);
        PDMetadata pDMetadata = new PDMetadata(document);
        byte[] bytes = metadata.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        pDMetadata.importXMPMetadata(bytes);
        document.getDocumentCatalog().setMetadata(pDMetadata);
    }

    public final void addStringMetadata(InputStream docStream, OutputStream output, String metadata) {
        Intrinsics.checkNotNullParameter(docStream, "docStream");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        PDDocument document = PDDocument.load(docStream);
        Intrinsics.checkNotNullExpressionValue(document, "document");
        addStringMetadata(document, metadata);
        document.save(output);
    }

    public final void addStringMetadata(File document, String metadata) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        File tmp = File.createTempFile("tmp", ".pdf");
        Intrinsics.checkNotNullExpressionValue(tmp, "tmp");
        addStringMetadata(new FileInputStream(document), new FileOutputStream(tmp), metadata);
        new FileOutputStream(document).write(FilesKt.readBytes(tmp));
    }

    public final String getStringMetadata(PDDocument document) {
        Intrinsics.checkNotNullParameter(document, "document");
        InputStream xmpMetadata = document.getDocumentCatalog().getMetadata().exportXMPMetadata();
        Intrinsics.checkNotNullExpressionValue(xmpMetadata, "xmpMetadata");
        return new String(ByteStreamsKt.readBytes(xmpMetadata), Charsets.UTF_8);
    }

    public final String getStringMetadata(InputStream docStream) {
        Intrinsics.checkNotNullParameter(docStream, "docStream");
        PDDocument document = PDDocument.load(docStream);
        Intrinsics.checkNotNullExpressionValue(document, "document");
        return getStringMetadata(document);
    }
}
