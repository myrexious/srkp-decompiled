package org.informatika.sirekap.ui.verify.tabulationPartai;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBinding;
import org.informatika.sirekap.databinding.DialogVerifyBodyBinding;
import org.informatika.sirekap.databinding.FragmentVerifyTabulationPartaiBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.model.FormConfig;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.UiUtil;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.sendImage.SendImageFragment;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1AdministrationAdapter;
import org.informatika.sirekap.ui.verify.FormC1ListItem;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: VerifyTabulationPartaiFragment.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 22\u00020\u0001:\u00012B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020\u001dH\u0002J\b\u0010-\u001a\u00020\u001dH\u0002J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001a¨\u00063"}, d2 = {"Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapterPerolehanSuara", "Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter;", "args", "Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "authRequestLauncher2", "binding", "Lorg/informatika/sirekap/databinding/FragmentVerifyTabulationPartaiBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "verifyTabulationPartaiViewModel", "Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiViewModel;", "getVerifyTabulationPartaiViewModel", "()Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiViewModel;", "verifyTabulationPartaiViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "retakePhoto", "isManual", "", "setupBinding", "setupLaunchers", "setupViewModel", "showCorrectionDialog", "item", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "trySubmit", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class VerifyTabulationPartaiFragment extends Hilt_VerifyTabulationPartaiFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VerifyTabParFragment";
    private FormC1AdministrationAdapter adapterPerolehanSuara;
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private ActivityResultLauncher<Intent> authRequestLauncher2;
    private FragmentVerifyTabulationPartaiBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy verifyTabulationPartaiViewModel$delegate;

    public static final void showCorrectionDialog$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23(DialogInterface dialogInterface, int i) {
    }

    public VerifyTabulationPartaiFragment() {
        final VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.verifyTabulationPartaiViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifyTabulationPartaiFragment, Reflection.getOrCreateKotlinClass(VerifyTabulationPartaiViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(VerifyTabulationPartaiFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$navArgs$1
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifyTabulationPartaiFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = verifyTabulationPartaiFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$special$$inlined$activityViewModels$default$3
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

    public final VerifyTabulationPartaiViewModel getVerifyTabulationPartaiViewModel() {
        return (VerifyTabulationPartaiViewModel) this.verifyTabulationPartaiViewModel$delegate.getValue();
    }

    public final VerifyTabulationPartaiFragmentArgs getArgs() {
        return (VerifyTabulationPartaiFragmentArgs) this.args$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifyTabulationPartaiFragment.setupLaunchers$lambda$0(VerifyTabulationPartaiFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifyTabulationPartaiFragment.setupLaunchers$lambda$1(VerifyTabulationPartaiFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher2 = registerForActivityResult2;
    }

    public static final void setupLaunchers$lambda$0(VerifyTabulationPartaiFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupLaunchers$1$1
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
                BaseFragment.showSnackBar$default(VerifyTabulationPartaiFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Submit' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupLaunchers$1$2
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
                BaseFragment.showSnackBar$default(VerifyTabulationPartaiFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    public static final void setupLaunchers$lambda$1(VerifyTabulationPartaiFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupLaunchers$2$1
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
                BaseFragment.showSnackBar$default(VerifyTabulationPartaiFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Ulangi' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupLaunchers$2$2
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
                BaseFragment.showSnackBar$default(VerifyTabulationPartaiFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentVerifyTabulationPartaiBinding inflate = FragmentVerifyTabulationPartaiBinding.inflate(inflater, viewGroup, false);
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
        getVerifyTabulationPartaiViewModel().getGetElectionPageUseCase().setup(getArgs().getElectionPageId());
    }

    private final void setupBinding() {
        FragmentVerifyTabulationPartaiBinding fragmentVerifyTabulationPartaiBinding = this.binding;
        FormC1AdministrationAdapter formC1AdministrationAdapter = null;
        if (fragmentVerifyTabulationPartaiBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyTabulationPartaiBinding = null;
        }
        fragmentVerifyTabulationPartaiBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentVerifyTabulationPartaiBinding.setViewModel(getVerifyTabulationPartaiViewModel());
        fragmentVerifyTabulationPartaiBinding.setGetElectionPageUseCase(getVerifyTabulationPartaiViewModel().getGetElectionPageUseCase());
        fragmentVerifyTabulationPartaiBinding.c1ImageViewCard.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyTabulationPartaiFragment.setupBinding$lambda$8$lambda$3(VerifyTabulationPartaiFragment.this, view);
            }
        });
        fragmentVerifyTabulationPartaiBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyTabulationPartaiFragment.setupBinding$lambda$8$lambda$4(VerifyTabulationPartaiFragment.this, view);
            }
        });
        fragmentVerifyTabulationPartaiBinding.buttonContinueVerify.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyTabulationPartaiFragment.setupBinding$lambda$8$lambda$5(VerifyTabulationPartaiFragment.this, view);
            }
        });
        fragmentVerifyTabulationPartaiBinding.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyTabulationPartaiFragment.setupBinding$lambda$8$lambda$6(VerifyTabulationPartaiFragment.this, view);
            }
        });
        fragmentVerifyTabulationPartaiBinding.butonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyTabulationPartaiFragment.setupBinding$lambda$8$lambda$7(VerifyTabulationPartaiFragment.this, view);
            }
        });
        this.adapterPerolehanSuara = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupBinding$1$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormC1ListItem formC1ListItem) {
                invoke2(formC1ListItem);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(FormC1ListItem it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VerifyTabulationPartaiFragment.this.showCorrectionDialog(it);
            }
        });
        RecyclerView recyclerView = fragmentVerifyTabulationPartaiBinding.recyclerViewDataPerolehanSuara;
        FormC1AdministrationAdapter formC1AdministrationAdapter2 = this.adapterPerolehanSuara;
        if (formC1AdministrationAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterPerolehanSuara");
        } else {
            formC1AdministrationAdapter = formC1AdministrationAdapter2;
        }
        recyclerView.setAdapter(formC1AdministrationAdapter);
    }

    public static final void setupBinding$lambda$8$lambda$3(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        String value = this$0.getVerifyTabulationPartaiViewModel().getPreviewImagePath().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(VerifyTabulationPartaiFragmentDirections.Companion.actionVerifyTabulationPartaiFragmentToPreviewImageFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$8$lambda$4(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("VerifyTabParFragment: User clicks 'Ulang Foto' button");
        this$0.retakePhoto(false);
    }

    public static final void setupBinding$lambda$8$lambda$5(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyTabulationPartaiViewModel().continueVerify();
    }

    public static final void setupBinding$lambda$8$lambda$6(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("VerifyTabParFragment Click 'Submit'");
        if (ElectionUtil.extractIdImageReal(this$0.getArgs().getIdImage()) < 0) {
            this$0.trySubmit();
        } else if (this$0.getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupBinding$1$4$1
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
                    VerifyTabulationPartaiFragment.this.trySubmit();
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupBinding$1$4$2
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
                    Toast.makeText(VerifyTabulationPartaiFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = VerifyTabulationPartaiFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = VerifyTabulationPartaiFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = VerifyTabulationPartaiFragment.this.authRequestLauncher;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = VerifyTabulationPartaiFragment.this;
                        String string = verifyTabulationPartaiFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(verifyTabulationPartaiFragment, string, null, null, 6, null);
                    }
                }
            });
        } else {
            this$0.trySubmit();
        }
    }

    public static final void setupBinding$lambda$8$lambda$7(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupBinding$1$5$1
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
                    VerifyTabulationPartaiViewModel verifyTabulationPartaiViewModel;
                    Intrinsics.checkNotNullParameter(it, "it");
                    verifyTabulationPartaiViewModel = VerifyTabulationPartaiFragment.this.getVerifyTabulationPartaiViewModel();
                    verifyTabulationPartaiViewModel.refreshIdImage();
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupBinding$1$5$2
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
                    Toast.makeText(VerifyTabulationPartaiFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = VerifyTabulationPartaiFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = VerifyTabulationPartaiFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = VerifyTabulationPartaiFragment.this.authRequestLauncher2;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher2");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = VerifyTabulationPartaiFragment.this;
                        String string = verifyTabulationPartaiFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(verifyTabulationPartaiFragment, string, null, null, 6, null);
                    }
                }
            });
            return;
        }
        this$0.getVerifyTabulationPartaiViewModel().refreshIdImage();
    }

    public final void trySubmit() {
        Boolean value = getVerifyTabulationPartaiViewModel().isCheckAllTrue().getValue();
        if (value != null) {
            getVerifyTabulationPartaiViewModel().getVerifyForm().setVerifyOption(value.booleanValue());
            DialogVerifyBodyBinding inflate = DialogVerifyBodyBinding.inflate(getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
            inflate.setLifecycleOwner(getViewLifecycleOwner());
            inflate.setViewModel(getVerifyTabulationPartaiViewModel());
            inflate.setHideCommentField(true);
            materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) getString(R.string.submit_verify_administration_dialog_message));
            final AlertDialog create = materialAlertDialogBuilder.create();
            Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…))\n            }.create()");
            create.show();
            inflate.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.trySubmit$lambda$11$lambda$10(VerifyTabulationPartaiFragment.this, create, view);
                }
            });
        }
    }

    public static final void trySubmit$lambda$11$lambda$10(VerifyTabulationPartaiFragment this$0, AlertDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        VerifyTabulationPartaiViewModel verifyTabulationPartaiViewModel = this$0.getVerifyTabulationPartaiViewModel();
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        verifyTabulationPartaiViewModel.submitVerification(requireContext, dialog);
    }

    private final void retakePhoto(boolean z) {
        VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = this;
        androidx.fragment.app.FragmentKt.setFragmentResult(verifyTabulationPartaiFragment, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO, true), TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE, new Gson().toJson(getVerifyTabulationPartaiViewModel().getGetElectionPageUseCase().getElectionPage().getValue())), TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_IS_MANUAL, Boolean.valueOf(z))));
        FragmentKt.findNavController(verifyTabulationPartaiFragment).navigateUp();
    }

    private final void setupViewModel() {
        VerifyTabulationPartaiViewModel verifyTabulationPartaiViewModel = getVerifyTabulationPartaiViewModel();
        verifyTabulationPartaiViewModel.getFormC1TabulationPartaiCompleteResource().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends FormC1TabulationPartaiComplete>, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends FormC1TabulationPartaiComplete> resource) {
                invoke2((Resource<FormC1TabulationPartaiComplete>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<FormC1TabulationPartaiComplete> resource) {
                if (resource != null) {
                    VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = VerifyTabulationPartaiFragment.this;
                    if (resource.getSuccess() == ResourceStatus.ERROR) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception("VerifyTabParFragment " + resource.getError()));
                        String string = verifyTabulationPartaiFragment.getString(R.string.fetch_formchasil_message_error_snackbar, resource.getError());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                        BaseFragment.showSnackBar$default(verifyTabulationPartaiFragment, string, null, null, 6, null);
                        return;
                    }
                    resource.getSuccess();
                    ResourceStatus resourceStatus = ResourceStatus.LOADING;
                }
            }
        }));
        verifyTabulationPartaiViewModel.getFormC1Kesesuaian().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1Kesesuaian, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormC1Kesesuaian formC1Kesesuaian) {
                invoke2(formC1Kesesuaian);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(FormC1Kesesuaian formC1Kesesuaian) {
                FormC1AdministrationAdapter formC1AdministrationAdapter;
                if (formC1Kesesuaian != null) {
                    formC1AdministrationAdapter = VerifyTabulationPartaiFragment.this.adapterPerolehanSuara;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPerolehanSuara");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.done();
                }
            }
        }));
        verifyTabulationPartaiViewModel.getGetElectionPageUseCase().getElectionPageWithRelation().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionPageWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$3$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ElectionPageWithRelation electionPageWithRelation) {
                invoke2(electionPageWithRelation);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ElectionPageWithRelation electionPageWithRelation) {
                VerifyTabulationPartaiViewModel verifyTabulationPartaiViewModel2;
                VerifyTabulationPartaiFragmentArgs args;
                VerifyTabulationPartaiFragmentArgs args2;
                VerifyTabulationPartaiViewModel verifyTabulationPartaiViewModel3;
                VerifyTabulationPartaiViewModel verifyTabulationPartaiViewModel4;
                if (electionPageWithRelation != null) {
                    VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = VerifyTabulationPartaiFragment.this;
                    verifyTabulationPartaiViewModel2 = verifyTabulationPartaiFragment.getVerifyTabulationPartaiViewModel();
                    args = verifyTabulationPartaiFragment.getArgs();
                    String idImage = args.getIdImage();
                    args2 = verifyTabulationPartaiFragment.getArgs();
                    verifyTabulationPartaiViewModel2.setup(idImage, args2.getElectionPageId(), electionPageWithRelation.getElection().getPemilihan(), electionPageWithRelation.getElection().getTps().getKodeTps());
                    verifyTabulationPartaiViewModel3 = verifyTabulationPartaiFragment.getVerifyTabulationPartaiViewModel();
                    if (verifyTabulationPartaiViewModel3.getPreviewImagePath().getValue() == null) {
                        verifyTabulationPartaiViewModel4 = verifyTabulationPartaiFragment.getVerifyTabulationPartaiViewModel();
                        verifyTabulationPartaiViewModel4.getPreviewImagePath().postValue(electionPageWithRelation.getElectionPage().getCroppedPhotoPath());
                    }
                }
            }
        }));
        verifyTabulationPartaiViewModel.getTablePerolehanSuara().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<FormC1ListItem> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<FormC1ListItem> list) {
                FormC1AdministrationAdapter formC1AdministrationAdapter;
                if (list != null) {
                    formC1AdministrationAdapter = VerifyTabulationPartaiFragment.this.adapterPerolehanSuara;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPerolehanSuara");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        verifyTabulationPartaiViewModel.isSesuaiPerItem().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$5
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Boolean> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Boolean> list) {
                invoke2((List<Boolean>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyTabulationPartaiViewModel.getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$6
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyTabulationPartaiViewModel.isCheckAllTrue().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$7
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
        verifyTabulationPartaiViewModel.getSubmittedFormResource().observe(getViewLifecycleOwner(), new VerifyTabulationPartaiFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends FormC1AdministrationComplete>, Unit>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$setupViewModel$1$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends FormC1AdministrationComplete> resource) {
                invoke2((Resource<FormC1AdministrationComplete>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<FormC1AdministrationComplete> resource) {
                if (resource != null) {
                    VerifyTabulationPartaiFragment verifyTabulationPartaiFragment = VerifyTabulationPartaiFragment.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        String string = verifyTabulationPartaiFragment.getString(R.string.verify_message_success_snackbar);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.verif…message_success_snackbar)");
                        BaseFragment.showSnackBar$default(verifyTabulationPartaiFragment, string, null, null, 6, null);
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String string2 = verifyTabulationPartaiFragment.getString(R.string.verify_message_error_snackbar, resource.getError());
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.verif…error_snackbar, it.error)");
                        BaseFragment.showSnackBar$default(verifyTabulationPartaiFragment, string2, null, null, 6, null);
                    }
                }
            }
        }));
    }

    public final void showCorrectionDialog(final FormC1ListItem formC1ListItem) {
        FormConfig config;
        Bitmap decodeFile;
        if (formC1ListItem.isChecked()) {
            getVerifyTabulationPartaiViewModel().setupCheckDialog(!Intrinsics.areEqual((Object) formC1ListItem.getCheckL(), (Object) true), !Intrinsics.areEqual((Object) formC1ListItem.getCheckP(), (Object) true), !Intrinsics.areEqual((Object) formC1ListItem.getCheckTotal(), (Object) true), formC1ListItem.getCorrectedL() != null ? formC1ListItem.getCorrectedL().toString() : "", formC1ListItem.getCorrectedP() != null ? formC1ListItem.getCorrectedP().toString() : "", formC1ListItem.getCorrectedTotal() != null ? formC1ListItem.getCorrectedTotal().toString() : "");
        } else {
            BaseVerifyViewModel.setupCheckDialog$default(getVerifyTabulationPartaiViewModel(), false, false, false, null, null, null, 63, null);
        }
        final DialogCheckFormC1ListItemBinding inflate = DialogCheckFormC1ListItemBinding.inflate(getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        inflate.setLifecycleOwner(getViewLifecycleOwner());
        inflate.setShowCorrection(true);
        inflate.setItem(formC1ListItem);
        inflate.setViewModel(getVerifyTabulationPartaiViewModel());
        try {
            getVerifyTabulationPartaiViewModel().resetImage();
            FormConfig.Companion companion = FormConfig.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String electionType = formC1ListItem.getElectionType();
            String str = electionType == null ? "" : electionType;
            boolean areEqual = Intrinsics.areEqual((Object) formC1ListItem.isLN(), (Object) true);
            boolean areEqual2 = Intrinsics.areEqual((Object) formC1ListItem.isPOS(), (Object) true);
            Integer candidateNum = formC1ListItem.getCandidateNum();
            config = companion.getConfig(requireContext, FormConfig.FORM_TALLY, str, areEqual, areEqual2, candidateNum != null ? candidateNum.intValue() : 0);
            String correctedPhotoPath = formC1ListItem.getCorrectedPhotoPath();
            if (correctedPhotoPath == null) {
                correctedPhotoPath = formC1ListItem.getCroppedPhotoPath();
            }
            decodeFile = BitmapFactory.decodeFile(correctedPhotoPath);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Error tidak diketahui";
            }
            getVerifyTabulationPartaiViewModel().getKesesuaianSliceError().postValue(message);
            inflate.kesesuaianSliceErrorShowDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda14
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$22$lambda$14(VerifyTabulationPartaiFragment.this, view);
                }
            });
            inflate.kesesuaianSliceErrorHideDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$22$lambda$15(VerifyTabulationPartaiFragment.this, view);
                }
            });
        }
        if (decodeFile != null) {
            String id2 = formC1ListItem.getId();
            Integer no = formC1ListItem.getNo();
            Integer candidateNum2 = formC1ListItem.getCandidateNum();
            getVerifyTabulationPartaiViewModel().getPreviewKesesuaianBitmap().postValue(config.getKesesuaianSlicedMat(FormConfig.VISION_TYPE_TALLY, id2, decodeFile, no, candidateNum2 != null ? candidateNum2.intValue() : 0));
            inflate.rowL.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$22$lambda$17(VerifyTabulationPartaiFragment.this, inflate, view);
                }
            });
            inflate.rowP.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$22$lambda$19(VerifyTabulationPartaiFragment.this, inflate, view);
                }
            });
            inflate.rowTotal.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$22$lambda$21(VerifyTabulationPartaiFragment.this, inflate, view);
                }
            });
            inflate.inputCorrectionL.setText(formC1ListItem.getCorrectedL() != null ? formC1ListItem.getCorrectedL().toString() : "");
            inflate.inputCorrectionP.setText(formC1ListItem.getCorrectedP() != null ? formC1ListItem.getCorrectedP().toString() : "");
            inflate.inputCorrectionTotal.setText(formC1ListItem.getCorrectedTotal() != null ? formC1ListItem.getCorrectedTotal().toString() : "");
            materialAlertDialogBuilder.setView(inflate.getRoot());
            inflate.textTitle.setText(materialAlertDialogBuilder.getContext().getString(R.string.check_form_chasil_dialog_title));
            inflate.textSubtitle.setText(formC1ListItem.getLabel());
            final AlertDialog create = materialAlertDialogBuilder.create();
            Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…label)\n        }.create()");
            inflate.buttonSave.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$27(DialogCheckFormC1ListItemBinding.this, this, formC1ListItem, create, view);
                }
            });
            create.show();
            return;
        }
        throw new Exception("Image Not Found");
    }

    public static final void showCorrectionDialog$lambda$22$lambda$14(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyTabulationPartaiViewModel().toggleKesesuaian();
    }

    public static final void showCorrectionDialog$lambda$22$lambda$15(VerifyTabulationPartaiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyTabulationPartaiViewModel().toggleKesesuaian();
    }

    public static final void showCorrectionDialog$lambda$22$lambda$17(VerifyTabulationPartaiFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getVerifyTabulationPartaiViewModel().isWrongL().getValue();
        if (value != null) {
            this$0.getVerifyTabulationPartaiViewModel().isWrongL().postValue(Boolean.valueOf(!value.booleanValue()));
            if (!value.booleanValue()) {
                UiUtil uiUtil = UiUtil.INSTANCE;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                TextInputEditText textInputEditText = binding.inputCorrectionL;
                Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.inputCorrectionL");
                uiUtil.showKeyboard(requireContext, textInputEditText);
                return;
            }
            UiUtil uiUtil2 = UiUtil.INSTANCE;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            TextInputEditText textInputEditText2 = binding.inputCorrectionL;
            Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.inputCorrectionL");
            uiUtil2.hideKeyboard(requireContext2, textInputEditText2);
        }
    }

    public static final void showCorrectionDialog$lambda$22$lambda$19(VerifyTabulationPartaiFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getVerifyTabulationPartaiViewModel().isWrongP().getValue();
        if (value != null) {
            this$0.getVerifyTabulationPartaiViewModel().isWrongP().postValue(Boolean.valueOf(!value.booleanValue()));
            if (!value.booleanValue()) {
                UiUtil uiUtil = UiUtil.INSTANCE;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                TextInputEditText textInputEditText = binding.inputCorrectionP;
                Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.inputCorrectionP");
                uiUtil.showKeyboard(requireContext, textInputEditText);
                return;
            }
            UiUtil uiUtil2 = UiUtil.INSTANCE;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            TextInputEditText textInputEditText2 = binding.inputCorrectionP;
            Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.inputCorrectionP");
            uiUtil2.hideKeyboard(requireContext2, textInputEditText2);
        }
    }

    public static final void showCorrectionDialog$lambda$22$lambda$21(VerifyTabulationPartaiFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getVerifyTabulationPartaiViewModel().isWrongTotal().getValue();
        if (value != null) {
            this$0.getVerifyTabulationPartaiViewModel().isWrongTotal().postValue(Boolean.valueOf(!value.booleanValue()));
            if (!value.booleanValue()) {
                UiUtil uiUtil = UiUtil.INSTANCE;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                TextInputEditText textInputEditText = binding.inputCorrectionTotal;
                Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.inputCorrectionTotal");
                uiUtil.showKeyboard(requireContext, textInputEditText);
                return;
            }
            UiUtil uiUtil2 = UiUtil.INSTANCE;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            TextInputEditText textInputEditText2 = binding.inputCorrectionTotal;
            Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.inputCorrectionTotal");
            uiUtil2.hideKeyboard(requireContext2, textInputEditText2);
        }
    }

    public static final void showCorrectionDialog$lambda$27(DialogCheckFormC1ListItemBinding binding, VerifyTabulationPartaiFragment this$0, FormC1ListItem item, AlertDialog dialog, View view) {
        Boolean isWrongP;
        Boolean isWrongTotal;
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        String valueOf = String.valueOf(binding.inputCorrectionL.getText());
        String valueOf2 = String.valueOf(binding.inputCorrectionP.getText());
        String valueOf3 = String.valueOf(binding.inputCorrectionTotal.getText());
        Boolean value = this$0.getVerifyTabulationPartaiViewModel().isWrongL().getValue();
        if (value == null || (isWrongP = this$0.getVerifyTabulationPartaiViewModel().isWrongP().getValue()) == null || (isWrongTotal = this$0.getVerifyTabulationPartaiViewModel().isWrongTotal().getValue()) == null) {
            return;
        }
        Election value2 = this$0.getVerifyTabulationPartaiViewModel().getGetElectionPageUseCase().getElection().getValue();
        int i = value2 != null ? value2.isLn() : true ? 5 : 3;
        boolean z = item.getL() != null;
        boolean z2 = item.getP() != null;
        if (!value.booleanValue() || !StringsKt.isBlank(valueOf) || item.getL() == null) {
            Intrinsics.checkNotNullExpressionValue(isWrongP, "isWrongP");
            if (!isWrongP.booleanValue() || !StringsKt.isBlank(valueOf2) || item.getP() == null) {
                Intrinsics.checkNotNullExpressionValue(isWrongTotal, "isWrongTotal");
                if (!isWrongTotal.booleanValue() || !StringsKt.isBlank(valueOf3)) {
                    if ((z && value.booleanValue() && StringsKt.trim((CharSequence) valueOf).toString().length() > i) || ((z2 && isWrongP.booleanValue() && StringsKt.trim((CharSequence) valueOf2).toString().length() > i) || (isWrongTotal.booleanValue() && StringsKt.trim((CharSequence) valueOf3).toString().length() > i))) {
                        new MaterialAlertDialogBuilder(this$0.requireContext()).setTitle((CharSequence) "Terjadi Kesalahan").setMessage((CharSequence) ("Angka yang diiisikan harus sesuai angka pada form C dan maksimal " + i + " digit")).setPositiveButton((CharSequence) "Tutup", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment$$ExternalSyntheticLambda7
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i2) {
                                VerifyTabulationPartaiFragment.showCorrectionDialog$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23(dialogInterface, i2);
                            }
                        }).show();
                        return;
                    }
                    MutableLiveData<String> correctedL = this$0.getVerifyTabulationPartaiViewModel().getCorrectedL();
                    if (!value.booleanValue()) {
                        valueOf = null;
                    }
                    correctedL.setValue(valueOf);
                    MutableLiveData<String> correctedP = this$0.getVerifyTabulationPartaiViewModel().getCorrectedP();
                    if (!isWrongP.booleanValue()) {
                        valueOf2 = null;
                    }
                    correctedP.setValue(valueOf2);
                    MutableLiveData<String> correctedTotal = this$0.getVerifyTabulationPartaiViewModel().getCorrectedTotal();
                    if (!isWrongTotal.booleanValue()) {
                        valueOf3 = null;
                    }
                    correctedTotal.setValue(valueOf3);
                    this$0.getVerifyTabulationPartaiViewModel().saveChecked(item);
                    dialog.dismiss();
                    return;
                }
            }
        }
        Toast.makeText(this$0.getContext(), this$0.getString(R.string.prompt_correction_invalid), 0).show();
    }

    /* compiled from: VerifyTabulationPartaiFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
