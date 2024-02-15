package org.visp.android;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.util.StringTokenizer;

/* loaded from: classes4.dex */
class StaticHelper {
    private static final String TAG = "ViSP/StaticHelper";

    private static native String getLibraryList();

    StaticHelper() {
    }

    public static boolean initViSP() {
        String str;
        Log.d(TAG, "Trying to get library list");
        try {
            System.loadLibrary("visp_core");
            str = getLibraryList();
        } catch (UnsatisfiedLinkError unused) {
            Log.e(TAG, "ViSP error: Cannot load info library for ViSP");
            str = "";
        }
        Log.d(TAG, "Library list: \"" + str + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        Log.d(TAG, "First attempt to load libs");
        if (initViSPLibs(str)) {
            Log.d(TAG, "First attempt to load libs is OK");
            return true;
        }
        Log.d(TAG, "First attempt to load libs fails");
        return false;
    }

    private static boolean loadLibrary(String str) {
        Log.d(TAG, "Trying to load library " + str);
        try {
            System.loadLibrary(str);
            Log.d(TAG, "Library " + str + " loaded");
            return true;
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "Cannot load library \"" + str + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
            e.printStackTrace();
            return false;
        }
    }

    private static boolean initViSPLibs(String str) {
        Log.d(TAG, "Trying to init ViSP libs");
        if (str != null && str.length() != 0) {
            Log.d(TAG, "Trying to load libs by dependency list");
            StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
            boolean z = true;
            while (stringTokenizer.hasMoreTokens()) {
                z &= loadLibrary(stringTokenizer.nextToken());
            }
            return z;
        }
        return loadLibrary("visp_java");
    }
}
