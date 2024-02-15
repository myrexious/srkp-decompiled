package org.informatika.sirekap.support.pdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.text.lookup.StringLookupFactory;

/* compiled from: OldPdfGeneratorWithFileWitness.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/pdf/OldPdfGeneratorWithFileWitness;", "Lorg/informatika/sirekap/support/pdf/PdfGenerator;", "context", "Landroid/content/Context;", "listImage", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "generate", "", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OldPdfGeneratorWithFileWitness extends PdfGenerator {
    private final ArrayList<String> listImage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OldPdfGeneratorWithFileWitness(Context context, ArrayList<String> listImage) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listImage, "listImage");
        this.listImage = listImage;
    }

    @Override // org.informatika.sirekap.support.pdf.PdfGenerator
    public void generate(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        PDDocument pDDocument = new PDDocument();
        Iterator<String> it = this.listImage.iterator();
        while (it.hasNext()) {
            String image = it.next();
            PDPage pDPage = new PDPage();
            pDDocument.addPage(pDPage);
            PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDPage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Intrinsics.checkNotNullExpressionValue(image, "image");
            Bitmap decodeFile = BitmapFactory.decodeFile(StringsKt.replace$default(image, ".jpg", ".png", false, 4, (Object) null));
            decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            pDPageContentStream.drawImage(new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.DCT_DECODE, decodeFile.getWidth(), decodeFile.getHeight(), 8, PDDeviceRGB.INSTANCE), 0.0f, 0.0f, PDRectangle.A4.getWidth() * 0.9f, PDRectangle.A4.getHeight() * 0.9f);
            pDPageContentStream.close();
            decodeFile.recycle();
        }
        pDDocument.save(new FileOutputStream(file));
        pDDocument.close();
    }
}
