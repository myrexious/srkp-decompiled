package org.informatika.sirekap.ui.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.work.WorkRequest;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.databinding.FragmentCertificateBinding;
import org.informatika.sirekap.support.messaging.CertificateGenerationMessage;
import org.informatika.sirekap.support.messaging.CertificateGenerationResponse;
import org.informatika.sirekap.support.worker.security.CertificateCheckerTask;

/* compiled from: CertificateFragment.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u000fH\u0002J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020&H\u0016J\u001a\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010)\u001a\u00020&H\u0002J\u0006\u0010*\u001a\u00020&J\b\u0010+\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00040\u00040\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/ui/security/CertificateFragment;", "Landroidx/fragment/app/Fragment;", "()V", "DELAY_FALLBACK_MS", "", "binding", "Lorg/informatika/sirekap/databinding/FragmentCertificateBinding;", "certificateViewModel", "Lorg/informatika/sirekap/ui/security/CertificateViewModel;", "getCertificateViewModel", "()Lorg/informatika/sirekap/ui/security/CertificateViewModel;", "certificateViewModel$delegate", "Lkotlin/Lazy;", "hasBeenPaused", "Landroidx/lifecycle/MutableLiveData;", "", "lastTry", "kotlin.jvm.PlatformType", "localAuthLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "generateCertificate", "Lkotlinx/coroutines/Job;", "exitWhenFailed", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "setupBinding", "setupLauncher", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class CertificateFragment extends Hilt_CertificateFragment {
    public static final String CERTIFICATE_FRAGMENT_RESULT = "CERTIFICATE_FRAGMENT_RESULT";
    public static final String CERTIFICATE_FRAGMENT_RESULT_IS_SUCCESS = "CERTIFICATE_FRAGMENT_RESULT_IS_SUCCESS";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CertificateFragment";
    private final long DELAY_FALLBACK_MS;
    private FragmentCertificateBinding binding;
    private final Lazy certificateViewModel$delegate;
    private final MutableLiveData<Boolean> hasBeenPaused;
    private final MutableLiveData<Long> lastTry;
    private ActivityResultLauncher<Intent> localAuthLauncher;
    private final Lazy mainViewModel$delegate;

    public CertificateFragment() {
        final CertificateFragment certificateFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.certificateViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(certificateFragment, Reflection.getOrCreateKotlinClass(CertificateViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$viewModels$default$5
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
        this.hasBeenPaused = new MutableLiveData<>(false);
        this.lastTry = new MutableLiveData<>(0L);
        this.DELAY_FALLBACK_MS = WorkRequest.MIN_BACKOFF_MILLIS;
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(certificateFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = certificateFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$special$$inlined$activityViewModels$default$3
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

    public final CertificateViewModel getCertificateViewModel() {
        return (CertificateViewModel) this.certificateViewModel$delegate.getValue();
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    public final void setupLauncher() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CertificateFragment.setupLauncher$lambda$0(CertificateFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.localAuthLauncher = registerForActivityResult;
    }

    public static final void setupLauncher$lambda$0(CertificateFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.getMainViewModel().getLocalAuth().postValue(true);
            this$0.generateCertificate(false);
            return;
        }
        this$0.getCertificateViewModel().failedAuthentication("Silahkan coba lagi beberapa saat");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentCertificateBinding inflate = FragmentCertificateBinding.inflate(inflater, viewGroup, false);
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
        setupLauncher();
        CertificateViewModel certificateViewModel = getCertificateViewModel();
        CertificateFragment certificateFragment = this;
        ActivityResultLauncher<Intent> activityResultLauncher = this.localAuthLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
            activityResultLauncher = null;
        }
        certificateViewModel.doLocalAuthentication(certificateFragment, activityResultLauncher, new CertificateFragment$onViewCreated$1(this));
    }

    private final void setupBinding() {
        FragmentCertificateBinding fragmentCertificateBinding = this.binding;
        if (fragmentCertificateBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCertificateBinding = null;
        }
        fragmentCertificateBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentCertificateBinding.setFragmentViewModel(getCertificateViewModel());
        fragmentCertificateBinding.buttonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CertificateFragment.setupBinding$lambda$5$lambda$2(CertificateFragment.this, view);
            }
        });
        fragmentCertificateBinding.buttonCheck.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CertificateFragment.setupBinding$lambda$5$lambda$4(CertificateFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$5$lambda$2(CertificateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getCertificateViewModel().isCheckButtonEnabled().postValue(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CertificateFragment.setupBinding$lambda$5$lambda$2$lambda$1(CertificateFragment.this);
            }
        }, this$0.DELAY_FALLBACK_MS);
        if (Intrinsics.areEqual((Object) this$0.getCertificateViewModel().isFailedAuthentication().getValue(), (Object) true)) {
            CertificateViewModel certificateViewModel = this$0.getCertificateViewModel();
            CertificateFragment certificateFragment = this$0;
            ActivityResultLauncher<Intent> activityResultLauncher = this$0.localAuthLauncher;
            if (activityResultLauncher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
                activityResultLauncher = null;
            }
            certificateViewModel.doLocalAuthentication(certificateFragment, activityResultLauncher, new Function1<Context, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$setupBinding$1$1$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Context context) {
                    invoke2(context);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Context it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    CertificateFragment.this.generateCertificate(false);
                }
            });
            return;
        }
        this$0.generateCertificate(false);
    }

    public static final void setupBinding$lambda$5$lambda$2$lambda$1(CertificateFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getCertificateViewModel().isCheckButtonEnabled().postValue(true);
    }

    public static final void setupBinding$lambda$5$lambda$4(CertificateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getCertificateViewModel().isCheckButtonEnabled().postValue(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CertificateFragment.setupBinding$lambda$5$lambda$4$lambda$3(CertificateFragment.this);
            }
        }, this$0.DELAY_FALLBACK_MS);
        generateCertificate$default(this$0, false, 1, null);
    }

    public static final void setupBinding$lambda$5$lambda$4$lambda$3(CertificateFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getCertificateViewModel().isCheckButtonEnabled().postValue(true);
    }

    private final void setupViewModel() {
        CertificateViewModel certificateViewModel = getCertificateViewModel();
        certificateViewModel.isSuccess().observe(getViewLifecycleOwner(), new CertificateFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$setupViewModel$1$1
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
                    FragmentKt.setFragmentResult(CertificateFragment.this, CertificateFragment.CERTIFICATE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(CertificateFragment.CERTIFICATE_FRAGMENT_RESULT_IS_SUCCESS, true)));
                    androidx.navigation.fragment.FragmentKt.findNavController(CertificateFragment.this).navigateUp();
                }
            }
        }));
        certificateViewModel.getShouldRelogin().observe(getViewLifecycleOwner(), new CertificateFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$setupViewModel$1$2
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
                    FragmentKt.setFragmentResult(CertificateFragment.this, CertificateFragment.CERTIFICATE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(CertificateFragment.CERTIFICATE_FRAGMENT_RESULT_IS_SUCCESS, false)));
                    androidx.navigation.fragment.FragmentKt.findNavController(CertificateFragment.this).navigateUp();
                }
            }
        }));
        CertificateGenerationMessage.INSTANCE.getCertificateResponse().observe(getViewLifecycleOwner(), new CertificateFragment$sam$androidx_lifecycle_Observer$0(new Function1<CertificateGenerationResponse, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$setupViewModel$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CertificateGenerationResponse certificateGenerationResponse) {
                invoke2(certificateGenerationResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(CertificateGenerationResponse certificateGenerationResponse) {
                if (certificateGenerationResponse == null) {
                    return;
                }
                CertificateFragment.generateCertificate$default(CertificateFragment.this, false, 1, null);
            }
        }));
    }

    public static /* synthetic */ Job generateCertificate$default(CertificateFragment certificateFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return certificateFragment.generateCertificate(z);
    }

    public final Job generateCertificate(boolean z) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CertificateFragment$generateCertificate$1(this, z, null), 3, null);
        return launch$default;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CertificateCheckerTask certificateCheckerWorker = getCertificateViewModel().getCertificateCheckerWorker();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "this.requireContext()");
        certificateCheckerWorker.stop(requireContext);
    }

    /* compiled from: CertificateFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/ui/security/CertificateFragment$Companion;", "", "()V", CertificateFragment.CERTIFICATE_FRAGMENT_RESULT, "", CertificateFragment.CERTIFICATE_FRAGMENT_RESULT_IS_SUCCESS, "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
