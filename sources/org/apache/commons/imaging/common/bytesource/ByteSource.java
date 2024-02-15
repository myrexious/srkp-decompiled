package org.apache.commons.imaging.common.bytesource;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
public abstract class ByteSource {
    private final String fileName;

    public abstract byte[] getAll() throws IOException;

    public abstract byte[] getBlock(long j, int i) throws IOException;

    public abstract String getDescription();

    public abstract InputStream getInputStream() throws IOException;

    public abstract long getLength() throws IOException;

    public ByteSource(String str) {
        this.fileName = str;
    }

    public final InputStream getInputStream(long j) throws IOException {
        InputStream inputStream;
        try {
            inputStream = getInputStream();
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            BinaryFunctions.skipBytes(inputStream, j);
            return inputStream;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public byte[] getBlock(int i, int i2) throws IOException {
        return getBlock(BodyPartID.bodyIdMax & i, i2);
    }

    public final String getFileName() {
        return this.fileName;
    }
}
