package com.tom_roush.pdfbox.util;

import android.graphics.PointF;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes3.dex */
public final class Matrix implements Cloneable {
    private static final float MAX_FLOAT_VALUE = Float.MAX_VALUE;
    public static final int SIZE = 9;
    private float[] single;

    public Matrix() {
        this.single = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    private Matrix(float[] fArr) {
        this.single = fArr;
    }

    public Matrix(COSArray cOSArray) {
        float[] fArr = new float[9];
        this.single = fArr;
        fArr[0] = ((COSNumber) cOSArray.getObject(0)).floatValue();
        this.single[1] = ((COSNumber) cOSArray.getObject(1)).floatValue();
        this.single[3] = ((COSNumber) cOSArray.getObject(2)).floatValue();
        this.single[4] = ((COSNumber) cOSArray.getObject(3)).floatValue();
        this.single[6] = ((COSNumber) cOSArray.getObject(4)).floatValue();
        this.single[7] = ((COSNumber) cOSArray.getObject(5)).floatValue();
        this.single[8] = 1.0f;
    }

    public Matrix(float f, float f2, float f3, float f4, float f5, float f6) {
        this.single = r0;
        float[] fArr = {f, f2, 0.0f, f3, f4, 0.0f, f5, f6, 1.0f};
    }

    public Matrix(AffineTransform affineTransform) {
        float[] fArr = new float[9];
        this.single = fArr;
        fArr[0] = (float) affineTransform.getScaleX();
        this.single[1] = (float) affineTransform.getShearY();
        this.single[3] = (float) affineTransform.getShearX();
        this.single[4] = (float) affineTransform.getScaleY();
        this.single[6] = (float) affineTransform.getTranslateX();
        this.single[7] = (float) affineTransform.getTranslateY();
        this.single[8] = 1.0f;
    }

    public static Matrix createMatrix(COSBase cOSBase) {
        if (!(cOSBase instanceof COSArray)) {
            return new Matrix();
        }
        COSArray cOSArray = (COSArray) cOSBase;
        if (cOSArray.size() < 6) {
            return new Matrix();
        }
        for (int i = 0; i < 6; i++) {
            if (!(cOSArray.getObject(i) instanceof COSNumber)) {
                return new Matrix();
            }
        }
        return new Matrix(cOSArray);
    }

    @Deprecated
    public void reset() {
        Arrays.fill(this.single, 0.0f);
        float[] fArr = this.single;
        fArr[0] = 1.0f;
        fArr[4] = 1.0f;
        fArr[8] = 1.0f;
    }

    public AffineTransform createAffineTransform() {
        float[] fArr = this.single;
        return new AffineTransform(fArr[0], fArr[1], fArr[3], fArr[4], fArr[6], fArr[7]);
    }

    @Deprecated
    public void setFromAffineTransform(AffineTransform affineTransform) {
        this.single[0] = (float) affineTransform.getScaleX();
        this.single[1] = (float) affineTransform.getShearY();
        this.single[3] = (float) affineTransform.getShearX();
        this.single[4] = (float) affineTransform.getScaleY();
        this.single[6] = (float) affineTransform.getTranslateX();
        this.single[7] = (float) affineTransform.getTranslateY();
    }

    public float getValue(int i, int i2) {
        return this.single[(i * 3) + i2];
    }

    public void setValue(int i, int i2, float f) {
        this.single[(i * 3) + i2] = f;
    }

    public float[][] getValues() {
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 3, 3);
        float[] fArr2 = fArr[0];
        float[] fArr3 = this.single;
        fArr2[0] = fArr3[0];
        fArr2[1] = fArr3[1];
        fArr2[2] = fArr3[2];
        float[] fArr4 = fArr[1];
        fArr4[0] = fArr3[3];
        fArr4[1] = fArr3[4];
        fArr4[2] = fArr3[5];
        float[] fArr5 = fArr[2];
        fArr5[0] = fArr3[6];
        fArr5[1] = fArr3[7];
        fArr5[2] = fArr3[8];
        return fArr;
    }

