package org.informatika.sirekap.api;

import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiResponse.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\u0010\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ2\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/api/SecurityApiResponse;", "T", "", "message", "", NotificationCompat.CATEGORY_STATUS, Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMessage", "()Ljava/lang/String;", "getStatus", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/informatika/sirekap/api/SecurityApiResponse;", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SecurityApiResponse<T> {
    private final T data;
    private final String message;
    private final String status;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SecurityApiResponse copy$default(SecurityApiResponse securityApiResponse, String str, String str2, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = securityApiResponse.message;
        }
        if ((i & 2) != 0) {
            str2 = securityApiResponse.status;
        }
        if ((i & 4) != 0) {
            obj = securityApiResponse.data;
        }
        return securityApiResponse.copy(str, str2, obj);
    }

    public final String component1() {
        return this.message;
    }

    public final String component2() {
        return this.status;
    }

    public final T component3() {
        return this.data;
    }

    public final SecurityApiResponse<T> copy(String message, String status, T t) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(status, "status");
        return new SecurityApiResponse<>(message, status, t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SecurityApiResponse) {
            SecurityApiResponse securityApiResponse = (SecurityApiResponse) obj;
            return Intrinsics.areEqual(this.message, securityApiResponse.message) && Intrinsics.areEqual(this.status, securityApiResponse.status) && Intrinsics.areEqual(this.data, securityApiResponse.data);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.message.hashCode() * 31) + this.status.hashCode()) * 31;
        T t = this.data;
        return hashCode + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        String str = this.message;
        String str2 = this.status;
        return "SecurityApiResponse(message=" + str + ", status=" + str2 + ", data=" + this.data + ")";
    }

    public SecurityApiResponse(String message, String status, T t) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(status, "status");
        this.message = message;
        this.status = status;
        this.data = t;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getStatus() {
        return this.status;
    }

    public final T getData() {
        return this.data;
    }
}
