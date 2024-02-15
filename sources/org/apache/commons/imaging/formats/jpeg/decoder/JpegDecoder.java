package org.apache.commons.imaging.formats.jpeg.decoder;

import androidx.core.view.MotionEventCompat;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.color.ColorConversions;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;
import org.apache.commons.imaging.formats.jpeg.JpegUtils;
import org.apache.commons.imaging.formats.jpeg.segments.DhtSegment;
import org.apache.commons.imaging.formats.jpeg.segments.DqtSegment;
import org.apache.commons.imaging.formats.jpeg.segments.SofnSegment;
import org.apache.commons.imaging.formats.jpeg.segments.SosSegment;

/* loaded from: classes2.dex */
public class JpegDecoder extends BinaryFileParser implements JpegUtils.Visitor {
    private BufferedImage image;
    private ImageReadException imageReadException;
    private IOException ioException;
    private SofnSegment sofnSegment;
    private SosSegment sosSegment;
    private final DqtSegment.QuantizationTable[] quantizationTables = new DqtSegment.QuantizationTable[4];
    private final DhtSegment.HuffmanTable[] huffmanDCTables = new DhtSegment.HuffmanTable[4];
    private final DhtSegment.HuffmanTable[] huffmanACTables = new DhtSegment.HuffmanTable[4];
    private final float[][] scaledQuantizationTables = new float[4];
    private final int[] zz = new int[64];
    private final int[] blockInt = new int[64];
    private final float[] block = new float[64];

    private int extend(int i, int i2) {
        return i < (1 << (i2 + (-1))) ? i + ((-1) << i2) + 1 : i;
    }

    private static int fastRound(float f) {
        return (int) (f + 0.5f);
    }

    @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
    public boolean beginSOS() {
        return true;
    }

