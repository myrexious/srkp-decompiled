package org.informatika.sirekap.ui.tpsTime;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TpsTimeFragmentArgs.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragmentArgs;", "Landroidx/navigation/NavArgs;", "kodeTps", "", "jenisWaktu", "", "jenisPemilihan", "isElectionZipped", "", "(Ljava/lang/String;ILjava/lang/String;Z)V", "()Z", "getJenisPemilihan", "()Ljava/lang/String;", "getJenisWaktu", "()I", "getKodeTps", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class TpsTimeFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final boolean isElectionZipped;
    private final String jenisPemilihan;
    private final int jenisWaktu;
    private final String kodeTps;

    public static /* synthetic */ TpsTimeFragmentArgs copy$default(TpsTimeFragmentArgs tpsTimeFragmentArgs, String str, int i, String str2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = tpsTimeFragmentArgs.kodeTps;
        }
        if ((i2 & 2) != 0) {
            i = tpsTimeFragmentArgs.jenisWaktu;
        }
        if ((i2 & 4) != 0) {
            str2 = tpsTimeFragmentArgs.jenisPemilihan;
        }
        if ((i2 & 8) != 0) {
            z = tpsTimeFragmentArgs.isElectionZipped;
        }
        return tpsTimeFragmentArgs.copy(str, i, str2, z);
    }

    @JvmStatic
    public static final TpsTimeFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final TpsTimeFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
    }

    public final String component1() {
        return this.kodeTps;
    }

    public final int component2() {
        return this.jenisWaktu;
    }

    public final String component3() {
        return this.jenisPemilihan;
    }

    public final boolean component4() {
        return this.isElectionZipped;
    }

    public final TpsTimeFragmentArgs copy(String kodeTps, int i, String jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new TpsTimeFragmentArgs(kodeTps, i, jenisPemilihan, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TpsTimeFragmentArgs) {
            TpsTimeFragmentArgs tpsTimeFragmentArgs = (TpsTimeFragmentArgs) obj;
            return Intrinsics.areEqual(this.kodeTps, tpsTimeFragmentArgs.kodeTps) && this.jenisWaktu == tpsTimeFragmentArgs.jenisWaktu && Intrinsics.areEqual(this.jenisPemilihan, tpsTimeFragmentArgs.jenisPemilihan) && this.isElectionZipped == tpsTimeFragmentArgs.isElectionZipped;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.kodeTps.hashCode() * 31) + Integer.hashCode(this.jenisWaktu)) * 31) + this.jenisPemilihan.hashCode()) * 31;
        boolean z = this.isElectionZipped;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.kodeTps;
        int i = this.jenisWaktu;
        String str2 = this.jenisPemilihan;
        return "TpsTimeFragmentArgs(kodeTps=" + str + ", jenisWaktu=" + i + ", jenisPemilihan=" + str2 + ", isElectionZipped=" + this.isElectionZipped + ")";
    }

    public TpsTimeFragmentArgs(String kodeTps, int i, String jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.kodeTps = kodeTps;
        this.jenisWaktu = i;
        this.jenisPemilihan = jenisPemilihan;
        this.isElectionZipped = z;
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final int getJenisWaktu() {
        return this.jenisWaktu;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final boolean isElectionZipped() {
        return this.isElectionZipped;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("kodeTps", this.kodeTps);
        bundle.putInt("jenisWaktu", this.jenisWaktu);
        bundle.putString("jenisPemilihan", this.jenisPemilihan);
        bundle.putBoolean("isElectionZipped", this.isElectionZipped);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("kodeTps", this.kodeTps);
        savedStateHandle.set("jenisWaktu", Integer.valueOf(this.jenisWaktu));
        savedStateHandle.set("jenisPemilihan", this.jenisPemilihan);
        savedStateHandle.set("isElectionZipped", Boolean.valueOf(this.isElectionZipped));
        return savedStateHandle;
    }

    /* compiled from: TpsTimeFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final TpsTimeFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(TpsTimeFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("kodeTps")) {
                String string = bundle.getString("kodeTps");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"kodeTps\" is marked as non-null but was passed a null value.");
                }
                if (bundle.containsKey("jenisWaktu")) {
                    int i = bundle.getInt("jenisWaktu");
                    if (bundle.containsKey("jenisPemilihan")) {
                        String string2 = bundle.getString("jenisPemilihan");
                        if (string2 == null) {
                            throw new IllegalArgumentException("Argument \"jenisPemilihan\" is marked as non-null but was passed a null value.");
                        }
                        if (!bundle.containsKey("isElectionZipped")) {
                            throw new IllegalArgumentException("Required argument \"isElectionZipped\" is missing and does not have an android:defaultValue");
                        }
                        return new TpsTimeFragmentArgs(string, i, string2, bundle.getBoolean("isElectionZipped"));
                    }
                    throw new IllegalArgumentException("Required argument \"jenisPemilihan\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"jenisWaktu\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"kodeTps\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final TpsTimeFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("kodeTps")) {
                String str = (String) savedStateHandle.get("kodeTps");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"kodeTps\" is marked as non-null but was passed a null value");
                }
                if (savedStateHandle.contains("jenisWaktu")) {
                    Integer num = (Integer) savedStateHandle.get("jenisWaktu");
                    if (num == null) {
                        throw new IllegalArgumentException("Argument \"jenisWaktu\" of type integer does not support null values");
                    }
                    if (savedStateHandle.contains("jenisPemilihan")) {
                        String str2 = (String) savedStateHandle.get("jenisPemilihan");
                        if (str2 == null) {
                            throw new IllegalArgumentException("Argument \"jenisPemilihan\" is marked as non-null but was passed a null value");
                        }
                        if (savedStateHandle.contains("isElectionZipped")) {
                            Boolean bool = (Boolean) savedStateHandle.get("isElectionZipped");
                            if (bool == null) {
                                throw new IllegalArgumentException("Argument \"isElectionZipped\" of type boolean does not support null values");
                            }
                            return new TpsTimeFragmentArgs(str, num.intValue(), str2, bool.booleanValue());
                        }
                        throw new IllegalArgumentException("Required argument \"isElectionZipped\" is missing and does not have an android:defaultValue");
                    }
                    throw new IllegalArgumentException("Required argument \"jenisPemilihan\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Required argument \"jenisWaktu\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"kodeTps\" is missing and does not have an android:defaultValue");
        }
    }
}
