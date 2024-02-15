package org.informatika.sirekap.model;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.FormC1AdministrationComplete;

/* compiled from: FormC1Tabulation.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0005HÆ\u0003JM\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u001c\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0005J\t\u0010&\u001a\u00020'HÖ\u0001J\u0006\u0010(\u001a\u00020\u001eJ\u0006\u0010)\u001a\u00020\u001eJ\t\u0010*\u001a\u00020+HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "votes", "", "Lorg/informatika/sirekap/model/FormC1TabulationCandidateVote;", "error", "Lorg/informatika/sirekap/model/FormC1Error;", "kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "kesesuaianVotes", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "(Lorg/informatika/sirekap/model/ElectionPage;Ljava/util/List;Lorg/informatika/sirekap/model/FormC1Error;Lorg/informatika/sirekap/model/FormC1Kesesuaian;Ljava/util/List;)V", "getElectionPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "getError", "()Lorg/informatika/sirekap/model/FormC1Error;", "getKesesuaian", "()Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getKesesuaianVotes", "()Ljava/util/List;", "getVotes", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "getSalinanData", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "context", "Landroid/content/Context;", "candidates", "Lorg/informatika/sirekap/model/Candidate;", "hashCode", "", "isCheckedAll", "isVerified", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationComplete {
    private final ElectionPage electionPage;
    private final FormC1Error error;
    private final FormC1Kesesuaian kesesuaian;
    private final List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes;
    private final List<FormC1TabulationCandidateVote> votes;

    public static /* synthetic */ FormC1TabulationComplete copy$default(FormC1TabulationComplete formC1TabulationComplete, ElectionPage electionPage, List list, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            electionPage = formC1TabulationComplete.electionPage;
        }
        List<FormC1TabulationCandidateVote> list3 = list;
        if ((i & 2) != 0) {
            list3 = formC1TabulationComplete.votes;
        }
        List list4 = list3;
        if ((i & 4) != 0) {
            formC1Error = formC1TabulationComplete.error;
        }
        FormC1Error formC1Error2 = formC1Error;
        if ((i & 8) != 0) {
            formC1Kesesuaian = formC1TabulationComplete.kesesuaian;
        }
        FormC1Kesesuaian formC1Kesesuaian2 = formC1Kesesuaian;
        List<FormC1KesesuaianTabulationCandidateVote> list5 = list2;
        if ((i & 16) != 0) {
            list5 = formC1TabulationComplete.kesesuaianVotes;
        }
        return formC1TabulationComplete.copy(electionPage, list4, formC1Error2, formC1Kesesuaian2, list5);
    }

    public final ElectionPage component1() {
        return this.electionPage;
    }

    public final List<FormC1TabulationCandidateVote> component2() {
        return this.votes;
    }

    public final FormC1Error component3() {
        return this.error;
    }

    public final FormC1Kesesuaian component4() {
        return this.kesesuaian;
    }

    public final List<FormC1KesesuaianTabulationCandidateVote> component5() {
        return this.kesesuaianVotes;
    }

    public final FormC1TabulationComplete copy(ElectionPage electionPage, List<FormC1TabulationCandidateVote> votes, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, List<FormC1KesesuaianTabulationCandidateVote> list) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(votes, "votes");
        return new FormC1TabulationComplete(electionPage, votes, formC1Error, formC1Kesesuaian, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationComplete) {
            FormC1TabulationComplete formC1TabulationComplete = (FormC1TabulationComplete) obj;
            return Intrinsics.areEqual(this.electionPage, formC1TabulationComplete.electionPage) && Intrinsics.areEqual(this.votes, formC1TabulationComplete.votes) && Intrinsics.areEqual(this.error, formC1TabulationComplete.error) && Intrinsics.areEqual(this.kesesuaian, formC1TabulationComplete.kesesuaian) && Intrinsics.areEqual(this.kesesuaianVotes, formC1TabulationComplete.kesesuaianVotes);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.electionPage.hashCode() * 31) + this.votes.hashCode()) * 31;
        FormC1Error formC1Error = this.error;
        int hashCode2 = (hashCode + (formC1Error == null ? 0 : formC1Error.hashCode())) * 31;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        int hashCode3 = (hashCode2 + (formC1Kesesuaian == null ? 0 : formC1Kesesuaian.hashCode())) * 31;
        List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
        return hashCode3 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        ElectionPage electionPage = this.electionPage;
        List<FormC1TabulationCandidateVote> list = this.votes;
        FormC1Error formC1Error = this.error;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        return "FormC1TabulationComplete(electionPage=" + electionPage + ", votes=" + list + ", error=" + formC1Error + ", kesesuaian=" + formC1Kesesuaian + ", kesesuaianVotes=" + this.kesesuaianVotes + ")";
    }

    public FormC1TabulationComplete(ElectionPage electionPage, List<FormC1TabulationCandidateVote> votes, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, List<FormC1KesesuaianTabulationCandidateVote> list) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(votes, "votes");
        this.electionPage = electionPage;
        this.votes = votes;
        this.error = formC1Error;
        this.kesesuaian = formC1Kesesuaian;
        this.kesesuaianVotes = list;
    }

    public final ElectionPage getElectionPage() {
        return this.electionPage;
    }

    public final List<FormC1TabulationCandidateVote> getVotes() {
        return this.votes;
    }

    public final FormC1Error getError() {
        return this.error;
    }

    public final FormC1Kesesuaian getKesesuaian() {
        return this.kesesuaian;
    }

    public final List<FormC1KesesuaianTabulationCandidateVote> getKesesuaianVotes() {
        return this.kesesuaianVotes;
    }

    public final boolean isCheckedAll() {
        boolean z;
        List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
        if (list != null) {
            List<FormC1KesesuaianTabulationCandidateVote> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                for (FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote : list2) {
                    if (!formC1KesesuaianTabulationCandidateVote.isChecked()) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            return z;
        }
        return false;
    }

    public final boolean isVerified() {
        return this.kesesuaian != null;
    }

    public final FormC1AdministrationComplete.SectionDataPdf getSalinanData(Context context, List<Candidate> candidates) {
        Boolean bool;
        Object obj;
        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote;
        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote2;
        Integer voteCorrected;
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        FormC1AdministrationComplete.SectionDataPdf sectionDataPdf = new FormC1AdministrationComplete.SectionDataPdf("IV.", "DATA RINCIAN PEROLEHAN SUARA", null, "Jumlah", null, null, 52, null);
        List<FormC1AdministrationComplete.RowDataPdf> rows = sectionDataPdf.getRows();
        List<FormC1TabulationCandidateVote> list = this.votes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj2 : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj2;
            Iterator<T> it = candidates.iterator();
            while (true) {
                bool = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Candidate) obj).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            Candidate candidate = (Candidate) obj;
            Candidate decrypted = candidate != null ? candidate.toDecrypted() : null;
            FormC1AdministrationComplete.RowDataPdfType rowDataPdfType = FormC1AdministrationComplete.RowDataPdfType.content;
            String num = decrypted != null ? Integer.valueOf(decrypted.getNoUrutPencalonan()).toString() : null;
            String str = (decrypted == null || (r10 = decrypted.getNama()) == null) ? "-" : "-";
            List<FormC1KesesuaianTabulationCandidateVote> list2 = this.kesesuaianVotes;
            Integer valueOf = Integer.valueOf((list2 == null || (formC1KesesuaianTabulationCandidateVote2 = list2.get(i)) == null || (voteCorrected = formC1KesesuaianTabulationCandidateVote2.getVoteCorrected()) == null) ? formC1TabulationCandidateVote.getVote() : voteCorrected.intValue());
            List<FormC1KesesuaianTabulationCandidateVote> list3 = this.kesesuaianVotes;
            if (list3 != null && (formC1KesesuaianTabulationCandidateVote = list3.get(i)) != null) {
                bool = formC1KesesuaianTabulationCandidateVote.getVote();
            }
            arrayList.add(new FormC1AdministrationComplete.RowDataPdf(rowDataPdfType, num, str, null, null, valueOf, bool));
            i = i2;
        }
        rows.addAll(arrayList);
        return sectionDataPdf;
    }
}
