package org.informatika.sirekap.ui.witness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.databinding.ItemWitnessBinding;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.ui.witness.WitnessAdapter;

/* compiled from: WitnessAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002 !B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001bH\u0016R0\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0013@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/WitnessWithShare;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "jenisPemilihan", "", "mappingCache", "", "onClick", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "value", "", "filterJenisPemilihan", "getFilterJenisPemilihan", "()Ljava/util/List;", "setFilterJenisPemilihan", "(Ljava/util/List;)V", "", "isEmpty", "()Z", "setEmpty", "(Z)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "WitnessViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessAdapter extends ListAdapter<WitnessWithShare, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final WitnessAdapter$Companion$WITNESS_COMPARATOR$1 WITNESS_COMPARATOR = new DiffUtil.ItemCallback<WitnessWithShare>() { // from class: org.informatika.sirekap.ui.witness.WitnessAdapter$Companion$WITNESS_COMPARATOR$1
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
    private List<String> filterJenisPemilihan;
    private boolean isEmpty;
    private final String jenisPemilihan;
    private final Map<String, WitnessWithShare> mappingCache;
    private final Function1<WitnessWithShare, Unit> onClick;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WitnessAdapter(String jenisPemilihan, Map<String, WitnessWithShare> mappingCache, Function1<? super WitnessWithShare, Unit> onClick) {
        super(WITNESS_COMPARATOR);
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(mappingCache, "mappingCache");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        this.jenisPemilihan = jenisPemilihan;
        this.mappingCache = mappingCache;
        this.onClick = onClick;
        this.filterJenisPemilihan = CollectionsKt.emptyList();
    }

    public final List<String> getFilterJenisPemilihan() {
        return this.filterJenisPemilihan;
    }

    public final void setFilterJenisPemilihan(List<String> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.filterJenisPemilihan = value;
        notifyDataSetChanged();
    }

    public final boolean isEmpty() {
        return this.isEmpty;
    }

    public final void setEmpty(boolean z) {
        this.isEmpty = z;
        notifyDataSetChanged();
    }

    /* compiled from: WitnessAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessAdapter$Companion;", "", "()V", "WITNESS_COMPARATOR", "org/informatika/sirekap/ui/witness/WitnessAdapter$Companion$WITNESS_COMPARATOR$1", "Lorg/informatika/sirekap/ui/witness/WitnessAdapter$Companion$WITNESS_COMPARATOR$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
        ((WitnessViewHolder) holder).bind(getItem(i), this.jenisPemilihan, this.filterJenisPemilihan, this.onClick, this.isEmpty, this.mappingCache);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return WitnessViewHolder.Companion.create(parent);
    }

    /* compiled from: WitnessAdapter.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JV\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessAdapter$WitnessViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemWitnessBinding;", "(Lorg/informatika/sirekap/databinding/ItemWitnessBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemWitnessBinding;", "bind", "", "witness", "Lorg/informatika/sirekap/model/WitnessWithShare;", "jenisPemilihan", "", "filterJenisPemilihan", "", "onClick", "Lkotlin/Function1;", "isEmpty", "", "mappingCache", "", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class WitnessViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemWitnessBinding binding;

        public final ItemWitnessBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WitnessViewHolder(ItemWitnessBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final WitnessWithShare witnessWithShare, String jenisPemilihan, List<String> filterJenisPemilihan, final Function1<? super WitnessWithShare, Unit> onClick, boolean z, Map<String, WitnessWithShare> mappingCache) {
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(filterJenisPemilihan, "filterJenisPemilihan");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            Intrinsics.checkNotNullParameter(mappingCache, "mappingCache");
            if (witnessWithShare != null) {
                String noHandphone = witnessWithShare.getWitness().getNoHandphone();
                if (!mappingCache.containsKey(noHandphone + witnessWithShare.getWitness().getIdPetugas())) {
                    String noHandphone2 = witnessWithShare.getWitness().getNoHandphone();
                    mappingCache.put(noHandphone2 + witnessWithShare.getWitness().getIdPetugas(), witnessWithShare.toDecrypted());
                }
                ItemWitnessBinding itemWitnessBinding = this.binding;
                String noHandphone3 = witnessWithShare.getWitness().getNoHandphone();
                itemWitnessBinding.setWitness(mappingCache.get(noHandphone3 + witnessWithShare.getWitness().getIdPetugas()));
                this.binding.setIsEmpty(Boolean.valueOf(z));
                this.binding.linearLayout.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.WitnessAdapter$WitnessViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WitnessAdapter.WitnessViewHolder.bind$lambda$1$lambda$0(Function1.this, witnessWithShare, view);
                    }
                });
            }
        }

        public static final void bind$lambda$1$lambda$0(Function1 onClick, WitnessWithShare witnessWithShare, View view) {
            Intrinsics.checkNotNullParameter(onClick, "$onClick");
            onClick.invoke(witnessWithShare);
        }

        /* compiled from: WitnessAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessAdapter$WitnessViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/witness/WitnessAdapter$WitnessViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final WitnessViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemWitnessBinding inflate = ItemWitnessBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new WitnessViewHolder(inflate);
            }
        }
    }
}
