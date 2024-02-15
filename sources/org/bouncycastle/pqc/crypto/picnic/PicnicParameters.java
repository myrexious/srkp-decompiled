package org.bouncycastle.pqc.crypto.picnic;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes2.dex */
public class PicnicParameters implements CipherParameters {
    private final String name;
    private final int param;
    public static final PicnicParameters picnicl1fs = new PicnicParameters("picnicl1fs", 1);
    public static final PicnicParameters picnicl1ur = new PicnicParameters("picnicl1ur", 2);
    public static final PicnicParameters picnicl3fs = new PicnicParameters("picnicl3fs", 3);
    public static final PicnicParameters picnicl3ur = new PicnicParameters("picnicl3ur", 4);
    public static final PicnicParameters picnicl5fs = new PicnicParameters("picnicl5fs", 5);
    public static final PicnicParameters picnicl5ur = new PicnicParameters("picnicl5ur", 6);
    public static final PicnicParameters picnic3l1 = new PicnicParameters("picnic3l1", 7);
    public static final PicnicParameters picnic3l3 = new PicnicParameters("picnic3l3", 8);
    public static final PicnicParameters picnic3l5 = new PicnicParameters("picnic3l5", 9);
    public static final PicnicParameters picnicl1full = new PicnicParameters("picnicl1full", 10);
    public static final PicnicParameters picnicl3full = new PicnicParameters("picnicl3full", 11);
    public static final PicnicParameters picnicl5full = new PicnicParameters("picnicl5full", 12);

    private PicnicParameters(String str, int i) {
        this.name = str;
        this.param = i;
    }

    public PicnicEngine getEngine() {
        return new PicnicEngine(this.param);
    }

    public String getName() {
        return this.name;
    }
}
