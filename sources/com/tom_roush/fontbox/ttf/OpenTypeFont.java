package com.tom_roush.fontbox.ttf;

import android.graphics.Path;
import java.io.IOException;

/* loaded from: classes3.dex */
public class OpenTypeFont extends TrueTypeFont {
    private boolean isPostScript;

    public OpenTypeFont(TTFDataStream tTFDataStream) {
        super(tTFDataStream);
    }

    @Override // com.tom_roush.fontbox.ttf.TrueTypeFont
    public void setVersion(float f) {
        this.isPostScript = Float.floatToIntBits(f) == 1184802985;
        super.setVersion(f);
    }

    public CFFTable getCFF() throws IOException {
        if (!this.isPostScript) {
            throw new UnsupportedOperationException("TTF fonts do not have a CFF table");
        }
        return (CFFTable) getTable(CFFTable.TAG);
    }

    @Override // com.tom_roush.fontbox.ttf.TrueTypeFont
    public GlyphTable getGlyph() throws IOException {
        if (this.isPostScript) {
            throw new UnsupportedOperationException("OTF fonts do not have a glyf table");
        }
        return super.getGlyph();
    }

    @Override // com.tom_roush.fontbox.ttf.TrueTypeFont, com.tom_roush.fontbox.FontBoxFont
    public Path getPath(String str) throws IOException {
        return getCFF().getFont().getType2CharString(nameToGID(str)).getPath();
    }

    public boolean isPostScript() {
        return this.tables.containsKey(CFFTable.TAG);
    }

    public boolean hasLayoutTables() {
        return this.tables.containsKey("BASE") || this.tables.containsKey("GDEF") || this.tables.containsKey("GPOS") || this.tables.containsKey(GlyphSubstitutionTable.TAG) || this.tables.containsKey("JSTF");
    }
}
