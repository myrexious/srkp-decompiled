package org.informatika.sirekap.model;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ElectionPageStage.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\u0006\u0010\u001b\u001a\u00020\tJ\u0006\u0010\u001c\u001a\u00020\tJ\u0006\u0010\u001d\u001a\u00020\tJ\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/model/ElectionPageStage;", "", "electionId", "", "electionPageId", "type", NotificationCompat.CATEGORY_STATUS, "", "isOffline", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZ)V", "getElectionId", "()Ljava/lang/String;", "getElectionPageId", "()Z", "getStatus", "()I", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "isDone", "isFailed", "isInProgress", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionPageStage {
    public static final Companion Companion = new Companion(null);
    public static final String ELECTION_PAGE_STAGE_TYPE_PHOTO = "Foto";
    public static final String ELECTION_PAGE_STAGE_TYPE_SEND = "Kirim";
    public static final String ELECTION_PAGE_STAGE_TYPE_VERIFY = "Periksa";
    public static final int PAGE_STATE_STATUS_FAILED = 0;
    public static final int PAGE_STATE_STATUS_IN_PROGRESS = 2;
    public static final int PAGE_STATE_STATUS_NOT_STARTED = -1;
    public static final int PAGE_STATE_STATUS_SUCCESS = 1;
    private final String electionId;
    private final String electionPageId;
    private final boolean isOffline;
    private final int status;
    private final String type;

    public static /* synthetic */ ElectionPageStage copy$default(ElectionPageStage electionPageStage, String str, String str2, String str3, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = electionPageStage.electionId;
        }
        if ((i2 & 2) != 0) {
            str2 = electionPageStage.electionPageId;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            str3 = electionPageStage.type;
        }
        String str5 = str3;
        if ((i2 & 8) != 0) {
            i = electionPageStage.status;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            z = electionPageStage.isOffline;
        }
        return electionPageStage.copy(str, str4, str5, i3, z);
    }

    public final String component1() {
        return this.electionId;
    }

    public final String component2() {
        return this.electionPageId;
    }

    public final String component3() {
        return this.type;
    }

    public final int component4() {
        return this.status;
    }

    public final boolean component5() {
        return this.isOffline;
    }

    public final ElectionPageStage copy(String electionId, String electionPageId, String type, int i, boolean z) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(type, "type");
        return new ElectionPageStage(electionId, electionPageId, type, i, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionPageStage) {
            ElectionPageStage electionPageStage = (ElectionPageStage) obj;
            return Intrinsics.areEqual(this.electionId, electionPageStage.electionId) && Intrinsics.areEqual(this.electionPageId, electionPageStage.electionPageId) && Intrinsics.areEqual(this.type, electionPageStage.type) && this.status == electionPageStage.status && this.isOffline == electionPageStage.isOffline;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((this.electionId.hashCode() * 31) + this.electionPageId.hashCode()) * 31) + this.type.hashCode()) * 31) + Integer.hashCode(this.status)) * 31;
        boolean z = this.isOffline;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.electionId;
        String str2 = this.electionPageId;
        String str3 = this.type;
        int i = this.status;
        return "ElectionPageStage(electionId=" + str + ", electionPageId=" + str2 + ", type=" + str3 + ", status=" + i + ", isOffline=" + this.isOffline + ")";
    }

    public ElectionPageStage(String electionId, String electionPageId, String type, int i, boolean z) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(type, "type");
        this.electionId = electionId;
        this.electionPageId = electionPageId;
        this.type = type;
        this.status = i;
        this.isOffline = z;
    }

    public /* synthetic */ ElectionPageStage(String str, String str2, String str3, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, (i2 & 16) != 0 ? false : z);
    }

    public final String getElectionId() {
        return this.electionId;
    }

    public final String getElectionPageId() {
        return this.electionPageId;
    }

    public final String getType() {
        return this.type;
    }

    public final int getStatus() {
        return this.status;
    }

    public final boolean isOffline() {
        return this.isOffline;
    }

    public final boolean isInProgress() {
        return this.status == 2;
    }

    public final boolean isFailed() {
        return this.status == 0;
    }

    public final boolean isDone() {
        return this.status == 1;
    }

    /* compiled from: ElectionPageStage.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPageStage$Companion;", "", "()V", "ELECTION_PAGE_STAGE_TYPE_PHOTO", "", "ELECTION_PAGE_STAGE_TYPE_SEND", "ELECTION_PAGE_STAGE_TYPE_VERIFY", "PAGE_STATE_STATUS_FAILED", "", "PAGE_STATE_STATUS_IN_PROGRESS", "PAGE_STATE_STATUS_NOT_STARTED", "PAGE_STATE_STATUS_SUCCESS", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
