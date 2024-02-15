package org.informatika.sirekap.support.pdf.data;

import com.google.firebase.messaging.Constants;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CHAdministratorData.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0005B-\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0002\u0010\fJ\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/VotersData;", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "", "(Ljava/util/List;)V", PDPageLabelRange.STYLE_LETTERS_LOWER, "Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;", "b1", "b2", "b3", "b4", "(Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;)V", "getA", "()Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;", "getB1", "getB2", "getB3", "getB4", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VotersData {
    private final VotersBasedGenderCount a;
    private final VotersBasedGenderCount b1;
    private final VotersBasedGenderCount b2;
    private final VotersBasedGenderCount b3;
    private final VotersBasedGenderCount b4;

    public static /* synthetic */ VotersData copy$default(VotersData votersData, VotersBasedGenderCount votersBasedGenderCount, VotersBasedGenderCount votersBasedGenderCount2, VotersBasedGenderCount votersBasedGenderCount3, VotersBasedGenderCount votersBasedGenderCount4, VotersBasedGenderCount votersBasedGenderCount5, int i, Object obj) {
        if ((i & 1) != 0) {
            votersBasedGenderCount = votersData.a;
        }
        if ((i & 2) != 0) {
            votersBasedGenderCount2 = votersData.b1;
        }
        VotersBasedGenderCount votersBasedGenderCount6 = votersBasedGenderCount2;
        if ((i & 4) != 0) {
            votersBasedGenderCount3 = votersData.b2;
        }
        VotersBasedGenderCount votersBasedGenderCount7 = votersBasedGenderCount3;
        if ((i & 8) != 0) {
            votersBasedGenderCount4 = votersData.b3;
        }
        VotersBasedGenderCount votersBasedGenderCount8 = votersBasedGenderCount4;
        if ((i & 16) != 0) {
            votersBasedGenderCount5 = votersData.b4;
        }
        return votersData.copy(votersBasedGenderCount, votersBasedGenderCount6, votersBasedGenderCount7, votersBasedGenderCount8, votersBasedGenderCount5);
    }

    public final VotersBasedGenderCount component1() {
        return this.a;
    }

    public final VotersBasedGenderCount component2() {
        return this.b1;
    }

    public final VotersBasedGenderCount component3() {
        return this.b2;
    }

    public final VotersBasedGenderCount component4() {
        return this.b3;
    }

    public final VotersBasedGenderCount component5() {
        return this.b4;
    }

    public final VotersData copy(VotersBasedGenderCount a, VotersBasedGenderCount b1, VotersBasedGenderCount b2, VotersBasedGenderCount b3, VotersBasedGenderCount b4) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b1, "b1");
        Intrinsics.checkNotNullParameter(b2, "b2");
        Intrinsics.checkNotNullParameter(b3, "b3");
        Intrinsics.checkNotNullParameter(b4, "b4");
        return new VotersData(a, b1, b2, b3, b4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VotersData) {
            VotersData votersData = (VotersData) obj;
            return Intrinsics.areEqual(this.a, votersData.a) && Intrinsics.areEqual(this.b1, votersData.b1) && Intrinsics.areEqual(this.b2, votersData.b2) && Intrinsics.areEqual(this.b3, votersData.b3) && Intrinsics.areEqual(this.b4, votersData.b4);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b1.hashCode()) * 31) + this.b2.hashCode()) * 31) + this.b3.hashCode()) * 31) + this.b4.hashCode();
    }

    public String toString() {
        VotersBasedGenderCount votersBasedGenderCount = this.a;
        VotersBasedGenderCount votersBasedGenderCount2 = this.b1;
        VotersBasedGenderCount votersBasedGenderCount3 = this.b2;
        VotersBasedGenderCount votersBasedGenderCount4 = this.b3;
        return "VotersData(a=" + votersBasedGenderCount + ", b1=" + votersBasedGenderCount2 + ", b2=" + votersBasedGenderCount3 + ", b3=" + votersBasedGenderCount4 + ", b4=" + this.b4 + ")";
    }

    public VotersData(VotersBasedGenderCount a, VotersBasedGenderCount b1, VotersBasedGenderCount b2, VotersBasedGenderCount b3, VotersBasedGenderCount b4) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b1, "b1");
        Intrinsics.checkNotNullParameter(b2, "b2");
        Intrinsics.checkNotNullParameter(b3, "b3");
        Intrinsics.checkNotNullParameter(b4, "b4");
        this.a = a;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
    }

    public final VotersBasedGenderCount getA() {
        return this.a;
    }

    public final VotersBasedGenderCount getB1() {
        return this.b1;
    }

    public final VotersBasedGenderCount getB2() {
        return this.b2;
    }

    public final VotersBasedGenderCount getB3() {
        return this.b3;
    }

    public final VotersBasedGenderCount getB4() {
        return this.b4;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VotersData(List<? extends List<Integer>> data) {
        this(new VotersBasedGenderCount(data.get(0)), new VotersBasedGenderCount(data.get(1)), new VotersBasedGenderCount(data.get(2)), new VotersBasedGenderCount(data.get(3)), new VotersBasedGenderCount(data.get(4)));
        Intrinsics.checkNotNullParameter(data, "data");
    }
}
