package org.apache.commons.imaging.formats.bmp;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BmpImageContents {
    final BmpHeaderInfo bhi;
    final byte[] colorTable;
    final byte[] imageData;
    final PixelParser pixelParser;

    public BmpImageContents(BmpHeaderInfo bmpHeaderInfo, byte[] bArr, byte[] bArr2, PixelParser pixelParser) {
        this.bhi = bmpHeaderInfo;
        this.colorTable = bArr;
        this.imageData = bArr2;
        this.pixelParser = pixelParser;
    }
}
