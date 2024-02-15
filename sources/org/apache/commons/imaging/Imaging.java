package org.apache.commons.imaging;

import java.awt.Dimension;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.icc.IccProfileInfo;
import org.apache.commons.imaging.icc.IccProfileParser;
import org.apache.commons.imaging.internal.Util;
import org.tensorflow.lite.schema.BuiltinOperator;

/* loaded from: classes2.dex */
public final class Imaging {
    private static final int[] MAGIC_NUMBERS_GIF = {71, 73};
    private static final int[] MAGIC_NUMBERS_PNG = {137, 80};
    private static final int[] MAGIC_NUMBERS_JPEG = {255, 216};
    private static final int[] MAGIC_NUMBERS_BMP = {66, 77};
    private static final int[] MAGIC_NUMBERS_TIFF_MOTOROLA = {77, 77};
    private static final int[] MAGIC_NUMBERS_TIFF_INTEL = {73, 73};
    private static final int[] MAGIC_NUMBERS_PAM = {80, 55};
    private static final int[] MAGIC_NUMBERS_PSD = {56, 66};
    private static final int[] MAGIC_NUMBERS_PBM_A = {80, 49};
    private static final int[] MAGIC_NUMBERS_PBM_B = {80, 52};
    private static final int[] MAGIC_NUMBERS_PGM_A = {80, 50};
    private static final int[] MAGIC_NUMBERS_PGM_B = {80, 53};
    private static final int[] MAGIC_NUMBERS_PPM_A = {80, 51};
    private static final int[] MAGIC_NUMBERS_PPM_B = {80, 54};
    private static final int[] MAGIC_NUMBERS_JBIG2_1 = {BuiltinOperator.DYNAMIC_UPDATE_SLICE, 74};
    private static final int[] MAGIC_NUMBERS_JBIG2_2 = {66, 50};
    private static final int[] MAGIC_NUMBERS_ICNS = {105, 99};
    private static final int[] MAGIC_NUMBERS_DCX = {177, 104};
    private static final int[] MAGIC_NUMBERS_RGBE = {35, 63};

    private Imaging() {
    }

    public static boolean hasImageFileExtension(File file) {
        if (file == null || !file.isFile()) {
            return false;
        }
        return hasImageFileExtension(file.getName());
    }

