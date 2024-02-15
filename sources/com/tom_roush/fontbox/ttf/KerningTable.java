package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;

/* loaded from: classes3.dex */
public class KerningTable extends TTFTable {
    public static final String TAG = "kern";
    private KerningSubtable[] subtables;

    public KerningTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        int i;
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort != 0) {
            readUnsignedShort = (readUnsignedShort << 16) | tTFDataStream.readUnsignedShort();
        }
        if (readUnsignedShort == 0) {
            i = tTFDataStream.readUnsignedShort();
        } else if (readUnsignedShort == 1) {
            i = (int) tTFDataStream.readUnsignedInt();
        } else {
            Log.d("PdfBox-Android", "Skipped kerning table due to an unsupported kerning table version: " + readUnsignedShort);
            i = 0;
        }
        if (i > 0) {
            this.subtables = new KerningSubtable[i];
            for (int i2 = 0; i2 < i; i2++) {
                KerningSubtable kerningSubtable = new KerningSubtable();
                kerningSubtable.read(tTFDataStream, readUnsignedShort);
                this.subtables[i2] = kerningSubtable;
            }
        }
        this.initialized = true;
    }

    public KerningSubtable getHorizontalKerningSubtable() {
        return getHorizontalKerningSubtable(false);
    }

    public KerningSubtable getHorizontalKerningSubtable(boolean z) {
        KerningSubtable[] kerningSubtableArr = this.subtables;
        if (kerningSubtableArr != null) {
            for (KerningSubtable kerningSubtable : kerningSubtableArr) {
                if (kerningSubtable.isHorizontalKerning(z)) {
                    return kerningSubtable;
                }
            }
            return null;
        }
        return null;
    }
}
