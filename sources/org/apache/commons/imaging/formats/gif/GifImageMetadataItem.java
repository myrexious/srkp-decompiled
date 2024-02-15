package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes2.dex */
public class GifImageMetadataItem implements ImageMetadata.ImageMetadataItem {
    private static final String NEWLINE = System.getProperty(SystemProperties.LINE_SEPARATOR);
    private final int delay;
    private final DisposalMethod disposalMethod;
    private final int leftPosition;
    private final int topPosition;

    public GifImageMetadataItem(int i, int i2, int i3, DisposalMethod disposalMethod) {
        this.delay = i;
        this.leftPosition = i2;
        this.topPosition = i3;
        this.disposalMethod = disposalMethod;
    }

    public int getDelay() {
        return this.delay;
    }

    public int getLeftPosition() {
        return this.leftPosition;
    }

    public int getTopPosition() {
        return this.topPosition;
    }

    public DisposalMethod getDisposalMethod() {
        return this.disposalMethod;
    }

    @Override // org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem
    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        String str2 = NEWLINE;
        sb.append(String.format("%sDelay: %d%s", str, Integer.valueOf(this.delay), str2));
        sb.append(String.format("%sLeft position: %d%s", str, Integer.valueOf(this.leftPosition), str2));
        sb.append(String.format("%sTop position: %d%s", str, Integer.valueOf(this.topPosition), str2));
        sb.append(String.format("%sDisposal method: %s%s", str, this.disposalMethod, str2));
        return sb.toString();
    }
}
