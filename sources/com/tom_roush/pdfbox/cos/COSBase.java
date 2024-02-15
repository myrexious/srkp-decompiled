package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class COSBase implements COSObjectable {
    private boolean direct;

    public abstract Object accept(ICOSVisitor iCOSVisitor) throws IOException;

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public void setDirect(boolean z) {
        this.direct = z;
    }
}
