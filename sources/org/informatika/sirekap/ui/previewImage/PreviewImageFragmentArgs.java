package org.informatika.sirekap.ui.previewImage;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PreviewImageFragmentArgs.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/ui/previewImage/PreviewImageFragmentArgs;", "Landroidx/navigation/NavArgs;", "imageFilePath", "", "(Ljava/lang/String;)V", "getImageFilePath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PreviewImageFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String imageFilePath;

    public static /* synthetic */ PreviewImageFragmentArgs copy$default(PreviewImageFragmentArgs previewImageFragmentArgs, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = previewImageFragmentArgs.imageFilePath;
        }
        return previewImageFragmentArgs.copy(str);
    }

    @JvmStatic
    public static final PreviewImageFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final PreviewImageFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
    }

    public final String component1() {
        return this.imageFilePath;
    }

    public final PreviewImageFragmentArgs copy(String imageFilePath) {
        Intrinsics.checkNotNullParameter(imageFilePath, "imageFilePath");
        return new PreviewImageFragmentArgs(imageFilePath);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PreviewImageFragmentArgs) && Intrinsics.areEqual(this.imageFilePath, ((PreviewImageFragmentArgs) obj).imageFilePath);
    }

    public int hashCode() {
        return this.imageFilePath.hashCode();
    }

    public String toString() {
        return "PreviewImageFragmentArgs(imageFilePath=" + this.imageFilePath + ")";
    }

    public PreviewImageFragmentArgs(String imageFilePath) {
        Intrinsics.checkNotNullParameter(imageFilePath, "imageFilePath");
        this.imageFilePath = imageFilePath;
    }

    public final String getImageFilePath() {
        return this.imageFilePath;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("imageFilePath", this.imageFilePath);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("imageFilePath", this.imageFilePath);
        return savedStateHandle;
    }

    /* compiled from: PreviewImageFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/previewImage/PreviewImageFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/previewImage/PreviewImageFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PreviewImageFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(PreviewImageFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("imageFilePath")) {
                String string = bundle.getString("imageFilePath");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"imageFilePath\" is marked as non-null but was passed a null value.");
                }
                return new PreviewImageFragmentArgs(string);
            }
            throw new IllegalArgumentException("Required argument \"imageFilePath\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final PreviewImageFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("imageFilePath")) {
                String str = (String) savedStateHandle.get("imageFilePath");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"imageFilePath\" is marked as non-null but was passed a null value");
                }
                return new PreviewImageFragmentArgs(str);
            }
            throw new IllegalArgumentException("Required argument \"imageFilePath\" is missing and does not have an android:defaultValue");
        }
    }
}
