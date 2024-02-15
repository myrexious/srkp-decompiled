package org.informatika.sirekap.support.pdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.ttf.OpenTypeScript;
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
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;

/* compiled from: OldPdfGenerator.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0017R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/support/pdf/OldPdfGenerator;", "Lorg/informatika/sirekap/support/pdf/PdfGenerator;", "context", "Landroid/content/Context;", "listImage", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "listSaksi", "keystoreManager", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "title", "defaultName", "(Landroid/content/Context;Ljava/util/ArrayList;Ljava/util/ArrayList;Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;Ljava/lang/String;Ljava/lang/String;)V", "generate", "", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OldPdfGenerator extends PdfGenerator {
    private final String defaultName;
    private final KeystoreManager keystoreManager;
    private final ArrayList<String> listImage;
    private final ArrayList<String> listSaksi;
    private final String title;

    public /* synthetic */ OldPdfGenerator(Context context, ArrayList arrayList, ArrayList arrayList2, KeystoreManager keystoreManager, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, arrayList, arrayList2, keystoreManager, str, (i & 32) != 0 ? null : str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OldPdfGenerator(Context context, ArrayList<String> listImage, ArrayList<String> listSaksi, KeystoreManager keystoreManager, String title, String str) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listImage, "listImage");
        Intrinsics.checkNotNullParameter(listSaksi, "listSaksi");
        Intrinsics.checkNotNullParameter(keystoreManager, "keystoreManager");
        Intrinsics.checkNotNullParameter(title, "title");
        this.listImage = listImage;
        this.listSaksi = listSaksi;
        this.keystoreManager = keystoreManager;
        this.title = title;
        this.defaultName = str;
    }

    @Override // org.informatika.sirekap.support.pdf.PdfGenerator
    public void generate(File file) {
        int i;
        List<String> groupValues;
        Intrinsics.checkNotNullParameter(file, "file");
        Log.wtf("PDF", this.listSaksi.toString());
        PDDocument pDDocument = new PDDocument();
        String str = null;
        List publicKeyInformation$default = SecurityFacade.getPublicKeyInformation$default(SecurityFacade.INSTANCE, this.keystoreManager, null, 2, null);
        if (publicKeyInformation$default.size() > 0) {
            MatchResult find$default = Regex.find$default(new Regex("CN=([^,]+),"), (CharSequence) ((Pair) publicKeyInformation$default.get(0)).getFirst(), 0, 2, null);
            if (find$default != null && (groupValues = find$default.getGroupValues()) != null) {
                str = groupValues.get(1);
            }
        } else {
            str = this.defaultName;
        }
        PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
        pDDocument.addPage(pDPage);
        PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDPage);
        pDPageContentStream.beginText();
        pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
        float f = 30;
        pDPageContentStream.newLineAtOffset(5.0f, PDRectangle.A4.getHeight() - f);
        pDPageContentStream.setLeading(14.0d);
        pDPageContentStream.showText(this.title);
        pDPageContentStream.newLine();
        pDPageContentStream.newLine();
        pDPageContentStream.newLine();
        pDPageContentStream.newLine();
        pDPageContentStream.setFont(PDType1Font.HELVETICA, 13.0f);
        pDPageContentStream.showText("Dokumen ini dibuat dan ditandatangani secara digital oleh Komisi Pemilihan Umum");
        pDPageContentStream.newLine();
        pDPageContentStream.newLine();
        pDPageContentStream.showText("Detil pemilihan:");
        pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
        pDPageContentStream.newLine();
        if (str == null) {
            str = OpenTypeScript.UNKNOWN;
        }
        pDPageContentStream.showText("        Nama Petugas: " + str);
        pDPageContentStream.newLine();
        pDPageContentStream.showText("        Device Id Petugas: " + SecurityFacade.INSTANCE.getDeviceId(getContext()));
        pDPageContentStream.setFont(PDType1Font.HELVETICA, 13.0f);
        pDPageContentStream.newLine();
        pDPageContentStream.newLine();
        pDPageContentStream.showText("Daftar PPS, saksi, dan panwas pada TPS ini:");
        pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
        Iterator<String> it = this.listSaksi.iterator();
        while (it.hasNext()) {
            pDPageContentStream.newLine();
            pDPageContentStream.showText("        " + it.next());
        }
        pDPageContentStream.endText();
        pDPageContentStream.close();
        Log.d("OldPdfGenerator", "Before sign");
        PDPage pDPage2 = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
        pDDocument.addPage(pDPage2);
        PDPageContentStream pDPageContentStream2 = new PDPageContentStream(pDDocument, pDPage2);
        pDPageContentStream2.beginText();
        pDPageContentStream2.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
        pDPageContentStream2.newLineAtOffset(5.0f, PDRectangle.A4.getHeight() - f);
        pDPageContentStream2.setLeading(14.0d);
        pDPageContentStream2.showText("Daftar file pada TPS ini beserta tanda tangan digitalnya");
        pDPageContentStream2.newLine();
        pDPageContentStream2.setFont(PDType1Font.HELVETICA, 8.0f);
        Iterator<String> it2 = this.listImage.iterator();
        int i2 = 1;
        while (true) {
            i = 100;
            if (!it2.hasNext()) {
                break;
            }
            String image = it2.next();
            Intrinsics.checkNotNullExpressionValue(image, "image");
            String substring = image.substring(StringsKt.lastIndexOf$default((CharSequence) image, RemoteSettings.FORWARD_SLASH_STRING, 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeFile = BitmapFactory.decodeFile(StringsKt.replace$default(image, ".jpg", ".png", false, 4, (Object) null));
            decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            pDPageContentStream2.showText(i2 + StringUtils.SPACE + substring);
            String replace$default = StringsKt.replace$default(SecurityFacade.INSTANCE.getImageSignature(image), "\n", "", false, 4, (Object) null);
            Log.d("OldPdfGenerator", "Hash value " + replace$default);
            int i3 = 0;
            while (true) {
                int i4 = i3 * 61;
                if (i4 < replace$default.length()) {
                    CharSequence subSequence = replace$default.subSequence(i4, RangesKt.coerceAtMost(i4 + 61, replace$default.length()));
                    pDPageContentStream2.newLine();
                    pDPageContentStream2.showText("              " + ((Object) subSequence));
                    i3++;
                }
            }
            i2++;
            decodeFile.recycle();
        }
        pDPageContentStream2.endText();
        pDPageContentStream2.close();
        Iterator<String> it3 = this.listImage.iterator();
        while (it3.hasNext()) {
            String image2 = it3.next();
            PDPage pDPage3 = new PDPage();
            pDDocument.addPage(pDPage3);
            PDPageContentStream pDPageContentStream3 = new PDPageContentStream(pDDocument, pDPage3);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            Intrinsics.checkNotNullExpressionValue(image2, "image");
            Bitmap decodeFile2 = BitmapFactory.decodeFile(StringsKt.replace$default(image2, ".jpg", ".png", false, 4, (Object) null));
            decodeFile2.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream2);
            pDPageContentStream3.drawImage(new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream2.toByteArray()), COSName.DCT_DECODE, decodeFile2.getWidth(), decodeFile2.getHeight(), 8, PDDeviceRGB.INSTANCE), 0.0f, 0.0f, PDRectangle.A4.getWidth() * 0.9f, PDRectangle.A4.getHeight() * 0.9f);
            pDPageContentStream3.close();
            decodeFile2.recycle();
            i = i;
        }
        PDPage pDPage4 = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
        pDDocument.addPage(pDPage4);
        Log.d("OldPdfGenerator", "PDF Security Info");
        PDPageContentStream pDPageContentStream4 = new PDPageContentStream(pDDocument, pDPage4);
        pDPageContentStream4.beginText();
        pDPageContentStream4.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
        pDPageContentStream4.newLineAtOffset(5.0f, PDRectangle.A4.getHeight() - 20);
        pDPageContentStream4.setLeading(14.0d);
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("Halaman Informasi Keamanan Dokumen");
        pDPageContentStream4.newLine();
        pDPageContentStream4.newLine();
        pDPageContentStream4.setFont(PDType1Font.HELVETICA, 12.0f);
        pDPageContentStream4.showText("        Public Key Petugas: ");
        pDPageContentStream4.newLine();
        int i5 = 0;
        while (true) {
            int i6 = i5 * 61;
            if (i6 < ((String) ((Pair) publicKeyInformation$default.get(1)).getSecond()).length()) {
                CharSequence subSequence2 = ((String) ((Pair) publicKeyInformation$default.get(1)).getSecond()).subSequence(i6, RangesKt.coerceAtMost(i6 + 61, ((String) ((Pair) publicKeyInformation$default.get(1)).getSecond()).length()));
                pDPageContentStream4.newLine();
                pDPageContentStream4.showText("                " + ((Object) subSequence2));
                i5++;
            } else {
                pDPageContentStream4.endText();
                pDPageContentStream4.close();
                Log.d("OldPdfGenerator", "Try to save file");
                pDDocument.save(new FileOutputStream(file));
                pDDocument.close();
                return;
            }
        }
    }
}
