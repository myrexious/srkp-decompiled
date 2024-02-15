package org.informatika.sirekap.ui.imageHistory;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.databinding.ItemElectionImageBinding;
import org.informatika.sirekap.model.ElectionImage;

/* compiled from: ElectionImageListAdapter.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/ElectionImage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ElectionImageViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionImageListAdapter extends ListAdapter<ElectionImage, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final ElectionImageListAdapter$Companion$ELECTION_IMAGE_COMPARATOR$1 ELECTION_IMAGE_COMPARATOR = new DiffUtil.ItemCallback<ElectionImage>() { // from class: org.informatika.sirekap.ui.imageHistory.ElectionImageListAdapter$Companion$ELECTION_IMAGE_COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(ElectionImage oldItem, ElectionImage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getId(), newItem.getId());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(ElectionImage oldItem, ElectionImage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };

    public ElectionImageListAdapter() {
        super(ELECTION_IMAGE_COMPARATOR);
    }

    /* compiled from: ElectionImageListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter$Companion;", "", "()V", "ELECTION_IMAGE_COMPARATOR", "org/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter$Companion$ELECTION_IMAGE_COMPARATOR$1", "Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter$Companion$ELECTION_IMAGE_COMPARATOR$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
        ((ElectionImageViewHolder) holder).bind(getItem(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return ElectionImageViewHolder.Companion.create(parent);
    }

    /* compiled from: ElectionImageListAdapter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter$ElectionImageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemElectionImageBinding;", "(Lorg/informatika/sirekap/databinding/ItemElectionImageBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemElectionImageBinding;", "bind", "", "electionImage", "Lorg/informatika/sirekap/model/ElectionImage;", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ElectionImageViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemElectionImageBinding binding;

        public final ItemElectionImageBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ElectionImageViewHolder(ItemElectionImageBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(ElectionImage electionImage) {
            if (electionImage != null) {
                this.binding.setElectionImage(electionImage);
            }
        }

        /* compiled from: ElectionImageListAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter$ElectionImageViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter$ElectionImageViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ElectionImageViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemElectionImageBinding inflate = ItemElectionImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new ElectionImageViewHolder(inflate);
            }
        }
    }
}
