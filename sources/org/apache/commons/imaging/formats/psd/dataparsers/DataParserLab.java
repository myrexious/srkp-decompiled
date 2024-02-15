package org.apache.commons.imaging.formats.psd.dataparsers;

import org.apache.commons.imaging.color.ColorConversions;
import org.apache.commons.imaging.formats.psd.PsdImageContents;

/* loaded from: classes2.dex */
public class DataParserLab extends DataParser {
    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    public int getBasicChannelsCount() {
        return 3;
    }

    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    protected int getRGB(int[][][] iArr, int i, int i2, PsdImageContents psdImageContents) {
        return ColorConversions.convertCIELabtoARGBTest(iArr[0][i2][i] & 255, (iArr[1][i2][i] & 255) - 128, (iArr[2][i2][i] & 255) - 128);
    }
}
