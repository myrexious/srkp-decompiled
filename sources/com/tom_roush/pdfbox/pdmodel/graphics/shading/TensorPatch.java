package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import java.lang.reflect.Array;
import java.util.List;

/* loaded from: classes3.dex */
class TensorPatch extends Patch {
    public TensorPatch(PointF[] pointFArr, float[][] fArr) {
        super(fArr);
        this.controlPoints = reshapeControlPoints(pointFArr);
        this.level = calcLevel();
        this.listOfTriangles = getTriangles();
    }

    private PointF[][] reshapeControlPoints(PointF[] pointFArr) {
        PointF[][] pointFArr2 = (PointF[][]) Array.newInstance(PointF.class, 4, 4);
        for (int i = 0; i <= 3; i++) {
            pointFArr2[0][i] = pointFArr[i];
            pointFArr2[3][i] = pointFArr[9 - i];
        }
        for (int i2 = 1; i2 <= 2; i2++) {
            pointFArr2[i2][0] = pointFArr[12 - i2];
            pointFArr2[i2][2] = pointFArr[i2 + 12];
            pointFArr2[i2][3] = pointFArr[i2 + 3];
        }
        pointFArr2[1][1] = pointFArr[12];
        pointFArr2[2][1] = pointFArr[15];
        return pointFArr2;
    }

    private int[] calcLevel() {
        int[] iArr = {4, 4};
        PointF[] pointFArr = new PointF[4];
        PointF[] pointFArr2 = new PointF[4];
        for (int i = 0; i < 4; i++) {
            pointFArr[i] = this.controlPoints[i][0];
            pointFArr2[i] = this.controlPoints[i][3];
        }
        if (isEdgeALine(pointFArr) && isEdgeALine(pointFArr2) && !isOnSameSideCC(this.controlPoints[1][1]) && !isOnSameSideCC(this.controlPoints[1][2]) && !isOnSameSideCC(this.controlPoints[2][1]) && !isOnSameSideCC(this.controlPoints[2][2])) {
            double len = getLen(pointFArr[0], pointFArr[3]);
            double len2 = getLen(pointFArr2[0], pointFArr2[3]);
            if (len <= 800.0d && len2 <= 800.0d) {
                if (len > 400.0d || len2 > 400.0d) {
                    iArr[0] = 3;
                } else if (len > 200.0d || len2 > 200.0d) {
                    iArr[0] = 2;
                } else {
                    iArr[0] = 1;
                }
            }
        }
        if (isEdgeALine(this.controlPoints[0]) && isEdgeALine(this.controlPoints[3]) && !isOnSameSideDD(this.controlPoints[1][1]) && !isOnSameSideDD(this.controlPoints[1][2]) && !isOnSameSideDD(this.controlPoints[2][1]) && !isOnSameSideDD(this.controlPoints[2][2])) {
            double len3 = getLen(this.controlPoints[0][0], this.controlPoints[0][3]);
            double len4 = getLen(this.controlPoints[3][0], this.controlPoints[3][3]);
            if (len3 <= 800.0d && len4 <= 800.0d) {
                if (len3 > 400.0d || len4 > 400.0d) {
                    iArr[1] = 3;
                } else if (len3 > 200.0d || len4 > 200.0d) {
                    iArr[1] = 2;
                } else {
                    iArr[1] = 1;
                }
            }
        }
        return iArr;
    }

    private boolean isOnSameSideCC(PointF pointF) {
        return edgeEquationValue(pointF, this.controlPoints[0][0], this.controlPoints[3][0]) * edgeEquationValue(pointF, this.controlPoints[0][3], this.controlPoints[3][3]) > 0.0d;
    }

    private boolean isOnSameSideDD(PointF pointF) {
        return edgeEquationValue(pointF, this.controlPoints[0][0], this.controlPoints[0][3]) * edgeEquationValue(pointF, this.controlPoints[3][0], this.controlPoints[3][3]) > 0.0d;
    }

