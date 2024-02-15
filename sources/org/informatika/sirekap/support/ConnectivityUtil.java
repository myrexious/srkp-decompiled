package org.informatika.sirekap.support;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConnectivityUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/support/ConnectivityUtil;", "", "()V", "TAG", "", "isConnectedToNetwork", "", "context", "Landroid/content/Context;", "isUserHasInternetConnection", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ConnectivityUtil {
    public static final ConnectivityUtil INSTANCE = new ConnectivityUtil();
    private static final String TAG = "ConnectivityUtil";

    private ConnectivityUtil() {
    }

    @JvmStatic
    public static final boolean isConnectedToNetwork(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    @JvmStatic
    public static final boolean isUserHasInternetConnection() {
        try {
            Thread.sleep(500L);
            return Runtime.getRuntime().exec("ping -c 1 www.google.com").waitFor() == 0;
        } catch (Exception e) {
            Log.e(TAG, "ERROR: " + e);
            return false;
        }
    }
}
