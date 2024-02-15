package org.apache.commons.imaging.formats.rgbe;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.GenericImageMetadata;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.tensorflow.lite.schema.BuiltinOptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class RgbeInfo implements Closeable {
    private static final byte[] HEADER = {BuiltinOptions.SplitOptions, BuiltinOptions.LogicalNotOptions, BuiltinOptions.AddNOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.FillOptions, BuiltinOptions.RangeOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.AbsOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions};
    private static final Pattern RESOLUTION_STRING = Pattern.compile("-Y (\\d+) \\+X (\\d+)");
    private static final byte[] TWO_TWO = {2, 2};
    private final InputStream in;
    private GenericImageMetadata metadata;
    private int width = -1;
    private int height = -1;

    public RgbeInfo(ByteSource byteSource) throws IOException {
        this.in = byteSource.getInputStream();
    }

    public ImageMetadata getMetadata() throws IOException, ImageReadException {
        if (this.metadata == null) {
            readMetadata();
        }
        return this.metadata;
    }

    public int getWidth() throws IOException, ImageReadException {
        if (-1 == this.width) {
            readDimensions();
        }
        return this.width;
    }

    public int getHeight() throws IOException, ImageReadException {
        if (-1 == this.height) {
            readDimensions();
        }
        return this.height;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    private void readDimensions() throws IOException, ImageReadException {
        getMetadata();
        String readNextLine = new InfoHeaderReader(this.in).readNextLine();
        Matcher matcher = RESOLUTION_STRING.matcher(readNextLine);
        if (!matcher.matches()) {
            throw new ImageReadException("Invalid HDR resolution string. Only \"-Y N +X M\" is supported. Found \"" + readNextLine + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        }
        this.height = Integer.parseInt(matcher.group(1));
        this.width = Integer.parseInt(matcher.group(2));
    }

    private void readMetadata() throws IOException, ImageReadException {
        BinaryFunctions.readAndVerifyBytes(this.in, HEADER, "Not a valid HDR: Incorrect Header");
        InfoHeaderReader infoHeaderReader = new InfoHeaderReader(this.in);
        if (!infoHeaderReader.readNextLine().isEmpty()) {
            throw new ImageReadException("Not a valid HDR: Incorrect Header");
        }
        this.metadata = new GenericImageMetadata();
        for (String readNextLine = infoHeaderReader.readNextLine(); !readNextLine.isEmpty(); readNextLine = infoHeaderReader.readNextLine()) {
            int indexOf = readNextLine.indexOf(61);
            if (indexOf > 0) {
                String substring = readNextLine.substring(0, indexOf);
                String substring2 = readNextLine.substring(indexOf + 1);
                if ("FORMAT".equals(substring2) && !"32-bit_rle_rgbe".equals(substring2)) {
                    throw new ImageReadException("Only 32-bit_rle_rgbe images are supported, trying to read " + substring2);
                }
                this.metadata.add(substring, substring2);
            } else {
                this.metadata.add("<command>", readNextLine);
            }
        }
    }

    public float[][] getPixelData() throws IOException, ImageReadException {
        int i;
        int i2;
        float[][] fArr;
        int height = getHeight();
        int width = getWidth();
        if (width >= 32768) {
            throw new ImageReadException("Scan lines must be less than 32768 bytes long");
        }
        byte[] bytes = ByteConversions.toBytes((short) width, ByteOrder.BIG_ENDIAN);
        byte[] bArr = new byte[width * 4];
        int i3 = 0;
        int i4 = 3;
        float[][] fArr2 = (float[][]) Array.newInstance(Float.TYPE, 3, width * height);
        int i5 = 0;
        while (i5 < height) {
            BinaryFunctions.readAndVerifyBytes(this.in, TWO_TWO, "Scan line " + i5 + " expected to start with 0x2 0x2");
            BinaryFunctions.readAndVerifyBytes(this.in, bytes, "Scan line " + i5 + " length expected");
            decompress(this.in, bArr);
            int i6 = i3;
            while (i6 < i4) {
                int i7 = i6 * width;
                int i8 = width * 3;
                int i9 = i3;
                while (i9 < width) {
                    int i10 = (i5 * width) + i9;
                    if ((bArr[i9 + i8] & UByte.MAX_VALUE) == 0) {
                        fArr2[i6][i10] = 0.0f;
                        fArr = fArr2;
                        i2 = i5;
                    } else {
                        i2 = i5;
                        fArr = fArr2;
                        fArr[i6][i10] = ((bArr[i9 + i7] & UByte.MAX_VALUE) + 0.5f) * ((float) Math.pow(2.0d, i - 136));
                    }
                    i9++;
                    i5 = i2;
                    fArr2 = fArr;
                }
                i6++;
                i3 = 0;
                i4 = 3;
            }
            i5++;
            i3 = 0;
            i4 = 3;
        }
        return fArr2;
    }

    private static void decompress(InputStream inputStream, byte[] bArr) throws IOException, ImageReadException {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int read = inputStream.read();
            if (read < 0) {
                throw new ImageReadException("Error decompressing RGBE file");
            }
            if (read > 128) {
                int read2 = inputStream.read();
                int i2 = 0;
                while (i2 < (read & 127)) {
                    bArr[i] = (byte) read2;
                    i2++;
                    i++;
                }
            } else {
                int i3 = 0;
                while (i3 < read) {
                    bArr[i] = (byte) inputStream.read();
                    i3++;
                    i++;
                }
            }
        }
    }
}
