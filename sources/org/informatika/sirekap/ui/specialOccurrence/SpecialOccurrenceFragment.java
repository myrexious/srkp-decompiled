package org.informatika.sirekap.ui.specialOccurrence;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import java.io.File;
import java.util.List;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentSpecialOccurrenceBinding;
import org.informatika.sirekap.databinding.ViewLockSpecialOccurrenceBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.imageCrop.ImageCropActivity;
import org.informatika.sirekap.ui.imageCrop.ImageCropConstants;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: SpecialOccurrenceFragment.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 <2\u00020\u0001:\u0001<B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010'\u001a\u00020$2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J$\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020+2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u00102\u001a\u00020$H\u0002J\b\u00103\u001a\u00020$H\u0002J\b\u00104\u001a\u00020$H\u0002J\b\u00105\u001a\u00020$H\u0002J\u0006\u00106\u001a\u00020$J\b\u00107\u001a\u00020$H\u0002J\u0011\u00108\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u00109J\b\u0010:\u001a\u00020$H\u0002J\b\u0010;\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\fX\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceAdapter;", "args", "Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentSpecialOccurrenceBinding;", "cropImageLauncher", "localAuthLauncher", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "openAppSettingsLauncher", "pdfGenerationAuthRequest", "requestPermissionsLauncher", "", "specialOccurrenceViewModel", "Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceViewModel;", "getSpecialOccurrenceViewModel", "()Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceViewModel;", "specialOccurrenceViewModel$delegate", "takePictureLauncher", "Landroid/net/Uri;", "deleteSpecialOccurrence", "", "specialOccurrencePage", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupFragmentListener", "setupLauncher", "setupLaunchers", "setupViewModel", "tryCreatePdf", "trySubmit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySubmitOnline", "tryTakePhoto", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class SpecialOccurrenceFragment extends Hilt_SpecialOccurrenceFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SpecialOccurrenceFragment";
    private SpecialOccurrenceAdapter adapter;
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentSpecialOccurrenceBinding binding;
    private ActivityResultLauncher<Intent> cropImageLauncher;
    private ActivityResultLauncher<Intent> localAuthLauncher;
    private final Lazy mainViewModel$delegate;
    private ActivityResultLauncher<Intent> openAppSettingsLauncher;
    private ActivityResultLauncher<Intent> pdfGenerationAuthRequest;
    private ActivityResultLauncher<String> requestPermissionsLauncher;
    private final Lazy specialOccurrenceViewModel$delegate;
    private ActivityResultLauncher<Uri> takePictureLauncher;

    public static final void deleteSpecialOccurrence$lambda$19(DialogInterface dialogInterface, int i) {
    }

    public static final void setupLauncher$lambda$15(ActivityResult activityResult) {
    }

    public SpecialOccurrenceFragment() {
        final SpecialOccurrenceFragment specialOccurrenceFragment = this;
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SpecialOccurrenceFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$navArgs$1
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
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.specialOccurrenceViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(specialOccurrenceFragment, Reflection.getOrCreateKotlinClass(SpecialOccurrenceViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(specialOccurrenceFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = specialOccurrenceFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$special$$inlined$activityViewModels$default$3
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

    public final SpecialOccurrenceFragmentArgs getArgs() {
        return (SpecialOccurrenceFragmentArgs) this.args$delegate.getValue();
    }

    public final SpecialOccurrenceViewModel getSpecialOccurrenceViewModel() {
        return (SpecialOccurrenceViewModel) this.specialOccurrenceViewModel$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda11
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLaunchers$lambda$0(SpecialOccurrenceFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.localAuthLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda12
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLaunchers$lambda$1(SpecialOccurrenceFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda13
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLaunchers$lambda$2(SpecialOccurrenceFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          )\n            }");
        this.pdfGenerationAuthRequest = registerForActivityResult3;
    }

    public static final void setupLaunchers$lambda$0(SpecialOccurrenceFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.getMainViewModel().getLocalAuth().postValue(true);
            this$0.getSpecialOccurrenceViewModel().startGeneratePdf(this$0);
            return;
        }
        this$0.getSpecialOccurrenceViewModel().failedAuthentication(this$0);
    }

    public static final void setupLaunchers$lambda$1(SpecialOccurrenceFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLaunchers$2$1
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
                BaseFragment.showSnackBar$default(SpecialOccurrenceFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Unggah Berkas (PDF)' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLaunchers$2$2
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
                BaseFragment.showSnackBar$default(SpecialOccurrenceFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    public static final void setupLaunchers$lambda$2(SpecialOccurrenceFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLaunchers$3$1
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
                BaseFragment.showSnackBar$default(SpecialOccurrenceFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Kunci PDF' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLaunchers$3$2
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
                BaseFragment.showSnackBar$default(SpecialOccurrenceFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSpecialOccurrenceBinding inflate = FragmentSpecialOccurrenceBinding.inflate(inflater, viewGroup, false);
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
        setupLauncher();
        setupViewModel();
        setupFragmentListener();
        getSpecialOccurrenceViewModel().setup(getArgs().getKodeTps());
    }

    private final void setupBinding() {
        FragmentSpecialOccurrenceBinding fragmentSpecialOccurrenceBinding = this.binding;
        SpecialOccurrenceAdapter specialOccurrenceAdapter = null;
        if (fragmentSpecialOccurrenceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSpecialOccurrenceBinding = null;
        }
        fragmentSpecialOccurrenceBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentSpecialOccurrenceBinding.setViewModel(getSpecialOccurrenceViewModel());
        this.adapter = new SpecialOccurrenceAdapter(new Function1<SpecialOccurrencePage, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupBinding$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SpecialOccurrencePage specialOccurrencePage) {
                invoke2(specialOccurrencePage);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SpecialOccurrencePage it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FirebaseCrashlytics.getInstance().log("SpecialOccurrenceFragment User clicks 'Delete Special Occurrence Photo' button");
                SpecialOccurrenceFragment.this.deleteSpecialOccurrence(it);
            }
        }, new Function1<SpecialOccurrencePage, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupBinding$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SpecialOccurrencePage specialOccurrencePage) {
                invoke2(specialOccurrencePage);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SpecialOccurrencePage it) {
                SpecialOccurrenceViewModel specialOccurrenceViewModel;
                Intrinsics.checkNotNullParameter(it, "it");
                FirebaseCrashlytics.getInstance().log("SpecialOccurrenceFragment User clicks 'Retake Special Occurrence Photo' button");
                specialOccurrenceViewModel = SpecialOccurrenceFragment.this.getSpecialOccurrenceViewModel();
                specialOccurrenceViewModel.getRetakeSpecialOccurrencePage().postValue(it);
                SpecialOccurrenceFragment.this.tryTakePhoto();
            }
        }, new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupBinding$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                try {
                    FragmentKt.findNavController(SpecialOccurrenceFragment.this).navigate(SpecialOccurrenceFragmentDirections.Companion.actionSpecialOccurrenceFragmentToPreviewImageFragment(it));
                } catch (Exception e) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                }
            }
        });
        RecyclerView recyclerView = fragmentSpecialOccurrenceBinding.recyclerView;
        SpecialOccurrenceAdapter specialOccurrenceAdapter2 = this.adapter;
        if (specialOccurrenceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            specialOccurrenceAdapter = specialOccurrenceAdapter2;
        }
        recyclerView.setAdapter(specialOccurrenceAdapter);
        fragmentSpecialOccurrenceBinding.buttonSend.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialOccurrenceFragment.setupBinding$lambda$12$lambda$3(SpecialOccurrenceFragment.this, view);
            }
        });
        fragmentSpecialOccurrenceBinding.buttonAdd.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialOccurrenceFragment.setupBinding$lambda$12$lambda$4(SpecialOccurrenceFragment.this, view);
            }
        });
        ViewLockSpecialOccurrenceBinding viewLockSpecialOccurrenceBinding = fragmentSpecialOccurrenceBinding.viewLockSpecialOccurrence;
        viewLockSpecialOccurrenceBinding.buttonCreatePdf.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialOccurrenceFragment.setupBinding$lambda$12$lambda$11$lambda$5(SpecialOccurrenceFragment.this, view);
            }
        });
        viewLockSpecialOccurrenceBinding.buttonUploadPdf.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialOccurrenceFragment.setupBinding$lambda$12$lambda$11$lambda$6(SpecialOccurrenceFragment.this, view);
            }
        });
        viewLockSpecialOccurrenceBinding.buttonUploadPdfOffline.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialOccurrenceFragment.setupBinding$lambda$12$lambda$11$lambda$7(SpecialOccurrenceFragment.this, view);
            }
        });
        viewLockSpecialOccurrenceBinding.buttonShareZip.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpecialOccurrenceFragment.setupBinding$lambda$12$lambda$11$lambda$10(SpecialOccurrenceFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$12$lambda$3(SpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SpecialOccurrenceFragment User clicks 'Send Special Occurrence List Photo' button");
        FragmentKt.findNavController(this$0).navigate(SpecialOccurrenceFragmentDirections.Companion.actionSpecialOccurrenceFragmentToVerifyOccurrenceFragment(this$0.getArgs().getKodeTps()));
    }

    public static final void setupBinding$lambda$12$lambda$4(SpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SpecialOccurrenceFragment User clicks 'Add Special Occurrence List Photo' button");
        ActivityResultLauncher<String> activityResultLauncher = this$0.requestPermissionsLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestPermissionsLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch("android.permission.CAMERA");
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$5(SpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SpecialOccurrenceFragment: Click 'Kunci & Buat Dokumen'");
        this$0.tryCreatePdf();
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$6(SpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMainViewModel().getAuthRequestUseCase().isLoading().postValue(true);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new SpecialOccurrenceFragment$setupBinding$1$6$2$1(this$0, null), 2, null);
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$7(SpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.trySubmitOnline();
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$10(SpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan PDF Daftar Hadir'");
        String value = this$0.getSpecialOccurrenceViewModel().getKodeTps().getValue();
        ZipSpecialOccurrenceWorker.Companion companion = ZipSpecialOccurrenceWorker.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Intrinsics.checkNotNull(value);
        File pdfFile = companion.getPdfFile(requireContext, value, this$0.getSpecialOccurrenceViewModel().getEncryptedSharedPreferences());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this$0.requireContext(), BuildConfig.FILE_PROVIDER, pdfFile));
        intent.setType("application/pdf");
        intent.addFlags(1);
        this$0.startActivity(Intent.createChooser(intent, this$0.getString(R.string.share_zip_via)));
    }

    public final void trySubmitOnline() {
        if (!getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new SpecialOccurrenceFragment$trySubmitOnline$3(this, null), 3, null);
            return;
        }
        AuthRequestUseCase authRequestUseCase = getMainViewModel().getAuthRequestUseCase();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmitOnline$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActiveProfile activeProfile) {
                invoke2(activeProfile);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: SpecialOccurrenceFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmitOnline$1$1", f = "SpecialOccurrenceFragment.kt", i = {}, l = {295}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmitOnline$1$1  reason: invalid class name */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ SpecialOccurrenceFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(SpecialOccurrenceFragment specialOccurrenceFragment, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = specialOccurrenceFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object trySubmit;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        trySubmit = this.this$0.trySubmit(this);
                        if (trySubmit == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ActiveProfile it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(SpecialOccurrenceFragment.this, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmitOnline$2
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
                Toast.makeText(SpecialOccurrenceFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                try {
                    mainViewModel = SpecialOccurrenceFragment.this.getMainViewModel();
                    AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                    Context requireContext2 = SpecialOccurrenceFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    activityResultLauncher = SpecialOccurrenceFragment.this.authRequestLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                        activityResultLauncher = null;
                    }
                    authRequestUseCase2.start(requireContext2, activityResultLauncher);
                } catch (ActivityNotFoundException e) {
                    FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                    SpecialOccurrenceFragment specialOccurrenceFragment = SpecialOccurrenceFragment.this;
                    String string = specialOccurrenceFragment.getString(R.string.key_setup_error_browser_not_found);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                    BaseFragment.showSnackBar$default(specialOccurrenceFragment, string, null, null, 6, null);
                }
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:3|(4:5|6|7|(1:(1:(3:11|12|13)(2:15|16))(7:17|18|19|20|(1:22)|12|13))(2:24|(7:26|27|(3:29|(1:31)|19)|20|(0)|12|13)(2:32|33))))|36|6|7|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0045, code lost:
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x009a, code lost:
        com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance().recordException(r11);
        r2 = r2 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e1 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x0097 -> B:75:0x00a5). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object trySubmit(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$1
            if (r0 == 0) goto L14
            r0 = r11
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$1 r0 = (org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$1 r0 = new org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$1
            r0.<init>(r10, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L47
            if (r2 == r5) goto L37
            if (r2 != r4) goto L2f
            kotlin.ResultKt.throwOnFailure(r11)
            goto Le2
        L2f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L37:
            int r2 = r0.I$0
            java.lang.Object r6 = r0.L$1
            org.informatika.sirekap.model.SpecialOccurrenceWithPages r6 = (org.informatika.sirekap.model.SpecialOccurrenceWithPages) r6
            java.lang.Object r7 = r0.L$0
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment r7 = (org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment) r7
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L45
            goto La5
        L45:
            r11 = move-exception
            goto L9a
        L47:
            kotlin.ResultKt.throwOnFailure(r11)
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel r11 = r10.getSpecialOccurrenceViewModel()
            androidx.lifecycle.LiveData r11 = r11.getSpecialOccurrence()
            java.lang.Object r11 = r11.getValue()
            org.informatika.sirekap.model.SpecialOccurrenceWithPages r11 = (org.informatika.sirekap.model.SpecialOccurrenceWithPages) r11
            if (r11 == 0) goto Le5
            org.informatika.sirekap.MainViewModel r2 = r10.getMainViewModel()
            org.informatika.sirekap.usecase.AuthRequestUseCase r2 = r2.getAuthRequestUseCase()
            androidx.lifecycle.MutableLiveData r2 = r2.isLoading()
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            r2.postValue(r6)
            r7 = r10
            r6 = r11
            r2 = r3
        L70:
            r11 = 4
            if (r2 >= r11) goto La5
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel r11 = r7.getSpecialOccurrenceViewModel()     // Catch: java.lang.Exception -> L45
            android.content.Context r8 = r7.requireContext()     // Catch: java.lang.Exception -> L45
            java.lang.String r9 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)     // Catch: java.lang.Exception -> L45
            org.informatika.sirekap.model.SpecialOccurrence r9 = r6.getSpecialOccurrence()     // Catch: java.lang.Exception -> L45
            java.lang.String r9 = r9.getPdfFilePath()     // Catch: java.lang.Exception -> L45
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch: java.lang.Exception -> L45
            r0.L$0 = r7     // Catch: java.lang.Exception -> L45
            r0.L$1 = r6     // Catch: java.lang.Exception -> L45
            r0.I$0 = r2     // Catch: java.lang.Exception -> L45
            r0.label = r5     // Catch: java.lang.Exception -> L45
            java.lang.Object r11 = r11.makePdfLtv(r8, r9, r0)     // Catch: java.lang.Exception -> L45
            if (r11 != r1) goto La5
            return r1
        L9a:
            com.google.firebase.crashlytics.FirebaseCrashlytics r8 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            r8.recordException(r11)
            int r2 = r2 + r5
            goto L70
        La5:
            org.informatika.sirekap.MainViewModel r11 = r7.getMainViewModel()
            org.informatika.sirekap.usecase.AuthRequestUseCase r11 = r11.getAuthRequestUseCase()
            androidx.lifecycle.MutableLiveData r11 = r11.isLoading()
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r11.postValue(r2)
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel r11 = r7.getSpecialOccurrenceViewModel()
            org.informatika.sirekap.usecase.SendSpecialOccurrencePdfUseCase r11 = r11.getSendSpecialOccurrencePdfUseCase()
            org.informatika.sirekap.model.SpecialOccurrence r2 = r6.getSpecialOccurrence()
            r11.start(r2)
            kotlinx.coroutines.MainCoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$2 r2 = new org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$2
            r3 = 0
            r2.<init>(r7, r3)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r3
            r0.L$1 = r3
            r0.label = r4
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r0)
            if (r11 != r1) goto Le2
            return r1
        Le2:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        Le5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment.trySubmit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setupViewModel() {
        final SpecialOccurrenceViewModel specialOccurrenceViewModel = getSpecialOccurrenceViewModel();
        specialOccurrenceViewModel.getSpecialOccurrenceList().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends SpecialOccurrencePage>, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends SpecialOccurrencePage> list) {
                invoke2((List<SpecialOccurrencePage>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<SpecialOccurrencePage> list) {
                SpecialOccurrenceAdapter specialOccurrenceAdapter;
                if (list != null) {
                    specialOccurrenceAdapter = SpecialOccurrenceFragment.this.adapter;
                    if (specialOccurrenceAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        specialOccurrenceAdapter = null;
                    }
                    specialOccurrenceAdapter.submitList(list);
                }
            }
        }));
        specialOccurrenceViewModel.getCaptureImageUseCase().getCropPhotoResult().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<CaptureImageUseCase.CropPhotoResult, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CaptureImageUseCase.CropPhotoResult cropPhotoResult) {
                invoke2(cropPhotoResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(CaptureImageUseCase.CropPhotoResult cropPhotoResult) {
                if (cropPhotoResult != null) {
                    SpecialOccurrenceFragment specialOccurrenceFragment = SpecialOccurrenceFragment.this;
                    SpecialOccurrenceViewModel specialOccurrenceViewModel2 = specialOccurrenceViewModel;
                    if (!cropPhotoResult.isSuccess()) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(cropPhotoResult.getErrorMessage()));
                        String string = specialOccurrenceFragment.getString(R.string.error_capture_image, cropPhotoResult.getErrorMessage());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error…image, this.errorMessage)");
                        BaseFragment.showSnackBar$default(specialOccurrenceFragment, string, null, null, 6, null);
                    }
                    specialOccurrenceViewModel2.getCaptureImageUseCase().finishCrop();
                }
            }
        }));
        specialOccurrenceViewModel.getCaptureImageUseCase().getSpecialOccurrenceUpdateResource().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends SpecialOccurrencePage>, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends SpecialOccurrencePage> resource) {
                invoke2((Resource<SpecialOccurrencePage>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<SpecialOccurrencePage> resource) {
                if (resource != null) {
                    SpecialOccurrenceViewModel specialOccurrenceViewModel2 = SpecialOccurrenceViewModel.this;
                    SpecialOccurrenceFragment specialOccurrenceFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (specialOccurrenceViewModel2.getCaptureImageUseCase().getSpecialOccurrenceUpdateModel().getValue() != null) {
                            BaseFragment.showSnackBar$default(specialOccurrenceFragment, "Berhasil memperbarui gambar", null, null, 6, null);
                        }
                        specialOccurrenceViewModel2.finishTakingPicture();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(specialOccurrenceFragment, error, null, null, 6, null);
                        }
                        specialOccurrenceViewModel2.finishTakingPicture();
                    }
                }
            }
        }));
        specialOccurrenceViewModel.getCaptureImageUseCase().getSpecialOccurrenceInsertResource().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends SpecialOccurrencePage>, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends SpecialOccurrencePage> resource) {
                invoke2((Resource<SpecialOccurrencePage>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<SpecialOccurrencePage> resource) {
                if (resource != null) {
                    SpecialOccurrenceViewModel specialOccurrenceViewModel2 = SpecialOccurrenceViewModel.this;
                    SpecialOccurrenceFragment specialOccurrenceFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (specialOccurrenceViewModel2.getCaptureImageUseCase().getSpecialOccurrenceInsertModel().getValue() != null) {
                            BaseFragment.showSnackBar$default(specialOccurrenceFragment, "Berhasil menambahkan gambar", null, null, 6, null);
                        }
                        specialOccurrenceViewModel2.finishTakingPicture();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(specialOccurrenceFragment, error, null, null, 6, null);
                        }
                        specialOccurrenceViewModel2.finishTakingPicture();
                    }
                }
            }
        }));
        specialOccurrenceViewModel.getDeleteSpecialOccurrenceResource().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends Boolean> resource) {
                invoke2((Resource<Boolean>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<Boolean> resource) {
                if (resource != null) {
                    SpecialOccurrenceFragment specialOccurrenceFragment = SpecialOccurrenceFragment.this;
                    SpecialOccurrenceViewModel specialOccurrenceViewModel2 = specialOccurrenceViewModel;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        BaseFragment.showSnackBar$default(specialOccurrenceFragment, "Berhasil menghapus gambar", null, null, 6, null);
                        specialOccurrenceViewModel2.finishDeletingSpecialOccurrencePage();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(specialOccurrenceFragment, error, null, null, 6, null);
                        }
                        specialOccurrenceViewModel2.finishDeletingSpecialOccurrencePage();
                    }
                }
            }
        }));
        specialOccurrenceViewModel.isAnyUnverified().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$6
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
                SpecialOccurrenceAdapter specialOccurrenceAdapter;
                specialOccurrenceAdapter = SpecialOccurrenceFragment.this.adapter;
                if (specialOccurrenceAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    specialOccurrenceAdapter = null;
                }
                specialOccurrenceAdapter.setVerified(!bool.booleanValue());
            }
        }));
        specialOccurrenceViewModel.getBackgroundProcessZipSpecialOccurrence().observe(getViewLifecycleOwner(), new SpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<BackgroundProcess, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupViewModel$1$7
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
                ActivityResultLauncher<Intent> activityResultLauncher;
                if (backgroundProcess != null) {
                    SpecialOccurrenceFragment specialOccurrenceFragment = SpecialOccurrenceFragment.this;
                    if (backgroundProcess.isLoading() || Intrinsics.areEqual((Object) backgroundProcess.isSuccess(), (Object) true)) {
                        return;
                    }
                    String errorMessage = backgroundProcess.getErrorMessage();
                    ActivityResultLauncher<Intent> activityResultLauncher2 = null;
                    boolean z = false;
                    if (errorMessage != null && StringsKt.contains$default((CharSequence) errorMessage, (CharSequence) "jwt", false, 2, (Object) null)) {
                        z = true;
                    }
                    if (z) {
                        Toast.makeText(specialOccurrenceFragment.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                        try {
                            mainViewModel = specialOccurrenceFragment.getMainViewModel();
                            AuthRequestUseCase authRequestUseCase = mainViewModel.getAuthRequestUseCase();
                            Context requireContext = specialOccurrenceFragment.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            activityResultLauncher = specialOccurrenceFragment.pdfGenerationAuthRequest;
                            if (activityResultLauncher == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("pdfGenerationAuthRequest");
                            } else {
                                activityResultLauncher2 = activityResultLauncher;
                            }
                            authRequestUseCase.start(requireContext, activityResultLauncher2);
                            return;
                        } catch (ActivityNotFoundException e) {
                            FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                            String string = specialOccurrenceFragment.getString(R.string.key_setup_error_browser_not_found);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                            BaseFragment.showSnackBar$default(specialOccurrenceFragment, string, null, null, 6, null);
                            return;
                        }
                    }
                    BaseFragment.showSnackBar$default(specialOccurrenceFragment, "Error: " + backgroundProcess.getErrorMessage(), null, null, 6, null);
                }
            }
        }));
    }

    private final void setupLauncher() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda14
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLauncher$lambda$14(SpecialOccurrenceFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.requestPermissionsLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLauncher$lambda$15((ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…rtActivityForResult()) {}");
        this.openAppSettingsLauncher = registerForActivityResult2;
        ActivityResultLauncher<Uri> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLauncher$lambda$16(SpecialOccurrenceFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          }\n            }");
        this.takePictureLauncher = registerForActivityResult3;
        ActivityResultLauncher<Intent> registerForActivityResult4 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SpecialOccurrenceFragment.setupLauncher$lambda$18(SpecialOccurrenceFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult4, "registerForActivityResul…          }\n            }");
        this.cropImageLauncher = registerForActivityResult4;
    }

    public static final void setupLauncher$lambda$14(SpecialOccurrenceFragment this$0, Boolean permissionGranted) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(permissionGranted, "permissionGranted");
        if (permissionGranted.booleanValue()) {
            this$0.tryTakePhoto();
            return;
        }
        String string = this$0.getString(R.string.message_camera_permissions_denied, this$0.getString(R.string.app_name_real));
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
        String string2 = this$0.getString(R.string.action_allow);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.action_allow)");
        this$0.showSnackBar(string, string2, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLauncher$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ActivityResultLauncher activityResultLauncher;
                activityResultLauncher = SpecialOccurrenceFragment.this.openAppSettingsLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("openAppSettingsLauncher");
                    activityResultLauncher = null;
                }
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", SpecialOccurrenceFragment.this.requireContext().getPackageName(), null));
                activityResultLauncher.launch(intent);
            }
        });
    }

    public static final void setupLauncher$lambda$16(SpecialOccurrenceFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            CaptureSpecialOccurrenceImageUseCase captureImageUseCase = this$0.getSpecialOccurrenceViewModel().getCaptureImageUseCase();
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            captureImageUseCase.saveOriginalPhoto(requireActivity, this$0.getArgs().getKodeTps(), "kejadian_khusus", new Function1<Bitmap, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLauncher$3$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Bitmap bitmap) {
                    invoke2(bitmap);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Bitmap bitmap) {
                    ActivityResultLauncher activityResultLauncher;
                    Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                    ImageCropConstants.selectedImageBitmap = bitmap;
                    activityResultLauncher = SpecialOccurrenceFragment.this.cropImageLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
                        activityResultLauncher = null;
                    }
                    activityResultLauncher.launch(new Intent(SpecialOccurrenceFragment.this.getContext(), ImageCropActivity.class));
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupLauncher$3$2
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
                public final void invoke2(Exception exception) {
                    Intrinsics.checkNotNullParameter(exception, "exception");
                    FirebaseCrashlytics.getInstance().recordException(exception);
                    SpecialOccurrenceFragment specialOccurrenceFragment = SpecialOccurrenceFragment.this;
                    String string = specialOccurrenceFragment.getString(R.string.error_capture_image, exception.getMessage());
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(specialOccurrenceFragment, string, null, null, 6, null);
                }
            });
            return;
        }
        this$0.getSpecialOccurrenceViewModel().getCaptureImageUseCase().deletePreparedPhoto();
    }

    public static final void setupLauncher$lambda$18(SpecialOccurrenceFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer valueOf = activityResult != null ? Integer.valueOf(activityResult.getResultCode()) : null;
        if (valueOf != null && valueOf.intValue() == -1) {
            SpecialOccurrencePage value = this$0.getSpecialOccurrenceViewModel().getRetakeSpecialOccurrencePage().getValue();
            Bitmap bitmap = ImageCropConstants.croppedImageBitmap;
            if (bitmap != null) {
                this$0.getSpecialOccurrenceViewModel().getCaptureImageUseCase().saveCroppedPhoto(bitmap, "kejadian_khusus", this$0.getArgs().getKodeTps(), value);
            }
        } else if (valueOf != null && valueOf.intValue() == 0) {
            this$0.getSpecialOccurrenceViewModel().getCaptureImageUseCase().deleteOriginalPhoto();
        }
    }

    private final void setupFragmentListener() {
        androidx.fragment.app.FragmentKt.setFragmentResultListener(this, VerifySpecialOccurrenceFragment.SEND_SPECIAL_OCCURRENCE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$setupFragmentListener$1
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
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                if (bundle.getBoolean(VerifySpecialOccurrenceFragment.SEND_SPECIAL_OCCURRENCE_RESULT_TYPE_SEND_SUCCESS, false)) {
                    SpecialOccurrenceFragment.this.tryCreatePdf();
                } else {
                    BaseFragment.showSnackBar$default(SpecialOccurrenceFragment.this, "Terjadi kesalahan dalam penguncian tanda terima salinan saksi dan kejadian khusus. Silakan verifikasi ulang.", null, null, 6, null);
                }
            }
        });
    }

    public final void tryTakePhoto() {
        FirebaseCrashlytics.getInstance().log("SpecialOccurrenceFragment : User clicks 'Photo' button");
        CaptureSpecialOccurrenceImageUseCase captureImageUseCase = getSpecialOccurrenceViewModel().getCaptureImageUseCase();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        captureImageUseCase.prepareTakingPhoto(requireContext, getArgs().getKodeTps(), new Function3<Uri, Uri, Uri, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$tryTakePhoto$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Uri uri, Uri uri2, Uri uri3) {
                invoke2(uri, uri2, uri3);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Uri imageUri, Uri uri, Uri uri2) {
                ActivityResultLauncher activityResultLauncher;
                Intrinsics.checkNotNullParameter(imageUri, "imageUri");
                Intrinsics.checkNotNullParameter(uri, "<anonymous parameter 1>");
                Intrinsics.checkNotNullParameter(uri2, "<anonymous parameter 2>");
                activityResultLauncher = SpecialOccurrenceFragment.this.takePictureLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("takePictureLauncher");
                    activityResultLauncher = null;
                }
                activityResultLauncher.launch(imageUri);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$tryTakePhoto$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Exception exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                FirebaseCrashlytics.getInstance().recordException(exception);
            }
        });
    }

    public final void tryCreatePdf() {
        SpecialOccurrenceViewModel specialOccurrenceViewModel = getSpecialOccurrenceViewModel();
        SpecialOccurrenceFragment specialOccurrenceFragment = this;
        ActivityResultLauncher<Intent> activityResultLauncher = this.localAuthLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
            activityResultLauncher = null;
        }
        specialOccurrenceViewModel.createPdf(specialOccurrenceFragment, activityResultLauncher);
    }

    public final void deleteSpecialOccurrence(final SpecialOccurrencePage specialOccurrencePage) {
        new MaterialAlertDialogBuilder(requireContext()).setMessage((CharSequence) getString(R.string.delete_special_occurrence_message)).setNegativeButton((CharSequence) getString(R.string.action_no), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SpecialOccurrenceFragment.deleteSpecialOccurrence$lambda$19(dialogInterface, i);
            }
        }).setPositiveButton((CharSequence) getString(R.string.action_delete_image), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SpecialOccurrenceFragment.deleteSpecialOccurrence$lambda$20(SpecialOccurrenceFragment.this, specialOccurrencePage, dialogInterface, i);
            }
        }).show();
    }

    public static final void deleteSpecialOccurrence$lambda$20(SpecialOccurrenceFragment this$0, SpecialOccurrencePage specialOccurrencePage, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(specialOccurrencePage, "$specialOccurrencePage");
        this$0.getSpecialOccurrenceViewModel().deleteSpecialOccurrence(specialOccurrencePage);
    }

    /* compiled from: SpecialOccurrenceFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
