package org.informatika.sirekap.support.pdf;

import android.content.Context;
import androidx.room.RoomMasterTable;
import com.google.firebase.messaging.Constants;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.informatika.sirekap.support.pdf.data.CHCandidateData;

/* compiled from: CHSalinanPPWPDNGenerator.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J.\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/support/pdf/CHSalinanPPWPDNGenerator;", "Lorg/informatika/sirekap/support/pdf/PdfGenerator;", "context", "Landroid/content/Context;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/support/pdf/data/CHCandidateData;", "(Landroid/content/Context;Lorg/informatika/sirekap/support/pdf/data/CHCandidateData;)V", "position", "", "", "Lkotlin/Pair;", "", "generate", "", StringLookupFactory.KEY_FILE, "Ljava/io/File;", "page1Writer", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "page2Writer", "writeSectionVPos", "contentStream", "Lcom/tom_roush/pdfbox/pdmodel/PDPageContentStream;", "count", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CHSalinanPPWPDNGenerator extends PdfGenerator {
    private final CHCandidateData data;
    private final Map<String, Pair<Float, Float>> position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CHSalinanPPWPDNGenerator(Context context, CHCandidateData data) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        Float valueOf = Float.valueOf(317.0f);
        Float valueOf2 = Float.valueOf(492.0f);
        Float valueOf3 = Float.valueOf(395.0f);
        Float valueOf4 = Float.valueOf(473.0f);
        Float valueOf5 = Float.valueOf(448.0f);
        Float valueOf6 = Float.valueOf(424.0f);
        Float valueOf7 = Float.valueOf(400.0f);
        Float valueOf8 = Float.valueOf(376.0f);
        Float valueOf9 = Float.valueOf(154.0f);
        Float valueOf10 = Float.valueOf(504.0f);
        Float valueOf11 = Float.valueOf(100.0f);
        Float valueOf12 = Float.valueOf(249.0f);
        this.position = MapsKt.mapOf(TuplesKt.to("1AL", new Pair(valueOf, valueOf2)), TuplesKt.to("1AP", new Pair(valueOf3, valueOf2)), TuplesKt.to("1AT", new Pair(valueOf4, valueOf2)), TuplesKt.to("1B1L", new Pair(valueOf, valueOf5)), TuplesKt.to("1B1P", new Pair(valueOf3, valueOf5)), TuplesKt.to("1B1T", new Pair(valueOf4, valueOf5)), TuplesKt.to("1B2L", new Pair(valueOf, valueOf6)), TuplesKt.to("1B2P", new Pair(valueOf3, valueOf6)), TuplesKt.to("1B2T", new Pair(valueOf4, valueOf6)), TuplesKt.to("1B3L", new Pair(valueOf, valueOf7)), TuplesKt.to("1B3P", new Pair(valueOf3, valueOf7)), TuplesKt.to("1B3T", new Pair(valueOf4, valueOf7)), TuplesKt.to("1B4L", new Pair(valueOf, valueOf8)), TuplesKt.to("1B4P", new Pair(valueOf3, valueOf8)), TuplesKt.to("1B4T", new Pair(valueOf4, valueOf8)), TuplesKt.to("21", new Pair(valueOf4, Float.valueOf(300.0f))), TuplesKt.to("22", new Pair(valueOf4, Float.valueOf(276.0f))), TuplesKt.to("23", new Pair(valueOf4, Float.valueOf(252.0f))), TuplesKt.to("24", new Pair(valueOf4, Float.valueOf(228.0f))), TuplesKt.to("3L", new Pair(valueOf, valueOf9)), TuplesKt.to("3P", new Pair(valueOf3, valueOf9)), TuplesKt.to("3T", new Pair(valueOf4, valueOf9)), TuplesKt.to("41", new Pair(valueOf10, Float.valueOf(690.0f))), TuplesKt.to(RoomMasterTable.DEFAULT_ID, new Pair(valueOf10, Float.valueOf(633.0f))), TuplesKt.to("43", new Pair(valueOf10, Float.valueOf(576.0f))), TuplesKt.to("44", new Pair(valueOf10, Float.valueOf(519.0f))), TuplesKt.to("5A", new Pair(valueOf10, Float.valueOf(416.0f))), TuplesKt.to("5B", new Pair(valueOf10, Float.valueOf(360.0f))), TuplesKt.to("5C", new Pair(valueOf10, Float.valueOf(304.0f))), TuplesKt.to("locationOfPenetapan", new Pair(valueOf11, valueOf12)), TuplesKt.to("dateOfPenetapan", new Pair(Float.valueOf(290.0f), valueOf12)), TuplesKt.to("monthOfPenetapan", new Pair(Float.valueOf(385.0f), valueOf12)), TuplesKt.to("yearOfPenetapan", new Pair(Float.valueOf(480.0f), valueOf12)));
    }

    @Override // org.informatika.sirekap.support.pdf.PdfGenerator
    public void generate(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        InputStream open = getContext().getAssets().open("pdf/10_FORMAT_PPWP_DN.pdf");
        Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(\"pdf/10_FORMAT_PPWP_DN.pdf\")");
        FilesKt.writeBytes(file, ByteStreamsKt.readBytes(open));
        PDDocument document = PDDocument.load(new FileInputStream(file));
        Intrinsics.checkNotNullExpressionValue(document, "document");
        page1Writer(document);
        page2Writer(document);
        document.save(file);
        document.close();
    }

    private final void page1Writer(PDDocument pDDocument) {
        PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDDocument.getPage(0), PDPageContentStream.AppendMode.APPEND, true, true);
        pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
        CHSalinanPPWPDNGenerator cHSalinanPPWPDNGenerator = this;
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1AL"), this.data.getSectionI().getA().getMenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1AP"), this.data.getSectionI().getA().getWomenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1AT"), this.data.getSectionI().getA().getTotal(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B1L"), this.data.getSectionI().getB1().getMenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B1P"), this.data.getSectionI().getB1().getWomenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B1T"), this.data.getSectionI().getB1().getTotal(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B2L"), this.data.getSectionI().getB2().getMenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B2P"), this.data.getSectionI().getB2().getWomenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B2T"), this.data.getSectionI().getB2().getTotal(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B3L"), this.data.getSectionI().getB3().getMenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B3P"), this.data.getSectionI().getB3().getWomenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B3T"), this.data.getSectionI().getB3().getTotal(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B4L"), this.data.getSectionI().getB4().getMenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B4P"), this.data.getSectionI().getB4().getWomenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("1B4T"), this.data.getSectionI().getB4().getTotal(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("21"), this.data.getSectionII().getRow1(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("22"), this.data.getSectionII().getRow2(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("23"), this.data.getSectionII().getRow3(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("24"), this.data.getSectionII().getRow4(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("3L"), this.data.getSectionIII().getCount().getMenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("3P"), this.data.getSectionIII().getCount().getWomenVotersCount(), 0, 0.0f, 24, null);
        PdfGenerator.writeValueInt$default(cHSalinanPPWPDNGenerator, pDPageContentStream, this.position.get("3T"), this.data.getSectionIII().getCount().getTotal(), 0, 0.0f, 24, null);
        pDPageContentStream.close();
    }

    private final void page2Writer(PDDocument pDDocument) {
        PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDDocument.getPage(1), PDPageContentStream.AppendMode.APPEND, true, true);
        int i = 0;
        for (Object obj : this.data.getSectionIV().get(0)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int intValue = ((Number) obj).intValue();
            String readNumber = PdfFacade.INSTANCE.readNumber(intValue);
            Pair<Float, Float> pair = this.position.get("4" + i2);
            if (pair != null) {
                pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);
                PdfGenerator.writeValueInt$default(this, pDPageContentStream, pair, intValue, 0, 0.0f, 24, null);
                pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 8.0f);
                Pair<Float, Float> pair2 = new Pair<>(Float.valueOf(90.0f), Float.valueOf(pair.getSecond().floatValue() - 26));
                String upperCase = readNumber.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                writeValueString(pDPageContentStream, pair2, upperCase);
            }
            i = i2;
        }
        writeSectionVPos(pDPageContentStream, this.position.get("5A"), this.data.getSectionV().getValidDataCount());
        writeSectionVPos(pDPageContentStream, this.position.get("5B"), this.data.getSectionV().getInvalidDataCount());
        writeSectionVPos(pDPageContentStream, this.position.get("5C"), this.data.getSectionV().getAllDataCount());
        pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 8.0f);
        pDPageContentStream.close();
    }

    private final void writeSectionVPos(PDPageContentStream pDPageContentStream, Pair<Float, Float> pair, int i) {
        if (pair == null) {
            throw new Exception("Position is required");
        }
        String readNumber = PdfFacade.INSTANCE.readNumber(i);
        pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 10.0f);
        PdfGenerator.writeValueInt$default(this, pDPageContentStream, pair, i, 0, 0.0f, 24, null);
        pDPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 8.0f);
        Pair<Float, Float> pair2 = new Pair<>(Float.valueOf(191.0f), Float.valueOf(pair.getSecond().floatValue() - 20));
        String upperCase = readNumber.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        writeValueString(pDPageContentStream, pair2, upperCase);
    }
}
