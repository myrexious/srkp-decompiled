package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes3.dex */
public class KerningSubtable {
    private static final int COVERAGE_CROSS_STREAM = 4;
    private static final int COVERAGE_CROSS_STREAM_SHIFT = 2;
    private static final int COVERAGE_FORMAT = 65280;
    private static final int COVERAGE_FORMAT_SHIFT = 8;
    private static final int COVERAGE_HORIZONTAL = 1;
    private static final int COVERAGE_HORIZONTAL_SHIFT = 0;
    private static final int COVERAGE_MINIMUMS = 2;
    private static final int COVERAGE_MINIMUMS_SHIFT = 1;
    private boolean crossStream;
    private boolean horizontal;
    private boolean minimums;
    private PairData pairs;

    /* loaded from: classes3.dex */
    public interface PairData {
        int getKerning(int i, int i2);

        void read(TTFDataStream tTFDataStream) throws IOException;
    }

    private static int getBits(int i, int i2, int i3) {
        return (i & i2) >> i3;
    }

    public void read(TTFDataStream tTFDataStream, int i) throws IOException {
        if (i == 0) {
            readSubtable0(tTFDataStream);
        } else if (i == 1) {
            readSubtable1(tTFDataStream);
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isHorizontalKerning() {
        return isHorizontalKerning(false);
    }

    public boolean isHorizontalKerning(boolean z) {
        if (this.horizontal && !this.minimums) {
            if (z) {
                return this.crossStream;
            }
            return !this.crossStream;
        }
        return false;
    }

    public int[] getKerning(int[] iArr) {
        int i;
        if (this.pairs != null) {
            int length = iArr.length;
            int[] iArr2 = new int[length];
            int i2 = 0;
            while (i2 < length) {
                int i3 = iArr[i2];
                int i4 = i2 + 1;
                int i5 = i4;
                while (true) {
                    if (i5 >= length) {
                        i = -1;
                        break;
                    }
                    i = iArr[i5];
                    if (i >= 0) {
                        break;
                    }
                    i5++;
                }
                iArr2[i2] = getKerning(i3, i);
                i2 = i4;
            }
            return iArr2;
        }
        Log.w("PdfBox-Android", "No kerning subtable data available due to an unsupported kerning subtable version");
        return null;
    }

    public int getKerning(int i, int i2) {
        PairData pairData = this.pairs;
        if (pairData == null) {
            Log.w("PdfBox-Android", "No kerning subtable data available due to an unsupported kerning subtable version");
            return 0;
        }
        return pairData.getKerning(i, i2);
    }

    private void readSubtable0(TTFDataStream tTFDataStream) throws IOException {
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort != 0) {
            Log.i("PdfBox-Android", "Unsupported kerning sub-table version: " + readUnsignedShort);
            return;
        }
        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort2 < 6) {
            throw new IOException("Kerning sub-table too short, got " + readUnsignedShort2 + " bytes, expect 6 or more.");
        }
        int readUnsignedShort3 = tTFDataStream.readUnsignedShort();
        if (isBitsSet(readUnsignedShort3, 1, 0)) {
            this.horizontal = true;
        }
        if (isBitsSet(readUnsignedShort3, 2, 1)) {
            this.minimums = true;
        }
        if (isBitsSet(readUnsignedShort3, 4, 2)) {
            this.crossStream = true;
        }
        int bits = getBits(readUnsignedShort3, 65280, 8);
        if (bits == 0) {
            readSubtable0Format0(tTFDataStream);
        } else if (bits == 2) {
            readSubtable0Format2(tTFDataStream);
        } else {
            Log.d("PdfBox-Android", "Skipped kerning subtable due to an unsupported kerning subtable version: " + bits);
        }
    }

    private void readSubtable0Format0(TTFDataStream tTFDataStream) throws IOException {
        PairData0Format0 pairData0Format0 = new PairData0Format0();
        this.pairs = pairData0Format0;
        pairData0Format0.read(tTFDataStream);
    }

    private void readSubtable0Format2(TTFDataStream tTFDataStream) {
        Log.i("PdfBox-Android", "Kerning subtable format 2 not yet supported.");
    }

    private void readSubtable1(TTFDataStream tTFDataStream) {
        Log.i("PdfBox-Android", "Kerning subtable format 1 not yet supported.");
    }

    private static boolean isBitsSet(int i, int i2, int i3) {
        return getBits(i, i2, i3) != 0;
    }

    /* loaded from: classes3.dex */
    public static class PairData0Format0 implements Comparator<int[]>, PairData {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int[][] pairs;
        private int searchRange;

        private PairData0Format0() {
        }

        @Override // com.tom_roush.fontbox.ttf.KerningSubtable.PairData
        public void read(TTFDataStream tTFDataStream) throws IOException {
            int readUnsignedShort = tTFDataStream.readUnsignedShort();
            this.searchRange = tTFDataStream.readUnsignedShort() / 6;
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
            this.pairs = (int[][]) Array.newInstance(Integer.TYPE, readUnsignedShort, 3);
            for (int i = 0; i < readUnsignedShort; i++) {
                int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
                int readUnsignedShort3 = tTFDataStream.readUnsignedShort();
                short readSignedShort = tTFDataStream.readSignedShort();
                int[] iArr = this.pairs[i];
                iArr[0] = readUnsignedShort2;
                iArr[1] = readUnsignedShort3;
                iArr[2] = readSignedShort;
            }
        }

        @Override // com.tom_roush.fontbox.ttf.KerningSubtable.PairData
        public int getKerning(int i, int i2) {
            int binarySearch = Arrays.binarySearch(this.pairs, new int[]{i, i2, 0}, this);
            if (binarySearch >= 0) {
                return this.pairs[binarySearch][2];
            }
            return 0;
        }

        @Override // java.util.Comparator
        public int compare(int[] iArr, int[] iArr2) {
            int i = iArr[0];
            int i2 = iArr2[0];
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int i3 = iArr[1];
            int i4 = iArr2[1];
            if (i3 < i4) {
                return -1;
            }
            return i3 > i4 ? 1 : 0;
        }
    }
}
