package org.informatika.sirekap.ui.verify.administrationHal2;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VerifyAdministrationHal2FragmentArgs.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001c"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2/VerifyAdministrationHal2FragmentArgs;", "Landroidx/navigation/NavArgs;", "idImage", "", "electionPageKind", "", "electionPageId", "(Ljava/lang/String;ILjava/lang/String;)V", "getElectionPageId", "()Ljava/lang/String;", "getElectionPageKind", "()I", "getIdImage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2FragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String electionPageId;
    private final int electionPageKind;
    private final String idImage;

    public static /* synthetic */ VerifyAdministrationHal2FragmentArgs copy$default(VerifyAdministrationHal2FragmentArgs verifyAdministrationHal2FragmentArgs, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = verifyAdministrationHal2FragmentArgs.idImage;
        }
        if ((i2 & 2) != 0) {
            i = verifyAdministrationHal2FragmentArgs.electionPageKind;
        }
        if ((i2 & 4) != 0) {
            str2 = verifyAdministrationHal2FragmentArgs.electionPageId;
        }
        return verifyAdministrationHal2FragmentArgs.copy(str, i, str2);
    }

    @JvmStatic
    public static final VerifyAdministrationHal2FragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final VerifyAdministrationHal2FragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
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

    public final VerifyAdministrationHal2FragmentArgs copy(String idImage, int i, String electionPageId) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return new VerifyAdministrationHal2FragmentArgs(idImage, i, electionPageId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VerifyAdministrationHal2FragmentArgs) {
            VerifyAdministrationHal2FragmentArgs verifyAdministrationHal2FragmentArgs = (VerifyAdministrationHal2FragmentArgs) obj;
            return Intrinsics.areEqual(this.idImage, verifyAdministrationHal2FragmentArgs.idImage) && this.electionPageKind == verifyAdministrationHal2FragmentArgs.electionPageKind && Intrinsics.areEqual(this.electionPageId, verifyAdministrationHal2FragmentArgs.electionPageId);
        }
        return false;
    }

    public int hashCode() {
        return (((this.idImage.hashCode() * 31) + Integer.hashCode(this.electionPageKind)) * 31) + this.electionPageId.hashCode();
    }

    public String toString() {
        String str = this.idImage;
        int i = this.electionPageKind;
        return "VerifyAdministrationHal2FragmentArgs(idImage=" + str + ", electionPageKind=" + i + ", electionPageId=" + this.electionPageId + ")";
    }

    public VerifyAdministrationHal2FragmentArgs(String idImage, int i, String electionPageId) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.idImage = idImage;
        this.electionPageKind = i;
        this.electionPageId = electionPageId;
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

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("idImage", this.idImage);
        bundle.putInt("electionPageKind", this.electionPageKind);
        bundle.putString("electionPageId", this.electionPageId);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("idImage", this.idImage);
        savedStateHandle.set("electionPageKind", Integer.valueOf(this.electionPageKind));
        savedStateHandle.set("electionPageId", this.electionPageId);
        return savedStateHandle;
    }

    /* compiled from: VerifyAdministrationHal2FragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2/VerifyAdministrationHal2FragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/verify/administrationHal2/VerifyAdministrationHal2FragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final VerifyAdministrationHal2FragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(VerifyAdministrationHal2FragmentArgs.class.getClassLoader());
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
                        return new VerifyAdministrationHal2FragmentArgs(string, i, string2);
                    }
                    throw new IllegalArgumentException("Required argument \"electionPageId\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"electionPageKind\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"idImage\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final VerifyAdministrationHal2FragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
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
                        return new VerifyAdministrationHal2FragmentArgs(str, num.intValue(), str2);
                    }
                    throw new IllegalArgumentException("Required argument \"electionPageId\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"electionPageKind\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"idImage\" is missing and does not have an android:defaultValue");
        }
    }
}