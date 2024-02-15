package org.informatika.sirekap.api.request;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* compiled from: UserDashboardStatusRequestPayload.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/api/request/UserDashboardStatusRequestPayload;", "", "userStatus", "", "(I)V", "getUserStatus", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UserDashboardStatusRequestPayload {
    @SerializedName("user_status")
    private final int userStatus;

    public static /* synthetic */ UserDashboardStatusRequestPayload copy$default(UserDashboardStatusRequestPayload userDashboardStatusRequestPayload, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = userDashboardStatusRequestPayload.userStatus;
        }
        return userDashboardStatusRequestPayload.copy(i);
    }

    public final int component1() {
        return this.userStatus;
    }

    public final UserDashboardStatusRequestPayload copy(int i) {
        return new UserDashboardStatusRequestPayload(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UserDashboardStatusRequestPayload) && this.userStatus == ((UserDashboardStatusRequestPayload) obj).userStatus;
    }

    public int hashCode() {
        return Integer.hashCode(this.userStatus);
    }

    public String toString() {
        return "UserDashboardStatusRequestPayload(userStatus=" + this.userStatus + ")";
    }

    public UserDashboardStatusRequestPayload(int i) {
        this.userStatus = i;
    }

    public final int getUserStatus() {
        return this.userStatus;
    }
}
