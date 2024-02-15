package org.apache.commons.imaging.formats.png.chunks;

import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class PngChunkScal extends PngChunk {
    public final int unitSpecifier;
    public final double unitsPerPixelXAxis;
    public final double unitsPerPixelYAxis;

    public PngChunkScal(int i, int i2, int i3, byte[] bArr) throws ImageReadException {
        super(i, i2, i3, bArr);
        byte b = bArr[0];
        this.unitSpecifier = b;
        if (b != 1 && b != 2) {
            throw new ImageReadException("PNG sCAL invalid unit specifier: " + ((int) b));
        }
        int findNull = BinaryFunctions.findNull(bArr);
        if (findNull < 0) {
            throw new ImageReadException("PNG sCAL x and y axis value separator not found.");
        }
        this.unitsPerPixelXAxis = toDouble(new String(bArr, 1, findNull - 1, StandardCharsets.ISO_8859_1));
        int i4 = findNull + 1;
        if (i4 >= i) {
            throw new ImageReadException("PNG sCAL chunk missing the y axis value.");
        }
        this.unitsPerPixelYAxis = toDouble(new String(bArr, i4, i - i4, StandardCharsets.ISO_8859_1));
    }

    private double toDouble(String str) throws ImageReadException {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            throw new ImageReadException("PNG sCAL error reading axis value - " + str);
        }
    }
}
