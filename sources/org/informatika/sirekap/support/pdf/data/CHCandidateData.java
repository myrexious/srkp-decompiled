package org.informatika.sirekap.support.pdf.data;

import android.content.Context;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.WitnessWithShare;
import org.tensorflow.lite.schema.BuiltinOperator;

/* compiled from: CHCandidateData.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003JG\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\t2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\nHÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/CHCandidateData;", "", "sectionI", "Lorg/informatika/sirekap/support/pdf/data/VotersData;", "sectionII", "Lorg/informatika/sirekap/support/pdf/data/BallotData;", "sectionIII", "Lorg/informatika/sirekap/support/pdf/data/DisabilityVotersData;", "sectionIV", "", "", "sectionV", "Lorg/informatika/sirekap/support/pdf/data/ValidAndInvalidData;", "(Lorg/informatika/sirekap/support/pdf/data/VotersData;Lorg/informatika/sirekap/support/pdf/data/BallotData;Lorg/informatika/sirekap/support/pdf/data/DisabilityVotersData;Ljava/util/List;Lorg/informatika/sirekap/support/pdf/data/ValidAndInvalidData;)V", "getSectionI", "()Lorg/informatika/sirekap/support/pdf/data/VotersData;", "getSectionII", "()Lorg/informatika/sirekap/support/pdf/data/BallotData;", "getSectionIII", "()Lorg/informatika/sirekap/support/pdf/data/DisabilityVotersData;", "getSectionIV", "()Ljava/util/List;", "getSectionV", "()Lorg/informatika/sirekap/support/pdf/data/ValidAndInvalidData;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CHCandidateData {
    public static final Companion Companion = new Companion(null);
    private final VotersData sectionI;
    private final BallotData sectionII;
    private final DisabilityVotersData sectionIII;
    private final List<List<Integer>> sectionIV;
    private final ValidAndInvalidData sectionV;

    public static /* synthetic */ CHCandidateData copy$default(CHCandidateData cHCandidateData, VotersData votersData, BallotData ballotData, DisabilityVotersData disabilityVotersData, List list, ValidAndInvalidData validAndInvalidData, int i, Object obj) {
        if ((i & 1) != 0) {
            votersData = cHCandidateData.sectionI;
        }
        if ((i & 2) != 0) {
            ballotData = cHCandidateData.sectionII;
        }
        BallotData ballotData2 = ballotData;
        if ((i & 4) != 0) {
            disabilityVotersData = cHCandidateData.sectionIII;
        }
        DisabilityVotersData disabilityVotersData2 = disabilityVotersData;
        List<List<Integer>> list2 = list;
        if ((i & 8) != 0) {
            list2 = cHCandidateData.sectionIV;
        }
        List list3 = list2;
        if ((i & 16) != 0) {
            validAndInvalidData = cHCandidateData.sectionV;
        }
        return cHCandidateData.copy(votersData, ballotData2, disabilityVotersData2, list3, validAndInvalidData);
    }

    public final VotersData component1() {
        return this.sectionI;
    }

    public final BallotData component2() {
        return this.sectionII;
    }

    public final DisabilityVotersData component3() {
        return this.sectionIII;
    }

    public final List<List<Integer>> component4() {
        return this.sectionIV;
    }

    public final ValidAndInvalidData component5() {
        return this.sectionV;
    }

    public final CHCandidateData copy(VotersData sectionI, BallotData sectionII, DisabilityVotersData sectionIII, List<? extends List<Integer>> sectionIV, ValidAndInvalidData sectionV) {
        Intrinsics.checkNotNullParameter(sectionI, "sectionI");
        Intrinsics.checkNotNullParameter(sectionII, "sectionII");
        Intrinsics.checkNotNullParameter(sectionIII, "sectionIII");
        Intrinsics.checkNotNullParameter(sectionIV, "sectionIV");
        Intrinsics.checkNotNullParameter(sectionV, "sectionV");
        return new CHCandidateData(sectionI, sectionII, sectionIII, sectionIV, sectionV);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CHCandidateData) {
            CHCandidateData cHCandidateData = (CHCandidateData) obj;
            return Intrinsics.areEqual(this.sectionI, cHCandidateData.sectionI) && Intrinsics.areEqual(this.sectionII, cHCandidateData.sectionII) && Intrinsics.areEqual(this.sectionIII, cHCandidateData.sectionIII) && Intrinsics.areEqual(this.sectionIV, cHCandidateData.sectionIV) && Intrinsics.areEqual(this.sectionV, cHCandidateData.sectionV);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.sectionI.hashCode() * 31) + this.sectionII.hashCode()) * 31) + this.sectionIII.hashCode()) * 31) + this.sectionIV.hashCode()) * 31) + this.sectionV.hashCode();
    }

    public String toString() {
        VotersData votersData = this.sectionI;
        BallotData ballotData = this.sectionII;
        DisabilityVotersData disabilityVotersData = this.sectionIII;
        List<List<Integer>> list = this.sectionIV;
        return "CHCandidateData(sectionI=" + votersData + ", sectionII=" + ballotData + ", sectionIII=" + disabilityVotersData + ", sectionIV=" + list + ", sectionV=" + this.sectionV + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CHCandidateData(VotersData sectionI, BallotData sectionII, DisabilityVotersData sectionIII, List<? extends List<Integer>> sectionIV, ValidAndInvalidData sectionV) {
        Intrinsics.checkNotNullParameter(sectionI, "sectionI");
        Intrinsics.checkNotNullParameter(sectionII, "sectionII");
        Intrinsics.checkNotNullParameter(sectionIII, "sectionIII");
        Intrinsics.checkNotNullParameter(sectionIV, "sectionIV");
        Intrinsics.checkNotNullParameter(sectionV, "sectionV");
        this.sectionI = sectionI;
        this.sectionII = sectionII;
        this.sectionIII = sectionIII;
        this.sectionIV = sectionIV;
        this.sectionV = sectionV;
    }

    public final VotersData getSectionI() {
        return this.sectionI;
    }

    public final BallotData getSectionII() {
        return this.sectionII;
    }

    public final DisabilityVotersData getSectionIII() {
        return this.sectionIII;
    }

    public final List<List<Integer>> getSectionIV() {
        return this.sectionIV;
    }

    public final ValidAndInvalidData getSectionV() {
        return this.sectionV;
    }

    /* compiled from: CHCandidateData.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J,\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\u0006\u0010\u0015\u001a\u00020\u0016¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/CHCandidateData$Companion;", "", "()V", "fromAppModels", "Lorg/informatika/sirekap/support/pdf/data/CHCandidateData;", "context", "Landroid/content/Context;", "formC1AdministrationComplete", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "formC1TabulationCompletesSectionIV", "", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "formC1TabulationPartaiCompletesSectionIV", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "formC1TabulationCompleteSectionV", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "electionWithRelation", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getDummy", "witnesses", "Lorg/informatika/sirekap/model/WitnessWithShare;", "tempatPenetapan", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:132:0x0086, code lost:
            if (r0.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:135:0x008f, code lost:
            if (r0.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x0093, code lost:
            r0 = 14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:156:0x00f6, code lost:
            if (r11 != null) goto L24;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final org.informatika.sirekap.support.pdf.data.CHCandidateData fromAppModels(android.content.Context r17, org.informatika.sirekap.model.FormC1AdministrationComplete r18, java.util.List<org.informatika.sirekap.model.FormC1TabulationComplete> r19, java.util.List<org.informatika.sirekap.model.FormC1TabulationPartaiComplete> r20, org.informatika.sirekap.model.FormC1AdministrationHal2Complete r21, org.informatika.sirekap.model.ElectionWithRelation r22) {
            /*
                Method dump skipped, instructions count: 726
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.pdf.data.CHCandidateData.Companion.fromAppModels(android.content.Context, org.informatika.sirekap.model.FormC1AdministrationComplete, java.util.List, java.util.List, org.informatika.sirekap.model.FormC1AdministrationHal2Complete, org.informatika.sirekap.model.ElectionWithRelation):org.informatika.sirekap.support.pdf.data.CHCandidateData");
        }

        public final CHCandidateData getDummy(Context context, ElectionWithRelation electionWithRelation, List<WitnessWithShare> witnesses, String tempatPenetapan) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(electionWithRelation, "electionWithRelation");
            Intrinsics.checkNotNullParameter(witnesses, "witnesses");
            Intrinsics.checkNotNullParameter(tempatPenetapan, "tempatPenetapan");
            Calendar.getInstance();
            ArrayList arrayList = new ArrayList();
            if (Intrinsics.areEqual(electionWithRelation.getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.addAll(CollectionsKt.listOf(CollectionsKt.mutableListOf(61, 59, 60, 73, 45)));
            }
            return new CHCandidateData(new VotersData(new VotersBasedGenderCount(143, 157, 300), new VotersBasedGenderCount(133, BuiltinOperator.MULTINOMIAL, 282), new VotersBasedGenderCount(7, 4, 11), new VotersBasedGenderCount(3, 4, 7), new VotersBasedGenderCount(143, 157, 300)), new BallotData(306, 2, 4, 300), new DisabilityVotersData(new VotersBasedGenderCount(3, 5, 8)), arrayList, new ValidAndInvalidData(298, 2, 300));
        }
    }
}
