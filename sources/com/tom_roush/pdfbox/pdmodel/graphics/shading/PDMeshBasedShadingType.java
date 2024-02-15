package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.harmony.javax.imageio.stream.ImageInputStream;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class PDMeshBasedShadingType extends PDShadingType4 {
    abstract Patch generatePatch(PointF[] pointFArr, float[][] fArr);

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShadingType4, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public abstract RectF getBounds(AffineTransform affineTransform, Matrix matrix) throws IOException;

    public PDMeshBasedShadingType(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v16, types: [com.tom_roush.pdfbox.pdmodel.common.PDRange] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.tom_roush.harmony.javax.imageio.stream.ImageInputStream] */
    /* JADX WARN: Type inference failed for: r3v8 */
    final List<Patch> collectPatches(AffineTransform affineTransform, Matrix matrix, int i) throws IOException {
        ?? r3;
        ArrayList arrayList;
        MemoryCacheImageInputStream memoryCacheImageInputStream;
        ArrayList arrayList2;
        MemoryCacheImageInputStream memoryCacheImageInputStream2;
        String str;
        boolean z;
        Patch readPatch;
        byte readBits;
        PointF[] pointFArr;
        float[][] flag1Color;
        String str2 = "PdfBox-Android";
        COSDictionary cOSObject = getCOSObject();
        if (!(cOSObject instanceof COSStream)) {
            return Collections.emptyList();
        }
        boolean z2 = false;
        PDRange decodeForParameter = getDecodeForParameter(0);
        boolean z3 = true;
        PDRange decodeForParameter2 = getDecodeForParameter(1);
        if (Float.compare(decodeForParameter.getMin(), decodeForParameter.getMax()) == 0 || Float.compare(decodeForParameter2.getMin(), decodeForParameter2.getMax()) == 0) {
            return Collections.emptyList();
        }
        int bitsPerFlag = getBitsPerFlag();
        int numberOfColorComponents = getNumberOfColorComponents();
        PDRange[] pDRangeArr = new PDRange[numberOfColorComponents];
        for (int i2 = 0; i2 < numberOfColorComponents; i2++) {
            r3 = getDecodeForParameter(i2 + 2);
            pDRangeArr[i2] = r3;
            if (r3 == 0) {
                throw new IOException("Range missing in shading /Decode entry");
            }
        }
        ArrayList arrayList3 = new ArrayList();
        long pow = ((long) Math.pow(2.0d, getBitsPerCoordinate())) - 1;
        long pow2 = ((long) Math.pow(2.0d, getBitsPerComponent())) - 1;
        MemoryCacheImageInputStream memoryCacheImageInputStream3 = new MemoryCacheImageInputStream(((COSStream) cOSObject).createInputStream());
        try {
            PointF[] pointFArr2 = new PointF[4];
            char c = 2;
            try {
                try {
                    float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 2, numberOfColorComponents);
                    byte readBits2 = (byte) (memoryCacheImageInputStream3.readBits(bitsPerFlag) & 3);
                    boolean z4 = false;
                    while (true) {
                        if (z4) {
                            arrayList = arrayList3;
                            memoryCacheImageInputStream = memoryCacheImageInputStream3;
                            break;
                        }
                        PointF[] pointFArr3 = pointFArr2;
                        PointF[] pointFArr4 = pointFArr2;
                        ArrayList arrayList4 = arrayList3;
                        MemoryCacheImageInputStream memoryCacheImageInputStream4 = memoryCacheImageInputStream3;
                        PDRange[] pDRangeArr2 = pDRangeArr;
                        int i3 = bitsPerFlag;
                        boolean z5 = z2;
                        String str3 = str2;
                        try {
                            try {
                                readPatch = readPatch(memoryCacheImageInputStream3, readBits2 == 0 ? z3 : z2, pointFArr3, fArr, pow, pow2, decodeForParameter, decodeForParameter2, pDRangeArr2, matrix, affineTransform, i);
                            } catch (EOFException unused) {
                                arrayList2 = arrayList4;
                            }
                            if (readPatch == null) {
                                arrayList = arrayList4;
                                memoryCacheImageInputStream = memoryCacheImageInputStream4;
                                break;
                            }
                            arrayList2 = arrayList4;
                            try {
                                arrayList2.add(readPatch);
                                memoryCacheImageInputStream2 = memoryCacheImageInputStream4;
                            } catch (EOFException unused2) {
                                memoryCacheImageInputStream2 = memoryCacheImageInputStream4;
                                str = str3;
                                z = true;
                                c = 2;
                                bitsPerFlag = i3;
                                arrayList3 = arrayList2;
                                memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                                z3 = z;
                                z4 = z3;
                                str2 = str;
                                pointFArr2 = pointFArr4;
                                pDRangeArr = pDRangeArr2;
                                z2 = z5;
                            }
                            try {
                                readBits = (byte) (memoryCacheImageInputStream2.readBits(i3) & 3);
                            } catch (EOFException unused3) {
                                str = str3;
                                z = true;
                                c = 2;
                                bitsPerFlag = i3;
                                arrayList3 = arrayList2;
                                memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                                z3 = z;
                                z4 = z3;
                                str2 = str;
                                pointFArr2 = pointFArr4;
                                pDRangeArr = pDRangeArr2;
                                z2 = z5;
                            }
                            if (readBits != 0) {
                                z = true;
                                if (readBits != 1) {
                                    c = 2;
                                    if (readBits != 2) {
                                        if (readBits == 3) {
                                            str = str3;
                                            pointFArr = readPatch.getFlag3Edge();
                                            try {
                                                flag1Color = readPatch.getFlag3Color();
                                            } catch (EOFException unused4) {
                                                readBits2 = readBits;
                                                pointFArr4 = pointFArr;
                                            }
                                        } else {
                                            try {
                                                str = str3;
                                                try {
                                                    Log.w(str, "bad flag: " + ((int) readBits));
                                                } catch (EOFException unused5) {
                                                    readBits2 = readBits;
                                                    bitsPerFlag = i3;
                                                    arrayList3 = arrayList2;
                                                    memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                                                    z3 = z;
                                                    z4 = z3;
                                                    str2 = str;
                                                    pointFArr2 = pointFArr4;
                                                    pDRangeArr = pDRangeArr2;
                                                    z2 = z5;
                                                }
                                            } catch (EOFException unused6) {
                                                str = str3;
                                            }
                                        }
                                        bitsPerFlag = i3;
                                        arrayList3 = arrayList2;
                                        memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                                        z3 = z;
                                        z4 = z3;
                                        str2 = str;
                                        pointFArr2 = pointFArr4;
                                        pDRangeArr = pDRangeArr2;
                                        z2 = z5;
                                    } else {
                                        str = str3;
                                        pointFArr = readPatch.getFlag2Edge();
                                        flag1Color = readPatch.getFlag2Color();
                                    }
                                } else {
                                    str = str3;
                                    c = 2;
                                    pointFArr = readPatch.getFlag1Edge();
                                    flag1Color = readPatch.getFlag1Color();
                                }
                                fArr = flag1Color;
                                bitsPerFlag = i3;
                                memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                                readBits2 = readBits;
                                z3 = z;
                                str2 = str;
                                pointFArr2 = pointFArr;
                                pDRangeArr = pDRangeArr2;
                                z2 = z5;
                                arrayList3 = arrayList2;
                            } else {
                                str = str3;
                                z = true;
                                c = 2;
                            }
                            pointFArr = pointFArr4;
                            bitsPerFlag = i3;
                            memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                            readBits2 = readBits;
                            z3 = z;
                            str2 = str;
                            pointFArr2 = pointFArr;
                            pDRangeArr = pDRangeArr2;
                            z2 = z5;
                            arrayList3 = arrayList2;
                        } catch (Throwable th) {
                            th = th;
                            r3 = memoryCacheImageInputStream4;
                            r3.close();
                            throw th;
                        }
                    }
                    memoryCacheImageInputStream.close();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (EOFException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
                memoryCacheImageInputStream3.close();
                return arrayList3;
            }
        } catch (Throwable th3) {
            th = th3;
            r3 = memoryCacheImageInputStream3;
        }
    }

    protected Patch readPatch(ImageInputStream imageInputStream, boolean z, PointF[] pointFArr, float[][] fArr, long j, long j2, PDRange pDRange, PDRange pDRange2, PDRange[] pDRangeArr, Matrix matrix, AffineTransform affineTransform, int i) throws IOException {
        int i2;
        int numberOfColorComponents = getNumberOfColorComponents();
        int i3 = 2;
        float[][] fArr2 = (float[][]) Array.newInstance(Float.TYPE, 4, numberOfColorComponents);
        PointF[] pointFArr2 = new PointF[i];
        if (z) {
            i3 = 0;
            i2 = 0;
        } else {
            pointFArr2[0] = pointFArr[0];
            pointFArr2[1] = pointFArr[1];
            pointFArr2[2] = pointFArr[2];
            pointFArr2[3] = pointFArr[3];
            for (int i4 = 0; i4 < numberOfColorComponents; i4++) {
                fArr2[0][i4] = fArr[0][i4];
                fArr2[1][i4] = fArr[1][i4];
            }
            i2 = 4;
        }
        while (i2 < i) {
            try {
                PointF transformPoint = matrix.transformPoint(interpolate((float) imageInputStream.readBits(getBitsPerCoordinate()), j, pDRange.getMin(), pDRange.getMax()), interpolate((float) imageInputStream.readBits(getBitsPerCoordinate()), j, pDRange2.getMin(), pDRange2.getMax()));
                affineTransform.transform(transformPoint, transformPoint);
                pointFArr2[i2] = transformPoint;
                i2++;
            } catch (EOFException e) {
                Log.d("PdfBox-Android", "EOF", e);
                return null;
            }
        }
        while (i3 < 4) {
            for (int i5 = 0; i5 < numberOfColorComponents; i5++) {
                fArr2[i3][i5] = interpolate((float) imageInputStream.readBits(getBitsPerComponent()), j2, pDRangeArr[i5].getMin(), pDRangeArr[i5].getMax());
            }
            i3++;
        }
        return generatePatch(pointFArr2, fArr2);
    }

    public RectF getBounds(AffineTransform affineTransform, Matrix matrix, int i) throws IOException {
        RectF rectF = null;
        for (Patch patch : collectPatches(affineTransform, matrix, i)) {
            for (ShadedTriangle shadedTriangle : patch.listOfTriangles) {
                if (rectF == null) {
                    rectF = new RectF(shadedTriangle.corner[0].x, shadedTriangle.corner[0].y, 0.0f, 0.0f);
                }
                rectF.union(shadedTriangle.corner[0].x, shadedTriangle.corner[0].y);
                rectF.union(shadedTriangle.corner[1].x, shadedTriangle.corner[1].y);
                rectF.union(shadedTriangle.corner[2].x, shadedTriangle.corner[2].y);
            }
        }
        return rectF;
    }
}
