package org.apache.commons.imaging.formats.psd.dataparsers;

import androidx.core.view.ViewCompat;
import kotlin.UByte;
import org.apache.commons.imaging.formats.psd.PsdImageContents;

/* loaded from: classes2.dex */
public class DataParserIndexed extends DataParser {
    private final int[] colorTable = new int[256];

    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    public int getBasicChannelsCount() {
        return 1;
    }

    public DataParserIndexed(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.colorTable[i] = (((bArr[i + 0] & UByte.MAX_VALUE) & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | (((bArr[i + 256] & UByte.MAX_VALUE) & 255) << 8) | (((bArr[i + 512] & UByte.MAX_VALUE) & 255) << 0);
        }
    }

    @Override // org.apache.commons.imaging.formats.psd.dataparsers.DataParser
    protected int getRGB(int[][][] iArr, int i, int i2, PsdImageContents psdImageContents) {
        return this.colorTable[iArr[0][i2][i] & 255];
    }
}
