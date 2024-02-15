package com.tom_roush.fontbox.util.autodetect;

/* loaded from: classes3.dex */
public class MacFontDirFinder extends NativeFontDirFinder {
    @Override // com.tom_roush.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{System.getProperty("user.home") + "/Library/Fonts/", "/Library/Fonts/", "/System/Library/Fonts/", "/Network/Library/Fonts/"};
    }
}
