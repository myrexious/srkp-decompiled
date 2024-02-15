package org.apache.commons.imaging.common;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes2.dex */
public class GenericImageMetadata implements ImageMetadata {
    private static final String NEWLINE = System.getProperty(SystemProperties.LINE_SEPARATOR);
    private final List<ImageMetadata.ImageMetadataItem> items = new ArrayList();

    public void add(String str, String str2) {
        add(new GenericImageMetadataItem(str, str2));
    }

    public void add(ImageMetadata.ImageMetadataItem imageMetadataItem) {
        this.items.add(imageMetadataItem);
    }

    @Override // org.apache.commons.imaging.common.ImageMetadata
    public List<? extends ImageMetadata.ImageMetadataItem> getItems() {
        return new ArrayList(this.items);
    }

    public String toString() {
        return toString(null);
    }

    @Override // org.apache.commons.imaging.common.ImageMetadata
    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++) {
            if (i > 0) {
                sb.append(NEWLINE);
            }
            sb.append(this.items.get(i).toString(str + "\t"));
        }
        return sb.toString();
    }

    /* loaded from: classes2.dex */
    public static class GenericImageMetadataItem implements ImageMetadata.ImageMetadataItem {
        private final String keyword;
        private final String text;

        public GenericImageMetadataItem(String str, String str2) {
            this.keyword = str;
            this.text = str2;
        }

        public String getKeyword() {
            return this.keyword;
        }

        public String getText() {
            return this.text;
        }

        @Override // org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem
        public String toString() {
            return toString(null);
        }

        @Override // org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem
        public String toString(String str) {
            String str2 = this.keyword + ": " + this.text;
            return str != null ? str + str2 : str2;
        }
    }
}
