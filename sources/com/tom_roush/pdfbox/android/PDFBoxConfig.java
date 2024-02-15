package com.tom_roush.pdfbox.android;

/* loaded from: classes3.dex */
public class PDFBoxConfig {
    public static FontLoadLevel FONT_LOAD_LEVEL = FontLoadLevel.MINIMUM;
    private static boolean debugLoggingEnabled = false;

    /* loaded from: classes3.dex */
    public enum FontLoadLevel {
        FULL,
        MINIMUM,
        NONE
    }

    public static FontLoadLevel getFontLoadLevel() {
        return FONT_LOAD_LEVEL;
    }

    public static void setFontLoadLevel(FontLoadLevel fontLoadLevel) {
        FONT_LOAD_LEVEL = fontLoadLevel;
    }

    public static void setDebugLoggingEnabled(boolean z) {
        debugLoggingEnabled = z;
    }

    public static boolean isDebugEnabled() {
        return debugLoggingEnabled;
    }
}
