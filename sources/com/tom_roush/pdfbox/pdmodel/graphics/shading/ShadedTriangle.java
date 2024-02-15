package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.Point;
import android.graphics.PointF;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
class ShadedTriangle {
    private final double area;
    protected final float[][] color;
    protected final PointF[] corner;
    private final int degree;
    private final Line line;
    private final double v0;
    private final double v1;
    private final double v2;

    public ShadedTriangle(PointF[] pointFArr, float[][] fArr) {
        PointF[] pointFArr2 = (PointF[]) pointFArr.clone();
        this.corner = pointFArr2;
        float[][] fArr2 = (float[][]) fArr.clone();
        this.color = fArr2;
        this.area = getArea(pointFArr[0], pointFArr[1], pointFArr[2]);
        int calcDeg = calcDeg(pointFArr);
        this.degree = calcDeg;
        if (calcDeg == 2) {
            if (overlaps(pointFArr2[1], pointFArr2[2]) && !overlaps(pointFArr2[0], pointFArr2[2])) {
                this.line = new Line(new Point(Math.round(pointFArr2[0].x), Math.round(pointFArr2[0].y)), new Point(Math.round(pointFArr2[2].x), Math.round(pointFArr2[2].y)), fArr2[0], fArr2[2]);
            } else {
                this.line = new Line(new Point(Math.round(pointFArr2[1].x), Math.round(pointFArr2[1].y)), new Point(Math.round(pointFArr2[2].x), Math.round(pointFArr2[2].y)), fArr2[1], fArr2[2]);
            }
        } else {
            this.line = null;
        }
        this.v0 = edgeEquationValue(pointFArr[0], pointFArr[1], pointFArr[2]);
        this.v1 = edgeEquationValue(pointFArr[1], pointFArr[2], pointFArr[0]);
        this.v2 = edgeEquationValue(pointFArr[2], pointFArr[0], pointFArr[1]);
    }

    private int calcDeg(PointF[] pointFArr) {
        HashSet hashSet = new HashSet();
        for (PointF pointF : pointFArr) {
            hashSet.add(new Point(Math.round(pointF.x * 1000.0f), Math.round(pointF.y * 1000.0f)));
        }
        return hashSet.size();
    }

    public int getDeg() {
        return this.degree;
    }

    public int[] getBoundary() {
        int round = Math.round(this.corner[0].x);
        int round2 = Math.round(this.corner[1].x);
        int round3 = Math.round(this.corner[2].x);
        int round4 = Math.round(this.corner[0].y);
        int round5 = Math.round(this.corner[1].y);
        int round6 = Math.round(this.corner[2].y);
        return new int[]{Math.min(Math.min(round, round2), round3), Math.max(Math.max(round, round2), round3), Math.min(Math.min(round4, round5), round6), Math.max(Math.max(round4, round5), round6)};
    }

    public Line getLine() {
        return this.line;
    }

    public boolean contains(PointF pointF) {
        int i = this.degree;
        if (i == 1) {
            return overlaps(this.corner[0], pointF) || overlaps(this.corner[1], pointF) || overlaps(this.corner[2], pointF);
        } else if (i == 2) {
            return this.line.linePoints.contains(new Point(Math.round(pointF.x), Math.round(pointF.y)));
        } else {
            PointF[] pointFArr = this.corner;
            if (edgeEquationValue(pointF, pointFArr[1], pointFArr[2]) * this.v0 < 0.0d) {
                return false;
            }
            PointF[] pointFArr2 = this.corner;
            if (edgeEquationValue(pointF, pointFArr2[2], pointFArr2[0]) * this.v1 < 0.0d) {
                return false;
            }
            PointF[] pointFArr3 = this.corner;
            return edgeEquationValue(pointF, pointFArr3[0], pointFArr3[1]) * this.v2 >= 0.0d;
        }
    }

    private boolean overlaps(PointF pointF, PointF pointF2) {
        return ((double) Math.abs(pointF.x - pointF2.x)) < 0.001d && ((double) Math.abs(pointF.y - pointF2.y)) < 0.001d;
    }

    private double edgeEquationValue(PointF pointF, PointF pointF2, PointF pointF3) {
        return ((pointF3.y - pointF2.y) * (pointF.x - pointF2.x)) - ((pointF3.x - pointF2.x) * (pointF.y - pointF2.y));
    }

    private double getArea(PointF pointF, PointF pointF2, PointF pointF3) {
        return Math.abs(((pointF3.x - pointF2.x) * (pointF3.y - pointF.y)) - ((pointF3.x - pointF.x) * (pointF3.y - pointF2.y))) / 2.0d;
    }

    public float[] calcColor(PointF pointF) {
        int length = this.color[0].length;
        float[] fArr = new float[length];
        int i = this.degree;
        if (i == 1) {
            for (int i2 = 0; i2 < length; i2++) {
                float[][] fArr2 = this.color;
                fArr[i2] = ((fArr2[0][i2] + fArr2[1][i2]) + fArr2[2][i2]) / 3.0f;
            }
        } else if (i == 2) {
            return this.line.calcColor(new Point(Math.round(pointF.x), Math.round(pointF.y)));
        } else {
            PointF[] pointFArr = this.corner;
            float area = (float) (getArea(pointF, pointFArr[1], pointFArr[2]) / this.area);
            PointF[] pointFArr2 = this.corner;
            float area2 = (float) (getArea(pointF, pointFArr2[2], pointFArr2[0]) / this.area);
            PointF[] pointFArr3 = this.corner;
            float area3 = (float) (getArea(pointF, pointFArr3[0], pointFArr3[1]) / this.area);
            for (int i3 = 0; i3 < length; i3++) {
                float[][] fArr3 = this.color;
                fArr[i3] = (fArr3[0][i3] * area) + (fArr3[1][i3] * area2) + (fArr3[2][i3] * area3);
            }
        }
        return fArr;
    }

    public String toString() {
        return this.corner[0] + StringUtils.SPACE + this.corner[1] + StringUtils.SPACE + this.corner[2];
    }
}
