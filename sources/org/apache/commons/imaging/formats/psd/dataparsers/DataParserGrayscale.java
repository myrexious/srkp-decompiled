package org.apache.commons.imaging.formats.psd.dataparsers;

import androidx.core.view.ViewCompat;
import org.apache.commons.imaging.formats.psd.PsdImageContents;

/* loaded from: classes2.dex */
public class DataParserGrayscale extends DataParser {
    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    public int getBasicChannelsCount() {
        return 1;
    }

    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    protected int getRGB(int[][][] iArr, int i, int i2, PsdImageContents psdImageContents) {
        int i3 = iArr[0][i2][i] & 255 & 255;
        return (i3 << 0) | (i3 << 16) | ViewCompat.MEASURED_STATE_MASK | (i3 << 8);
    }
}
