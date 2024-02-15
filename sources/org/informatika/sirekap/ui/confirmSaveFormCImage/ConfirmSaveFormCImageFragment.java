package org.informatika.sirekap.ui.confirmSaveFormCImage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.snackbar.Snackbar;
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
import org.informatika.sirekap.databinding.FragmentConfirmSaveFormCImageBinding;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* compiled from: ConfirmSaveFormCImageFragment.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentConfirmSaveFormCImageBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "confirmSave", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setupBinding", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class ConfirmSaveFormCImageFragment extends Hilt_ConfirmSaveFormCImageFragment {
    public static final String CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT = "CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT";
    public static final String CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT_RETAKE_PHOTO = "retake_photo";
    public static final String CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT_SAVE_PHOTO = "save_photo";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ConfirmSaveFormCF";
    private final NavArgsLazy args$delegate;
    private FragmentConfirmSaveFormCImageBinding binding;
    private final Lazy fragmentViewModel$delegate;

    public ConfirmSaveFormCImageFragment() {
        final ConfirmSaveFormCImageFragment confirmSaveFormCImageFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(confirmSaveFormCImageFragment, Reflection.getOrCreateKotlinClass(ConfirmSaveFormCImageViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(ConfirmSaveFormCImageFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$special$$inlined$navArgs$1
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

    private final ConfirmSaveFormCImageViewModel getFragmentViewModel() {
        return (ConfirmSaveFormCImageViewModel) this.fragmentViewModel$delegate.getValue();
    }

    private final ConfirmSaveFormCImageFragmentArgs getArgs() {
        return (ConfirmSaveFormCImageFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentConfirmSaveFormCImageBinding inflate = FragmentConfirmSaveFormCImageBinding.inflate(inflater, viewGroup, false);
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
        getFragmentViewModel().setup(getArgs().getElectionPageId(), getArgs().getImagePath());
    }

    private final void setupBinding() {
        FragmentConfirmSaveFormCImageBinding fragmentConfirmSaveFormCImageBinding = this.binding;
        if (fragmentConfirmSaveFormCImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentConfirmSaveFormCImageBinding = null;
        }
        fragmentConfirmSaveFormCImageBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentConfirmSaveFormCImageBinding.setViewModel(getFragmentViewModel());
        fragmentConfirmSaveFormCImageBinding.setGetElectionPageUseCase(getFragmentViewModel().getGetElectionPageUseCase());
        fragmentConfirmSaveFormCImageBinding.c1ImageViewCard.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmSaveFormCImageFragment.setupBinding$lambda$5$lambda$1(ConfirmSaveFormCImageFragment.this, view);
            }
        });
        fragmentConfirmSaveFormCImageBinding.buttonSave.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmSaveFormCImageFragment.setupBinding$lambda$5$lambda$2(ConfirmSaveFormCImageFragment.this, view);
            }
        });
        fragmentConfirmSaveFormCImageBinding.buttonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmSaveFormCImageFragment.setupBinding$lambda$5$lambda$3(ConfirmSaveFormCImageFragment.this, view);
            }
        });
        fragmentConfirmSaveFormCImageBinding.buttonBack.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmSaveFormCImageFragment.setupBinding$lambda$5$lambda$4(ConfirmSaveFormCImageFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$5$lambda$1(ConfirmSaveFormCImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        String value = this$0.getFragmentViewModel().getPreviewImagePath().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(ConfirmSaveFormCImageFragmentDirections.Companion.actionGlobalPreviewImageFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$5$lambda$2(ConfirmSaveFormCImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirmSave();
    }

    public static final void setupBinding$lambda$5$lambda$3(ConfirmSaveFormCImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConfirmSaveFormCImageFragment confirmSaveFormCImageFragment = this$0;
        androidx.fragment.app.FragmentKt.setFragmentResult(confirmSaveFormCImageFragment, CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to("retake_photo", true)));
        FragmentKt.findNavController(confirmSaveFormCImageFragment).navigateUp();
    }

    public static final void setupBinding$lambda$5$lambda$4(ConfirmSaveFormCImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentKt.findNavController(this$0).navigateUp();
    }

    public final void confirmSave() {
        ConfirmSaveFormCImageFragment confirmSaveFormCImageFragment = this;
        androidx.fragment.app.FragmentKt.setFragmentResult(confirmSaveFormCImageFragment, CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to("save_photo", true)));
        FragmentKt.findNavController(confirmSaveFormCImageFragment).navigateUp();
    }

    private final void setupViewModel() {
        GetElectionPageUseCase getElectionPageUseCase = getFragmentViewModel().getGetElectionPageUseCase();
        getElectionPageUseCase.getElection().observe(getViewLifecycleOwner(), new ConfirmSaveFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<Election, Unit>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$setupViewModel$1$1$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Election election) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Election election) {
                invoke2(election);
                return Unit.INSTANCE;
            }
        }));
        getElectionPageUseCase.getElectionPage().observe(getViewLifecycleOwner(), new ConfirmSaveFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionPage, Unit>() { // from class: org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment$setupViewModel$1$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ElectionPage electionPage) {
                invoke2(electionPage);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ElectionPage electionPage) {
                if (electionPage != null) {
                    ConfirmSaveFormCImageFragment confirmSaveFormCImageFragment = ConfirmSaveFormCImageFragment.this;
                    if (electionPage.isPhotoTaken()) {
                        return;
                    }
                    confirmSaveFormCImageFragment.confirmSave();
                }
            }
        }));
    }

    /* compiled from: ConfirmSaveFormCImageFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageFragment$Companion;", "", "()V", ConfirmSaveFormCImageFragment.CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT, "", "CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT_RETAKE_PHOTO", "CONFIRM_SAVE_IMAGE_FRAGMENT_RESULT_SAVE_PHOTO", "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
