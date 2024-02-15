package org.informatika.sirekap.ui.loginTps;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.snackbar.Snackbar;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentLoginTpsBinding;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.BaseFragment;

/* compiled from: LoginTpsFragment.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001e\u001a\u00020\u001bH\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0002J\b\u0010 \u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/informatika/sirekap/ui/loginTps/LoginTpsFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "binding", "Lorg/informatika/sirekap/databinding/FragmentLoginTpsBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/loginTps/LoginTpsViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/loginTps/LoginTpsViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "setupBinding", "setupNavigation", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class LoginTpsFragment extends Hilt_LoginTpsFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "LoginTpsFragment";
    private FragmentLoginTpsBinding binding;
    private final Lazy fragmentViewModel$delegate;
    private final Lazy mainViewModel$delegate;
    private OnBackPressedCallback onBackPressedCallback;

    public LoginTpsFragment() {
        final LoginTpsFragment loginTpsFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(loginTpsFragment, Reflection.getOrCreateKotlinClass(LoginTpsViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(loginTpsFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = loginTpsFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$special$$inlined$activityViewModels$default$3
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

    private final LoginTpsViewModel getFragmentViewModel() {
        return (LoginTpsViewModel) this.fragmentViewModel$delegate.getValue();
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentLoginTpsBinding inflate = FragmentLoginTpsBinding.inflate(inflater, viewGroup, false);
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
        getFragmentViewModel().getGetListTPSUseCase().setup();
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

    private final void setupBinding() {
        FragmentLoginTpsBinding fragmentLoginTpsBinding = this.binding;
        if (fragmentLoginTpsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginTpsBinding = null;
        }
        fragmentLoginTpsBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentLoginTpsBinding.setViewModel(getFragmentViewModel());
        fragmentLoginTpsBinding.setGetListTPSUseCase(getFragmentViewModel().getGetListTPSUseCase());
        fragmentLoginTpsBinding.setLoginTpsFormState(getFragmentViewModel().getLoginTpsFormState());
        fragmentLoginTpsBinding.buttonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginTpsFragment.setupBinding$lambda$2$lambda$0(LoginTpsFragment.this, view);
            }
        });
        fragmentLoginTpsBinding.buttonLogin.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginTpsFragment.setupBinding$lambda$2$lambda$1(LoginTpsFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$2$lambda$0(LoginTpsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        this$0.getFragmentViewModel().getGetListTPSUseCase().refresh();
    }

    public static final void setupBinding$lambda$2$lambda$1(LoginTpsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        LoginTpsViewModel fragmentViewModel = this$0.getFragmentViewModel();
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Tps tpsInternalValue = this$0.getFragmentViewModel().getLoginTpsFormState().getTpsInternalValue();
        Intrinsics.checkNotNull(tpsInternalValue);
        fragmentViewModel.doLogin(requireContext, tpsInternalValue.getKodeTps());
    }

    private final void setupNavigation() {
        this.onBackPressedCallback = new OnBackPressedCallback() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$setupNavigation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                LoginTpsFragment.this.requireActivity().finish();
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

    private final void setupViewModel() {
        final LoginTpsViewModel fragmentViewModel = getFragmentViewModel();
        fragmentViewModel.getGetListTPSUseCase().getResource().observe(getViewLifecycleOwner(), new LoginTpsFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends List<? extends Tps>>, Unit>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$setupViewModel$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends List<? extends Tps>> resource) {
                invoke2((Resource<? extends List<Tps>>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<? extends List<Tps>> resource) {
                FragmentLoginTpsBinding fragmentLoginTpsBinding;
                FragmentLoginTpsBinding fragmentLoginTpsBinding2;
                if (resource != null) {
                    LoginTpsViewModel loginTpsViewModel = LoginTpsViewModel.this;
                    LoginTpsFragment loginTpsFragment = this;
                    if (resource.getSuccess() != ResourceStatus.SUCCESS || resource.getPayload() == null) {
                        return;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    List<Tps> payload = resource.getPayload();
                    Intrinsics.checkNotNull(payload);
                    Iterator<T> it = payload.iterator();
                    while (true) {
                        fragmentLoginTpsBinding = null;
                        String str = null;
                        if (!it.hasNext()) {
                            break;
                        }
                        Tps tps = (Tps) it.next();
                        String name = tps.getName();
                        Kelurahan kelurahan = tps.getKelurahan();
                        if (kelurahan != null) {
                            str = kelurahan.getName();
                        }
                        linkedHashMap.put(name + StringUtils.SPACE + str, tps);
                    }
                    loginTpsViewModel.getLoginTpsFormState().setTpsOptions(linkedHashMap);
                    List<Tps> payload2 = resource.getPayload();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(payload2, 10));
                    for (Tps tps2 : payload2) {
                        String name2 = tps2.getName();
                        Kelurahan kelurahan2 = tps2.getKelurahan();
                        arrayList.add(name2 + StringUtils.SPACE + (kelurahan2 != null ? kelurahan2.getName() : null));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(loginTpsFragment.requireContext(), 17367043, arrayList);
                    fragmentLoginTpsBinding2 = loginTpsFragment.binding;
                    if (fragmentLoginTpsBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentLoginTpsBinding = fragmentLoginTpsBinding2;
                    }
                    fragmentLoginTpsBinding.tpsInput.setAdapter(arrayAdapter);
                }
            }
        }));
        fragmentViewModel.getBackgroundProcessLogin().observe(getViewLifecycleOwner(), new LoginTpsFragment$sam$androidx_lifecycle_Observer$0(new Function1<BackgroundProcess, Unit>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$setupViewModel$1$2
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
                if (backgroundProcess != null) {
                    LoginTpsFragment loginTpsFragment = LoginTpsFragment.this;
                    if (backgroundProcess.isLoading()) {
                        return;
                    }
                    if (Intrinsics.areEqual((Object) backgroundProcess.isSuccess(), (Object) true)) {
                        mainViewModel = loginTpsFragment.getMainViewModel();
                        mainViewModel.finishLogin();
                        return;
                    }
                    BaseFragment.showSnackBar$default(loginTpsFragment, "Error: " + backgroundProcess.getErrorMessage(), null, null, 6, null);
                }
            }
        }));
        getMainViewModel().getGetLoggedInUserUseCase().isAuthenticated().observe(getViewLifecycleOwner(), new LoginTpsFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsFragment$setupViewModel$2$1
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
                    FragmentKt.findNavController(LoginTpsFragment.this).popBackStack(R.id.dashboardFragment, false);
                }
            }
        }));
    }

    /* compiled from: LoginTpsFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/loginTps/LoginTpsFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
