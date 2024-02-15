package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1TabulationWithCandidates.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationWithCandidates;", "", "formC1Tabulation", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "(Lorg/informatika/sirekap/model/FormC1TabulationComplete;Ljava/util/List;)V", "getCandidates", "()Ljava/util/List;", "getFormC1Tabulation", "()Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationWithCandidates {
    private final List<Candidate> candidates;
    private final FormC1TabulationComplete formC1Tabulation;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FormC1TabulationWithCandidates copy$default(FormC1TabulationWithCandidates formC1TabulationWithCandidates, FormC1TabulationComplete formC1TabulationComplete, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            formC1TabulationComplete = formC1TabulationWithCandidates.formC1Tabulation;
        }
        if ((i & 2) != 0) {
            list = formC1TabulationWithCandidates.candidates;
        }
        return formC1TabulationWithCandidates.copy(formC1TabulationComplete, list);
    }

    public final FormC1TabulationComplete component1() {
        return this.formC1Tabulation;
    }

    public final List<Candidate> component2() {
        return this.candidates;
    }

    public final FormC1TabulationWithCandidates copy(FormC1TabulationComplete formC1Tabulation, List<Candidate> candidates) {
        Intrinsics.checkNotNullParameter(formC1Tabulation, "formC1Tabulation");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        return new FormC1TabulationWithCandidates(formC1Tabulation, candidates);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationWithCandidates) {
            FormC1TabulationWithCandidates formC1TabulationWithCandidates = (FormC1TabulationWithCandidates) obj;
            return Intrinsics.areEqual(this.formC1Tabulation, formC1TabulationWithCandidates.formC1Tabulation) && Intrinsics.areEqual(this.candidates, formC1TabulationWithCandidates.candidates);
        }
        return false;
    }

    public int hashCode() {
        return (this.formC1Tabulation.hashCode() * 31) + this.candidates.hashCode();
    }

    public String toString() {
        FormC1TabulationComplete formC1TabulationComplete = this.formC1Tabulation;
        return "FormC1TabulationWithCandidates(formC1Tabulation=" + formC1TabulationComplete + ", candidates=" + this.candidates + ")";
    }

    public FormC1TabulationWithCandidates(FormC1TabulationComplete formC1Tabulation, List<Candidate> candidates) {
        Intrinsics.checkNotNullParameter(formC1Tabulation, "formC1Tabulation");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        this.formC1Tabulation = formC1Tabulation;
        this.candidates = candidates;
    }

    public final FormC1TabulationComplete getFormC1Tabulation() {
        return this.formC1Tabulation;
    }

    public final List<Candidate> getCandidates() {
        return this.candidates;
    }
}
