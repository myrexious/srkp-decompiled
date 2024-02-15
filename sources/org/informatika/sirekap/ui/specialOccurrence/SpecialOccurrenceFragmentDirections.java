package org.informatika.sirekap.ui.specialOccurrence;

import android.os.Bundle;
import androidx.navigation.NavDirections;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.MainNavGraphDirections;
import org.informatika.sirekap.R;

/* compiled from: SpecialOccurrenceFragmentDirections.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00052\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragmentDirections;", "", "()V", "ActionSpecialOccurrenceFragmentToPreviewImageFragment", "ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SpecialOccurrenceFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SpecialOccurrenceFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragmentDirections$ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment;", "Landroidx/navigation/NavDirections;", "kodeTps", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getKodeTps", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment implements NavDirections {
        private final int actionId;
        private final String kodeTps;

        public static /* synthetic */ ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment copy$default(ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment actionSpecialOccurrenceFragmentToVerifyOccurrenceFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionSpecialOccurrenceFragmentToVerifyOccurrenceFragment.kodeTps;
            }
            return actionSpecialOccurrenceFragmentToVerifyOccurrenceFragment.copy(str);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment copy(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment(kodeTps);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment) && Intrinsics.areEqual(this.kodeTps, ((ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment) obj).kodeTps);
        }

        public int hashCode() {
            return this.kodeTps.hashCode();
        }

        public String toString() {
            return "ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment(kodeTps=" + this.kodeTps + ")";
        }

        public ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            this.kodeTps = kodeTps;
            this.actionId = R.id.action_SpecialOccurrenceFragment_to_VerifyOccurrenceFragment;
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

    private SpecialOccurrenceFragmentDirections() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SpecialOccurrenceFragmentDirections.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragmentDirections$ActionSpecialOccurrenceFragmentToPreviewImageFragment;", "Landroidx/navigation/NavDirections;", "imageFilePath", "", "(Ljava/lang/String;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getImageFilePath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ActionSpecialOccurrenceFragmentToPreviewImageFragment implements NavDirections {
        private final int actionId;
        private final String imageFilePath;

        public static /* synthetic */ ActionSpecialOccurrenceFragmentToPreviewImageFragment copy$default(ActionSpecialOccurrenceFragmentToPreviewImageFragment actionSpecialOccurrenceFragmentToPreviewImageFragment, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionSpecialOccurrenceFragmentToPreviewImageFragment.imageFilePath;
            }
            return actionSpecialOccurrenceFragmentToPreviewImageFragment.copy(str);
        }

        public final String component1() {
            return this.imageFilePath;
        }

        public final ActionSpecialOccurrenceFragmentToPreviewImageFragment copy(String imageFilePath) {
            Intrinsics.checkNotNullParameter(imageFilePath, "imageFilePath");
            return new ActionSpecialOccurrenceFragmentToPreviewImageFragment(imageFilePath);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionSpecialOccurrenceFragmentToPreviewImageFragment) && Intrinsics.areEqual(this.imageFilePath, ((ActionSpecialOccurrenceFragmentToPreviewImageFragment) obj).imageFilePath);
        }

        public int hashCode() {
            return this.imageFilePath.hashCode();
        }

        public String toString() {
            return "ActionSpecialOccurrenceFragmentToPreviewImageFragment(imageFilePath=" + this.imageFilePath + ")";
        }

        public ActionSpecialOccurrenceFragmentToPreviewImageFragment(String imageFilePath) {
            Intrinsics.checkNotNullParameter(imageFilePath, "imageFilePath");
            this.imageFilePath = imageFilePath;
            this.actionId = R.id.action_specialOccurrenceFragment_to_previewImageFragment;
        }

        public final String getImageFilePath() {
            return this.imageFilePath;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("imageFilePath", this.imageFilePath);
            return bundle;
        }
    }

    /* compiled from: SpecialOccurrenceFragmentDirections.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragmentDirections$Companion;", "", "()V", "actionGlobalLoginNavGraph", "Landroidx/navigation/NavDirections;", "actionGlobalPreviewImageFragment", "imageFilePath", "", "actionSpecialOccurrenceFragmentToPreviewImageFragment", "actionSpecialOccurrenceFragmentToVerifyOccurrenceFragment", "kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavDirections actionSpecialOccurrenceFragmentToVerifyOccurrenceFragment(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new ActionSpecialOccurrenceFragmentToVerifyOccurrenceFragment(kodeTps);
        }

        public final NavDirections actionSpecialOccurrenceFragmentToPreviewImageFragment(String imageFilePath) {
            Intrinsics.checkNotNullParameter(imageFilePath, "imageFilePath");
            return new ActionSpecialOccurrenceFragmentToPreviewImageFragment(imageFilePath);
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