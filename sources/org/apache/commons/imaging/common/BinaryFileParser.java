package org.apache.commons.imaging.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class BinaryFileParser {
    private static final Logger LOGGER = Logger.getLogger(BinaryFileParser.class.getName());
    private ByteOrder byteOrder;

    public BinaryFileParser(ByteOrder byteOrder) {
        ByteOrder byteOrder2 = ByteOrder.BIG_ENDIAN;
        this.byteOrder = byteOrder;
    }

    public BinaryFileParser() {
        this.byteOrder = ByteOrder.BIG_ENDIAN;
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    public final void debugNumber(String str, int i, int i2) {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            try {
                debugNumber(printWriter, str, i, i2);
                printWriter.flush();
                stringWriter.flush();
                LOGGER.fine(stringWriter.toString());
                printWriter.close();
                stringWriter.close();
            } catch (Throwable th) {
                try {
                    printWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), (Throwable) e);
        }
    }

    public final void debugNumber(PrintWriter printWriter, String str, int i, int i2) {
        printWriter.print(str + ": " + i + " (");
        int i3 = i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 > 0) {
                printWriter.print(",");
            }
            int i5 = i3 & 255;
            printWriter.print(((char) i5) + " [" + i5 + "]");
            i3 >>= 8;
        }
        printWriter.println(") [0x" + Integer.toHexString(i) + ", " + Integer.toBinaryString(i) + "]");
        printWriter.flush();
    }
}
