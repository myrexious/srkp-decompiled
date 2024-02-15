package com.tom_roush.pdfbox.contentstream.operator;

import com.tom_roush.pdfbox.contentstream.PDFStreamEngine;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class OperatorProcessor {
    protected PDFStreamEngine context;

    public abstract String getName();

    public abstract void process(Operator operator, List<COSBase> list) throws IOException;

    protected final PDFStreamEngine getContext() {
        return this.context;
    }

    public void setContext(PDFStreamEngine pDFStreamEngine) {
        this.context = pDFStreamEngine;
    }

    public boolean checkArrayTypesClass(List<COSBase> list, Class<?> cls) {
        for (COSBase cOSBase : list) {
            if (!cls.isInstance(cOSBase)) {
                return false;
            }
        }
        return true;
    }
}