    @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
    public void visitSOS(int i, byte[] bArr, byte[] bArr2) {
        DirectColorModel directColorModel;
        WritableRaster createPackedRaster;
        JpegInputStream[] jpegInputStreamArr;
        int i2;
        int[] iArr;
        int i3;
        DirectColorModel directColorModel2;
        int i4;
        int i5;
        DirectColorModel directColorModel3;
        JpegInputStream jpegInputStream;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
        try {
            int read2Bytes = BinaryFunctions.read2Bytes("segmentLength", byteArrayInputStream, "Not a Valid JPEG File", getByteOrder());
            this.sosSegment = new SosSegment(i, BinaryFunctions.readBytes("SosSegment", byteArrayInputStream, read2Bytes - 2, "Not a Valid JPEG File"));
            int length = bArr2.length - read2Bytes;
            int[] iArr2 = new int[length];
            for (int i6 = 0; i6 < length; i6++) {
                iArr2[i6] = byteArrayInputStream.read();
            }
            int i7 = 0;
            int i8 = 0;
            for (int i9 = 0; i9 < this.sofnSegment.numberOfComponents; i9++) {
                i7 = Math.max(i7, this.sofnSegment.getComponents(i9).horizontalSamplingFactor);
                i8 = Math.max(i8, this.sofnSegment.getComponents(i9).verticalSamplingFactor);
            }
            int i10 = i7 * 8;
            int i11 = i8 * 8;
            int i12 = ((this.sofnSegment.width + i10) - 1) / i10;
            int i13 = ((this.sofnSegment.height + i11) - 1) / i11;
            Block[] allocateMCUMemory = allocateMCUMemory();
            int length2 = allocateMCUMemory.length;
            Block[] blockArr = new Block[length2];
            for (int i14 = 0; i14 < length2; i14++) {
                blockArr[i14] = new Block(i10, i11);
            }
            int[] iArr3 = new int[this.sofnSegment.numberOfComponents];
            int i15 = this.sofnSegment.numberOfComponents;
            if (i15 == 1) {
                directColorModel = new DirectColorModel(24, 16711680, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255);
                createPackedRaster = Raster.createPackedRaster(3, this.sofnSegment.width, this.sofnSegment.height, new int[]{16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255}, (Point) null);
            } else if (i15 == 3) {
                directColorModel = new DirectColorModel(24, 16711680, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255);
                createPackedRaster = Raster.createPackedRaster(3, this.sofnSegment.width, this.sofnSegment.height, new int[]{16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255}, (Point) null);
            } else if (i15 == 4) {
                directColorModel = new DirectColorModel(24, 16711680, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255);
                createPackedRaster = Raster.createPackedRaster(3, this.sofnSegment.width, this.sofnSegment.height, new int[]{16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255}, (Point) null);
            } else {
                throw new ImageReadException(this.sofnSegment.numberOfComponents + " components are invalid or unsupported");
            }
            DataBuffer dataBuffer = createPackedRaster.getDataBuffer();
            JpegInputStream[] splitByRstMarkers = splitByRstMarkers(iArr2);
            JpegInputStream jpegInputStream2 = splitByRstMarkers[0];
            int i16 = 0;
            int i17 = 0;
            while (i17 < i11 * i13) {
                int i18 = i16;
                WritableRaster writableRaster = createPackedRaster;
                int i19 = i13;
                int i20 = 0;
                while (i20 < i10 * i12) {
                    if (!jpegInputStream2.hasNext() && (i18 = i18 + 1) < splitByRstMarkers.length) {
                        jpegInputStream2 = splitByRstMarkers[i18];
                    }
                    readMCU(jpegInputStream2, iArr3, allocateMCUMemory);
                    rescaleMCU(allocateMCUMemory, i10, i11, blockArr);
                    int i21 = (this.sofnSegment.width * i17) + i20;
                    int i22 = i12;
                    int i23 = 0;
                    int i24 = 0;
                    while (true) {
                        if (i23 >= i11) {
                            jpegInputStreamArr = splitByRstMarkers;
                            i2 = i17;
                            iArr = iArr3;
                            break;
                        }
                        jpegInputStreamArr = splitByRstMarkers;
                        iArr = iArr3;
                        if (i17 + i23 >= this.sofnSegment.height) {
                            i2 = i17;
                            break;
                        }
                        int i25 = 0;
                        while (true) {
                            if (i25 >= i10) {
                                i3 = i17;
                                directColorModel2 = directColorModel;
                                i4 = i18;
                                break;
                            }
                            i4 = i18;
                            if (i20 + i25 >= this.sofnSegment.width) {
                                i3 = i17;
                                directColorModel2 = directColorModel;
                                break;
                            }
                            if (length2 == 4) {
                                int i26 = i24 + i25;
                                jpegInputStream = jpegInputStream2;
                                directColorModel3 = directColorModel;
                                i5 = i17;
                                dataBuffer.setElem(i21 + i25, ColorConversions.convertCMYKtoRGB(blockArr[0].samples[i26], blockArr[1].samples[i26], blockArr[2].samples[i26], blockArr[3].samples[i26]));
                            } else {
                                i5 = i17;
                                directColorModel3 = directColorModel;
                                jpegInputStream = jpegInputStream2;
                                if (length2 == 3) {
                                    int i27 = i24 + i25;
                                    dataBuffer.setElem(i21 + i25, YCbCrConverter.convertYCbCrToRGB(blockArr[0].samples[i27], blockArr[1].samples[i27], blockArr[2].samples[i27]));
                                } else if (allocateMCUMemory.length == 1) {
                                    int i28 = blockArr[0].samples[i24 + i25];
                                    dataBuffer.setElem(i21 + i25, (i28 << 16) | (i28 << 8) | i28);
                                    i25++;
                                    i18 = i4;
                                    jpegInputStream2 = jpegInputStream;
                                    directColorModel = directColorModel3;
                                    i17 = i5;
                                } else {
                                    throw new ImageReadException("Unsupported JPEG with " + allocateMCUMemory.length + " components");
                                }
                            }
                            i25++;
                            i18 = i4;
                            jpegInputStream2 = jpegInputStream;
                            directColorModel = directColorModel3;
                            i17 = i5;
                        }
                        i24 += i10;
                        i21 += this.sofnSegment.width;
                        i23++;
                        splitByRstMarkers = jpegInputStreamArr;
                        iArr3 = iArr;
                        i18 = i4;
                        jpegInputStream2 = jpegInputStream2;
                        directColorModel = directColorModel2;
                        i17 = i3;
                    }
                    i20 += i10;
                    i12 = i22;
                    splitByRstMarkers = jpegInputStreamArr;
                    iArr3 = iArr;
                    i18 = i18;
                    jpegInputStream2 = jpegInputStream2;
                    directColorModel = directColorModel;
                    i17 = i2;
                }
                i13 = i19;
                i17 += i11;
                i16 = i18;
                createPackedRaster = writableRaster;
                i12 = i12;
                iArr3 = iArr3;
                directColorModel = directColorModel;
            }
            DirectColorModel directColorModel4 = directColorModel;
            this.image = new BufferedImage(directColorModel4, createPackedRaster, directColorModel4.isAlphaPremultiplied(), new Properties());
        } catch (IOException e) {
            this.ioException = e;
        } catch (RuntimeException e2) {
            this.imageReadException = new ImageReadException("Error parsing JPEG", e2);
        } catch (ImageReadException e3) {
            this.imageReadException = e3;
        }
    }