    public static boolean hasImageFileExtension(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        for (ImageParser<?> imageParser : ImageParser.getAllImageParsers()) {
            for (String str2 : imageParser.getAcceptedExtensions()) {
                if (lowerCase.endsWith(str2.toLowerCase(Locale.ENGLISH))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ImageFormat guessFormat(byte[] bArr) throws IOException {
        return guessFormat(new ByteSourceArray(bArr));
    }

    public static ImageFormat guessFormat(File file) throws IOException {
        return guessFormat(new ByteSourceFile(file));
    }

    private static boolean compareBytePair(int[] iArr, int[] iArr2) {
        if (iArr.length == 2 || iArr2.length == 2) {
            return iArr[0] == iArr2[0] && iArr[1] == iArr2[1];
        }
        throw new IllegalArgumentException("Invalid Byte Pair.");
    }

    public static ImageFormat guessFormat(final ByteSource byteSource) throws IOException {
        if (byteSource == null) {
            return ImageFormats.UNKNOWN;
        }
        InputStream inputStream = byteSource.getInputStream();
        try {
            int read = inputStream.read();
            int read2 = inputStream.read();
            if (read < 0 || read2 < 0) {
                throw new IllegalArgumentException("Couldn't read magic numbers to guess format.");
            }
            int[] iArr = {read & 255, read2 & 255};
            if (compareBytePair(MAGIC_NUMBERS_GIF, iArr)) {
                ImageFormats imageFormats = ImageFormats.GIF;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats;
            } else if (compareBytePair(MAGIC_NUMBERS_PNG, iArr)) {
                ImageFormats imageFormats2 = ImageFormats.PNG;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats2;
            } else if (compareBytePair(MAGIC_NUMBERS_JPEG, iArr)) {
                ImageFormats imageFormats3 = ImageFormats.JPEG;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats3;
            } else if (compareBytePair(MAGIC_NUMBERS_BMP, iArr)) {
                ImageFormats imageFormats4 = ImageFormats.BMP;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats4;
            } else if (compareBytePair(MAGIC_NUMBERS_TIFF_MOTOROLA, iArr)) {
                ImageFormats imageFormats5 = ImageFormats.TIFF;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats5;
            } else if (compareBytePair(MAGIC_NUMBERS_TIFF_INTEL, iArr)) {
                ImageFormats imageFormats6 = ImageFormats.TIFF;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats6;
            } else if (compareBytePair(MAGIC_NUMBERS_PSD, iArr)) {
                ImageFormats imageFormats7 = ImageFormats.PSD;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats7;
            } else if (compareBytePair(MAGIC_NUMBERS_PAM, iArr)) {
                ImageFormats imageFormats8 = ImageFormats.PAM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats8;
            } else if (compareBytePair(MAGIC_NUMBERS_PBM_A, iArr)) {
                ImageFormats imageFormats9 = ImageFormats.PBM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats9;
            } else if (compareBytePair(MAGIC_NUMBERS_PBM_B, iArr)) {
                ImageFormats imageFormats10 = ImageFormats.PBM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats10;
            } else if (compareBytePair(MAGIC_NUMBERS_PGM_A, iArr)) {
                ImageFormats imageFormats11 = ImageFormats.PGM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats11;
            } else if (compareBytePair(MAGIC_NUMBERS_PGM_B, iArr)) {
                ImageFormats imageFormats12 = ImageFormats.PGM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats12;
            } else if (compareBytePair(MAGIC_NUMBERS_PPM_A, iArr)) {
                ImageFormats imageFormats13 = ImageFormats.PPM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats13;
            } else if (compareBytePair(MAGIC_NUMBERS_PPM_B, iArr)) {
                ImageFormats imageFormats14 = ImageFormats.PPM;
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormats14;
            } else {
                if (compareBytePair(MAGIC_NUMBERS_JBIG2_1, iArr)) {
                    int read3 = inputStream.read();
                    int read4 = inputStream.read();
                    if (read3 < 0 || read4 < 0) {
                        throw new IllegalArgumentException("Couldn't read magic numbers to guess format.");
                    }
                    if (compareBytePair(MAGIC_NUMBERS_JBIG2_2, new int[]{read3 & 255, read4 & 255})) {
                        ImageFormats imageFormats15 = ImageFormats.JBIG2;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return imageFormats15;
                    }
                } else if (compareBytePair(MAGIC_NUMBERS_ICNS, iArr)) {
                    ImageFormats imageFormats16 = ImageFormats.ICNS;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return imageFormats16;
                } else if (compareBytePair(MAGIC_NUMBERS_DCX, iArr)) {
                    ImageFormats imageFormats17 = ImageFormats.DCX;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return imageFormats17;
                } else if (compareBytePair(MAGIC_NUMBERS_RGBE, iArr)) {
                    ImageFormats imageFormats18 = ImageFormats.RGBE;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return imageFormats18;
                }
                ImageFormat imageFormat = (ImageFormat) Stream.of((Object[]) ImageFormats.values()).filter(new Predicate() { // from class: org.apache.commons.imaging.Imaging$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean anyMatch;
                        anyMatch = Stream.of((Object[]) ((ImageFormats) obj).getExtensions()).anyMatch(new Predicate() { // from class: org.apache.commons.imaging.Imaging$$ExternalSyntheticLambda0
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj2) {
                                return Imaging.lambda$guessFormat$0(ByteSource.this, (String) obj2);
                            }
                        });
                        return anyMatch;
                    }
                }).findFirst().orElse(ImageFormats.UNKNOWN);
                if (inputStream != null) {
                    inputStream.close();
                }
                return imageFormat;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static /* synthetic */ boolean lambda$guessFormat$0(ByteSource byteSource, String str) {
        String fileName = byteSource.getFileName();
        if (fileName == null || fileName.trim().length() == 0) {
            return false;
        }
        return str != null && str.trim().length() > 0 && fileName.substring(fileName.lastIndexOf(46) + 1).equalsIgnoreCase(str);
    }

    public static ICC_Profile getICCProfile(byte[] bArr) throws ImageReadException, IOException {
        return getICCProfile(new ByteSourceArray(bArr));
    }

    public static ICC_Profile getICCProfile(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getICCProfile(new ByteSourceInputStream(inputStream, str));
    }

    public static ICC_Profile getICCProfile(File file) throws ImageReadException, IOException {
        return getICCProfile(new ByteSourceFile(file));
    }

    protected static ICC_Profile getICCProfile(ByteSource byteSource) throws ImageReadException, IOException {
        IccProfileInfo iCCProfileInfo;
        byte[] iCCProfileBytes = getICCProfileBytes(byteSource);
        if (iCCProfileBytes == null || (iCCProfileInfo = new IccProfileParser().getICCProfileInfo(iCCProfileBytes)) == null || iCCProfileInfo.issRGB()) {
            return null;
        }
        return ICC_Profile.getInstance(iCCProfileBytes);
    }

    public static byte[] getICCProfileBytes(byte[] bArr) throws ImageReadException, IOException {
        return getICCProfileBytes(new ByteSourceArray(bArr));
    }

    public static byte[] getICCProfileBytes(File file) throws ImageReadException, IOException {
        return getICCProfileBytes(new ByteSourceFile(file));
    }

    private static byte[] getICCProfileBytes(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getICCProfileBytes(byteSource, (ByteSource) null);
    }

    public static ImageInfo getImageInfo(String str, byte[] bArr) throws ImageReadException, IOException {
        return getImageInfo(new ByteSourceArray(str, bArr));
    }

    public static ImageInfo getImageInfo(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getImageInfo(new ByteSourceInputStream(inputStream, str));
    }

    public static ImageInfo getImageInfo(byte[] bArr) throws ImageReadException, IOException {
        return getImageInfo(new ByteSourceArray(bArr));
    }

    public static ImageInfo getImageInfo(File file) throws ImageReadException, IOException {
        return getImageInfo(new ByteSourceFile(file));
    }

    private static ImageInfo getImageInfo(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getImageInfo(byteSource, (ByteSource) null);
    }

    public static Dimension getImageSize(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getImageSize(new ByteSourceInputStream(inputStream, str));
    }

    public static Dimension getImageSize(byte[] bArr) throws ImageReadException, IOException {
        return getImageSize(new ByteSourceArray(bArr));
    }

    public static Dimension getImageSize(File file) throws ImageReadException, IOException {
        return getImageSize(new ByteSourceFile(file));
    }

    public static Dimension getImageSize(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getImageSize(byteSource, (ByteSource) null);
    }

    public static String getXmpXml(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getXmpXml(new ByteSourceInputStream(inputStream, str));
    }

    public static String getXmpXml(byte[] bArr) throws ImageReadException, IOException {
        return getXmpXml(new ByteSourceArray(bArr));
    }

    public static String getXmpXml(File file) throws ImageReadException, IOException {
        return getXmpXml(new ByteSourceFile(file));
    }

    public static String getXmpXml(ByteSource byteSource) throws ImageReadException, IOException {
        ImageParser<?> imageParser = Util.getImageParser(byteSource);
        if (imageParser instanceof XmpEmbeddable) {
            return ((XmpEmbeddable) imageParser).getXmpXml(byteSource, null);
        }
        return null;
    }

    public static ImageMetadata getMetadata(byte[] bArr) throws ImageReadException, IOException {
        return getMetadata(new ByteSourceArray(bArr));
    }

    public static ImageMetadata getMetadata(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getMetadata(new ByteSourceInputStream(inputStream, str));
    }

    public static ImageMetadata getMetadata(File file) throws ImageReadException, IOException {
        return getMetadata(new ByteSourceFile(file));
    }

    private static ImageMetadata getMetadata(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getMetadata(byteSource, (ByteSource) null);
    }

    public static String dumpImageFile(byte[] bArr) throws ImageReadException, IOException {
        return dumpImageFile(new ByteSourceArray(bArr));
    }

    public static String dumpImageFile(File file) throws ImageReadException, IOException {
        return dumpImageFile(new ByteSourceFile(file));
    }

    private static String dumpImageFile(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).dumpImageFile(byteSource);
    }

    public static FormatCompliance getFormatCompliance(byte[] bArr) throws ImageReadException, IOException {
        return getFormatCompliance(new ByteSourceArray(bArr));
    }

    public static FormatCompliance getFormatCompliance(File file) throws ImageReadException, IOException {
        return getFormatCompliance(new ByteSourceFile(file));
    }

    private static FormatCompliance getFormatCompliance(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getFormatCompliance(byteSource);
    }

    public static List<BufferedImage> getAllBufferedImages(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getAllBufferedImages(new ByteSourceInputStream(inputStream, str));
    }

    public static List<BufferedImage> getAllBufferedImages(byte[] bArr) throws ImageReadException, IOException {
        return getAllBufferedImages(new ByteSourceArray(bArr));
    }

    public static List<BufferedImage> getAllBufferedImages(File file) throws ImageReadException, IOException {
        return getAllBufferedImages(new ByteSourceFile(file));
    }

    private static List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getAllBufferedImages(byteSource);
    }

    public static BufferedImage getBufferedImage(InputStream inputStream) throws ImageReadException, IOException {
        return getBufferedImage(inputStream, null);
    }

    public static BufferedImage getBufferedImage(InputStream inputStream, String str) throws ImageReadException, IOException {
        return getBufferedImage(new ByteSourceInputStream(inputStream, str));
    }

    public static BufferedImage getBufferedImage(byte[] bArr) throws ImageReadException, IOException {
        return getBufferedImage(new ByteSourceArray(bArr));
    }

    public static BufferedImage getBufferedImage(File file) throws ImageReadException, IOException {
        return getBufferedImage(new ByteSourceFile(file));
    }

    private static BufferedImage getBufferedImage(ByteSource byteSource) throws ImageReadException, IOException {
        return Util.getImageParser(byteSource).getBufferedImage(byteSource, (ByteSource) null);
    }

    public static void writeImage(BufferedImage bufferedImage, File file, ImageFormat imageFormat) throws ImageWriteException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            writeImage(bufferedImage, bufferedOutputStream, imageFormat);
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static byte[] writeImageToBytes(BufferedImage bufferedImage, ImageFormat imageFormat) throws ImageWriteException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeImage(bufferedImage, byteArrayOutputStream, imageFormat);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void writeImage(BufferedImage bufferedImage, OutputStream outputStream, ImageFormat imageFormat) throws ImageWriteException, IOException {
        Objects.requireNonNull(bufferedImage, "src must not be null");
        Objects.requireNonNull(outputStream, "os must not be null");
        Objects.requireNonNull(imageFormat, "format must not be null");
        Util.getImageParser(imageFormat).writeImage(bufferedImage, outputStream, null);
    }
}
