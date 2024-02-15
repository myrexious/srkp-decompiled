package com.tom_roush.pdfbox.text;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageTree;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation.PDThreadBead;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Bidi;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes3.dex */
public class PDFTextStripper extends LegacyPDFStreamEngine {
    private static final float END_OF_LAST_TEXT_X_RESET_VALUE = -1.0f;
    private static final float EXPECTED_START_OF_NEXT_WORD_X_RESET_VALUE = -3.4028235E38f;
    private static final float LAST_WORD_SPACING_RESET_VALUE = -1.0f;
    private static final String[] LIST_ITEM_EXPRESSIONS;
    private static final float MAX_HEIGHT_FOR_LINE_RESET_VALUE = -1.0f;
    private static final float MAX_Y_FOR_LINE_RESET_VALUE = -3.4028235E38f;
    private static final float MIN_Y_TOP_FOR_LINE_RESET_VALUE = Float.MAX_VALUE;
    private static Map<Character, Character> MIRRORING_CHAR_MAP = null;
    private static float defaultDropThreshold = 2.5f;
    private static float defaultIndentThreshold = 2.0f;
    protected final String LINE_SEPARATOR;
    private boolean addMoreFormatting;
    private String articleEnd;
    private String articleStart;
    private float averageCharTolerance;
    private List<PDRectangle> beadRectangles;
    private Map<String, TreeMap<Float, TreeSet<Float>>> characterListMapping;
    protected ArrayList<List<TextPosition>> charactersByArticle;
    private int currentPageNo;
    protected PDDocument document;
    private float dropThreshold;
    private PDOutlineItem endBookmark;
    private int endBookmarkPageNumber;
    private int endPage;
    private boolean inParagraph;
    private float indentThreshold;
    private String lineSeparator;
    private List<Pattern> listOfPatterns;
    protected Writer output;
    private String pageEnd;
    private String pageStart;
    private String paragraphEnd;
    private String paragraphStart;
    private boolean shouldSeparateByBeads;
    private boolean sortByPosition;
    private float spacingTolerance;
    private PDOutlineItem startBookmark;
    private int startBookmarkPageNumber;
    private int startPage;
    private boolean suppressDuplicateOverlappingText;
    private String wordSeparator;

    private boolean within(float f, float f2, float f3) {
        return f2 < f + f3 && f2 > f - f3;
    }

    protected void endDocument(PDDocument pDDocument) throws IOException {
    }

    protected void endPage(PDPage pDPage) throws IOException {
    }

    protected void startDocument(PDDocument pDDocument) throws IOException {
    }