    @Override // org.apache.commons.imaging.formats.jpeg.JpegUtils.Visitor
    public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException {
        DhtSegment.HuffmanTable[] huffmanTableArr;
        if (Arrays.binarySearch(new int[]{JpegConstants.SOF0_MARKER, JpegConstants.SOF1_MARKER, JpegConstants.SOF2_MARKER, JpegConstants.SOF3_MARKER, JpegConstants.SOF5_MARKER, JpegConstants.SOF6_MARKER, JpegConstants.SOF7_MARKER, JpegConstants.SOF9_MARKER, JpegConstants.SOF10_MARKER, JpegConstants.SOF11_MARKER, JpegConstants.SOF13_MARKER, JpegConstants.SOF14_MARKER, JpegConstants.SOF15_MARKER}, i) >= 0) {
            if (i != 65472) {
                throw new ImageReadException("Only sequential, baseline JPEGs are supported at the moment");
            }
            this.sofnSegment = new SofnSegment(i, bArr3);
        } else if (i == 65499) {
            for (DqtSegment.QuantizationTable quantizationTable : new DqtSegment(i, bArr3).quantizationTables) {
                if (quantizationTable.destinationIdentifier >= 0) {
                    int i3 = quantizationTable.destinationIdentifier;
                    DqtSegment.QuantizationTable[] quantizationTableArr = this.quantizationTables;
                    if (i3 < quantizationTableArr.length) {
                        quantizationTableArr[quantizationTable.destinationIdentifier] = quantizationTable;
                        int[] iArr = new int[64];
                        ZigZag.zigZagToBlock(quantizationTable.getElements(), iArr);
                        float[] fArr = new float[64];
                        for (int i4 = 0; i4 < 64; i4++) {
                            fArr[i4] = iArr[i4];
                        }
                        Dct.scaleDequantizationMatrix(fArr);
                        this.scaledQuantizationTables[quantizationTable.destinationIdentifier] = fArr;
                    }
                }
                throw new ImageReadException("Invalid quantization table identifier " + quantizationTable.destinationIdentifier);
            }
        } else if (i == 65476) {
            for (DhtSegment.HuffmanTable huffmanTable : new DhtSegment(i, bArr3).huffmanTables) {
                if (huffmanTable.tableClass == 0) {
                    huffmanTableArr = this.huffmanDCTables;
                } else if (huffmanTable.tableClass == 1) {
                    huffmanTableArr = this.huffmanACTables;
                } else {
                    throw new ImageReadException("Invalid huffman table class " + huffmanTable.tableClass);
                }
                if (huffmanTable.destinationIdentifier < 0 || huffmanTable.destinationIdentifier >= huffmanTableArr.length) {
                    throw new ImageReadException("Invalid huffman table identifier " + huffmanTable.destinationIdentifier);
                }
                huffmanTableArr[huffmanTable.destinationIdentifier] = huffmanTable;
            }
        }
        return true;
    }

