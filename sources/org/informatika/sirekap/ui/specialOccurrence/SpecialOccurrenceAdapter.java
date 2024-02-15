package org.informatika.sirekap.ui.specialOccurrence;

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
import org.informatika.sirekap.databinding.ItemSpecialOccurrenceBinding;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceAdapter;

/* compiled from: SpecialOccurrenceAdapter.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001d\u001eBn\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u0012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u0012!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0018H\u0016R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R)\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onDelete", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "specialOccurrencePage", "", "onRetake", "onClickPicture", "", "croppedPhotoPath", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "value", "", "isVerified", "()Z", "setVerified", "(Z)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "SpecialOccurrenceViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SpecialOccurrenceAdapter extends ListAdapter<SpecialOccurrencePage, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final SpecialOccurrenceAdapter$Companion$SPECIAL_OCCURRENCE_COMPARATOR$1 SPECIAL_OCCURRENCE_COMPARATOR = new DiffUtil.ItemCallback<SpecialOccurrencePage>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceAdapter$Companion$SPECIAL_OCCURRENCE_COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(SpecialOccurrencePage oldItem, SpecialOccurrencePage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return oldItem.getId() == newItem.getId();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(SpecialOccurrencePage oldItem, SpecialOccurrencePage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };
    private boolean isVerified;
    private final Function1<String, Unit> onClickPicture;
    private final Function1<SpecialOccurrencePage, Unit> onDelete;
    private final Function1<SpecialOccurrencePage, Unit> onRetake;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SpecialOccurrenceAdapter(Function1<? super SpecialOccurrencePage, Unit> onDelete, Function1<? super SpecialOccurrencePage, Unit> onRetake, Function1<? super String, Unit> onClickPicture) {
        super(SPECIAL_OCCURRENCE_COMPARATOR);
        Intrinsics.checkNotNullParameter(onDelete, "onDelete");
        Intrinsics.checkNotNullParameter(onRetake, "onRetake");
        Intrinsics.checkNotNullParameter(onClickPicture, "onClickPicture");
        this.onDelete = onDelete;
        this.onRetake = onRetake;
        this.onClickPicture = onClickPicture;
    }

    /* compiled from: SpecialOccurrenceAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter$Companion;", "", "()V", "SPECIAL_OCCURRENCE_COMPARATOR", "org/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter$Companion$SPECIAL_OCCURRENCE_COMPARATOR$1", "Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter$Companion$SPECIAL_OCCURRENCE_COMPARATOR$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final boolean isVerified() {
        return this.isVerified;
    }

    public final void setVerified(boolean z) {
        this.isVerified = z;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        SpecialOccurrenceViewHolder specialOccurrenceViewHolder = (SpecialOccurrenceViewHolder) holder;
        SpecialOccurrencePage item = getItem(i);
        Intrinsics.checkNotNullExpressionValue(item, "getItem(position)");
        specialOccurrenceViewHolder.bind(item, this.onDelete, this.onRetake, this.isVerified, this.onClickPicture);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return SpecialOccurrenceViewHolder.Companion.create(parent);
    }

    /* compiled from: SpecialOccurrenceAdapter.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u007f\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\f2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0011\u001a\u00020\u00122!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\b0\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter$SpecialOccurrenceViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemSpecialOccurrenceBinding;", "(Lorg/informatika/sirekap/databinding/ItemSpecialOccurrenceBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemSpecialOccurrenceBinding;", "bind", "", "item", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "onDelete", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "specialOccurrencePage", "onRetake", "isVerified", "", "onClickPicture", "", "croppedPhotoPath", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class SpecialOccurrenceViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemSpecialOccurrenceBinding binding;

        public final ItemSpecialOccurrenceBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SpecialOccurrenceViewHolder(ItemSpecialOccurrenceBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final SpecialOccurrencePage item, final Function1<? super SpecialOccurrencePage, Unit> onDelete, final Function1<? super SpecialOccurrencePage, Unit> onRetake, boolean z, final Function1<? super String, Unit> onClickPicture) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(onDelete, "onDelete");
            Intrinsics.checkNotNullParameter(onRetake, "onRetake");
            Intrinsics.checkNotNullParameter(onClickPicture, "onClickPicture");
            final ItemSpecialOccurrenceBinding itemSpecialOccurrenceBinding = this.binding;
            itemSpecialOccurrenceBinding.setCroppedPhotoPath(item.getCroppedPhotoPath());
            itemSpecialOccurrenceBinding.buttonDeletePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceAdapter$SpecialOccurrenceViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SpecialOccurrenceAdapter.SpecialOccurrenceViewHolder.bind$lambda$4$lambda$3$lambda$0(Function1.this, item, view);
                }
            });
            itemSpecialOccurrenceBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceAdapter$SpecialOccurrenceViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SpecialOccurrenceAdapter.SpecialOccurrenceViewHolder.bind$lambda$4$lambda$3$lambda$1(Function1.this, item, view);
                }
            });
            if (z) {
                itemSpecialOccurrenceBinding.buttonRetakePhoto.setEnabled(false);
                itemSpecialOccurrenceBinding.buttonDeletePhoto.setEnabled(false);
            }
            itemSpecialOccurrenceBinding.image.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceAdapter$SpecialOccurrenceViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SpecialOccurrenceAdapter.SpecialOccurrenceViewHolder.bind$lambda$4$lambda$3$lambda$2(Function1.this, itemSpecialOccurrenceBinding, view);
                }
            });
        }

        public static final void bind$lambda$4$lambda$3$lambda$0(Function1 onDelete, SpecialOccurrencePage currentItem, View view) {
            Intrinsics.checkNotNullParameter(onDelete, "$onDelete");
            Intrinsics.checkNotNullParameter(currentItem, "$currentItem");
            onDelete.invoke(currentItem);
        }

        public static final void bind$lambda$4$lambda$3$lambda$1(Function1 onRetake, SpecialOccurrencePage currentItem, View view) {
            Intrinsics.checkNotNullParameter(onRetake, "$onRetake");
            Intrinsics.checkNotNullParameter(currentItem, "$currentItem");
            onRetake.invoke(currentItem);
        }

        public static final void bind$lambda$4$lambda$3$lambda$2(Function1 onClickPicture, ItemSpecialOccurrenceBinding this_apply, View view) {
            Intrinsics.checkNotNullParameter(onClickPicture, "$onClickPicture");
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            String croppedPhotoPath = this_apply.getCroppedPhotoPath();
            if (croppedPhotoPath == null) {
                croppedPhotoPath = "";
            }
            onClickPicture.invoke(croppedPhotoPath);
        }

        /* compiled from: SpecialOccurrenceAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter$SpecialOccurrenceViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter$SpecialOccurrenceViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SpecialOccurrenceViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemSpecialOccurrenceBinding inflate = ItemSpecialOccurrenceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new SpecialOccurrenceViewHolder(inflate);
            }
        }
    }
}
