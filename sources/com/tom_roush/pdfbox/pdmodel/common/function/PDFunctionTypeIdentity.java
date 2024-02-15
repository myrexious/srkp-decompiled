package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDFunctionTypeIdentity extends PDFunction {
    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public COSArray getRangeValues() {
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public String toString() {
        return "FunctionTypeIdentity";
    }

    public PDFunctionTypeIdentity(COSBase cOSBase) {
        super(null);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        throw new UnsupportedOperationException();
    }
}
