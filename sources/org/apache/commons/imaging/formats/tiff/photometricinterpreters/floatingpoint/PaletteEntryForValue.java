package org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint;

import java.awt.Color;

/* loaded from: classes2.dex */
public class PaletteEntryForValue implements PaletteEntry {
    private final Color color;
    private final int iColor;
    private final boolean isNull;
    private final float value;

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public boolean coversSingleEntry() {
        return true;
    }

    public PaletteEntryForValue(float f, Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Null colors not allowed");
        }
        this.value = f;
        this.color = color;
        this.iColor = color.getRGB();
        this.isNull = Float.isNaN(f);
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public boolean isCovered(float f) {
        if (this.isNull) {
            return Float.isNaN(f);
        }
        return f == this.value;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public int getARGB(float f) {
        if (this.isNull && Float.isNaN(f)) {
            return this.iColor;
        }
        if (f == this.value) {
            return this.iColor;
        }
        return 0;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public Color getColor(float f) {
        if (this.isNull && Float.isNaN(f)) {
            return this.color;
        }
        if (f == this.value) {
            return this.color;
        }
        return null;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public float getLowerBound() {
        return this.value;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry
    public float getUpperBound() {
        return this.value;
    }

    public String toString() {
        return "PaletteEntry for single value" + this.value;
    }
}
