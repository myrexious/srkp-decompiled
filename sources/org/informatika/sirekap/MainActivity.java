package org.informatika.sirekap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.biometric.BiometricPrompt;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.ToolbarKt;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.databinding.ActivityMainBinding;
import org.informatika.sirekap.support.security.DeviceSecurityFacade;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\u0006\u0010\u0015\u001a\u00020\u000fJ\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lorg/informatika/sirekap/databinding/ActivityMainBinding;", "localAuthLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "doLocalAuthentication", "", "failedAuthentication", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupBinding", "setupLauncher", "setupNavigation", "setupViewModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes2.dex */
public final class MainActivity extends Hilt_MainActivity {
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> localAuthLauncher;
    private final Lazy mainViewModel$delegate;

    public MainActivity() {
        final MainActivity mainActivity = this;
        this.mainViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.MainActivity$special$$inlined$viewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.MainActivity$special$$inlined$viewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.MainActivity$special$$inlined$viewModels$default$3
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
                    CreationExtras defaultViewModelCreationExtras = mainActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        });
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    public final void setupLauncher() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: org.informatika.sirekap.MainActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.setupLauncher$lambda$0(MainActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.localAuthLauncher = registerForActivityResult;
    }

    public static final void setupLauncher$lambda$0(MainActivity this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.getMainViewModel().getLocalAuth().postValue(true);
        } else {
            this$0.failedAuthentication();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.layout.activity_main)");
        this.binding = (ActivityMainBinding) contentView;
        setupBinding();
        setupNavigation();
        setupViewModel();
        setupLauncher();
        if (getMainViewModel().isNeedDoLocalAuthentication()) {
            doLocalAuthentication();
        }
        getMainViewModel().startRefreshTokenTask(this);
    }

    private final void doLocalAuthentication() {
        DeviceSecurityFacade deviceSecurityFacade = DeviceSecurityFacade.INSTANCE;
        MainActivity mainActivity = this;
        BiometricPrompt.AuthenticationCallback authenticationCallback = new BiometricPrompt.AuthenticationCallback() { // from class: org.informatika.sirekap.MainActivity$doLocalAuthentication$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                MainViewModel mainViewModel;
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                mainViewModel = MainActivity.this.getMainViewModel();
                mainViewModel.getLocalAuth().postValue(true);
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                super.onAuthenticationError(i, errString);
                MainActivity.this.failedAuthentication();
            }
        };
        ActivityResultLauncher<Intent> activityResultLauncher = this.localAuthLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAuthLauncher");
            activityResultLauncher = null;
        }
        deviceSecurityFacade.doLocalAuthentication(mainActivity, authenticationCallback, activityResultLauncher);
    }

    public final void failedAuthentication() {
        new AlertDialog.Builder(this).setCancelable(false).setTitle("Otentikasi Gagal").setMessage("Anda harus mengotentikasi diri untuk menggunakan aplikasi ini").setPositiveButton("Ulangi", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.MainActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.failedAuthentication$lambda$1(MainActivity.this, dialogInterface, i);
            }
        }).setNegativeButton("Keluar", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.MainActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.failedAuthentication$lambda$2(MainActivity.this, dialogInterface, i);
            }
        }).show();
    }

    public static final void failedAuthentication$lambda$1(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doLocalAuthentication();
    }

    public static final void failedAuthentication$lambda$2(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void setupBinding() {
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        activityMainBinding.setLifecycleOwner(this);
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding3;
        }
        activityMainBinding2.setAuthRequestUseCase(getMainViewModel().getAuthRequestUseCase());
    }

    private final void setupNavigation() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavController navController = ((NavHostFragment) findFragmentById).getNavController();
        ActivityMainBinding activityMainBinding = null;
        AppBarConfiguration build = new AppBarConfiguration.Builder(SetsKt.setOf((Object[]) new Integer[]{Integer.valueOf(R.id.loginFragment), Integer.valueOf(R.id.loginTpsFragment), Integer.valueOf(R.id.dashboardFragment), Integer.valueOf(R.id.certificateFragment)})).setOpenableLayout(null).setFallbackOnNavigateUpListener(new MainActivity$inlined$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(new Function0<Boolean>() { // from class: org.informatika.sirekap.MainActivity$setupNavigation$$inlined$AppBarConfiguration$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return false;
            }
        })).build();
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding2 = null;
        }
        setSupportActionBar(activityMainBinding2.toolbar);
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding = activityMainBinding3;
        }
        MaterialToolbar materialToolbar = activityMainBinding.toolbar;
        Intrinsics.checkNotNullExpressionValue(materialToolbar, "binding.toolbar");
        ToolbarKt.setupWithNavController(materialToolbar, navController, build);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() { // from class: org.informatika.sirekap.MainActivity$$ExternalSyntheticLambda2
            @Override // androidx.navigation.NavController.OnDestinationChangedListener
            public final void onDestinationChanged(NavController navController2, NavDestination navDestination, Bundle bundle) {
                MainActivity.setupNavigation$lambda$3(MainActivity.this, navController2, navDestination, bundle);
            }
        });
    }

    public static final void setupNavigation$lambda$3(MainActivity this$0, NavController navController, NavDestination destination, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(navController, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        FirebaseCrashlytics.getInstance().log("Navigation change: " + destination + " | ARGS: " + bundle);
        ActivityMainBinding activityMainBinding = null;
        if (destination.getId() == R.id.certificateFragment) {
            ActivityMainBinding activityMainBinding2 = this$0.binding;
            if (activityMainBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding2;
            }
            activityMainBinding.appBarLayout.setVisibility(8);
            return;
        }
        ActivityMainBinding activityMainBinding3 = this$0.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding = activityMainBinding3;
        }
        activityMainBinding.appBarLayout.setVisibility(0);
    }

    private final void setupViewModel() {
        getMainViewModel().getGetLoggedInUserUseCase().isAuthenticated().observe(this, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.MainActivity$setupViewModel$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    return;
                }
                ActivityKt.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(MainNavGraphDirections.Companion.actionGlobalLoginNavGraph());
            }
        }));
    }
}
