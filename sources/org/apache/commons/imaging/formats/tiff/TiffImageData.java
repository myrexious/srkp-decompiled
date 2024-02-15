package org.apache.commons.imaging.formats.tiff;

import java.io.IOException;
import java.nio.ByteOrder;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImagingConstants;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.tiff.TiffElement;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.datareaders.DataReaderStrips;
import org.apache.commons.imaging.formats.tiff.datareaders.DataReaderTiled;
import org.apache.commons.imaging.formats.tiff.datareaders.ImageDataReader;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;

/* loaded from: classes2.dex */
public abstract class TiffImageData {
    public abstract ImageDataReader getDataReader(TiffDirectory tiffDirectory, PhotometricInterpreter photometricInterpreter, int i, int[] iArr, int i2, int i3, int i4, int i5, int i6, TiffPlanarConfiguration tiffPlanarConfiguration, ByteOrder byteOrder) throws IOException, ImageReadException;

    public abstract TiffElement.DataElement[] getImageData();

    public abstract boolean stripsNotTiles();

    /* loaded from: classes2.dex */
    public static class Tiles extends TiffImageData {
        private final int tileLength;
        private final int tileWidth;
        public final TiffElement.DataElement[] tiles;

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData
        public boolean stripsNotTiles() {
            return false;
        }

        public Tiles(TiffElement.DataElement[] dataElementArr, int i, int i2) {
            this.tiles = dataElementArr;
            this.tileWidth = i;
            this.tileLength = i2;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData
        public TiffElement.DataElement[] getImageData() {
            return this.tiles;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData
        public ImageDataReader getDataReader(TiffDirectory tiffDirectory, PhotometricInterpreter photometricInterpreter, int i, int[] iArr, int i2, int i3, int i4, int i5, int i6, TiffPlanarConfiguration tiffPlanarConfiguration, ByteOrder byteOrder) throws IOException, ImageReadException {
            return new DataReaderTiled(tiffDirectory, photometricInterpreter, this.tileWidth, this.tileLength, i, iArr, i2, i3, TiffImageData.extractSampleFormat(tiffDirectory), i4, i5, i6, tiffPlanarConfiguration, byteOrder, this);
        }

        public int getTileWidth() {
            return this.tileWidth;
        }

        public int getTileHeight() {
            return this.tileLength;
        }
    }

    /* loaded from: classes2.dex */
    public static class Strips extends TiffImageData {
        public final int rowsPerStrip;
        private final TiffElement.DataElement[] strips;

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData
        public boolean stripsNotTiles() {
            return true;
        }

        public Strips(TiffElement.DataElement[] dataElementArr, int i) {
            this.strips = dataElementArr;
            this.rowsPerStrip = i;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData
        public TiffElement.DataElement[] getImageData() {
            return this.strips;
        }

        public TiffElement.DataElement getImageData(int i) {
            return this.strips[i];
        }

        public int getImageDataLength() {
            return this.strips.length;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData
        public ImageDataReader getDataReader(TiffDirectory tiffDirectory, PhotometricInterpreter photometricInterpreter, int i, int[] iArr, int i2, int i3, int i4, int i5, int i6, TiffPlanarConfiguration tiffPlanarConfiguration, ByteOrder byteOrder) throws IOException, ImageReadException {
            return new DataReaderStrips(tiffDirectory, photometricInterpreter, i, iArr, i2, i3, TiffImageData.extractSampleFormat(tiffDirectory), i4, i5, i6, tiffPlanarConfiguration, byteOrder, this.rowsPerStrip, this);
        }
    }

    /* loaded from: classes2.dex */
    public static class Data extends TiffElement.DataElement {
        public Data(long j, int i, byte[] bArr) {
            super(j, i, bArr);
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffElement
        public String getElementDescription() {
            return "Tiff image data: " + getDataLength() + " bytes";
        }
    }

    /* loaded from: classes2.dex */
    public static class ByteSourceData extends Data {
        final ByteSourceFile byteSourceFile;

        public ByteSourceData(long j, int i, ByteSourceFile byteSourceFile) {
            super(j, i, ImagingConstants.EMPTY_BYTE_ARRAY);
            this.byteSourceFile = byteSourceFile;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffImageData.Data, org.apache.commons.imaging.formats.tiff.TiffElement
        public String getElementDescription() {
            return "Tiff image data: " + getDataLength() + " bytes";
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffElement.DataElement
        public byte[] getData() {
            try {
                return this.byteSourceFile.getBlock(this.offset, this.length);
            } catch (IOException unused) {
                return ImagingConstants.EMPTY_BYTE_ARRAY;
            }
        }
    }

    public static int extractSampleFormat(TiffDirectory tiffDirectory) throws ImageReadException {
        short[] fieldValue = tiffDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false);
        if (fieldValue == null || fieldValue.length <= 0) {
            return 0;
        }
        return fieldValue[0];
    }
}
