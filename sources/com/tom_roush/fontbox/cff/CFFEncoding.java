package com.tom_roush.fontbox.cff;

import com.tom_roush.fontbox.encoding.Encoding;

/* loaded from: classes3.dex */
public abstract class CFFEncoding extends Encoding {
    public void add(int i, int i2, String str) {
        addCharacterEncoding(i, str);
    }

    public void add(int i, int i2) {
        addCharacterEncoding(i, CFFStandardString.getName(i2));
    }
}
