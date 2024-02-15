package org.apache.commons.imaging.formats.tiff.constants;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.tom_roush.fontbox.ttf.WGL4Names;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.psd.PsdImageParser;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAny;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoByteOrShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLongs;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoUnknowns;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;

/* loaded from: classes2.dex */
public final class TiffTagConstants {
    public static final List<TagInfo> ALL_TIFF_TAGS;
    public static final int COMPRESSION_VALUE_ADOBE_DEFLATE = 8;
    public static final int COMPRESSION_VALUE_CCIRLEW = 32771;
    public static final int COMPRESSION_VALUE_CCITT_1D = 2;
    public static final int COMPRESSION_VALUE_DCS = 32947;
    public static final int COMPRESSION_VALUE_DEFLATE = 32946;
    public static final int COMPRESSION_VALUE_EPSON_ERF_COMPRESSED = 32769;
    public static final int COMPRESSION_VALUE_IT8BL = 32898;
    public static final int COMPRESSION_VALUE_IT8CTPAD = 32895;
    public static final int COMPRESSION_VALUE_IT8LW = 32896;
    public static final int COMPRESSION_VALUE_IT8MP = 32897;
    public static final int COMPRESSION_VALUE_JBIG = 34661;
    public static final int COMPRESSION_VALUE_JBIG_B_AND_W = 9;
    public static final int COMPRESSION_VALUE_JBIG_COLOR = 10;
    public static final int COMPRESSION_VALUE_JPEG = 7;
    public static final int COMPRESSION_VALUE_JPEG_2000 = 34712;
    public static final int COMPRESSION_VALUE_JPEG_OLD_STYLE = 6;
    public static final int COMPRESSION_VALUE_KODAK_DCR_COMPRESSED = 65000;
    public static final int COMPRESSION_VALUE_LZW = 5;
    public static final int COMPRESSION_VALUE_NEXT = 32766;
    public static final int COMPRESSION_VALUE_NIKON_NEF_COMPRESSED = 34713;
    public static final int COMPRESSION_VALUE_PACK_BITS = 32773;
    public static final int COMPRESSION_VALUE_PENTAX_PEF_COMPRESSED = 65535;
    public static final int COMPRESSION_VALUE_PIXAR_FILM = 32908;
    public static final int COMPRESSION_VALUE_PIXAR_LOG = 32909;
    public static final int COMPRESSION_VALUE_SGILOG = 34676;
    public static final int COMPRESSION_VALUE_SGILOG_24 = 34677;
    public static final int COMPRESSION_VALUE_T4_GROUP_3_FAX = 3;
    public static final int COMPRESSION_VALUE_T6_GROUP_4_FAX = 4;
    public static final int COMPRESSION_VALUE_THUNDERSCAN = 32809;
    public static final int COMPRESSION_VALUE_UNCOMPRESSED = 1;
    public static final int EXTRA_SAMPLE_ASSOCIATED_ALPHA = 1;
    public static final int EXTRA_SAMPLE_UNASSOCIATED_ALPHA = 2;
    public static final int FILL_ORDER_VALUE_NORMAL = 1;
    public static final int FILL_ORDER_VALUE_REVERSED = 2;
    public static final int GRAY_RESPONSE_UNIT_VALUE_0_00001 = 5;
    public static final int GRAY_RESPONSE_UNIT_VALUE_0_0001 = 4;
    public static final int GRAY_RESPONSE_UNIT_VALUE_0_001 = 3;
    public static final int GRAY_RESPONSE_UNIT_VALUE_0_01 = 2;
    public static final int GRAY_RESPONSE_UNIT_VALUE_0_1 = 1;
    public static final int INK_SET_VALUE_CMYK = 1;
    public static final int INK_SET_VALUE_NOT_CMYK = 2;
    public static final int JPEGPROC_VALUE_BASELINE = 1;
    public static final int JPEGPROC_VALUE_LOSSLESS = 14;
    public static final int OLD_SUBFILE_TYPE_VALUE_FULL_RESOLUTION_IMAGE = 1;
    public static final int OLD_SUBFILE_TYPE_VALUE_REDUCED_RESOLUTION_IMAGE = 2;
    public static final int OLD_SUBFILE_TYPE_VALUE_SINGLE_PAGE_OF_MULTI_PAGE_IMAGE = 3;
    public static final int ORIENTATION_VALUE_HORIZONTAL_NORMAL = 1;
    public static final int ORIENTATION_VALUE_MIRROR_HORIZONTAL = 2;
    public static final int ORIENTATION_VALUE_MIRROR_HORIZONTAL_AND_ROTATE_270_CW = 5;
    public static final int ORIENTATION_VALUE_MIRROR_HORIZONTAL_AND_ROTATE_90_CW = 7;
    public static final int ORIENTATION_VALUE_MIRROR_VERTICAL = 4;
    public static final int ORIENTATION_VALUE_ROTATE_180 = 3;
    public static final int ORIENTATION_VALUE_ROTATE_270_CW = 8;
    public static final int ORIENTATION_VALUE_ROTATE_90_CW = 6;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_CIELAB = 8;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_CMYK = 5;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_COLOR_FILTER_ARRAY = 32803;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_ICCLAB = 9;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_ITULAB = 10;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_LINEAR_RAW = 34892;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_PIXAR_LOG_L = 32844;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_PIXAR_LOG_LUV = 32845;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_RGB_PALETTE = 3;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_TRANSPARENCY_MASK = 4;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_VALUE_YCB_CR = 6;
    public static final int PLANAR_CONFIGURATION_VALUE_CHUNKY = 1;
    public static final int PLANAR_CONFIGURATION_VALUE_PLANAR = 2;
    public static final int PREDICTOR_VALUE_FLOATING_POINT_DIFFERENCING = 3;
    public static final int PREDICTOR_VALUE_HORIZONTAL_DIFFERENCING = 2;
    public static final int PREDICTOR_VALUE_NONE = 1;
    public static final int RESOLUTION_UNIT_VALUE_CM = 3;
    public static final int RESOLUTION_UNIT_VALUE_INCHES = 2;
    public static final int RESOLUTION_UNIT_VALUE_NONE = 1;
    public static final int SAMPLE_FORMAT_VALUE_COMPLEX_INTEGER = 5;
    public static final int SAMPLE_FORMAT_VALUE_IEEE_COMPLEX_FLOAT = 6;
    public static final int SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT = 3;
    public static final int SAMPLE_FORMAT_VALUE_TWOS_COMPLEMENT_SIGNED_INTEGER = 2;
    public static final int SAMPLE_FORMAT_VALUE_UNDEFINED = 4;
    public static final int SAMPLE_FORMAT_VALUE_UNSIGNED_INTEGER = 1;
    public static final int SUBFILE_TYPE_VALUE_FULL_RESOLUTION_IMAGE = 0;
    public static final int SUBFILE_TYPE_VALUE_REDUCED_RESOLUTION_IMAGE = 1;
    public static final int SUBFILE_TYPE_VALUE_SINGLE_PAGE_OF_MULTI_PAGE_IMAGE = 2;
    public static final int SUBFILE_TYPE_VALUE_SINGLE_PAGE_OF_MULTI_PAGE_REDUCED_RESOLUTION_IMAGE = 3;
    public static final int SUBFILE_TYPE_VALUE_TRANSPARENCY_MASK = 4;
    public static final int SUBFILE_TYPE_VALUE_TRANSPARENCY_MASK_OF_MULTI_PAGE_IMAGE = 6;
    public static final int SUBFILE_TYPE_VALUE_TRANSPARENCY_MASK_OF_REDUCED_RESOLUTION_IMAGE = 5;
    public static final int SUBFILE_TYPE_VALUE_TRANSPARENCY_MASK_OF_REDUCED_RESOLUTION_MULTI_PAGE_IMAGE = 7;
    public static final int THRESHOLDING_VALUE_NO_DITHERING_OR_HALFTONING = 1;
    public static final int THRESHOLDING_VALUE_ORDERED_DITHER_OR_HALFTONE = 2;
    public static final int THRESHOLDING_VALUE_RANDOMIZED_DITHER = 3;
    public static final TagInfoAscii TIFF_TAG_ARTIST;
    public static final TagInfoShorts TIFF_TAG_BITS_PER_SAMPLE;
    public static final TagInfoShort TIFF_TAG_CELL_LENGTH;
    public static final TagInfoShort TIFF_TAG_CELL_WIDTH;
    public static final TagInfoShorts TIFF_TAG_COLOR_MAP;
    public static final TagInfoShort TIFF_TAG_COMPRESSION;
    public static final TagInfoAscii TIFF_TAG_COPYRIGHT;
    public static final TagInfoAscii TIFF_TAG_DATE_TIME;
    public static final TagInfoAscii TIFF_TAG_DOCUMENT_NAME;
    public static final TagInfoByteOrShort TIFF_TAG_DOT_RANGE;
    public static final TagInfoShorts TIFF_TAG_EXTRA_SAMPLES;
    public static final TagInfoShort TIFF_TAG_FILL_ORDER;
    public static final TagInfoLongs TIFF_TAG_FREE_BYTE_COUNTS;
    public static final TagInfoLongs TIFF_TAG_FREE_OFFSETS;
    public static final TagInfoShorts TIFF_TAG_GRAY_RESPONSE_CURVE;
    public static final TagInfoShort TIFF_TAG_GRAY_RESPONSE_UNIT;
    public static final TagInfoShorts TIFF_TAG_HALFTONE_HINTS;
    public static final TagInfoAscii TIFF_TAG_HOST_COMPUTER;
    public static final TagInfoAscii TIFF_TAG_IMAGE_DESCRIPTION;
    public static final TagInfoShortOrLong TIFF_TAG_IMAGE_LENGTH;
    public static final TagInfoShortOrLong TIFF_TAG_IMAGE_WIDTH;
    public static final TagInfoAscii TIFF_TAG_INK_NAMES;
    public static final TagInfoShort TIFF_TAG_INK_SET;
    public static final TagInfoLongs TIFF_TAG_JPEG_ACTABLES;
    public static final TagInfoLongs TIFF_TAG_JPEG_DCTABLES;
    public static final TagInfoLong TIFF_TAG_JPEG_INTERCHANGE_FORMAT;
    public static final TagInfoLong TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH;
    public static final TagInfoShorts TIFF_TAG_JPEG_LOSSLESS_PREDICTORS;
    public static final TagInfoShorts TIFF_TAG_JPEG_POINT_TRANSFORMS;
    public static final TagInfoShort TIFF_TAG_JPEG_PROC;
    public static final TagInfoLongs TIFF_TAG_JPEG_QTABLES;
    public static final TagInfoShort TIFF_TAG_JPEG_RESTART_INTERVAL;
    public static final TagInfoAscii TIFF_TAG_MAKE;
    public static final TagInfoShorts TIFF_TAG_MAX_SAMPLE_VALUE;
    public static final TagInfoShorts TIFF_TAG_MIN_SAMPLE_VALUE;
    public static final TagInfoAscii TIFF_TAG_MODEL;
    public static final TagInfoLong TIFF_TAG_NEW_SUBFILE_TYPE;
    public static final TagInfoShort TIFF_TAG_NUMBER_OF_INKS;
    public static final TagInfoShort TIFF_TAG_ORIENTATION;
    public static final TagInfoAscii TIFF_TAG_PAGE_NAME;
    public static final TagInfoShorts TIFF_TAG_PAGE_NUMBER;
    public static final TagInfoShort TIFF_TAG_PHOTOMETRIC_INTERPRETATION;
    public static final TagInfoShort TIFF_TAG_PLANAR_CONFIGURATION;
    public static final TagInfoShort TIFF_TAG_PREDICTOR;
    public static final TagInfoRationals TIFF_TAG_PRIMARY_CHROMATICITIES;
    public static final TagInfoLongs TIFF_TAG_REFERENCE_BLACK_WHITE;
    public static final TagInfoShort TIFF_TAG_RESOLUTION_UNIT;
    public static final TagInfoShortOrLong TIFF_TAG_ROWS_PER_STRIP;
    public static final TagInfoShort TIFF_TAG_SAMPLES_PER_PIXEL;
    public static final TagInfoShorts TIFF_TAG_SAMPLE_FORMAT;
    public static final TagInfoAny TIFF_TAG_SMAX_SAMPLE_VALUE;
    public static final TagInfoAny TIFF_TAG_SMIN_SAMPLE_VALUE;
    public static final TagInfoAscii TIFF_TAG_SOFTWARE;
    public static final TagInfoShortOrLong TIFF_TAG_STRIP_BYTE_COUNTS;
    public static final TagInfoShortOrLong TIFF_TAG_STRIP_OFFSETS;
    public static final TagInfoShort TIFF_TAG_SUBFILE_TYPE;
    public static final TagInfoLong TIFF_TAG_T4_OPTIONS;
    public static final TagInfoLong TIFF_TAG_T6_OPTIONS;
    public static final TagInfoAscii TIFF_TAG_TARGET_PRINTER;
    public static final TagInfoShort TIFF_TAG_THRESHHOLDING;
    public static final TagInfoShortOrLong TIFF_TAG_TILE_BYTE_COUNTS;
    public static final TagInfoShortOrLong TIFF_TAG_TILE_LENGTH;
    public static final TagInfoLongs TIFF_TAG_TILE_OFFSETS;
    public static final TagInfoShortOrLong TIFF_TAG_TILE_WIDTH;
    public static final TagInfoShorts TIFF_TAG_TRANSFER_FUNCTION;
    public static final TagInfoShorts TIFF_TAG_TRANSFER_RANGE;
    public static final TagInfoUnknowns TIFF_TAG_UNKNOWN;
    public static final TagInfoRationals TIFF_TAG_WHITE_POINT;
    public static final TagInfoBytes TIFF_TAG_XMP;
    public static final TagInfoRationals TIFF_TAG_XPOSITION;
    public static final TagInfoRational TIFF_TAG_XRESOLUTION;
    public static final TagInfoRationals TIFF_TAG_YCBCR_COEFFICIENTS;
    public static final TagInfoShort TIFF_TAG_YCBCR_POSITIONING;
    public static final TagInfoShorts TIFF_TAG_YCBCR_SUB_SAMPLING;
    public static final TagInfoRationals TIFF_TAG_YPOSITION;
    public static final TagInfoRational TIFF_TAG_YRESOLUTION;
    public static final int YCB_CR_POSITIONING_VALUE_CENTERED = 1;
    public static final int YCB_CR_POSITIONING_VALUE_CO_SITED = 2;

