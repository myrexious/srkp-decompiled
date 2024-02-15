package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserInformation.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/model/UserInformation;", "", "userId", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getUserId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UserInformation {
    private final String name;
    private final String userId;

    public static /* synthetic */ UserInformation copy$default(UserInformation userInformation, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userInformation.userId;
        }
        if ((i & 2) != 0) {
            str2 = userInformation.name;
        }
        return userInformation.copy(str, str2);
    }

    public final String component1() {
        return this.userId;
    }

    public final String component2() {
        return this.name;
    }

    public final UserInformation copy(String userId, String name) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(name, "name");
        return new UserInformation(userId, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserInformation) {
            UserInformation userInformation = (UserInformation) obj;
            return Intrinsics.areEqual(this.userId, userInformation.userId) && Intrinsics.areEqual(this.name, userInformation.name);
        }
        return false;
    }

    public int hashCode() {
        return (this.userId.hashCode() * 31) + this.name.hashCode();
    }

    public String toString() {
        String str = this.userId;
        return "UserInformation(userId=" + str + ", name=" + this.name + ")";
    }

    public UserInformation(String userId, String name) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(name, "name");
        this.userId = userId;
        this.name = name;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getName() {
        return this.name;
    }
}
