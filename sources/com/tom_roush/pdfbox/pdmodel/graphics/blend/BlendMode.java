package com.tom_roush.pdfbox.pdmodel.graphics.blend;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.schema.BuiltinOperator;

/* loaded from: classes3.dex */
public abstract class BlendMode {
    private static final Map<COSName, BlendMode> BLEND_MODES;
    private static final Map<BlendMode, COSName> BLEND_MODE_NAMES;
    public static final NonSeparableBlendMode COLOR;
    public static final SeparableBlendMode COLOR_BURN;
    public static final SeparableBlendMode COLOR_DODGE;
    public static final SeparableBlendMode COMPATIBLE;
    public static final SeparableBlendMode DARKEN;
    public static final SeparableBlendMode DIFFERENCE;
    public static final SeparableBlendMode EXCLUSION;
    public static final SeparableBlendMode HARD_LIGHT;
    public static final NonSeparableBlendMode HUE;
    public static final SeparableBlendMode LIGHTEN;
    public static final NonSeparableBlendMode LUMINOSITY;
    public static final SeparableBlendMode MULTIPLY;
    public static final SeparableBlendMode NORMAL;
    public static final SeparableBlendMode OVERLAY;
    public static final NonSeparableBlendMode SATURATION;
    public static final SeparableBlendMode SCREEN;
    public static final SeparableBlendMode SOFT_LIGHT;

