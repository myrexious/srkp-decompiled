package org.informatika.sirekap.ui.witness.register;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentWitnessRegisterBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.ConnectivityUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: WitnessRegisterFragment.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010&\u001a\u00020\u001aH\u0002J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\u001aH\u0002J\b\u0010+\u001a\u00020\u001aH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002J\b\u0010-\u001a\u00020\u001aH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017¨\u0006/"}, d2 = {"Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentWitnessRegisterBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "witnessRegisterViewModel", "Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterViewModel;", "getWitnessRegisterViewModel", "()Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterViewModel;", "witnessRegisterViewModel$delegate", "finishRegister", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupForm", "isElectionDone", "", "setupLaunchers", "setupViewModel", "trySubmit", "trySubmitOnline", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class WitnessRegisterFragment extends Hilt_WitnessRegisterFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessRegisterFragment";
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentWitnessRegisterBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy witnessRegisterViewModel$delegate;

    public WitnessRegisterFragment() {
        final WitnessRegisterFragment witnessRegisterFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.witnessRegisterViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessRegisterFragment, Reflection.getOrCreateKotlinClass(WitnessRegisterViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(WitnessRegisterFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$navArgs$1
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessRegisterFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = witnessRegisterFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$special$$inlined$activityViewModels$default$3
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
    }

    public final WitnessRegisterViewModel getWitnessRegisterViewModel() {
        return (WitnessRegisterViewModel) this.witnessRegisterViewModel$delegate.getValue();
    }

    private final WitnessRegisterFragmentArgs getArgs() {
        return (WitnessRegisterFragmentArgs) this.args$delegate.getValue();
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupLaunchers();
    }

    private final void setupLaunchers() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessRegisterFragment.setupLaunchers$lambda$0(WitnessRegisterFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
    }

    public static final void setupLaunchers$lambda$0(WitnessRegisterFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupLaunchers$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActiveProfile activeProfile) {
                invoke2(activeProfile);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ActiveProfile it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseFragment.showSnackBar$default(WitnessRegisterFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Daftar' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupLaunchers$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                BaseFragment.showSnackBar$default(WitnessRegisterFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWitnessRegisterBinding inflate = FragmentWitnessRegisterBinding.inflate(inflater, viewGroup, false);
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
        getWitnessRegisterViewModel().initForm(getArgs().getKodeTps());
    }

    private final void setupBinding() {
        FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding = this.binding;
        if (fragmentWitnessRegisterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessRegisterBinding = null;
        }
        fragmentWitnessRegisterBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentWitnessRegisterBinding.setViewModel(getWitnessRegisterViewModel());
        fragmentWitnessRegisterBinding.setFormState(getWitnessRegisterViewModel().getWitnessRegisterFormUseCase());
        fragmentWitnessRegisterBinding.buttonRegister.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessRegisterFragment.setupBinding$lambda$2$lambda$1(WitnessRegisterFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$2$lambda$1(WitnessRegisterFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMainViewModel().getAuthRequestUseCase().isLoading().postValue(true);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new WitnessRegisterFragment$setupBinding$1$1$1(this$0, null), 2, null);
    }

    public final void trySubmitOnline() {
        if (getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = getMainViewModel().getAuthRequestUseCase();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$trySubmitOnline$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ActiveProfile activeProfile) {
                    invoke2(activeProfile);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(ActiveProfile it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    WitnessRegisterFragment.this.trySubmit();
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$trySubmitOnline$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    MainViewModel mainViewModel;
                    ActivityResultLauncher<Intent> activityResultLauncher;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Toast.makeText(WitnessRegisterFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = WitnessRegisterFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = WitnessRegisterFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = WitnessRegisterFragment.this.authRequestLauncher;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                        String string = witnessRegisterFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(witnessRegisterFragment, string, null, null, 6, null);
                    }
                }
            });
            return;
        }
        trySubmit();
    }

    public final void trySubmit() {
        getWitnessRegisterViewModel().registerLocal();
    }

    private final void setupViewModel() {
        final WitnessRegisterFormUseCase witnessRegisterFormUseCase = getWitnessRegisterViewModel().getWitnessRegisterFormUseCase();
        witnessRegisterFormUseCase.isElectionDone().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                if (bool != null) {
                    WitnessRegisterFragment.this.setupForm(bool.booleanValue());
                }
            }
        }));
        witnessRegisterFormUseCase.getElections().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends ElectionWithRelation> list) {
                invoke2((List<ElectionWithRelation>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<ElectionWithRelation> list) {
                if (list != null) {
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = WitnessRegisterFormUseCase.this;
                    ArrayList arrayList = new ArrayList();
                    for (ElectionWithRelation electionWithRelation : list) {
                        arrayList.add(electionWithRelation.getElection().getPemilihan());
                    }
                    witnessRegisterFormUseCase2.setupElectionTypes(arrayList);
                }
            }
        }));
        witnessRegisterFormUseCase.getCandidatesPresiden().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Candidate>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Candidate> list) {
                invoke2((List<Candidate>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Candidate> list) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (list != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Candidate candidate : list) {
                        linkedHashMap.put(candidate.getNama(), Long.valueOf(candidate.getIdPilihan()));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(linkedHashMap.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonPresiden;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupPresidenOptions(linkedHashMap, false);
                }
            }
        }));
        witnessRegisterFormUseCase.getCandidatesPilgub().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Candidate>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Candidate> list) {
                invoke2((List<Candidate>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Candidate> list) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (list != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Candidate candidate : list) {
                        linkedHashMap.put(candidate.getNama(), Long.valueOf(candidate.getIdPilihan()));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(linkedHashMap.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonPilgub;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupPilgupOptions(linkedHashMap, false);
                }
            }
        }));
        witnessRegisterFormUseCase.getCandidatesPilwalkot().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Candidate>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Candidate> list) {
                invoke2((List<Candidate>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Candidate> list) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (list != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Candidate candidate : list) {
                        linkedHashMap.put(candidate.getNama(), Long.valueOf(candidate.getIdPilihan()));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(linkedHashMap.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonPilwalkot;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupPilwalkotOptions(linkedHashMap, false);
                }
            }
        }));
        witnessRegisterFormUseCase.getPartaisDpr().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<Map<String, ? extends Long>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Long> map) {
                invoke2((Map<String, Long>) map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Map<String, Long> map) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (map != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(map.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonDpr;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupDprOptions(map, false);
                }
            }
        }));
        witnessRegisterFormUseCase.getCandidatesDprdp().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<Map<String, ? extends Long>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$7
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Long> map) {
                invoke2((Map<String, Long>) map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Map<String, Long> map) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (map != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(map.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonDprdp;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupDprdpOptions(map, false);
                }
            }
        }));
        witnessRegisterFormUseCase.getCandidatesDprdk().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<Map<String, ? extends Long>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$8
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Long> map) {
                invoke2((Map<String, Long>) map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Map<String, Long> map) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (map != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(map.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonDprdk;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupDprdkOptions(map, false);
                }
            }
        }));
        witnessRegisterFormUseCase.getCandidatesDpd().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Candidate>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$1$9
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Candidate> list) {
                invoke2((List<Candidate>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Candidate> list) {
                FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding;
                if (list != null) {
                    WitnessRegisterFragment witnessRegisterFragment = WitnessRegisterFragment.this;
                    WitnessRegisterFormUseCase witnessRegisterFormUseCase2 = witnessRegisterFormUseCase;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Candidate candidate : list) {
                        linkedHashMap.put(candidate.getNama(), Long.valueOf(candidate.getIdPilihan()));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(witnessRegisterFragment.requireContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(linkedHashMap.keySet()));
                    fragmentWitnessRegisterBinding = witnessRegisterFragment.binding;
                    if (fragmentWitnessRegisterBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWitnessRegisterBinding = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.idPaslonDpd;
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
                    if (materialAutoCompleteTextView2 != null) {
                        materialAutoCompleteTextView2.setAdapter(arrayAdapter);
                    }
                    witnessRegisterFormUseCase2.setupDpdOptions(linkedHashMap, false);
                }
            }
        }));
        final WitnessRegisterViewModel witnessRegisterViewModel = getWitnessRegisterViewModel();
        witnessRegisterViewModel.getFormResultLocalResource().observe(getViewLifecycleOwner(), new WitnessRegisterFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends WitnessWithShare>, Unit>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment$setupViewModel$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends WitnessWithShare> resource) {
                invoke2((Resource<WitnessWithShare>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<WitnessWithShare> resource) {
                WitnessRegisterViewModel witnessRegisterViewModel2;
                Witness witness;
                if (resource != null) {
                    WitnessRegisterViewModel witnessRegisterViewModel3 = WitnessRegisterViewModel.this;
                    WitnessRegisterFragment witnessRegisterFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        witnessRegisterViewModel3.finishRegisterFormLocal();
                        witnessRegisterFragment.finishRegister();
                        Context requireContext = witnessRegisterFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (ConnectivityUtil.isConnectedToNetwork(requireContext)) {
                            witnessRegisterViewModel2 = witnessRegisterFragment.getWitnessRegisterViewModel();
                            WitnessWithShare payload = resource.getPayload();
                            witnessRegisterViewModel2.register((payload == null || (witness = payload.getWitness()) == null || (r5 = witness.getNoHandphone()) == null) ? "" : "");
                        }
                    }
                }
            }
        }));
    }

    public final void finishRegister() {
        Toast.makeText(getContext(), getString(R.string.success_witness_registration), 0).show();
        FragmentKt.findNavController(this).navigateUp();
    }

    public final void setupForm(boolean z) {
        List<String> post_zip_witness_types;
        if (!z) {
            post_zip_witness_types = Witness.Companion.getPRE_ZIP_WITNESS_TYPES();
        } else {
            post_zip_witness_types = Witness.Companion.getPOST_ZIP_WITNESS_TYPES();
        }
        List<String> list = post_zip_witness_types;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (String str : list) {
            Witness.Companion companion = Witness.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            arrayList.add(companion.getJenisPemeriksaText(requireContext, str));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList);
        FragmentWitnessRegisterBinding fragmentWitnessRegisterBinding = this.binding;
        if (fragmentWitnessRegisterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessRegisterBinding = null;
        }
        MaterialAutoCompleteTextView materialAutoCompleteTextView = fragmentWitnessRegisterBinding.perwakilan;
        MaterialAutoCompleteTextView materialAutoCompleteTextView2 = materialAutoCompleteTextView instanceof AutoCompleteTextView ? materialAutoCompleteTextView : null;
        if (materialAutoCompleteTextView2 != null) {
            materialAutoCompleteTextView2.setAdapter(arrayAdapter);
        }
        getWitnessRegisterViewModel().getWitnessRegisterFormUseCase().setupWitnessTypeOptions(post_zip_witness_types);
    }

    /* compiled from: WitnessRegisterFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
