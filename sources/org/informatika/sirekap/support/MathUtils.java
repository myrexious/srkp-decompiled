package org.informatika.sirekap.support;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.opencv.core.Point;

/* compiled from: MathUtils.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J!\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/support/MathUtils;", "", "()V", "areFloatsEqual", "", "left", "", "right", "calculateAngleBetweenPoints", "point1", "Lorg/opencv/core/Point;", "point2", "refPoint", "getMaxCosine", "maxCosine", "approxPoints", "", "(D[Lorg/opencv/core/Point;)D", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MathUtils {
    public static final MathUtils INSTANCE = new MathUtils();

    private MathUtils() {
    }

    public final boolean areFloatsEqual(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    public final double getMaxCosine(double d, Point[] approxPoints) {
        Intrinsics.checkNotNullParameter(approxPoints, "approxPoints");
        for (int i = 2; i < 5; i++) {
            d = Math.max(Math.abs(calculateAngleBetweenPoints(approxPoints[i % 4], approxPoints[i - 2], approxPoints[i - 1])), d);
        }
        return d;
    }

    private final double calculateAngleBetweenPoints(Point point, Point point2, Point point3) {
        double d = point.x - point3.x;
        double d2 = point.y - point3.y;
        double d3 = point2.x - point3.x;
        double d4 = point2.y - point3.y;
        return ((d * d3) + (d2 * d4)) / Math.sqrt((((d * d) + (d2 * d2)) * ((d3 * d3) + (d4 * d4))) + 1.0E-10d);
    }
}
