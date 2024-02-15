package com.tom_roush.fontbox.ttf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public final class OTFParser extends TTFParser {
    @Override // com.tom_roush.fontbox.ttf.TTFParser
    protected boolean allowCFF() {
        return true;
    }

    public OTFParser() {
    }

    public OTFParser(boolean z) {
        this(z, false);
    }

    public OTFParser(boolean z, boolean z2) {
        super(z, z2);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFParser
    public OpenTypeFont parse(String str) throws IOException {
        return (OpenTypeFont) super.parse(str);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFParser
    public OpenTypeFont parse(File file) throws IOException {
        return (OpenTypeFont) super.parse(file);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFParser
    public OpenTypeFont parse(InputStream inputStream) throws IOException {
        return (OpenTypeFont) super.parse(inputStream);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFParser
    public OpenTypeFont parse(TTFDataStream tTFDataStream) throws IOException {
        return (OpenTypeFont) super.parse(tTFDataStream);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFParser
    public OpenTypeFont newFont(TTFDataStream tTFDataStream) {
        return new OpenTypeFont(tTFDataStream);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFParser
    public TTFTable readTable(TrueTypeFont trueTypeFont, String str) {
        if (str.equals("BASE") || str.equals("GDEF") || str.equals("GPOS") || str.equals(GlyphSubstitutionTable.TAG) || str.equals("JSTF")) {
            return new OTLTable(trueTypeFont);
        }
        if (str.equals(CFFTable.TAG)) {
            return new CFFTable(trueTypeFont);
        }
        return super.readTable(trueTypeFont, str);
    }
}
