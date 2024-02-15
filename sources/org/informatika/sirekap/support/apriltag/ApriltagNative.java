package org.informatika.sirekap.support.apriltag;

import android.graphics.Bitmap;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ApriltagNative {
    public static native ArrayList<ApriltagDetection> apriltag_detect_yuv(byte[] src, int width, int height);

    public static native void apriltag_init(String tagFamily, int errorBits, double decimateFactor, double blurSigma, int nthreads);

    public static native void native_init();

    public static native void yuv_to_rgb(byte[] src, int width, int height, Bitmap dst);

    static {
        System.loadLibrary("apriltag");
        native_init();
    }
}
