package com.tom_roush.fontbox.ttf;

import android.graphics.Path;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.util.BoundingBox;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class TrueTypeFont implements FontBoxFont, Closeable {
    private final TTFDataStream data;
    private Map<String, Integer> postScriptNames;
    private float version;
    private int numberOfGlyphs = -1;
    private int unitsPerEm = -1;
    protected Map<String, TTFTable> tables = new HashMap();
    private final List<String> enabledGsubFeatures = new ArrayList();

    public TrueTypeFont(TTFDataStream tTFDataStream) {
        this.data = tTFDataStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.data.close();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    public float getVersion() {
        return this.version;
    }

    public void setVersion(float f) {
        this.version = f;
    }

    public void addTable(TTFTable tTFTable) {
        this.tables.put(tTFTable.getTag(), tTFTable);
    }

    public Collection<TTFTable> getTables() {
        return this.tables.values();
    }

    public Map<String, TTFTable> getTableMap() {
        return this.tables;
    }

    public synchronized byte[] getTableBytes(TTFTable tTFTable) throws IOException {
        byte[] read;
        long currentPosition = this.data.getCurrentPosition();
        this.data.seek(tTFTable.getOffset());
        read = this.data.read((int) tTFTable.getLength());
        this.data.seek(currentPosition);
        return read;
    }

    public synchronized TTFTable getTable(String str) throws IOException {
        TTFTable tTFTable;
        tTFTable = this.tables.get(str);
        if (tTFTable != null && !tTFTable.getInitialized()) {
            readTable(tTFTable);
        }
        return tTFTable;
    }

    public NamingTable getNaming() throws IOException {
        return (NamingTable) getTable("name");
    }

    public PostScriptTable getPostScript() throws IOException {
        return (PostScriptTable) getTable(PostScriptTable.TAG);
    }

    public OS2WindowsMetricsTable getOS2Windows() throws IOException {
        return (OS2WindowsMetricsTable) getTable(OS2WindowsMetricsTable.TAG);
    }

    public MaximumProfileTable getMaximumProfile() throws IOException {
        return (MaximumProfileTable) getTable(MaximumProfileTable.TAG);
    }

    public HeaderTable getHeader() throws IOException {
        return (HeaderTable) getTable(HeaderTable.TAG);
    }

    public HorizontalHeaderTable getHorizontalHeader() throws IOException {
        return (HorizontalHeaderTable) getTable(HorizontalHeaderTable.TAG);
    }

    public HorizontalMetricsTable getHorizontalMetrics() throws IOException {
        return (HorizontalMetricsTable) getTable(HorizontalMetricsTable.TAG);
    }

    public IndexToLocationTable getIndexToLocation() throws IOException {
        return (IndexToLocationTable) getTable(IndexToLocationTable.TAG);
    }

    public GlyphTable getGlyph() throws IOException {
        return (GlyphTable) getTable(GlyphTable.TAG);
    }

    public CmapTable getCmap() throws IOException {
        return (CmapTable) getTable(CmapTable.TAG);
    }

    public VerticalHeaderTable getVerticalHeader() throws IOException {
        return (VerticalHeaderTable) getTable(VerticalHeaderTable.TAG);
    }

    public VerticalMetricsTable getVerticalMetrics() throws IOException {
        return (VerticalMetricsTable) getTable(VerticalMetricsTable.TAG);
    }

    public VerticalOriginTable getVerticalOrigin() throws IOException {
        return (VerticalOriginTable) getTable(VerticalOriginTable.TAG);
    }

    public KerningTable getKerning() throws IOException {
        return (KerningTable) getTable(KerningTable.TAG);
    }

    public GlyphSubstitutionTable getGsub() throws IOException {
        return (GlyphSubstitutionTable) getTable(GlyphSubstitutionTable.TAG);
    }

    public InputStream getOriginalData() throws IOException {
        return this.data.getOriginalData();
    }

    public long getOriginalDataSize() {
        return this.data.getOriginalDataSize();
    }

    public void readTable(TTFTable tTFTable) throws IOException {
        synchronized (this.data) {
            long currentPosition = this.data.getCurrentPosition();
            this.data.seek(tTFTable.getOffset());
            tTFTable.read(this, this.data);
            this.data.seek(currentPosition);
        }
    }

    public int getNumberOfGlyphs() throws IOException {
        if (this.numberOfGlyphs == -1) {
            MaximumProfileTable maximumProfile = getMaximumProfile();
            if (maximumProfile != null) {
                this.numberOfGlyphs = maximumProfile.getNumGlyphs();
            } else {
                this.numberOfGlyphs = 0;
            }
        }
        return this.numberOfGlyphs;
    }

    public int getUnitsPerEm() throws IOException {
        if (this.unitsPerEm == -1) {
            HeaderTable header = getHeader();
            if (header != null) {
                this.unitsPerEm = header.getUnitsPerEm();
            } else {
                this.unitsPerEm = 0;
            }
        }
        return this.unitsPerEm;
    }

    public int getAdvanceWidth(int i) throws IOException {
        HorizontalMetricsTable horizontalMetrics = getHorizontalMetrics();
        return horizontalMetrics != null ? horizontalMetrics.getAdvanceWidth(i) : ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    }

    public int getAdvanceHeight(int i) throws IOException {
        VerticalMetricsTable verticalMetrics = getVerticalMetrics();
        return verticalMetrics != null ? verticalMetrics.getAdvanceHeight(i) : ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public String getName() throws IOException {
        NamingTable naming = getNaming();
        if (naming != null) {
            return naming.getPostScriptName();
        }
        return null;
    }

    private synchronized void readPostScriptNames() throws IOException {
        if (this.postScriptNames == null && getPostScript() != null) {
            String[] glyphNames = getPostScript().getGlyphNames();
            if (glyphNames != null) {
                this.postScriptNames = new HashMap(glyphNames.length);
                for (int i = 0; i < glyphNames.length; i++) {
                    this.postScriptNames.put(glyphNames[i], Integer.valueOf(i));
                }
            } else {
                this.postScriptNames = new HashMap();
            }
        }
    }

    @Deprecated
    public CmapSubtable getUnicodeCmap() throws IOException {
        return getUnicodeCmap(true);
    }

    @Deprecated
    public CmapSubtable getUnicodeCmap(boolean z) throws IOException {
        return getUnicodeCmapImpl(z);
    }

    public CmapLookup getUnicodeCmapLookup() throws IOException {
        return getUnicodeCmapLookup(true);
    }

    public CmapLookup getUnicodeCmapLookup(boolean z) throws IOException {
        GlyphSubstitutionTable gsub;
        CmapSubtable unicodeCmapImpl = getUnicodeCmapImpl(z);
        return (this.enabledGsubFeatures.isEmpty() || (gsub = getGsub()) == null) ? unicodeCmapImpl : new SubstitutingCmapLookup(unicodeCmapImpl, gsub, Collections.unmodifiableList(this.enabledGsubFeatures));
    }

    private CmapSubtable getUnicodeCmapImpl(boolean z) throws IOException {
        CmapTable cmap = getCmap();
        if (cmap == null) {
            if (z) {
                throw new IOException("The TrueType font " + getName() + " does not contain a 'cmap' table");
            }
            return null;
        }
        CmapSubtable subtable = cmap.getSubtable(0, 4);
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 10);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(0, 3);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 1);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 0);
        }
        if (subtable == null) {
            if (z) {
                throw new IOException("The TrueType font does not contain a Unicode cmap");
            }
            return cmap.getCmaps().length > 0 ? cmap.getCmaps()[0] : subtable;
        }
        return subtable;
    }

    public int nameToGID(String str) throws IOException {
        Integer num;
        readPostScriptNames();
        Map<String, Integer> map = this.postScriptNames;
        if (map != null && (num = map.get(str)) != null && num.intValue() > 0 && num.intValue() < getMaximumProfile().getNumGlyphs()) {
            return num.intValue();
        }
        int parseUniName = parseUniName(str);
        if (parseUniName > -1) {
            return getUnicodeCmapLookup(false).getGlyphId(parseUniName);
        }
        return 0;
    }

    private int parseUniName(String str) {
        if (str.startsWith("uni") && str.length() == 7) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            int i = 3;
            while (true) {
                int i2 = i + 4;
                if (i2 > length) {
                    break;
                }
                try {
                    int parseInt = Integer.parseInt(str.substring(i, i2), 16);
                    if (parseInt <= 55295 || parseInt >= 57344) {
                        sb.append((char) parseInt);
                    }
                    i = i2;
                } catch (NumberFormatException unused) {
                }
            }
            String sb2 = sb.toString();
            if (sb2.length() == 0) {
                return -1;
            }
            return sb2.codePointAt(0);
        }
        return -1;
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public Path getPath(String str) throws IOException {
        GlyphData glyph = getGlyph().getGlyph(nameToGID(str));
        if (glyph == null) {
            return new Path();
        }
        return glyph.getPath();
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public float getWidth(String str) throws IOException {
        return getAdvanceWidth(nameToGID(str));
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public boolean hasGlyph(String str) throws IOException {
        return nameToGID(str) != 0;
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public BoundingBox getFontBBox() throws IOException {
        HeaderTable header = getHeader();
        short xMin = header.getXMin();
        short xMax = header.getXMax();
        short yMin = header.getYMin();
        short yMax = header.getYMax();
        float unitsPerEm = 1000.0f / getUnitsPerEm();
        return new BoundingBox(xMin * unitsPerEm, yMin * unitsPerEm, xMax * unitsPerEm, yMax * unitsPerEm);
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public List<Number> getFontMatrix() throws IOException {
        float unitsPerEm = (1000.0f / getUnitsPerEm()) * 0.001f;
        return Arrays.asList(Float.valueOf(unitsPerEm), 0, 0, Float.valueOf(unitsPerEm), 0, 0);
    }

    public void enableGsubFeature(String str) {
        this.enabledGsubFeatures.add(str);
    }

    public void disableGsubFeature(String str) {
        this.enabledGsubFeatures.remove(str);
    }

    public void enableVerticalSubstitutions() {
        enableGsubFeature("vrt2");
        enableGsubFeature("vert");
    }

    public String toString() {
        try {
            NamingTable naming = getNaming();
            return naming != null ? naming.getPostScriptName() : "(null)";
        } catch (IOException e) {
            return "(null - " + e.getMessage() + ")";
        }
    }
}
