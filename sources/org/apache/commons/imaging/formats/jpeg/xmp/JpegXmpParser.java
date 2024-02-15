package org.apache.commons.imaging.formats.jpeg.xmp;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;

/* loaded from: classes2.dex */
public class JpegXmpParser extends BinaryFileParser {
    public JpegXmpParser() {
        setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    public boolean isXmpJpegSegment(byte[] bArr) {
        return BinaryFunctions.startsWith(bArr, JpegConstants.XMP_IDENTIFIER);
    }

    public String parseXmpJpegSegment(byte[] bArr) throws ImageReadException {
        if (!isXmpJpegSegment(bArr)) {
            throw new ImageReadException("Invalid JPEG XMP Segment.");
        }
        int size = JpegConstants.XMP_IDENTIFIER.size();
        return new String(bArr, size, bArr.length - size, StandardCharsets.UTF_8);
    }
}
