package com.tom_roush.pdfbox.text;

import android.graphics.RectF;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDFTextStripperByArea extends PDFTextStripper {
    private final List<String> regions = new ArrayList();
    private final Map<String, RectF> regionArea = new HashMap();
    private final Map<String, ArrayList<List<TextPosition>>> regionCharacterList = new HashMap();
    private final Map<String, StringWriter> regionText = new HashMap();

    @Override // com.tom_roush.pdfbox.text.PDFTextStripper
    public final void setShouldSeparateByBeads(boolean z) {
    }

    public PDFTextStripperByArea() throws IOException {
        super.setShouldSeparateByBeads(false);
    }

    public void addRegion(String str, RectF rectF) {
        this.regions.add(str);
        this.regionArea.put(str, rectF);
    }

    public void removeRegion(String str) {
        this.regions.remove(str);
        this.regionArea.remove(str);
    }

    public List<String> getRegions() {
        return this.regions;
    }

    public String getTextForRegion(String str) {
        return this.regionText.get(str).toString();
    }

    public void extractRegions(PDPage pDPage) throws IOException {
        for (String str : this.regions) {
            setStartPage(getCurrentPageNo());
            setEndPage(getCurrentPageNo());
            ArrayList<List<TextPosition>> arrayList = new ArrayList<>();
            arrayList.add(new ArrayList());
            this.regionCharacterList.put(str, arrayList);
            this.regionText.put(str, new StringWriter());
        }
        if (pDPage.hasContents()) {
            processPage(pDPage);
        }
    }

    @Override // com.tom_roush.pdfbox.text.PDFTextStripper, com.tom_roush.pdfbox.text.LegacyPDFStreamEngine
    public void processTextPosition(TextPosition textPosition) {
        for (Map.Entry<String, RectF> entry : this.regionArea.entrySet()) {
            if (entry.getValue().contains(textPosition.getX(), textPosition.getY())) {
                this.charactersByArticle = this.regionCharacterList.get(entry.getKey());
                super.processTextPosition(textPosition);
            }
        }
    }

    @Override // com.tom_roush.pdfbox.text.PDFTextStripper
    public void writePage() throws IOException {
        for (String str : this.regionArea.keySet()) {
            this.charactersByArticle = this.regionCharacterList.get(str);
            this.output = this.regionText.get(str);
            super.writePage();
        }
    }
}
