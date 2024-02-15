package com.tom_roush.pdfbox.rendering;

import com.tom_roush.pdfbox.pdmodel.PDPage;

/* loaded from: classes3.dex */
public final class PageDrawerParameters {
    private final RenderDestination destination;
    private final float imageDownscalingOptimizationThreshold;
    private final PDPage page;
    private final PDFRenderer renderer;
    private final boolean subsamplingAllowed;

    public PageDrawerParameters(PDFRenderer pDFRenderer, PDPage pDPage, boolean z, RenderDestination renderDestination, float f) {
        this.renderer = pDFRenderer;
        this.page = pDPage;
        this.subsamplingAllowed = z;
        this.destination = renderDestination;
        this.imageDownscalingOptimizationThreshold = f;
    }

    public PDPage getPage() {
        return this.page;
    }

    public PDFRenderer getRenderer() {
        return this.renderer;
    }

    public boolean isSubsamplingAllowed() {
        return this.subsamplingAllowed;
    }

    public RenderDestination getDestination() {
        return this.destination;
    }

    public float getImageDownscalingOptimizationThreshold() {
        return this.imageDownscalingOptimizationThreshold;
    }
}
