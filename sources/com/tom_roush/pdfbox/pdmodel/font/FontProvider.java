package com.tom_roush.pdfbox.pdmodel.font;

import java.util.List;

/* loaded from: classes3.dex */
public abstract class FontProvider {
    public abstract List<? extends FontInfo> getFontInfo();

    public abstract String toDebugString();
}
