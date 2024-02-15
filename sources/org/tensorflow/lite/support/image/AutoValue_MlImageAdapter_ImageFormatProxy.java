package org.tensorflow.lite.support.image;

import org.apache.commons.text.StringSubstitutor;
import org.tensorflow.lite.support.image.MlImageAdapter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class AutoValue_MlImageAdapter_ImageFormatProxy extends MlImageAdapter.ImageFormatProxy {
    private final ColorSpaceType colorSpaceType;
    private final int imageFormat;

    public AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType colorSpaceType, int imageFormat) {
        if (colorSpaceType == null) {
            throw new NullPointerException("Null colorSpaceType");
        }
        this.colorSpaceType = colorSpaceType;
        this.imageFormat = imageFormat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.tensorflow.lite.support.image.MlImageAdapter.ImageFormatProxy
    public ColorSpaceType getColorSpaceType() {
        return this.colorSpaceType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.tensorflow.lite.support.image.MlImageAdapter.ImageFormatProxy
    public int getImageFormat() {
        return this.imageFormat;
    }

    public String toString() {
        return "ImageFormatProxy{colorSpaceType=" + this.colorSpaceType + ", imageFormat=" + this.imageFormat + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof MlImageAdapter.ImageFormatProxy) {
            MlImageAdapter.ImageFormatProxy imageFormatProxy = (MlImageAdapter.ImageFormatProxy) o;
            return this.colorSpaceType.equals(imageFormatProxy.getColorSpaceType()) && this.imageFormat == imageFormatProxy.getImageFormat();
        }
        return false;
    }

    public int hashCode() {
        return ((this.colorSpaceType.hashCode() ^ 1000003) * 1000003) ^ this.imageFormat;
    }
}
