package org.apache.commons.imaging.formats.tiff.datareaders;

import androidx.core.view.ViewCompat;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.TiffRasterData;
import org.apache.commons.imaging.formats.tiff.TiffRasterDataFloat;
import org.apache.commons.imaging.formats.tiff.TiffRasterDataInt;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterRgb;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public final class DataReaderStrips extends ImageDataReader {
    private final int bitsPerPixel;
    private final ByteOrder byteOrder;
    private final int compression;
    private final TiffImageData.Strips imageData;
    private final TiffPlanarConfiguration planarConfiguration;
    private final int rowsPerStrip;
    private int x;
    private int y;

    public DataReaderStrips(TiffDirectory tiffDirectory, PhotometricInterpreter photometricInterpreter, int i, int[] iArr, int i2, int i3, int i4, int i5, int i6, int i7, TiffPlanarConfiguration tiffPlanarConfiguration, ByteOrder byteOrder, int i8, TiffImageData.Strips strips) {
        super(tiffDirectory, photometricInterpreter, iArr, i2, i3, i4, i5, i6, tiffPlanarConfiguration);
        this.bitsPerPixel = i;
        this.compression = i7;
        this.rowsPerStrip = i8;
        this.planarConfiguration = tiffPlanarConfiguration;
        this.imageData = strips;
        this.byteOrder = byteOrder;
    }

    private void interpretStrip(ImageBuilder imageBuilder, byte[] bArr, int i, int i2) throws ImageReadException, IOException {
        if (this.y >= i2) {
            return;
        }
        if (this.sampleFormat == 3) {
            int i3 = i / this.width;
            int i4 = this.y;
            if (i4 + i3 > i2) {
                i3 = i2 - i4;
            }
            int i5 = i4 + i3;
            this.x = 0;
            this.y = i3 + i4;
            int[] iArr = new int[1];
            int[] unpackFloatingPointSamples = unpackFloatingPointSamples(this.width, i5 - i4, this.width, bArr, this.bitsPerPixel, this.byteOrder);
            int i6 = 0;
            while (i4 < i5) {
                for (int i7 = 0; i7 < this.width; i7++) {
                    iArr[0] = unpackFloatingPointSamples[i6];
                    i6 += this.samplesPerPixel;
                    this.photometricInterpreter.interpretPixel(imageBuilder, iArr, i7, i4);
                }
                i4++;
            }
            return;
        }
        boolean isHomogenous = isHomogenous(8);
        if (this.predictor != 2 && this.bitsPerPixel == 8 && isHomogenous) {
            int i8 = i / this.width;
            int i9 = this.y;
            if (i9 + i8 > i2) {
                i8 = i2 - i9;
            }
            int i10 = i9 + i8;
            this.x = 0;
            this.y = i8 + i9;
            int[] iArr2 = new int[1];
            int i11 = 0;
            while (i9 < i10) {
                int i12 = 0;
                while (i12 < this.width) {
                    iArr2[0] = bArr[i11] & UByte.MAX_VALUE;
                    this.photometricInterpreter.interpretPixel(imageBuilder, iArr2, i12, i9);
                    i12++;
                    i11++;
                }
                i9++;
            }
            return;
        }
        int i13 = this.bitsPerPixel;
        if ((i13 == 24 || i13 == 32) && isHomogenous && (this.photometricInterpreter instanceof PhotometricInterpreterRgb)) {
            int i14 = i / this.width;
            int i15 = this.y;
            if (i15 + i14 > i2) {
                i14 = i2 - i15;
            }
            int i16 = i15 + i14;
            this.x = 0;
            this.y = i15 + i14;
            if (this.predictor == 2) {
                applyPredictorToBlock(this.width, i14, this.samplesPerPixel, bArr);
            }
            if (this.bitsPerPixel == 24) {
                int i17 = 0;
                while (i15 < i16) {
                    int i18 = 0;
                    while (i18 < this.width) {
                        imageBuilder.setRGB(i18, i15, (bArr[i17] << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i17 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i17 + 2] & UByte.MAX_VALUE));
                        i18++;
                        i17 += 3;
                    }
                    i15++;
                }
                return;
            }
            int i19 = 0;
            while (i15 < i16) {
                int i20 = 0;
                while (i20 < this.width) {
                    imageBuilder.setRGB(i20, i15, ((bArr[i19] & UByte.MAX_VALUE) << 16) | ((bArr[i19 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i19 + 2] & UByte.MAX_VALUE) | (bArr[i19 + 3] << BuiltinOptions.BatchToSpaceNDOptions));
                    i20++;
                    i19 += 4;
                }
                i15++;
            }
            return;
        }
        BitInputStream bitInputStream = new BitInputStream(new ByteArrayInputStream(bArr), this.byteOrder);
        try {
            int[] iArr3 = new int[this.bitsPerSampleLength];
            resetPredictor();
            for (int i21 = 0; i21 < i; i21++) {
                getSamplesAsBytes(bitInputStream, iArr3);
                if (this.x < this.width) {
                    iArr3 = applyPredictor(iArr3);
                    this.photometricInterpreter.interpretPixel(imageBuilder, iArr3, this.x, this.y);
                }
                int i22 = this.x + 1;
                this.x = i22;
                if (i22 >= this.width) {
                    this.x = 0;
                    resetPredictor();
                    this.y++;
                    bitInputStream.flushCache();
                    if (this.y >= i2) {
                        break;
                    }
                }
            }
            bitInputStream.close();
        } catch (Throwable th) {
            try {
                bitInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // org.apache.commons.imaging.formats.tiff.datareaders.ImageDataReader
    public ImageBuilder readImageData(Rectangle rectangle, boolean z, boolean z2) throws ImageReadException, IOException {
        int i;
        int i2;
        Rectangle rectangle2;
        int i3;
        int i4 = 0;
        Rectangle rectangle3 = rectangle == null ? new Rectangle(0, 0, this.width, this.height) : rectangle;
        int i5 = rectangle3.y / this.rowsPerStrip;
        int i6 = this.rowsPerStrip;
        int i7 = ((rectangle3.y + rectangle3.height) - 1) / i6;
        int i8 = ((i7 - i5) + 1) * i6;
        int i9 = i5 * i6;
        int i10 = (rectangle3.y - i9) + rectangle3.height;
        ImageBuilder imageBuilder = new ImageBuilder(this.width, i8, z, z2);
        TiffPlanarConfiguration tiffPlanarConfiguration = this.planarConfiguration;
        TiffPlanarConfiguration tiffPlanarConfiguration2 = TiffPlanarConfiguration.PLANAR;
        long j = BodyPartID.bodyIdMax;
        if (tiffPlanarConfiguration != tiffPlanarConfiguration2) {
            int i11 = i5;
            while (i11 <= i7) {
                long j2 = this.rowsPerStrip & j;
                long min = Math.min(this.height - (i11 * j2), j2);
                interpretStrip(imageBuilder, decompress(this.imageData.getImageData(i11).getData(), this.compression, (int) ((((this.bitsPerPixel * this.width) + 7) / 8) * min), this.width, (int) min), (int) (this.width * min), i10);
                i11++;
                i8 = i8;
                i9 = i9;
                j = BodyPartID.bodyIdMax;
            }
            i = i9;
            rectangle2 = rectangle3;
            i2 = i8;
        } else {
            int i12 = i8;
            i = i9;
            int i13 = 3;
            int imageDataLength = this.imageData.getImageDataLength() / 3;
            int i14 = i5;
            while (i14 <= i7) {
                long j3 = this.rowsPerStrip & BodyPartID.bodyIdMax;
                long min2 = Math.min(this.height - (i14 * j3), j3);
                long j4 = this.width * min2;
                int i15 = (int) ((((this.bitsPerPixel * this.width) + 7) / 8) * min2);
                byte[] bArr = new byte[i15];
                int i16 = i4;
                while (i16 < i13) {
                    int i17 = i7;
                    int i18 = imageDataLength;
                    int i19 = i16;
                    int i20 = i12;
                    byte[] bArr2 = bArr;
                    Rectangle rectangle4 = rectangle3;
                    long j5 = j4;
                    long j6 = min2;
                    int i21 = i19;
                    for (byte b : decompress(this.imageData.getImageData((i16 * imageDataLength) + i14).getData(), this.compression, i15, this.width, (int) min2)) {
                        bArr2[i21] = b;
                        i21 += 3;
                    }
                    i16 = i19 + 1;
                    imageDataLength = i18;
                    i13 = 3;
                    j4 = j5;
                    bArr = bArr2;
                    i7 = i17;
                    rectangle3 = rectangle4;
                    min2 = j6;
                    i12 = i20;
                }
                interpretStrip(imageBuilder, bArr, (int) j4, this.height);
                i14++;
                i12 = i12;
                rectangle3 = rectangle3;
                i4 = 0;
            }
            i2 = i12;
            rectangle2 = rectangle3;
        }
        if (rectangle2.x == 0) {
            i3 = i;
            if (rectangle2.y == i3 && rectangle2.width == this.width && rectangle2.height == i2) {
                return imageBuilder;
            }
        } else {
            i3 = i;
        }
        return imageBuilder.getSubset(rectangle2.x, rectangle2.y - i3, rectangle2.width, rectangle2.height);
    }

    @Override // org.apache.commons.imaging.formats.tiff.datareaders.ImageDataReader
    public TiffRasterData readRasterData(Rectangle rectangle) throws ImageReadException, IOException {
        int i = this.sampleFormat;
        if (i != 2) {
            if (i == 3) {
                return readRasterDataFloat(rectangle);
            }
            throw new ImageReadException("Unsupported sample format, value=" + this.sampleFormat);
        }
        return readRasterDataInt(rectangle);
    }

    private TiffRasterData readRasterDataFloat(Rectangle rectangle) throws ImageReadException, IOException {
        int i;
        int i2;
        int i3;
        int i4;
        if (rectangle != null) {
            int i5 = rectangle.x;
            int i6 = rectangle.y;
            i = rectangle.width;
            i2 = rectangle.height;
            i3 = i5;
            i4 = i6;
        } else {
            i = this.width;
            i2 = this.height;
            i3 = 0;
            i4 = 0;
        }
        int i7 = i;
        float[] fArr = new float[i7 * i2 * this.samplesPerPixel];
        int i8 = this.rowsPerStrip;
        int i9 = i4 / i8;
        for (int i10 = ((i4 + i2) - 1) / i8; i9 <= i10; i10 = i10) {
            int i11 = i9 * this.rowsPerStrip;
            int min = Math.min(this.height - i11, this.rowsPerStrip);
            float[] fArr2 = fArr;
            transferBlockToRaster(0, i11, this.width, min, unpackFloatingPointSamples(this.width, min, this.width, decompress(this.imageData.getImageData(i9).getData(), this.compression, min * (((this.bitsPerPixel * this.width) + 7) / 8), this.width, min), this.bitsPerPixel, this.byteOrder), i3, i4, i7, i2, this.samplesPerPixel, fArr2);
            i9++;
            fArr = fArr2;
            i7 = i7;
            i3 = i3;
        }
        return new TiffRasterDataFloat(i7, i2, this.samplesPerPixel, fArr);
    }

    private TiffRasterData readRasterDataInt(Rectangle rectangle) throws ImageReadException, IOException {
        int i;
        int i2;
        int i3;
        int i4;
        if (rectangle != null) {
            int i5 = rectangle.x;
            int i6 = rectangle.y;
            i = rectangle.width;
            i2 = rectangle.height;
            i3 = i5;
            i4 = i6;
        } else {
            i = this.width;
            i2 = this.height;
            i3 = 0;
            i4 = 0;
        }
        int i7 = i;
        int[] iArr = new int[i7 * i2];
        int i8 = this.rowsPerStrip;
        int i9 = i4 / i8;
        for (int i10 = ((i4 + i2) - 1) / i8; i9 <= i10; i10 = i10) {
            int i11 = i9 * this.rowsPerStrip;
            int min = Math.min(this.height - i11, this.rowsPerStrip);
            transferBlockToRaster(0, i11, this.width, min, unpackIntSamples(this.width, min, this.width, decompress(this.imageData.getImageData(i9).getData(), this.compression, min * (((this.bitsPerPixel * this.width) + 7) / 8), this.width, min), this.predictor, this.bitsPerPixel, this.byteOrder), i3, i4, i7, i2, iArr);
            i9++;
        }
        return new TiffRasterDataInt(i7, i2, iArr);
    }
}
