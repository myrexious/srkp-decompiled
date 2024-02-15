package org.informatika.sirekap.ui.electionDetail;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.MainNavGraphDirections;
import org.informatika.sirekap.R;

/* compiled from: ElectionDetailFragmentDirections.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\u0018\u0000 \f2\u00020\u0001:\n\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections;", "", "()V", "ActionElectionDetailFragmentToAprilTagConflictFragment", "ActionElectionDetailFragmentToAutoCaptureFragment", "ActionElectionDetailFragmentToConfirmSaveFormCImageFragment", "ActionElectionDetailFragmentToSendImageFragment", "ActionElectionDetailFragmentToVerifyAdministrationFragment", "ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment", "ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment", "ActionElectionDetailFragmentToVerifyTabulationFragment", "ActionElectionDetailFragmentToVerifyTabulationPartaiFragment", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionDetailFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToVerifyAdministrationFragment;", "Landroidx/navigation/NavDirections;", "idImage", "", "electionPageKind", "", "electionPageId", "(Ljava/lang/String;ILjava/lang/String;)V", "actionId", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "getIdImage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToVerifyAdministrationFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final int electionPageKind;
        private final String idImage;

        public static /* synthetic */ ActionElectionDetailFragmentToVerifyAdministrationFragment copy$default(ActionElectionDetailFragmentToVerifyAdministrationFragment actionElectionDetailFragmentToVerifyAdministrationFragment, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = actionElectionDetailFragmentToVerifyAdministrationFragment.idImage;
            }
            if ((i2 & 2) != 0) {
                i = actionElectionDetailFragmentToVerifyAdministrationFragment.electionPageKind;
            }
            if ((i2 & 4) != 0) {
                str2 = actionElectionDetailFragmentToVerifyAdministrationFragment.electionPageId;
            }
            return actionElectionDetailFragmentToVerifyAdministrationFragment.copy(str, i, str2);
        }

        public final String component1() {
            return this.idImage;
        }

        public final int component2() {
            return this.electionPageKind;
        }

        public final String component3() {
            return this.electionPageId;
        }

        public final ActionElectionDetailFragmentToVerifyAdministrationFragment copy(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToVerifyAdministrationFragment(idImage, i, electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToVerifyAdministrationFragment) {
                ActionElectionDetailFragmentToVerifyAdministrationFragment actionElectionDetailFragmentToVerifyAdministrationFragment = (ActionElectionDetailFragmentToVerifyAdministrationFragment) obj;
                return Intrinsics.areEqual(this.idImage, actionElectionDetailFragmentToVerifyAdministrationFragment.idImage) && this.electionPageKind == actionElectionDetailFragmentToVerifyAdministrationFragment.electionPageKind && Intrinsics.areEqual(this.electionPageId, actionElectionDetailFragmentToVerifyAdministrationFragment.electionPageId);
            }
            return false;
        }

        public int hashCode() {
            return (((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode();
        }

        public String toString() {
            String str = this.idImage;
            int i = this.electionPageKind;
            return "ActionElectionDetailFragmentToVerifyAdministrationFragment(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + this.electionPageId + ")";
        }

        public ActionElectionDetailFragmentToVerifyAdministrationFragment(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.idImage = idImage;
            this.electionPageKind = i;
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_electionDetailFragment_to_verifyAdministrationFragment;
        }

        public final String getIdImage() {
            return this.idImage;
        }

        public final int getElectionPageKind() {
            return this.electionPageKind;
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
            bundle.putString("idImage", this.idImage);
            bundle.putInt("electionPageKind", this.electionPageKind);
            bundle.putString("electionPageId", this.electionPageId);
            return bundle;
        }
    }

    private ElectionDetailFragmentDirections() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\t\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToVerifyTabulationFragment;", "Landroidx/navigation/NavDirections;", "idImage", "", "electionPageKind", "", "electionPageId", "electionPemilihan", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "actionId", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "getElectionPemilihan", "getIdImage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToVerifyTabulationFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final int electionPageKind;
        private final String electionPemilihan;
        private final String idImage;

        public static /* synthetic */ ActionElectionDetailFragmentToVerifyTabulationFragment copy$default(ActionElectionDetailFragmentToVerifyTabulationFragment actionElectionDetailFragmentToVerifyTabulationFragment, String str, int i, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = actionElectionDetailFragmentToVerifyTabulationFragment.idImage;
            }
            if ((i2 & 2) != 0) {
                i = actionElectionDetailFragmentToVerifyTabulationFragment.electionPageKind;
            }
            if ((i2 & 4) != 0) {
                str2 = actionElectionDetailFragmentToVerifyTabulationFragment.electionPageId;
            }
            if ((i2 & 8) != 0) {
                str3 = actionElectionDetailFragmentToVerifyTabulationFragment.electionPemilihan;
            }
            return actionElectionDetailFragmentToVerifyTabulationFragment.copy(str, i, str2, str3);
        }

        public final String component1() {
            return this.idImage;
        }

        public final int component2() {
            return this.electionPageKind;
        }

        public final String component3() {
            return this.electionPageId;
        }

        public final String component4() {
            return this.electionPemilihan;
        }

        public final ActionElectionDetailFragmentToVerifyTabulationFragment copy(String idImage, int i, String electionPageId, String electionPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
            return new ActionElectionDetailFragmentToVerifyTabulationFragment(idImage, i, electionPageId, electionPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToVerifyTabulationFragment) {
                ActionElectionDetailFragmentToVerifyTabulationFragment actionElectionDetailFragmentToVerifyTabulationFragment = (ActionElectionDetailFragmentToVerifyTabulationFragment) obj;
                return Intrinsics.areEqual(this.idImage, actionElectionDetailFragmentToVerifyTabulationFragment.idImage) && this.electionPageKind == actionElectionDetailFragmentToVerifyTabulationFragment.electionPageKind && Intrinsics.areEqual(this.electionPageId, actionElectionDetailFragmentToVerifyTabulationFragment.electionPageId) && Intrinsics.areEqual(this.electionPemilihan, actionElectionDetailFragmentToVerifyTabulationFragment.electionPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode()) * 31) + this.electionPemilihan.hashCode();
        }

        public String toString() {
            String str = this.idImage;
            int i = this.electionPageKind;
            String str2 = this.electionPageId;
            return "ActionElectionDetailFragmentToVerifyTabulationFragment(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + str2 + ", electionPemilihan=" + this.electionPemilihan + ")";
        }

        public ActionElectionDetailFragmentToVerifyTabulationFragment(String idImage, int i, String electionPageId, String electionPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
            this.idImage = idImage;
            this.electionPageKind = i;
            this.electionPageId = electionPageId;
            this.electionPemilihan = electionPemilihan;
            this.actionId = R.id.action_electionDetailFragment_to_verifyTabulationFragment;
        }

        public final String getIdImage() {
            return this.idImage;
        }

        public final int getElectionPageKind() {
            return this.electionPageKind;
        }

        public final String getElectionPageId() {
            return this.electionPageId;
        }

        public final String getElectionPemilihan() {
            return this.electionPemilihan;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("idImage", this.idImage);
            bundle.putInt("electionPageKind", this.electionPageKind);
            bundle.putString("electionPageId", this.electionPageId);
            bundle.putString("electionPemilihan", this.electionPemilihan);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToSendImageFragment;", "Landroidx/navigation/NavDirections;", "electionPageId", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToSendImageFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;

        public static /* synthetic */ ActionElectionDetailFragmentToSendImageFragment copy$default(ActionElectionDetailFragmentToSendImageFragment actionElectionDetailFragmentToSendImageFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionElectionDetailFragmentToSendImageFragment.electionPageId;
            }
            return actionElectionDetailFragmentToSendImageFragment.copy(str);
        }

        public final String component1() {
            return this.electionPageId;
        }

        public final ActionElectionDetailFragmentToSendImageFragment copy(String electionPageId) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToSendImageFragment(electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionElectionDetailFragmentToSendImageFragment) && Intrinsics.areEqual(this.electionPageId, ((ActionElectionDetailFragmentToSendImageFragment) obj).electionPageId);
        }

        public int hashCode() {
            return this.electionPageId.hashCode();
        }

        public String toString() {
            return "ActionElectionDetailFragmentToSendImageFragment(electionPageId=" + this.electionPageId + ")";
        }

        public ActionElectionDetailFragmentToSendImageFragment(String electionPageId) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_electionDetailFragment_to_sendImageFragment;
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
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment;", "Landroidx/navigation/NavDirections;", "idImage", "", "electionPageKind", "", "electionPageId", "(Ljava/lang/String;ILjava/lang/String;)V", "actionId", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "getIdImage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final int electionPageKind;
        private final String idImage;

        public static /* synthetic */ ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment copy$default(ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment actionElectionDetailFragmentToVerifyAdministrationHal2Fragment, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.idImage;
            }
            if ((i2 & 2) != 0) {
                i = actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.electionPageKind;
            }
            if ((i2 & 4) != 0) {
                str2 = actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.electionPageId;
            }
            return actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.copy(str, i, str2);
        }

        public final String component1() {
            return this.idImage;
        }

        public final int component2() {
            return this.electionPageKind;
        }

        public final String component3() {
            return this.electionPageId;
        }

        public final ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment copy(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment(idImage, i, electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment) {
                ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment actionElectionDetailFragmentToVerifyAdministrationHal2Fragment = (ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment) obj;
                return Intrinsics.areEqual(this.idImage, actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.idImage) && this.electionPageKind == actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.electionPageKind && Intrinsics.areEqual(this.electionPageId, actionElectionDetailFragmentToVerifyAdministrationHal2Fragment.electionPageId);
            }
            return false;
        }

        public int hashCode() {
            return (((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode();
        }

        public String toString() {
            String str = this.idImage;
            int i = this.electionPageKind;
            return "ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + this.electionPageId + ")";
        }

        public ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.idImage = idImage;
            this.electionPageKind = i;
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_electionDetailFragment_to_verifyAdministrationHal2Fragment;
        }

        public final String getIdImage() {
            return this.idImage;
        }

        public final int getElectionPageKind() {
            return this.electionPageKind;
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
            bundle.putString("idImage", this.idImage);
            bundle.putInt("electionPageKind", this.electionPageKind);
            bundle.putString("electionPageId", this.electionPageId);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\t\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToVerifyTabulationPartaiFragment;", "Landroidx/navigation/NavDirections;", "idImage", "", "electionPageKind", "", "electionPageId", "electionPemilihan", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "actionId", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "getElectionPemilihan", "getIdImage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToVerifyTabulationPartaiFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final int electionPageKind;
        private final String electionPemilihan;
        private final String idImage;

        public static /* synthetic */ ActionElectionDetailFragmentToVerifyTabulationPartaiFragment copy$default(ActionElectionDetailFragmentToVerifyTabulationPartaiFragment actionElectionDetailFragmentToVerifyTabulationPartaiFragment, String str, int i, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = actionElectionDetailFragmentToVerifyTabulationPartaiFragment.idImage;
            }
            if ((i2 & 2) != 0) {
                i = actionElectionDetailFragmentToVerifyTabulationPartaiFragment.electionPageKind;
            }
            if ((i2 & 4) != 0) {
                str2 = actionElectionDetailFragmentToVerifyTabulationPartaiFragment.electionPageId;
            }
            if ((i2 & 8) != 0) {
                str3 = actionElectionDetailFragmentToVerifyTabulationPartaiFragment.electionPemilihan;
            }
            return actionElectionDetailFragmentToVerifyTabulationPartaiFragment.copy(str, i, str2, str3);
        }

        public final String component1() {
            return this.idImage;
        }

        public final int component2() {
            return this.electionPageKind;
        }

        public final String component3() {
            return this.electionPageId;
        }

        public final String component4() {
            return this.electionPemilihan;
        }

        public final ActionElectionDetailFragmentToVerifyTabulationPartaiFragment copy(String idImage, int i, String electionPageId, String electionPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
            return new ActionElectionDetailFragmentToVerifyTabulationPartaiFragment(idImage, i, electionPageId, electionPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToVerifyTabulationPartaiFragment) {
                ActionElectionDetailFragmentToVerifyTabulationPartaiFragment actionElectionDetailFragmentToVerifyTabulationPartaiFragment = (ActionElectionDetailFragmentToVerifyTabulationPartaiFragment) obj;
                return Intrinsics.areEqual(this.idImage, actionElectionDetailFragmentToVerifyTabulationPartaiFragment.idImage) && this.electionPageKind == actionElectionDetailFragmentToVerifyTabulationPartaiFragment.electionPageKind && Intrinsics.areEqual(this.electionPageId, actionElectionDetailFragmentToVerifyTabulationPartaiFragment.electionPageId) && Intrinsics.areEqual(this.electionPemilihan, actionElectionDetailFragmentToVerifyTabulationPartaiFragment.electionPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode()) * 31) + this.electionPemilihan.hashCode();
        }

        public String toString() {
            String str = this.idImage;
            int i = this.electionPageKind;
            String str2 = this.electionPageId;
            return "ActionElectionDetailFragmentToVerifyTabulationPartaiFragment(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + str2 + ", electionPemilihan=" + this.electionPemilihan + ")";
        }

        public ActionElectionDetailFragmentToVerifyTabulationPartaiFragment(String idImage, int i, String electionPageId, String electionPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
            this.idImage = idImage;
            this.electionPageKind = i;
            this.electionPageId = electionPageId;
            this.electionPemilihan = electionPemilihan;
            this.actionId = R.id.action_electionDetailFragment_to_verifyTabulationPartaiFragment;
        }

        public final String getIdImage() {
            return this.idImage;
        }

        public final int getElectionPageKind() {
            return this.electionPageKind;
        }

        public final String getElectionPageId() {
            return this.electionPageId;
        }

        public final String getElectionPemilihan() {
            return this.electionPemilihan;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("idImage", this.idImage);
            bundle.putInt("electionPageKind", this.electionPageKind);
            bundle.putString("electionPageId", this.electionPageId);
            bundle.putString("electionPemilihan", this.electionPemilihan);
            return bundle;
        }
    }

    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment;", "Landroidx/navigation/NavDirections;", "idImage", "", "electionPageKind", "", "electionPageId", "(Ljava/lang/String;ILjava/lang/String;)V", "actionId", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "getIdImage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final int electionPageKind;
        private final String idImage;

        public static /* synthetic */ ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment copy$default(ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.idImage;
            }
            if ((i2 & 2) != 0) {
                i = actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.electionPageKind;
            }
            if ((i2 & 4) != 0) {
                str2 = actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.electionPageId;
            }
            return actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.copy(str, i, str2);
        }

        public final String component1() {
            return this.idImage;
        }

        public final int component2() {
            return this.electionPageKind;
        }

        public final String component3() {
            return this.electionPageId;
        }

        public final ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment copy(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment(idImage, i, electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment) {
                ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment = (ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment) obj;
                return Intrinsics.areEqual(this.idImage, actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.idImage) && this.electionPageKind == actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.electionPageKind && Intrinsics.areEqual(this.electionPageId, actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment.electionPageId);
            }
            return false;
        }

        public int hashCode() {
            return (((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode();
        }

        public String toString() {
            String str = this.idImage;
            int i = this.electionPageKind;
            return "ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + this.electionPageId + ")";
        }

        public ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.idImage = idImage;
            this.electionPageKind = i;
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_electionDetailFragment_to_verifyAdministrationHal2PpwpFragment;
        }

        public final String getIdImage() {
            return this.idImage;
        }

        public final int getElectionPageKind() {
            return this.electionPageKind;
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
            bundle.putString("idImage", this.idImage);
            bundle.putInt("electionPageKind", this.electionPageKind);
            bundle.putString("electionPageId", this.electionPageId);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToConfirmSaveFormCImageFragment;", "Landroidx/navigation/NavDirections;", "imagePath", "", "electionPageId", "(Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageId", "()Ljava/lang/String;", "getImagePath", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToConfirmSaveFormCImageFragment implements NavDirections {
        private final int actionId;
        private final String electionPageId;
        private final String imagePath;

        public static /* synthetic */ ActionElectionDetailFragmentToConfirmSaveFormCImageFragment copy$default(ActionElectionDetailFragmentToConfirmSaveFormCImageFragment actionElectionDetailFragmentToConfirmSaveFormCImageFragment, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionElectionDetailFragmentToConfirmSaveFormCImageFragment.imagePath;
            }
            if ((i & 2) != 0) {
                str2 = actionElectionDetailFragmentToConfirmSaveFormCImageFragment.electionPageId;
            }
            return actionElectionDetailFragmentToConfirmSaveFormCImageFragment.copy(str, str2);
        }

        public final String component1() {
            return this.imagePath;
        }

        public final String component2() {
            return this.electionPageId;
        }

        public final ActionElectionDetailFragmentToConfirmSaveFormCImageFragment copy(String imagePath, String electionPageId) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToConfirmSaveFormCImageFragment(imagePath, electionPageId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToConfirmSaveFormCImageFragment) {
                ActionElectionDetailFragmentToConfirmSaveFormCImageFragment actionElectionDetailFragmentToConfirmSaveFormCImageFragment = (ActionElectionDetailFragmentToConfirmSaveFormCImageFragment) obj;
                return Intrinsics.areEqual(this.imagePath, actionElectionDetailFragmentToConfirmSaveFormCImageFragment.imagePath) && Intrinsics.areEqual(this.electionPageId, actionElectionDetailFragmentToConfirmSaveFormCImageFragment.electionPageId);
            }
            return false;
        }

        public int hashCode() {
            return (this.imagePath.hashCode() * 31) + this.electionPageId.hashCode();
        }

        public String toString() {
            String str = this.imagePath;
            return "ActionElectionDetailFragmentToConfirmSaveFormCImageFragment(imagePath=" + str + ", electionPageId=" + this.electionPageId + ")";
        }

        public ActionElectionDetailFragmentToConfirmSaveFormCImageFragment(String imagePath, String electionPageId) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.imagePath = imagePath;
            this.electionPageId = electionPageId;
            this.actionId = R.id.action_electionDetailFragment_to_confirmSaveFormCImageFragment;
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
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToAprilTagConflictFragment;", "Landroidx/navigation/NavDirections;", "imagePath", "", "electionPageIdManual", "electionPageIdAprilTag", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getElectionPageIdAprilTag", "()Ljava/lang/String;", "getElectionPageIdManual", "getImagePath", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToAprilTagConflictFragment implements NavDirections {
        private final int actionId;
        private final String electionPageIdAprilTag;
        private final String electionPageIdManual;
        private final String imagePath;

        public static /* synthetic */ ActionElectionDetailFragmentToAprilTagConflictFragment copy$default(ActionElectionDetailFragmentToAprilTagConflictFragment actionElectionDetailFragmentToAprilTagConflictFragment, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionElectionDetailFragmentToAprilTagConflictFragment.imagePath;
            }
            if ((i & 2) != 0) {
                str2 = actionElectionDetailFragmentToAprilTagConflictFragment.electionPageIdManual;
            }
            if ((i & 4) != 0) {
                str3 = actionElectionDetailFragmentToAprilTagConflictFragment.electionPageIdAprilTag;
            }
            return actionElectionDetailFragmentToAprilTagConflictFragment.copy(str, str2, str3);
        }

        public final String component1() {
            return this.imagePath;
        }

        public final String component2() {
            return this.electionPageIdManual;
        }

        public final String component3() {
            return this.electionPageIdAprilTag;
        }

        public final ActionElectionDetailFragmentToAprilTagConflictFragment copy(String imagePath, String electionPageIdManual, String electionPageIdAprilTag) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageIdManual, "electionPageIdManual");
            Intrinsics.checkNotNullParameter(electionPageIdAprilTag, "electionPageIdAprilTag");
            return new ActionElectionDetailFragmentToAprilTagConflictFragment(imagePath, electionPageIdManual, electionPageIdAprilTag);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToAprilTagConflictFragment) {
                ActionElectionDetailFragmentToAprilTagConflictFragment actionElectionDetailFragmentToAprilTagConflictFragment = (ActionElectionDetailFragmentToAprilTagConflictFragment) obj;
                return Intrinsics.areEqual(this.imagePath, actionElectionDetailFragmentToAprilTagConflictFragment.imagePath) && Intrinsics.areEqual(this.electionPageIdManual, actionElectionDetailFragmentToAprilTagConflictFragment.electionPageIdManual) && Intrinsics.areEqual(this.electionPageIdAprilTag, actionElectionDetailFragmentToAprilTagConflictFragment.electionPageIdAprilTag);
            }
            return false;
        }

        public int hashCode() {
            return (((this.imagePath.hashCode() * 31) + this.electionPageIdManual.hashCode()) * 31) + this.electionPageIdAprilTag.hashCode();
        }

        public String toString() {
            String str = this.imagePath;
            String str2 = this.electionPageIdManual;
            return "ActionElectionDetailFragmentToAprilTagConflictFragment(imagePath=" + str + ", electionPageIdManual=" + str2 + ", electionPageIdAprilTag=" + this.electionPageIdAprilTag + ")";
        }

        public ActionElectionDetailFragmentToAprilTagConflictFragment(String imagePath, String electionPageIdManual, String electionPageIdAprilTag) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageIdManual, "electionPageIdManual");
            Intrinsics.checkNotNullParameter(electionPageIdAprilTag, "electionPageIdAprilTag");
            this.imagePath = imagePath;
            this.electionPageIdManual = electionPageIdManual;
            this.electionPageIdAprilTag = electionPageIdAprilTag;
            this.actionId = R.id.action_electionDetailFragment_to_aprilTagConflictFragment;
        }

        public final String getImagePath() {
            return this.imagePath;
        }

        public final String getElectionPageIdManual() {
            return this.electionPageIdManual;
        }

        public final String getElectionPageIdAprilTag() {
            return this.electionPageIdAprilTag;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("imagePath", this.imagePath);
            bundle.putString("electionPageIdManual", this.electionPageIdManual);
            bundle.putString("electionPageIdAprilTag", this.electionPageIdAprilTag);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$ActionElectionDetailFragmentToAutoCaptureFragment;", "Landroidx/navigation/NavDirections;", "imageUri", "Landroid/net/Uri;", "croppedImageUri", "correctedImageUri", "(Landroid/net/Uri;Landroid/net/Uri;Landroid/net/Uri;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getCorrectedImageUri", "()Landroid/net/Uri;", "getCroppedImageUri", "getImageUri", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionElectionDetailFragmentToAutoCaptureFragment implements NavDirections {
        private final int actionId;
        private final Uri correctedImageUri;
        private final Uri croppedImageUri;
        private final Uri imageUri;

        public static /* synthetic */ ActionElectionDetailFragmentToAutoCaptureFragment copy$default(ActionElectionDetailFragmentToAutoCaptureFragment actionElectionDetailFragmentToAutoCaptureFragment, Uri uri, Uri uri2, Uri uri3, int i, Object obj) {
            if ((i & 1) != 0) {
                uri = actionElectionDetailFragmentToAutoCaptureFragment.imageUri;
            }
            if ((i & 2) != 0) {
                uri2 = actionElectionDetailFragmentToAutoCaptureFragment.croppedImageUri;
            }
            if ((i & 4) != 0) {
                uri3 = actionElectionDetailFragmentToAutoCaptureFragment.correctedImageUri;
            }
            return actionElectionDetailFragmentToAutoCaptureFragment.copy(uri, uri2, uri3);
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

        public final ActionElectionDetailFragmentToAutoCaptureFragment copy(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
            Intrinsics.checkNotNullParameter(imageUri, "imageUri");
            Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
            Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
            return new ActionElectionDetailFragmentToAutoCaptureFragment(imageUri, croppedImageUri, correctedImageUri);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ActionElectionDetailFragmentToAutoCaptureFragment) {
                ActionElectionDetailFragmentToAutoCaptureFragment actionElectionDetailFragmentToAutoCaptureFragment = (ActionElectionDetailFragmentToAutoCaptureFragment) obj;
                return Intrinsics.areEqual(this.imageUri, actionElectionDetailFragmentToAutoCaptureFragment.imageUri) && Intrinsics.areEqual(this.croppedImageUri, actionElectionDetailFragmentToAutoCaptureFragment.croppedImageUri) && Intrinsics.areEqual(this.correctedImageUri, actionElectionDetailFragmentToAutoCaptureFragment.correctedImageUri);
            }
            return false;
        }

        public int hashCode() {
            return (((this.imageUri.hashCode() * 31) + this.croppedImageUri.hashCode()) * 31) + this.correctedImageUri.hashCode();
        }

        public String toString() {
            Uri uri = this.imageUri;
            Uri uri2 = this.croppedImageUri;
            return "ActionElectionDetailFragmentToAutoCaptureFragment(imageUri=" + uri + ", croppedImageUri=" + uri2 + ", correctedImageUri=" + this.correctedImageUri + ")";
        }

        public ActionElectionDetailFragmentToAutoCaptureFragment(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
            Intrinsics.checkNotNullParameter(imageUri, "imageUri");
            Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
            Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
            this.imageUri = imageUri;
            this.croppedImageUri = croppedImageUri;
            this.correctedImageUri = correctedImageUri;
            this.actionId = R.id.action_electionDetailFragment_to_autoCaptureFragment;
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

    /* compiled from: ElectionDetailFragmentDirections.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bJ\u0016\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006J\u001e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0006J\u001e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0006J\u001e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0006J&\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006J&\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0006¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentDirections$Companion;", "", "()V", "actionElectionDetailFragmentToAprilTagConflictFragment", "Landroidx/navigation/NavDirections;", "imagePath", "", "electionPageIdManual", "electionPageIdAprilTag", "actionElectionDetailFragmentToAutoCaptureFragment", "imageUri", "Landroid/net/Uri;", "croppedImageUri", "correctedImageUri", "actionElectionDetailFragmentToConfirmSaveFormCImageFragment", "electionPageId", "actionElectionDetailFragmentToSendImageFragment", "actionElectionDetailFragmentToVerifyAdministrationFragment", "idImage", "electionPageKind", "", "actionElectionDetailFragmentToVerifyAdministrationHal2Fragment", "actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment", "actionElectionDetailFragmentToVerifyTabulationFragment", "electionPemilihan", "actionElectionDetailFragmentToVerifyTabulationPartaiFragment", "actionGlobalLoginNavGraph", "actionGlobalPreviewImageFragment", "imageFilePath", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavDirections actionElectionDetailFragmentToVerifyAdministrationFragment(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToVerifyAdministrationFragment(idImage, i, electionPageId);
        }

        public final NavDirections actionElectionDetailFragmentToVerifyTabulationFragment(String idImage, int i, String electionPageId, String electionPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
            return new ActionElectionDetailFragmentToVerifyTabulationFragment(idImage, i, electionPageId, electionPemilihan);
        }

        public final NavDirections actionElectionDetailFragmentToSendImageFragment(String electionPageId) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToSendImageFragment(electionPageId);
        }

        public final NavDirections actionElectionDetailFragmentToVerifyAdministrationHal2Fragment(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToVerifyAdministrationHal2Fragment(idImage, i, electionPageId);
        }

        public final NavDirections actionElectionDetailFragmentToVerifyTabulationPartaiFragment(String idImage, int i, String electionPageId, String electionPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
            return new ActionElectionDetailFragmentToVerifyTabulationPartaiFragment(idImage, i, electionPageId, electionPemilihan);
        }

        public final NavDirections actionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment(String idImage, int i, String electionPageId) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToVerifyAdministrationHal2PpwpFragment(idImage, i, electionPageId);
        }

        public final NavDirections actionElectionDetailFragmentToConfirmSaveFormCImageFragment(String imagePath, String electionPageId) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ActionElectionDetailFragmentToConfirmSaveFormCImageFragment(imagePath, electionPageId);
        }

        public final NavDirections actionElectionDetailFragmentToAprilTagConflictFragment(String imagePath, String electionPageIdManual, String electionPageIdAprilTag) {
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            Intrinsics.checkNotNullParameter(electionPageIdManual, "electionPageIdManual");
            Intrinsics.checkNotNullParameter(electionPageIdAprilTag, "electionPageIdAprilTag");
            return new ActionElectionDetailFragmentToAprilTagConflictFragment(imagePath, electionPageIdManual, electionPageIdAprilTag);
        }

        public final NavDirections actionElectionDetailFragmentToAutoCaptureFragment(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
            Intrinsics.checkNotNullParameter(imageUri, "imageUri");
            Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
            Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
            return new ActionElectionDetailFragmentToAutoCaptureFragment(imageUri, croppedImageUri, correctedImageUri);
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
