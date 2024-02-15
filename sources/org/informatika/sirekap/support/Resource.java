package org.informatika.sirekap.support;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Resource.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001b*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0001\u001bB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/informatika/sirekap/support/Resource;", "T", "", FirebaseAnalytics.Param.SUCCESS, "Lorg/informatika/sirekap/support/ResourceStatus;", "payload", "error", "", "(Lorg/informatika/sirekap/support/ResourceStatus;Ljava/lang/Object;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getPayload", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSuccess", "()Lorg/informatika/sirekap/support/ResourceStatus;", "component1", "component2", "component3", "copy", "(Lorg/informatika/sirekap/support/ResourceStatus;Ljava/lang/Object;Ljava/lang/String;)Lorg/informatika/sirekap/support/Resource;", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Resource<T> {
    public static final Companion Companion = new Companion(null);
    private final String error;
    private final T payload;
    private final ResourceStatus success;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Resource copy$default(Resource resource, ResourceStatus resourceStatus, Object obj, String str, int i, Object obj2) {
        if ((i & 1) != 0) {
            resourceStatus = resource.success;
        }
        if ((i & 2) != 0) {
            obj = resource.payload;
        }
        if ((i & 4) != 0) {
            str = resource.error;
        }
        return resource.copy(resourceStatus, obj, str);
    }

    public final ResourceStatus component1() {
        return this.success;
    }

    public final T component2() {
        return this.payload;
    }

    public final String component3() {
        return this.error;
    }

    public final Resource<T> copy(ResourceStatus success, T t, String str) {
        Intrinsics.checkNotNullParameter(success, "success");
        return new Resource<>(success, t, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Resource) {
            Resource resource = (Resource) obj;
            return this.success == resource.success && Intrinsics.areEqual(this.payload, resource.payload) && Intrinsics.areEqual(this.error, resource.error);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.success.hashCode() * 31;
        T t = this.payload;
        int hashCode2 = (hashCode + (t == null ? 0 : t.hashCode())) * 31;
        String str = this.error;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        ResourceStatus resourceStatus = this.success;
        T t = this.payload;
        return "Resource(success=" + resourceStatus + ", payload=" + t + ", error=" + this.error + ")";
    }

    public Resource(ResourceStatus success, T t, String str) {
        Intrinsics.checkNotNullParameter(success, "success");
        this.success = success;
        this.payload = t;
        this.error = str;
    }

    public final ResourceStatus getSuccess() {
        return this.success;
    }

    public final T getPayload() {
        return this.payload;
    }

    public final String getError() {
        return this.error;
    }

    /* compiled from: Resource.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u0001H\u0005¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\b\u001a\u0004\u0018\u0001H\u0005¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\b\u001a\u0004\u0018\u0001H\u0005¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/Resource$Companion;", "", "()V", "error", "Lorg/informatika/sirekap/support/Resource;", "T", NotificationCompat.CATEGORY_MESSAGE, "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "(Ljava/lang/String;Ljava/lang/Object;)Lorg/informatika/sirekap/support/Resource;", "loading", "(Ljava/lang/Object;)Lorg/informatika/sirekap/support/Resource;", FirebaseAnalytics.Param.SUCCESS, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> Resource<T> success(T t) {
            return new Resource<>(ResourceStatus.SUCCESS, t, null);
        }

        public final <T> Resource<T> error(String msg, T t) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            return new Resource<>(ResourceStatus.ERROR, t, msg);
        }

        public final <T> Resource<T> loading(T t) {
            return new Resource<>(ResourceStatus.LOADING, t, null);
        }
    }
}
