package org.informatika.sirekap.support;

import java.io.File;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SecurityUtil.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/support/SecurityUtil;", "", "()V", "hashDocument", "", "filepath", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SecurityUtil {
    public final String hashDocument(String filepath) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(FilesKt.readBytes(new File(filepath)));
        Intrinsics.checkNotNullExpressionValue(digest, "digest");
        int length = digest.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(digest[i])}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
            str = str + format;
        }
        return str;
    }
}
