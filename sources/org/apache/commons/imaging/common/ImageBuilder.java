package org.apache.commons.imaging.common;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.RasterFormatException;
import java.awt.image.WritableRaster;
import java.util.Properties;

/* loaded from: classes2.dex */
public class ImageBuilder {
    private final int[] data;
    private final boolean hasAlpha;
    private final int height;
    private final boolean isAlphaPremultiplied;
    private final int width;

    public ImageBuilder(int i, int i2, boolean z) {
        checkDimensions(i, i2);
        this.data = new int[i * i2];
        this.width = i;
        this.height = i2;
        this.hasAlpha = z;
        this.isAlphaPremultiplied = false;
    }

    public ImageBuilder(int i, int i2, boolean z, boolean z2) {
        checkDimensions(i, i2);
        this.data = new int[i * i2];
        this.width = i;
        this.height = i2;
        this.hasAlpha = z;
        this.isAlphaPremultiplied = z2;
    }

    private void checkDimensions(int i, int i2) {
        if (i <= 0) {
            throw new RasterFormatException("zero or negative width value");
        }
        if (i2 <= 0) {
            throw new RasterFormatException("zero or negative height value");
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRGB(int i, int i2) {
        return this.data[(i2 * this.width) + i];
    }

    public void setRGB(int i, int i2, int i3) {
        this.data[(i2 * this.width) + i] = i3;
    }

    public BufferedImage getBufferedImage() {
        return makeBufferedImage(this.data, this.width, this.height, this.hasAlpha);
    }

    private void checkBounds(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (i3 <= 0) {
            throw new RasterFormatException("negative or zero subimage width");
        }
        if (i4 <= 0) {
            throw new RasterFormatException("negative or zero subimage height");
        }
        if (i < 0 || i >= (i5 = this.width)) {
            throw new RasterFormatException("subimage x is outside raster");
        }
        if (i + i3 > i5) {
            throw new RasterFormatException("subimage (x+width) is outside raster");
        }
        if (i2 < 0 || i2 >= (i6 = this.height)) {
            throw new RasterFormatException("subimage y is outside raster");
        }
        if (i2 + i4 > i6) {
            throw new RasterFormatException("subimage (y+height) is outside raster");
        }
    }

    public ImageBuilder getSubset(int i, int i2, int i3, int i4) {
        checkBounds(i, i2, i3, i4);
        ImageBuilder imageBuilder = new ImageBuilder(i3, i4, this.hasAlpha, this.isAlphaPremultiplied);
        for (int i5 = 0; i5 < i4; i5++) {
            System.arraycopy(this.data, ((i5 + i2) * this.width) + i, imageBuilder.data, i5 * i3, i3);
        }
        return imageBuilder;
    }

    public BufferedImage getSubimage(int i, int i2, int i3, int i4) {
        checkBounds(i, i2, i3, i4);
        int[] iArr = new int[i3 * i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            System.arraycopy(this.data, ((i6 + i2) * this.width) + i, iArr, i5, i3);
            i5 += i3;
        }
        return makeBufferedImage(iArr, i3, i4, this.hasAlpha);
    }

    private BufferedImage makeBufferedImage(int[] iArr, int i, int i2, boolean z) {
        DirectColorModel directColorModel;
        WritableRaster createPackedRaster;
        DataBufferInt dataBufferInt = new DataBufferInt(iArr, i * i2);
        if (z) {
            directColorModel = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, (int) ViewCompat.MEASURED_STATE_MASK, this.isAlphaPremultiplied, 3);
            createPackedRaster = Raster.createPackedRaster(dataBufferInt, i, i2, i, new int[]{16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, ViewCompat.MEASURED_STATE_MASK}, (Point) null);
        } else {
            directColorModel = new DirectColorModel(24, 16711680, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255);
            createPackedRaster = Raster.createPackedRaster(dataBufferInt, i, i2, i, new int[]{16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255}, (Point) null);
        }
        return new BufferedImage(directColorModel, createPackedRaster, directColorModel.isAlphaPremultiplied(), new Properties());
    }
}
