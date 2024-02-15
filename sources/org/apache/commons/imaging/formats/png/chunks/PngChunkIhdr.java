package org.apache.commons.imaging.formats.png.chunks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.png.InterlaceMethod;
import org.apache.commons.imaging.formats.png.PngColorType;

/* loaded from: classes2.dex */
public class PngChunkIhdr extends PngChunk {
    public final int bitDepth;
    public final int compressionMethod;
    public final int filterMethod;
    public final int height;
    public final InterlaceMethod interlaceMethod;
    public final PngColorType pngColorType;
    public final int width;

    public PngChunkIhdr(int i, int i2, int i3, byte[] bArr) throws ImageReadException, IOException {
        super(i, i2, i3, bArr);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        this.width = BinaryFunctions.read4Bytes("Width", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt", getByteOrder());
        this.height = BinaryFunctions.read4Bytes("Height", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt", getByteOrder());
        this.bitDepth = BinaryFunctions.readByte("BitDepth", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt");
        byte readByte = BinaryFunctions.readByte("ColorType", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt");
        PngColorType colorType = PngColorType.getColorType(readByte);
        this.pngColorType = colorType;
        if (colorType == null) {
            throw new ImageReadException("PNG: unknown color type: " + ((int) readByte));
        }
        this.compressionMethod = BinaryFunctions.readByte("CompressionMethod", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt");
        this.filterMethod = BinaryFunctions.readByte("FilterMethod", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt");
        byte readByte2 = BinaryFunctions.readByte("InterlaceMethod", byteArrayInputStream, "Not a Valid Png File: IHDR Corrupt");
        if (readByte2 < 0 || readByte2 >= InterlaceMethod.values().length) {
            throw new ImageReadException("PNG: unknown interlace method: " + ((int) readByte2));
        }
        this.interlaceMethod = InterlaceMethod.values()[readByte2];
    }
}
