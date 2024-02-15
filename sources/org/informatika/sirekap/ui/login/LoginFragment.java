package org.informatika.sirekap.ui.login;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentLoginBinding;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.security.CertificateFragment;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: LoginFragment.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001a\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010#\u001a\u00020\u0018H\u0016J\b\u0010$\u001a\u00020\u0018H\u0016J\b\u0010%\u001a\u00020\u0018H\u0016J\u001a\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020\u0018H\u0002J\b\u0010*\u001a\u00020\u0018H\u0002J\b\u0010+\u001a\u00020\u0018H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/ui/login/LoginFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentLoginBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/login/LoginViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/login/LoginViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "checkUserAndCertificate", "", "doAuthorization", "", "getTpsData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "setupBinding", "setupLaunchers", "setupNavigation", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class LoginFragment extends Hilt_LoginFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "LoginFragment";
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentLoginBinding binding;
    private final Lazy fragmentViewModel$delegate;
    private final Lazy mainViewModel$delegate;
    private OnBackPressedCallback onBackPressedCallback;

    public LoginFragment() {
        final LoginFragment loginFragment = this;
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(loginFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = loginFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$activityViewModels$default$3
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
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(loginFragment, Reflection.getOrCreateKotlinClass(LoginViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$special$$inlined$viewModels$default$5
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

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    public final LoginViewModel getFragmentViewModel() {
        return (LoginViewModel) this.fragmentViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupLaunchers();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentLoginBinding inflate = FragmentLoginBinding.inflate(inflater, viewGroup, false);
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
        setupNavigation();
        setupViewModel();
        getFragmentViewModel().setup();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        OnBackPressedCallback onBackPressedCallback2 = null;
        if (onBackPressedCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBackPressedCallback");
            onBackPressedCallback = null;
        }
        onBackPressedCallback.setEnabled(false);
        OnBackPressedCallback onBackPressedCallback3 = this.onBackPressedCallback;
        if (onBackPressedCallback3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBackPressedCallback");
        } else {
            onBackPressedCallback2 = onBackPressedCallback3;
        }
        onBackPressedCallback2.remove();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentKt.setFragmentResultListener(this, CertificateFragment.CERTIFICATE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$onResume$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String str, Bundle bundle) {
                LoginViewModel fragmentViewModel;
                MainViewModel mainViewModel;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                boolean z = bundle.getBoolean(CertificateFragment.CERTIFICATE_FRAGMENT_RESULT_IS_SUCCESS, false);
                fragmentViewModel = LoginFragment.this.getFragmentViewModel();
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(LoginFragment.this, fragmentViewModel.getSharedPreferences().getInt(SharedPreferencesModule.SP_STATUS_INITIALIZATION, 9), null), 3, null);
                if (z) {
                    LoginFragment.this.getTpsData();
                    return;
                }
                mainViewModel = LoginFragment.this.getMainViewModel();
                mainViewModel.logout();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: LoginFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "org.informatika.sirekap.ui.login.LoginFragment$onResume$1$1", f = "LoginFragment.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.login.LoginFragment$onResume$1$1  reason: invalid class name */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $status;
                int label;
                final /* synthetic */ LoginFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LoginFragment loginFragment, int i, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = loginFragment;
                    this.$status = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$status, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    LoginViewModel fragmentViewModel;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            fragmentViewModel = this.this$0.getFragmentViewModel();
                            Context requireContext = this.this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            this.label = 1;
                            if (fragmentViewModel.reportUserStatus(requireContext, this.$status, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                    } catch (Exception e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentKt.clearFragmentResultListener(this, CertificateFragment.CERTIFICATE_FRAGMENT_RESULT);
    }

    private final void setupNavigation() {
        this.onBackPressedCallback = new OnBackPressedCallback() { // from class: org.informatika.sirekap.ui.login.LoginFragment$setupNavigation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                LoginFragment.this.requireActivity().finish();
            }
        };
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBackPressedCallback");
            onBackPressedCallback = null;
        }
        onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback);
    }

    private final void setupLaunchers() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.login.LoginFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                LoginFragment.setupLaunchers$lambda$0(LoginFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
    }

    public static final void setupLaunchers$lambda$0(LoginFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$setupLaunchers$1$1
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
                boolean checkUserAndCertificate;
                Intrinsics.checkNotNullParameter(it2, "it");
                checkUserAndCertificate = LoginFragment.this.checkUserAndCertificate();
                if (checkUserAndCertificate) {
                    LoginFragment.this.getTpsData();
                }
            }
        }, new LoginFragment$setupLaunchers$1$2(this$0));
    }

    public final void getTpsData() {
        ActiveProfile activeProfile = getFragmentViewModel().getActiveProfile();
        Intrinsics.checkNotNull(activeProfile);
        if (Intrinsics.areEqual(activeProfile.getRoleUser(), User.USER_ROLE_KECAMATAN)) {
            androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(LoginFragmentDirections.Companion.actionLoginFragmentToLoginTpsFragment());
            return;
        }
        LoginViewModel fragmentViewModel = getFragmentViewModel();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        fragmentViewModel.doLogin(requireContext);
    }

    private final void setupBinding() {
        FragmentLoginBinding fragmentLoginBinding = this.binding;
        if (fragmentLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding = null;
        }
        fragmentLoginBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentLoginBinding.setAuthRequestUseCase(getMainViewModel().getAuthRequestUseCase());
        fragmentLoginBinding.setLoginViewModel(getFragmentViewModel());
        fragmentLoginBinding.buttonLogin.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.login.LoginFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginFragment.setupBinding$lambda$2$lambda$1(LoginFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$2$lambda$1(LoginFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        this$0.doAuthorization();
    }

    private final void setupViewModel() {
        getFragmentViewModel().getBackgroundProcessLogin().observe(getViewLifecycleOwner(), new LoginFragment$sam$androidx_lifecycle_Observer$0(new Function1<BackgroundProcess, Unit>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BackgroundProcess backgroundProcess) {
                invoke2(backgroundProcess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(BackgroundProcess backgroundProcess) {
                MainViewModel mainViewModel;
                MainViewModel mainViewModel2;
                if (backgroundProcess != null) {
                    LoginFragment loginFragment = LoginFragment.this;
                    if (backgroundProcess.isLoading()) {
                        return;
                    }
                    if (Intrinsics.areEqual((Object) backgroundProcess.isSuccess(), (Object) true)) {
                        mainViewModel2 = loginFragment.getMainViewModel();
                        mainViewModel2.finishLogin();
                        return;
                    }
                    BaseFragment.showSnackBar$default(loginFragment, "Error: " + backgroundProcess.getErrorMessage(), null, null, 6, null);
                    mainViewModel = loginFragment.getMainViewModel();
                    mainViewModel.logout();
                }
            }
        }));
        getMainViewModel().getGetLoggedInUserUseCase().isAuthenticated().observe(getViewLifecycleOwner(), new LoginFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.login.LoginFragment$setupViewModel$2$1
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
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    androidx.navigation.fragment.FragmentKt.findNavController(LoginFragment.this).popBackStack(R.id.dashboardFragment, false);
                }
            }
        }));
    }

    private final void doAuthorization() {
        try {
            if (getMainViewModel().getAuthRepository$app_productionRelease().getActiveProfile() != null) {
                getMainViewModel().getAuthRepository$app_productionRelease().logout();
            }
            AuthRequestUseCase authRequestUseCase = getMainViewModel().getAuthRequestUseCase();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ActivityResultLauncher<Intent> activityResultLauncher = this.authRequestLauncher;
            if (activityResultLauncher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                activityResultLauncher = null;
            }
            authRequestUseCase.start(requireContext, activityResultLauncher);
        } catch (ActivityNotFoundException e) {
            FirebaseCrashlytics.getInstance().recordException(new Exception(e));
            String string = getString(R.string.key_setup_error_browser_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
            BaseFragment.showSnackBar$default(this, string, null, null, 6, null);
        }
    }

    public final boolean checkUserAndCertificate() {
        SharedPreferencesModule sharedPreferencesModule = SharedPreferencesModule.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!sharedPreferencesModule.provideSharedPreferences(requireContext).getBoolean(SharedPreferencesModule.SP_APP_INITIALIZATION, false)) {
            androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(LoginFragmentDirections.Companion.actionLoginFragmentToCertificateFragment());
            return false;
        }
        SharedPreferencesModule sharedPreferencesModule2 = SharedPreferencesModule.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        boolean z = sharedPreferencesModule2.provideSharedPreferences(requireContext2).getBoolean(SharedPreferencesModule.SP_DONT_HAVE_PAIR_KEY, false);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        if (BYPASS_SIGNING.booleanValue() || z) {
            return true;
        }
        try {
            getFragmentViewModel().checkCertificateAndToken();
            return true;
        } catch (Exception e) {
            new AlertDialog.Builder(requireContext()).setCancelable(false).setTitle("Login Gagal").setMessage(e.getMessage()).setPositiveButton("Logout", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.login.LoginFragment$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.informatika.sirekap.ui.login.LoginFragment$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    LoginFragment.checkUserAndCertificate$lambda$6(LoginFragment.this, dialogInterface);
                }
            }).show();
            return false;
        }
    }

    public static final void checkUserAndCertificate$lambda$6(LoginFragment this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMainViewModel().logout();
    }

    /* compiled from: LoginFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/login/LoginFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
