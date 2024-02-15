package org.apache.commons.imaging.formats.png.chunks;

import java.io.ByteArrayInputStream;
import org.apache.commons.imaging.common.BinaryFileParser;

/* loaded from: classes2.dex */
public class PngChunk extends BinaryFileParser {
    public final boolean ancillary;
    private final byte[] bytes;
    public final int chunkType;
    public final int crc;
    public final boolean isPrivate;
    public final int length;
    private final boolean[] propertyBits = new boolean[4];
    public final boolean reserved;
    public final boolean safeToCopy;

    public PngChunk(int i, int i2, int i3, byte[] bArr) {
        this.length = i;
        this.chunkType = i2;
        this.crc = i3;
        this.bytes = (byte[]) bArr.clone();
        int i4 = 24;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 < 4) {
                i4 -= 8;
                boolean[] zArr = this.propertyBits;
                if (((i2 >> i4) & 255 & 32) <= 0) {
                    z = false;
                }
                zArr[i5] = z;
                i5++;
            } else {
                boolean[] zArr2 = this.propertyBits;
                this.ancillary = zArr2[0];
                this.isPrivate = zArr2[1];
                this.reserved = zArr2[2];
                this.safeToCopy = zArr2[3];
                return;
            }
        }
    }

    public byte[] getBytes() {
        return (byte[]) this.bytes.clone();
    }

    public boolean[] getPropertyBits() {
        return (boolean[]) this.propertyBits.clone();
    }

    protected ByteArrayInputStream getDataStream() {
        return new ByteArrayInputStream(getBytes());
    }
}
