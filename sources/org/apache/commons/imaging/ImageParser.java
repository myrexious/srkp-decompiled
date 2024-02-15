package org.apache.commons.imaging;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImagingParameters;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BufferedImageFactory;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.SimpleBufferedImageFactory;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.dcx.DcxImageParser;
import org.apache.commons.imaging.formats.gif.GifImageParser;
import org.apache.commons.imaging.formats.icns.IcnsImageParser;
import org.apache.commons.imaging.formats.ico.IcoImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;
import org.apache.commons.imaging.formats.png.PngImageParser;
import org.apache.commons.imaging.formats.pnm.PnmImageParser;
import org.apache.commons.imaging.formats.psd.PsdImageParser;
import org.apache.commons.imaging.formats.rgbe.RgbeImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.wbmp.WbmpImageParser;
import org.apache.commons.imaging.formats.xbm.XbmImageParser;
import org.apache.commons.imaging.formats.xpm.XpmImageParser;

/* loaded from: classes2.dex */
public abstract class ImageParser<T extends ImagingParameters> extends BinaryFileParser {
    private static final Logger LOGGER = Logger.getLogger(ImageParser.class.getName());

    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        return false;
    }

    public abstract String[] getAcceptedExtensions();

    protected abstract ImageFormat[] getAcceptedTypes();

    public abstract BufferedImage getBufferedImage(ByteSource byteSource, T t) throws ImageReadException, IOException;

    public abstract String getDefaultExtension();

    public abstract T getDefaultParameters();

    public FormatCompliance getFormatCompliance(ByteSource byteSource) throws ImageReadException, IOException {
        return null;
    }

    public abstract byte[] getICCProfileBytes(ByteSource byteSource, T t) throws ImageReadException, IOException;

    public abstract ImageInfo getImageInfo(ByteSource byteSource, T t) throws ImageReadException, IOException;

    public abstract Dimension getImageSize(ByteSource byteSource, T t) throws ImageReadException, IOException;

    public abstract ImageMetadata getMetadata(ByteSource byteSource, T t) throws ImageReadException, IOException;

    public abstract String getName();

    public static List<ImageParser<?>> getAllImageParsers() {
        return Arrays.asList(new BmpImageParser(), new DcxImageParser(), new GifImageParser(), new IcnsImageParser(), new IcoImageParser(), new JpegImageParser(), new PcxImageParser(), new PngImageParser(), new PnmImageParser(), new PsdImageParser(), new RgbeImageParser(), new TiffImageParser(), new WbmpImageParser(), new XbmImageParser(), new XpmImageParser());
    }

    public final ImageMetadata getMetadata(ByteSource byteSource) throws ImageReadException, IOException {
        return getMetadata(byteSource, (ByteSource) null);
    }

    public final ImageMetadata getMetadata(byte[] bArr) throws ImageReadException, IOException {
        return getMetadata(bArr, (byte[]) null);
    }

    public final ImageMetadata getMetadata(byte[] bArr, T t) throws ImageReadException, IOException {
        return getMetadata((ByteSource) new ByteSourceArray(bArr), (ByteSourceArray) t);
    }

    public final ImageMetadata getMetadata(File file) throws ImageReadException, IOException {
        return getMetadata(file, (File) null);
    }

    public final ImageMetadata getMetadata(File file, T t) throws ImageReadException, IOException {
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest(getName() + ".getMetadata: " + file.getName());
        }
        if (canAcceptExtension(file)) {
            return getMetadata((ByteSource) new ByteSourceFile(file), (ByteSourceFile) t);
        }
        return null;
    }

    public final ImageInfo getImageInfo(ByteSource byteSource) throws ImageReadException, IOException {
        return getImageInfo(byteSource, (ByteSource) null);
    }

    public final ImageInfo getImageInfo(byte[] bArr, T t) throws ImageReadException, IOException {
        return getImageInfo((ByteSource) new ByteSourceArray(bArr), (ByteSourceArray) t);
    }

    public final ImageInfo getImageInfo(File file, T t) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            return getImageInfo((ByteSource) new ByteSourceFile(file), (ByteSourceFile) t);
        }
        return null;
    }

    public final FormatCompliance getFormatCompliance(byte[] bArr) throws ImageReadException, IOException {
        return getFormatCompliance(new ByteSourceArray(bArr));
    }

    public final FormatCompliance getFormatCompliance(File file) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            return getFormatCompliance(new ByteSourceFile(file));
        }
        return null;
    }

    public List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        BufferedImage bufferedImage = getBufferedImage(byteSource, (ByteSource) null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(bufferedImage);
        return arrayList;
    }

    public final List<BufferedImage> getAllBufferedImages(byte[] bArr) throws ImageReadException, IOException {
        return getAllBufferedImages(new ByteSourceArray(bArr));
    }

    public final List<BufferedImage> getAllBufferedImages(File file) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            return getAllBufferedImages(new ByteSourceFile(file));
        }
        return null;
    }

    public final BufferedImage getBufferedImage(byte[] bArr, T t) throws ImageReadException, IOException {
        return getBufferedImage((ByteSource) new ByteSourceArray(bArr), (ByteSourceArray) t);
    }

    public final BufferedImage getBufferedImage(File file, T t) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            return getBufferedImage((ByteSource) new ByteSourceFile(file), (ByteSourceFile) t);
        }
        return null;
    }

    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, T t) throws ImageWriteException, IOException {
        outputStream.close();
        throw new ImageWriteException("This image format (" + getName() + ") cannot be written.");
    }

    public final Dimension getImageSize(byte[] bArr) throws ImageReadException, IOException {
        return getImageSize(bArr, (byte[]) null);
    }

    public final Dimension getImageSize(byte[] bArr, T t) throws ImageReadException, IOException {
        return getImageSize((ByteSource) new ByteSourceArray(bArr), (ByteSourceArray) t);
    }

    public final Dimension getImageSize(File file) throws ImageReadException, IOException {
        return getImageSize(file, (File) null);
    }

    public final Dimension getImageSize(File file, T t) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            return getImageSize((ByteSource) new ByteSourceFile(file), (ByteSourceFile) t);
        }
        return null;
    }

    public final byte[] getICCProfileBytes(byte[] bArr) throws ImageReadException, IOException {
        return getICCProfileBytes(bArr, (byte[]) null);
    }

    public final byte[] getICCProfileBytes(byte[] bArr, T t) throws ImageReadException, IOException {
        return getICCProfileBytes((ByteSource) new ByteSourceArray(bArr), (ByteSourceArray) t);
    }

    public final byte[] getICCProfileBytes(File file) throws ImageReadException, IOException {
        return getICCProfileBytes(file, (File) null);
    }

    public final byte[] getICCProfileBytes(File file, T t) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest(getName() + ": " + file.getName());
            }
            return getICCProfileBytes((ByteSource) new ByteSourceFile(file), (ByteSourceFile) t);
        }
        return null;
    }

    public final String dumpImageFile(byte[] bArr) throws ImageReadException, IOException {
        return dumpImageFile(new ByteSourceArray(bArr));
    }

    public final String dumpImageFile(File file) throws ImageReadException, IOException {
        if (canAcceptExtension(file)) {
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest(getName() + ": " + file.getName());
            }
            return dumpImageFile(new ByteSourceFile(file));
        }
        return null;
    }

    public final String dumpImageFile(ByteSource byteSource) throws ImageReadException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        dumpImageFile(printWriter, byteSource);
        printWriter.flush();
        return stringWriter.toString();
    }

    public boolean canAcceptType(ImageFormat imageFormat) {
        for (ImageFormat imageFormat2 : getAcceptedTypes()) {
            if (imageFormat2.equals(imageFormat)) {
                return true;
            }
        }
        return false;
    }

    public boolean canAcceptExtension(File file) {
        return canAcceptExtension(file.getName());
    }

    public final boolean canAcceptExtension(String str) {
        String[] acceptedExtensions = getAcceptedExtensions();
        if (acceptedExtensions == null) {
            return true;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String lowerCase = str.substring(lastIndexOf + 1).toLowerCase(Locale.ENGLISH);
            for (String str2 : acceptedExtensions) {
                if (str2.equals(lowerCase)) {
                    return true;
                }
            }
        }
        return false;
    }

    public BufferedImageFactory getBufferedImageFactory(T t) {
        if (t == null) {
            return new SimpleBufferedImageFactory();
        }
        BufferedImageFactory bufferedImageFactory = t.getBufferedImageFactory();
        return bufferedImageFactory != null ? bufferedImageFactory : new SimpleBufferedImageFactory();
    }
}
