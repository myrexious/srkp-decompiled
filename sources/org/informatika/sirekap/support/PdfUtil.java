package org.informatika.sirekap.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmpbox.type.ThumbnailType;

/* compiled from: PdfUtil.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0007¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/PdfUtil;", "", "()V", "createPdf", "", "context", "Landroid/content/Context;", "passphrase", "", "pdfPath", "listImage", "Ljava/util/ArrayList;", "listSaksi", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfUtil {
    public final void createPdf(Context context, String passphrase, String pdfPath, ArrayList<String> listImage, ArrayList<String> listSaksi) {
        int i;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        Intrinsics.checkNotNullParameter(pdfPath, "pdfPath");
        Intrinsics.checkNotNullParameter(listImage, "listImage");
        Intrinsics.checkNotNullParameter(listSaksi, "listSaksi");
        PDDocument pDDocument = new PDDocument();
        String[] strArr = new String[2];
        ArrayList arrayList = new ArrayList(242);
        for (int i2 = 0; i2 < 242; i2++) {
            arrayList.add(PDPageLabelRange.STYLE_LETTERS_LOWER);
        }
        strArr[0] = CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null);
        ArrayList arrayList2 = new ArrayList(242);
        for (int i3 = 0; i3 < 242; i3++) {
            arrayList2.add(PDPageLabelRange.STYLE_LETTERS_LOWER);
        }
        strArr[1] = CollectionsKt.joinToString$default(arrayList2, null, null, null, 0, null, null, 63, null);
        List listOf = CollectionsKt.listOf((Object[]) strArr);
        float f = 2;
        PDPage pDPage = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() / f));
        pDDocument.addPage(pDPage);
        PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDPage);
        pDPageContentStream.beginText();
        pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
        float f2 = 30;
        pDPageContentStream.newLineAtOffset(5.0f, (PDRectangle.A4.getHeight() / f) - f2);
        pDPageContentStream.setLeading(14.0d);
        pDPageContentStream.showText("Salinan C. Hasil");
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
        pDPageContentStream.showText("        Nama Petugas: " + listOf.get(0));
        pDPageContentStream.newLine();
        pDPageContentStream.showText("        Device Id Petugas: " + Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "android_id"));
        pDPageContentStream.setFont(PDType1Font.HELVETICA, 13.0f);
        pDPageContentStream.newLine();
        pDPageContentStream.newLine();
        pDPageContentStream.showText("Daftar PPS, saksi, dan panwas pada TPS ini:");
        pDPageContentStream.setFont(PDType1Font.HELVETICA, 12.0f);
        Iterator<String> it = listSaksi.iterator();
        while (it.hasNext()) {
            pDPageContentStream.newLine();
            pDPageContentStream.showText("        " + it.next());
        }
        pDPageContentStream.endText();
        pDPageContentStream.close();
        PDPage pDPage2 = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() / f));
        pDDocument.addPage(pDPage2);
        PDPageContentStream pDPageContentStream2 = new PDPageContentStream(pDDocument, pDPage2);
        pDPageContentStream2.beginText();
        pDPageContentStream2.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
        pDPageContentStream2.newLineAtOffset(5.0f, (PDRectangle.A4.getHeight() / f) - f2);
        pDPageContentStream2.setLeading(14.0d);
        pDPageContentStream2.showText("Daftar file pada TPS ini beserta tanda tangan digitalnya");
        pDPageContentStream2.newLine();
        pDPageContentStream2.setFont(PDType1Font.HELVETICA, 10.0f);
        Iterator<String> it2 = listImage.iterator();
        int i4 = 1;
        while (true) {
            boolean hasNext = it2.hasNext();
            i = 100;
            str = ThumbnailType.IMAGE;
            if (!hasNext) {
                break;
            }
            String image = it2.next();
            Intrinsics.checkNotNullExpressionValue(image, "image");
            String substring = image.substring(StringsKt.lastIndexOf$default((CharSequence) image, RemoteSettings.FORWARD_SLASH_STRING, 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeFile = BitmapFactory.decodeFile(StringsKt.replace$default(image, ".jpg", ".png", false, 4, (Object) null));
            decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            pDPageContentStream2.showText(i4 + StringUtils.SPACE + substring);
            ArrayList arrayList3 = new ArrayList(172);
            for (int i5 = 0; i5 < 172; i5++) {
                arrayList3.add(PDPageLabelRange.STYLE_LETTERS_LOWER);
            }
            String joinToString$default = CollectionsKt.joinToString$default(arrayList3, null, null, null, 0, null, null, 63, null);
            pDPageContentStream2.newLine();
            pDPageContentStream2.showText("              " + ((Object) joinToString$default.subSequence(0, 61)));
            pDPageContentStream2.newLine();
            pDPageContentStream2.showText("              " + ((Object) joinToString$default.subSequence(61, 122)));
            pDPageContentStream2.newLine();
            pDPageContentStream2.showText("              " + ((Object) joinToString$default.subSequence(122, 172)));
            pDPageContentStream2.newLine();
            i4++;
            decodeFile.recycle();
        }
        pDPageContentStream2.endText();
        pDPageContentStream2.close();
        Iterator<String> it3 = listImage.iterator();
        while (it3.hasNext()) {
            String next = it3.next();
            PDPage pDPage3 = new PDPage();
            pDDocument.addPage(pDPage3);
            PDPageContentStream pDPageContentStream3 = new PDPageContentStream(pDDocument, pDPage3);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            Intrinsics.checkNotNullExpressionValue(next, str);
            Bitmap decodeFile2 = BitmapFactory.decodeFile(StringsKt.replace$default(next, ".jpg", ".png", false, 4, (Object) null));
            decodeFile2.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream2);
            pDPageContentStream3.drawImage(new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream2.toByteArray()), COSName.DCT_DECODE, decodeFile2.getWidth(), decodeFile2.getHeight(), 8, PDDeviceRGB.INSTANCE), 0.0f, 0.0f, PDRectangle.A4.getWidth() * 0.9f, PDRectangle.A4.getHeight() * 0.9f);
            pDPageContentStream3.close();
            decodeFile2.recycle();
            it3 = it3;
            str = str;
            i = 100;
        }
        PDPage pDPage4 = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() / f));
        pDDocument.addPage(pDPage4);
        PDPageContentStream pDPageContentStream4 = new PDPageContentStream(pDDocument, pDPage4);
        pDPageContentStream4.beginText();
        pDPageContentStream4.setFont(PDType1Font.HELVETICA_BOLD, 14.0f);
        pDPageContentStream4.newLineAtOffset(5.0f, (PDRectangle.A4.getHeight() / f) - 20);
        pDPageContentStream4.setLeading(14.0d);
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("Halaman Informasi Keamanan Dokumen");
        pDPageContentStream4.newLine();
        pDPageContentStream4.newLine();
        pDPageContentStream4.setFont(PDType1Font.HELVETICA, 12.0f);
        pDPageContentStream4.showText("        Public Key Petugas: ");
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("                " + ((Object) ((String) listOf.get(1)).subSequence(0, 61)));
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("                " + ((Object) ((String) listOf.get(1)).subSequence(61, 122)));
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("                " + ((Object) ((String) listOf.get(1)).subSequence(122, 182)));
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("                " + ((Object) ((String) listOf.get(1)).subSequence(182, 242)));
        pDPageContentStream4.newLine();
        pDPageContentStream4.showText("                " + ((Object) ((String) listOf.get(1)).subSequence(242, ((String) listOf.get(1)).length())));
        pDPageContentStream4.endText();
        pDPageContentStream4.close();
        pDDocument.save(pdfPath);
        pDDocument.close();
    }
}
