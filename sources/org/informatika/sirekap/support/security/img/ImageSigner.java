package org.informatika.sirekap.support.security.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.informatika.sirekap.support.security.signature.Signer;

/* compiled from: ImageSigner.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/img/ImageSigner;", "", "signer", "Lorg/informatika/sirekap/support/security/signature/Signer;", "(Lorg/informatika/sirekap/support/security/signature/Signer;)V", "sign", "", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageSigner {
    private final Signer signer;

    public ImageSigner(Signer signer) {
        Intrinsics.checkNotNullParameter(signer, "signer");
        this.signer = signer;
    }

    public final void sign(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        byte[] signature = this.signer.sign(new FileInputStream(file));
        File tmpFile = File.createTempFile("signing", ".jpg");
        Intrinsics.checkNotNullExpressionValue(tmpFile, "tmpFile");
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        fileOutputStream.write(FilesKt.readBytes(file));
        Intrinsics.checkNotNullExpressionValue(signature, "signature");
        ImageSignatureUtil.INSTANCE.writeSignature(fileOutputStream, signature);
        new FileOutputStream(file).write(FilesKt.readBytes(tmpFile));
    }
}
