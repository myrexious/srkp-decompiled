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
import org.apache.commons.imaging.formats.tiff.TiffElement;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.TiffRasterData;
import org.apache.commons.imaging.formats.tiff.TiffRasterDataFloat;
import org.apache.commons.imaging.formats.tiff.TiffRasterDataInt;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterRgb;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public final class DataReaderTiled extends ImageDataReader {
    private final int bitsPerPixel;
    private final ByteOrder byteOrder;
    private final int compression;
    private final TiffImageData.Tiles imageData;
    private final int tileLength;
    private final int tileWidth;

    public DataReaderTiled(TiffDirectory tiffDirectory, PhotometricInterpreter photometricInterpreter, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, int i7, int i8, int i9, TiffPlanarConfiguration tiffPlanarConfiguration, ByteOrder byteOrder, TiffImageData.Tiles tiles) {
        super(tiffDirectory, photometricInterpreter, iArr, i4, i5, i6, i7, i8, tiffPlanarConfiguration);
        this.tileWidth = i;
        this.tileLength = i2;
        this.bitsPerPixel = i3;
        this.compression = i9;
        this.imageData = tiles;
        this.byteOrder = byteOrder;
    }

    private void interpretTile(ImageBuilder imageBuilder, byte[] bArr, int i, int i2, int i3, int i4) throws ImageReadException, IOException {
        int i5 = i3;
        int i6 = i4;
        if (this.sampleFormat == 3) {
            int i7 = i2 + this.tileLength;
            int i8 = i7 > i6 ? i6 : i7;
            int i9 = this.tileWidth;
            int i10 = i + i9;
            int i11 = i10 > i5 ? i5 : i10;
            int[] iArr = new int[4];
            int[] unpackFloatingPointSamples = unpackFloatingPointSamples(i11 - i, i8 - i2, i9, bArr, this.bitsPerPixel, this.byteOrder);
            for (int i12 = i2; i12 < i8; i12++) {
                int i13 = (i12 - i2) * this.tileWidth;
                for (int i14 = i; i14 < i11; i14++) {
                    iArr[0] = unpackFloatingPointSamples[((i14 - i) + i13) * this.samplesPerPixel];
                    this.photometricInterpreter.interpretPixel(imageBuilder, iArr, i14, i12);
                }
            }
            return;
        }
        boolean isHomogenous = isHomogenous(8);
        int i15 = this.bitsPerPixel;
        if ((i15 == 24 || i15 == 32) && isHomogenous && (this.photometricInterpreter instanceof PhotometricInterpreterRgb)) {
            int i16 = i2 + this.tileLength;
            if (i16 <= i6) {
                i6 = i16;
            }
            int i17 = i + this.tileWidth;
            if (i17 <= i5) {
                i5 = i17;
            }
            if (this.predictor == 2) {
                applyPredictorToBlock(this.tileWidth, i6 - i2, this.samplesPerPixel, bArr);
            }
            int i18 = this.bitsPerPixel;
            if (i18 == 24) {
                for (int i19 = i2; i19 < i6; i19++) {
                    int i20 = (i19 - i2) * this.tileWidth * 3;
                    int i21 = i;
                    while (i21 < i5) {
                        imageBuilder.setRGB(i21, i19, (bArr[i20] << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i20 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i20 + 2] & UByte.MAX_VALUE));
                        i21++;
                        i20 += 3;
                    }
                }
                return;
            } else if (i18 == 32) {
                for (int i22 = i2; i22 < i6; i22++) {
                    int i23 = (i22 - i2) * this.tileWidth * 4;
                    int i24 = i;
                    while (i24 < i5) {
                        imageBuilder.setRGB(i24, i22, ((bArr[i23] & UByte.MAX_VALUE) << 16) | ((bArr[i23 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i23 + 2] & UByte.MAX_VALUE) | (bArr[i23 + 3] << BuiltinOptions.BatchToSpaceNDOptions));
                        i24++;
                        i23 += 4;
                    }
                }
                return;
            } else {
                return;
            }
        }
        BitInputStream bitInputStream = new BitInputStream(new ByteArrayInputStream(bArr), this.byteOrder);
        try {
            int i25 = this.tileWidth * this.tileLength;
            int[] iArr2 = new int[this.bitsPerSampleLength];
            resetPredictor();
            int i26 = 0;
            int i27 = 0;
            for (int i28 = 0; i28 < i25; i28++) {
                int i29 = i26 + i;
                int i30 = i27 + i2;
                getSamplesAsBytes(bitInputStream, iArr2);
                if (i29 < i5 && i30 < i6) {
                    iArr2 = applyPredictor(iArr2);
                    this.photometricInterpreter.interpretPixel(imageBuilder, iArr2, i29, i30);
                }
                i26++;
                if (i26 >= this.tileWidth) {
                    resetPredictor();
                    i27++;
                    bitInputStream.flushCache();
                    if (i27 >= this.tileLength) {
                        break;
                    }
                    i26 = 0;
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
        DataReaderTiled dataReaderTiled = this;
        Rectangle rectangle2 = rectangle == null ? new Rectangle(0, 0, dataReaderTiled.width, dataReaderTiled.height) : rectangle;
        int i = (((dataReaderTiled.tileWidth * dataReaderTiled.bitsPerPixel) + 7) / 8) * dataReaderTiled.tileLength;
        int i2 = rectangle2.x / dataReaderTiled.tileWidth;
        int i3 = ((rectangle2.x + rectangle2.width) - 1) / dataReaderTiled.tileWidth;
        int i4 = rectangle2.y / dataReaderTiled.tileLength;
        int i5 = dataReaderTiled.tileLength;
        int i6 = ((rectangle2.y + rectangle2.height) - 1) / i5;
        int i7 = ((i3 - i2) + 1) * dataReaderTiled.tileWidth;
        int i8 = ((i6 - i4) + 1) * i5;
        int i9 = dataReaderTiled.width;
        int i10 = dataReaderTiled.tileWidth;
        int i11 = ((i9 + i10) - 1) / i10;
        int i12 = i2 * i10;
        int i13 = i4 * dataReaderTiled.tileLength;
        ImageBuilder imageBuilder = new ImageBuilder(i7, i8, z, z2);
        int i14 = i4;
        while (i14 <= i6) {
            int i15 = i2;
            while (i15 <= i3) {
                TiffElement.DataElement[] dataElementArr = dataReaderTiled.imageData.tiles;
                ImageBuilder imageBuilder2 = imageBuilder;
                int i16 = i13;
                int i17 = i15;
                int i18 = i14;
                interpretTile(imageBuilder2, decompress(dataElementArr[(i14 * i11) + i15].getData(), dataReaderTiled.compression, i, dataReaderTiled.tileWidth, dataReaderTiled.tileLength), (dataReaderTiled.tileWidth * i17) - i12, (dataReaderTiled.tileLength * i18) - i16, dataReaderTiled.width, dataReaderTiled.height);
                i15 = i17 + 1;
                imageBuilder = imageBuilder2;
                i12 = i12;
                i13 = i16;
                i14 = i18;
                dataReaderTiled = this;
                i = i;
            }
            i14++;
            dataReaderTiled = this;
            i = i;
        }
        ImageBuilder imageBuilder3 = imageBuilder;
        int i19 = i13;
        int i20 = i12;
        return (rectangle2.x == i20 && rectangle2.y == i19 && rectangle2.width == i7 && rectangle2.height == i8) ? imageBuilder3 : imageBuilder3.getSubset(rectangle2.x - i20, rectangle2.y - i19, rectangle2.width, rectangle2.height);
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
        int i5;
        int i6 = (((this.tileWidth * this.bitsPerPixel) + 7) / 8) * this.tileLength;
        if (rectangle != null) {
            int i7 = rectangle.x;
            int i8 = rectangle.y;
            i = rectangle.width;
            i2 = rectangle.height;
            i3 = i7;
            i4 = i8;
        } else {
            i = this.width;
            i2 = this.height;
            i3 = 0;
            i4 = 0;
        }
        int i9 = i;
        float[] fArr = new float[i9 * i2 * this.samplesPerPixel];
        int i10 = this.tileWidth;
        int i11 = i3 / i10;
        int i12 = ((i3 + i9) - 1) / i10;
        int i13 = this.tileLength;
        int i14 = i4 / i13;
        int i15 = ((i4 + i2) - 1) / i13;
        int i16 = this.width;
        int i17 = ((i16 + i5) - 1) / this.tileWidth;
        int i18 = i14;
        while (i18 <= i15) {
            int i19 = i11;
            while (i19 <= i12) {
                byte[] decompress = decompress(this.imageData.tiles[(i18 * i17) + i19].getData(), this.compression, i6, this.tileWidth, this.tileLength);
                int i20 = this.tileWidth;
                int i21 = this.tileLength;
                float[] fArr2 = fArr;
                transferBlockToRaster(i19 * i20, i18 * i21, this.tileWidth, this.tileLength, unpackFloatingPointSamples(i20, i21, i20, decompress, this.bitsPerPixel, this.byteOrder), i3, i4, i9, i2, this.samplesPerPixel, fArr2);
                i19++;
                fArr = fArr2;
                i9 = i9;
                i6 = i6;
                i18 = i18;
                i15 = i15;
                i12 = i12;
            }
            i18++;
            i6 = i6;
        }
        return new TiffRasterDataFloat(i9, i2, this.samplesPerPixel, fArr);
    }

    private TiffRasterData readRasterDataInt(Rectangle rectangle) throws ImageReadException, IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        DataReaderTiled dataReaderTiled = this;
        int i6 = (((dataReaderTiled.tileWidth * dataReaderTiled.bitsPerPixel) + 7) / 8) * dataReaderTiled.tileLength;
        if (rectangle != null) {
            int i7 = rectangle.x;
            int i8 = rectangle.y;
            i = rectangle.width;
            i2 = rectangle.height;
            i3 = i7;
            i4 = i8;
        } else {
            i = dataReaderTiled.width;
            i2 = dataReaderTiled.height;
            i3 = 0;
            i4 = 0;
        }
        int i9 = i;
        int[] iArr = new int[i9 * i2];
        int i10 = dataReaderTiled.tileWidth;
        int i11 = i3 / i10;
        int i12 = ((i3 + i9) - 1) / i10;
        int i13 = dataReaderTiled.tileLength;
        int i14 = i4 / i13;
        int i15 = ((i4 + i2) - 1) / i13;
        int i16 = dataReaderTiled.width;
        int i17 = ((i16 + i5) - 1) / dataReaderTiled.tileWidth;
        int i18 = i14;
        while (i18 <= i15) {
            int i19 = i11;
            while (i19 <= i12) {
                int i20 = i19;
                byte[] decompress = decompress(dataReaderTiled.imageData.tiles[(i18 * i17) + i19].getData(), dataReaderTiled.compression, i6, dataReaderTiled.tileWidth, dataReaderTiled.tileLength);
                int i21 = dataReaderTiled.tileWidth;
                int i22 = dataReaderTiled.tileLength;
                int[] iArr2 = iArr;
                transferBlockToRaster(i20 * i21, i18 * i22, dataReaderTiled.tileWidth, dataReaderTiled.tileLength, unpackIntSamples(i21, i22, i21, decompress, dataReaderTiled.predictor, dataReaderTiled.bitsPerPixel, dataReaderTiled.byteOrder), i3, i4, i9, i2, iArr2);
                i19 = i20 + 1;
                iArr = iArr2;
                i9 = i9;
                i12 = i12;
                i15 = i15;
                i18 = i18;
                dataReaderTiled = this;
            }
            i18++;
            dataReaderTiled = this;
        }
        return new TiffRasterDataInt(i9, i2, iArr);
    }
}
