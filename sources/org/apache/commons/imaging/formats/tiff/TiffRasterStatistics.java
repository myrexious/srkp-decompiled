package org.apache.commons.imaging.formats.tiff;

/* loaded from: classes2.dex */
public class TiffRasterStatistics {
    private final float excludedValue;
    private final float maxValue;
    private final float meanValue;
    private final float minValue;
    private final int nNull;
    private final int nSample;

    public TiffRasterStatistics(TiffRasterData tiffRasterData, float f) {
        this.excludedValue = f;
        float[] data = tiffRasterData.getData();
        int length = data.length;
        float f2 = Float.POSITIVE_INFINITY;
        float f3 = Float.NEGATIVE_INFINITY;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            float f4 = data[i3];
            if (Float.isNaN(f4)) {
                i2++;
            } else if (f4 != f) {
                i++;
                d += f4;
                f2 = f4 < f2 ? f4 : f2;
                if (f4 > f3) {
                    f3 = f4;
                }
            }
        }
        this.minValue = f2;
        this.maxValue = f3;
        this.nSample = i;
        this.nNull = i2;
        if (i == 0) {
            this.meanValue = 0.0f;
        } else {
            this.meanValue = (float) (d / i);
        }
    }

    public int getCountOfSamples() {
        return this.nSample;
    }

    public int getCountOfNulls() {
        return this.nNull;
    }

    public float getMinValue() {
        return this.minValue;
    }

    public float getMaxValue() {
        return this.maxValue;
    }

    public float getMeanValue() {
        return this.meanValue;
    }

    public boolean isAnExcludedValueSet() {
        return !Float.isNaN(this.excludedValue);
    }

    public float getExcludedValue() {
        return this.excludedValue;
    }
}
