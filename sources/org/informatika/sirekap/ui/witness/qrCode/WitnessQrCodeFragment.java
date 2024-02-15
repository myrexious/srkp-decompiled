package org.informatika.sirekap.ui.witness.qrCode;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
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
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentWitnessQrCodeBinding;
import org.informatika.sirekap.databinding.ViewWitnessShareBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: WitnessQrCodeFragment.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010(\u001a\u00020\u001aH\u0002J\b\u0010)\u001a\u00020\u001aH\u0002J\b\u0010*\u001a\u00020\u001aH\u0002J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017¨\u0006."}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentWitnessQrCodeBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "witnessQrCodeViewModel", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeViewModel;", "getWitnessQrCodeViewModel", "()Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeViewModel;", "witnessQrCodeViewModel$delegate", "copyUrlToClipboard", "", "url", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupLaunchers", "setupViewModel", "shareUrl", "syncWitness", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class WitnessQrCodeFragment extends Hilt_WitnessQrCodeFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessQrCodeFragment";
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentWitnessQrCodeBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy witnessQrCodeViewModel$delegate;

    public WitnessQrCodeFragment() {
        final WitnessQrCodeFragment witnessQrCodeFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.witnessQrCodeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessQrCodeFragment, Reflection.getOrCreateKotlinClass(WitnessQrCodeViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(WitnessQrCodeFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$navArgs$1
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessQrCodeFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = witnessQrCodeFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$special$$inlined$activityViewModels$default$3
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

    private final WitnessQrCodeViewModel getWitnessQrCodeViewModel() {
        return (WitnessQrCodeViewModel) this.witnessQrCodeViewModel$delegate.getValue();
    }

    private final WitnessQrCodeFragmentArgs getArgs() {
        return (WitnessQrCodeFragmentArgs) this.args$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda21
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessQrCodeFragment.setupLaunchers$lambda$0(WitnessQrCodeFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
    }

    public static final void setupLaunchers$lambda$0(WitnessQrCodeFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$setupLaunchers$1$1
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
                BaseFragment.showSnackBar$default(WitnessQrCodeFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Kirim Data PPS/Saksi/Panwas' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$setupLaunchers$1$2
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
                BaseFragment.showSnackBar$default(WitnessQrCodeFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWitnessQrCodeBinding inflate = FragmentWitnessQrCodeBinding.inflate(inflater, viewGroup, false);
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
        getWitnessQrCodeViewModel().setup(getArgs().getWitnessId());
    }

    private final void setupBinding() {
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding = this.binding;
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding2 = null;
        if (fragmentWitnessQrCodeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding = null;
        }
        fragmentWitnessQrCodeBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentWitnessQrCodeBinding.setViewModel(getWitnessQrCodeViewModel());
        fragmentWitnessQrCodeBinding.buttonSync.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$2$lambda$1(WitnessQrCodeFragment.this, view);
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding3 = this.binding;
        if (fragmentWitnessQrCodeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding3 = null;
        }
        ViewWitnessShareBinding viewWitnessShareBinding = fragmentWitnessQrCodeBinding3.sharePilpresCard;
        viewWitnessShareBinding.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$11$lambda$3(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$11$lambda$5(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView = viewWitnessShareBinding.cardUrl;
        materialCardView.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$11$lambda$10$lambda$7(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$11$lambda$10$lambda$9(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding4 = this.binding;
        if (fragmentWitnessQrCodeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding4 = null;
        }
        ViewWitnessShareBinding viewWitnessShareBinding2 = fragmentWitnessQrCodeBinding4.sharePkwkpCard;
        viewWitnessShareBinding2.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$20$lambda$12(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding2.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$20$lambda$14(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView2 = viewWitnessShareBinding2.cardUrl;
        materialCardView2.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$20$lambda$19$lambda$16(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView2.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda19
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$20$lambda$19$lambda$18(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding5 = this.binding;
        if (fragmentWitnessQrCodeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding5 = null;
        }
        ViewWitnessShareBinding viewWitnessShareBinding3 = fragmentWitnessQrCodeBinding5.sharePkwkkCard;
        viewWitnessShareBinding3.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$29$lambda$21(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding3.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$29$lambda$23(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView3 = viewWitnessShareBinding3.cardUrl;
        materialCardView3.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$29$lambda$28$lambda$25(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView3.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$29$lambda$28$lambda$27(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding6 = this.binding;
        if (fragmentWitnessQrCodeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding6 = null;
        }
        ViewWitnessShareBinding viewWitnessShareBinding4 = fragmentWitnessQrCodeBinding6.shareDprCard;
        viewWitnessShareBinding4.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$38$lambda$30(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding4.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$38$lambda$32(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView4 = viewWitnessShareBinding4.cardUrl;
        materialCardView4.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$38$lambda$37$lambda$34(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView4.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda27
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$38$lambda$37$lambda$36(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding7 = this.binding;
        if (fragmentWitnessQrCodeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding7 = null;
        }
        ViewWitnessShareBinding viewWitnessShareBinding5 = fragmentWitnessQrCodeBinding7.shareDprdpCard;
        viewWitnessShareBinding5.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$47$lambda$39(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding5.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$47$lambda$41(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView5 = viewWitnessShareBinding5.cardUrl;
        materialCardView5.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$47$lambda$46$lambda$43(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView5.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$47$lambda$46$lambda$45(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding8 = this.binding;
        if (fragmentWitnessQrCodeBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessQrCodeBinding8 = null;
        }
        ViewWitnessShareBinding viewWitnessShareBinding6 = fragmentWitnessQrCodeBinding8.shareDprdkCard;
        viewWitnessShareBinding6.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$56$lambda$48(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding6.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$56$lambda$50(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView6 = viewWitnessShareBinding6.cardUrl;
        materialCardView6.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$56$lambda$55$lambda$52(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView6.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$56$lambda$55$lambda$54(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
        FragmentWitnessQrCodeBinding fragmentWitnessQrCodeBinding9 = this.binding;
        if (fragmentWitnessQrCodeBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWitnessQrCodeBinding2 = fragmentWitnessQrCodeBinding9;
        }
        ViewWitnessShareBinding viewWitnessShareBinding7 = fragmentWitnessQrCodeBinding2.shareDpdCard;
        viewWitnessShareBinding7.buttonShareUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$65$lambda$57(WitnessQrCodeFragment.this, view);
            }
        });
        viewWitnessShareBinding7.buttonCopyUrl.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$65$lambda$59(WitnessQrCodeFragment.this, view);
            }
        });
        MaterialCardView materialCardView7 = viewWitnessShareBinding7.cardUrl;
        materialCardView7.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessQrCodeFragment.setupBinding$lambda$65$lambda$64$lambda$61(WitnessQrCodeFragment.this, view);
            }
        });
        materialCardView7.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean z;
                z = WitnessQrCodeFragment.setupBinding$lambda$65$lambda$64$lambda$63(WitnessQrCodeFragment.this, view);
                return z;
            }
        });
    }

    public static final void setupBinding$lambda$2$lambda$1(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Kirim Data Saksi'");
        if (this$0.getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$setupBinding$1$1$1
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
                    WitnessQrCodeFragment.this.syncWitness();
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$setupBinding$1$1$2
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
                    Toast.makeText(WitnessQrCodeFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = WitnessQrCodeFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = WitnessQrCodeFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = WitnessQrCodeFragment.this.authRequestLauncher;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        WitnessQrCodeFragment witnessQrCodeFragment = WitnessQrCodeFragment.this;
                        String string = witnessQrCodeFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(witnessQrCodeFragment, string, null, null, 6, null);
                    }
                }
            });
            return;
        }
        this$0.syncWitness();
    }

    public static final void setupBinding$lambda$11$lambda$3(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form ppwp'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_PRESIDEN);
    }

    public static final void setupBinding$lambda$11$lambda$5(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form ppwp'");
        String value = this$0.getWitnessQrCodeViewModel().getSharePilpres().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$11$lambda$10$lambda$7(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form ppwp'");
        String value = this$0.getWitnessQrCodeViewModel().getSharePilpres().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$11$lambda$10$lambda$9(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form ppwp' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getSharePilpres().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    public static final void setupBinding$lambda$20$lambda$12(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form pkwkp'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_PROVINSI);
    }

    public static final void setupBinding$lambda$20$lambda$14(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pkwkp'");
        String value = this$0.getWitnessQrCodeViewModel().getSharePkwkp().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$20$lambda$19$lambda$16(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form pkwkp'");
        String value = this$0.getWitnessQrCodeViewModel().getSharePkwkp().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$20$lambda$19$lambda$18(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pkwkp' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getSharePkwkp().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    public static final void setupBinding$lambda$29$lambda$21(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form pkwkk'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_KOTAKAB);
    }

    public static final void setupBinding$lambda$29$lambda$23(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pkwkk'");
        String value = this$0.getWitnessQrCodeViewModel().getSharePkwkk().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$29$lambda$28$lambda$25(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form pkwkk'");
        String value = this$0.getWitnessQrCodeViewModel().getSharePkwkk().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$29$lambda$28$lambda$27(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pkwkk' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getSharePkwkk().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    public static final void setupBinding$lambda$38$lambda$30(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form pdpr'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_DPR);
    }

    public static final void setupBinding$lambda$38$lambda$32(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdpr'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDpr().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$38$lambda$37$lambda$34(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form pdpr'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDpr().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$38$lambda$37$lambda$36(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdpr' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getShareDpr().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    public static final void setupBinding$lambda$47$lambda$39(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form pdprdp'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
    }

    public static final void setupBinding$lambda$47$lambda$41(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdprdp'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDprdp().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$47$lambda$46$lambda$43(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form pdprdp'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDprdp().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$47$lambda$46$lambda$45(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdprdp' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getShareDprdp().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    public static final void setupBinding$lambda$56$lambda$48(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form dprdk'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
    }

    public static final void setupBinding$lambda$56$lambda$50(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdprdk'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDprdk().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$56$lambda$55$lambda$52(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form pdprdk'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDprdk().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$56$lambda$55$lambda$54(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdprdk' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getShareDprdk().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    public static final void setupBinding$lambda$65$lambda$57(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form pdpd'");
        this$0.getWitnessQrCodeViewModel().markWitnessAsShared(Election.ELECTION_PEMILIHAN_DPD);
    }

    public static final void setupBinding$lambda$65$lambda$59(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdpd'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDpd().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
        }
    }

    public static final void setupBinding$lambda$65$lambda$64$lambda$61(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form pdpd'");
        String value = this$0.getWitnessQrCodeViewModel().getShareDpd().getUrl().getValue();
        if (value != null) {
            this$0.shareUrl(value);
        }
    }

    public static final boolean setupBinding$lambda$65$lambda$64$lambda$63(WitnessQrCodeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form pdpd' (long click)");
        String value = this$0.getWitnessQrCodeViewModel().getShareDpd().getUrl().getValue();
        if (value != null) {
            this$0.copyUrlToClipboard(value);
            return true;
        }
        return true;
    }

    private final void setupViewModel() {
        getWitnessQrCodeViewModel().getMarkWitnessResource().observe(getViewLifecycleOwner(), new WitnessQrCodeFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends WitnessWithShare>, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment$setupViewModel$1$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends WitnessWithShare> resource) {
                invoke2((Resource<WitnessWithShare>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<WitnessWithShare> resource) {
                Log.wtf("WitnessQrCodeFragment", String.valueOf(resource));
            }
        }));
    }

    private final void shareUrl(String str) {
        Snackbar snackBar = getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.share_url_witness_via)));
    }

    private final void copyUrlToClipboard(String str) {
        Snackbar snackBar = getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText(getString(R.string.url_saksi), str);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
        }
        String string = getString(R.string.url_copied);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.url_copied)");
        BaseFragment.showSnackBar$default(this, string, null, null, 6, null);
    }

    public final void syncWitness() {
        getWitnessQrCodeViewModel().syncWitness2(getArgs().getWitnessId());
    }

    /* compiled from: WitnessQrCodeFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
