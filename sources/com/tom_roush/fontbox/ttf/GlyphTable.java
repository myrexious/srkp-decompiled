package com.tom_roush.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes3.dex */
public class GlyphTable extends TTFTable {
    private static final int MAX_CACHED_GLYPHS = 100;
    private static final int MAX_CACHE_SIZE = 5000;
    public static final String TAG = "glyf";
    private int cached;
    private TTFDataStream data;
    private GlyphData[] glyphs;
    private HorizontalMetricsTable hmt;
    private IndexToLocationTable loca;
    private int numGlyphs;

    public GlyphTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
        this.cached = 0;
        this.hmt = null;
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.loca = trueTypeFont.getIndexToLocation();
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
        this.numGlyphs = numberOfGlyphs;
        if (numberOfGlyphs < MAX_CACHE_SIZE) {
            this.glyphs = new GlyphData[numberOfGlyphs];
        }
        this.data = tTFDataStream;
        this.hmt = this.font.getHorizontalMetrics();
        this.initialized = true;
    }

    @Deprecated
    public GlyphData[] getGlyphs() throws IOException {
        GlyphData[] glyphDataArr;
        synchronized (this.data) {
            long[] offsets = this.loca.getOffsets();
            long j = offsets[this.numGlyphs];
            long offset = getOffset();
            if (this.glyphs == null) {
                this.glyphs = new GlyphData[this.numGlyphs];
            }
            int i = 0;
            while (i < this.numGlyphs && (j == 0 || j != offsets[i])) {
                int i2 = i + 1;
                long j2 = offsets[i2];
                long j3 = offsets[i];
                if (j2 > j3 && this.glyphs[i] == null) {
                    this.data.seek(j3 + offset);
                    GlyphData[] glyphDataArr2 = this.glyphs;
                    if (glyphDataArr2[i] == null) {
                        this.cached++;
                    }
                    glyphDataArr2[i] = getGlyphData(i);
                }
                i = i2;
            }
            this.initialized = true;
            glyphDataArr = this.glyphs;
        }
        return glyphDataArr;
    }

    public void setGlyphs(GlyphData[] glyphDataArr) {
        this.glyphs = glyphDataArr;
    }

    public GlyphData getGlyph(int i) throws IOException {
        GlyphData glyphData;
        int i2;
        GlyphData glyphData2;
        if (i < 0 || i >= this.numGlyphs) {
            return null;
        }
        GlyphData[] glyphDataArr = this.glyphs;
        if (glyphDataArr == null || (glyphData2 = glyphDataArr[i]) == null) {
            synchronized (this.data) {
                long[] offsets = this.loca.getOffsets();
                if (offsets[i] == offsets[i + 1]) {
                    glyphData = new GlyphData();
                    glyphData.initEmptyData();
                } else {
                    long currentPosition = this.data.getCurrentPosition();
                    this.data.seek(getOffset() + offsets[i]);
                    glyphData = getGlyphData(i);
                    this.data.seek(currentPosition);
                }
                GlyphData[] glyphDataArr2 = this.glyphs;
                if (glyphDataArr2 != null && glyphDataArr2[i] == null && (i2 = this.cached) < 100) {
                    glyphDataArr2[i] = glyphData;
                    this.cached = i2 + 1;
                }
            }
            return glyphData;
        }
        return glyphData2;
    }

    private GlyphData getGlyphData(int i) throws IOException {
        GlyphData glyphData = new GlyphData();
        HorizontalMetricsTable horizontalMetricsTable = this.hmt;
        glyphData.initData(this, this.data, horizontalMetricsTable == null ? 0 : horizontalMetricsTable.getLeftSideBearing(i));
        if (glyphData.getDescription().isComposite()) {
            glyphData.getDescription().resolve();
        }
        return glyphData;
    }
}
