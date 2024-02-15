package com.tom_roush.fontbox.ttf;

import com.tom_roush.fontbox.cff.CFFFont;
import com.tom_roush.fontbox.cff.CFFParser;
import java.io.IOException;

/* loaded from: classes3.dex */
public class CFFTable extends TTFTable {
    public static final String TAG = "CFF ";
    private CFFFont cffFont;

    public CFFTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.cffFont = new CFFParser().parse(tTFDataStream.read((int) getLength()), new CFFBytesource(this.font)).get(0);
        this.initialized = true;
    }

    public CFFFont getFont() {
        return this.cffFont;
    }

    /* loaded from: classes3.dex */
    private static class CFFBytesource implements CFFParser.ByteSource {
        private final TrueTypeFont ttf;

        CFFBytesource(TrueTypeFont trueTypeFont) {
            this.ttf = trueTypeFont;
        }

        @Override // com.tom_roush.fontbox.cff.CFFParser.ByteSource
        public byte[] getBytes() throws IOException {
            TrueTypeFont trueTypeFont = this.ttf;
            return trueTypeFont.getTableBytes(trueTypeFont.getTableMap().get(CFFTable.TAG));
        }
    }
}
