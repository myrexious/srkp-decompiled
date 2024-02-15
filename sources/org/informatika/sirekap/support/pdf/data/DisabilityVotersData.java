package org.informatika.sirekap.support.pdf.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CHAdministratorData.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/DisabilityVotersData;", "", "count", "Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;", "(Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;)V", "getCount", "()Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DisabilityVotersData {
    private final VotersBasedGenderCount count;

    public static /* synthetic */ DisabilityVotersData copy$default(DisabilityVotersData disabilityVotersData, VotersBasedGenderCount votersBasedGenderCount, int i, Object obj) {
        if ((i & 1) != 0) {
            votersBasedGenderCount = disabilityVotersData.count;
        }
        return disabilityVotersData.copy(votersBasedGenderCount);
    }

    public final VotersBasedGenderCount component1() {
        return this.count;
    }

    public final DisabilityVotersData copy(VotersBasedGenderCount count) {
        Intrinsics.checkNotNullParameter(count, "count");
        return new DisabilityVotersData(count);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DisabilityVotersData) && Intrinsics.areEqual(this.count, ((DisabilityVotersData) obj).count);
    }

    public int hashCode() {
        return this.count.hashCode();
    }

    public String toString() {
        return "DisabilityVotersData(count=" + this.count + ")";
    }

    public DisabilityVotersData(VotersBasedGenderCount count) {
        Intrinsics.checkNotNullParameter(count, "count");
        this.count = count;
    }

    public final VotersBasedGenderCount getCount() {
        return this.count;
    }
}
