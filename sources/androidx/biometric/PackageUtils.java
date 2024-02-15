package androidx.biometric;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class PackageUtils {
    private PackageUtils() {
    }

    public static boolean hasSystemFeatureFingerprint(Context context) {
        return (context == null || context.getPackageManager() == null || !Api23Impl.hasSystemFeatureFingerprint(context.getPackageManager())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api23Impl {
        private Api23Impl() {
        }

        static boolean hasSystemFeatureFingerprint(PackageManager packageManager) {
            return packageManager.hasSystemFeature("android.hardware.fingerprint");
        }
    }
}
