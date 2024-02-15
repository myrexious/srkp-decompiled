package com.tom_roush.fontbox.ttf;

import java.util.List;

/* loaded from: classes3.dex */
public interface CmapLookup {
    List<Integer> getCharCodes(int i);

    int getGlyphId(int i);
}
