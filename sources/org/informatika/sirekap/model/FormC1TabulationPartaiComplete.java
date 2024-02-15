package org.informatika.sirekap.model;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.FormC1AdministrationComplete;

/* compiled from: FormC1TabulationPartai.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007¢\u0006\u0002\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007HÆ\u0003Je\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u001c\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u0007J\u0006\u00100\u001a\u000201J\t\u00102\u001a\u000201HÖ\u0001J\u0006\u00103\u001a\u00020(J\u0006\u00104\u001a\u00020(J\u000e\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u0007J\u000e\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u0007J\u000e\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u0007J\t\u00108\u001a\u000209HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001d¨\u0006:"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "form", "Lorg/informatika/sirekap/model/FormC1TabulationPartai;", "votes", "", "Lorg/informatika/sirekap/model/FormC1TabulationCandidateVote;", "error", "Lorg/informatika/sirekap/model/FormC1Error;", "kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "kesesuaianTabulationPartai", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;", "kesesuaianVotes", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "(Lorg/informatika/sirekap/model/ElectionPage;Lorg/informatika/sirekap/model/FormC1TabulationPartai;Ljava/util/List;Lorg/informatika/sirekap/model/FormC1Error;Lorg/informatika/sirekap/model/FormC1Kesesuaian;Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;Ljava/util/List;)V", "getElectionPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "getError", "()Lorg/informatika/sirekap/model/FormC1Error;", "getForm", "()Lorg/informatika/sirekap/model/FormC1TabulationPartai;", "getKesesuaian", "()Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getKesesuaianTabulationPartai", "()Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;", "getKesesuaianVotes", "()Ljava/util/List;", "getVotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "getSalinanData", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "context", "Landroid/content/Context;", "candidates", "Lorg/informatika/sirekap/model/Candidate;", "getSuratSahPartaiDanCalonFinal", "", "hashCode", "isCheckedAll", "isVerified", "toIsSesuaiPerItem", "toKoreksiPerItem", "toNilaiPerItem", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationPartaiComplete {
    private final ElectionPage electionPage;
    private final FormC1Error error;
    private final FormC1TabulationPartai form;
    private final FormC1Kesesuaian kesesuaian;
    private final FormC1KesesuaianTabulationPartai kesesuaianTabulationPartai;
    private final List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes;
    private final List<FormC1TabulationCandidateVote> votes;

    public static /* synthetic */ FormC1TabulationPartaiComplete copy$default(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete, ElectionPage electionPage, FormC1TabulationPartai formC1TabulationPartai, List list, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            electionPage = formC1TabulationPartaiComplete.electionPage;
        }
        if ((i & 2) != 0) {
            formC1TabulationPartai = formC1TabulationPartaiComplete.form;
        }
        FormC1TabulationPartai formC1TabulationPartai2 = formC1TabulationPartai;
        List<FormC1TabulationCandidateVote> list3 = list;
        if ((i & 4) != 0) {
            list3 = formC1TabulationPartaiComplete.votes;
        }
        List list4 = list3;
        if ((i & 8) != 0) {
            formC1Error = formC1TabulationPartaiComplete.error;
        }
        FormC1Error formC1Error2 = formC1Error;
        if ((i & 16) != 0) {
            formC1Kesesuaian = formC1TabulationPartaiComplete.kesesuaian;
        }
        FormC1Kesesuaian formC1Kesesuaian2 = formC1Kesesuaian;
        if ((i & 32) != 0) {
            formC1KesesuaianTabulationPartai = formC1TabulationPartaiComplete.kesesuaianTabulationPartai;
        }
        FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai2 = formC1KesesuaianTabulationPartai;
        List<FormC1KesesuaianTabulationCandidateVote> list5 = list2;
        if ((i & 64) != 0) {
            list5 = formC1TabulationPartaiComplete.kesesuaianVotes;
        }
        return formC1TabulationPartaiComplete.copy(electionPage, formC1TabulationPartai2, list4, formC1Error2, formC1Kesesuaian2, formC1KesesuaianTabulationPartai2, list5);
    }

    public final ElectionPage component1() {
        return this.electionPage;
    }

    public final FormC1TabulationPartai component2() {
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

    public final FormC1KesesuaianTabulationPartai component6() {
        return this.kesesuaianTabulationPartai;
    }

    public final List<FormC1KesesuaianTabulationCandidateVote> component7() {
        return this.kesesuaianVotes;
    }

    public final FormC1TabulationPartaiComplete copy(ElectionPage electionPage, FormC1TabulationPartai formC1TabulationPartai, List<FormC1TabulationCandidateVote> votes, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai, List<FormC1KesesuaianTabulationCandidateVote> list) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(votes, "votes");
        return new FormC1TabulationPartaiComplete(electionPage, formC1TabulationPartai, votes, formC1Error, formC1Kesesuaian, formC1KesesuaianTabulationPartai, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationPartaiComplete) {
            FormC1TabulationPartaiComplete formC1TabulationPartaiComplete = (FormC1TabulationPartaiComplete) obj;
            return Intrinsics.areEqual(this.electionPage, formC1TabulationPartaiComplete.electionPage) && Intrinsics.areEqual(this.form, formC1TabulationPartaiComplete.form) && Intrinsics.areEqual(this.votes, formC1TabulationPartaiComplete.votes) && Intrinsics.areEqual(this.error, formC1TabulationPartaiComplete.error) && Intrinsics.areEqual(this.kesesuaian, formC1TabulationPartaiComplete.kesesuaian) && Intrinsics.areEqual(this.kesesuaianTabulationPartai, formC1TabulationPartaiComplete.kesesuaianTabulationPartai) && Intrinsics.areEqual(this.kesesuaianVotes, formC1TabulationPartaiComplete.kesesuaianVotes);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.electionPage.hashCode() * 31;
        FormC1TabulationPartai formC1TabulationPartai = this.form;
        int hashCode2 = (((hashCode + (formC1TabulationPartai == null ? 0 : formC1TabulationPartai.hashCode())) * 31) + this.votes.hashCode()) * 31;
        FormC1Error formC1Error = this.error;
        int hashCode3 = (hashCode2 + (formC1Error == null ? 0 : formC1Error.hashCode())) * 31;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        int hashCode4 = (hashCode3 + (formC1Kesesuaian == null ? 0 : formC1Kesesuaian.hashCode())) * 31;
        FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai = this.kesesuaianTabulationPartai;
        int hashCode5 = (hashCode4 + (formC1KesesuaianTabulationPartai == null ? 0 : formC1KesesuaianTabulationPartai.hashCode())) * 31;
        List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
        return hashCode5 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        ElectionPage electionPage = this.electionPage;
        FormC1TabulationPartai formC1TabulationPartai = this.form;
        List<FormC1TabulationCandidateVote> list = this.votes;
        FormC1Error formC1Error = this.error;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai = this.kesesuaianTabulationPartai;
        return "FormC1TabulationPartaiComplete(electionPage=" + electionPage + ", form=" + formC1TabulationPartai + ", votes=" + list + ", error=" + formC1Error + ", kesesuaian=" + formC1Kesesuaian + ", kesesuaianTabulationPartai=" + formC1KesesuaianTabulationPartai + ", kesesuaianVotes=" + this.kesesuaianVotes + ")";
    }

    public FormC1TabulationPartaiComplete(ElectionPage electionPage, FormC1TabulationPartai formC1TabulationPartai, List<FormC1TabulationCandidateVote> votes, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai, List<FormC1KesesuaianTabulationCandidateVote> list) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        Intrinsics.checkNotNullParameter(votes, "votes");
        this.electionPage = electionPage;
        this.form = formC1TabulationPartai;
        this.votes = votes;
        this.error = formC1Error;
        this.kesesuaian = formC1Kesesuaian;
        this.kesesuaianTabulationPartai = formC1KesesuaianTabulationPartai;
        this.kesesuaianVotes = list;
    }

    public final ElectionPage getElectionPage() {
        return this.electionPage;
    }

    public final FormC1TabulationPartai getForm() {
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

    public final FormC1KesesuaianTabulationPartai getKesesuaianTabulationPartai() {
        return this.kesesuaianTabulationPartai;
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
            org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai r0 = r4.kesesuaianTabulationPartai
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
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.model.FormC1TabulationPartaiComplete.isCheckedAll():boolean");
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
        FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai = this.kesesuaianTabulationPartai;
        arrayList.add(formC1KesesuaianTabulationPartai != null ? formC1KesesuaianTabulationPartai.getSuratSahPartaiDanCalon() : null);
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
        FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai = this.kesesuaianTabulationPartai;
        arrayList.add(formC1KesesuaianTabulationPartai != null ? formC1KesesuaianTabulationPartai.getSuratSahPartaiDanCalonCorrected() : null);
        return arrayList;
    }

    public final List<Integer> toNilaiPerItem() {
        ArrayList arrayList = new ArrayList();
        List<FormC1TabulationCandidateVote> list = this.votes;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (FormC1TabulationCandidateVote formC1TabulationCandidateVote : list) {
            arrayList2.add(Integer.valueOf(formC1TabulationCandidateVote.getVote()));
        }
        arrayList.addAll(arrayList2);
        FormC1TabulationPartai formC1TabulationPartai = this.form;
        arrayList.add(formC1TabulationPartai != null ? formC1TabulationPartai.getSuratSahPartaiDanCalon() : null);
        return arrayList;
    }

    public final int getSuratSahPartaiDanCalonFinal() {
        Integer suratSahPartaiDanCalon;
        FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai = this.kesesuaianTabulationPartai;
        if (formC1KesesuaianTabulationPartai != null ? Intrinsics.areEqual((Object) formC1KesesuaianTabulationPartai.getSuratSahPartaiDanCalon(), (Object) false) : false) {
            Integer suratSahPartaiDanCalonCorrected = this.kesesuaianTabulationPartai.getSuratSahPartaiDanCalonCorrected();
            if (suratSahPartaiDanCalonCorrected == null) {
                FormC1TabulationPartai formC1TabulationPartai = this.form;
                suratSahPartaiDanCalonCorrected = formC1TabulationPartai != null ? formC1TabulationPartai.getSuratSahPartaiDanCalon() : null;
                if (suratSahPartaiDanCalonCorrected == null) {
                    return 0;
                }
            }
            return suratSahPartaiDanCalonCorrected.intValue();
        }
        FormC1TabulationPartai formC1TabulationPartai2 = this.form;
        if (formC1TabulationPartai2 == null || (suratSahPartaiDanCalon = formC1TabulationPartai2.getSuratSahPartaiDanCalon()) == null) {
            return 0;
        }
        return suratSahPartaiDanCalon.intValue();
    }

    public final FormC1AdministrationComplete.SectionDataPdf getSalinanData(Context context, List<Candidate> candidates) {
        String str;
        Object obj;
        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote;
        Integer voteCorrected;
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        FormC1AdministrationComplete.SectionDataPdf sectionDataPdf = new FormC1AdministrationComplete.SectionDataPdf("IV.", "DATA RINCIAN PEROLEHAN SUARA", null, "Jumlah", null, null, 52, null);
        sectionDataPdf.getRows().addAll(CollectionsKt.listOf(new FormC1AdministrationComplete.RowDataPdf(FormC1AdministrationComplete.RowDataPdfType.header, "A.1.", "SUARA PARTAI", null, null, null, null, 120, null)));
        int i = 0;
        for (Object obj2 : this.votes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj2;
            Iterator<T> it = candidates.iterator();
            while (true) {
                str = null;
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
            List<FormC1AdministrationComplete.RowDataPdf> rows = sectionDataPdf.getRows();
            FormC1AdministrationComplete.RowDataPdfType rowDataPdfType = FormC1AdministrationComplete.RowDataPdfType.content;
            if ((decrypted != null ? decrypted.getNoUrutPencalonan() : 0) >= 0 && decrypted != null) {
                str = Integer.valueOf(decrypted.getNoUrutPencalonan()).toString();
            }
            String str2 = str;
            String str3 = (decrypted == null || (r8 = decrypted.getNama()) == null) ? "-" : "-";
            List<FormC1KesesuaianTabulationCandidateVote> list = this.kesesuaianVotes;
            rows.add(new FormC1AdministrationComplete.RowDataPdf(rowDataPdfType, str2, str3, null, null, Integer.valueOf((list == null || (formC1KesesuaianTabulationCandidateVote = list.get(i)) == null || (voteCorrected = formC1KesesuaianTabulationCandidateVote.getVoteCorrected()) == null) ? formC1TabulationCandidateVote.getVote() : voteCorrected.intValue()), null, 64, null));
            if (i == 0) {
                sectionDataPdf.getRows().add(new FormC1AdministrationComplete.RowDataPdf(FormC1AdministrationComplete.RowDataPdfType.header, "A.2.", "SUARA CALON", null, null, null, null, 120, null));
            }
            i = i2;
        }
        sectionDataPdf.getRows().addAll(CollectionsKt.listOf(new FormC1AdministrationComplete.RowDataPdf(FormC1AdministrationComplete.RowDataPdfType.content, "B.", "Jumlah Suara Sah Partai Politik dan Calon ( A.1 + A.2 )", null, null, Integer.valueOf(getSuratSahPartaiDanCalonFinal()), null, 64, null)));
        return sectionDataPdf;
    }
}
