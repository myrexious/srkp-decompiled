package org.informatika.sirekap.ui.witness.qrCode;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.WitnessUtil;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListAdapter;

/* compiled from: WitnessUrlListAdapter.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002-.Bõ\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00126\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t\u00126\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\t\u00126\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\t\u00126\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0002\u0010\u0012J\u0018\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(H\u0016J\u0018\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020(H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0019@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR>\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006/"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/WitnessWithShare;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "jenisPemilihan", "", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "onShareUrl", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "witnessId", "", "onCopyUrl", "url", "onCardUrlClick", "onCardUrlLongClick", "(Ljava/lang/String;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "value", "filterJenisPemilihan", "getFilterJenisPemilihan", "()Ljava/lang/String;", "setFilterJenisPemilihan", "(Ljava/lang/String;)V", "", "isZipped", "()Z", "setZipped", "(Z)V", "", "Lorg/informatika/sirekap/model/Candidate;", "paslons", "getPaslons", "()Ljava/util/List;", "setPaslons", "(Ljava/util/List;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "WitnessViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessUrlListAdapter extends ListAdapter<WitnessWithShare, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final WitnessUrlListAdapter$Companion$WITNESS_COMPARATOR$1 WITNESS_COMPARATOR = new DiffUtil.ItemCallback<WitnessWithShare>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListAdapter$Companion$WITNESS_COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(WitnessWithShare oldItem, WitnessWithShare newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return oldItem.getWitness().getIdPetugas() == newItem.getWitness().getIdPetugas();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(WitnessWithShare oldItem, WitnessWithShare newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private String filterJenisPemilihan;
    private boolean isZipped;
    private final Function2<String, String, Unit> onCardUrlClick;
    private final Function2<String, String, Unit> onCardUrlLongClick;
    private final Function2<String, String, Unit> onCopyUrl;
    private final Function2<String, String, Unit> onShareUrl;
    private List<Candidate> paslons;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WitnessUrlListAdapter(String jenisPemilihan, EncryptedSharedPreferences encryptedSharedPreferences, Function2<? super String, ? super String, Unit> onShareUrl, Function2<? super String, ? super String, Unit> onCopyUrl, Function2<? super String, ? super String, Unit> onCardUrlClick, Function2<? super String, ? super String, Unit> onCardUrlLongClick) {
        super(WITNESS_COMPARATOR);
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(onShareUrl, "onShareUrl");
        Intrinsics.checkNotNullParameter(onCopyUrl, "onCopyUrl");
        Intrinsics.checkNotNullParameter(onCardUrlClick, "onCardUrlClick");
        Intrinsics.checkNotNullParameter(onCardUrlLongClick, "onCardUrlLongClick");
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.onShareUrl = onShareUrl;
        this.onCopyUrl = onCopyUrl;
        this.onCardUrlClick = onCardUrlClick;
        this.onCardUrlLongClick = onCardUrlLongClick;
        this.filterJenisPemilihan = jenisPemilihan;
        this.paslons = CollectionsKt.emptyList();
    }

    public final String getFilterJenisPemilihan() {
        return this.filterJenisPemilihan;
    }

    public final void setFilterJenisPemilihan(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.filterJenisPemilihan = value;
        notifyDataSetChanged();
    }

    public final boolean isZipped() {
        return this.isZipped;
    }

    public final void setZipped(boolean z) {
        this.isZipped = z;
        notifyDataSetChanged();
    }

    public final List<Candidate> getPaslons() {
        return this.paslons;
    }

    public final void setPaslons(List<Candidate> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.paslons = value;
        notifyDataSetChanged();
    }

    /* compiled from: WitnessUrlListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter$Companion;", "", "()V", "WITNESS_COMPARATOR", "org/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter$Companion$WITNESS_COMPARATOR$1", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter$Companion$WITNESS_COMPARATOR$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ((WitnessViewHolder) holder).bind(getItem(i), this.filterJenisPemilihan, this.isZipped, this.paslons, this.encryptedSharedPreferences, this.onShareUrl, this.onCopyUrl, this.onCardUrlClick, this.onCardUrlLongClick);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return WitnessViewHolder.Companion.create(parent);
    }

    /* compiled from: WitnessUrlListAdapter.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0096\u0002\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u001326\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\b0\u001526\u0010\u0019\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\b0\u001526\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\b0\u001526\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\b0\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter$WitnessViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ViewWitnessSharePpsBinding;", "(Lorg/informatika/sirekap/databinding/ViewWitnessSharePpsBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ViewWitnessSharePpsBinding;", "bind", "", "witness", "Lorg/informatika/sirekap/model/WitnessWithShare;", "jenisPemilihan", "", "isZipped", "", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "onShareUrl", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "witnessId", "onCopyUrl", "url", "onCardUrlClick", "onCardUrlLongClick", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class WitnessViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ViewWitnessSharePpsBinding binding;

        public final ViewWitnessSharePpsBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WitnessViewHolder(ViewWitnessSharePpsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final WitnessWithShare witnessWithShare, final String jenisPemilihan, boolean z, List<Candidate> candidates, EncryptedSharedPreferences encryptedSharedPreferences, final Function2<? super String, ? super String, Unit> onShareUrl, final Function2<? super String, ? super String, Unit> onCopyUrl, final Function2<? super String, ? super String, Unit> onCardUrlClick, final Function2<? super String, ? super String, Unit> onCardUrlLongClick) {
            WitnessPemeriksa witnessPemeriksa;
            boolean z2;
            Object obj;
            Object obj2;
            final String generateWitnessShareUrlOnline;
            String url;
            boolean z3;
            Object obj3;
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
            Intrinsics.checkNotNullParameter(onShareUrl, "onShareUrl");
            Intrinsics.checkNotNullParameter(onCopyUrl, "onCopyUrl");
            Intrinsics.checkNotNullParameter(onCardUrlClick, "onCardUrlClick");
            Intrinsics.checkNotNullParameter(onCardUrlLongClick, "onCardUrlLongClick");
            if (witnessWithShare != null) {
                this.binding.setWitness(witnessWithShare);
                this.binding.setFilterJenisPemilihan(jenisPemilihan);
                this.binding.setIsZipped(Boolean.valueOf(z));
                List<WitnessPemeriksa> pemeriksas = witnessWithShare.getPemeriksas();
                if (pemeriksas != null) {
                    Iterator<T> it = pemeriksas.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj3 = null;
                            break;
                        }
                        obj3 = it.next();
                        if (Intrinsics.areEqual(((WitnessPemeriksa) obj3).getJenisPemilihan(), jenisPemilihan)) {
                            break;
                        }
                    }
                    witnessPemeriksa = (WitnessPemeriksa) obj3;
                } else {
                    witnessPemeriksa = null;
                }
                List<Candidate> list = candidates;
                Iterator<T> it2 = list.iterator();
                while (true) {
                    z2 = true;
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it2.next();
                    Candidate candidate = (Candidate) obj;
                    if (witnessPemeriksa == null || candidate.getIdPilihan() != witnessPemeriksa.getIdPilihan()) {
                        z3 = false;
                        continue;
                    } else {
                        z3 = true;
                        continue;
                    }
                    if (z3) {
                        break;
                    }
                }
                Candidate candidate2 = (Candidate) obj;
                this.binding.setPaslon(candidate2 != null ? candidate2.getNama() : null);
                Iterator<T> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it3.next();
                    if (Intrinsics.areEqual(((Candidate) obj2).getIdPartai(), witnessPemeriksa != null ? Long.valueOf(witnessPemeriksa.getIdPilihan()) : null)) {
                        break;
                    }
                }
                Candidate candidate3 = (Candidate) obj2;
                this.binding.setPartai(candidate3 != null ? candidate3.getNamaPartai() : null);
                String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                String str = "";
                if (stringEncrypted == null) {
                    stringEncrypted = "";
                }
                String url2 = witnessPemeriksa != null ? witnessPemeriksa.getUrl() : null;
                if (url2 != null && !StringsKt.isBlank(url2)) {
                    z2 = false;
                }
                if (z2) {
                    generateWitnessShareUrlOnline = WitnessUtil.Companion.generateWitnessShareUrlOffline(stringEncrypted, jenisPemilihan, witnessWithShare.getWitness().getKodeTps());
                } else {
                    WitnessUtil.Companion companion = WitnessUtil.Companion;
                    if (witnessPemeriksa != null && (url = witnessPemeriksa.getUrl()) != null) {
                        str = url;
                    }
                    generateWitnessShareUrlOnline = companion.generateWitnessShareUrlOnline(stringEncrypted, jenisPemilihan, str);
                }
                this.binding.setUrl(generateWitnessShareUrlOnline);
                BitMatrix encode = new QRCodeWriter().encode(generateWitnessShareUrlOnline, BarcodeFormat.QR_CODE, 512, 512);
                int width = encode.getWidth();
                int height = encode.getHeight();
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                for (int i = 0; i < width; i++) {
                    for (int i2 = 0; i2 < height; i2++) {
                        createBitmap.setPixel(i, i2, encode.get(i, i2) ? ViewCompat.MEASURED_STATE_MASK : -1);
                    }
                }
                this.binding.setQrCode(createBitmap);
                this.binding.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListAdapter$WitnessViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WitnessUrlListAdapter.WitnessViewHolder.bind$lambda$8$lambda$3(Function2.this, jenisPemilihan, witnessWithShare, view);
                    }
                });
                this.binding.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListAdapter$WitnessViewHolder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WitnessUrlListAdapter.WitnessViewHolder.bind$lambda$8$lambda$4(Function2.this, generateWitnessShareUrlOnline, jenisPemilihan, view);
                    }
                });
                MaterialCardView materialCardView = this.binding.cardUrl;
                materialCardView.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListAdapter$WitnessViewHolder$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WitnessUrlListAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$5(Function2.this, generateWitnessShareUrlOnline, jenisPemilihan, view);
                    }
                });
                materialCardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListAdapter$WitnessViewHolder$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        boolean bind$lambda$8$lambda$7$lambda$6;
                        bind$lambda$8$lambda$7$lambda$6 = WitnessUrlListAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$6(Function2.this, generateWitnessShareUrlOnline, jenisPemilihan, view);
                        return bind$lambda$8$lambda$7$lambda$6;
                    }
                });
            }
        }

        public static final void bind$lambda$8$lambda$3(Function2 onShareUrl, String jenisPemilihan, WitnessWithShare witnessWithShare, View view) {
            Intrinsics.checkNotNullParameter(onShareUrl, "$onShareUrl");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
            onShareUrl.invoke(jenisPemilihan, witnessWithShare.getWitness().getNoHandphone());
        }

        public static final void bind$lambda$8$lambda$4(Function2 onCopyUrl, String url, String jenisPemilihan, View view) {
            Intrinsics.checkNotNullParameter(onCopyUrl, "$onCopyUrl");
            Intrinsics.checkNotNullParameter(url, "$url");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
            onCopyUrl.invoke(url, jenisPemilihan);
        }

        public static final void bind$lambda$8$lambda$7$lambda$5(Function2 onCardUrlClick, String url, String jenisPemilihan, View view) {
            Intrinsics.checkNotNullParameter(onCardUrlClick, "$onCardUrlClick");
            Intrinsics.checkNotNullParameter(url, "$url");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
            onCardUrlClick.invoke(url, jenisPemilihan);
        }

        public static final boolean bind$lambda$8$lambda$7$lambda$6(Function2 onCardUrlLongClick, String url, String jenisPemilihan, View view) {
            Intrinsics.checkNotNullParameter(onCardUrlLongClick, "$onCardUrlLongClick");
            Intrinsics.checkNotNullParameter(url, "$url");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
            onCardUrlLongClick.invoke(url, jenisPemilihan);
            return true;
        }

        /* compiled from: WitnessUrlListAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter$WitnessViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter$WitnessViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final WitnessViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ViewWitnessSharePpsBinding inflate = ViewWitnessSharePpsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new WitnessViewHolder(inflate);
            }
        }
    }
}
