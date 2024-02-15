package org.apache.commons.imaging.icc;

import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
interface IccTagDataType {
    void dump(String str, byte[] bArr) throws ImageReadException, IOException;

    String getName();

    int getSignature();
}
