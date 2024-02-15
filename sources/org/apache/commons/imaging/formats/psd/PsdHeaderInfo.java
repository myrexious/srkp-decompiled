package org.apache.commons.imaging.formats.psd;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class PsdHeaderInfo {
    private static final Logger LOGGER = Logger.getLogger(PsdHeaderInfo.class.getName());
    public final int channels;
    public final int columns;
    public final int depth;
    public final int mode;
    private final byte[] reserved;
    public final int rows;
    public final int version;

    public PsdHeaderInfo(int i, byte[] bArr, int i2, int i3, int i4, int i5, int i6) {
        this.version = i;
        this.reserved = (byte[]) bArr.clone();
        this.channels = i2;
        this.rows = i3;
        this.columns = i4;
        this.depth = i5;
        this.mode = i6;
    }

    public byte[] getReserved() {
        return (byte[]) this.reserved.clone();
    }

    public void dump() {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            try {
                dump(printWriter);
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

    public void dump(PrintWriter printWriter) {
        printWriter.println("");
        printWriter.println("Header");
        printWriter.println("Version: " + this.version + " (" + Integer.toHexString(this.version) + ")");
        printWriter.println("Channels: " + this.channels + " (" + Integer.toHexString(this.channels) + ")");
        printWriter.println("Rows: " + this.rows + " (" + Integer.toHexString(this.rows) + ")");
        printWriter.println("Columns: " + this.columns + " (" + Integer.toHexString(this.columns) + ")");
        printWriter.println("Depth: " + this.depth + " (" + Integer.toHexString(this.depth) + ")");
        printWriter.println("Mode: " + this.mode + " (" + Integer.toHexString(this.mode) + ")");
        printWriter.println("Reserved: " + this.reserved.length);
        printWriter.println("");
        printWriter.flush();
    }
}
