package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class ShowTextLineAndSpace extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.SHOW_TEXT_LINE_AND_SPACE;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 3) {
            throw new MissingOperandException(operator, list);
        }
        this.context.processOperator(OperatorName.SET_WORD_SPACING, list.subList(0, 1));
        this.context.processOperator(OperatorName.SET_CHAR_SPACING, list.subList(1, 2));
        this.context.processOperator(OperatorName.SHOW_TEXT_LINE, list.subList(2, 3));
    }
}
