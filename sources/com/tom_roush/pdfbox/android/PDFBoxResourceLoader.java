package com.tom_roush.pdfbox.android;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class PDFBoxResourceLoader {
    private static AssetManager ASSET_MANAGER;

    public static void init(Context context) {
        ASSET_MANAGER = context.getApplicationContext().getAssets();
    }

    public static boolean isReady() {
        return ASSET_MANAGER != null;
    }

    public static InputStream getStream(String str) throws IOException {
        if (ASSET_MANAGER == null) {
            Log.e("PdfBox-Android", "PDFBoxResourceLoader is not initialized, call PDFBoxResourceLoader.init() before use");
        }
        return ASSET_MANAGER.open(str);
    }
}
