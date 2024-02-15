package org.informatika.sirekap.support.security.img;

import com.google.firebase.messaging.Constants;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.tensorflow.lite.schema.BuiltinOptions;

/* compiled from: ImageSignatureUtil.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/security/img/ImageSignatureUtil;", "", "()V", "SIGNATURE_PREFIX", "", "splitSignatureAndData", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Ljava/io/InputStream;", "payload", "Ljava/io/OutputStream;", "signature", "writeSignature", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageSignatureUtil {
    public static final ImageSignatureUtil INSTANCE = new ImageSignatureUtil();
    private static final byte[] SIGNATURE_PREFIX = {0, 0, BuiltinOptions.EmbeddingLookupSparseOptions, 2, BuiltinOptions.LogSoftmaxOptions, 0, 0};

    private ImageSignatureUtil() {
    }

    public final void writeSignature(OutputStream data, byte[] signature) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(signature, "signature");
        data.write(SIGNATURE_PREFIX);
        data.write(signature);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x003e, code lost:
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x003f, code lost:
        if (r2 >= r1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0041, code lost:
        r7.write(org.informatika.sirekap.support.security.img.ImageSignatureUtil.SIGNATURE_PREFIX[r2]);
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void splitSignatureAndData(java.io.InputStream r6, java.io.OutputStream r7, java.io.OutputStream r8) {
        /*
            r5 = this;
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "payload"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "signature"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 0
        L10:
            r1 = r0
        L11:
            byte[] r2 = org.informatika.sirekap.support.security.img.ImageSignatureUtil.SIGNATURE_PREFIX
            int r3 = r2.length
            r4 = -1
            if (r1 != r3) goto L22
        L17:
            int r7 = r6.read()
            if (r7 != r4) goto L1e
            return
        L1e:
            r8.write(r7)
            goto L17
        L22:
            int r3 = r6.read()
            if (r3 != r4) goto L35
        L28:
            if (r0 >= r1) goto L34
            byte[] r6 = org.informatika.sirekap.support.security.img.ImageSignatureUtil.SIGNATURE_PREFIX
            r6 = r6[r0]
            r7.write(r6)
            int r0 = r0 + 1
            goto L28
        L34:
            return
        L35:
            r2 = r2[r1]
            if (r2 != r3) goto L3c
            int r1 = r1 + 1
            goto L11
        L3c:
            if (r1 <= 0) goto L4f
            r2 = r0
        L3f:
            if (r2 >= r1) goto L4b
            byte[] r4 = org.informatika.sirekap.support.security.img.ImageSignatureUtil.SIGNATURE_PREFIX
            r4 = r4[r2]
            r7.write(r4)
            int r2 = r2 + 1
            goto L3f
        L4b:
            r7.write(r3)
            goto L10
        L4f:
            r7.write(r3)
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.img.ImageSignatureUtil.splitSignatureAndData(java.io.InputStream, java.io.OutputStream, java.io.OutputStream):void");
    }
}
