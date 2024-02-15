package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.fontbox.util.Charsets;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import java.io.IOException;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class PDFreeTextAppearanceHandler extends PDAbstractAppearanceHandler {
    private static final Pattern COLOR_PATTERN = Pattern.compile(".*color\\:\\s*\\#([0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]).*");
    private COSName fontName;
    private float fontSize;

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDFreeTextAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
        this.fontSize = 10.0f;
        this.fontName = COSName.HELV;
    }

    public PDFreeTextAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
        this.fontSize = 10.0f;
        this.fontName = COSName.HELV;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(40:1|(2:3|(1:151))(1:152)|9|10|11|12|13|(2:15|(29:17|18|(1:20)|21|(6:24|25|26|(4:28|(2:30|(1:32))(1:36)|33|34)(2:37|38)|35|22)|51|52|(1:54)|55|(1:138)(3:61|(1:63)(1:137)|64)|65|66|(1:135)(6:70|71|72|73|74|75)|76|(14:80|81|(11:85|86|(1:94)|95|(2:97|(1:(1:100)(1:124))(1:125))(1:126)|101|(4:103|104|105|106)|116|(4:118|(2:121|119)|122|123)|43|44)|127|86|(4:88|90|92|94)|95|(0)(0)|101|(0)|116|(0)|43|44)|128|81|(11:85|86|(0)|95|(0)(0)|101|(0)|116|(0)|43|44)|127|86|(0)|95|(0)(0)|101|(0)|116|(0)|43|44))|140|18|(0)|21|(1:22)|51|52|(0)|55|(1:57)|138|65|66|(1:68)|135|76|(14:80|81|(0)|127|86|(0)|95|(0)(0)|101|(0)|116|(0)|43|44)|128|81|(0)|127|86|(0)|95|(0)(0)|101|(0)|116|(0)|43|44|(2:(0)|(1:112))) */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x03e7, code lost:
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0098 A[Catch: all -> 0x03f1, IOException -> 0x03f5, TryCatch #12 {IOException -> 0x03f5, all -> 0x03f1, blocks: (B:171:0x0037, B:173:0x0057, B:175:0x0063, B:177:0x0093, B:179:0x0098, B:180:0x009d, B:181:0x00a3), top: B:303:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0110 A[Catch: all -> 0x03e9, IOException -> 0x03ed, TryCatch #11 {IOException -> 0x03ed, all -> 0x03e9, blocks: (B:184:0x00aa, B:186:0x00b2, B:188:0x00be, B:190:0x00e4, B:192:0x00f5, B:194:0x00ff, B:193:0x00f9, B:195:0x010a, B:197:0x0110, B:198:0x0113, B:200:0x011d, B:202:0x0129, B:204:0x012d, B:206:0x0147, B:208:0x015e, B:210:0x017a, B:212:0x0182, B:214:0x018e, B:207:0x0157), top: B:305:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x024c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0268 A[Catch: IOException -> 0x03e7, all -> 0x040b, TryCatch #1 {IOException -> 0x03e7, blocks: (B:218:0x01b2, B:224:0x0213, B:228:0x0238, B:230:0x0241, B:234:0x024f, B:236:0x0261, B:238:0x0268, B:240:0x0274, B:242:0x027a, B:250:0x028e, B:254:0x02ff, B:256:0x030d, B:258:0x0359, B:266:0x0367, B:267:0x036a, B:268:0x036b, B:270:0x036e, B:271:0x037a, B:273:0x037f, B:274:0x039a, B:251:0x02a9, B:252:0x02c5, B:253:0x02e3, B:235:0x0258, B:229:0x023d, B:223:0x01e3), top: B:298:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x02e3 A[Catch: IOException -> 0x03e7, all -> 0x040b, TryCatch #1 {IOException -> 0x03e7, blocks: (B:218:0x01b2, B:224:0x0213, B:228:0x0238, B:230:0x0241, B:234:0x024f, B:236:0x0261, B:238:0x0268, B:240:0x0274, B:242:0x027a, B:250:0x028e, B:254:0x02ff, B:256:0x030d, B:258:0x0359, B:266:0x0367, B:267:0x036a, B:268:0x036b, B:270:0x036e, B:271:0x037a, B:273:0x037f, B:274:0x039a, B:251:0x02a9, B:252:0x02c5, B:253:0x02e3, B:235:0x0258, B:229:0x023d, B:223:0x01e3), top: B:298:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:256:0x030d A[Catch: IOException -> 0x03e7, all -> 0x040b, TRY_LEAVE, TryCatch #1 {IOException -> 0x03e7, blocks: (B:218:0x01b2, B:224:0x0213, B:228:0x0238, B:230:0x0241, B:234:0x024f, B:236:0x0261, B:238:0x0268, B:240:0x0274, B:242:0x027a, B:250:0x028e, B:254:0x02ff, B:256:0x030d, B:258:0x0359, B:266:0x0367, B:267:0x036a, B:268:0x036b, B:270:0x036e, B:271:0x037a, B:273:0x037f, B:274:0x039a, B:251:0x02a9, B:252:0x02c5, B:253:0x02e3, B:235:0x0258, B:229:0x023d, B:223:0x01e3), top: B:298:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:270:0x036e A[Catch: IOException -> 0x03e7, all -> 0x040b, TryCatch #1 {IOException -> 0x03e7, blocks: (B:218:0x01b2, B:224:0x0213, B:228:0x0238, B:230:0x0241, B:234:0x024f, B:236:0x0261, B:238:0x0268, B:240:0x0274, B:242:0x027a, B:250:0x028e, B:254:0x02ff, B:256:0x030d, B:258:0x0359, B:266:0x0367, B:267:0x036a, B:268:0x036b, B:270:0x036e, B:271:0x037a, B:273:0x037f, B:274:0x039a, B:251:0x02a9, B:252:0x02c5, B:253:0x02e3, B:235:0x0258, B:229:0x023d, B:223:0x01e3), top: B:298:0x0180 }] */
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void generateNormalAppearance() {
        /*
            Method dump skipped, instructions count: 1040
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDFreeTextAppearanceHandler.generateNormalAppearance():void");
    }

    private PDColor extractNonStrokingColor(PDAnnotationMarkup pDAnnotationMarkup) {
        PDColor pDColor;
        PDColor pDColor2 = new PDColor(new float[]{0.0f}, PDDeviceGray.INSTANCE);
        String defaultAppearance = pDAnnotationMarkup.getDefaultAppearance();
        if (defaultAppearance == null) {
            return pDColor2;
        }
        try {
            PDFStreamParser pDFStreamParser = new PDFStreamParser(defaultAppearance.getBytes(Charsets.US_ASCII));
            COSArray cOSArray = new COSArray();
            Operator operator = null;
            COSArray cOSArray2 = null;
            for (Object parseNextToken = pDFStreamParser.parseNextToken(); parseNextToken != null; parseNextToken = pDFStreamParser.parseNextToken()) {
                if (parseNextToken instanceof Operator) {
                    Operator operator2 = (Operator) parseNextToken;
                    String name = operator2.getName();
                    if (!OperatorName.NON_STROKING_GRAY.equals(name) && !OperatorName.NON_STROKING_RGB.equals(name) && !OperatorName.NON_STROKING_CMYK.equals(name)) {
                        cOSArray = cOSArray2;
                        cOSArray2 = cOSArray;
                        cOSArray = new COSArray();
                    }
                    operator = operator2;
                    cOSArray2 = cOSArray;
                    cOSArray = new COSArray();
                } else {
                    cOSArray.add((COSBase) parseNextToken);
                }
            }
            if (operator != null) {
                String name2 = operator.getName();
                if (OperatorName.NON_STROKING_GRAY.equals(name2)) {
                    pDColor = new PDColor(cOSArray2, PDDeviceGray.INSTANCE);
                } else if (OperatorName.NON_STROKING_RGB.equals(name2)) {
                    pDColor = new PDColor(cOSArray2, PDDeviceRGB.INSTANCE);
                } else {
                    OperatorName.NON_STROKING_CMYK.equals(name2);
                    return pDColor2;
                }
                return pDColor;
            }
            return pDColor2;
        } catch (IOException e) {
            Log.w("PdfBox-Android", "Problem parsing /DA, will use default black", e);
            return pDColor2;
        }
    }

    private void extractFontDetails(PDAnnotationMarkup pDAnnotationMarkup) {
        PDAcroForm acroForm;
        String defaultAppearance = pDAnnotationMarkup.getDefaultAppearance();
        if (defaultAppearance == null && this.document != null && (acroForm = this.document.getDocumentCatalog().getAcroForm()) != null) {
            defaultAppearance = acroForm.getDefaultAppearance();
        }
        if (defaultAppearance == null) {
            return;
        }
        try {
            PDFStreamParser pDFStreamParser = new PDFStreamParser(defaultAppearance.getBytes(Charsets.US_ASCII));
            COSArray cOSArray = new COSArray();
            COSArray cOSArray2 = new COSArray();
            while (true) {
                Object parseNextToken = pDFStreamParser.parseNextToken();
                if (parseNextToken == null) {
                    break;
                } else if (parseNextToken instanceof Operator) {
                    if (!OperatorName.SET_FONT_AND_SIZE.equals(((Operator) parseNextToken).getName())) {
                        cOSArray = cOSArray2;
                    }
                    cOSArray2 = cOSArray;
                    cOSArray = new COSArray();
                } else {
                    cOSArray.add((COSBase) parseNextToken);
                }
            }
            if (cOSArray2.size() >= 2) {
                COSBase cOSBase = cOSArray2.get(0);
                if (cOSBase instanceof COSName) {
                    this.fontName = (COSName) cOSBase;
                }
                COSBase cOSBase2 = cOSArray2.get(1);
                if (cOSBase2 instanceof COSNumber) {
                    this.fontSize = ((COSNumber) cOSBase2).floatValue();
                }
            }
        } catch (IOException e) {
            Log.w("PdfBox-Android", "Problem parsing /DA, will use default 'Helv 10'", e);
        }
    }
}
