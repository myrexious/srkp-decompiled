package org.apache.commons.imaging.formats.png.chunks;

import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class PngChunkGama extends PngChunk {
    public final int gamma;

    public PngChunkGama(int i, int i2, int i3, byte[] bArr) throws IOException {
        super(i, i2, i3, bArr);
        this.gamma = BinaryFunctions.read4Bytes(ExifInterface.TAG_GAMMA, new ByteArrayInputStream(bArr), "Not a Valid Png File: gAMA Corrupt", getByteOrder());
    }

    public double getGamma() {
        return 1.0d / (this.gamma / 100000.0d);
    }
}
