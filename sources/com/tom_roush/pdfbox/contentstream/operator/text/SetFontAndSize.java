package com.tom_roush.pdfbox.contentstream.operator.text;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class SetFontAndSize extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.SET_FONT_AND_SIZE;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        COSBase cOSBase2 = list.get(1);
        if ((cOSBase instanceof COSName) && (cOSBase2 instanceof COSNumber)) {
            COSName cOSName = (COSName) cOSBase;
            this.context.getGraphicsState().getTextState().setFontSize(((COSNumber) cOSBase2).floatValue());
            PDFont font = this.context.getResources().getFont(cOSName);
            if (font == null) {
                Log.w("PdfBox-Android", "font '" + cOSName.getName() + "' not found in resources");
            }
            this.context.getGraphicsState().getTextState().setFont(font);
        }
    }
}
