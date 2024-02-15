package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

/* loaded from: classes2.dex */
public class TiffImagingParameters extends XmpImagingParameters {
    private int subImageHeight;
    private int subImageWidth;
    private int subImageX;
    private int subImageY;
    private boolean readThumbnails = true;
    private TiffOutputSet tiffOutputSet = null;
    private PhotometricInterpreter customPhotometricInterpreter = null;
    private Integer compression = null;
    private Integer lzwCompressionBlockSize = null;
    private Integer t4Options = null;
    private Integer t6Options = null;

    public boolean isReadThumbnails() {
        return this.readThumbnails;
    }

    public void setReadThumbnails(boolean z) {
        this.readThumbnails = z;
    }

    public TiffOutputSet getOutputSet() {
        return this.tiffOutputSet;
    }

    public void setOutputSet(TiffOutputSet tiffOutputSet) {
        this.tiffOutputSet = tiffOutputSet;
    }

    public void setSubImage(int i, int i2, int i3, int i4) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Invalid sub-image specification: negative x and y values not allowed");
        }
        if (i3 <= 0 || i4 <= 0) {
            throw new IllegalArgumentException("Invalid sub-image specification width and height must be greater than zero");
        }
        this.subImageX = i;
        this.subImageY = i2;
        this.subImageWidth = i3;
        this.subImageHeight = i4;
    }

    public void clearSubImage() {
        this.subImageWidth = 0;
        this.subImageHeight = 0;
    }

    public boolean isSubImageSet() {
        return this.subImageWidth > 0 && this.subImageHeight > 0;
    }

    public int getSubImageX() {
        return this.subImageX;
    }

    public int getSubImageY() {
        return this.subImageY;
    }

    public int getSubImageWidth() {
        return this.subImageWidth;
    }

    public int getSubImageHeight() {
        return this.subImageHeight;
    }

    public PhotometricInterpreter getCustomPhotometricInterpreter() {
        return this.customPhotometricInterpreter;
    }

    public void setCustomPhotometricInterpreter(PhotometricInterpreter photometricInterpreter) {
        this.customPhotometricInterpreter = photometricInterpreter;
    }

    public Integer getCompression() {
        return this.compression;
    }

    public void setCompression(Integer num) {
        this.compression = num;
    }

    public Integer getLzwCompressionBlockSize() {
        return this.lzwCompressionBlockSize;
    }

    public void setLzwCompressionBlockSize(Integer num) {
        this.lzwCompressionBlockSize = num;
    }

    public Integer getT4Options() {
        return this.t4Options;
    }

    public void setT4Options(Integer num) {
        this.t4Options = num;
    }

    public Integer getT6Options() {
        return this.t6Options;
    }

    public void setT6Options(Integer num) {
        this.t6Options = num;
    }
}
