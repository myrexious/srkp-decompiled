package org.informatika.sirekap.support.security.img;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.informatika.sirekap.support.security.signature.Verifier;

/* compiled from: ImageValidator.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/support/security/img/ImageValidator;", "", "verifier", "Lorg/informatika/sirekap/support/security/signature/Verifier;", "(Lorg/informatika/sirekap/support/security/signature/Verifier;)V", "validate", "", "context", "Landroid/content/Context;", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageValidator {
    private final Verifier verifier;

    public ImageValidator(Verifier verifier) {
        Intrinsics.checkNotNullParameter(verifier, "verifier");
        this.verifier = verifier;
    }

    public final void validate(Context context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        File payload = File.createTempFile("verify", ".jpg");
        Intrinsics.checkNotNullExpressionValue(payload, "payload");
        FilesKt.writeBytes(payload, FilesKt.readBytes(file));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ImageSignatureUtil.INSTANCE.splitSignatureAndData(new FileInputStream(payload), byteArrayOutputStream, byteArrayOutputStream2);
        Verifier verifier = this.verifier;
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "signatureData.toByteArray()");
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray2, "imageData.toByteArray()");
        verifier.verify(context, byteArray, byteArray2);
        byteArrayOutputStream.close();
        byteArrayOutputStream2.close();
    }
}
