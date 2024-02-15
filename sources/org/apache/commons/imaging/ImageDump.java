package org.apache.commons.imaging;

import androidx.core.os.EnvironmentCompat;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import org.apache.commons.imaging.icc.IccProfileParser;

/* loaded from: classes2.dex */
public class ImageDump {
    private static final Logger LOGGER = Logger.getLogger(ImageDump.class.getName());

    private String colorSpaceTypeToName(ColorSpace colorSpace) {
        int type = colorSpace.getType();
        if (type != 5) {
            if (type != 9) {
                switch (type) {
                    case 1000:
                        return "CS_sRGB";
                    case 1001:
                        return "CS_CIEXYZ";
                    case 1002:
                        return "CS_PYCC";
                    case 1003:
                        return "CS_GRAY";
                    case 1004:
                        return "CS_LINEAR_RGB";
                    default:
                        return EnvironmentCompat.MEDIA_UNKNOWN;
                }
            }
            return "TYPE_CMYK";
        }
        return "TYPE_RGB";
    }

    public void dumpColorSpace(String str, ColorSpace colorSpace) {
        Logger logger = LOGGER;
        logger.fine(str + ": type: " + colorSpace.getType() + " (" + colorSpaceTypeToName(colorSpace) + ")");
        if (!(colorSpace instanceof ICC_ColorSpace)) {
            logger.fine(str + ": Unknown ColorSpace: " + colorSpace.getClass().getName());
            return;
        }
        new IccProfileParser().getICCProfileInfo(((ICC_ColorSpace) colorSpace).getProfile().getData()).dump(str);
    }

    public void dump(BufferedImage bufferedImage) {
        dump("", bufferedImage);
    }

    public void dump(String str, BufferedImage bufferedImage) {
        LOGGER.fine(str + ": dump");
        dumpColorSpace(str, bufferedImage.getColorModel().getColorSpace());
        dumpBIProps(str, bufferedImage);
    }

    public void dumpBIProps(String str, BufferedImage bufferedImage) {
        String[] propertyNames = bufferedImage.getPropertyNames();
        if (propertyNames == null) {
            LOGGER.fine(str + ": no props");
            return;
        }
        for (String str2 : propertyNames) {
            LOGGER.fine(str + ": " + str2 + ": " + bufferedImage.getProperty(str2));
        }
    }
}
