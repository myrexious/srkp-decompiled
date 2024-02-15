package org.apache.commons.imaging.formats.tiff;

import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class TiffHeader extends TiffElement {
    public final ByteOrder byteOrder;
    public final long offsetToFirstIFD;
    public final int tiffVersion;

    @Override // org.apache.commons.imaging.formats.tiff.TiffElement
    public String getElementDescription() {
        return "TIFF Header";
    }

    public TiffHeader(ByteOrder byteOrder, int i, long j) {
        super(0L, 8);
        this.byteOrder = byteOrder;
        this.tiffVersion = i;
        this.offsetToFirstIFD = j;
    }
}
