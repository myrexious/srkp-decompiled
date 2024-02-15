package org.informatika.sirekap.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialOccurrence.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0005J\t\u0010\u0016\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0013HÖ\u0001R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/model/SpecialOccurrenceWithPages;", "", "specialOccurrence", "Lorg/informatika/sirekap/model/SpecialOccurrence;", "pages", "", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "(Lorg/informatika/sirekap/model/SpecialOccurrence;Ljava/util/List;)V", "getPages", "()Ljava/util/List;", "getSpecialOccurrence", "()Lorg/informatika/sirekap/model/SpecialOccurrence;", "component1", "component2", "copy", "equals", "", "other", "getAvailableImages", "", "getAvailablePageNums", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SpecialOccurrenceWithPages {
    private final List<SpecialOccurrencePage> pages;
    private final SpecialOccurrence specialOccurrence;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SpecialOccurrenceWithPages copy$default(SpecialOccurrenceWithPages specialOccurrenceWithPages, SpecialOccurrence specialOccurrence, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            specialOccurrence = specialOccurrenceWithPages.specialOccurrence;
        }
        if ((i & 2) != 0) {
            list = specialOccurrenceWithPages.pages;
        }
        return specialOccurrenceWithPages.copy(specialOccurrence, list);
    }

    public final SpecialOccurrence component1() {
        return this.specialOccurrence;
    }

    public final List<SpecialOccurrencePage> component2() {
        return this.pages;
    }

    public final SpecialOccurrenceWithPages copy(SpecialOccurrence specialOccurrence, List<SpecialOccurrencePage> pages) {
        Intrinsics.checkNotNullParameter(specialOccurrence, "specialOccurrence");
        Intrinsics.checkNotNullParameter(pages, "pages");
        return new SpecialOccurrenceWithPages(specialOccurrence, pages);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SpecialOccurrenceWithPages) {
            SpecialOccurrenceWithPages specialOccurrenceWithPages = (SpecialOccurrenceWithPages) obj;
            return Intrinsics.areEqual(this.specialOccurrence, specialOccurrenceWithPages.specialOccurrence) && Intrinsics.areEqual(this.pages, specialOccurrenceWithPages.pages);
        }
        return false;
    }

    public int hashCode() {
        return (this.specialOccurrence.hashCode() * 31) + this.pages.hashCode();
    }

    public String toString() {
        SpecialOccurrence specialOccurrence = this.specialOccurrence;
        return "SpecialOccurrenceWithPages(specialOccurrence=" + specialOccurrence + ", pages=" + this.pages + ")";
    }

    public SpecialOccurrenceWithPages(SpecialOccurrence specialOccurrence, List<SpecialOccurrencePage> pages) {
        Intrinsics.checkNotNullParameter(specialOccurrence, "specialOccurrence");
        Intrinsics.checkNotNullParameter(pages, "pages");
        this.specialOccurrence = specialOccurrence;
        this.pages = pages;
    }

    public final SpecialOccurrence getSpecialOccurrence() {
        return this.specialOccurrence;
    }

    public final List<SpecialOccurrencePage> getPages() {
        return this.pages;
    }

    public final List<String> getAvailableImages() {
        ArrayList arrayList = new ArrayList();
        for (SpecialOccurrencePage specialOccurrencePage : this.pages) {
            arrayList.add(specialOccurrencePage.getCroppedPhotoPath());
        }
        return arrayList;
    }

    public final List<Integer> getAvailablePageNums() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : this.pages) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((SpecialOccurrencePage) obj).getCroppedPhotoPath();
            arrayList.add(Integer.valueOf(i2));
            i = i2;
        }
        return arrayList;
    }
}
