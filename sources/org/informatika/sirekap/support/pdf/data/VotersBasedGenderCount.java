package org.informatika.sirekap.support.pdf.data;

import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CHAdministratorData.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/VotersBasedGenderCount;", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "", "(Ljava/util/List;)V", "menVotersCount", "womenVotersCount", "total", "(III)V", "getMenVotersCount", "()I", "getTotal", "getWomenVotersCount", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VotersBasedGenderCount {
    private final int menVotersCount;
    private final int total;
    private final int womenVotersCount;

    public static /* synthetic */ VotersBasedGenderCount copy$default(VotersBasedGenderCount votersBasedGenderCount, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = votersBasedGenderCount.menVotersCount;
        }
        if ((i4 & 2) != 0) {
            i2 = votersBasedGenderCount.womenVotersCount;
        }
        if ((i4 & 4) != 0) {
            i3 = votersBasedGenderCount.total;
        }
        return votersBasedGenderCount.copy(i, i2, i3);
    }

    public final int component1() {
        return this.menVotersCount;
    }

    public final int component2() {
        return this.womenVotersCount;
    }

    public final int component3() {
        return this.total;
    }

    public final VotersBasedGenderCount copy(int i, int i2, int i3) {
        return new VotersBasedGenderCount(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VotersBasedGenderCount) {
            VotersBasedGenderCount votersBasedGenderCount = (VotersBasedGenderCount) obj;
            return this.menVotersCount == votersBasedGenderCount.menVotersCount && this.womenVotersCount == votersBasedGenderCount.womenVotersCount && this.total == votersBasedGenderCount.total;
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.menVotersCount) * 31) + Integer.hashCode(this.womenVotersCount)) * 31) + Integer.hashCode(this.total);
    }

    public String toString() {
        int i = this.menVotersCount;
        int i2 = this.womenVotersCount;
        return "VotersBasedGenderCount(menVotersCount=" + i + ", womenVotersCount=" + i2 + ", total=" + this.total + ")";
    }

    public VotersBasedGenderCount(int i, int i2, int i3) {
        this.menVotersCount = i;
        this.womenVotersCount = i2;
        this.total = i3;
    }

    public final int getMenVotersCount() {
        return this.menVotersCount;
    }

    public final int getWomenVotersCount() {
        return this.womenVotersCount;
    }

    public final int getTotal() {
        return this.total;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VotersBasedGenderCount(List<Integer> data) {
        this(data.get(0).intValue(), data.get(1).intValue(), data.get(2).intValue());
        Intrinsics.checkNotNullParameter(data, "data");
    }
}
