package org.apache.commons.imaging.formats.psd.dataparsers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import org.apache.commons.imaging.formats.psd.PsdHeaderInfo;
import org.apache.commons.imaging.formats.psd.PsdImageContents;

/* loaded from: classes2.dex */
public abstract class DataParser {
    public abstract int getBasicChannelsCount();

    protected abstract int getRGB(int[][][] iArr, int i, int i2, PsdImageContents psdImageContents);

    public final void parseData(int[][][] iArr, BufferedImage bufferedImage, PsdImageContents psdImageContents) {
        DataBuffer dataBuffer = bufferedImage.getRaster().getDataBuffer();
        PsdHeaderInfo psdHeaderInfo = psdImageContents.header;
        int i = psdHeaderInfo.columns;
        int i2 = psdHeaderInfo.rows;
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                dataBuffer.setElem((i3 * i) + i4, getRGB(iArr, i4, i3, psdImageContents));
            }
        }
    }
}
