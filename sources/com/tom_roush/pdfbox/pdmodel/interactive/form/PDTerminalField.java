package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes3.dex */
public abstract class PDTerminalField extends PDField {
    public abstract void constructAppearances() throws IOException;

    public PDTerminalField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    public PDTerminalField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public void setActions(PDFormFieldAdditionalActions pDFormFieldAdditionalActions) {
        getCOSObject().setItem(COSName.AA, pDFormFieldAdditionalActions);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public int getFieldFlags() {
        COSInteger cOSInteger = (COSInteger) getCOSObject().getDictionaryObject(COSName.FF);
        if (cOSInteger != null) {
            return cOSInteger.intValue();
        }
        if (getParent() != null) {
            return getParent().getFieldFlags();
        }
        return 0;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public String getFieldType() {
        String nameAsString = getCOSObject().getNameAsString(COSName.FT);
        return (nameAsString != null || getParent() == null) ? nameAsString : getParent().getFieldType();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public void importFDF(FDFField fDFField) throws IOException {
        super.importFDF(fDFField);
        Integer widgetFieldFlags = fDFField.getWidgetFieldFlags();
        for (PDAnnotationWidget pDAnnotationWidget : getWidgets()) {
            if (widgetFieldFlags != null) {
                pDAnnotationWidget.setAnnotationFlags(widgetFieldFlags.intValue());
            } else {
                Integer setWidgetFieldFlags = fDFField.getSetWidgetFieldFlags();
                int annotationFlags = pDAnnotationWidget.getAnnotationFlags();
                if (setWidgetFieldFlags != null) {
                    annotationFlags |= setWidgetFieldFlags.intValue();
                    pDAnnotationWidget.setAnnotationFlags(annotationFlags);
                }
                Integer clearWidgetFieldFlags = fDFField.getClearWidgetFieldFlags();
                if (clearWidgetFieldFlags != null) {
                    pDAnnotationWidget.setAnnotationFlags(((int) (clearWidgetFieldFlags.intValue() ^ BodyPartID.bodyIdMax)) & annotationFlags);
                }
            }
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public FDFField exportFDF() throws IOException {
        FDFField fDFField = new FDFField();
        fDFField.setPartialFieldName(getPartialName());
        fDFField.setValue(getCOSObject().getDictionaryObject(COSName.V));
        return fDFField;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public List<PDAnnotationWidget> getWidgets() {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            arrayList.add(new PDAnnotationWidget(getCOSObject()));
        } else if (cOSArray.size() > 0) {
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSDictionary) {
                    arrayList.add(new PDAnnotationWidget((COSDictionary) object));
                }
            }
        }
        return arrayList;
    }

    public void setWidgets(List<PDAnnotationWidget> list) {
        getCOSObject().setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
        for (PDAnnotationWidget pDAnnotationWidget : list) {
            pDAnnotationWidget.getCOSObject().setItem(COSName.PARENT, this);
        }
    }

    @Deprecated
    public PDAnnotationWidget getWidget() {
        return getWidgets().get(0);
    }

    public final void applyChange() throws IOException {
        if (getAcroForm().getNeedAppearances()) {
            return;
        }
        constructAppearances();
    }
}
