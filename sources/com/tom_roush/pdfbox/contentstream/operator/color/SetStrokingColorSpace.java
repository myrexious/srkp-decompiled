package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class SetStrokingColorSpace extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.STROKING_COLORSPACE;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSName) {
            PDColorSpace colorSpace = this.context.getResources().getColorSpace((COSName) cOSBase);
            this.context.getGraphicsState().setStrokingColorSpace(colorSpace);
            this.context.getGraphicsState().setStrokingColor(colorSpace.getInitialColor());
        }
    }
}
