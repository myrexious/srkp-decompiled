package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.graphics.Path;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class FillNonZeroRule extends GraphicsOperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.FILL_NON_ZERO;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public final void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.fillPath(Path.FillType.WINDING);
    }
}
