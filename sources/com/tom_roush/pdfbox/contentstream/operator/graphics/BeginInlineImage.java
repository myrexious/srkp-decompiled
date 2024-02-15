package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDInlineImage;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public final class BeginInlineImage extends GraphicsOperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.BEGIN_INLINE_IMAGE;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (operator.getImageData() == null || operator.getImageData().length == 0) {
            return;
        }
        this.context.drawImage(new PDInlineImage(operator.getImageParameters(), operator.getImageData(), this.context.getResources()));
    }
}
