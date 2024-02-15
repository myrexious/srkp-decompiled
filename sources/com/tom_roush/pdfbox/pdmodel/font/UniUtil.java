package com.tom_roush.pdfbox.pdmodel.font;

import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class UniUtil {
    private UniUtil() {
    }

    public static String getUniNameOfCodePoint(int i) {
        String upperCase = Integer.toString(i, 16).toUpperCase(Locale.US);
        int length = upperCase.length();
        if (length != 1) {
            if (length != 2) {
                if (length == 3) {
                    return "uni0" + upperCase;
                }
                return "uni" + upperCase;
            }
            return "uni00" + upperCase;
        }
        return "uni000" + upperCase;
    }
}
