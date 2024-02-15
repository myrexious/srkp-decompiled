package org.apache.commons.imaging.formats.gif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes2.dex */
public class GifImageMetadata implements ImageMetadata {
    private static final String NEWLINE = System.getProperty(SystemProperties.LINE_SEPARATOR);
    private final int height;
    private final List<GifImageMetadataItem> items;
    private final int width;

    public GifImageMetadata(int i, int i2, List<GifImageMetadataItem> list) {
        this.width = i;
        this.height = i2;
        this.items = Collections.unmodifiableList(new ArrayList(list));
    }

    @Override // org.apache.commons.imaging.common.ImageMetadata
    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%sGIF metadata:", str));
        String str2 = NEWLINE;
        sb.append(String.format("%sWidth: %d%s", str, Integer.valueOf(this.width), str2));
        sb.append(String.format("%sHeight: %d%s", str, Integer.valueOf(this.height), str2));
        sb.append(str2);
        sb.append(String.format("%sImages:", str));
        for (GifImageMetadataItem gifImageMetadataItem : this.items) {
            sb.append(NEWLINE);
            sb.append(gifImageMetadataItem.toString(str));
        }
        return sb.toString();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override // org.apache.commons.imaging.common.ImageMetadata
    public List<GifImageMetadataItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }
}
