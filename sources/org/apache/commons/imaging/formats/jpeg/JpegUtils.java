package org.apache.commons.imaging.formats.jpeg;

import com.tom_roush.fontbox.ttf.OpenTypeScript;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.internal.Debug;

/* loaded from: classes2.dex */
public class JpegUtils extends BinaryFileParser {

    /* loaded from: classes2.dex */
    public interface Visitor {
        boolean beginSOS();

        void visitSOS(int i, byte[] bArr, byte[] bArr2);

        boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException;
    }

    public static String getMarkerName(int i) {
        if (i != 65501) {
            switch (i) {
                case JpegConstants.SOF0_MARKER /* 65472 */:
                    return "SOF0_MARKER";
                case JpegConstants.SOF1_MARKER /* 65473 */:
                    return "SOF1_MARKER";
                case JpegConstants.SOF2_MARKER /* 65474 */:
                    return "SOF2_MARKER";
                case JpegConstants.SOF3_MARKER /* 65475 */:
                    return "SOF3_MARKER";
                case JpegConstants.DHT_MARKER /* 65476 */:
                    return "SOF4_MARKER";
                case JpegConstants.SOF5_MARKER /* 65477 */:
                    return "SOF5_MARKER";
                case JpegConstants.SOF6_MARKER /* 65478 */:
                    return "SOF6_MARKER";
                case JpegConstants.SOF7_MARKER /* 65479 */:
                    return "SOF7_MARKER";
                case JpegConstants.SOF8_MARKER /* 65480 */:
                    return "SOF8_MARKER";
                case JpegConstants.SOF9_MARKER /* 65481 */:
                    return "SOF9_MARKER";
                case JpegConstants.SOF10_MARKER /* 65482 */:
                    return "SOF10_MARKER";
                case JpegConstants.SOF11_MARKER /* 65483 */:
                    return "SOF11_MARKER";
                case JpegConstants.DAC_MARKER /* 65484 */:
                    return "DAC_MARKER";
                case JpegConstants.SOF13_MARKER /* 65485 */:
                    return "SOF13_MARKER";
                case JpegConstants.SOF14_MARKER /* 65486 */:
                    return "SOF14_MARKER";
                case JpegConstants.SOF15_MARKER /* 65487 */:
                    return "SOF15_MARKER";
                case JpegConstants.RST0_MARKER /* 65488 */:
                    return "RST0_MARKER";
                case JpegConstants.RST1_MARKER /* 65489 */:
                    return "RST1_MARKER";
                case JpegConstants.RST2_MARKER /* 65490 */:
                    return "RST2_MARKER";
                case JpegConstants.RST3_MARKER /* 65491 */:
                    return "RST3_MARKER";
                case JpegConstants.RST4_MARKER /* 65492 */:
                    return "RST4_MARKER";
                case JpegConstants.RST5_MARKER /* 65493 */:
                    return "RST5_MARKER";
                case JpegConstants.RST6_MARKER /* 65494 */:
                    return "RST6_MARKER";
                case JpegConstants.RST7_MARKER /* 65495 */:
                    return "RST7_MARKER";
                default:
                    switch (i) {
                        case JpegConstants.SOS_MARKER /* 65498 */:
                            return "SOS_MARKER";
                        case JpegConstants.DQT_MARKER /* 65499 */:
                            return "DQT_MARKER";
                        default:
                            switch (i) {
                                case 65504:
                                    return "JFIF_MARKER";
                                case JpegConstants.JPEG_APP1_MARKER /* 65505 */:
                                    return "JPEG_APP1_MARKER";
                                case JpegConstants.JPEG_APP2_MARKER /* 65506 */:
                                    return "JPEG_APP2_MARKER";
                                default:
                                    switch (i) {
                                        case JpegConstants.JPEG_APP13_MARKER /* 65517 */:
                                            return "JPEG_APP13_MARKER";
                                        case JpegConstants.JPEG_APP14_MARKER /* 65518 */:
                                            return "JPEG_APP14_MARKER";
                                        case JpegConstants.JPEG_APP15_MARKER /* 65519 */:
                                            return "JPEG_APP15_MARKER";
                                        default:
                                            return OpenTypeScript.UNKNOWN;
                                    }
                            }
                    }
            }
        }
        return "DRI_MARKER";
    }

    public JpegUtils() {
        setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    public void traverseJFIF(ByteSource byteSource, Visitor visitor) throws ImageReadException, IOException {
        byte[] bArr;
        byte readByte;
        byte b;
        int i;
        InputStream inputStream = byteSource.getInputStream();
        try {
            BinaryFunctions.readAndVerifyBytes(inputStream, JpegConstants.SOI, "Not a Valid JPEG File: doesn't begin with 0xffd8");
            int i2 = 0;
            while (true) {
                bArr = new byte[2];
                while (true) {
                    bArr[0] = bArr[1];
                    readByte = BinaryFunctions.readByte("marker", inputStream, "Could not read marker");
                    bArr[1] = readByte;
                    b = bArr[0];
                    if ((b & UByte.MAX_VALUE) == 255 && (readByte & UByte.MAX_VALUE) != 255) {
                        break;
                    }
                }
                i = (readByte & UByte.MAX_VALUE) | ((b & UByte.MAX_VALUE) << 8);
                if (i == 65497 || i == 65498) {
                    break;
                }
                byte[] readBytes = BinaryFunctions.readBytes("segmentLengthBytes", inputStream, 2, "segmentLengthBytes");
                int uInt16 = ByteConversions.toUInt16(readBytes, getByteOrder());
                if (uInt16 < 2) {
                    throw new ImageReadException("Invalid segment size");
                }
                if (!visitor.visitSegment(i, bArr, uInt16, readBytes, BinaryFunctions.readBytes("Segment Data", inputStream, uInt16 - 2, "Invalid Segment: insufficient data"))) {
                    if (inputStream != null) {
                        inputStream.close();
                        return;
                    }
                    return;
                }
                i2++;
            }
            if (!visitor.beginSOS()) {
                if (inputStream != null) {
                    inputStream.close();
                    return;
                }
                return;
            }
            visitor.visitSOS(i, bArr, BinaryFunctions.getStreamBytes(inputStream));
            Debug.debug(i2 + " markers");
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

    public void dumpJFIF(ByteSource byteSource) throws ImageReadException, IOException {
        traverseJFIF(byteSource, new Visitor() { // from class: org.apache.commons.imaging.formats.jpeg.JpegUtils.1
            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean beginSOS() {
                return true;
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
                Debug.debug("SOS marker.  " + bArr2.length + " bytes of image data.");
                Debug.debug("");
            }

            @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
                Debug.debug("Segment marker: " + Integer.toHexString(i) + " (" + JpegUtils.getMarkerName(i) + "), " + bArr3.length + " bytes of segment data.");
                return true;
            }
        });
    }
}
