package org.apache.commons.imaging.formats.tiff.write;

import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;

/* loaded from: classes2.dex */
public abstract class TiffImageWriterBase {
    private static final int MAX_PIXELS_FOR_RGB = 1048576;
    protected final ByteOrder byteOrder;

    public abstract void write(OutputStream outputStream, TiffOutputSet tiffOutputSet) throws IOException, ImageWriteException;

    public TiffImageWriterBase() {
        this.byteOrder = TiffConstants.DEFAULT_TIFF_BYTE_ORDER;
    }

    public TiffImageWriterBase(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public static int imageDataPaddingLength(int i) {
        return (4 - (i % 4)) % 4;
    }

    public TiffOutputSummary validateDirectories(TiffOutputSet tiffOutputSet) throws ImageWriteException {
        List<TiffOutputDirectory> directories = tiffOutputSet.getDirectories();
        if (directories.isEmpty()) {
            throw new ImageWriteException("No directories.");
        }
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        TiffOutputDirectory tiffOutputDirectory = null;
        TiffOutputDirectory tiffOutputDirectory2 = null;
        TiffOutputDirectory tiffOutputDirectory3 = null;
        TiffOutputDirectory tiffOutputDirectory4 = null;
        TiffOutputField tiffOutputField = null;
        TiffOutputField tiffOutputField2 = null;
        TiffOutputField tiffOutputField3 = null;
        for (TiffOutputDirectory tiffOutputDirectory5 : directories) {
            int i = tiffOutputDirectory5.type;
            hashMap.put(Integer.valueOf(i), tiffOutputDirectory5);
            if (i < 0) {
                if (i != -4) {
                    if (i != -3) {
                        if (i != -2) {
                            throw new ImageWriteException("Unknown directory: " + i);
                        }
                        if (tiffOutputDirectory4 != null) {
                            throw new ImageWriteException("More than one EXIF directory.");
                        }
                        tiffOutputDirectory4 = tiffOutputDirectory5;
                    } else if (tiffOutputDirectory3 != null) {
                        throw new ImageWriteException("More than one GPS directory.");
                    } else {
                        tiffOutputDirectory3 = tiffOutputDirectory5;
                    }
                } else if (tiffOutputDirectory2 != null) {
                    throw new ImageWriteException("More than one Interoperability directory.");
                } else {
                    tiffOutputDirectory2 = tiffOutputDirectory5;
                }
            } else if (arrayList.contains(Integer.valueOf(i))) {
                throw new ImageWriteException("More than one directory with index: " + i + ".");
            } else {
                arrayList.add(Integer.valueOf(i));
            }
            HashSet hashSet = new HashSet();
            for (TiffOutputField tiffOutputField4 : tiffOutputDirectory5.getFields()) {
                if (hashSet.contains(Integer.valueOf(tiffOutputField4.tag))) {
                    throw new ImageWriteException("Tag (" + tiffOutputField4.tagInfo.getDescription() + ") appears twice in directory.");
                }
                hashSet.add(Integer.valueOf(tiffOutputField4.tag));
                if (tiffOutputField4.tag == ExifTagConstants.EXIF_TAG_EXIF_OFFSET.tag) {
                    if (tiffOutputField2 != null) {
                        throw new ImageWriteException("More than one Exif directory offset field.");
                    }
                    tiffOutputField2 = tiffOutputField4;
                } else if (tiffOutputField4.tag == ExifTagConstants.EXIF_TAG_INTEROP_OFFSET.tag) {
                    if (tiffOutputField != null) {
                        throw new ImageWriteException("More than one Interoperability directory offset field.");
                    }
                    tiffOutputField = tiffOutputField4;
                } else if (tiffOutputField4.tag != ExifTagConstants.EXIF_TAG_GPSINFO.tag) {
                    continue;
                } else if (tiffOutputField3 != null) {
                    throw new ImageWriteException("More than one GPS directory offset field.");
                } else {
                    tiffOutputField3 = tiffOutputField4;
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new ImageWriteException("Missing root directory.");
        }
        arrayList.sort(null);
        int i2 = 0;
        while (i2 < arrayList.size()) {
            Integer num = (Integer) arrayList.get(i2);
            if (num.intValue() != i2) {
                throw new ImageWriteException("Missing directory: " + i2 + ".");
            }
            TiffOutputDirectory tiffOutputDirectory6 = (TiffOutputDirectory) hashMap.get(num);
            if (tiffOutputDirectory != null) {
                tiffOutputDirectory.setNextDirectory(tiffOutputDirectory6);
            }
            i2++;
            tiffOutputDirectory = tiffOutputDirectory6;
        }
        TiffOutputDirectory tiffOutputDirectory7 = (TiffOutputDirectory) hashMap.get(0);
        TiffOutputSummary tiffOutputSummary = new TiffOutputSummary(this.byteOrder, tiffOutputDirectory7, hashMap);
        if (tiffOutputDirectory2 != null || tiffOutputField == null) {
            if (tiffOutputDirectory2 != null) {
                if (tiffOutputDirectory4 == null) {
                    tiffOutputDirectory4 = tiffOutputSet.addExifDirectory();
                }
                if (tiffOutputField == null) {
                    tiffOutputField = TiffOutputField.createOffsetField(ExifTagConstants.EXIF_TAG_INTEROP_OFFSET, this.byteOrder);
                    tiffOutputDirectory4.add(tiffOutputField);
                }
                tiffOutputSummary.add(tiffOutputDirectory2, tiffOutputField);
            }
            if (tiffOutputDirectory4 != null || tiffOutputField2 == null) {
                if (tiffOutputDirectory4 != null) {
                    if (tiffOutputField2 == null) {
                        tiffOutputField2 = TiffOutputField.createOffsetField(ExifTagConstants.EXIF_TAG_EXIF_OFFSET, this.byteOrder);
                        tiffOutputDirectory7.add(tiffOutputField2);
                    }
                    tiffOutputSummary.add(tiffOutputDirectory4, tiffOutputField2);
                }
                if (tiffOutputDirectory3 != null || tiffOutputField3 == null) {
                    if (tiffOutputDirectory3 != null) {
                        if (tiffOutputField3 == null) {
                            tiffOutputField3 = TiffOutputField.createOffsetField(ExifTagConstants.EXIF_TAG_GPSINFO, this.byteOrder);
                            tiffOutputDirectory7.add(tiffOutputField3);
                        }
                        tiffOutputSummary.add(tiffOutputDirectory3, tiffOutputField3);
                    }
                    return tiffOutputSummary;
                }
                throw new ImageWriteException("Output set has GPS Directory Offset field, but no GPS Directory");
            }
            throw new ImageWriteException("Output set has Exif Directory Offset field, but no Exif Directory");
        }
        throw new ImageWriteException("Output set has Interoperability Directory Offset field, but no Interoperability Directory");
    }

    private boolean checkForActualAlpha(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int i = 1048576 / width;
        int i2 = i < 1 ? 1 : i;
        int i3 = ((height + i2) - 1) / i2;
        int[] iArr = new int[i2 * width];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * i2;
            int i6 = i5 + i2;
            if (i6 > height) {
                i6 = height;
            }
            int i7 = i6 - i5;
            bufferedImage.getRGB(0, i5, width, i7, iArr, 0, width);
            int i8 = i7 * width;
            for (int i9 = 0; i9 < i8; i9++) {
                if ((iArr[i9] & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
                    return true;
                }
            }
        }
        return false;
    }

    private void applyPredictor(int i, int i2, byte[] bArr) {
        int i3 = i * i2;
        int length = bArr.length / i3;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i4 * i3;
            for (int i6 = i3 - 1; i6 >= i2; i6--) {
                int i7 = i5 + i6;
                bArr[i7] = (byte) (bArr[i7] - bArr[i7 - i2]);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:265:0x01c2 A[LOOP:1: B:263:0x01bf->B:265:0x01c2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0306  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeImage(java.awt.image.BufferedImage r23, java.io.OutputStream r24, org.apache.commons.imaging.formats.tiff.TiffImagingParameters r25) throws org.apache.commons.imaging.ImageWriteException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 793
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.tiff.write.TiffImageWriterBase.writeImage(java.awt.image.BufferedImage, java.io.OutputStream, org.apache.commons.imaging.formats.tiff.TiffImagingParameters):void");
    }

    private void combineUserExifIntoFinalExif(TiffOutputSet tiffOutputSet, TiffOutputSet tiffOutputSet2) throws ImageWriteException {
        List<TiffOutputDirectory> directories = tiffOutputSet2.getDirectories();
        directories.sort(TiffOutputDirectory.COMPARATOR);
        for (TiffOutputDirectory tiffOutputDirectory : tiffOutputSet.getDirectories()) {
            int binarySearch = Collections.binarySearch(directories, tiffOutputDirectory, TiffOutputDirectory.COMPARATOR);
            if (binarySearch < 0) {
                tiffOutputSet2.addDirectory(tiffOutputDirectory);
            } else {
                TiffOutputDirectory tiffOutputDirectory2 = directories.get(binarySearch);
                for (TiffOutputField tiffOutputField : tiffOutputDirectory.getFields()) {
                    if (tiffOutputDirectory2.findField(tiffOutputField.tagInfo) == null) {
                        tiffOutputDirectory2.add(tiffOutputField);
                    }
                }
            }
        }
    }

    private byte[][] getStrips(BufferedImage bufferedImage, int i, int i2, int i3) {
        char c;
        int i4 = i3;
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int i5 = ((height + i4) - 1) / i4;
        byte[][] bArr = new byte[i5];
        int i6 = height;
        int i7 = 0;
        while (i7 < i5) {
            int min = Math.min(i4, i6);
            i6 -= min;
            char c2 = '\b';
            byte[] bArr2 = new byte[min * ((((i2 * i) * width) + 7) / 8)];
            int i8 = i7 * i4;
            int i9 = i8 + i4;
            int i10 = 0;
            while (i8 < height && i8 < i9) {
                int i11 = 0;
                int i12 = 0;
                int i13 = 0;
                while (i11 < width) {
                    int rgb = bufferedImage.getRGB(i11, i8);
                    int i14 = (rgb >> 16) & 255;
                    int i15 = (rgb >> 8) & 255;
                    int i16 = width;
                    int i17 = (rgb >> 0) & 255;
                    int i18 = height;
                    if (i2 == 1) {
                        int i19 = (((i14 + i15) + i17) / 3 > 127 ? 0 : 1) | (i13 << 1);
                        int i20 = i12 + 1;
                        if (i20 == 8) {
                            bArr2[i10] = (byte) i19;
                            i10++;
                            i12 = 0;
                            i13 = 0;
                        } else {
                            i13 = i19;
                            i12 = i20;
                        }
                        c = '\b';
                    } else {
                        c = '\b';
                        if (i == 4) {
                            int i21 = i10 + 1;
                            bArr2[i10] = (byte) i14;
                            int i22 = i21 + 1;
                            bArr2[i21] = (byte) i15;
                            int i23 = i22 + 1;
                            bArr2[i22] = (byte) i17;
                            bArr2[i23] = (byte) (rgb >> 24);
                            i10 = i23 + 1;
                        } else {
                            int i24 = i10 + 1;
                            bArr2[i10] = (byte) i14;
                            int i25 = i24 + 1;
                            bArr2[i24] = (byte) i15;
                            bArr2[i25] = (byte) i17;
                            i10 = i25 + 1;
                        }
                    }
                    i11++;
                    c2 = c;
                    width = i16;
                    height = i18;
                }
                int i26 = width;
                int i27 = height;
                char c3 = c2;
                if (i12 > 0) {
                    bArr2[i10] = (byte) (i13 << (8 - i12));
                    i10++;
                }
                i8++;
                c2 = c3;
                width = i26;
                height = i27;
            }
            bArr[i7] = bArr2;
            i7++;
            i4 = i3;
            width = width;
            height = height;
        }
        return bArr;
    }

    public void writeImageFileHeader(BinaryOutputStream binaryOutputStream) throws IOException {
        writeImageFileHeader(binaryOutputStream, 8L);
    }

    public void writeImageFileHeader(BinaryOutputStream binaryOutputStream, long j) throws IOException {
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            binaryOutputStream.write(73);
            binaryOutputStream.write(73);
        } else {
            binaryOutputStream.write(77);
            binaryOutputStream.write(77);
        }
        binaryOutputStream.write2Bytes(42);
        binaryOutputStream.write4Bytes((int) j);
    }
}
