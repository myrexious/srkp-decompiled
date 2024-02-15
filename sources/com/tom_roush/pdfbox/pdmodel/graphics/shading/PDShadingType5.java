package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import android.graphics.RectF;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class PDShadingType5 extends PDTriangleBasedShadingType {
    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 5;
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

    public PDShadingType5(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getVerticesPerRow() {
        return getCOSObject().getInt(COSName.VERTICES_PER_ROW, -1);
    }

    public void setVerticesPerRow(int i) {
        getCOSObject().setInt(COSName.VERTICES_PER_ROW, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    List<ShadedTriangle> collectTriangles(AffineTransform affineTransform, Matrix matrix) throws IOException {
        COSDictionary cOSObject = getCOSObject();
        if (!(cOSObject instanceof COSStream)) {
            return Collections.emptyList();
        }
        PDRange decodeForParameter = getDecodeForParameter(0);
        PDRange decodeForParameter2 = getDecodeForParameter(1);
        if (decodeForParameter == null || decodeForParameter2 == null || Float.compare(decodeForParameter.getMin(), decodeForParameter.getMax()) == 0 || Float.compare(decodeForParameter2.getMin(), decodeForParameter2.getMax()) == 0) {
            return Collections.emptyList();
        }
        int verticesPerRow = getVerticesPerRow();
        int numberOfColorComponents = getNumberOfColorComponents();
        PDRange[] pDRangeArr = new PDRange[numberOfColorComponents];
        for (int i = 0; i < numberOfColorComponents; i++) {
            PDRange decodeForParameter3 = getDecodeForParameter(i + 2);
            pDRangeArr[i] = decodeForParameter3;
            if (decodeForParameter3 == null) {
                throw new IOException("Range missing in shading /Decode entry");
            }
        }
        ArrayList arrayList = new ArrayList();
        long pow = ((long) Math.pow(2.0d, getBitsPerCoordinate())) - 1;
        long pow2 = ((long) Math.pow(2.0d, getBitsPerComponent())) - 1;
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(((COSStream) cOSObject).createInputStream());
        boolean z = false;
        while (!z) {
            MemoryCacheImageInputStream memoryCacheImageInputStream2 = memoryCacheImageInputStream;
            ArrayList arrayList2 = arrayList;
            PDRange[] pDRangeArr2 = pDRangeArr;
            int i2 = verticesPerRow;
            try {
                arrayList2.add(readVertex(memoryCacheImageInputStream, pow, pow2, decodeForParameter, decodeForParameter2, pDRangeArr, matrix, affineTransform));
                arrayList = arrayList2;
                verticesPerRow = i2;
                memoryCacheImageInputStream = memoryCacheImageInputStream2;
                pDRangeArr = pDRangeArr2;
            } catch (EOFException unused) {
                arrayList = arrayList2;
                verticesPerRow = i2;
                memoryCacheImageInputStream = memoryCacheImageInputStream2;
                pDRangeArr = pDRangeArr2;
                z = true;
            } catch (Throwable th) {
                memoryCacheImageInputStream2.close();
                throw th;
            }
        }
        ArrayList arrayList3 = arrayList;
        int i3 = verticesPerRow;
        memoryCacheImageInputStream.close();
        int size = arrayList3.size() / i3;
        if (size < 2) {
            return Collections.emptyList();
        }
        Vertex[][] vertexArr = (Vertex[][]) Array.newInstance(Vertex.class, size, i3);
        for (int i4 = 0; i4 < size; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                vertexArr[i4][i5] = (Vertex) arrayList3.get((i4 * i3) + i5);
            }
        }
        return createShadedTriangleList(size, i3, vertexArr);
    }

    private List<ShadedTriangle> createShadedTriangleList(int i, int i2, Vertex[][] vertexArr) {
        PointF[] pointFArr = new PointF[3];
        float[][] fArr = new float[3];
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i - 1; i3++) {
            int i4 = 0;
            while (i4 < i2 - 1) {
                pointFArr[0] = vertexArr[i3][i4].point;
                int i5 = i4 + 1;
                pointFArr[1] = vertexArr[i3][i5].point;
                int i6 = i3 + 1;
                pointFArr[2] = vertexArr[i6][i4].point;
                fArr[0] = vertexArr[i3][i4].color;
                fArr[1] = vertexArr[i3][i5].color;
                fArr[2] = vertexArr[i6][i4].color;
                arrayList.add(new ShadedTriangle(pointFArr, fArr));
                pointFArr[0] = vertexArr[i3][i5].point;
                pointFArr[1] = vertexArr[i6][i4].point;
                pointFArr[2] = vertexArr[i6][i5].point;
                fArr[0] = vertexArr[i3][i5].color;
                fArr[1] = vertexArr[i6][i4].color;
                fArr[2] = vertexArr[i6][i5].color;
                arrayList.add(new ShadedTriangle(pointFArr, fArr));
                i4 = i5;
            }
        }
        return arrayList;
    }
}
