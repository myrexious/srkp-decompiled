package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class PDShadingType4 extends PDTriangleBasedShadingType {
    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 4;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ int getBitsPerComponent() {
        return super.getBitsPerComponent();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ int getBitsPerCoordinate() {
        return super.getBitsPerCoordinate();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public /* bridge */ /* synthetic */ RectF getBounds(AffineTransform affineTransform, Matrix matrix) throws IOException {
        return super.getBounds(affineTransform, matrix);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ PDRange getDecodeForParameter(int i) {
        return super.getDecodeForParameter(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ int getNumberOfColorComponents() throws IOException {
        return super.getNumberOfColorComponents();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ void setBitsPerComponent(int i) {
        super.setBitsPerComponent(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ void setBitsPerCoordinate(int i) {
        super.setBitsPerCoordinate(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ void setDecodeValues(COSArray cOSArray) {
        super.setDecodeValues(cOSArray);
    }

    public PDShadingType4(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getBitsPerFlag() {
        return getCOSObject().getInt(COSName.BITS_PER_FLAG, -1);
    }

    public void setBitsPerFlag(int i) {
        getCOSObject().setInt(COSName.BITS_PER_FLAG, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    List<ShadedTriangle> collectTriangles(AffineTransform affineTransform, Matrix matrix) throws IOException {
        byte b;
        MemoryCacheImageInputStream memoryCacheImageInputStream;
        ArrayList arrayList;
        PDRange[] pDRangeArr;
        byte b2;
        Vertex readVertex;
        ArrayList arrayList2;
        char c;
        PointF pointF;
        char c2;
        float[] fArr;
        int bitsPerFlag = getBitsPerFlag();
        COSDictionary cOSObject = getCOSObject();
        if (!(cOSObject instanceof COSStream)) {
            return Collections.emptyList();
        }
        char c3 = 0;
        PDRange decodeForParameter = getDecodeForParameter(0);
        byte b3 = 1;
        PDRange decodeForParameter2 = getDecodeForParameter(1);
        if (decodeForParameter == null || decodeForParameter2 == null || Float.compare(decodeForParameter.getMin(), decodeForParameter.getMax()) == 0 || Float.compare(decodeForParameter2.getMin(), decodeForParameter2.getMax()) == 0) {
            return Collections.emptyList();
        }
        int numberOfColorComponents = getNumberOfColorComponents();
        PDRange[] pDRangeArr2 = new PDRange[numberOfColorComponents];
        for (int i = 0; i < numberOfColorComponents; i++) {
            PDRange decodeForParameter3 = getDecodeForParameter(i + 2);
            pDRangeArr2[i] = decodeForParameter3;
            if (decodeForParameter3 == null) {
                throw new IOException("Range missing in shading /Decode entry");
            }
        }
        ArrayList arrayList3 = new ArrayList();
        long pow = ((long) Math.pow(2.0d, getBitsPerCoordinate())) - 1;
        long pow2 = ((long) Math.pow(2.0d, getBitsPerComponent())) - 1;
        MemoryCacheImageInputStream memoryCacheImageInputStream2 = new MemoryCacheImageInputStream(((COSStream) cOSObject).createInputStream());
        try {
            try {
                b = (byte) (memoryCacheImageInputStream2.readBits(bitsPerFlag) & 3);
            } catch (EOFException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
                b = 0;
            }
            byte b4 = 0;
            while (b4 == 0) {
                if (b != 0) {
                    if (b == b3 || b == 2) {
                        try {
                            int size = arrayList3.size() - b3;
                            if (size < 0) {
                                Log.e("PdfBox-Android", "broken data stream: " + arrayList3.size());
                            } else {
                                ShadedTriangle shadedTriangle = (ShadedTriangle) arrayList3.get(size);
                                MemoryCacheImageInputStream memoryCacheImageInputStream3 = memoryCacheImageInputStream2;
                                ArrayList arrayList4 = arrayList3;
                                pDRangeArr = pDRangeArr2;
                                try {
                                    try {
                                        Vertex readVertex2 = readVertex(memoryCacheImageInputStream2, pow, pow2, decodeForParameter, decodeForParameter2, pDRangeArr2, matrix, affineTransform);
                                        PointF[] pointFArr = new PointF[3];
                                        if (b == 1) {
                                            try {
                                                pointF = shadedTriangle.corner[1];
                                                c2 = 0;
                                            } catch (EOFException unused) {
                                                memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                                arrayList2 = arrayList4;
                                                c = 0;
                                                b2 = 1;
                                                arrayList3 = arrayList2;
                                                c3 = c;
                                                memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                                                b3 = b2;
                                                b4 = b3;
                                                pDRangeArr2 = pDRangeArr;
                                            }
                                        } else {
                                            c2 = 0;
                                            try {
                                                pointF = shadedTriangle.corner[0];
                                            } catch (EOFException unused2) {
                                                c = c2;
                                                memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                                arrayList2 = arrayList4;
                                                b2 = 1;
                                                arrayList3 = arrayList2;
                                                c3 = c;
                                                memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                                                b3 = b2;
                                                b4 = b3;
                                                pDRangeArr2 = pDRangeArr;
                                            }
                                        }
                                        pointFArr[c2] = pointF;
                                        pointFArr[1] = shadedTriangle.corner[2];
                                        pointFArr[2] = readVertex2.point;
                                        float[][] fArr2 = new float[3];
                                        if (b == 1) {
                                            fArr = shadedTriangle.color[1];
                                            c = 0;
                                        } else {
                                            c = 0;
                                            try {
                                                fArr = shadedTriangle.color[0];
                                            } catch (EOFException unused3) {
                                                memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                                arrayList2 = arrayList4;
                                                b2 = 1;
                                                arrayList3 = arrayList2;
                                                c3 = c;
                                                memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                                                b3 = b2;
                                                b4 = b3;
                                                pDRangeArr2 = pDRangeArr;
                                            }
                                        }
                                        fArr2[c] = fArr;
                                        fArr2[1] = shadedTriangle.color[2];
                                        fArr2[2] = readVertex2.color;
                                        arrayList = arrayList4;
                                        try {
                                            arrayList.add(new ShadedTriangle(pointFArr, fArr2));
                                            try {
                                                b = (byte) (memoryCacheImageInputStream3.readBits(bitsPerFlag) & 3);
                                                memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                                b2 = 1;
                                                arrayList2 = arrayList;
                                                c = 0;
                                            } catch (EOFException unused4) {
                                                memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                                b2 = 1;
                                                arrayList2 = arrayList;
                                                c = 0;
                                                arrayList3 = arrayList2;
                                                c3 = c;
                                                memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                                                b3 = b2;
                                                b4 = b3;
                                                pDRangeArr2 = pDRangeArr;
                                            } catch (Throwable th) {
                                                th = th;
                                                memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                                memoryCacheImageInputStream.close();
                                                throw th;
                                            }
                                        } catch (EOFException unused5) {
                                            b2 = 1;
                                            arrayList2 = arrayList;
                                            memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                            c = 0;
                                            arrayList3 = arrayList2;
                                            c3 = c;
                                            memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                                            b3 = b2;
                                            b4 = b3;
                                            pDRangeArr2 = pDRangeArr;
                                        }
                                    } catch (EOFException unused6) {
                                        memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                        arrayList2 = arrayList4;
                                        c = 0;
                                        b2 = 1;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    memoryCacheImageInputStream = memoryCacheImageInputStream3;
                                }
                            }
                        } catch (EOFException unused7) {
                            pDRangeArr = pDRangeArr2;
                            memoryCacheImageInputStream = memoryCacheImageInputStream2;
                            arrayList2 = arrayList3;
                            b2 = b3;
                            c = c3;
                            arrayList3 = arrayList2;
                            c3 = c;
                            memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                            b3 = b2;
                            b4 = b3;
                            pDRangeArr2 = pDRangeArr;
                        }
                    } else {
                        try {
                            Log.w("PdfBox-Android", "bad flag: " + ((int) b));
                        } catch (EOFException unused8) {
                            memoryCacheImageInputStream = memoryCacheImageInputStream2;
                            arrayList2 = arrayList3;
                            pDRangeArr = pDRangeArr2;
                            b2 = b3;
                            c = c3;
                            arrayList3 = arrayList2;
                            c3 = c;
                            memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                            b3 = b2;
                            b4 = b3;
                            pDRangeArr2 = pDRangeArr;
                        }
                    }
                    memoryCacheImageInputStream = memoryCacheImageInputStream2;
                    arrayList2 = arrayList3;
                    pDRangeArr = pDRangeArr2;
                    b2 = b3;
                    c = c3;
                } else {
                    arrayList = arrayList3;
                    pDRangeArr = pDRangeArr2;
                    MemoryCacheImageInputStream memoryCacheImageInputStream4 = memoryCacheImageInputStream2;
                    memoryCacheImageInputStream = memoryCacheImageInputStream4;
                    b2 = b3;
                    try {
                        try {
                            readVertex = readVertex(memoryCacheImageInputStream4, pow, pow2, decodeForParameter, decodeForParameter2, pDRangeArr, matrix, affineTransform);
                            b = (byte) (memoryCacheImageInputStream.readBits(bitsPerFlag) & 3);
                            if (b != 0) {
                                Log.e("PdfBox-Android", "bad triangle: " + ((int) b));
                            }
                        } catch (EOFException unused9) {
                            arrayList2 = arrayList;
                            c = 0;
                            arrayList3 = arrayList2;
                            c3 = c;
                            memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                            b3 = b2;
                            b4 = b3;
                            pDRangeArr2 = pDRangeArr;
                        }
                        try {
                            Vertex readVertex3 = readVertex(memoryCacheImageInputStream, pow, pow2, decodeForParameter, decodeForParameter2, pDRangeArr, matrix, affineTransform);
                            memoryCacheImageInputStream.readBits(bitsPerFlag);
                            if (b != 0) {
                                Log.e("PdfBox-Android", "bad triangle: " + ((int) b));
                            }
                            Vertex readVertex4 = readVertex(memoryCacheImageInputStream, pow, pow2, decodeForParameter, decodeForParameter2, pDRangeArr, matrix, affineTransform);
                            PointF[] pointFArr2 = new PointF[3];
                            c = 0;
                            try {
                                pointFArr2[0] = readVertex.point;
                                pointFArr2[b2] = readVertex3.point;
                                pointFArr2[2] = readVertex4.point;
                                float[][] fArr3 = new float[3];
                                c = 0;
                                fArr3[0] = readVertex.color;
                                fArr3[b2] = readVertex3.color;
                                fArr3[2] = readVertex4.color;
                                arrayList2 = arrayList;
                                try {
                                    arrayList2.add(new ShadedTriangle(pointFArr2, fArr3));
                                    b = (byte) (memoryCacheImageInputStream.readBits(bitsPerFlag) & 3);
                                } catch (EOFException unused10) {
                                    arrayList3 = arrayList2;
                                    c3 = c;
                                    memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                                    b3 = b2;
                                    b4 = b3;
                                    pDRangeArr2 = pDRangeArr;
                                }
                            } catch (EOFException unused11) {
                                arrayList2 = arrayList;
                            }
                        } catch (EOFException unused12) {
                            arrayList2 = arrayList;
                            c = 0;
                            arrayList3 = arrayList2;
                            c3 = c;
                            memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                            b3 = b2;
                            b4 = b3;
                            pDRangeArr2 = pDRangeArr;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        memoryCacheImageInputStream.close();
                        throw th;
                    }
                }
                arrayList3 = arrayList2;
                c3 = c;
                memoryCacheImageInputStream2 = memoryCacheImageInputStream;
                b3 = b2;
                pDRangeArr2 = pDRangeArr;
            }
            ArrayList arrayList5 = arrayList3;
            memoryCacheImageInputStream2.close();
            return arrayList5;
        } catch (Throwable th4) {
            th = th4;
            memoryCacheImageInputStream = memoryCacheImageInputStream2;
            memoryCacheImageInputStream.close();
            throw th;
        }
    }
}
