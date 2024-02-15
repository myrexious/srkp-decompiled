package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.ttf.CmapLookup;
import com.tom_roush.fontbox.ttf.CmapSubtable;
import com.tom_roush.fontbox.ttf.GlyphTable;
import com.tom_roush.fontbox.ttf.HeaderTable;
import com.tom_roush.fontbox.ttf.HorizontalHeaderTable;
import com.tom_roush.fontbox.ttf.HorizontalMetricsTable;
import com.tom_roush.fontbox.ttf.IndexToLocationTable;
import com.tom_roush.fontbox.ttf.MaximumProfileTable;
import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TTFSubsetter;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class TrueTypeEmbedder implements Subsetter {
    private static final String BASE25 = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ITALIC = 1;
    private static final int OBLIQUE = 512;
    @Deprecated
    protected final CmapSubtable cmap;
    protected final CmapLookup cmapLookup;
    private final PDDocument document;
    private final boolean embedSubset;
    protected PDFontDescriptor fontDescriptor;
    private final Set<Integer> subsetCodePoints = new HashSet();
    protected TrueTypeFont ttf;

    protected abstract void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException;

    public TrueTypeEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, TrueTypeFont trueTypeFont, boolean z) throws IOException {
        this.document = pDDocument;
        this.embedSubset = z;
        this.ttf = trueTypeFont;
        this.fontDescriptor = createFontDescriptor(trueTypeFont);
        if (!isEmbeddingPermitted(trueTypeFont)) {
            throw new IOException("This font does not permit embedding");
        }
        if (!z) {
            InputStream originalData = trueTypeFont.getOriginalData();
            byte[] bArr = new byte[4];
            originalData.mark(4);
            if (originalData.read(bArr) == 4 && new String(bArr).equals("ttcf")) {
                originalData.close();
                throw new IOException("Full embedding of TrueType font collections not supported");
            }
            if (originalData.markSupported()) {
                originalData.reset();
            } else {
                originalData.close();
                originalData = trueTypeFont.getOriginalData();
            }
            PDStream pDStream = new PDStream(pDDocument, originalData, COSName.FLATE_DECODE);
            pDStream.getCOSObject().setLong(COSName.LENGTH1, trueTypeFont.getOriginalDataSize());
            this.fontDescriptor.setFontFile2(pDStream);
        }
        cOSDictionary.setName(COSName.BASE_FONT, trueTypeFont.getName());
        this.cmap = trueTypeFont.getUnicodeCmap();
        this.cmapLookup = trueTypeFont.getUnicodeCmapLookup();
    }

    public void buildFontFile2(InputStream inputStream) throws IOException {
        COSInputStream cOSInputStream;
        PDStream pDStream = new PDStream(this.document, inputStream, COSName.FLATE_DECODE);
        try {
            cOSInputStream = pDStream.createInputStream();
        } catch (Throwable th) {
            th = th;
            cOSInputStream = null;
        }
        try {
            TrueTypeFont parseEmbedded = new TTFParser().parseEmbedded(cOSInputStream);
            this.ttf = parseEmbedded;
            if (!isEmbeddingPermitted(parseEmbedded)) {
                throw new IOException("This font does not permit embedding");
            }
            if (this.fontDescriptor == null) {
                this.fontDescriptor = createFontDescriptor(this.ttf);
            }
            IOUtils.closeQuietly(cOSInputStream);
            pDStream.getCOSObject().setLong(COSName.LENGTH1, this.ttf.getOriginalDataSize());
            this.fontDescriptor.setFontFile2(pDStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(cOSInputStream);
            throw th;
        }
    }

    boolean isEmbeddingPermitted(TrueTypeFont trueTypeFont) throws IOException {
        if (trueTypeFont.getOS2Windows() != null) {
            short fsType = trueTypeFont.getOS2Windows().getFsType();
            return ((fsType & 15) == 2 || (fsType & OS2WindowsMetricsTable.FSTYPE_BITMAP_ONLY) == 512) ? false : true;
        }
        return true;
    }

    private boolean isSubsettingPermitted(TrueTypeFont trueTypeFont) throws IOException {
        return trueTypeFont.getOS2Windows() == null || (trueTypeFont.getOS2Windows().getFsType() & OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING) != 256;
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0057, code lost:
        if (r4 != 5) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor createFontDescriptor(com.tom_roush.fontbox.ttf.TrueTypeFont r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.TrueTypeEmbedder.createFontDescriptor(com.tom_roush.fontbox.ttf.TrueTypeFont):com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor");
    }

    @Deprecated
    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.Subsetter
    public void addToSubset(int i) {
        this.subsetCodePoints.add(Integer.valueOf(i));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.Subsetter
    public void subset() throws IOException {
        if (!isSubsettingPermitted(this.ttf)) {
            throw new IOException("This font does not permit subsetting");
        }
        if (!this.embedSubset) {
            throw new IllegalStateException("Subsetting is disabled");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(HeaderTable.TAG);
        arrayList.add(HorizontalHeaderTable.TAG);
        arrayList.add(IndexToLocationTable.TAG);
        arrayList.add(MaximumProfileTable.TAG);
        arrayList.add("cvt ");
        arrayList.add("prep");
        arrayList.add(GlyphTable.TAG);
        arrayList.add(HorizontalMetricsTable.TAG);
        arrayList.add("fpgm");
        arrayList.add("gasp");
        TTFSubsetter tTFSubsetter = new TTFSubsetter(this.ttf, arrayList);
        tTFSubsetter.addAll(this.subsetCodePoints);
        Map<Integer, Integer> gIDMap = tTFSubsetter.getGIDMap();
        String tag = getTag(gIDMap);
        tTFSubsetter.setPrefix(tag);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        tTFSubsetter.writeToStream(byteArrayOutputStream);
        buildSubset(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), tag, gIDMap);
        this.ttf.close();
    }

    public boolean needsSubset() {
        return this.embedSubset;
    }

    public String getTag(Map<Integer, Integer> map) {
        long hashCode = map.hashCode();
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j = hashCode / 25;
            sb.append(BASE25.charAt((int) (hashCode % 25)));
            if (j == 0 || sb.length() >= 6) {
                break;
            }
            hashCode = j;
        }
        while (sb.length() < 6) {
            sb.insert(0, 'A');
        }
        sb.append('+');
        return sb.toString();
    }
}
