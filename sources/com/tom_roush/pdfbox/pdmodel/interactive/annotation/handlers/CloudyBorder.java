package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.graphics.PointF;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class CloudyBorder {
    private static final double ANGLE_180_DEG = 3.141592653589793d;
    private static final double ANGLE_90_DEG = 1.5707963267948966d;
    private final PDRectangle annotRect;
    private double bboxMaxX;
    private double bboxMaxY;
    private double bboxMinX;
    private double bboxMinY;
    private final double intensity;
    private final double lineWidth;
    private final PDAppearanceContentStream output;
    private boolean outputStarted = false;
    private PDRectangle rectWithDiff;
    private static final double ANGLE_34_DEG = Math.toRadians(34.0d);
    private static final double ANGLE_30_DEG = Math.toRadians(30.0d);
    private static final double ANGLE_12_DEG = Math.toRadians(12.0d);

    private static PointF[] flattenEllipse(double d, double d2, double d3, double d4) {
        return new PointF[0];
    }

    public CloudyBorder(PDAppearanceContentStream pDAppearanceContentStream, double d, double d2, PDRectangle pDRectangle) {
        this.output = pDAppearanceContentStream;
        this.intensity = d;
        this.lineWidth = d2;
        this.annotRect = pDRectangle;
    }

    public void createCloudyRectangle(PDRectangle pDRectangle) throws IOException {
        PDRectangle applyRectDiff = applyRectDiff(pDRectangle, (float) (this.lineWidth / 2.0d));
        this.rectWithDiff = applyRectDiff;
        cloudyRectangleImpl(applyRectDiff.getLowerLeftX(), this.rectWithDiff.getLowerLeftY(), this.rectWithDiff.getUpperRightX(), this.rectWithDiff.getUpperRightY(), false);
        finish();
    }

    public void createCloudyPolygon(float[][] fArr) throws IOException {
        int length = fArr.length;
        PointF[] pointFArr = new PointF[length];
        for (int i = 0; i < length; i++) {
            float[] fArr2 = fArr[i];
            if (fArr2.length == 2) {
                pointFArr[i] = new PointF(fArr2[0], fArr2[1]);
            } else if (fArr2.length == 6) {
                pointFArr[i] = new PointF(fArr2[4], fArr2[5]);
            }
        }
        cloudyPolygonImpl(pointFArr, false);
        finish();
    }

    public void createCloudyEllipse(PDRectangle pDRectangle) throws IOException {
        PDRectangle applyRectDiff = applyRectDiff(pDRectangle, 0.0f);
        this.rectWithDiff = applyRectDiff;
        cloudyEllipseImpl(applyRectDiff.getLowerLeftX(), this.rectWithDiff.getLowerLeftY(), this.rectWithDiff.getUpperRightX(), this.rectWithDiff.getUpperRightY());
        finish();
    }

    public PDRectangle getBBox() {
        return getRectangle();
    }

    public PDRectangle getRectangle() {
        double d = this.bboxMinX;
        double d2 = this.bboxMinY;
        return new PDRectangle((float) d, (float) d2, (float) (this.bboxMaxX - d), (float) (this.bboxMaxY - d2));
    }

    public AffineTransform getMatrix() {
        return AffineTransform.getTranslateInstance(-this.bboxMinX, -this.bboxMinY);
    }

    public PDRectangle getRectDifference() {
        PDRectangle pDRectangle = this.annotRect;
        if (pDRectangle == null) {
            float f = ((float) this.lineWidth) / 2.0f;
            double d = this.lineWidth;
            return new PDRectangle(f, f, (float) d, (float) d);
        }
        PDRectangle pDRectangle2 = this.rectWithDiff;
        if (pDRectangle2 != null) {
            pDRectangle = pDRectangle2;
        }
        float lowerLeftX = pDRectangle.getLowerLeftX() - ((float) this.bboxMinX);
        float lowerLeftY = pDRectangle.getLowerLeftY() - ((float) this.bboxMinY);
        return new PDRectangle(lowerLeftX, lowerLeftY, (((float) this.bboxMaxX) - pDRectangle.getUpperRightX()) - lowerLeftX, (((float) this.bboxMaxY) - pDRectangle.getUpperRightY()) - lowerLeftY);
    }

    private static double cosine(double d, double d2) {
        if (Double.compare(d2, 0.0d) == 0) {
            return 0.0d;
        }
        return d / d2;
    }

    private static double sine(double d, double d2) {
        if (Double.compare(d2, 0.0d) == 0) {
            return 0.0d;
        }
        return d / d2;
    }

    private void cloudyRectangleImpl(double d, double d2, double d3, double d4, boolean z) throws IOException {
        boolean z2;
        PointF[] pointFArr;
        double d5 = d3 - d;
        double d6 = d4 - d2;
        if (this.intensity <= 0.0d) {
            this.output.addRect((float) d, (float) d2, (float) d5, (float) d6);
            this.bboxMinX = d;
            this.bboxMinY = d2;
            this.bboxMaxX = d3;
            this.bboxMaxY = d4;
            return;
        }
        if (d5 < 1.0d) {
            float f = (float) d;
            float f2 = (float) d2;
            pointFArr = new PointF[]{new PointF(f, f2), new PointF(f, (float) d4), new PointF(f, f2)};
            z2 = z;
        } else if (d6 < 1.0d) {
            float f3 = (float) d;
            float f4 = (float) d2;
            PointF[] pointFArr2 = {new PointF(f3, f4), new PointF((float) d3, f4), new PointF(f3, f4)};
            z2 = z;
            pointFArr = pointFArr2;
        } else {
            float f5 = (float) d;
            float f6 = (float) d2;
            float f7 = (float) d3;
            float f8 = (float) d4;
            PointF[] pointFArr3 = {new PointF(f5, f6), new PointF(f7, f6), new PointF(f7, f8), new PointF(f5, f8), new PointF(f5, f6)};
            z2 = z;
            pointFArr = pointFArr3;
        }
        cloudyPolygonImpl(pointFArr, z2);
    }

    private void cloudyPolygonImpl(PointF[] pointFArr, boolean z) throws IOException {
        int i;
        int i2;
        double d;
        PointF[] pointFArr2;
        boolean z2;
        CloudyBorder cloudyBorder;
        double d2;
        int i3;
        double d3;
        CloudyBorder cloudyBorder2 = this;
        PointF[] removeZeroLengthSegments = removeZeroLengthSegments(pointFArr);
        cloudyBorder2.getPositivePolygon(removeZeroLengthSegments);
        int length = removeZeroLengthSegments.length;
        if (length < 2) {
            return;
        }
        if (cloudyBorder2.intensity <= 0.0d) {
            cloudyBorder2.moveTo(removeZeroLengthSegments[0]);
            for (int i4 = 1; i4 < length; i4++) {
                cloudyBorder2.lineTo(removeZeroLengthSegments[i4]);
            }
            return;
        }
        double ellipseCloudRadius = z ? getEllipseCloudRadius() : getPolygonCloudRadius();
        double d4 = ellipseCloudRadius < 0.5d ? 0.5d : ellipseCloudRadius;
        double d5 = ANGLE_34_DEG;
        double cos = Math.cos(d5);
        double d6 = cos * 2.0d * d4;
        double d7 = cos * d4;
        double[] dArr = new double[2];
        int i5 = length - 2;
        double d8 = d4;
        boolean z3 = true;
        if (computeParamsPolygon(d6, d7, cos, d4, Math.hypot(removeZeroLengthSegments[0].x - removeZeroLengthSegments[i5].x, removeZeroLengthSegments[0].y - removeZeroLengthSegments[i5].y), dArr) == 0) {
            d5 = dArr[0];
        }
        int i6 = 0;
        double d9 = d5;
        double d10 = 0.0d;
        while (true) {
            int i7 = i6 + 1;
            if (i7 >= length) {
                return;
            }
            PointF pointF = removeZeroLengthSegments[i6];
            PointF pointF2 = removeZeroLengthSegments[i7];
            double hypot = Math.hypot(pointF2.x - pointF.x, pointF2.y - pointF.y);
            if (Double.compare(hypot, 0.0d) == 0) {
                d2 = d8;
                i2 = i7;
                pointFArr2 = removeZeroLengthSegments;
                i = length;
                cloudyBorder = cloudyBorder2;
                d3 = ANGLE_34_DEG;
                z2 = z3;
            } else {
                i = length;
                i2 = i7;
                int computeParamsPolygon = computeParamsPolygon(d6, d7, cos, d8, hypot, dArr);
                if (computeParamsPolygon < 0) {
                    if (!cloudyBorder2.outputStarted) {
                        cloudyBorder2.moveTo(pointF);
                    }
                    d2 = d8;
                    pointFArr2 = removeZeroLengthSegments;
                    cloudyBorder = cloudyBorder2;
                    d3 = d9;
                    z2 = true;
                } else {
                    double d11 = dArr[0];
                    double d12 = dArr[1];
                    double atan2 = Math.atan2(pointF2.y - pointF.y, pointF2.x - pointF.x);
                    if (i6 == 0) {
                        PointF pointF3 = removeZeroLengthSegments[i5];
                        d = Math.atan2(pointF.y - pointF3.y, pointF.x - pointF3.x);
                    } else {
                        d = d10;
                    }
                    double cosine = cosine(pointF2.x - pointF.x, hypot);
                    double sine = sine(pointF2.y - pointF.y, hypot);
                    double d13 = pointF.x;
                    double d14 = pointF.y;
                    pointFArr2 = removeZeroLengthSegments;
                    addCornerCurl(d, atan2, d8, pointF.x, pointF.y, d11, d9, !cloudyBorder2.outputStarted);
                    double d15 = d6 + (d12 * 2.0d);
                    double d16 = d13 + (d15 * cosine);
                    double d17 = d14 + (d15 * sine);
                    if (computeParamsPolygon >= 1) {
                        z2 = true;
                        addFirstIntermediateCurl(atan2, d8, d11, d16, d17);
                        d16 += d6 * cosine;
                        d17 += d6 * sine;
                        cloudyBorder = this;
                        d2 = d8;
                        i3 = computeParamsPolygon - 1;
                    } else {
                        z2 = true;
                        cloudyBorder = this;
                        d2 = d8;
                        i3 = computeParamsPolygon;
                    }
                    double d18 = atan2;
                    PointF[] intermediateCurlTemplate = cloudyBorder.getIntermediateCurlTemplate(d18, d2);
                    int i8 = 0;
                    while (i8 < i3) {
                        outputCurlTemplate(intermediateCurlTemplate, d16, d17);
                        d16 += d6 * cosine;
                        d17 += d6 * sine;
                        i8++;
                        d18 = d18;
                    }
                    double d19 = d18;
                    d3 = computeParamsPolygon == 0 ? d11 : ANGLE_34_DEG;
                    d10 = d19;
                }
            }
            cloudyBorder2 = cloudyBorder;
            d8 = d2;
            z3 = z2;
            removeZeroLengthSegments = pointFArr2;
            length = i;
            i6 = i2;
            d9 = d3;
        }
    }

    private int computeParamsPolygon(double d, double d2, double d3, double d4, double d5, double[] dArr) {
        double d6 = 0.0d;
        if (Double.compare(d5, 0.0d) == 0) {
            dArr[0] = ANGLE_34_DEG;
            dArr[1] = 0.0d;
            return -1;
        }
        double d7 = d2 * 2.0d;
        int ceil = (int) Math.ceil((d5 - d7) / d);
        double d8 = (d5 - (d7 + (ceil * d))) / 2.0d;
        double d9 = ((d3 * d4) + d8) / d4;
        if (d9 >= -1.0d && d9 <= 1.0d) {
            d6 = Math.acos(d9);
        }
        dArr[0] = d6;
        dArr[1] = d8;
        return ceil;
    }

    private void addCornerCurl(double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z) throws IOException {
        double d8 = d + 3.141592653589793d + d7;
        double radians = d8 - Math.toRadians(22.0d);
        getArcSegment(d8, radians, d4, d5, d3, d3, null, z);
        getArc(radians, d2 - d6, d3, d3, d4, d5, null, false);
    }

    private void addFirstIntermediateCurl(double d, double d2, double d3, double d4, double d5) throws IOException {
        double d6 = d + 3.141592653589793d;
        double d7 = d6 + d3;
        double d8 = ANGLE_30_DEG;
        getArcSegment(d7, d7 - d8, d4, d5, d2, d2, null, false);
        double d9 = d6 + ANGLE_90_DEG;
        getArcSegment(d7 - d8, d9, d4, d5, d2, d2, null, false);
        getArcSegment(d9, (d6 + 3.141592653589793d) - ANGLE_34_DEG, d4, d5, d2, d2, null, false);
    }

    private PointF[] getIntermediateCurlTemplate(double d, double d2) throws IOException {
        ArrayList<PointF> arrayList = new ArrayList<>();
        double d3 = d + 3.141592653589793d;
        double d4 = ANGLE_34_DEG;
        double d5 = ANGLE_12_DEG;
        getArcSegment(d3 + d4, d3 + d5, 0.0d, 0.0d, d2, d2, arrayList, false);
        double d6 = d3 + d5;
        double d7 = d3 + ANGLE_90_DEG;
        getArcSegment(d6, d7, 0.0d, 0.0d, d2, d2, arrayList, false);
        getArcSegment(d7, (d3 + 3.141592653589793d) - d4, 0.0d, 0.0d, d2, d2, arrayList, false);
        return (PointF[]) arrayList.toArray(new PointF[arrayList.size()]);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0051 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x001c -> B:16:0x001e). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void outputCurlTemplate(android.graphics.PointF[] r21, double r22, double r24) throws java.io.IOException {
        /*
            r20 = this;
            r0 = r21
            int r1 = r0.length
            int r2 = r1 % 3
            r3 = 0
            r4 = 1
            if (r2 != r4) goto L1c
            r2 = r0[r3]
            float r3 = r2.x
            double r5 = (double) r3
            double r5 = r5 + r22
            float r2 = r2.y
            double r2 = (double) r2
            double r2 = r2 + r24
            r14 = r20
            r14.moveTo(r5, r2)
            r3 = r4
            goto L1e
        L1c:
            r14 = r20
        L1e:
            int r2 = r3 + 2
            if (r2 >= r1) goto L51
            r4 = r0[r3]
            int r5 = r3 + 1
            r5 = r0[r5]
            r2 = r0[r2]
            float r6 = r4.x
            double r6 = (double) r6
            double r8 = r6 + r22
            float r4 = r4.y
            double r6 = (double) r4
            double r10 = r6 + r24
            float r4 = r5.x
            double r6 = (double) r4
            double r12 = r6 + r22
            float r4 = r5.y
            double r4 = (double) r4
            double r4 = r4 + r24
            float r6 = r2.x
            double r6 = (double) r6
            double r16 = r6 + r22
            float r2 = r2.y
            double r6 = (double) r2
            double r18 = r6 + r24
            r7 = r20
            r14 = r4
            r7.curveTo(r8, r10, r12, r14, r16, r18)
            int r3 = r3 + 3
            goto L1c
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.CloudyBorder.outputCurlTemplate(android.graphics.PointF[], double, double):void");
    }

    private PDRectangle applyRectDiff(PDRectangle pDRectangle, float f) {
        float f2;
        float f3;
        float f4;
        float lowerLeftX = this.annotRect.getLowerLeftX();
        float lowerLeftY = this.annotRect.getLowerLeftY();
        float upperRightX = this.annotRect.getUpperRightX();
        float upperRightY = this.annotRect.getUpperRightY();
        float min = Math.min(lowerLeftX, upperRightX);
        float min2 = Math.min(lowerLeftY, upperRightY);
        float max = Math.max(min, upperRightX);
        float max2 = Math.max(min2, upperRightY);
        if (pDRectangle != null) {
            float max3 = Math.max(pDRectangle.getLowerLeftX(), f);
            f3 = Math.max(pDRectangle.getLowerLeftY(), f);
            f4 = Math.max(pDRectangle.getUpperRightX(), f);
            f2 = Math.max(pDRectangle.getUpperRightY(), f);
            f = max3;
        } else {
            f2 = f;
            f3 = f2;
            f4 = f3;
        }
        float f5 = min + f;
        float f6 = min2 + f3;
        return new PDRectangle(f5, f6, (max - f4) - f5, (max2 - f2) - f6);
    }

    private void reversePolygon(PointF[] pointFArr) {
        int length = pointFArr.length;
        int i = length / 2;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (length - i2) - 1;
            PointF pointF = pointFArr[i2];
            pointFArr[i2] = pointFArr[i3];
            pointFArr[i3] = pointF;
        }
    }

    private void getPositivePolygon(PointF[] pointFArr) {
        if (getPolygonDirection(pointFArr) < 0.0d) {
            reversePolygon(pointFArr);
        }
    }

    private double getPolygonDirection(PointF[] pointFArr) {
        int length = pointFArr.length;
        double d = 0.0d;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = i2 % length;
            d += (pointFArr[i].x * pointFArr[i3].y) - (pointFArr[i].y * pointFArr[i3].x);
            i = i2;
        }
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0048 A[LOOP:1: B:34:0x003f->B:36:0x0048, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getArc(double r28, double r30, double r32, double r34, double r36, double r38, java.util.ArrayList<android.graphics.PointF> r40, boolean r41) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r40
            double r0 = java.lang.Math.cos(r28)
            double r0 = r0 * r32
            double r0 = r0 + r36
            double r2 = java.lang.Math.sin(r28)
            double r2 = r2 * r34
            double r2 = r2 + r38
            double r4 = r30 - r28
            r16 = r4
        L16:
            r18 = 0
            int r4 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r4 >= 0) goto L24
            r4 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r16 = r16 + r4
            goto L16
        L24:
            if (r41 == 0) goto L39
            if (r15 == 0) goto L33
            android.graphics.PointF r4 = new android.graphics.PointF
            float r0 = (float) r0
            float r1 = (float) r2
            r4.<init>(r0, r1)
            r15.add(r4)
            goto L39
        L33:
            r14 = r27
            r14.moveTo(r0, r2)
            goto L3b
        L39:
            r14 = r27
        L3b:
            r20 = r16
            r22 = r18
        L3f:
            r24 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            int r0 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r0 <= 0) goto L66
            double r1 = r28 + r22
            double r3 = r1 + r24
            r26 = 0
            r0 = r27
            r5 = r36
            r7 = r38
            r9 = r32
            r11 = r34
            r13 = r40
            r14 = r26
            r0.getArcSegment(r1, r3, r5, r7, r9, r11, r13, r14)
            double r22 = r22 + r24
            double r20 = r20 - r24
            r14 = r27
            goto L3f
        L66:
            int r0 = (r20 > r18 ? 1 : (r20 == r18 ? 0 : -1))
            if (r0 <= 0) goto L7e
            double r1 = r28 + r22
            double r3 = r28 + r16
            r14 = 0
            r0 = r27
            r5 = r36
            r7 = r38
            r9 = r32
            r11 = r34
            r13 = r40
            r0.getArcSegment(r1, r3, r5, r7, r9, r11, r13, r14)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.CloudyBorder.getArc(double, double, double, double, double, double, java.util.ArrayList, boolean):void");
    }

    private void getArcSegment(double d, double d2, double d3, double d4, double d5, double d6, ArrayList<PointF> arrayList, boolean z) throws IOException {
        double d7;
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        double cos2 = Math.cos(d2);
        double sin2 = Math.sin(d2);
        double d8 = (d2 - d) / 2.0d;
        double sin3 = Math.sin(d8);
        if (Double.compare(sin3, 0.0d) == 0) {
            if (z) {
                double d9 = d3 + (cos * d5);
                double d10 = d4 + (sin * d6);
                if (arrayList != null) {
                    arrayList.add(new PointF((float) d9, (float) d10));
                    return;
                } else {
                    moveTo(d9, d10);
                    return;
                }
            }
            return;
        }
        double cos3 = ((1.0d - Math.cos(d8)) * 1.333333333d) / sin3;
        double d11 = d3 + ((cos - (cos3 * sin)) * d5);
        double d12 = d4 + (((cos3 * cos) + sin) * d6);
        double d13 = d3 + ((cos2 + (cos3 * sin2)) * d5);
        double d14 = d4 + ((sin2 - (cos3 * cos2)) * d6);
        double d15 = d3 + (cos2 * d5);
        double d16 = d4 + (sin2 * d6);
        if (z) {
            double d17 = d3 + (cos * d5);
            double d18 = d4 + (sin * d6);
            if (arrayList != null) {
                d7 = d16;
                arrayList.add(new PointF((float) d17, (float) d18));
            } else {
                d7 = d16;
                moveTo(d17, d18);
            }
        } else {
            d7 = d16;
        }
        if (arrayList != null) {
            arrayList.add(new PointF((float) d11, (float) d12));
            arrayList.add(new PointF((float) d13, (float) d14));
            arrayList.add(new PointF((float) d15, (float) d7));
            return;
        }
        curveTo(d11, d12, d13, d14, d15, d7);
    }

    private void cloudyEllipseImpl(double d, double d2, double d3, double d4) throws IOException {
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        double d10;
        double d11;
        int i;
        double d12;
        if (this.intensity <= 0.0d) {
            drawBasicEllipse(d, d2, d3, d4);
            return;
        }
        double d13 = d3 - d;
        double d14 = d4 - d2;
        double ellipseCloudRadius = getEllipseCloudRadius();
        double d15 = ellipseCloudRadius * 0.5d;
        if (d13 < d15 && d14 < d15) {
            drawBasicEllipse(d, d2, d3, d4);
        } else if ((d13 < 5.0d && d14 > 20.0d) || (d13 > 20.0d && d14 < 5.0d)) {
            cloudyRectangleImpl(d, d2, d3, d4, true);
        } else {
            double sin = (Math.sin(ANGLE_12_DEG) * ellipseCloudRadius) - 1.5d;
            double d16 = sin * 2.0d;
            if (d13 > d16) {
                d6 = d + sin;
                d5 = d3 - sin;
            } else {
                double d17 = (d + d3) / 2.0d;
                d5 = d17 + 0.1d;
                d6 = d17 - 0.1d;
            }
            if (d14 > d16) {
                d8 = d4 - sin;
                d7 = d2 + sin;
            } else {
                double d18 = (d4 + d2) / 2.0d;
                d7 = d18 - 0.1d;
                d8 = d18 + 0.1d;
            }
            PointF[] flattenEllipse = flattenEllipse(d6, d7, d5, d8);
            int length = flattenEllipse.length;
            if (length < 2) {
                return;
            }
            double d19 = 0.0d;
            for (int i2 = 1; i2 < length; i2++) {
                int i3 = i2 - 1;
                d19 += Math.hypot(flattenEllipse[i2].x - flattenEllipse[i3].x, flattenEllipse[i2].y - flattenEllipse[i3].y);
            }
            double cos = Math.cos(ANGLE_34_DEG) * 2.0d;
            int ceil = (int) Math.ceil(d19 / (ellipseCloudRadius * cos));
            if (ceil < 2) {
                drawBasicEllipse(d, d2, d3, d4);
                return;
            }
            double d20 = d19 / ceil;
            double d21 = d20 / cos;
            if (d21 < 0.5d) {
                d20 = cos * 0.5d;
                d9 = 0.5d;
            } else if (d21 < 3.0d) {
                drawBasicEllipse(d, d2, d3, d4);
                return;
            } else {
                d9 = d21;
            }
            double d22 = d20;
            PointF[] pointFArr = new PointF[ceil];
            double d23 = this.lineWidth * 0.1d;
            int i4 = 0;
            int i5 = 0;
            double d24 = 0.0d;
            while (true) {
                int i6 = i4 + 1;
                if (i6 >= length) {
                    break;
                }
                PointF pointF = flattenEllipse[i4];
                PointF pointF2 = flattenEllipse[i6];
                double d25 = pointF2.x - pointF.x;
                PointF[] pointFArr2 = flattenEllipse;
                int i7 = i5;
                double d26 = pointF2.y - pointF.y;
                PointF[] pointFArr3 = pointFArr;
                double hypot = Math.hypot(pointF2.x - pointF.x, pointF2.y - pointF.y);
                if (Double.compare(hypot, 0.0d) == 0) {
                    i = length;
                    d12 = d23;
                } else {
                    double d27 = hypot + d24;
                    double d28 = d22 - d23;
                    if (d27 >= d28 || i4 == length - 2) {
                        double cosine = cosine(d25, hypot);
                        i = length;
                        double sine = sine(d26, hypot);
                        double d29 = d22 - d24;
                        PointF pointF3 = pointF;
                        int i8 = i7;
                        while (true) {
                            d12 = d23;
                            double d30 = pointF3.x + (d29 * cosine);
                            double d31 = cosine;
                            PointF pointF4 = pointF3;
                            double d32 = pointF3.y + (d29 * sine);
                            if (i8 < ceil) {
                                pointFArr3[i8] = new PointF((float) d30, (float) d32);
                                i8++;
                            }
                            d27 -= d22;
                            d29 += d22;
                            if (d27 < d28) {
                                break;
                            }
                            d23 = d12;
                            pointF3 = pointF4;
                            cosine = d31;
                        }
                        d24 = d27 < 0.0d ? 0.0d : d27;
                        i5 = i8;
                        d23 = d12;
                        length = i;
                        flattenEllipse = pointFArr2;
                        pointFArr = pointFArr3;
                        i4 = i6;
                    } else {
                        i = length;
                        d12 = d23;
                        d24 = d27;
                    }
                }
                i5 = i7;
                d23 = d12;
                length = i;
                flattenEllipse = pointFArr2;
                pointFArr = pointFArr3;
                i4 = i6;
            }
            PointF[] pointFArr4 = pointFArr;
            double d33 = 0.0d;
            double d34 = 0.0d;
            int i9 = 0;
            while (i9 < i5) {
                int i10 = i9 + 1;
                int i11 = i10 >= i5 ? 0 : i10;
                PointF pointF5 = pointFArr4[i9];
                PointF pointF6 = pointFArr4[i11];
                if (i9 == 0) {
                    PointF pointF7 = pointFArr4[i5 - 1];
                    d11 = Math.atan2(pointF5.y - pointF7.y, pointF5.x - pointF7.x);
                    d10 = computeParamsEllipse(pointF7, pointF5, d9, d22);
                } else {
                    d10 = d34;
                    d11 = d33;
                }
                double atan2 = Math.atan2(pointF6.y - pointF5.y, pointF6.x - pointF5.x);
                double computeParamsEllipse = computeParamsEllipse(pointF5, pointF6, d9, d22);
                addCornerCurl(d11, atan2, d9, pointF5.x, pointF5.y, computeParamsEllipse, d10, !this.outputStarted);
                i9 = i10;
                d33 = atan2;
                d34 = computeParamsEllipse;
                i5 = i5;
            }
        }
    }

    private double computeParamsEllipse(PointF pointF, PointF pointF2, double d, double d2) {
        double hypot = Math.hypot(pointF2.x - pointF.x, pointF2.y - pointF.y);
        if (Double.compare(hypot, 0.0d) == 0) {
            return ANGLE_34_DEG;
        }
        double d3 = ((d2 / 2.0d) + ((hypot - d2) / 2.0d)) / d;
        if (d3 < -1.0d || d3 > 1.0d) {
            return 0.0d;
        }
        return Math.acos(d3);
    }

    private PointF[] removeZeroLengthSegments(PointF[] pointFArr) {
        int length = pointFArr.length;
        if (length <= 2) {
            return pointFArr;
        }
        PointF pointF = pointFArr[0];
        int i = 1;
        int i2 = length;
        while (i < length) {
            PointF pointF2 = pointFArr[i];
            if (Math.abs(pointF2.x - pointF.x) < 0.5d && Math.abs(pointF2.y - pointF.y) < 0.5d) {
                pointFArr[i] = null;
                i2--;
            }
            i++;
            pointF = pointF2;
        }
        if (i2 == length) {
            return pointFArr;
        }
        PointF[] pointFArr2 = new PointF[i2];
        int i3 = 0;
        for (PointF pointF3 : pointFArr) {
            if (pointF3 != null) {
                pointFArr2[i3] = pointF3;
                i3++;
            }
        }
        return pointFArr2;
    }

    private void drawBasicEllipse(double d, double d2, double d3, double d4) throws IOException {
        getArc(0.0d, 6.283185307179586d, Math.abs(d3 - d) / 2.0d, Math.abs(d4 - d2) / 2.0d, (d + d3) / 2.0d, (d2 + d4) / 2.0d, null, true);
    }

    private void beginOutput(double d, double d2) throws IOException {
        this.bboxMinX = d;
        this.bboxMinY = d2;
        this.bboxMaxX = d;
        this.bboxMaxY = d2;
        this.outputStarted = true;
        this.output.setLineJoinStyle(2);
    }

    private void updateBBox(double d, double d2) {
        this.bboxMinX = Math.min(this.bboxMinX, d);
        this.bboxMinY = Math.min(this.bboxMinY, d2);
        this.bboxMaxX = Math.max(this.bboxMaxX, d);
        this.bboxMaxY = Math.max(this.bboxMaxY, d2);
    }

    private void moveTo(PointF pointF) throws IOException {
        moveTo(pointF.x, pointF.y);
    }

    private void moveTo(double d, double d2) throws IOException {
        if (this.outputStarted) {
            updateBBox(d, d2);
        } else {
            beginOutput(d, d2);
        }
        this.output.moveTo((float) d, (float) d2);
    }

    private void lineTo(PointF pointF) throws IOException {
        lineTo(pointF.x, pointF.y);
    }

    private void lineTo(double d, double d2) throws IOException {
        if (this.outputStarted) {
            updateBBox(d, d2);
        } else {
            beginOutput(d, d2);
        }
        this.output.lineTo((float) d, (float) d2);
    }

    private void curveTo(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        updateBBox(d, d2);
        updateBBox(d3, d4);
        updateBBox(d5, d6);
        this.output.curveTo((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6);
    }

    private void finish() throws IOException {
        if (this.outputStarted) {
            this.output.closePath();
        }
        double d = this.lineWidth;
        if (d > 0.0d) {
            double d2 = d / 2.0d;
            this.bboxMinX -= d2;
            this.bboxMinY -= d2;
            this.bboxMaxX += d2;
            this.bboxMaxY += d2;
        }
    }

    private double getEllipseCloudRadius() {
        return (this.intensity * 4.75d) + (this.lineWidth * 0.5d);
    }

    private double getPolygonCloudRadius() {
        return (this.intensity * 4.0d) + (this.lineWidth * 0.5d);
    }
}
