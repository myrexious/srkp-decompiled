package org.informatika.sirekap.ui.sendImage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentSendImageBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.sendImage.SendImageFragmentDirections;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: SendImageFragment.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010&\u001a\u00020\u001aH\u0002J\b\u0010'\u001a\u00020\u001aH\u0002J\b\u0010(\u001a\u00020\u001aH\u0002J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\u001aH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017¨\u0006."}, d2 = {"Lorg/informatika/sirekap/ui/sendImage/SendImageFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/sendImage/SendImageFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/sendImage/SendImageFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentSendImageBinding;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "sendImageViewModel", "Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel;", "getSendImageViewModel", "()Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel;", "sendImageViewModel$delegate", "deleteFormCImage", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupLaunchers", "setupViewModel", "trySendPhoto", "isOffline", "", "trySendPhotoOnline", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class SendImageFragment extends Hilt_SendImageFragment {
    public static final Companion Companion = new Companion(null);
    public static final String SEND_IMAGE_FRAGMENT_RESULT = "SEND_IMAGE_FRAGMENT_RESULT";
    public static final String SEND_IMAGE_RESULT_TYPE_DELETE_SUCCESS = "SIFR_DELETE_SUCCESS";
    public static final String SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO = "SIFR_RETAKE_PHOTO";
    public static final String SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE = "SIFR_RETAKE_PHOTO_EL_PAGE";
    public static final String SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_IS_MANUAL = "SIFR_RETAKE_PHOTO_IS_MANUAL";
    public static final String SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS = "SIFR_SEND_SUCCESS";
    public static final String SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS_IMAGE_DESC = "SIFR_SEND_SUCCESS_IMG_DESC";
    private static final String TAG = "SendImageFragment";
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentSendImageBinding binding;
    private final Lazy mainViewModel$delegate;
    private final Lazy sendImageViewModel$delegate;

    public static final void deleteFormCImage$lambda$10(DialogInterface dialogInterface, int i) {
    }

    public SendImageFragment() {
        final SendImageFragment sendImageFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.sendImageViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(sendImageFragment, Reflection.getOrCreateKotlinClass(SendImageViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SendImageFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$navArgs$1
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(sendImageFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = sendImageFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$special$$inlined$activityViewModels$default$3
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

    private final SendImageViewModel getSendImageViewModel() {
        return (SendImageViewModel) this.sendImageViewModel$delegate.getValue();
    }

    private final SendImageFragmentArgs getArgs() {
        return (SendImageFragmentArgs) this.args$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SendImageFragment.setupLaunchers$lambda$0(SendImageFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
    }

    public static final void setupLaunchers$lambda$0(SendImageFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$setupLaunchers$1$1
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
                BaseFragment.showSnackBar$default(SendImageFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Kirim' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$setupLaunchers$1$2
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
                BaseFragment.showSnackBar$default(SendImageFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSendImageBinding inflate = FragmentSendImageBinding.inflate(inflater, viewGroup, false);
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
        getSendImageViewModel().setup(getArgs().getElectionPageId());
    }

    private final void setupBinding() {
        FragmentSendImageBinding fragmentSendImageBinding = this.binding;
        if (fragmentSendImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSendImageBinding = null;
        }
        fragmentSendImageBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentSendImageBinding.setViewModel(getSendImageViewModel());
        fragmentSendImageBinding.setGetElectionPageUseCase(getSendImageViewModel().getGetElectionPageUseCase());
        fragmentSendImageBinding.cardImage.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendImageFragment.setupBinding$lambda$6$lambda$2(SendImageFragment.this, view);
            }
        });
        fragmentSendImageBinding.buttonSendPhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendImageFragment.setupBinding$lambda$6$lambda$3(SendImageFragment.this, view);
            }
        });
        fragmentSendImageBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendImageFragment.setupBinding$lambda$6$lambda$4(SendImageFragment.this, view);
            }
        });
        fragmentSendImageBinding.buttonDeletePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendImageFragment.setupBinding$lambda$6$lambda$5(SendImageFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$6$lambda$2(SendImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SendImageFragment: User clicks 'Preview Image' card");
        ElectionPage value = this$0.getSendImageViewModel().getGetElectionPageUseCase().getElectionPage().getValue();
        if (value != null) {
            try {
                NavController findNavController = FragmentKt.findNavController(this$0);
                SendImageFragmentDirections.Companion companion = SendImageFragmentDirections.Companion;
                String croppedPhotoPath = value.getCroppedPhotoPath();
                if (croppedPhotoPath == null) {
                    croppedPhotoPath = "";
                }
                findNavController.navigate(companion.actionSendImageFragmentToPreviewImageFragment(croppedPhotoPath));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$6$lambda$3(SendImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SendImageFragment: User clicks 'Kirim' button");
        this$0.getMainViewModel().getAuthRequestUseCase().isLoading().postValue(true);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new SendImageFragment$setupBinding$1$2$1(this$0, null), 2, null);
    }

    public static final void setupBinding$lambda$6$lambda$4(SendImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SendImageFragment: User clicks 'Ulang Foto' button");
        SendImageFragment sendImageFragment = this$0;
        androidx.fragment.app.FragmentKt.setFragmentResult(sendImageFragment, SEND_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO, true), TuplesKt.to(SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE, new Gson().toJson(this$0.getSendImageViewModel().getGetElectionPageUseCase().getElectionPage().getValue()))));
        FragmentKt.findNavController(sendImageFragment).navigateUp();
    }

    public static final void setupBinding$lambda$6$lambda$5(SendImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseCrashlytics.getInstance().log("SendImageFragment: User clicks 'Hapus Foto' button");
        this$0.deleteFormCImage();
    }

    public final void trySendPhotoOnline() {
        if (getMainViewModel().getAuthRequestUseCase().isLocalTokenExpired()) {
            AuthRequestUseCase authRequestUseCase = getMainViewModel().getAuthRequestUseCase();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$trySendPhotoOnline$1
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
                public final void invoke2(ActiveProfile it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    SendImageFragment.this.trySendPhoto(false);
                }
            }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$trySendPhotoOnline$2
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
                    Toast.makeText(SendImageFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                    try {
                        mainViewModel = SendImageFragment.this.getMainViewModel();
                        AuthRequestUseCase authRequestUseCase2 = mainViewModel.getAuthRequestUseCase();
                        Context requireContext2 = SendImageFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        activityResultLauncher = SendImageFragment.this.authRequestLauncher;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                            activityResultLauncher = null;
                        }
                        authRequestUseCase2.start(requireContext2, activityResultLauncher);
                    } catch (ActivityNotFoundException e) {
                        FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                        SendImageFragment sendImageFragment = SendImageFragment.this;
                        String string = sendImageFragment.getString(R.string.key_setup_error_browser_not_found);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                        BaseFragment.showSnackBar$default(sendImageFragment, string, null, null, 6, null);
                    }
                }
            });
            return;
        }
        trySendPhoto(false);
    }

    public final void trySendPhoto(boolean z) {
        String str;
        getSendImageViewModel().startSendFormCImageUseCase(z);
        ElectionPageWithRelation value = getSendImageViewModel().getGetElectionPageUseCase().getElectionPageWithRelation().getValue();
        if (value != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            str = value.getImageDescription(requireContext);
        } else {
            str = "";
        }
        SendImageFragment sendImageFragment = this;
        androidx.fragment.app.FragmentKt.setFragmentResult(sendImageFragment, SEND_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS, true), TuplesKt.to(SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS_IMAGE_DESC, str)));
        FragmentKt.findNavController(sendImageFragment).navigateUp();
    }

    private final void setupViewModel() {
        final SendImageViewModel sendImageViewModel = getSendImageViewModel();
        GetElectionPageUseCase getElectionPageUseCase = sendImageViewModel.getGetElectionPageUseCase();
        getElectionPageUseCase.getElectionWithRelation().observe(getViewLifecycleOwner(), new SendImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$setupViewModel$1$1$1
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ElectionWithRelation electionWithRelation) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ElectionWithRelation electionWithRelation) {
                invoke2(electionWithRelation);
                return Unit.INSTANCE;
            }
        }));
        getElectionPageUseCase.getElectionPageWithRelation().observe(getViewLifecycleOwner(), new SendImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionPageWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$setupViewModel$1$1$2
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
                if (electionPageWithRelation == null || electionPageWithRelation.getElectionPage().isCorrected()) {
                    return;
                }
                SendImageViewModel.this.correctImage();
            }
        }));
        sendImageViewModel.getDeleteFormCImageResource().observe(getViewLifecycleOwner(), new SendImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends Boolean>, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$setupViewModel$1$2
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
                    SendImageFragment sendImageFragment = SendImageFragment.this;
                    SendImageViewModel sendImageViewModel2 = sendImageViewModel;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        BaseFragment.showSnackBar$default(sendImageFragment, "Berhasil menghapus gambar", null, null, 6, null);
                        sendImageViewModel2.finishDeletingFormCImage();
                        SendImageFragment sendImageFragment2 = sendImageFragment;
                        androidx.fragment.app.FragmentKt.setFragmentResult(sendImageFragment2, SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to(SendImageFragment.SEND_IMAGE_RESULT_TYPE_DELETE_SUCCESS, true)));
                        sendImageViewModel2.finishDeletingFormCImage();
                        FragmentKt.findNavController(sendImageFragment2).navigateUp();
                    } else if (resource.getSuccess() == ResourceStatus.ERROR) {
                        String error = resource.getError();
                        if (error != null) {
                            BaseFragment.showSnackBar$default(sendImageFragment, error, null, null, 6, null);
                        }
                        sendImageViewModel2.finishDeletingFormCImage();
                    }
                }
            }
        }));
        sendImageViewModel.getPerspectiveCorrectionResource().observe(getViewLifecycleOwner(), new SendImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$setupViewModel$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
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
                    SendImageViewModel sendImageViewModel2 = SendImageViewModel.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS || resource.getSuccess() == ResourceStatus.ERROR) {
                        sendImageViewModel2.finishPerspectiveCorrection();
                    }
                }
            }
        }));
    }

    private final void deleteFormCImage() {
        new MaterialAlertDialogBuilder(requireContext()).setMessage((CharSequence) getString(R.string.delete_formc_hasil_message)).setNegativeButton((CharSequence) getString(R.string.action_no), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SendImageFragment.deleteFormCImage$lambda$10(dialogInterface, i);
            }
        }).setPositiveButton((CharSequence) getString(R.string.action_delete_image), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.sendImage.SendImageFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SendImageFragment.deleteFormCImage$lambda$11(SendImageFragment.this, dialogInterface, i);
            }
        }).show();
    }

    public static final void deleteFormCImage$lambda$11(SendImageFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSendImageViewModel().deleteFormCImage();
    }

    /* compiled from: SendImageFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0002¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/ui/sendImage/SendImageFragment$Companion;", "", "()V", SendImageFragment.SEND_IMAGE_FRAGMENT_RESULT, "", "SEND_IMAGE_RESULT_TYPE_DELETE_SUCCESS", "SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO", "SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_ELECTION_PAGE", "SEND_IMAGE_RESULT_TYPE_RETAKE_PHOTO_IS_MANUAL", "SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS", "SEND_IMAGE_RESULT_TYPE_SEND_SUCCESS_IMAGE_DESC", "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
