package org.apache.commons.imaging.formats.png;

/* loaded from: classes2.dex */
public class PhysicalScale {
    private static final int METER_UNITS = 1;
    private static final int RADIAN_UNITS = 2;
    public static final PhysicalScale UNDEFINED = createFromMeters(-1.0d, -1.0d);
    private final double horizontalUnitsPerPixel;
    private final int units;
    private final double verticalUnitsPerPixel;

    private PhysicalScale(int i, double d, double d2) {
        this.units = i;
        this.horizontalUnitsPerPixel = d;
        this.verticalUnitsPerPixel = d2;
    }

    public static PhysicalScale createFromMeters(double d, double d2) {
        return new PhysicalScale(1, d, d2);
    }

    public static PhysicalScale createFromRadians(double d, double d2) {
        return new PhysicalScale(2, d, d2);
    }

    public boolean isInMeters() {
        return 1 == this.units;
    }

    public boolean isInRadians() {
        return 2 == this.units;
    }

    public double getHorizontalUnitsPerPixel() {
        return this.horizontalUnitsPerPixel;
    }

    public double getVerticalUnitsPerPixel() {
        return this.verticalUnitsPerPixel;
    }
}
