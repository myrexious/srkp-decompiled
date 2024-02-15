package org.apache.commons.imaging.formats.tiff.write;

import java.io.IOException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryOutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class TiffOutputItem {
    public static final long UNDEFINED_VALUE = -1;
    private long offset = -1;

    public abstract String getItemDescription();

    public abstract int getItemLength();

    public abstract void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException;

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    /* loaded from: classes2.dex */
    public static class Value extends TiffOutputItem {
        private final byte[] bytes;
        private final String name;

        public Value(String str, byte[] bArr) {
            this.name = str;
            this.bytes = bArr;
        }

        @Override // org.apache.commons.imaging.formats.tiff.write.TiffOutputItem
        public int getItemLength() {
            return this.bytes.length;
        }

        @Override // org.apache.commons.imaging.formats.tiff.write.TiffOutputItem
        public String getItemDescription() {
            return this.name;
        }

        public void updateValue(byte[] bArr) throws ImageWriteException {
            byte[] bArr2 = this.bytes;
            if (bArr2.length != bArr.length) {
                throw new ImageWriteException("Updated data size mismatch: " + this.bytes.length + " vs. " + bArr.length);
            }
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }

        @Override // org.apache.commons.imaging.formats.tiff.write.TiffOutputItem
        public void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
            binaryOutputStream.write(this.bytes);
        }
    }
}
