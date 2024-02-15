package org.informatika.sirekap.ui.imageHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.databinding.FragmentImageHistoryBinding;
import org.informatika.sirekap.model.ElectionImage;
import org.informatika.sirekap.ui.BaseFragment;

/* compiled from: ImageHistoryFragment.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ImageHistoryFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/imageHistory/ElectionImageListAdapter;", "binding", "Lorg/informatika/sirekap/databinding/FragmentImageHistoryBinding;", "imageHistoryViewModel", "Lorg/informatika/sirekap/ui/imageHistory/ImageHistoryViewModel;", "getImageHistoryViewModel", "()Lorg/informatika/sirekap/ui/imageHistory/ImageHistoryViewModel;", "imageHistoryViewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupBinding", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ImageHistoryFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ImageHistoryFragment";
    private ElectionImageListAdapter adapter;
    private FragmentImageHistoryBinding binding;
    private final Lazy imageHistoryViewModel$delegate;

    public ImageHistoryFragment() {
        final ImageHistoryFragment imageHistoryFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.imageHistoryViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(imageHistoryFragment, Reflection.getOrCreateKotlinClass(ImageHistoryViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStoreOwner m68viewModels$lambda1;
                m68viewModels$lambda1 = FragmentViewModelLazyKt.m68viewModels$lambda1(Lazy.this);
                return m68viewModels$lambda1.getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                ViewModelStoreOwner m68viewModels$lambda1;
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) {
                    m68viewModels$lambda1 = FragmentViewModelLazyKt.m68viewModels$lambda1(lazy);
                    HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m68viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m68viewModels$lambda1 : null;
                    return hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$special$$inlined$viewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelStoreOwner m68viewModels$lambda1;
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                m68viewModels$lambda1 = FragmentViewModelLazyKt.m68viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m68viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m68viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    ViewModelProvider.Factory defaultViewModelProviderFactory2 = Fragment.this.getDefaultViewModelProviderFactory();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory2, "defaultViewModelProviderFactory");
                    return defaultViewModelProviderFactory2;
                }
                return defaultViewModelProviderFactory;
            }
        });
    }

    private final ImageHistoryViewModel getImageHistoryViewModel() {
        return (ImageHistoryViewModel) this.imageHistoryViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentImageHistoryBinding inflate = FragmentImageHistoryBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setupBinding();
        setupViewModel();
    }

    private final void setupBinding() {
        FragmentImageHistoryBinding fragmentImageHistoryBinding = this.binding;
        ElectionImageListAdapter electionImageListAdapter = null;
        if (fragmentImageHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentImageHistoryBinding = null;
        }
        fragmentImageHistoryBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentImageHistoryBinding.setViewModel(getImageHistoryViewModel());
        this.adapter = new ElectionImageListAdapter();
        RecyclerView recyclerView = fragmentImageHistoryBinding.listData;
        ElectionImageListAdapter electionImageListAdapter2 = this.adapter;
        if (electionImageListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            electionImageListAdapter = electionImageListAdapter2;
        }
        recyclerView.setAdapter(electionImageListAdapter);
        fragmentImageHistoryBinding.chipFilterNotSent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ImageHistoryFragment.setupBinding$lambda$5$lambda$0(ImageHistoryFragment.this, compoundButton, z);
            }
        });
        fragmentImageHistoryBinding.chipFilterProcessed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ImageHistoryFragment.setupBinding$lambda$5$lambda$1(ImageHistoryFragment.this, compoundButton, z);
            }
        });
        fragmentImageHistoryBinding.chipFilterScanned.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ImageHistoryFragment.setupBinding$lambda$5$lambda$2(ImageHistoryFragment.this, compoundButton, z);
            }
        });
        fragmentImageHistoryBinding.chipFilterValid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ImageHistoryFragment.setupBinding$lambda$5$lambda$3(ImageHistoryFragment.this, compoundButton, z);
            }
        });
        fragmentImageHistoryBinding.chipFilterInvalid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ImageHistoryFragment.setupBinding$lambda$5$lambda$4(ImageHistoryFragment.this, compoundButton, z);
            }
        });
        fragmentImageHistoryBinding.autocompleteFilterElectionPemilihan.setAdapter(new ArrayAdapter(requireContext(), 17367043, CollectionsKt.listOf("Presiden dan Wakil Presiden")));
    }

    public static final void setupBinding$lambda$5$lambda$0(ImageHistoryFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageHistoryViewModel.updateFilter$default(this$0.getImageHistoryViewModel(), Boolean.valueOf(z), null, null, null, null, 30, null);
    }

    public static final void setupBinding$lambda$5$lambda$1(ImageHistoryFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageHistoryViewModel.updateFilter$default(this$0.getImageHistoryViewModel(), null, Boolean.valueOf(z), null, null, null, 29, null);
    }

    public static final void setupBinding$lambda$5$lambda$2(ImageHistoryFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageHistoryViewModel.updateFilter$default(this$0.getImageHistoryViewModel(), null, null, Boolean.valueOf(z), null, null, 27, null);
    }

    public static final void setupBinding$lambda$5$lambda$3(ImageHistoryFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageHistoryViewModel.updateFilter$default(this$0.getImageHistoryViewModel(), null, null, null, Boolean.valueOf(z), null, 23, null);
    }

    public static final void setupBinding$lambda$5$lambda$4(ImageHistoryFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageHistoryViewModel.updateFilter$default(this$0.getImageHistoryViewModel(), null, null, null, null, Boolean.valueOf(z), 15, null);
    }

    private final void setupViewModel() {
        getImageHistoryViewModel().getImageHistory().observe(getViewLifecycleOwner(), new ImageHistoryFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends ElectionImage>, Unit>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends ElectionImage> list) {
                invoke2((List<ElectionImage>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<ElectionImage> list) {
                ElectionImageListAdapter electionImageListAdapter;
                if (list != null) {
                    electionImageListAdapter = ImageHistoryFragment.this.adapter;
                    if (electionImageListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        electionImageListAdapter = null;
                    }
                    electionImageListAdapter.submitList(list);
                }
            }
        }));
    }

    /* compiled from: ImageHistoryFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ImageHistoryFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
