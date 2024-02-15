package org.visp.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.visp.core.VpImageRGBa;
import org.visp.core.VpImageUChar;

/* loaded from: classes4.dex */
public class Utils {
    public static native void bitmapToVpImageRGBa(Bitmap bitmap, long j);

    public static native void bitmapToVpImageUChar(Bitmap bitmap, long j);

    public static native void vpImageRGBaToBitmap(long j, Bitmap bitmap);

    public static native void vpImageUCharToBitmap(long j, Bitmap bitmap);

    public static String exportResource(Context context, int i) {
        return exportResource(context, i, "ViSP_data");
    }

    public static String exportResource(Context context, int i, String str) {
        String string = context.getResources().getString(i);
        String substring = string.substring(string.lastIndexOf(RemoteSettings.FORWARD_SLASH_STRING) + 1);
        try {
            InputStream openRawResource = context.getResources().openRawResource(i);
            File file = new File(context.getDir(str, 0), substring);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = openRawResource.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    openRawResource.close();
                    fileOutputStream.close();
                    return file.getAbsolutePath();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("VISP::Utils.java", "Failed to export resource " + substring + ". Exception thrown: " + e);
            return null;
        }
    }

    public static VpImageUChar loadResource(Context context, int i) throws IOException {
        InputStream openRawResource = context.getResources().openRawResource(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(openRawResource.available());
        byte[] bArr = new byte[4096];
        while (true) {
            int read = openRawResource.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                openRawResource.close();
                VpImageUChar vpImageUChar = new VpImageUChar(byteArrayOutputStream.toByteArray(), 1, byteArrayOutputStream.size(), true);
                byteArrayOutputStream.close();
                return vpImageUChar;
            }
        }
    }

    public static void bitmapToVpImageUChar(Bitmap bitmap, VpImageUChar vpImageUChar) {
        if (bitmap == null) {
            throw new IllegalArgumentException("bmp == null");
        }
        if (vpImageUChar == null) {
            throw new IllegalArgumentException("image == null");
        }
        bitmapToVpImageUChar(bitmap, vpImageUChar.nativeObj);
    }

    public static void vpImageUCharToBitmap(VpImageUChar vpImageUChar, Bitmap bitmap) {
        if (vpImageUChar == null) {
            throw new IllegalArgumentException("image == null");
        }
        if (bitmap == null) {
            throw new IllegalArgumentException("bmp == null");
        }
        vpImageUCharToBitmap(vpImageUChar.nativeObj, bitmap);
    }

    public static void bitmapToVpImageRGBa(Bitmap bitmap, VpImageRGBa vpImageRGBa) {
        if (bitmap == null) {
            throw new IllegalArgumentException("bmp == null");
        }
        if (vpImageRGBa == null) {
            throw new IllegalArgumentException("image == null");
        }
        bitmapToVpImageRGBa(bitmap, vpImageRGBa.nativeObj);
    }

    public static void vpImageRGBaToBitmap(VpImageRGBa vpImageRGBa, Bitmap bitmap) {
        if (vpImageRGBa == null) {
            throw new IllegalArgumentException("image == null");
        }
        if (bitmap == null) {
            throw new IllegalArgumentException("bmp == null");
        }
        vpImageRGBaToBitmap(vpImageRGBa.nativeObj, bitmap);
    }
}
