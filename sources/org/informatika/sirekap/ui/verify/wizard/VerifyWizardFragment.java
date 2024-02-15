package org.informatika.sirekap.ui.verify.wizard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
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
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentVerifyWizardBinding;
import org.informatika.sirekap.databinding.ViewVerifyWizardOngoingBinding;
import org.informatika.sirekap.databinding.ViewVerifyWizardStartBinding;

/* compiled from: VerifyWizardFragment.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\u001a\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\"\u001a\u00020\u0014H\u0002J\b\u0010#\u001a\u00020\u0014H\u0002J\b\u0010$\u001a\u00020\u0014H\u0002J\b\u0010%\u001a\u00020\u0014H\u0002J\u0012\u0010&\u001a\u00020\u00142\b\b\u0002\u0010'\u001a\u00020(H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006*"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentVerifyWizardBinding;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "verifyWizardViewModel", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardViewModel;", "getVerifyWizardViewModel", "()Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardViewModel;", "verifyWizardViewModel$delegate", "Lkotlin/Lazy;", "finishChecking", "", "hideKeyboardClearFocus", "nextItemOrFinish", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "saveCorrectedValue", "setupBinding", "setupNavigation", "setupViewModel", "startChecking", "isJump", "", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class VerifyWizardFragment extends Hilt_VerifyWizardFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VWElectionPageFragment";
    public static final String VERIFY_WIZARD_FRAGMENT_RESULT = "VERIFY_WIZARD_FRAGMENT_RESULT";
    public static final String VERIFY_WIZARD_RESULT_ELECTION_PAGE_ID = "VWFR_ELECTION_PAGE_ID";
    public static final String VERIFY_WIZARD_RESULT_ELECTION_PAGE_KIND = "VWFR_ELECTION_PAGE_KIND";
    public static final String VERIFY_WIZARD_RESULT_ELECTION_PEMILIHAN = "VWFR_ELECTION_PEMILIHAN";
    public static final String VERIFY_WIZARD_RESULT_ID_IMAGE = "VWFR_ID_IMAGE";
    private final NavArgsLazy args$delegate;
    private FragmentVerifyWizardBinding binding;
    private OnBackPressedCallback onBackPressedCallback;
    private final Lazy verifyWizardViewModel$delegate;

    public static final void finishChecking$lambda$23(DialogInterface dialogInterface, int i) {
    }

    public VerifyWizardFragment() {
        final VerifyWizardFragment verifyWizardFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.verifyWizardViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifyWizardFragment, Reflection.getOrCreateKotlinClass(VerifyWizardViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(VerifyWizardFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$special$$inlined$navArgs$1
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

    public final VerifyWizardViewModel getVerifyWizardViewModel() {
        return (VerifyWizardViewModel) this.verifyWizardViewModel$delegate.getValue();
    }

    private final VerifyWizardFragmentArgs getArgs() {
        return (VerifyWizardFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentVerifyWizardBinding inflate = FragmentVerifyWizardBinding.inflate(inflater, viewGroup, false);
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
        setupNavigation();
        getVerifyWizardViewModel().getGetElectionPageUseCase().setup(getArgs().getElectionPageId());
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
        FragmentVerifyWizardBinding fragmentVerifyWizardBinding = this.binding;
        if (fragmentVerifyWizardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyWizardBinding = null;
        }
        fragmentVerifyWizardBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentVerifyWizardBinding.setViewModel(getVerifyWizardViewModel());
        ViewVerifyWizardStartBinding viewVerifyWizardStartBinding = fragmentVerifyWizardBinding.viewVerifyWizardStart;
        viewVerifyWizardStartBinding.buttonStartVerification.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$3$lambda$0(VerifyWizardFragment.this, view);
            }
        });
        viewVerifyWizardStartBinding.buttonResumeVerification.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$3$lambda$1(VerifyWizardFragment.this, view);
            }
        });
        viewVerifyWizardStartBinding.buttonRestartStartVerification.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$3$lambda$2(VerifyWizardFragment.this, view);
            }
        });
        final ViewVerifyWizardOngoingBinding viewVerifyWizardOngoingBinding = fragmentVerifyWizardBinding.viewVerifyWizardOngoing;
        viewVerifyWizardOngoingBinding.buttonCheckTrue.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$4(ViewVerifyWizardOngoingBinding.this, this, view);
            }
        });
        viewVerifyWizardOngoingBinding.buttonCheckFalse.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$5(ViewVerifyWizardOngoingBinding.this, this, view);
            }
        });
        viewVerifyWizardOngoingBinding.buttonCheckCancel.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$6(ViewVerifyWizardOngoingBinding.this, this, view);
            }
        });
        viewVerifyWizardOngoingBinding.buttonCheckOk.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$7(VerifyWizardFragment.this, view);
            }
        });
        viewVerifyWizardOngoingBinding.inputCorrection.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean z;
                z = VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$8(VerifyWizardFragment.this, textView, i, keyEvent);
                return z;
            }
        });
        viewVerifyWizardOngoingBinding.buttonBack.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$9(VerifyWizardFragment.this, view);
            }
        });
        viewVerifyWizardOngoingBinding.buttonNext.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$12(VerifyWizardFragment.this, view);
            }
        });
        viewVerifyWizardOngoingBinding.buttonFinish.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyWizardFragment.setupBinding$lambda$15$lambda$14$lambda$13(VerifyWizardFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$15$lambda$3$lambda$0(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startChecking$default(this$0, false, 1, null);
    }

    public static final void setupBinding$lambda$15$lambda$3$lambda$1(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startChecking(true);
    }

    public static final void setupBinding$lambda$15$lambda$3$lambda$2(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startChecking$default(this$0, false, 1, null);
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$4(ViewVerifyWizardOngoingBinding this_apply, VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_apply.buttonCheckTrue.isChecked()) {
            this$0.getVerifyWizardViewModel().checkItem(true, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupBinding$1$2$1$1
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
                    VerifyWizardFragment.this.nextItemOrFinish();
                }
            });
        } else {
            VerifyWizardViewModel.checkItem$default(this$0.getVerifyWizardViewModel(), null, null, 2, null);
        }
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$5(final ViewVerifyWizardOngoingBinding this_apply, VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_apply.buttonCheckFalse.isChecked()) {
            this$0.getVerifyWizardViewModel().checkItem(false, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupBinding$1$2$2$1
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
                    VerifyWizardViewModel verifyWizardViewModel;
                    verifyWizardViewModel = VerifyWizardFragment.this.getVerifyWizardViewModel();
                    Boolean value = verifyWizardViewModel.isCorrectValue().getValue();
                    if (value != null) {
                        ViewVerifyWizardOngoingBinding viewVerifyWizardOngoingBinding = this_apply;
                        VerifyWizardFragment verifyWizardFragment = VerifyWizardFragment.this;
                        if (!value.booleanValue()) {
                            verifyWizardFragment.nextItemOrFinish();
                            return;
                        }
                        viewVerifyWizardOngoingBinding.inputCorrection.requestFocus();
                        Object systemService = verifyWizardFragment.requireContext().getSystemService("input_method");
                        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                        ((InputMethodManager) systemService).showSoftInput(viewVerifyWizardOngoingBinding.inputCorrection, 1);
                    }
                }
            });
        } else {
            VerifyWizardViewModel.checkItem$default(this$0.getVerifyWizardViewModel(), null, null, 2, null);
        }
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$6(ViewVerifyWizardOngoingBinding this_apply, VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.buttonToggleCheckCorrection.clearChecked();
        VerifyWizardViewModel.checkItem$default(this$0.getVerifyWizardViewModel(), null, null, 2, null);
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$7(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.saveCorrectedValue();
    }

    public static final boolean setupBinding$lambda$15$lambda$14$lambda$8(VerifyWizardFragment this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 6) {
            this$0.saveCorrectedValue();
            return true;
        }
        return false;
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$9(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getVerifyWizardViewModel().backItem();
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$12(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Boolean value = this$0.getVerifyWizardViewModel().isCorrectValue().getValue();
        if (value != null) {
            if (value.booleanValue()) {
                FormC1CheckItem value2 = this$0.getVerifyWizardViewModel().getCurrentItem().getValue();
                if (value2 != null) {
                    if (Intrinsics.areEqual((Object) value2.getChecked(), (Object) false)) {
                        this$0.saveCorrectedValue();
                        return;
                    } else {
                        this$0.nextItemOrFinish();
                        return;
                    }
                }
                return;
            }
            this$0.nextItemOrFinish();
        }
    }

    public static final void setupBinding$lambda$15$lambda$14$lambda$13(VerifyWizardFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishChecking();
    }

    private final void setupViewModel() {
        VerifyWizardViewModel verifyWizardViewModel = getVerifyWizardViewModel();
        verifyWizardViewModel.getCurrentItem().observe(getViewLifecycleOwner(), new VerifyWizardFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1CheckItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormC1CheckItem formC1CheckItem) {
                invoke2(formC1CheckItem);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(FormC1CheckItem formC1CheckItem) {
                FragmentVerifyWizardBinding fragmentVerifyWizardBinding;
                FragmentVerifyWizardBinding fragmentVerifyWizardBinding2;
                if (formC1CheckItem != null) {
                    VerifyWizardFragment verifyWizardFragment = VerifyWizardFragment.this;
                    FragmentVerifyWizardBinding fragmentVerifyWizardBinding3 = null;
                    if (formC1CheckItem.getChecked() != null) {
                        fragmentVerifyWizardBinding2 = verifyWizardFragment.binding;
                        if (fragmentVerifyWizardBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentVerifyWizardBinding3 = fragmentVerifyWizardBinding2;
                        }
                        fragmentVerifyWizardBinding3.viewVerifyWizardOngoing.buttonToggleCheck.check(Intrinsics.areEqual((Object) formC1CheckItem.getChecked(), (Object) true) ? R.id.button_check_true : R.id.button_check_false);
                        return;
                    }
                    fragmentVerifyWizardBinding = verifyWizardFragment.binding;
                    if (fragmentVerifyWizardBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentVerifyWizardBinding3 = fragmentVerifyWizardBinding;
                    }
                    fragmentVerifyWizardBinding3.viewVerifyWizardOngoing.buttonToggleCheck.clearChecked();
                }
            }
        }));
        verifyWizardViewModel.isSesuaiPerItem().observe(getViewLifecycleOwner(), new VerifyWizardFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupViewModel$1$2
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Boolean> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Boolean> list) {
                invoke2((List<Boolean>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyWizardViewModel.getVerifyFormC1AdministrationUseCase$app_productionRelease().getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyWizardFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupViewModel$1$3$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyWizardViewModel.getVerifyFormC1TabulationUseCase$app_productionRelease().getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyWizardFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupViewModel$1$4$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyWizardViewModel.getVerifyFormC1TabulationPartaiUseCase$app_productionRelease().getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyWizardFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupViewModel$1$5$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
        verifyWizardViewModel.getVerifyFormC1AdministrationHal2UseCase$app_productionRelease().getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyWizardFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$setupViewModel$1$6$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
    }

    private final void setupNavigation() {
        this.onBackPressedCallback = new VerifyWizardFragment$setupNavigation$1(this);
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBackPressedCallback");
            onBackPressedCallback = null;
        }
        onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback);
    }

    static /* synthetic */ void startChecking$default(VerifyWizardFragment verifyWizardFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        verifyWizardFragment.startChecking(z);
    }

    private final void startChecking(boolean z) {
        getVerifyWizardViewModel().startChecking(z);
    }

    public final void nextItemOrFinish() {
        Boolean value = getVerifyWizardViewModel().isLastItem().getValue();
        if (value != null) {
            if (value.booleanValue()) {
                finishChecking();
            } else {
                getVerifyWizardViewModel().nextItem();
            }
        }
    }

    private final void saveCorrectedValue() {
        hideKeyboardClearFocus();
        FragmentVerifyWizardBinding fragmentVerifyWizardBinding = this.binding;
        if (fragmentVerifyWizardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyWizardBinding = null;
        }
        fragmentVerifyWizardBinding.viewVerifyWizardOngoing.buttonToggleCheckCorrection.clearChecked();
        FormC1CheckItem value = getVerifyWizardViewModel().getCurrentItem().getValue();
        if (value != null) {
            if (value.needCorrection() && value.isCorrectedValueValid()) {
                getVerifyWizardViewModel().correctItem(value.getCorrectedValueInt(), new Function0<Unit>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$saveCorrectedValue$1$1
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
                        VerifyWizardFragment.this.nextItemOrFinish();
                    }
                });
            } else {
                Toast.makeText(getContext(), getString(R.string.prompt_correction_invalid), 0).show();
            }
        }
    }

    private final void hideKeyboardClearFocus() {
        FragmentVerifyWizardBinding fragmentVerifyWizardBinding = this.binding;
        FragmentVerifyWizardBinding fragmentVerifyWizardBinding2 = null;
        if (fragmentVerifyWizardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyWizardBinding = null;
        }
        fragmentVerifyWizardBinding.viewVerifyWizardOngoing.inputCorrection.clearFocus();
        Object systemService = requireContext().getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        FragmentVerifyWizardBinding fragmentVerifyWizardBinding3 = this.binding;
        if (fragmentVerifyWizardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVerifyWizardBinding2 = fragmentVerifyWizardBinding3;
        }
        inputMethodManager.hideSoftInputFromWindow(fragmentVerifyWizardBinding2.viewVerifyWizardOngoing.inputCorrection.getWindowToken(), 0);
    }

    private final void finishChecking() {
        new MaterialAlertDialogBuilder(requireContext()).setMessage((CharSequence) getString(R.string.verify_wizard_finish_dialog_message)).setNegativeButton((CharSequence) getString(R.string.action_not_yet), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                VerifyWizardFragment.finishChecking$lambda$23(dialogInterface, i);
            }
        }).setPositiveButton((CharSequence) getString(R.string.action_already), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                VerifyWizardFragment.finishChecking$lambda$24(VerifyWizardFragment.this, dialogInterface, i);
            }
        }).show();
    }

    public static final void finishChecking$lambda$24(VerifyWizardFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentKt.setFragmentResult(this$0, VERIFY_WIZARD_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(VERIFY_WIZARD_RESULT_ID_IMAGE, this$0.getArgs().getIdImage()), TuplesKt.to(VERIFY_WIZARD_RESULT_ELECTION_PAGE_KIND, Integer.valueOf(this$0.getArgs().getElectionPageKind())), TuplesKt.to(VERIFY_WIZARD_RESULT_ELECTION_PAGE_ID, this$0.getArgs().getElectionPageId()), TuplesKt.to(VERIFY_WIZARD_RESULT_ELECTION_PEMILIHAN, this$0.getArgs().getElectionPemilihan())));
        try {
            androidx.navigation.fragment.FragmentKt.findNavController(this$0).navigateUp();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    /* compiled from: VerifyWizardFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", VerifyWizardFragment.VERIFY_WIZARD_FRAGMENT_RESULT, "VERIFY_WIZARD_RESULT_ELECTION_PAGE_ID", "VERIFY_WIZARD_RESULT_ELECTION_PAGE_KIND", "VERIFY_WIZARD_RESULT_ELECTION_PEMILIHAN", "VERIFY_WIZARD_RESULT_ID_IMAGE", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
