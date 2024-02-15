package org.informatika.sirekap.support.security;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.activity.result.ActivityResultLauncher;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.scottyab.rootbeer.RootBeer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.openid.appauth.AuthorizationRequest;

/* compiled from: DeviceSecurityFacade.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u001c\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/support/security/DeviceSecurityFacade;", "", "()V", "doLocalAuthentication", "", AuthorizationRequest.ResponseMode.FRAGMENT, "Landroidx/fragment/app/Fragment;", "fallback", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "Landroidx/fragment/app/FragmentActivity;", "doLocalAuthenticationUsingKeyguard", "context", "Landroid/content/Context;", "getBiometricStatus", "", "getNetworkStatus", "Lorg/informatika/sirekap/support/security/DeviceSecurityFacade$DeviceNetworkStatus;", "isBiometricAvailable", "", "isDeviceHasBiometricButNotSet", "isDeviceRooted", "isDeviceSecured", "DeviceNetworkStatus", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceSecurityFacade {
    public static final DeviceSecurityFacade INSTANCE = new DeviceSecurityFacade();

    /* compiled from: DeviceSecurityFacade.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/security/DeviceSecurityFacade$DeviceNetworkStatus;", "", "(Ljava/lang/String;I)V", "CONNECTED_WIFI", "CONNECTED_CELLULAR", "CONNECTED_ETHERNET", "CONNECTED_VPN", "NOT_CONNECTED", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum DeviceNetworkStatus {
        CONNECTED_WIFI,
        CONNECTED_CELLULAR,
        CONNECTED_ETHERNET,
        CONNECTED_VPN,
        NOT_CONNECTED
    }

    private DeviceSecurityFacade() {
    }

    public final void doLocalAuthentication(Fragment fragment, BiometricPrompt.AuthenticationCallback fallback, ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        Intrinsics.checkNotNullParameter(activityResultLauncher, "activityResultLauncher");
        Context requireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "fragment.requireContext()");
        int biometricStatus = getBiometricStatus(requireContext);
        if (biometricStatus == -2 || biometricStatus == -1 || biometricStatus == 1 || biometricStatus == 12 || biometricStatus == 15) {
            Context requireContext2 = fragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "fragment.requireContext()");
            doLocalAuthenticationUsingKeyguard(requireContext2, activityResultLauncher);
            return;
        }
        BiometricPrompt biometricPrompt = new BiometricPrompt(fragment, fallback);
        BiometricPrompt.PromptInfo.Builder builder = new BiometricPrompt.PromptInfo.Builder();
        builder.setTitle("Diperlukan Otentikasi");
        DeviceSecurityFacade deviceSecurityFacade = INSTANCE;
        Context requireContext3 = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "fragment.requireContext()");
        if (!deviceSecurityFacade.isBiometricAvailable(requireContext3)) {
            builder.setDescription("Silahkan masukan Password, PIN, atau pola kunci layar anda");
            builder.setDeviceCredentialAllowed(true);
        } else {
            builder.setDescription("Silahkan lakukan otentikasi dengan sidik jadi anda");
            builder.setNegativeButtonText("Batalkan");
        }
        BiometricPrompt.PromptInfo build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().apply {\n      …      }\n        }.build()");
        biometricPrompt.authenticate(build);
    }

    public final void doLocalAuthentication(FragmentActivity fragment, BiometricPrompt.AuthenticationCallback fallback, ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        Intrinsics.checkNotNullParameter(activityResultLauncher, "activityResultLauncher");
        FragmentActivity fragmentActivity = fragment;
        int biometricStatus = getBiometricStatus(fragmentActivity);
        if (biometricStatus == -2 || biometricStatus == -1 || biometricStatus == 1 || biometricStatus == 12 || biometricStatus == 15) {
            doLocalAuthenticationUsingKeyguard(fragmentActivity, activityResultLauncher);
            return;
        }
        BiometricPrompt biometricPrompt = new BiometricPrompt(fragment, fallback);
        BiometricPrompt.PromptInfo.Builder builder = new BiometricPrompt.PromptInfo.Builder();
        builder.setTitle("Diperlukan Otentikasi");
        if (!INSTANCE.isBiometricAvailable(fragmentActivity)) {
            builder.setDescription("Silahkan masukan Password, PIN, atau pola kunci layar anda");
            builder.setDeviceCredentialAllowed(true);
        } else {
            builder.setDescription("Silahkan lakukan otentikasi dengan sidik jadi atau kunci layar anda");
            builder.setNegativeButtonText("Batalkan");
        }
        BiometricPrompt.PromptInfo build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().apply {\n      …      }\n        }.build()");
        biometricPrompt.authenticate(build);
    }

    public final void doLocalAuthenticationUsingKeyguard(Context context, ActivityResultLauncher<Intent> activityResultLauncher) {
        Intent createConfirmDeviceCredentialIntent;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityResultLauncher, "activityResultLauncher");
        Object systemService = context.getSystemService("keyguard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        KeyguardManager keyguardManager = (KeyguardManager) systemService;
        if (keyguardManager.isKeyguardSecure()) {
            if (isBiometricAvailable(context)) {
                createConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent("Diperlukan Otentikasi", "");
            } else {
                createConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent("Diperlukan Otentikasi", "Silahkan masukan Password, PIN, atau pola kunci layar anda");
            }
            if (createConfirmDeviceCredentialIntent != null) {
                activityResultLauncher.launch(createConfirmDeviceCredentialIntent);
            }
        }
    }

    public final boolean isDeviceHasBiometricButNotSet(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getBiometricStatus(context) == 11;
    }

    public final boolean isBiometricAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getBiometricStatus(context) == 0;
    }

    public final boolean isDeviceSecured(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("keyguard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        return ((KeyguardManager) systemService).isKeyguardSecure();
    }

    public final boolean isDeviceRooted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new RootBeer(context).isRooted();
    }

    public final int getBiometricStatus(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BiometricManager.from(context).canAuthenticate(15);
    }

    public final DeviceNetworkStatus getNetworkStatus(Context context) {
        NetworkCapabilities networkCapabilities;
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
            return networkCapabilities.hasTransport(4) ? DeviceNetworkStatus.CONNECTED_VPN : networkCapabilities.hasTransport(1) ? DeviceNetworkStatus.CONNECTED_WIFI : networkCapabilities.hasTransport(0) ? DeviceNetworkStatus.CONNECTED_CELLULAR : networkCapabilities.hasTransport(3) ? DeviceNetworkStatus.CONNECTED_ETHERNET : DeviceNetworkStatus.NOT_CONNECTED;
        }
        return DeviceNetworkStatus.NOT_CONNECTED;
    }
}
