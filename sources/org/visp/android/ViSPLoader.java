package org.visp.android;

import android.content.Context;

/* loaded from: classes4.dex */
public class ViSPLoader {
    public static final String VISP_VERSION = "3.6.0";
    public static final String VISP_VERSION_2_10_0 = "2.10.0";
    public static final String VISP_VERSION_3_0_0 = "3.0.0";
    public static final String VISP_VERSION_3_0_1 = "3.0.1";
    public static final String VISP_VERSION_3_1_0 = "3.1.0";

    public static boolean initDebug() {
        return StaticHelper.initViSP();
    }

    public static boolean initAsync(String str, Context context, LoaderCallbackInterface loaderCallbackInterface) {
        return AsyncServiceHelper.initViSP(str, context, loaderCallbackInterface);
    }
}
