package com.tom_roush.pdfbox.contentstream.operator.state;

import android.graphics.Paint;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class SetLineJoinStyle extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.SET_LINE_JOINSTYLE;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        Paint.Join join;
        if (list.isEmpty()) {
            throw new MissingOperandException(operator, list);
        }
        int intValue = ((COSNumber) list.get(0)).intValue();
        if (intValue == 0) {
            join = Paint.Join.MITER;
        } else if (intValue == 1) {
            join = Paint.Join.ROUND;
        } else {
            join = intValue != 2 ? null : Paint.Join.BEVEL;
        }
        this.context.getGraphicsState().setLineJoin(join);
    }
}
