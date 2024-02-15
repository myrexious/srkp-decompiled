package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class SetStrokingDeviceCMYKColor extends SetStrokingColor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetStrokingColor, com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "K";
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetColor, com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().setStrokingColorSpace(this.context.getResources().getColorSpace(COSName.DEVICECMYK));
        super.process(operator, list);
    }
}
