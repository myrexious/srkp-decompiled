package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.OpenTypeFont;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
final class FontMapperImpl implements FontMapper {
    private static final FontCache fontCache = new FontCache();
    private Map<String, FontInfo> fontInfoByName;
    private FontProvider fontProvider;
    private final TrueTypeFont lastResortFont;
    private final Map<String, List<String>> substitutes = new HashMap();

    public FontMapperImpl() {
        InputStream resourceAsStream;
        addSubstitutes("Courier", new ArrayList(Arrays.asList("CourierNew", "CourierNewPSMT", "LiberationMono", "NimbusMonL-Regu", "DroidSansMono")));
        addSubstitutes("Courier-Bold", new ArrayList(Arrays.asList("CourierNewPS-BoldMT", "CourierNew-Bold", "LiberationMono-Bold", "NimbusMonL-Bold", "DroidSansMono")));
        addSubstitutes("Courier-Oblique", new ArrayList(Arrays.asList("CourierNewPS-ItalicMT", "CourierNew-Italic", "LiberationMono-Italic", "NimbusMonL-ReguObli", "DroidSansMono")));
        addSubstitutes("Courier-BoldOblique", new ArrayList(Arrays.asList("CourierNewPS-BoldItalicMT", "CourierNew-BoldItalic", "LiberationMono-BoldItalic", "NimbusMonL-BoldObli", "DroidSansMono")));
        addSubstitutes("Helvetica", new ArrayList(Arrays.asList("ArialMT", "Arial", "LiberationSans", "NimbusSanL-Regu", "Roboto-Regular")));
        addSubstitutes("Helvetica-Bold", new ArrayList(Arrays.asList("Arial-BoldMT", "Arial-Bold", "LiberationSans-Bold", "NimbusSanL-Bold", "Roboto-Bold")));
        addSubstitutes("Helvetica-Oblique", new ArrayList(Arrays.asList("Arial-ItalicMT", "Arial-Italic", "Helvetica-Italic", "LiberationSans-Italic", "NimbusSanL-ReguItal", "Roboto-Italic")));
        addSubstitutes("Helvetica-BoldOblique", new ArrayList(Arrays.asList("Arial-BoldItalicMT", "Helvetica-BoldItalic", "LiberationSans-BoldItalic", "NimbusSanL-BoldItal", "Roboto-BoldItalic")));
        addSubstitutes("Times-Roman", new ArrayList(Arrays.asList("TimesNewRomanPSMT", "TimesNewRoman", "TimesNewRomanPS", "LiberationSerif", "NimbusRomNo9L-Regu", "Roboto-Regular")));
        addSubstitutes("Times-Bold", new ArrayList(Arrays.asList("TimesNewRomanPS-BoldMT", "TimesNewRomanPS-Bold", "TimesNewRoman-Bold", "LiberationSerif-Bold", "NimbusRomNo9L-Medi", "DroidSerif-Bold", "Roboto-Bold")));
        addSubstitutes("Times-Italic", new ArrayList(Arrays.asList("TimesNewRomanPS-ItalicMT", "TimesNewRomanPS-Italic", "TimesNewRoman-Italic", "LiberationSerif-Italic", "NimbusRomNo9L-ReguItal", "DroidSerif-Italic", "Roboto-Italic")));
        addSubstitutes("Times-BoldItalic", new ArrayList(Arrays.asList("TimesNewRomanPS-BoldItalicMT", "TimesNewRomanPS-BoldItalic", "TimesNewRoman-BoldItalic", "LiberationSerif-BoldItalic", "NimbusRomNo9L-MediItal", "DroidSerif-BoldItalic", "Roboto-BoldItalic")));
        addSubstitutes("Symbol", new ArrayList(Arrays.asList("Symbol", "SymbolMT", "StandardSymL")));
        addSubstitutes("ZapfDingbats", new ArrayList(Arrays.asList("ZapfDingbatsITCbyBT-Regular", "ZapfDingbatsITC", "Dingbats", "MS-Gothic")));
        for (String str : Standard14Fonts.getNames()) {
            if (getSubstitutes(str).isEmpty()) {
                addSubstitutes(str, copySubstitutes(Standard14Fonts.getMappedFontName(str).toLowerCase(Locale.ENGLISH)));
            }
        }
        try {
            if (PDFBoxResourceLoader.isReady()) {
                resourceAsStream = PDFBoxResourceLoader.getStream("com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
            } else {
                resourceAsStream = FontMapper.class.getResourceAsStream(RemoteSettings.FORWARD_SLASH_STRING + "com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
            }
            if (resourceAsStream == null) {
                throw new IOException("resource 'com/tom_roush/pdfbox/resources/ttf/LiberationSans-Regular.ttf' not found");
            }
            this.lastResortFont = new TTFParser().parse(new BufferedInputStream(resourceAsStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* loaded from: classes3.dex */
    public static class DefaultFontProvider {
        private static final FontProvider INSTANCE = new FileSystemFontProvider(FontMapperImpl.fontCache);

        private DefaultFontProvider() {
        }
    }

    public synchronized void setProvider(FontProvider fontProvider) {
        this.fontInfoByName = createFontInfoByName(fontProvider.getFontInfo());
        this.fontProvider = fontProvider;
    }

    public synchronized FontProvider getProvider() {
        if (this.fontProvider == null) {
            setProvider(DefaultFontProvider.INSTANCE);
        }
        return this.fontProvider;
    }

    public FontCache getFontCache() {
        return fontCache;
    }

    private Map<String, FontInfo> createFontInfoByName(List<? extends FontInfo> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FontInfo fontInfo : list) {
            for (String str : getPostScriptNames(fontInfo.getPostScriptName())) {
                linkedHashMap.put(str.toLowerCase(Locale.ENGLISH), fontInfo);
            }
        }
        return linkedHashMap;
    }

    private Set<String> getPostScriptNames(String str) {
        HashSet hashSet = new HashSet(2);
        hashSet.add(str);
        hashSet.add(str.replace("-", ""));
        return hashSet;
    }

    private List<String> copySubstitutes(String str) {
        return new ArrayList(this.substitutes.get(str));
    }

    public void addSubstitute(String str, String str2) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        if (!this.substitutes.containsKey(lowerCase)) {
            this.substitutes.put(lowerCase, new ArrayList());
        }
        this.substitutes.get(lowerCase).add(str2);
    }

    private void addSubstitutes(String str, List<String> list) {
        this.substitutes.put(str.toLowerCase(Locale.ENGLISH), list);
    }

    private List<String> getSubstitutes(String str) {
        List<String> list = this.substitutes.get(str.replace(StringUtils.SPACE, "").toLowerCase(Locale.ENGLISH));
        return list != null ? list : Collections.emptyList();
    }

    private String getFallbackFontName(PDFontDescriptor pDFontDescriptor) {
        String str;
        if (pDFontDescriptor != null) {
            boolean z = false;
            if (pDFontDescriptor.getFontName() != null) {
                String lowerCase = pDFontDescriptor.getFontName().toLowerCase();
                if (lowerCase.contains("bold") || lowerCase.contains("black") || lowerCase.contains("heavy")) {
                    z = true;
                }
            }
            if (pDFontDescriptor.isFixedPitch()) {
                if (z && pDFontDescriptor.isItalic()) {
                    str = "Courier-BoldOblique";
                } else if (z) {
                    str = "Courier-Bold";
                } else {
                    str = pDFontDescriptor.isItalic() ? "Courier-Oblique" : "Courier";
                }
            } else if (pDFontDescriptor.isSerif()) {
                if (z && pDFontDescriptor.isItalic()) {
                    str = "Times-BoldItalic";
                } else if (z) {
                    str = "Times-Bold";
                } else if (!pDFontDescriptor.isItalic()) {
                    return "Times-Roman";
                } else {
                    str = "Times-Italic";
                }
            } else if (z && pDFontDescriptor.isItalic()) {
                str = "Helvetica-BoldOblique";
            } else if (z) {
                str = "Helvetica-Bold";
            } else {
                str = pDFontDescriptor.isItalic() ? "Helvetica-Oblique" : "Helvetica";
            }
            return str;
        }
        return "Times-Roman";
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.FontMapper
    public FontMapping<TrueTypeFont> getTrueTypeFont(String str, PDFontDescriptor pDFontDescriptor) {
        TrueTypeFont trueTypeFont = (TrueTypeFont) findFont(FontFormat.TTF, str);
        if (trueTypeFont != null) {
            return new FontMapping<>(trueTypeFont, false);
        }
        TrueTypeFont trueTypeFont2 = (TrueTypeFont) findFont(FontFormat.TTF, getFallbackFontName(pDFontDescriptor));
        if (trueTypeFont2 == null) {
            trueTypeFont2 = this.lastResortFont;
        }
        return new FontMapping<>(trueTypeFont2, true);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.FontMapper
    public FontMapping<FontBoxFont> getFontBoxFont(String str, PDFontDescriptor pDFontDescriptor) {
        FontBoxFont findFontBoxFont = findFontBoxFont(str);
        if (findFontBoxFont != null) {
            return new FontMapping<>(findFontBoxFont, false);
        }
        FontBoxFont findFontBoxFont2 = findFontBoxFont(getFallbackFontName(pDFontDescriptor));
        if (findFontBoxFont2 == null) {
            findFontBoxFont2 = this.lastResortFont;
        }
        return new FontMapping<>(findFontBoxFont2, true);
    }

    private FontBoxFont findFontBoxFont(String str) {
        Type1Font type1Font = (Type1Font) findFont(FontFormat.PFB, str);
        if (type1Font != null) {
            return type1Font;
        }
        TrueTypeFont trueTypeFont = (TrueTypeFont) findFont(FontFormat.TTF, str);
        if (trueTypeFont != null) {
            return trueTypeFont;
        }
        OpenTypeFont openTypeFont = (OpenTypeFont) findFont(FontFormat.OTF, str);
        if (openTypeFont != null) {
            return openTypeFont;
        }
        return null;
    }

    private FontBoxFont findFont(FontFormat fontFormat, String str) {
        if (str == null) {
            return null;
        }
        if (this.fontProvider == null) {
            getProvider();
        }
        FontInfo font = getFont(fontFormat, str);
        if (font != null) {
            return font.getFont();
        }
        FontInfo font2 = getFont(fontFormat, str.replace("-", ""));
        if (font2 != null) {
            return font2.getFont();
        }
        for (String str2 : getSubstitutes(str)) {
            FontInfo font3 = getFont(fontFormat, str2);
            if (font3 != null) {
                return font3.getFont();
            }
        }
        FontInfo font4 = getFont(fontFormat, str.replace(",", "-"));
        if (font4 != null) {
            return font4.getFont();
        }
        FontInfo font5 = getFont(fontFormat, str + "-Regular");
        if (font5 != null) {
            return font5.getFont();
        }
        return null;
    }

    private FontInfo getFont(FontFormat fontFormat, String str) {
        if (str.contains("+")) {
            str = str.substring(str.indexOf(43) + 1);
        }
        FontInfo fontInfo = this.fontInfoByName.get(str.toLowerCase(Locale.ENGLISH));
        if (fontInfo == null || fontInfo.getFormat() != fontFormat) {
            return null;
        }
        if (PDFBoxConfig.isDebugEnabled()) {
            Log.d("PdfBox-Android", String.format("getFont('%s','%s') returns %s", fontFormat, str, fontInfo));
        }
        return fontInfo;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.FontMapper
    public CIDFontMapping getCIDFont(String str, PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo) {
        FontMatch poll;
        OpenTypeFont openTypeFont = (OpenTypeFont) findFont(FontFormat.OTF, str);
        if (openTypeFont != null) {
            return new CIDFontMapping(openTypeFont, null, false);
        }
        TrueTypeFont trueTypeFont = (TrueTypeFont) findFont(FontFormat.TTF, str);
        if (trueTypeFont != null) {
            return new CIDFontMapping(null, trueTypeFont, false);
        }
        if (pDCIDSystemInfo != null) {
            String str2 = pDCIDSystemInfo.getRegistry() + "-" + pDCIDSystemInfo.getOrdering();
            if ((str2.equals("Adobe-GB1") || str2.equals("Adobe-CNS1") || str2.equals("Adobe-Japan1") || str2.equals("Adobe-Korea1")) && (poll = getFontMatches(pDFontDescriptor, pDCIDSystemInfo).poll()) != null) {
                if (PDFBoxConfig.isDebugEnabled()) {
                    Log.d("PdfBox-Android", "Best match for '" + str + "': " + poll.info);
                }
                FontBoxFont font = poll.info.getFont();
                if (font instanceof OpenTypeFont) {
                    return new CIDFontMapping((OpenTypeFont) font, null, true);
                }
                if (font != null) {
                    return new CIDFontMapping(null, font, true);
                }
            }
        }
        return new CIDFontMapping(null, this.lastResortFont, true);
    }

    private PriorityQueue<FontMatch> getFontMatches(PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo) {
        PriorityQueue<FontMatch> priorityQueue = new PriorityQueue<>(20);
        for (FontInfo fontInfo : this.fontInfoByName.values()) {
            if (pDCIDSystemInfo == null || isCharSetMatch(pDCIDSystemInfo, fontInfo)) {
                FontMatch fontMatch = new FontMatch(fontInfo);
                if (pDFontDescriptor.getPanose() != null && fontInfo.getPanose() != null) {
                    PDPanoseClassification panose = pDFontDescriptor.getPanose().getPanose();
                    if (panose.getFamilyKind() == fontInfo.getPanose().getFamilyKind()) {
                        if (panose.getFamilyKind() != 0 || ((!fontInfo.getPostScriptName().toLowerCase().contains("barcode") && !fontInfo.getPostScriptName().startsWith(StandardStructureTypes.CODE)) || probablyBarcodeFont(pDFontDescriptor))) {
                            if (panose.getSerifStyle() == fontInfo.getPanose().getSerifStyle()) {
                                fontMatch.score += 2.0d;
                            } else if (panose.getSerifStyle() >= 2 && panose.getSerifStyle() <= 5 && fontInfo.getPanose().getSerifStyle() >= 2 && fontInfo.getPanose().getSerifStyle() <= 5) {
                                fontMatch.score += 1.0d;
                            } else if (panose.getSerifStyle() >= 11 && panose.getSerifStyle() <= 13 && fontInfo.getPanose().getSerifStyle() >= 11 && fontInfo.getPanose().getSerifStyle() <= 13) {
                                fontMatch.score += 1.0d;
                            } else if (panose.getSerifStyle() != 0 && fontInfo.getPanose().getSerifStyle() != 0) {
                                fontMatch.score -= 1.0d;
                            }
                            int weight = fontInfo.getPanose().getWeight();
                            int weightClassAsPanose = fontInfo.getWeightClassAsPanose();
                            if (Math.abs(weight - weightClassAsPanose) > 2) {
                                weight = weightClassAsPanose;
                            }
                            if (panose.getWeight() == weight) {
                                fontMatch.score += 2.0d;
                            } else if (panose.getWeight() > 1 && weight > 1) {
                                fontMatch.score += 1.0d - (Math.abs(panose.getWeight() - weight) * 0.5d);
                            }
                        }
                    }
                } else if (pDFontDescriptor.getFontWeight() > 0.0f && fontInfo.getWeightClass() > 0) {
                    fontMatch.score += 1.0d - ((Math.abs(pDFontDescriptor.getFontWeight() - fontInfo.getWeightClass()) / 100.0f) * 0.5d);
                }
                priorityQueue.add(fontMatch);
            }
        }
        return priorityQueue;
    }

    private boolean probablyBarcodeFont(PDFontDescriptor pDFontDescriptor) {
        String fontFamily = pDFontDescriptor.getFontFamily();
        if (fontFamily == null) {
            fontFamily = "";
        }
        String fontName = pDFontDescriptor.getFontName();
        String str = fontName != null ? fontName : "";
        return fontFamily.startsWith(StandardStructureTypes.CODE) || fontFamily.toLowerCase().contains("barcode") || str.startsWith(StandardStructureTypes.CODE) || str.toLowerCase().contains("barcode");
    }

    private boolean isCharSetMatch(PDCIDSystemInfo pDCIDSystemInfo, FontInfo fontInfo) {
        if (fontInfo.getCIDSystemInfo() != null) {
            return fontInfo.getCIDSystemInfo().getRegistry().equals(pDCIDSystemInfo.getRegistry()) && fontInfo.getCIDSystemInfo().getOrdering().equals(pDCIDSystemInfo.getOrdering());
        }
        long codePageRange = fontInfo.getCodePageRange();
        if ("MalgunGothic-Semilight".equals(fontInfo.getPostScriptName())) {
            codePageRange &= -1441793;
        }
        if (pDCIDSystemInfo.getOrdering().equals("GB1") && (codePageRange & 262144) == 262144) {
            return true;
        }
        if (pDCIDSystemInfo.getOrdering().equals("CNS1") && (codePageRange & FileUtils.ONE_MB) == FileUtils.ONE_MB) {
            return true;
        }
        if (pDCIDSystemInfo.getOrdering().equals("Japan1") && (codePageRange & 131072) == 131072) {
            return true;
        }
        if (pDCIDSystemInfo.getOrdering().equals("Korea1")) {
            return (codePageRange & 524288) == 524288 || (codePageRange & 2097152) == 2097152;
        }
        return false;
    }

    /* loaded from: classes3.dex */
    public static class FontMatch implements Comparable<FontMatch> {
        final FontInfo info;
        double score;

        FontMatch(FontInfo fontInfo) {
            this.info = fontInfo;
        }

        @Override // java.lang.Comparable
        public int compareTo(FontMatch fontMatch) {
            return Double.compare(fontMatch.score, this.score);
        }
    }

    private FontMatch printMatches(PriorityQueue<FontMatch> priorityQueue) {
        FontMatch peek = priorityQueue.peek();
        System.out.println("-------");
        while (!priorityQueue.isEmpty()) {
            FontMatch poll = priorityQueue.poll();
            FontInfo fontInfo = poll.info;
            System.out.println(poll.score + " | " + fontInfo.getMacStyle() + StringUtils.SPACE + fontInfo.getFamilyClass() + StringUtils.SPACE + fontInfo.getPanose() + StringUtils.SPACE + fontInfo.getCIDSystemInfo() + StringUtils.SPACE + fontInfo.getPostScriptName() + StringUtils.SPACE + fontInfo.getFormat());
        }
        System.out.println("-------");
        return peek;
    }
}
