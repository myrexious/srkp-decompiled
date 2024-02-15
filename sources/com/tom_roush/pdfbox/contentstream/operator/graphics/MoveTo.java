package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.graphics.PointF;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public final class MoveTo extends GraphicsOperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.MOVE_TO;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSNumber) {
            COSBase cOSBase2 = list.get(1);
            if (cOSBase2 instanceof COSNumber) {
                PointF transformedPoint = this.context.transformedPoint(((COSNumber) cOSBase).floatValue(), ((COSNumber) cOSBase2).floatValue());
                this.context.moveTo(transformedPoint.x, transformedPoint.y);
            }
        }
    }
}
