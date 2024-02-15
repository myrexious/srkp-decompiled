package org.apache.commons.imaging.formats.tiff;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/* loaded from: classes2.dex */
public class TiffRasterDataFloat extends TiffRasterData {
    private final float[] data;

    public TiffRasterDataFloat(int i, int i2) {
        super(i, i2, 1);
        this.data = new float[this.nCells];
    }

    public TiffRasterDataFloat(int i, int i2, int i3) {
        super(i, i2, i3);
        this.data = new float[this.nCells];
    }

    public TiffRasterDataFloat(int i, int i2, float[] fArr) {
        super(i, i2, 1);
        if (fArr == null || fArr.length < this.nCells) {
            throw new IllegalArgumentException("Specified data does not contain sufficient elements");
        }
        this.data = fArr;
    }

    public TiffRasterDataFloat(int i, int i2, int i3, float[] fArr) {
        super(i, i2, i3);
        if (fArr == null || fArr.length < this.nCells) {
            throw new IllegalArgumentException("Specified data does not contain sufficient elements");
        }
        this.data = fArr;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public TiffRasterDataType getDataType() {
        return TiffRasterDataType.FLOAT;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public void setValue(int i, int i2, float f) {
        this.data[checkCoordinatesAndComputeIndex(i, i2, 0)] = f;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public void setValue(int i, int i2, int i3, float f) {
        this.data[checkCoordinatesAndComputeIndex(i, i2, i3)] = f;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public float getValue(int i, int i2) {
        return this.data[checkCoordinatesAndComputeIndex(i, i2, 0)];
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public float getValue(int i, int i2, int i3) {
        return this.data[checkCoordinatesAndComputeIndex(i, i2, i3)];
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public void setIntValue(int i, int i2, int i3) {
        this.data[checkCoordinatesAndComputeIndex(i, i2, 0)] = i3;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public void setIntValue(int i, int i2, int i3, int i4) {
        this.data[checkCoordinatesAndComputeIndex(i, i2, 0)] = i4;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public int getIntValue(int i, int i2) {
        return (int) this.data[checkCoordinatesAndComputeIndex(i, i2, 0)];
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public int getIntValue(int i, int i2, int i3) {
        return (int) this.data[checkCoordinatesAndComputeIndex(i, i2, 0)];
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public TiffRasterStatistics getSimpleStatistics() {
        return new TiffRasterStatistics(this, Float.NaN);
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public TiffRasterStatistics getSimpleStatistics(float f) {
        return new TiffRasterStatistics(this, f);
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public float[] getData() {
        return this.data;
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffRasterData
    public int[] getIntData() {
        return IntStream.range(0, this.nCells).map(new IntUnaryOperator() { // from class: org.apache.commons.imaging.formats.tiff.TiffRasterDataFloat$$ExternalSyntheticLambda0
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return TiffRasterDataFloat.this.m1844x51d39231(i);
            }
        }).toArray();
    }

    /* renamed from: lambda$getIntData$0$org-apache-commons-imaging-formats-tiff-TiffRasterDataFloat */
    public /* synthetic */ int m1844x51d39231(int i) {
        return (int) this.data[i];
    }
}
