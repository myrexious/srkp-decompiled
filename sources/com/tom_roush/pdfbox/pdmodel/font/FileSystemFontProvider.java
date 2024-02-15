package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.cff.CFFCIDFont;
import com.tom_roush.fontbox.cff.CFFFont;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import com.tom_roush.fontbox.ttf.OTFParser;
import com.tom_roush.fontbox.ttf.OpenTypeFont;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeCollection;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.fontbox.util.autodetect.FontFileFinder;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.support.ElectionUtil;

/* loaded from: classes3.dex */
public final class FileSystemFontProvider extends FontProvider {
    private final FontCache cache;
    private final List<FSFontInfo> fontInfoList = new ArrayList();

    /* loaded from: classes3.dex */
    public static class FSFontInfo extends FontInfo {
        private final CIDSystemInfo cidSystemInfo;
        private final File file;
        private final FontFormat format;
        private final int macStyle;
        private final PDPanoseClassification panose;
        private final FileSystemFontProvider parent;
        private final String postScriptName;
        private final int sFamilyClass;
        private final int ulCodePageRange1;
        private final int ulCodePageRange2;
        private final int usWeightClass;

        private FSFontInfo(File file, FontFormat fontFormat, String str, CIDSystemInfo cIDSystemInfo, int i, int i2, int i3, int i4, int i5, byte[] bArr, FileSystemFontProvider fileSystemFontProvider) {
            this.file = file;
            this.format = fontFormat;
            this.postScriptName = str;
            this.cidSystemInfo = cIDSystemInfo;
            this.usWeightClass = i;
            this.sFamilyClass = i2;
            this.ulCodePageRange1 = i3;
            this.ulCodePageRange2 = i4;
            this.macStyle = i5;
            this.panose = (bArr == null || bArr.length < 10) ? null : new PDPanoseClassification(bArr);
            this.parent = fileSystemFontProvider;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public String getPostScriptName() {
            return this.postScriptName;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public FontFormat getFormat() {
            return this.format;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public CIDSystemInfo getCIDSystemInfo() {
            return this.cidSystemInfo;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public synchronized FontBoxFont getFont() {
            FontBoxFont type1Font;
            FontBoxFont font = this.parent.cache.getFont(this);
            if (font != null) {
                return font;
            }
            int i = AnonymousClass2.$SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat[this.format.ordinal()];
            if (i == 1) {
                type1Font = getType1Font(this.postScriptName, this.file);
            } else if (i == 2) {
                type1Font = getTrueTypeFont(this.postScriptName, this.file);
            } else if (i == 3) {
                type1Font = getOTFFont(this.postScriptName, this.file);
            } else {
                throw new RuntimeException("can't happen");
            }
            if (type1Font != null) {
                this.parent.cache.addFont(this, type1Font);
            }
            return type1Font;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public int getFamilyClass() {
            return this.sFamilyClass;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public int getWeightClass() {
            return this.usWeightClass;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public int getCodePageRange1() {
            return this.ulCodePageRange1;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public int getCodePageRange2() {
            return this.ulCodePageRange2;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public int getMacStyle() {
            return this.macStyle;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public PDPanoseClassification getPanose() {
            return this.panose;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.font.FontInfo
        public String toString() {
            return super.toString() + StringUtils.SPACE + this.file;
        }

        private TrueTypeFont getTrueTypeFont(String str, File file) {
            try {
                TrueTypeFont readTrueTypeFont = readTrueTypeFont(str, file);
                if (PDFBoxConfig.isDebugEnabled()) {
                    Log.d("PdfBox-Android", "Loaded " + str + " from " + file);
                }
                return readTrueTypeFont;
            } catch (IOException e) {
                Log.w("PdfBox-Android", "Could not load font file: " + file, e);
                return null;
            }
        }

        private TrueTypeFont readTrueTypeFont(String str, File file) throws IOException {
            if (file.getName().toLowerCase().endsWith(".ttc")) {
                TrueTypeCollection trueTypeCollection = new TrueTypeCollection(file);
                try {
                    TrueTypeFont fontByName = trueTypeCollection.getFontByName(str);
                    if (fontByName != null) {
                        return fontByName;
                    }
                    trueTypeCollection.close();
                    throw new IOException("Font " + str + " not found in " + file);
                } catch (IOException e) {
                    trueTypeCollection.close();
                    throw e;
                }
            }
            return new TTFParser(false, true).parse(file);
        }

        private OpenTypeFont getOTFFont(String str, File file) {
            try {
                if (file.getName().toLowerCase().endsWith(".ttc")) {
                    TrueTypeCollection trueTypeCollection = new TrueTypeCollection(file);
                    try {
                        TrueTypeFont fontByName = trueTypeCollection.getFontByName(str);
                        if (fontByName == null) {
                            trueTypeCollection.close();
                            throw new IOException("Font " + str + " not found in " + file);
                        }
                        return (OpenTypeFont) fontByName;
                    } catch (IOException e) {
                        Log.e("PdfBox-Android", e.getMessage(), e);
                        trueTypeCollection.close();
                        return null;
                    }
                }
                OpenTypeFont parse = new OTFParser(false, true).parse(file);
                if (PDFBoxConfig.isDebugEnabled()) {
                    Log.d("PdfBox-Android", "Loaded " + str + " from " + file);
                }
                return parse;
            } catch (IOException e2) {
                Log.w("PdfBox-Android", "Could not load font file: " + file, e2);
                return null;
            }
        }

        private Type1Font getType1Font(String str, File file) {
            FileInputStream fileInputStream;
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (IOException e) {
                e = e;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileInputStream2);
                throw th;
            }
            try {
                try {
                    Type1Font createWithPFB = Type1Font.createWithPFB(fileInputStream);
                    if (PDFBoxConfig.isDebugEnabled()) {
                        Log.d("PdfBox-Android", "Loaded " + str + " from " + file);
                    }
                    IOUtils.closeQuietly(fileInputStream);
                    return createWithPFB;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    IOUtils.closeQuietly(fileInputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                Log.w("PdfBox-Android", "Could not load font file: " + file, e);
                IOUtils.closeQuietly(fileInputStream);
                return null;
            }
        }
    }

    /* renamed from: com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider$2 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat;

        static {
            int[] iArr = new int[FontFormat.values().length];
            $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat = iArr;
            try {
                iArr[FontFormat.PFB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat[FontFormat.TTF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$font$FontFormat[FontFormat.OTF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class FSIgnored extends FSFontInfo {
        private FSIgnored(File file, FontFormat fontFormat, String str) {
            super(file, fontFormat, str, null, 0, 0, 0, 0, 0, null, null);
        }
    }

    public FileSystemFontProvider(FontCache fontCache) {
        this.cache = fontCache;
        if (PDFBoxConfig.getFontLoadLevel() == PDFBoxConfig.FontLoadLevel.NONE) {
            return;
        }
        if (PDFBoxConfig.getFontLoadLevel() == PDFBoxConfig.FontLoadLevel.MINIMUM) {
            try {
                addTrueTypeFont(new File("/system/fonts/DroidSans.ttf"));
                addTrueTypeFont(new File("/system/fonts/DroidSans-Bold.ttf"));
                addTrueTypeFont(new File("/system/fonts/DroidSansMono.ttf"));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (PDFBoxConfig.isDebugEnabled()) {
                Log.d("PdfBox-Android", "Will search the local system for fonts");
            }
            List<URI> find = new FontFileFinder().find();
            ArrayList arrayList = new ArrayList(find.size());
            for (URI uri : find) {
                arrayList.add(new File(uri));
            }
            if (PDFBoxConfig.isDebugEnabled()) {
                Log.d("PdfBox-Android", "Found " + arrayList.size() + " fonts on the local system");
            }
            if (arrayList.isEmpty()) {
                return;
            }
            List<FSFontInfo> loadDiskCache = loadDiskCache(arrayList);
            if (loadDiskCache != null && !loadDiskCache.isEmpty()) {
                this.fontInfoList.addAll(loadDiskCache);
                return;
            }
            Log.w("PdfBox-Android", "Building on-disk font cache, this may take a while");
            scanFonts(arrayList);
            saveDiskCache();
            Log.w("PdfBox-Android", "Finished building on-disk font cache, found " + this.fontInfoList.size() + " fonts");
        } catch (AccessControlException e2) {
            Log.e("PdfBox-Android", "Error accessing the file system", e2);
        }
    }

    private void scanFonts(List<File> list) {
        String lowerCase;
        for (File file : list) {
            try {
                lowerCase = file.getPath().toLowerCase();
            } catch (IOException e) {
                Log.w("PdfBox-Android", "Error parsing font " + file.getPath(), e);
            }
            if (!lowerCase.endsWith(".ttf") && !lowerCase.endsWith(".otf")) {
                if (!lowerCase.endsWith(".ttc") && !lowerCase.endsWith(".otc")) {
                    if (lowerCase.endsWith(".pfb")) {
                        addType1Font(file);
                    }
                }
                addTrueTypeCollection(file);
            }
            addTrueTypeFont(file);
        }
    }

    private File getDiskCacheFile() {
        String property = System.getProperty("pdfbox.fontcache");
        if (isBadPath(property)) {
            property = System.getProperty("user.home");
            if (isBadPath(property)) {
                property = System.getProperty("java.io.tmpdir");
            }
        }
        return new File(property, ".pdfbox.cache");
    }

    private static boolean isBadPath(String str) {
        return (str != null && new File(str).isDirectory() && new File(str).canWrite()) ? false : true;
    }

    private void saveDiskCache() {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(getDiskCacheFile()));
                try {
                    for (FSFontInfo fSFontInfo : this.fontInfoList) {
                        bufferedWriter2.write(fSFontInfo.postScriptName.trim());
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        bufferedWriter2.write(fSFontInfo.format.toString());
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        if (fSFontInfo.cidSystemInfo != null) {
                            bufferedWriter2.write(fSFontInfo.cidSystemInfo.getRegistry() + Soundex.SILENT_MARKER + fSFontInfo.cidSystemInfo.getOrdering() + Soundex.SILENT_MARKER + fSFontInfo.cidSystemInfo.getSupplement());
                        }
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        if (fSFontInfo.usWeightClass > -1) {
                            bufferedWriter2.write(Integer.toHexString(fSFontInfo.usWeightClass));
                        }
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        if (fSFontInfo.sFamilyClass > -1) {
                            bufferedWriter2.write(Integer.toHexString(fSFontInfo.sFamilyClass));
                        }
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        bufferedWriter2.write(Integer.toHexString(fSFontInfo.ulCodePageRange1));
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        bufferedWriter2.write(Integer.toHexString(fSFontInfo.ulCodePageRange2));
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        if (fSFontInfo.macStyle > -1) {
                            bufferedWriter2.write(Integer.toHexString(fSFontInfo.macStyle));
                        }
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        if (fSFontInfo.panose != null) {
                            byte[] bytes = fSFontInfo.panose.getBytes();
                            for (int i = 0; i < 10; i++) {
                                String hexString = Integer.toHexString(bytes[i]);
                                if (hexString.length() == 1) {
                                    bufferedWriter2.write(48);
                                }
                                bufferedWriter2.write(hexString);
                            }
                        }
                        bufferedWriter2.write(ElectionUtil.KODE_TPS_SEPARATOR);
                        bufferedWriter2.write(fSFontInfo.file.getAbsolutePath());
                        bufferedWriter2.newLine();
                    }
                    IOUtils.closeQuietly(bufferedWriter2);
                } catch (IOException e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    Log.w("PdfBox-Android", "Could not write to font cache", e);
                    Log.w("PdfBox-Android", "Installed fonts information will have to be reloaded for each start");
                    Log.w("PdfBox-Android", "You can assign a directory to the 'pdfbox.fontcache' property");
                    IOUtils.closeQuietly(bufferedWriter);
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    IOUtils.closeQuietly(bufferedWriter);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            } catch (SecurityException unused) {
                IOUtils.closeQuietly(null);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0197 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider.FSFontInfo> loadDiskCache(java.util.List<java.io.File> r21) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider.loadDiskCache(java.util.List):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void addTrueTypeCollection(final java.io.File r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "Could not load font file: "
            r1 = 0
            com.tom_roush.fontbox.ttf.TrueTypeCollection r2 = new com.tom_roush.fontbox.ttf.TrueTypeCollection     // Catch: java.lang.Throwable -> L16 java.io.IOException -> L18
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L16 java.io.IOException -> L18
            com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider$1 r1 = new com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider$1     // Catch: java.io.IOException -> L14 java.lang.Throwable -> L32
            r1.<init>()     // Catch: java.io.IOException -> L14 java.lang.Throwable -> L32
            r2.processAllFonts(r1)     // Catch: java.io.IOException -> L14 java.lang.Throwable -> L32
        L10:
            r2.close()
            goto L31
        L14:
            r1 = move-exception
            goto L1c
        L16:
            r7 = move-exception
            goto L34
        L18:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L1c:
            java.lang.String r3 = "PdfBox-Android"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L32
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L32
            java.lang.StringBuilder r7 = r4.append(r7)     // Catch: java.lang.Throwable -> L32
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L32
            android.util.Log.w(r3, r7, r1)     // Catch: java.lang.Throwable -> L32
            if (r2 == 0) goto L31
            goto L10
        L31:
            return
        L32:
            r7 = move-exception
            r1 = r2
        L34:
            if (r1 == 0) goto L39
            r1.close()
        L39:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.FileSystemFontProvider.addTrueTypeCollection(java.io.File):void");
    }

    private void addTrueTypeFont(File file) throws IOException {
        try {
            if (file.getPath().toLowerCase().endsWith(".otf")) {
                addTrueTypeFontImpl(new OTFParser(false, true).parse(file), file);
            } else {
                addTrueTypeFontImpl(new TTFParser(false, true).parse(file), file);
            }
        } catch (IOException e) {
            Log.w("PdfBox-Android", "Could not load font file: " + file, e);
        }
    }

    /* JADX WARN: Not initialized variable reg: 25, insn: 0x016b: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r25 I:??[OBJECT, ARRAY]), block:B:143:0x0168 */
    public void addTrueTypeFontImpl(TrueTypeFont trueTypeFont, File file) throws IOException {
        AnonymousClass1 anonymousClass1;
        String str;
        String str2;
        File file2;
        FileSystemFontProvider fileSystemFontProvider;
        int i;
        int i2;
        int i3;
        int i4;
        byte[] bArr;
        String str3;
        String str4;
        CIDSystemInfo cIDSystemInfo;
        String str5;
        String str6;
        NamingTable naming;
        CIDSystemInfo cIDSystemInfo2;
        try {
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
                anonymousClass1 = null;
                str = "Could not load font file: ";
                str2 = "PdfBox-Android";
                file2 = file;
            }
            if (trueTypeFont.getName() != null && trueTypeFont.getName().contains(ElectionUtil.KODE_TPS_SEPARATOR)) {
                this.fontInfoList.add(new FSIgnored(file, FontFormat.TTF, "*skippipeinname*"));
                Log.w("PdfBox-Android", "Skipping font with '|' in name " + trueTypeFont.getName() + " in file " + file);
            } else if (trueTypeFont.getName() != null) {
                try {
                    try {
                        if (trueTypeFont.getHeader() == null) {
                            this.fontInfoList.add(new FSIgnored(file, FontFormat.TTF, trueTypeFont.getName()));
                            trueTypeFont.close();
                            return;
                        }
                        int macStyle = trueTypeFont.getHeader().getMacStyle();
                        OS2WindowsMetricsTable oS2Windows = trueTypeFont.getOS2Windows();
                        if (oS2Windows != null) {
                            int familyClass = oS2Windows.getFamilyClass();
                            int weightClass = oS2Windows.getWeightClass();
                            int codePageRange1 = (int) oS2Windows.getCodePageRange1();
                            int codePageRange2 = (int) oS2Windows.getCodePageRange2();
                            bArr = oS2Windows.getPanose();
                            i3 = codePageRange1;
                            i4 = codePageRange2;
                            i2 = familyClass;
                            i = weightClass;
                        } else {
                            i = -1;
                            i2 = -1;
                            i3 = 0;
                            i4 = 0;
                            bArr = null;
                        }
                        try {
                            try {
                                if (trueTypeFont instanceof OpenTypeFont) {
                                    try {
                                        if (((OpenTypeFont) trueTypeFont).isPostScript()) {
                                            str4 = "OTF";
                                            CFFFont font = ((OpenTypeFont) trueTypeFont).getCFF().getFont();
                                            if (font instanceof CFFCIDFont) {
                                                CFFCIDFont cFFCIDFont = (CFFCIDFont) font;
                                                cIDSystemInfo = new CIDSystemInfo(cFFCIDFont.getRegistry(), cFFCIDFont.getOrdering(), cFFCIDFont.getSupplement());
                                            } else {
                                                cIDSystemInfo = null;
                                            }
                                            str = "Could not load font file: ";
                                            str5 = "PdfBox-Android";
                                            this.fontInfoList.add(new FSFontInfo(file, FontFormat.OTF, trueTypeFont.getName(), cIDSystemInfo, i, i2, i3, i4, macStyle, bArr, this));
                                            str6 = str4;
                                            if (PDFBoxConfig.isDebugEnabled() && (naming = trueTypeFont.getNaming()) != null) {
                                                String str7 = str6 + ": '" + naming.getPostScriptName() + "' / '" + naming.getFontFamily() + "' / '" + naming.getFontSubFamily() + OperatorName.SHOW_TEXT_LINE;
                                                str2 = str5;
                                                try {
                                                    Log.d(str2, str7);
                                                } catch (IOException e2) {
                                                    e = e2;
                                                    fileSystemFontProvider = this;
                                                    file2 = file;
                                                    anonymousClass1 = null;
                                                    fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                                                    Log.w(str2, str + file2, e);
                                                    trueTypeFont.close();
                                                }
                                            }
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                        str = "Could not load font file: ";
                                        file2 = file;
                                        anonymousClass1 = null;
                                        str2 = "PdfBox-Android";
                                        fileSystemFontProvider = this;
                                        fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                                        Log.w(str2, str + file2, e);
                                        trueTypeFont.close();
                                    }
                                }
                                if (trueTypeFont.getTableMap().containsKey("gcid")) {
                                    byte[] tableBytes = trueTypeFont.getTableBytes(trueTypeFont.getTableMap().get("gcid"));
                                    String str8 = new String(tableBytes, 10, 64, Charsets.US_ASCII);
                                    String substring = str8.substring(0, str8.indexOf(0));
                                    String str9 = new String(tableBytes, 76, 64, Charsets.US_ASCII);
                                    cIDSystemInfo2 = new CIDSystemInfo(substring, str9.substring(0, str9.indexOf(0)), tableBytes[141] & UByte.MAX_VALUE & (tableBytes[140] << 8));
                                } else {
                                    cIDSystemInfo2 = null;
                                }
                                str4 = "TTF";
                                this.fontInfoList.add(new FSFontInfo(file, FontFormat.TTF, trueTypeFont.getName(), cIDSystemInfo2, i, i2, i3, i4, macStyle, bArr, this));
                                str6 = str4;
                                if (PDFBoxConfig.isDebugEnabled()) {
                                    String str72 = str6 + ": '" + naming.getPostScriptName() + "' / '" + naming.getFontFamily() + "' / '" + naming.getFontSubFamily() + OperatorName.SHOW_TEXT_LINE;
                                    str2 = str5;
                                    Log.d(str2, str72);
                                }
                            } catch (IOException e4) {
                                e = e4;
                                str2 = str5;
                                fileSystemFontProvider = this;
                                file2 = file;
                                anonymousClass1 = null;
                                fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                                Log.w(str2, str + file2, e);
                                trueTypeFont.close();
                            }
                            str = "Could not load font file: ";
                            str5 = "PdfBox-Android";
                        } catch (IOException e5) {
                            e = e5;
                            file2 = file;
                            fileSystemFontProvider = this;
                            str2 = str3;
                            anonymousClass1 = null;
                            fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                            Log.w(str2, str + file2, e);
                            trueTypeFont.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        trueTypeFont.close();
                        throw th;
                    }
                } catch (IOException e6) {
                    e = e6;
                    str = "Could not load font file: ";
                    str2 = "PdfBox-Android";
                    fileSystemFontProvider = this;
                    file2 = file;
                    anonymousClass1 = null;
                    fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                    Log.w(str2, str + file2, e);
                    trueTypeFont.close();
                }
            } else {
                str = "Could not load font file: ";
                str2 = "PdfBox-Android";
                fileSystemFontProvider = this;
                try {
                    file2 = file;
                    anonymousClass1 = null;
                    try {
                        fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipnoname*"));
                        Log.w(str2, "Missing 'name' entry for PostScript name in font " + file2);
                    } catch (IOException e7) {
                        e = e7;
                        fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                        Log.w(str2, str + file2, e);
                        trueTypeFont.close();
                    }
                } catch (IOException e8) {
                    e = e8;
                    file2 = file;
                    anonymousClass1 = null;
                    fileSystemFontProvider.fontInfoList.add(new FSIgnored(file2, FontFormat.TTF, "*skipexception*"));
                    Log.w(str2, str + file2, e);
                    trueTypeFont.close();
                }
            }
            trueTypeFont.close();
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private void addType1Font(File file) throws IOException {
        FileInputStream fileInputStream;
        String str;
        String str2;
        Type1Font createWithPFB;
        FileInputStream fileInputStream2 = new FileInputStream(file);
        try {
            try {
                createWithPFB = Type1Font.createWithPFB(fileInputStream2);
            } catch (IOException e) {
                e = e;
                fileInputStream = fileInputStream2;
                str = "Could not load font file: ";
                str2 = "PdfBox-Android";
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                fileInputStream.close();
                throw th;
            }
            if (createWithPFB.getName() == null) {
                this.fontInfoList.add(new FSIgnored(file, FontFormat.PFB, "*skipnoname*"));
                Log.w("PdfBox-Android", "Missing 'name' entry for PostScript name in font " + file);
                fileInputStream2.close();
            } else if (createWithPFB.getName().contains(ElectionUtil.KODE_TPS_SEPARATOR)) {
                this.fontInfoList.add(new FSIgnored(file, FontFormat.PFB, "*skippipeinname*"));
                Log.w("PdfBox-Android", "Skipping font with '|' in name " + createWithPFB.getName() + " in file " + file);
                fileInputStream2.close();
            } else {
                fileInputStream = fileInputStream2;
                str = "Could not load font file: ";
                str2 = "PdfBox-Android";
                try {
                    this.fontInfoList.add(new FSFontInfo(file, FontFormat.PFB, createWithPFB.getName(), null, -1, -1, 0, 0, -1, null, this));
                    if (PDFBoxConfig.isDebugEnabled()) {
                        Log.d(str2, "PFB: '" + createWithPFB.getName() + "' / '" + createWithPFB.getFamilyName() + "' / '" + createWithPFB.getWeight() + OperatorName.SHOW_TEXT_LINE);
                    }
                } catch (IOException e2) {
                    e = e2;
                    Log.w(str2, str + file, e);
                    fileInputStream.close();
                }
                fileInputStream.close();
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream.close();
            throw th;
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.FontProvider
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        for (FSFontInfo fSFontInfo : this.fontInfoList) {
            sb.append(fSFontInfo.getFormat());
            sb.append(": ");
            sb.append(fSFontInfo.getPostScriptName());
            sb.append(": ");
            sb.append(fSFontInfo.file.getPath());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.FontProvider
    public List<? extends FontInfo> getFontInfo() {
        return this.fontInfoList;
    }
}
