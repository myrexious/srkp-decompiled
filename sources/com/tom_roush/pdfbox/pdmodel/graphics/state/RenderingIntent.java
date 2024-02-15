package com.tom_roush.pdfbox.pdmodel.graphics.state;

/* loaded from: classes3.dex */
public enum RenderingIntent {
    ABSOLUTE_COLORIMETRIC("AbsoluteColorimetric"),
    RELATIVE_COLORIMETRIC("RelativeColorimetric"),
    SATURATION("Saturation"),
    PERCEPTUAL("Perceptual");
    
    private final String value;

    public static RenderingIntent fromString(String str) {
        RenderingIntent[] values;
        for (RenderingIntent renderingIntent : values()) {
            if (renderingIntent.value.equals(str)) {
                return renderingIntent;
            }
        }
        return RELATIVE_COLORIMETRIC;
    }

    RenderingIntent(String str) {
        this.value = str;
    }

    public String stringValue() {
        return this.value;
    }
}
