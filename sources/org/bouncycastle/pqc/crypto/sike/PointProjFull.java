package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PointProjFull {
    long[][] X;
    long[][] Y;
    long[][] Z;

    public PointProjFull(int i) {
        this.X = (long[][]) Array.newInstance(Long.TYPE, 2, i);
        this.Y = (long[][]) Array.newInstance(Long.TYPE, 2, i);
        this.Z = (long[][]) Array.newInstance(Long.TYPE, 2, i);
    }
}
