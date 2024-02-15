package org.informatika.sirekap.ui.witness.qrCode;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WitnessQrCodeFragmentArgs.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentArgs;", "Landroidx/navigation/NavArgs;", "witnessId", "", "kodeTps", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getJenisPemilihan", "()Ljava/lang/String;", "getKodeTps", "getWitnessId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessQrCodeFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String jenisPemilihan;
    private final String kodeTps;
    private final String witnessId;

    public static /* synthetic */ WitnessQrCodeFragmentArgs copy$default(WitnessQrCodeFragmentArgs witnessQrCodeFragmentArgs, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = witnessQrCodeFragmentArgs.witnessId;
        }
        if ((i & 2) != 0) {
            str2 = witnessQrCodeFragmentArgs.kodeTps;
        }
        if ((i & 4) != 0) {
            str3 = witnessQrCodeFragmentArgs.jenisPemilihan;
        }
        return witnessQrCodeFragmentArgs.copy(str, str2, str3);
    }

    @JvmStatic
    public static final WitnessQrCodeFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final WitnessQrCodeFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
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

    public final WitnessQrCodeFragmentArgs copy(String witnessId, String kodeTps, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(witnessId, "witnessId");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new WitnessQrCodeFragmentArgs(witnessId, kodeTps, jenisPemilihan);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WitnessQrCodeFragmentArgs) {
            WitnessQrCodeFragmentArgs witnessQrCodeFragmentArgs = (WitnessQrCodeFragmentArgs) obj;
            return Intrinsics.areEqual(this.witnessId, witnessQrCodeFragmentArgs.witnessId) && Intrinsics.areEqual(this.kodeTps, witnessQrCodeFragmentArgs.kodeTps) && Intrinsics.areEqual(this.jenisPemilihan, witnessQrCodeFragmentArgs.jenisPemilihan);
        }
        return false;
    }

    public int hashCode() {
        return (((this.witnessId.hashCode() * 31) + this.kodeTps.hashCode()) * 31) + this.jenisPemilihan.hashCode();
    }

    public String toString() {
        String str = this.witnessId;
        String str2 = this.kodeTps;
        return "WitnessQrCodeFragmentArgs(witnessId=" + str + ", kodeTps=" + str2 + ", jenisPemilihan=" + this.jenisPemilihan + ")";
    }

    public WitnessQrCodeFragmentArgs(String witnessId, String kodeTps, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(witnessId, "witnessId");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.witnessId = witnessId;
        this.kodeTps = kodeTps;
        this.jenisPemilihan = jenisPemilihan;
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

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("witnessId", this.witnessId);
        bundle.putString("kodeTps", this.kodeTps);
        bundle.putString("jenisPemilihan", this.jenisPemilihan);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("witnessId", this.witnessId);
        savedStateHandle.set("kodeTps", this.kodeTps);
        savedStateHandle.set("jenisPemilihan", this.jenisPemilihan);
        return savedStateHandle;
    }

    /* compiled from: WitnessQrCodeFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WitnessQrCodeFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(WitnessQrCodeFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("witnessId")) {
                String string = bundle.getString("witnessId");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"witnessId\" is marked as non-null but was passed a null value.");
                }
                if (bundle.containsKey("kodeTps")) {
                    String string2 = bundle.getString("kodeTps");
                    if (string2 == null) {
                        throw new IllegalArgumentException("Argument \"kodeTps\" is marked as non-null but was passed a null value.");
                    }
                    if (bundle.containsKey("jenisPemilihan")) {
                        String string3 = bundle.getString("jenisPemilihan");
                        if (string3 == null) {
                            throw new IllegalArgumentException("Argument \"jenisPemilihan\" is marked as non-null but was passed a null value.");
                        }
                        return new WitnessQrCodeFragmentArgs(string, string2, string3);
                    }
                    throw new IllegalArgumentException("Required argument \"jenisPemilihan\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"kodeTps\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"witnessId\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final WitnessQrCodeFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("witnessId")) {
                String str = (String) savedStateHandle.get("witnessId");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"witnessId\" is marked as non-null but was passed a null value");
                }
                if (savedStateHandle.contains("kodeTps")) {
                    String str2 = (String) savedStateHandle.get("kodeTps");
                    if (str2 == null) {
                        throw new IllegalArgumentException("Argument \"kodeTps\" is marked as non-null but was passed a null value");
                    }
                    if (savedStateHandle.contains("jenisPemilihan")) {
                        String str3 = (String) savedStateHandle.get("jenisPemilihan");
                        if (str3 == null) {
                            throw new IllegalArgumentException("Argument \"jenisPemilihan\" is marked as non-null but was passed a null value");
                        }
                        return new WitnessQrCodeFragmentArgs(str, str2, str3);
                    }
                    throw new IllegalArgumentException("Required argument \"jenisPemilihan\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"kodeTps\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"witnessId\" is missing and does not have an android:defaultValue");
        }
    }
}
