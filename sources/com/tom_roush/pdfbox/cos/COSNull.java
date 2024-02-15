package com.tom_roush.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public final class COSNull extends COSBase {
    public static final byte[] NULL_BYTES = {BuiltinOptions.HashtableSizeOptions, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.HashtableFindOptions};
    public static final COSNull NULL = new COSNull();

    public String toString() {
        return "COSNull{}";
    }

    private COSNull() {
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromNull(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(NULL_BYTES);
    }
}
