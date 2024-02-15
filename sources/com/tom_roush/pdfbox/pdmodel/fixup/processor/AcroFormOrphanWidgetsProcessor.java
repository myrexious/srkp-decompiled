package com.tom_roush.pdfbox.pdmodel.fixup.processor;

import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.font.FontMappers;
import com.tom_roush.pdfbox.pdmodel.font.FontMapping;
import com.tom_roush.pdfbox.pdmodel.font.PDType0Font;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDFieldFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDVariableText;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class AcroFormOrphanWidgetsProcessor extends AbstractProcessor {
    public AcroFormOrphanWidgetsProcessor(PDDocument pDDocument) {
        super(pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.fixup.processor.PDDocumentProcessor
    public void process() {
        PDAcroForm acroForm = this.document.getDocumentCatalog().getAcroForm(null);
        if (acroForm != null) {
            resolveFieldsFromWidgets(acroForm);
        }
    }

    private void resolveFieldsFromWidgets(PDAcroForm pDAcroForm) {
        Log.d("PdfBox-Android", "rebuilding fields from widgets");
        PDResources defaultResources = pDAcroForm.getDefaultResources();
        if (defaultResources == null) {
            Log.d("PdfBox-Android", "AcroForm default resources is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        Iterator<PDPage> it = this.document.getPages().iterator();
        while (it.hasNext()) {
            try {
                handleAnnotations(pDAcroForm, defaultResources, arrayList, it.next().getAnnotations(), hashMap);
            } catch (IOException e) {
                Log.d("PdfBox-Android", "couldn't read annotations for page " + e.getMessage());
            }
        }
        pDAcroForm.setFields(arrayList);
        Iterator<PDField> it2 = pDAcroForm.getFieldTree().iterator();
        while (it2.hasNext()) {
            PDField next = it2.next();
            if (next instanceof PDVariableText) {
                ensureFontResources(defaultResources, (PDVariableText) next);
            }
        }
    }

    private void handleAnnotations(PDAcroForm pDAcroForm, PDResources pDResources, List<PDField> list, List<PDAnnotation> list2, Map<String, PDField> map) {
        for (PDAnnotation pDAnnotation : list2) {
            if (pDAnnotation instanceof PDAnnotationWidget) {
                addFontFromWidget(pDResources, pDAnnotation);
                COSDictionary cOSDictionary = pDAnnotation.getCOSObject().getCOSDictionary(COSName.PARENT);
                if (cOSDictionary != null) {
                    PDField resolveNonRootField = resolveNonRootField(pDAcroForm, cOSDictionary, map);
                    if (resolveNonRootField != null) {
                        list.add(resolveNonRootField);
                    }
                } else {
                    list.add(PDFieldFactory.createField(pDAcroForm, pDAnnotation.getCOSObject(), null));
                }
            }
        }
    }

    private void addFontFromWidget(PDResources pDResources, PDAnnotation pDAnnotation) {
        PDResources resources;
        PDAppearanceStream normalAppearanceStream = pDAnnotation.getNormalAppearanceStream();
        if (normalAppearanceStream == null || (resources = normalAppearanceStream.getResources()) == null) {
            return;
        }
        for (COSName cOSName : resources.getFontNames()) {
            if (!cOSName.getName().startsWith("+")) {
                try {
                    if (pDResources.getFont(cOSName) == null) {
                        pDResources.put(cOSName, resources.getFont(cOSName));
                        Log.d("PdfBox-Android", "added font resource to AcroForm from widget for font name " + cOSName.getName());
                    }
                } catch (IOException unused) {
                    Log.d("PdfBox-Android", "unable to add font to AcroForm for font name " + cOSName.getName());
                }
            } else {
                Log.d("PdfBox-Android", "font resource for widget was a subsetted font - ignored: " + cOSName.getName());
            }
        }
    }

    private PDField resolveNonRootField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, Map<String, PDField> map) {
        while (cOSDictionary.containsKey(COSName.PARENT)) {
            cOSDictionary = cOSDictionary.getCOSDictionary(COSName.PARENT);
            if (cOSDictionary == null) {
                return null;
            }
        }
        if (map.get(cOSDictionary.getString(COSName.T)) == null) {
            PDField createField = PDFieldFactory.createField(pDAcroForm, cOSDictionary, null);
            if (createField != null) {
                map.put(createField.getFullyQualifiedName(), createField);
            }
            return createField;
        }
        return null;
    }

    private void ensureFontResources(PDResources pDResources, PDVariableText pDVariableText) {
        String defaultAppearance = pDVariableText.getDefaultAppearance();
        if (!defaultAppearance.startsWith(RemoteSettings.FORWARD_SLASH_STRING) || defaultAppearance.length() <= 1) {
            return;
        }
        COSName pDFName = COSName.getPDFName(defaultAppearance.substring(1, defaultAppearance.indexOf(StringUtils.SPACE)));
        try {
            if (pDResources.getFont(pDFName) == null) {
                Log.d("PdfBox-Android", "trying to add missing font resource for field " + pDVariableText.getFullyQualifiedName());
                FontMapping<TrueTypeFont> trueTypeFont = FontMappers.instance().getTrueTypeFont(pDFName.getName(), null);
                if (trueTypeFont != null) {
                    PDType0Font load = PDType0Font.load(this.document, trueTypeFont.getFont(), false);
                    Log.d("PdfBox-Android", "looked up font for " + pDFName.getName() + " - found " + trueTypeFont.getFont().getName());
                    pDResources.put(pDFName, load);
                } else {
                    Log.d("PdfBox-Android", "no suitable font found for field " + pDVariableText.getFullyQualifiedName() + " for font name " + pDFName.getName());
                }
            }
        } catch (IOException e) {
            Log.d("PdfBox-Android", "Unable to handle font resources for field " + pDVariableText.getFullyQualifiedName() + ": " + e.getMessage());
        }
    }
}
