package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class SetNonStrokingDeviceGrayColor extends SetNonStrokingColor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetNonStrokingColor, com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.NON_STROKING_GRAY;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetColor, com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().setNonStrokingColorSpace(this.context.getResources().getColorSpace(COSName.DEVICEGRAY));
        super.process(operator, list);
    }
}
