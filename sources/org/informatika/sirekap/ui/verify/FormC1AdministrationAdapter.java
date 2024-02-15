package org.informatika.sirekap.ui.verify;

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
import org.informatika.sirekap.databinding.ItemFormC1AdministrationBinding;
import org.informatika.sirekap.ui.verify.FormC1AdministrationAdapter;

/* compiled from: FormC1AdministrationAdapter.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0018\u0019B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u000f\u001a\u00020\bJ\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0013H\u0016R$\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "showCorrection", "", "onClickItem", "Lkotlin/Function1;", "", "(ZLkotlin/jvm/functions/Function1;)V", "value", "isDone", "()Z", "setDone", "(Z)V", "done", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ItemFormC1AdministrationViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class FormC1AdministrationAdapter extends ListAdapter<FormC1ListItem, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final FormC1AdministrationAdapter$Companion$DIFF_CALLBACK$1 DIFF_CALLBACK = new DiffUtil.ItemCallback<FormC1ListItem>() { // from class: org.informatika.sirekap.ui.verify.FormC1AdministrationAdapter$Companion$DIFF_CALLBACK$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(FormC1ListItem oldItem, FormC1ListItem newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getNo(), newItem.getNo());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(FormC1ListItem oldItem, FormC1ListItem newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };
    private boolean isDone;
    private final Function1<FormC1ListItem, Unit> onClickItem;
    private final boolean showCorrection;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FormC1AdministrationAdapter(boolean z, Function1<? super FormC1ListItem, Unit> onClickItem) {
        super(DIFF_CALLBACK);
        Intrinsics.checkNotNullParameter(onClickItem, "onClickItem");
        this.showCorrection = z;
        this.onClickItem = onClickItem;
    }

    /* compiled from: FormC1AdministrationAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "org/informatika/sirekap/ui/verify/FormC1AdministrationAdapter$Companion$DIFF_CALLBACK$1", "Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter$Companion$DIFF_CALLBACK$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
        ((ItemFormC1AdministrationViewHolder) holder).bind(getItem(i), this.onClickItem, this.isDone, this.showCorrection);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return ItemFormC1AdministrationViewHolder.Companion.create(parent);
    }

    public final boolean isDone() {
        return this.isDone;
    }

    public final void setDone(boolean z) {
        this.isDone = z;
        notifyDataSetChanged();
    }

    public final void done() {
        if (this.isDone) {
            return;
        }
        setDone(true);
    }

    /* compiled from: FormC1AdministrationAdapter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J4\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter$ItemFormC1AdministrationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemFormC1AdministrationBinding;", "(Lorg/informatika/sirekap/databinding/ItemFormC1AdministrationBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemFormC1AdministrationBinding;", "bind", "", "item", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "onClickItem", "Lkotlin/Function1;", "isDone", "", "showCorrection", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ItemFormC1AdministrationViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemFormC1AdministrationBinding binding;

        public final ItemFormC1AdministrationBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemFormC1AdministrationViewHolder(ItemFormC1AdministrationBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final FormC1ListItem formC1ListItem, final Function1<? super FormC1ListItem, Unit> onClickItem, final boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(onClickItem, "onClickItem");
            if (formC1ListItem != null) {
                this.binding.setItem(formC1ListItem);
                this.binding.linearLayout.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.FormC1AdministrationAdapter$ItemFormC1AdministrationViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormC1AdministrationAdapter.ItemFormC1AdministrationViewHolder.bind$lambda$1$lambda$0(z, onClickItem, formC1ListItem, view);
                    }
                });
                this.binding.setIsDone(Boolean.valueOf(z));
                this.binding.setShowCorrection(Boolean.valueOf(z2));
            }
        }

        public static final void bind$lambda$1$lambda$0(boolean z, Function1 onClickItem, FormC1ListItem this_apply, View view) {
            Intrinsics.checkNotNullParameter(onClickItem, "$onClickItem");
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (z) {
                return;
            }
            onClickItem.invoke(this_apply);
        }

        /* compiled from: FormC1AdministrationAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter$ItemFormC1AdministrationViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter$ItemFormC1AdministrationViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ItemFormC1AdministrationViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemFormC1AdministrationBinding inflate = ItemFormC1AdministrationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new ItemFormC1AdministrationViewHolder(inflate);
            }
        }
    }
}
