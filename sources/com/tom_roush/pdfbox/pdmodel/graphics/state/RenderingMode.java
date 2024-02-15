package com.tom_roush.pdfbox.pdmodel.graphics.state;

/* loaded from: classes3.dex */
public enum RenderingMode {
    FILL(0),
    STROKE(1),
    FILL_STROKE(2),
    NEITHER(3),
    FILL_CLIP(4),
    STROKE_CLIP(5),
    FILL_STROKE_CLIP(6),
    NEITHER_CLIP(7);
    
    private static final RenderingMode[] VALUES = values();
    private final int value;

    public static RenderingMode fromInt(int i) {
        return VALUES[i];
    }

    RenderingMode(int i) {
        this.value = i;
    }

    public int intValue() {
        return this.value;
    }

    public boolean isFill() {
        return this == FILL || this == FILL_STROKE || this == FILL_CLIP || this == FILL_STROKE_CLIP;
    }

    public boolean isStroke() {
        return this == STROKE || this == FILL_STROKE || this == STROKE_CLIP || this == FILL_STROKE_CLIP;
    }

    public boolean isClip() {
        return this == FILL_CLIP || this == STROKE_CLIP || this == FILL_STROKE_CLIP || this == NEITHER_CLIP;
    }
}
