package org.apache.commons.imaging.formats.gif;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class GenericGifBlock extends GifBlock {
    final List<byte[]> subBlocks;

    public GenericGifBlock(int i, List<byte[]> list) {
        super(i);
        this.subBlocks = list;
    }

    public byte[] appendSubBlocks() throws IOException {
        return appendSubBlocks(false);
    }

    public byte[] appendSubBlocks(boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < this.subBlocks.size(); i++) {
            byte[] bArr = this.subBlocks.get(i);
            if (z && i > 0) {
                byteArrayOutputStream.write(bArr.length);
            }
            byteArrayOutputStream.write(bArr);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
