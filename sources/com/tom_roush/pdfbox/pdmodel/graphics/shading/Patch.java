package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
abstract class Patch {
    protected PointF[][] controlPoints;
    protected float[][] cornerColor;
    protected int[] level;
    protected List<ShadedTriangle> listOfTriangles;

    public abstract PointF[] getFlag1Edge();

    public abstract PointF[] getFlag2Edge();

    public abstract PointF[] getFlag3Edge();

    public Patch(float[][] fArr) {
        this.cornerColor = (float[][]) fArr.clone();
    }

    public float[][] getFlag1Color() {
        int length = this.cornerColor[0].length;
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 2, length);
        for (int i = 0; i < length; i++) {
            float[] fArr2 = fArr[0];
            float[][] fArr3 = this.cornerColor;
            fArr2[i] = fArr3[1][i];
            fArr[1][i] = fArr3[2][i];
        }
        return fArr;
    }

    public float[][] getFlag2Color() {
        int length = this.cornerColor[0].length;
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 2, length);
        for (int i = 0; i < length; i++) {
            float[] fArr2 = fArr[0];
            float[][] fArr3 = this.cornerColor;
            fArr2[i] = fArr3[2][i];
            fArr[1][i] = fArr3[3][i];
        }
        return fArr;
    }

    public float[][] getFlag3Color() {
        int length = this.cornerColor[0].length;
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 2, length);
        for (int i = 0; i < length; i++) {
            float[] fArr2 = fArr[0];
            float[][] fArr3 = this.cornerColor;
            fArr2[i] = fArr3[3][i];
            fArr[1][i] = fArr3[0][i];
        }
        return fArr;
    }

    public double getLen(PointF pointF, PointF pointF2) {
        double d = pointF2.x - pointF.x;
        double d2 = pointF2.y - pointF.y;
        return Math.sqrt((d * d) + (d2 * d2));
    }

    public boolean isEdgeALine(PointF[] pointFArr) {
        double abs = Math.abs(edgeEquationValue(pointFArr[1], pointFArr[0], pointFArr[3]));
        double abs2 = Math.abs(edgeEquationValue(pointFArr[2], pointFArr[0], pointFArr[3]));
        double abs3 = Math.abs(pointFArr[0].x - pointFArr[3].x);
        double abs4 = Math.abs(pointFArr[0].y - pointFArr[3].y);
        if (abs > abs3 || abs2 > abs3) {
            return abs <= abs4 && abs2 <= abs4;
        }
        return true;
    }

    public double edgeEquationValue(PointF pointF, PointF pointF2, PointF pointF3) {
        return ((pointF3.y - pointF2.y) * (pointF.x - pointF2.x)) - ((pointF3.x - pointF2.x) * (pointF.y - pointF2.y));
    }

    public List<ShadedTriangle> getShadedTriangles(CoordinateColorPair[][] coordinateColorPairArr) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        int length = coordinateColorPairArr.length;
        int length2 = coordinateColorPairArr[0].length;
        for (int i = 1; i < length; i++) {
            for (int i2 = 1; i2 < length2; i2++) {
                int i3 = i - 1;
                int i4 = i2 - 1;
                PointF pointF = coordinateColorPairArr[i3][i4].coordinate;
                PointF pointF2 = coordinateColorPairArr[i3][i2].coordinate;
                PointF pointF3 = coordinateColorPairArr[i][i2].coordinate;
                PointF pointF4 = coordinateColorPairArr[i][i4].coordinate;
                if (overlaps(pointF, pointF2) || overlaps(pointF, pointF4)) {
                    z = false;
                } else {
                    arrayList.add(new ShadedTriangle(new PointF[]{pointF, pointF2, pointF4}, new float[][]{coordinateColorPairArr[i3][i4].color, coordinateColorPairArr[i3][i2].color, coordinateColorPairArr[i][i4].color}));
                    z = true;
                }
                if (!z || (!overlaps(pointF3, pointF2) && !overlaps(pointF3, pointF4))) {
                    arrayList.add(new ShadedTriangle(new PointF[]{pointF4, pointF2, pointF3}, new float[][]{coordinateColorPairArr[i][i4].color, coordinateColorPairArr[i3][i2].color, coordinateColorPairArr[i][i2].color}));
                }
            }
        }
        return arrayList;
    }

    private boolean overlaps(PointF pointF, PointF pointF2) {
        return ((double) Math.abs(pointF.x - pointF2.x)) < 0.001d && ((double) Math.abs(pointF.y - pointF2.y)) < 0.001d;
    }
}
