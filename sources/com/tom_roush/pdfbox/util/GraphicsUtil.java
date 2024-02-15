package com.tom_roush.pdfbox.util;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;

/* loaded from: classes3.dex */
public class GraphicsUtil {
    public static Region getPathRegion(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        Region region = new Region();
        Rect rect = new Rect();
        rectF.round(rect);
        region.setPath(path, new Region(rect));
        return region;
    }
}
