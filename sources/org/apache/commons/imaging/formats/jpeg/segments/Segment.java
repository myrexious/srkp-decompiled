package org.apache.commons.imaging.formats.jpeg.segments;

import com.tom_roush.fontbox.ttf.OpenTypeScript;
import java.io.PrintWriter;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;

/* loaded from: classes2.dex */
public abstract class Segment extends BinaryFileParser {
    public final int length;
    public final int marker;

    public void dump(PrintWriter printWriter) {
    }

    public abstract String getDescription();

    public Segment(int i, int i2) {
        this.marker = i;
        this.length = i2;
    }

    public String toString() {
        return "[Segment: " + getDescription() + "]";
    }

    public String getSegmentType() {
        int i = this.marker;
        if (i != 65281) {
            if (i != 65534) {
                switch (i) {
                    case JpegConstants.SOF0_MARKER /* 65472 */:
                        return "Start Of Frame, Baseline Dct, Huffman coding";
                    case JpegConstants.SOF1_MARKER /* 65473 */:
                        return "Start Of Frame, Extended sequential Dct, Huffman coding";
                    case JpegConstants.SOF2_MARKER /* 65474 */:
                        return "Start Of Frame, Progressive Dct, Huffman coding";
                    case JpegConstants.SOF3_MARKER /* 65475 */:
                        return "Start Of Frame, Lossless (sequential), Huffman coding";
                    case JpegConstants.DHT_MARKER /* 65476 */:
                        return "Define Huffman table(s)";
                    case JpegConstants.SOF5_MARKER /* 65477 */:
                        return "Start Of Frame, Differential sequential Dct, Huffman coding";
                    case JpegConstants.SOF6_MARKER /* 65478 */:
                        return "Start Of Frame, Differential progressive Dct, Huffman coding";
                    case JpegConstants.SOF7_MARKER /* 65479 */:
                        return "Start Of Frame, Differential lossless (sequential), Huffman coding";
                    case JpegConstants.SOF8_MARKER /* 65480 */:
                        return "Start Of Frame, Reserved for JPEG extensions, arithmetic coding";
                    case JpegConstants.SOF9_MARKER /* 65481 */:
                        return "Start Of Frame, Extended sequential Dct, arithmetic coding";
                    case JpegConstants.SOF10_MARKER /* 65482 */:
                        return "Start Of Frame, Progressive Dct, arithmetic coding";
                    case JpegConstants.SOF11_MARKER /* 65483 */:
                        return "Start Of Frame, Lossless (sequential), arithmetic coding";
                    case JpegConstants.DAC_MARKER /* 65484 */:
                        return "Define arithmetic coding conditioning(s)";
                    case JpegConstants.SOF13_MARKER /* 65485 */:
                        return "Start Of Frame, Differential sequential Dct, arithmetic coding";
                    case JpegConstants.SOF14_MARKER /* 65486 */:
                        return "Start Of Frame, Differential progressive Dct, arithmetic coding";
                    case JpegConstants.SOF15_MARKER /* 65487 */:
                        return "Start Of Frame, Differential lossless (sequential), arithmetic coding";
                    case JpegConstants.RST0_MARKER /* 65488 */:
                        return "Restart with modulo 8 count 0";
                    case JpegConstants.RST1_MARKER /* 65489 */:
                        return "Restart with modulo 8 count 1";
                    case JpegConstants.RST2_MARKER /* 65490 */:
                        return "Restart with modulo 8 count 2";
                    case JpegConstants.RST3_MARKER /* 65491 */:
                        return "Restart with modulo 8 count 3";
                    case JpegConstants.RST4_MARKER /* 65492 */:
                        return "Restart with modulo 8 count 4";
                    case JpegConstants.RST5_MARKER /* 65493 */:
                        return "Restart with modulo 8 count 5";
                    case JpegConstants.RST6_MARKER /* 65494 */:
                        return "Restart with modulo 8 count 6";
                    case JpegConstants.RST7_MARKER /* 65495 */:
                        return "Restart with modulo 8 count 7";
                    case 65496:
                        return "Start of image";
                    case JpegConstants.EOI_MARKER /* 65497 */:
                        return "End of image";
                    case JpegConstants.SOS_MARKER /* 65498 */:
                        return "Start of scan";
                    case JpegConstants.DQT_MARKER /* 65499 */:
                        return "Define quantization table(s)";
                    case JpegConstants.DNL_MARKER /* 65500 */:
                        return "Define number of lines";
                    case JpegConstants.DRI_MARKER /* 65501 */:
                        return "Define restart interval";
                    case 65502:
                        return "Define hierarchical progression";
                    case 65503:
                        return "Expand reference component(s)";
                    default:
                        if (i < 65282 || i > 65471) {
                            if (i < 65504 || i > 65519) {
                                return (i < 65520 || i > 65533) ? OpenTypeScript.UNKNOWN : "JPG" + (this.marker - 65504);
                            }
                            return "APP" + (this.marker - 65504);
                        }
                        return "Reserved";
                }
            }
            return "Comment";
        }
        return "For temporary private use in arithmetic coding";
    }
}
