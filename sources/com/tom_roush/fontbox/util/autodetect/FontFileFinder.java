package com.tom_roush.fontbox.util.autodetect;

import android.util.Log;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes3.dex */
public class FontFileFinder {
    private FontDirFinder fontDirFinder = null;

    private FontDirFinder determineDirFinder() {
        if (System.getProperty(SystemProperties.JAVA_VENDOR).equals("The Android Project")) {
            return new AndroidFontDirFinder();
        }
        String property = System.getProperty(SystemProperties.OS_NAME);
        if (property.startsWith("Windows")) {
            return new WindowsFontDirFinder();
        }
        if (property.startsWith("Mac")) {
            return new MacFontDirFinder();
        }
        if (property.startsWith("OS/400")) {
            return new OS400FontDirFinder();
        }
        return new UnixFontDirFinder();
    }

    public List<URI> find() {
        if (this.fontDirFinder == null) {
            this.fontDirFinder = determineDirFinder();
        }
        List<File> find = this.fontDirFinder.find();
        ArrayList arrayList = new ArrayList();
        for (File file : find) {
            walk(file, arrayList);
        }
        return arrayList;
    }

    public List<URI> find(String str) {
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (file.isDirectory()) {
            walk(file, arrayList);
        }
        return arrayList;
    }

    private void walk(File file, List<URI> list) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    if (!file2.getName().startsWith(".")) {
                        walk(file2, list);
                    }
                } else {
                    if (PDFBoxConfig.isDebugEnabled()) {
                        Log.d("PdfBox-Android", "checkFontfile check " + file2);
                    }
                    if (checkFontfile(file2)) {
                        if (PDFBoxConfig.isDebugEnabled()) {
                            Log.d("PdfBox-Android", "checkFontfile found " + file2);
                        }
                        list.add(file2.toURI());
                    }
                }
            }
        }
    }

    private boolean checkFontfile(File file) {
        String lowerCase = file.getName().toLowerCase(Locale.US);
        return (lowerCase.endsWith(".ttf") || lowerCase.endsWith(".otf") || lowerCase.endsWith(".pfb") || lowerCase.endsWith(".ttc")) && !lowerCase.startsWith("fonts.");
    }
}
