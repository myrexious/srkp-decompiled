package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.fontbox.ttf.GlyphData;
import com.tom_roush.fontbox.ttf.GlyphTable;
import com.tom_roush.fontbox.ttf.HorizontalMetricsTable;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.ttf.VerticalHeaderTable;
import com.tom_roush.fontbox.ttf.VerticalMetricsTable;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public final class PDCIDFontType2Embedder extends TrueTypeEmbedder {
    private final COSDictionary cidFont;
    private final COSDictionary dict;
    private final PDDocument document;
    private final PDType0Font parent;
    private final boolean vertical;

    /* loaded from: classes3.dex */
    public enum State {
        FIRST,
        BRACKET,
        SERIAL
    }

    public PDCIDFontType2Embedder(PDDocument pDDocument, COSDictionary cOSDictionary, TrueTypeFont trueTypeFont, boolean z, PDType0Font pDType0Font, boolean z2) throws IOException {
        super(pDDocument, cOSDictionary, trueTypeFont, z);
        this.document = pDDocument;
        this.dict = cOSDictionary;
        this.parent = pDType0Font;
        this.vertical = z2;
        cOSDictionary.setItem(COSName.SUBTYPE, COSName.TYPE0);
        cOSDictionary.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
        cOSDictionary.setItem(COSName.ENCODING, z2 ? COSName.IDENTITY_V : COSName.IDENTITY_H);
        COSDictionary createCIDFont = createCIDFont();
        this.cidFont = createCIDFont;
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) createCIDFont);
        cOSDictionary.setItem(COSName.DESCENDANT_FONTS, (COSBase) cOSArray);
        if (z) {
            return;
        }
        buildToUnicodeCMap(null);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.TrueTypeEmbedder
    protected void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            treeMap.put(Integer.valueOf(entry.getValue().intValue()), Integer.valueOf(entry.getKey().intValue()));
        }
        buildToUnicodeCMap(map);
        if (this.vertical) {
            buildVerticalMetrics(treeMap);
        }
        buildFontFile2(inputStream);
        addNameTag(str);
        buildWidths(treeMap);
        buildCIDToGIDMap(treeMap);
        buildCIDSet(treeMap);
    }

    private void buildToUnicodeCMap(Map<Integer, Integer> map) throws IOException {
        int i;
        ToUnicodeWriter toUnicodeWriter = new ToUnicodeWriter();
        int numGlyphs = this.ttf.getMaximumProfile().getNumGlyphs();
        boolean z = false;
        for (int i2 = 1; i2 <= numGlyphs; i2++) {
            if (map == null) {
                i = i2;
            } else if (map.containsKey(Integer.valueOf(i2))) {
                i = map.get(Integer.valueOf(i2)).intValue();
            }
            List<Integer> charCodes = this.cmapLookup.getCharCodes(i);
            if (charCodes != null) {
                int intValue = charCodes.get(0).intValue();
                if (intValue > 65535) {
                    z = true;
                }
                toUnicodeWriter.add(i, new String(new int[]{intValue}, 0, 1));
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        toUnicodeWriter.writeTo(byteArrayOutputStream);
        PDStream pDStream = new PDStream(this.document, (InputStream) new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE);
        if (z && this.document.getVersion() < 1.5d) {
            this.document.setVersion(1.5f);
        }
        this.dict.setItem(COSName.TO_UNICODE, pDStream);
    }

    private COSDictionary toCIDSystemInfo(String str, String str2, int i) {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setString(COSName.REGISTRY, str);
        cOSDictionary.setString(COSName.ORDERING, str2);
        cOSDictionary.setInt(COSName.SUPPLEMENT, i);
        return cOSDictionary;
    }

    private COSDictionary createCIDFont() throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        cOSDictionary.setItem(COSName.SUBTYPE, (COSBase) COSName.CID_FONT_TYPE2);
        cOSDictionary.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
        cOSDictionary.setItem(COSName.CIDSYSTEMINFO, (COSBase) toCIDSystemInfo("Adobe", "Identity", 0));
        cOSDictionary.setItem(COSName.FONT_DESC, (COSBase) this.fontDescriptor.getCOSObject());
        buildWidths(cOSDictionary);
        if (this.vertical) {
            buildVerticalMetrics(cOSDictionary);
        }
        cOSDictionary.setItem(COSName.CID_TO_GID_MAP, (COSBase) COSName.IDENTITY);
        return cOSDictionary;
    }

    private void addNameTag(String str) {
        String str2 = str + this.fontDescriptor.getFontName();
        this.dict.setName(COSName.BASE_FONT, str2);
        this.fontDescriptor.setFontName(str2);
        this.cidFont.setName(COSName.BASE_FONT, str2);
    }

    private void buildCIDToGIDMap(TreeMap<Integer, Integer> treeMap) throws IOException {
        int intValue = treeMap.lastKey().intValue();
        byte[] bArr = new byte[(intValue * 2) + 2];
        int i = 0;
        for (int i2 = 0; i2 <= intValue; i2++) {
            Integer num = treeMap.get(Integer.valueOf(i2));
            if (num != null) {
                bArr[i] = (byte) ((num.intValue() >> 8) & 255);
                bArr[i + 1] = (byte) (num.intValue() & 255);
            }
            i += 2;
        }
        this.cidFont.setItem(COSName.CID_TO_GID_MAP, new PDStream(this.document, (InputStream) new ByteArrayInputStream(bArr), COSName.FLATE_DECODE));
    }

    private void buildCIDSet(TreeMap<Integer, Integer> treeMap) throws IOException {
        int intValue = treeMap.lastKey().intValue();
        byte[] bArr = new byte[(intValue / 8) + 1];
        for (int i = 0; i <= intValue; i++) {
            int i2 = i / 8;
            bArr[i2] = (byte) ((1 << (7 - (i % 8))) | bArr[i2]);
        }
        this.fontDescriptor.setCIDSet(new PDStream(this.document, (InputStream) new ByteArrayInputStream(bArr), COSName.FLATE_DECODE));
    }

    private void buildWidths(TreeMap<Integer, Integer> treeMap) throws IOException {
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        COSArray cOSArray = new COSArray();
        COSArray cOSArray2 = new COSArray();
        Set<Integer> keySet = treeMap.keySet();
        HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
        int i = Integer.MIN_VALUE;
        for (Integer num : keySet) {
            int intValue = num.intValue();
            long round = Math.round(horizontalMetrics.getAdvanceWidth(treeMap.get(Integer.valueOf(intValue)).intValue()) * unitsPerEm);
            if (round != 1000) {
                if (i != intValue - 1) {
                    cOSArray2 = new COSArray();
                    cOSArray.add((COSBase) COSInteger.get(intValue));
                    cOSArray.add((COSBase) cOSArray2);
                }
                cOSArray2.add((COSBase) COSInteger.get(round));
                i = intValue;
            }
        }
        this.cidFont.setItem(COSName.W, (COSBase) cOSArray);
    }

    private boolean buildVerticalHeader(COSDictionary cOSDictionary) throws IOException {
        VerticalHeaderTable verticalHeader;
        if (this.ttf.getVerticalHeader() == null) {
            Log.w("PdfBox-Android", "Font to be subset is set to vertical, but has no 'vhea' table");
            return false;
        }
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        long round = Math.round(verticalHeader.getAscender() * unitsPerEm);
        long round2 = Math.round((-verticalHeader.getAdvanceHeightMax()) * unitsPerEm);
        if (round == 880 && round2 == -1000) {
            return true;
        }
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) COSInteger.get(round));
        cOSArray.add((COSBase) COSInteger.get(round2));
        cOSDictionary.setItem(COSName.DW2, (COSBase) cOSArray);
        return true;
    }

    private void buildVerticalMetrics(TreeMap<Integer, Integer> treeMap) throws IOException {
        GlyphData glyph;
        VerticalMetricsTable verticalMetricsTable;
        GlyphTable glyphTable;
        long j;
        if (buildVerticalHeader(this.cidFont)) {
            float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
            VerticalHeaderTable verticalHeader = this.ttf.getVerticalHeader();
            VerticalMetricsTable verticalMetrics = this.ttf.getVerticalMetrics();
            GlyphTable glyph2 = this.ttf.getGlyph();
            HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
            long round = Math.round(verticalHeader.getAscender() * unitsPerEm);
            long round2 = Math.round((-verticalHeader.getAdvanceHeightMax()) * unitsPerEm);
            COSArray cOSArray = new COSArray();
            COSArray cOSArray2 = new COSArray();
            int i = Integer.MIN_VALUE;
            for (Integer num : treeMap.keySet()) {
                int intValue = num.intValue();
                if (glyph2.getGlyph(intValue) == null) {
                    verticalMetricsTable = verticalMetrics;
                    glyphTable = glyph2;
                } else {
                    long round3 = Math.round((glyph.getYMaximum() + verticalMetrics.getTopSideBearing(intValue)) * unitsPerEm);
                    glyphTable = glyph2;
                    verticalMetricsTable = verticalMetrics;
                    long round4 = Math.round((-verticalMetrics.getAdvanceHeight(intValue)) * unitsPerEm);
                    if (round3 != round || round4 != round2) {
                        long j2 = round;
                        if (i != intValue - 1) {
                            COSArray cOSArray3 = new COSArray();
                            j = round2;
                            cOSArray.add((COSBase) COSInteger.get(intValue));
                            cOSArray.add((COSBase) cOSArray3);
                            cOSArray2 = cOSArray3;
                        } else {
                            j = round2;
                        }
                        cOSArray2.add((COSBase) COSInteger.get(round4));
                        cOSArray2.add((COSBase) COSInteger.get(Math.round(horizontalMetrics.getAdvanceWidth(intValue) * unitsPerEm) / 2));
                        cOSArray2.add((COSBase) COSInteger.get(round3));
                        i = intValue;
                        glyph2 = glyphTable;
                        verticalMetrics = verticalMetricsTable;
                        round = j2;
                        round2 = j;
                    }
                }
                glyph2 = glyphTable;
                verticalMetrics = verticalMetricsTable;
            }
            this.cidFont.setItem(COSName.W2, (COSBase) cOSArray);
        }
    }

    private void buildWidths(COSDictionary cOSDictionary) throws IOException {
        int numberOfGlyphs = this.ttf.getNumberOfGlyphs();
        int[] iArr = new int[numberOfGlyphs * 2];
        HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
        for (int i = 0; i < numberOfGlyphs; i++) {
            int i2 = i * 2;
            iArr[i2] = i;
            iArr[i2 + 1] = horizontalMetrics.getAdvanceWidth(i);
        }
        cOSDictionary.setItem(COSName.W, (COSBase) getWidths(iArr));
    }

    private COSArray getWidths(int[] iArr) throws IOException {
        State state;
        int[] iArr2 = iArr;
        if (iArr2.length < 2) {
            throw new IllegalArgumentException("length of widths must be >= 2");
        }
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        long j = iArr2[0];
        long round = Math.round(iArr2[1] * unitsPerEm);
        COSArray cOSArray = new COSArray();
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) COSInteger.get(j));
        State state2 = State.FIRST;
        int i = 2;
        for (int i2 = 1; i < iArr2.length - i2; i2 = 1) {
            long j2 = iArr2[i];
            long round2 = Math.round(iArr2[i + 1] * unitsPerEm);
            int i3 = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[state2.ordinal()];
            if (i3 == 1) {
                int i4 = (j2 > (j + 1) ? 1 : (j2 == (j + 1) ? 0 : -1));
                if (i4 == 0 && round2 == round) {
                    state = State.SERIAL;
                    state2 = state;
                } else if (i4 == 0) {
                    State state3 = State.BRACKET;
                    COSArray cOSArray3 = new COSArray();
                    cOSArray3.add((COSBase) COSInteger.get(round));
                    state2 = state3;
                    cOSArray = cOSArray3;
                } else {
                    COSArray cOSArray4 = new COSArray();
                    cOSArray4.add((COSBase) COSInteger.get(round));
                    cOSArray2.add((COSBase) cOSArray4);
                    cOSArray2.add((COSBase) COSInteger.get(j2));
                    cOSArray = cOSArray4;
                }
            } else if (i3 == 2) {
                int i5 = (j2 > (j + 1) ? 1 : (j2 == (j + 1) ? 0 : -1));
                if (i5 == 0 && round2 == round) {
                    state = State.SERIAL;
                    cOSArray2.add((COSBase) cOSArray);
                    cOSArray2.add((COSBase) COSInteger.get(j));
                } else if (i5 == 0) {
                    cOSArray.add((COSBase) COSInteger.get(round));
                } else {
                    state = State.FIRST;
                    cOSArray.add((COSBase) COSInteger.get(round));
                    cOSArray2.add((COSBase) cOSArray);
                    cOSArray2.add((COSBase) COSInteger.get(j2));
                }
                state2 = state;
            } else if (i3 == 3 && (j2 != j + 1 || round2 != round)) {
                cOSArray2.add((COSBase) COSInteger.get(j));
                cOSArray2.add((COSBase) COSInteger.get(round));
                cOSArray2.add((COSBase) COSInteger.get(j2));
                state = State.FIRST;
                state2 = state;
            }
            i += 2;
            iArr2 = iArr;
            round = round2;
            j = j2;
        }
        int i6 = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[state2.ordinal()];
        if (i6 == 1) {
            COSArray cOSArray5 = new COSArray();
            cOSArray5.add((COSBase) COSInteger.get(round));
            cOSArray2.add((COSBase) cOSArray5);
        } else if (i6 == 2) {
            cOSArray.add((COSBase) COSInteger.get(round));
            cOSArray2.add((COSBase) cOSArray);
        } else if (i6 == 3) {
            cOSArray2.add((COSBase) COSInteger.get(j));
            cOSArray2.add((COSBase) COSInteger.get(round));
        }
        return cOSArray2;
    }

    /* renamed from: com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2Embedder$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State = iArr;
            try {
                iArr[State.FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[State.BRACKET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[State.SERIAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void buildVerticalMetrics(COSDictionary cOSDictionary) throws IOException {
        if (buildVerticalHeader(cOSDictionary)) {
            int numberOfGlyphs = this.ttf.getNumberOfGlyphs();
            int[] iArr = new int[numberOfGlyphs * 4];
            GlyphTable glyph = this.ttf.getGlyph();
            VerticalMetricsTable verticalMetrics = this.ttf.getVerticalMetrics();
            HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
            for (int i = 0; i < numberOfGlyphs; i++) {
                GlyphData glyph2 = glyph.getGlyph(i);
                if (glyph2 == null) {
                    iArr[i * 4] = Integer.MIN_VALUE;
                } else {
                    int i2 = i * 4;
                    iArr[i2] = i;
                    iArr[i2 + 1] = verticalMetrics.getAdvanceHeight(i);
                    iArr[i2 + 2] = horizontalMetrics.getAdvanceWidth(i);
                    iArr[i2 + 3] = glyph2.getYMaximum() + verticalMetrics.getTopSideBearing(i);
                }
            }
            cOSDictionary.setItem(COSName.W2, (COSBase) getVerticalMetrics(iArr));
        }
    }

    private COSArray getVerticalMetrics(int[] iArr) throws IOException {
        char c;
        float f;
        COSArray cOSArray;
        State state;
        COSArray cOSArray2;
        int[] iArr2 = iArr;
        if (iArr2.length < 4) {
            throw new IllegalArgumentException("length of values must be >= 4");
        }
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        long j = iArr2[0];
        long round = Math.round((iArr2[2] * unitsPerEm) / 2.0f);
        int i = 3;
        COSArray cOSArray3 = new COSArray();
        COSArray cOSArray4 = new COSArray();
        cOSArray4.add((COSBase) COSInteger.get(j));
        COSArray cOSArray5 = cOSArray3;
        long round2 = Math.round(iArr2[3] * unitsPerEm);
        long j2 = round;
        State state2 = State.FIRST;
        long round3 = Math.round((-iArr2[1]) * unitsPerEm);
        long j3 = j;
        int i2 = 4;
        while (i2 < iArr2.length - i) {
            long j4 = iArr2[i2];
            if (j4 == -2147483648L) {
                f = unitsPerEm;
                state = state2;
                c = 0;
                cOSArray = cOSArray4;
            } else {
                COSArray cOSArray6 = cOSArray4;
                long round4 = Math.round((-iArr2[i2 + 1]) * unitsPerEm);
                c = 0;
                long j5 = j2;
                j2 = Math.round((iArr2[i2 + 2] * unitsPerEm) / 2.0f);
                f = unitsPerEm;
                long round5 = Math.round(iArr2[i2 + 3] * unitsPerEm);
                int i3 = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[state2.ordinal()];
                State state3 = state2;
                if (i3 != 1) {
                    if (i3 == 2) {
                        cOSArray = cOSArray6;
                        int i4 = (j4 > (j3 + 1) ? 1 : (j4 == (j3 + 1) ? 0 : -1));
                        if (i4 == 0 && round4 == round3 && j2 == j5 && round5 == round2) {
                            state = State.SERIAL;
                            cOSArray.add((COSBase) cOSArray5);
                            cOSArray.add((COSBase) COSInteger.get(j3));
                        } else {
                            cOSArray2 = cOSArray5;
                            if (i4 == 0) {
                                cOSArray2.add((COSBase) COSInteger.get(round3));
                                cOSArray2.add((COSBase) COSInteger.get(j5));
                                cOSArray2.add((COSBase) COSInteger.get(round2));
                                cOSArray5 = cOSArray2;
                                state = state3;
                            } else {
                                state = State.FIRST;
                                cOSArray2.add((COSBase) COSInteger.get(round3));
                                cOSArray2.add((COSBase) COSInteger.get(j5));
                                cOSArray2.add((COSBase) COSInteger.get(round2));
                                cOSArray.add((COSBase) cOSArray2);
                                cOSArray.add((COSBase) COSInteger.get(j4));
                                cOSArray5 = cOSArray2;
                            }
                        }
                    } else if (i3 == 3 && !(j4 == j3 + 1 && round4 == round3 && j2 == j5 && round5 == round2)) {
                        cOSArray = cOSArray6;
                        cOSArray.add((COSBase) COSInteger.get(j3));
                        cOSArray.add((COSBase) COSInteger.get(round3));
                        cOSArray.add((COSBase) COSInteger.get(j5));
                        cOSArray.add((COSBase) COSInteger.get(round2));
                        cOSArray.add((COSBase) COSInteger.get(j4));
                        state = State.FIRST;
                    } else {
                        cOSArray2 = cOSArray5;
                        cOSArray = cOSArray6;
                        cOSArray5 = cOSArray2;
                        state = state3;
                    }
                    round3 = round4;
                    round2 = round5;
                    j3 = j4;
                } else {
                    COSArray cOSArray7 = cOSArray5;
                    cOSArray = cOSArray6;
                    int i5 = (j4 > (j3 + 1) ? 1 : (j4 == (j3 + 1) ? 0 : -1));
                    if (i5 == 0 && round4 == round3 && j2 == j5 && round5 == round2) {
                        cOSArray5 = cOSArray7;
                        state = State.SERIAL;
                        round3 = round4;
                        round2 = round5;
                        j3 = j4;
                    } else if (i5 == 0) {
                        state = State.BRACKET;
                        cOSArray2 = new COSArray();
                        cOSArray2.add((COSBase) COSInteger.get(round3));
                        cOSArray2.add((COSBase) COSInteger.get(j5));
                        cOSArray2.add((COSBase) COSInteger.get(round2));
                        cOSArray5 = cOSArray2;
                        round3 = round4;
                        round2 = round5;
                        j3 = j4;
                    } else {
                        COSArray cOSArray8 = new COSArray();
                        cOSArray8.add((COSBase) COSInteger.get(round3));
                        cOSArray8.add((COSBase) COSInteger.get(j5));
                        cOSArray8.add((COSBase) COSInteger.get(round2));
                        cOSArray.add((COSBase) cOSArray8);
                        cOSArray.add((COSBase) COSInteger.get(j4));
                        cOSArray5 = cOSArray8;
                        state = state3;
                        round3 = round4;
                        round2 = round5;
                        j3 = j4;
                    }
                }
            }
            i2 += 4;
            cOSArray4 = cOSArray;
            unitsPerEm = f;
            i = 3;
            state2 = state;
            iArr2 = iArr;
        }
        State state4 = state2;
        long j6 = j2;
        COSArray cOSArray9 = cOSArray5;
        COSArray cOSArray10 = cOSArray4;
        int i6 = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[state4.ordinal()];
        if (i6 == 1) {
            COSArray cOSArray11 = new COSArray();
            cOSArray11.add((COSBase) COSInteger.get(round3));
            cOSArray11.add((COSBase) COSInteger.get(j6));
            cOSArray11.add((COSBase) COSInteger.get(round2));
            cOSArray10.add((COSBase) cOSArray11);
        } else if (i6 == 2) {
            cOSArray9.add((COSBase) COSInteger.get(round3));
            cOSArray9.add((COSBase) COSInteger.get(j6));
            cOSArray9.add((COSBase) COSInteger.get(round2));
            cOSArray10.add((COSBase) cOSArray9);
        } else if (i6 == 3) {
            cOSArray10.add((COSBase) COSInteger.get(j3));
            cOSArray10.add((COSBase) COSInteger.get(round3));
            cOSArray10.add((COSBase) COSInteger.get(j6));
            cOSArray10.add((COSBase) COSInteger.get(round2));
        }
        return cOSArray10;
    }

    public PDCIDFont getCIDFont() throws IOException {
        return new PDCIDFontType2(this.cidFont, this.parent, this.ttf);
    }
}
