package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.Point;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
class Line {
    private final float[] color0;
    private final float[] color1;
    protected final Set<Point> linePoints;
    private final Point point0;
    private final Point point1;

    public Line(Point point, Point point2, float[] fArr, float[] fArr2) {
        this.point0 = point;
        this.point1 = point2;
        this.color0 = (float[]) fArr.clone();
        this.color1 = (float[]) fArr2.clone();
        this.linePoints = calcLine(point.x, point.y, point2.x, point2.y);
    }

    private Set<Point> calcLine(int i, int i2, int i3, int i4) {
        HashSet hashSet = new HashSet(3);
        int abs = Math.abs(i3 - i);
        int abs2 = Math.abs(i4 - i2);
        int i5 = i < i3 ? 1 : -1;
        int i6 = i2 >= i4 ? -1 : 1;
        int i7 = abs - abs2;
        while (true) {
            hashSet.add(new Point(i, i2));
            if (i == i3 && i2 == i4) {
                return hashSet;
            }
            int i8 = i7 * 2;
            if (i8 > (-abs2)) {
                i7 -= abs2;
                i += i5;
            }
            if (i8 < abs) {
                i7 += abs;
                i2 += i6;
            }
        }
    }

    public float[] calcColor(Point point) {
        if (this.point0.x == this.point1.x && this.point0.y == this.point1.y) {
            return this.color0;
        }
        int length = this.color0.length;
        float[] fArr = new float[length];
        int i = 0;
        if (this.point0.x == this.point1.x) {
            float f = this.point1.y - this.point0.y;
            while (i < length) {
                fArr[i] = ((this.color0[i] * (this.point1.y - point.y)) / f) + ((this.color1[i] * (point.y - this.point0.y)) / f);
                i++;
            }
        } else {
            float f2 = this.point1.x - this.point0.x;
            while (i < length) {
                fArr[i] = ((this.color0[i] * (this.point1.x - point.x)) / f2) + ((this.color1[i] * (point.x - this.point0.x)) / f2);
                i++;
            }
        }
        return fArr;
    }
}
