package org.informatika.sirekap.ui.verify;

import android.graphics.Bitmap;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseVerifyViewModel.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJB\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u0005J\u0006\u0010&\u001a\u00020\u001eR\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001f\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u001f\u0010\r\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u001f\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u001f\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006("}, d2 = {"Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "correctedL", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getCorrectedL", "()Landroidx/lifecycle/MutableLiveData;", "correctedP", "getCorrectedP", "correctedTotal", "getCorrectedTotal", "isConfirmationChecked", "", "isWrongL", "isWrongP", "isWrongTotal", "kesesuaianSliceError", "getKesesuaianSliceError", "previewKesesuaianBitmap", "Landroid/graphics/Bitmap;", "getPreviewKesesuaianBitmap", "showKesesuaianSliceError", "getShowKesesuaianSliceError", "verifyForm", "Lorg/informatika/sirekap/ui/verify/VerifyFormState;", "getVerifyForm", "()Lorg/informatika/sirekap/ui/verify/VerifyFormState;", "resetImage", "", "setupCheckDialog", "wrongL", "wrongP", "wrongTotal", "_correctedL", "_correctedP", "_correctedTotal", "toggleKesesuaian", "VerifyFormC1Model", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class BaseVerifyViewModel extends ViewModel {
    private final VerifyFormState verifyForm = new VerifyFormState();
    private final MutableLiveData<Boolean> isConfirmationChecked = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isWrongL = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isWrongP = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isWrongTotal = new MutableLiveData<>(false);
    private final MutableLiveData<String> correctedL = new MutableLiveData<>("");
    private final MutableLiveData<String> correctedP = new MutableLiveData<>("");
    private final MutableLiveData<String> correctedTotal = new MutableLiveData<>("");
    private final MutableLiveData<Bitmap> previewKesesuaianBitmap = new MutableLiveData<>(null);
    private final MutableLiveData<String> kesesuaianSliceError = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> showKesesuaianSliceError = new MutableLiveData<>(false);

    public final VerifyFormState getVerifyForm() {
        return this.verifyForm;
    }

    public final MutableLiveData<Boolean> isConfirmationChecked() {
        return this.isConfirmationChecked;
    }

    /* compiled from: BaseVerifyViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003Jk\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\nHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0012R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel$VerifyFormC1Model;", "", "idImage", "", "isSesuai", "", "isSesuaiPerItem", "", "komentar", "koreksiPerItem", "", "deviceId", "jenisPemilihan", "kodeTps", "(Ljava/lang/String;ZLjava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceId", "()Ljava/lang/String;", "getIdImage", "()Z", "()Ljava/util/List;", "getJenisPemilihan", "getKodeTps", "getKomentar", "getKoreksiPerItem", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class VerifyFormC1Model {
        private final String deviceId;
        private final String idImage;
        private final boolean isSesuai;
        private final List<Boolean> isSesuaiPerItem;
        private final String jenisPemilihan;
        private final String kodeTps;
        private final String komentar;
        private final List<Integer> koreksiPerItem;

        public final String component1() {
            return this.idImage;
        }

        public final boolean component2() {
            return this.isSesuai;
        }

        public final List<Boolean> component3() {
            return this.isSesuaiPerItem;
        }

        public final String component4() {
            return this.komentar;
        }

        public final List<Integer> component5() {
            return this.koreksiPerItem;
        }

        public final String component6() {
            return this.deviceId;
        }

        public final String component7() {
            return this.jenisPemilihan;
        }

        public final String component8() {
            return this.kodeTps;
        }

        public final VerifyFormC1Model copy(String idImage, boolean z, List<Boolean> isSesuaiPerItem, String str, List<Integer> koreksiPerItem, String deviceId, String jenisPemilihan, String kodeTps) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return new VerifyFormC1Model(idImage, z, isSesuaiPerItem, str, koreksiPerItem, deviceId, jenisPemilihan, kodeTps);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof VerifyFormC1Model) {
                VerifyFormC1Model verifyFormC1Model = (VerifyFormC1Model) obj;
                return Intrinsics.areEqual(this.idImage, verifyFormC1Model.idImage) && this.isSesuai == verifyFormC1Model.isSesuai && Intrinsics.areEqual(this.isSesuaiPerItem, verifyFormC1Model.isSesuaiPerItem) && Intrinsics.areEqual(this.komentar, verifyFormC1Model.komentar) && Intrinsics.areEqual(this.koreksiPerItem, verifyFormC1Model.koreksiPerItem) && Intrinsics.areEqual(this.deviceId, verifyFormC1Model.deviceId) && Intrinsics.areEqual(this.jenisPemilihan, verifyFormC1Model.jenisPemilihan) && Intrinsics.areEqual(this.kodeTps, verifyFormC1Model.kodeTps);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = this.idImage.hashCode() * 31;
            boolean z = this.isSesuai;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int hashCode2 = (((hashCode + i) * 31) + this.isSesuaiPerItem.hashCode()) * 31;
            String str = this.komentar;
            return ((((((((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + this.koreksiPerItem.hashCode()) * 31) + this.deviceId.hashCode()) * 31) + this.jenisPemilihan.hashCode()) * 31) + this.kodeTps.hashCode();
        }

        public String toString() {
            String str = this.idImage;
            boolean z = this.isSesuai;
            List<Boolean> list = this.isSesuaiPerItem;
            String str2 = this.komentar;
            List<Integer> list2 = this.koreksiPerItem;
            String str3 = this.deviceId;
            String str4 = this.jenisPemilihan;
            return "VerifyFormC1Model(idImage=" + str + ", isSesuai=" + z + ", isSesuaiPerItem=" + list + ", komentar=" + str2 + ", koreksiPerItem=" + list2 + ", deviceId=" + str3 + ", jenisPemilihan=" + str4 + ", kodeTps=" + this.kodeTps + ")";
        }

        public VerifyFormC1Model(String idImage, boolean z, List<Boolean> isSesuaiPerItem, String str, List<Integer> koreksiPerItem, String deviceId, String jenisPemilihan, String kodeTps) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            this.idImage = idImage;
            this.isSesuai = z;
            this.isSesuaiPerItem = isSesuaiPerItem;
            this.komentar = str;
            this.koreksiPerItem = koreksiPerItem;
            this.deviceId = deviceId;
            this.jenisPemilihan = jenisPemilihan;
            this.kodeTps = kodeTps;
        }

        public final String getIdImage() {
            return this.idImage;
        }

        public final boolean isSesuai() {
            return this.isSesuai;
        }

        public final List<Boolean> isSesuaiPerItem() {
            return this.isSesuaiPerItem;
        }

        public final String getKomentar() {
            return this.komentar;
        }

        public /* synthetic */ VerifyFormC1Model(String str, boolean z, List list, String str2, List list2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, z, list, str2, (i & 16) != 0 ? CollectionsKt.emptyList() : list2, str3, str4, str5);
        }

        public final List<Integer> getKoreksiPerItem() {
            return this.koreksiPerItem;
        }

        public final String getDeviceId() {
            return this.deviceId;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }
    }

    public final MutableLiveData<Boolean> isWrongL() {
        return this.isWrongL;
    }

    public final MutableLiveData<Boolean> isWrongP() {
        return this.isWrongP;
    }

    public final MutableLiveData<Boolean> isWrongTotal() {
        return this.isWrongTotal;
    }

    public final MutableLiveData<String> getCorrectedL() {
        return this.correctedL;
    }

    public final MutableLiveData<String> getCorrectedP() {
        return this.correctedP;
    }

    public final MutableLiveData<String> getCorrectedTotal() {
        return this.correctedTotal;
    }

    public static /* synthetic */ void setupCheckDialog$default(BaseVerifyViewModel baseVerifyViewModel, boolean z, boolean z2, boolean z3, String str, String str2, String str3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setupCheckDialog");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            str = "";
        }
        if ((i & 16) != 0) {
            str2 = "";
        }
        if ((i & 32) != 0) {
            str3 = "";
        }
        baseVerifyViewModel.setupCheckDialog(z, z2, z3, str, str2, str3);
    }

    public final void setupCheckDialog(boolean z, boolean z2, boolean z3, String _correctedL, String _correctedP, String _correctedTotal) {
        Intrinsics.checkNotNullParameter(_correctedL, "_correctedL");
        Intrinsics.checkNotNullParameter(_correctedP, "_correctedP");
        Intrinsics.checkNotNullParameter(_correctedTotal, "_correctedTotal");
        this.isWrongL.postValue(Boolean.valueOf(z));
        this.isWrongP.postValue(Boolean.valueOf(z2));
        this.isWrongTotal.postValue(Boolean.valueOf(z3));
        this.correctedL.postValue(_correctedL);
        this.correctedP.postValue(_correctedP);
        this.correctedTotal.postValue(_correctedTotal);
    }

    public final MutableLiveData<Bitmap> getPreviewKesesuaianBitmap() {
        return this.previewKesesuaianBitmap;
    }

    public final MutableLiveData<String> getKesesuaianSliceError() {
        return this.kesesuaianSliceError;
    }

    public final MutableLiveData<Boolean> getShowKesesuaianSliceError() {
        return this.showKesesuaianSliceError;
    }

    public final void toggleKesesuaian() {
        if (Intrinsics.areEqual((Object) this.showKesesuaianSliceError.getValue(), (Object) true)) {
            this.showKesesuaianSliceError.postValue(false);
        } else {
            this.showKesesuaianSliceError.postValue(true);
        }
    }

    public final void resetImage() {
        this.previewKesesuaianBitmap.postValue(null);
        this.kesesuaianSliceError.postValue(null);
        this.showKesesuaianSliceError.postValue(false);
    }
}
