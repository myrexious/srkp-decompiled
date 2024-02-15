package org.informatika.sirekap.ui.electionDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.databinding.ItemElectionPageCaptureC1Binding;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.electionDetail.ElectionPageListAdapter;

/* compiled from: ElectionPageListAdapter.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001d\u001eBx\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u000b\u00126\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/ElectionPage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "election", "Lorg/informatika/sirekap/model/Election;", "viewModel", "Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailViewModel;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "onClickDetail", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "electionPage", "", "onClickMore", "Lkotlin/Function2;", "Landroid/view/View;", "view", "(Lorg/informatika/sirekap/model/Election;Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailViewModel;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ItemElectionPageCaptureC1ViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionPageListAdapter extends ListAdapter<ElectionPage, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final ElectionPageListAdapter$Companion$DIFF_CALLBACK$1 DIFF_CALLBACK = new DiffUtil.ItemCallback<ElectionPage>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionPageListAdapter$Companion$DIFF_CALLBACK$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(ElectionPage oldItem, ElectionPage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getId(), newItem.getId());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(ElectionPage oldItem, ElectionPage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };
    private final Election election;
    private final LifecycleOwner lifecycleOwner;
    private final Function1<ElectionPage, Unit> onClickDetail;
    private final Function2<View, ElectionPage, Unit> onClickMore;
    private final ElectionDetailViewModel viewModel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ElectionPageListAdapter(Election election, ElectionDetailViewModel viewModel, LifecycleOwner lifecycleOwner, Function1<? super ElectionPage, Unit> onClickDetail, Function2<? super View, ? super ElectionPage, Unit> onClickMore) {
        super(DIFF_CALLBACK);
        Intrinsics.checkNotNullParameter(election, "election");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(onClickDetail, "onClickDetail");
        Intrinsics.checkNotNullParameter(onClickMore, "onClickMore");
        this.election = election;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.onClickDetail = onClickDetail;
        this.onClickMore = onClickMore;
    }

    /* compiled from: ElectionPageListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "org/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter$Companion$DIFF_CALLBACK$1", "Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter$Companion$DIFF_CALLBACK$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
        ItemElectionPageCaptureC1ViewHolder itemElectionPageCaptureC1ViewHolder = (ItemElectionPageCaptureC1ViewHolder) holder;
        ElectionPage item = getItem(i);
        Intrinsics.checkNotNullExpressionValue(item, "getItem(position)");
        itemElectionPageCaptureC1ViewHolder.bind(item, this.election, this.viewModel, this.lifecycleOwner, this.onClickDetail, this.onClickMore);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return ItemElectionPageCaptureC1ViewHolder.Companion.create(parent);
    }

    /* compiled from: ElectionPageListAdapter.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0081\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\b0\u001226\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\b0\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter$ItemElectionPageCaptureC1ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemElectionPageCaptureC1Binding;", "(Lorg/informatika/sirekap/databinding/ItemElectionPageCaptureC1Binding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemElectionPageCaptureC1Binding;", "bind", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "election", "Lorg/informatika/sirekap/model/Election;", "viewModel", "Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailViewModel;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "onClickDetail", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onClickMore", "Lkotlin/Function2;", "Landroid/view/View;", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ItemElectionPageCaptureC1ViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemElectionPageCaptureC1Binding binding;

        public final ItemElectionPageCaptureC1Binding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemElectionPageCaptureC1ViewHolder(ItemElectionPageCaptureC1Binding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final ElectionPage electionPage, Election election, ElectionDetailViewModel viewModel, LifecycleOwner lifecycleOwner, final Function1<? super ElectionPage, Unit> onClickDetail, final Function2<? super View, ? super ElectionPage, Unit> onClickMore) {
            Intrinsics.checkNotNullParameter(electionPage, "electionPage");
            Intrinsics.checkNotNullParameter(election, "election");
            Intrinsics.checkNotNullParameter(viewModel, "viewModel");
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(onClickDetail, "onClickDetail");
            Intrinsics.checkNotNullParameter(onClickMore, "onClickMore");
            this.binding.setElectionPage(electionPage);
            this.binding.setElectionJmlLembar(Integer.valueOf(election.getJmlLembar()));
            this.binding.buttonDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionPageListAdapter$ItemElectionPageCaptureC1ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ElectionPageListAdapter.ItemElectionPageCaptureC1ViewHolder.bind$lambda$0(Function1.this, electionPage, view);
                }
            });
            this.binding.buttonMoreMenu.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionPageListAdapter$ItemElectionPageCaptureC1ViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ElectionPageListAdapter.ItemElectionPageCaptureC1ViewHolder.bind$lambda$1(Function2.this, electionPage, view);
                }
            });
            viewModel.getElectionPageStages(electionPage.getId()).observe(lifecycleOwner, new ElectionPageListAdapter$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionPageWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionPageListAdapter$ItemElectionPageCaptureC1ViewHolder$bind$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends ElectionPageWithRelation> resource) {
                    invoke2((Resource<ElectionPageWithRelation>) resource);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Resource<ElectionPageWithRelation> resource) {
                    if ((resource != null ? resource.getSuccess() : null) == ResourceStatus.SUCCESS) {
                        ElectionPageListAdapter.ItemElectionPageCaptureC1ViewHolder.this.getBinding().setElectionPageWithRelation(resource.getPayload());
                    }
                }
            }));
        }

        public static final void bind$lambda$0(Function1 onClickDetail, ElectionPage electionPage, View view) {
            Intrinsics.checkNotNullParameter(onClickDetail, "$onClickDetail");
            Intrinsics.checkNotNullParameter(electionPage, "$electionPage");
            onClickDetail.invoke(electionPage);
        }

        public static final void bind$lambda$1(Function2 onClickMore, ElectionPage electionPage, View it) {
            Intrinsics.checkNotNullParameter(onClickMore, "$onClickMore");
            Intrinsics.checkNotNullParameter(electionPage, "$electionPage");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            onClickMore.invoke(it, electionPage);
        }

        /* compiled from: ElectionPageListAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter$ItemElectionPageCaptureC1ViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter$ItemElectionPageCaptureC1ViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ItemElectionPageCaptureC1ViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemElectionPageCaptureC1Binding inflate = ItemElectionPageCaptureC1Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new ItemElectionPageCaptureC1ViewHolder(inflate);
            }
        }
    }
}
