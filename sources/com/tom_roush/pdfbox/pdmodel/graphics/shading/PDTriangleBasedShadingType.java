package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.harmony.javax.imageio.stream.ImageInputStream;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class PDTriangleBasedShadingType extends PDShading {
    private int bitsPerColorComponent;
    private int bitsPerCoordinate;
    private COSArray decode;
    private int numberOfColorComponents;

    abstract List<ShadedTriangle> collectTriangles(AffineTransform affineTransform, Matrix matrix) throws IOException;

    public float interpolate(float f, long j, float f2, float f3) {
        return f2 + ((f * (f3 - f2)) / ((float) j));
    }

    public PDTriangleBasedShadingType(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.decode = null;
        this.bitsPerCoordinate = -1;
        this.bitsPerColorComponent = -1;
        this.numberOfColorComponents = -1;
    }

    public int getBitsPerComponent() {
        if (this.bitsPerColorComponent == -1) {
            this.bitsPerColorComponent = getCOSObject().getInt(COSName.BITS_PER_COMPONENT, -1);
            Log.d("PdfBox-Android", "bitsPerColorComponent: " + this.bitsPerColorComponent);
        }
        return this.bitsPerColorComponent;
    }

    public void setBitsPerComponent(int i) {
        getCOSObject().setInt(COSName.BITS_PER_COMPONENT, i);
        this.bitsPerColorComponent = i;
    }

    public int getBitsPerCoordinate() {
        if (this.bitsPerCoordinate == -1) {
            this.bitsPerCoordinate = getCOSObject().getInt(COSName.BITS_PER_COORDINATE, -1);
            Log.d("PdfBox-Android", "bitsPerCoordinate: " + (Math.pow(2.0d, this.bitsPerCoordinate) - 1.0d));
        }
        return this.bitsPerCoordinate;
    }

    public void setBitsPerCoordinate(int i) {
        getCOSObject().setInt(COSName.BITS_PER_COORDINATE, i);
        this.bitsPerCoordinate = i;
    }

    public int getNumberOfColorComponents() throws IOException {
        if (this.numberOfColorComponents == -1) {
            this.numberOfColorComponents = getFunction() != null ? 1 : getColorSpace().getNumberOfComponents();
            Log.d("PdfBox-Android", "numberOfColorComponents: " + this.numberOfColorComponents);
        }
        return this.numberOfColorComponents;
    }

    private COSArray getDecodeValues() {
        if (this.decode == null) {
            this.decode = (COSArray) getCOSObject().getDictionaryObject(COSName.DECODE);
        }
        return this.decode;
    }

    public void setDecodeValues(COSArray cOSArray) {
        this.decode = cOSArray;
        getCOSObject().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    public PDRange getDecodeForParameter(int i) {
        COSArray decodeValues = getDecodeValues();
        if (decodeValues == null || decodeValues.size() < (i * 2) + 1) {
            return null;
        }
        return new PDRange(decodeValues, i);
    }

    public Vertex readVertex(ImageInputStream imageInputStream, long j, long j2, PDRange pDRange, PDRange pDRange2, PDRange[] pDRangeArr, Matrix matrix, AffineTransform affineTransform) throws IOException {
        float[] fArr = new float[this.numberOfColorComponents];
        long readBits = imageInputStream.readBits(this.bitsPerCoordinate);
        long readBits2 = imageInputStream.readBits(this.bitsPerCoordinate);
        float interpolate = interpolate((float) readBits, j, pDRange.getMin(), pDRange.getMax());
        float interpolate2 = interpolate((float) readBits2, j, pDRange2.getMin(), pDRange2.getMax());
        Log.d("PdfBox-Android", "coord: " + String.format("[%06X,%06X] -> [%f,%f]", Long.valueOf(readBits), Long.valueOf(readBits2), Float.valueOf(interpolate), Float.valueOf(interpolate2)));
        PointF transformPoint = matrix.transformPoint(interpolate, interpolate2);
        affineTransform.transform(transformPoint, transformPoint);
        for (int i = 0; i < this.numberOfColorComponents; i++) {
            int readBits3 = (int) imageInputStream.readBits(this.bitsPerColorComponent);
            fArr[i] = interpolate(readBits3, j2, pDRangeArr[i].getMin(), pDRangeArr[i].getMax());
            Log.d("PdfBox-Android", "color[" + i + "]: " + readBits3 + RemoteSettings.FORWARD_SLASH_STRING + String.format("%02x", Integer.valueOf(readBits3)) + "-> color[" + i + "]: " + fArr[i]);
        }
        int bitOffset = imageInputStream.getBitOffset();
        if (bitOffset != 0) {
            imageInputStream.readBits(8 - bitOffset);
        }
        return new Vertex(transformPoint, fArr);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public RectF getBounds(AffineTransform affineTransform, Matrix matrix) throws IOException {
        RectF rectF = null;
        for (ShadedTriangle shadedTriangle : collectTriangles(affineTransform, matrix)) {
            if (rectF == null) {
                rectF = new RectF(shadedTriangle.corner[0].x, shadedTriangle.corner[0].y, 0.0f, 0.0f);
            }
            rectF.union(shadedTriangle.corner[0].x, shadedTriangle.corner[0].y);
            rectF.union(shadedTriangle.corner[1].x, shadedTriangle.corner[1].y);
            rectF.union(shadedTriangle.corner[2].x, shadedTriangle.corner[2].y);
        }
        return rectF == null ? new RectF() : rectF;
    }
}
