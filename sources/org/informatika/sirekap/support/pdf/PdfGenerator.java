package org.informatika.sirekap.support.pdf;

import android.content.Context;
import com.google.firebase.messaging.Constants;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.text.lookup.StringLookupFactory;

/* compiled from: PdfGenerator.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0007\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u000e\u0010\u0007\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rJB\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u0013H\u0004J.\u0010\u0018\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\rH\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/support/pdf/PdfGenerator;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "generate", "", "", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "pdfPath", "", "writeValueInt", "contentStream", "Lcom/tom_roush/pdfbox/pdmodel/PDPageContentStream;", "position", "Lkotlin/Pair;", "", "count", "", "charCount", "spaceSize", "writeValueString", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class PdfGenerator {
    private final Context context;

    public abstract void generate(File file);

    public PdfGenerator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        PDFBoxResourceLoader.init(context.getApplicationContext());
    }

    public final Context getContext() {
        return this.context;
    }

    public final void generate(String pdfPath) {
        Intrinsics.checkNotNullParameter(pdfPath, "pdfPath");
        generate(new File(pdfPath));
    }

    public final byte[] generate() {
        File temp = File.createTempFile("gen_", ".pdf");
        Intrinsics.checkNotNullExpressionValue(temp, "temp");
        generate(temp);
        return ByteStreamsKt.readBytes(new FileInputStream(temp));
    }

    public static /* synthetic */ void writeValueInt$default(PdfGenerator pdfGenerator, PDPageContentStream pDPageContentStream, Pair pair, int i, int i2, float f, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeValueInt");
        }
        if ((i3 & 8) != 0) {
            i2 = 3;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            f = 23.0f;
        }
        pdfGenerator.writeValueInt(pDPageContentStream, pair, i, i4, f);
    }

    public final void writeValueInt(PDPageContentStream contentStream, Pair<Float, Float> pair, int i, int i2, float f) {
        Intrinsics.checkNotNullParameter(contentStream, "contentStream");
        if (pair == null) {
            throw new Exception("Position is required");
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%0" + i2 + OperatorName.SET_LINE_DASHPATTERN, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        if (i2 == 0) {
            return;
        }
        contentStream.beginText();
        contentStream.newLineAtOffset(pair.getFirst().floatValue(), pair.getSecond().floatValue());
        contentStream.newLine();
        contentStream.showText(String.valueOf(format.charAt(0)));
        CharSequence subSequence = format.subSequence(1, format.length());
        for (int i3 = 0; i3 < subSequence.length(); i3++) {
            char charAt = subSequence.charAt(i3);
            contentStream.newLineAtOffset(f, 0.0f);
            contentStream.newLine();
            contentStream.showText(String.valueOf(charAt));
        }
        contentStream.endText();
    }

    public final void writeValueString(PDPageContentStream contentStream, Pair<Float, Float> pair, String data) {
        Intrinsics.checkNotNullParameter(contentStream, "contentStream");
        Intrinsics.checkNotNullParameter(data, "data");
        if (pair == null) {
            throw new Exception("Position is required");
        }
        contentStream.beginText();
        contentStream.newLineAtOffset(pair.getFirst().floatValue(), pair.getSecond().floatValue());
        contentStream.newLine();
        contentStream.showText(data);
        contentStream.endText();
    }
}
