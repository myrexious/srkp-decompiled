package org.informatika.sirekap.support.pdf;

import android.content.Context;
import com.google.firebase.messaging.Constants;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.pdf.PdfGeneratorNew;
import org.informatika.sirekap.support.pdf.data.CHCandidateData;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;

/* compiled from: PdfFacade.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J¦\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000bJF\u0010!\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u000bJF\u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u000bJ\u001e\u0010%\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020*¨\u0006+"}, d2 = {"Lorg/informatika/sirekap/support/pdf/PdfFacade;", "", "()V", "generatePdfCSalinanHasil", "", "context", "Landroid/content/Context;", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "listImages", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "KeystoreManager", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "sectionI", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "sectionII", "sectionIII", "sectionIVs", "", "sectionV", "tps", "Lorg/informatika/sirekap/model/Tps;", "electionDescription", "witnesses", "Lorg/informatika/sirekap/model/WitnessWithShare;", "tpsTimePenghitunganSuara", "Lorg/informatika/sirekap/model/TpsTime;", "tpsTimePemungutanSuara", "isPpwp", "", "defaultName", "generatePdfDaftarHadir", "keystoreManager", "generatePdfKejadianKhusus", "PKCS12KeystoreManager", "generatePdfWitness", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/support/pdf/data/CHCandidateData;", "readNumber", "x", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfFacade {
    public static final PdfFacade INSTANCE = new PdfFacade();

    private PdfFacade() {
    }

    public final String readNumber(int i) {
        if (i < 0 || i >= 1000000000) {
            throw new InputMismatchException("the number is out of range");
        }
        return i == 0 ? "nol" : readNumber$bilangan(new String[]{"", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan"}, new String[]{"", "se", "dua ", "tiga ", "empat ", "lima ", "enam ", "tujuh ", "delapan ", "sembilan "}, i);
    }

    private static final String readNumber$bilangan(String[] strArr, String[] strArr2, int i) {
        int i2;
        if (i < 10) {
            return strArr[i];
        }
        if (i == 10) {
            return "sepuluh";
        }
        if (i < 20) {
            return strArr2[i - 10] + "belas";
        }
        String str = "";
        if (i < 100) {
            String str2 = strArr2[i / 10];
            if (i % 10 != 0) {
                str = StringUtils.SPACE + readNumber$bilangan(strArr, strArr2, i2);
            }
            return str2 + "puluh" + str;
        } else if (i < 1000) {
            int i3 = i / 100;
            int i4 = i % 100;
            String str3 = strArr2[i3];
            if (i4 != 0) {
                str = StringUtils.SPACE + readNumber$bilangan(strArr, strArr2, i4);
            }
            return str3 + "ratus" + str;
        } else if (i < 1000000) {
            int i5 = i / 1000;
            int i6 = i % 1000;
            if (i5 < 10) {
                String str4 = strArr2[i5];
                if (i6 != 0) {
                    str = StringUtils.SPACE + readNumber$bilangan(strArr, strArr2, i6);
                }
                return str4 + "ribu" + str;
            }
            String readNumber$bilangan = readNumber$bilangan(strArr, strArr2, i5);
            if (i6 != 0) {
                str = StringUtils.SPACE + readNumber$bilangan(strArr, strArr2, i6);
            }
            return readNumber$bilangan + " ribu" + str;
        } else {
            int i7 = i / DurationKt.NANOS_IN_MILLIS;
            int i8 = i % DurationKt.NANOS_IN_MILLIS;
            String readNumber$bilangan2 = readNumber$bilangan(strArr, strArr2, i7);
            if (i8 != 0) {
                str = StringUtils.SPACE + readNumber$bilangan(strArr, strArr2, i8);
            }
            return readNumber$bilangan2 + " juta" + str;
        }
    }

    public final void generatePdfCSalinanHasil(Context context, File file, ArrayList<String> listImages, KeystoreManager KeystoreManager, FormC1AdministrationComplete.SectionDataPdf sectionI, FormC1AdministrationComplete.SectionDataPdf sectionII, FormC1AdministrationComplete.SectionDataPdf sectionIII, List<FormC1AdministrationComplete.SectionDataPdf> sectionIVs, FormC1AdministrationComplete.SectionDataPdf sectionV, Tps tps, String electionDescription, List<WitnessWithShare> witnesses, TpsTime tpsTimePenghitunganSuara, TpsTime tpsTime, boolean z, String defaultName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(listImages, "listImages");
        Intrinsics.checkNotNullParameter(KeystoreManager, "KeystoreManager");
        Intrinsics.checkNotNullParameter(sectionI, "sectionI");
        Intrinsics.checkNotNullParameter(sectionII, "sectionII");
        Intrinsics.checkNotNullParameter(sectionIII, "sectionIII");
        Intrinsics.checkNotNullParameter(sectionIVs, "sectionIVs");
        Intrinsics.checkNotNullParameter(sectionV, "sectionV");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(electionDescription, "electionDescription");
        Intrinsics.checkNotNullParameter(witnesses, "witnesses");
        Intrinsics.checkNotNullParameter(tpsTimePenghitunganSuara, "tpsTimePenghitunganSuara");
        Intrinsics.checkNotNullParameter(defaultName, "defaultName");
        PdfGeneratorNew.Companion.init(context);
        PDDocument pDDocument = new PDDocument();
        pDDocument.save(file);
        pDDocument.close();
        PDDocument load = PDDocument.load(file);
        Intrinsics.checkNotNullExpressionValue(load, "load(file)");
        PdfGeneratorNew.Companion.appendFormCSalinanFirstPage(context, load, KeystoreManager, tps, electionDescription, "DOKUMEN C.HASIL SALINAN", (r25 & 64) != 0 ? null : null, (r25 & 128) != 0 ? null : tpsTimePenghitunganSuara, (r25 & 256) != 0 ? null : tpsTime, defaultName);
        PdfGeneratorNew.Companion.appendFormCImagePages$default(PdfGeneratorNew.Companion, load, listImages, "HALAMAN GAMBAR FORM C.HASIL", null, 8, null);
        PdfGeneratorNew.Companion.appendFormCImageFileNamePages(load, listImages);
        PdfGeneratorNew.Companion.appendFormCDataPages(load, sectionI, sectionII, sectionIII, sectionIVs, sectionV, z);
        PdfGeneratorNew.Companion companion = PdfGeneratorNew.Companion;
        List<WitnessWithShare> list = witnesses;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (WitnessWithShare witnessWithShare : list) {
            arrayList.add(witnessWithShare.toDecrypted());
        }
        companion.appendWitnessPages(load, arrayList);
        PdfGeneratorNew.Companion.appendDocumentSecurityPage(load, KeystoreManager);
        load.save(new FileOutputStream(file));
        load.close();
    }

    public final void generatePdfKejadianKhusus(Context context, File file, ArrayList<String> listImages, KeystoreManager PKCS12KeystoreManager, Tps tps, String defaultName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(listImages, "listImages");
        Intrinsics.checkNotNullParameter(PKCS12KeystoreManager, "PKCS12KeystoreManager");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(defaultName, "defaultName");
        PdfGeneratorNew.Companion.init(context);
        PDDocument pDDocument = new PDDocument();
        pDDocument.save(file);
        pDDocument.close();
        PDDocument load = PDDocument.load(file);
        Intrinsics.checkNotNullExpressionValue(load, "load(file)");
        PdfGeneratorNew.Companion.appendFormCSalinanFirstPage(context, load, PKCS12KeystoreManager, tps, null, "DOKUMEN SALINAN TANDA TERIMA SALINAN SAKSI & KEJADIAN KHUSUS", (r25 & 64) != 0 ? null : 37, (r25 & 128) != 0 ? null : null, (r25 & 256) != 0 ? null : null, defaultName);
        PdfGeneratorNew.Companion.appendFormCImagePages(load, listImages, "HALAMAN GAMBAR SALINAN TANDA TERIMA SALINAN SAKSI & KEJADIAN KHUSUS", 50);
        PdfGeneratorNew.Companion.appendFormCImageFileNamePages(load, listImages);
        PdfGeneratorNew.Companion.appendDocumentSecurityPage(load, PKCS12KeystoreManager);
        load.save(new FileOutputStream(file));
        load.close();
    }

    public final void generatePdfDaftarHadir(Context context, File file, ArrayList<String> listImages, KeystoreManager keystoreManager, Tps tps, String defaultName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(listImages, "listImages");
        Intrinsics.checkNotNullParameter(keystoreManager, "keystoreManager");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(defaultName, "defaultName");
        PdfGeneratorNew.Companion.init(context);
        PDDocument pDDocument = new PDDocument();
        pDDocument.save(file);
        pDDocument.close();
        PDDocument load = PDDocument.load(file);
        Intrinsics.checkNotNullExpressionValue(load, "load(file)");
        PdfGeneratorNew.Companion.appendFormCSalinanFirstPage(context, load, keystoreManager, tps, null, "DOKUMEN SALINAN DAFTAR HADIR", (r25 & 64) != 0 ? null : null, (r25 & 128) != 0 ? null : null, (r25 & 256) != 0 ? null : null, defaultName);
        PdfGeneratorNew.Companion.appendFormCImagePages$default(PdfGeneratorNew.Companion, load, listImages, "HALAMAN GAMBAR SALINAN DAFTAR HADIR", null, 8, null);
        PdfGeneratorNew.Companion.appendFormCImageFileNamePages(load, listImages);
        PdfGeneratorNew.Companion.appendDocumentSecurityPage(load, keystoreManager);
        load.save(new FileOutputStream(file));
        load.close();
    }

    public final void generatePdfWitness(Context context, CHCandidateData data, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(file, "file");
        new OldPdfGeneratorWithFileWitness(context, new ArrayList()).generate(file);
    }
}
