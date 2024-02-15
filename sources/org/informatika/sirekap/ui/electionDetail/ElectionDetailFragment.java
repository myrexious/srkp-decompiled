package org.informatika.sirekap.ui.electionDetail;

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
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.core.os.BundleKt;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import dagger.hilt.android.AndroidEntryPoint;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
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
import org.informatika.sirekap.databinding.DialogVerifyBodyBinding;
import org.informatika.sirekap.databinding.FragmentElectionDetailBinding;
import org.informatika.sirekap.databinding.ViewLockElectionBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.GetElectionUseCase;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment;
import org.informatika.sirekap.ui.autocapture.AutoCaptureFragment;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment;
import org.informatika.sirekap.ui.dashboard.AprilTagCheckUseCase;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.imageCrop.ImageCropActivity;
import org.informatika.sirekap.ui.imageCrop.ImageCropConstants;
import org.informatika.sirekap.ui.sendImage.SendImageFragment;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: ElectionDetailFragment.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\b\u0007\u0018\u0000 E2\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0002J\"\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(H\u0002J\u0012\u0010)\u001a\u00020\"2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J$\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001a\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020-2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u00104\u001a\u00020\"2\u0006\u00105\u001a\u000206H\u0002J\b\u00107\u001a\u00020\"H\u0002J\b\u00108\u001a\u00020\"H\u0002J\b\u00109\u001a\u00020\"H\u0002J\b\u0010:\u001a\u00020\"H\u0002J\b\u0010;\u001a\u00020\"H\u0002J\u001e\u0010<\u001a\u00020\"2\u0006\u0010=\u001a\u00020(2\f\u0010>\u001a\b\u0012\u0004\u0012\u0002060?H\u0002J\b\u0010@\u001a\u00020\"H\u0002J\u0011\u0010A\u001a\u00020\"H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020\"H\u0002J\b\u0010D\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\fX\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006F"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/electionDetail/ElectionPageListAdapter;", "args", "Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentElectionDetailBinding;", "cropImageLauncher", "electionDetailViewModel", "Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailViewModel;", "getElectionDetailViewModel", "()Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailViewModel;", "electionDetailViewModel$delegate", "Lkotlin/Lazy;", "localAuthLauncher", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "openAppSettingsLauncher", "pdfGenerationRequestLauncher", "requestPermissionsLauncher", "", "confirmCreatePdf", "", "matchAprilTag", "detectedPemilihan", "detectedNomorHalaman", "", "isRaw", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "requestPermissionTakePhoto", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "setupBinding", "setupFragmentListener", "setupLauncher", "setupLaunchers", "setupViewModel", "submitListAdapter", "hideCompletedPages", "electionPages", "", "tryCreatePdf", "trySubmit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySubmitOnline", "tryTakePhoto", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class ElectionDetailFragment extends Hilt_ElectionDetailFragment {
    public static final Companion Companion = new Companion(null);
    public static final String ELECTION_DETAIL_FRAGMENT_RESULT = "ELECTION_DETAIL_FRAGMENT_RESULT";
    public static final String RESULT_KEY_ELECTION_ID = "RESULT_KEY_ELECTION_ID";
    public static final String RESULT_KEY_JENIS_PEMILIHAN = "RESULT_KEY_JENIS_PEMILIHAN";
    public static final String RESULT_KEY_JENIS_WAKTU = "RESULT_KEY_JENIS_WAKTU";
    private static final String TAG = "ElectionDetailFragment";
    private ElectionPageListAdapter adapter;
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentElectionDetailBinding binding;
    private ActivityResultLauncher<Intent> cropImageLauncher;
    private final Lazy electionDetailViewModel$delegate;
    private ActivityResultLauncher<Intent> localAuthLauncher;
    private final Lazy mainViewModel$delegate;
    private ActivityResultLauncher<Intent> openAppSettingsLauncher;
    private ActivityResultLauncher<Intent> pdfGenerationRequestLauncher;
    private ActivityResultLauncher<String> requestPermissionsLauncher;

    public static final void confirmCreatePdf$lambda$33$lambda$32(DialogInterface dialogInterface, int i) {
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$9$lambda$5$lambda$4(DialogInterface dialogInterface, int i) {
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$9$lambda$8$lambda$7(DialogInterface dialogInterface, int i) {
    }

    public static final void setupLauncher$lambda$18(ActivityResult activityResult) {
    }

    public ElectionDetailFragment() {
        final ElectionDetailFragment electionDetailFragment = this;
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(ElectionDetailFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$navArgs$1
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
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.electionDetailViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(electionDetailFragment, Reflection.getOrCreateKotlinClass(ElectionDetailViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(electionDetailFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = electionDetailFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$special$$inlined$activityViewModels$default$3
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

    public final ElectionDetailFragmentArgs getArgs() {
        return (ElectionDetailFragmentArgs) this.args$delegate.getValue();
    }

    public final ElectionDetailViewModel getElectionDetailViewModel() {
        return (ElectionDetailViewModel) this.electionDetailViewModel$delegate.getValue();
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    private final void setupLaunchers() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ElectionDetailFragment.setupLaunchers$lambda$0(ElectionDetailFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.localAuthLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ElectionDetailFragment.setupLaunchers$lambda$1(ElectionDetailFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ElectionDetailFragment.setupLaunchers$lambda$2(ElectionDetailFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          )\n            }");
        this.pdfGenerationRequestLauncher = registerForActivityResult3;
    }

    public static final void setupLaunchers$lambda$0(ElectionDetailFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.getMainViewModel().getLocalAuth().postValue(true);
            this$0.getElectionDetailViewModel().startGeneratePdf(this$0);
            return;
        }
        this$0.getElectionDetailViewModel().failedAuthentication(this$0);
    }

    public static final void setupLaunchers$lambda$1(ElectionDetailFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupLaunchers$2$1
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
                BaseFragment.showSnackBar$default(ElectionDetailFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Unggah Berkas (PDF)' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupLaunchers$2$2
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
                BaseFragment.showSnackBar$default(ElectionDetailFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    public static final void setupLaunchers$lambda$2(ElectionDetailFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupLaunchers$3$1
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
                BaseFragment.showSnackBar$default(ElectionDetailFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Kunci & Buat Dokumen' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupLaunchers$3$2
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
                BaseFragment.showSnackBar$default(ElectionDetailFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupLaunchers();
        setupFragmentListener();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentElectionDetailBinding inflate = FragmentElectionDetailBinding.inflate(inflater, viewGroup, false);
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
    }

    private final void setupBinding() {
        FragmentElectionDetailBinding fragmentElectionDetailBinding = this.binding;
        if (fragmentElectionDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentElectionDetailBinding = null;
        }
        fragmentElectionDetailBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentElectionDetailBinding.setViewModel(getElectionDetailViewModel());
        fragmentElectionDetailBinding.setGetElectionUseCase(getElectionDetailViewModel().getGetElectionUseCase());
        ViewLockElectionBinding viewLockElectionBinding = fragmentElectionDetailBinding.viewLockElection;
        viewLockElectionBinding.buttonCreatePdf.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$9(ElectionDetailFragment.this, view);
            }
        });
        viewLockElectionBinding.buttonShareZip.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$12(ElectionDetailFragment.this, view);
            }
        });
        viewLockElectionBinding.buttonUploadPdf.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$13(ElectionDetailFragment.this, view);
            }
        });
        viewLockElectionBinding.buttonUploadPdfOffline.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$14(ElectionDetailFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$9(ElectionDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("ElectionDetailFragment: Click 'Kunci & Buat Dokumen'");
        if (Intrinsics.areEqual(this$0.getArgs().getJenisPemilihan(), Election.ELECTION_PEMILIHAN_PRESIDEN) && !Intrinsics.areEqual((Object) this$0.getElectionDetailViewModel().getGetTpsTimeUseCasePemungutanSuara().isDataExist().getValue(), (Object) true)) {
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this$0.requireContext());
            materialAlertDialogBuilder.setTitle((CharSequence) "Waktu Pemungutan Suara Belum Dicatat");
            materialAlertDialogBuilder.setMessage((CharSequence) "Sebelum pemilihan Presiden dan Wakil Presiden dapat dikunci, Anda perlu mencatat waktu pemungutan suara pada TPS ini terlebih dahulu.");
            materialAlertDialogBuilder.setPositiveButton((CharSequence) "Catat Waktu", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda12
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$9$lambda$5$lambda$3(ElectionDetailFragment.this, dialogInterface, i);
                }
            });
            materialAlertDialogBuilder.setNegativeButton((CharSequence) this$0.getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda13
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$9$lambda$5$lambda$4(dialogInterface, i);
                }
            });
            materialAlertDialogBuilder.show();
        } else if (!Intrinsics.areEqual((Object) this$0.getElectionDetailViewModel().getGetTpsTimeUseCasePenghitunganSuara().isDataExist().getValue(), (Object) true)) {
            MaterialAlertDialogBuilder materialAlertDialogBuilder2 = new MaterialAlertDialogBuilder(this$0.requireContext());
            materialAlertDialogBuilder2.setTitle((CharSequence) "Waktu Penghitungan Suara Belum Dicatat");
            materialAlertDialogBuilder2.setMessage((CharSequence) "Sebelum pemilihan dapat dikunci, Anda perlu mencatat waktu penghitungan suara pada pemilihan ini terlebih dahulu.");
            materialAlertDialogBuilder2.setPositiveButton((CharSequence) "Catat Waktu", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda14
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$9$lambda$8$lambda$6(ElectionDetailFragment.this, dialogInterface, i);
                }
            });
            materialAlertDialogBuilder2.setNegativeButton((CharSequence) this$0.getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda15
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ElectionDetailFragment.setupBinding$lambda$16$lambda$15$lambda$9$lambda$8$lambda$7(dialogInterface, i);
                }
            });
            materialAlertDialogBuilder2.show();
        } else {
            this$0.confirmCreatePdf();
        }
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$9$lambda$5$lambda$3(ElectionDetailFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ElectionDetailFragment electionDetailFragment = this$0;
        FragmentKt.setFragmentResult(electionDetailFragment, ELECTION_DETAIL_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(RESULT_KEY_JENIS_PEMILIHAN, this$0.getArgs().getJenisPemilihan()), TuplesKt.to(RESULT_KEY_ELECTION_ID, this$0.getArgs().getElectionId()), TuplesKt.to(RESULT_KEY_JENIS_WAKTU, 0)));
        androidx.navigation.fragment.FragmentKt.findNavController(electionDetailFragment).navigateUp();
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$9$lambda$8$lambda$6(ElectionDetailFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ElectionDetailFragment electionDetailFragment = this$0;
        FragmentKt.setFragmentResult(electionDetailFragment, ELECTION_DETAIL_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(RESULT_KEY_JENIS_PEMILIHAN, this$0.getArgs().getJenisPemilihan()), TuplesKt.to(RESULT_KEY_ELECTION_ID, this$0.getArgs().getElectionId()), TuplesKt.to(RESULT_KEY_JENIS_WAKTU, 1)));
        androidx.navigation.fragment.FragmentKt.findNavController(electionDetailFragment).navigateUp();
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$12(ElectionDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("Click 'Bagikan PDF'");
        ElectionWithRelation value = this$0.getElectionDetailViewModel().getGetElectionUseCase().getElectionWithRelation().getValue();
        ZipElectionWorker.Companion companion = ZipElectionWorker.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Intrinsics.checkNotNull(value);
        File pdfFile = companion.getPdfFile(requireContext, value);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this$0.requireContext(), BuildConfig.FILE_PROVIDER, pdfFile));
        intent.setType("application/pdf");
        intent.addFlags(1);
        this$0.startActivity(Intent.createChooser(intent, this$0.getString(R.string.share_zip_via)));
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$13(ElectionDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMainViewModel().getAuthRequestUseCase().isLoading().postValue(true);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new ElectionDetailFragment$setupBinding$1$1$3$1(this$0, null), 2, null);
    }

    public static final void setupBinding$lambda$16$lambda$15$lambda$14(ElectionDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.trySubmitOnline();
    }

    public final void trySubmitOnline() {
        if (!getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ElectionDetailFragment$trySubmitOnline$3(this, null), 3, null);
            return;
        }
        AuthRequestUseCase authRequestUseCase = getMainViewModel().getAuthRequestUseCase();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$trySubmitOnline$1
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
            /* compiled from: ElectionDetailFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$trySubmitOnline$1$1", f = "ElectionDetailFragment.kt", i = {}, l = {303}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$trySubmitOnline$1$1  reason: invalid class name */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ ElectionDetailFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(ElectionDetailFragment electionDetailFragment, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = electionDetailFragment;
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
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(ElectionDetailFragment.this, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$trySubmitOnline$2
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
                Toast.makeText(ElectionDetailFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                try {
                    mainViewModel = ElectionDetailFragment.this.getMainViewModel();
                    AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                    Context requireContext2 = ElectionDetailFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    activityResultLauncher = ElectionDetailFragment.this.authRequestLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                        activityResultLauncher = null;
                    }
                    authRequestUseCase2.start(requireContext2, activityResultLauncher);
                } catch (ActivityNotFoundException e) {
                    FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                    ElectionDetailFragment electionDetailFragment = ElectionDetailFragment.this;
                    String string = electionDetailFragment.getString(R.string.key_setup_error_browser_not_found);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                    BaseFragment.showSnackBar$default(electionDetailFragment, string, null, null, 6, null);
                }
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:3|(4:5|6|7|(1:(1:(3:11|12|13)(2:15|16))(5:17|18|19|20|(5:22|(1:24)|25|26|27)(6:28|(1:30)|31|(1:33)|12|13)))(5:35|(1:47)(1:39)|(5:41|42|(3:44|(1:46)|19)|20|(0)(0))|26|27)))|50|6|7|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0048, code lost:
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00be, code lost:
        com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance().recordException(r12);
        r2 = r2 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0110  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x00bb -> B:103:0x00c9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object trySubmit(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment.trySubmit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setupLauncher() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ElectionDetailFragment.setupLauncher$lambda$17(ElectionDetailFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.requestPermissionsLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ElectionDetailFragment.setupLauncher$lambda$18((ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…rtActivityForResult()) {}");
        this.openAppSettingsLauncher = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ElectionDetailFragment.setupLauncher$lambda$19(ElectionDetailFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          }\n            }");
        this.cropImageLauncher = registerForActivityResult3;
    }

    public static final void setupLauncher$lambda$17(ElectionDetailFragment this$0, Boolean permissionGranted) {
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
        this$0.showSnackBar(string, string2, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupLauncher$1$1
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
                activityResultLauncher = ElectionDetailFragment.this.openAppSettingsLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("openAppSettingsLauncher");
                    activityResultLauncher = null;
                }
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", ElectionDetailFragment.this.requireContext().getPackageName(), null));
                activityResultLauncher.launch(intent);
            }
        });
    }

    public static final void setupLauncher$lambda$19(ElectionDetailFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer valueOf = activityResult != null ? Integer.valueOf(activityResult.getResultCode()) : null;
        if (valueOf != null && valueOf.intValue() == -1) {
            final Bitmap bitmap = ImageCropConstants.croppedImageBitmap;
            CaptureImageUseCase captureImageUseCase = this$0.getElectionDetailViewModel().getCaptureImageUseCase();
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            captureImageUseCase.saveTempCroppedPhoto(bitmap, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupLauncher$3$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    ElectionDetailViewModel electionDetailViewModel;
                    electionDetailViewModel = ElectionDetailFragment.this.getElectionDetailViewModel();
                    Bitmap bitmap2 = bitmap;
                    Intrinsics.checkNotNullExpressionValue(bitmap2, "bitmap");
                    electionDetailViewModel.detectAprilTag(bitmap2);
                }
            });
        } else if (valueOf != null && valueOf.intValue() == 0) {
            this$0.getElectionDetailViewModel().getCaptureImageUseCase().deleteOriginalPhoto();
        }
    }

    private final void setupViewModel() {
        final ElectionDetailViewModel electionDetailViewModel = getElectionDetailViewModel();
        GetElectionUseCase getElectionUseCase = electionDetailViewModel.getGetElectionUseCase();
        getElectionUseCase.getElectionResource().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends ElectionWithRelation> resource) {
                invoke2((Resource<ElectionWithRelation>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(final Resource<ElectionWithRelation> resource) {
                ElectionDetailViewModel electionDetailViewModel2;
                FragmentElectionDetailBinding fragmentElectionDetailBinding;
                ElectionPageListAdapter electionPageListAdapter;
                if (resource != null) {
                    final ElectionDetailFragment electionDetailFragment = ElectionDetailFragment.this;
                    ElectionDetailViewModel electionDetailViewModel3 = electionDetailViewModel;
                    if (resource.getSuccess() != ResourceStatus.SUCCESS || resource.getPayload() == null) {
                        return;
                    }
                    Election election = resource.getPayload().getElection();
                    electionDetailViewModel2 = electionDetailFragment.getElectionDetailViewModel();
                    LifecycleOwner viewLifecycleOwner = electionDetailFragment.getViewLifecycleOwner();
                    Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                    electionDetailFragment.adapter = new ElectionPageListAdapter(election, electionDetailViewModel2, viewLifecycleOwner, new Function1<ElectionPage, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$1$1$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ElectionPage electionPage) {
                            invoke2(electionPage);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void invoke2(org.informatika.sirekap.model.ElectionPage r8) {
                            /*
                                Method dump skipped, instructions count: 376
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$1$1$1.invoke2(org.informatika.sirekap.model.ElectionPage):void");
                        }
                    }, new ElectionDetailFragment$setupViewModel$1$1$1$1$2(electionDetailFragment));
                    fragmentElectionDetailBinding = electionDetailFragment.binding;
                    ElectionPageListAdapter electionPageListAdapter2 = null;
                    if (fragmentElectionDetailBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentElectionDetailBinding = null;
                    }
                    RecyclerView recyclerView = fragmentElectionDetailBinding.recyclerViewElectionPages;
                    electionPageListAdapter = electionDetailFragment.adapter;
                    if (electionPageListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        electionPageListAdapter2 = electionPageListAdapter;
                    }
                    recyclerView.setAdapter(electionPageListAdapter2);
                    electionDetailFragment.submitListAdapter(Intrinsics.areEqual((Object) electionDetailViewModel3.getHideCompletedPages().getValue(), (Object) true), resource.getPayload().getPages());
                }
            }
        }));
        getElectionUseCase.getFormC1AdministrationComplete().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1AdministrationComplete, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$2
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(FormC1AdministrationComplete formC1AdministrationComplete) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                invoke2(formC1AdministrationComplete);
                return Unit.INSTANCE;
            }
        }));
        getElectionUseCase.getFormC1TabulationCompletesSectionIV().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends FormC1TabulationComplete>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$3
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<FormC1TabulationComplete> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends FormC1TabulationComplete> list) {
                invoke2((List<FormC1TabulationComplete>) list);
                return Unit.INSTANCE;
            }
        }));
        getElectionUseCase.getFormC1TabulationPartaiCompletesSectionIV().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends FormC1TabulationPartaiComplete>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$4
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<FormC1TabulationPartaiComplete> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends FormC1TabulationPartaiComplete> list) {
                invoke2((List<FormC1TabulationPartaiComplete>) list);
                return Unit.INSTANCE;
            }
        }));
        getElectionUseCase.getFormC1TabulationCompleteSectionV().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1AdministrationHal2Complete, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$5
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                invoke2(formC1AdministrationHal2Complete);
                return Unit.INSTANCE;
            }
        }));
        getElectionUseCase.getCandidates().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Candidate>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$6
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Candidate> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Candidate> list) {
                invoke2((List<Candidate>) list);
                return Unit.INSTANCE;
            }
        }));
        electionDetailViewModel.getGetTpsTimeUseCasePenghitunganSuara().isDataExist().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$2$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
        electionDetailViewModel.getGetTpsTimeUseCasePemungutanSuara().isDataExist().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$3$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
        electionDetailViewModel.getHideCompletedPages().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                ElectionDetailFragmentArgs args;
                if (bool != null) {
                    ElectionDetailViewModel electionDetailViewModel2 = ElectionDetailViewModel.this;
                    ElectionDetailFragment electionDetailFragment = this;
                    boolean booleanValue = bool.booleanValue();
                    ElectionWithRelation value = electionDetailViewModel2.getGetElectionUseCase().getElectionWithRelation().getValue();
                    List<ElectionPage> pages = value != null ? value.getPages() : null;
                    if (pages != null) {
                        electionDetailFragment.submitListAdapter(booleanValue, pages);
                        args = electionDetailFragment.getArgs();
                        electionDetailViewModel2.updateHideCompletedPages(booleanValue, args.getElectionId());
                    }
                }
            }
        }));
        electionDetailViewModel.getElections().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$5
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
                    ElectionDetailViewModel.this.getAprilTagCheckUseCase().getElections().postValue(list);
                }
            }
        }));
        CaptureImageUseCase captureImageUseCase = electionDetailViewModel.getCaptureImageUseCase();
        captureImageUseCase.getPhotoResource().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$6$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends ElectionWithRelation> resource) {
                invoke2((Resource<ElectionWithRelation>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<ElectionWithRelation> resource) {
                ElectionDetailViewModel electionDetailViewModel2;
                if (resource != null) {
                    ElectionDetailFragment electionDetailFragment = ElectionDetailFragment.this;
                    ElectionDetailViewModel electionDetailViewModel3 = electionDetailViewModel;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        electionDetailViewModel2 = electionDetailFragment.getElectionDetailViewModel();
                        if (electionDetailViewModel2.getSaveCorrectedImage()) {
                            String value = electionDetailViewModel3.getKodeTps().getValue();
                            Election value2 = electionDetailViewModel3.getConfirmedElection().getValue();
                            Bitmap bitmap = ImageCropConstants.correctedImageBitmap;
                            ElectionPage value3 = electionDetailViewModel3.getConfirmedPage().getValue();
                            if (value3 != null && value != null && value2 != null && bitmap != null) {
                                electionDetailViewModel3.getCaptureImageUseCase().saveCorrectedPhoto(bitmap, value2.getPemilihan(), value, value3, electionDetailViewModel3.getAprilTagCode());
                            } else {
                                String string = electionDetailFragment.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                                Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                                BaseFragment.showSnackBar$default(electionDetailFragment, string, null, null, 6, null);
                                electionDetailViewModel3.getCaptureImageUseCase().deletePreparedPhoto();
                            }
                        } else {
                            CaptureImageUseCase.ElectionPagePhotoModel value4 = electionDetailViewModel3.getCaptureImageUseCase().getPhotoModel().getValue();
                            if (value4 != null) {
                                androidx.navigation.fragment.FragmentKt.findNavController(electionDetailFragment).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToSendImageFragment(value4.getElectionPageId()));
                            }
                        }
                        electionDetailViewModel3.getCaptureImageUseCase().finishTakingPhoto();
                        electionDetailViewModel3.finishSavingImage();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(electionDetailFragment, error, null, null, 6, null);
                        }
                        electionDetailViewModel3.getCaptureImageUseCase().finishTakingPhoto();
                        electionDetailViewModel3.finishSavingImage();
                    }
                }
            }
        }));
        captureImageUseCase.getPerspectiveCorrectionResource().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$6$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends ElectionWithRelation> resource) {
                invoke2((Resource<ElectionWithRelation>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<ElectionWithRelation> resource) {
                if (resource != null) {
                    ElectionDetailViewModel electionDetailViewModel2 = ElectionDetailViewModel.this;
                    ElectionDetailFragment electionDetailFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        SendImageViewModel.ElectionPagePerspectiveCorrectionModel value = electionDetailViewModel2.getCaptureImageUseCase().getPerspectiveCorrectionModel().getValue();
                        if (value != null) {
                            androidx.navigation.fragment.FragmentKt.findNavController(electionDetailFragment).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToSendImageFragment(value.getElectionPageId()));
                        }
                        electionDetailViewModel2.getCaptureImageUseCase().finishPerspectiveCorrection();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(electionDetailFragment, error, null, null, 6, null);
                        }
                        electionDetailViewModel2.getCaptureImageUseCase().finishPerspectiveCorrection();
                    }
                }
            }
        }));
        electionDetailViewModel.getWitnesses().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends WitnessWithShare>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$7
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<WitnessWithShare> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends WitnessWithShare> list) {
                invoke2((List<WitnessWithShare>) list);
                return Unit.INSTANCE;
            }
        }));
        electionDetailViewModel.getAprilTagCheckUseCase().getDetectionResult().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<AprilTagCheckUseCase.DetectResult, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$8$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AprilTagCheckUseCase.DetectResult detectResult) {
                invoke2(detectResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(AprilTagCheckUseCase.DetectResult detectResult) {
                if (detectResult != null) {
                    if (detectResult.isSuccess() && detectResult.getPemilihan() != null && detectResult.getNomorHalaman() != null && detectResult.getAprilTagCode() != null) {
                        ElectionDetailViewModel.this.setAprilTagCode(detectResult.getAprilTagCode());
                        ElectionDetailFragment.matchAprilTag$default(this, detectResult.getPemilihan(), detectResult.getNomorHalaman().intValue(), false, 4, null);
                    } else {
                        String croppedPhotoAbsolutePath = ElectionDetailViewModel.this.getCaptureImageUseCase().getCroppedPhotoAbsolutePath();
                        ElectionPage value = ElectionDetailViewModel.this.getManualCaptureElectionPage().getValue();
                        Election value2 = ElectionDetailViewModel.this.getGetElectionUseCase().getElection().getValue();
                        if (croppedPhotoAbsolutePath != null && value != null) {
                            androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToConfirmSaveFormCImageFragment(croppedPhotoAbsolutePath, value.getId()));
                            ElectionDetailViewModel.this.getConfirmedElection().postValue(value2);
                            ElectionDetailViewModel.this.getConfirmedPage().postValue(value);
                        } else {
                            ElectionDetailFragment electionDetailFragment = this;
                            String string = electionDetailFragment.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                            BaseFragment.showSnackBar$default(electionDetailFragment, string, null, null, 6, null);
                            ElectionDetailViewModel.this.getCaptureImageUseCase().deletePreparedPhoto();
                        }
                    }
                    ElectionDetailViewModel.this.finishDetectingTag();
                }
            }
        }));
        electionDetailViewModel.getBackgroundProcessZipElection().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<BackgroundProcess, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$9
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
                    ElectionDetailFragment electionDetailFragment = ElectionDetailFragment.this;
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
                        Toast.makeText(electionDetailFragment.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                        try {
                            mainViewModel = electionDetailFragment.getMainViewModel();
                            AuthRequestUseCase authRequestUseCase = mainViewModel.getAuthRequestUseCase();
                            Context requireContext = electionDetailFragment.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            activityResultLauncher = electionDetailFragment.pdfGenerationRequestLauncher;
                            if (activityResultLauncher == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("pdfGenerationRequestLauncher");
                            } else {
                                activityResultLauncher2 = activityResultLauncher;
                            }
                            authRequestUseCase.start(requireContext, activityResultLauncher2);
                            return;
                        } catch (ActivityNotFoundException e) {
                            FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                            String string = electionDetailFragment.getString(R.string.key_setup_error_browser_not_found);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                            BaseFragment.showSnackBar$default(electionDetailFragment, string, null, null, 6, null);
                            return;
                        }
                    }
                    BaseFragment.showSnackBar$default(electionDetailFragment, "Error: " + backgroundProcess.getErrorMessage(), null, null, 6, null);
                }
            }
        }));
        MainViewModel mainViewModel = getMainViewModel();
        mainViewModel.getGetLoggedInUserUseCase().getLoggedInUserResource().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends User>, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends User> resource) {
                invoke2((Resource<User>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<User> resource) {
                MainViewModel mainViewModel2;
                ElectionDetailViewModel electionDetailViewModel2;
                ElectionDetailFragmentArgs args;
                ElectionDetailFragmentArgs args2;
                if (resource != null) {
                    ElectionDetailFragment electionDetailFragment = ElectionDetailFragment.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (resource.getPayload() != null) {
                            electionDetailViewModel2 = electionDetailFragment.getElectionDetailViewModel();
                            String kodeTps = resource.getPayload().getTps().getKodeTps();
                            args = electionDetailFragment.getArgs();
                            String electionId = args.getElectionId();
                            args2 = electionDetailFragment.getArgs();
                            electionDetailViewModel2.setup(kodeTps, electionId, args2.getJenisPemilihan());
                            return;
                        }
                        mainViewModel2 = electionDetailFragment.getMainViewModel();
                        mainViewModel2.logout();
                    }
                }
            }
        }));
        mainViewModel.getGetLoggedInUserUseCase().getLoggedInUser().observe(getViewLifecycleOwner(), new ElectionDetailFragment$sam$androidx_lifecycle_Observer$0(new Function1<User, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$2$2
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

    public final void submitListAdapter(boolean z, List<ElectionPage> list) {
        ElectionPageListAdapter electionPageListAdapter = null;
        if (z) {
            ElectionPageListAdapter electionPageListAdapter2 = this.adapter;
            if (electionPageListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                electionPageListAdapter = electionPageListAdapter2;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (!((ElectionPage) obj).isDone()) {
                    arrayList.add(obj);
                }
            }
            electionPageListAdapter.submitList(arrayList);
            return;
        }
        ElectionPageListAdapter electionPageListAdapter3 = this.adapter;
        if (electionPageListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            electionPageListAdapter = electionPageListAdapter3;
        }
        electionPageListAdapter.submitList(list);
    }

    private final void setupFragmentListener() {
        ElectionDetailFragment electionDetailFragment = this;
        FragmentKt.setFragmentResultListener(electionDetailFragment, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$1
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
                boolean z = bundle.getBoolean(SendImageFragment.SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS, false);
                boolean z2 = bundle.getBoolean(SendImageFragment.SEND_IMAGE_RESULT_TYPE_DELETE_SUCCESS, false);
                boolean z3 = bundle.getBoolean(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO, false);
                if (z) {
                    String imageDescription = bundle.getString(SendImageFragment.SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS_IMAGE_DESC, "");
                    Intrinsics.checkNotNullExpressionValue(imageDescription, "imageDescription");
                    if (imageDescription.length() > 0) {
                        ElectionDetailFragment electionDetailFragment2 = ElectionDetailFragment.this;
                        String string = electionDetailFragment2.getString(R.string.event_upload_image_started, imageDescription);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.event…tarted, imageDescription)");
                        BaseFragment.showSnackBar$default(electionDetailFragment2, string, null, null, 6, null);
                    }
                } else if (z2) {
                    BaseFragment.showSnackBar$default(ElectionDetailFragment.this, "Berhasil menghapus gambar", null, null, 6, null);
                } else if (z3) {
                    ElectionPage electionPage = (ElectionPage) new Gson().fromJson(bundle.getString(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE, null), (Class<Object>) ElectionPage.class);
                    ElectionDetailFragment electionDetailFragment3 = ElectionDetailFragment.this;
                    Intrinsics.checkNotNullExpressionValue(electionPage, "electionPage");
                    electionDetailFragment3.requestPermissionTakePhoto(electionPage);
                }
            }
        });
        FragmentKt.setFragmentResultListener(electionDetailFragment, AutoCaptureFragment.AUTOCAPTURE_CAMERA_OR_NOTHING, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$2
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
                ElectionDetailViewModel electionDetailViewModel;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                if (bundle.getBoolean(AutoCaptureFragment.CAMERA_PERMISSION_DENIED, false)) {
                    ElectionDetailFragment electionDetailFragment2 = ElectionDetailFragment.this;
                    String string = electionDetailFragment2.getString(R.string.message_camera_permissions_denied, ElectionDetailFragment.this.getString(R.string.app_name_real));
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …al)\n                    )");
                    String string2 = ElectionDetailFragment.this.getString(R.string.action_allow);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.action_allow)");
                    final ElectionDetailFragment electionDetailFragment3 = ElectionDetailFragment.this;
                    electionDetailFragment2.showSnackBar(string, string2, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$2.1
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
                            activityResultLauncher = ElectionDetailFragment.this.openAppSettingsLauncher;
                            if (activityResultLauncher == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("openAppSettingsLauncher");
                                activityResultLauncher = null;
                            }
                            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", ElectionDetailFragment.this.requireContext().getPackageName(), null));
                            activityResultLauncher.launch(intent);
                        }
                    });
                }
                electionDetailViewModel = ElectionDetailFragment.this.getElectionDetailViewModel();
                electionDetailViewModel.getCaptureImageUseCase().deletePreparedPhoto();
            }
        });
        FragmentKt.setFragmentResultListener(electionDetailFragment, AutoCaptureFragment.AUTO_CAPTURE, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$3
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
                ElectionDetailViewModel electionDetailViewModel;
                ActivityResultLauncher activityResultLauncher;
                ElectionDetailViewModel electionDetailViewModel2;
                ElectionDetailViewModel electionDetailViewModel3;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                boolean z = bundle.getBoolean(AutoCaptureFragment.AUTO_CAPTURE_RESULT, false);
                ActivityResultLauncher activityResultLauncher2 = null;
                String string = bundle.getString(AutoCaptureFragment.PEMILIHAN_RESULT, null);
                int i = bundle.getInt(AutoCaptureFragment.PAGE_NUM_RESULT, -1);
                int i2 = bundle.getInt(AutoCaptureFragment.APRIL_TAG_CODE, -1);
                if (z && string != null && i != -1) {
                    electionDetailViewModel2 = ElectionDetailFragment.this.getElectionDetailViewModel();
                    electionDetailViewModel2.setSaveCorrectedImage(true);
                    electionDetailViewModel3 = ElectionDetailFragment.this.getElectionDetailViewModel();
                    electionDetailViewModel3.setAprilTagCode(Integer.valueOf(i2));
                    ElectionDetailFragment.this.matchAprilTag(string, i, true);
                    return;
                }
                String string2 = bundle.getString(AutoCaptureFragment.AUTO_CAPTURE_ERROR, "Terjadi Kesalahan");
                if (Intrinsics.areEqual(string2, AutoCaptureFragment.DETECTION_ERROR)) {
                    activityResultLauncher = ElectionDetailFragment.this.cropImageLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
                    } else {
                        activityResultLauncher2 = activityResultLauncher;
                    }
                    activityResultLauncher2.launch(new Intent(ElectionDetailFragment.this.getContext(), ImageCropActivity.class));
                    return;
                }
                ElectionDetailFragment electionDetailFragment2 = ElectionDetailFragment.this;
                String string3 = electionDetailFragment2.getString(R.string.error_capture_image, string2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.error…ture_image, errorMessage)");
                BaseFragment.showSnackBar$default(electionDetailFragment2, string3, null, null, 6, null);
                electionDetailViewModel = ElectionDetailFragment.this.getElectionDetailViewModel();
                electionDetailViewModel.getCaptureImageUseCase().deletePreparedPhoto();
            }
        });
        FragmentKt.setFragmentResultListener(electionDetailFragment, ConfirmSaveFormCImageFragment.CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$4
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
                final ElectionDetailViewModel electionDetailViewModel;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                boolean z = bundle.getBoolean("retake_photo");
                boolean z2 = bundle.getBoolean("save_photo");
                if (z) {
                    ElectionDetailFragment.this.tryTakePhoto();
                } else if (z2) {
                    electionDetailViewModel = ElectionDetailFragment.this.getElectionDetailViewModel();
                    final ElectionDetailFragment electionDetailFragment2 = ElectionDetailFragment.this;
                    final ElectionPage value = electionDetailViewModel.getConfirmedPage().getValue();
                    final String value2 = electionDetailViewModel.getKodeTps().getValue();
                    final Election value3 = electionDetailViewModel.getConfirmedElection().getValue();
                    final Bitmap bitmap = ImageCropConstants.croppedImageBitmap;
                    if (value != null && value2 != null && value3 != null && bitmap != null) {
                        FragmentActivity requireActivity = electionDetailFragment2.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        electionDetailViewModel.savePhoto(requireActivity, value2, value3.getPemilihan(), new Function1<Bitmap, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$4$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Bitmap bitmap2) {
                                invoke2(bitmap2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2(Bitmap it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                CaptureImageUseCase captureImageUseCase = ElectionDetailViewModel.this.getCaptureImageUseCase();
                                Bitmap croppedBitmap = bitmap;
                                Intrinsics.checkNotNullExpressionValue(croppedBitmap, "croppedBitmap");
                                captureImageUseCase.saveCroppedPhoto(croppedBitmap, value3.getPemilihan(), value2, value, ElectionDetailViewModel.this.getAprilTagCode());
                            }
                        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$4$1$2
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
                                ElectionDetailFragment electionDetailFragment3 = ElectionDetailFragment.this;
                                String string = electionDetailFragment3.getString(R.string.error_capture_image, exception.getMessage());
                                Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                                BaseFragment.showSnackBar$default(electionDetailFragment3, string, null, null, 6, null);
                            }
                        });
                        return;
                    }
                    String string = electionDetailFragment2.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(electionDetailFragment2, string, null, null, 6, null);
                    electionDetailViewModel.getCaptureImageUseCase().deletePreparedPhoto();
                }
            }
        });
        FragmentKt.setFragmentResultListener(electionDetailFragment, AprilTagConflictFragment.APRIL_TAG_CONFLICT_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupFragmentListener$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String str, Bundle bundle) {
                ElectionDetailViewModel electionDetailViewModel;
                ElectionWithRelation electionWithRelation;
                List<ElectionPage> pages;
                Object obj;
                boolean z;
                ElectionDetailViewModel electionDetailViewModel2;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                boolean z2 = bundle.getBoolean("retake_photo");
                boolean z3 = bundle.getBoolean("save_photo");
                if (z2) {
                    electionDetailViewModel2 = ElectionDetailFragment.this.getElectionDetailViewModel();
                    electionDetailViewModel2.getCaptureImageUseCase().deletePreparedPhoto();
                    ElectionDetailFragment.this.tryTakePhoto();
                } else if (z3) {
                    String string = bundle.getString("election_page_id");
                    electionDetailViewModel = ElectionDetailFragment.this.getElectionDetailViewModel();
                    ElectionDetailFragment electionDetailFragment2 = ElectionDetailFragment.this;
                    String croppedPhotoAbsolutePath = electionDetailViewModel.getCaptureImageUseCase().getCroppedPhotoAbsolutePath();
                    List<ElectionWithRelation> value = electionDetailViewModel.getElections().getValue();
                    ElectionPage electionPage = null;
                    if (value != null) {
                        Intrinsics.checkNotNullExpressionValue(value, "value");
                        Iterator<T> it = value.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            List<ElectionPage> pages2 = ((ElectionWithRelation) obj).getPages();
                            if (!(pages2 instanceof Collection) || !pages2.isEmpty()) {
                                for (ElectionPage electionPage2 : pages2) {
                                    if (Intrinsics.areEqual(electionPage2.getId(), string)) {
                                        z = true;
                                        continue;
                                        break;
                                    }
                                }
                            }
                            z = false;
                            continue;
                            if (z) {
                                break;
                            }
                        }
                        electionWithRelation = (ElectionWithRelation) obj;
                    } else {
                        electionWithRelation = null;
                    }
                    if (electionWithRelation != null && (pages = electionWithRelation.getPages()) != null) {
                        Iterator<T> it2 = pages.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Object next = it2.next();
                            if (Intrinsics.areEqual(((ElectionPage) next).getId(), string)) {
                                electionPage = next;
                                break;
                            }
                        }
                        electionPage = electionPage;
                    }
                    if (croppedPhotoAbsolutePath != null && electionPage != null) {
                        androidx.navigation.fragment.FragmentKt.findNavController(electionDetailFragment2).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToConfirmSaveFormCImageFragment(croppedPhotoAbsolutePath, electionPage.getId()));
                        electionDetailViewModel.getConfirmedElection().postValue(electionWithRelation.getElection());
                        electionDetailViewModel.getConfirmedPage().postValue(electionPage);
                        return;
                    }
                    FirebaseCrashlytics.getInstance().recordException(new Exception("ElectionDetailFragment Page ID Kosong"));
                    String string2 = electionDetailFragment2.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(electionDetailFragment2, string2, null, null, 6, null);
                    electionDetailViewModel.getCaptureImageUseCase().deletePreparedPhoto();
                }
            }
        });
    }

    public final void requestPermissionTakePhoto(ElectionPage electionPage) {
        getElectionDetailViewModel().setManualCaptureElectionPage(electionPage);
        ActivityResultLauncher<String> activityResultLauncher = this.requestPermissionsLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestPermissionsLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch("android.permission.CAMERA");
    }

    public final void tryTakePhoto() {
        FirebaseCrashlytics.getInstance().log("ElectionDetailFragment User clicks 'Photo' button");
        String value = getElectionDetailViewModel().getKodeTps().getValue();
        if (value != null) {
            CaptureImageUseCase captureImageUseCase = getElectionDetailViewModel().getCaptureImageUseCase();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            captureImageUseCase.prepareTakingPhoto(requireContext, value, new Function3<Uri, Uri, Uri, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$tryTakePhoto$1
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
                public final void invoke2(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
                    Intrinsics.checkNotNullParameter(imageUri, "imageUri");
                    Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
                    Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
                    androidx.navigation.fragment.FragmentKt.findNavController(ElectionDetailFragment.this).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToAutoCaptureFragment(imageUri, croppedImageUri, correctedImageUri));
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$tryTakePhoto$2
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
            return;
        }
        FirebaseCrashlytics.getInstance().recordException(new Exception("ElectionDetailFragment Kode TPS kosong"));
        BaseFragment.showSnackBar$default(this, "Kode TPS kosong", null, null, 6, null);
    }

    public static /* synthetic */ void matchAprilTag$default(ElectionDetailFragment electionDetailFragment, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        electionDetailFragment.matchAprilTag(str, i, z);
    }

    public final void matchAprilTag(String str, int i, boolean z) {
        ElectionWithRelation electionWithRelation;
        int i2;
        boolean z2;
        Object obj;
        ElectionDetailViewModel electionDetailViewModel = getElectionDetailViewModel();
        Election value = electionDetailViewModel.getGetElectionUseCase().getElection().getValue();
        ElectionPage value2 = electionDetailViewModel.getManualCaptureElectionPage().getValue();
        List<ElectionWithRelation> value3 = electionDetailViewModel.getElections().getValue();
        Object obj2 = null;
        if (value3 != null) {
            Intrinsics.checkNotNullExpressionValue(value3, "value");
            Iterator<T> it = value3.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), str)) {
                    break;
                }
            }
            electionWithRelation = (ElectionWithRelation) obj;
        } else {
            electionWithRelation = null;
        }
        String croppedPhotoAbsolutePath = electionDetailViewModel.getCaptureImageUseCase().getCroppedPhotoAbsolutePath();
        if (value == null || electionWithRelation == null || croppedPhotoAbsolutePath == null) {
            FirebaseCrashlytics.getInstance().recordException(new Exception("ElectionDetailFragment Election kosong"));
            String string = getString(R.string.error_capture_image, "Gagal Memproses Gambar");
            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …ar\"\n                    )");
            BaseFragment.showSnackBar$default(this, string, null, null, 6, null);
            electionDetailViewModel.getCaptureImageUseCase().deletePreparedPhoto();
            return;
        }
        if (z) {
            i2 = i == 99 ? electionWithRelation.getElection().getJmlLembar() : i;
            if (i == 25 && !electionWithRelation.getElection().isAceh() && (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) || Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPRD_KABKO))) {
                i2 = i - 6;
            }
        } else {
            i2 = i;
        }
        Iterator<T> it2 = electionWithRelation.getPages().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((ElectionPage) next).getPageNumber(electionWithRelation.getElection().getJmlLembar()) == i2) {
                z2 = true;
                continue;
            } else {
                z2 = false;
                continue;
            }
            if (z2) {
                obj2 = next;
                break;
            }
        }
        ElectionPage electionPage = (ElectionPage) obj2;
        if (value2 != null && electionPage != null) {
            if (!Intrinsics.areEqual(value2.getId(), electionPage.getId()) || !Intrinsics.areEqual(value.getPemilihan(), electionWithRelation.getElection().getPemilihan())) {
                androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToAprilTagConflictFragment(croppedPhotoAbsolutePath, value2.getId(), electionPage.getId()));
                return;
            }
            androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(ElectionDetailFragmentDirections.Companion.actionElectionDetailFragmentToConfirmSaveFormCImageFragment(croppedPhotoAbsolutePath, value2.getId()));
            electionDetailViewModel.getConfirmedElection().postValue(value);
            electionDetailViewModel.getConfirmedPage().postValue(value2);
            return;
        }
        FirebaseCrashlytics.getInstance().recordException(new Exception("ElectionDetailFragment ElectionPage kosong"));
        String string2 = getString(R.string.error_capture_image, "Gagal Memproses Gambar");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …                        )");
        BaseFragment.showSnackBar$default(this, string2, null, null, 6, null);
        electionDetailViewModel.getCaptureImageUseCase().deletePreparedPhoto();
    }

    private final void confirmCreatePdf() {
        String string;
        List<WitnessWithShare> value = getElectionDetailViewModel().getWitnesses().getValue();
        if (value == null || value.isEmpty()) {
            string = getString(R.string.create_pdf_dialog_message_empty_witness);
        } else {
            string = getString(R.string.create_pdf_dialog_message);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (witnesses.isNullOrEm…dialog_message)\n        }");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setTitle((CharSequence) getString(R.string.create_pdf_dialog_title));
        materialAlertDialogBuilder.setMessage((CharSequence) HtmlCompat.fromHtml(string, 63));
        materialAlertDialogBuilder.setPositiveButton((CharSequence) getString(R.string.action_yes), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda16
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ElectionDetailFragment.confirmCreatePdf$lambda$33$lambda$31(ElectionDetailFragment.this, dialogInterface, i);
            }
        });
        materialAlertDialogBuilder.setNegativeButton((CharSequence) getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ElectionDetailFragment.confirmCreatePdf$lambda$33$lambda$32(dialogInterface, i);
            }
        });
        materialAlertDialogBuilder.show();
    }

    public static final void confirmCreatePdf$lambda$33$lambda$31(ElectionDetailFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.tryCreatePdf();
    }

    private final void tryCreatePdf() {
        DialogVerifyBodyBinding inflate = DialogVerifyBodyBinding.inflate(getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        inflate.setLifecycleOwner(getViewLifecycleOwner());
        inflate.setViewModel(getElectionDetailViewModel());
        inflate.setHideCommentField(true);
        materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) getString(R.string.lock_election_dialog_message));
        final AlertDialog create = materialAlertDialogBuilder.create();
        Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…sage))\n        }.create()");
        create.show();
        inflate.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ElectionDetailFragment.tryCreatePdf$lambda$35(AlertDialog.this, this, view);
            }
        });
    }

    public static final void tryCreatePdf$lambda$35(AlertDialog dialog, ElectionDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        ElectionDetailViewModel electionDetailViewModel = this$0.getElectionDetailViewModel();
        ElectionDetailFragment electionDetailFragment = this$0;
        ActivityResultLauncher<Intent> activityResultLauncher = this$0.localAuthLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
            activityResultLauncher = null;
        }
        electionDetailViewModel.createPdf(electionDetailFragment, activityResultLauncher);
    }

    /* compiled from: ElectionDetailFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailFragment$Companion;", "", "()V", ElectionDetailFragment.ELECTION_DETAIL_FRAGMENT_RESULT, "", ElectionDetailFragment.RESULT_KEY_ELECTION_ID, ElectionDetailFragment.RESULT_KEY_JENIS_PEMILIHAN, ElectionDetailFragment.RESULT_KEY_JENIS_WAKTU, "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
