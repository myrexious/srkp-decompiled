package org.apache.commons.imaging.formats.tiff.write;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.List;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryOutputStream;

/* loaded from: classes2.dex */
public class TiffImageWriterLossy extends TiffImageWriterBase {
    public TiffImageWriterLossy() {
    }

    public TiffImageWriterLossy(ByteOrder byteOrder) {
        super(byteOrder);
    }

    @Override // org.apache.commons.imaging.formats.tiff.write.TiffImageWriterBase
    public void write(OutputStream outputStream, TiffOutputSet tiffOutputSet) throws IOException, ImageWriteException {
        TiffOutputSummary validateDirectories = validateDirectories(tiffOutputSet);
        List<TiffOutputItem> outputItems = tiffOutputSet.getOutputItems(validateDirectories);
        updateOffsetsStep(outputItems);
        validateDirectories.updateOffsets(this.byteOrder);
        writeStep(new BinaryOutputStream(outputStream, this.byteOrder), outputItems);
    }

    private void updateOffsetsStep(List<TiffOutputItem> list) {
        int i = 8;
        for (TiffOutputItem tiffOutputItem : list) {
            tiffOutputItem.setOffset(i);
            int itemLength = tiffOutputItem.getItemLength();
            i = i + itemLength + imageDataPaddingLength(itemLength);
        }
    }

    private void writeStep(BinaryOutputStream binaryOutputStream, List<TiffOutputItem> list) throws IOException, ImageWriteException {
        writeImageFileHeader(binaryOutputStream);
        for (TiffOutputItem tiffOutputItem : list) {
            tiffOutputItem.writeItem(binaryOutputStream);
            int imageDataPaddingLength = imageDataPaddingLength(tiffOutputItem.getItemLength());
            for (int i = 0; i < imageDataPaddingLength; i++) {
                binaryOutputStream.write(0);
            }
        }
    }
}
