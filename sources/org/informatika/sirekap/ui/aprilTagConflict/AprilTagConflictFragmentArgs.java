package org.informatika.sirekap.ui.aprilTagConflict;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AprilTagConflictFragmentArgs.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragmentArgs;", "Landroidx/navigation/NavArgs;", "imagePath", "", "electionPageIdManual", "electionPageIdAprilTag", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getElectionPageIdAprilTag", "()Ljava/lang/String;", "getElectionPageIdManual", "getImagePath", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AprilTagConflictFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String electionPageIdAprilTag;
    private final String electionPageIdManual;
    private final String imagePath;

    public static /* synthetic */ AprilTagConflictFragmentArgs copy$default(AprilTagConflictFragmentArgs aprilTagConflictFragmentArgs, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aprilTagConflictFragmentArgs.imagePath;
        }
        if ((i & 2) != 0) {
            str2 = aprilTagConflictFragmentArgs.electionPageIdManual;
        }
        if ((i & 4) != 0) {
            str3 = aprilTagConflictFragmentArgs.electionPageIdAprilTag;
        }
        return aprilTagConflictFragmentArgs.copy(str, str2, str3);
    }

    @JvmStatic
    public static final AprilTagConflictFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final AprilTagConflictFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
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

    public final AprilTagConflictFragmentArgs copy(String imagePath, String electionPageIdManual, String electionPageIdAprilTag) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Intrinsics.checkNotNullParameter(electionPageIdManual, "electionPageIdManual");
        Intrinsics.checkNotNullParameter(electionPageIdAprilTag, "electionPageIdAprilTag");
        return new AprilTagConflictFragmentArgs(imagePath, electionPageIdManual, electionPageIdAprilTag);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AprilTagConflictFragmentArgs) {
            AprilTagConflictFragmentArgs aprilTagConflictFragmentArgs = (AprilTagConflictFragmentArgs) obj;
            return Intrinsics.areEqual(this.imagePath, aprilTagConflictFragmentArgs.imagePath) && Intrinsics.areEqual(this.electionPageIdManual, aprilTagConflictFragmentArgs.electionPageIdManual) && Intrinsics.areEqual(this.electionPageIdAprilTag, aprilTagConflictFragmentArgs.electionPageIdAprilTag);
        }
        return false;
    }

    public int hashCode() {
        return (((this.imagePath.hashCode() * 31) + this.electionPageIdManual.hashCode()) * 31) + this.electionPageIdAprilTag.hashCode();
    }

    public String toString() {
        String str = this.imagePath;
        String str2 = this.electionPageIdManual;
        return "AprilTagConflictFragmentArgs(imagePath=" + str + ", electionPageIdManual=" + str2 + ", electionPageIdAprilTag=" + this.electionPageIdAprilTag + ")";
    }

    public AprilTagConflictFragmentArgs(String imagePath, String electionPageIdManual, String electionPageIdAprilTag) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Intrinsics.checkNotNullParameter(electionPageIdManual, "electionPageIdManual");
        Intrinsics.checkNotNullParameter(electionPageIdAprilTag, "electionPageIdAprilTag");
        this.imagePath = imagePath;
        this.electionPageIdManual = electionPageIdManual;
        this.electionPageIdAprilTag = electionPageIdAprilTag;
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

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("imagePath", this.imagePath);
        bundle.putString("electionPageIdManual", this.electionPageIdManual);
        bundle.putString("electionPageIdAprilTag", this.electionPageIdAprilTag);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("imagePath", this.imagePath);
        savedStateHandle.set("electionPageIdManual", this.electionPageIdManual);
        savedStateHandle.set("electionPageIdAprilTag", this.electionPageIdAprilTag);
        return savedStateHandle;
    }

    /* compiled from: AprilTagConflictFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AprilTagConflictFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(AprilTagConflictFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("imagePath")) {
                String string = bundle.getString("imagePath");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"imagePath\" is marked as non-null but was passed a null value.");
                }
                if (bundle.containsKey("electionPageIdManual")) {
                    String string2 = bundle.getString("electionPageIdManual");
                    if (string2 == null) {
                        throw new IllegalArgumentException("Argument \"electionPageIdManual\" is marked as non-null but was passed a null value.");
                    }
                    if (bundle.containsKey("electionPageIdAprilTag")) {
                        String string3 = bundle.getString("electionPageIdAprilTag");
                        if (string3 == null) {
                            throw new IllegalArgumentException("Argument \"electionPageIdAprilTag\" is marked as non-null but was passed a null value.");
                        }
                        return new AprilTagConflictFragmentArgs(string, string2, string3);
                    }
                    throw new IllegalArgumentException("Required argument \"electionPageIdAprilTag\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"electionPageIdManual\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"imagePath\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final AprilTagConflictFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("imagePath")) {
                String str = (String) savedStateHandle.get("imagePath");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"imagePath\" is marked as non-null but was passed a null value");
                }
                if (savedStateHandle.contains("electionPageIdManual")) {
                    String str2 = (String) savedStateHandle.get("electionPageIdManual");
                    if (str2 == null) {
                        throw new IllegalArgumentException("Argument \"electionPageIdManual\" is marked as non-null but was passed a null value");
                    }
                    if (savedStateHandle.contains("electionPageIdAprilTag")) {
                        String str3 = (String) savedStateHandle.get("electionPageIdAprilTag");
                        if (str3 == null) {
                            throw new IllegalArgumentException("Argument \"electionPageIdAprilTag\" is marked as non-null but was passed a null value");
                        }
                        return new AprilTagConflictFragmentArgs(str, str2, str3);
                    }
                    throw new IllegalArgumentException("Required argument \"electionPageIdAprilTag\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"electionPageIdManual\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"imagePath\" is missing and does not have an android:defaultValue");
        }
    }
}
