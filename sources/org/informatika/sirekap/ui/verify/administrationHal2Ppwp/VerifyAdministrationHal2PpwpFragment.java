package org.informatika.sirekap.ui.verify.administrationHal2Ppwp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.MutableLiveData;
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
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBinding;
import org.informatika.sirekap.databinding.DialogVerifyBodyBinding;
import org.informatika.sirekap.databinding.FragmentVerifyAdministrationHal2PpwpBinding;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.model.FormConfig;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.UiUtil;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.sendImage.SendImageFragment;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1AdministrationAdapter;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* compiled from: VerifyAdministrationHal2PpwpFragment.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006("}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapterPerolehanSuara", "Lorg/informatika/sirekap/ui/verify/FormC1AdministrationAdapter;", "adapterSurat", "args", "Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentVerifyAdministrationHal2PpwpBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "retakePhoto", "isManual", "", "setupBinding", "setupViewModel", "showCorrectionDialog", "item", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2PpwpFragment extends Hilt_VerifyAdministrationHal2PpwpFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VerifyAdmHal2PpwpFragment";
    private FormC1AdministrationAdapter adapterPerolehanSuara;
    private FormC1AdministrationAdapter adapterSurat;
    private final NavArgsLazy args$delegate;
    private FragmentVerifyAdministrationHal2PpwpBinding binding;
    private final Lazy fragmentViewModel$delegate;

    public VerifyAdministrationHal2PpwpFragment() {
        final VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(verifyAdministrationHal2PpwpFragment, Reflection.getOrCreateKotlinClass(VerifyAdministrationHal2PpwpViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(VerifyAdministrationHal2PpwpFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$special$$inlined$navArgs$1
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

    public final VerifyAdministrationHal2PpwpViewModel getFragmentViewModel() {
        return (VerifyAdministrationHal2PpwpViewModel) this.fragmentViewModel$delegate.getValue();
    }

    public final VerifyAdministrationHal2PpwpFragmentArgs getArgs() {
        return (VerifyAdministrationHal2PpwpFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentVerifyAdministrationHal2PpwpBinding inflate = FragmentVerifyAdministrationHal2PpwpBinding.inflate(inflater, viewGroup, false);
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
        getFragmentViewModel().getGetElectionPageUseCase().setup(getArgs().getElectionPageId());
    }

    private final void setupBinding() {
        FragmentVerifyAdministrationHal2PpwpBinding fragmentVerifyAdministrationHal2PpwpBinding = this.binding;
        FormC1AdministrationAdapter formC1AdministrationAdapter = null;
        if (fragmentVerifyAdministrationHal2PpwpBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyAdministrationHal2PpwpBinding = null;
        }
        fragmentVerifyAdministrationHal2PpwpBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentVerifyAdministrationHal2PpwpBinding.setViewModel(getFragmentViewModel());
        fragmentVerifyAdministrationHal2PpwpBinding.setGetElectionPageUseCase(getFragmentViewModel().getGetElectionPageUseCase());
        fragmentVerifyAdministrationHal2PpwpBinding.c1ImageViewCard.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationHal2PpwpFragment.setupBinding$lambda$8$lambda$1(VerifyAdministrationHal2PpwpFragment.this, view);
            }
        });
        fragmentVerifyAdministrationHal2PpwpBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationHal2PpwpFragment.setupBinding$lambda$8$lambda$2(VerifyAdministrationHal2PpwpFragment.this, view);
            }
        });
        fragmentVerifyAdministrationHal2PpwpBinding.buttonContinueVerify.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationHal2PpwpFragment.setupBinding$lambda$8$lambda$3(VerifyAdministrationHal2PpwpFragment.this, view);
            }
        });
        fragmentVerifyAdministrationHal2PpwpBinding.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerifyAdministrationHal2PpwpFragment.setupBinding$lambda$8$lambda$7(VerifyAdministrationHal2PpwpFragment.this, view);
            }
        });
        this.adapterPerolehanSuara = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupBinding$1$5
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
                VerifyAdministrationHal2PpwpFragment.this.showCorrectionDialog(it);
            }
        });
        RecyclerView recyclerView = fragmentVerifyAdministrationHal2PpwpBinding.recyclerViewDataPerolehanSuara;
        FormC1AdministrationAdapter formC1AdministrationAdapter2 = this.adapterPerolehanSuara;
        if (formC1AdministrationAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterPerolehanSuara");
            formC1AdministrationAdapter2 = null;
        }
        recyclerView.setAdapter(formC1AdministrationAdapter2);
        this.adapterSurat = new FormC1AdministrationAdapter(true, new Function1<FormC1ListItem, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupBinding$1$6
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
                VerifyAdministrationHal2PpwpFragment.this.showCorrectionDialog(it);
            }
        });
        RecyclerView recyclerView2 = fragmentVerifyAdministrationHal2PpwpBinding.recyclerViewDataSurat;
        FormC1AdministrationAdapter formC1AdministrationAdapter3 = this.adapterSurat;
        if (formC1AdministrationAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterSurat");
        } else {
            formC1AdministrationAdapter = formC1AdministrationAdapter3;
        }
        recyclerView2.setAdapter(formC1AdministrationAdapter);
    }

    public static final void setupBinding$lambda$8$lambda$1(VerifyAdministrationHal2PpwpFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        String value = this$0.getFragmentViewModel().getPreviewImagePath().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(VerifyAdministrationHal2PpwpFragmentDirections.Companion.actionVerifyAdministrationHal2PpwpFragmentToPreviewImageFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$8$lambda$2(VerifyAdministrationHal2PpwpFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("VerifyAdmHal2PpwpFragment: User clicks 'Ulang Foto' button");
        this$0.retakePhoto(false);
    }

    public static final void setupBinding$lambda$8$lambda$3(VerifyAdministrationHal2PpwpFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFragmentViewModel().continueVerify();
    }

    public static final void setupBinding$lambda$8$lambda$7(VerifyAdministrationHal2PpwpFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("VerifyAdmHal2PpwpFragment Click 'Submit'");
        Boolean value = this$0.getFragmentViewModel().isCheckAllTrue().getValue();
        if (value != null) {
            this$0.getFragmentViewModel().getVerifyForm().setVerifyOption(value.booleanValue());
            DialogVerifyBodyBinding inflate = DialogVerifyBodyBinding.inflate(this$0.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this$0.requireContext());
            inflate.setLifecycleOwner(this$0.getViewLifecycleOwner());
            inflate.setViewModel(this$0.getFragmentViewModel());
            inflate.setHideCommentField(true);
            materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) this$0.getString(R.string.submit_verify_administration_dialog_message));
            final AlertDialog create = materialAlertDialogBuilder.create();
            Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…               }.create()");
            create.show();
            inflate.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VerifyAdministrationHal2PpwpFragment.setupBinding$lambda$8$lambda$7$lambda$6$lambda$5(VerifyAdministrationHal2PpwpFragment.this, create, view2);
                }
            });
        }
    }

    public static final void setupBinding$lambda$8$lambda$7$lambda$6$lambda$5(VerifyAdministrationHal2PpwpFragment this$0, AlertDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        VerifyAdministrationHal2PpwpViewModel fragmentViewModel = this$0.getFragmentViewModel();
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        fragmentViewModel.submitVerification(requireContext, dialog);
    }

    private final void retakePhoto(boolean z) {
        VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment = this;
        androidx.fragment.app.FragmentKt.setFragmentResult(verifyAdministrationHal2PpwpFragment, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO, true), TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE, new Gson().toJson(getFragmentViewModel().getGetElectionPageUseCase().getElectionPage().getValue())), TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_IS_MANUAL, Boolean.valueOf(z))));
        FragmentKt.findNavController(verifyAdministrationHal2PpwpFragment).navigateUp();
    }

    private final void setupViewModel() {
        VerifyAdministrationHal2PpwpViewModel fragmentViewModel = getFragmentViewModel();
        fragmentViewModel.getFormC1AdministrationHal2PpwpResource().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends FormC1AdministrationHal2PpwpComplete>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends FormC1AdministrationHal2PpwpComplete> resource) {
                invoke2((Resource<FormC1AdministrationHal2PpwpComplete>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<FormC1AdministrationHal2PpwpComplete> resource) {
                if (resource != null) {
                    VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment = VerifyAdministrationHal2PpwpFragment.this;
                    if (resource.getSuccess() == ResourceStatus.ERROR) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception("VerifyAdmHal2PpwpFragment " + resource.getError()));
                        String string = verifyAdministrationHal2PpwpFragment.getString(R.string.fetch_formchasil_message_error_snackbar, resource.getError());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                        BaseFragment.showSnackBar$default(verifyAdministrationHal2PpwpFragment, string, null, null, 6, null);
                        return;
                    }
                    resource.getSuccess();
                    ResourceStatus resourceStatus = ResourceStatus.LOADING;
                }
            }
        }));
        fragmentViewModel.getFormC1Kesesuaian().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1Kesesuaian, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$2
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
                if (formC1Kesesuaian != null) {
                    VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment = VerifyAdministrationHal2PpwpFragment.this;
                    formC1AdministrationAdapter = verifyAdministrationHal2PpwpFragment.adapterSurat;
                    FormC1AdministrationAdapter formC1AdministrationAdapter3 = null;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterSurat");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.done();
                    formC1AdministrationAdapter2 = verifyAdministrationHal2PpwpFragment.adapterPerolehanSuara;
                    if (formC1AdministrationAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPerolehanSuara");
                    } else {
                        formC1AdministrationAdapter3 = formC1AdministrationAdapter2;
                    }
                    formC1AdministrationAdapter3.done();
                }
            }
        }));
        fragmentViewModel.getGetElectionPageUseCase().getElectionPageWithRelation().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionPageWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$3$1
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
                VerifyAdministrationHal2PpwpViewModel fragmentViewModel2;
                VerifyAdministrationHal2PpwpFragmentArgs args;
                VerifyAdministrationHal2PpwpFragmentArgs args2;
                VerifyAdministrationHal2PpwpViewModel fragmentViewModel3;
                VerifyAdministrationHal2PpwpViewModel fragmentViewModel4;
                if (electionPageWithRelation != null) {
                    VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment = VerifyAdministrationHal2PpwpFragment.this;
                    fragmentViewModel2 = verifyAdministrationHal2PpwpFragment.getFragmentViewModel();
                    args = verifyAdministrationHal2PpwpFragment.getArgs();
                    String idImage = args.getIdImage();
                    args2 = verifyAdministrationHal2PpwpFragment.getArgs();
                    fragmentViewModel2.setup(idImage, args2.getElectionPageId(), electionPageWithRelation.getElection().getPemilihan(), electionPageWithRelation.getElection().getTps().getKodeTps());
                    fragmentViewModel3 = verifyAdministrationHal2PpwpFragment.getFragmentViewModel();
                    if (fragmentViewModel3.getPreviewImagePath().getValue() == null) {
                        fragmentViewModel4 = verifyAdministrationHal2PpwpFragment.getFragmentViewModel();
                        fragmentViewModel4.getPreviewImagePath().postValue(electionPageWithRelation.getElectionPage().getCroppedPhotoPath());
                    }
                }
            }
        }));
        fragmentViewModel.getTablePerolehanSuara().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$4
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
                    formC1AdministrationAdapter = VerifyAdministrationHal2PpwpFragment.this.adapterPerolehanSuara;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterPerolehanSuara");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        fragmentViewModel.getTableSurat().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<FormC1ListItem>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$5
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
                    formC1AdministrationAdapter = VerifyAdministrationHal2PpwpFragment.this.adapterSurat;
                    if (formC1AdministrationAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterSurat");
                        formC1AdministrationAdapter = null;
                    }
                    formC1AdministrationAdapter.submitList(list);
                }
            }
        }));
        fragmentViewModel.isSesuaiPerItem().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$6
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Boolean> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Boolean> list) {
                invoke2((List<Boolean>) list);
                return Unit.INSTANCE;
            }
        }));
        fragmentViewModel.getKoreksiPerItem().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Integer>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$7
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list) {
                invoke2((List<Integer>) list);
                return Unit.INSTANCE;
            }
        }));
        fragmentViewModel.isCheckAllTrue().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$8
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }
        }));
        fragmentViewModel.getFormC1KesesuaianAdministrationHal2().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<FormC1KesesuaianAdministrationHal2, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$9
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
                invoke2(formC1KesesuaianAdministrationHal2);
                return Unit.INSTANCE;
            }
        }));
        fragmentViewModel.getSubmittedFormResource().observe(getViewLifecycleOwner(), new VerifyAdministrationHal2PpwpFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends FormC1AdministrationComplete>, Unit>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$setupViewModel$1$10
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
                    VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment = VerifyAdministrationHal2PpwpFragment.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        String string = verifyAdministrationHal2PpwpFragment.getString(R.string.verify_message_success_snackbar);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.verif…message_success_snackbar)");
                        BaseFragment.showSnackBar$default(verifyAdministrationHal2PpwpFragment, string, null, null, 6, null);
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String string2 = verifyAdministrationHal2PpwpFragment.getString(R.string.verify_message_error_snackbar, resource.getError());
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.verif…error_snackbar, it.error)");
                        BaseFragment.showSnackBar$default(verifyAdministrationHal2PpwpFragment, string2, null, null, 6, null);
                    }
                }
            }
        }));
    }

    public final void showCorrectionDialog(final FormC1ListItem formC1ListItem) {
        FormConfig config$default;
        Bitmap decodeFile;
        if (formC1ListItem.isChecked()) {
            getFragmentViewModel().setupCheckDialog(!Intrinsics.areEqual((Object) formC1ListItem.getCheckL(), (Object) true), !Intrinsics.areEqual((Object) formC1ListItem.getCheckP(), (Object) true), !Intrinsics.areEqual((Object) formC1ListItem.getCheckTotal(), (Object) true), formC1ListItem.getCorrectedL() != null ? formC1ListItem.getCorrectedL().toString() : "", formC1ListItem.getCorrectedP() != null ? formC1ListItem.getCorrectedP().toString() : "", formC1ListItem.getCorrectedTotal() != null ? formC1ListItem.getCorrectedTotal().toString() : "");
        } else {
            BaseVerifyViewModel.setupCheckDialog$default(getFragmentViewModel(), false, false, false, null, null, null, 63, null);
        }
        final DialogCheckFormC1ListItemBinding inflate = DialogCheckFormC1ListItemBinding.inflate(getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        inflate.setLifecycleOwner(getViewLifecycleOwner());
        inflate.setShowCorrection(true);
        inflate.setItem(formC1ListItem);
        inflate.setViewModel(getFragmentViewModel());
        try {
            getFragmentViewModel().resetImage();
            FormConfig.Companion companion = FormConfig.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String electionType = formC1ListItem.getElectionType();
            config$default = FormConfig.Companion.getConfig$default(companion, requireContext, FormConfig.FORM_ADMIN2, electionType == null ? "" : electionType, Intrinsics.areEqual((Object) formC1ListItem.isLN(), (Object) true), Intrinsics.areEqual((Object) formC1ListItem.isPOS(), (Object) true), 0, 32, null);
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
            getFragmentViewModel().getKesesuaianSliceError().postValue(message);
            inflate.kesesuaianSliceErrorShowDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationHal2PpwpFragment.showCorrectionDialog$lambda$19$lambda$11(VerifyAdministrationHal2PpwpFragment.this, view);
                }
            });
            inflate.kesesuaianSliceErrorHideDetail.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationHal2PpwpFragment.showCorrectionDialog$lambda$19$lambda$12(VerifyAdministrationHal2PpwpFragment.this, view);
                }
            });
        }
        if (decodeFile != null) {
            String id2 = formC1ListItem.getId();
            Integer no = formC1ListItem.getNo();
            getFragmentViewModel().getPreviewKesesuaianBitmap().postValue(FormConfig.getKesesuaianSlicedMat$default(config$default, FormConfig.VISION_TYPE_ADMIN2, id2, decodeFile, Integer.valueOf(no != null ? no.intValue() : 0), 0, 16, null));
            inflate.rowL.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationHal2PpwpFragment.showCorrectionDialog$lambda$19$lambda$14(VerifyAdministrationHal2PpwpFragment.this, inflate, view);
                }
            });
            inflate.rowP.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationHal2PpwpFragment.showCorrectionDialog$lambda$19$lambda$16(VerifyAdministrationHal2PpwpFragment.this, inflate, view);
                }
            });
            inflate.rowTotal.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationHal2PpwpFragment.showCorrectionDialog$lambda$19$lambda$18(VerifyAdministrationHal2PpwpFragment.this, inflate, view);
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
            inflate.buttonSave.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VerifyAdministrationHal2PpwpFragment.showCorrectionDialog$lambda$23(DialogCheckFormC1ListItemBinding.this, this, formC1ListItem, create, view);
                }
            });
            create.show();
            return;
        }
        throw new Exception("Image Not Found");
    }

    public static final void showCorrectionDialog$lambda$19$lambda$11(VerifyAdministrationHal2PpwpFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFragmentViewModel().toggleKesesuaian();
    }

    public static final void showCorrectionDialog$lambda$19$lambda$12(VerifyAdministrationHal2PpwpFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFragmentViewModel().toggleKesesuaian();
    }

    public static final void showCorrectionDialog$lambda$19$lambda$14(VerifyAdministrationHal2PpwpFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getFragmentViewModel().isWrongL().getValue();
        if (value != null) {
            this$0.getFragmentViewModel().isWrongL().postValue(Boolean.valueOf(!value.booleanValue()));
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

    public static final void showCorrectionDialog$lambda$19$lambda$16(VerifyAdministrationHal2PpwpFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getFragmentViewModel().isWrongP().getValue();
        if (value != null) {
            this$0.getFragmentViewModel().isWrongP().postValue(Boolean.valueOf(!value.booleanValue()));
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

    public static final void showCorrectionDialog$lambda$19$lambda$18(VerifyAdministrationHal2PpwpFragment this$0, DialogCheckFormC1ListItemBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Boolean value = this$0.getFragmentViewModel().isWrongTotal().getValue();
        if (value != null) {
            this$0.getFragmentViewModel().isWrongTotal().postValue(Boolean.valueOf(!value.booleanValue()));
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

    public static final void showCorrectionDialog$lambda$23(DialogCheckFormC1ListItemBinding binding, VerifyAdministrationHal2PpwpFragment this$0, FormC1ListItem item, AlertDialog dialog, View view) {
        Boolean isWrongP;
        Boolean isWrongTotal;
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        String valueOf = String.valueOf(binding.inputCorrectionL.getText());
        String valueOf2 = String.valueOf(binding.inputCorrectionP.getText());
        String valueOf3 = String.valueOf(binding.inputCorrectionTotal.getText());
        Boolean value = this$0.getFragmentViewModel().isWrongL().getValue();
        if (value == null || (isWrongP = this$0.getFragmentViewModel().isWrongP().getValue()) == null || (isWrongTotal = this$0.getFragmentViewModel().isWrongTotal().getValue()) == null) {
            return;
        }
        if (!value.booleanValue() || !StringsKt.isBlank(valueOf) || item.getL() == null) {
            Intrinsics.checkNotNullExpressionValue(isWrongP, "isWrongP");
            if (!isWrongP.booleanValue() || !StringsKt.isBlank(valueOf2) || item.getP() == null) {
                Intrinsics.checkNotNullExpressionValue(isWrongTotal, "isWrongTotal");
                if (!isWrongTotal.booleanValue() || !StringsKt.isBlank(valueOf3)) {
                    MutableLiveData<String> correctedL = this$0.getFragmentViewModel().getCorrectedL();
                    if (!value.booleanValue()) {
                        valueOf = null;
                    }
                    correctedL.setValue(valueOf);
                    MutableLiveData<String> correctedP = this$0.getFragmentViewModel().getCorrectedP();
                    if (!isWrongP.booleanValue()) {
                        valueOf2 = null;
                    }
                    correctedP.setValue(valueOf2);
                    MutableLiveData<String> correctedTotal = this$0.getFragmentViewModel().getCorrectedTotal();
                    if (!isWrongTotal.booleanValue()) {
                        valueOf3 = null;
                    }
                    correctedTotal.setValue(valueOf3);
                    this$0.getFragmentViewModel().saveChecked(item);
                    dialog.dismiss();
                    return;
                }
            }
        }
        Toast.makeText(this$0.getContext(), this$0.getString(R.string.prompt_correction_invalid), 0).show();
    }

    /* compiled from: VerifyAdministrationHal2PpwpFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
