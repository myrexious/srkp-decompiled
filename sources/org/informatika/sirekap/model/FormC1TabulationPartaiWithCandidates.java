package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1TabulationPartaiWithCandidates.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPartaiWithCandidates;", "", "formC1TabulationPartai", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "(Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;Ljava/util/List;)V", "getCandidates", "()Ljava/util/List;", "getFormC1TabulationPartai", "()Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationPartaiWithCandidates {
    private final List<Candidate> candidates;
    private final FormC1TabulationPartaiComplete formC1TabulationPartai;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FormC1TabulationPartaiWithCandidates copy$default(FormC1TabulationPartaiWithCandidates formC1TabulationPartaiWithCandidates, FormC1TabulationPartaiComplete formC1TabulationPartaiComplete, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            formC1TabulationPartaiComplete = formC1TabulationPartaiWithCandidates.formC1TabulationPartai;
        }
        if ((i & 2) != 0) {
            list = formC1TabulationPartaiWithCandidates.candidates;
        }
        return formC1TabulationPartaiWithCandidates.copy(formC1TabulationPartaiComplete, list);
    }

    public final FormC1TabulationPartaiComplete component1() {
        return this.formC1TabulationPartai;
    }

    public final List<Candidate> component2() {
        return this.candidates;
    }

    public final FormC1TabulationPartaiWithCandidates copy(FormC1TabulationPartaiComplete formC1TabulationPartai, List<Candidate> candidates) {
        Intrinsics.checkNotNullParameter(formC1TabulationPartai, "formC1TabulationPartai");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        return new FormC1TabulationPartaiWithCandidates(formC1TabulationPartai, candidates);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationPartaiWithCandidates) {
            FormC1TabulationPartaiWithCandidates formC1TabulationPartaiWithCandidates = (FormC1TabulationPartaiWithCandidates) obj;
            return Intrinsics.areEqual(this.formC1TabulationPartai, formC1TabulationPartaiWithCandidates.formC1TabulationPartai) && Intrinsics.areEqual(this.candidates, formC1TabulationPartaiWithCandidates.candidates);
        }
        return false;
    }

    public int hashCode() {
        return (this.formC1TabulationPartai.hashCode() * 31) + this.candidates.hashCode();
    }

    public String toString() {
        FormC1TabulationPartaiComplete formC1TabulationPartaiComplete = this.formC1TabulationPartai;
        return "FormC1TabulationPartaiWithCandidates(formC1TabulationPartai=" + formC1TabulationPartaiComplete + ", candidates=" + this.candidates + ")";
    }

    public FormC1TabulationPartaiWithCandidates(FormC1TabulationPartaiComplete formC1TabulationPartai, List<Candidate> candidates) {
        Intrinsics.checkNotNullParameter(formC1TabulationPartai, "formC1TabulationPartai");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        this.formC1TabulationPartai = formC1TabulationPartai;
        this.candidates = candidates;
    }

    public final FormC1TabulationPartaiComplete getFormC1TabulationPartai() {
        return this.formC1TabulationPartai;
    }

    public final List<Candidate> getCandidates() {
        return this.candidates;
    }
}
