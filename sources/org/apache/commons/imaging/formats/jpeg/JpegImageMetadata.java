package org.apache.commons.imaging.formats.jpeg;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.tiff.JpegImageData;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.internal.Debug;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes2.dex */
public class JpegImageMetadata implements ImageMetadata {
    private static final String NEWLINE = System.getProperty(SystemProperties.LINE_SEPARATOR);
    private final TiffImageMetadata exif;
    private final JpegPhotoshopMetadata photoshop;

    public JpegImageMetadata(JpegPhotoshopMetadata jpegPhotoshopMetadata, TiffImageMetadata tiffImageMetadata) {
        this.photoshop = jpegPhotoshopMetadata;
        this.exif = tiffImageMetadata;
    }

    public TiffImageMetadata getExif() {
        return this.exif;
    }

    public JpegPhotoshopMetadata getPhotoshop() {
        return this.photoshop;
    }

    public TiffField findEXIFValue(TagInfo tagInfo) {
        try {
            TiffImageMetadata tiffImageMetadata = this.exif;
            if (tiffImageMetadata != null) {
                return tiffImageMetadata.findField(tagInfo);
            }
            return null;
        } catch (ImageReadException unused) {
            return null;
        }
    }

    public TiffField findEXIFValueWithExactMatch(TagInfo tagInfo) {
        try {
            TiffImageMetadata tiffImageMetadata = this.exif;
            if (tiffImageMetadata != null) {
                return tiffImageMetadata.findField(tagInfo, true);
            }
            return null;
        } catch (ImageReadException unused) {
            return null;
        }
    }

    public Dimension getEXIFThumbnailSize() throws ImageReadException, IOException {
        byte[] eXIFThumbnailData = getEXIFThumbnailData();
        if (eXIFThumbnailData != null) {
            return Imaging.getImageSize(eXIFThumbnailData);
        }
        return null;
    }

    public byte[] getEXIFThumbnailData() {
        byte[] bArr;
        TiffImageMetadata tiffImageMetadata = this.exif;
        if (tiffImageMetadata == null) {
            return null;
        }
        Iterator<? extends ImageMetadata.ImageMetadataItem> it = tiffImageMetadata.getDirectories().iterator();
        while (it.hasNext()) {
            TiffImageMetadata.Directory directory = (TiffImageMetadata.Directory) it.next();
            if (directory.getJpegImageData() != null) {
                bArr = directory.getJpegImageData().getData();
                continue;
            } else {
                bArr = null;
                continue;
            }
            if (bArr != null) {
                return bArr;
            }
        }
        return null;
    }

    public BufferedImage getEXIFThumbnail() throws ImageReadException, IOException {
        BufferedImage read;
        TiffImageMetadata tiffImageMetadata = this.exif;
        if (tiffImageMetadata == null) {
            return null;
        }
        Iterator<? extends ImageMetadata.ImageMetadataItem> it = tiffImageMetadata.getDirectories().iterator();
        while (it.hasNext()) {
            TiffImageMetadata.Directory directory = (TiffImageMetadata.Directory) it.next();
            BufferedImage thumbnail = directory.getThumbnail();
            if (thumbnail != null) {
                return thumbnail;
            }
            JpegImageData jpegImageData = directory.getJpegImageData();
            if (jpegImageData != null) {
                try {
                    read = Imaging.getBufferedImage(jpegImageData.getData());
                } catch (IOException | ImagingException unused) {
                    read = ImageIO.read(new ByteArrayInputStream(jpegImageData.getData()));
                } catch (Throwable th) {
                    ImageIO.read(new ByteArrayInputStream(jpegImageData.getData()));
                    throw th;
                }
                if (read != null) {
                    return read;
                }
            }
        }
        return null;
    }

    public TiffImageData getRawImageData() {
        TiffImageMetadata tiffImageMetadata = this.exif;
        if (tiffImageMetadata == null) {
            return null;
        }
        Iterator<? extends ImageMetadata.ImageMetadataItem> it = tiffImageMetadata.getDirectories().iterator();
        while (it.hasNext()) {
            TiffImageData tiffImageData = ((TiffImageMetadata.Directory) it.next()).getTiffImageData();
            if (tiffImageData != null) {
                return tiffImageData;
            }
        }
        return null;
    }

    @Override // org.apache.commons.imaging.common.ImageMetadata
    public List<ImageMetadata.ImageMetadataItem> getItems() {
        ArrayList arrayList = new ArrayList();
        TiffImageMetadata tiffImageMetadata = this.exif;
        if (tiffImageMetadata != null) {
            arrayList.addAll(tiffImageMetadata.getItems());
        }
        JpegPhotoshopMetadata jpegPhotoshopMetadata = this.photoshop;
        if (jpegPhotoshopMetadata != null) {
            arrayList.addAll(jpegPhotoshopMetadata.getItems());
        }
        return arrayList;
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
        sb.append(str);
        if (this.exif == null) {
            sb.append("No Exif metadata.");
        } else {
            sb.append("Exif metadata:");
            sb.append(NEWLINE);
            sb.append(this.exif.toString("\t"));
        }
        String str2 = NEWLINE;
        sb.append(str2);
        sb.append(str);
        if (this.photoshop == null) {
            sb.append("No Photoshop (IPTC) metadata.");
        } else {
            sb.append("Photoshop (IPTC) metadata:");
            sb.append(str2);
            sb.append(this.photoshop.toString("\t"));
        }
        return sb.toString();
    }

    public void dump() {
        Debug.debug(toString());
    }
}
