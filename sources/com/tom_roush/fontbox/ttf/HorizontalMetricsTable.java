package com.tom_roush.fontbox.ttf;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.IOException;

/* loaded from: classes3.dex */
public class HorizontalMetricsTable extends TTFTable {
    public static final String TAG = "hmtx";
    private int[] advanceWidth;
    private short[] leftSideBearing;
    private short[] nonHorizontalLeftSideBearing;
    private int numHMetrics;

    public HorizontalMetricsTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        int i;
        HorizontalHeaderTable horizontalHeader = trueTypeFont.getHorizontalHeader();
        if (horizontalHeader == null) {
            throw new IOException("Could not get hmtx table");
        }
        this.numHMetrics = horizontalHeader.getNumberOfHMetrics();
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
        int i2 = this.numHMetrics;
        this.advanceWidth = new int[i2];
        this.leftSideBearing = new short[i2];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = this.numHMetrics;
            if (i3 >= i) {
                break;
            }
            this.advanceWidth[i3] = tTFDataStream.readUnsignedShort();
            this.leftSideBearing[i3] = tTFDataStream.readSignedShort();
            i4 += 4;
            i3++;
        }
        int i5 = numberOfGlyphs - i;
        if (i5 >= 0) {
            numberOfGlyphs = i5;
        }
        this.nonHorizontalLeftSideBearing = new short[numberOfGlyphs];
        if (i4 < getLength()) {
            for (int i6 = 0; i6 < numberOfGlyphs; i6++) {
                if (i4 < getLength()) {
                    this.nonHorizontalLeftSideBearing[i6] = tTFDataStream.readSignedShort();
                    i4 += 2;
                }
            }
        }
        this.initialized = true;
    }

    public int getAdvanceWidth(int i) {
        int[] iArr = this.advanceWidth;
        if (iArr.length == 0) {
            return ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        }
        if (i < this.numHMetrics) {
            return iArr[i];
        }
        return iArr[iArr.length - 1];
    }

    public int getLeftSideBearing(int i) {
        short[] sArr = this.leftSideBearing;
        if (sArr.length == 0) {
            return 0;
        }
        int i2 = this.numHMetrics;
        if (i < i2) {
            return sArr[i];
        }
        return this.nonHorizontalLeftSideBearing[i - i2];
    }
}
