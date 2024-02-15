package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PointProj {
    long[][] X;
    long[][] Z;

    public PointProj(int i) {
        this.X = (long[][]) Array.newInstance(Long.TYPE, 2, i);
        this.Z = (long[][]) Array.newInstance(Long.TYPE, 2, i);
    }
}
