package com.tom_roush.pdfbox.rendering;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType0;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class CIDType0Glyph2D implements Glyph2D {
    private final Map<Integer, Path> cache = new HashMap();
    private final PDCIDFontType0 font;
    private final String fontName;

    public CIDType0Glyph2D(PDCIDFontType0 pDCIDFontType0) {
        this.font = pDCIDFontType0;
        this.fontName = pDCIDFontType0.getBaseFont();
    }

    @Override // com.tom_roush.pdfbox.rendering.Glyph2D
    public Path getPathForCharacterCode(int i) {
        Path path = this.cache.get(Integer.valueOf(i));
        if (path == null) {
            try {
                if (!this.font.hasGlyph(i)) {
                    Log.w("PdfBox-Android", "No glyph for " + i + " (CID " + String.format("%04x", Integer.valueOf(this.font.getParent().codeToCID(i))) + ") in font " + this.fontName);
                }
                Path path2 = this.font.getPath(i);
                this.cache.put(Integer.valueOf(i), path2);
                return path2;
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
}