    private List<ShadedTriangle> getTriangles() {
        return getShadedTriangles(getPatchCoordinatesColor());
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.Patch
    public PointF[] getFlag1Edge() {
        PointF[] pointFArr = new PointF[4];
        for (int i = 0; i < 4; i++) {
            pointFArr[i] = this.controlPoints[i][3];
        }
        return pointFArr;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.Patch
    public PointF[] getFlag2Edge() {
        PointF[] pointFArr = new PointF[4];
        for (int i = 0; i < 4; i++) {
            pointFArr[i] = this.controlPoints[3][3 - i];
        }
        return pointFArr;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.Patch
    public PointF[] getFlag3Edge() {
        PointF[] pointFArr = new PointF[4];
        for (int i = 0; i < 4; i++) {
            pointFArr[i] = this.controlPoints[3 - i][0];
        }
        return pointFArr;
    }

    private CoordinateColorPair[][] getPatchCoordinatesColor() {
        int i = 0;
        int length = this.cornerColor[0].length;
        double[][] bernsteinPolynomials = getBernsteinPolynomials(this.level[0]);
        int length2 = bernsteinPolynomials[0].length;
        double[][] bernsteinPolynomials2 = getBernsteinPolynomials(this.level[1]);
        int length3 = bernsteinPolynomials2[0].length;
        CoordinateColorPair[][] coordinateColorPairArr = (CoordinateColorPair[][]) Array.newInstance(CoordinateColorPair.class, length3, length2);
        double d = 1.0d / (length2 - 1);
        double d2 = 1.0d / (length3 - 1);
        double d3 = -d2;
        int i2 = 0;
        while (i2 < length3) {
            double d4 = d3 + d2;
            double d5 = -d;
            while (i < length2) {
                double d6 = d4;
                double d7 = d2;
                double d8 = 0.0d;
                int i3 = 0;
                int i4 = length2;
                int i5 = length3;
                double d9 = 0.0d;
                while (true) {
                    if (i3 >= 4) {
                        break;
                    }
                    int i6 = length;
                    int i7 = 0;
                    for (int i8 = 4; i7 < i8; i8 = 4) {
                        d8 += this.controlPoints[i3][i7].x * bernsteinPolynomials[i3][i] * bernsteinPolynomials2[i7][i2];
                        d9 += this.controlPoints[i3][i7].y * bernsteinPolynomials[i3][i] * bernsteinPolynomials2[i7][i2];
                        i7++;
                        d = d;
                    }
                    i3++;
                    length = i6;
                }
                int i9 = length;
                double d10 = d;
                PointF pointF = new PointF((float) d8, (float) d9);
                d5 += d10;
                int i10 = i9;
                float[] fArr = new float[i10];
                int i11 = 0;
                while (i11 < i10) {
                    double d11 = 1.0d - d5;
                    fArr[i11] = (float) (((1.0d - d6) * ((this.cornerColor[0][i11] * d11) + (this.cornerColor[3][i11] * d5))) + (d6 * ((d11 * this.cornerColor[1][i11]) + (this.cornerColor[2][i11] * d5))));
                    i11++;
                    bernsteinPolynomials = bernsteinPolynomials;
                    i10 = i10;
                }
                int i12 = i10;
                coordinateColorPairArr[i2][i] = new CoordinateColorPair(pointF, fArr);
                i++;
                length3 = i5;
                length2 = i4;
                d2 = d7;
                d4 = d6;
                d = d10;
                bernsteinPolynomials = bernsteinPolynomials;
                length = i12;
            }
            i2++;
            i = 0;
            length2 = length2;
            d3 = d4;
        }
        return coordinateColorPairArr;
    }

    private double[][] getBernsteinPolynomials(int i) {
        int i2 = (1 << i) + 1;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, 4, i2);
        double d = 1.0d / (i2 - 1);
        double d2 = -d;
        for (int i3 = 0; i3 < i2; i3++) {
            d2 += d;
            double d3 = 1.0d - d2;
            dArr[0][i3] = d3 * d3 * d3;
            double d4 = 3.0d * d2;
            dArr[1][i3] = d4 * d3 * d3;
            dArr[2][i3] = d4 * d2 * d3;
            dArr[3][i3] = d2 * d2 * d2;
        }
        return dArr;
    }
}
