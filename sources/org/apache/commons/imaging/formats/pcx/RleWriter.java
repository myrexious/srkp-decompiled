package org.apache.commons.imaging.formats.pcx;

import java.io.IOException;
import kotlin.UByte;
import org.apache.commons.imaging.common.BinaryOutputStream;

/* loaded from: classes2.dex */
class RleWriter {
    private final boolean isCompressed;
    private int previousByte = -1;
    private int repeatCount = 0;

    public RleWriter(boolean z) {
        this.isCompressed = z;
    }

    public void write(BinaryOutputStream binaryOutputStream, byte[] bArr) throws IOException {
        int i;
        if (this.isCompressed) {
            for (byte b : bArr) {
                int i2 = b & UByte.MAX_VALUE;
                int i3 = this.previousByte;
                if (i2 == i3 && (i = this.repeatCount) < 63) {
                    this.repeatCount = i + 1;
                } else {
                    int i4 = this.repeatCount;
                    if (i4 > 0) {
                        if (i4 == 1 && (i3 & 192) != 192) {
                            binaryOutputStream.write(i3);
                        } else {
                            binaryOutputStream.write(i4 | 192);
                            binaryOutputStream.write(this.previousByte);
                        }
                    }
                    this.previousByte = i2;
                    this.repeatCount = 1;
                }
            }
            return;
        }
        binaryOutputStream.write(bArr);
    }

    public void flush(BinaryOutputStream binaryOutputStream) throws IOException {
        int i = this.repeatCount;
        if (i > 0) {
            if (i == 1) {
                int i2 = this.previousByte;
                if ((i2 & 192) != 192) {
                    binaryOutputStream.write(i2);
                    return;
                }
            }
            binaryOutputStream.write(i | 192);
            binaryOutputStream.write(this.previousByte);
        }
    }
}
