package org.apache.commons.imaging.common;

import java.awt.image.BufferedImage;

/* loaded from: classes2.dex */
public class SimpleBufferedImageFactory implements BufferedImageFactory {
    @Override // org.apache.commons.imaging.common.BufferedImageFactory
    public BufferedImage getColorBufferedImage(int i, int i2, boolean z) {
        if (z) {
            return new BufferedImage(i, i2, 2);
        }
        return new BufferedImage(i, i2, 1);
    }

    @Override // org.apache.commons.imaging.common.BufferedImageFactory
    public BufferedImage getGrayscaleBufferedImage(int i, int i2, boolean z) {
        if (z) {
            return new BufferedImage(i, i2, 2);
        }
        return new BufferedImage(i, i2, 10);
    }
}
