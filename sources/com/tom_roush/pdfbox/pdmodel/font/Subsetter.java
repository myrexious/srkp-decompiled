package com.tom_roush.pdfbox.pdmodel.font;

import java.io.IOException;

/* loaded from: classes3.dex */
interface Subsetter {
    void addToSubset(int i);

    void subset() throws IOException;
}
