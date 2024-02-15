package com.tom_roush.pdfbox.pdmodel.graphics.state;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.util.GraphicsUtil;
import com.tom_roush.pdfbox.util.Matrix;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDGraphicsState implements Cloneable {
    private boolean isClippingPathDirty;
    private RenderingIntent renderingIntent;
    private PDSoftMask softMask;
    private List<Path> clippingPaths = new ArrayList(1);
    private Map<Path, Region> clippingCache = new IdentityHashMap();
    private Matrix currentTransformationMatrix = new Matrix();
    private PDColor strokingColor = PDDeviceGray.INSTANCE.getInitialColor();
    private PDColor nonStrokingColor = PDDeviceGray.INSTANCE.getInitialColor();
    private PDColorSpace strokingColorSpace = PDDeviceGray.INSTANCE;
    private PDColorSpace nonStrokingColorSpace = PDDeviceGray.INSTANCE;
    private PDTextState textState = new PDTextState();
    private float lineWidth = 1.0f;
    private Paint.Cap lineCap = Paint.Cap.BUTT;
    private Paint.Join lineJoin = Paint.Join.MITER;
    private float miterLimit = 10.0f;
    private PDLineDashPattern lineDashPattern = new PDLineDashPattern();
    private boolean strokeAdjustment = false;
    private BlendMode blendMode = BlendMode.NORMAL;
    private double alphaConstant = 1.0d;
    private double nonStrokingAlphaConstant = 1.0d;
    private boolean alphaSource = false;
    private boolean overprint = false;
    private boolean nonStrokingOverprint = false;
    private double overprintMode = 0.0d;
    private COSBase transfer = null;
    private double flatness = 1.0d;
    private double smoothness = 0.0d;

    public PDGraphicsState(PDRectangle pDRectangle) {
        this.clippingPaths.add(pDRectangle.toGeneralPath());
    }

    public Matrix getCurrentTransformationMatrix() {
        return this.currentTransformationMatrix;
    }

    public void setCurrentTransformationMatrix(Matrix matrix) {
        this.currentTransformationMatrix = matrix;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }

    public void setLineWidth(float f) {
        this.lineWidth = f;
    }

    public Paint.Cap getLineCap() {
        return this.lineCap;
    }

    public void setLineCap(Paint.Cap cap) {
        this.lineCap = cap;
    }

    public Paint.Join getLineJoin() {
        return this.lineJoin;
    }

    public void setLineJoin(Paint.Join join) {
        this.lineJoin = join;
    }

    public float getMiterLimit() {
        return this.miterLimit;
    }

    public void setMiterLimit(float f) {
        this.miterLimit = f;
    }

    public boolean isStrokeAdjustment() {
        return this.strokeAdjustment;
    }

    public void setStrokeAdjustment(boolean z) {
        this.strokeAdjustment = z;
    }

    public double getAlphaConstant() {
        return this.alphaConstant;
    }

    public void setAlphaConstant(double d) {
        this.alphaConstant = d;
    }

    @Deprecated
    public double getNonStrokeAlphaConstants() {
        return this.nonStrokingAlphaConstant;
    }

    @Deprecated
    public void setNonStrokeAlphaConstants(double d) {
        this.nonStrokingAlphaConstant = d;
    }

    public double getNonStrokeAlphaConstant() {
        return this.nonStrokingAlphaConstant;
    }

    public void setNonStrokeAlphaConstant(double d) {
        this.nonStrokingAlphaConstant = d;
    }

    public boolean isAlphaSource() {
        return this.alphaSource;
    }

    public void setAlphaSource(boolean z) {
        this.alphaSource = z;
    }

    public PDSoftMask getSoftMask() {
        return this.softMask;
    }

    public void setSoftMask(PDSoftMask pDSoftMask) {
        this.softMask = pDSoftMask;
    }

    public BlendMode getBlendMode() {
        return this.blendMode;
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    public boolean isOverprint() {
        return this.overprint;
    }

    public void setOverprint(boolean z) {
        this.overprint = z;
    }

    public boolean isNonStrokingOverprint() {
        return this.nonStrokingOverprint;
    }

    public void setNonStrokingOverprint(boolean z) {
        this.nonStrokingOverprint = z;
    }

    public double getOverprintMode() {
        return this.overprintMode;
    }

    public void setOverprintMode(double d) {
        this.overprintMode = d;
    }

    public double getFlatness() {
        return this.flatness;
    }

    public void setFlatness(double d) {
        this.flatness = d;
    }

    public double getSmoothness() {
        return this.smoothness;
    }

    public void setSmoothness(double d) {
        this.smoothness = d;
    }

    public PDTextState getTextState() {
        return this.textState;
    }

    public void setTextState(PDTextState pDTextState) {
        this.textState = pDTextState;
    }

    public PDLineDashPattern getLineDashPattern() {
        return this.lineDashPattern;
    }

    public void setLineDashPattern(PDLineDashPattern pDLineDashPattern) {
        this.lineDashPattern = pDLineDashPattern;
    }

    public RenderingIntent getRenderingIntent() {
        return this.renderingIntent;
    }

    public void setRenderingIntent(RenderingIntent renderingIntent) {
        this.renderingIntent = renderingIntent;
    }

    /* renamed from: clone */
    public PDGraphicsState m266clone() {
        try {
            PDGraphicsState pDGraphicsState = (PDGraphicsState) super.clone();
            pDGraphicsState.textState = this.textState.m267clone();
            pDGraphicsState.currentTransformationMatrix = this.currentTransformationMatrix.m268clone();
            pDGraphicsState.strokingColor = this.strokingColor;
            pDGraphicsState.nonStrokingColor = this.nonStrokingColor;
            pDGraphicsState.lineDashPattern = this.lineDashPattern;
            pDGraphicsState.clippingPaths = this.clippingPaths;
            pDGraphicsState.clippingCache = this.clippingCache;
            pDGraphicsState.isClippingPathDirty = false;
            return pDGraphicsState;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public PDColor getStrokingColor() {
        return this.strokingColor;
    }

    public void setStrokingColor(PDColor pDColor) {
        this.strokingColor = pDColor;
    }

    public PDColor getNonStrokingColor() {
        return this.nonStrokingColor;
    }

    public void setNonStrokingColor(PDColor pDColor) {
        this.nonStrokingColor = pDColor;
    }

    public PDColorSpace getStrokingColorSpace() {
        return this.strokingColorSpace;
    }

    public void setStrokingColorSpace(PDColorSpace pDColorSpace) {
        this.strokingColorSpace = pDColorSpace;
    }

    public PDColorSpace getNonStrokingColorSpace() {
        return this.nonStrokingColorSpace;
    }

    public void setNonStrokingColorSpace(PDColorSpace pDColorSpace) {
        this.nonStrokingColorSpace = pDColorSpace;
    }

    public void intersectClippingPath(Path path) {
        intersectClippingPath(path, true);
    }

    private void intersectClippingPath(Path path, boolean z) {
        if (!this.isClippingPathDirty) {
            this.clippingPaths = new ArrayList(this.clippingPaths);
            this.isClippingPathDirty = true;
        }
        List<Path> list = this.clippingPaths;
        if (z) {
            path = new Path(path);
        }
        list.add(path);
    }

    public void intersectClippingPath(Region region) {
        intersectClippingPath(region.getBoundaryPath(), false);
    }

    public Region getCurrentClippingPath() {
        if (this.clippingPaths.size() == 1) {
            Path path = this.clippingPaths.get(0);
            Region region = this.clippingCache.get(path);
            if (region == null) {
                Region pathRegion = GraphicsUtil.getPathRegion(path);
                this.clippingCache.put(path, pathRegion);
                return pathRegion;
            }
            return region;
        }
        Path path2 = new Path(this.clippingPaths.get(0));
        for (int i = 1; i < this.clippingPaths.size(); i++) {
            path2.op(this.clippingPaths.get(i), Path.Op.INTERSECT);
        }
        Region pathRegion2 = GraphicsUtil.getPathRegion(path2);
        ArrayList arrayList = new ArrayList(1);
        this.clippingPaths = arrayList;
        arrayList.add(path2);
        this.clippingCache.put(path2, pathRegion2);
        return pathRegion2;
    }

    public List<Path> getCurrentClippingPaths() {
        return this.clippingPaths;
    }

    public COSBase getTransfer() {
        return this.transfer;
    }

    public void setTransfer(COSBase cOSBase) {
        this.transfer = cOSBase;
    }
}
