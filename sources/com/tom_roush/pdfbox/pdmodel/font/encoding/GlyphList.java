package com.tom_roush.pdfbox.pdmodel.font.encoding;

import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.io.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public final class GlyphList {
    private static final GlyphList DEFAULT = load("glyphlist.txt", 4281);
    private static final GlyphList ZAPF_DINGBATS = load("zapfdingbats.txt", 201);
    private final Map<String, String> nameToUnicode;
    private final Map<String, String> uniNameToUnicodeCache = new ConcurrentHashMap();
    private final Map<String, String> unicodeToName;

    static {
        try {
            if (System.getProperty("glyphlist_ext") == null) {
                return;
            }
            throw new UnsupportedOperationException("glyphlist_ext is no longer supported, use GlyphList.DEFAULT.addGlyphs(Properties) instead");
        } catch (SecurityException unused) {
        }
    }

    private static GlyphList load(String str, int i) {
        InputStream resourceAsStream;
        String str2 = "com/tom_roush/pdfbox/resources/glyphlist/" + str;
        try {
            try {
                if (PDFBoxResourceLoader.isReady()) {
                    resourceAsStream = PDFBoxResourceLoader.getStream(str2);
                } else {
                    resourceAsStream = GlyphList.class.getResourceAsStream(RemoteSettings.FORWARD_SLASH_STRING + str2);
                }
                InputStream inputStream = resourceAsStream;
                if (inputStream == null) {
                    throw new IOException("GlyphList '" + str2 + "' not found");
                }
                GlyphList glyphList = new GlyphList(inputStream, i);
                IOUtils.closeQuietly(inputStream);
                return glyphList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            IOUtils.closeQuietly(null);
            throw th;
        }
    }

    public static GlyphList getAdobeGlyphList() {
        return DEFAULT;
    }

    public static GlyphList getZapfDingbats() {
        return ZAPF_DINGBATS;
    }

    public GlyphList(InputStream inputStream, int i) throws IOException {
        this.nameToUnicode = new HashMap(i);
        this.unicodeToName = new HashMap(i);
        loadList(inputStream);
    }

    public GlyphList(GlyphList glyphList, InputStream inputStream) throws IOException {
        this.nameToUnicode = new HashMap(glyphList.nameToUnicode);
        this.unicodeToName = new HashMap(glyphList.unicodeToName);
        loadList(inputStream);
    }

    private void loadList(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
        while (bufferedReader.ready()) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null && !readLine.startsWith("#")) {
                    String[] split = readLine.split(";");
                    if (split.length < 2) {
                        throw new IOException("Invalid glyph list entry: " + readLine);
                    }
                    boolean z = false;
                    String str = split[0];
                    String[] split2 = split[1].split(StringUtils.SPACE);
                    if (this.nameToUnicode.containsKey(str)) {
                        Log.w("PdfBox-Android", "duplicate value for " + str + " -> " + split[1] + StringUtils.SPACE + this.nameToUnicode.get(str));
                    }
                    int length = split2.length;
                    int[] iArr = new int[length];
                    int length2 = split2.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length2) {
                        iArr[i2] = Integer.parseInt(split2[i], 16);
                        i++;
                        i2++;
                    }
                    String str2 = new String(iArr, 0, length);
                    this.nameToUnicode.put(str, str2);
                    if (WinAnsiEncoding.INSTANCE.contains(str) || MacRomanEncoding.INSTANCE.contains(str) || MacExpertEncoding.INSTANCE.contains(str) || SymbolEncoding.INSTANCE.contains(str) || ZapfDingbatsEncoding.INSTANCE.contains(str)) {
                        z = true;
                    }
                    if (!this.unicodeToName.containsKey(str2) || z) {
                        this.unicodeToName.put(str2, str);
                    }
                }
            } finally {
                bufferedReader.close();
            }
        }
    }

    public String codePointToName(int i) {
        String str = this.unicodeToName.get(new String(new int[]{i}, 0, 1));
        return str == null ? ".notdef" : str;
    }

    public String sequenceToName(String str) {
        String str2 = this.unicodeToName.get(str);
        return str2 == null ? ".notdef" : str2;
    }

    public String toUnicode(String str) {
        if (str == null) {
            return null;
        }
        String str2 = this.nameToUnicode.get(str);
        if (str2 != null) {
            return str2;
        }
        String str3 = this.uniNameToUnicodeCache.get(str);
        if (str3 == null) {
            if (str.indexOf(46) > 0) {
                str3 = toUnicode(str.substring(0, str.indexOf(46)));
            } else if (str.startsWith("uni") && str.length() == 7) {
                int length = str.length();
                StringBuilder sb = new StringBuilder();
                int i = 3;
                while (true) {
                    int i2 = i + 4;
                    if (i2 > length) {
                        break;
                    }
                    try {
                        int parseInt = Integer.parseInt(str.substring(i, i2), 16);
                        if (parseInt > 55295 && parseInt < 57344) {
                            Log.w("PdfBox-Android", "Unicode character name with disallowed code area: " + str);
                        } else {
                            sb.append((char) parseInt);
                        }
                        i = i2;
                    } catch (NumberFormatException unused) {
                        Log.w("PdfBox-Android", "Not a number in Unicode character name: " + str);
                    }
                    Log.w("PdfBox-Android", "Not a number in Unicode character name: " + str);
                }
                str3 = sb.toString();
            } else if (str.startsWith("u") && str.length() == 5) {
                try {
                    int parseInt2 = Integer.parseInt(str.substring(1), 16);
                    if (parseInt2 > 55295 && parseInt2 < 57344) {
                        Log.w("PdfBox-Android", "Unicode character name with disallowed code area: " + str);
                    } else {
                        str3 = String.valueOf((char) parseInt2);
                    }
                } catch (NumberFormatException unused2) {
                    Log.w("PdfBox-Android", "Not a number in Unicode character name: " + str);
                }
            }
            if (str3 != null) {
                this.uniNameToUnicodeCache.put(str, str3);
            }
        }
        return str3;
    }
}
