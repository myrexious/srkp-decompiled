package org.informatika.sirekap.ui.selectFormCImage;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectFormCImageFragmentArgs.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragmentArgs;", "Landroidx/navigation/NavArgs;", "imagePath", "", "(Ljava/lang/String;)V", "getImagePath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SelectFormCImageFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String imagePath;

    public static /* synthetic */ SelectFormCImageFragmentArgs copy$default(SelectFormCImageFragmentArgs selectFormCImageFragmentArgs, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = selectFormCImageFragmentArgs.imagePath;
        }
        return selectFormCImageFragmentArgs.copy(str);
    }

    @JvmStatic
    public static final SelectFormCImageFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final SelectFormCImageFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
    }

    public final String component1() {
        return this.imagePath;
    }

    public final SelectFormCImageFragmentArgs copy(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        return new SelectFormCImageFragmentArgs(imagePath);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SelectFormCImageFragmentArgs) && Intrinsics.areEqual(this.imagePath, ((SelectFormCImageFragmentArgs) obj).imagePath);
    }

    public int hashCode() {
        return this.imagePath.hashCode();
    }

    public String toString() {
        return "SelectFormCImageFragmentArgs(imagePath=" + this.imagePath + ")";
    }

    public SelectFormCImageFragmentArgs(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        this.imagePath = imagePath;
    }

    public final String getImagePath() {
        return this.imagePath;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("imagePath", this.imagePath);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("imagePath", this.imagePath);
        return savedStateHandle;
    }

    /* compiled from: SelectFormCImageFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SelectFormCImageFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(SelectFormCImageFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("imagePath")) {
                String string = bundle.getString("imagePath");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"imagePath\" is marked as non-null but was passed a null value.");
                }
                return new SelectFormCImageFragmentArgs(string);
            }
            throw new IllegalArgumentException("Required argument \"imagePath\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final SelectFormCImageFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("imagePath")) {
                String str = (String) savedStateHandle.get("imagePath");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"imagePath\" is marked as non-null but was passed a null value");
                }
                return new SelectFormCImageFragmentArgs(str);
            }
            throw new IllegalArgumentException("Required argument \"imagePath\" is missing and does not have an android:defaultValue");
        }
    }
}
