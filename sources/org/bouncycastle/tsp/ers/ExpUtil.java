package org.bouncycastle.tsp.ers;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ExpUtil {
    ExpUtil() {
    }

    public static IllegalStateException createIllegalState(String str, Throwable th) {
        return new IllegalStateException(str, th);
    }
}
