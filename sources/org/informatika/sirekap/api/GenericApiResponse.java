package org.informatika.sirekap.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GenericApiResponse.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/api/GenericApiResponse;", "", "isSuccess", "", "message", "", "trace", "(ZLjava/lang/String;Ljava/lang/String;)V", "()Z", "getMessage", "()Ljava/lang/String;", "getTrace", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class GenericApiResponse {
    private final boolean isSuccess;
    private final String message;
    private final String trace;

    public GenericApiResponse(boolean z, String message, String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.isSuccess = z;
        this.message = message;
        this.trace = str;
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getTrace() {
        return this.trace;
    }
}
