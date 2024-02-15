package org.apache.commons.imaging.formats.pnm;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImagingParameters;

/* loaded from: classes2.dex */
public class PnmImagingParameters extends ImagingParameters {
    private boolean rawBits = true;
    private ImageFormats subtype = null;

    public boolean isRawBits() {
        return this.rawBits;
    }

    public void setRawBits(boolean z) {
        this.rawBits = z;
    }

    public ImageFormats getSubtype() {
        return this.subtype;
    }

    public void setSubtype(ImageFormats imageFormats) {
        this.subtype = imageFormats;
    }
}
