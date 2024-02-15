package org.apache.commons.imaging.formats.tiff.constants;

/* loaded from: classes2.dex */
public enum TiffDirectoryType {
    TIFF_DIRECTORY_IFD0(true, 0, "IFD0"),
    TIFF_DIRECTORY_IFD1(true, 1, "IFD1"),
    TIFF_DIRECTORY_IFD2(true, 2, "IFD2"),
    TIFF_DIRECTORY_IFD3(true, 3, "IFD3"),
    EXIF_DIRECTORY_INTEROP_IFD(false, -4, "Interop IFD"),
    EXIF_DIRECTORY_MAKER_NOTES(false, -5, "Maker Notes"),
    EXIF_DIRECTORY_EXIF_IFD(false, -2, "Exif IFD"),
    EXIF_DIRECTORY_GPS(false, -3, "GPS IFD");
    
    public static final TiffDirectoryType EXIF_DIRECTORY_IFD0;
    public static final TiffDirectoryType EXIF_DIRECTORY_IFD1;
    public static final TiffDirectoryType EXIF_DIRECTORY_IFD2;
    public static final TiffDirectoryType EXIF_DIRECTORY_IFD3;
    public static final TiffDirectoryType EXIF_DIRECTORY_SUB_IFD;
    public static final TiffDirectoryType EXIF_DIRECTORY_SUB_IFD1;
    public static final TiffDirectoryType EXIF_DIRECTORY_SUB_IFD2;
    public static final TiffDirectoryType EXIF_DIRECTORY_UNKNOWN;
    public static final TiffDirectoryType TIFF_DIRECTORY_ROOT;
    public final int directoryType;
    private final boolean isImageDirectory;
    public final String name;

    static {
        TiffDirectoryType tiffDirectoryType = TIFF_DIRECTORY_IFD0;
        TiffDirectoryType tiffDirectoryType2 = TIFF_DIRECTORY_IFD1;
        TiffDirectoryType tiffDirectoryType3 = TIFF_DIRECTORY_IFD2;
        TiffDirectoryType tiffDirectoryType4 = TIFF_DIRECTORY_IFD3;
        EXIF_DIRECTORY_IFD0 = tiffDirectoryType;
        TIFF_DIRECTORY_ROOT = tiffDirectoryType;
        EXIF_DIRECTORY_IFD1 = tiffDirectoryType2;
        EXIF_DIRECTORY_IFD2 = tiffDirectoryType3;
        EXIF_DIRECTORY_IFD3 = tiffDirectoryType4;
        EXIF_DIRECTORY_SUB_IFD = tiffDirectoryType2;
        EXIF_DIRECTORY_SUB_IFD1 = tiffDirectoryType3;
        EXIF_DIRECTORY_SUB_IFD2 = tiffDirectoryType4;
        EXIF_DIRECTORY_UNKNOWN = null;
    }

    TiffDirectoryType(boolean z, int i, String str) {
        this.isImageDirectory = z;
        this.directoryType = i;
        this.name = str;
    }

    public boolean isImageDirectory() {
        return this.isImageDirectory;
    }

    public static TiffDirectoryType getExifDirectoryType(int i) {
        TiffDirectoryType[] values;
        for (TiffDirectoryType tiffDirectoryType : values()) {
            if (tiffDirectoryType.directoryType == i) {
                return tiffDirectoryType;
            }
        }
        return EXIF_DIRECTORY_UNKNOWN;
    }
}
