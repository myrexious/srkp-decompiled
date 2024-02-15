package com.tom_roush.fontbox.ttf;

import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class TTFParser {
    private boolean isEmbedded;
    private boolean parseOnDemandOnly;

    protected boolean allowCFF() {
        return false;
    }

    public TTFParser() {
        this(false);
    }

    public TTFParser(boolean z) {
        this(z, false);
    }

    public TTFParser(boolean z, boolean z2) {
        this.isEmbedded = z;
        this.parseOnDemandOnly = z2;
    }

    public TrueTypeFont parse(String str) throws IOException {
        return parse(new File(str));
    }

    public TrueTypeFont parse(File file) throws IOException {
        RAFDataStream rAFDataStream = new RAFDataStream(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        try {
            return parse(rAFDataStream);
        } catch (IOException e) {
            rAFDataStream.close();
            throw e;
        }
    }

    public TrueTypeFont parse(InputStream inputStream) throws IOException {
        return parse(new MemoryTTFDataStream(inputStream));
    }

    public TrueTypeFont parseEmbedded(InputStream inputStream) throws IOException {
        this.isEmbedded = true;
        return parse(new MemoryTTFDataStream(inputStream));
    }

    public TrueTypeFont parse(TTFDataStream tTFDataStream) throws IOException {
        TrueTypeFont newFont = newFont(tTFDataStream);
        newFont.setVersion(tTFDataStream.read32Fixed());
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            TTFTable readTableDirectory = readTableDirectory(newFont, tTFDataStream);
            if (readTableDirectory != null) {
                if (readTableDirectory.getOffset() + readTableDirectory.getLength() > newFont.getOriginalDataSize()) {
                    Log.w("PdfBox-Android", "Skip table '" + readTableDirectory.getTag() + "' which goes past the file size; offset: " + readTableDirectory.getOffset() + ", size: " + readTableDirectory.getLength() + ", font size: " + newFont.getOriginalDataSize());
                } else {
                    newFont.addTable(readTableDirectory);
                }
            }
        }
        if (!this.parseOnDemandOnly) {
            parseTables(newFont);
        }
        return newFont;
    }

    TrueTypeFont newFont(TTFDataStream tTFDataStream) {
        return new TrueTypeFont(tTFDataStream);
    }

    private void parseTables(TrueTypeFont trueTypeFont) throws IOException {
        for (TTFTable tTFTable : trueTypeFont.getTables()) {
            if (!tTFTable.getInitialized()) {
                trueTypeFont.readTable(tTFTable);
            }
        }
        boolean containsKey = trueTypeFont.tables.containsKey(CFFTable.TAG);
        boolean z = allowCFF() && containsKey;
        if (trueTypeFont.getHeader() == null) {
            throw new IOException("'head' table is mandatory");
        }
        if (trueTypeFont.getHorizontalHeader() == null) {
            throw new IOException("'hhea' table is mandatory");
        }
        if (trueTypeFont.getMaximumProfile() == null) {
            throw new IOException("'maxp' table is mandatory");
        }
        if (trueTypeFont.getPostScript() == null && !this.isEmbedded) {
            throw new IOException("'post' table is mandatory");
        }
        if (!z) {
            String str = containsKey ? "; this an OpenType CFF font, but we expected a TrueType font here" : "";
            if (trueTypeFont.getIndexToLocation() == null) {
                throw new IOException("'loca' table is mandatory".concat(str));
            }
            if (trueTypeFont.getGlyph() == null) {
                throw new IOException("'glyf' table is mandatory".concat(str));
            }
        }
        if (trueTypeFont.getNaming() == null && !this.isEmbedded) {
            throw new IOException("'name' table is mandatory");
        }
        if (trueTypeFont.getHorizontalMetrics() == null) {
            throw new IOException("'hmtx' table is mandatory");
        }
        if (!this.isEmbedded && trueTypeFont.getCmap() == null) {
            throw new IOException("'cmap' table is mandatory");
        }
    }

    private TTFTable readTableDirectory(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        TTFTable readTable;
        String readString = tTFDataStream.readString(4);
        if (readString.equals(CmapTable.TAG)) {
            readTable = new CmapTable(trueTypeFont);
        } else if (readString.equals(GlyphTable.TAG)) {
            readTable = new GlyphTable(trueTypeFont);
        } else if (readString.equals(HeaderTable.TAG)) {
            readTable = new HeaderTable(trueTypeFont);
        } else if (readString.equals(HorizontalHeaderTable.TAG)) {
            readTable = new HorizontalHeaderTable(trueTypeFont);
        } else if (readString.equals(HorizontalMetricsTable.TAG)) {
            readTable = new HorizontalMetricsTable(trueTypeFont);
        } else if (readString.equals(IndexToLocationTable.TAG)) {
            readTable = new IndexToLocationTable(trueTypeFont);
        } else if (readString.equals(MaximumProfileTable.TAG)) {
            readTable = new MaximumProfileTable(trueTypeFont);
        } else if (readString.equals("name")) {
            readTable = new NamingTable(trueTypeFont);
        } else if (readString.equals(OS2WindowsMetricsTable.TAG)) {
            readTable = new OS2WindowsMetricsTable(trueTypeFont);
        } else if (readString.equals(PostScriptTable.TAG)) {
            readTable = new PostScriptTable(trueTypeFont);
        } else if (readString.equals(DigitalSignatureTable.TAG)) {
            readTable = new DigitalSignatureTable(trueTypeFont);
        } else if (readString.equals(KerningTable.TAG)) {
            readTable = new KerningTable(trueTypeFont);
        } else if (readString.equals(VerticalHeaderTable.TAG)) {
            readTable = new VerticalHeaderTable(trueTypeFont);
        } else if (readString.equals(VerticalMetricsTable.TAG)) {
            readTable = new VerticalMetricsTable(trueTypeFont);
        } else if (readString.equals(VerticalOriginTable.TAG)) {
            readTable = new VerticalOriginTable(trueTypeFont);
        } else if (readString.equals(GlyphSubstitutionTable.TAG)) {
            readTable = new GlyphSubstitutionTable(trueTypeFont);
        } else {
            readTable = readTable(trueTypeFont, readString);
        }
        readTable.setTag(readString);
        readTable.setCheckSum(tTFDataStream.readUnsignedInt());
        readTable.setOffset(tTFDataStream.readUnsignedInt());
        readTable.setLength(tTFDataStream.readUnsignedInt());
        if (readTable.getLength() != 0 || readString.equals(GlyphTable.TAG)) {
            return readTable;
        }
        return null;
    }

    public TTFTable readTable(TrueTypeFont trueTypeFont, String str) {
        return new TTFTable(trueTypeFont);
    }
}
