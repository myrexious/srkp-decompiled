package com.tom_roush.pdfbox.text;

import com.tom_roush.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequence;
import com.tom_roush.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequenceWithProperties;
import com.tom_roush.pdfbox.contentstream.operator.markedcontent.DrawObject;
import com.tom_roush.pdfbox.contentstream.operator.markedcontent.EndMarkedContentSequence;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDFMarkedContentExtractor extends LegacyPDFStreamEngine {
    private final Map<String, List<TextPosition>> characterListMapping;
    private final Deque<PDMarkedContent> currentMarkedContents;
    private final List<PDMarkedContent> markedContents;
    private boolean suppressDuplicateOverlappingText;

    private boolean within(float f, float f2, float f3) {
        return f2 > f - f3 && f2 < f + f3;
    }

    @Override // com.tom_roush.pdfbox.text.LegacyPDFStreamEngine, com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public /* bridge */ /* synthetic */ void processPage(PDPage pDPage) throws IOException {
        super.processPage(pDPage);
    }

    public PDFMarkedContentExtractor() throws IOException {
        this(null);
    }

    public PDFMarkedContentExtractor(String str) throws IOException {
        this.suppressDuplicateOverlappingText = true;
        this.markedContents = new ArrayList();
        this.currentMarkedContents = new ArrayDeque();
        this.characterListMapping = new HashMap();
        addOperator(new BeginMarkedContentSequenceWithProperties());
        addOperator(new BeginMarkedContentSequence());
        addOperator(new EndMarkedContentSequence());
        addOperator(new DrawObject());
    }

    public boolean isSuppressDuplicateOverlappingText() {
        return this.suppressDuplicateOverlappingText;
    }

    public void setSuppressDuplicateOverlappingText(boolean z) {
        this.suppressDuplicateOverlappingText = z;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void beginMarkedContentSequence(COSName cOSName, COSDictionary cOSDictionary) {
        PDMarkedContent create = PDMarkedContent.create(cOSName, cOSDictionary);
        if (this.currentMarkedContents.isEmpty()) {
            this.markedContents.add(create);
        } else {
            PDMarkedContent peek = this.currentMarkedContents.peek();
            if (peek != null) {
                peek.addMarkedContent(create);
            }
        }
        this.currentMarkedContents.push(create);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void endMarkedContentSequence() {
        if (this.currentMarkedContents.isEmpty()) {
            return;
        }
        this.currentMarkedContents.pop();
    }

    public void xobject(PDXObject pDXObject) {
        if (this.currentMarkedContents.isEmpty()) {
            return;
        }
        this.currentMarkedContents.peek().addXObject(pDXObject);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    @Override // com.tom_roush.pdfbox.text.LegacyPDFStreamEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void processTextPosition(com.tom_roush.pdfbox.text.TextPosition r11) {
        /*
            r10 = this;
            boolean r0 = r10.suppressDuplicateOverlappingText
            r1 = 1
            if (r0 == 0) goto L65
            java.lang.String r0 = r11.getUnicode()
            float r2 = r11.getX()
            float r3 = r11.getY()
            java.util.Map<java.lang.String, java.util.List<com.tom_roush.pdfbox.text.TextPosition>> r4 = r10.characterListMapping
            java.lang.Object r4 = r4.get(r0)
            java.util.List r4 = (java.util.List) r4
            if (r4 != 0) goto L25
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Map<java.lang.String, java.util.List<com.tom_roush.pdfbox.text.TextPosition>> r5 = r10.characterListMapping
            r5.put(r0, r4)
        L25:
            float r5 = r11.getWidth()
            int r0 = r0.length()
            float r0 = (float) r0
            float r5 = r5 / r0
            r0 = 1077936128(0x40400000, float:3.0)
            float r5 = r5 / r0
            java.util.Iterator r0 = r4.iterator()
        L36:
            boolean r6 = r0.hasNext()
            r7 = 0
            if (r6 == 0) goto L5f
            java.lang.Object r6 = r0.next()
            com.tom_roush.pdfbox.text.TextPosition r6 = (com.tom_roush.pdfbox.text.TextPosition) r6
            java.lang.String r8 = r6.getUnicode()
            float r9 = r6.getX()
            float r6 = r6.getY()
            if (r8 == 0) goto L36
            boolean r8 = r10.within(r9, r2, r5)
            if (r8 == 0) goto L36
            boolean r6 = r10.within(r6, r3, r5)
            if (r6 == 0) goto L36
            r0 = r1
            goto L60
        L5f:
            r0 = r7
        L60:
            if (r0 != 0) goto L66
            r4.add(r11)
        L65:
            r7 = r1
        L66:
            if (r7 == 0) goto Lc3
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L77
            r0.add(r11)
            goto Lb0
        L77:
            int r2 = r0.size()
            int r2 = r2 - r1
            java.lang.Object r2 = r0.get(r2)
            com.tom_roush.pdfbox.text.TextPosition r2 = (com.tom_roush.pdfbox.text.TextPosition) r2
            boolean r3 = r11.isDiacritic()
            if (r3 == 0) goto L92
            boolean r3 = r2.contains(r11)
            if (r3 == 0) goto L92
            r2.mergeDiacritic(r11)
            goto Lb0
        L92:
            boolean r3 = r2.isDiacritic()
            if (r3 == 0) goto Lad
            boolean r3 = r11.contains(r2)
            if (r3 == 0) goto Lad
            r11.mergeDiacritic(r2)
            int r2 = r0.size()
            int r2 = r2 - r1
            r0.remove(r2)
            r0.add(r11)
            goto Lb0
        Lad:
            r0.add(r11)
        Lb0:
            java.util.Deque<com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent> r0 = r10.currentMarkedContents
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto Lc3
            java.util.Deque<com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent> r0 = r10.currentMarkedContents
            java.lang.Object r0 = r0.peek()
            com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent r0 = (com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent) r0
            r0.addText(r11)
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFMarkedContentExtractor.processTextPosition(com.tom_roush.pdfbox.text.TextPosition):void");
    }

    public List<PDMarkedContent> getMarkedContents() {
        return this.markedContents;
    }
}
