package org.informatika.sirekap.ui.electionDetail;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ElectionDetailFragmentArgs.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentArgs;", "Landroidx/navigation/NavArgs;", "electionId", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;)V", "getElectionId", "()Ljava/lang/String;", "getJenisPemilihan", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionDetailFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final String electionId;
    private final String jenisPemilihan;

    public static /* synthetic */ ElectionDetailFragmentArgs copy$default(ElectionDetailFragmentArgs electionDetailFragmentArgs, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = electionDetailFragmentArgs.electionId;
        }
        if ((i & 2) != 0) {
            str2 = electionDetailFragmentArgs.jenisPemilihan;
        }
        return electionDetailFragmentArgs.copy(str, str2);
    }

    @JvmStatic
    public static final ElectionDetailFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @JvmStatic
    public static final ElectionDetailFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        return Companion.fromSavedStateHandle(savedStateHandle);
    }

    public final String component1() {
        return this.electionId;
    }

    public final String component2() {
        return this.jenisPemilihan;
    }

    public final ElectionDetailFragmentArgs copy(String electionId, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new ElectionDetailFragmentArgs(electionId, jenisPemilihan);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionDetailFragmentArgs) {
            ElectionDetailFragmentArgs electionDetailFragmentArgs = (ElectionDetailFragmentArgs) obj;
            return Intrinsics.areEqual(this.electionId, electionDetailFragmentArgs.electionId) && Intrinsics.areEqual(this.jenisPemilihan, electionDetailFragmentArgs.jenisPemilihan);
        }
        return false;
    }

    public int hashCode() {
        return (this.electionId.hashCode() * 31) + this.jenisPemilihan.hashCode();
    }

    public String toString() {
        String str = this.electionId;
        return "ElectionDetailFragmentArgs(electionId=" + str + ", jenisPemilihan=" + this.jenisPemilihan + ")";
    }

    public ElectionDetailFragmentArgs(String electionId, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.electionId = electionId;
        this.jenisPemilihan = jenisPemilihan;
    }

    public final String getElectionId() {
        return this.electionId;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("electionId", this.electionId);
        bundle.putString("jenisPemilihan", this.jenisPemilihan);
        return bundle;
    }

    public final SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        savedStateHandle.set("electionId", this.electionId);
        savedStateHandle.set("jenisPemilihan", this.jenisPemilihan);
        return savedStateHandle;
    }

    /* compiled from: ElectionDetailFragmentArgs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentArgs$Companion;", "", "()V", "fromBundle", "Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ElectionDetailFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(ElectionDetailFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("electionId")) {
                String string = bundle.getString("electionId");
                if (string == null) {
                    throw new IllegalArgumentException("Argument \"electionId\" is marked as non-null but was passed a null value.");
                }
                if (bundle.containsKey("jenisPemilihan")) {
                    String string2 = bundle.getString("jenisPemilihan");
                    if (string2 == null) {
                        throw new IllegalArgumentException("Argument \"jenisPemilihan\" is marked as non-null but was passed a null value.");
                    }
                    return new ElectionDetailFragmentArgs(string, string2);
                }
                throw new IllegalArgumentException("Required argument \"jenisPemilihan\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"electionId\" is missing and does not have an android:defaultValue");
        }

        @JvmStatic
        public final ElectionDetailFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
            Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
            if (savedStateHandle.contains("electionId")) {
                String str = (String) savedStateHandle.get("electionId");
                if (str == null) {
                    throw new IllegalArgumentException("Argument \"electionId\" is marked as non-null but was passed a null value");
                }
                if (savedStateHandle.contains("jenisPemilihan")) {
                    String str2 = (String) savedStateHandle.get("jenisPemilihan");
                    if (str2 == null) {
                        throw new IllegalArgumentException("Argument \"jenisPemilihan\" is marked as non-null but was passed a null value");
                    }
                    return new ElectionDetailFragmentArgs(str, str2);
                }
                throw new IllegalArgumentException("Required argument \"jenisPemilihan\" is missing and does not have an android:defaultValue");
            }
            throw new IllegalArgumentException("Required argument \"electionId\" is missing and does not have an android:defaultValue");
        }
    }
}
