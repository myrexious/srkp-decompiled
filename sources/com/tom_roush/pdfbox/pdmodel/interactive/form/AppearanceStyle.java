package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.pdmodel.font.PDFont;

/* loaded from: classes3.dex */
public class AppearanceStyle {
    private PDFont font;
    private float fontSize = 12.0f;
    private float leading = 14.4f;

    public PDFont getFont() {
        return this.font;
    }

    public void setFont(PDFont pDFont) {
        this.font = pDFont;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(float f) {
        this.fontSize = f;
        this.leading = f * 1.2f;
    }

    public float getLeading() {
        return this.leading;
    }

    public void setLeading(float f) {
        this.leading = f;
    }
}
