package com.tom_roush.fontbox.util.autodetect;

/* loaded from: classes3.dex */
public class AndroidFontDirFinder extends NativeFontDirFinder {
    @Override // com.tom_roush.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{"/system/fonts"};
    }
}
