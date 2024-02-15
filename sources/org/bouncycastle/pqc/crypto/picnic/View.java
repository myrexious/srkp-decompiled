package org.bouncycastle.pqc.crypto.picnic;

/* loaded from: classes2.dex */
public class View {
    byte[] communicatedBits;
    int[] inputShare;
    int[] outputShare;

    public View(PicnicEngine picnicEngine) {
        this.inputShare = new int[picnicEngine.stateSizeBytes];
        this.communicatedBits = new byte[picnicEngine.andSizeBytes];
        this.outputShare = new int[picnicEngine.stateSizeBytes];
    }
}
