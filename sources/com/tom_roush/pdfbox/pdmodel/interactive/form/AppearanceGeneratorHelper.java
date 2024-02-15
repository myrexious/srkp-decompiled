package com.tom_roush.pdfbox.pdmodel.interactive.form;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdfwriter.ContentStreamWriter;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType3CharProc;
import com.tom_roush.pdfbox.pdmodel.font.PDType3Font;
import com.tom_roush.pdfbox.pdmodel.font.PDVectorFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PlainText;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PlainTextFormatter;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
class AppearanceGeneratorHelper {
    private static final float DEFAULT_FONT_SIZE = 12.0f;
    private static final float DEFAULT_PADDING = 0.5f;
    private static final int FONTSCALE = 1000;
    private static final float MAXIMUM_FONT_SIZE = 300.0f;
    private static final float MINIMUM_FONT_SIZE = 4.0f;
    private PDDefaultAppearanceString defaultAppearance;
    private final PDVariableText field;
    private String value;
    private static final Operator BMC = Operator.getOperator(OperatorName.BEGIN_MARKED_CONTENT);
    private static final Operator EMC = Operator.getOperator(OperatorName.END_MARKED_CONTENT);
    private static final float[] HIGHLIGHT_COLOR = {0.6f, 0.75686276f, 0.84313726f};

    public AppearanceGeneratorHelper(PDVariableText pDVariableText) throws IOException {
        this.field = pDVariableText;
        validateAndEnsureAcroFormResources();
        try {
            this.defaultAppearance = pDVariableText.getDefaultAppearanceString();
        } catch (IOException e) {
            throw new IOException("Could not process default appearance string '" + pDVariableText.getDefaultAppearance() + "' for field '" + pDVariableText.getFullyQualifiedName() + OperatorName.SHOW_TEXT_LINE, e);
        }
    }

    private void validateAndEnsureAcroFormResources() {
        PDResources resources;
        if (this.field.getAcroForm().getDefaultResources() == null) {
            return;
        }
        PDResources defaultResources = this.field.getAcroForm().getDefaultResources();
        for (PDAnnotationWidget pDAnnotationWidget : this.field.getWidgets()) {
            PDAppearanceStream normalAppearanceStream = pDAnnotationWidget.getNormalAppearanceStream();
            if (normalAppearanceStream != null && (resources = normalAppearanceStream.getResources()) != null) {
                COSDictionary cOSDictionary = resources.getCOSObject().getCOSDictionary(COSName.FONT);
                COSDictionary cOSDictionary2 = defaultResources.getCOSObject().getCOSDictionary(COSName.FONT);
                for (COSName cOSName : resources.getFontNames()) {
                    try {
                        if (defaultResources.getFont(cOSName) == null) {
                            Log.d("PdfBox-Android", "Adding font resource " + cOSName + " from widget to AcroForm");
                            cOSDictionary2.setItem(cOSName, cOSDictionary.getItem(cOSName));
                        }
                    } catch (IOException unused) {
                        Log.w("PdfBox-Android", "Unable to match field level font with AcroForm font");
                    }
                }
            }
        }
    }

