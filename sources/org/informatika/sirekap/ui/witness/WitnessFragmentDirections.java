package org.informatika.sirekap.ui.witness;

import android.os.Bundle;
import androidx.navigation.NavDirections;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.MainNavGraphDirections;
import org.informatika.sirekap.R;

/* compiled from: WitnessFragmentDirections.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00052\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessFragmentDirections;", "", "()V", "ActionWitnessFragmentToWitnessQrCodeFragment", "ActionWitnessFragmentToWitnessRegisterFragment", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WitnessFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessFragmentDirections$ActionWitnessFragmentToWitnessQrCodeFragment;", "Landroidx/navigation/NavDirections;", "witnessId", "", "kodeTps", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getJenisPemilihan", "()Ljava/lang/String;", "getKodeTps", "getWitnessId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionWitnessFragmentToWitnessQrCodeFragment implements NavDirections {
        private final int actionId;
        private final String jenisPemilihan;
        private final String kodeTps;
        private final String witnessId;

        public static /* synthetic */ ActionWitnessFragmentToWitnessQrCodeFragment copy$default(ActionWitnessFragmentToWitnessQrCodeFragment actionWitnessFragmentToWitnessQrCodeFragment, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionWitnessFragmentToWitnessQrCodeFragment.witnessId;
            }
            if ((i & 2) != 0) {
                str2 = actionWitnessFragmentToWitnessQrCodeFragment.kodeTps;
            }
            if ((i & 4) != 0) {
                str3 = actionWitnessFragmentToWitnessQrCodeFragment.jenisPemilihan;
            }
            return actionWitnessFragmentToWitnessQrCodeFragment.copy(str, str2, str3);
        }

        public final String component1() {
            return this.witnessId;
        }

        public final String component2() {
            return this.kodeTps;
        }

        public final String component3() {
            return this.jenisPemilihan;
        }

        public final ActionWitnessFragmentToWitnessQrCodeFragment copy(String witnessId, String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(witnessId, "witnessId");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionWitnessFragmentToWitnessQrCodeFragment(witnessId, kodeTps, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionWitnessFragmentToWitnessQrCodeFragment) {
                ActionWitnessFragmentToWitnessQrCodeFragment actionWitnessFragmentToWitnessQrCodeFragment = (ActionWitnessFragmentToWitnessQrCodeFragment) obj;
                return Intrinsics.areEqual(this.witnessId, actionWitnessFragmentToWitnessQrCodeFragment.witnessId) && Intrinsics.areEqual(this.kodeTps, actionWitnessFragmentToWitnessQrCodeFragment.kodeTps) && Intrinsics.areEqual(this.jenisPemilihan, actionWitnessFragmentToWitnessQrCodeFragment.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (((this.witnessId.hashCode() * 31) + this.kodeTps.hashCode()) * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.witnessId;
            String str2 = this.kodeTps;
            return "ActionWitnessFragmentToWitnessQrCodeFragment(witnessId=" + str + ", kodeTps=" + str2 + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public ActionWitnessFragmentToWitnessQrCodeFragment(String witnessId, String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(witnessId, "witnessId");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.witnessId = witnessId;
            this.kodeTps = kodeTps;
            this.jenisPemilihan = jenisPemilihan;
            this.actionId = R.id.action_witnessFragment_to_witnessQrCodeFragment;
        }

        public final String getWitnessId() {
            return this.witnessId;
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
            bundle.putString("witnessId", this.witnessId);
            bundle.putString("kodeTps", this.kodeTps);
            bundle.putString("jenisPemilihan", this.jenisPemilihan);
            return bundle;
        }
    }

    private WitnessFragmentDirections() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WitnessFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessFragmentDirections$ActionWitnessFragmentToWitnessRegisterFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getKodeTps", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionWitnessFragmentToWitnessRegisterFragment implements NavDirections {
        private final int actionId;
        private final String kodeTps;

        public static /* synthetic */ ActionWitnessFragmentToWitnessRegisterFragment copy$default(ActionWitnessFragmentToWitnessRegisterFragment actionWitnessFragmentToWitnessRegisterFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionWitnessFragmentToWitnessRegisterFragment.kodeTps;
            }
            return actionWitnessFragmentToWitnessRegisterFragment.copy(str);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final ActionWitnessFragmentToWitnessRegisterFragment copy(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionWitnessFragmentToWitnessRegisterFragment(kodeTps);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionWitnessFragmentToWitnessRegisterFragment) && Intrinsics.areEqual(this.kodeTps, ((ActionWitnessFragmentToWitnessRegisterFragment) obj).kodeTps);
        }

        public int hashCode() {
            return this.kodeTps.hashCode();
        }

        public String toString() {
            return "ActionWitnessFragmentToWitnessRegisterFragment(kodeTps=" + this.kodeTps + ")";
        }

        public ActionWitnessFragmentToWitnessRegisterFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            this.kodeTps = kodeTps;
            this.actionId = R.id.action_witnessFragment_to_witnessRegisterFragment;
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

    /* compiled from: WitnessFragmentDirections.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0007¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessFragmentDirections$Companion;", "", "()V", "actionGlobalLoginNavGraph", "Landroidx/navigation/NavDirections;", "actionGlobalPreviewImageFragment", "imageFilePath", "", "actionWitnessFragmentToWitnessQrCodeFragment", "witnessId", "kodeTps", "jenisPemilihan", "actionWitnessFragmentToWitnessRegisterFragment", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavDirections actionWitnessFragmentToWitnessQrCodeFragment(String witnessId, String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(witnessId, "witnessId");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionWitnessFragmentToWitnessQrCodeFragment(witnessId, kodeTps, jenisPemilihan);
        }

        public final NavDirections actionWitnessFragmentToWitnessRegisterFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionWitnessFragmentToWitnessRegisterFragment(kodeTps);
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
