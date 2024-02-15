package org.informatika.sirekap.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.MainNavGraphDirections;
import org.informatika.sirekap.R;

/* compiled from: DashboardFragmentDirections.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\u0018\u0000 \r2\u00020\u0001:\u000b\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections;", "", "()V", "ActionDashboardFragmentToAutoCaptureFragment", "ActionDashboardFragmentToConfirmSaveFormCImageFragment", "ActionDashboardFragmentToElectionDetailFragment", "ActionDashboardFragmentToManageTimeFragment", "ActionDashboardFragmentToSelectFormCImageFragment", "ActionDashboardFragmentToSendImageFragment", "ActionDashboardFragmentToSettingsFragment", "ActionDashboardFragmentToSpecialOccurrenceFragment", "ActionDashboardFragmentToWitnessAttendanceListFragment", "ActionDashboardFragmentToWitnessFragment", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DashboardFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToElectionDetailFragment;", "Landroidx/navigation/NavDirections;", "electionId", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionId", "()Ljava/lang/String;", "getJenisPemilihan", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToElectionDetailFragment implements NavDirections {
        private final int actionId;
        private final String electionId;
        private final String jenisPemilihan;

        public static /* synthetic */ ActionDashboardFragmentToElectionDetailFragment copy$default(ActionDashboardFragmentToElectionDetailFragment actionDashboardFragmentToElectionDetailFragment, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToElectionDetailFragment.electionId;
            }
            if ((i & 2) != 0) {
                str2 = actionDashboardFragmentToElectionDetailFragment.jenisPemilihan;
            }
            return actionDashboardFragmentToElectionDetailFragment.copy(str, str2);
        }

        public final String component1() {
            return this.electionId;
        }

        public final String component2() {
            return this.jenisPemilihan;
        }

        public final ActionDashboardFragmentToElectionDetailFragment copy(String electionId, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionDashboardFragmentToElectionDetailFragment(electionId, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionDashboardFragmentToElectionDetailFragment) {
                ActionDashboardFragmentToElectionDetailFragment actionDashboardFragmentToElectionDetailFragment = (ActionDashboardFragmentToElectionDetailFragment) obj;
                return Intrinsics.areEqual(this.electionId, actionDashboardFragmentToElectionDetailFragment.electionId) && Intrinsics.areEqual(this.jenisPemilihan, actionDashboardFragmentToElectionDetailFragment.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (this.electionId.hashCode() * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.electionId;
            return "ActionDashboardFragmentToElectionDetailFragment(electionId=" + str + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public ActionDashboardFragmentToElectionDetailFragment(String electionId, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.electionId = electionId;
            this.jenisPemilihan = jenisPemilihan;
            this.actionId = R.id.action_dashboardFragment_to_electionDetailFragment;
        }

        public final String getElectionId() {
            return this.electionId;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("electionId", this.electionId);
            bundle.putString("jenisPemilihan", this.jenisPemilihan);
            return bundle;
        }
    }

    private DashboardFragmentDirections() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToWitnessFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getJenisPemilihan", "()Ljava/lang/String;", "getKodeTps", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToWitnessFragment implements NavDirections {
        private final int actionId;
        private final String jenisPemilihan;
        private final String kodeTps;

        public static /* synthetic */ ActionDashboardFragmentToWitnessFragment copy$default(ActionDashboardFragmentToWitnessFragment actionDashboardFragmentToWitnessFragment, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToWitnessFragment.kodeTps;
            }
            if ((i & 2) != 0) {
                str2 = actionDashboardFragmentToWitnessFragment.jenisPemilihan;
            }
            return actionDashboardFragmentToWitnessFragment.copy(str, str2);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final String component2() {
            return this.jenisPemilihan;
        }

        public final ActionDashboardFragmentToWitnessFragment copy(String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionDashboardFragmentToWitnessFragment(kodeTps, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionDashboardFragmentToWitnessFragment) {
                ActionDashboardFragmentToWitnessFragment actionDashboardFragmentToWitnessFragment = (ActionDashboardFragmentToWitnessFragment) obj;
                return Intrinsics.areEqual(this.kodeTps, actionDashboardFragmentToWitnessFragment.kodeTps) && Intrinsics.areEqual(this.jenisPemilihan, actionDashboardFragmentToWitnessFragment.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (this.kodeTps.hashCode() * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.kodeTps;
            return "ActionDashboardFragmentToWitnessFragment(kodeTps=" + str + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public ActionDashboardFragmentToWitnessFragment(String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.kodeTps = kodeTps;
            this.jenisPemilihan = jenisPemilihan;
            this.actionId = R.id.action_dashboardFragment_to_witnessFragment;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("kodeTps", this.kodeTps);
            bundle.putString("jenisPemilihan", this.jenisPemilihan);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToSendImageFragment;", "Landroidx/navigation/NavDirections;", "electionPageId", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToSendImageFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;

        public static /* synthetic */ ActionDashboardFragmentToSendImageFragment copy$default(ActionDashboardFragmentToSendImageFragment actionDashboardFragmentToSendImageFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToSendImageFragment.electionPageId;
            }
            return actionDashboardFragmentToSendImageFragment.copy(str);
        }

        public final String component1() {
            return this.electionPageId;
        }

        public final ActionDashboardFragmentToSendImageFragment copy(String electionPageId) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionDashboardFragmentToSendImageFragment(electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionDashboardFragmentToSendImageFragment) && Intrinsics.areEqual(this.electionPageId, ((ActionDashboardFragmentToSendImageFragment) obj).electionPageId);
        }

        public int hashCode() {
            return this.electionPageId.hashCode();
        }

        public String toString() {
            return "ActionDashboardFragmentToSendImageFragment(electionPageId=" + this.electionPageId + ")";
        }

        public ActionDashboardFragmentToSendImageFragment(String electionPageId) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_dashboardFragment_to_sendImageFragment;
        }

        public final String getElectionPageId() {
            return this.electionPageId;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("electionPageId", this.electionPageId);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToWitnessAttendanceListFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getKodeTps", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToWitnessAttendanceListFragment implements NavDirections {
        private final int actionId;
        private final String kodeTps;

        public static /* synthetic */ ActionDashboardFragmentToWitnessAttendanceListFragment copy$default(ActionDashboardFragmentToWitnessAttendanceListFragment actionDashboardFragmentToWitnessAttendanceListFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToWitnessAttendanceListFragment.kodeTps;
            }
            return actionDashboardFragmentToWitnessAttendanceListFragment.copy(str);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final ActionDashboardFragmentToWitnessAttendanceListFragment copy(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionDashboardFragmentToWitnessAttendanceListFragment(kodeTps);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionDashboardFragmentToWitnessAttendanceListFragment) && Intrinsics.areEqual(this.kodeTps, ((ActionDashboardFragmentToWitnessAttendanceListFragment) obj).kodeTps);
        }

        public int hashCode() {
            return this.kodeTps.hashCode();
        }

        public String toString() {
            return "ActionDashboardFragmentToWitnessAttendanceListFragment(kodeTps=" + this.kodeTps + ")";
        }

        public ActionDashboardFragmentToWitnessAttendanceListFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            this.kodeTps = kodeTps;
            this.actionId = R.id.action_dashboardFragment_to_witnessAttendanceListFragment;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("kodeTps", this.kodeTps);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToSpecialOccurrenceFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getKodeTps", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToSpecialOccurrenceFragment implements NavDirections {
        private final int actionId;
        private final String kodeTps;

        public static /* synthetic */ ActionDashboardFragmentToSpecialOccurrenceFragment copy$default(ActionDashboardFragmentToSpecialOccurrenceFragment actionDashboardFragmentToSpecialOccurrenceFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToSpecialOccurrenceFragment.kodeTps;
            }
            return actionDashboardFragmentToSpecialOccurrenceFragment.copy(str);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final ActionDashboardFragmentToSpecialOccurrenceFragment copy(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionDashboardFragmentToSpecialOccurrenceFragment(kodeTps);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionDashboardFragmentToSpecialOccurrenceFragment) && Intrinsics.areEqual(this.kodeTps, ((ActionDashboardFragmentToSpecialOccurrenceFragment) obj).kodeTps);
        }

        public int hashCode() {
            return this.kodeTps.hashCode();
        }

        public String toString() {
            return "ActionDashboardFragmentToSpecialOccurrenceFragment(kodeTps=" + this.kodeTps + ")";
        }

        public ActionDashboardFragmentToSpecialOccurrenceFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            this.kodeTps = kodeTps;
            this.actionId = R.id.action_dashboardFragment_to_specialOccurrenceFragment;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("kodeTps", this.kodeTps);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToAutoCaptureFragment;", "Landroidx/navigation/NavDirections;", "imageUri", "Landroid/net/Uri;", "croppedImageUri", "correctedImageUri", "(Landroid/net/Uri;Landroid/net/Uri;Landroid/net/Uri;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getCorrectedImageUri", "()Landroid/net/Uri;", "getCroppedImageUri", "getImageUri", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToAutoCaptureFragment implements NavDirections {
        private final int actionId;
        private final Uri correctedImageUri;
        private final Uri croppedImageUri;
        private final Uri imageUri;

        public static /* synthetic */ ActionDashboardFragmentToAutoCaptureFragment copy$default(ActionDashboardFragmentToAutoCaptureFragment actionDashboardFragmentToAutoCaptureFragment, Uri uri, Uri uri2, Uri uri3, int i, Object obj) {
            if ((i & 1) != 0) {
                uri = actionDashboardFragmentToAutoCaptureFragment.imageUri;
            }
            if ((i & 2) != 0) {
                uri2 = actionDashboardFragmentToAutoCaptureFragment.croppedImageUri;
            }
            if ((i & 4) != 0) {
                uri3 = actionDashboardFragmentToAutoCaptureFragment.correctedImageUri;
            }
            return actionDashboardFragmentToAutoCaptureFragment.copy(uri, uri2, uri3);
        }

        public final Uri component1() {
            return this.imageUri;
        }

        public final Uri component2() {
            return this.croppedImageUri;
        }

        public final Uri component3() {
            return this.correctedImageUri;
        }

        public final ActionDashboardFragmentToAutoCaptureFragment copy(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
            Intrinsics.checkNotNullParameter(imageUri, "imageUri");
            Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
            Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
            return new ActionDashboardFragmentToAutoCaptureFragment(imageUri, croppedImageUri, correctedImageUri);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionDashboardFragmentToAutoCaptureFragment) {
                ActionDashboardFragmentToAutoCaptureFragment actionDashboardFragmentToAutoCaptureFragment = (ActionDashboardFragmentToAutoCaptureFragment) obj;
                return Intrinsics.areEqual(this.imageUri, actionDashboardFragmentToAutoCaptureFragment.imageUri) && Intrinsics.areEqual(this.croppedImageUri, actionDashboardFragmentToAutoCaptureFragment.croppedImageUri) && Intrinsics.areEqual(this.correctedImageUri, actionDashboardFragmentToAutoCaptureFragment.correctedImageUri);
            }
            return false;
        }

        public int hashCode() {
            return (((this.imageUri.hashCode() * 31) + this.croppedImageUri.hashCode()) * 31) + this.correctedImageUri.hashCode();
        }

        public String toString() {
            Uri uri = this.imageUri;
            Uri uri2 = this.croppedImageUri;
            return "ActionDashboardFragmentToAutoCaptureFragment(imageUri=" + uri + ", croppedImageUri=" + uri2 + ", correctedImageUri=" + this.correctedImageUri + ")";
        }

        public ActionDashboardFragmentToAutoCaptureFragment(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
            Intrinsics.checkNotNullParameter(imageUri, "imageUri");
            Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
            Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
            this.imageUri = imageUri;
            this.croppedImageUri = croppedImageUri;
            this.correctedImageUri = correctedImageUri;
            this.actionId = R.id.action_dashboardFragment_to_autoCaptureFragment;
        }

        public final Uri getImageUri() {
            return this.imageUri;
        }

        public final Uri getCroppedImageUri() {
            return this.croppedImageUri;
        }

        public final Uri getCorrectedImageUri() {
            return this.correctedImageUri;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (Parcelable.class.isAssignableFrom(Uri.class)) {
                Uri uri = this.imageUri;
                Intrinsics.checkNotNull(uri, "null cannot be cast to non-null type android.os.Parcelable");
                bundle.putParcelable("imageUri", uri);
            } else if (Serializable.class.isAssignableFrom(Uri.class)) {
                Uri uri2 = this.imageUri;
                Intrinsics.checkNotNull(uri2, "null cannot be cast to non-null type java.io.Serializable");
                bundle.putSerializable("imageUri", (Serializable) uri2);
            } else {
                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            if (Parcelable.class.isAssignableFrom(Uri.class)) {
                Uri uri3 = this.croppedImageUri;
                Intrinsics.checkNotNull(uri3, "null cannot be cast to non-null type android.os.Parcelable");
                bundle.putParcelable("croppedImageUri", uri3);
            } else if (Serializable.class.isAssignableFrom(Uri.class)) {
                Uri uri4 = this.croppedImageUri;
                Intrinsics.checkNotNull(uri4, "null cannot be cast to non-null type java.io.Serializable");
                bundle.putSerializable("croppedImageUri", (Serializable) uri4);
            } else {
                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            if (Parcelable.class.isAssignableFrom(Uri.class)) {
                Uri uri5 = this.correctedImageUri;
                Intrinsics.checkNotNull(uri5, "null cannot be cast to non-null type android.os.Parcelable");
                bundle.putParcelable("correctedImageUri", uri5);
            } else if (Serializable.class.isAssignableFrom(Uri.class)) {
                Uri uri6 = this.correctedImageUri;
                Intrinsics.checkNotNull(uri6, "null cannot be cast to non-null type java.io.Serializable");
                bundle.putSerializable("correctedImageUri", (Serializable) uri6);
            } else {
                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToSelectFormCImageFragment;", "Landroidx/navigation/NavDirections;", "imagePath", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getImagePath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToSelectFormCImageFragment implements NavDirections {
        private final int actionId;
        private final String imagePath;

        public static /* synthetic */ ActionDashboardFragmentToSelectFormCImageFragment copy$default(ActionDashboardFragmentToSelectFormCImageFragment actionDashboardFragmentToSelectFormCImageFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToSelectFormCImageFragment.imagePath;
            }
            return actionDashboardFragmentToSelectFormCImageFragment.copy(str);
        }

        public final String component1() {
            return this.imagePath;
        }

        public final ActionDashboardFragmentToSelectFormCImageFragment copy(String imagePath) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            return new ActionDashboardFragmentToSelectFormCImageFragment(imagePath);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionDashboardFragmentToSelectFormCImageFragment) && Intrinsics.areEqual(this.imagePath, ((ActionDashboardFragmentToSelectFormCImageFragment) obj).imagePath);
        }

        public int hashCode() {
            return this.imagePath.hashCode();
        }

        public String toString() {
            return "ActionDashboardFragmentToSelectFormCImageFragment(imagePath=" + this.imagePath + ")";
        }

        public ActionDashboardFragmentToSelectFormCImageFragment(String imagePath) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            this.imagePath = imagePath;
            this.actionId = R.id.action_dashboardFragment_to_selectFormCImageFragment;
        }

        public final String getImagePath() {
            return this.imagePath;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("imagePath", this.imagePath);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToConfirmSaveFormCImageFragment;", "Landroidx/navigation/NavDirections;", "imagePath", "", "electionPageId", "(Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getImagePath", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToConfirmSaveFormCImageFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final String imagePath;

        public static /* synthetic */ ActionDashboardFragmentToConfirmSaveFormCImageFragment copy$default(ActionDashboardFragmentToConfirmSaveFormCImageFragment actionDashboardFragmentToConfirmSaveFormCImageFragment, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToConfirmSaveFormCImageFragment.imagePath;
            }
            if ((i & 2) != 0) {
                str2 = actionDashboardFragmentToConfirmSaveFormCImageFragment.electionPageId;
            }
            return actionDashboardFragmentToConfirmSaveFormCImageFragment.copy(str, str2);
        }

        public final String component1() {
            return this.imagePath;
        }

        public final String component2() {
            return this.electionPageId;
        }

        public final ActionDashboardFragmentToConfirmSaveFormCImageFragment copy(String imagePath, String electionPageId) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionDashboardFragmentToConfirmSaveFormCImageFragment(imagePath, electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionDashboardFragmentToConfirmSaveFormCImageFragment) {
                ActionDashboardFragmentToConfirmSaveFormCImageFragment actionDashboardFragmentToConfirmSaveFormCImageFragment = (ActionDashboardFragmentToConfirmSaveFormCImageFragment) obj;
                return Intrinsics.areEqual(this.imagePath, actionDashboardFragmentToConfirmSaveFormCImageFragment.imagePath) && Intrinsics.areEqual(this.electionPageId, actionDashboardFragmentToConfirmSaveFormCImageFragment.electionPageId);
            }
            return false;
        }

        public int hashCode() {
            return (this.imagePath.hashCode() * 31) + this.electionPageId.hashCode();
        }

        public String toString() {
            String str = this.imagePath;
            return "ActionDashboardFragmentToConfirmSaveFormCImageFragment(imagePath=" + str + ", electionPageId=" + this.electionPageId + ")";
        }

        public ActionDashboardFragmentToConfirmSaveFormCImageFragment(String imagePath, String electionPageId) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.imagePath = imagePath;
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_dashboardFragment_to_confirmSaveFormCImageFragment;
        }

        public final String getImagePath() {
            return this.imagePath;
        }

        public final String getElectionPageId() {
            return this.electionPageId;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("imagePath", this.imagePath);
            bundle.putString("electionPageId", this.electionPageId);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J1\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\n\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToManageTimeFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "jenisWaktu", "", "jenisPemilihan", "isElectionZipped", "", "(Ljava/lang/String;ILjava/lang/String;Z)V", "actionId", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "()Z", "getJenisPemilihan", "()Ljava/lang/String;", "getJenisWaktu", "getKodeTps", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToManageTimeFragment implements NavDirections {
        private final int actionId;
        private final boolean isElectionZipped;
        private final String jenisPemilihan;
        private final int jenisWaktu;
        private final String kodeTps;

        public static /* synthetic */ ActionDashboardFragmentToManageTimeFragment copy$default(ActionDashboardFragmentToManageTimeFragment actionDashboardFragmentToManageTimeFragment, String str, int i, String str2, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = actionDashboardFragmentToManageTimeFragment.kodeTps;
            }
            if ((i2 & 2) != 0) {
                i = actionDashboardFragmentToManageTimeFragment.jenisWaktu;
            }
            if ((i2 & 4) != 0) {
                str2 = actionDashboardFragmentToManageTimeFragment.jenisPemilihan;
            }
            if ((i2 & 8) != 0) {
                z = actionDashboardFragmentToManageTimeFragment.isElectionZipped;
            }
            return actionDashboardFragmentToManageTimeFragment.copy(str, i, str2, z);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final int component2() {
            return this.jenisWaktu;
        }

        public final String component3() {
            return this.jenisPemilihan;
        }

        public final boolean component4() {
            return this.isElectionZipped;
        }

        public final ActionDashboardFragmentToManageTimeFragment copy(String kodeTps, int i, String jenisPemilihan, boolean z) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionDashboardFragmentToManageTimeFragment(kodeTps, i, jenisPemilihan, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionDashboardFragmentToManageTimeFragment) {
                ActionDashboardFragmentToManageTimeFragment actionDashboardFragmentToManageTimeFragment = (ActionDashboardFragmentToManageTimeFragment) obj;
                return Intrinsics.areEqual(this.kodeTps, actionDashboardFragmentToManageTimeFragment.kodeTps) && this.jenisWaktu == actionDashboardFragmentToManageTimeFragment.jenisWaktu && Intrinsics.areEqual(this.jenisPemilihan, actionDashboardFragmentToManageTimeFragment.jenisPemilihan) && this.isElectionZipped == actionDashboardFragmentToManageTimeFragment.isElectionZipped;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((this.kodeTps.hashCode() * 31) + Integer.hashCode(this.jenisWaktu)) * 31) + this.jenisPemilihan.hashCode()) * 31;
            boolean z = this.isElectionZipped;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public String toString() {
            String str = this.kodeTps;
            int i = this.jenisWaktu;
            String str2 = this.jenisPemilihan;
            return "ActionDashboardFragmentToManageTimeFragment(kodeTps=" + str + ", jenisWaktu=" + i + ", jenisPemilihan=" + str2 + ", isElectionZipped=" + this.isElectionZipped + ")";
        }

        public ActionDashboardFragmentToManageTimeFragment(String kodeTps, int i, String jenisPemilihan, boolean z) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.kodeTps = kodeTps;
            this.jenisWaktu = i;
            this.jenisPemilihan = jenisPemilihan;
            this.isElectionZipped = z;
            this.actionId = R.id.action_dashboardFragment_to_manageTimeFragment;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        public final int getJenisWaktu() {
            return this.jenisWaktu;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        public final boolean isElectionZipped() {
            return this.isElectionZipped;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("kodeTps", this.kodeTps);
            bundle.putInt("jenisWaktu", this.jenisWaktu);
            bundle.putString("jenisPemilihan", this.jenisPemilihan);
            bundle.putBoolean("isElectionZipped", this.isElectionZipped);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$ActionDashboardFragmentToSettingsFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getKodeTps", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionDashboardFragmentToSettingsFragment implements NavDirections {
        private final int actionId;
        private final String kodeTps;

        public static /* synthetic */ ActionDashboardFragmentToSettingsFragment copy$default(ActionDashboardFragmentToSettingsFragment actionDashboardFragmentToSettingsFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionDashboardFragmentToSettingsFragment.kodeTps;
            }
            return actionDashboardFragmentToSettingsFragment.copy(str);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final ActionDashboardFragmentToSettingsFragment copy(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionDashboardFragmentToSettingsFragment(kodeTps);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionDashboardFragmentToSettingsFragment) && Intrinsics.areEqual(this.kodeTps, ((ActionDashboardFragmentToSettingsFragment) obj).kodeTps);
        }

        public int hashCode() {
            return this.kodeTps.hashCode();
        }

        public String toString() {
            return "ActionDashboardFragmentToSettingsFragment(kodeTps=" + this.kodeTps + ")";
        }

        public ActionDashboardFragmentToSettingsFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            this.kodeTps = kodeTps;
            this.actionId = R.id.action_dashboardFragment_to_settingsFragment;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("kodeTps", this.kodeTps);
            return bundle;
        }
    }

    /* compiled from: DashboardFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0006\u0010\u0010\u001a\u00020\u0004J&\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000bJ\u0016\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u000b¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragmentDirections$Companion;", "", "()V", "actionDashboardFragmentToAutoCaptureFragment", "Landroidx/navigation/NavDirections;", "imageUri", "Landroid/net/Uri;", "croppedImageUri", "correctedImageUri", "actionDashboardFragmentToConfirmSaveFormCImageFragment", "imagePath", "", "electionPageId", "actionDashboardFragmentToElectionDetailFragment", "electionId", "jenisPemilihan", "actionDashboardFragmentToImageHistoryFragment", "actionDashboardFragmentToManageTimeFragment", "kodeTps", "jenisWaktu", "", "isElectionZipped", "", "actionDashboardFragmentToSelectFormCImageFragment", "actionDashboardFragmentToSendImageFragment", "actionDashboardFragmentToSettingsFragment", "actionDashboardFragmentToSpecialOccurrenceFragment", "actionDashboardFragmentToWitnessAttendanceListFragment", "actionDashboardFragmentToWitnessFragment", "actionGlobalLoginNavGraph", "actionGlobalPreviewImageFragment", "imageFilePath", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavDirections actionDashboardFragmentToElectionDetailFragment(String electionId, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionDashboardFragmentToElectionDetailFragment(electionId, jenisPemilihan);
        }

        public final NavDirections actionDashboardFragmentToWitnessFragment(String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionDashboardFragmentToWitnessFragment(kodeTps, jenisPemilihan);
        }

        public final NavDirections actionDashboardFragmentToSendImageFragment(String electionPageId) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionDashboardFragmentToSendImageFragment(electionPageId);
        }

        public final NavDirections actionDashboardFragmentToImageHistoryFragment() {
            return new ActionOnlyNavDirections(R.id.action_dashboardFragment_to_imageHistoryFragment);
        }

        public final NavDirections actionDashboardFragmentToWitnessAttendanceListFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionDashboardFragmentToWitnessAttendanceListFragment(kodeTps);
        }

        public final NavDirections actionDashboardFragmentToSpecialOccurrenceFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionDashboardFragmentToSpecialOccurrenceFragment(kodeTps);
        }

        public final NavDirections actionDashboardFragmentToAutoCaptureFragment(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
            Intrinsics.checkNotNullParameter(imageUri, "imageUri");
            Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
            Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
            return new ActionDashboardFragmentToAutoCaptureFragment(imageUri, croppedImageUri, correctedImageUri);
        }

        public final NavDirections actionDashboardFragmentToSelectFormCImageFragment(String imagePath) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            return new ActionDashboardFragmentToSelectFormCImageFragment(imagePath);
        }

        public final NavDirections actionDashboardFragmentToConfirmSaveFormCImageFragment(String imagePath, String electionPageId) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionDashboardFragmentToConfirmSaveFormCImageFragment(imagePath, electionPageId);
        }

        public final NavDirections actionDashboardFragmentToManageTimeFragment(String kodeTps, int i, String jenisPemilihan, boolean z) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionDashboardFragmentToManageTimeFragment(kodeTps, i, jenisPemilihan, z);
        }

        public final NavDirections actionDashboardFragmentToSettingsFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionDashboardFragmentToSettingsFragment(kodeTps);
        }

        public final NavDirections actionGlobalLoginNavGraph() {
            return MainNavGraphDirections.Companion.actionGlobalLoginNavGraph();
        }

        public final NavDirections actionGlobalPreviewImageFragment(String imageFilePath) {
            Intrinsics.checkNotNullParameter(imageFilePath, "imageFilePath");
            return MainNavGraphDirections.Companion.actionGlobalPreviewImageFragment(imageFilePath);
        }
    }
}
