package org.informatika.sirekap.support.pdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.ttf.OpenTypeScript;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.bouncycastle.i18n.TextBundle;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.Kecamatan;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.KotaKabupaten;
import org.informatika.sirekap.model.Provinsi;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;

/* compiled from: PdfGeneratorNew.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/pdf/PdfGeneratorNew;", "", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfGeneratorNew {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_FILENAMES_PER_PAGE = 10;
    public static final int MAX_WITNESSES_PER_PAGE = 8;
    public static final float PAGE_HORIZONTAL_MARGIN = 30.0f;
    public static final float PAGE_VERTICAL_MARGIN = 50.0f;
    public static final float WIDTH_NUMBER = 20.0f;
    public static final float WIDTH_TEXT = 275.0f;
    public static final float WIDTH_VALUE = 75.0f;

    @JvmStatic
    public static final void appendDocumentSecurityPage(PDDocument pDDocument, KeystoreManager keystoreManager) {
        Companion.appendDocumentSecurityPage(pDDocument, keystoreManager);
    }

    @JvmStatic
    public static final void appendFormCDataPages(PDDocument pDDocument, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf2, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf3, List<FormC1AdministrationComplete.SectionDataPdf> list, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf4, boolean z) {
        Companion.appendFormCDataPages(pDDocument, sectionDataPdf, sectionDataPdf2, sectionDataPdf3, list, sectionDataPdf4, z);
    }

    @JvmStatic
    public static final void appendFormCImageFileNamePages(PDDocument pDDocument, ArrayList<String> arrayList) {
        Companion.appendFormCImageFileNamePages(pDDocument, arrayList);
    }

    @JvmStatic
    public static final void appendFormCImagePages(PDDocument pDDocument, ArrayList<String> arrayList, String str, Integer num) {
        Companion.appendFormCImagePages(pDDocument, arrayList, str, num);
    }

    @JvmStatic
    public static final void appendFormCSalinanFirstPage(Context context, PDDocument pDDocument, KeystoreManager keystoreManager, Tps tps, String str, String str2, Integer num, TpsTime tpsTime, TpsTime tpsTime2, String str3) {
        Companion.appendFormCSalinanFirstPage(context, pDDocument, keystoreManager, tps, str, str2, num, tpsTime, tpsTime2, str3);
    }

    @JvmStatic
    public static final void appendWitnessPages(PDDocument pDDocument, List<WitnessWithShare> list) {
        Companion.appendWitnessPages(pDDocument, list);
    }

    @JvmStatic
    public static final String formatNumber(int i) {
        return Companion.formatNumber(i);
    }

    @JvmStatic
    public static final void init(Context context) {
        Companion.init(context);
    }

    @JvmStatic
    private static final void writeDocumentTitle(PDPageContentStream pDPageContentStream, String str, String str2, Integer num) {
        Companion.writeDocumentTitle(pDPageContentStream, str, str2, num);
    }

    @JvmStatic
    private static final void writeFormCDataSection(PDPageContentStream pDPageContentStream, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf, boolean z) {
        Companion.writeFormCDataSection(pDPageContentStream, sectionDataPdf, z);
    }

    @JvmStatic
    private static final void writePageTitle(PDPageContentStream pDPageContentStream, String str, String str2, Integer num) {
        Companion.writePageTitle(pDPageContentStream, str, str2, num);
    }

    @JvmStatic
    private static final void writeWrappedText(PDPageContentStream pDPageContentStream, String str, int i) {
        Companion.writeWrappedText(pDPageContentStream, str, i);
    }

    /* compiled from: PdfGeneratorNew.kt */
    @Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007JF\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J(\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` H\u0007JA\u0010!\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0006\u0010\"\u001a\u00020\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010$Jk\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u001f2\u0006\u0010,\u001a\u00020\u001f2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010/2\u0006\u00101\u001a\u00020\u001fH\u0007¢\u0006\u0002\u00102J\u001e\u00103\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u00104\u001a\b\u0012\u0004\u0012\u0002050\u0018H\u0007J\u0010\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u00020\u0004H\u0007J\u0010\u00108\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0007J \u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u001fH\u0002J\u0018\u0010>\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020\u001f2\u0006\u0010?\u001a\u00020\u001fH\u0002J5\u0010@\u001a\u00020\r2\u0006\u0010;\u001a\u00020<2\u0006\u0010A\u001a\u00020\u001f2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0004H\u0003¢\u0006\u0002\u0010DJ\"\u0010E\u001a\u00020\r2\u0006\u0010;\u001a\u00020<2\u0006\u0010F\u001a\u00020\u00142\b\b\u0002\u0010G\u001a\u00020\u001bH\u0003J5\u0010H\u001a\u00020\r2\u0006\u0010;\u001a\u00020<2\u0006\u0010A\u001a\u00020\u001f2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0004H\u0003¢\u0006\u0002\u0010DJ\"\u0010I\u001a\u00020\r2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u001f2\b\b\u0002\u0010C\u001a\u00020\u0004H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lorg/informatika/sirekap/support/pdf/PdfGeneratorNew$Companion;", "", "()V", "MAX_FILENAMES_PER_PAGE", "", "MAX_WITNESSES_PER_PAGE", "PAGE_HORIZONTAL_MARGIN", "", "PAGE_VERTICAL_MARGIN", "WIDTH_NUMBER", "WIDTH_TEXT", "WIDTH_VALUE", "appendDocumentSecurityPage", "", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "PKCS12KeystoreManager", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "appendFormCDataPages", "sectionI", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "sectionII", "sectionIII", "sectionIVs", "", "sectionV", "isPpwp", "", "appendFormCImageFileNamePages", "listImage", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "appendFormCImagePages", "pageTitle", "pageTitleMaxCharacter", "(Lcom/tom_roush/pdfbox/pdmodel/PDDocument;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/Integer;)V", "appendFormCSalinanFirstPage", "context", "Landroid/content/Context;", "KeystoreManager", "tps", "Lorg/informatika/sirekap/model/Tps;", "electionDescription", "documentTitle", "documentTitleMaxCharacters", "tpsTimePenghitunganSuara", "Lorg/informatika/sirekap/model/TpsTime;", "tpsTimePemungutanSuara", "defaultName", "(Landroid/content/Context;Lcom/tom_roush/pdfbox/pdmodel/PDDocument;Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;Lorg/informatika/sirekap/model/Tps;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lorg/informatika/sirekap/model/TpsTime;Lorg/informatika/sirekap/model/TpsTime;Ljava/lang/String;)V", "appendWitnessPages", "witnesses", "Lorg/informatika/sirekap/model/WitnessWithShare;", "formatNumber", OperatorName.ENDPATH, "init", "printUnicode", OperatorName.SET_FLATNESS, "contentStream", "Lcom/tom_roush/pdfbox/pdmodel/PDPageContentStream;", TextBundle.TEXT_ENTRY, "replaceUnicode", "unicodeCharacter", "writeDocumentTitle", "title", "subtitle", "maxCharacter", "(Lcom/tom_roush/pdfbox/pdmodel/PDPageContentStream;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "writeFormCDataSection", "section", "isPpwpSectionII", "writePageTitle", "writeWrappedText", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final String formatNumber(int i) {
            String format = new DecimalFormat("#,###").format(Integer.valueOf(i));
            Intrinsics.checkNotNullExpressionValue(format, "DecimalFormat(\"#,###\")\n                .format(n)");
            return StringsKt.padStart(StringsKt.replace$default(format, ",", ".", false, 4, (Object) null), 7, ' ');
        }

        @JvmStatic
        public final void init(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PDFBoxResourceLoader.init(context.getApplicationContext());
        }

        @JvmStatic
        public final void appendFormCSalinanFirstPage(Context context, PDDocument document, KeystoreManager KeystoreManager, Tps tps, String str, String documentTitle, Integer num, TpsTime tpsTime, TpsTime tpsTime2, String defaultName) {
            String str2;
            List<String> groupValues;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(KeystoreManager, "KeystoreManager");
            Intrinsics.checkNotNullParameter(tps, "tps");
            Intrinsics.checkNotNullParameter(documentTitle, "documentTitle");
            Intrinsics.checkNotNullParameter(defaultName, "defaultName");
            List publicKeyInformation$default = SecurityFacade.getPublicKeyInformation$default(SecurityFacade.INSTANCE, KeystoreManager, null, 2, null);
            if (publicKeyInformation$default.size() > 0) {
                MatchResult find$default = Regex.find$default(new Regex("CN=([^,]+),"), (CharSequence) ((Pair) publicKeyInformation$default.get(0)).getFirst(), 0, 2, null);
                str2 = (find$default == null || (groupValues = find$default.getGroupValues()) == null) ? null : groupValues.get(1);
            } else {
                str2 = defaultName;
            }
            PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
            document.addPage(pDPage);
            PDPageContentStream pDPageContentStream = new PDPageContentStream(document, pDPage);
            pDPageContentStream.beginText();
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
            pDPageContentStream.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
            pDPageContentStream.setLeading(14.0d);
            writeDocumentTitle$default(PdfGeneratorNew.Companion, pDPageContentStream, documentTitle, null, num, 4, null);
            pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
            pDPageContentStream.showText("Dokumen ini dibuat dan ditandatangani secara digital oleh Komisi Pemilihan Umum.");
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
            pDPageContentStream.showText("Detail Petugas:");
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
            if (str2 == null) {
                str2 = OpenTypeScript.UNKNOWN;
            }
            pDPageContentStream.showText("Nama Petugas: " + str2);
            pDPageContentStream.newLine();
            pDPageContentStream.showText("Device ID Petugas: " + SecurityFacade.INSTANCE.getDeviceId(context));
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
            pDPageContentStream.showText("Detail TPS:");
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
            pDPageContentStream.showText("Nama TPS: " + tps.getName());
            pDPageContentStream.newLine();
            pDPageContentStream.showText("Kode TPS: " + ElectionUtil.extractKodeTpsReal(tps.getKodeTps()));
            pDPageContentStream.newLine();
            Kelurahan kelurahan = tps.getKelurahan();
            pDPageContentStream.showText("Kelurahan: " + (kelurahan != null ? kelurahan.getName() : null));
            pDPageContentStream.newLine();
            Kecamatan kecamatan = tps.getKecamatan();
            pDPageContentStream.showText("Kecamatan: " + (kecamatan != null ? kecamatan.getName() : null));
            pDPageContentStream.newLine();
            KotaKabupaten kabko = tps.getKabko();
            pDPageContentStream.showText("Kota/Kabupaten: " + (kabko != null ? kabko.getName() : null));
            pDPageContentStream.newLine();
            Provinsi provinsi = tps.getProvinsi();
            pDPageContentStream.showText("Provinsi: " + (provinsi != null ? provinsi.getName() : null));
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            if (str != null) {
                pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
                pDPageContentStream.showText("Detail Pemilihan:");
                pDPageContentStream.newLine();
                pDPageContentStream.newLine();
                pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
                pDPageContentStream.showText("Pemilihan: " + str);
                pDPageContentStream.newLine();
                pDPageContentStream.newLine();
            }
            if (tpsTime2 != null) {
                pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
                pDPageContentStream.showText("Waktu Pemungutan Suara:");
                pDPageContentStream.newLine();
                pDPageContentStream.newLine();
                pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
                pDPageContentStream.showText("Mulai: " + tpsTime2.startDateTimeFormatted());
                pDPageContentStream.newLine();
                pDPageContentStream.showText("Selesai: " + tpsTime2.endDateTimeFormatted());
                pDPageContentStream.newLine();
                pDPageContentStream.newLine();
            }
            if (tpsTime != null) {
                pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
                pDPageContentStream.showText("Waktu Penghitungan Suara:");
                pDPageContentStream.newLine();
                pDPageContentStream.newLine();
                pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
                pDPageContentStream.showText("Mulai: " + tpsTime.startDateTimeFormatted());
                pDPageContentStream.newLine();
                pDPageContentStream.showText("Selesai: " + tpsTime.endDateTimeFormatted());
                pDPageContentStream.newLine();
                pDPageContentStream.newLine();
            }
            pDPageContentStream.endText();
            pDPageContentStream.close();
        }

        private final void printUnicode(int i, PDPageContentStream pDPageContentStream, String str) {
            List split$default;
            try {
                pDPageContentStream.showText(str);
            } catch (Exception e) {
                String message = e.getMessage();
                String str2 = (message == null || (split$default = StringsKt.split$default((CharSequence) message, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null)) == null) ? null : (String) CollectionsKt.firstOrNull((List<? extends Object>) split$default);
                Log.wtf("TAG", "Replacing " + str2);
                if (str2 != null && i < 10) {
                    String replaceUnicode = replaceUnicode(str, str2);
                    Log.wtf("TAG", str + " > " + replaceUnicode);
                    printUnicode(i + 1, pDPageContentStream, replaceUnicode);
                    return;
                }
                throw e;
            }
        }

        private final String replaceUnicode(String str, String str2) {
            try {
                int length = str.length();
                String str3 = "";
                for (int i = 0; i < length; i++) {
                    String str4 = "u+" + Integer.toHexString(str.codePointAt(i));
                    String lowerCase = str2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    if (!Intrinsics.areEqual(str4, lowerCase)) {
                        str3 = str3 + str.charAt(i);
                    }
                }
                return str3;
            } catch (Exception unused) {
                return "?";
            }
        }

        @JvmStatic
        public final void appendWitnessPages(PDDocument document, List<WitnessWithShare> witnesses) {
            int size;
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(witnesses, "witnesses");
            int i = 8;
            if (witnesses.size() % 8 == 0) {
                size = witnesses.size() / 8;
            } else {
                size = (witnesses.size() / 8) + 1;
            }
            int i2 = size;
            if (1 > i2) {
                return;
            }
            int i3 = 1;
            int i4 = 0;
            while (true) {
                PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
                document.addPage(pDPage);
                PDPageContentStream pDPageContentStream = new PDPageContentStream(document, pDPage);
                pDPageContentStream.beginText();
                pDPageContentStream.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
                pDPageContentStream.setLeading(14.0d);
                writePageTitle$default(PdfGeneratorNew.Companion, pDPageContentStream, "DAFTAR PPS, SAKSI, & PANWAS", i2 > 1 ? "Halaman " + i3 : null, null, 8, null);
                int i5 = i4;
                int i6 = 0;
                while (i6 < i && i5 <= CollectionsKt.getLastIndex(witnesses)) {
                    WitnessWithShare witnessWithShare = witnesses.get(i5);
                    pDPageContentStream.setFont(PDType1Font.HELVETICA, 10.0f);
                    try {
                        PdfGeneratorNew.Companion.printUnicode(0, pDPageContentStream, (i5 + 1) + ". " + witnessWithShare.getWitness().getNama());
                    } catch (Exception e) {
                        e.printStackTrace();
                        String message = e.getMessage();
                        Exception exc = e;
                        Log.e("TAG", message, exc);
                        FirebaseCrashlytics.getInstance().recordException(exc);
                        pDPageContentStream.showText((i5 + 1) + ". -");
                    }
                    pDPageContentStream.newLine();
                    pDPageContentStream.newLineAtOffset(20.0f, 0.0f);
                    pDPageContentStream.setFont(PDType1Font.HELVETICA, 8.0f);
                    try {
                        PdfGeneratorNew.Companion.printUnicode(0, pDPageContentStream, "NIK: " + witnessWithShare.getWitness().getNik());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        String message2 = e2.getMessage();
                        Exception exc2 = e2;
                        Log.e("TAG", message2, exc2);
                        FirebaseCrashlytics.getInstance().recordException(exc2);
                        pDPageContentStream.showText("NIK: -");
                    }
                    pDPageContentStream.newLine();
                    try {
                        PdfGeneratorNew.Companion.printUnicode(0, pDPageContentStream, "No Handphone: " + witnessWithShare.getWitness().getNoHandphoneFormatted());
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        String message3 = e3.getMessage();
                        Exception exc3 = e3;
                        Log.e("TAG", message3, exc3);
                        FirebaseCrashlytics.getInstance().recordException(exc3);
                        pDPageContentStream.showText("No Handphone: -");
                    }
                    pDPageContentStream.newLine();
                    pDPageContentStream.newLineAtOffset(-20.0f, 0.0f);
                    i5++;
                    i6++;
                    i = 8;
                }
                pDPageContentStream.endText();
                pDPageContentStream.close();
                if (i3 == i2) {
                    return;
                }
                i3++;
                i4 = i5;
                i = 8;
            }
        }

        @JvmStatic
        public final void appendFormCImageFileNamePages(PDDocument document, ArrayList<String> listImage) {
            int size;
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(listImage, "listImage");
            int i = 10;
            if (listImage.size() % 10 == 0) {
                size = listImage.size() / 10;
            } else {
                size = (listImage.size() / 10) + 1;
            }
            if (1 > size) {
                return;
            }
            int i2 = 1;
            int i3 = 0;
            while (true) {
                PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
                document.addPage(pDPage);
                PDPageContentStream pDPageContentStream = new PDPageContentStream(document, pDPage);
                pDPageContentStream.beginText();
                pDPageContentStream.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
                pDPageContentStream.setLeading(14.0d);
                writePageTitle$default(PdfGeneratorNew.Companion, pDPageContentStream, "DAFTAR FILE & TANDA TANGAN DIGITALNYA", size > 1 ? "Halaman " + i2 : null, null, 8, null);
                int i4 = 0;
                while (i4 < i && i3 <= CollectionsKt.getLastIndex(listImage)) {
                    String str = listImage.get(i3);
                    Intrinsics.checkNotNullExpressionValue(str, "listImage[indexImage]");
                    String str2 = str;
                    pDPageContentStream.setFont(PDType1Font.HELVETICA, 10.0f);
                    String substring = str2.substring(StringsKt.lastIndexOf$default((CharSequence) str2, RemoteSettings.FORWARD_SLASH_STRING, 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                    i3++;
                    pDPageContentStream.showText(i3 + ". " + substring);
                    pDPageContentStream.newLine();
                    pDPageContentStream.setFont(PDType1Font.HELVETICA, 8.0f);
                    pDPageContentStream.newLineAtOffset(20.0f, 0.0f);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    Bitmap decodeFile = BitmapFactory.decodeFile(StringsKt.replace$default(str2, ".jpg", ".png", false, 4, (Object) null));
                    decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    writeWrappedText$default(PdfGeneratorNew.Companion, pDPageContentStream, StringsKt.replace$default(SecurityFacade.INSTANCE.getImageSignature(str2), "\n", "", false, 4, (Object) null), 0, 4, null);
                    decodeFile.recycle();
                    pDPageContentStream.newLineAtOffset(-20.0f, 0.0f);
                    pDPageContentStream.newLine();
                    i4++;
                    i = 10;
                }
                pDPageContentStream.endText();
                pDPageContentStream.close();
                if (i2 == size) {
                    return;
                }
                i2++;
                i = 10;
            }
        }

        public static /* synthetic */ void appendFormCImagePages$default(Companion companion, PDDocument pDDocument, ArrayList arrayList, String str, Integer num, int i, Object obj) {
            if ((i & 8) != 0) {
                num = null;
            }
            companion.appendFormCImagePages(pDDocument, arrayList, str, num);
        }

        @JvmStatic
        public final void appendFormCImagePages(PDDocument document, ArrayList<String> listImage, String pageTitle, Integer num) {
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(listImage, "listImage");
            Intrinsics.checkNotNullParameter(pageTitle, "pageTitle");
            int i = 0;
            for (Object obj : listImage) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj;
                PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
                document.addPage(pDPage);
                PDPageContentStream pDPageContentStream = new PDPageContentStream(document, pDPage);
                pDPageContentStream.beginText();
                pDPageContentStream.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
                pDPageContentStream.setLeading(14.0d);
                PdfGeneratorNew.Companion.writePageTitle(pDPageContentStream, pageTitle, "Halaman " + i2, num);
                pDPageContentStream.endText();
                FileInputStream fileInputStream = new FileInputStream(str);
                Bitmap decodeFile = BitmapFactory.decodeFile(StringsKt.replace$default(str, ".jpg", ".png", false, 4, (Object) null));
                pDPageContentStream.drawImage(new PDImageXObject(document, new ByteArrayInputStream(ByteStreamsKt.readBytes(fileInputStream)), COSName.DCT_DECODE, decodeFile.getWidth(), decodeFile.getHeight(), 8, PDDeviceRGB.INSTANCE), PDRectangle.A4.getWidth() * 0.1f, PDRectangle.A4.getHeight() * 0.05f, PDRectangle.A4.getWidth() * 0.8f, PDRectangle.A4.getHeight() * 0.8f);
                pDPageContentStream.close();
                decodeFile.recycle();
                i = i2;
            }
        }

        @JvmStatic
        public final void appendFormCDataPages(PDDocument document, FormC1AdministrationComplete.SectionDataPdf sectionI, FormC1AdministrationComplete.SectionDataPdf sectionII, FormC1AdministrationComplete.SectionDataPdf sectionIII, List<FormC1AdministrationComplete.SectionDataPdf> sectionIVs, FormC1AdministrationComplete.SectionDataPdf sectionV, boolean z) {
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(sectionI, "sectionI");
            Intrinsics.checkNotNullParameter(sectionII, "sectionII");
            Intrinsics.checkNotNullParameter(sectionIII, "sectionIII");
            Intrinsics.checkNotNullParameter(sectionIVs, "sectionIVs");
            Intrinsics.checkNotNullParameter(sectionV, "sectionV");
            PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
            document.addPage(pDPage);
            PDPageContentStream pDPageContentStream = new PDPageContentStream(document, pDPage);
            pDPageContentStream.beginText();
            pDPageContentStream.setFont(PDType1Font.HELVETICA, 10.0f);
            pDPageContentStream.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
            pDPageContentStream.setLeading(14.0d);
            writePageTitle$default(PdfGeneratorNew.Companion, pDPageContentStream, "HALAMAN DATA FORM C.HASIL", "Halaman 1", null, 8, null);
            writeFormCDataSection$default(PdfGeneratorNew.Companion, pDPageContentStream, sectionI, false, 4, null);
            writeFormCDataSection$default(PdfGeneratorNew.Companion, pDPageContentStream, sectionII, false, 4, null);
            writeFormCDataSection$default(PdfGeneratorNew.Companion, pDPageContentStream, sectionIII, false, 4, null);
            pDPageContentStream.endText();
            pDPageContentStream.close();
            int i = 0;
            for (Object obj : sectionIVs) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PDPage pDPage2 = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
                document.addPage(pDPage2);
                PDPageContentStream pDPageContentStream2 = new PDPageContentStream(document, pDPage2);
                pDPageContentStream2.beginText();
                pDPageContentStream2.setFont(PDType1Font.HELVETICA, 10.0f);
                pDPageContentStream2.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
                pDPageContentStream2.setLeading(14.0d);
                writePageTitle$default(PdfGeneratorNew.Companion, pDPageContentStream2, "HALAMAN DATA FORM C.HASIL", "Halaman " + (i + 2), null, 8, null);
                PdfGeneratorNew.Companion.writeFormCDataSection(pDPageContentStream2, (FormC1AdministrationComplete.SectionDataPdf) obj, z);
                pDPageContentStream2.endText();
                pDPageContentStream2.close();
                i = i2;
            }
            PDPage pDPage3 = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
            document.addPage(pDPage3);
            PDPageContentStream pDPageContentStream3 = new PDPageContentStream(document, pDPage3);
            pDPageContentStream3.beginText();
            pDPageContentStream3.setFont(PDType1Font.HELVETICA, 10.0f);
            pDPageContentStream3.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
            pDPageContentStream3.setLeading(14.0d);
            writePageTitle$default(PdfGeneratorNew.Companion, pDPageContentStream3, "HALAMAN DATA FORM C.HASIL", "Halaman " + (sectionIVs.size() + 2), null, 8, null);
            writeFormCDataSection$default(PdfGeneratorNew.Companion, pDPageContentStream3, sectionV, false, 4, null);
            pDPageContentStream3.endText();
            pDPageContentStream3.close();
        }

        @JvmStatic
        public final void appendDocumentSecurityPage(PDDocument document, KeystoreManager PKCS12KeystoreManager) {
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(PKCS12KeystoreManager, "PKCS12KeystoreManager");
            List publicKeyInformation$default = SecurityFacade.getPublicKeyInformation$default(SecurityFacade.INSTANCE, PKCS12KeystoreManager, null, 2, null);
            PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
            document.addPage(pDPage);
            PDPageContentStream pDPageContentStream = new PDPageContentStream(document, pDPage);
            pDPageContentStream.beginText();
            pDPageContentStream.newLineAtOffset(30.0f, PDRectangle.A4.getHeight() - 50.0f);
            pDPageContentStream.setLeading(14.0d);
            writePageTitle$default(PdfGeneratorNew.Companion, pDPageContentStream, "HALAMAN INFORMASI KEAMANAN DOKUMEN", null, null, 12, null);
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
            pDPageContentStream.showText("Public Key Petugas:");
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
            pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
            if (publicKeyInformation$default.size() > 0) {
                writeWrappedText$default(PdfGeneratorNew.Companion, pDPageContentStream, (String) ((Pair) publicKeyInformation$default.get(1)).getSecond(), 0, 4, null);
            } else {
                writeWrappedText$default(PdfGeneratorNew.Companion, pDPageContentStream, "Tidak ada informasi public key", 0, 4, null);
            }
            pDPageContentStream.endText();
            pDPageContentStream.close();
        }

        static /* synthetic */ void writeDocumentTitle$default(Companion companion, PDPageContentStream pDPageContentStream, String str, String str2, Integer num, int i, Object obj) {
            if ((i & 4) != 0) {
                str2 = null;
            }
            if ((i & 8) != 0) {
                num = null;
            }
            companion.writeDocumentTitle(pDPageContentStream, str, str2, num);
        }

        @JvmStatic
        public final void writeDocumentTitle(PDPageContentStream pDPageContentStream, String str, String str2, Integer num) {
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 20.0f);
            pDPageContentStream.setLeading(20.0f);
            PdfGeneratorNew.Companion.writeWrappedText(pDPageContentStream, str, num != null ? num.intValue() : 100);
            pDPageContentStream.setLeading(14.0f);
            if (str2 != null) {
                pDPageContentStream.newLine();
                pDPageContentStream.setFont(PDType1Font.HELVETICA, 14.0f);
                pDPageContentStream.showText(str2);
            }
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
        }

        static /* synthetic */ void writePageTitle$default(Companion companion, PDPageContentStream pDPageContentStream, String str, String str2, Integer num, int i, Object obj) {
            if ((i & 4) != 0) {
                str2 = null;
            }
            if ((i & 8) != 0) {
                num = null;
            }
            companion.writePageTitle(pDPageContentStream, str, str2, num);
        }

        @JvmStatic
        public final void writePageTitle(PDPageContentStream pDPageContentStream, String str, String str2, Integer num) {
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 16.0f);
            pDPageContentStream.setLeading(16.0f);
            PdfGeneratorNew.Companion.writeWrappedText(pDPageContentStream, str, num != null ? num.intValue() : 100);
            pDPageContentStream.setLeading(14.0f);
            if (str2 != null) {
                pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
                pDPageContentStream.showText(str2);
            }
            pDPageContentStream.newLine();
            pDPageContentStream.newLine();
        }

        static /* synthetic */ void writeWrappedText$default(Companion companion, PDPageContentStream pDPageContentStream, String str, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = 61;
            }
            companion.writeWrappedText(pDPageContentStream, str, i);
        }

        @JvmStatic
        public final void writeWrappedText(PDPageContentStream pDPageContentStream, String str, int i) {
            int i2 = 0;
            while (true) {
                int i3 = i2 * i;
                if (i3 >= str.length()) {
                    return;
                }
                pDPageContentStream.showText(String.valueOf(str.subSequence(i3, RangesKt.coerceAtMost(i3 + i, str.length()))));
                pDPageContentStream.newLine();
                i2++;
            }
        }

        static /* synthetic */ void writeFormCDataSection$default(Companion companion, PDPageContentStream pDPageContentStream, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            companion.writeFormCDataSection(pDPageContentStream, sectionDataPdf, z);
        }

        @JvmStatic
        public final void writeFormCDataSection(PDPageContentStream pDPageContentStream, FormC1AdministrationComplete.SectionDataPdf sectionDataPdf, boolean z) {
            boolean z2;
            pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 10.0f);
            pDPageContentStream.showText(sectionDataPdf.getNumber());
            pDPageContentStream.newLineAtOffset(20.0f, 0.0f);
            pDPageContentStream.showText(sectionDataPdf.getTitle());
            float f = 275.0f;
            pDPageContentStream.newLineAtOffset(275.0f, 0.0f);
            if (sectionDataPdf.getMaleLabel() != null) {
                pDPageContentStream.showText(String.valueOf(sectionDataPdf.getMaleLabel()));
            }
            pDPageContentStream.newLineAtOffset(75.0f, 0.0f);
            if (sectionDataPdf.getFemaleLabel() != null) {
                pDPageContentStream.showText(String.valueOf(sectionDataPdf.getFemaleLabel()));
            }
            pDPageContentStream.newLineAtOffset(75.0f, 0.0f);
            pDPageContentStream.showText(sectionDataPdf.getTotalLabel());
            pDPageContentStream.newLineAtOffset(-425.0f, 0.0f);
            pDPageContentStream.newLineAtOffset(-20.0f, 0.0f);
            pDPageContentStream.newLine();
            Iterator<T> it = sectionDataPdf.getRows().iterator();
            while (true) {
                z2 = false;
                if (!it.hasNext()) {
                    break;
                }
                FormC1AdministrationComplete.RowDataPdf rowDataPdf = (FormC1AdministrationComplete.RowDataPdf) it.next();
                if (rowDataPdf.getType() == FormC1AdministrationComplete.RowDataPdfType.content) {
                    pDPageContentStream.setFont(PDType1Font.HELVETICA, 10.0f);
                    String wrap = WordUtils.wrap(rowDataPdf.getTitle(), 50);
                    Intrinsics.checkNotNullExpressionValue(wrap, "wrap(it.title, 50)");
                    List<String> split = new Regex("\\r?\\n").split(wrap, 0);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split, 10));
                    for (String str : split) {
                        arrayList.add(StringsKt.replace$default(StringsKt.replace$default(str, StringUtils.CR, "", false, 4, (Object) null), "\n", "", false, 4, (Object) null));
                    }
                    ArrayList arrayList2 = arrayList;
                    int size = arrayList2.size();
                    int i = 0;
                    while (i < size) {
                        if (i == 0 && rowDataPdf.getNumber() != null) {
                            pDPageContentStream.showText(String.valueOf(rowDataPdf.getNumber()));
                        }
                        pDPageContentStream.newLineAtOffset(20.0f, 0.0f);
                        pDPageContentStream.showText((String) arrayList2.get(i));
                        pDPageContentStream.setFont(PDType1Font.COURIER, 10.0f);
                        if (i == 0) {
                            pDPageContentStream.newLineAtOffset(f, 0.0f);
                            if (rowDataPdf.getMale() != null) {
                                pDPageContentStream.showText(PdfGeneratorNew.Companion.formatNumber(rowDataPdf.getMale().intValue()));
                            }
                            pDPageContentStream.newLineAtOffset(75.0f, 0.0f);
                            if (rowDataPdf.getFemale() != null) {
                                pDPageContentStream.showText(PdfGeneratorNew.Companion.formatNumber(rowDataPdf.getFemale().intValue()));
                            }
                            pDPageContentStream.newLineAtOffset(75.0f, 0.0f);
                            if (rowDataPdf.getTotal() != null) {
                                String formatNumber = PdfGeneratorNew.Companion.formatNumber(rowDataPdf.getTotal().intValue());
                                if (z && Intrinsics.areEqual((Object) rowDataPdf.isSesuai(), (Object) false)) {
                                    formatNumber = formatNumber + " (*)";
                                }
                                pDPageContentStream.showText(formatNumber);
                            }
                            pDPageContentStream.newLineAtOffset(-425.0f, 0.0f);
                        }
                        pDPageContentStream.setFont(PDType1Font.HELVETICA, 10.0f);
                        pDPageContentStream.newLineAtOffset(-20.0f, 0.0f);
                        pDPageContentStream.newLine();
                        i++;
                        f = 275.0f;
                    }
                } else {
                    pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 10.0f);
                    if (rowDataPdf.getNumber() != null) {
                        pDPageContentStream.showText(String.valueOf(rowDataPdf.getNumber()));
                    }
                    pDPageContentStream.newLineAtOffset(20.0f, 0.0f);
                    pDPageContentStream.showText(rowDataPdf.getTitle());
                    pDPageContentStream.newLineAtOffset(-20.0f, 0.0f);
                    pDPageContentStream.newLine();
                }
                f = 275.0f;
            }
            pDPageContentStream.newLine();
            if (z) {
                List<FormC1AdministrationComplete.RowDataPdf> rows = sectionDataPdf.getRows();
                if (!(rows instanceof Collection) || !rows.isEmpty()) {
                    Iterator<T> it2 = rows.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (Intrinsics.areEqual((Object) ((FormC1AdministrationComplete.RowDataPdf) it2.next()).isSesuai(), (Object) false)) {
                                z2 = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (z2) {
                    pDPageContentStream.showText("(*): hasil OMR dinyatakan tidak sesuai dengan yang tertera pada Form CHasil oleh KPPS");
                }
            }
        }
    }
}
