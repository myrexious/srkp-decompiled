package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: BackgroundProcess.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JF\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\u0006\u0010 \u001a\u00020\bJ\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0007\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lorg/informatika/sirekap/model/BackgroundProcess;", "", JobType.ID, "", "startedAt", "", "endedAt", "isSuccess", "", "errorMessage", "(Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;)V", "getEndedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getErrorMessage", "()Ljava/lang/String;", "getId", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getStartedAt", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;)Lorg/informatika/sirekap/model/BackgroundProcess;", "equals", "other", "hashCode", "", "isLoading", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BackgroundProcess {
    private final Long endedAt;
    private final String errorMessage;

    /* renamed from: id */
    private final String f61id;
    private final Boolean isSuccess;
    private final long startedAt;

    public static /* synthetic */ BackgroundProcess copy$default(BackgroundProcess backgroundProcess, String str, long j, Long l, Boolean bool, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = backgroundProcess.f61id;
        }
        if ((i & 2) != 0) {
            j = backgroundProcess.startedAt;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            l = backgroundProcess.endedAt;
        }
        Long l2 = l;
        if ((i & 8) != 0) {
            bool = backgroundProcess.isSuccess;
        }
        Boolean bool2 = bool;
        if ((i & 16) != 0) {
            str2 = backgroundProcess.errorMessage;
        }
        return backgroundProcess.copy(str, j2, l2, bool2, str2);
    }

    public final String component1() {
        return this.f61id;
    }

    public final long component2() {
        return this.startedAt;
    }

    public final Long component3() {
        return this.endedAt;
    }

    public final Boolean component4() {
        return this.isSuccess;
    }

    public final String component5() {
        return this.errorMessage;
    }

    public final BackgroundProcess copy(String id2, long j, Long l, Boolean bool, String str) {
        Intrinsics.checkNotNullParameter(id2, "id");
        return new BackgroundProcess(id2, j, l, bool, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BackgroundProcess) {
            BackgroundProcess backgroundProcess = (BackgroundProcess) obj;
            return Intrinsics.areEqual(this.f61id, backgroundProcess.f61id) && this.startedAt == backgroundProcess.startedAt && Intrinsics.areEqual(this.endedAt, backgroundProcess.endedAt) && Intrinsics.areEqual(this.isSuccess, backgroundProcess.isSuccess) && Intrinsics.areEqual(this.errorMessage, backgroundProcess.errorMessage);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.f61id.hashCode() * 31) + Long.hashCode(this.startedAt)) * 31;
        Long l = this.endedAt;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        Boolean bool = this.isSuccess;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.errorMessage;
        return hashCode3 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        String str = this.f61id;
        long j = this.startedAt;
        Long l = this.endedAt;
        Boolean bool = this.isSuccess;
        return "BackgroundProcess(id=" + str + ", startedAt=" + j + ", endedAt=" + l + ", isSuccess=" + bool + ", errorMessage=" + this.errorMessage + ")";
    }

    public BackgroundProcess(String id2, long j, Long l, Boolean bool, String str) {
        Intrinsics.checkNotNullParameter(id2, "id");
        this.f61id = id2;
        this.startedAt = j;
        this.endedAt = l;
        this.isSuccess = bool;
        this.errorMessage = str;
    }

    public /* synthetic */ BackgroundProcess(String str, long j, Long l, Boolean bool, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : str2);
    }

    public final String getId() {
        return this.f61id;
    }

    public final long getStartedAt() {
        return this.startedAt;
    }

    public final Long getEndedAt() {
        return this.endedAt;
    }

    public final Boolean isSuccess() {
        return this.isSuccess;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final boolean isLoading() {
        return this.endedAt == null;
    }
}
