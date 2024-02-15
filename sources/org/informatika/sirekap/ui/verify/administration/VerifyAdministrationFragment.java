package org.informatika.sirekap.ui.verify.administration;

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
import org.informatika.sirekap.databinding.FragmentVerifyAdministrationBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1Kesesuaian;
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

/* compiled from: VerifyAdministrationFragment.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 52\u00020\u0001:\u00015B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J$\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u001a\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020$2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020 H\u0002J\b\u0010/\u001a\u00020 H\u0002J\b\u00100\u001a\u00020 H\u0002J\u0010\u00101\u001a\u00020 2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001d¨\u00066"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapterDisabilitas", "Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter;", "adapterPemilih", "adapterPengguna", "adapterSurat", "args", "Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "authRequestLauncher2", "binding", "Lorg/informatika/sirekap/databinding/FragmentVerifyAdministrationBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "verifyAdministrationViewModel", "Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationViewModel;", "getVerifyAdministrationViewModel", "()Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationViewModel;", "verifyAdministrationViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "retakePhoto", "isManual", "", "setupBinding", "setupLaunchers", "setupViewModel", "showCorrectionDialog", "item", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "trySubmit", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class VerifyAdministrationFragment extends Hilt_VerifyAdministrationFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VerifyAdminFragment";
    private FormC1AdministrationAdapter adapterDisabilitas;
    private FormC1AdministrationAdapter adapterPemilih;
    private FormC1AdministrationAdapter adapterPengguna;
    private FormC1AdministrationAdapter adapterSurat;
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private ActivityResultLauncher<Intent> authRequestLauncher2;
    private FragmentVerifyAdministrationBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy verifyAdministrationViewModel$delegate;

    public static final void showCorrectionDialog$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23(DialogInterface dialogInterface, int i) {
    }

    public VerifyAdministrationFragment() {
        final VerifyAdministrationFragment verifyAdministrationFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.verifyAdministrationViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifyAdministrationFragment, Reflection.getOrCreateKotlinClass(VerifyAdministrationViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(VerifyAdministrationFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$navArgs$1
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifyAdministrationFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = verifyAdministrationFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$special$$inlined$activityViewModels$default$3
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

    public final VerifyAdministrationViewModel getVerifyAdministrationViewModel() {
        return (VerifyAdministrationViewModel) this.verifyAdministrationViewModel$delegate.getValue();
    }

    public final VerifyAdministrationFragmentArgs getArgs() {
        return (VerifyAdministrationFragmentArgs) this.args$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda11
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifyAdministrationFragment.setupLaunchers$lambda$0(VerifyAdministrationFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda12
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifyAdministrationFragment.setupLaunchers$lambda$1(VerifyAdministrationFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher2 = registerForActivityResult2;
    }

    public static final void setupLaunchers$lambda$0(VerifyAdministrationFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupLaunchers$1$1
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
                BaseFragment.showSnackBar$default(VerifyAdministrationFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Submit' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupLaunchers$1$2
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
                BaseFragment.showSnackBar$default(VerifyAdministrationFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    public static final void setupLaunchers$lambda$1(VerifyAdministrationFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupLaunchers$2$1
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
                BaseFragment.showSnackBar$default(VerifyAdministrationFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Ulangi' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupLaunchers$2$2
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
                BaseFragment.showSnackBar$default(VerifyAdministrationFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentVerifyAdministrationBinding inflate = FragmentVerifyAdministrationBinding.inflate(inflater, viewGroup, false);
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
        getVerifyAdministrationViewModel().getGetElectionPageUseCase().setup(getArgs().getElectionPageId());
    }

    private final void setupBinding() {
        FragmentVerifyAdministrationBinding fragmentVerifyAdministrationBinding = this.binding;
        FormC1AdministrationAdapter formC1AdministrationAdapter = null;
        if (fragmentVerifyAdministrationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyAdministrationBinding = null;
        }
        fragmentVerifyAdministrationBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentVerifyAdministrationBinding.setViewModel(getVerifyAdministrationViewModel());
        fragmentVerifyAdministrationBinding.setGetElectionPageUseCase(getVerifyAdministrationViewModel().getGetElectionPageUseCase());
        fragmentVerifyAdministrationBinding.c1ImageViewCard.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationFragment.setupBinding$lambda$8$lambda$3(VerifyAdministrationFragment.this, view);
            }
        });
        fragmentVerifyAdministrationBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationFragment.setupBinding$lambda$8$lambda$4(VerifyAdministrationFragment.this, view);
            }
        });
        fragmentVerifyAdministrationBinding.buttonContinueVerify.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationFragment.setupBinding$lambda$8$lambda$5(VerifyAdministrationFragment.this, view);
            }
        });
        fragmentVerifyAdministrationBinding.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationFragment.setupBinding$lambda$8$lambda$6(VerifyAdministrationFragment.this, view);
            }
        });
        fragmentVerifyAdministrationBinding.butonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationFragment.setupBinding$lambda$8$lambda$7(VerifyAdministrationFragment.this, view);
            }
        });
        this.adapterPemilih = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$6
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
                VerifyAdministrationFragment.this.showCorrectionDialog(it);
            }
        });
        this.adapterPengguna = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$7
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
                VerifyAdministrationFragment.this.showCorrectionDialog(it);
            }
        });
        this.adapterDisabilitas = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$8
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
                VerifyAdministrationFragment.this.showCorrectionDialog(it);
            }
        });
        this.adapterSurat = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$9
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
                VerifyAdministrationFragment.this.showCorrectionDialog(it);
            }
        });
        RecyclerView recyclerView = fragmentVerifyAdministrationBinding.recyclerViewDataPemilih;
        FormC1AdministrationAdapter formC1AdministrationAdapter2 = this.adapterPemilih;
        if (formC1AdministrationAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterPemilih");
            formC1AdministrationAdapter2 = null;
        }
        recyclerView.setAdapter(formC1AdministrationAdapter2);
        RecyclerView recyclerView2 = fragmentVerifyAdministrationBinding.recyclerViewDataPengguna;
        FormC1AdministrationAdapter formC1AdministrationAdapter3 = this.adapterPengguna;
        if (formC1AdministrationAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterPengguna");
            formC1AdministrationAdapter3 = null;
        }
        recyclerView2.setAdapter(formC1AdministrationAdapter3);
        RecyclerView recyclerView3 = fragmentVerifyAdministrationBinding.recyclerViewDataDisabilitas;
        FormC1AdministrationAdapter formC1AdministrationAdapter4 = this.adapterDisabilitas;
        if (formC1AdministrationAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterDisabilitas");
            formC1AdministrationAdapter4 = null;
        }
        recyclerView3.setAdapter(formC1AdministrationAdapter4);
        RecyclerView recyclerView4 = fragmentVerifyAdministrationBinding.recyclerViewDataSurat;
        FormC1AdministrationAdapter formC1AdministrationAdapter5 = this.adapterSurat;
        if (formC1AdministrationAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterSurat");
        } else {
            formC1AdministrationAdapter = formC1AdministrationAdapter5;
        }
        recyclerView4.setAdapter(formC1AdministrationAdapter);
    }

    public static final void setupBinding$lambda$8$lambda$3(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String value = this$0.getVerifyAdministrationViewModel().getPreviewImagePath().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(VerifyAdministrationFragmentDirections.Companion.actionVerifyAdministrationFragmentToPreviewImageFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$8$lambda$4(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("VerifyAdminFragment: User clicks 'Ulang Foto' button");
        this$0.retakePhoto(false);
    }

    public static final void setupBinding$lambda$8$lambda$5(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyAdministrationViewModel().continueVerify();
    }

    public static final void setupBinding$lambda$8$lambda$6(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("VerifyAdminFragment Click 'Submit'");
        if (ElectionUtil.extractIdImageReal(this$0.getArgs().getIdImage()) < 0) {
            this$0.trySubmit();
        } else if (this$0.getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$4$1
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
                    VerifyAdministrationFragment.this.trySubmit();
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$4$2
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
                    Toast.makeText(VerifyAdministrationFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = VerifyAdministrationFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = VerifyAdministrationFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = VerifyAdministrationFragment.this.authRequestLauncher;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        VerifyAdministrationFragment verifyAdministrationFragment = VerifyAdministrationFragment.this;
                        String string = verifyAdministrationFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(verifyAdministrationFragment, string, null, null, 6, null);
                    }
                }
            });
        } else {
            this$0.trySubmit();
        }
    }

    public static final void setupBinding$lambda$8$lambda$7(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$5$1
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
                    VerifyAdministrationViewModel verifyAdministrationViewModel;
                    Intrinsics.checkNotNullParameter(it, "it");
                    verifyAdministrationViewModel = VerifyAdministrationFragment.this.getVerifyAdministrationViewModel();
                    verifyAdministrationViewModel.refreshIdImage();
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupBinding$1$5$2
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
                    Toast.makeText(VerifyAdministrationFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = VerifyAdministrationFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = VerifyAdministrationFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = VerifyAdministrationFragment.this.authRequestLauncher2;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher2");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        VerifyAdministrationFragment verifyAdministrationFragment = VerifyAdministrationFragment.this;
                        String string = verifyAdministrationFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(verifyAdministrationFragment, string, null, null, 6, null);
                    }
                }
            });
            return;
        }
        this$0.getVerifyAdministrationViewModel().refreshIdImage();
    }

    public final void trySubmit() {
        Boolean value = getVerifyAdministrationViewModel().isCheckAllTrue().getValue();
        if (value != null) {
            getVerifyAdministrationViewModel().getVerifyForm().setVerifyOption(value.booleanValue());
            DialogVerifyBodyBinding inflate = DialogVerifyBodyBinding.inflate(getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
            inflate.setLifecycleOwner(getViewLifecycleOwner());
            inflate.setViewModel(getVerifyAdministrationViewModel());
            inflate.setHideCommentField(true);
            materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) getString(R.string.submit_verify_administration_dialog_message));
            final AlertDialog create = materialAlertDialogBuilder.create();
            Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…))\n            }.create()");
            create.show();
            inflate.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.trySubmit$lambda$11$lambda$10(VerifyAdministrationFragment.this, create, view);
                }
            });
        }
    }

    public static final void trySubmit$lambda$11$lambda$10(VerifyAdministrationFragment this$0, AlertDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        VerifyAdministrationViewModel verifyAdministrationViewModel = this$0.getVerifyAdministrationViewModel();
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        verifyAdministrationViewModel.submitVerification(requireContext, dialog);
    }

    private final void retakePhoto(boolean z) {
        VerifyAdministrationFragment verifyAdministrationFragment = this;
        androidx.fragment.app.FragmentKt.setFragmentResult(verifyAdministrationFragment, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO, true), TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE, new Gson().toJson(getVerifyAdministrationViewModel().getGetElectionPageUseCase().getElectionPage().getValue())), TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_IS_MANUAL, Boolean.valueOf(z))));
        FragmentKt.findNavController(verifyAdministrationFragment).navigateUp();
    }

    private final void setupViewModel() {
        VerifyAdministrationViewModel verifyAdministrationViewModel = getVerifyAdministrationViewModel();
        verifyAdministrationViewModel.getFormC1AdministrationResource().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends FormC1AdministrationComplete>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$1
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
                    VerifyAdministrationFragment verifyAdministrationFragment = VerifyAdministrationFragment.this;
                    if (resource.getSuccess() == ResourceStatus.ERROR) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(resource.getError()));
                        String string = verifyAdministrationFragment.getString(R.string.fetch_formchasil_message_error_snackbar, resource.getError());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fetch…error_snackbar, it.error)");
                        BaseFragment.showSnackBar$default(verifyAdministrationFragment, string, null, null, 6, null);
                        return;
                    }
                    resource.getSuccess();
                    ResourceStatus resourceStatus = ResourceStatus.LOADING;
                }
            }
        }));
        verifyAdministrationViewModel.getFormC1Kesesuaian().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1Kesesuaian, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$2
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
                FormC1AdministrationAdapter formC1AdministrationAdapter2;
                FormC1AdministrationAdapter formC1AdministrationAdapter3;
                FormC1AdministrationAdapter formC1AdministrationAdapter4;
                if (formC1Kesesuaian != null) {
                    VerifyAdministrationFragment verifyAdministrationFragment = VerifyAdministrationFragment.this;
                    formC1AdministrationAdapter = verifyAdministrationFragment.adapterPemilih;
                    FormC1AdministrationAdapter formC1AdministrationAdapter5 = null;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPemilih");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.done();
                    formC1AdministrationAdapter2 = verifyAdministrationFragment.adapterPengguna;
                    if (formC1AdministrationAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPengguna");
                        formC1AdministrationAdapter2 = null;
                    }
                    formC1AdministrationAdapter2.done();
                    formC1AdministrationAdapter3 = verifyAdministrationFragment.adapterDisabilitas;
                    if (formC1AdministrationAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterDisabilitas");
                        formC1AdministrationAdapter3 = null;
                    }
                    formC1AdministrationAdapter3.done();
                    formC1AdministrationAdapter4 = verifyAdministrationFragment.adapterSurat;
                    if (formC1AdministrationAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterSurat");
                    } else {
                        formC1AdministrationAdapter5 = formC1AdministrationAdapter4;
                    }
                    formC1AdministrationAdapter5.done();
                }
            }
        }));
        verifyAdministrationViewModel.getTablePemilih().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends FormC1ListItem> list) {
                invoke2((List<FormC1ListItem>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<FormC1ListItem> list) {
                FormC1AdministrationAdapter formC1AdministrationAdapter;
                if (list != null) {
                    formC1AdministrationAdapter = VerifyAdministrationFragment.this.adapterPemilih;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPemilih");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        verifyAdministrationViewModel.getTablePengguna().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$4
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
                    formC1AdministrationAdapter = VerifyAdministrationFragment.this.adapterPengguna;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPengguna");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        verifyAdministrationViewModel.getTableDisabilitas().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends FormC1ListItem> list) {
                invoke2((List<FormC1ListItem>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<FormC1ListItem> list) {
                FormC1AdministrationAdapter formC1AdministrationAdapter;
                if (list != null) {
                    formC1AdministrationAdapter = VerifyAdministrationFragment.this.adapterDisabilitas;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterDisabilitas");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        verifyAdministrationViewModel.getTableSurat().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$6
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
                    formC1AdministrationAdapter = VerifyAdministrationFragment.this.adapterSurat;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterSurat");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        verifyAdministrationViewModel.isSesuaiPerItem().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$7
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Boolean> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Boolean> list) {
                invoke2((List<Boolean>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyAdministrationViewModel.getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$8
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyAdministrationViewModel.isCheckAllTrue().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$9
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
        verifyAdministrationViewModel.getGetElectionPageUseCase().getElectionPageWithRelation().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionPageWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$10$1
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
                VerifyAdministrationViewModel verifyAdministrationViewModel2;
                VerifyAdministrationFragmentArgs args;
                VerifyAdministrationFragmentArgs args2;
                VerifyAdministrationViewModel verifyAdministrationViewModel3;
                VerifyAdministrationViewModel verifyAdministrationViewModel4;
                if (electionPageWithRelation != null) {
                    VerifyAdministrationFragment verifyAdministrationFragment = VerifyAdministrationFragment.this;
                    verifyAdministrationViewModel2 = verifyAdministrationFragment.getVerifyAdministrationViewModel();
                    args = verifyAdministrationFragment.getArgs();
                    String idImage = args.getIdImage();
                    args2 = verifyAdministrationFragment.getArgs();
                    verifyAdministrationViewModel2.setup(idImage, args2.getElectionPageId(), electionPageWithRelation.getElection().getPemilihan(), electionPageWithRelation.getElection().getTps().getKodeTps());
                    verifyAdministrationViewModel3 = verifyAdministrationFragment.getVerifyAdministrationViewModel();
                    if (verifyAdministrationViewModel3.getPreviewImagePath().getValue() == null) {
                        verifyAdministrationViewModel4 = verifyAdministrationFragment.getVerifyAdministrationViewModel();
                        verifyAdministrationViewModel4.getPreviewImagePath().postValue(electionPageWithRelation.getElectionPage().getCroppedPhotoPath());
                    }
                }
            }
        }));
        verifyAdministrationViewModel.getSubmittedFormResource().observe(getViewLifecycleOwner(), new VerifyAdministrationFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends FormC1AdministrationComplete>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$setupViewModel$1$11
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
                    VerifyAdministrationFragment verifyAdministrationFragment = VerifyAdministrationFragment.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        String string = verifyAdministrationFragment.getString(R.string.verify_message_success_snackbar);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.verif…message_success_snackbar)");
                        BaseFragment.showSnackBar$default(verifyAdministrationFragment, string, null, null, 6, null);
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String string2 = verifyAdministrationFragment.getString(R.string.verify_message_error_snackbar, resource.getError());
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.verif…error_snackbar, it.error)");
                        BaseFragment.showSnackBar$default(verifyAdministrationFragment, string2, null, null, 6, null);
                    }
                }
            }
        }));
    }

    public final void showCorrectionDialog(final FormC1ListItem formC1ListItem) {
        FormConfig config$default;
        Bitmap decodeFile;
        if (formC1ListItem.isChecked()) {
            getVerifyAdministrationViewModel().setupCheckDialog(!Intrinsics.areEqual((Object) formC1ListItem.getCheckL(), (Object) true), !Intrinsics.areEqual((Object) formC1ListItem.getCheckP(), (Object) true), !Intrinsics.areEqual((Object) formC1ListItem.getCheckTotal(), (Object) true), formC1ListItem.getCorrectedL() != null ? formC1ListItem.getCorrectedL().toString() : "", formC1ListItem.getCorrectedP() != null ? formC1ListItem.getCorrectedP().toString() : "", formC1ListItem.getCorrectedTotal() != null ? formC1ListItem.getCorrectedTotal().toString() : "");
        } else {
            BaseVerifyViewModel.setupCheckDialog$default(getVerifyAdministrationViewModel(), false, false, false, null, null, null, 63, null);
        }
        final DialogCheckFormC1ListItemBinding inflate = DialogCheckFormC1ListItemBinding.inflate(getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        inflate.setLifecycleOwner(getViewLifecycleOwner());
        inflate.setShowCorrection(true);
        inflate.setItem(formC1ListItem);
        inflate.setViewModel(getVerifyAdministrationViewModel());
        try {
            getVerifyAdministrationViewModel().resetImage();
            FormConfig.Companion companion = FormConfig.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String electionType = formC1ListItem.getElectionType();
            config$default = FormConfig.Companion.getConfig$default(companion, requireContext, FormConfig.FORM_ADMIN1, electionType == null ? "" : electionType, Intrinsics.areEqual((Object) formC1ListItem.isLN(), (Object) true), Intrinsics.areEqual((Object) formC1ListItem.isPOS(), (Object) true), 0, 32, null);
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
            getVerifyAdministrationViewModel().getKesesuaianSliceError().postValue(message);
            inflate.kesesuaianSliceErrorShowDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda14
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.showCorrectionDialog$lambda$22$lambda$14(VerifyAdministrationFragment.this, view);
                }
            });
            inflate.kesesuaianSliceErrorHideDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.showCorrectionDialog$lambda$22$lambda$15(VerifyAdministrationFragment.this, view);
                }
            });
        }
        if (decodeFile != null) {
            String id2 = formC1ListItem.getId();
            Integer no = formC1ListItem.getNo();
            getVerifyAdministrationViewModel().getPreviewKesesuaianBitmap().postValue(FormConfig.getKesesuaianSlicedMat$default(config$default, FormConfig.VISION_TYPE_ADMIN, id2, decodeFile, Integer.valueOf(no != null ? no.intValue() : 0), 0, 16, null));
            inflate.rowL.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.showCorrectionDialog$lambda$22$lambda$17(VerifyAdministrationFragment.this, inflate, view);
                }
            });
            inflate.rowP.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.showCorrectionDialog$lambda$22$lambda$19(VerifyAdministrationFragment.this, inflate, view);
                }
            });
            inflate.rowTotal.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.showCorrectionDialog$lambda$22$lambda$21(VerifyAdministrationFragment.this, inflate, view);
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
            inflate.buttonSave.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationFragment.showCorrectionDialog$lambda$27(DialogCheckFormC1ListItemBinding.this, this, formC1ListItem, create, view);
                }
            });
            create.show();
            return;
        }
        throw new Exception("Image Not Found");
    }

    public static final void showCorrectionDialog$lambda$22$lambda$14(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyAdministrationViewModel().toggleKesesuaian();
    }

    public static final void showCorrectionDialog$lambda$22$lambda$15(VerifyAdministrationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyAdministrationViewModel().toggleKesesuaian();
    }

    public static final void showCorrectionDialog$lambda$22$lambda$17(VerifyAdministrationFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getVerifyAdministrationViewModel().isWrongL().getValue();
        if (value != null) {
            this$0.getVerifyAdministrationViewModel().isWrongL().postValue(Boolean.valueOf(!value.booleanValue()));
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

    public static final void showCorrectionDialog$lambda$22$lambda$19(VerifyAdministrationFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getVerifyAdministrationViewModel().isWrongP().getValue();
        if (value != null) {
            this$0.getVerifyAdministrationViewModel().isWrongP().postValue(Boolean.valueOf(!value.booleanValue()));
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

    public static final void showCorrectionDialog$lambda$22$lambda$21(VerifyAdministrationFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getVerifyAdministrationViewModel().isWrongTotal().getValue();
        if (value != null) {
            this$0.getVerifyAdministrationViewModel().isWrongTotal().postValue(Boolean.valueOf(!value.booleanValue()));
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

    public static final void showCorrectionDialog$lambda$27(DialogCheckFormC1ListItemBinding binding, VerifyAdministrationFragment this$0, FormC1ListItem item, AlertDialog dialog, View view) {
        Boolean isWrongP;
        Boolean isWrongTotal;
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        String valueOf = String.valueOf(binding.inputCorrectionL.getText());
        String valueOf2 = String.valueOf(binding.inputCorrectionP.getText());
        String valueOf3 = String.valueOf(binding.inputCorrectionTotal.getText());
        Boolean value = this$0.getVerifyAdministrationViewModel().isWrongL().getValue();
        if (value == null || (isWrongP = this$0.getVerifyAdministrationViewModel().isWrongP().getValue()) == null || (isWrongTotal = this$0.getVerifyAdministrationViewModel().isWrongTotal().getValue()) == null) {
            return;
        }
        Election value2 = this$0.getVerifyAdministrationViewModel().getGetElectionPageUseCase().getElection().getValue();
        int i = value2 != null ? value2.isLn() : true ? 5 : 3;
        boolean z = item.getL() != null;
        boolean z2 = item.getP() != null;
        if (!value.booleanValue() || !StringsKt.isBlank(valueOf) || item.getL() == null) {
            Intrinsics.checkNotNullExpressionValue(isWrongP, "isWrongP");
            if (!isWrongP.booleanValue() || !StringsKt.isBlank(valueOf2) || item.getP() == null) {
                Intrinsics.checkNotNullExpressionValue(isWrongTotal, "isWrongTotal");
                if (!isWrongTotal.booleanValue() || !StringsKt.isBlank(valueOf3)) {
                    if ((z && value.booleanValue() && StringsKt.trim((CharSequence) valueOf).toString().length() > i) || ((z2 && isWrongP.booleanValue() && StringsKt.trim((CharSequence) valueOf2).toString().length() > i) || (isWrongTotal.booleanValue() && StringsKt.trim((CharSequence) valueOf3).toString().length() > i))) {
                        new MaterialAlertDialogBuilder(this$0.requireContext()).setTitle((CharSequence) "Terjadi Kesalahan").setMessage((CharSequence) ("Angka yang diiisikan harus sesuai angka pada form C dan maksimal " + i + " digit")).setPositiveButton((CharSequence) "Tutup", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment$$ExternalSyntheticLambda13
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i2) {
                                VerifyAdministrationFragment.showCorrectionDialog$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23(dialogInterface, i2);
                            }
                        }).show();
                        return;
                    }
                    MutableLiveData<String> correctedL = this$0.getVerifyAdministrationViewModel().getCorrectedL();
                    if (!value.booleanValue()) {
                        valueOf = null;
                    }
                    correctedL.setValue(valueOf);
                    MutableLiveData<String> correctedP = this$0.getVerifyAdministrationViewModel().getCorrectedP();
                    if (!isWrongP.booleanValue()) {
                        valueOf2 = null;
                    }
                    correctedP.setValue(valueOf2);
                    MutableLiveData<String> correctedTotal = this$0.getVerifyAdministrationViewModel().getCorrectedTotal();
                    if (!isWrongTotal.booleanValue()) {
                        valueOf3 = null;
                    }
                    correctedTotal.setValue(valueOf3);
                    this$0.getVerifyAdministrationViewModel().saveChecked(item);
                    dialog.dismiss();
                    return;
                }
            }
        }
        Toast.makeText(this$0.getContext(), this$0.getString(R.string.prompt_correction_invalid), 0).show();
    }

    /* compiled from: VerifyAdministrationFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