    static {
        TagInfoLong tagInfoLong = new TagInfoLong(ExifInterface.TAG_NEW_SUBFILE_TYPE, 254, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_NEW_SUBFILE_TYPE = tagInfoLong;
        TagInfoShort tagInfoShort = new TagInfoShort(ExifInterface.TAG_SUBFILE_TYPE, 255, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_SUBFILE_TYPE = tagInfoShort;
        TagInfoShortOrLong tagInfoShortOrLong = new TagInfoShortOrLong("ImageWidth", 256, 1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_IMAGE_WIDTH = tagInfoShortOrLong;
        TagInfoShortOrLong tagInfoShortOrLong2 = new TagInfoShortOrLong("ImageLength", 257, 1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_IMAGE_LENGTH = tagInfoShortOrLong2;
        TagInfoShorts tagInfoShorts = new TagInfoShorts("BitsPerSample", WGL4Names.NUMBER_OF_MAC_GLYPHS, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_BITS_PER_SAMPLE = tagInfoShorts;
        TagInfoShort tagInfoShort2 = new TagInfoShort("Compression", 259, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_COMPRESSION = tagInfoShort2;
        TagInfoShort tagInfoShort3 = new TagInfoShort("PhotometricInterpretation", 262, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_PHOTOMETRIC_INTERPRETATION = tagInfoShort3;
        TagInfoShort tagInfoShort4 = new TagInfoShort("Threshholding", 263, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_THRESHHOLDING = tagInfoShort4;
        TagInfoShort tagInfoShort5 = new TagInfoShort("CellWidth", 264, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_CELL_WIDTH = tagInfoShort5;
        TagInfoShort tagInfoShort6 = new TagInfoShort("CellLength", 265, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_CELL_LENGTH = tagInfoShort6;
        TagInfoShort tagInfoShort7 = new TagInfoShort("FillOrder", 266, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_FILL_ORDER = tagInfoShort7;
        TagInfoAscii tagInfoAscii = new TagInfoAscii("DocumentName", 269, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_DOCUMENT_NAME = tagInfoAscii;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii("ImageDescription", 270, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_IMAGE_DESCRIPTION = tagInfoAscii2;
        TagInfoAscii tagInfoAscii3 = new TagInfoAscii("Make", 271, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_MAKE = tagInfoAscii3;
        TagInfoAscii tagInfoAscii4 = new TagInfoAscii("Model", 272, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_MODEL = tagInfoAscii4;
        TagInfoShortOrLong tagInfoShortOrLong3 = new TagInfoShortOrLong(ExifInterface.TAG_STRIP_OFFSETS, 273, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT, true);
        TIFF_TAG_STRIP_OFFSETS = tagInfoShortOrLong3;
        TagInfoShort tagInfoShort8 = new TagInfoShort("Orientation", 274, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_ORIENTATION = tagInfoShort8;
        TagInfoShort tagInfoShort9 = new TagInfoShort("SamplesPerPixel", 277, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_SAMPLES_PER_PIXEL = tagInfoShort9;
        TagInfoShortOrLong tagInfoShortOrLong4 = new TagInfoShortOrLong(ExifInterface.TAG_ROWS_PER_STRIP, 278, 1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_ROWS_PER_STRIP = tagInfoShortOrLong4;
        TagInfoShortOrLong tagInfoShortOrLong5 = new TagInfoShortOrLong(ExifInterface.TAG_STRIP_BYTE_COUNTS, 279, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_STRIP_BYTE_COUNTS = tagInfoShortOrLong5;
        TagInfoShorts tagInfoShorts2 = new TagInfoShorts("MinSampleValue", 280, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_MIN_SAMPLE_VALUE = tagInfoShorts2;
        TagInfoShorts tagInfoShorts3 = new TagInfoShorts("MaxSampleValue", 281, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_MAX_SAMPLE_VALUE = tagInfoShorts3;
        TagInfoRational tagInfoRational = new TagInfoRational("XResolution", 282, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_XRESOLUTION = tagInfoRational;
        TagInfoRational tagInfoRational2 = new TagInfoRational("YResolution", 283, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_YRESOLUTION = tagInfoRational2;
        TagInfoShort tagInfoShort10 = new TagInfoShort("PlanarConfiguration", 284, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_PLANAR_CONFIGURATION = tagInfoShort10;
        TagInfoAscii tagInfoAscii5 = new TagInfoAscii("PageName", 285, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_PAGE_NAME = tagInfoAscii5;
        TagInfoRationals tagInfoRationals = new TagInfoRationals("XPosition", 286, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_XPOSITION = tagInfoRationals;
        TagInfoRationals tagInfoRationals2 = new TagInfoRationals("YPosition", 287, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_YPOSITION = tagInfoRationals2;
        TagInfoLongs tagInfoLongs = new TagInfoLongs("FreeOffsets", 288, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_FREE_OFFSETS = tagInfoLongs;
        TagInfoLongs tagInfoLongs2 = new TagInfoLongs("FreeByteCounts", 289, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_FREE_BYTE_COUNTS = tagInfoLongs2;
        TagInfoShort tagInfoShort11 = new TagInfoShort("GrayResponseUnit", 290, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_GRAY_RESPONSE_UNIT = tagInfoShort11;
        TagInfoShorts tagInfoShorts4 = new TagInfoShorts("GrayResponseCurve", 291, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_GRAY_RESPONSE_CURVE = tagInfoShorts4;
        TagInfoLong tagInfoLong2 = new TagInfoLong("T4Options", 292, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_T4_OPTIONS = tagInfoLong2;
        TagInfoLong tagInfoLong3 = new TagInfoLong("T6Options", 293, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_T6_OPTIONS = tagInfoLong3;
        TagInfoShort tagInfoShort12 = new TagInfoShort("ResolutionUnit", 296, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_RESOLUTION_UNIT = tagInfoShort12;
        TagInfoShorts tagInfoShorts5 = new TagInfoShorts("PageNumber", 297, 2, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_PAGE_NUMBER = tagInfoShorts5;
        TagInfoShorts tagInfoShorts6 = new TagInfoShorts("TransferFunction", 301, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_TRANSFER_FUNCTION = tagInfoShorts6;
        TagInfoAscii tagInfoAscii6 = new TagInfoAscii("Software", 305, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_SOFTWARE = tagInfoAscii6;
        TagInfoAscii tagInfoAscii7 = new TagInfoAscii("DateTime", 306, 20, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_DATE_TIME = tagInfoAscii7;
        TagInfoAscii tagInfoAscii8 = new TagInfoAscii("Artist", 315, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_ARTIST = tagInfoAscii8;
        TagInfoAscii tagInfoAscii9 = new TagInfoAscii("HostComputer", TypedValues.AttributesType.TYPE_PATH_ROTATE, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_HOST_COMPUTER = tagInfoAscii9;
        TagInfoShort tagInfoShort13 = new TagInfoShort("Predictor", TypedValues.AttributesType.TYPE_EASING, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_PREDICTOR = tagInfoShort13;
        TagInfoRationals tagInfoRationals3 = new TagInfoRationals("WhitePoint", TypedValues.AttributesType.TYPE_PIVOT_TARGET, 2, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_WHITE_POINT = tagInfoRationals3;
        TagInfoRationals tagInfoRationals4 = new TagInfoRationals("PrimaryChromaticities", 319, 6, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_PRIMARY_CHROMATICITIES = tagInfoRationals4;
        TagInfoShorts tagInfoShorts7 = new TagInfoShorts("ColorMap", DilithiumEngine.DilithiumPolyT1PackedBytes, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_COLOR_MAP = tagInfoShorts7;
        TagInfoShorts tagInfoShorts8 = new TagInfoShorts("HalftoneHints", 321, 2, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_HALFTONE_HINTS = tagInfoShorts8;
        TagInfoShortOrLong tagInfoShortOrLong6 = new TagInfoShortOrLong("TileWidth", 322, 1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_TILE_WIDTH = tagInfoShortOrLong6;
        TagInfoShortOrLong tagInfoShortOrLong7 = new TagInfoShortOrLong("TileLength", 323, 1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_TILE_LENGTH = tagInfoShortOrLong7;
        TagInfoLongs tagInfoLongs3 = new TagInfoLongs("TileOffsets", 324, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT, true);
        TIFF_TAG_TILE_OFFSETS = tagInfoLongs3;
        TagInfoShortOrLong tagInfoShortOrLong8 = new TagInfoShortOrLong("TileByteCounts", 325, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_TILE_BYTE_COUNTS = tagInfoShortOrLong8;
        TagInfoShort tagInfoShort14 = new TagInfoShort("InkSet", 332, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_INK_SET = tagInfoShort14;
        TagInfoAscii tagInfoAscii10 = new TagInfoAscii("InkNames", 333, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_INK_NAMES = tagInfoAscii10;
        TagInfoShort tagInfoShort15 = new TagInfoShort("NumberOfInks", 334, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_NUMBER_OF_INKS = tagInfoShort15;
        TagInfoByteOrShort tagInfoByteOrShort = new TagInfoByteOrShort("DotRange", 336, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_DOT_RANGE = tagInfoByteOrShort;
        TagInfoAscii tagInfoAscii11 = new TagInfoAscii("TargetPrinter", 337, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_TARGET_PRINTER = tagInfoAscii11;
        TagInfoShorts tagInfoShorts9 = new TagInfoShorts("ExtraSamples", 338, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_EXTRA_SAMPLES = tagInfoShorts9;
        TagInfoShorts tagInfoShorts10 = new TagInfoShorts("SampleFormat", 339, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_SAMPLE_FORMAT = tagInfoShorts10;
        TagInfoAny tagInfoAny = new TagInfoAny("SMinSampleValue", 340, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_SMIN_SAMPLE_VALUE = tagInfoAny;
        TagInfoAny tagInfoAny2 = new TagInfoAny("SMaxSampleValue", 341, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_SMAX_SAMPLE_VALUE = tagInfoAny2;
        TagInfoShorts tagInfoShorts11 = new TagInfoShorts("TransferRange", 342, 6, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_TRANSFER_RANGE = tagInfoShorts11;
        TagInfoShort tagInfoShort16 = new TagInfoShort("JPEGProc", 512, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_PROC = tagInfoShort16;
        TagInfoLong tagInfoLong4 = new TagInfoLong(ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT, 513, TiffDirectoryType.TIFF_DIRECTORY_ROOT, true);
        TIFF_TAG_JPEG_INTERCHANGE_FORMAT = tagInfoLong4;
        TagInfoLong tagInfoLong5 = new TagInfoLong(ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = tagInfoLong5;
        TagInfoShort tagInfoShort17 = new TagInfoShort("JPEGRestartInterval", 515, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_RESTART_INTERVAL = tagInfoShort17;
        TagInfoShorts tagInfoShorts12 = new TagInfoShorts("JPEGLosslessPredictors", 517, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_LOSSLESS_PREDICTORS = tagInfoShorts12;
        TagInfoShorts tagInfoShorts13 = new TagInfoShorts("JPEGPointTransforms", 518, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_POINT_TRANSFORMS = tagInfoShorts13;
        TagInfoLongs tagInfoLongs4 = new TagInfoLongs("JPEGQTables", 519, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_QTABLES = tagInfoLongs4;
        TagInfoLongs tagInfoLongs5 = new TagInfoLongs("JPEGDCTables", 520, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_DCTABLES = tagInfoLongs5;
        TagInfoLongs tagInfoLongs6 = new TagInfoLongs("JPEGACTables", 521, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_JPEG_ACTABLES = tagInfoLongs6;
        TagInfoRationals tagInfoRationals5 = new TagInfoRationals("YCbCrCoefficients", 529, 3, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_YCBCR_COEFFICIENTS = tagInfoRationals5;
        TagInfoShorts tagInfoShorts14 = new TagInfoShorts("YCbCrSubSampling", 530, 2, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_YCBCR_SUB_SAMPLING = tagInfoShorts14;
        TagInfoShort tagInfoShort18 = new TagInfoShort("YCbCrPositioning", 531, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_YCBCR_POSITIONING = tagInfoShort18;
        TagInfoLongs tagInfoLongs7 = new TagInfoLongs("ReferenceBlackWhite", 532, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_REFERENCE_BLACK_WHITE = tagInfoLongs7;
        TagInfoAscii tagInfoAscii12 = new TagInfoAscii("Copyright", 33432, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_COPYRIGHT = tagInfoAscii12;
        TagInfoBytes tagInfoBytes = new TagInfoBytes(PsdImageParser.BLOCK_NAME_XMP, 700, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_XMP = tagInfoBytes;
        TIFF_TAG_UNKNOWN = new TagInfoUnknowns("Unknown Tag", -1, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        ALL_TIFF_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoLong, tagInfoShort, tagInfoShortOrLong, tagInfoShortOrLong2, tagInfoShorts, tagInfoShort2, tagInfoShort3, tagInfoShort4, tagInfoShort5, tagInfoShort6, tagInfoShort7, tagInfoAscii, tagInfoAscii2, tagInfoAscii3, tagInfoAscii4, tagInfoShortOrLong3, tagInfoShort8, tagInfoShort9, tagInfoShortOrLong4, tagInfoShortOrLong5, tagInfoShorts2, tagInfoShorts3, tagInfoRational, tagInfoRational2, tagInfoShort10, tagInfoAscii5, tagInfoRationals, tagInfoRationals2, tagInfoLongs, tagInfoLongs2, tagInfoShort11, tagInfoShorts4, tagInfoLong2, tagInfoLong3, tagInfoShort12, tagInfoShorts5, tagInfoShorts6, tagInfoAscii6, tagInfoAscii7, tagInfoAscii8, tagInfoAscii9, tagInfoShort13, tagInfoRationals3, tagInfoRationals4, tagInfoShorts7, tagInfoShorts8, tagInfoShortOrLong6, tagInfoShortOrLong7, tagInfoLongs3, tagInfoShortOrLong8, tagInfoShort14, tagInfoAscii10, tagInfoShort15, tagInfoByteOrShort, tagInfoAscii11, tagInfoShorts9, tagInfoShorts10, tagInfoAny, tagInfoAny2, tagInfoShorts11, tagInfoShort16, tagInfoLong4, tagInfoLong5, tagInfoShort17, tagInfoShorts12, tagInfoShorts13, tagInfoLongs4, tagInfoLongs5, tagInfoLongs6, tagInfoRationals5, tagInfoShorts14, tagInfoShort18, tagInfoLongs7, tagInfoAscii12, tagInfoBytes));
    }

    private TiffTagConstants() {
    }
}