    public void setAppearanceValue(String str) throws IOException {
        PDAppearanceStream pDAppearanceStream;
        this.value = getFormattedValue(str);
        PDVariableText pDVariableText = this.field;
        if ((pDVariableText instanceof PDTextField) && !((PDTextField) pDVariableText).isMultiline()) {
            this.value = this.value.replaceAll("\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]", StringUtils.SPACE);
        }
        for (PDAnnotationWidget pDAnnotationWidget : this.field.getWidgets()) {
            if (pDAnnotationWidget.getCOSObject().containsKey("PMD")) {
                Log.w("PdfBox-Android", "widget of field " + this.field.getFullyQualifiedName() + " is a PaperMetaData widget, no appearance stream created");
            } else {
                PDDefaultAppearanceString pDDefaultAppearanceString = this.defaultAppearance;
                if (pDAnnotationWidget.getCOSObject().getDictionaryObject(COSName.DA) != null) {
                    this.defaultAppearance = getWidgetDefaultAppearanceString(pDAnnotationWidget);
                }
                if (pDAnnotationWidget.getRectangle() == null) {
                    pDAnnotationWidget.getCOSObject().removeItem(COSName.AP);
                    Log.w("PdfBox-Android", "widget of field " + this.field.getFullyQualifiedName() + " has no rectangle, no appearance stream created");
                } else {
                    PDAppearanceDictionary appearance = pDAnnotationWidget.getAppearance();
                    if (appearance == null) {
                        appearance = new PDAppearanceDictionary();
                        pDAnnotationWidget.setAppearance(appearance);
                    }
                    PDAppearanceEntry normalAppearance = appearance.getNormalAppearance();
                    if (isValidAppearanceStream(normalAppearance)) {
                        pDAppearanceStream = normalAppearance.getAppearanceStream();
                    } else {
                        PDAppearanceStream prepareNormalAppearanceStream = prepareNormalAppearanceStream(pDAnnotationWidget);
                        appearance.setNormalAppearance(prepareNormalAppearanceStream);
                        pDAppearanceStream = prepareNormalAppearanceStream;
                    }
                    PDAppearanceCharacteristicsDictionary appearanceCharacteristics = pDAnnotationWidget.getAppearanceCharacteristics();
                    if (appearanceCharacteristics != null || pDAppearanceStream.getContentStream().getLength() == 0) {
                        initializeAppearanceContent(pDAnnotationWidget, appearanceCharacteristics, pDAppearanceStream);
                    }
                    setAppearanceContent(pDAnnotationWidget, pDAppearanceStream);
                    this.defaultAppearance = pDDefaultAppearanceString;
                }
            }
        }
    }

    private String getFormattedValue(String str) {
        PDAction f;
        PDFormFieldAdditionalActions actions = this.field.getActions();
        if (actions != null && (f = actions.getF()) != null) {
            if (this.field.getAcroForm().getScriptingHandler() != null) {
                return this.field.getAcroForm().getScriptingHandler().format((PDActionJavaScript) f, str);
            }
            Log.i("PdfBox-Android", "Field contains a formatting action but no ScriptingHandler has been supplied - formatted value might be incorrect");
        }
        return str;
    }

    private static boolean isValidAppearanceStream(PDAppearanceEntry pDAppearanceEntry) {
        PDRectangle bBox;
        return pDAppearanceEntry != null && pDAppearanceEntry.isStream() && (bBox = pDAppearanceEntry.getAppearanceStream().getBBox()) != null && Math.abs(bBox.getWidth()) > 0.0f && Math.abs(bBox.getHeight()) > 0.0f;
    }

    private PDAppearanceStream prepareNormalAppearanceStream(PDAnnotationWidget pDAnnotationWidget) {
        PDAppearanceStream pDAppearanceStream = new PDAppearanceStream(this.field.getAcroForm().getDocument());
        int resolveRotation = resolveRotation(pDAnnotationWidget);
        PDRectangle rectangle = pDAnnotationWidget.getRectangle();
        PointF transformPoint = Matrix.getRotateInstance(Math.toRadians(resolveRotation), 0.0f, 0.0f).transformPoint(rectangle.getWidth(), rectangle.getHeight());
        PDRectangle pDRectangle = new PDRectangle(Math.abs(transformPoint.x), Math.abs(transformPoint.y));
        pDAppearanceStream.setBBox(pDRectangle);
        AffineTransform calculateMatrix = calculateMatrix(pDRectangle, resolveRotation);
        if (!calculateMatrix.isIdentity()) {
            pDAppearanceStream.setMatrix(calculateMatrix);
        }
        pDAppearanceStream.setFormType(1);
        pDAppearanceStream.setResources(new PDResources());
        return pDAppearanceStream;
    }

    private PDDefaultAppearanceString getWidgetDefaultAppearanceString(PDAnnotationWidget pDAnnotationWidget) throws IOException {
        return new PDDefaultAppearanceString((COSString) pDAnnotationWidget.getCOSObject().getDictionaryObject(COSName.DA), this.field.getAcroForm().getDefaultResources());
    }

    private int resolveRotation(PDAnnotationWidget pDAnnotationWidget) {
        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = pDAnnotationWidget.getAppearanceCharacteristics();
        if (appearanceCharacteristics != null) {
            return appearanceCharacteristics.getRotation();
        }
        return 0;
    }

