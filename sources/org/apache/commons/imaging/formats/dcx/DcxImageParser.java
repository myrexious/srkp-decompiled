package org.apache.commons.imaging.formats.dcx;

import androidx.fragment.app.FragmentTransaction;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;
import org.apache.commons.imaging.formats.pcx.PcxImagingParameters;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
public class DcxImageParser extends ImageParser<PcxImagingParameters> {
    private static final String DEFAULT_EXTENSION = ImageFormats.DCX.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.DCX.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Dcx-Custom";
    }

    public DcxImageParser() {
        super.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public PcxImagingParameters getDefaultParameters() {
        return new PcxImagingParameters();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String[] getAcceptedExtensions() {
        return ACCEPTED_EXTENSIONS;
    }

    @Override // org.apache.commons.imaging.ImageParser
    protected ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormats.DCX};
    }

    /* loaded from: classes2.dex */
    public static class DcxHeader {
        public static final int DCX_ID = 987654321;

        /* renamed from: id */
        public final int f33id;
        public final long[] pageTable;

        DcxHeader(int i, long[] jArr) {
            this.f33id = i;
            this.pageTable = jArr;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("DcxHeader");
            printWriter.println("Id: 0x" + Integer.toHexString(this.f33id));
            printWriter.println("Pages: " + this.pageTable.length);
            printWriter.println();
        }
    }

    private DcxHeader readDcxHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            int read4Bytes = BinaryFunctions.read4Bytes("Id", inputStream, "Not a Valid DCX File", getByteOrder());
            ArrayList arrayList = new ArrayList(1024);
            for (int i = 0; i < 1024; i++) {
                long read4Bytes2 = BinaryFunctions.read4Bytes("PageTable", inputStream, "Not a Valid DCX File", getByteOrder()) & BodyPartID.bodyIdMax;
                if (read4Bytes2 == 0) {
                    break;
                }
                arrayList.add(Long.valueOf(read4Bytes2));
            }
            if (read4Bytes != 987654321) {
                throw new ImageReadException("Not a Valid DCX File: file id incorrect");
            }
            if (arrayList.size() == 1024) {
                throw new ImageReadException("DCX page table not terminated by zero entry");
            }
            Object[] array = arrayList.toArray();
            long[] jArr = new long[array.length];
            for (int i2 = 0; i2 < array.length; i2++) {
                jArr[i2] = ((Long) array[i2]).longValue();
            }
            DcxHeader dcxHeader = new DcxHeader(read4Bytes, jArr);
            if (inputStream != null) {
                inputStream.close();
            }
            return dcxHeader;
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

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        readDcxHeader(byteSource).dump(printWriter);
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public final BufferedImage getBufferedImage(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        List<BufferedImage> allBufferedImages = getAllBufferedImages(byteSource);
        if (allBufferedImages.isEmpty()) {
            return null;
        }
        return allBufferedImages.get(0);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        DcxHeader readDcxHeader = readDcxHeader(byteSource);
        ArrayList arrayList = new ArrayList();
        PcxImageParser pcxImageParser = new PcxImageParser();
        for (long j : readDcxHeader.pageTable) {
            InputStream inputStream = byteSource.getInputStream(j);
            try {
                arrayList.add(pcxImageParser.getBufferedImage((ByteSource) new ByteSourceInputStream(inputStream, null), new PcxImagingParameters()));
                if (inputStream != null) {
                    inputStream.close();
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
        return arrayList;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PcxImagingParameters pcxImagingParameters) throws ImageWriteException, IOException {
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        binaryOutputStream.write4Bytes(DcxHeader.DCX_ID);
        binaryOutputStream.write4Bytes(FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_OPEN);
        for (int i = 0; i < 1023; i++) {
            binaryOutputStream.write4Bytes(0);
        }
        new PcxImageParser().writeImage(bufferedImage, (OutputStream) binaryOutputStream, pcxImagingParameters);
    }
}
