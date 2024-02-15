package com.tom_roush.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public final class COSBoolean extends COSBase {
    private final boolean value;
    public static final byte[] TRUE_BYTES = {BuiltinOptions.GeluOptions, BuiltinOptions.RandomOptions, BuiltinOptions.DynamicUpdateSliceOptions, BuiltinOptions.BatchMatMulOptions};
    public static final byte[] FALSE_BYTES = {BuiltinOptions.CumsumOptions, BuiltinOptions.ScatterNdOptions, BuiltinOptions.HashtableFindOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.BatchMatMulOptions};
    public static final COSBoolean TRUE = new COSBoolean(true);
    public static final COSBoolean FALSE = new COSBoolean(false);

    private COSBoolean(boolean z) {
        this.value = z;
    }

    public boolean getValue() {
        return this.value;
    }

    public Boolean getValueAsObject() {
        return this.value ? Boolean.TRUE : Boolean.FALSE;
    }

    public static COSBoolean getBoolean(boolean z) {
        return z ? TRUE : FALSE;
    }

    public static COSBoolean getBoolean(Boolean bool) {
        return getBoolean(bool.booleanValue());
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromBoolean(this);
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        if (this.value) {
            outputStream.write(TRUE_BYTES);
        } else {
            outputStream.write(FALSE_BYTES);
        }
    }
}