    protected void startPage(PDPage pDPage) throws IOException {
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0083 A[Catch: all -> 0x00a8, IOException -> 0x00aa, TryCatch #0 {IOException -> 0x00aa, blocks: (B:72:0x007d, B:74:0x0083, B:76:0x009c, B:75:0x008e), top: B:95:0x007d, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x008e A[Catch: all -> 0x00a8, IOException -> 0x00aa, TryCatch #0 {IOException -> 0x00aa, blocks: (B:72:0x007d, B:74:0x0083, B:76:0x009c, B:75:0x008e), top: B:95:0x007d, outer: #2 }] */
    static {
        /*
            java.lang.String r0 = "Could not close BidiMirroring.txt "
            java.lang.String r1 = "PdfBox-Android"
            java.lang.String r2 = "Could not parse BidiMirroring.txt, mirroring char map will be empty: "
            r3 = 0
            java.lang.String r4 = "PDFTextStripper"
            java.lang.String r4 = r4.toLowerCase()     // Catch: java.lang.SecurityException -> L3c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.SecurityException -> L3c
            r5.<init>()     // Catch: java.lang.SecurityException -> L3c
            java.lang.StringBuilder r5 = r5.append(r4)     // Catch: java.lang.SecurityException -> L3c
            java.lang.String r6 = ".indent"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.SecurityException -> L3c
            java.lang.String r5 = r5.toString()     // Catch: java.lang.SecurityException -> L3c
            java.lang.String r5 = java.lang.System.getProperty(r5)     // Catch: java.lang.SecurityException -> L3c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.SecurityException -> L3d
            r6.<init>()     // Catch: java.lang.SecurityException -> L3d
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch: java.lang.SecurityException -> L3d
            java.lang.String r6 = ".drop"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch: java.lang.SecurityException -> L3d
            java.lang.String r4 = r4.toString()     // Catch: java.lang.SecurityException -> L3d
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch: java.lang.SecurityException -> L3d
            goto L3e
        L3c:
            r5 = r3
        L3d:
            r4 = r3
        L3e:
            if (r5 == 0) goto L4c
            int r6 = r5.length()
            if (r6 <= 0) goto L4c
            float r5 = java.lang.Float.parseFloat(r5)     // Catch: java.lang.NumberFormatException -> L4c
            com.tom_roush.pdfbox.text.PDFTextStripper.defaultIndentThreshold = r5     // Catch: java.lang.NumberFormatException -> L4c
        L4c:
            if (r4 == 0) goto L5a
            int r5 = r4.length()
            if (r5 <= 0) goto L5a
            float r4 = java.lang.Float.parseFloat(r4)     // Catch: java.lang.NumberFormatException -> L5a
            com.tom_roush.pdfbox.text.PDFTextStripper.defaultDropThreshold = r4     // Catch: java.lang.NumberFormatException -> L5a
        L5a:
            java.lang.String r5 = "\\."
            java.lang.String r6 = "\\d+\\."
            java.lang.String r7 = "\\[\\d+\\]"
            java.lang.String r8 = "\\d+\\)"
            java.lang.String r9 = "[A-Z]\\."
            java.lang.String r10 = "[a-z]\\."
            java.lang.String r11 = "[A-Z]\\)"
            java.lang.String r12 = "[a-z]\\)"
            java.lang.String r13 = "[IVXL]+\\."
            java.lang.String r14 = "[ivxl]+\\."
            java.lang.String[] r4 = new java.lang.String[]{r5, r6, r7, r8, r9, r10, r11, r12, r13, r14}
            com.tom_roush.pdfbox.text.PDFTextStripper.LIST_ITEM_EXPRESSIONS = r4
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            com.tom_roush.pdfbox.text.PDFTextStripper.MIRRORING_CHAR_MAP = r4
            java.lang.String r4 = "com/tom_roush/pdfbox/resources/text/BidiMirroring.txt"
            boolean r5 = com.tom_roush.pdfbox.android.PDFBoxResourceLoader.isReady()     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            if (r5 == 0) goto L8e
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            java.io.InputStream r4 = com.tom_roush.pdfbox.android.PDFBoxResourceLoader.getStream(r4)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            r5.<init>(r4)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            r3 = r5
            goto L9c
        L8e:
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            java.lang.Class<com.tom_roush.pdfbox.text.PDFTextStripper> r5 = com.tom_roush.pdfbox.text.PDFTextStripper.class
            java.lang.String r6 = "/com/tom_roush/pdfbox/resources/text/BidiMirroring.txt"
            java.io.InputStream r5 = r5.getResourceAsStream(r6)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            r4.<init>(r5)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            r3 = r4
        L9c:
            parseBidiFile(r3)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Laa
            r3.close()     // Catch: java.io.IOException -> La3
            goto Lc2
        La3:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
            goto Lc2
        La8:
            r2 = move-exception
            goto Lc3
        Laa:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8
            r5.<init>(r2)     // Catch: java.lang.Throwable -> La8
            java.lang.String r2 = r4.getMessage()     // Catch: java.lang.Throwable -> La8
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch: java.lang.Throwable -> La8
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> La8
            android.util.Log.w(r1, r2)     // Catch: java.lang.Throwable -> La8
            r3.close()     // Catch: java.io.IOException -> La3
        Lc2:
            return
        Lc3:
            r3.close()     // Catch: java.io.IOException -> Lc7
            goto Lcb
        Lc7:
            r3 = move-exception
            android.util.Log.e(r1, r0, r3)
        Lcb:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFTextStripper.<clinit>():void");
    }

    public PDFTextStripper() throws IOException {
        String property = System.getProperty(SystemProperties.LINE_SEPARATOR);
        this.LINE_SEPARATOR = property;
        this.lineSeparator = property;
        this.wordSeparator = StringUtils.SPACE;
        this.paragraphStart = "";
        this.paragraphEnd = "";
        this.pageStart = "";
        this.pageEnd = property;
        this.articleStart = "";
        this.articleEnd = "";
        this.currentPageNo = 0;
        this.startPage = 1;
        this.endPage = Integer.MAX_VALUE;
        this.startBookmark = null;
        this.startBookmarkPageNumber = -1;
        this.endBookmarkPageNumber = -1;
        this.endBookmark = null;
        this.suppressDuplicateOverlappingText = true;
        this.shouldSeparateByBeads = true;
        this.sortByPosition = false;
        this.addMoreFormatting = false;
        this.indentThreshold = defaultIndentThreshold;
        this.dropThreshold = defaultDropThreshold;
        this.spacingTolerance = 0.5f;
        this.averageCharTolerance = 0.3f;
        this.beadRectangles = null;
        this.charactersByArticle = new ArrayList<>();
        this.characterListMapping = new HashMap();
        this.listOfPatterns = null;
    }

    public String getText(PDDocument pDDocument) throws IOException {
        StringWriter stringWriter = new StringWriter();
        writeText(pDDocument, stringWriter);
        return stringWriter.toString();
    }

    private void resetEngine() {
        this.currentPageNo = 0;
        this.document = null;
        ArrayList<List<TextPosition>> arrayList = this.charactersByArticle;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.characterListMapping.clear();
    }

    public void writeText(PDDocument pDDocument, Writer writer) throws IOException {
        resetEngine();
        this.document = pDDocument;
        this.output = writer;
        if (getAddMoreFormatting()) {
            String str = this.lineSeparator;
            this.paragraphEnd = str;
            this.pageStart = str;
            this.articleStart = str;
            this.articleEnd = str;
        }
        startDocument(this.document);
        processPages(this.document.getPages());
        endDocument(this.document);
    }

    protected void processPages(PDPageTree pDPageTree) throws IOException {
        PDOutlineItem pDOutlineItem;
        PDOutlineItem pDOutlineItem2 = this.startBookmark;
        PDPage findDestinationPage = pDOutlineItem2 == null ? null : pDOutlineItem2.findDestinationPage(this.document);
        if (findDestinationPage != null) {
            this.startBookmarkPageNumber = pDPageTree.indexOf(findDestinationPage) + 1;
        } else {
            this.startBookmarkPageNumber = -1;
        }
        PDOutlineItem pDOutlineItem3 = this.endBookmark;
        PDPage findDestinationPage2 = pDOutlineItem3 != null ? pDOutlineItem3.findDestinationPage(this.document) : null;
        if (findDestinationPage2 != null) {
            this.endBookmarkPageNumber = pDPageTree.indexOf(findDestinationPage2) + 1;
        } else {
            this.endBookmarkPageNumber = -1;
        }
        if (this.startBookmarkPageNumber == -1 && (pDOutlineItem = this.startBookmark) != null && this.endBookmarkPageNumber == -1 && this.endBookmark != null && pDOutlineItem.getCOSObject() == this.endBookmark.getCOSObject()) {
            this.startBookmarkPageNumber = 0;
            this.endBookmarkPageNumber = 0;
        }
        Iterator<PDPage> it = pDPageTree.iterator();
        while (it.hasNext()) {
            PDPage next = it.next();
            this.currentPageNo++;
            if (next.hasContents()) {
                processPage(next);
            }
        }
    }

    @Override // com.tom_roush.pdfbox.text.LegacyPDFStreamEngine, com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void processPage(PDPage pDPage) throws IOException {
        int i = this.currentPageNo;
        if (i < this.startPage || i > this.endPage) {
            return;
        }
        int i2 = this.startBookmarkPageNumber;
        if (i2 == -1 || i >= i2) {
            int i3 = this.endBookmarkPageNumber;
            if (i3 == -1 || i <= i3) {
                startPage(pDPage);
                int i4 = 1;
                if (this.shouldSeparateByBeads) {
                    fillBeadRectangles(pDPage);
                    i4 = 1 + (this.beadRectangles.size() * 2);
                }
                int size = this.charactersByArticle.size();
                this.charactersByArticle.ensureCapacity(i4);
                int max = Math.max(i4, size);
                for (int i5 = 0; i5 < max; i5++) {
                    if (i5 < size) {
                        this.charactersByArticle.get(i5).clear();
                    } else if (i4 < size) {
                        this.charactersByArticle.remove(i5);
                    } else {
                        this.charactersByArticle.add(new ArrayList());
                    }
                }
                this.characterListMapping.clear();
                super.processPage(pDPage);
                writePage();
                endPage(pDPage);
            }
        }
    }

    private void fillBeadRectangles(PDPage pDPage) {
        this.beadRectangles = new ArrayList();
        for (PDThreadBead pDThreadBead : pDPage.getThreadBeads()) {
            if (pDThreadBead == null || pDThreadBead.getRectangle() == null) {
                this.beadRectangles.add(null);
            } else {
                PDRectangle rectangle = pDThreadBead.getRectangle();
                PDRectangle mediaBox = pDPage.getMediaBox();
                float upperRightY = mediaBox.getUpperRightY() - rectangle.getLowerLeftY();
                rectangle.setLowerLeftY(mediaBox.getUpperRightY() - rectangle.getUpperRightY());
                rectangle.setUpperRightY(upperRightY);
                PDRectangle cropBox = pDPage.getCropBox();
                if (cropBox.getLowerLeftX() != 0.0f || cropBox.getLowerLeftY() != 0.0f) {
                    rectangle.setLowerLeftX(rectangle.getLowerLeftX() - cropBox.getLowerLeftX());
                    rectangle.setLowerLeftY(rectangle.getLowerLeftY() - cropBox.getLowerLeftY());
                    rectangle.setUpperRightX(rectangle.getUpperRightX() - cropBox.getLowerLeftX());
                    rectangle.setUpperRightY(rectangle.getUpperRightY() - cropBox.getLowerLeftY());
                }
                this.beadRectangles.add(rectangle);
            }
        }
    }

    protected void startArticle() throws IOException {
        startArticle(true);
    }

    protected void startArticle(boolean z) throws IOException {
        this.output.write(getArticleStart());
    }

    protected void endArticle() throws IOException {
        this.output.write(getArticleEnd());
    }

    /* JADX WARN: Removed duplicated region for block: B:167:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x01be A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writePage() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFTextStripper.writePage():void");
    }

    private boolean overlap(float f, float f2, float f3, float f4) {
        return within(f, f3, 0.1f) || (f3 <= f && f3 >= f - f2) || (f <= f3 && f >= f3 - f4);
    }

    protected void writeLineSeparator() throws IOException {
        this.output.write(getLineSeparator());
    }

    protected void writeWordSeparator() throws IOException {
        this.output.write(getWordSeparator());
    }

    protected void writeCharacters(TextPosition textPosition) throws IOException {
        this.output.write(textPosition.getUnicode());
    }

    protected void writeString(String str, List<TextPosition> list) throws IOException {
        writeString(str);
    }

    protected void writeString(String str) throws IOException {
        this.output.write(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    @Override // com.tom_roush.pdfbox.text.LegacyPDFStreamEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void processTextPosition(com.tom_roush.pdfbox.text.TextPosition r13) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFTextStripper.processTextPosition(com.tom_roush.pdfbox.text.TextPosition):void");
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int i) {
        this.startPage = i;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int i) {
        this.endPage = i;
    }

    public void setLineSeparator(String str) {
        this.lineSeparator = str;
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public String getWordSeparator() {
        return this.wordSeparator;
    }

    public void setWordSeparator(String str) {
        this.wordSeparator = str;
    }

    public boolean getSuppressDuplicateOverlappingText() {
        return this.suppressDuplicateOverlappingText;
    }

    public int getCurrentPageNo() {
        return this.currentPageNo;
    }

    protected Writer getOutput() {
        return this.output;
    }

    protected List<List<TextPosition>> getCharactersByArticle() {
        return this.charactersByArticle;
    }

    public void setSuppressDuplicateOverlappingText(boolean z) {
        this.suppressDuplicateOverlappingText = z;
    }

    public boolean getSeparateByBeads() {
        return this.shouldSeparateByBeads;
    }

    public void setShouldSeparateByBeads(boolean z) {
        this.shouldSeparateByBeads = z;
    }

    public PDOutlineItem getEndBookmark() {
        return this.endBookmark;
    }

    public void setEndBookmark(PDOutlineItem pDOutlineItem) {
        this.endBookmark = pDOutlineItem;
    }

    public PDOutlineItem getStartBookmark() {
        return this.startBookmark;
    }

    public void setStartBookmark(PDOutlineItem pDOutlineItem) {
        this.startBookmark = pDOutlineItem;
    }

    public boolean getAddMoreFormatting() {
        return this.addMoreFormatting;
    }

    public void setAddMoreFormatting(boolean z) {
        this.addMoreFormatting = z;
    }

    public boolean getSortByPosition() {
        return this.sortByPosition;
    }

    public void setSortByPosition(boolean z) {
        this.sortByPosition = z;
    }

    public float getSpacingTolerance() {
        return this.spacingTolerance;
    }

    public void setSpacingTolerance(float f) {
        this.spacingTolerance = f;
    }

    public float getAverageCharTolerance() {
        return this.averageCharTolerance;
    }

    public void setAverageCharTolerance(float f) {
        this.averageCharTolerance = f;
    }

    public float getIndentThreshold() {
        return this.indentThreshold;
    }

    public void setIndentThreshold(float f) {
        this.indentThreshold = f;
    }

    public float getDropThreshold() {
        return this.dropThreshold;
    }

    public void setDropThreshold(float f) {
        this.dropThreshold = f;
    }

    public String getParagraphStart() {
        return this.paragraphStart;
    }

    public void setParagraphStart(String str) {
        this.paragraphStart = str;
    }

    public String getParagraphEnd() {
        return this.paragraphEnd;
    }

    public void setParagraphEnd(String str) {
        this.paragraphEnd = str;
    }

    public String getPageStart() {
        return this.pageStart;
    }

    public void setPageStart(String str) {
        this.pageStart = str;
    }

    public String getPageEnd() {
        return this.pageEnd;
    }

    public void setPageEnd(String str) {
        this.pageEnd = str;
    }

    public String getArticleStart() {
        return this.articleStart;
    }

    public void setArticleStart(String str) {
        this.articleStart = str;
    }

    public String getArticleEnd() {
        return this.articleEnd;
    }

    public void setArticleEnd(String str) {
        this.articleEnd = str;
    }

    private PositionWrapper handleLineSeparation(PositionWrapper positionWrapper, PositionWrapper positionWrapper2, PositionWrapper positionWrapper3, float f) throws IOException {
        positionWrapper.setLineStart();
        isParagraphSeparation(positionWrapper, positionWrapper2, positionWrapper3, f);
        if (positionWrapper.isParagraphStart()) {
            if (positionWrapper2.isArticleStart()) {
                if (positionWrapper2.isLineStart()) {
                    writeLineSeparator();
                }
                writeParagraphStart();
            } else {
                writeLineSeparator();
                writeParagraphSeparator();
            }
        } else {
            writeLineSeparator();
        }
        return positionWrapper;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0076, code lost:
        if (r8.isParagraphStart() == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x009b, code lost:
        if (r7 == matchListItemPattern(r6)) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void isParagraphSeparation(com.tom_roush.pdfbox.text.PDFTextStripper.PositionWrapper r6, com.tom_roush.pdfbox.text.PDFTextStripper.PositionWrapper r7, com.tom_roush.pdfbox.text.PDFTextStripper.PositionWrapper r8, float r9) {
        /*
            r5 = this;
            r0 = 1
            if (r8 != 0) goto L5
            goto L9f
        L5:
            com.tom_roush.pdfbox.text.TextPosition r1 = r6.getTextPosition()
            float r1 = r1.getYDirAdj()
            com.tom_roush.pdfbox.text.TextPosition r7 = r7.getTextPosition()
            float r7 = r7.getYDirAdj()
            float r1 = r1 - r7
            float r7 = java.lang.Math.abs(r1)
            float r1 = r5.getDropThreshold()
            float r9 = r5.multiplyFloat(r1, r9)
            com.tom_roush.pdfbox.text.TextPosition r1 = r6.getTextPosition()
            float r1 = r1.getXDirAdj()
            com.tom_roush.pdfbox.text.TextPosition r2 = r8.getTextPosition()
            float r2 = r2.getXDirAdj()
            float r1 = r1 - r2
            float r2 = r5.getIndentThreshold()
            com.tom_roush.pdfbox.text.TextPosition r3 = r6.getTextPosition()
            float r3 = r3.getWidthOfSpace()
            float r2 = r5.multiplyFloat(r2, r3)
            com.tom_roush.pdfbox.text.TextPosition r3 = r6.getTextPosition()
            float r3 = r3.getWidth()
            r4 = 1048576000(0x3e800000, float:0.25)
            float r3 = r5.multiplyFloat(r4, r3)
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 <= 0) goto L56
            goto L9f
        L56:
            int r7 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r7 <= 0) goto L65
            boolean r7 = r8.isParagraphStart()
            if (r7 != 0) goto L61
            goto L9f
        L61:
            r6.setHangingIndent()
            goto L9e
        L65:
            com.tom_roush.pdfbox.text.TextPosition r7 = r6.getTextPosition()
            float r7 = r7.getWidthOfSpace()
            float r7 = -r7
            int r7 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r7 >= 0) goto L79
            boolean r7 = r8.isParagraphStart()
            if (r7 != 0) goto L9e
            goto L9f
        L79:
            float r7 = java.lang.Math.abs(r1)
            int r7 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r7 >= 0) goto L9e
            boolean r7 = r8.isHangingIndent()
            if (r7 == 0) goto L8b
            r6.setHangingIndent()
            goto L9e
        L8b:
            boolean r7 = r8.isParagraphStart()
            if (r7 == 0) goto L9e
            java.util.regex.Pattern r7 = r5.matchListItemPattern(r8)
            if (r7 == 0) goto L9e
            java.util.regex.Pattern r8 = r5.matchListItemPattern(r6)
            if (r7 != r8) goto L9e
            goto L9f
        L9e:
            r0 = 0
        L9f:
            if (r0 == 0) goto La4
            r6.setParagraphStart()
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.text.PDFTextStripper.isParagraphSeparation(com.tom_roush.pdfbox.text.PDFTextStripper$PositionWrapper, com.tom_roush.pdfbox.text.PDFTextStripper$PositionWrapper, com.tom_roush.pdfbox.text.PDFTextStripper$PositionWrapper, float):void");
    }

    private float multiplyFloat(float f, float f2) {
        return Math.round((f * f2) * 1000.0f) / 1000.0f;
    }

    protected void writeParagraphSeparator() throws IOException {
        writeParagraphEnd();
        writeParagraphStart();
    }

    protected void writeParagraphStart() throws IOException {
        if (this.inParagraph) {
            writeParagraphEnd();
            this.inParagraph = false;
        }
        this.output.write(getParagraphStart());
        this.inParagraph = true;
    }

    protected void writeParagraphEnd() throws IOException {
        if (!this.inParagraph) {
            writeParagraphStart();
        }
        this.output.write(getParagraphEnd());
        this.inParagraph = false;
    }

    protected void writePageStart() throws IOException {
        this.output.write(getPageStart());
    }

    protected void writePageEnd() throws IOException {
        this.output.write(getPageEnd());
    }

    private Pattern matchListItemPattern(PositionWrapper positionWrapper) {
        return matchPattern(positionWrapper.getTextPosition().getUnicode(), getListItemPatterns());
    }

    protected void setListItemPatterns(List<Pattern> list) {
        this.listOfPatterns = list;
    }

    protected List<Pattern> getListItemPatterns() {
        if (this.listOfPatterns == null) {
            this.listOfPatterns = new ArrayList();
            for (String str : LIST_ITEM_EXPRESSIONS) {
                this.listOfPatterns.add(Pattern.compile(str));
            }
        }
        return this.listOfPatterns;
    }

    protected static Pattern matchPattern(String str, List<Pattern> list) {
        for (Pattern pattern : list) {
            if (pattern.matcher(str).matches()) {
                return pattern;
            }
        }
        return null;
    }

    private void writeLine(List<WordWithTextPositions> list) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WordWithTextPositions wordWithTextPositions = list.get(i);
            writeString(wordWithTextPositions.getText(), wordWithTextPositions.getTextPositions());
            if (i < size - 1) {
                writeWordSeparator();
            }
        }
    }

    private List<WordWithTextPositions> normalize(List<LineItem> list) {
        LinkedList linkedList = new LinkedList();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (LineItem lineItem : list) {
            sb = normalizeAdd(linkedList, sb, arrayList, lineItem);
        }
        if (sb.length() > 0) {
            linkedList.add(createWord(sb.toString(), arrayList));
        }
        return linkedList;
    }

    private String handleDirection(String str) {
        Bidi bidi = new Bidi(str, -2);
        if (bidi.isMixed() || bidi.getBaseLevel() != 0) {
            int runCount = bidi.getRunCount();
            byte[] bArr = new byte[runCount];
            Integer[] numArr = new Integer[runCount];
            for (int i = 0; i < runCount; i++) {
                bArr[i] = (byte) bidi.getRunLevel(i);
                numArr[i] = Integer.valueOf(i);
            }
            Bidi.reorderVisually(bArr, 0, numArr, 0, runCount);
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < runCount; i2++) {
                int intValue = numArr[i2].intValue();
                int runStart = bidi.getRunStart(intValue);
                int runLimit = bidi.getRunLimit(intValue);
                if ((bArr[intValue] & 1) != 0) {
                    while (true) {
                        runLimit--;
                        if (runLimit >= runStart) {
                            char charAt = str.charAt(runLimit);
                            if (Character.isMirrored(str.codePointAt(runLimit))) {
                                if (MIRRORING_CHAR_MAP.containsKey(Character.valueOf(charAt))) {
                                    sb.append(MIRRORING_CHAR_MAP.get(Character.valueOf(charAt)));
                                } else {
                                    sb.append(charAt);
                                }
                            } else {
                                sb.append(charAt);
                            }
                        }
                    }
                } else {
                    sb.append((CharSequence) str, runStart, runLimit);
                }
            }
            return sb.toString();
        }
        return str;
    }

    private static void parseBidiFile(InputStream inputStream) throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
        while (true) {
            String readLine = lineNumberReader.readLine();
            if (readLine == null) {
                return;
            }
            int indexOf = readLine.indexOf(35);
            if (indexOf != -1) {
                readLine = readLine.substring(0, indexOf);
            }
            if (readLine.length() >= 2) {
                StringTokenizer stringTokenizer = new StringTokenizer(readLine, ";");
                int countTokens = stringTokenizer.countTokens();
                Character[] chArr = new Character[countTokens];
                for (int i = 0; i < countTokens; i++) {
                    chArr[i] = Character.valueOf((char) Integer.parseInt(stringTokenizer.nextToken().trim(), 16));
                }
                if (countTokens == 2) {
                    MIRRORING_CHAR_MAP.put(chArr[0], chArr[1]);
                }
            }
        }
    }

    private WordWithTextPositions createWord(String str, List<TextPosition> list) {
        return new WordWithTextPositions(normalizeWord(str), list);
    }

    private String normalizeWord(String str) {
        int length = str.length();
        StringBuilder sb = null;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if ((64256 <= charAt && charAt <= 65023) || (65136 <= charAt && charAt <= 65279)) {
                if (sb == null) {
                    sb = new StringBuilder(length * 2);
                }
                sb.append((CharSequence) str, i2, i);
                if (charAt == 65010 && i > 0) {
                    int i3 = i - 1;
                    if (str.charAt(i3) == 1575 || str.charAt(i3) == 65165) {
                        sb.append("لله");
                        i2 = i + 1;
                    }
                }
                sb.append(Normalizer.normalize(str.substring(i, i + 1), Normalizer.Form.NFKC).trim());
                i2 = i + 1;
            }
            i++;
        }
        if (sb == null) {
            return handleDirection(str);
        }
        sb.append((CharSequence) str, i2, i);
        return handleDirection(sb.toString());
    }

    private StringBuilder normalizeAdd(List<WordWithTextPositions> list, StringBuilder sb, List<TextPosition> list2, LineItem lineItem) {
        if (lineItem.isWordSeparator()) {
            list.add(createWord(sb.toString(), new ArrayList(list2)));
            StringBuilder sb2 = new StringBuilder();
            list2.clear();
            return sb2;
        }
        TextPosition textPosition = lineItem.getTextPosition();
        sb.append(textPosition.getUnicode());
        list2.add(textPosition);
        return sb;
    }

    /* loaded from: classes3.dex */
    public static final class LineItem {
        public static LineItem WORD_SEPARATOR = new LineItem();
        private final TextPosition textPosition;

        public static LineItem getWordSeparator() {
            return WORD_SEPARATOR;
        }

        private LineItem() {
            this.textPosition = null;
        }

        LineItem(TextPosition textPosition) {
            this.textPosition = textPosition;
        }

        public TextPosition getTextPosition() {
            return this.textPosition;
        }

        public boolean isWordSeparator() {
            return this.textPosition == null;
        }
    }

    /* loaded from: classes3.dex */
    public static final class WordWithTextPositions {
        String text;
        List<TextPosition> textPositions;

        WordWithTextPositions(String str, List<TextPosition> list) {
            this.text = str;
            this.textPositions = list;
        }

        public String getText() {
            return this.text;
        }

        public List<TextPosition> getTextPositions() {
            return this.textPositions;
        }
    }

    /* loaded from: classes3.dex */
    public static final class PositionWrapper {
        private TextPosition position;
        private boolean isLineStart = false;
        private boolean isParagraphStart = false;
        private boolean isPageBreak = false;
        private boolean isHangingIndent = false;
        private boolean isArticleStart = false;

        PositionWrapper(TextPosition textPosition) {
            this.position = textPosition;
        }

        public TextPosition getTextPosition() {
            return this.position;
        }

        public boolean isLineStart() {
            return this.isLineStart;
        }

        public void setLineStart() {
            this.isLineStart = true;
        }

        public boolean isParagraphStart() {
            return this.isParagraphStart;
        }

        public void setParagraphStart() {
            this.isParagraphStart = true;
        }

        public boolean isArticleStart() {
            return this.isArticleStart;
        }

        public void setArticleStart() {
            this.isArticleStart = true;
        }

        public boolean isPageBreak() {
            return this.isPageBreak;
        }

        public void setPageBreak() {
            this.isPageBreak = true;
        }

        public boolean isHangingIndent() {
            return this.isHangingIndent;
        }

        public void setHangingIndent() {
            this.isHangingIndent = true;
        }
    }
}
