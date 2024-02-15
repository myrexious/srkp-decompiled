package org.informatika.sirekap.ui.witness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.databinding.FragmentWitnessBinding;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.ui.witness.WitnessFragmentDirections;

/* compiled from: WitnessFragment.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010#\u001a\u00020!H\u0002J\b\u0010$\u001a\u00020!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/witness/WitnessAdapter;", "args", "Lorg/informatika/sirekap/ui/witness/WitnessFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/witness/WitnessFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentWitnessBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lorg/informatika/sirekap/ui/witness/WitnessViewModel;", "getViewModel", "()Lorg/informatika/sirekap/ui/witness/WitnessViewModel;", "viewModel$delegate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupBinding", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class WitnessFragment extends Hilt_WitnessFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessFragment";
    private WitnessAdapter adapter;
    private final NavArgsLazy args$delegate;
    private FragmentWitnessBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy viewModel$delegate;

    public WitnessFragment() {
        final WitnessFragment witnessFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessFragment, Reflection.getOrCreateKotlinClass(WitnessViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) {
                    CreationExtras defaultViewModelCreationExtras = witnessFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(WitnessFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$special$$inlined$navArgs$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle arguments = Fragment.this.getArguments();
                if (arguments != null) {
                    return arguments;
                }
                throw new IllegalStateException("Fragment " + Fragment.this + " has null arguments");
            }
        });
    }

    public final WitnessViewModel getViewModel() {
        return (WitnessViewModel) this.viewModel$delegate.getValue();
    }

    private final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    public final WitnessFragmentArgs getArgs() {
        return (WitnessFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWitnessBinding inflate = FragmentWitnessBinding.inflate(inflater, viewGroup, false);
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
        getViewModel().setup(getArgs().getKodeTps(), getArgs().getJenisPemilihan());
    }

    private final void setupBinding() {
        FragmentWitnessBinding fragmentWitnessBinding = this.binding;
        FragmentWitnessBinding fragmentWitnessBinding2 = null;
        if (fragmentWitnessBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding = null;
        }
        fragmentWitnessBinding.setLifecycleOwner(getViewLifecycleOwner());
        FragmentWitnessBinding fragmentWitnessBinding3 = this.binding;
        if (fragmentWitnessBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding3 = null;
        }
        fragmentWitnessBinding3.setViewModel(getViewModel());
        this.adapter = new WitnessAdapter(getArgs().getJenisPemilihan(), getMainViewModel().getMappingCache(), new Function1<WitnessWithShare, Unit>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$setupBinding$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WitnessWithShare witnessWithShare) {
                invoke2(witnessWithShare);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(WitnessWithShare witnessWithShare) {
                WitnessViewModel viewModel;
                WitnessFragmentArgs args;
                WitnessFragmentArgs args2;
                Intrinsics.checkNotNullParameter(witnessWithShare, "witnessWithShare");
                viewModel = WitnessFragment.this.getViewModel();
                if (viewModel.getFilterJenisPemilihan().getValue() != null) {
                    WitnessFragment witnessFragment = WitnessFragment.this;
                    FirebaseCrashlytics.getInstance().log("WitnessFragment: User clicks 'Saksi " + witnessWithShare.getWitness().getIdPetugas() + OperatorName.SHOW_TEXT_LINE);
                    try {
                        NavController findNavController = FragmentKt.findNavController(witnessFragment);
                        WitnessFragmentDirections.Companion companion = WitnessFragmentDirections.Companion;
                        String noHandphone = witnessWithShare.getWitness().getNoHandphone();
                        args = witnessFragment.getArgs();
                        String kodeTps = args.getKodeTps();
                        args2 = witnessFragment.getArgs();
                        findNavController.navigate(companion.actionWitnessFragmentToWitnessQrCodeFragment(noHandphone, kodeTps, args2.getJenisPemilihan()));
                    } catch (Exception e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }
                }
            }
        });
        FragmentWitnessBinding fragmentWitnessBinding4 = this.binding;
        if (fragmentWitnessBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding4 = null;
        }
        RecyclerView recyclerView = fragmentWitnessBinding4.listWitness;
        WitnessAdapter witnessAdapter = this.adapter;
        if (witnessAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            witnessAdapter = null;
        }
        recyclerView.setAdapter(witnessAdapter);
        FragmentWitnessBinding fragmentWitnessBinding5 = this.binding;
        if (fragmentWitnessBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding5 = null;
        }
        fragmentWitnessBinding5.addWitnessButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessFragment.setupBinding$lambda$1(WitnessFragment.this, view);
            }
        });
        FragmentWitnessBinding fragmentWitnessBinding6 = this.binding;
        if (fragmentWitnessBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding6 = null;
        }
        fragmentWitnessBinding6.chipFilterPilpres.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessFragment.setupBinding$lambda$2(WitnessFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessBinding fragmentWitnessBinding7 = this.binding;
        if (fragmentWitnessBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding7 = null;
        }
        fragmentWitnessBinding7.chipFilterDpr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessFragment.setupBinding$lambda$3(WitnessFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessBinding fragmentWitnessBinding8 = this.binding;
        if (fragmentWitnessBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding8 = null;
        }
        fragmentWitnessBinding8.chipFilterDprdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessFragment.setupBinding$lambda$4(WitnessFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessBinding fragmentWitnessBinding9 = this.binding;
        if (fragmentWitnessBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessBinding9 = null;
        }
        fragmentWitnessBinding9.chipFilterDprdk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessFragment.setupBinding$lambda$5(WitnessFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessBinding fragmentWitnessBinding10 = this.binding;
        if (fragmentWitnessBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWitnessBinding2 = fragmentWitnessBinding10;
        }
        fragmentWitnessBinding2.chipFilterDpd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessFragment.setupBinding$lambda$6(WitnessFragment.this, compoundButton, z);
            }
        });
    }

    public static final void setupBinding$lambda$1(WitnessFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("WitnessFragment User clicks 'Add Witness' button");
        if (this$0.getViewModel().getFilterJenisPemilihan().getValue() != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(WitnessFragmentDirections.Companion.actionWitnessFragmentToWitnessRegisterFragment(this$0.getArgs().getKodeTps()));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$2(WitnessFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterPilpres(z);
    }

    public static final void setupBinding$lambda$3(WitnessFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDpr(z);
    }

    public static final void setupBinding$lambda$4(WitnessFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDprdp(z);
    }

    public static final void setupBinding$lambda$5(WitnessFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDprdk(z);
    }

    public static final void setupBinding$lambda$6(WitnessFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDpd(z);
    }

    private final void setupViewModel() {
        getViewModel().getWitnesses().observe(getViewLifecycleOwner(), new WitnessFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends WitnessWithShare>, Unit>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$setupViewModel$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends WitnessWithShare> list) {
                invoke2((List<WitnessWithShare>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<WitnessWithShare> list) {
                WitnessAdapter witnessAdapter;
                WitnessAdapter witnessAdapter2;
                if (list != null) {
                    WitnessFragment witnessFragment = WitnessFragment.this;
                    witnessAdapter = witnessFragment.adapter;
                    WitnessAdapter witnessAdapter3 = null;
                    if (witnessAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        witnessAdapter = null;
                    }
                    witnessAdapter.submitList(list);
                    witnessAdapter2 = witnessFragment.adapter;
                    if (witnessAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        witnessAdapter3 = witnessAdapter2;
                    }
                    witnessAdapter3.setEmpty(list.isEmpty());
                }
            }
        }));
        getViewModel().getFilterJenisPemilihan().observe(getViewLifecycleOwner(), new WitnessFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends String>, Unit>() { // from class: org.informatika.sirekap.ui.witness.WitnessFragment$setupViewModel$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends String> list) {
                invoke2((List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<String> list) {
                WitnessAdapter witnessAdapter;
                if (list != null) {
                    witnessAdapter = WitnessFragment.this.adapter;
                    if (witnessAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        witnessAdapter = null;
                    }
                    witnessAdapter.setFilterJenisPemilihan(list);
                }
            }
        }));
    }

    /* compiled from: WitnessFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
