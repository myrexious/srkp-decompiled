package com.tom_roush.fontbox.ttf;

import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class TrueTypeCollection implements Closeable {
    private final long[] fontOffsets;
    private final int numFonts;
    private final TTFDataStream stream;

    /* loaded from: classes3.dex */
    public interface TrueTypeFontProcessor {
        void process(TrueTypeFont trueTypeFont) throws IOException;
    }

    public TrueTypeCollection(File file) throws IOException {
        this(new RAFDataStream(file, PDPageLabelRange.STYLE_ROMAN_LOWER));
    }

    public TrueTypeCollection(InputStream inputStream) throws IOException {
        this(new MemoryTTFDataStream(inputStream));
    }

    TrueTypeCollection(TTFDataStream tTFDataStream) throws IOException {
        this.stream = tTFDataStream;
        if (!tTFDataStream.readTag().equals("ttcf")) {
            throw new IOException("Missing TTC header");
        }
        float read32Fixed = tTFDataStream.read32Fixed();
        int readUnsignedInt = (int) tTFDataStream.readUnsignedInt();
        this.numFonts = readUnsignedInt;
        if (readUnsignedInt <= 0 || readUnsignedInt > 1024) {
            throw new IOException("Invalid number of fonts " + readUnsignedInt);
        }
        this.fontOffsets = new long[readUnsignedInt];
        for (int i = 0; i < this.numFonts; i++) {
            this.fontOffsets[i] = tTFDataStream.readUnsignedInt();
        }
        if (read32Fixed >= 2.0f) {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
        }
    }

    public void processAllFonts(TrueTypeFontProcessor trueTypeFontProcessor) throws IOException {
        for (int i = 0; i < this.numFonts; i++) {
            trueTypeFontProcessor.process(getFontAtIndex(i));
        }
    }

    private TrueTypeFont getFontAtIndex(int i) throws IOException {
        TTFParser tTFParser;
        this.stream.seek(this.fontOffsets[i]);
        if (this.stream.readTag().equals("OTTO")) {
            tTFParser = new OTFParser(false, true);
        } else {
            tTFParser = new TTFParser(false, true);
        }
        this.stream.seek(this.fontOffsets[i]);
        return tTFParser.parse(new TTCDataStream(this.stream));
    }

    public TrueTypeFont getFontByName(String str) throws IOException {
        for (int i = 0; i < this.numFonts; i++) {
            TrueTypeFont fontAtIndex = getFontAtIndex(i);
            if (fontAtIndex.getName().equals(str)) {
                return fontAtIndex;
            }
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stream.close();
    }
}
