package org.informatika.sirekap.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dagger.hilt.android.AndroidEntryPoint;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentSettingsBinding;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.User;

/* compiled from: SettingsFragment.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lorg/informatika/sirekap/ui/settings/SettingsFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/settings/SettingsFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/settings/SettingsFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentSettingsBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lorg/informatika/sirekap/ui/settings/SettingsViewModel;", "getViewModel", "()Lorg/informatika/sirekap/ui/settings/SettingsViewModel;", "viewModel$delegate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupBinding", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class SettingsFragment extends Hilt_SettingsFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SettingsFragment";
    private final NavArgsLazy args$delegate;
    private FragmentSettingsBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy viewModel$delegate;

    public static final void setupBinding$lambda$6$lambda$0(DialogInterface dialogInterface, int i) {
    }

    public static final void setupBinding$lambda$6$lambda$4$lambda$3$lambda$1(DialogInterface dialogInterface, int i) {
    }

    public SettingsFragment() {
        final SettingsFragment settingsFragment = this;
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(settingsFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = Function0.this;
                if (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) {
                    CreationExtras defaultViewModelCreationExtras = settingsFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$activityViewModels$default$3
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
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(settingsFragment, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SettingsFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$special$$inlined$navArgs$1
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

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    public final SettingsViewModel getViewModel() {
        return (SettingsViewModel) this.viewModel$delegate.getValue();
    }

    private final SettingsFragmentArgs getArgs() {
        return (SettingsFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSettingsBinding inflate = FragmentSettingsBinding.inflate(inflater, viewGroup, false);
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
        getViewModel().setup(getArgs().getKodeTps());
    }

    private final void setupBinding() {
        FragmentSettingsBinding fragmentSettingsBinding = this.binding;
        FragmentSettingsBinding fragmentSettingsBinding2 = null;
        if (fragmentSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingsBinding = null;
        }
        fragmentSettingsBinding.setLifecycleOwner(getViewLifecycleOwner());
        FragmentSettingsBinding fragmentSettingsBinding3 = this.binding;
        if (fragmentSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingsBinding3 = null;
        }
        fragmentSettingsBinding3.setViewModel(getViewModel());
        FragmentSettingsBinding fragmentSettingsBinding4 = this.binding;
        if (fragmentSettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingsBinding4 = null;
        }
        fragmentSettingsBinding4.setMainViewModel(getMainViewModel());
        FragmentSettingsBinding fragmentSettingsBinding5 = this.binding;
        if (fragmentSettingsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingsBinding5 = null;
        }
        fragmentSettingsBinding5.itemChangeElectionType.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsFragment.setupBinding$lambda$6(SettingsFragment.this, view);
            }
        });
        FragmentSettingsBinding fragmentSettingsBinding6 = this.binding;
        if (fragmentSettingsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentSettingsBinding2 = fragmentSettingsBinding6;
        }
        fragmentSettingsBinding2.itemChangeProfile.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsFragment.setupBinding$lambda$7(SettingsFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$6(SettingsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = 0;
        String[] strArr = {this$0.getString(R.string.election_type_normal_description), this$0.getString(R.string.election_type_psu_ulangan_description), this$0.getString(R.string.election_type_psu_susulan_description), this$0.getString(R.string.election_type_psu_lanjutan_description)};
        String value = this$0.getViewModel().getElectionType().getValue();
        if (value != null) {
            int hashCode = value.hashCode();
            if (hashCode != 76) {
                if (hashCode != 83) {
                    if (hashCode == 85 && value.equals("U")) {
                        i = 1;
                    }
                } else if (value.equals("S")) {
                    i = 2;
                }
            } else if (value.equals("L")) {
                i = 3;
            }
        }
        this$0.getViewModel().saveSelectedElectionType(i);
        new MaterialAlertDialogBuilder(this$0.requireContext()).setTitle((CharSequence) this$0.getString(R.string.change_election_type_dialog_title)).setNeutralButton((CharSequence) this$0.getResources().getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SettingsFragment.setupBinding$lambda$6$lambda$0(dialogInterface, i2);
            }
        }).setPositiveButton((CharSequence) this$0.getResources().getString(R.string.action_ok), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SettingsFragment.setupBinding$lambda$6$lambda$4(SettingsFragment.this, dialogInterface, i2);
            }
        }).setSingleChoiceItems((CharSequence[]) strArr, i, new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SettingsFragment.setupBinding$lambda$6$lambda$5(SettingsFragment.this, dialogInterface, i2);
            }
        }).show();
    }

    public static final void setupBinding$lambda$6$lambda$4(SettingsFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String value = this$0.getViewModel().getSelectedElectionType().getValue();
        if (value != null) {
            if (value.hashCode() == 82 && value.equals("R")) {
                this$0.getViewModel().confirmSelectedElectionType();
                return;
            }
            Election.Companion companion = Election.Companion;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            new MaterialAlertDialogBuilder(this$0.requireContext()).setMessage((CharSequence) this$0.getString(R.string.change_election_type_confirmation_dialog_message, companion.getElectionTypeDescriptionShort(requireContext, value))).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int i2) {
                    SettingsFragment.setupBinding$lambda$6$lambda$4$lambda$3$lambda$1(dialogInterface2, i2);
                }
            }).setPositiveButton(R.string.action_yes, new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$$ExternalSyntheticLambda6
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int i2) {
                    SettingsFragment.setupBinding$lambda$6$lambda$4$lambda$3$lambda$2(SettingsFragment.this, dialogInterface2, i2);
                }
            }).show();
        }
    }

    public static final void setupBinding$lambda$6$lambda$4$lambda$3$lambda$2(SettingsFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().confirmSelectedElectionType();
    }

    public static final void setupBinding$lambda$6$lambda$5(SettingsFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().saveSelectedElectionType(i);
    }

    public static final void setupBinding$lambda$7(SettingsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new SettingsFragment$setupBinding$2$1(this$0, null), 2, null);
    }

    private final void setupViewModel() {
        getViewModel().getElectionType().observe(getViewLifecycleOwner(), new SettingsFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupViewModel$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        }));
        getViewModel().getSelectedElectionType().observe(getViewLifecycleOwner(), new SettingsFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupViewModel$2
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        }));
        getMainViewModel().getGetLoggedInUserUseCase().getLoggedInUser().observe(getViewLifecycleOwner(), new SettingsFragment$sam$androidx_lifecycle_Observer$0(new Function1<User, Unit>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupViewModel$3
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(User user) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(User user) {
                invoke2(user);
                return Unit.INSTANCE;
            }
        }));
    }

    /* compiled from: SettingsFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/settings/SettingsFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
