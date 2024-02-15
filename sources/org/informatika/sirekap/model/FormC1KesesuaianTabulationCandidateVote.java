package org.informatika.sirekap.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1KesesuaianTabulationCandidateVote.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0001 B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J:\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u001e\u001a\u00020\u0007J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "", "idImage", "", FirebaseAnalytics.Param.INDEX, "", "vote", "", "(Ljava/lang/String;ILjava/lang/Boolean;)V", "voteCorrected", "(Ljava/lang/String;ILjava/lang/Boolean;Ljava/lang/Integer;)V", "getIdImage", "()Ljava/lang/String;", "getIndex", "()I", "getVote", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getVoteCorrected", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;ILjava/lang/Boolean;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "equals", "other", "hashCode", "isChecked", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1KesesuaianTabulationCandidateVote {
    public static final Companion Companion = new Companion(null);
    private final String idImage;
    private final int index;
    private final Boolean vote;
    private final Integer voteCorrected;

    public static /* synthetic */ FormC1KesesuaianTabulationCandidateVote copy$default(FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote, String str, int i, Boolean bool, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = formC1KesesuaianTabulationCandidateVote.idImage;
        }
        if ((i2 & 2) != 0) {
            i = formC1KesesuaianTabulationCandidateVote.index;
        }
        if ((i2 & 4) != 0) {
            bool = formC1KesesuaianTabulationCandidateVote.vote;
        }
        if ((i2 & 8) != 0) {
            num = formC1KesesuaianTabulationCandidateVote.voteCorrected;
        }
        return formC1KesesuaianTabulationCandidateVote.copy(str, i, bool, num);
    }

    public final String component1() {
        return this.idImage;
    }

    public final int component2() {
        return this.index;
    }

    public final Boolean component3() {
        return this.vote;
    }

    public final Integer component4() {
        return this.voteCorrected;
    }

    public final FormC1KesesuaianTabulationCandidateVote copy(String idImage, int i, Boolean bool, Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1KesesuaianTabulationCandidateVote(idImage, i, bool, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1KesesuaianTabulationCandidateVote) {
            FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote = (FormC1KesesuaianTabulationCandidateVote) obj;
            return Intrinsics.areEqual(this.idImage, formC1KesesuaianTabulationCandidateVote.idImage) && this.index == formC1KesesuaianTabulationCandidateVote.index && Intrinsics.areEqual(this.vote, formC1KesesuaianTabulationCandidateVote.vote) && Intrinsics.areEqual(this.voteCorrected, formC1KesesuaianTabulationCandidateVote.voteCorrected);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.idImage.hashCode() * 31) + Integer.hashCode(this.index)) * 31;
        Boolean bool = this.vote;
        int hashCode2 = (hashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num = this.voteCorrected;
        return hashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        String str = this.idImage;
        int i = this.index;
        Boolean bool = this.vote;
        return "FormC1KesesuaianTabulationCandidateVote(idImage=" + str + ", index=" + i + ", vote=" + bool + ", voteCorrected=" + this.voteCorrected + ")";
    }

    public FormC1KesesuaianTabulationCandidateVote(String idImage, int i, Boolean bool, Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.index = i;
        this.vote = bool;
        this.voteCorrected = num;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Boolean getVote() {
        return this.vote;
    }

    public final Integer getVoteCorrected() {
        return this.voteCorrected;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FormC1KesesuaianTabulationCandidateVote(String idImage, int i, Boolean bool) {
        this(idImage, i, bool, null);
        Intrinsics.checkNotNullParameter(idImage, "idImage");
    }

    public /* synthetic */ FormC1KesesuaianTabulationCandidateVote(String str, int i, Boolean bool, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? null : bool);
    }

    public final boolean isChecked() {
        return this.vote != null;
    }

    /* compiled from: FormC1KesesuaianTabulationCandidateVote.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00042\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote$Companion;", "", "()V", "createFromIsSesuaiPerItem", "", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "idImage", "", "listIndexes", "", "isSesuaiPerItem", "", "koreksiPerItem", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<FormC1KesesuaianTabulationCandidateVote> createFromIsSesuaiPerItem(String idImage, List<Integer> listIndexes, List<Boolean> isSesuaiPerItem, List<Integer> koreksiPerItem) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(listIndexes, "listIndexes");
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            List<Boolean> list = isSesuaiPerItem;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(new FormC1KesesuaianTabulationCandidateVote(idImage, listIndexes.get(i).intValue(), (Boolean) obj, koreksiPerItem.get(i)));
                i = i2;
            }
            return arrayList;
        }
    }
}
