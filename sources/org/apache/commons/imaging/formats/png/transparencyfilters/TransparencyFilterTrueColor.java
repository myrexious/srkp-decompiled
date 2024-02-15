package org.apache.commons.imaging.formats.png.transparencyfilters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class TransparencyFilterTrueColor extends TransparencyFilter {
    private final int transparentColor;

    public TransparencyFilterTrueColor(byte[] bArr) throws IOException {
        super(bArr);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        this.transparentColor = ((BinaryFunctions.read2Bytes("transparentRed", byteArrayInputStream, "tRNS: Missing transparentColor", getByteOrder()) & 255) << 16) | ((BinaryFunctions.read2Bytes("transparentGreen", byteArrayInputStream, "tRNS: Missing transparentColor", getByteOrder()) & 255) << 8) | ((BinaryFunctions.read2Bytes("transparentBlue", byteArrayInputStream, "tRNS: Missing transparentColor", getByteOrder()) & 255) << 0);
    }

    @Override // org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilter
    public int filter(int i, int i2) throws ImageReadException, IOException {
        if ((16777215 & i) == this.transparentColor) {
            return 0;
        }
        return i;
    }
}
