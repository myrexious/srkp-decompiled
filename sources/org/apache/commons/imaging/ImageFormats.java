package org.apache.commons.imaging;

/* loaded from: classes2.dex */
public enum ImageFormats implements ImageFormat {
    UNKNOWN(new String[0]),
    BMP("bmp", "dib"),
    DCX("dcx"),
    GIF("gif"),
    ICNS("icns"),
    ICO("ico"),
    JBIG2(new String[0]),
    JPEG("jpg", "jpeg"),
    PAM("pam"),
    PSD("psd"),
    PBM("pbm"),
    PGM("pgm"),
    PNM("pnm"),
    PPM("ppm"),
    PCX("pcx", "pcc"),
    PNG("png"),
    RGBE("hdr", "pic"),
    TGA(new String[0]),
    TIFF("tif", "tiff"),
    WBMP("wbmp"),
    XBM("xbm"),
    XPM("xpm");
    
    private final String[] extensions;

    ImageFormats(String... strArr) {
        this.extensions = strArr;
    }

    @Override // org.apache.commons.imaging.ImageFormat
    public String getName() {
        return name();
    }

    @Override // org.apache.commons.imaging.ImageFormat
    public String[] getExtensions() {
        return (String[]) this.extensions.clone();
    }

    @Override // org.apache.commons.imaging.ImageFormat
    public String getDefaultExtension() {
        String[] strArr = this.extensions;
        if (strArr != null) {
            return strArr[0];
        }
        return null;
    }
}
