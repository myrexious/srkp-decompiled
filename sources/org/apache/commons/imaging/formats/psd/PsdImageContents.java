package org.apache.commons.imaging.formats.psd;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class PsdImageContents {
    private static final Logger LOGGER = Logger.getLogger(PsdImageContents.class.getName());
    public final int ColorModeDataLength;
    public final int Compression;
    public final int ImageResourcesLength;
    public final int LayerAndMaskDataLength;
    public final PsdHeaderInfo header;

    public PsdImageContents(PsdHeaderInfo psdHeaderInfo, int i, int i2, int i3, int i4) {
        this.header = psdHeaderInfo;
        this.ColorModeDataLength = i;
        this.ImageResourcesLength = i2;
        this.LayerAndMaskDataLength = i3;
        this.Compression = i4;
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
        printWriter.println("ImageContents");
        printWriter.println("Compression: " + this.Compression + " (" + Integer.toHexString(this.Compression) + ")");
        printWriter.println("ColorModeDataLength: " + this.ColorModeDataLength + " (" + Integer.toHexString(this.ColorModeDataLength) + ")");
        printWriter.println("ImageResourcesLength: " + this.ImageResourcesLength + " (" + Integer.toHexString(this.ImageResourcesLength) + ")");
        printWriter.println("LayerAndMaskDataLength: " + this.LayerAndMaskDataLength + " (" + Integer.toHexString(this.LayerAndMaskDataLength) + ")");
        printWriter.println("");
        printWriter.flush();
    }
}
