package org.informatika.sirekap.ui.dashboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
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
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentDashboardBinding;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.security.DeviceSecurityFacade;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.autocapture.AutoCaptureFragment;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment;
import org.informatika.sirekap.ui.dashboard.AprilTagCheckUseCase;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment;
import org.informatika.sirekap.ui.imageCrop.ImageCropActivity;
import org.informatika.sirekap.ui.imageCrop.ImageCropConstants;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment;
import org.informatika.sirekap.ui.sendImage.SendImageFragment;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;
import org.informatika.sirekap.usecase.GetLoggedInUserUseCase;

/* compiled from: DashboardFragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0018H\u0016J\b\u0010#\u001a\u00020\u0018H\u0016J\u001a\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010&\u001a\u00020\u0018H\u0002J\b\u0010'\u001a\u00020\u0018H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020\u0018H\u0002J\b\u0010*\u001a\u00020\u0018H\u0002J\b\u0010+\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/dashboard/ElectionCardListAdapter;", "binding", "Lorg/informatika/sirekap/databinding/FragmentDashboardBinding;", "cropImageLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "dashboardViewModel", "Lorg/informatika/sirekap/ui/dashboard/DashboardViewModel;", "getDashboardViewModel", "()Lorg/informatika/sirekap/ui/dashboard/DashboardViewModel;", "dashboardViewModel$delegate", "Lkotlin/Lazy;", "localAuthLauncher", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "openAppSettingsLauncher", "doLocalAuthentication", "", "failedAuthentication", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onViewCreated", "view", "setupBinding", "setupFragmentListener", "setupLauncher", "setupViewModel", "tryChangeTps", "tryTakePhoto", "Companion", "HorizontalMarginItemDecoration", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class DashboardFragment extends Hilt_DashboardFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DashboardFragment";
    private ElectionCardListAdapter adapter;
    private FragmentDashboardBinding binding;
    private ActivityResultLauncher<Intent> cropImageLauncher;
    private final Lazy dashboardViewModel$delegate;
    private ActivityResultLauncher<Intent> localAuthLauncher;
    private final Lazy mainViewModel$delegate;
    private ActivityResultLauncher<Intent> openAppSettingsLauncher;

    public static final void setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$13(DialogInterface dialogInterface, int i) {
    }

    public static final void setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$15(DialogInterface dialogInterface, int i) {
    }

    public static final void setupLauncher$lambda$28(ActivityResult activityResult) {
    }

    public DashboardFragment() {
        final DashboardFragment dashboardFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.dashboardViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(dashboardFragment, Reflection.getOrCreateKotlinClass(DashboardViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(dashboardFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = dashboardFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$special$$inlined$activityViewModels$default$3
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

    public final DashboardViewModel getDashboardViewModel() {
        return (DashboardViewModel) this.dashboardViewModel$delegate.getValue();
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setupFragmentListener();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new DashboardFragment$onResume$1(this, null), 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        DashboardFragment dashboardFragment = this;
        FragmentKt.clearFragmentResultListener(dashboardFragment, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT);
        FragmentKt.clearFragmentResultListener(dashboardFragment, AutoCaptureFragment.AUTOCAPTURE_CAMERA_OR_NOTHING);
        FragmentKt.clearFragmentResultListener(dashboardFragment, AutoCaptureFragment.AUTO_CAPTURE);
        FragmentKt.clearFragmentResultListener(dashboardFragment, SelectFormCImageFragment.SELECT_IMAGE_FRAGMENT_RESULT);
        FragmentKt.clearFragmentResultListener(dashboardFragment, ConfirmSaveFormCImageFragment.CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT);
        FragmentKt.clearFragmentResultListener(dashboardFragment, ElectionDetailFragment.ELECTION_DETAIL_FRAGMENT_RESULT);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDashboardBinding inflate = FragmentDashboardBinding.inflate(inflater, viewGroup, false);
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
        getMainViewModel().isNeedDoLocalAuthentication();
        getDashboardViewModel().updateInitializationStatus();
        getDashboardViewModel().updateVisitDashboard();
    }

    private final void doLocalAuthentication() {
        DeviceSecurityFacade deviceSecurityFacade = DeviceSecurityFacade.INSTANCE;
        DashboardFragment dashboardFragment = this;
        BiometricPrompt.AuthenticationCallback authenticationCallback = new BiometricPrompt.AuthenticationCallback() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$doLocalAuthentication$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                MainViewModel mainViewModel;
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                mainViewModel = DashboardFragment.this.getMainViewModel();
                mainViewModel.getLocalAuth().postValue(true);
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                DashboardFragment.this.failedAuthentication();
            }
        };
        ActivityResultLauncher<Intent> activityResultLauncher = this.localAuthLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
            activityResultLauncher = null;
        }
        deviceSecurityFacade.doLocalAuthentication(dashboardFragment, authenticationCallback, activityResultLauncher);
    }

    public final void failedAuthentication() {
        new AlertDialog.Builder(requireContext()).setCancelable(false).setTitle("Otentikasi Gagal").setMessage("Anda harus mengotentikasi diri untuk menggunakan aplikasi ini").setPositiveButton("Ulangi", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardFragment.failedAuthentication$lambda$0(DashboardFragment.this, dialogInterface, i);
            }
        }).setNegativeButton("Keluar", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardFragment.failedAuthentication$lambda$1(DashboardFragment.this, dialogInterface, i);
            }
        }).show();
    }

    public static final void failedAuthentication$lambda$0(DashboardFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doLocalAuthentication();
    }

    public static final void failedAuthentication$lambda$1(DashboardFragment this$0, DialogInterface dialogInterface, int i) {
        Unit unit;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new Exception("Activity is null");
        }
    }

    private final void setupViewModel() {
        GetLoggedInUserUseCase getLoggedInUserUseCase = getMainViewModel().getGetLoggedInUserUseCase();
        getLoggedInUserUseCase.getLoggedInUserResource().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends User>, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$1$1$1
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
                MainViewModel mainViewModel;
                FragmentDashboardBinding fragmentDashboardBinding;
                DashboardViewModel dashboardViewModel;
                if (resource != null) {
                    DashboardFragment dashboardFragment = DashboardFragment.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (resource.getPayload() != null) {
                            fragmentDashboardBinding = dashboardFragment.binding;
                            if (fragmentDashboardBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentDashboardBinding = null;
                            }
                            fragmentDashboardBinding.setUser(resource.getPayload());
                            dashboardViewModel = dashboardFragment.getDashboardViewModel();
                            dashboardViewModel.setup(resource.getPayload().getTps().getKodeTps());
                            return;
                        }
                        mainViewModel = dashboardFragment.getMainViewModel();
                        mainViewModel.logout();
                    }
                }
            }
        }));
        getLoggedInUserUseCase.getLoggedInUser().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<User, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$1$1$2
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(User user) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(User user) {
                invoke2(user);
                return Unit.INSTANCE;
            }
        }));
        final DashboardViewModel dashboardViewModel = getDashboardViewModel();
        dashboardViewModel.getElections().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                ElectionCardListAdapter electionCardListAdapter;
                FragmentDashboardBinding fragmentDashboardBinding;
                if (list != null) {
                    DashboardFragment dashboardFragment = DashboardFragment.this;
                    DashboardViewModel dashboardViewModel2 = dashboardViewModel;
                    electionCardListAdapter = dashboardFragment.adapter;
                    if (electionCardListAdapter != null) {
                        electionCardListAdapter.submitList(list);
                    }
                    if (dashboardViewModel2.getCurrentIndex() < list.size()) {
                        fragmentDashboardBinding = dashboardFragment.binding;
                        if (fragmentDashboardBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentDashboardBinding = null;
                        }
                        fragmentDashboardBinding.carouselElection.setCurrentItem(dashboardViewModel2.getCurrentIndex(), false);
                    }
                    dashboardViewModel2.getAprilTagCheckUseCase().getElections().postValue(list);
                }
            }
        }));
        final AprilTagCheckUseCase aprilTagCheckUseCase = dashboardViewModel.getAprilTagCheckUseCase();
        aprilTagCheckUseCase.getCheckResult().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<AprilTagCheckUseCase.CheckResult, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AprilTagCheckUseCase.CheckResult checkResult) {
                invoke2(checkResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(final AprilTagCheckUseCase.CheckResult checkResult) {
                if (checkResult != null) {
                    if (checkResult.isSuccess()) {
                        String value = DashboardViewModel.this.getKodeTps().getValue();
                        ElectionPage page = checkResult.getPage();
                        Election election = checkResult.getElection();
                        String croppedPhotoAbsolutePath = DashboardViewModel.this.getCaptureImageUseCase().getCroppedPhotoAbsolutePath();
                        if (value != null && page != null && croppedPhotoAbsolutePath != null && election != null) {
                            androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToConfirmSaveFormCImageFragment(croppedPhotoAbsolutePath, page.getId()));
                            DashboardViewModel.this.getConfirmedPage().postValue(page);
                            DashboardViewModel.this.getConfirmedElection().postValue(election);
                        } else {
                            FirebaseCrashlytics.getInstance().recordException(new Exception("DashboardFragment Terjadi Kesalahan"));
                            BaseFragment.showSnackBar$default(this, "Terjadi Kesalahan", null, null, 6, null);
                        }
                    } else {
                        String errorMessage = checkResult.getErrorMessage();
                        if (errorMessage != null) {
                            final DashboardFragment dashboardFragment = this;
                            if (checkResult.getErrorType() != null && checkResult.getElection() != null) {
                                BaseFragment.showSnackBar$default(dashboardFragment, errorMessage, null, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$2$1$1$1
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
                                        androidx.navigation.fragment.FragmentKt.findNavController(DashboardFragment.this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToElectionDetailFragment(checkResult.getElection().getId(), checkResult.getElection().getPemilihan()));
                                    }
                                }, 2, null);
                            } else {
                                BaseFragment.showSnackBar$default(dashboardFragment, errorMessage, null, null, 6, null);
                            }
                        }
                    }
                    aprilTagCheckUseCase.finishAprilTagCheck();
                }
            }
        }));
        aprilTagCheckUseCase.getDetectionResult().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<AprilTagCheckUseCase.DetectResult, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$2$2
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
                DashboardViewModel dashboardViewModel2;
                DashboardViewModel dashboardViewModel3;
                if (detectResult != null) {
                    if (!detectResult.isSuccess() || detectResult.getPemilihan() == null || detectResult.getNomorHalaman() == null || detectResult.getAprilTagCode() == null) {
                        dashboardViewModel2 = this.getDashboardViewModel();
                        String croppedPhotoAbsolutePath = dashboardViewModel2.getCaptureImageUseCase().getCroppedPhotoAbsolutePath();
                        if (croppedPhotoAbsolutePath != null) {
                            androidx.navigation.fragment.FragmentKt.findNavController(this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToSelectFormCImageFragment(croppedPhotoAbsolutePath));
                        } else {
                            DashboardFragment dashboardFragment = this;
                            String string = dashboardFragment.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                            BaseFragment.showSnackBar$default(dashboardFragment, string, null, null, 6, null);
                            dashboardViewModel3 = this.getDashboardViewModel();
                            dashboardViewModel3.getCaptureImageUseCase().deletePreparedPhoto();
                        }
                    } else {
                        DashboardViewModel.this.setAprilTagCode(detectResult.getAprilTagCode());
                        aprilTagCheckUseCase.check(detectResult.getPemilihan(), detectResult.getNomorHalaman().intValue());
                    }
                    DashboardViewModel.this.finishDetectingTag();
                }
            }
        }));
        dashboardViewModel.getCaptureImageUseCase().getCropPhotoResult().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<CaptureImageUseCase.CropPhotoResult, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$3
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
                    DashboardFragment dashboardFragment = DashboardFragment.this;
                    DashboardViewModel dashboardViewModel2 = dashboardViewModel;
                    if (!cropPhotoResult.isSuccess()) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(cropPhotoResult.getErrorMessage()));
                        String string = dashboardFragment.getString(R.string.error_capture_image, cropPhotoResult.getErrorMessage());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error…image, this.errorMessage)");
                        BaseFragment.showSnackBar$default(dashboardFragment, string, null, null, 6, null);
                    }
                    dashboardViewModel2.getCaptureImageUseCase().finishCrop();
                }
            }
        }));
        dashboardViewModel.getCaptureImageUseCase().getPhotoResource().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$4
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
                DashboardViewModel dashboardViewModel2;
                if (resource != null) {
                    DashboardFragment dashboardFragment = DashboardFragment.this;
                    DashboardViewModel dashboardViewModel3 = dashboardViewModel;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        dashboardViewModel2 = dashboardFragment.getDashboardViewModel();
                        if (dashboardViewModel2.getSaveCorrectedImage()) {
                            String value = dashboardViewModel3.getKodeTps().getValue();
                            Election value2 = dashboardViewModel3.getConfirmedElection().getValue();
                            Bitmap bitmap = ImageCropConstants.correctedImageBitmap;
                            ElectionPage value3 = dashboardViewModel3.getConfirmedPage().getValue();
                            if (value3 != null && value != null && value2 != null && bitmap != null) {
                                dashboardViewModel3.getCaptureImageUseCase().saveCorrectedPhoto(bitmap, value2.getPemilihan(), value, value3, dashboardViewModel3.getAprilTagCode());
                            } else {
                                String string = dashboardFragment.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                                Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                                BaseFragment.showSnackBar$default(dashboardFragment, string, null, null, 6, null);
                                dashboardViewModel3.getCaptureImageUseCase().deletePreparedPhoto();
                            }
                        } else {
                            CaptureImageUseCase.ElectionPagePhotoModel value4 = dashboardViewModel3.getCaptureImageUseCase().getPhotoModel().getValue();
                            if (value4 != null) {
                                androidx.navigation.fragment.FragmentKt.findNavController(dashboardFragment).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToSendImageFragment(value4.getElectionPageId()));
                            }
                        }
                        dashboardViewModel3.getCaptureImageUseCase().finishTakingPhoto();
                        dashboardViewModel3.finishSavingImage();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(dashboardFragment, error, null, null, 6, null);
                        }
                        dashboardViewModel3.getCaptureImageUseCase().finishTakingPhoto();
                        dashboardViewModel3.finishSavingImage();
                    }
                }
            }
        }));
        dashboardViewModel.getCaptureImageUseCase().getPerspectiveCorrectionResource().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$5
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
                    DashboardViewModel dashboardViewModel2 = DashboardViewModel.this;
                    DashboardFragment dashboardFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        SendImageViewModel.ElectionPagePerspectiveCorrectionModel value = dashboardViewModel2.getCaptureImageUseCase().getPerspectiveCorrectionModel().getValue();
                        if (value != null) {
                            androidx.navigation.fragment.FragmentKt.findNavController(dashboardFragment).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToSendImageFragment(value.getElectionPageId()));
                        }
                        dashboardViewModel2.getCaptureImageUseCase().finishPerspectiveCorrection();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(dashboardFragment, error, null, null, 6, null);
                        }
                        dashboardViewModel2.getCaptureImageUseCase().finishPerspectiveCorrection();
                    }
                }
            }
        }));
        dashboardViewModel.isAnyElectionStarted().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$6
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
        dashboardViewModel.isAnyElectionsUnzipped().observe(getViewLifecycleOwner(), new DashboardFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupViewModel$2$7
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
    }

    private final void setupFragmentListener() {
        DashboardFragment dashboardFragment = this;
        FragmentKt.setFragmentResultListener(dashboardFragment, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$1
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
                if (!z) {
                    if (z2) {
                        BaseFragment.showSnackBar$default(DashboardFragment.this, "Berhasil menghapus gambar", null, null, 6, null);
                        return;
                    } else if (z3) {
                        DashboardFragment.this.tryTakePhoto();
                        return;
                    } else {
                        return;
                    }
                }
                String imageDescription = bundle.getString(SendImageFragment.SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS_IMAGE_DESC, "");
                Intrinsics.checkNotNullExpressionValue(imageDescription, "imageDescription");
                if (imageDescription.length() > 0) {
                    DashboardFragment dashboardFragment2 = DashboardFragment.this;
                    String string = dashboardFragment2.getString(R.string.event_upload_image_started, imageDescription);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(dashboardFragment2, string, null, null, 6, null);
                }
            }
        });
        FragmentKt.setFragmentResultListener(dashboardFragment, AutoCaptureFragment.AUTOCAPTURE_CAMERA_OR_NOTHING, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$2
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
                DashboardViewModel dashboardViewModel;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                if (bundle.getBoolean(AutoCaptureFragment.CAMERA_PERMISSION_DENIED, false)) {
                    DashboardFragment dashboardFragment2 = DashboardFragment.this;
                    String string = dashboardFragment2.getString(R.string.message_camera_permissions_denied, DashboardFragment.this.getString(R.string.app_name_real));
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …al)\n                    )");
                    String string2 = DashboardFragment.this.getString(R.string.action_allow);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.action_allow)");
                    final DashboardFragment dashboardFragment3 = DashboardFragment.this;
                    dashboardFragment2.showSnackBar(string, string2, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$2.1
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
                            activityResultLauncher = DashboardFragment.this.openAppSettingsLauncher;
                            if (activityResultLauncher == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("openAppSettingsLauncher");
                                activityResultLauncher = null;
                            }
                            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", DashboardFragment.this.requireContext().getPackageName(), null));
                            activityResultLauncher.launch(intent);
                        }
                    });
                }
                dashboardViewModel = DashboardFragment.this.getDashboardViewModel();
                dashboardViewModel.getCaptureImageUseCase().deletePreparedPhoto();
            }
        });
        FragmentKt.setFragmentResultListener(dashboardFragment, AutoCaptureFragment.AUTO_CAPTURE, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$3
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
                DashboardViewModel dashboardViewModel;
                ActivityResultLauncher activityResultLauncher;
                DashboardViewModel dashboardViewModel2;
                DashboardViewModel dashboardViewModel3;
                DashboardViewModel dashboardViewModel4;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                boolean z = bundle.getBoolean(AutoCaptureFragment.AUTO_CAPTURE_RESULT, false);
                ActivityResultLauncher activityResultLauncher2 = null;
                String string = bundle.getString(AutoCaptureFragment.PEMILIHAN_RESULT, null);
                int i = bundle.getInt(AutoCaptureFragment.PAGE_NUM_RESULT, -1);
                int i2 = bundle.getInt(AutoCaptureFragment.APRIL_TAG_CODE, -1);
                String string2 = bundle.getString(AutoCaptureFragment.AUTO_CAPTURE_ERROR, "Terjadi Kesalahan");
                if (z && string != null && i != -1) {
                    dashboardViewModel2 = DashboardFragment.this.getDashboardViewModel();
                    dashboardViewModel2.setAprilTagCode(Integer.valueOf(i2));
                    dashboardViewModel3 = DashboardFragment.this.getDashboardViewModel();
                    dashboardViewModel3.setSaveCorrectedImage(true);
                    dashboardViewModel4 = DashboardFragment.this.getDashboardViewModel();
                    dashboardViewModel4.getAprilTagCheckUseCase().check(string, i);
                } else if (Intrinsics.areEqual(string2, AutoCaptureFragment.DETECTION_ERROR)) {
                    activityResultLauncher = DashboardFragment.this.cropImageLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
                    } else {
                        activityResultLauncher2 = activityResultLauncher;
                    }
                    activityResultLauncher2.launch(new Intent(DashboardFragment.this.getContext(), ImageCropActivity.class));
                } else {
                    DashboardFragment dashboardFragment2 = DashboardFragment.this;
                    String string3 = dashboardFragment2.getString(R.string.error_capture_image, string2);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.error…ture_image, errorMessage)");
                    BaseFragment.showSnackBar$default(dashboardFragment2, string3, null, null, 6, null);
                    dashboardViewModel = DashboardFragment.this.getDashboardViewModel();
                    dashboardViewModel.getCaptureImageUseCase().deletePreparedPhoto();
                }
            }
        });
        FragmentKt.setFragmentResultListener(dashboardFragment, SelectFormCImageFragment.SELECT_IMAGE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void invoke2(java.lang.String r8, android.os.Bundle r9) {
                /*
                    r7 = this;
                    java.lang.String r0 = "<anonymous parameter 0>"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                    java.lang.String r8 = "bundle"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r8)
                    java.lang.String r8 = "election_page_id"
                    java.lang.String r8 = r9.getString(r8)
                    org.informatika.sirekap.ui.dashboard.DashboardFragment r9 = org.informatika.sirekap.ui.dashboard.DashboardFragment.this
                    org.informatika.sirekap.ui.dashboard.DashboardViewModel r9 = org.informatika.sirekap.ui.dashboard.DashboardFragment.access$getDashboardViewModel(r9)
                    org.informatika.sirekap.ui.dashboard.DashboardFragment r0 = org.informatika.sirekap.ui.dashboard.DashboardFragment.this
                    androidx.lifecycle.LiveData r1 = r9.getElections()
                    java.lang.Object r1 = r1.getValue()
                    java.util.List r1 = (java.util.List) r1
                    r2 = 0
                    if (r1 == 0) goto L74
                    java.lang.String r3 = "value"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                    java.lang.Iterable r1 = (java.lang.Iterable) r1
                    java.util.Iterator r1 = r1.iterator()
                L30:
                    boolean r3 = r1.hasNext()
                    if (r3 == 0) goto L70
                    java.lang.Object r3 = r1.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    java.util.List r4 = r4.getPages()
                    java.lang.Iterable r4 = (java.lang.Iterable) r4
                    boolean r5 = r4 instanceof java.util.Collection
                    r6 = 0
                    if (r5 == 0) goto L52
                    r5 = r4
                    java.util.Collection r5 = (java.util.Collection) r5
                    boolean r5 = r5.isEmpty()
                    if (r5 == 0) goto L52
                    goto L6d
                L52:
                    java.util.Iterator r4 = r4.iterator()
                L56:
                    boolean r5 = r4.hasNext()
                    if (r5 == 0) goto L6d
                    java.lang.Object r5 = r4.next()
                    org.informatika.sirekap.model.ElectionPage r5 = (org.informatika.sirekap.model.ElectionPage) r5
                    java.lang.String r5 = r5.getId()
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)
                    if (r5 == 0) goto L56
                    r6 = 1
                L6d:
                    if (r6 == 0) goto L30
                    goto L71
                L70:
                    r3 = r2
                L71:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    goto L75
                L74:
                    r3 = r2
                L75:
                    if (r3 == 0) goto L9d
                    java.util.List r1 = r3.getPages()
                    if (r1 == 0) goto L9d
                    java.lang.Iterable r1 = (java.lang.Iterable) r1
                    java.util.Iterator r1 = r1.iterator()
                L83:
                    boolean r4 = r1.hasNext()
                    if (r4 == 0) goto L9b
                    java.lang.Object r4 = r1.next()
                    r5 = r4
                    org.informatika.sirekap.model.ElectionPage r5 = (org.informatika.sirekap.model.ElectionPage) r5
                    java.lang.String r5 = r5.getId()
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)
                    if (r5 == 0) goto L83
                    r2 = r4
                L9b:
                    org.informatika.sirekap.model.ElectionPage r2 = (org.informatika.sirekap.model.ElectionPage) r2
                L9d:
                    org.informatika.sirekap.ui.dashboard.CaptureImageUseCase r8 = r9.getCaptureImageUseCase()
                    java.lang.String r8 = r8.getCroppedPhotoAbsolutePath()
                    if (r3 == 0) goto Ld1
                    if (r2 == 0) goto Ld1
                    if (r8 == 0) goto Ld1
                    androidx.fragment.app.Fragment r0 = (androidx.fragment.app.Fragment) r0
                    androidx.navigation.NavController r0 = androidx.navigation.fragment.FragmentKt.findNavController(r0)
                    org.informatika.sirekap.ui.dashboard.DashboardFragmentDirections$Companion r1 = org.informatika.sirekap.ui.dashboard.DashboardFragmentDirections.Companion
                    java.lang.String r4 = r2.getId()
                    androidx.navigation.NavDirections r8 = r1.actionDashboardFragmentToConfirmSaveFormCImageFragment(r8, r4)
                    r0.navigate(r8)
                    androidx.lifecycle.MutableLiveData r8 = r9.getConfirmedPage()
                    r8.postValue(r2)
                    androidx.lifecycle.MutableLiveData r8 = r9.getConfirmedElection()
                    org.informatika.sirekap.model.Election r9 = r3.getElection()
                    r8.postValue(r9)
                    goto Led
                Ld1:
                    com.google.firebase.crashlytics.FirebaseCrashlytics r8 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()
                    java.lang.Exception r9 = new java.lang.Exception
                    java.lang.String r1 = "DashboardFragment Terjadi Kesalahan"
                    r9.<init>(r1)
                    java.lang.Throwable r9 = (java.lang.Throwable) r9
                    r8.recordException(r9)
                    r1 = r0
                    org.informatika.sirekap.ui.BaseFragment r1 = (org.informatika.sirekap.ui.BaseFragment) r1
                    java.lang.String r2 = "Terjadi Kesalahan"
                    r3 = 0
                    r4 = 0
                    r5 = 6
                    r6 = 0
                    org.informatika.sirekap.ui.BaseFragment.showSnackBar$default(r1, r2, r3, r4, r5, r6)
                Led:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$4.invoke2(java.lang.String, android.os.Bundle):void");
            }
        });
        FragmentKt.setFragmentResultListener(dashboardFragment, ConfirmSaveFormCImageFragment.CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$5
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
                final DashboardViewModel dashboardViewModel;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                boolean z = bundle.getBoolean("retake_photo");
                boolean z2 = bundle.getBoolean("save_photo");
                if (z) {
                    DashboardFragment.this.tryTakePhoto();
                } else if (z2) {
                    dashboardViewModel = DashboardFragment.this.getDashboardViewModel();
                    final DashboardFragment dashboardFragment2 = DashboardFragment.this;
                    final ElectionPage value = dashboardViewModel.getConfirmedPage().getValue();
                    final String value2 = dashboardViewModel.getKodeTps().getValue();
                    final Election value3 = dashboardViewModel.getConfirmedElection().getValue();
                    final Bitmap bitmap = ImageCropConstants.croppedImageBitmap;
                    if (value != null && value2 != null && value3 != null && bitmap != null) {
                        FragmentActivity requireActivity = dashboardFragment2.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        dashboardViewModel.savePhoto(requireActivity, value2, value3.getPemilihan(), new Function1<Bitmap, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$5$1$1
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
                            public final void invoke2(Bitmap bitmap2) {
                                Intrinsics.checkNotNullParameter(bitmap2, "<anonymous parameter 0>");
                                CaptureImageUseCase captureImageUseCase = DashboardViewModel.this.getCaptureImageUseCase();
                                Bitmap croppedBitmap = bitmap;
                                Intrinsics.checkNotNullExpressionValue(croppedBitmap, "croppedBitmap");
                                captureImageUseCase.saveCroppedPhoto(croppedBitmap, value3.getPemilihan(), value2, value, DashboardViewModel.this.getAprilTagCode());
                            }
                        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$5$1$2
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
                                DashboardFragment dashboardFragment3 = DashboardFragment.this;
                                String string = dashboardFragment3.getString(R.string.error_capture_image, exception.getMessage());
                                Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                                BaseFragment.showSnackBar$default(dashboardFragment3, string, null, null, 6, null);
                            }
                        });
                        return;
                    }
                    String string = dashboardFragment2.getString(R.string.error_capture_image, "Gagal Memproses Gambar");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(dashboardFragment2, string, null, null, 6, null);
                    dashboardViewModel.getCaptureImageUseCase().deletePreparedPhoto();
                    dashboardViewModel.finishSavingImage();
                }
            }
        });
        FragmentKt.setFragmentResultListener(dashboardFragment, ElectionDetailFragment.ELECTION_DETAIL_FRAGMENT_RESULT, new Function2<String, Bundle, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupFragmentListener$6
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
                MainViewModel mainViewModel;
                Tps tps;
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                String string = bundle.getString(ElectionDetailFragment.RESULT_KEY_JENIS_PEMILIHAN);
                String string2 = bundle.getString(ElectionDetailFragment.RESULT_KEY_ELECTION_ID);
                int i = bundle.getInt(ElectionDetailFragment.RESULT_KEY_JENIS_WAKTU);
                mainViewModel = DashboardFragment.this.getMainViewModel();
                User value = mainViewModel.getGetLoggedInUserUseCase().getLoggedInUser().getValue();
                String kodeTps = (value == null || (tps = value.getTps()) == null) ? null : tps.getKodeTps();
                if (string == null || string2 == null || kodeTps == null) {
                    return;
                }
                androidx.navigation.fragment.FragmentKt.findNavController(DashboardFragment.this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToManageTimeFragment(kodeTps, i, string, true));
            }
        });
    }

    private final void setupBinding() {
        HorizontalMarginItemDecoration horizontalMarginItemDecoration;
        FragmentDashboardBinding fragmentDashboardBinding = this.binding;
        FragmentDashboardBinding fragmentDashboardBinding2 = null;
        if (fragmentDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDashboardBinding = null;
        }
        fragmentDashboardBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentDashboardBinding.setViewModel(getDashboardViewModel());
        this.adapter = new ElectionCardListAdapter(new Function1<Election, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupBinding$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Election election) {
                invoke2(election);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Election election) {
                Intrinsics.checkNotNullParameter(election, "election");
                FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Lihat Detail Pemilihan' button");
                androidx.navigation.fragment.FragmentKt.findNavController(DashboardFragment.this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToElectionDetailFragment(election.getId(), election.getPemilihan()));
            }
        }, new Function1<Election, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupBinding$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Election election) {
                invoke2(election);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Election election) {
                MainViewModel mainViewModel;
                Tps tps;
                Intrinsics.checkNotNullParameter(election, "election");
                FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Kelola PPS, Saksi, & Panwas' button");
                mainViewModel = DashboardFragment.this.getMainViewModel();
                User value = mainViewModel.getGetLoggedInUserUseCase().getLoggedInUser().getValue();
                String kodeTps = (value == null || (tps = value.getTps()) == null) ? null : tps.getKodeTps();
                if (kodeTps != null) {
                    androidx.navigation.fragment.FragmentKt.findNavController(DashboardFragment.this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToWitnessFragment(kodeTps, election.getPemilihan()));
                    return;
                }
                FirebaseCrashlytics.getInstance().recordException(new Exception("DashboardFragment: kodeTps is null"));
                BaseFragment.showSnackBar$default(DashboardFragment.this, "Terjadi kesalahan mohon hubungi administrator.", null, null, 6, null);
            }
        }, new Function1<ElectionWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupBinding$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ElectionWithRelation electionWithRelation) {
                invoke2(electionWithRelation);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ElectionWithRelation electionWithRelation) {
                DashboardViewModel dashboardViewModel;
                Intrinsics.checkNotNullParameter(electionWithRelation, "electionWithRelation");
                Election election = electionWithRelation.getElection();
                FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Waktu Pengitungan Suara' button: " + election.getPemilihan());
                dashboardViewModel = DashboardFragment.this.getDashboardViewModel();
                String value = dashboardViewModel.getKodeTps().getValue();
                if (value != null) {
                    androidx.navigation.fragment.FragmentKt.findNavController(DashboardFragment.this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToManageTimeFragment(value, 1, election.getPemilihan(), electionWithRelation.canFillTpsTime()));
                }
            }
        });
        fragmentDashboardBinding.carouselElection.setAdapter(this.adapter);
        fragmentDashboardBinding.carouselElection.setOffscreenPageLimit(1);
        fragmentDashboardBinding.carouselElection.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupBinding$1$4
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                DashboardViewModel dashboardViewModel;
                super.onPageSelected(i);
                dashboardViewModel = DashboardFragment.this.getDashboardViewModel();
                dashboardViewModel.updateCurrentIndex(i);
            }
        });
        final float dimension = getResources().getDimension(R.dimen.viewpager_next_item_visible) + getResources().getDimension(R.dimen.viewpager_current_item_horizontal_margin);
        fragmentDashboardBinding.carouselElection.setPageTransformer(new ViewPager2.PageTransformer() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda12
            @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
            public final void transformPage(View view, float f) {
                DashboardFragment.setupBinding$lambda$27$lambda$6(dimension, view, f);
            }
        });
        Context it = getContext();
        if (it != null) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            horizontalMarginItemDecoration = new HorizontalMarginItemDecoration(it, R.dimen.viewpager_current_item_horizontal_margin);
        } else {
            horizontalMarginItemDecoration = null;
        }
        if (horizontalMarginItemDecoration != null) {
            fragmentDashboardBinding.carouselElection.addItemDecoration(horizontalMarginItemDecoration);
        }
        FragmentDashboardBinding fragmentDashboardBinding3 = this.binding;
        if (fragmentDashboardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDashboardBinding3 = null;
        }
        TabLayout tabLayout = fragmentDashboardBinding3.tabPagerLayout;
        FragmentDashboardBinding fragmentDashboardBinding4 = this.binding;
        if (fragmentDashboardBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDashboardBinding2 = fragmentDashboardBinding4;
        }
        new TabLayoutMediator(tabLayout, fragmentDashboardBinding2.carouselElection, new TabLayoutMediator.TabConfigurationStrategy() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda13
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                Intrinsics.checkNotNullParameter(tab, "<anonymous parameter 0>");
            }
        }).attach();
        fragmentDashboardBinding.buttonSettings.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$10(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.buttonCopyKodeTps.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$11(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.buttonChangeTps.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$18(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.buttonImageHistory.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$19(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.buttonManageWitnessAttendance.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$21(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.buttonManageSpecialOccurrence.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$23(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.buttonManageTime.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$25(DashboardFragment.this, view);
            }
        });
        fragmentDashboardBinding.scanButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardFragment.setupBinding$lambda$27$lambda$26(DashboardFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$27$lambda$6(float f, View page, float f2) {
        Intrinsics.checkNotNullParameter(page, "page");
        page.setTranslationX((-f) * f2);
        page.setScaleY(1 - (Math.abs(f2) * 0.25f));
    }

    public static final void setupBinding$lambda$27$lambda$10(DashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        String value = this$0.getDashboardViewModel().getKodeTps().getValue();
        if (value != null) {
            try {
                androidx.navigation.fragment.FragmentKt.findNavController(this$0).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToSettingsFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(new Exception(e));
            }
        }
    }

    public static final void setupBinding$lambda$27$lambda$11(DashboardFragment this$0, View view) {
        Tps tps;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        User value = this$0.getMainViewModel().getGetLoggedInUserUseCase().getLoggedInUser().getValue();
        String kodeTps = (value == null || (tps = value.getTps()) == null) ? null : tps.getKodeTps();
        if (kodeTps != null) {
            String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTps);
            ClipboardManager clipboardManager = (ClipboardManager) this$0.requireContext().getSystemService("clipboard");
            ClipData newPlainText = ClipData.newPlainText(this$0.requireContext().getString(R.string.kode_tps), extractKodeTpsReal);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(newPlainText);
            }
            String string = this$0.requireContext().getString(R.string.kode_tps_copied);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…R.string.kode_tps_copied)");
            BaseFragment.showSnackBar$default(this$0, string, null, null, 6, null);
        }
    }

    public static final void setupBinding$lambda$27$lambda$18(DashboardFragment this$0, View view) {
        Boolean isAnyElectionStarted;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        Boolean value = this$0.getDashboardViewModel().isAnyElectionsUnzipped().getValue();
        if (value == null || (isAnyElectionStarted = this$0.getDashboardViewModel().isAnyElectionStarted().getValue()) == null) {
            return;
        }
        if (value.booleanValue()) {
            Intrinsics.checkNotNullExpressionValue(isAnyElectionStarted, "isAnyElectionStarted");
            if (isAnyElectionStarted.booleanValue()) {
                new MaterialAlertDialogBuilder(this$0.requireContext()).setTitle((CharSequence) this$0.getString(R.string.change_tps_dialog_title)).setMessage((CharSequence) this$0.getString(R.string.change_tps_dialog_message)).setPositiveButton((CharSequence) this$0.getString(R.string.action_yes), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda4
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        DashboardFragment.setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$12(DashboardFragment.this, dialogInterface, i);
                    }
                }).setNegativeButton((CharSequence) this$0.getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        DashboardFragment.setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$13(dialogInterface, i);
                    }
                }).show();
                return;
            }
        }
        new MaterialAlertDialogBuilder(this$0.requireContext()).setTitle((CharSequence) this$0.getString(R.string.change_tps_dialog_title)).setMessage((CharSequence) this$0.getString(R.string.change_tps_dialog_message_normal)).setPositiveButton((CharSequence) this$0.getString(R.string.action_yes), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardFragment.setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$14(DashboardFragment.this, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) this$0.getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardFragment.setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$15(dialogInterface, i);
            }
        }).show();
    }

    public static final void setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$12(DashboardFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.tryChangeTps();
    }

    public static final void setupBinding$lambda$27$lambda$18$lambda$17$lambda$16$lambda$14(DashboardFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.tryChangeTps();
    }

    public static final void setupBinding$lambda$27$lambda$19(DashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Riwayat Gambar' button");
        androidx.navigation.fragment.FragmentKt.findNavController(this$0).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToImageHistoryFragment());
    }

    public static final void setupBinding$lambda$27$lambda$21(DashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Kelola Daftar Hadir' button");
        String value = this$0.getDashboardViewModel().getKodeTps().getValue();
        if (value != null) {
            androidx.navigation.fragment.FragmentKt.findNavController(this$0).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToWitnessAttendanceListFragment(value));
        }
    }

    public static final void setupBinding$lambda$27$lambda$23(DashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Kelola Kejadian Khusus' button");
        String value = this$0.getDashboardViewModel().getKodeTps().getValue();
        if (value != null) {
            androidx.navigation.fragment.FragmentKt.findNavController(this$0).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToSpecialOccurrenceFragment(value));
        }
    }

    public static final void setupBinding$lambda$27$lambda$25(DashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("DashboardFragment: User clicks 'Waktu Pemungutan Suara' button");
        String value = this$0.getDashboardViewModel().getKodeTps().getValue();
        if (value != null) {
            androidx.navigation.fragment.FragmentKt.findNavController(this$0).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToManageTimeFragment(value, 0, Election.ELECTION_PEMILIHAN_PRESIDEN, true));
        }
    }

    public static final void setupBinding$lambda$27$lambda$26(DashboardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.tryTakePhoto();
    }

    private final void tryChangeTps() {
        FirebaseCrashlytics.getInstance().log("User Change TPS");
        getMainViewModel().logout();
        Toast.makeText(getContext(), R.string.change_tps_success, 0).show();
    }

    private final void setupLauncher() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DashboardFragment.setupLauncher$lambda$28((ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…rtActivityForResult()) {}");
        this.openAppSettingsLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda10
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DashboardFragment.setupLauncher$lambda$29(DashboardFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…)\n            }\n        }");
        this.localAuthLauncher = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$$ExternalSyntheticLambda11
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DashboardFragment.setupLauncher$lambda$30(DashboardFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          }\n            }");
        this.cropImageLauncher = registerForActivityResult3;
    }

    public static final void setupLauncher$lambda$29(DashboardFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.getMainViewModel().getLocalAuth().postValue(true);
        } else {
            this$0.failedAuthentication();
        }
    }

    public static final void setupLauncher$lambda$30(DashboardFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer valueOf = activityResult != null ? Integer.valueOf(activityResult.getResultCode()) : null;
        if (valueOf != null && valueOf.intValue() == -1) {
            final Bitmap bitmap = ImageCropConstants.croppedImageBitmap;
            CaptureImageUseCase captureImageUseCase = this$0.getDashboardViewModel().getCaptureImageUseCase();
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            captureImageUseCase.saveTempCroppedPhoto(bitmap, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$setupLauncher$3$1
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
                    DashboardViewModel dashboardViewModel;
                    dashboardViewModel = DashboardFragment.this.getDashboardViewModel();
                    Bitmap bitmap2 = bitmap;
                    Intrinsics.checkNotNullExpressionValue(bitmap2, "bitmap");
                    dashboardViewModel.detectAprilTag(bitmap2);
                }
            });
        } else if (valueOf != null && valueOf.intValue() == 0) {
            this$0.getDashboardViewModel().getCaptureImageUseCase().deletePreparedPhoto();
        }
    }

    public final void tryTakePhoto() {
        FirebaseCrashlytics.getInstance().log("DashboardFragment User clicks 'Photo' button");
        String value = getDashboardViewModel().getKodeTps().getValue();
        if (value != null) {
            CaptureImageUseCase captureImageUseCase = getDashboardViewModel().getCaptureImageUseCase();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            captureImageUseCase.prepareTakingPhoto(requireContext, value, new Function3<Uri, Uri, Uri, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$tryTakePhoto$1
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
                    androidx.navigation.fragment.FragmentKt.findNavController(DashboardFragment.this).navigate(DashboardFragmentDirections.Companion.actionDashboardFragmentToAutoCaptureFragment(imageUri, croppedImageUri, correctedImageUri));
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$tryTakePhoto$2
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
        FirebaseCrashlytics.getInstance().recordException(new Exception("DashboardFragment Kode TPS kosong"));
        BaseFragment.showSnackBar$default(this, "Kode TPS kosong", null, null, 6, null);
    }

    /* compiled from: DashboardFragment.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragment$HorizontalMarginItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "context", "Landroid/content/Context;", "horizontalMarginInDp", "", "(Landroid/content/Context;I)V", "horizontalMarginInPx", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {
        private final int horizontalMarginInPx;

        public HorizontalMarginItemDecoration(Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.horizontalMarginInPx = (int) context.getResources().getDimension(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            outRect.right = this.horizontalMarginInPx;
            outRect.left = this.horizontalMarginInPx;
        }
    }

    /* compiled from: DashboardFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
