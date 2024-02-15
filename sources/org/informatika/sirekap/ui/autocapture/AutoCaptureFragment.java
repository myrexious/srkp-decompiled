package org.informatika.sirekap.ui.autocapture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
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
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentAutocaptureBinding;
import org.informatika.sirekap.model.PolygonPoints;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;
import org.informatika.sirekap.ui.autocapture.ImageProcessingUseCase;

/* compiled from: AutoCaptureFragment.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u001d2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002J\b\u0010&\u001a\u00020\u0019H\u0002J\b\u0010'\u001a\u00020\u0019H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "autocaptureViewModels", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel;", "getAutocaptureViewModels", "()Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel;", "autocaptureViewModels$delegate", "Lkotlin/Lazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentAutocaptureBinding;", "cameraProviderResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "scanSurfaceView", "Lorg/informatika/sirekap/ui/autocapture/ScanSurfaceView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupLauncher", "setupNavigation", "setupScanSurfaceView", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class AutoCaptureFragment extends Hilt_AutoCaptureFragment {
    public static final String APRIL_TAG_CODE = "APRIL_TAG_CODE";
    public static final String AUTOCAPTURE_CAMERA_OR_NOTHING = "AUTOCAPTURE_CAMERA_OR_NOTHING";
    public static final String AUTO_CAPTURE = "AUTOCAPTURE";
    public static final String AUTO_CAPTURE_ERROR = "AUTO_CAPTURE_ERROR";
    public static final String AUTO_CAPTURE_RESULT = "AUTO_CAPTURE_RESULT";
    public static final String CAMERA_PERMISSION_DENIED = "AUTOCAPTURE_CAMERA_PERMISSION_RESULT";
    public static final Companion Companion = new Companion(null);
    public static final String DETECTION_ERROR = "DETECTION_ERROR";
    public static final String PAGE_NUM_RESULT = "PAGE_NUM_RESULT";
    public static final String PEMILIHAN_RESULT = "PEMILIHAN_RESULT";
    public static final String TAG = "AutocaptureFragment";
    private static PolygonPoints manualCropPoints;
    private final NavArgsLazy args$delegate;
    private final Lazy autocaptureViewModels$delegate;
    private FragmentAutocaptureBinding binding;
    private ActivityResultLauncher<String> cameraProviderResult;
    private OnBackPressedCallback onBackPressedCallback;
    private ScanSurfaceView scanSurfaceView;

    public AutoCaptureFragment() {
        final AutoCaptureFragment autoCaptureFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.autocaptureViewModels$delegate = FragmentViewModelLazyKt.createViewModelLazy(autoCaptureFragment, Reflection.getOrCreateKotlinClass(AutoCaptureViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(AutoCaptureFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$special$$inlined$navArgs$1
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
    }

    public final AutoCaptureViewModel getAutocaptureViewModels() {
        return (AutoCaptureViewModel) this.autocaptureViewModels$delegate.getValue();
    }

    private final AutoCaptureFragmentArgs getArgs() {
        return (AutoCaptureFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentAutocaptureBinding inflate = FragmentAutocaptureBinding.inflate(inflater, viewGroup, false);
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
        setupNavigation();
        setupBinding();
        setupLauncher();
        setupViewModel();
        getAutocaptureViewModels().setup(getArgs().getImageUri(), getArgs().getCroppedImageUri(), getArgs().getCorrectedImageUri());
    }

    private final void setupViewModel() {
        final ImageProcessingUseCase imageProcessingUseCase = getAutocaptureViewModels().getImageProcessingUseCase();
        imageProcessingUseCase.getAutoCropResult().observe(getViewLifecycleOwner(), new AutoCaptureFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$setupViewModel$1$1
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
                AutoCaptureViewModel autocaptureViewModels;
                AutoCaptureViewModel autocaptureViewModels2;
                if (bool != null) {
                    AutoCaptureFragment autoCaptureFragment = AutoCaptureFragment.this;
                    ImageProcessingUseCase imageProcessingUseCase2 = imageProcessingUseCase;
                    if (!bool.booleanValue()) {
                        AutoCaptureFragment autoCaptureFragment2 = autoCaptureFragment;
                        FragmentKt.setFragmentResult(autoCaptureFragment2, AutoCaptureFragment.AUTO_CAPTURE, BundleKt.bundleOf(TuplesKt.to(AutoCaptureFragment.AUTO_CAPTURE_RESULT, false), TuplesKt.to(AutoCaptureFragment.AUTO_CAPTURE_ERROR, AutoCaptureFragment.DETECTION_ERROR)));
                        androidx.navigation.fragment.FragmentKt.findNavController(autoCaptureFragment2).navigateUp();
                    }
                    autocaptureViewModels = autoCaptureFragment.getAutocaptureViewModels();
                    autocaptureViewModels.isProcessingImage().postValue(false);
                    autocaptureViewModels2 = autoCaptureFragment.getAutocaptureViewModels();
                    autocaptureViewModels2.setHintOmr(AutoCaptureViewModel.ScanHint.NO_MESSAGE);
                    imageProcessingUseCase2.finishAutoCrop();
                }
            }
        }));
        imageProcessingUseCase.getFinishPhotoResult().observe(getViewLifecycleOwner(), new AutoCaptureFragment$sam$androidx_lifecycle_Observer$0(new Function1<ImageProcessingUseCase.FinishPhotoResult, Unit>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$setupViewModel$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ImageProcessingUseCase.FinishPhotoResult finishPhotoResult) {
                invoke2(finishPhotoResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ImageProcessingUseCase.FinishPhotoResult finishPhotoResult) {
                if (finishPhotoResult != null) {
                    AutoCaptureFragment autoCaptureFragment = AutoCaptureFragment.this;
                    ImageProcessingUseCase imageProcessingUseCase2 = imageProcessingUseCase;
                    if (finishPhotoResult.isSuccess()) {
                        AutoCaptureFragment autoCaptureFragment2 = autoCaptureFragment;
                        FragmentKt.setFragmentResult(autoCaptureFragment2, AutoCaptureFragment.AUTO_CAPTURE, BundleKt.bundleOf(TuplesKt.to(AutoCaptureFragment.AUTO_CAPTURE_RESULT, true), TuplesKt.to(AutoCaptureFragment.PEMILIHAN_RESULT, finishPhotoResult.getPemilihan()), TuplesKt.to(AutoCaptureFragment.PAGE_NUM_RESULT, finishPhotoResult.getPageNum()), TuplesKt.to(AutoCaptureFragment.APRIL_TAG_CODE, finishPhotoResult.getAprilTagCode())));
                        androidx.navigation.fragment.FragmentKt.findNavController(autoCaptureFragment2).navigateUp();
                    } else {
                        String string = autoCaptureFragment.getString(R.string.error_cropping_autocapture);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error_cropping_autocapture)");
                        BaseFragment.showSnackBar$default(autoCaptureFragment, string, null, null, 6, null);
                    }
                    imageProcessingUseCase2.finishPhoto();
                }
            }
        }));
    }

    private final void setupBinding() {
        final FragmentAutocaptureBinding fragmentAutocaptureBinding = this.binding;
        if (fragmentAutocaptureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAutocaptureBinding = null;
        }
        fragmentAutocaptureBinding.setViewModel(getAutocaptureViewModels());
        fragmentAutocaptureBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentAutocaptureBinding.buttonCropOk.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoCaptureFragment.setupBinding$lambda$7$lambda$1(AutoCaptureFragment.this, view);
            }
        });
        fragmentAutocaptureBinding.buttonCropReject.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoCaptureFragment.setupBinding$lambda$7$lambda$2(AutoCaptureFragment.this, fragmentAutocaptureBinding, view);
            }
        });
        fragmentAutocaptureBinding.shutter.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoCaptureFragment.setupBinding$lambda$7$lambda$3(AutoCaptureFragment.this, view);
            }
        });
        MaterialButtonToggleGroup materialButtonToggleGroup = fragmentAutocaptureBinding.buttonToggleCapture;
        fragmentAutocaptureBinding.buttonAuto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoCaptureFragment.setupBinding$lambda$7$lambda$6$lambda$4(FragmentAutocaptureBinding.this, this, view);
            }
        });
        fragmentAutocaptureBinding.buttonManual.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoCaptureFragment.setupBinding$lambda$7$lambda$6$lambda$5(FragmentAutocaptureBinding.this, this, view);
            }
        });
    }

    public static final void setupBinding$lambda$7$lambda$1(AutoCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("AutocaptureFragment : User Clicks Accept Crop Result");
        this$0.getAutocaptureViewModels().getImageProcessingUseCase().processFinalImage();
    }

    public static final void setupBinding$lambda$7$lambda$2(AutoCaptureFragment this$0, FragmentAutocaptureBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        FirebaseCrashlytics.getInstance().log("AutocaptureFragment : User Clicks Reject Crop Result");
        this$0.getAutocaptureViewModels().getImageProcessingUseCase().setupCropView(false);
        ScanSurfaceView scanSurfaceView = this$0.scanSurfaceView;
        if (scanSurfaceView != null) {
            scanSurfaceView.setPreviewCallback();
        }
        if (this_apply.buttonManual.isChecked()) {
            this$0.getAutocaptureViewModels().setEnableShutter(true);
        }
    }

    public static final void setupBinding$lambda$7$lambda$3(AutoCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            ScanSurfaceView scanSurfaceView = this$0.scanSurfaceView;
            if (scanSurfaceView != null) {
                scanSurfaceView.autoCapture(AutoCaptureViewModel.ScanHint.CAPTURING_IMAGE);
            }
        } catch (Exception unused) {
            AutoCaptureFragment autoCaptureFragment = this$0;
            FragmentKt.setFragmentResult(autoCaptureFragment, AUTO_CAPTURE, BundleKt.bundleOf(TuplesKt.to(AUTO_CAPTURE_RESULT, false)));
            androidx.navigation.fragment.FragmentKt.findNavController(autoCaptureFragment).navigateUp();
        }
    }

    public static final void setupBinding$lambda$7$lambda$6$lambda$4(FragmentAutocaptureBinding this_apply, AutoCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_apply.buttonAuto.isChecked()) {
            this$0.getAutocaptureViewModels().getUseAutoCapture().postValue(true);
            this$0.getAutocaptureViewModels().setEnableShutter(false);
        }
    }

    public static final void setupBinding$lambda$7$lambda$6$lambda$5(FragmentAutocaptureBinding this_apply, AutoCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_apply.buttonManual.isChecked()) {
            this$0.getAutocaptureViewModels().getUseAutoCapture().postValue(false);
            this$0.getAutocaptureViewModels().setEnableShutter(true);
        }
    }

    private final void setupNavigation() {
        this.onBackPressedCallback = new OnBackPressedCallback() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$setupNavigation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FragmentKt.setFragmentResult(AutoCaptureFragment.this, AutoCaptureFragment.AUTOCAPTURE_CAMERA_OR_NOTHING, BundleKt.bundleOf(TuplesKt.to(AutoCaptureFragment.CAMERA_PERMISSION_DENIED, false)));
                androidx.navigation.fragment.FragmentKt.findNavController(AutoCaptureFragment.this).navigateUp();
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

    private final void setupScanSurfaceView() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        FragmentActivity fragmentActivity = requireActivity;
        AutoCaptureViewModel autocaptureViewModels = getAutocaptureViewModels();
        FragmentAutocaptureBinding fragmentAutocaptureBinding = this.binding;
        FragmentAutocaptureBinding fragmentAutocaptureBinding2 = null;
        if (fragmentAutocaptureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAutocaptureBinding = null;
        }
        int width = fragmentAutocaptureBinding.autocropContainer.getWidth();
        FragmentAutocaptureBinding fragmentAutocaptureBinding3 = this.binding;
        if (fragmentAutocaptureBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAutocaptureBinding3 = null;
        }
        this.scanSurfaceView = new ScanSurfaceView(fragmentActivity, autocaptureViewModels, width, fragmentAutocaptureBinding3.autocropContainer.getHeight());
        FragmentAutocaptureBinding fragmentAutocaptureBinding4 = this.binding;
        if (fragmentAutocaptureBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentAutocaptureBinding2 = fragmentAutocaptureBinding4;
        }
        fragmentAutocaptureBinding2.cameraPreview.addView(this.scanSurfaceView);
    }

    private final void setupLauncher() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                AutoCaptureFragment.setupLauncher$lambda$8(AutoCaptureFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.cameraProviderResult = registerForActivityResult;
        if (registerForActivityResult == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraProviderResult");
            registerForActivityResult = null;
        }
        registerForActivityResult.launch("android.permission.CAMERA");
    }

    public static final void setupLauncher$lambda$8(AutoCaptureFragment this$0, Boolean permissionGranted) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(permissionGranted, "permissionGranted");
        if (permissionGranted.booleanValue()) {
            this$0.setupScanSurfaceView();
            return;
        }
        AutoCaptureFragment autoCaptureFragment = this$0;
        FragmentKt.setFragmentResult(autoCaptureFragment, AUTOCAPTURE_CAMERA_OR_NOTHING, BundleKt.bundleOf(TuplesKt.to(CAMERA_PERMISSION_DENIED, true)));
        androidx.navigation.fragment.FragmentKt.findNavController(autoCaptureFragment).navigateUp();
    }

    /* compiled from: AutoCaptureFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureFragment$Companion;", "", "()V", AutoCaptureFragment.APRIL_TAG_CODE, "", AutoCaptureFragment.AUTOCAPTURE_CAMERA_OR_NOTHING, "AUTO_CAPTURE", AutoCaptureFragment.AUTO_CAPTURE_ERROR, AutoCaptureFragment.AUTO_CAPTURE_RESULT, "CAMERA_PERMISSION_DENIED", AutoCaptureFragment.DETECTION_ERROR, AutoCaptureFragment.PAGE_NUM_RESULT, AutoCaptureFragment.PEMILIHAN_RESULT, "TAG", "manualCropPoints", "Lorg/informatika/sirekap/model/PolygonPoints;", "getManualCropPoints", "()Lorg/informatika/sirekap/model/PolygonPoints;", "setManualCropPoints", "(Lorg/informatika/sirekap/model/PolygonPoints;)V", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PolygonPoints getManualCropPoints() {
            return AutoCaptureFragment.manualCropPoints;
        }

        public final void setManualCropPoints(PolygonPoints polygonPoints) {
            AutoCaptureFragment.manualCropPoints = polygonPoints;
        }
    }
}
