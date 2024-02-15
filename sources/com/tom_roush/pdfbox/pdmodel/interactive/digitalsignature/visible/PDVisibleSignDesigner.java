package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public class PDVisibleSignDesigner {
    private Bitmap image;
    private Float imageHeight;
    private float imageSizeInPercents;
    private Float imageWidth;
    private float pageHeight;
    private float pageWidth;
    private float xAxis;
    private float yAxis;
    private String signatureFieldName = "sig";
    private byte[] formatterRectangleParams = {0, 0, BuiltinOptions.SegmentSumOptions, 50};
    private int[] formatterRectangleParameters = {0, 0, 100, 50};
    private AffineTransform affineTransform = new AffineTransform();
    private int rotation = 0;

    public PDVisibleSignDesigner(String str, InputStream inputStream, int i) throws IOException {
        readImageStream(inputStream);
        calculatePageSizeFromFile(str, i);
    }

    public PDVisibleSignDesigner(InputStream inputStream, InputStream inputStream2, int i) throws IOException {
        readImageStream(inputStream2);
        calculatePageSizeFromStream(inputStream, i);
    }

    public PDVisibleSignDesigner(PDDocument pDDocument, InputStream inputStream, int i) throws IOException {
        readImageStream(inputStream);
        calculatePageSize(pDDocument, i);
    }

    public PDVisibleSignDesigner(String str, Bitmap bitmap, int i) throws IOException {
        setImage(bitmap);
        calculatePageSizeFromFile(str, i);
    }

    public PDVisibleSignDesigner(InputStream inputStream, Bitmap bitmap, int i) throws IOException {
        setImage(bitmap);
        calculatePageSizeFromStream(inputStream, i);
    }

    public PDVisibleSignDesigner(PDDocument pDDocument, Bitmap bitmap, int i) {
        setImage(bitmap);
        calculatePageSize(pDDocument, i);
    }

    public PDVisibleSignDesigner(InputStream inputStream) throws IOException {
        readImageStream(inputStream);
    }

    private void calculatePageSizeFromFile(String str, int i) throws IOException {
        PDDocument load = PDDocument.load(new File(str));
        calculatePageSize(load, i);
        load.close();
    }

    private void calculatePageSizeFromStream(InputStream inputStream, int i) throws IOException {
        PDDocument load = PDDocument.load(inputStream);
        calculatePageSize(load, i);
        load.close();
    }

    private void calculatePageSize(PDDocument pDDocument, int i) {
        if (i < 1) {
            throw new IllegalArgumentException("First page of pdf is 1, not " + i);
        }
        PDPage page = pDDocument.getPage(i - 1);
        PDRectangle mediaBox = page.getMediaBox();
        pageHeight(mediaBox.getHeight());
        this.pageWidth = mediaBox.getWidth();
        this.imageSizeInPercents = 100.0f;
        this.rotation = page.getRotation() % 360;
    }

    public PDVisibleSignDesigner adjustForRotation() {
        int i = this.rotation;
        if (i == 90) {
            float f = this.yAxis;
            this.yAxis = (this.pageHeight - this.xAxis) - this.imageWidth.floatValue();
            this.xAxis = f;
            this.affineTransform = new AffineTransform(0.0f, this.imageHeight.floatValue() / this.imageWidth.floatValue(), (-this.imageWidth.floatValue()) / this.imageHeight.floatValue(), 0.0f, this.imageWidth.floatValue(), 0.0f);
            float floatValue = this.imageHeight.floatValue();
            this.imageHeight = this.imageWidth;
            this.imageWidth = Float.valueOf(floatValue);
        } else if (i == 180) {
            this.xAxis = (this.pageWidth - this.xAxis) - this.imageWidth.floatValue();
            this.yAxis = (this.pageHeight - this.yAxis) - this.imageHeight.floatValue();
            this.affineTransform = new AffineTransform(-1.0f, 0.0f, 0.0f, -1.0f, this.imageWidth.floatValue(), this.imageHeight.floatValue());
        } else if (i == 270) {
            float f2 = this.xAxis;
            this.xAxis = (this.pageWidth - this.yAxis) - this.imageHeight.floatValue();
            this.yAxis = f2;
            this.affineTransform = new AffineTransform(0.0f, (-this.imageHeight.floatValue()) / this.imageWidth.floatValue(), this.imageWidth.floatValue() / this.imageHeight.floatValue(), 0.0f, 0.0f, this.imageHeight.floatValue());
            float floatValue2 = this.imageHeight.floatValue();
            this.imageHeight = this.imageWidth;
            this.imageWidth = Float.valueOf(floatValue2);
        }
        return this;
    }

    public PDVisibleSignDesigner signatureImage(String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        } catch (Throwable th) {
            th = th;
        }
        try {
            readImageStream(bufferedInputStream);
            IOUtils.closeQuietly(bufferedInputStream);
            return this;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            IOUtils.closeQuietly(bufferedInputStream2);
            throw th;
        }
    }

    public PDVisibleSignDesigner zoom(float f) {
        this.imageHeight = Float.valueOf(this.imageHeight.floatValue() + ((this.imageHeight.floatValue() * f) / 100.0f));
        Float valueOf = Float.valueOf(this.imageWidth.floatValue() + ((this.imageWidth.floatValue() * f) / 100.0f));
        this.imageWidth = valueOf;
        this.formatterRectangleParameters[2] = (int) valueOf.floatValue();
        this.formatterRectangleParameters[3] = (int) this.imageHeight.floatValue();
        return this;
    }

    public PDVisibleSignDesigner coordinates(float f, float f2) {
        xAxis(f);
        yAxis(f2);
        return this;
    }

    public float getxAxis() {
        return this.xAxis;
    }

    public PDVisibleSignDesigner xAxis(float f) {
        this.xAxis = f;
        return this;
    }

    public float getyAxis() {
        return this.yAxis;
    }

    public PDVisibleSignDesigner yAxis(float f) {
        this.yAxis = f;
        return this;
    }

    public float getWidth() {
        return this.imageWidth.floatValue();
    }

    public PDVisibleSignDesigner width(float f) {
        this.imageWidth = Float.valueOf(f);
        this.formatterRectangleParameters[2] = (int) f;
        return this;
    }

    public float getHeight() {
        return this.imageHeight.floatValue();
    }

    public PDVisibleSignDesigner height(float f) {
        this.imageHeight = Float.valueOf(f);
        this.formatterRectangleParameters[3] = (int) f;
        return this;
    }

    public float getTemplateHeight() {
        return getPageHeight();
    }

    private PDVisibleSignDesigner pageHeight(float f) {
        this.pageHeight = f;
        return this;
    }

    public String getSignatureFieldName() {
        return this.signatureFieldName;
    }

    public PDVisibleSignDesigner signatureFieldName(String str) {
        this.signatureFieldName = str;
        return this;
    }

    public Bitmap getImage() {
        return this.image;
    }

    private void readImageStream(InputStream inputStream) throws IOException {
        setImage(BitmapFactory.decodeStream(inputStream));
    }

    private void setImage(Bitmap bitmap) {
        this.image = bitmap;
        this.imageHeight = Float.valueOf(bitmap.getHeight());
        this.imageWidth = Float.valueOf(bitmap.getWidth());
        this.formatterRectangleParameters[2] = bitmap.getWidth();
        this.formatterRectangleParameters[3] = bitmap.getHeight();
    }

    @Deprecated
    public byte[] getAffineTransformParams() {
        return new byte[]{(byte) this.affineTransform.getScaleX(), (byte) this.affineTransform.getShearY(), (byte) this.affineTransform.getShearX(), (byte) this.affineTransform.getScaleY(), (byte) this.affineTransform.getTranslateX(), (byte) this.affineTransform.getTranslateY()};
    }

    public AffineTransform getTransform() {
        return this.affineTransform;
    }

    @Deprecated
    public PDVisibleSignDesigner affineTransformParams(byte[] bArr) {
        this.affineTransform = new AffineTransform(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5]);
        return this;
    }

    public PDVisibleSignDesigner transform(AffineTransform affineTransform) {
        this.affineTransform = new AffineTransform(affineTransform);
        return this;
    }

    @Deprecated
    public byte[] getFormatterRectangleParams() {
        return this.formatterRectangleParams;
    }

    public int[] getFormatterRectangleParameters() {
        return this.formatterRectangleParameters;
    }

    @Deprecated
    public PDVisibleSignDesigner formatterRectangleParams(byte[] bArr) {
        this.formatterRectangleParams = bArr;
        return this;
    }

    public PDVisibleSignDesigner formatterRectangleParameters(int[] iArr) {
        this.formatterRectangleParameters = iArr;
        return this;
    }

    public float getPageWidth() {
        return this.pageWidth;
    }

    public PDVisibleSignDesigner pageWidth(float f) {
        this.pageWidth = f;
        return this;
    }

    public float getPageHeight() {
        return this.pageHeight;
    }

    public float getImageSizeInPercents() {
        return this.imageSizeInPercents;
    }

    public void imageSizeInPercents(float f) {
        this.imageSizeInPercents = f;
    }

    public String getSignatureText() {
        throw new UnsupportedOperationException("That method is not yet implemented");
    }

    public PDVisibleSignDesigner signatureText(String str) {
        throw new UnsupportedOperationException("That method is not yet implemented");
    }
}
