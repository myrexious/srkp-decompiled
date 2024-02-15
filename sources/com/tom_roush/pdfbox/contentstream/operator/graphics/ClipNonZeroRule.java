package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.graphics.Path;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class ClipNonZeroRule extends GraphicsOperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "W";
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.clip(Path.FillType.WINDING);
    }
}
