package org.informatika.sirekap.support.device;

import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: DeviceUtil.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/support/device/DeviceUtil;", "", "()V", "getDeviceInformation", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceUtil {
    public static final DeviceUtil INSTANCE = new DeviceUtil();

    private DeviceUtil() {
    }

    @JvmStatic
    public static final String getDeviceInformation() {
        String str = Build.MODEL;
        return str + ":" + Build.MANUFACTURER;
    }
}
