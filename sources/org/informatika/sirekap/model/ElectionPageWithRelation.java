package org.informatika.sirekap.model;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.R;

/* compiled from: ElectionPage.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001d\u001a\u0004\u0018\u00010\u0006J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006J\b\u0010\u001f\u001a\u0004\u0018\u00010\u0006J\t\u0010 \u001a\u00020!HÖ\u0001J\u0006\u0010\"\u001a\u00020\u0015J\t\u0010#\u001a\u00020\u0018HÖ\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006$"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "states", "", "Lorg/informatika/sirekap/model/ElectionPageStage;", "election", "Lorg/informatika/sirekap/model/Election;", "(Lorg/informatika/sirekap/model/ElectionPage;Ljava/util/List;Lorg/informatika/sirekap/model/Election;)V", "getElection", "()Lorg/informatika/sirekap/model/Election;", "getElectionPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "getStates", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "getImageDescription", "", "context", "Landroid/content/Context;", "getImageDescriptionShort", "getPageNumber", "getPhotoState", "getSendState", "getVerifyState", "hashCode", "", "isDone", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionPageWithRelation {
    private final Election election;
    private final ElectionPage electionPage;
    private final List<ElectionPageStage> states;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ElectionPageWithRelation copy$default(ElectionPageWithRelation electionPageWithRelation, ElectionPage electionPage, List list, Election election, int i, Object obj) {
        if ((i & 1) != 0) {
            electionPage = electionPageWithRelation.electionPage;
        }
        if ((i & 2) != 0) {
            list = electionPageWithRelation.states;
        }
        if ((i & 4) != 0) {
            election = electionPageWithRelation.election;
        }
        return electionPageWithRelation.copy(electionPage, list, election);
    }

    public final ElectionPage component1() {
        return this.electionPage;
    }

    public final List<ElectionPageStage> component2() {
        return this.states;
    }

    public final Election component3() {
        return this.election;
    }

    public final ElectionPageWithRelation copy(ElectionPage electionPage, List<ElectionPageStage> states, Election election) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(states, "states");
        Intrinsics.checkNotNullParameter(election, "election");
        return new ElectionPageWithRelation(electionPage, states, election);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionPageWithRelation) {
            ElectionPageWithRelation electionPageWithRelation = (ElectionPageWithRelation) obj;
            return Intrinsics.areEqual(this.electionPage, electionPageWithRelation.electionPage) && Intrinsics.areEqual(this.states, electionPageWithRelation.states) && Intrinsics.areEqual(this.election, electionPageWithRelation.election);
        }
        return false;
    }

    public int hashCode() {
        return (((this.electionPage.hashCode() * 31) + this.states.hashCode()) * 31) + this.election.hashCode();
    }

    public String toString() {
        ElectionPage electionPage = this.electionPage;
        List<ElectionPageStage> list = this.states;
        return "ElectionPageWithRelation(electionPage=" + electionPage + ", states=" + list + ", election=" + this.election + ")";
    }

    public ElectionPageWithRelation(ElectionPage electionPage, List<ElectionPageStage> states, Election election) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(states, "states");
        Intrinsics.checkNotNullParameter(election, "election");
        this.electionPage = electionPage;
        this.states = states;
        this.election = election;
    }

    public final ElectionPage getElectionPage() {
        return this.electionPage;
    }

    public final List<ElectionPageStage> getStates() {
        return this.states;
    }

    public final Election getElection() {
        return this.election;
    }

    public final boolean isDone() {
        return this.electionPage.getCurrentStageIndex() >= this.states.size();
    }

    public final ElectionPageStage getPhotoState() {
        Object obj;
        Iterator<T> it = this.states.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((ElectionPageStage) obj).getType(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO)) {
                break;
            }
        }
        return (ElectionPageStage) obj;
    }

    public final ElectionPageStage getSendState() {
        Object obj;
        Iterator<T> it = this.states.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((ElectionPageStage) obj).getType(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND)) {
                break;
            }
        }
        return (ElectionPageStage) obj;
    }

    public final ElectionPageStage getVerifyState() {
        Object obj;
        Iterator<T> it = this.states.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((ElectionPageStage) obj).getType(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY)) {
                break;
            }
        }
        return (ElectionPageStage) obj;
    }

    public final String getPageNumber(Context context) {
        int number;
        Intrinsics.checkNotNullParameter(context, "context");
        int kind = this.electionPage.getKind();
        if (kind == 10) {
            number = this.electionPage.getNumber();
        } else if (kind == 20) {
            number = this.electionPage.getNumber() + 1;
        } else {
            number = kind != 30 ? 0 : this.election.getJmlLembar();
        }
        if (number != 0) {
            String string = context.getString(R.string.election_page_number, Integer.valueOf(number));
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ion_page_number, pageNum)");
            return string;
        }
        return "";
    }

    public final String getImageDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String description = this.election.getDescription(context);
        String kindDescription = this.electionPage.getKindDescription(context);
        return description + StringUtils.SPACE + kindDescription + StringUtils.SPACE + getPageNumber(context);
    }

    public final String getImageDescriptionShort(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String description = this.election.getDescription(context);
        return description + "  " + getPageNumber(context);
    }
}