    private void initializeAppearanceContent(PDAnnotationWidget pDAnnotationWidget, PDAppearanceCharacteristicsDictionary pDAppearanceCharacteristicsDictionary, PDAppearanceStream pDAppearanceStream) throws IOException {
        float f;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PDPageContentStream pDPageContentStream = new PDPageContentStream(this.field.getAcroForm().getDocument(), pDAppearanceStream, (OutputStream) byteArrayOutputStream);
        if (pDAppearanceCharacteristicsDictionary != null) {
            PDColor background = pDAppearanceCharacteristicsDictionary.getBackground();
            if (background != null) {
                pDPageContentStream.setNonStrokingColor(background);
                PDRectangle resolveBoundingBox = resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream);
                pDPageContentStream.addRect(resolveBoundingBox.getLowerLeftX(), resolveBoundingBox.getLowerLeftY(), resolveBoundingBox.getWidth(), resolveBoundingBox.getHeight());
                pDPageContentStream.fill();
            }
            PDColor borderColour = pDAppearanceCharacteristicsDictionary.getBorderColour();
            if (borderColour != null) {
                pDPageContentStream.setStrokingColor(borderColour);
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            PDBorderStyleDictionary borderStyle = pDAnnotationWidget.getBorderStyle();
            if (borderStyle != null && borderStyle.getWidth() > 0.0f) {
                f = borderStyle.getWidth();
            }
            if (f > 0.0f && borderColour != null) {
                if (f != 1.0f) {
                    pDPageContentStream.setLineWidth(f);
                }
                PDRectangle applyPadding = applyPadding(resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream), Math.max(0.5f, f / 2.0f));
                pDPageContentStream.addRect(applyPadding.getLowerLeftX(), applyPadding.getLowerLeftY(), applyPadding.getWidth(), applyPadding.getHeight());
                pDPageContentStream.closeAndStroke();
            }
        }
        pDPageContentStream.close();
        byteArrayOutputStream.close();
        writeToStream(byteArrayOutputStream.toByteArray(), pDAppearanceStream);
    }

    private List<Object> tokenize(PDAppearanceStream pDAppearanceStream) throws IOException {
        PDFStreamParser pDFStreamParser = new PDFStreamParser(pDAppearanceStream);
        pDFStreamParser.parse();
        return pDFStreamParser.getTokens();
    }

    private void setAppearanceContent(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream) throws IOException {
        this.defaultAppearance.copyNeededResourcesTo(pDAppearanceStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ContentStreamWriter contentStreamWriter = new ContentStreamWriter(byteArrayOutputStream);
        List<Object> list = tokenize(pDAppearanceStream);
        Operator operator = BMC;
        int indexOf = list.indexOf(operator);
        if (indexOf == -1) {
            contentStreamWriter.writeTokens(list);
            contentStreamWriter.writeTokens(COSName.TX, operator);
        } else {
            contentStreamWriter.writeTokens(list.subList(0, indexOf + 1));
        }
        insertGeneratedAppearance(pDAnnotationWidget, pDAppearanceStream, byteArrayOutputStream);
        Operator operator2 = EMC;
        int indexOf2 = list.indexOf(operator2);
        if (indexOf2 == -1) {
            contentStreamWriter.writeTokens(operator2);
        } else {
            contentStreamWriter.writeTokens(list.subList(indexOf2, list.size()));
        }
        byteArrayOutputStream.close();
        writeToStream(byteArrayOutputStream.toByteArray(), pDAppearanceStream);
    }

    private void insertGeneratedAppearance(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream, OutputStream outputStream) throws IOException {
        float f;
        float f2;
        float min;
        PDPageContentStream pDPageContentStream = new PDPageContentStream(this.field.getAcroForm().getDocument(), pDAppearanceStream, outputStream);
        PDRectangle resolveBoundingBox = resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream);
        float width = pDAnnotationWidget.getBorderStyle() != null ? pDAnnotationWidget.getBorderStyle().getWidth() : 0.0f;
        PDRectangle applyPadding = applyPadding(resolveBoundingBox, Math.max(1.0f, width));
        PDRectangle applyPadding2 = applyPadding(applyPadding, Math.max(1.0f, width));
        pDPageContentStream.saveGraphicsState();
        pDPageContentStream.addRect(applyPadding.getLowerLeftX(), applyPadding.getLowerLeftY(), applyPadding.getWidth(), applyPadding.getHeight());
        pDPageContentStream.clip();
        PDFont font = this.defaultAppearance.getFont();
        if (font == null) {
            throw new IllegalArgumentException("font is null, check whether /DA entry is incomplete or incorrect");
        }
        if (font.getName().contains("+")) {
            Log.w("PdfBox-Android", "Font '" + this.defaultAppearance.getFontName().getName() + "' of field '" + this.field.getFullyQualifiedName() + "' contains subsetted font '" + font.getName() + OperatorName.SHOW_TEXT_LINE);
            Log.w("PdfBox-Android", "This may bring trouble with PDField.setValue(), PDAcroForm.flatten() or PDAcroForm.refreshAppearances()");
            Log.w("PdfBox-Android", "You should replace this font with a non-subsetted font:");
            Log.w("PdfBox-Android", "PDFont font = PDType0Font.load(doc, new FileInputStream(fontfile), false);");
            Log.w("PdfBox-Android", "acroForm.getDefaultResources().put(COSName.getPDFName(\"" + this.defaultAppearance.getFontName().getName() + "\", font);");
        }
        float fontSize = this.defaultAppearance.getFontSize();
        if (fontSize == 0.0f) {
            fontSize = calculateFontSize(font, applyPadding2);
        }
        float f3 = fontSize;
        if (this.field instanceof PDListBox) {
            insertGeneratedListboxSelectionHighlight(pDPageContentStream, pDAppearanceStream, font, f3);
        }
        pDPageContentStream.beginText();
        this.defaultAppearance.writeTo(pDPageContentStream, f3);
        float f4 = f3 / 1000.0f;
        float height = font.getBoundingBox().getHeight() * f4;
        if (font.getFontDescriptor() != null) {
            f = font.getFontDescriptor().getCapHeight() * f4;
            f2 = font.getFontDescriptor().getDescent() * f4;
        } else {
            float resolveCapHeight = resolveCapHeight(font);
            float resolveDescent = resolveDescent(font);
            Log.d("PdfBox-Android", "missing font descriptor - resolved Cap/Descent to " + resolveCapHeight + RemoteSettings.FORWARD_SLASH_STRING + resolveDescent);
            f = resolveCapHeight * f4;
            f2 = resolveDescent * f4;
        }
        PDVariableText pDVariableText = this.field;
        if ((pDVariableText instanceof PDTextField) && ((PDTextField) pDVariableText).isMultiline()) {
            min = applyPadding2.getUpperRightY() - height;
        } else if (f > applyPadding.getHeight()) {
            min = applyPadding.getLowerLeftY() + (-f2);
        } else {
            float lowerLeftY = applyPadding.getLowerLeftY() + ((applyPadding.getHeight() - f) / 2.0f);
            float f5 = -f2;
            min = lowerLeftY - applyPadding.getLowerLeftY() < f5 ? Math.min(f5 + applyPadding2.getLowerLeftY(), Math.max(lowerLeftY, (applyPadding2.getHeight() - applyPadding2.getLowerLeftY()) - f)) : lowerLeftY;
        }
        float lowerLeftX = applyPadding2.getLowerLeftX();
        if (shallComb()) {
            insertGeneratedCombAppearance(pDPageContentStream, pDAppearanceStream, font, f3);
        } else if (this.field instanceof PDListBox) {
            insertGeneratedListboxAppearance(pDPageContentStream, pDAppearanceStream, applyPadding2, font, f3);
        } else {
            PlainText plainText = new PlainText(this.value);
            AppearanceStyle appearanceStyle = new AppearanceStyle();
            appearanceStyle.setFont(font);
            appearanceStyle.setFontSize(f3);
            appearanceStyle.setLeading(font.getBoundingBox().getHeight() * f4);
            new PlainTextFormatter.Builder(pDPageContentStream).style(appearanceStyle).text(plainText).width(applyPadding2.getWidth()).wrapLines(isMultiLine()).initialOffset(lowerLeftX, min).textAlign(getTextAlign(pDAnnotationWidget)).build().format();
        }
        pDPageContentStream.endText();
        pDPageContentStream.restoreGraphicsState();
        pDPageContentStream.close();
    }

    private int getTextAlign(PDAnnotationWidget pDAnnotationWidget) {
        return pDAnnotationWidget.getCOSObject().getInt(COSName.Q, this.field.getQ());
    }

    private AffineTransform calculateMatrix(PDRectangle pDRectangle, int i) {
        float f;
        if (i == 0) {
            return new AffineTransform();
        }
        float f2 = 0.0f;
        if (i == 90) {
            f2 = pDRectangle.getUpperRightY();
            f = 0.0f;
        } else if (i == 180) {
            f2 = pDRectangle.getUpperRightY();
            f = pDRectangle.getUpperRightX();
        } else {
            f = i != 270 ? 0.0f : pDRectangle.getUpperRightX();
        }
        return Matrix.getRotateInstance(Math.toRadians(i), f2, f).createAffineTransform();
    }

    private boolean isMultiLine() {
        PDVariableText pDVariableText = this.field;
        return (pDVariableText instanceof PDTextField) && ((PDTextField) pDVariableText).isMultiline();
    }

    private boolean shallComb() {
        PDVariableText pDVariableText = this.field;
        return (!(pDVariableText instanceof PDTextField) || !((PDTextField) pDVariableText).isComb() || ((PDTextField) this.field).isMultiline() || ((PDTextField) this.field).isPassword() || ((PDTextField) this.field).isFileSelect()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005e A[LOOP:0: B:24:0x005c->B:25:0x005e, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void insertGeneratedCombAppearance(com.tom_roush.pdfbox.pdmodel.PDPageContentStream r11, com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream r12, com.tom_roush.pdfbox.pdmodel.font.PDFont r13, float r14) throws java.io.IOException {
        /*
            r10 = this;
            com.tom_roush.pdfbox.pdmodel.interactive.form.PDVariableText r0 = r10.field
            com.tom_roush.pdfbox.pdmodel.interactive.form.PDTextField r0 = (com.tom_roush.pdfbox.pdmodel.interactive.form.PDTextField) r0
            int r0 = r0.getMaxLen()
            com.tom_roush.pdfbox.pdmodel.interactive.form.PDVariableText r1 = r10.field
            int r1 = r1.getQ()
            java.lang.String r2 = r10.value
            int r2 = r2.length()
            int r2 = java.lang.Math.min(r2, r0)
            com.tom_roush.pdfbox.pdmodel.common.PDRectangle r3 = r12.getBBox()
            r4 = 1065353216(0x3f800000, float:1.0)
            com.tom_roush.pdfbox.pdmodel.common.PDRectangle r3 = r10.applyPadding(r3, r4)
            com.tom_roush.pdfbox.pdmodel.common.PDRectangle r4 = r12.getBBox()
            float r4 = r4.getWidth()
            float r5 = (float) r0
            float r4 = r4 / r5
            com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor r5 = r13.getFontDescriptor()
            float r5 = r5.getAscent()
            r6 = 1148846080(0x447a0000, float:1000.0)
            float r5 = r5 / r6
            float r5 = r5 * r14
            float r3 = r3.getLowerLeftY()
            com.tom_roush.pdfbox.pdmodel.common.PDRectangle r12 = r12.getBBox()
            float r12 = r12.getHeight()
            float r12 = r12 - r5
            r5 = 1073741824(0x40000000, float:2.0)
            float r12 = r12 / r5
            float r3 = r3 + r12
            float r12 = r4 / r5
            r7 = 2
            if (r1 != r7) goto L53
            int r0 = r0 - r2
        L4f:
            float r0 = (float) r0
            float r0 = r0 * r4
            float r12 = r12 + r0
            goto L59
        L53:
            r8 = 1
            if (r1 != r8) goto L59
            int r0 = r0 - r2
            int r0 = r0 / r7
            goto L4f
        L59:
            r0 = 0
            r1 = 0
            r7 = r0
        L5c:
            if (r1 >= r2) goto L7d
            java.lang.String r8 = r10.value
            int r9 = r1 + 1
            java.lang.String r1 = r8.substring(r1, r9)
            float r8 = r13.getStringWidth(r1)
            float r8 = r8 / r6
            float r8 = r8 * r14
            float r8 = r8 / r5
            float r7 = r7 / r5
            float r12 = r12 + r7
            float r7 = r8 / r5
            float r12 = r12 - r7
            r11.newLineAtOffset(r12, r3)
            r11.showText(r1)
            r3 = r0
            r12 = r4
            r7 = r8
            r1 = r9
            goto L5c
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.interactive.form.AppearanceGeneratorHelper.insertGeneratedCombAppearance(com.tom_roush.pdfbox.pdmodel.PDPageContentStream, com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream, com.tom_roush.pdfbox.pdmodel.font.PDFont, float):void");
    }

    private void insertGeneratedListboxSelectionHighlight(PDPageContentStream pDPageContentStream, PDAppearanceStream pDAppearanceStream, PDFont pDFont, float f) throws IOException {
        PDListBox pDListBox = (PDListBox) this.field;
        List<Integer> selectedOptionsIndex = pDListBox.getSelectedOptionsIndex();
        List<String> value = pDListBox.getValue();
        List<String> optionsExportValues = pDListBox.getOptionsExportValues();
        if (!value.isEmpty() && !optionsExportValues.isEmpty() && selectedOptionsIndex.isEmpty()) {
            selectedOptionsIndex = new ArrayList<>(value.size());
            for (String str : value) {
                selectedOptionsIndex.add(Integer.valueOf(optionsExportValues.indexOf(str)));
            }
        }
        int topIndex = pDListBox.getTopIndex();
        float height = (pDFont.getBoundingBox().getHeight() * f) / 1000.0f;
        PDRectangle applyPadding = applyPadding(pDAppearanceStream.getBBox(), 1.0f);
        for (Integer num : selectedOptionsIndex) {
            int intValue = num.intValue();
            float[] fArr = HIGHLIGHT_COLOR;
            pDPageContentStream.setNonStrokingColor(fArr[0], fArr[1], fArr[2]);
            pDPageContentStream.addRect(applyPadding.getLowerLeftX(), (applyPadding.getUpperRightY() - (((intValue - topIndex) + 1) * height)) + 2.0f, applyPadding.getWidth(), height);
            pDPageContentStream.fill();
        }
        pDPageContentStream.setNonStrokingColor(0.0f);
    }

    private void insertGeneratedListboxAppearance(PDPageContentStream pDPageContentStream, PDAppearanceStream pDAppearanceStream, PDRectangle pDRectangle, PDFont pDFont, float f) throws IOException {
        pDPageContentStream.setNonStrokingColor(0.0f);
        int q = this.field.getQ();
        if (q == 1 || q == 2) {
            float width = (pDAppearanceStream.getBBox().getWidth() - ((pDFont.getStringWidth(this.value) / 1000.0f) * f)) - MINIMUM_FONT_SIZE;
            if (q == 1) {
                width /= 2.0f;
            }
            pDPageContentStream.newLineAtOffset(width, 0.0f);
        } else if (q != 0) {
            throw new IOException("Error: Unknown justification value:" + q);
        }
        List<String> optionsDisplayValues = ((PDListBox) this.field).getOptionsDisplayValues();
        int size = optionsDisplayValues.size();
        float upperRightY = pDRectangle.getUpperRightY();
        int topIndex = ((PDListBox) this.field).getTopIndex();
        float ascent = pDFont.getFontDescriptor().getAscent();
        float height = pDFont.getBoundingBox().getHeight();
        for (int i = topIndex; i < size; i++) {
            if (i == topIndex) {
                upperRightY -= (ascent / 1000.0f) * f;
            } else {
                upperRightY -= (height / 1000.0f) * f;
                pDPageContentStream.beginText();
            }
            pDPageContentStream.newLineAtOffset(pDRectangle.getLowerLeftX(), upperRightY);
            pDPageContentStream.showText(optionsDisplayValues.get(i));
            if (i != size - 1) {
                pDPageContentStream.endText();
            }
        }
    }

    private void writeToStream(byte[] bArr, PDAppearanceStream pDAppearanceStream) throws IOException {
        OutputStream createOutputStream = pDAppearanceStream.getCOSObject().createOutputStream();
        createOutputStream.write(bArr);
        createOutputStream.close();
    }

    private float calculateFontSize(PDFont pDFont, PDRectangle pDRectangle) throws IOException {
        float fontSize = this.defaultAppearance.getFontSize();
        if (fontSize == 0.0f) {
            if (isMultiLine()) {
                PlainText plainText = new PlainText(this.value);
                if (plainText.getParagraphs() != null) {
                    float width = pDRectangle.getWidth() - pDRectangle.getLowerLeftX();
                    float f = 4.0f;
                    while (f <= DEFAULT_FONT_SIZE) {
                        int i = 0;
                        for (PlainText.Paragraph paragraph : plainText.getParagraphs()) {
                            i += paragraph.getLines(pDFont, f, width).size();
                        }
                        if (pDFont.getBoundingBox().getHeight() * (f / 1000.0f) * i > pDRectangle.getHeight()) {
                            return Math.max(f - 1.0f, (float) MINIMUM_FONT_SIZE);
                        }
                        f += 1.0f;
                    }
                    return Math.min(f, (float) DEFAULT_FONT_SIZE);
                }
                return DEFAULT_FONT_SIZE;
            }
            float scaleY = pDFont.getFontMatrix().getScaleY() * 1000.0f;
            float width2 = (pDRectangle.getWidth() / (pDFont.getStringWidth(this.value) * pDFont.getFontMatrix().getScaleX())) * pDFont.getFontMatrix().getScaleX() * 1000.0f;
            float capHeight = (pDFont.getFontDescriptor().getCapHeight() + (-pDFont.getFontDescriptor().getDescent())) * pDFont.getFontMatrix().getScaleY();
            if (capHeight <= 0.0f) {
                capHeight = pDFont.getBoundingBox().getHeight() * pDFont.getFontMatrix().getScaleY();
            }
            return Math.min((pDRectangle.getHeight() / capHeight) * scaleY, width2);
        }
        return fontSize;
    }

    private float resolveCapHeight(PDFont pDFont) throws IOException {
        return resolveGlyphHeight(pDFont, StandardStructureTypes.H.codePointAt(0));
    }

    private float resolveDescent(PDFont pDFont) throws IOException {
        return resolveGlyphHeight(pDFont, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT.codePointAt(0)) - resolveGlyphHeight(pDFont, PDPageLabelRange.STYLE_LETTERS_LOWER.codePointAt(0));
    }

    private float resolveGlyphHeight(PDFont pDFont, int i) throws IOException {
        Path path = null;
        if (pDFont instanceof PDType3Font) {
            PDType3Font pDType3Font = (PDType3Font) pDFont;
            PDType3CharProc charProc = pDType3Font.getCharProc(i);
            if (charProc != null) {
                BoundingBox boundingBox = pDType3Font.getBoundingBox();
                PDRectangle glyphBBox = charProc.getGlyphBBox();
                if (glyphBBox != null) {
                    glyphBBox.setLowerLeftX(Math.max(boundingBox.getLowerLeftX(), glyphBBox.getLowerLeftX()));
                    glyphBBox.setLowerLeftY(Math.max(boundingBox.getLowerLeftY(), glyphBBox.getLowerLeftY()));
                    glyphBBox.setUpperRightX(Math.min(boundingBox.getUpperRightX(), glyphBBox.getUpperRightX()));
                    glyphBBox.setUpperRightY(Math.min(boundingBox.getUpperRightY(), glyphBBox.getUpperRightY()));
                    path = glyphBBox.toGeneralPath();
                }
            }
        } else if (pDFont instanceof PDVectorFont) {
            path = ((PDVectorFont) pDFont).getPath(i);
        } else if (pDFont instanceof PDSimpleFont) {
            PDSimpleFont pDSimpleFont = (PDSimpleFont) pDFont;
            path = pDSimpleFont.getPath(pDSimpleFont.getEncoding().getName(i));
        } else {
            Log.w("PdfBox-Android", "Unknown font class: " + pDFont.getClass());
        }
        if (path == null) {
            return -1.0f;
        }
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return rectF.height();
    }

    private PDRectangle resolveBoundingBox(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream) {
        PDRectangle bBox = pDAppearanceStream.getBBox();
        return bBox == null ? pDAnnotationWidget.getRectangle().createRetranslatedRectangle() : bBox;
    }

    private PDRectangle applyPadding(PDRectangle pDRectangle, float f) {
        float lowerLeftX = pDRectangle.getLowerLeftX() + f;
        float lowerLeftY = pDRectangle.getLowerLeftY() + f;
        float f2 = f * 2.0f;
        return new PDRectangle(lowerLeftX, lowerLeftY, pDRectangle.getWidth() - f2, pDRectangle.getHeight() - f2);
    }
}
