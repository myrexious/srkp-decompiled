package org.apache.commons.imaging.formats.png;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageInfo;

/* loaded from: classes2.dex */
public class PngImageInfo extends ImageInfo {
    private final PhysicalScale physicalScale;
    private final List<PngText> textChunks;

    public PngImageInfo(String str, int i, List<String> list, ImageFormat imageFormat, String str2, int i2, String str3, int i3, int i4, float f, int i5, float f2, int i6, boolean z, boolean z2, boolean z3, ImageInfo.ColorType colorType, ImageInfo.CompressionAlgorithm compressionAlgorithm, List<PngText> list2, PhysicalScale physicalScale) {
        super(str, i, list, imageFormat, str2, i2, str3, i3, i4, f, i5, f2, i6, z, z2, z3, colorType, compressionAlgorithm);
        this.textChunks = list2;
        this.physicalScale = physicalScale;
    }

    public List<PngText> getTextChunks() {
        return new ArrayList(this.textChunks);
    }

    public PhysicalScale getPhysicalScale() {
        return this.physicalScale;
    }
}
