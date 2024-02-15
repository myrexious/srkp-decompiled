package org.apache.commons.imaging.formats.pcx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.tensorflow.lite.schema.BuiltinOptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class RleReader {
    private int count;
    private final boolean isCompressed;
    private byte sample;

    public RleReader(boolean z) {
        this.isCompressed = z;
    }

    public void read(InputStream inputStream, byte[] bArr) throws IOException, ImageReadException {
        int i = 0;
        if (this.isCompressed) {
            int min = Math.min(this.count, bArr.length);
            Arrays.fill(bArr, 0, min, this.sample);
            this.count -= min;
            while (min < bArr.length) {
                byte readByte = BinaryFunctions.readByte("RleByte", inputStream, "Error reading image data");
                if ((readByte & 192) == 192) {
                    this.count = readByte & BuiltinOptions.LogicalNotOptions;
                    this.sample = BinaryFunctions.readByte("RleValue", inputStream, "Error reading image data");
                } else {
                    this.count = 1;
                    this.sample = readByte;
                }
                int min2 = Math.min(this.count, bArr.length - min);
                int i2 = min + min2;
                Arrays.fill(bArr, min, i2, this.sample);
                this.count -= min2;
                min = i2;
            }
            return;
        }
        while (i < bArr.length) {
            int read = inputStream.read(bArr, i, bArr.length - i);
            if (read < 0) {
                throw new ImageReadException("Premature end of file reading image data");
            }
            i += read;
        }
    }
}
