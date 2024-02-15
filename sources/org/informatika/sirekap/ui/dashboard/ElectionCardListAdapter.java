package org.informatika.sirekap.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.databinding.ItemElectionCardBinding;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.ui.dashboard.ElectionCardListAdapter;

/* compiled from: ElectionCardListAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0017\u0018Bn\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0012H\u0016R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onActionDetailElection", "Lkotlin/Function1;", "Lorg/informatika/sirekap/model/Election;", "Lkotlin/ParameterName;", "name", "election", "", "onActionWitness", "onActionTimeTps", "electionWithRelation", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ItemElectionCardViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionCardListAdapter extends ListAdapter<ElectionWithRelation, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final ElectionCardListAdapter$Companion$DIFF_CALLBACK$1 DIFF_CALLBACK = new DiffUtil.ItemCallback<ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.dashboard.ElectionCardListAdapter$Companion$DIFF_CALLBACK$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(ElectionWithRelation oldItem, ElectionWithRelation newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getElection().getId(), newItem.getElection().getId());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(ElectionWithRelation oldItem, ElectionWithRelation newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };
    private final Function1<Election, Unit> onActionDetailElection;
    private final Function1<ElectionWithRelation, Unit> onActionTimeTps;
    private final Function1<Election, Unit> onActionWitness;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ElectionCardListAdapter(Function1<? super Election, Unit> onActionDetailElection, Function1<? super Election, Unit> onActionWitness, Function1<? super ElectionWithRelation, Unit> onActionTimeTps) {
        super(DIFF_CALLBACK);
        Intrinsics.checkNotNullParameter(onActionDetailElection, "onActionDetailElection");
        Intrinsics.checkNotNullParameter(onActionWitness, "onActionWitness");
        Intrinsics.checkNotNullParameter(onActionTimeTps, "onActionTimeTps");
        this.onActionDetailElection = onActionDetailElection;
        this.onActionWitness = onActionWitness;
        this.onActionTimeTps = onActionTimeTps;
    }

    /* compiled from: ElectionCardListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "org/informatika/sirekap/ui/dashboard/ElectionCardListAdapter$Companion$DIFF_CALLBACK$1", "Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter$Companion$DIFF_CALLBACK$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
        ElectionWithRelation item = getItem(i);
        Intrinsics.checkNotNullExpressionValue(item, "getItem(position)");
        ((ItemElectionCardViewHolder) holder).bind(item, this.onActionDetailElection, this.onActionWitness, this.onActionTimeTps);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return ItemElectionCardViewHolder.Companion.create(parent);
    }

    /* compiled from: ElectionCardListAdapter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Jw\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\f2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\f2!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\b0\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter$ItemElectionCardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemElectionCardBinding;", "(Lorg/informatika/sirekap/databinding/ItemElectionCardBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemElectionCardBinding;", "bind", "", "electionWithRelation", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "onActionDetailElection", "Lkotlin/Function1;", "Lorg/informatika/sirekap/model/Election;", "Lkotlin/ParameterName;", "name", "election", "onActionWitness", "onActionTimeTps", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ItemElectionCardViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemElectionCardBinding binding;

        public final ItemElectionCardBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemElectionCardViewHolder(ItemElectionCardBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final ElectionWithRelation electionWithRelation, final Function1<? super Election, Unit> onActionDetailElection, final Function1<? super Election, Unit> onActionWitness, final Function1<? super ElectionWithRelation, Unit> onActionTimeTps) {
            Intrinsics.checkNotNullParameter(electionWithRelation, "electionWithRelation");
            Intrinsics.checkNotNullParameter(onActionDetailElection, "onActionDetailElection");
            Intrinsics.checkNotNullParameter(onActionWitness, "onActionWitness");
            Intrinsics.checkNotNullParameter(onActionTimeTps, "onActionTimeTps");
            this.binding.setElectionWithRelation(electionWithRelation);
            this.binding.buttonDetailElection.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.ElectionCardListAdapter$ItemElectionCardViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ElectionCardListAdapter.ItemElectionCardViewHolder.bind$lambda$0(Function1.this, electionWithRelation, view);
                }
            });
            this.binding.buttonWitness.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.ElectionCardListAdapter$ItemElectionCardViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ElectionCardListAdapter.ItemElectionCardViewHolder.bind$lambda$1(Function1.this, electionWithRelation, view);
                }
            });
            this.binding.buttonTimeTps.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.ElectionCardListAdapter$ItemElectionCardViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ElectionCardListAdapter.ItemElectionCardViewHolder.bind$lambda$2(Function1.this, electionWithRelation, view);
                }
            });
        }

        public static final void bind$lambda$0(Function1 onActionDetailElection, ElectionWithRelation electionWithRelation, View view) {
            Intrinsics.checkNotNullParameter(onActionDetailElection, "$onActionDetailElection");
            Intrinsics.checkNotNullParameter(electionWithRelation, "$electionWithRelation");
            onActionDetailElection.invoke(electionWithRelation.getElection());
        }

        public static final void bind$lambda$1(Function1 onActionWitness, ElectionWithRelation electionWithRelation, View view) {
            Intrinsics.checkNotNullParameter(onActionWitness, "$onActionWitness");
            Intrinsics.checkNotNullParameter(electionWithRelation, "$electionWithRelation");
            onActionWitness.invoke(electionWithRelation.getElection());
        }

        public static final void bind$lambda$2(Function1 onActionTimeTps, ElectionWithRelation electionWithRelation, View view) {
            Intrinsics.checkNotNullParameter(onActionTimeTps, "$onActionTimeTps");
            Intrinsics.checkNotNullParameter(electionWithRelation, "$electionWithRelation");
            onActionTimeTps.invoke(electionWithRelation);
        }

        /* compiled from: ElectionCardListAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter$ItemElectionCardViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter$ItemElectionCardViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ItemElectionCardViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemElectionCardBinding inflate = ItemElectionCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new ItemElectionCardViewHolder(inflate);
            }
        }
    }
}
