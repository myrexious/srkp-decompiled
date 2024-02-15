package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;

/* loaded from: classes2.dex */
public final class AliasSketchbookProTagConstants {
    public static final List<TagInfo> ALL_ALIAS_SKETCHBOOK_PRO_TAGS;
    public static final TagInfoAscii EXIF_TAG_ALIAS_LAYER_METADATA;

    static {
        TagInfoAscii tagInfoAscii = new TagInfoAscii("Alias Layer Metadata", 50784, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_ALIAS_LAYER_METADATA = tagInfoAscii;
        ALL_ALIAS_SKETCHBOOK_PRO_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoAscii));
    }

    private AliasSketchbookProTagConstants() {
    }
}
