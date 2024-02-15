package org.apache.commons.imaging.color;

import androidx.core.view.ViewCompat;

/* loaded from: classes2.dex */
public final class ColorConversions {
    private static final double REF_X = 95.047d;
    private static final double REF_Y = 100.0d;
    private static final double REF_Z = 108.883d;
    private static final double XYZ_m = 7.787037d;
    private static final double XYZ_t0 = 0.008856d;

    private static double convertHuetoRGB(double d, double d2, double d3) {
        double d4;
        if (d3 < 0.0d) {
            d3 += 1.0d;
        }
        if (d3 > 1.0d) {
            d3 -= 1.0d;
        }
        if (d3 * 6.0d < 1.0d) {
            d4 = (d2 - d) * 6.0d * d3;
        } else if (d3 * 2.0d < 1.0d) {
            return d2;
        } else {
            if (3.0d * d3 >= 2.0d) {
                return d;
            }
            d4 = (d2 - d) * (0.6666666666666666d - d3) * 6.0d;
        }
        return d + d4;
    }

    private static double cube(double d) {
        return d * d * d;
    }

    public static double degree_2_radian(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    public static double radian_2_degree(double d) {
        return (d * 180.0d) / 3.141592653589793d;
    }

    private static double square(double d) {
        return d * d;
    }

    private ColorConversions() {
    }

    public static ColorCieLab convertXYZtoCIELab(ColorXyz colorXyz) {
        return convertXYZtoCIELab(colorXyz.X, colorXyz.Y, colorXyz.Z);
    }

    public static ColorCieLab convertXYZtoCIELab(double d, double d2, double d3) {
        double d4 = d / REF_X;
        double d5 = d2 / REF_Y;
        double d6 = d3 / REF_Z;
        double pivotXYZ = pivotXYZ(d4);
        double pivotXYZ2 = pivotXYZ(d5);
        return new ColorCieLab(Math.max(0.0d, (116.0d * pivotXYZ2) - 16.0d), (pivotXYZ - pivotXYZ2) * 500.0d, (pivotXYZ2 - pivotXYZ(d6)) * 200.0d);
    }

    public static ColorXyz convertCIELabtoXYZ(ColorCieLab colorCieLab) {
        return convertCIELabtoXYZ(colorCieLab.L, colorCieLab.a, colorCieLab.b);
    }

    public static ColorXyz convertCIELabtoXYZ(double d, double d2, double d3) {
        double d4 = (d + 16.0d) / 116.0d;
        double d5 = (d2 / 500.0d) + d4;
        double d6 = d4 - (d3 / 200.0d);
        double unPivotXYZ = unPivotXYZ(d4);
        return new ColorXyz(unPivotXYZ(d5) * REF_X, unPivotXYZ * REF_Y, unPivotXYZ(d6) * REF_Z);
    }

    public static ColorHunterLab convertXYZtoHunterLab(ColorXyz colorXyz) {
        return convertXYZtoHunterLab(colorXyz.X, colorXyz.Y, colorXyz.Z);
    }

    public static ColorHunterLab convertXYZtoHunterLab(double d, double d2, double d3) {
        int i = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
        return new ColorHunterLab(Math.sqrt(d2) * 10.0d, i == 0 ? 0.0d : (((1.02d * d) - d2) / Math.sqrt(d2)) * 17.5d, i != 0 ? ((d2 - (0.847d * d3)) / Math.sqrt(d2)) * 7.0d : 0.0d);
    }

    public static ColorXyz convertHunterLabtoXYZ(ColorHunterLab colorHunterLab) {
        return convertHunterLabtoXYZ(colorHunterLab.L, colorHunterLab.a, colorHunterLab.b);
    }

    public static ColorXyz convertHunterLabtoXYZ(double d, double d2, double d3) {
        double pow = Math.pow(d / 10.0d, 2.0d);
        return new ColorXyz(((((d2 / 17.5d) * d) / 10.0d) + pow) / 1.02d, pow, (-((((d3 / 7.0d) * d) / 10.0d) - pow)) / 0.847d);
    }

    public static int convertXYZtoRGB(ColorXyz colorXyz) {
        return convertXYZtoRGB(colorXyz.X, colorXyz.Y, colorXyz.Z);
    }

    public static int convertXYZtoRGB(double d, double d2, double d3) {
        double d4 = d / REF_Y;
        double d5 = d2 / REF_Y;
        double d6 = d3 / REF_Y;
        double d7 = (3.2404542d * d4) + ((-1.5371385d) * d5) + ((-0.4985314d) * d6);
        double d8 = (d4 * 0.0556434d) + (d5 * (-0.2040259d)) + (d6 * 1.0572252d);
        return convertRGBtoRGB(pivotRGB(d7) * 255.0d, pivotRGB(((-0.969266d) * d4) + (1.8760108d * d5) + (0.041556d * d6)) * 255.0d, pivotRGB(d8) * 255.0d);
    }

    public static ColorXyz convertRGBtoXYZ(int i) {
        double unPivotRGB = unPivotRGB(((i >> 16) & 255) / 255.0d);
        double unPivotRGB2 = unPivotRGB(((i >> 8) & 255) / 255.0d);
        double unPivotRGB3 = unPivotRGB(((i >> 0) & 255) / 255.0d);
        double d = unPivotRGB * REF_Y;
        double d2 = unPivotRGB2 * REF_Y;
        double d3 = unPivotRGB3 * REF_Y;
        return new ColorXyz((0.4124564d * d) + (0.3575761d * d2) + (0.1804375d * d3), (0.2126729d * d) + (0.7151522d * d2) + (0.072175d * d3), (d * 0.0193339d) + (d2 * 0.119192d) + (d3 * 0.9503041d));
    }

    public static ColorCmy convertRGBtoCMY(int i) {
        return new ColorCmy(1.0d - (((i >> 16) & 255) / 255.0d), 1.0d - (((i >> 8) & 255) / 255.0d), 1.0d - (((i >> 0) & 255) / 255.0d));
    }

    public static int convertCMYtoRGB(ColorCmy colorCmy) {
        return convertRGBtoRGB((1.0d - colorCmy.C) * 255.0d, (1.0d - colorCmy.M) * 255.0d, (1.0d - colorCmy.Y) * 255.0d);
    }

    public static ColorCmyk convertCMYtoCMYK(ColorCmy colorCmy) {
        double d;
        double d2;
        double d3;
        double d4 = colorCmy.C;
        double d5 = colorCmy.M;
        double d6 = colorCmy.Y;
        double d7 = d4 < 1.0d ? d4 : 1.0d;
        if (d5 < d7) {
            d7 = d5;
        }
        double d8 = d6 < d7 ? d6 : d7;
        if (d8 == 1.0d) {
            d = 0.0d;
            d2 = 0.0d;
            d3 = 0.0d;
        } else {
            double d9 = 1.0d - d8;
            d = (d4 - d8) / d9;
            d2 = (d5 - d8) / d9;
            d3 = (d6 - d8) / d9;
        }
        return new ColorCmyk(d, d2, d3, d8);
    }

    public static ColorCmy convertCMYKtoCMY(ColorCmyk colorCmyk) {
        return convertCMYKtoCMY(colorCmyk.C, colorCmyk.M, colorCmyk.Y, colorCmyk.K);
    }

    public static ColorCmy convertCMYKtoCMY(double d, double d2, double d3, double d4) {
        double d5 = 1.0d - d4;
        return new ColorCmy((d * d5) + d4, (d2 * d5) + d4, (d5 * d3) + d4);
    }

    public static int convertCMYKtoRGB(int i, int i2, int i3, int i4) {
        return convertCMYtoRGB(convertCMYKtoCMY(i / 255.0d, i2 / 255.0d, i3 / 255.0d, i4 / 255.0d));
    }

    public static ColorHsl convertRGBtoHSL(int i) {
        double d;
        double d2;
        double d3;
        boolean z = false;
        double d4 = ((i >> 16) & 255) / 255.0d;
        double d5 = ((i >> 8) & 255) / 255.0d;
        double d6 = ((i >> 0) & 255) / 255.0d;
        double min = Math.min(d4, Math.min(d5, d6));
        boolean z2 = true;
        if (d4 >= d5 && d4 >= d6) {
            d = d4;
            z2 = false;
            z = true;
        } else if (d5 > d6) {
            d = d5;
        } else {
            z2 = false;
            d = d6;
        }
        double d7 = d - min;
        double d8 = d + min;
        double d9 = d8 / 2.0d;
        if (d7 == 0.0d) {
            d2 = 0.0d;
            d3 = 0.0d;
        } else {
            if (d9 >= 0.5d) {
                d8 = (2.0d - d) - min;
            }
            double d10 = d7 / d8;
            double d11 = d7 / 2.0d;
            double d12 = (((d - d4) / 6.0d) + d11) / d7;
            double d13 = (((d - d5) / 6.0d) + d11) / d7;
            double d14 = (((d - d6) / 6.0d) + d11) / d7;
            double d15 = z ? d14 - d13 : z2 ? (d12 + 0.3333333333333333d) - d14 : (d13 + 0.6666666666666666d) - d12;
            if (d15 < 0.0d) {
                d15 += 1.0d;
            }
            if (d15 > 1.0d) {
                d15 -= 1.0d;
            }
            d2 = d10;
            d3 = d15;
        }
        return new ColorHsl(d3, d2, d9);
    }

    public static int convertHSLtoRGB(ColorHsl colorHsl) {
        return convertHSLtoRGB(colorHsl.H, colorHsl.S, colorHsl.L);
    }

    public static int convertHSLtoRGB(double d, double d2, double d3) {
        double convertHuetoRGB;
        double convertHuetoRGB2;
        double d4;
        if (d2 == 0.0d) {
            d4 = d3 * 255.0d;
            convertHuetoRGB2 = d4;
            convertHuetoRGB = convertHuetoRGB2;
        } else {
            double d5 = d3 < 0.5d ? (d2 + 1.0d) * d3 : (d3 + d2) - (d2 * d3);
            double d6 = (2.0d * d3) - d5;
            double convertHuetoRGB3 = convertHuetoRGB(d6, d5, d + 0.3333333333333333d) * 255.0d;
            convertHuetoRGB = convertHuetoRGB(d6, d5, d - 0.3333333333333333d) * 255.0d;
            convertHuetoRGB2 = convertHuetoRGB(d6, d5, d) * 255.0d;
            d4 = convertHuetoRGB3;
        }
        return convertRGBtoRGB(d4, convertHuetoRGB2, convertHuetoRGB);
    }

    public static ColorHsv convertRGBtoHSV(int i) {
        double d;
        double d2;
        double d3;
        boolean z = false;
        double d4 = ((i >> 16) & 255) / 255.0d;
        double d5 = ((i >> 8) & 255) / 255.0d;
        double d6 = ((i >> 0) & 255) / 255.0d;
        double min = Math.min(d4, Math.min(d5, d6));
        boolean z2 = true;
        if (d4 >= d5 && d4 >= d6) {
            d = d4;
            z2 = false;
            z = true;
        } else if (d5 > d6) {
            d = d5;
        } else {
            z2 = false;
            d = d6;
        }
        double d7 = d - min;
        if (d7 == 0.0d) {
            d2 = 0.0d;
            d3 = 0.0d;
        } else {
            double d8 = d7 / d;
            double d9 = d7 / 2.0d;
            double d10 = (((d - d4) / 6.0d) + d9) / d7;
            double d11 = (((d - d5) / 6.0d) + d9) / d7;
            double d12 = (((d - d6) / 6.0d) + d9) / d7;
            double d13 = z ? d12 - d11 : z2 ? (d10 + 0.3333333333333333d) - d12 : (d11 + 0.6666666666666666d) - d10;
            if (d13 < 0.0d) {
                d13 += 1.0d;
            }
            if (d13 > 1.0d) {
                d13 -= 1.0d;
            }
            d2 = d8;
            d3 = d13;
        }
        return new ColorHsv(d3, d2, d);
    }

    public static int convertHSVtoRGB(ColorHsv colorHsv) {
        return convertHSVtoRGB(colorHsv.H, colorHsv.S, colorHsv.V);
    }

    public static int convertHSVtoRGB(double d, double d2, double d3) {
        double d4;
        double d5;
        double d6;
        if (d2 == 0.0d) {
            d4 = d3 * 255.0d;
            d6 = d4;
            d5 = d6;
        } else {
            double d7 = d * 6.0d;
            if (d7 == 6.0d) {
                d7 = 0.0d;
            }
            double floor = Math.floor(d7);
            double d8 = (1.0d - d2) * d3;
            double d9 = d7 - floor;
            double d10 = (1.0d - (d2 * d9)) * d3;
            double d11 = (1.0d - ((1.0d - d9) * d2)) * d3;
            if (floor == 0.0d) {
                d10 = d11;
                d11 = d8;
            } else {
                if (floor == 1.0d) {
                    d11 = d8;
                    d8 = d10;
                } else if (floor != 2.0d) {
                    if (floor != 3.0d) {
                        if (floor == 4.0d) {
                            d10 = d8;
                            d8 = d11;
                        } else {
                            d11 = d10;
                            d10 = d8;
                        }
                    }
                    d11 = d3;
                    d4 = d8 * 255.0d;
                    d5 = d10 * 255.0d;
                    d6 = d11 * 255.0d;
                }
                d10 = d3;
                d4 = d8 * 255.0d;
                d5 = d10 * 255.0d;
                d6 = d11 * 255.0d;
            }
            d8 = d3;
            d4 = d8 * 255.0d;
            d5 = d10 * 255.0d;
            d6 = d11 * 255.0d;
        }
        return convertRGBtoRGB(d4, d5, d6);
    }

    public static int convertCMYKtoRGB_Adobe(int i, int i2, int i3, int i4) {
        return convertRGBtoRGB(255 - (i + i4), 255 - (i2 + i4), 255 - (i3 + i4));
    }

    public static int convertCIELabtoARGBTest(int i, int i2, int i3) {
        double d = (((i * REF_Y) / 255.0d) + 16.0d) / 116.0d;
        double d2 = d - (i3 / 200.0d);
        double unPivotXYZ = unPivotXYZ((i2 / 500.0d) + d);
        double unPivotXYZ2 = unPivotXYZ(d);
        double unPivotXYZ3 = unPivotXYZ(d2);
        double d3 = unPivotXYZ * REF_X;
        double d4 = unPivotXYZ2 * REF_Y;
        double d5 = unPivotXYZ3 * REF_Z;
        double d6 = d3 / REF_Y;
        double d7 = d4 / REF_Y;
        double d8 = d5 / REF_Y;
        return convertRGBtoRGB(pivotRGB((3.2406d * d6) + ((-1.5372d) * d7) + ((-0.4986d) * d8)) * 255.0d, pivotRGB(((-0.9689d) * d6) + (1.8758d * d7) + (0.0415d * d8)) * 255.0d, pivotRGB((d6 * 0.0557d) + (d7 * (-0.204d)) + (d8 * 1.057d)) * 255.0d);
    }

    private static int convertRGBtoRGB(double d, double d2, double d3) {
        return (Math.min(255, Math.max(0, (int) Math.round(d))) << 16) | ViewCompat.MEASURED_STATE_MASK | (Math.min(255, Math.max(0, (int) Math.round(d2))) << 8) | (Math.min(255, Math.max(0, (int) Math.round(d3))) << 0);
    }

    private static int convertRGBtoRGB(int i, int i2, int i3) {
        return (Math.min(255, Math.max(0, i)) << 16) | ViewCompat.MEASURED_STATE_MASK | (Math.min(255, Math.max(0, i2)) << 8) | (Math.min(255, Math.max(0, i3)) << 0);
    }

    public static ColorCieLch convertCIELabtoCIELCH(ColorCieLab colorCieLab) {
        return convertCIELabtoCIELCH(colorCieLab.L, colorCieLab.a, colorCieLab.b);
    }

    public static ColorCieLch convertCIELabtoCIELCH(double d, double d2, double d3) {
        double degrees;
        double atan2 = Math.atan2(d3, d2);
        if (atan2 > 0.0d) {
            degrees = Math.toDegrees(atan2);
        } else {
            degrees = Math.toDegrees(atan2) + 360.0d;
        }
        return new ColorCieLch(d, Math.sqrt(square(d2) + square(d3)), degrees);
    }

    public static ColorCieLab convertCIELCHtoCIELab(ColorCieLch colorCieLch) {
        return convertCIELCHtoCIELab(colorCieLch.L, colorCieLch.C, colorCieLch.h);
    }

    public static ColorCieLab convertCIELCHtoCIELab(double d, double d2, double d3) {
        return new ColorCieLab(d, Math.cos(degree_2_radian(d3)) * d2, Math.sin(degree_2_radian(d3)) * d2);
    }

    public static ColorCieLuv convertXYZtoCIELuv(ColorXyz colorXyz) {
        return convertXYZtoCIELuv(colorXyz.X, colorXyz.Y, colorXyz.Z);
    }

    public static ColorCieLuv convertXYZtoCIELuv(double d, double d2, double d3) {
        double d4 = 4.0d * d;
        double d5 = d + (15.0d * d2) + (d3 * 3.0d);
        double d6 = d4 / d5;
        double d7 = (9.0d * d2) / d5;
        double pivotXYZ = (pivotXYZ(d2 / REF_Y) * 116.0d) - 16.0d;
        double d8 = 13.0d * pivotXYZ;
        return new ColorCieLuv(pivotXYZ, d8 * (d6 - 0.19783982482140777d), d8 * (d7 - 0.46833630293240974d));
    }

    public static ColorXyz convertCIELuvtoXYZ(ColorCieLuv colorCieLuv) {
        return convertCIELuvtoXYZ(colorCieLuv.L, colorCieLuv.u, colorCieLuv.v);
    }

    public static ColorXyz convertCIELuvtoXYZ(double d, double d2, double d3) {
        double d4 = d * 13.0d;
        double d5 = (d2 / d4) + 0.19783982482140777d;
        double d6 = (d3 / d4) + 0.46833630293240974d;
        double unPivotXYZ = unPivotXYZ((16.0d + d) / 116.0d) * REF_Y;
        double d7 = 9.0d * unPivotXYZ;
        double d8 = (-(d7 * d5)) / (((d5 - 4.0d) * d6) - (d5 * d6));
        return new ColorXyz(d8, unPivotXYZ, ((d7 - ((15.0d * d6) * unPivotXYZ)) - (d6 * d8)) / (d6 * 3.0d));
    }

    public static ColorDin99Lab convertCIELabToDIN99bLab(ColorCieLab colorCieLab) {
        return convertCIELabToDIN99bLab(colorCieLab.L, colorCieLab.a, colorCieLab.b);
    }

    public static ColorDin99Lab convertCIELabToDIN99bLab(double d, double d2, double d3) {
        double d4;
        double d5;
        double log = REF_Y / Math.log(2.58d);
        double radians = Math.toRadians(16.0d);
        double log2 = log * 1.0d * Math.log((0.0158d * d) + 1.0d);
        if (d2 != 0.0d || d3 != 0.0d) {
            double cos = (Math.cos(radians) * d2) + (Math.sin(radians) * d3);
            double cos2 = ((Math.cos(radians) * d3) - (Math.sin(radians) * d2)) * 0.7d;
            double sqrt = Math.sqrt((cos * cos) + (cos2 * cos2));
            if (sqrt != 0.0d) {
                double d6 = sqrt * 0.045d;
                double log3 = Math.log(1.0d + d6) / d6;
                d4 = log3 * cos2;
                d5 = cos * log3;
                return new ColorDin99Lab(log2, d5, d4);
            }
        }
        d5 = 0.0d;
        d4 = 0.0d;
        return new ColorDin99Lab(log2, d5, d4);
    }

    public static ColorCieLab convertDIN99bLabToCIELab(ColorDin99Lab colorDin99Lab) {
        return convertDIN99bLabToCIELab(colorDin99Lab.L99, colorDin99Lab.a99, colorDin99Lab.b99);
    }

    public static ColorCieLab convertDIN99bLabToCIELab(double d, double d2, double d3) {
        double log = REF_Y / Math.log(2.58d);
        double radians = Math.toRadians(16.0d);
        double atan2 = Math.atan2(d3, d2);
        double exp = (Math.exp(((Math.sqrt((d2 * d2) + (d3 * d3)) * 0.045d) * 1.0d) * 1.0d) - 1.0d) / 0.045d;
        double cos = Math.cos(atan2) * exp;
        double sin = (exp * Math.sin(atan2)) / 0.7d;
        return new ColorCieLab((Math.exp((d * 1.0d) / log) - 1.0d) / 0.0158d, (Math.cos(radians) * cos) - (Math.sin(radians) * sin), (cos * Math.sin(radians)) + (sin * Math.cos(radians)));
    }

    public static ColorDin99Lab convertCIELabToDIN99oLab(ColorCieLab colorCieLab) {
        return convertCIELabToDIN99oLab(colorCieLab.L, colorCieLab.a, colorCieLab.b);
    }

    public static ColorDin99Lab convertCIELabToDIN99oLab(double d, double d2, double d3) {
        double cos;
        double sin;
        double log = REF_Y / Math.log(1.39d);
        double radians = Math.toRadians(26.0d);
        double log2 = (log / 1.0d) * Math.log((0.0039d * d) + 1.0d);
        if (d2 == 0.0d && d3 == 0.0d) {
            cos = 0.0d;
            sin = 0.0d;
        } else {
            double cos2 = (Math.cos(radians) * d2) + (Math.sin(radians) * d3);
            double cos3 = ((Math.cos(radians) * d3) - (Math.sin(radians) * d2)) * 0.83d;
            double log3 = Math.log((Math.sqrt((cos2 * cos2) + (cos3 * cos3)) * 0.075d) + 1.0d) / 0.0435d;
            double atan2 = Math.atan2(cos3, cos2) + radians;
            cos = Math.cos(atan2) * log3;
            sin = log3 * Math.sin(atan2);
        }
        return new ColorDin99Lab(log2, cos, sin);
    }

    public static ColorCieLab convertDIN99oLabToCIELab(ColorDin99Lab colorDin99Lab) {
        return convertDIN99oLabToCIELab(colorDin99Lab.L99, colorDin99Lab.a99, colorDin99Lab.b99);
    }

    public static ColorCieLab convertDIN99oLabToCIELab(double d, double d2, double d3) {
        double log = REF_Y / Math.log(1.39d);
        double radians = Math.toRadians(26.0d);
        double exp = (Math.exp((d * 1.0d) / log) - 1.0d) / 0.0039d;
        double atan2 = Math.atan2(d3, d2) - radians;
        double exp2 = (Math.exp(Math.sqrt((d2 * d2) + (d3 * d3)) * 0.0435d) - 1.0d) / 0.075d;
        double cos = Math.cos(atan2) * exp2;
        double sin = (exp2 * Math.sin(atan2)) / 0.83d;
        return new ColorCieLab(exp, (Math.cos(radians) * cos) - (Math.sin(radians) * sin), (cos * Math.sin(radians)) + (sin * Math.cos(radians)));
    }

    private static double pivotRGB(double d) {
        return d > 0.0031308d ? (Math.pow(d, 0.4166666666666667d) * 1.055d) - 0.055d : d * 12.92d;
    }

    private static double unPivotRGB(double d) {
        return d > 0.04045d ? Math.pow((d + 0.055d) / 1.055d, 2.4d) : d / 12.92d;
    }

    private static double pivotXYZ(double d) {
        return d > XYZ_t0 ? Math.pow(d, 0.3333333333333333d) : (d * XYZ_m) + 0.13793103448275862d;
    }

    private static double unPivotXYZ(double d) {
        double pow = Math.pow(d, 3.0d);
        return pow > XYZ_t0 ? pow : (d - 0.13793103448275862d) / XYZ_m;
    }
}
