package com.tom_roush.pdfbox.rendering;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Type1Glyph2D implements Glyph2D {
    private final Map<Integer, Path> cache = new HashMap();
    private final PDSimpleFont font;

    public Type1Glyph2D(PDSimpleFont pDSimpleFont) {
        this.font = pDSimpleFont;
    }

    @Override // com.tom_roush.pdfbox.rendering.Glyph2D
    public Path getPathForCharacterCode(int i) {
        Path path = this.cache.get(Integer.valueOf(i));
        if (path == null) {
            try {
                String name = this.font.getEncoding().getName(i);
                if (!this.font.hasGlyph(name)) {
                    Log.w("PdfBox-Android", "No glyph for code " + i + " (" + name + ") in font " + this.font.getName());
                    if (i == 10 && this.font.isStandard14()) {
                        Path path2 = new Path();
                        this.cache.put(Integer.valueOf(i), path2);
                        return path2;
                    }
                    String unicode = this.font.getGlyphList().toUnicode(name);
                    if (unicode != null && unicode.length() == 1) {
                        String uniNameOfCodePoint = getUniNameOfCodePoint(unicode.codePointAt(0));
                        if (this.font.hasGlyph(uniNameOfCodePoint)) {
                            name = uniNameOfCodePoint;
                        }
                    }
                }
                Path path3 = this.font.getPath(name);
                return path3 == null ? this.font.getPath(".notdef") : path3;
            } catch (IOException e) {
                Log.e("PdfBox-Android", "Glyph rendering failed", e);
                return new Path();
            }
        }
        return path;
    }

    @Override // com.tom_roush.pdfbox.rendering.Glyph2D
    public void dispose() {
        this.cache.clear();
    }

    private static String getUniNameOfCodePoint(int i) {
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
