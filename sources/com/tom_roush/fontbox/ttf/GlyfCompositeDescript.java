package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class GlyfCompositeDescript extends GlyfDescript {
    private boolean beingResolved;
    private final List<GlyfCompositeComp> components;
    private int contourCount;
    private final Map<Integer, GlyphDescription> descriptions;
    private GlyphTable glyphTable;
    private int pointCount;
    private boolean resolved;

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public boolean isComposite() {
        return true;
    }

    public GlyfCompositeDescript(TTFDataStream tTFDataStream, GlyphTable glyphTable) throws IOException {
        super((short) -1, tTFDataStream);
        GlyfCompositeComp glyfCompositeComp;
        this.components = new ArrayList();
        this.descriptions = new HashMap();
        this.beingResolved = false;
        this.resolved = false;
        this.pointCount = -1;
        this.contourCount = -1;
        this.glyphTable = glyphTable;
        do {
            glyfCompositeComp = new GlyfCompositeComp(tTFDataStream);
            this.components.add(glyfCompositeComp);
        } while ((glyfCompositeComp.getFlags() & 32) != 0);
        if ((glyfCompositeComp.getFlags() & OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING) != 0) {
            readInstructions(tTFDataStream, tTFDataStream.readUnsignedShort());
        }
        initDescriptions();
    }

    @Override // com.tom_roush.fontbox.ttf.GlyfDescript, com.tom_roush.fontbox.ttf.GlyphDescription
    public void resolve() {
        if (this.resolved) {
            return;
        }
        if (this.beingResolved) {
            Log.e("PdfBox-Android", "Circular reference in GlyfCompositeDesc");
            return;
        }
        this.beingResolved = true;
        int i = 0;
        int i2 = 0;
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            glyfCompositeComp.setFirstIndex(i);
            glyfCompositeComp.setFirstContour(i2);
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(glyfCompositeComp.getGlyphIndex()));
            if (glyphDescription != null) {
                glyphDescription.resolve();
                i += glyphDescription.getPointCount();
                i2 += glyphDescription.getContourCount();
            }
        }
        this.resolved = true;
        this.beingResolved = false;
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public int getEndPtOfContours(int i) {
        GlyfCompositeComp compositeCompEndPt = getCompositeCompEndPt(i);
        if (compositeCompEndPt != null) {
            return this.descriptions.get(Integer.valueOf(compositeCompEndPt.getGlyphIndex())).getEndPtOfContours(i - compositeCompEndPt.getFirstContour()) + compositeCompEndPt.getFirstIndex();
        }
        return 0;
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public byte getFlags(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp != null) {
            return this.descriptions.get(Integer.valueOf(compositeComp.getGlyphIndex())).getFlags(i - compositeComp.getFirstIndex());
        }
        return (byte) 0;
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public short getXCoordinate(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp != null) {
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(compositeComp.getGlyphIndex()));
            int firstIndex = i - compositeComp.getFirstIndex();
            return (short) (compositeComp.scaleX(glyphDescription.getXCoordinate(firstIndex), glyphDescription.getYCoordinate(firstIndex)) + compositeComp.getXTranslate());
        }
        return (short) 0;
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public short getYCoordinate(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp != null) {
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(compositeComp.getGlyphIndex()));
            int firstIndex = i - compositeComp.getFirstIndex();
            return (short) (compositeComp.scaleY(glyphDescription.getXCoordinate(firstIndex), glyphDescription.getYCoordinate(firstIndex)) + compositeComp.getYTranslate());
        }
        return (short) 0;
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public int getPointCount() {
        if (!this.resolved) {
            Log.e("PdfBox-Android", "getPointCount called on unresolved GlyfCompositeDescript");
        }
        if (this.pointCount < 0) {
            List<GlyfCompositeComp> list = this.components;
            GlyfCompositeComp glyfCompositeComp = list.get(list.size() - 1);
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(glyfCompositeComp.getGlyphIndex()));
            if (glyphDescription == null) {
                Log.e("PdfBox-Android", "GlyphDescription for index " + glyfCompositeComp.getGlyphIndex() + " is null, returning 0");
                this.pointCount = 0;
            } else {
                this.pointCount = glyfCompositeComp.getFirstIndex() + glyphDescription.getPointCount();
            }
        }
        return this.pointCount;
    }

    @Override // com.tom_roush.fontbox.ttf.GlyfDescript, com.tom_roush.fontbox.ttf.GlyphDescription
    public int getContourCount() {
        if (!this.resolved) {
            Log.e("PdfBox-Android", "getContourCount called on unresolved GlyfCompositeDescript");
        }
        if (this.contourCount < 0) {
            List<GlyfCompositeComp> list = this.components;
            GlyfCompositeComp glyfCompositeComp = list.get(list.size() - 1);
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(glyfCompositeComp.getGlyphIndex()));
            if (glyphDescription == null) {
                Log.e("PdfBox-Android", "missing glyph description for index " + glyfCompositeComp.getGlyphIndex());
                this.contourCount = 0;
            } else {
                this.contourCount = glyfCompositeComp.getFirstContour() + glyphDescription.getContourCount();
            }
        }
        return this.contourCount;
    }

    public int getComponentCount() {
        return this.components.size();
    }

    private GlyfCompositeComp getCompositeComp(int i) {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(glyfCompositeComp.getGlyphIndex()));
            if (glyfCompositeComp.getFirstIndex() <= i && glyphDescription != null && i < glyfCompositeComp.getFirstIndex() + glyphDescription.getPointCount()) {
                return glyfCompositeComp;
            }
        }
        return null;
    }

    private GlyfCompositeComp getCompositeCompEndPt(int i) {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            GlyphDescription glyphDescription = this.descriptions.get(Integer.valueOf(glyfCompositeComp.getGlyphIndex()));
            if (glyfCompositeComp.getFirstContour() <= i && glyphDescription != null && i < glyfCompositeComp.getFirstContour() + glyphDescription.getContourCount()) {
                return glyfCompositeComp;
            }
        }
        return null;
    }

    private void initDescriptions() {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            try {
                int glyphIndex = glyfCompositeComp.getGlyphIndex();
                GlyphData glyph = this.glyphTable.getGlyph(glyphIndex);
                if (glyph != null) {
                    this.descriptions.put(Integer.valueOf(glyphIndex), glyph.getDescription());
                }
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        }
    }
}