    private void rescaleMCU(Block[] blockArr, int i, int i2, Block[] blockArr2) {
        for (int i3 = 0; i3 < blockArr.length; i3++) {
            Block block = blockArr[i3];
            if (block.width == i && block.height == i2) {
                System.arraycopy(block.samples, 0, blockArr2[i3].samples, 0, i * i2);
            } else {
                int i4 = i / block.width;
                int i5 = i2 / block.height;
                if (i4 == 2 && i5 == 2) {
                    int i6 = 0;
                    int i7 = 0;
                    for (int i8 = 0; i8 < block.height; i8++) {
                        for (int i9 = 0; i9 < i; i9++) {
                            int i10 = block.samples[(i9 >> 1) + i6];
                            blockArr2[i3].samples[i7 + i9] = i10;
                            blockArr2[i3].samples[i7 + i + i9] = i10;
                        }
                        i6 += block.width;
                        i7 += i * 2;
                    }
                } else {
                    int i11 = 0;
                    for (int i12 = 0; i12 < i2; i12++) {
                        for (int i13 = 0; i13 < i; i13++) {
                            blockArr2[i3].samples[i11 + i13] = block.samples[((i12 / i5) * block.width) + (i13 / i4)];
                        }
                        i11 += i;
                    }
                }
            }
        }
    }

    private Block[] allocateMCUMemory() throws ImageReadException {
        SofnSegment.Component component;
        Block[] blockArr = new Block[this.sosSegment.numberOfComponents];
        for (int i = 0; i < this.sosSegment.numberOfComponents; i++) {
            SosSegment.Component components = this.sosSegment.getComponents(i);
            int i2 = 0;
            while (true) {
                if (i2 >= this.sofnSegment.numberOfComponents) {
                    component = null;
                    break;
                } else if (this.sofnSegment.getComponents(i2).componentIdentifier == components.scanComponentSelector) {
                    component = this.sofnSegment.getComponents(i2);
                    break;
                } else {
                    i2++;
                }
            }
            if (component == null) {
                throw new ImageReadException("Invalid component");
            }
            blockArr[i] = new Block(component.horizontalSamplingFactor * 8, component.verticalSamplingFactor * 8);
        }
        return blockArr;
    }

