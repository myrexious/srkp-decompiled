package com.tom_roush.fontbox.ttf;

import java.util.List;

/* loaded from: classes3.dex */
public class SubstitutingCmapLookup implements CmapLookup {
    private final CmapSubtable cmap;
    private final List<String> enabledFeatures;
    private final GlyphSubstitutionTable gsub;

    public SubstitutingCmapLookup(CmapSubtable cmapSubtable, GlyphSubstitutionTable glyphSubstitutionTable, List<String> list) {
        this.cmap = cmapSubtable;
        this.gsub = glyphSubstitutionTable;
        this.enabledFeatures = list;
    }

    @Override // com.tom_roush.fontbox.ttf.CmapLookup
    public int getGlyphId(int i) {
        return this.gsub.getSubstitution(this.cmap.getGlyphId(i), OpenTypeScript.getScriptTags(i), this.enabledFeatures);
    }

    @Override // com.tom_roush.fontbox.ttf.CmapLookup
    public List<Integer> getCharCodes(int i) {
        return this.cmap.getCharCodes(this.gsub.getUnsubstitution(i));
    }
}
