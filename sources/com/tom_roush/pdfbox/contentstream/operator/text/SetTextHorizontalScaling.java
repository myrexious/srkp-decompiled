package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class SetTextHorizontalScaling extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.SET_TEXT_HORIZONTAL_SCALING;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.isEmpty()) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSNumber) {
            this.context.getGraphicsState().getTextState().setHorizontalScaling(((COSNumber) cOSBase).floatValue());
        }
    }
}