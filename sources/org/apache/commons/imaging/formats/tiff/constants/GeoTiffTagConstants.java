package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoDoubles;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;

/* loaded from: classes2.dex */
public final class GeoTiffTagConstants {
    public static final List<TagInfo> ALL_GEO_TIFF_TAGS;
    public static final TagInfoAscii EXIF_TAG_GEO_ASCII_PARAMS_TAG;
    public static final TagInfoDoubles EXIF_TAG_GEO_DOUBLE_PARAMS_TAG;
    public static final TagInfoShorts EXIF_TAG_GEO_KEY_DIRECTORY_TAG;
    public static final TagInfoDoubles EXIF_TAG_INTERGRAPH_MATRIX_TAG;
    public static final TagInfoDoubles EXIF_TAG_MODEL_PIXEL_SCALE_TAG;
    public static final TagInfoDoubles EXIF_TAG_MODEL_TIEPOINT_TAG;
    public static final TagInfoDoubles EXIF_TAG_MODEL_TRANSFORMATION_TAG;

    static {
        TagInfoDoubles tagInfoDoubles = new TagInfoDoubles("ModelPixelScaleTag", 33550, 3, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MODEL_PIXEL_SCALE_TAG = tagInfoDoubles;
        TagInfoDoubles tagInfoDoubles2 = new TagInfoDoubles("IntergraphMatrixTag", 33920, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_INTERGRAPH_MATRIX_TAG = tagInfoDoubles2;
        TagInfoDoubles tagInfoDoubles3 = new TagInfoDoubles("ModelTiepointTag", 33922, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MODEL_TIEPOINT_TAG = tagInfoDoubles3;
        TagInfoDoubles tagInfoDoubles4 = new TagInfoDoubles("ModelTransformationTag", 34264, 16, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_MODEL_TRANSFORMATION_TAG = tagInfoDoubles4;
        TagInfoShorts tagInfoShorts = new TagInfoShorts("GeoKeyDirectoryTag", 34735, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_GEO_KEY_DIRECTORY_TAG = tagInfoShorts;
        TagInfoDoubles tagInfoDoubles5 = new TagInfoDoubles("GeoDoubleParamsTag", 34736, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_GEO_DOUBLE_PARAMS_TAG = tagInfoDoubles5;
        TagInfoAscii tagInfoAscii = new TagInfoAscii("GeoAsciiParamsTag", 34737, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_GEO_ASCII_PARAMS_TAG = tagInfoAscii;
        ALL_GEO_TIFF_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoDoubles, tagInfoDoubles2, tagInfoDoubles3, tagInfoDoubles4, tagInfoShorts, tagInfoDoubles5, tagInfoAscii));
    }

    private GeoTiffTagConstants() {
    }
}
