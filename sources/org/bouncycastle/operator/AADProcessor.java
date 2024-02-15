package org.bouncycastle.operator;

import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface AADProcessor {
    OutputStream getAADStream();

    byte[] getMAC();
}
