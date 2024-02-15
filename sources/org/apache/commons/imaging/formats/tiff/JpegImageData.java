package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.formats.tiff.TiffElement;

/* loaded from: classes2.dex */
public class JpegImageData extends TiffElement.DataElement {
    public JpegImageData(long j, int i, byte[] bArr) {
        super(j, i, bArr);
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffElement
    public String getElementDescription() {
        return "Jpeg image data: " + getDataLength() + " bytes";
    }
}
