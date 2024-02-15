package org.informatika.sirekap.ui.tpsTime;

import android.content.ActivityNotFoundException;
import android.content.Context;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Date;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
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
import org.informatika.sirekap.databinding.DialogVerifyBodyBinding;
import org.informatika.sirekap.databinding.FragmentTpsTimeBinding;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: TpsTimeFragment.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001a\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010%\u001a\u00020\u001aH\u0002J\b\u0010&\u001a\u00020\u001aH\u0002J\b\u0010'\u001a\u00020\u001aH\u0002J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u001aH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lorg/informatika/sirekap/databinding/FragmentTpsTimeBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/tpsTime/TpsTimeViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/tpsTime/TpsTimeViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupBinding", "setupLaunchers", "setupViewModel", "trySubmit", "isOffline", "", "trySubmitOnline", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class TpsTimeFragment extends Hilt_TpsTimeFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ManageTimeFragment";
    private final NavArgsLazy args$delegate;
    private ActivityResultLauncher<Intent> authRequestLauncher;
    private FragmentTpsTimeBinding binding;
    private final Lazy fragmentViewModel$delegate;
    private final Lazy mainViewModel$delegate;

    public TpsTimeFragment() {
        final TpsTimeFragment tpsTimeFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(tpsTimeFragment, Reflection.getOrCreateKotlinClass(TpsTimeViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$viewModels$default$5
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
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(TpsTimeFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$navArgs$1
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(tpsTimeFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$activityViewModels$default$2
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
                    CreationExtras defaultViewModelCreationExtras = tpsTimeFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$special$$inlined$activityViewModels$default$3
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

    public final TpsTimeViewModel getFragmentViewModel() {
        return (TpsTimeViewModel) this.fragmentViewModel$delegate.getValue();
    }

    private final TpsTimeFragmentArgs getArgs() {
        return (TpsTimeFragmentArgs) this.args$delegate.getValue();
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
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                TpsTimeFragment.setupLaunchers$lambda$0(TpsTimeFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          )\n            }");
        this.authRequestLauncher = registerForActivityResult;
    }

    public static final void setupLaunchers$lambda$0(TpsTimeFragment this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AuthRequestUseCase authRequestUseCase = this$0.getMainViewModel().getAuthRequestUseCase();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this$0.getMainViewModel());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        authRequestUseCase.processToken(it, viewModelScope, requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$setupLaunchers$1$1
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
                BaseFragment.showSnackBar$default(TpsTimeFragment.this, "Sesi Anda berhasil diperpanjang. Silakan tekan tombol 'Submit' lagi.", null, null, 6, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$setupLaunchers$1$2
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
                BaseFragment.showSnackBar$default(TpsTimeFragment.this, String.valueOf(e.getMessage()), null, null, 6, null);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentTpsTimeBinding inflate = FragmentTpsTimeBinding.inflate(inflater, viewGroup, false);
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
        getFragmentViewModel().setup(getArgs().getKodeTps(), getArgs().getJenisWaktu(), getArgs().getJenisPemilihan(), getArgs().isElectionZipped());
        ((Toolbar) requireActivity().findViewById(R.id.toolbar)).setTitle(getArgs().getJenisWaktu() == 1 ? "Waktu Penghitungan Suara" : "Waktu Pemungutan Suara");
    }

    private final void setupBinding() {
        FragmentTpsTimeBinding fragmentTpsTimeBinding = this.binding;
        if (fragmentTpsTimeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentTpsTimeBinding = null;
        }
        fragmentTpsTimeBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentTpsTimeBinding.setViewModel(getFragmentViewModel());
        fragmentTpsTimeBinding.inputStartDate.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$4(TpsTimeFragment.this, view);
            }
        });
        fragmentTpsTimeBinding.inputStartTime.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$7(TpsTimeFragment.this, view);
            }
        });
        fragmentTpsTimeBinding.inputEndDate.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$11(TpsTimeFragment.this, view);
            }
        });
        fragmentTpsTimeBinding.inputEndTime.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$14(TpsTimeFragment.this, view);
            }
        });
        fragmentTpsTimeBinding.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$17(TpsTimeFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$18$lambda$4(TpsTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long j = MaterialDatePicker.todayInUtcMilliseconds();
        Date value = this$0.getFragmentViewModel().getStartDate().getValue();
        if (value != null) {
            j = value.getTime();
        }
        MaterialDatePicker<Long> build = MaterialDatePicker.Builder.datePicker().setSelection(Long.valueOf(j)).setTitleText("Pilih tanggal mulai").setCalendarConstraints(new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.now()).build()).build();
        final Function1<Long, Unit> function1 = new Function1<Long, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$setupBinding$1$1$datePicker$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke2(l);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Long it) {
                TpsTimeViewModel fragmentViewModel;
                fragmentViewModel = TpsTimeFragment.this.getFragmentViewModel();
                MutableLiveData<Date> startDate = fragmentViewModel.getStartDate();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                startDate.postValue(new Date(it.longValue()));
            }
        };
        build.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
            public final void onPositiveButtonClick(Object obj) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$4$lambda$3$lambda$2(Function1.this, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(build, "datePicker()\n           …  }\n                    }");
        build.show(this$0.getParentFragmentManager(), "startDatePicker");
    }

    public static final void setupBinding$lambda$18$lambda$4$lambda$3$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void setupBinding$lambda$18$lambda$7(TpsTimeFragment this$0, View view) {
        Integer num;
        Integer num2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Pair<Integer, Integer> value = this$0.getFragmentViewModel().getStartTime().getValue();
        if (value != null) {
            num = value.getFirst();
            num2 = value.getSecond();
        } else {
            num = null;
            num2 = null;
        }
        MaterialTimePicker.Builder titleText = new MaterialTimePicker.Builder().setTimeFormat(1).setTitleText("Isi waktu mulai");
        Intrinsics.checkNotNullExpressionValue(titleText, "Builder()\n              …leText(\"Isi waktu mulai\")");
        if (num != null) {
            titleText.setHour(num.intValue());
        }
        if (num2 != null) {
            titleText.setMinute(num2.intValue());
        }
        final MaterialTimePicker build = titleText.build();
        Intrinsics.checkNotNullExpressionValue(build, "timePickerBuilder.build()");
        build.addOnPositiveButtonClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$7$lambda$6(TpsTimeFragment.this, build, view2);
            }
        });
        build.show(this$0.getParentFragmentManager(), "startTimePicker");
    }

    public static final void setupBinding$lambda$18$lambda$7$lambda$6(TpsTimeFragment this$0, MaterialTimePicker timePicker, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(timePicker, "$timePicker");
        this$0.getFragmentViewModel().getStartTime().setValue(new Pair<>(Integer.valueOf(timePicker.getHour()), Integer.valueOf(timePicker.getMinute())));
    }

    public static final void setupBinding$lambda$18$lambda$11(TpsTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long j = MaterialDatePicker.todayInUtcMilliseconds();
        Date value = this$0.getFragmentViewModel().getEndDate().getValue();
        if (value != null) {
            j = value.getTime();
        }
        MaterialDatePicker<Long> build = MaterialDatePicker.Builder.datePicker().setSelection(Long.valueOf(j)).setTitleText("Pilih tanggal selesai").setCalendarConstraints(new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.now()).build()).build();
        final Function1<Long, Unit> function1 = new Function1<Long, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$setupBinding$1$3$datePicker$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke2(l);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Long it) {
                TpsTimeViewModel fragmentViewModel;
                fragmentViewModel = TpsTimeFragment.this.getFragmentViewModel();
                MutableLiveData<Date> endDate = fragmentViewModel.getEndDate();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                endDate.postValue(new Date(it.longValue()));
            }
        };
        build.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda4
            @Override // com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
            public final void onPositiveButtonClick(Object obj) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$11$lambda$10$lambda$9(Function1.this, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(build, "datePicker()\n           …  }\n                    }");
        build.show(this$0.getParentFragmentManager(), "endDatePicker");
    }

    public static final void setupBinding$lambda$18$lambda$11$lambda$10$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void setupBinding$lambda$18$lambda$14(TpsTimeFragment this$0, View view) {
        Integer num;
        Integer num2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Pair<Integer, Integer> value = this$0.getFragmentViewModel().getEndTime().getValue();
        if (value != null) {
            num = value.getFirst();
            num2 = value.getSecond();
        } else {
            num = null;
            num2 = null;
        }
        MaterialTimePicker.Builder titleText = new MaterialTimePicker.Builder().setTimeFormat(1).setTitleText("Isi waktu selesai");
        Intrinsics.checkNotNullExpressionValue(titleText, "Builder()\n              …Text(\"Isi waktu selesai\")");
        if (num != null) {
            titleText.setHour(num.intValue());
        }
        if (num2 != null) {
            titleText.setMinute(num2.intValue());
        }
        final MaterialTimePicker build = titleText.build();
        Intrinsics.checkNotNullExpressionValue(build, "timePickerBuilder.build()");
        build.addOnPositiveButtonClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$14$lambda$13(TpsTimeFragment.this, build, view2);
            }
        });
        build.show(this$0.getParentFragmentManager(), "endTimePicker");
    }

    public static final void setupBinding$lambda$18$lambda$14$lambda$13(TpsTimeFragment this$0, MaterialTimePicker timePicker, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(timePicker, "$timePicker");
        this$0.getFragmentViewModel().getEndTime().setValue(new Pair<>(Integer.valueOf(timePicker.getHour()), Integer.valueOf(timePicker.getMinute())));
    }

    public static final void setupBinding$lambda$18$lambda$17(final TpsTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        FirebaseCrashlytics.getInstance().log("ManageTimeFragment Click 'Submit'");
        DialogVerifyBodyBinding inflate = DialogVerifyBodyBinding.inflate(this$0.getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, null, false)");
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this$0.requireContext());
        inflate.setLifecycleOwner(this$0.getViewLifecycleOwner());
        inflate.setViewModel(this$0.getFragmentViewModel());
        inflate.setHideCommentField(true);
        materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) "Submit pencatatan waktu ini?");
        final AlertDialog create = materialAlertDialogBuilder.create();
        Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…               }.create()");
        create.show();
        inflate.submitButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TpsTimeFragment.setupBinding$lambda$18$lambda$17$lambda$16(AlertDialog.this, this$0, view2);
            }
        });
    }

    public static final void setupBinding$lambda$18$lambda$17$lambda$16(AlertDialog dialog, TpsTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.getMainViewModel().getAuthRequestUseCase().isLoading().postValue(true);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new TpsTimeFragment$setupBinding$1$5$1$1(this$0, null), 2, null);
    }

    public final void trySubmitOnline() {
        getFragmentViewModel().validateInput(new Function0<Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$trySubmitOnline$1
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
                MainViewModel mainViewModel;
                MainViewModel mainViewModel2;
                mainViewModel = TpsTimeFragment.this.getMainViewModel();
                if (mainViewModel.getAuthRequestUseCase().isLocalTokenExpired()) {
                    mainViewModel2 = TpsTimeFragment.this.getMainViewModel();
                    AuthRequestUseCase authRequestUseCase = mainViewModel2.getAuthRequestUseCase();
                    Context requireContext = TpsTimeFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    final TpsTimeFragment tpsTimeFragment = TpsTimeFragment.this;
                    final TpsTimeFragment tpsTimeFragment2 = TpsTimeFragment.this;
                    authRequestUseCase.startRefreshToken(requireContext, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$trySubmitOnline$1.1
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
                            TpsTimeFragment.this.trySubmit(false);
                        }
                    }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$trySubmitOnline$1.2
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
                            MainViewModel mainViewModel3;
                            ActivityResultLauncher<Intent> activityResultLauncher;
                            Intrinsics.checkNotNullParameter(it, "it");
                            Toast.makeText(TpsTimeFragment.this.requireContext(), "Sesi Anda telah berakhir. Harap login kembali", 1).show();
                            try {
                                mainViewModel3 = TpsTimeFragment.this.getMainViewModel();
                                AuthRequestUseCase authRequestUseCase2 = mainViewModel3.getAuthRequestUseCase();
                                Context requireContext2 = TpsTimeFragment.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                activityResultLauncher = TpsTimeFragment.this.authRequestLauncher;
                                if (activityResultLauncher == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("authRequestLauncher");
                                    activityResultLauncher = null;
                                }
                                authRequestUseCase2.start(requireContext2, activityResultLauncher);
                            } catch (ActivityNotFoundException e) {
                                FirebaseCrashlytics.getInstance().recordException(new Exception(e));
                                TpsTimeFragment tpsTimeFragment3 = TpsTimeFragment.this;
                                String string = tpsTimeFragment3.getString(R.string.key_setup_error_browser_not_found);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.key_s…_error_browser_not_found)");
                                BaseFragment.showSnackBar$default(tpsTimeFragment3, string, null, null, 6, null);
                            }
                        }
                    });
                    return;
                }
                TpsTimeFragment.this.trySubmit(false);
            }
        }, new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$trySubmitOnline$2
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
                BaseFragment.showSnackBar$default(TpsTimeFragment.this, it, null, null, 6, null);
            }
        });
    }

    public final void trySubmit(boolean z) {
        TpsTimeViewModel fragmentViewModel = getFragmentViewModel();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        fragmentViewModel.submit(requireContext, getArgs().getKodeTps(), getArgs().getJenisWaktu(), getArgs().getJenisPemilihan(), z);
    }

    private final void setupViewModel() {
        getFragmentViewModel().getAddTpsTimeUseCase().getResource().observe(getViewLifecycleOwner(), new TpsTimeFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends TpsTime>, Unit>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeFragment$setupViewModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends TpsTime> resource) {
                invoke2((Resource<TpsTime>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<TpsTime> resource) {
                if (resource != null) {
                    TpsTimeFragment tpsTimeFragment = TpsTimeFragment.this;
                    if (resource.getSuccess() == ResourceStatus.ERROR) {
                        BaseFragment.showSnackBar$default(tpsTimeFragment, String.valueOf(resource.getError()), null, null, 6, null);
                    }
                }
            }
        }));
    }

    /* compiled from: TpsTimeFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/tpsTime/TpsTimeFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
