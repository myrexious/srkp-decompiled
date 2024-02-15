package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class ShowTextAdjusted extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.SHOW_TEXT_ADJUSTED;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        COSBase cOSBase = list.get(0);
        if ((cOSBase instanceof COSArray) && this.context.getTextMatrix() != null) {
            this.context.showTextStrings((COSArray) cOSBase);
        }
    }
}
