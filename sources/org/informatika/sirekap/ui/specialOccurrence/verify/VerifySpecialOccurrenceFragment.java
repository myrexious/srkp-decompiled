package org.informatika.sirekap.ui.specialOccurrence.verify;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.DialogVerifyBodyBinding;
import org.informatika.sirekap.databinding.FragmentVerifySpecialOccurrenceBinding;
import org.informatika.sirekap.databinding.ViewSpecialOccurrenceOngoingBinding;
import org.informatika.sirekap.databinding.ViewSpecialOccurrenceStartBinding;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.imageCrop.ImageCropActivity;
import org.informatika.sirekap.ui.imageCrop.ImageCropConstants;
import org.informatika.sirekap.ui.specialOccurrence.CaptureSpecialOccurrenceImageUseCase;

/* compiled from: VerifySpecialOccurrenceFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 12\u00020\u0001:\u00011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001cH\u0016J\u001a\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010)\u001a\u00020\u001cH\u0002J\b\u0010*\u001a\u00020\u001cH\u0002J\b\u0010+\u001a\u00020\u001cH\u0002J\u0006\u0010,\u001a\u00020\u001cJ\u0012\u0010-\u001a\u00020\u001c2\b\b\u0002\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020\u001cH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u00062"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentVerifySpecialOccurrenceBinding;", "cropImageLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "openAppSettingsLauncher", "requestPermissionsLauncher", "", "takePictureLauncher", "Landroid/net/Uri;", "verifySpecialOccurrenceViewModel", "Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceViewModel;", "getVerifySpecialOccurrenceViewModel", "()Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceViewModel;", "verifySpecialOccurrenceViewModel$delegate", "Lkotlin/Lazy;", "finishChecking", "", "nextItemOrFinish", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupBinding", "setupLauncher", "setupNavigation", "setupViewModel", "startChecking", "isJump", "", "tryTakePhoto", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class VerifySpecialOccurrenceFragment extends Hilt_VerifySpecialOccurrenceFragment {
    public static final Companion Companion = new Companion(null);
    public static final String SEND_SPECIAL_OCCURRENCE_FRAGMENT_RESULT = "SEND_SPECIAL_OCCURRENCE_FRAGMENT_RESULT";
    public static final String SEND_SPECIAL_OCCURRENCE_RESULT_TYPE_SEND_SUCCESS = "SPECIAL_OCCURRENCE_SEND_SUCCESS";
    private static final String TAG = "VerifySpecialOccurrenceFragment";
    private final NavArgsLazy args$delegate;
    private FragmentVerifySpecialOccurrenceBinding binding;
    private ActivityResultLauncher<Intent> cropImageLauncher;
    private OnBackPressedCallback onBackPressedCallback;
    private ActivityResultLauncher<Intent> openAppSettingsLauncher;
    private ActivityResultLauncher<String> requestPermissionsLauncher;
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private final Lazy verifySpecialOccurrenceViewModel$delegate;

    public static final void setupLauncher$lambda$16(ActivityResult activityResult) {
    }

    public VerifySpecialOccurrenceFragment() {
        final VerifySpecialOccurrenceFragment verifySpecialOccurrenceFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.verifySpecialOccurrenceViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifySpecialOccurrenceFragment, Reflection.getOrCreateKotlinClass(VerifySpecialOccurrenceViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(VerifySpecialOccurrenceFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$special$$inlined$navArgs$1
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

    public final VerifySpecialOccurrenceViewModel getVerifySpecialOccurrenceViewModel() {
        return (VerifySpecialOccurrenceViewModel) this.verifySpecialOccurrenceViewModel$delegate.getValue();
    }

    private final VerifySpecialOccurrenceFragmentArgs getArgs() {
        return (VerifySpecialOccurrenceFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentVerifySpecialOccurrenceBinding inflate = FragmentVerifySpecialOccurrenceBinding.inflate(inflater, viewGroup, false);
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
        setupLauncher();
        getVerifySpecialOccurrenceViewModel().setup(getArgs().getKodeTps());
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
        FragmentVerifySpecialOccurrenceBinding fragmentVerifySpecialOccurrenceBinding = this.binding;
        FragmentVerifySpecialOccurrenceBinding fragmentVerifySpecialOccurrenceBinding2 = null;
        if (fragmentVerifySpecialOccurrenceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifySpecialOccurrenceBinding = null;
        }
        fragmentVerifySpecialOccurrenceBinding.setLifecycleOwner(getViewLifecycleOwner());
        FragmentVerifySpecialOccurrenceBinding fragmentVerifySpecialOccurrenceBinding3 = this.binding;
        if (fragmentVerifySpecialOccurrenceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifySpecialOccurrenceBinding3 = null;
        }
        fragmentVerifySpecialOccurrenceBinding3.setViewModel(getVerifySpecialOccurrenceViewModel());
        FragmentVerifySpecialOccurrenceBinding fragmentVerifySpecialOccurrenceBinding4 = this.binding;
        if (fragmentVerifySpecialOccurrenceBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifySpecialOccurrenceBinding4 = null;
        }
        ViewSpecialOccurrenceStartBinding viewSpecialOccurrenceStartBinding = fragmentVerifySpecialOccurrenceBinding4.viewVerifySpecialOccurrenceStart;
        viewSpecialOccurrenceStartBinding.buttonStartVerification.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$3$lambda$0(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        viewSpecialOccurrenceStartBinding.buttonResumeVerification.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$3$lambda$1(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        viewSpecialOccurrenceStartBinding.buttonRestartStartVerification.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$3$lambda$2(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        FragmentVerifySpecialOccurrenceBinding fragmentVerifySpecialOccurrenceBinding5 = this.binding;
        if (fragmentVerifySpecialOccurrenceBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVerifySpecialOccurrenceBinding2 = fragmentVerifySpecialOccurrenceBinding5;
        }
        ViewSpecialOccurrenceOngoingBinding viewSpecialOccurrenceOngoingBinding = fragmentVerifySpecialOccurrenceBinding2.viewVerifySpecialOccurrenceOngoing;
        viewSpecialOccurrenceOngoingBinding.buttonBack.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$12$lambda$4(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        viewSpecialOccurrenceOngoingBinding.buttonNext.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$12$lambda$5(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        viewSpecialOccurrenceOngoingBinding.buttonFinish.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$12$lambda$8(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        viewSpecialOccurrenceOngoingBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$12$lambda$9(VerifySpecialOccurrenceFragment.this, view);
            }
        });
        viewSpecialOccurrenceOngoingBinding.cardImage.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$12$lambda$11(VerifySpecialOccurrenceFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$3$lambda$0(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startChecking$default(this$0, false, 1, null);
    }

    public static final void setupBinding$lambda$3$lambda$1(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startChecking(true);
    }

    public static final void setupBinding$lambda$3$lambda$2(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startChecking$default(this$0, false, 1, null);
    }

    public static final void setupBinding$lambda$12$lambda$4(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifySpecialOccurrenceViewModel().backItem();
    }

    public static final void setupBinding$lambda$12$lambda$5(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifySpecialOccurrenceViewModel().checkItem();
        this$0.nextItemOrFinish();
    }

    public static final void setupBinding$lambda$12$lambda$8(final VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogVerifyBodyBinding inflate = DialogVerifyBodyBinding.inflate(this$0.getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this$0.requireContext());
        inflate.setLifecycleOwner(this$0.getViewLifecycleOwner());
        inflate.setViewModel(this$0.getVerifySpecialOccurrenceViewModel());
        inflate.setHideCommentField(true);
        materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) this$0.getString(R.string.verify_special_occurrence_finish_dialog_message));
        final AlertDialog create = materialAlertDialogBuilder.create();
        Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…               }.create()");
        create.show();
        inflate.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VerifySpecialOccurrenceFragment.setupBinding$lambda$12$lambda$8$lambda$7(AlertDialog.this, this$0, view2);
            }
        });
    }

    public static final void setupBinding$lambda$12$lambda$8$lambda$7(AlertDialog dialog, VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finishChecking();
    }

    public static final void setupBinding$lambda$12$lambda$9(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("VerifySpecialOccurrenceFragment User clicks 'Retake Special Occurrence Photo' button");
        this$0.tryTakePhoto();
    }

    public static final void setupBinding$lambda$12$lambda$11(VerifySpecialOccurrenceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("VerifySpecialOccurrenceFragment : User clicks 'Preview Image' card");
        SpecialOccurrencePage value = this$0.getVerifySpecialOccurrenceViewModel().getCurrentItem().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(VerifySpecialOccurrenceFragmentDirections.Companion.actionVerifySpecialOccurrenceFragmentToPreviewImageFragment(value.getCroppedPhotoPath()));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public final void setupViewModel() {
        final VerifySpecialOccurrenceViewModel verifySpecialOccurrenceViewModel = getVerifySpecialOccurrenceViewModel();
        verifySpecialOccurrenceViewModel.getCaptureImageUseCase().getCropPhotoResult().observe(getViewLifecycleOwner(), new VerifySpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<CaptureImageUseCase.CropPhotoResult, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$setupViewModel$1$1
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
                    VerifySpecialOccurrenceFragment verifySpecialOccurrenceFragment = VerifySpecialOccurrenceFragment.this;
                    VerifySpecialOccurrenceViewModel verifySpecialOccurrenceViewModel2 = verifySpecialOccurrenceViewModel;
                    if (!cropPhotoResult.isSuccess()) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(cropPhotoResult.getErrorMessage()));
                        String string = verifySpecialOccurrenceFragment.getString(R.string.error_capture_image, cropPhotoResult.getErrorMessage());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error…image, this.errorMessage)");
                        BaseFragment.showSnackBar$default(verifySpecialOccurrenceFragment, string, null, null, 6, null);
                    }
                    verifySpecialOccurrenceViewModel2.getCaptureImageUseCase().finishCrop();
                }
            }
        }));
        verifySpecialOccurrenceViewModel.getCaptureImageUseCase().getSpecialOccurrenceUpdateResource().observe(getViewLifecycleOwner(), new VerifySpecialOccurrenceFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends SpecialOccurrencePage>, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$setupViewModel$1$2
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
                    VerifySpecialOccurrenceViewModel verifySpecialOccurrenceViewModel2 = VerifySpecialOccurrenceViewModel.this;
                    VerifySpecialOccurrenceFragment verifySpecialOccurrenceFragment = this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (verifySpecialOccurrenceViewModel2.getCaptureImageUseCase().getSpecialOccurrenceUpdateModel().getValue() != null) {
                            BaseFragment.showSnackBar$default(verifySpecialOccurrenceFragment, "Berhasil memperbarui gambar", null, null, 6, null);
                        }
                        verifySpecialOccurrenceViewModel2.finishTakingPicture();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(verifySpecialOccurrenceFragment, error, null, null, 6, null);
                        }
                        verifySpecialOccurrenceViewModel2.finishTakingPicture();
                    }
                }
            }
        }));
    }

    private final void setupNavigation() {
        this.onBackPressedCallback = new VerifySpecialOccurrenceFragment$setupNavigation$1(this);
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBackPressedCallback");
            onBackPressedCallback = null;
        }
        onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback);
    }

    static /* synthetic */ void startChecking$default(VerifySpecialOccurrenceFragment verifySpecialOccurrenceFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        verifySpecialOccurrenceFragment.startChecking(z);
    }

    private final void startChecking(boolean z) {
        getVerifySpecialOccurrenceViewModel().startChecking(z);
    }

    private final void nextItemOrFinish() {
        Boolean value = getVerifySpecialOccurrenceViewModel().isLastItem().getValue();
        if (value != null) {
            if (value.booleanValue()) {
                finishChecking();
            } else {
                getVerifySpecialOccurrenceViewModel().nextItem();
            }
        }
    }

    private final void finishChecking() {
        androidx.fragment.app.FragmentKt.setFragmentResult(this, SEND_SPECIAL_OCCURRENCE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SEND_SPECIAL_OCCURRENCE_RESULT_TYPE_SEND_SUCCESS, true)));
        try {
            getVerifySpecialOccurrenceViewModel().checkItem();
            FragmentKt.findNavController(this).navigateUp();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    private final void setupLauncher() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifySpecialOccurrenceFragment.setupLauncher$lambda$15(VerifySpecialOccurrenceFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.requestPermissionsLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifySpecialOccurrenceFragment.setupLauncher$lambda$16((ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…rtActivityForResult()) {}");
        this.openAppSettingsLauncher = registerForActivityResult2;
        ActivityResultLauncher<Uri> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifySpecialOccurrenceFragment.setupLauncher$lambda$17(VerifySpecialOccurrenceFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResul…          }\n            }");
        this.takePictureLauncher = registerForActivityResult3;
        ActivityResultLauncher<Intent> registerForActivityResult4 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VerifySpecialOccurrenceFragment.setupLauncher$lambda$19(VerifySpecialOccurrenceFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult4, "registerForActivityResul…          }\n            }");
        this.cropImageLauncher = registerForActivityResult4;
    }

    public static final void setupLauncher$lambda$15(VerifySpecialOccurrenceFragment this$0, Boolean permissionGranted) {
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
        this$0.showSnackBar(string, string2, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$setupLauncher$1$1
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
                activityResultLauncher = VerifySpecialOccurrenceFragment.this.openAppSettingsLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("openAppSettingsLauncher");
                    activityResultLauncher = null;
                }
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", VerifySpecialOccurrenceFragment.this.requireContext().getPackageName(), null));
                activityResultLauncher.launch(intent);
            }
        });
    }

    public static final void setupLauncher$lambda$17(VerifySpecialOccurrenceFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            CaptureSpecialOccurrenceImageUseCase captureImageUseCase = this$0.getVerifySpecialOccurrenceViewModel().getCaptureImageUseCase();
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            captureImageUseCase.saveOriginalPhoto(requireActivity, this$0.getArgs().getKodeTps(), "kejadian_khusus", new Function1<Bitmap, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$setupLauncher$3$1
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
                    activityResultLauncher = VerifySpecialOccurrenceFragment.this.cropImageLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
                        activityResultLauncher = null;
                    }
                    activityResultLauncher.launch(new Intent(VerifySpecialOccurrenceFragment.this.getContext(), ImageCropActivity.class));
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$setupLauncher$3$2
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
                    VerifySpecialOccurrenceFragment verifySpecialOccurrenceFragment = VerifySpecialOccurrenceFragment.this;
                    String string = verifySpecialOccurrenceFragment.getString(R.string.error_capture_image, exception.getMessage());
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    BaseFragment.showSnackBar$default(verifySpecialOccurrenceFragment, string, null, null, 6, null);
                }
            });
            return;
        }
        this$0.getVerifySpecialOccurrenceViewModel().getCaptureImageUseCase().deletePreparedPhoto();
    }

    public static final void setupLauncher$lambda$19(VerifySpecialOccurrenceFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer valueOf = activityResult != null ? Integer.valueOf(activityResult.getResultCode()) : null;
        if (valueOf != null && valueOf.intValue() == -1) {
            SpecialOccurrencePage value = this$0.getVerifySpecialOccurrenceViewModel().getCurrentItem().getValue();
            if (value != null) {
                Bitmap bitmap = ImageCropConstants.selectedImageBitmap;
                if (bitmap != null) {
                    this$0.getVerifySpecialOccurrenceViewModel().getCaptureImageUseCase().saveCroppedPhoto(bitmap, "kejadian_khusus", this$0.getArgs().getKodeTps(), value);
                    return;
                }
                return;
            }
            BaseFragment.showSnackBar$default(this$0, "ID Gambar Tidak Ditemukan", null, null, 6, null);
        } else if (valueOf != null && valueOf.intValue() == 0) {
            this$0.getVerifySpecialOccurrenceViewModel().getCaptureImageUseCase().deleteOriginalPhoto();
        }
    }

    private final void tryTakePhoto() {
        FirebaseCrashlytics.getInstance().log("VerifySpecialOccurrenceFragment : User clicks 'Photo' button");
        CaptureSpecialOccurrenceImageUseCase captureImageUseCase = getVerifySpecialOccurrenceViewModel().getCaptureImageUseCase();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        captureImageUseCase.prepareTakingPhoto(requireContext, getArgs().getKodeTps(), new Function3<Uri, Uri, Uri, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$tryTakePhoto$1
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
                activityResultLauncher = VerifySpecialOccurrenceFragment.this.takePictureLauncher;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("takePictureLauncher");
                    activityResultLauncher = null;
                }
                activityResultLauncher.launch(imageUri);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment$tryTakePhoto$2
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

    /* compiled from: VerifySpecialOccurrenceFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceFragment$Companion;", "", "()V", VerifySpecialOccurrenceFragment.SEND_SPECIAL_OCCURRENCE_FRAGMENT_RESULT, "", "SEND_SPECIAL_OCCURRENCE_RESULT_TYPE_SEND_SUCCESS", "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
