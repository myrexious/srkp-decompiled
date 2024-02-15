package org.informatika.sirekap.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1AdministrationHal2Ppwp.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007¢\u0006\u0002\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007HÆ\u0003Je\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\u0006\u0010,\u001a\u00020(J\u0006\u0010-\u001a\u00020(J\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u0007J\u000e\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\u0007J\t\u00100\u001a\u000201HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001d¨\u00062"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "form", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "votes", "", "Lorg/informatika/sirekap/model/FormC1TabulationCandidateVote;", "error", "Lorg/informatika/sirekap/model/FormC1Error;", "kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "kesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "kesesuaianVotes", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "(Lorg/informatika/sirekap/model/ElectionPage;Lorg/informatika/sirekap/model/FormC1AdministrationHal2;Ljava/util/List;Lorg/informatika/sirekap/model/FormC1Error;Lorg/informatika/sirekap/model/FormC1Kesesuaian;Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;Ljava/util/List;)V", "getElectionPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "getError", "()Lorg/informatika/sirekap/model/FormC1Error;", "getForm", "()Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "getKesesuaian", "()Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getKesesuaianAdministrationHal2", "()Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "getKesesuaianVotes", "()Ljava/util/List;", "getVotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "isCheckedAll", "isVerified", "toIsSesuaiPerItem", "toKoreksiPerItem", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1AdministrationHal2PpwpComplete {
    private final ElectionPage electionPage;
    private final FormC1Error error;
    private final FormC1AdministrationHal2 form;
    private final FormC1Kesesuaian kesesuaian;
    private final FormC1KesesuaianAdministrationHal2 kesesuaianAdministrationHal2;
    private final List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes;
    private final List<FormC1TabulationCandidateVote> votes;

    public static /* synthetic */ FormC1AdministrationHal2PpwpComplete copy$default(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete, ElectionPage electionPage, FormC1AdministrationHal2 formC1AdministrationHal2, List list, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            electionPage = formC1AdministrationHal2PpwpComplete.electionPage;
        }
        if ((i & 2) != 0) {
            formC1AdministrationHal2 = formC1AdministrationHal2PpwpComplete.form;
        }
        FormC1AdministrationHal2 formC1AdministrationHal22 = formC1AdministrationHal2;
        List<FormC1TabulationCandidateVote> list3 = list;
        if ((i & 4) != 0) {
            list3 = formC1AdministrationHal2PpwpComplete.votes;
        }
        List list4 = list3;
        if ((i & 8) != 0) {
            formC1Error = formC1AdministrationHal2PpwpComplete.error;
        }
        FormC1Error formC1Error2 = formC1Error;
        if ((i & 16) != 0) {
            formC1Kesesuaian = formC1AdministrationHal2PpwpComplete.kesesuaian;
        }
        FormC1Kesesuaian formC1Kesesuaian2 = formC1Kesesuaian;
        if ((i & 32) != 0) {
            formC1KesesuaianAdministrationHal2 = formC1AdministrationHal2PpwpComplete.kesesuaianAdministrationHal2;
        }
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal22 = formC1KesesuaianAdministrationHal2;
        List<FormC1KesesuaianTabulationCandidateVote> list5 = list2;
        if ((i & 64) != 0) {
            list5 = formC1AdministrationHal2PpwpComplete.kesesuaianVotes;
        }
        return formC1AdministrationHal2PpwpComplete.copy(electionPage, formC1AdministrationHal22, list4, formC1Error2, formC1Kesesuaian2, formC1KesesuaianAdministrationHal22, list5);
    }

    public final ElectionPage component1() {
        return this.electionPage;
    }

    public final FormC1AdministrationHal2 component2() {
        return this.form;
    }

    public final List<FormC1TabulationCandidateVote> component3() {
        return this.votes;
    }

    public final FormC1Error component4() {
        return this.error;
    }

    public final FormC1Kesesuaian component5() {
        return this.kesesuaian;
    }

    public final FormC1KesesuaianAdministrationHal2 component6() {
        return this.kesesuaianAdministrationHal2;
    }

    public final List<FormC1KesesuaianTabulationCandidateVote> component7() {
        return this.kesesuaianVotes;
    }

    public final FormC1AdministrationHal2PpwpComplete copy(ElectionPage electionPage, FormC1AdministrationHal2 formC1AdministrationHal2, List<FormC1TabulationCandidateVote> votes, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2, List<FormC1KesesuaianTabulationCandidateVote> list) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(votes, "votes");
        return new FormC1AdministrationHal2PpwpComplete(electionPage, formC1AdministrationHal2, votes, formC1Error, formC1Kesesuaian, formC1KesesuaianAdministrationHal2, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1AdministrationHal2PpwpComplete) {
            FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete = (FormC1AdministrationHal2PpwpComplete) obj;
            return Intrinsics.areEqual(this.electionPage, formC1AdministrationHal2PpwpComplete.electionPage) && Intrinsics.areEqual(this.form, formC1AdministrationHal2PpwpComplete.form) && Intrinsics.areEqual(this.votes, formC1AdministrationHal2PpwpComplete.votes) && Intrinsics.areEqual(this.error, formC1AdministrationHal2PpwpComplete.error) && Intrinsics.areEqual(this.kesesuaian, formC1AdministrationHal2PpwpComplete.kesesuaian) && Intrinsics.areEqual(this.kesesuaianAdministrationHal2, formC1AdministrationHal2PpwpComplete.kesesuaianAdministrationHal2) && Intrinsics.areEqual(this.kesesuaianVotes, formC1AdministrationHal2PpwpComplete.kesesuaianVotes);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.electionPage.hashCode() * 31;
        FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
        int hashCode2 = (((hashCode + (formC1AdministrationHal2 == null ? 0 : formC1AdministrationHal2.hashCode())) * 31) + this.votes.hashCode()) * 31;
        FormC1Error formC1Error = this.error;
        int hashCode3 = (hashCode2 + (formC1Error == null ? 0 : formC1Error.hashCode())) * 31;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        int hashCode4 = (hashCode3 + (formC1Kesesuaian == null ? 0 : formC1Kesesuaian.hashCode())) * 31;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        int hashCode5 = (hashCode4 + (formC1KesesuaianAdministrationHal2 == null ? 0 : formC1KesesuaianAdministrationHal2.hashCode())) * 31;
        List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
        return hashCode5 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        ElectionPage electionPage = this.electionPage;
        FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
        List<FormC1TabulationCandidateVote> list = this.votes;
        FormC1Error formC1Error = this.error;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        return "FormC1AdministrationHal2PpwpComplete(electionPage=" + electionPage + ", form=" + formC1AdministrationHal2 + ", votes=" + list + ", error=" + formC1Error + ", kesesuaian=" + formC1Kesesuaian + ", kesesuaianAdministrationHal2=" + formC1KesesuaianAdministrationHal2 + ", kesesuaianVotes=" + this.kesesuaianVotes + ")";
    }

    public FormC1AdministrationHal2PpwpComplete(ElectionPage electionPage, FormC1AdministrationHal2 formC1AdministrationHal2, List<FormC1TabulationCandidateVote> votes, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2, List<FormC1KesesuaianTabulationCandidateVote> list) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(votes, "votes");
        this.electionPage = electionPage;
        this.form = formC1AdministrationHal2;
        this.votes = votes;
        this.error = formC1Error;
        this.kesesuaian = formC1Kesesuaian;
        this.kesesuaianAdministrationHal2 = formC1KesesuaianAdministrationHal2;
        this.kesesuaianVotes = list;
    }

    public final ElectionPage getElectionPage() {
        return this.electionPage;
    }

    public final FormC1AdministrationHal2 getForm() {
        return this.form;
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

    public final FormC1KesesuaianAdministrationHal2 getKesesuaianAdministrationHal2() {
        return this.kesesuaianAdministrationHal2;
    }

    public final List<FormC1KesesuaianTabulationCandidateVote> getKesesuaianVotes() {
        return this.kesesuaianVotes;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isCheckedAll() {
        /*
            r4 = this;
            org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2 r0 = r4.kesesuaianAdministrationHal2
            r1 = 0
            r2 = 1
            if (r0 == 0) goto Le
            boolean r0 = r0.isCheckedAll()
            if (r0 != r2) goto Le
            r0 = r2
            goto Lf
        Le:
            r0 = r1
        Lf:
            if (r0 == 0) goto L45
            java.util.List<org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote> r0 = r4.kesesuaianVotes
            if (r0 == 0) goto L41
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r3 = r0 instanceof java.util.Collection
            if (r3 == 0) goto L26
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L26
        L24:
            r0 = r2
            goto L3d
        L26:
            java.util.Iterator r0 = r0.iterator()
        L2a:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L24
            java.lang.Object r3 = r0.next()
            org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote r3 = (org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote) r3
            boolean r3 = r3.isChecked()
            if (r3 != 0) goto L2a
            r0 = r1
        L3d:
            if (r0 != r2) goto L41
            r0 = r2
            goto L42
        L41:
            r0 = r1
        L42:
            if (r0 == 0) goto L45
            r1 = r2
        L45:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete.isCheckedAll():boolean");
    }

    public final boolean isVerified() {
        return this.kesesuaian != null;
    }

    public final List<Boolean> toIsSesuaiPerItem() {
        ArrayList emptyList;
        ArrayList arrayList = new ArrayList();
        List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
        if (list == null) {
            emptyList = CollectionsKt.emptyList();
        } else {
            List<FormC1KesesuaianTabulationCandidateVote> list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote : list2) {
                arrayList2.add(formC1KesesuaianTabulationCandidateVote.getVote());
            }
            emptyList = arrayList2;
        }
        arrayList.addAll(emptyList);
        Boolean[] boolArr = new Boolean[3];
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        boolArr[0] = formC1KesesuaianAdministrationHal2 != null ? formC1KesesuaianAdministrationHal2.getSuratSuaraSah() : null;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal22 = this.kesesuaianAdministrationHal2;
        boolArr[1] = formC1KesesuaianAdministrationHal22 != null ? formC1KesesuaianAdministrationHal22.getSuratSuaraTidakSah() : null;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal23 = this.kesesuaianAdministrationHal2;
        boolArr[2] = formC1KesesuaianAdministrationHal23 != null ? formC1KesesuaianAdministrationHal23.getTotalSuratSuara() : null;
        arrayList.addAll(CollectionsKt.listOf((Object[]) boolArr));
        return arrayList;
    }

    public final List<Integer> toKoreksiPerItem() {
        ArrayList emptyList;
        ArrayList arrayList = new ArrayList();
        List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
        if (list == null) {
            emptyList = CollectionsKt.emptyList();
        } else {
            List<FormC1KesesuaianTabulationCandidateVote> list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote : list2) {
                arrayList2.add(formC1KesesuaianTabulationCandidateVote.getVoteCorrected());
            }
            emptyList = arrayList2;
        }
        arrayList.addAll(emptyList);
        Integer[] numArr = new Integer[3];
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        numArr[0] = formC1KesesuaianAdministrationHal2 != null ? formC1KesesuaianAdministrationHal2.getSuratSuaraSahCorrected() : null;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal22 = this.kesesuaianAdministrationHal2;
        numArr[1] = formC1KesesuaianAdministrationHal22 != null ? formC1KesesuaianAdministrationHal22.getSuratSuaraTidakSahCorrected() : null;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal23 = this.kesesuaianAdministrationHal2;
        numArr[2] = formC1KesesuaianAdministrationHal23 != null ? formC1KesesuaianAdministrationHal23.getTotalSuratSuaraCorrected() : null;
        arrayList.addAll(CollectionsKt.listOf((Object[]) numArr));
        return arrayList;
    }
}
