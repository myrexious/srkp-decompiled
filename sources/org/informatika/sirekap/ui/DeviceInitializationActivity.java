package org.informatika.sirekap.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.MainActivity;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.ActivityDeviceInitializationBinding;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.support.security.DeviceSecurityFacade;
import org.informatika.sirekap.usecase.GetNotificationPermissionUseCase;

/* compiled from: DeviceInitializationActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/ui/DeviceInitializationActivity;", "Lorg/informatika/sirekap/ui/BaseActivity;", "()V", "binding", "Lorg/informatika/sirekap/databinding/ActivityDeviceInitializationBinding;", "deviceInitializationViewModel", "Lorg/informatika/sirekap/ui/DeviceInitializationViewModel;", "getDeviceInitializationViewModel", "()Lorg/informatika/sirekap/ui/DeviceInitializationViewModel;", "deviceInitializationViewModel$delegate", "Lkotlin/Lazy;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "finishInitialization", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "runDeviceCheck", "", "setupBinding", "setupLauncher", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class DeviceInitializationActivity extends Hilt_DeviceInitializationActivity {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DeviceInitializationActivity";
    private ActivityDeviceInitializationBinding binding;
    private final Lazy deviceInitializationViewModel$delegate;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    public DeviceInitializationActivity() {
        final DeviceInitializationActivity deviceInitializationActivity = this;
        this.deviceInitializationViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(DeviceInitializationViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.DeviceInitializationActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.DeviceInitializationActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.DeviceInitializationActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = Function0.this;
                if (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) {
                    CreationExtras defaultViewModelCreationExtras = deviceInitializationActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        });
    }

    private final DeviceInitializationViewModel getDeviceInitializationViewModel() {
        return (DeviceInitializationViewModel) this.deviceInitializationViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_device_initialization);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.l…ty_device_initialization)");
        this.binding = (ActivityDeviceInitializationBinding) contentView;
        setupLauncher();
        setupBinding();
        setupViewModel();
        DeviceInitializationActivity deviceInitializationActivity = this;
        if (!SharedPreferencesModule.INSTANCE.provideSharedPreferences(deviceInitializationActivity).getBoolean(SharedPreferencesModule.SP_KEYSTORE_PREPARATION, false)) {
            DeviceInitializationViewModel deviceInitializationViewModel = getDeviceInitializationViewModel();
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "this@DeviceInitializatio…tivity.applicationContext");
            deviceInitializationViewModel.symmetricKeyGeneration(applicationContext);
            DeviceInitializationViewModel deviceInitializationViewModel2 = getDeviceInitializationViewModel();
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "this@DeviceInitializatio…tivity.applicationContext");
            deviceInitializationViewModel2.generateKeyPair(applicationContext2);
            getDeviceInitializationViewModel().cleanupBsreCertificate();
            SharedPreferencesModule.INSTANCE.provideSharedPreferences(deviceInitializationActivity).edit().putBoolean(SharedPreferencesModule.SP_KEYSTORE_PREPARATION, true).apply();
        }
        if (!runDeviceCheck()) {
            getDeviceInitializationViewModel().isDeviceCheckFailed().setValue(true);
        } else if (getDeviceInitializationViewModel().isExpired()) {
            GetNotificationPermissionUseCase getNotificationPermissionUseCase = getDeviceInitializationViewModel().getGetNotificationPermissionUseCase();
            String string = getString(R.string.error_app_expired);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error_app_expired)");
            getNotificationPermissionUseCase.setError(string);
        } else {
            getDeviceInitializationViewModel().getGetFcmTokenUseCase().setup();
        }
    }

    private final void setupLauncher() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: org.informatika.sirekap.ui.DeviceInitializationActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DeviceInitializationActivity.setupLauncher$lambda$0(DeviceInitializationActivity.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.requestPermissionLauncher = registerForActivityResult;
    }

    public static final void setupLauncher$lambda$0(DeviceInitializationActivity this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            this$0.finishInitialization();
        } else {
            this$0.getDeviceInitializationViewModel().getGetNotificationPermissionUseCase().setError("Pengguna tidak memberikan izin notifikasi.");
        }
    }

    public final void finishInitialization() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private final boolean runDeviceCheck() {
        DeviceInitializationActivity deviceInitializationActivity = this;
        if (DeviceSecurityFacade.INSTANCE.isDeviceRooted(deviceInitializationActivity)) {
            getDeviceInitializationViewModel().getGetNotificationPermissionUseCase().setError("Perangkat anda tidak diizinkan untuk menggunakan aplikasi ini");
            return false;
        } else if (!DeviceSecurityFacade.INSTANCE.isDeviceSecured(deviceInitializationActivity)) {
            getDeviceInitializationViewModel().getGetNotificationPermissionUseCase().setError("Silahkan aktifkan kunci layar untuk menggunakan aplikasi");
            return false;
        } else {
            if (DeviceSecurityFacade.INSTANCE.isDeviceHasBiometricButNotSet(deviceInitializationActivity)) {
                Toast.makeText(deviceInitializationActivity, "Kami sarankan untuk menambahkan sidik jari pada perangkat anda", 1).show();
            }
            return true;
        }
    }

    private final void setupBinding() {
        ActivityDeviceInitializationBinding activityDeviceInitializationBinding = this.binding;
        if (activityDeviceInitializationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceInitializationBinding = null;
        }
        activityDeviceInitializationBinding.setLifecycleOwner(this);
        activityDeviceInitializationBinding.setFragmentViewModel(getDeviceInitializationViewModel());
        activityDeviceInitializationBinding.buttonRetry.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.DeviceInitializationActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceInitializationActivity.setupBinding$lambda$2$lambda$1(DeviceInitializationActivity.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$2$lambda$1(DeviceInitializationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDeviceInitializationViewModel().getGetFcmTokenUseCase().setup();
    }

    private final void setupViewModel() {
        final DeviceInitializationViewModel deviceInitializationViewModel = getDeviceInitializationViewModel();
        deviceInitializationViewModel.getGetFcmTokenUseCase().isSuccess().observe(this, new DeviceInitializationActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.DeviceInitializationActivity$setupViewModel$1$1$1
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
                ActivityResultLauncher<String> activityResultLauncher;
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    if (Build.VERSION.SDK_INT < 33) {
                        this.finishInitialization();
                        return;
                    }
                    GetNotificationPermissionUseCase getNotificationPermissionUseCase = DeviceInitializationViewModel.this.getGetNotificationPermissionUseCase();
                    activityResultLauncher = this.requestPermissionLauncher;
                    if (activityResultLauncher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("requestPermissionLauncher");
                        activityResultLauncher = null;
                    }
                    getNotificationPermissionUseCase.start(activityResultLauncher);
                }
            }
        }));
    }

    /* compiled from: DeviceInitializationActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/DeviceInitializationActivity$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
