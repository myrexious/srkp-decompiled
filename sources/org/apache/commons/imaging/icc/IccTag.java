package org.apache.commons.imaging.icc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class IccTag {
    private static final Logger LOGGER = Logger.getLogger(IccTag.class.getName());
    private byte[] data;
    private int dataTypeSignature;
    public final IccTagType fIccTagType;
    private IccTagDataType itdt;
    public final int length;
    public final int offset;
    public final int signature;

    public IccTag(int i, int i2, int i3, IccTagType iccTagType) {
        this.signature = i;
        this.offset = i2;
        this.length = i3;
        this.fIccTagType = iccTagType;
    }

    public void setData(byte[] bArr) throws IOException {
        this.data = bArr;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            int read4Bytes = BinaryFunctions.read4Bytes("data type signature", byteArrayInputStream, "ICC: corrupt tag data", ByteOrder.BIG_ENDIAN);
            this.dataTypeSignature = read4Bytes;
            this.itdt = getIccTagDataType(read4Bytes);
            byteArrayInputStream.close();
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private IccTagDataType getIccTagDataType(int i) {
        IccTagDataTypes[] values;
        for (IccTagDataTypes iccTagDataTypes : IccTagDataTypes.values()) {
            if (iccTagDataTypes.getSignature() == i) {
                return iccTagDataTypes;
            }
        }
        return null;
    }

    public void dump(String str) throws ImageReadException, IOException {
        StringWriter stringWriter = new StringWriter();
        try {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            dump(printWriter, str);
            printWriter.flush();
            stringWriter.flush();
            LOGGER.fine(stringWriter.toString());
            printWriter.close();
            stringWriter.close();
        } catch (Throwable th) {
            try {
                stringWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void dump(PrintWriter printWriter, String str) throws ImageReadException, IOException {
        StringBuilder append = new StringBuilder().append(str).append("tag signature: ").append(Integer.toHexString(this.signature)).append(" (");
        int i = this.signature;
        printWriter.println(append.append(new String(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 0) & 255)}, StandardCharsets.US_ASCII)).append(")").toString());
        if (this.data == null) {
            printWriter.println(str + "data: " + Arrays.toString((byte[]) null));
        } else {
            printWriter.println(str + "data: " + this.data.length);
            StringBuilder append2 = new StringBuilder().append(str).append("data type signature: ").append(Integer.toHexString(this.dataTypeSignature)).append(" (");
            int i2 = this.dataTypeSignature;
            printWriter.println(append2.append(new String(new byte[]{(byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 0) & 255)}, StandardCharsets.US_ASCII)).append(")").toString());
            if (this.itdt == null) {
                printWriter.println(str + "IccTagType : unknown");
            } else {
                printWriter.println(str + "IccTagType : " + this.itdt.getName());
                this.itdt.dump(str, this.data);
            }
        }
        printWriter.println("");
        printWriter.flush();
    }
}
