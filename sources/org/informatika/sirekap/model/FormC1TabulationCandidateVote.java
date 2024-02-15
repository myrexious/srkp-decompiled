package org.informatika.sirekap.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.FormC1AdministrationHal2Payload;
import org.informatika.sirekap.model.FormC1TabulationPayload;
import org.informatika.sirekap.support.ElectionUtil;

/* compiled from: FormC1Tabulation.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationCandidateVote;", "", "idImage", "", FirebaseAnalytics.Param.INDEX, "", "vote", "(Ljava/lang/String;II)V", "getIdImage", "()Ljava/lang/String;", "getIndex", "()I", "getVote", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationCandidateVote {
    public static final Companion Companion = new Companion(null);
    private final String idImage;
    private final int index;
    private final int vote;

    public static /* synthetic */ FormC1TabulationCandidateVote copy$default(FormC1TabulationCandidateVote formC1TabulationCandidateVote, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = formC1TabulationCandidateVote.idImage;
        }
        if ((i3 & 2) != 0) {
            i = formC1TabulationCandidateVote.index;
        }
        if ((i3 & 4) != 0) {
            i2 = formC1TabulationCandidateVote.vote;
        }
        return formC1TabulationCandidateVote.copy(str, i, i2);
    }

    public final String component1() {
        return this.idImage;
    }

    public final int component2() {
        return this.index;
    }

    public final int component3() {
        return this.vote;
    }

    public final FormC1TabulationCandidateVote copy(String idImage, int i, int i2) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1TabulationCandidateVote(idImage, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationCandidateVote) {
            FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj;
            return Intrinsics.areEqual(this.idImage, formC1TabulationCandidateVote.idImage) && this.index == formC1TabulationCandidateVote.index && this.vote == formC1TabulationCandidateVote.vote;
        }
        return false;
    }

    public int hashCode() {
        return (((this.idImage.hashCode() * 31) + Integer.hashCode(this.index)) * 31) + Integer.hashCode(this.vote);
    }

    public String toString() {
        String str = this.idImage;
        int i = this.index;
        return "FormC1TabulationCandidateVote(idImage=" + str + ", index=" + i + ", vote=" + this.vote + ")";
    }

    public FormC1TabulationCandidateVote(String idImage, int i, int i2) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.index = i;
        this.vote = i2;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getVote() {
        return this.vote;
    }

    /* compiled from: FormC1Tabulation.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004J&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationCandidateVote$Companion;", "", "()V", "createEmpty", "", "Lorg/informatika/sirekap/model/FormC1TabulationCandidateVote;", "numCandidates", "", "idImage", "", TypedValues.CycleType.S_WAVE_OFFSET, "candidateVote", "createFromFormC1AdministrationHal2", "payload", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload;", "createFromFormC1TabulationPayload", "Lorg/informatika/sirekap/model/FormC1TabulationPayload;", "createFromFormC1TabulationPayloadPartai", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ List createFromFormC1TabulationPayload$default(Companion companion, FormC1TabulationPayload formC1TabulationPayload, String str, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = 0;
            }
            return companion.createFromFormC1TabulationPayload(formC1TabulationPayload, str, i);
        }

        public final List<FormC1TabulationCandidateVote> createFromFormC1TabulationPayload(FormC1TabulationPayload payload, String idImage, int i) {
            List<List<Integer>> predComb;
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            FormC1TabulationPayload.FormC1TabulationPayloadSuaraPaslon suaraPaslon = payload.getSuaraPaslon();
            if (suaraPaslon == null || (predComb = suaraPaslon.getPredComb()) == null) {
                return CollectionsKt.emptyList();
            }
            List<List<Integer>> list = predComb;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i2 = 0;
            for (Object obj : list) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(new FormC1TabulationCandidateVote(idImage, i2 + i, ElectionUtil.joinThreeNumbers((List) obj)));
                i2 = i3;
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ List createEmpty$default(Companion companion, int i, String str, int i2, List list, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i2 = 0;
            }
            if ((i3 & 8) != 0) {
                list = null;
            }
            return companion.createEmpty(i, str, i2, list);
        }

        public final List<FormC1TabulationCandidateVote> createEmpty(int i, String idImage, int i2, List<Integer> list) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            int i3 = 0;
            if (list != null) {
                List<Integer> list2 = list;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (Object obj : list2) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    arrayList.add(new FormC1TabulationCandidateVote(idImage, i3 + i2, ((Number) obj).intValue()));
                    i3 = i4;
                }
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(i);
            for (int i5 = 0; i5 < i; i5++) {
                arrayList2.add(0);
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            for (Object obj2 : arrayList3) {
                int i6 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList4.add(new FormC1TabulationCandidateVote(idImage, i3 + i2, ((Number) obj2).intValue()));
                i3 = i6;
            }
            return arrayList4;
        }

        public static /* synthetic */ List createFromFormC1TabulationPayloadPartai$default(Companion companion, FormC1TabulationPayload formC1TabulationPayload, String str, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = 0;
            }
            return companion.createFromFormC1TabulationPayloadPartai(formC1TabulationPayload, str, i);
        }

        public final List<FormC1TabulationCandidateVote> createFromFormC1TabulationPayloadPartai(FormC1TabulationPayload payload, String idImage, int i) {
            ArrayList emptyList;
            List<List<Integer>> predComb;
            List<Integer> list;
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            ArrayList arrayList = new ArrayList();
            FormC1TabulationPayload.FormC1TabulationPayloadDataSuara dataSuara = payload.getDataSuara();
            List<List<Integer>> predComb2 = dataSuara != null ? dataSuara.getPredComb() : null;
            int i2 = 0;
            if (predComb2 != null && (list = predComb2.get(0)) != null) {
                arrayList.add(new FormC1TabulationCandidateVote(idImage, i, Integer.valueOf(ElectionUtil.joinThreeNumbers(list)).intValue()));
            }
            FormC1TabulationPayload.FormC1TabulationPayloadSuaraPaslon suaraPaslon = payload.getSuaraPaslon();
            if (suaraPaslon == null || (predComb = suaraPaslon.getPredComb()) == null) {
                emptyList = CollectionsKt.emptyList();
            } else {
                List<List<Integer>> list2 = predComb;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (Object obj : list2) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    arrayList2.add(new FormC1TabulationCandidateVote(idImage, i2 + i + 1, ElectionUtil.joinThreeNumbers((List) obj)));
                    i2 = i3;
                }
                emptyList = arrayList2;
            }
            arrayList.addAll(emptyList);
            return arrayList;
        }

        public static /* synthetic */ List createFromFormC1AdministrationHal2$default(Companion companion, FormC1AdministrationHal2Payload formC1AdministrationHal2Payload, String str, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = 3;
            }
            return companion.createFromFormC1AdministrationHal2(formC1AdministrationHal2Payload, str, i);
        }

        public final List<FormC1TabulationCandidateVote> createFromFormC1AdministrationHal2(FormC1AdministrationHal2Payload payload, String idImage, int i) {
            ArrayList emptyList;
            List<List<Integer>> predComb;
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            ArrayList arrayList = new ArrayList();
            FormC1AdministrationHal2Payload.FormC1AdministrationHal2PayloadSuaraPaslon suaraPaslon = payload.getSuaraPaslon();
            if (suaraPaslon == null || (predComb = suaraPaslon.getPredComb()) == null) {
                emptyList = CollectionsKt.emptyList();
            } else {
                List<List<Integer>> list = predComb;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                int i2 = 0;
                for (Object obj : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    arrayList2.add(new FormC1TabulationCandidateVote(idImage, i2 + i, ElectionUtil.joinThreeNumbers((List) obj)));
                    i2 = i3;
                }
                emptyList = arrayList2;
            }
            arrayList.addAll(emptyList);
            return arrayList;
        }
    }
}