    private void readMCU(JpegInputStream jpegInputStream, int[] iArr, Block[] blockArr) throws ImageReadException {
        SofnSegment.Component component;
        int fastRound;
        int i = 0;
        int i2 = 0;
        while (i2 < this.sosSegment.numberOfComponents) {
            SosSegment.Component components = this.sosSegment.getComponents(i2);
            int i3 = i;
            while (true) {
                if (i3 >= this.sofnSegment.numberOfComponents) {
                    component = null;
                    break;
                } else if (this.sofnSegment.getComponents(i3).componentIdentifier == components.scanComponentSelector) {
                    component = this.sofnSegment.getComponents(i3);
                    break;
                } else {
                    i3++;
                }
            }
            if (component == null) {
                throw new ImageReadException("Invalid component");
            }
            Block block = blockArr[i2];
            int i4 = i;
            while (i4 < component.verticalSamplingFactor) {
                int i5 = i;
                while (i5 < component.horizontalSamplingFactor) {
                    Arrays.fill(this.zz, i);
                    int decode = decode(jpegInputStream, this.huffmanDCTables[components.dcCodingTableSelector]);
                    int extend = extend(receive(decode, jpegInputStream), decode);
                    int[] iArr2 = this.zz;
                    int i6 = iArr[i2] + extend;
                    iArr2[i] = i6;
                    iArr[i2] = i6;
                    int i7 = 1;
                    while (true) {
                        int decode2 = decode(jpegInputStream, this.huffmanACTables[components.acCodingTableSelector]);
                        int i8 = decode2 & 15;
                        int i9 = decode2 >> 4;
                        if (i8 != 0) {
                            int i10 = i7 + i9;
                            this.zz[i10] = receive(i8, jpegInputStream);
                            int[] iArr3 = this.zz;
                            iArr3[i10] = extend(iArr3[i10], i8);
                            if (i10 == 63) {
                                break;
                            }
                            i7 = i10 + 1;
                            i = 0;
                        } else if (i9 != 15) {
                            break;
                        } else {
                            i7 += 16;
                            i = 0;
                        }
                    }
                    int i11 = 1 << (this.sofnSegment.precision - 1);
                    int i12 = (1 << this.sofnSegment.precision) - 1;
                    float[] fArr = this.scaledQuantizationTables[component.quantTabDestSelector];
                    ZigZag.zigZagToBlock(this.zz, this.blockInt);
                    for (int i13 = i; i13 < 64; i13++) {
                        this.block[i13] = this.blockInt[i13] * fArr[i13];
                    }
                    Dct.inverseDCT8x8(this.block);
                    int i14 = (i4 * 8 * 8 * component.horizontalSamplingFactor) + (i5 * 8);
                    int i15 = i;
                    int i16 = i15;
                    while (i15 < 8) {
                        int i17 = i;
                        while (i17 < 8) {
                            int i18 = i16 + 1;
                            float f = this.block[i16] + i11;
                            if (f < 0.0f) {
                                fastRound = 0;
                            } else {
                                fastRound = f > ((float) i12) ? i12 : fastRound(f);
                            }
                            block.samples[i14 + i17] = fastRound;
                            i17++;
                            i16 = i18;
                        }
                        i14 += component.horizontalSamplingFactor * 8;
                        i15++;
                        i = 0;
                    }
                    i5++;
                    i = 0;
                }
                i4++;
                i = 0;
            }
            i2++;
            i = 0;
        }
    }

    static JpegInputStream[] splitByRstMarkers(int[] iArr) {
        List<Integer> intervalStartPositions = getIntervalStartPositions(iArr);
        int size = intervalStartPositions.size();
        JpegInputStream[] jpegInputStreamArr = new JpegInputStream[size];
        for (int i = 0; i < size; i++) {
            jpegInputStreamArr[i] = new JpegInputStream(Arrays.copyOfRange(iArr, intervalStartPositions.get(i).intValue(), i < size - 1 ? intervalStartPositions.get(i + 1).intValue() - 2 : iArr.length));
        }
        return jpegInputStreamArr;
    }

    static List<Integer> getIntervalStartPositions(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < iArr.length; i++) {
            if (z) {
                int i2 = iArr[i];
                if (i2 < 208 || i2 > 215) {
                    z = false;
                } else {
                    z2 = true;
                }
            }
            boolean z3 = iArr[i] != 255 ? z : true;
            if (z3 && z2) {
                arrayList.add(Integer.valueOf(i + 1));
                z = false;
                z2 = false;
            } else {
                z = z3;
            }
        }
        return arrayList;
    }

    private int receive(int i, JpegInputStream jpegInputStream) throws ImageReadException {
        int i2 = 0;
        int i3 = 0;
        while (i2 != i) {
            i2++;
            i3 = (i3 << 1) + jpegInputStream.nextBit();
        }
        return i3;
    }

    private int decode(JpegInputStream jpegInputStream, DhtSegment.HuffmanTable huffmanTable) throws ImageReadException {
        int nextBit = jpegInputStream.nextBit();
        int i = 1;
        while (nextBit > huffmanTable.getMaxCode(i)) {
            i++;
            nextBit = (nextBit << 1) | jpegInputStream.nextBit();
        }
        return huffmanTable.getHuffVal(huffmanTable.getValPtr(i) + (nextBit - huffmanTable.getMinCode(i)));
    }

    public BufferedImage decode(ByteSource byteSource) throws IOException, ImageReadException {
        new JpegUtils().traverseJFIF(byteSource, this);
        ImageReadException imageReadException = this.imageReadException;
        if (imageReadException != null) {
            throw imageReadException;
        }
        IOException iOException = this.ioException;
        if (iOException != null) {
            throw iOException;
        }
        return this.image;
    }
}
