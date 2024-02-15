package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;

/* loaded from: classes2.dex */
public final class OceScanjobTagConstants {
    public static final List<TagInfo> ALL_OCE_SCANJOB_TAGS;
    public static final TagInfoAscii EXIF_TAG_OCE_APPLICATION_SELECTOR;
    public static final TagInfoAscii EXIF_TAG_OCE_IDENTIFICATION_NUMBER;
    public static final TagInfoAscii EXIF_TAG_OCE_IMAGE_LOGIC_CHARACTERISTICS;
    public static final TagInfoAscii EXIF_TAG_OCE_SCANJOB_DESCRIPTION;

    static {
        TagInfoAscii tagInfoAscii = new TagInfoAscii("Oce Scanjob Description", 50215, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_OCE_SCANJOB_DESCRIPTION = tagInfoAscii;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii("Oce Application Selector", 50216, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_OCE_APPLICATION_SELECTOR = tagInfoAscii2;
        TagInfoAscii tagInfoAscii3 = new TagInfoAscii("Oce Identification Number", 50217, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_OCE_IDENTIFICATION_NUMBER = tagInfoAscii3;
        TagInfoAscii tagInfoAscii4 = new TagInfoAscii("Oce ImageLogic Characteristics", 50218, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_OCE_IMAGE_LOGIC_CHARACTERISTICS = tagInfoAscii4;
        ALL_OCE_SCANJOB_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoAscii, tagInfoAscii2, tagInfoAscii3, tagInfoAscii4));
    }

    private OceScanjobTagConstants() {
    }
}
