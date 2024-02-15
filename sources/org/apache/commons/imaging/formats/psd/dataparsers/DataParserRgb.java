package org.apache.commons.imaging.formats.psd.dataparsers;

import androidx.core.view.ViewCompat;
import org.apache.commons.imaging.formats.psd.PsdImageContents;

/* loaded from: classes2.dex */
public class DataParserRgb extends DataParser {
    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    public int getBasicChannelsCount() {
        return 3;
    }

    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    protected int getRGB(int[][][] iArr, int i, int i2, PsdImageContents psdImageContents) {
        return (((iArr[2][i2][i] & 255) & 255) << 0) | (((iArr[0][i2][i] & 255) & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | (((iArr[1][i2][i] & 255) & 255) << 8);
    }
}
