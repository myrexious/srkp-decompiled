package org.informatika.sirekap.ui.aprilTagConflict;

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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentAprilTagConflictBinding;

/* compiled from: AprilTagConflictFragment.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentAprilTagConflictBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupBinding", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class AprilTagConflictFragment extends Hilt_AprilTagConflictFragment {
    public static final String APRIL_TAG_CONFLICT_FRAGMENT_RESULT = "APRIL_TAG_CONFLICT_FRAGMENT_RESULT";
    public static final String APRIL_TAG_CONFLICT_FRAGMENT_RESULT_ELECTION_PAGE_ID = "election_page_id";
    public static final String APRIL_TAG_CONFLICT_FRAGMENT_RESULT_RETAKE_PHOTO = "retake_photo";
    public static final String APRIL_TAG_CONFLICT_FRAGMENT_RESULT_SAVE_PHOTO = "save_photo";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AprilTagConflictF";
    private final NavArgsLazy args$delegate;
    private FragmentAprilTagConflictBinding binding;
    private final Lazy fragmentViewModel$delegate;

    public AprilTagConflictFragment() {
        final AprilTagConflictFragment aprilTagConflictFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(aprilTagConflictFragment, Reflection.getOrCreateKotlinClass(AprilTagConflictViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(AprilTagConflictFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$special$$inlined$navArgs$1
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

    private final AprilTagConflictViewModel getFragmentViewModel() {
        return (AprilTagConflictViewModel) this.fragmentViewModel$delegate.getValue();
    }

    private final AprilTagConflictFragmentArgs getArgs() {
        return (AprilTagConflictFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentAprilTagConflictBinding inflate = FragmentAprilTagConflictBinding.inflate(inflater, viewGroup, false);
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
        getFragmentViewModel().setup(getArgs().getImagePath(), getArgs().getElectionPageIdManual(), getArgs().getElectionPageIdAprilTag());
    }

    private final void setupBinding() {
        FragmentAprilTagConflictBinding fragmentAprilTagConflictBinding = this.binding;
        if (fragmentAprilTagConflictBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAprilTagConflictBinding = null;
        }
        fragmentAprilTagConflictBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentAprilTagConflictBinding.setViewModel(getFragmentViewModel());
        fragmentAprilTagConflictBinding.setGetElectionPageUseCaseManual(getFragmentViewModel().getGetElectionPageUseCaseManual());
        fragmentAprilTagConflictBinding.setGetElectionPageUseCaseAprilTag(getFragmentViewModel().getGetElectionPageUseCaseAprilTag());
        fragmentAprilTagConflictBinding.c1ImageViewCard.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AprilTagConflictFragment.setupBinding$lambda$4$lambda$1(AprilTagConflictFragment.this, view);
            }
        });
        fragmentAprilTagConflictBinding.buttonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AprilTagConflictFragment.setupBinding$lambda$4$lambda$2(AprilTagConflictFragment.this, view);
            }
        });
        fragmentAprilTagConflictBinding.buttonContinue.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AprilTagConflictFragment.setupBinding$lambda$4$lambda$3(AprilTagConflictFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$4$lambda$1(AprilTagConflictFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        String value = this$0.getFragmentViewModel().getPreviewImagePath().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(AprilTagConflictFragmentDirections.Companion.actionGlobalPreviewImageFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$4$lambda$2(AprilTagConflictFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AprilTagConflictFragment aprilTagConflictFragment = this$0;
        androidx.fragment.app.FragmentKt.setFragmentResult(aprilTagConflictFragment, APRIL_TAG_CONFLICT_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to("retake_photo", true)));
        FragmentKt.findNavController(aprilTagConflictFragment).navigateUp();
    }

    public static final void setupBinding$lambda$4$lambda$3(AprilTagConflictFragment this$0, View view) {
        String electionPageIdAprilTag;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual((Object) this$0.getFragmentViewModel().getCanSubmit().getValue(), (Object) true)) {
            Integer value = this$0.getFragmentViewModel().getSelectedElectionPage().getValue();
            int i = R.id.radio_election_page_manual;
            if (value != null && value.intValue() == i) {
                electionPageIdAprilTag = this$0.getArgs().getElectionPageIdManual();
            } else {
                electionPageIdAprilTag = (value != null && value.intValue() == R.id.radio_election_page_april_tag) ? this$0.getArgs().getElectionPageIdAprilTag() : null;
            }
            AprilTagConflictFragment aprilTagConflictFragment = this$0;
            androidx.fragment.app.FragmentKt.setFragmentResult(aprilTagConflictFragment, APRIL_TAG_CONFLICT_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to("save_photo", true), TuplesKt.to("election_page_id", electionPageIdAprilTag)));
            FragmentKt.findNavController(aprilTagConflictFragment).navigateUp();
        }
    }

    private final void setupViewModel() {
        getFragmentViewModel();
    }

    /* compiled from: AprilTagConflictFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictFragment$Companion;", "", "()V", AprilTagConflictFragment.APRIL_TAG_CONFLICT_FRAGMENT_RESULT, "", "APRIL_TAG_CONFLICT_FRAGMENT_RESULT_ELECTION_PAGE_ID", "APRIL_TAG_CONFLICT_FRAGMENT_RESULT_RETAKE_PHOTO", "APRIL_TAG_CONFLICT_FRAGMENT_RESULT_SAVE_PHOTO", "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
