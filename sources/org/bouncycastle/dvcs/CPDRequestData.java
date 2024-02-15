package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;

/* loaded from: classes2.dex */
public class CPDRequestData extends DVCSRequestData {
    public CPDRequestData(Data data) throws DVCSConstructionException {
        super(data);
        initMessage();
    }

    private void initMessage() throws DVCSConstructionException {
        if (this.data.getMessage() == null) {
            throw new DVCSConstructionException("DVCSRequest.data.message should be specified for CPD service");
        }
    }

    public byte[] getMessage() {
        return this.data.getMessage().getOctets();
    }
}
