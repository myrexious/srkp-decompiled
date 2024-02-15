package com.tom_roush.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes3.dex */
public class IndexToLocationTable extends TTFTable {
    private static final short LONG_OFFSETS = 1;
    private static final short SHORT_OFFSETS = 0;
    public static final String TAG = "loca";
    private long[] offsets;

    public IndexToLocationTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        HeaderTable header = trueTypeFont.getHeader();
        if (header == null) {
            throw new IOException("Could not get head table");
        }
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs() + 1;
        this.offsets = new long[numberOfGlyphs];
        for (int i = 0; i < numberOfGlyphs; i++) {
            if (header.getIndexToLocFormat() == 0) {
                this.offsets[i] = tTFDataStream.readUnsignedShort() * 2;
            } else if (header.getIndexToLocFormat() == 1) {
                this.offsets[i] = tTFDataStream.readUnsignedInt();
            } else {
                throw new IOException("Error:TTF.loca unknown offset format.");
            }
        }
        this.initialized = true;
    }

    public long[] getOffsets() {
        return this.offsets;
    }

    public void setOffsets(long[] jArr) {
        this.offsets = jArr;
    }
}
