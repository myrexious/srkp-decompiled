package org.informatika.sirekap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Election.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u0005J\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0005J\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010\u001f\u001a\u00020\u0019J\u0006\u0010 \u001a\u00020\u001dJ\u0006\u0010!\u001a\u00020\u001dJ\u0006\u0010\"\u001a\u00020\u0019J\u0006\u0010#\u001a\u00020\u001dJ\t\u0010$\u001a\u00020\u001dHÖ\u0001J\u0006\u0010%\u001a\u00020\u0010J\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010'\u001a\u00020\u0010J\u0006\u0010(\u001a\u00020\u0010J\u0006\u0010)\u001a\u00020\u0010J\u0006\u0010*\u001a\u00020\u0010J\t\u0010+\u001a\u00020\u0019HÖ\u0001R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/model/ElectionWithRelation;", "", "election", "Lorg/informatika/sirekap/model/Election;", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "pages", "Lorg/informatika/sirekap/model/ElectionPage;", "(Lorg/informatika/sirekap/model/Election;Ljava/util/List;Ljava/util/List;)V", "getCandidates", "()Ljava/util/List;", "getElection", "()Lorg/informatika/sirekap/model/Election;", "getPages", "canFillTpsTime", "", "component1", "component2", "component3", "copy", "equals", "other", "getAllPartais", "", "", "", "getAvailableImages", "getAvailablePageNums", "", "getCandidatesNum", "getCandidatesNumString", "getCapturedCount", "getNotRecognizedCount", "getPartaisNumString", "getVerifiedCount", "hashCode", "isAllPhotosDone", "isAllPhotosDoneOrCreatedPdf", "isAllPhotosTaken", "isAllPhotosTakenOrCreatedPdf", "isPdfOffline", "isShowInfoAlertOffline", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionWithRelation {
    private final List<Candidate> candidates;
    private final Election election;
    private final List<ElectionPage> pages;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ElectionWithRelation copy$default(ElectionWithRelation electionWithRelation, Election election, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            election = electionWithRelation.election;
        }
        if ((i & 2) != 0) {
            list = electionWithRelation.candidates;
        }
        if ((i & 4) != 0) {
            list2 = electionWithRelation.pages;
        }
        return electionWithRelation.copy(election, list, list2);
    }

    public final Election component1() {
        return this.election;
    }

    public final List<Candidate> component2() {
        return this.candidates;
    }

    public final List<ElectionPage> component3() {
        return this.pages;
    }

    public final ElectionWithRelation copy(Election election, List<Candidate> candidates, List<ElectionPage> pages) {
        Intrinsics.checkNotNullParameter(election, "election");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(pages, "pages");
        return new ElectionWithRelation(election, candidates, pages);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionWithRelation) {
            ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
            return Intrinsics.areEqual(this.election, electionWithRelation.election) && Intrinsics.areEqual(this.candidates, electionWithRelation.candidates) && Intrinsics.areEqual(this.pages, electionWithRelation.pages);
        }
        return false;
    }

    public int hashCode() {
        return (((this.election.hashCode() * 31) + this.candidates.hashCode()) * 31) + this.pages.hashCode();
    }

    public String toString() {
        Election election = this.election;
        List<Candidate> list = this.candidates;
        return "ElectionWithRelation(election=" + election + ", candidates=" + list + ", pages=" + this.pages + ")";
    }

    public ElectionWithRelation(Election election, List<Candidate> candidates, List<ElectionPage> pages) {
        Intrinsics.checkNotNullParameter(election, "election");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(pages, "pages");
        this.election = election;
        this.candidates = candidates;
        this.pages = pages;
    }

    public final Election getElection() {
        return this.election;
    }

    public final List<Candidate> getCandidates() {
        return this.candidates;
    }

    public final List<ElectionPage> getPages() {
        return this.pages;
    }

    public final String getCandidatesNumString() {
        return String.valueOf(getCandidatesNum());
    }

    public final String getPartaisNumString() {
        return String.valueOf(getAllPartais().size());
    }

    public final int getCandidatesNum() {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.candidates) {
            if (((Candidate) obj).getIdPilihan() > 0) {
                arrayList.add(obj);
            }
        }
        return arrayList.size();
    }

    public final int getCapturedCount() {
        List<ElectionPage> list = this.pages;
        if ((list instanceof Collection) && list.isEmpty()) {
            return 0;
        }
        int i = 0;
        for (ElectionPage electionPage : list) {
            if ((electionPage.isPhotoTaken() && !electionPage.isDone()) && (i = i + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return i;
    }

    public final int getVerifiedCount() {
        List<ElectionPage> list = this.pages;
        int i = 0;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (ElectionPage electionPage : list) {
                if (electionPage.isDone() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i;
    }

    public final int getNotRecognizedCount() {
        List<ElectionPage> list = this.pages;
        int i = 0;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (ElectionPage electionPage : list) {
                if ((!electionPage.isPhotoTaken()) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i;
    }

    public final boolean isAllPhotosTaken() {
        List<ElectionPage> list = this.pages;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        for (ElectionPage electionPage : list) {
            if (!electionPage.isPhotoTaken()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isAllPhotosDone() {
        List<ElectionPage> list = this.pages;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        for (ElectionPage electionPage : list) {
            if (!electionPage.isDone()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isAllPhotosTakenOrCreatedPdf() {
        return isAllPhotosTaken() || this.election.isZipped();
    }

    public final boolean isAllPhotosDoneOrCreatedPdf() {
        return isAllPhotosDone() || this.election.isZipped();
    }

    public final boolean canFillTpsTime() {
        return isAllPhotosDone();
    }

    public final boolean isShowInfoAlertOffline() {
        return isAllPhotosDone() && this.election.isZipped() && isPdfOffline() && !this.election.isUploadedPdf();
    }

    public final List<String> getAvailableImages() {
        ArrayList arrayList = new ArrayList();
        for (ElectionPage electionPage : CollectionsKt.sortedWith(this.pages, new Comparator() { // from class: org.informatika.sirekap.model.ElectionWithRelation$getAvailableImages$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((ElectionPage) t).getKind()), Integer.valueOf(((ElectionPage) t2).getKind()));
            }
        })) {
            String croppedPhotoPath = electionPage.getCroppedPhotoPath();
            if (croppedPhotoPath != null) {
                arrayList.add(croppedPhotoPath);
            }
        }
        return arrayList;
    }

    public final List<Integer> getAvailablePageNums() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : CollectionsKt.sortedWith(this.pages, new Comparator() { // from class: org.informatika.sirekap.model.ElectionWithRelation$getAvailablePageNums$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((ElectionPage) t).getKind()), Integer.valueOf(((ElectionPage) t2).getKind()));
            }
        })) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((ElectionPage) obj).getCroppedPhotoPath() != null) {
                arrayList.add(Integer.valueOf(i2));
            }
            i = i2;
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x003c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isPdfOffline() {
        /*
            r5 = this;
            java.util.List<org.informatika.sirekap.model.ElectionPage> r0 = r5.pages
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r1 = r0 instanceof java.util.Collection
            r2 = 0
            if (r1 == 0) goto L13
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L13
            goto L3d
        L13:
            java.util.Iterator r0 = r0.iterator()
        L17:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L3d
            java.lang.Object r1 = r0.next()
            org.informatika.sirekap.model.ElectionPage r1 = (org.informatika.sirekap.model.ElectionPage) r1
            java.lang.String r3 = r1.getIdImage()
            r4 = 1
            if (r3 != 0) goto L2c
        L2a:
            r1 = r2
            goto L3a
        L2c:
            java.lang.String r1 = r1.getIdImage()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = org.informatika.sirekap.support.ElectionUtil.extractIdImageReal(r1)
            if (r1 >= 0) goto L2a
            r1 = r4
        L3a:
            if (r1 == 0) goto L17
            r2 = r4
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.model.ElectionWithRelation.isPdfOffline():boolean");
    }

    public final Map<String, Long> getAllPartais() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Candidate candidate : this.candidates) {
            Long idPartai = candidate.getIdPartai();
            if (idPartai != null) {
                long longValue = idPartai.longValue();
                String namaPartai = candidate.getNamaPartai();
                if (namaPartai != null && !linkedHashMap.containsValue(Long.valueOf(longValue))) {
                    linkedHashMap.put(namaPartai, Long.valueOf(longValue));
                }
            }
        }
        return linkedHashMap;
    }
}
