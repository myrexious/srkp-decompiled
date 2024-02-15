package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class KeyguardUtils {
    private KeyguardUtils() {
    }

    public static KeyguardManager getKeyguardManager(Context context) {
        return Api23Impl.getKeyguardManager(context);
    }

    public static boolean isDeviceSecuredWithCredential(Context context) {
        KeyguardManager keyguardManager = getKeyguardManager(context);
        if (keyguardManager == null) {
            return false;
        }
        return Api23Impl.isDeviceSecure(keyguardManager);
    }

    /* loaded from: classes.dex */
    public static class Api23Impl {
        private Api23Impl() {
        }

        static KeyguardManager getKeyguardManager(Context context) {
            return (KeyguardManager) context.getSystemService(KeyguardManager.class);
        }

        static boolean isDeviceSecure(KeyguardManager keyguardManager) {
            return keyguardManager.isDeviceSecure();
        }
    }

    /* loaded from: classes.dex */
    private static class Api16Impl {
        private Api16Impl() {
        }

        static boolean isKeyguardSecure(KeyguardManager keyguardManager) {
            return keyguardManager.isKeyguardSecure();
        }
    }
}
