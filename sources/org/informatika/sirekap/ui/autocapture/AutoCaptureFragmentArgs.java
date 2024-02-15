package org.informatika.sirekap.ui.autocapture;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AutoCaptureFragmentArgs.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001c"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragmentArgs;", "Landroidx/navigation/NavArgs;", "imageUri", "Landroid/net/Uri;", "croppedImageUri", "correctedImageUri", "(Landroid/net/Uri;Landroid/net/Uri;Landroid/net/Uri;)V", "getCorrectedImageUri", "()Landroid/net/Uri;", "getCroppedImageUri", "getImageUri", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AutoCaptureFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final Uri correctedImageUri;
    private final Uri croppedImageUri;
    private final Uri imageUri;

    public static /* synthetic */ AutoCaptureFragmentArgs copy$default(AutoCaptureFragmentArgs autoCaptureFragmentArgs, Uri uri, Uri uri2, Uri uri3, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = autoCaptureFragmentArgs.imageUri;
        }
        if ((i & 2) != 0) {
            uri2 = autoCaptureFragmentArgs.croppedImageUri;
        }
        if ((i & 4) != 0) {
            uri3 = autoCaptureFragmentArgs.correctedImageUri;
        }
        return autoCaptureFragmentArgs.copy(uri, uri2, uri3);
    }

    @JvmStatic
    public static final AutoCaptureFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final AutoCaptureFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
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

    public final AutoCaptureFragmentArgs copy(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
        Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
        return new AutoCaptureFragmentArgs(imageUri, croppedImageUri, correctedImageUri);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AutoCaptureFragmentArgs) {
            AutoCaptureFragmentArgs autoCaptureFragmentArgs = (AutoCaptureFragmentArgs) obj;
            return Intrinsics.areEqual(this.imageUri, autoCaptureFragmentArgs.imageUri) && Intrinsics.areEqual(this.croppedImageUri, autoCaptureFragmentArgs.croppedImageUri) && Intrinsics.areEqual(this.correctedImageUri, autoCaptureFragmentArgs.correctedImageUri);
        }
        return false;
    }

    public int hashCode() {
        return (((this.imageUri.hashCode() * 31) + this.croppedImageUri.hashCode()) * 31) + this.correctedImageUri.hashCode();
    }

    public String toString() {
        Uri uri = this.imageUri;
        Uri uri2 = this.croppedImageUri;
        return "AutoCaptureFragmentArgs(imageUri=" + uri + ", croppedImageUri=" + uri2 + ", correctedImageUri=" + this.correctedImageUri + ")";
    }

    public AutoCaptureFragmentArgs(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
        Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
        this.imageUri = imageUri;
        this.croppedImageUri = croppedImageUri;
        this.correctedImageUri = correctedImageUri;
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

    public final Bundle toBundle() {
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

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        if (Parcelable.class.isAssignableFrom(Uri.class)) {
            Uri uri = this.imageUri;
            Intrinsics.checkNotNull(uri, "null cannot be cast to non-null type android.os.Parcelable");
            savedStateHandle.set("imageUri", uri);
        } else if (Serializable.class.isAssignableFrom(Uri.class)) {
            Uri uri2 = this.imageUri;
            Intrinsics.checkNotNull(uri2, "null cannot be cast to non-null type java.io.Serializable");
            savedStateHandle.set("imageUri", (Serializable) uri2);
        } else {
            throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
        if (Parcelable.class.isAssignableFrom(Uri.class)) {
            Uri uri3 = this.croppedImageUri;
            Intrinsics.checkNotNull(uri3, "null cannot be cast to non-null type android.os.Parcelable");
            savedStateHandle.set("croppedImageUri", uri3);
        } else if (Serializable.class.isAssignableFrom(Uri.class)) {
            Uri uri4 = this.croppedImageUri;
            Intrinsics.checkNotNull(uri4, "null cannot be cast to non-null type java.io.Serializable");
            savedStateHandle.set("croppedImageUri", (Serializable) uri4);
        } else {
            throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
        if (Parcelable.class.isAssignableFrom(Uri.class)) {
            Uri uri5 = this.correctedImageUri;
            Intrinsics.checkNotNull(uri5, "null cannot be cast to non-null type android.os.Parcelable");
            savedStateHandle.set("correctedImageUri", uri5);
        } else if (Serializable.class.isAssignableFrom(Uri.class)) {
            Uri uri6 = this.correctedImageUri;
            Intrinsics.checkNotNull(uri6, "null cannot be cast to non-null type java.io.Serializable");
            savedStateHandle.set("correctedImageUri", (Serializable) uri6);
        } else {
            throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
        return savedStateHandle;
    }

    /* compiled from: AutoCaptureFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AutoCaptureFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(AutoCaptureFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("imageUri")) {
                if (Parcelable.class.isAssignableFrom(Uri.class) || Serializable.class.isAssignableFrom(Uri.class)) {
                    Uri uri = (Uri) bundle.get("imageUri");
                    if (uri == null) {
                        throw new IllegalArgumentException("Argument \"imageUri\" is marked as non-null but was passed a null value.");
                    }
                    if (bundle.containsKey("croppedImageUri")) {
                        if (Parcelable.class.isAssignableFrom(Uri.class) || Serializable.class.isAssignableFrom(Uri.class)) {
                            Uri uri2 = (Uri) bundle.get("croppedImageUri");
                            if (uri2 == null) {
                                throw new IllegalArgumentException("Argument \"croppedImageUri\" is marked as non-null but was passed a null value.");
                            }
                            if (bundle.containsKey("correctedImageUri")) {
                                if (Parcelable.class.isAssignableFrom(Uri.class) || Serializable.class.isAssignableFrom(Uri.class)) {
                                    Uri uri3 = (Uri) bundle.get("correctedImageUri");
                                    if (uri3 == null) {
                                        throw new IllegalArgumentException("Argument \"correctedImageUri\" is marked as non-null but was passed a null value.");
                                    }
                                    return new AutoCaptureFragmentArgs(uri, uri2, uri3);
                                }
                                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                            }
                            throw new IllegalArgumentException("Required argument \"correctedImageUri\" is missing and does not have an android:defaultValue");
                        }
                        throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                    }
                    throw new IllegalArgumentException("Required argument \"croppedImageUri\" is missing and does not have an android:defaultValue");
                }
                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            throw new IllegalArgumentException("Required argument \"imageUri\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final AutoCaptureFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("imageUri")) {
                if (Parcelable.class.isAssignableFrom(Uri.class) || Serializable.class.isAssignableFrom(Uri.class)) {
                    Uri uri = (Uri) savedStateHandle.get("imageUri");
                    if (uri == null) {
                        throw new IllegalArgumentException("Argument \"imageUri\" is marked as non-null but was passed a null value");
                    }
                    if (savedStateHandle.contains("croppedImageUri")) {
                        if (Parcelable.class.isAssignableFrom(Uri.class) || Serializable.class.isAssignableFrom(Uri.class)) {
                            Uri uri2 = (Uri) savedStateHandle.get("croppedImageUri");
                            if (uri2 == null) {
                                throw new IllegalArgumentException("Argument \"croppedImageUri\" is marked as non-null but was passed a null value");
                            }
                            if (savedStateHandle.contains("correctedImageUri")) {
                                if (Parcelable.class.isAssignableFrom(Uri.class) || Serializable.class.isAssignableFrom(Uri.class)) {
                                    Uri uri3 = (Uri) savedStateHandle.get("correctedImageUri");
                                    if (uri3 == null) {
                                        throw new IllegalArgumentException("Argument \"correctedImageUri\" is marked as non-null but was passed a null value");
                                    }
                                    return new AutoCaptureFragmentArgs(uri, uri2, uri3);
                                }
                                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                            }
                            throw new IllegalArgumentException("Required argument \"correctedImageUri\" is missing and does not have an android:defaultValue");
                        }
                        throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                    }
                    throw new IllegalArgumentException("Required argument \"croppedImageUri\" is missing and does not have an android:defaultValue");
                }
                throw new UnsupportedOperationException(Uri.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            throw new IllegalArgumentException("Required argument \"imageUri\" is missing and does not have an android:defaultValue");
        }
    }
}
