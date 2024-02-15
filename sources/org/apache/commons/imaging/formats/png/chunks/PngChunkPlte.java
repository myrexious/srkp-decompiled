package org.apache.commons.imaging.formats.png.chunks;

import androidx.core.view.ViewCompat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.png.GammaCorrection;

/* loaded from: classes2.dex */
public class PngChunkPlte extends PngChunk {
    private final int[] rgb;

    public PngChunkPlte(int i, int i2, int i3, byte[] bArr) throws ImageReadException, IOException {
        super(i, i2, i3, bArr);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        if (i % 3 != 0) {
            throw new ImageReadException("PLTE: wrong length: " + i);
        }
        int i4 = i / 3;
        this.rgb = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            this.rgb[i5] = ((BinaryFunctions.readByte("red[" + i5 + "]", byteArrayInputStream, "Not a Valid Png File: PLTE Corrupt") & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK | ((BinaryFunctions.readByte("green[" + i5 + "]", byteArrayInputStream, "Not a Valid Png File: PLTE Corrupt") & UByte.MAX_VALUE) << 8) | ((BinaryFunctions.readByte("blue[" + i5 + "]", byteArrayInputStream, "Not a Valid Png File: PLTE Corrupt") & UByte.MAX_VALUE) << 0);
        }
    }

    public int[] getRgb() {
        return (int[]) this.rgb.clone();
    }

    public int getRGB(int i) throws ImageReadException {
        if (i >= 0) {
            int[] iArr = this.rgb;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        throw new ImageReadException("PNG: unknown Palette reference: " + i);
    }

    public void correct(GammaCorrection gammaCorrection) {
        int i = 0;
        while (true) {
            int[] iArr = this.rgb;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = gammaCorrection.correctARGB(iArr[i]);
            i++;
        }
    }
}
