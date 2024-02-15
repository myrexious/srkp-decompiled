package org.apache.commons.imaging.formats.psd;

import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.internal.Debug;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ImageResourceBlock {
    final byte[] data;

    /* renamed from: id */
    final int f34id;
    final byte[] nameData;

    public ImageResourceBlock(int i, byte[] bArr, byte[] bArr2) {
        this.f34id = i;
        this.nameData = bArr;
        this.data = bArr2;
    }

    String getName() {
        Debug.debug("getName: " + this.nameData.length);
        return new String(this.nameData, StandardCharsets.ISO_8859_1);
    }
}
