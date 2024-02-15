package org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint;

import java.awt.Color;

/* loaded from: classes2.dex */
public class PaletteEntryForRange implements PaletteEntry {
    private final float a0;
    private final float a1;
    private final float b0;
    private final float b1;
    private final float g0;
    private final float g1;
    private final float r0;
    private final float r1;
    private final float v0;
    private final float v1;

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public boolean coversSingleEntry() {
        return false;
    }

    public PaletteEntryForRange(float f, float f2, Color color, Color color2) {
        this.v0 = f;
        this.v1 = f2;
        float f3 = f2 - f;
        if (f3 <= 0.0f || Float.isNaN(f3)) {
            throw new IllegalArgumentException("Specified values must be v0<v1");
        }
        if (color == null || color2 == null) {
            throw new IllegalArgumentException("Null colors not allowed");
        }
        int rgb = color.getRGB();
        this.a0 = (rgb >> 24) & 255;
        this.r0 = (rgb >> 16) & 255;
        this.g0 = (rgb >> 8) & 255;
        this.b0 = rgb & 255;
        int rgb2 = color2.getRGB();
        this.a1 = (rgb2 >> 24) & 255;
        this.r1 = (rgb2 >> 16) & 255;
        this.g1 = (rgb2 >> 8) & 255;
        this.b1 = rgb2 & 255;
    }

    public PaletteEntryForRange(float f, float f2, Color color) {
        this.v0 = f;
        this.v1 = f2;
        float f3 = f2 - f;
        if (f3 <= 0.0f || Float.isNaN(f3)) {
            throw new IllegalArgumentException("Specified values must be v0<v1");
        }
        if (color == null) {
            throw new IllegalArgumentException("Null colors not allowed");
        }
        int rgb = color.getRGB();
        this.a0 = (rgb >> 24) & 255;
        this.r0 = (rgb >> 16) & 255;
        this.g0 = (rgb >> 8) & 255;
        this.b0 = rgb & 255;
        int rgb2 = color.getRGB();
        this.a1 = (rgb2 >> 24) & 255;
        this.r1 = (rgb2 >> 16) & 255;
        this.g1 = (rgb2 >> 8) & 255;
        this.b1 = rgb2 & 255;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public boolean isCovered(float f) {
        return this.v0 <= f && f < this.v1;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public int getARGB(float f) {
        float f2 = this.v0;
        if (f2 <= f) {
            float f3 = this.v1;
            if (f <= f3) {
                float f4 = (f - f2) / (f3 - f2);
                float f5 = this.a1;
                float f6 = this.a0;
                float f7 = this.r1;
                float f8 = this.r0;
                int i = (int) (((f7 - f8) * f4) + f8 + 0.5d);
                float f9 = this.g1;
                float f10 = this.g0;
                float f11 = this.b1;
                float f12 = this.b0;
                return ((int) ((f4 * (f11 - f12)) + f12 + 0.5d)) | (((((((int) ((((f5 - f6) * f4) + f6) + 0.5d)) << 8) | i) << 8) | ((int) ((((f9 - f10) * f4) + f10) + 0.5d))) << 8);
            }
            return 0;
        }
        return 0;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public Color getColor(float f) {
        float f2 = this.v0;
        if (f2 <= f) {
            float f3 = this.v1;
            if (f <= f3) {
                float f4 = (f - f2) / (f3 - f2);
                float f5 = this.a1;
                float f6 = this.a0;
                float f7 = this.r1;
                float f8 = this.r0;
                int i = (int) (((f7 - f8) * f4) + f8 + 0.5d);
                float f9 = this.g1;
                float f10 = this.g0;
                int i2 = (int) (((f9 - f10) * f4) + f10 + 0.5d);
                float f11 = this.b1;
                float f12 = this.b0;
                return new Color(i, i2, (int) ((f4 * (f11 - f12)) + f12 + 0.5d), (int) (((f5 - f6) * f4) + f6 + 0.5d));
            }
            return null;
        }
        return null;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public float getLowerBound() {
        return this.v0;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public float getUpperBound() {
        return this.v1;
    }

    public String toString() {
        return "PaletteEntry for range " + this.v0 + ", " + this.v1;
    }
}
