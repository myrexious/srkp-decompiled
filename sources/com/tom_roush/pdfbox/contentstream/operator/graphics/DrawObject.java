package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.MissingResourceException;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public final class DrawObject extends GraphicsOperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.DRAW_OBJECT;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.isEmpty()) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSName) {
            COSName cOSName = (COSName) cOSBase;
            PDXObject xObject = this.context.getResources().getXObject(cOSName);
            if (xObject == null) {
                throw new MissingResourceException("Missing XObject: " + cOSName.getName());
            }
            if (xObject instanceof PDImageXObject) {
                this.context.drawImage((PDImageXObject) xObject);
            } else if (xObject instanceof PDFormXObject) {
                try {
                    this.context.increaseLevel();
                    if (this.context.getLevel() > 50) {
                        Log.e("PdfBox-Android", "recursion is too deep, skipping form XObject");
                        return;
                    }
                    if (xObject instanceof PDTransparencyGroup) {
                        this.context.showTransparencyGroup((PDTransparencyGroup) xObject);
                    } else {
                        this.context.showForm((PDFormXObject) xObject);
                    }
                } finally {
                    this.context.decreaseLevel();
                }
            }
        }
    }
}
