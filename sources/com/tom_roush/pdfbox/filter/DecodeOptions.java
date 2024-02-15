package com.tom_roush.pdfbox.filter;

import android.graphics.Rect;

/* loaded from: classes3.dex */
public class DecodeOptions {
    public static final DecodeOptions DEFAULT = new FinalDecodeOptions(true);
    private boolean filterSubsampled;
    private Rect sourceRegion;
    private int subsamplingOffsetX;
    private int subsamplingOffsetY;
    private int subsamplingX;
    private int subsamplingY;

    public DecodeOptions() {
        this.sourceRegion = null;
        this.subsamplingX = 1;
        this.subsamplingY = 1;
        this.subsamplingOffsetX = 0;
        this.subsamplingOffsetY = 0;
        this.filterSubsampled = false;
    }

    public DecodeOptions(Rect rect) {
        this.subsamplingX = 1;
        this.subsamplingY = 1;
        this.subsamplingOffsetX = 0;
        this.subsamplingOffsetY = 0;
        this.filterSubsampled = false;
        this.sourceRegion = rect;
    }

    public DecodeOptions(int i, int i2, int i3, int i4) {
        this(new Rect(i, i2, i3, i4));
    }

    public DecodeOptions(int i) {
        this.sourceRegion = null;
        this.subsamplingOffsetX = 0;
        this.subsamplingOffsetY = 0;
        this.filterSubsampled = false;
        this.subsamplingX = i;
        this.subsamplingY = i;
    }

    public Rect getSourceRegion() {
        return this.sourceRegion;
    }

    public void setSourceRegion(Rect rect) {
        this.sourceRegion = rect;
    }

    public int getSubsamplingX() {
        return this.subsamplingX;
    }

    public void setSubsamplingX(int i) {
        this.subsamplingX = i;
    }

    public int getSubsamplingY() {
        return this.subsamplingY;
    }

    public void setSubsamplingY(int i) {
        this.subsamplingY = i;
    }

    public int getSubsamplingOffsetX() {
        return this.subsamplingOffsetX;
    }

    public void setSubsamplingOffsetX(int i) {
        this.subsamplingOffsetX = i;
    }

    public int getSubsamplingOffsetY() {
        return this.subsamplingOffsetY;
    }

    public void setSubsamplingOffsetY(int i) {
        this.subsamplingOffsetY = i;
    }

    public boolean isFilterSubsampled() {
        return this.filterSubsampled;
    }

    void setFilterSubsampled(boolean z) {
        this.filterSubsampled = z;
    }

    /* loaded from: classes3.dex */
    private static class FinalDecodeOptions extends DecodeOptions {
        @Override // com.tom_roush.pdfbox.filter.DecodeOptions
        void setFilterSubsampled(boolean z) {
        }

        FinalDecodeOptions(boolean z) {
            super.setFilterSubsampled(z);
        }

        @Override // com.tom_roush.pdfbox.filter.DecodeOptions
        public void setSourceRegion(Rect rect) {
            throw new UnsupportedOperationException("This instance may not be modified.");
        }

        @Override // com.tom_roush.pdfbox.filter.DecodeOptions
        public void setSubsamplingX(int i) {
            throw new UnsupportedOperationException("This instance may not be modified.");
        }

        @Override // com.tom_roush.pdfbox.filter.DecodeOptions
        public void setSubsamplingY(int i) {
            throw new UnsupportedOperationException("This instance may not be modified.");
        }

        @Override // com.tom_roush.pdfbox.filter.DecodeOptions
        public void setSubsamplingOffsetX(int i) {
            throw new UnsupportedOperationException("This instance may not be modified.");
        }

        @Override // com.tom_roush.pdfbox.filter.DecodeOptions
        public void setSubsamplingOffsetY(int i) {
            throw new UnsupportedOperationException("This instance may not be modified.");
        }
    }
}
