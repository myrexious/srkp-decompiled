package com.tom_roush.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes3.dex */
public class VerticalMetricsTable extends TTFTable {
    public static final String TAG = "vmtx";
    private short[] additionalTopSideBearing;
    private int[] advanceHeight;
    private int numVMetrics;
    private short[] topSideBearing;

    public VerticalMetricsTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        VerticalHeaderTable verticalHeader = trueTypeFont.getVerticalHeader();
        if (verticalHeader == null) {
            throw new IOException("Could not get vhea table");
        }
        this.numVMetrics = verticalHeader.getNumberOfVMetrics();
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
        int i = this.numVMetrics;
        this.advanceHeight = new int[i];
        this.topSideBearing = new short[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.numVMetrics; i3++) {
            this.advanceHeight[i3] = tTFDataStream.readUnsignedShort();
            this.topSideBearing[i3] = tTFDataStream.readSignedShort();
            i2 += 4;
        }
        if (i2 < getLength()) {
            int i4 = numberOfGlyphs - this.numVMetrics;
            if (i4 >= 0) {
                numberOfGlyphs = i4;
            }
            this.additionalTopSideBearing = new short[numberOfGlyphs];
            for (int i5 = 0; i5 < numberOfGlyphs; i5++) {
                if (i2 < getLength()) {
                    this.additionalTopSideBearing[i5] = tTFDataStream.readSignedShort();
                    i2 += 2;
                }
            }
        }
        this.initialized = true;
    }

    public int getTopSideBearing(int i) {
        int i2 = this.numVMetrics;
        if (i < i2) {
            return this.topSideBearing[i];
        }
        return this.additionalTopSideBearing[i - i2];
    }

    public int getAdvanceHeight(int i) {
        if (i < this.numVMetrics) {
            return this.advanceHeight[i];
        }
        int[] iArr = this.advanceHeight;
        return iArr[iArr.length - 1];
    }
}