    static {
        SeparableBlendMode separableBlendMode = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.1
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return f;
            }
        };
        NORMAL = separableBlendMode;
        COMPATIBLE = separableBlendMode;
        MULTIPLY = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.2
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return f * f2;
            }
        };
        SCREEN = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.3
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return (f + f2) - (f * f2);
            }
        };
        OVERLAY = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.4
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return ((double) f2) <= 0.5d ? f2 * 2.0f * f : (((f + f2) - (f * f2)) * 2.0f) - 1.0f;
            }
        };
        DARKEN = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.5
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return Math.min(f, f2);
            }
        };
        LIGHTEN = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.6
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return Math.max(f, f2);
            }
        };
        COLOR_DODGE = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.7
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                if (f2 == 0.0f) {
                    return 0.0f;
                }
                float f3 = 1.0f - f;
                if (f2 >= f3) {
                    return 1.0f;
                }
                return f2 / f3;
            }
        };
        COLOR_BURN = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.8
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                if (f2 == 1.0f) {
                    return 1.0f;
                }
                float f3 = 1.0f - f2;
                if (f3 >= f) {
                    return 0.0f;
                }
                return 1.0f - (f3 / f);
            }
        };
        HARD_LIGHT = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.9
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return ((double) f) <= 0.5d ? f2 * 2.0f * f : (((f + f2) - (f * f2)) * 2.0f) - 1.0f;
            }
        };
        SOFT_LIGHT = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.10
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                if (f <= 0.5d) {
                    return f2 - (((1.0f - (f * 2.0f)) * f2) * (1.0f - f2));
                }
                double d = f2;
                return f2 + (((f * 2.0f) - 1.0f) * ((d <= 0.25d ? ((((16.0f * f2) - 12.0f) * f2) + 4.0f) * f2 : (float) Math.sqrt(d)) - f2));
            }
        };
        DIFFERENCE = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.11
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return Math.abs(f2 - f);
            }
        };
        EXCLUSION = new SeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.12
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return (f2 + f) - ((f2 * 2.0f) * f);
            }
        };
        HUE = new NonSeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.13
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.NonSeparableBlendMode
            public void blend(float[] fArr, float[] fArr2, float[] fArr3) {
                float[] fArr4 = new float[3];
                BlendMode.getSaturationRGB(fArr2, fArr, fArr4);
                BlendMode.getLuminosityRGB(fArr2, fArr4, fArr3);
            }
        };
        SATURATION = new NonSeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.14
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.NonSeparableBlendMode
            public void blend(float[] fArr, float[] fArr2, float[] fArr3) {
                BlendMode.getSaturationRGB(fArr, fArr2, fArr3);
            }
        };
        COLOR = new NonSeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.15
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.NonSeparableBlendMode
            public void blend(float[] fArr, float[] fArr2, float[] fArr3) {
                BlendMode.getLuminosityRGB(fArr2, fArr, fArr3);
            }
        };
        LUMINOSITY = new NonSeparableBlendMode() { // from class: com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode.16
            @Override // com.tom_roush.pdfbox.pdmodel.graphics.blend.NonSeparableBlendMode
            public void blend(float[] fArr, float[] fArr2, float[] fArr3) {
                BlendMode.getLuminosityRGB(fArr, fArr2, fArr3);
            }
        };
        BLEND_MODES = createBlendModeMap();
        BLEND_MODE_NAMES = createBlendModeNamesMap();
    }

    public static BlendMode getInstance(COSBase cOSBase) {
        BlendMode blendMode;
        if (cOSBase instanceof COSName) {
            blendMode = BLEND_MODES.get(cOSBase);
        } else {
            BlendMode blendMode2 = null;
            if (cOSBase instanceof COSArray) {
                COSArray cOSArray = (COSArray) cOSBase;
                for (int i = 0; i < cOSArray.size() && (blendMode2 = BLEND_MODES.get(cOSArray.getObject(i))) == null; i++) {
                }
            }
            blendMode = blendMode2;
        }
        return blendMode != null ? blendMode : NORMAL;
    }

    public static COSName getCOSName(BlendMode blendMode) {
        return BLEND_MODE_NAMES.get(blendMode);
    }

    private static int get255Value(float f) {
        double d = f;
        return (int) Math.floor(d < 1.0d ? 255.0d * d : 255.0d);
    }

    public static void getSaturationRGB(float[] fArr, float[] fArr2, float[] fArr3) {
        int i = get255Value(fArr2[0]);
        int i2 = get255Value(fArr2[1]);
        int i3 = get255Value(fArr2[2]);
        int i4 = get255Value(fArr[0]);
        int i5 = get255Value(fArr[1]);
        int i6 = get255Value(fArr[2]);
        int min = Math.min(i, Math.min(i2, i3));
        int max = Math.max(i, Math.max(i2, i3));
        if (min == max) {
            float f = i2 / 255.0f;
            fArr3[0] = f;
            fArr3[1] = f;
            fArr3[2] = f;
            return;
        }
        int max2 = ((Math.max(i4, Math.max(i5, i6)) - Math.min(i4, Math.min(i5, i6))) << 16) / (max - min);
        int i7 = ((((i * 77) + (i2 * BuiltinOperator.DYNAMIC_UPDATE_SLICE)) + (i3 * 28)) + 128) >> 8;
        int i8 = ((((i - i7) * max2) + 32768) >> 16) + i7;
        int i9 = ((((i2 - i7) * max2) + 32768) >> 16) + i7;
        int i10 = ((((i3 - i7) * max2) + 32768) >> 16) + i7;
        if (((i8 | i9 | i10) & 256) == 256) {
            int min2 = Math.min(i8, Math.min(i9, i10));
            int max3 = Math.max(i8, Math.max(i9, i10));
            int min3 = Math.min(min2 < 0 ? (i7 << 16) / (i7 - min2) : 65536, max3 > 255 ? ((255 - i7) << 16) / (max3 - i7) : 65536);
            i8 = ((((i8 - i7) * min3) + 32768) >> 16) + i7;
            i9 = ((((i9 - i7) * min3) + 32768) >> 16) + i7;
            i10 = ((((i10 - i7) * min3) + 32768) >> 16) + i7;
        }
        fArr3[0] = i8 / 255.0f;
        fArr3[1] = i9 / 255.0f;
        fArr3[2] = i10 / 255.0f;
    }

    public static void getLuminosityRGB(float[] fArr, float[] fArr2, float[] fArr3) {
        int i;
        int i2 = get255Value(fArr2[0]);
        int i3 = get255Value(fArr2[1]);
        int i4 = get255Value(fArr2[2]);
        int i5 = get255Value(fArr[0]);
        int i6 = get255Value(fArr[1]);
        int i7 = get255Value(fArr[2]);
        int i8 = (((((i5 - i2) * 77) + ((i6 - i3) * BuiltinOperator.DYNAMIC_UPDATE_SLICE)) + ((i7 - i4) * 28)) + 128) >> 8;
        int i9 = i2 + i8;
        int i10 = i3 + i8;
        int i11 = i4 + i8;
        if (((i9 | i10 | i11) & 256) == 256) {
            int i12 = ((((i5 * 77) + (i6 * BuiltinOperator.DYNAMIC_UPDATE_SLICE)) + (i7 * 28)) + 128) >> 8;
            if (i8 > 0) {
                int max = Math.max(i9, Math.max(i10, i11));
                if (max != i12) {
                    i = ((255 - i12) << 16) / (max - i12);
                    i9 = ((((i9 - i12) * i) + 32768) >> 16) + i12;
                    i10 = ((((i10 - i12) * i) + 32768) >> 16) + i12;
                    i11 = ((((i11 - i12) * i) + 32768) >> 16) + i12;
                }
                i = 0;
                i9 = ((((i9 - i12) * i) + 32768) >> 16) + i12;
                i10 = ((((i10 - i12) * i) + 32768) >> 16) + i12;
                i11 = ((((i11 - i12) * i) + 32768) >> 16) + i12;
            } else {
                int min = Math.min(i9, Math.min(i10, i11));
                if (i12 != min) {
                    i = (i12 << 16) / (i12 - min);
                    i9 = ((((i9 - i12) * i) + 32768) >> 16) + i12;
                    i10 = ((((i10 - i12) * i) + 32768) >> 16) + i12;
                    i11 = ((((i11 - i12) * i) + 32768) >> 16) + i12;
                }
                i = 0;
                i9 = ((((i9 - i12) * i) + 32768) >> 16) + i12;
                i10 = ((((i10 - i12) * i) + 32768) >> 16) + i12;
                i11 = ((((i11 - i12) * i) + 32768) >> 16) + i12;
            }
        }
        fArr3[0] = i9 / 255.0f;
        fArr3[1] = i10 / 255.0f;
        fArr3[2] = i11 / 255.0f;
    }

    private static Map<COSName, BlendMode> createBlendModeMap() {
        HashMap hashMap = new HashMap(13);
        COSName cOSName = COSName.NORMAL;
        SeparableBlendMode separableBlendMode = NORMAL;
        hashMap.put(cOSName, separableBlendMode);
        hashMap.put(COSName.COMPATIBLE, separableBlendMode);
        hashMap.put(COSName.MULTIPLY, MULTIPLY);
        hashMap.put(COSName.SCREEN, SCREEN);
        hashMap.put(COSName.OVERLAY, OVERLAY);
        hashMap.put(COSName.DARKEN, DARKEN);
        hashMap.put(COSName.LIGHTEN, LIGHTEN);
        hashMap.put(COSName.COLOR_DODGE, COLOR_DODGE);
        hashMap.put(COSName.COLOR_BURN, COLOR_BURN);
        hashMap.put(COSName.HARD_LIGHT, HARD_LIGHT);
        hashMap.put(COSName.SOFT_LIGHT, SOFT_LIGHT);
        hashMap.put(COSName.DIFFERENCE, DIFFERENCE);
        hashMap.put(COSName.EXCLUSION, EXCLUSION);
        hashMap.put(COSName.HUE, HUE);
        hashMap.put(COSName.SATURATION, SATURATION);
        hashMap.put(COSName.LUMINOSITY, LUMINOSITY);
        hashMap.put(COSName.COLOR, COLOR);
        return hashMap;
    }

    private static Map<BlendMode, COSName> createBlendModeNamesMap() {
        HashMap hashMap = new HashMap(13);
        hashMap.put(NORMAL, COSName.NORMAL);
        hashMap.put(COMPATIBLE, COSName.NORMAL);
        hashMap.put(MULTIPLY, COSName.MULTIPLY);
        hashMap.put(SCREEN, COSName.SCREEN);
        hashMap.put(OVERLAY, COSName.OVERLAY);
        hashMap.put(DARKEN, COSName.DARKEN);
        hashMap.put(LIGHTEN, COSName.LIGHTEN);
        hashMap.put(COLOR_DODGE, COSName.COLOR_DODGE);
        hashMap.put(COLOR_BURN, COSName.COLOR_BURN);
        hashMap.put(HARD_LIGHT, COSName.HARD_LIGHT);
        hashMap.put(SOFT_LIGHT, COSName.SOFT_LIGHT);
        hashMap.put(DIFFERENCE, COSName.DIFFERENCE);
        hashMap.put(EXCLUSION, COSName.EXCLUSION);
        hashMap.put(HUE, COSName.HUE);
        hashMap.put(SATURATION, COSName.SATURATION);
        hashMap.put(LUMINOSITY, COSName.LUMINOSITY);
        hashMap.put(COLOR, COSName.COLOR);
        return hashMap;
    }
}
