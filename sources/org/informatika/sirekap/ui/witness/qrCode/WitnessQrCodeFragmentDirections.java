package org.informatika.sirekap.ui.witness.qrCode;

import android.os.Bundle;
import androidx.navigation.NavDirections;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.MainNavGraphDirections;
import org.informatika.sirekap.R;

/* compiled from: WitnessQrCodeFragmentDirections.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentDirections;", "", "()V", "ActionWitnessQrCodeFragmentToWitnessUrlListFragment", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessQrCodeFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* compiled from: WitnessQrCodeFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentDirections$ActionWitnessQrCodeFragmentToWitnessUrlListFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getJenisPemilihan", "()Ljava/lang/String;", "getKodeTps", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class ActionWitnessQrCodeFragmentToWitnessUrlListFragment implements NavDirections {
        private final int actionId;
        private final String jenisPemilihan;
        private final String kodeTps;

        public static /* synthetic */ ActionWitnessQrCodeFragmentToWitnessUrlListFragment copy$default(ActionWitnessQrCodeFragmentToWitnessUrlListFragment actionWitnessQrCodeFragmentToWitnessUrlListFragment, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionWitnessQrCodeFragmentToWitnessUrlListFragment.kodeTps;
            }
            if ((i & 2) != 0) {
                str2 = actionWitnessQrCodeFragmentToWitnessUrlListFragment.jenisPemilihan;
            }
            return actionWitnessQrCodeFragmentToWitnessUrlListFragment.copy(str, str2);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final String component2() {
            return this.jenisPemilihan;
        }

        public final ActionWitnessQrCodeFragmentToWitnessUrlListFragment copy(String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionWitnessQrCodeFragmentToWitnessUrlListFragment(kodeTps, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionWitnessQrCodeFragmentToWitnessUrlListFragment) {
                ActionWitnessQrCodeFragmentToWitnessUrlListFragment actionWitnessQrCodeFragmentToWitnessUrlListFragment = (ActionWitnessQrCodeFragmentToWitnessUrlListFragment) obj;
                return Intrinsics.areEqual(this.kodeTps, actionWitnessQrCodeFragmentToWitnessUrlListFragment.kodeTps) && Intrinsics.areEqual(this.jenisPemilihan, actionWitnessQrCodeFragmentToWitnessUrlListFragment.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (this.kodeTps.hashCode() * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.kodeTps;
            return "ActionWitnessQrCodeFragmentToWitnessUrlListFragment(kodeTps=" + str + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public ActionWitnessQrCodeFragmentToWitnessUrlListFragment(String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.kodeTps = kodeTps;
            this.jenisPemilihan = jenisPemilihan;
            this.actionId = R.id.action_witnessQrCodeFragment_to_witnessUrlListFragment;
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

    private WitnessQrCodeFragmentDirections() {
    }

    /* compiled from: WitnessQrCodeFragmentDirections.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentDirections$Companion;", "", "()V", "actionGlobalLoginNavGraph", "Landroidx/navigation/NavDirections;", "actionGlobalPreviewImageFragment", "imageFilePath", "", "actionWitnessQrCodeFragmentToWitnessUrlListFragment", "kodeTps", "jenisPemilihan", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavDirections actionWitnessQrCodeFragmentToWitnessUrlListFragment(String kodeTps, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new ActionWitnessQrCodeFragmentToWitnessUrlListFragment(kodeTps, jenisPemilihan);
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
