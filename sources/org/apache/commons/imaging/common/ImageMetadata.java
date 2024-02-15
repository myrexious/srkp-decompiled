package org.apache.commons.imaging.common;

import java.util.List;

/* loaded from: classes2.dex */
public interface ImageMetadata {

    /* loaded from: classes2.dex */
    public interface ImageMetadataItem {
        String toString();

        String toString(String str);
    }

    List<? extends ImageMetadataItem> getItems();

    String toString(String str);
}
