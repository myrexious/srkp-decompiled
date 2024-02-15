package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ElectionCard.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/model/ElectionCard;", "", "title", "", "capturedCount", "", "verifiedCount", "notRecognizedCount", "(Ljava/lang/String;III)V", "getCapturedCount", "()I", "getNotRecognizedCount", "getTitle", "()Ljava/lang/String;", "getVerifiedCount", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionCard {
    private final int capturedCount;
    private final int notRecognizedCount;
    private final String title;
    private final int verifiedCount;

    public static /* synthetic */ ElectionCard copy$default(ElectionCard electionCard, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = electionCard.title;
        }
        if ((i4 & 2) != 0) {
            i = electionCard.capturedCount;
        }
        if ((i4 & 4) != 0) {
            i2 = electionCard.verifiedCount;
        }
        if ((i4 & 8) != 0) {
            i3 = electionCard.notRecognizedCount;
        }
        return electionCard.copy(str, i, i2, i3);
    }

    public final String component1() {
        return this.title;
    }

    public final int component2() {
        return this.capturedCount;
    }

    public final int component3() {
        return this.verifiedCount;
    }

    public final int component4() {
        return this.notRecognizedCount;
    }

    public final ElectionCard copy(String title, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new ElectionCard(title, i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionCard) {
            ElectionCard electionCard = (ElectionCard) obj;
            return Intrinsics.areEqual(this.title, electionCard.title) && this.capturedCount == electionCard.capturedCount && this.verifiedCount == electionCard.verifiedCount && this.notRecognizedCount == electionCard.notRecognizedCount;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + Integer.hashCode(this.capturedCount)) * 31) + Integer.hashCode(this.verifiedCount)) * 31) + Integer.hashCode(this.notRecognizedCount);
    }

    public String toString() {
        String str = this.title;
        int i = this.capturedCount;
        int i2 = this.verifiedCount;
        return "ElectionCard(title=" + str + ", capturedCount=" + i + ", verifiedCount=" + i2 + ", notRecognizedCount=" + this.notRecognizedCount + ")";
    }

    public ElectionCard(String title, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.capturedCount = i;
        this.verifiedCount = i2;
        this.notRecognizedCount = i3;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getCapturedCount() {
        return this.capturedCount;
    }

    public final int getVerifiedCount() {
        return this.verifiedCount;
    }

    public final int getNotRecognizedCount() {
        return this.notRecognizedCount;
    }
}
