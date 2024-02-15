package org.apache.commons.imaging.formats.png;

import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.common.XmpImagingParameters;

/* loaded from: classes2.dex */
public class PngImagingParameters extends XmpImagingParameters {
    public static final byte DEFAULT_BIT_DEPTH = 8;
    private byte bitDepth = 8;
    private boolean forceIndexedColor = false;
    private boolean forceTrueColor = false;
    private boolean predictorEnabled = false;
    private PhysicalScale physicalScale = null;
    private List<? extends PngText> textChunks = null;

    public byte getBitDepth() {
        return this.bitDepth;
    }

    public void setBitDepth(byte b) {
        this.bitDepth = b;
    }

    public boolean isForceIndexedColor() {
        return this.forceIndexedColor;
    }

    public void setForceIndexedColor(boolean z) {
        this.forceIndexedColor = z;
    }

    public boolean isForceTrueColor() {
        return this.forceTrueColor;
    }

    public void setForceTrueColor(boolean z) {
        this.forceTrueColor = z;
    }

    public PhysicalScale getPhysicalScale() {
        return this.physicalScale;
    }

    public void setPhysicalScale(PhysicalScale physicalScale) {
        this.physicalScale = physicalScale;
    }

    public List<? extends PngText> getTextChunks() {
        List<? extends PngText> list = this.textChunks;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return null;
    }

    public void setTextChunks(List<? extends PngText> list) {
        this.textChunks = Collections.unmodifiableList(list);
    }

    public boolean isPredictorEnabled() {
        return this.predictorEnabled;
    }

    public void setPredictorEnabled(boolean z) {
        this.predictorEnabled = z;
    }
}
