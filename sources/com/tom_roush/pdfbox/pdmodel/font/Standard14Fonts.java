package com.tom_roush.pdfbox.pdmodel.font;

import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.fontbox.afm.FontMetrics;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class Standard14Fonts {
    private static final Map<String, String> ALIASES = new HashMap(38);
    private static final Map<String, FontMetrics> FONTS = new HashMap(14);

    static {
        mapName("Courier-Bold");
        mapName("Courier-BoldOblique");
        mapName("Courier");
        mapName("Courier-Oblique");
        mapName("Helvetica");
        mapName("Helvetica-Bold");
        mapName("Helvetica-BoldOblique");
        mapName("Helvetica-Oblique");
        mapName("Symbol");
        mapName("Times-Bold");
        mapName("Times-BoldItalic");
        mapName("Times-Italic");
        mapName("Times-Roman");
        mapName("ZapfDingbats");
        mapName("CourierCourierNew", "Courier");
        mapName("CourierNew", "Courier");
        mapName("CourierNew,Italic", "Courier-Oblique");
        mapName("CourierNew,Bold", "Courier-Bold");
        mapName("CourierNew,BoldItalic", "Courier-BoldOblique");
        mapName("Arial", "Helvetica");
        mapName("Arial,Italic", "Helvetica-Oblique");
        mapName("Arial,Bold", "Helvetica-Bold");
        mapName("Arial,BoldItalic", "Helvetica-BoldOblique");
        mapName("TimesNewRoman", "Times-Roman");
        mapName("TimesNewRoman,Italic", "Times-Italic");
        mapName("TimesNewRoman,Bold", "Times-Bold");
        mapName("TimesNewRoman,BoldItalic", "Times-BoldItalic");
        mapName("Symbol,Italic", "Symbol");
        mapName("Symbol,Bold", "Symbol");
        mapName("Symbol,BoldItalic", "Symbol");
        mapName("Times", "Times-Roman");
        mapName("Times,Italic", "Times-Italic");
        mapName("Times,Bold", "Times-Bold");
        mapName("Times,BoldItalic", "Times-BoldItalic");
        mapName("ArialMT", "Helvetica");
        mapName("Arial-ItalicMT", "Helvetica-Oblique");
        mapName("Arial-BoldMT", "Helvetica-Bold");
        mapName("Arial-BoldItalicMT", "Helvetica-BoldOblique");
    }

    private Standard14Fonts() {
    }

    private static void loadMetrics(String str) throws IOException {
        InputStream resourceAsStream;
        String str2 = "com/tom_roush/pdfbox/resources/afm/" + str + ".afm";
        if (PDFBoxResourceLoader.isReady()) {
            resourceAsStream = PDFBoxResourceLoader.getStream(str2);
        } else {
            resourceAsStream = PDType1Font.class.getResourceAsStream(RemoteSettings.FORWARD_SLASH_STRING + str2);
        }
        if (resourceAsStream == null) {
            throw new IOException("resource '" + str2 + "' not found");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(resourceAsStream);
        try {
            FONTS.put(str, new AFMParser(bufferedInputStream).parse(true));
        } finally {
            bufferedInputStream.close();
        }
    }

    private static void mapName(String str) {
        ALIASES.put(str, str);
    }

    private static void mapName(String str, String str2) {
        ALIASES.put(str, str2);
    }

    public static FontMetrics getAFM(String str) {
        String str2 = ALIASES.get(str);
        if (str2 == null) {
            return null;
        }
        Map<String, FontMetrics> map = FONTS;
        if (map.get(str2) == null) {
            synchronized (map) {
                if (map.get(str2) == null) {
                    try {
                        loadMetrics(str2);
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
        }
        return map.get(str2);
    }

    public static boolean containsName(String str) {
        return ALIASES.containsKey(str);
    }

    public static Set<String> getNames() {
        return Collections.unmodifiableSet(ALIASES.keySet());
    }

    public static String getMappedFontName(String str) {
        return ALIASES.get(str);
    }
}
