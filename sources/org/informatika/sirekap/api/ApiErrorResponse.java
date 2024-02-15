package org.informatika.sirekap.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiResponse.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/api/ApiErrorResponse;", "T", "Lorg/informatika/sirekap/api/ApiResponse;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApiErrorResponse<T> extends ApiResponse<T> {
    private final String error;

    public static /* synthetic */ ApiErrorResponse copy$default(ApiErrorResponse apiErrorResponse, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = apiErrorResponse.error;
        }
        return apiErrorResponse.copy(str);
    }

    public final String component1() {
        return this.error;
    }

    public final ApiErrorResponse<T> copy(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new ApiErrorResponse<>(error);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApiErrorResponse) && Intrinsics.areEqual(this.error, ((ApiErrorResponse) obj).error);
    }

    public int hashCode() {
        return this.error.hashCode();
    }

    public String toString() {
        return "ApiErrorResponse(error=" + this.error + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApiErrorResponse(String error) {
        super(null);
        Intrinsics.checkNotNullParameter(error, "error");
        this.error = error;
    }

    public final String getError() {
        return this.error;
    }
}
