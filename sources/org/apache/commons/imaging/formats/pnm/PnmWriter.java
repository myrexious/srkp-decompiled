package org.apache.commons.imaging.formats.pnm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.imaging.ImageWriteException;

/* loaded from: classes2.dex */
interface PnmWriter {
    void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PnmImagingParameters pnmImagingParameters) throws ImageWriteException, IOException;
}
