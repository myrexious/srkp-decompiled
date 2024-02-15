package org.informatika.sirekap.ui.verify.wizard;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VerifyWizardFragmentArgs.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragmentArgs;", "Landroidx/navigation/NavArgs;", "idImage", "", "electionPageKind", "", "electionPageId", "electionPemilihan", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "()I", "getElectionPemilihan", "getIdImage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyWizardFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String electionPageId;
    private final int electionPageKind;
    private final String electionPemilihan;
    private final String idImage;

    public static /* synthetic */ VerifyWizardFragmentArgs copy$default(VerifyWizardFragmentArgs verifyWizardFragmentArgs, String str, int i, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = verifyWizardFragmentArgs.idImage;
        }
        if ((i2 & 2) != 0) {
            i = verifyWizardFragmentArgs.electionPageKind;
        }
        if ((i2 & 4) != 0) {
            str2 = verifyWizardFragmentArgs.electionPageId;
        }
        if ((i2 & 8) != 0) {
            str3 = verifyWizardFragmentArgs.electionPemilihan;
        }
        return verifyWizardFragmentArgs.copy(str, i, str2, str3);
    }

    @JvmStatic
    public static final VerifyWizardFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final VerifyWizardFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
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

    public final VerifyWizardFragmentArgs copy(String idImage, int i, String electionPageId, String str) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return new VerifyWizardFragmentArgs(idImage, i, electionPageId, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VerifyWizardFragmentArgs) {
            VerifyWizardFragmentArgs verifyWizardFragmentArgs = (VerifyWizardFragmentArgs) obj;
            return Intrinsics.areEqual(this.idImage, verifyWizardFragmentArgs.idImage) && this.electionPageKind == verifyWizardFragmentArgs.electionPageKind && Intrinsics.areEqual(this.electionPageId, verifyWizardFragmentArgs.electionPageId) && Intrinsics.areEqual(this.electionPemilihan, verifyWizardFragmentArgs.electionPemilihan);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode()) * 31;
        String str = this.electionPemilihan;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.idImage;
        int i = this.electionPageKind;
        String str2 = this.electionPageId;
        return "VerifyWizardFragmentArgs(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + str2 + ", electionPemilihan=" + this.electionPemilihan + ")";
    }

    public VerifyWizardFragmentArgs(String idImage, int i, String electionPageId, String str) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.idImage = idImage;
        this.electionPageKind = i;
        this.electionPageId = electionPageId;
        this.electionPemilihan = str;
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

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("idImage", this.idImage);
        bundle.putInt("electionPageKind", this.electionPageKind);
        bundle.putString("electionPageId", this.electionPageId);
        bundle.putString("electionPemilihan", this.electionPemilihan);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("idImage", this.idImage);
        savedStateHandle.set("electionPageKind", Integer.valueOf(this.electionPageKind));
        savedStateHandle.set("electionPageId", this.electionPageId);
        savedStateHandle.set("electionPemilihan", this.electionPemilihan);
        return savedStateHandle;
    }

    /* compiled from: VerifyWizardFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final VerifyWizardFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(VerifyWizardFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("idImage")) {
                String string = bundle.getString("idImage");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"idImage\" is marked as non-null but was passed a null value.");
                }
                if (bundle.containsKey("electionPageKind")) {
                    int i = bundle.getInt("electionPageKind");
                    if (bundle.containsKey("electionPageId")) {
                        String string2 = bundle.getString("electionPageId");
                        if (string2 == null) {
                            throw new IllegalArgumentException("Argument \"electionPageId\" is marked as non-null but was passed a null value.");
                        }
                        if (!bundle.containsKey("electionPemilihan")) {
                            throw new IllegalArgumentException("Required argument \"electionPemilihan\" is missing and does not have an android:defaultValue");
                        }
                        return new VerifyWizardFragmentArgs(string, i, string2, bundle.getString("electionPemilihan"));
                    }
                    throw new IllegalArgumentException("Required argument \"electionPageId\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"electionPageKind\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"idImage\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final VerifyWizardFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("idImage")) {
                String str = (String) savedStateHandle.get("idImage");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"idImage\" is marked as non-null but was passed a null value");
                }
                if (savedStateHandle.contains("electionPageKind")) {
                    Integer num = (Integer) savedStateHandle.get("electionPageKind");
                    if (num == null) {
                        throw new IllegalArgumentException("Argument \"electionPageKind\" of type integer does not support null values");
                    }
                    if (savedStateHandle.contains("electionPageId")) {
                        String str2 = (String) savedStateHandle.get("electionPageId");
                        if (str2 == null) {
                            throw new IllegalArgumentException("Argument \"electionPageId\" is marked as non-null but was passed a null value");
                        }
                        if (!savedStateHandle.contains("electionPemilihan")) {
                            throw new IllegalArgumentException("Required argument \"electionPemilihan\" is missing and does not have an android:defaultValue");
                        }
                        return new VerifyWizardFragmentArgs(str, num.intValue(), str2, (String) savedStateHandle.get("electionPemilihan"));
                    }
                    throw new IllegalArgumentException("Required argument \"electionPageId\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"electionPageKind\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"idImage\" is missing and does not have an android:defaultValue");
        }
    }
}