    @Deprecated
    public double[][] getValuesAsDouble() {
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, 3, 3);
        double[] dArr2 = dArr[0];
        float[] fArr = this.single;
        dArr2[0] = fArr[0];
        dArr2[1] = fArr[1];
        dArr2[2] = fArr[2];
        double[] dArr3 = dArr[1];
        dArr3[0] = fArr[3];
        dArr3[1] = fArr[4];
        dArr3[2] = fArr[5];
        double[] dArr4 = dArr[2];
        dArr4[0] = fArr[6];
        dArr4[1] = fArr[7];
        dArr4[2] = fArr[8];
        return dArr;
    }

    public void concatenate(Matrix matrix) {
        matrix.multiply(this, this);
    }

    public void translate(Vector vector) {
        concatenate(getTranslateInstance(vector.getX(), vector.getY()));
    }

    public void translate(float f, float f2) {
        concatenate(getTranslateInstance(f, f2));
    }

    public void scale(float f, float f2) {
        concatenate(getScaleInstance(f, f2));
    }

    public void rotate(double d) {
        concatenate(getRotateInstance(d, 0.0f, 0.0f));
    }

    public Matrix multiply(Matrix matrix) {
        return multiply(matrix, new Matrix());
    }

    @Deprecated
    public Matrix multiply(Matrix matrix, Matrix matrix2) {
        float[] fArr = (matrix2 == null || matrix2 == matrix || matrix2 == this) ? new float[9] : matrix2.single;
        multiplyArrays(this.single, matrix.single, fArr);
        if (isFinite(fArr[0]) && isFinite(fArr[1]) && isFinite(fArr[2]) && isFinite(fArr[3]) && isFinite(fArr[4]) && isFinite(fArr[5]) && isFinite(fArr[6]) && isFinite(fArr[7]) && isFinite(fArr[8])) {
            if (matrix2 == null) {
                return new Matrix(fArr);
            }
            matrix2.single = fArr;
            return matrix2;
        }
        throw new IllegalArgumentException("Multiplying two matrices produces illegal values");
    }

    private static boolean isFinite(float f) {
        return Math.abs(f) <= Float.MAX_VALUE;
    }

    private void multiplyArrays(float[] fArr, float[] fArr2, float[] fArr3) {
        float f = fArr[1];
        float f2 = fArr2[3];
        float f3 = fArr[2];
        float f4 = fArr2[6];
        fArr3[0] = (fArr[0] * fArr2[0]) + (f * f2) + (f3 * f4);
        float f5 = fArr[0];
        float f6 = fArr2[4];
        float f7 = fArr2[7];
        fArr3[1] = (fArr2[1] * f5) + (f * f6) + (f3 * f7);
        float f8 = f5 * fArr2[2];
        float f9 = fArr[1];
        float f10 = fArr2[5];
        float f11 = fArr2[8];
        fArr3[2] = f8 + (f9 * f10) + (f3 * f11);
        float f12 = fArr[3];
        float f13 = fArr2[0];
        float f14 = fArr[4];
        float f15 = (f12 * f13) + (f2 * f14);
        float f16 = fArr[5];
        fArr3[3] = f15 + (f16 * f4);
        float f17 = fArr[3];
        float f18 = fArr2[1];
        fArr3[4] = (f17 * f18) + (f14 * f6) + (f16 * f7);
        float f19 = fArr2[2];
        fArr3[5] = (f17 * f19) + (fArr[4] * f10) + (f16 * f11);
        float f20 = fArr[6] * f13;
        float f21 = fArr[7];
        float f22 = f20 + (fArr2[3] * f21);
        float f23 = fArr[8];
        fArr3[6] = f22 + (f4 * f23);
        float f24 = fArr[6];
        fArr3[7] = (f18 * f24) + (f21 * fArr2[4]) + (f7 * f23);
        fArr3[8] = (f24 * f19) + (fArr[7] * fArr2[5]) + (f23 * f11);
    }

    public void transform(PointF pointF) {
        float f = pointF.x;
        float f2 = pointF.y;
        float[] fArr = this.single;
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = fArr[3];
        float f6 = fArr[4];
        pointF.set((f3 * f) + (f5 * f2) + fArr[6], (f * f4) + (f2 * f6) + fArr[7]);
    }

    public PointF transformPoint(float f, float f2) {
        float[] fArr = this.single;
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = fArr[3];
        float f6 = fArr[4];
        return new PointF((f3 * f) + (f5 * f2) + fArr[6], (f * f4) + (f2 * f6) + fArr[7]);
    }

    public Vector transform(Vector vector) {
        float[] fArr = this.single;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        float f5 = fArr[6];
        float f6 = fArr[7];
        float x = vector.getX();
        float y = vector.getY();
        return new Vector((f * x) + (f3 * y) + f5, (x * f2) + (y * f4) + f6);
    }

    @Deprecated
    public Matrix extractScaling() {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        float[] fArr2 = this.single;
        fArr[0] = fArr2[0];
        fArr[4] = fArr2[4];
        return matrix;
    }

    public static Matrix getScaleInstance(float f, float f2) {
        return new Matrix(f, 0.0f, 0.0f, f2, 0.0f, 0.0f);
    }

    @Deprecated
    public Matrix extractTranslating() {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        float[] fArr2 = this.single;
        fArr[6] = fArr2[6];
        fArr[7] = fArr2[7];
        return matrix;
    }

    @Deprecated
    public static Matrix getTranslatingInstance(float f, float f2) {
        return new Matrix(1.0f, 0.0f, 0.0f, 1.0f, f, f2);
    }

    public static Matrix getTranslateInstance(float f, float f2) {
        return new Matrix(1.0f, 0.0f, 0.0f, 1.0f, f, f2);
    }

    public static Matrix getRotateInstance(double d, float f, float f2) {
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix(cos, sin, -sin, cos, f, f2);
    }

    public static Matrix concatenate(Matrix matrix, Matrix matrix2) {
        return matrix2.multiply(matrix);
    }

    /* renamed from: clone */
    public Matrix m268clone() {
        return new Matrix((float[]) this.single.clone());
    }

    public float getScalingFactorX() {
        float[] fArr = this.single;
        if (fArr[1] != 0.0f) {
            return (float) Math.sqrt(Math.pow(fArr[0], 2.0d) + Math.pow(this.single[1], 2.0d));
        }
        return fArr[0];
    }

    public float getScalingFactorY() {
        float[] fArr = this.single;
        float f = fArr[3];
        if (f != 0.0f) {
            return (float) Math.sqrt(Math.pow(f, 2.0d) + Math.pow(this.single[4], 2.0d));
        }
        return fArr[4];
    }

    public float getScaleX() {
        return this.single[0];
    }

    public float getShearY() {
        return this.single[1];
    }

    public float getShearX() {
        return this.single[3];
    }

    public float getScaleY() {
        return this.single[4];
    }

    public float getTranslateX() {
        return this.single[6];
    }

    public float getTranslateY() {
        return this.single[7];
    }

    @Deprecated
    public float getXPosition() {
        return this.single[6];
    }

    @Deprecated
    public float getYPosition() {
        return this.single[7];
    }

    public COSArray toCOSArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) new COSFloat(this.single[0]));
        cOSArray.add((COSBase) new COSFloat(this.single[1]));
        cOSArray.add((COSBase) new COSFloat(this.single[3]));
        cOSArray.add((COSBase) new COSFloat(this.single[4]));
        cOSArray.add((COSBase) new COSFloat(this.single[6]));
        cOSArray.add((COSBase) new COSFloat(this.single[7]));
        return cOSArray;
    }

    public String toString() {
        return "[" + this.single[0] + "," + this.single[1] + "," + this.single[3] + "," + this.single[4] + "," + this.single[6] + "," + this.single[7] + "]";
    }

    public int hashCode() {
        return Arrays.hashCode(this.single);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Arrays.equals(this.single, ((Matrix) obj).single);
        }
        return false;
    }
}
