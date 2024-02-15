package org.apache.commons.imaging.formats.tiff.write;

import org.apache.commons.imaging.formats.tiff.TiffElement;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputItem;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ImageDataOffsets {
    final int[] imageDataOffsets;
    final TiffOutputField imageDataOffsetsField;
    final TiffOutputItem[] outputItems;

    public ImageDataOffsets(TiffElement.DataElement[] dataElementArr, int[] iArr, TiffOutputField tiffOutputField) {
        this.imageDataOffsets = iArr;
        this.imageDataOffsetsField = tiffOutputField;
        this.outputItems = new TiffOutputItem[dataElementArr.length];
        for (int i = 0; i < dataElementArr.length; i++) {
            this.outputItems[i] = new TiffOutputItem.Value("TIFF image data", dataElementArr[i].getData());
        }
    }
}
