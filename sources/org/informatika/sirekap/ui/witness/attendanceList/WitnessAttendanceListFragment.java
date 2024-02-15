package org.informatika.sirekap.ui.witness.attendanceList;

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
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentWitnessAttendanceListBinding;
import org.informatika.sirekap.databinding.ViewLockAttendanceBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.imageCrop.ImageCropActivity;
import org.informatika.sirekap.ui.imageCrop.ImageCropConstants;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListFragment;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: WitnessAttendanceListFragment.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 <2\u00020\u0001:\u0001<B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010'\u001a\u00020$2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J$\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020+2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u00102\u001a\u00020$H\u0002J\b\u00103\u001a\u00020$H\u0002J\b\u00104\u001a\u00020$H\u0002J\b\u00105\u001a\u00020$H\u0002J\u0006\u00106\u001a\u00020$J\b\u00107\u001a\u00020$H\u0002J\u0011\u00108\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u00109J\b\u0010:\u001a\u00020$H\u0002J\b\u0010;\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0017\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter;", "args", "Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentWitnessAttendanceListBinding;", "cropImageLauncher", "localAuthLauncher", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "openAppSettingsLauncher", "pdfGenerationAuthRequestLauncher", "requestPermissionsLauncher", "", "takePictureLauncher", "Landroid/net/Uri;", "witnessAttendanceListViewModel", "Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListViewModel;", "getWitnessAttendanceListViewModel", "()Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListViewModel;", "witnessAttendanceListViewModel$delegate", "deleteAttendance", "", "attendancePage", "Lorg/informatika/sirekap/model/AttendancePage;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupFragmentListener", "setupLauncher", "setupLaunchers", "setupViewModel", "tryCreatePdf", "trySubmit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySubmitOnline", "tryTakePhoto", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class WitnessAttendanceListFragment extends Hilt_WitnessAttendanceListFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessALFragment";
    private WitnessAttendanceAdapter adapter;
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentWitnessAttendanceListBinding binding;
    private ActivityResultLauncher<Intent> cropImageLauncher;
    private ActivityResultLauncher<Intent> localAuthLauncher;
    private final Lazy mainViewModel$delegate;
    private ActivityResultLauncher<Intent> openAppSettingsLauncher;
    private ActivityResultLauncher<Intent> pdfGenerationAuthRequestLauncher;
    private ActivityResultLauncher<String> requestPermissionsLauncher;
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private final Lazy witnessAttendanceListViewModel$delegate;

    public static final void deleteAttendance$lambda$19(DialogInterface dialogInterface, int i) {
    }

    public static final void setupLauncher$lambda$15(ActivityResult activityResult) {
    }

    public WitnessAttendanceListFragment() {
        final WitnessAttendanceListFragment witnessAttendanceListFragment = this;
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(WitnessAttendanceListFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$navArgs$1
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
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.witnessAttendanceListViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessAttendanceListFragment, Reflection.getOrCreateKotlinClass(WitnessAttendanceListViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessAttendanceListFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = witnessAttendanceListFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$special$$inlined$activityViewModels$default$3
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

    public final WitnessAttendanceListFragmentArgs getArgs() {
        return (WitnessAttendanceListFragmentArgs) this.args$delegate.getValue();
    }

    public final WitnessAttendanceListViewModel getWitnessAttendanceListViewModel() {
        return (WitnessAttendanceListViewModel) this.witnessAttendanceListViewModel$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda9
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLaunchers$lambda$0(WitnessAttendanceListFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.localAuthLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda10
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLaunchers$lambda$1(WitnessAttendanceListFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda11
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLaunchers$lambda$2(WitnessAttendanceListFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          )\n            }");
        this.pdfGenerationAuthRequestLauncher = registerForActivityResult3;
    }

    public static final void setupLaunchers$lambda$0(WitnessAttendanceListFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.getMainViewModel().getLocalAuth().postValue(true);
            this$0.getWitnessAttendanceListViewModel().startGeneratePdf(this$0);
            return;
        }
        this$0.getWitnessAttendanceListViewModel().failedAuthentication(this$0);
    }

    public static final void setupLaunchers$lambda$1(WitnessAttendanceListFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLaunchers$2$1
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
                BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Unggah Berkas (PDF)' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLaunchers$2$2
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
                BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    public static final void setupLaunchers$lambda$2(WitnessAttendanceListFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLaunchers$3$1
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
                BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Kunci & Buat Dokumen' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLaunchers$3$2
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
                BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWitnessAttendanceListBinding inflate = FragmentWitnessAttendanceListBinding.inflate(inflater, viewGroup, false);
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
        getWitnessAttendanceListViewModel().setup(getArgs().getKodeTps());
    }

    private final void setupBinding() {
        FragmentWitnessAttendanceListBinding fragmentWitnessAttendanceListBinding = this.binding;
        WitnessAttendanceAdapter witnessAttendanceAdapter = null;
        if (fragmentWitnessAttendanceListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessAttendanceListBinding = null;
        }
        fragmentWitnessAttendanceListBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentWitnessAttendanceListBinding.setViewModel(getWitnessAttendanceListViewModel());
        this.adapter = new WitnessAttendanceAdapter(new Function1<AttendancePage, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AttendancePage attendancePage) {
                invoke2(attendancePage);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(AttendancePage it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FirebaseCrashlytics.getInstance().log("WitnessALFragment User clicks 'Delete Attendance Photo' button");
                WitnessAttendanceListFragment.this.deleteAttendance(it);
            }
        }, new Function1<AttendancePage, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AttendancePage attendancePage) {
                invoke2(attendancePage);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(AttendancePage it) {
                WitnessAttendanceListViewModel witnessAttendanceListViewModel;
                Intrinsics.checkNotNullParameter(it, "it");
                FirebaseCrashlytics.getInstance().log("WitnessALFragment User clicks 'Retake Attendance Photo' button");
                witnessAttendanceListViewModel = WitnessAttendanceListFragment.this.getWitnessAttendanceListViewModel();
                witnessAttendanceListViewModel.getRetakeAttendancePage().postValue(it);
                WitnessAttendanceListFragment.this.tryTakePhoto();
            }
        }, new Function2<AttendancePage, Boolean, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(AttendancePage attendancePage, Boolean bool) {
                invoke(attendancePage, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AttendancePage attendees, boolean z) {
                WitnessAttendanceAdapter witnessAttendanceAdapter2;
                WitnessAttendanceAdapter witnessAttendanceAdapter3;
                WitnessAttendanceListViewModel witnessAttendanceListViewModel;
                WitnessAttendanceAdapter witnessAttendanceAdapter4;
                WitnessAttendanceListViewModel witnessAttendanceListViewModel2;
                Intrinsics.checkNotNullParameter(attendees, "attendees");
                WitnessAttendanceAdapter witnessAttendanceAdapter5 = null;
                if (z) {
                    FirebaseCrashlytics.getInstance().log("WitnessALFragment User clicks 'Move Attendance Photo Up' button");
                    if (attendees.getUrutan() != 1) {
                        witnessAttendanceAdapter4 = WitnessAttendanceListFragment.this.adapter;
                        if (witnessAttendanceAdapter4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            witnessAttendanceAdapter5 = witnessAttendanceAdapter4;
                        }
                        AttendancePage switchedAttendees = witnessAttendanceAdapter5.getCurrentList().get(attendees.getUrutan() - 2);
                        witnessAttendanceListViewModel2 = WitnessAttendanceListFragment.this.getWitnessAttendanceListViewModel();
                        Intrinsics.checkNotNullExpressionValue(switchedAttendees, "switchedAttendees");
                        witnessAttendanceListViewModel2.moveAttendees(attendees, switchedAttendees, true);
                        return;
                    }
                    BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, "Halaman ini sudah berada di paling atas", null, null, 6, null);
                    return;
                }
                FirebaseCrashlytics.getInstance().log("WitnessALFragment User clicks 'Move Attendance Photo Down' button");
                int urutan = attendees.getUrutan();
                witnessAttendanceAdapter2 = WitnessAttendanceListFragment.this.adapter;
                if (witnessAttendanceAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    witnessAttendanceAdapter2 = null;
                }
                if (urutan != witnessAttendanceAdapter2.getItemCount()) {
                    witnessAttendanceAdapter3 = WitnessAttendanceListFragment.this.adapter;
                    if (witnessAttendanceAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        witnessAttendanceAdapter5 = witnessAttendanceAdapter3;
                    }
                    AttendancePage switchedAttendees2 = witnessAttendanceAdapter5.getCurrentList().get(attendees.getUrutan());
                    witnessAttendanceListViewModel = WitnessAttendanceListFragment.this.getWitnessAttendanceListViewModel();
                    Intrinsics.checkNotNullExpressionValue(switchedAttendees2, "switchedAttendees");
                    witnessAttendanceListViewModel.moveAttendees(attendees, switchedAttendees2, false);
                    return;
                }
                BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, "Halaman ini sudah berada di paling bawah", null, null, 6, null);
            }
        }, new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$4
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
                    FragmentKt.findNavController(WitnessAttendanceListFragment.this).navigate(WitnessAttendanceListFragmentDirections.Companion.actionWitnessAttendanceListFragmentToPreviewImageFragment(it));
                } catch (Exception e) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                }
            }
        });
        RecyclerView recyclerView = fragmentWitnessAttendanceListBinding.recyclerView;
        WitnessAttendanceAdapter witnessAttendanceAdapter2 = this.adapter;
        if (witnessAttendanceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            witnessAttendanceAdapter = witnessAttendanceAdapter2;
        }
        recyclerView.setAdapter(witnessAttendanceAdapter);
        fragmentWitnessAttendanceListBinding.buttonSend.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessAttendanceListFragment.setupBinding$lambda$12$lambda$3(WitnessAttendanceListFragment.this, view);
            }
        });
        fragmentWitnessAttendanceListBinding.buttonAdd.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessAttendanceListFragment.setupBinding$lambda$12$lambda$4(WitnessAttendanceListFragment.this, view);
            }
        });
        ViewLockAttendanceBinding viewLockAttendanceBinding = fragmentWitnessAttendanceListBinding.viewLockAttendance;
        viewLockAttendanceBinding.buttonCreatePdf.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessAttendanceListFragment.setupBinding$lambda$12$lambda$11$lambda$5(WitnessAttendanceListFragment.this, view);
            }
        });
        viewLockAttendanceBinding.buttonUploadPdf.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessAttendanceListFragment.setupBinding$lambda$12$lambda$11$lambda$6(WitnessAttendanceListFragment.this, view);
            }
        });
        viewLockAttendanceBinding.buttonUploadPdfOffline.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessAttendanceListFragment.setupBinding$lambda$12$lambda$11$lambda$7(WitnessAttendanceListFragment.this, view);
            }
        });
        viewLockAttendanceBinding.buttonShareZip.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitnessAttendanceListFragment.setupBinding$lambda$12$lambda$11$lambda$10(WitnessAttendanceListFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$12$lambda$3(WitnessAttendanceListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("WitnessALFragment User clicks 'Send Attendance List Photo' button");
        FragmentKt.findNavController(this$0).navigate(WitnessAttendanceListFragmentDirections.Companion.actionWitnessAttendanceListFragmentToVerifyWitnessAttendanceListFragment(this$0.getArgs().getKodeTps()));
    }

    public static final void setupBinding$lambda$12$lambda$4(WitnessAttendanceListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("WitnessALFragment User clicks 'Add Attendance List Photo' button");
        ActivityResultLauncher<String> activityResultLauncher = this$0.requestPermissionsLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestPermissionsLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch("android.permission.CAMERA");
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$5(WitnessAttendanceListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("WitnessALFragment: Click 'Kunci & Buat Dokumen'");
        this$0.tryCreatePdf();
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$6(WitnessAttendanceListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMainViewModel().getAuthRequestUseCase().isLoading().postValue(true);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new WitnessAttendanceListFragment$setupBinding$1$7$2$1(this$0, null), 2, null);
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$7(WitnessAttendanceListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.trySubmitOnline();
    }

    public static final void setupBinding$lambda$12$lambda$11$lambda$10(WitnessAttendanceListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan PDF Daftar Hadir'");
        String value = this$0.getWitnessAttendanceListViewModel().getKodeTps().getValue();
        ZipAttendanceWorker.Companion companion = ZipAttendanceWorker.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Intrinsics.checkNotNull(value);
        File pdfFile = companion.getPdfFile(requireContext, value, this$0.getWitnessAttendanceListViewModel().getEncryptedSharedPreferences());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this$0.requireContext(), BuildConfig.FILE_PROVIDER, pdfFile));
        intent.setType("application/pdf");
        intent.addFlags(1);
        this$0.startActivity(Intent.createChooser(intent, this$0.getString(R.string.share_zip_via)));
    }

    public final void trySubmitOnline() {
        if (!getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new WitnessAttendanceListFragment$trySubmitOnline$3(this, null), 3, null);
            return;
        }
        AuthRequestUseCase authRequestUseCase = getMainViewModel().getAuthRequestUseCase();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$trySubmitOnline$1
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
            /* compiled from: WitnessAttendanceListFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$trySubmitOnline$1$1", f = "WitnessAttendanceListFragment.kt", i = {}, l = {DilithiumEngine.DilithiumPolyT1PackedBytes}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$trySubmitOnline$1$1  reason: invalid class name */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ WitnessAttendanceListFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(WitnessAttendanceListFragment witnessAttendanceListFragment, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = witnessAttendanceListFragment;
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
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(WitnessAttendanceListFragment.this, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$trySubmitOnline$2
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
                Toast.makeText(WitnessAttendanceListFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                try {
                    mainViewModel = WitnessAttendanceListFragment.this.getMainViewModel();
                    AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                    Context requireContext2 = WitnessAttendanceListFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    activityResultLauncher = WitnessAttendanceListFragment.this.authRequestLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                        activityResultLauncher = null;
                    }
                    authRequestUseCase2.start(requireContext2, activityResultLauncher);
                } catch (ActivityNotFoundException e) {
                    FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                    WitnessAttendanceListFragment witnessAttendanceListFragment = WitnessAttendanceListFragment.this;
                    String string = witnessAttendanceListFragment.getString(R.string.key_setup_error_browser_not_found);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                    BaseFragment.showSnackBar$default(witnessAttendanceListFragment, string, null, null, 6, null);
                }
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:3|(4:5|6|7|(1:(1:(3:11|12|13)(2:15|16))(6:17|18|19|(1:21)|12|13))(2:22|(7:24|25|(4:27|(1:35)(1:31)|32|(1:34))|19|(0)|12|13)(2:36|37))))|40|6|7|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0047, code lost:
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00b0, code lost:
        com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance().recordException(r12);
        r2 = r2 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f6 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object trySubmit(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment.trySubmit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setupViewModel() {
        final WitnessAttendanceListViewModel witnessAttendanceListViewModel = getWitnessAttendanceListViewModel();
        witnessAttendanceListViewModel.getAttendanceList().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends AttendancePage>, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends AttendancePage> list) {
                invoke2((List<AttendancePage>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<AttendancePage> list) {
                WitnessAttendanceAdapter witnessAttendanceAdapter;
                if (list != null) {
                    witnessAttendanceAdapter = WitnessAttendanceListFragment.this.adapter;
                    if (witnessAttendanceAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        witnessAttendanceAdapter = null;
                    }
                    witnessAttendanceAdapter.submitList(list);
                }
            }
        }));
        witnessAttendanceListViewModel.getCaptureImageUseCase().getCropPhotoResult().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<CaptureImageUseCase.CropPhotoResult, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$2
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
                    WitnessAttendanceListFragment witnessAttendanceListFragment = WitnessAttendanceListFragment.this;
                    WitnessAttendanceListViewModel witnessAttendanceListViewModel2 = witnessAttendanceListViewModel;
                    if (!cropPhotoResult.isSuccess()) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(cropPhotoResult.getErrorMessage()));
                        String string = witnessAttendanceListFragment.getString(R.string.error_capture_image, cropPhotoResult.getErrorMessage());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error…image, this.errorMessage)");
                        BaseFragment.showSnackBar$default(witnessAttendanceListFragment, string, null, null, 6, null);
                    }
                    witnessAttendanceListViewModel2.getCaptureImageUseCase().finishCrop();
                }
            }
        }));
        witnessAttendanceListViewModel.getCaptureImageUseCase().getAttendanceUpdateResource().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends AttendancePage>, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends AttendancePage> resource) {
                invoke2((Resource<AttendancePage>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<AttendancePage> resource) {
                if (resource != null) {
                    WitnessAttendanceListViewModel witnessAttendanceListViewModel2 = WitnessAttendanceListViewModel.this;
                    WitnessAttendanceListFragment witnessAttendanceListFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (witnessAttendanceListViewModel2.getCaptureImageUseCase().getAttendanceUpdateModel().getValue() != null) {
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, "Berhasil memperbarui gambar", null, null, 6, null);
                        }
                        witnessAttendanceListViewModel2.finishTakingPicture();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, error, null, null, 6, null);
                        }
                        witnessAttendanceListViewModel2.finishTakingPicture();
                    }
                }
            }
        }));
        witnessAttendanceListViewModel.getCaptureImageUseCase().getAttendanceInsertResource().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends AttendancePage>, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends AttendancePage> resource) {
                invoke2((Resource<AttendancePage>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<AttendancePage> resource) {
                if (resource != null) {
                    WitnessAttendanceListViewModel witnessAttendanceListViewModel2 = WitnessAttendanceListViewModel.this;
                    WitnessAttendanceListFragment witnessAttendanceListFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (witnessAttendanceListViewModel2.getCaptureImageUseCase().getAttendanceInsertModel().getValue() != null) {
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, "Berhasil menambahkan gambar", null, null, 6, null);
                        }
                        witnessAttendanceListViewModel2.finishTakingPicture();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, error, null, null, 6, null);
                        }
                        witnessAttendanceListViewModel2.finishTakingPicture();
                    }
                }
            }
        }));
        witnessAttendanceListViewModel.getDeleteAttendanceResource().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$5
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
                    WitnessAttendanceListFragment witnessAttendanceListFragment = WitnessAttendanceListFragment.this;
                    WitnessAttendanceListViewModel witnessAttendanceListViewModel2 = witnessAttendanceListViewModel;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        BaseFragment.showSnackBar$default(witnessAttendanceListFragment, "Berhasil menghapus gambar", null, null, 6, null);
                        witnessAttendanceListViewModel2.finishDeletingAttendance();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, error, null, null, 6, null);
                        }
                        witnessAttendanceListViewModel2.finishDeletingAttendance();
                    }
                }
            }
        }));
        witnessAttendanceListViewModel.getMoveAttendeesResource().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends AttendancePage>, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends AttendancePage> resource) {
                invoke2((Resource<AttendancePage>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<AttendancePage> resource) {
                if (resource != null) {
                    WitnessAttendanceListFragment witnessAttendanceListFragment = WitnessAttendanceListFragment.this;
                    WitnessAttendanceListViewModel witnessAttendanceListViewModel2 = witnessAttendanceListViewModel;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        BaseFragment.showSnackBar$default(witnessAttendanceListFragment, "Berhasil memindahkan gambar", null, null, 6, null);
                        witnessAttendanceListViewModel2.finishMovingAttendees();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, error, null, null, 6, null);
                        }
                        witnessAttendanceListViewModel2.finishMovingAttendees();
                    }
                }
            }
        }));
        witnessAttendanceListViewModel.isAnyUnverified().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$7
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
                WitnessAttendanceAdapter witnessAttendanceAdapter;
                witnessAttendanceAdapter = WitnessAttendanceListFragment.this.adapter;
                if (witnessAttendanceAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    witnessAttendanceAdapter = null;
                }
                witnessAttendanceAdapter.setVerified(!bool.booleanValue());
            }
        }));
        witnessAttendanceListViewModel.getBackgroundProcessZipAttendance().observe(getViewLifecycleOwner(), new WitnessAttendanceListFragment$sam$androidx_lifecycle_Observer$0(new Function1<BackgroundProcess, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupViewModel$1$8
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
                    WitnessAttendanceListFragment witnessAttendanceListFragment = WitnessAttendanceListFragment.this;
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
                        Toast.makeText(witnessAttendanceListFragment.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                        try {
                            mainViewModel = witnessAttendanceListFragment.getMainViewModel();
                            AuthRequestUseCase authRequestUseCase = mainViewModel.getAuthRequestUseCase();
                            Context requireContext = witnessAttendanceListFragment.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            activityResultLauncher = witnessAttendanceListFragment.pdfGenerationAuthRequestLauncher;
                            if (activityResultLauncher == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("pdfGenerationAuthRequestLauncher");
                            } else {
                                activityResultLauncher2 = activityResultLauncher;
                            }
                            authRequestUseCase.start(requireContext, activityResultLauncher2);
                            return;
                        } catch (ActivityNotFoundException e) {
                            FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                            String string = witnessAttendanceListFragment.getString(R.string.key_setup_error_browser_not_found);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, string, null, null, 6, null);
                            return;
                        }
                    }
                    BaseFragment.showSnackBar$default(witnessAttendanceListFragment, "Error: " + backgroundProcess.getErrorMessage(), null, null, 6, null);
                }
            }
        }));
    }

    private final void setupLauncher() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLauncher$lambda$14(WitnessAttendanceListFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.requestPermissionsLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLauncher$lambda$15((ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…rtActivityForResult()) {}");
        this.openAppSettingsLauncher = registerForActivityResult2;
        ActivityResultLauncher<Uri> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLauncher$lambda$16(WitnessAttendanceListFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          }\n            }");
        this.takePictureLauncher = registerForActivityResult3;
        ActivityResultLauncher<Intent> registerForActivityResult4 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda8
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WitnessAttendanceListFragment.setupLauncher$lambda$18(WitnessAttendanceListFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult4, "registerForActivityResul…          }\n            }");
        this.cropImageLauncher = registerForActivityResult4;
    }

    public static final void setupLauncher$lambda$14(WitnessAttendanceListFragment this$0, Boolean permissionGranted) {
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
        this$0.showSnackBar(string, string2, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLauncher$1$1
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
                activityResultLauncher = WitnessAttendanceListFragment.this.openAppSettingsLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("openAppSettingsLauncher");
                    activityResultLauncher = null;
                }
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", WitnessAttendanceListFragment.this.requireContext().getPackageName(), null));
                activityResultLauncher.launch(intent);
            }
        });
    }

    public static final void setupLauncher$lambda$16(WitnessAttendanceListFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            CaptureAttendanceImageUseCase captureImageUseCase = this$0.getWitnessAttendanceListViewModel().getCaptureImageUseCase();
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            captureImageUseCase.saveOriginalPhoto(requireActivity, this$0.getArgs().getKodeTps(), "daftar_hadir", new Function1<Bitmap, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLauncher$3$1
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
                    activityResultLauncher = WitnessAttendanceListFragment.this.cropImageLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
                        activityResultLauncher = null;
                    }
                    activityResultLauncher.launch(new Intent(WitnessAttendanceListFragment.this.getContext(), ImageCropActivity.class));
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupLauncher$3$2
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
                    WitnessAttendanceListFragment witnessAttendanceListFragment = WitnessAttendanceListFragment.this;
                    String string = witnessAttendanceListFragment.getString(R.string.error_capture_image, exception.getMessage());
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(witnessAttendanceListFragment, string, null, null, 6, null);
                }
            });
            return;
        }
        this$0.getWitnessAttendanceListViewModel().getCaptureImageUseCase().deletePreparedPhoto();
    }

    public static final void setupLauncher$lambda$18(WitnessAttendanceListFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WitnessAttendanceAdapter witnessAttendanceAdapter = null;
        Integer valueOf = activityResult != null ? Integer.valueOf(activityResult.getResultCode()) : null;
        if (valueOf != null && valueOf.intValue() == -1) {
            AttendancePage value = this$0.getWitnessAttendanceListViewModel().getRetakeAttendancePage().getValue();
            Bitmap bitmap = ImageCropConstants.croppedImageBitmap;
            if (bitmap != null) {
                CaptureAttendanceImageUseCase captureImageUseCase = this$0.getWitnessAttendanceListViewModel().getCaptureImageUseCase();
                String kodeTps = this$0.getArgs().getKodeTps();
                WitnessAttendanceAdapter witnessAttendanceAdapter2 = this$0.adapter;
                if (witnessAttendanceAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    witnessAttendanceAdapter = witnessAttendanceAdapter2;
                }
                captureImageUseCase.saveCroppedPhoto(bitmap, "daftar_hadir", kodeTps, value, witnessAttendanceAdapter.getItemCount() + 1);
            }
        } else if (valueOf != null && valueOf.intValue() == 0) {
            this$0.getWitnessAttendanceListViewModel().getCaptureImageUseCase().deleteOriginalPhoto();
        }
    }

    private final void setupFragmentListener() {
        androidx.fragment.app.FragmentKt.setFragmentResultListener(this, VerifyWitnessAttendanceListFragment.SEND_ATTENDANCE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupFragmentListener$1
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
                if (bundle.getBoolean(VerifyWitnessAttendanceListFragment.SEND_ATTENDANCE_RESULT_TYPE_SEND_SUCCESS, false)) {
                    WitnessAttendanceListFragment.this.tryCreatePdf();
                } else {
                    BaseFragment.showSnackBar$default(WitnessAttendanceListFragment.this, "Terjadi kesalahan dalam penguncian daftar hadir. Silakan verifikasi ulang.", null, null, 6, null);
                }
            }
        });
    }

    public final void tryTakePhoto() {
        FirebaseCrashlytics.getInstance().log("WitnessALFragment : User clicks 'Photo' button");
        CaptureAttendanceImageUseCase captureImageUseCase = getWitnessAttendanceListViewModel().getCaptureImageUseCase();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        captureImageUseCase.prepareTakingPhoto(requireContext, getArgs().getKodeTps(), new Function3<Uri, Uri, Uri, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$tryTakePhoto$1
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
                activityResultLauncher = WitnessAttendanceListFragment.this.takePictureLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("takePictureLauncher");
                    activityResultLauncher = null;
                }
                activityResultLauncher.launch(imageUri);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$tryTakePhoto$2
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
        WitnessAttendanceListViewModel witnessAttendanceListViewModel = getWitnessAttendanceListViewModel();
        WitnessAttendanceListFragment witnessAttendanceListFragment = this;
        ActivityResultLauncher<Intent> activityResultLauncher = this.localAuthLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
            activityResultLauncher = null;
        }
        witnessAttendanceListViewModel.createPdf(witnessAttendanceListFragment, activityResultLauncher);
    }

    public final void deleteAttendance(final AttendancePage attendancePage) {
        new MaterialAlertDialogBuilder(requireContext()).setMessage((CharSequence) getString(R.string.verify_attendees_delete_attendance_message)).setNegativeButton((CharSequence) getString(R.string.action_no), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                WitnessAttendanceListFragment.deleteAttendance$lambda$19(dialogInterface, i);
            }
        }).setPositiveButton((CharSequence) getString(R.string.action_delete_image), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                WitnessAttendanceListFragment.deleteAttendance$lambda$20(WitnessAttendanceListFragment.this, attendancePage, dialogInterface, i);
            }
        }).show();
    }

    public static final void deleteAttendance$lambda$20(WitnessAttendanceListFragment this$0, AttendancePage attendancePage, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(attendancePage, "$attendancePage");
        this$0.getWitnessAttendanceListViewModel().deleteAttendance(attendancePage);
    }

    /* compiled from: WitnessAttendanceListFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
