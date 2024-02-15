package org.apache.commons.imaging.formats.psd.datareaders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.mylzw.BitsToByteInputStream;
import org.apache.commons.imaging.common.mylzw.MyBitInputStream;
import org.apache.commons.imaging.formats.psd.PsdHeaderInfo;
import org.apache.commons.imaging.formats.psd.PsdImageContents;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParser;

/* loaded from: classes2.dex */
public class UncompressedDataReader implements DataReader {
    private final DataParser dataParser;

    public UncompressedDataReader(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override // org.apache.commons.imaging.formats.psd.datareaders.DataReader
    public void readData(InputStream inputStream, BufferedImage bufferedImage, PsdImageContents psdImageContents, BinaryFileParser binaryFileParser) throws ImageReadException, IOException {
        PsdHeaderInfo psdHeaderInfo = psdImageContents.header;
        int i = psdHeaderInfo.columns;
        int i2 = psdHeaderInfo.rows;
        int basicChannelsCount = this.dataParser.getBasicChannelsCount();
        int i3 = psdHeaderInfo.depth;
        BitsToByteInputStream bitsToByteInputStream = new BitsToByteInputStream(new MyBitInputStream(inputStream, ByteOrder.BIG_ENDIAN), 8);
        try {
            int[][][] iArr = (int[][][]) Array.newInstance(Integer.TYPE, basicChannelsCount, i2, i);
            for (int i4 = 0; i4 < basicChannelsCount; i4++) {
                for (int i5 = 0; i5 < i2; i5++) {
                    for (int i6 = 0; i6 < i; i6++) {
                        iArr[i4][i5][i6] = (byte) bitsToByteInputStream.readBits(i3);
                    }
                }
            }
            this.dataParser.parseData(iArr, bufferedImage, psdImageContents);
            bitsToByteInputStream.close();
        } catch (Throwable th) {
            try {
                bitsToByteInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
