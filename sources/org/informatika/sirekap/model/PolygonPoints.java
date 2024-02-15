package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.opencv.core.Point;

/* compiled from: PolygonPoints.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/model/PolygonPoints;", "", "topLeftPoint", "Lorg/opencv/core/Point;", "topRightPoint", "bottomLeftPoint", "bottomRightPoint", "(Lorg/opencv/core/Point;Lorg/opencv/core/Point;Lorg/opencv/core/Point;Lorg/opencv/core/Point;)V", "getBottomLeftPoint", "()Lorg/opencv/core/Point;", "getBottomRightPoint", "getTopLeftPoint", "getTopRightPoint", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PolygonPoints {
    private final Point bottomLeftPoint;
    private final Point bottomRightPoint;
    private final Point topLeftPoint;
    private final Point topRightPoint;

    public static /* synthetic */ PolygonPoints copy$default(PolygonPoints polygonPoints, Point point, Point point2, Point point3, Point point4, int i, Object obj) {
        if ((i & 1) != 0) {
            point = polygonPoints.topLeftPoint;
        }
        if ((i & 2) != 0) {
            point2 = polygonPoints.topRightPoint;
        }
        if ((i & 4) != 0) {
            point3 = polygonPoints.bottomLeftPoint;
        }
        if ((i & 8) != 0) {
            point4 = polygonPoints.bottomRightPoint;
        }
        return polygonPoints.copy(point, point2, point3, point4);
    }

    public final Point component1() {
        return this.topLeftPoint;
    }

    public final Point component2() {
        return this.topRightPoint;
    }

    public final Point component3() {
        return this.bottomLeftPoint;
    }

    public final Point component4() {
        return this.bottomRightPoint;
    }

    public final PolygonPoints copy(Point topLeftPoint, Point topRightPoint, Point bottomLeftPoint, Point bottomRightPoint) {
        Intrinsics.checkNotNullParameter(topLeftPoint, "topLeftPoint");
        Intrinsics.checkNotNullParameter(topRightPoint, "topRightPoint");
        Intrinsics.checkNotNullParameter(bottomLeftPoint, "bottomLeftPoint");
        Intrinsics.checkNotNullParameter(bottomRightPoint, "bottomRightPoint");
        return new PolygonPoints(topLeftPoint, topRightPoint, bottomLeftPoint, bottomRightPoint);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PolygonPoints) {
            PolygonPoints polygonPoints = (PolygonPoints) obj;
            return Intrinsics.areEqual(this.topLeftPoint, polygonPoints.topLeftPoint) && Intrinsics.areEqual(this.topRightPoint, polygonPoints.topRightPoint) && Intrinsics.areEqual(this.bottomLeftPoint, polygonPoints.bottomLeftPoint) && Intrinsics.areEqual(this.bottomRightPoint, polygonPoints.bottomRightPoint);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.topLeftPoint.hashCode() * 31) + this.topRightPoint.hashCode()) * 31) + this.bottomLeftPoint.hashCode()) * 31) + this.bottomRightPoint.hashCode();
    }

    public String toString() {
        Point point = this.topLeftPoint;
        Point point2 = this.topRightPoint;
        Point point3 = this.bottomLeftPoint;
        return "PolygonPoints(topLeftPoint=" + point + ", topRightPoint=" + point2 + ", bottomLeftPoint=" + point3 + ", bottomRightPoint=" + this.bottomRightPoint + ")";
    }

    public PolygonPoints(Point topLeftPoint, Point topRightPoint, Point bottomLeftPoint, Point bottomRightPoint) {
        Intrinsics.checkNotNullParameter(topLeftPoint, "topLeftPoint");
        Intrinsics.checkNotNullParameter(topRightPoint, "topRightPoint");
        Intrinsics.checkNotNullParameter(bottomLeftPoint, "bottomLeftPoint");
        Intrinsics.checkNotNullParameter(bottomRightPoint, "bottomRightPoint");
        this.topLeftPoint = topLeftPoint;
        this.topRightPoint = topRightPoint;
        this.bottomLeftPoint = bottomLeftPoint;
        this.bottomRightPoint = bottomRightPoint;
    }

    public final Point getTopLeftPoint() {
        return this.topLeftPoint;
    }

    public final Point getTopRightPoint() {
        return this.topRightPoint;
    }

    public final Point getBottomLeftPoint() {
        return this.bottomLeftPoint;
    }

    public final Point getBottomRightPoint() {
        return this.bottomRightPoint;
    }
}
