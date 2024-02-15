package org.apache.commons.imaging.formats.png;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class GammaCorrection {
    private static final Logger LOGGER = Logger.getLogger(GammaCorrection.class.getName());
    private final int[] lookupTable;

    public GammaCorrection(double d, double d2) {
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("src_gamma: " + d);
            logger.finest("dst_gamma: " + d2);
        }
        this.lookupTable = new int[256];
        for (int i = 0; i < 256; i++) {
            this.lookupTable[i] = correctSample(i, d, d2);
            Logger logger2 = LOGGER;
            if (logger2.isLoggable(Level.FINEST)) {
                logger2.finest("lookup_table[" + i + "]: " + this.lookupTable[i]);
            }
        }
    }

    public int correctSample(int i) {
        return this.lookupTable[i];
    }

    public int correctARGB(int i) {
        int i2 = (-16777216) & i;
        int correctSample = correctSample((i >> 16) & 255);
        int correctSample2 = correctSample((i >> 8) & 255);
        return ((correctSample((i >> 0) & 255) & 255) << 0) | i2 | ((correctSample & 255) << 16) | ((correctSample2 & 255) << 8);
    }

    private int correctSample(int i, double d, double d2) {
        return (int) Math.round(Math.pow(i / 255.0d, d / d2) * 255.0d);
    }
}
