package org.apache.commons.imaging.formats.png.transparencyfilters;

import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFileParser;

/* loaded from: classes2.dex */
public abstract class TransparencyFilter extends BinaryFileParser {
    private final byte[] bytes;

    public abstract int filter(int i, int i2) throws ImageReadException, IOException;

    public TransparencyFilter(byte[] bArr) {
        this.bytes = (byte[]) bArr.clone();
    }

    public byte getByte(int i) {
        return this.bytes[i];
    }

    public int getLength() {
        return this.bytes.length;
    }
}
