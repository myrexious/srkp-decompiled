package org.apache.commons.imaging.formats.tiff;

/* loaded from: classes2.dex */
public abstract class TiffRasterData {
    protected final int height;
    protected final int nCells;
    protected final int planarOffset;
    protected final int samplesPerPixel;
    protected final int width;

    public abstract float[] getData();

    public abstract TiffRasterDataType getDataType();

    public abstract int[] getIntData();

    public abstract int getIntValue(int i, int i2);

    public abstract int getIntValue(int i, int i2, int i3);

    public abstract TiffRasterStatistics getSimpleStatistics();

    public abstract TiffRasterStatistics getSimpleStatistics(float f);

    public abstract float getValue(int i, int i2);

    public abstract float getValue(int i, int i2, int i3);

    public abstract void setIntValue(int i, int i2, int i3);

    public abstract void setIntValue(int i, int i2, int i3, int i4);

    public abstract void setValue(int i, int i2, float f);

    public abstract void setValue(int i, int i2, int i3, float f);

    public TiffRasterData(int i, int i2, int i3) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Raster dimensions less than or equal to zero are not supported");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("Raster samples-per-pixel specification must be at least 1");
        }
        this.width = i;
        this.height = i2;
        this.samplesPerPixel = i3;
        int i4 = i * i2;
        this.nCells = i3 * i4;
        this.planarOffset = i4;
    }

    public final int checkCoordinatesAndComputeIndex(int i, int i2, int i3) {
        int i4;
        if (i < 0 || i >= (i4 = this.width) || i2 < 0 || i2 >= this.height) {
            throw new IllegalArgumentException("Coordinates out of range (" + i + ", " + i2 + ")");
        }
        if (i3 < 0 || i3 >= this.samplesPerPixel) {
            throw new IllegalArgumentException("Sample index out of range, value " + i3 + " where valid range is (0," + (this.samplesPerPixel - 1) + ")");
        }
        return (i2 * i4) + i + (i3 * this.planarOffset);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getSamplesPerPixel() {
        return this.samplesPerPixel;
    }
}
