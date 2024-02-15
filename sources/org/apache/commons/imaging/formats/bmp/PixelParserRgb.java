package org.apache.commons.imaging.formats.bmp;

import androidx.core.view.ViewCompat;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.IOException;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PixelParserRgb extends PixelParserSimple {
    private int byteCount;
    private int cachedBitCount;
    private int cachedByte;

    public PixelParserRgb(BmpHeaderInfo bmpHeaderInfo, byte[] bArr, byte[] bArr2) {
        super(bmpHeaderInfo, bArr, bArr2);
    }

    @Override // org.apache.commons.imaging.formats.bmp.PixelParserSimple
    public int getNextRGB() throws ImageReadException, IOException {
        if (this.bhi.bitsPerPixel == 1 || this.bhi.bitsPerPixel == 4) {
            if (this.cachedBitCount < this.bhi.bitsPerPixel) {
                int i = this.cachedBitCount;
                if (i != 0) {
                    throw new ImageReadException("Unexpected leftover bits: " + this.cachedBitCount + RemoteSettings.FORWARD_SLASH_STRING + this.bhi.bitsPerPixel);
                }
                this.cachedBitCount = i + 8;
                byte[] bArr = this.imageData;
                int i2 = this.byteCount;
                this.cachedByte = bArr[i2] & UByte.MAX_VALUE;
                this.byteCount = i2 + 1;
            }
            int i3 = ((1 << this.bhi.bitsPerPixel) - 1) & (this.cachedByte >> (8 - this.bhi.bitsPerPixel));
            this.cachedByte = (this.cachedByte << this.bhi.bitsPerPixel) & 255;
            this.cachedBitCount -= this.bhi.bitsPerPixel;
            return getColorTableRGB(i3);
        } else if (this.bhi.bitsPerPixel == 8) {
            int colorTableRGB = getColorTableRGB(this.imageData[this.byteCount + 0] & UByte.MAX_VALUE);
            this.byteCount++;
            return colorTableRGB;
        } else if (this.bhi.bitsPerPixel == 16) {
            int read2Bytes = BinaryFunctions.read2Bytes("Pixel", this.is, "BMP Image Data", ByteOrder.LITTLE_ENDIAN);
            int i4 = ((((read2Bytes >> 10) & 31) << 3) << 16) | ViewCompat.MEASURED_STATE_MASK | ((((read2Bytes >> 5) & 31) << 3) << 8) | ((((read2Bytes >> 0) & 31) << 3) << 0);
            this.byteCount += 2;
            return i4;
        } else if (this.bhi.bitsPerPixel == 24) {
            int i5 = this.imageData[this.byteCount + 0] & UByte.MAX_VALUE;
            int i6 = this.imageData[this.byteCount + 1] & UByte.MAX_VALUE;
            byte[] bArr2 = this.imageData;
            int i7 = this.byteCount;
            int i8 = (i5 << 0) | (i6 << 8) | ((bArr2[i7 + 2] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK;
            this.byteCount = i7 + 3;
            return i8;
        } else if (this.bhi.bitsPerPixel == 32) {
            int i9 = this.imageData[this.byteCount + 0] & UByte.MAX_VALUE;
            int i10 = this.imageData[this.byteCount + 1] & UByte.MAX_VALUE;
            byte[] bArr3 = this.imageData;
            int i11 = this.byteCount;
            int i12 = (i9 << 0) | (i10 << 8) | (-16777216) | ((bArr3[i11 + 2] & UByte.MAX_VALUE) << 16);
            this.byteCount = i11 + 4;
            return i12;
        } else {
            throw new ImageReadException("Unknown BitsPerPixel: " + this.bhi.bitsPerPixel);
        }
    }

    @Override // org.apache.commons.imaging.formats.bmp.PixelParserSimple
    public void newline() throws ImageReadException, IOException {
        this.cachedBitCount = 0;
        while (this.byteCount % 4 != 0) {
            BinaryFunctions.readByte("Pixel", this.is, "BMP Image Data");
            this.byteCount++;
        }
    }
}
