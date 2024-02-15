package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;

/* loaded from: classes2.dex */
public final class MolecularDynamicsGelTagConstants {
    public static final List<TagInfo> ALL_MOLECULAR_DYNAMICS_GEL_TAGS;
    public static final TagInfoShorts EXIF_TAG_MD_COLOR_TABLE;
    public static final TagInfoLong EXIF_TAG_MD_FILE_TAG;
    public static final TagInfoAscii EXIF_TAG_MD_FILE_UNITS;
    public static final TagInfoAscii EXIF_TAG_MD_LAB_NAME;
    public static final TagInfoAscii EXIF_TAG_MD_PREP_DATE;
    public static final TagInfoAscii EXIF_TAG_MD_PREP_TIME;
    public static final TagInfoAscii EXIF_TAG_MD_SAMPLE_INFO;
    public static final TagInfoRational EXIF_TAG_MD_SCALE_PIXEL;

    static {
        TagInfoLong tagInfoLong = new TagInfoLong("MD FileTag", 33445, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_FILE_TAG = tagInfoLong;
        TagInfoRational tagInfoRational = new TagInfoRational("MD ScalePixel", 33446, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_SCALE_PIXEL = tagInfoRational;
        TagInfoShorts tagInfoShorts = new TagInfoShorts("MD ColorTable", 33447, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_COLOR_TABLE = tagInfoShorts;
        TagInfoAscii tagInfoAscii = new TagInfoAscii("MD LabName", 33448, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_LAB_NAME = tagInfoAscii;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii("MD SampleInfo", 33449, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_SAMPLE_INFO = tagInfoAscii2;
        TagInfoAscii tagInfoAscii3 = new TagInfoAscii("MD PrepDate", 33450, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_PREP_DATE = tagInfoAscii3;
        TagInfoAscii tagInfoAscii4 = new TagInfoAscii("MD PrepTime", 33451, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_PREP_TIME = tagInfoAscii4;
        TagInfoAscii tagInfoAscii5 = new TagInfoAscii("MD FileUnits", 33452, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MD_FILE_UNITS = tagInfoAscii5;
        ALL_MOLECULAR_DYNAMICS_GEL_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoLong, tagInfoRational, tagInfoShorts, tagInfoAscii, tagInfoAscii2, tagInfoAscii3, tagInfoAscii4, tagInfoAscii5));
    }

    private MolecularDynamicsGelTagConstants() {
    }
}
