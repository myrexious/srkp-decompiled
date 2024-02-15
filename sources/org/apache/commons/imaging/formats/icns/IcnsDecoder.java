package org.apache.commons.imaging.formats.icns;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.icns.IcnsImageParser;
import org.opencv.core.Core;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class IcnsDecoder {
    private static final int[] PALETTE_4BPP = {-1, -199931, -39934, -2291706, -915324, -12189531, -16777004, -16602134, -14698732, -16751599, -11129851, -7311046, -4144960, -8355712, -12566464, ViewCompat.MEASURED_STATE_MASK};
    private static final int[] PALETTE_8BPP = {-1, -52, -103, -154, Core.StsUnmatchedFormats, InputDeviceCompat.SOURCE_ANY, -13057, -13108, -13159, -13210, -13261, -13312, -26113, -26164, -26215, -26266, -26317, -26368, -39169, -39220, -39271, -39322, -39373, -39424, -52225, -52276, -52327, -52378, -52429, -52480, -65281, -65332, -65383, -65434, -65485, SupportMenu.CATEGORY_MASK, -3342337, -3342388, -3342439, -3342490, -3342541, -3342592, -3355393, -3355444, -3355495, -3355546, -3355597, -3355648, -3368449, -3368500, -3368551, -3368602, -3368653, -3368704, -3381505, -3381556, -3381607, -3381658, -3381709, -3381760, -3394561, -3394612, -3394663, -3394714, -3394765, -3394816, -3407617, -3407668, -3407719, -3407770, -3407821, -3407872, -6684673, -6684724, -6684775, -6684826, -6684877, -6684928, -6697729, -6697780, -6697831, -6697882, -6697933, -6697984, -6710785, -6710836, -6710887, -6710938, -6710989, -6711040, -6723841, -6723892, -6723943, -6723994, -6724045, -6724096, -6736897, -6736948, -6736999, -6737050, -6737101, -6737152, -6749953, -6750004, -6750055, -6750106, -6750157, -6750208, -10027009, -10027060, -10027111, -10027162, -10027213, -10027264, -10040065, -10040116, -10040167, -10040218, -10040269, -10040320, -10053121, -10053172, -10053223, -10053274, -10053325, -10053376, -10066177, -10066228, -10066279, -10066330, -10066381, -10066432, -10079233, -10079284, -10079335, -10079386, -10079437, -10079488, -10092289, -10092340, -10092391, -10092442, -10092493, -10092544, -13369345, -13369396, -13369447, -13369498, -13369549, -13369600, -13382401, -13382452, -13382503, -13382554, -13382605, -13382656, -13395457, -13395508, -13395559, -13395610, -13395661, -13395712, -13408513, -13408564, -13408615, -13408666, -13408717, -13408768, -13421569, -13421620, -13421671, -13421722, -13421773, -13421824, -13434625, -13434676, -13434727, -13434778, -13434829, -13434880, -16711681, -16711732, -16711783, -16711834, -16711885, -16711936, -16724737, -16724788, -16724839, -16724890, -16724941, -16724992, -16737793, -16737844, -16737895, -16737946, -16737997, -16738048, -16750849, -16750900, -16750951, -16751002, -16751053, -16751104, -16763905, -16763956, -16764007, -16764058, -16764109, -16764160, -16776961, -16777012, -16777063, -16777114, -16777165, -1179648, -2293760, -4521984, -5636096, -7864320, -8978432, -11206656, -12320768, -14548992, -15663104, -16716288, -16720640, -16729344, -16733696, -16742400, -16746752, -16755456, -16759808, -16768512, -16772864, -16776978, -16776995, -16777029, -16777046, -16777080, -16777097, -16777131, -16777148, -16777182, -16777199, -1118482, -2236963, -4473925, -5592406, -7829368, -8947849, -11184811, -12303292, -14540254, -15658735, ViewCompat.MEASURED_STATE_MASK};

    private IcnsDecoder() {
    }

    private static void decode1BPPImage(IcnsType icnsType, byte[] bArr, ImageBuilder imageBuilder) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < icnsType.getHeight(); i4++) {
            for (int i5 = 0; i5 < icnsType.getWidth(); i5++) {
                if (i2 == 0) {
                    i3 = bArr[i] & UByte.MAX_VALUE;
                    i2 = 8;
                    i++;
                }
                int i6 = (i3 & 128) != 0 ? ViewCompat.MEASURED_STATE_MASK : -1;
                i3 <<= 1;
                i2--;
                imageBuilder.setRGB(i5, i4, i6);
            }
        }
    }

    private static void decode4BPPImage(IcnsType icnsType, byte[] bArr, ImageBuilder imageBuilder) {
        int i;
        int i2 = 0;
        boolean z = false;
        for (int i3 = 0; i3 < icnsType.getHeight(); i3++) {
            for (int i4 = 0; i4 < icnsType.getWidth(); i4++) {
                if (!z) {
                    i = (bArr[i2] >> 4) & 15;
                } else {
                    i = bArr[i2] & 15;
                    i2++;
                }
                z = !z;
                imageBuilder.setRGB(i4, i3, PALETTE_4BPP[i]);
            }
        }
    }

    private static void decode8BPPImage(IcnsType icnsType, byte[] bArr, ImageBuilder imageBuilder) {
        for (int i = 0; i < icnsType.getHeight(); i++) {
            for (int i2 = 0; i2 < icnsType.getWidth(); i2++) {
                imageBuilder.setRGB(i2, i, PALETTE_8BPP[bArr[(icnsType.getWidth() * i) + i2] & UByte.MAX_VALUE]);
            }
        }
    }

    private static void decode32BPPImage(IcnsType icnsType, byte[] bArr, ImageBuilder imageBuilder) {
        for (int i = 0; i < icnsType.getHeight(); i++) {
            for (int i2 = 0; i2 < icnsType.getWidth(); i2++) {
                imageBuilder.setRGB(i2, i, ((bArr[(((icnsType.getWidth() * i) + i2) * 4) + 1] & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[(((icnsType.getWidth() * i) + i2) * 4) + 2] & 255) << 8) | (bArr[(((icnsType.getWidth() * i) + i2) * 4) + 3] & 255));
            }
        }
    }

    private static void apply1BPPMask(byte[] bArr, ImageBuilder imageBuilder) throws ImageReadException {
        int width = ((imageBuilder.getWidth() * imageBuilder.getHeight()) + 7) / 8;
        if (bArr.length < width * 2) {
            throw new ImageReadException("1 BPP mask underrun parsing ICNS file");
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < imageBuilder.getHeight(); i3++) {
            for (int i4 = 0; i4 < imageBuilder.getWidth(); i4++) {
                int i5 = 255;
                if (i == 0) {
                    i2 = bArr[width] & UByte.MAX_VALUE;
                    width++;
                    i = 8;
                }
                if ((i2 & 128) == 0) {
                    i5 = 0;
                }
                i2 <<= 1;
                i--;
                imageBuilder.setRGB(i4, i3, (i5 << 24) | (16777215 & imageBuilder.getRGB(i4, i3)));
            }
        }
    }

    private static void apply8BPPMask(byte[] bArr, ImageBuilder imageBuilder) {
        for (int i = 0; i < imageBuilder.getHeight(); i++) {
            for (int i2 = 0; i2 < imageBuilder.getWidth(); i2++) {
                imageBuilder.setRGB(i2, i, ((bArr[(imageBuilder.getWidth() * i) + i2] & 255) << 24) | (16777215 & imageBuilder.getRGB(i2, i)));
            }
        }
    }

    public static List<BufferedImage> decodeAllImages(IcnsImageParser.IcnsElement[] icnsElementArr) throws ImageReadException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < icnsElementArr.length; i++) {
            BufferedImage decodeImage = decodeImage(icnsElementArr, i);
            if (decodeImage != null) {
                arrayList.add(decodeImage);
            }
        }
        return arrayList;
    }

    public static BufferedImage decodeImage(IcnsImageParser.IcnsElement[] icnsElementArr, int i) throws ImageReadException {
        IcnsImageParser.IcnsElement icnsElement = icnsElementArr[i];
        IcnsType findImageType = IcnsType.findImageType(icnsElement.type);
        BufferedImage bufferedImage = null;
        if (findImageType == null) {
            return null;
        }
        if (findImageType == IcnsType.ICNS_16x16_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_32x32_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_64x64_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_128x128_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_256x256_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_512x512_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_1024x1024_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_32x32_2x_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_64x64_2x_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_256x256_2x_32BIT_ARGB_IMAGE || findImageType == IcnsType.ICNS_512x512_2x_32BIT_ARGB_IMAGE) {
            try {
                return Imaging.getBufferedImage(icnsElement.data);
            } catch (Exception unused) {
                if (findImageType.getWidth() <= 32) {
                    try {
                        bufferedImage = decodeImageImpl(findImageType, icnsElement, icnsElementArr);
                    } catch (Exception unused2) {
                    }
                }
                return bufferedImage == null ? new BufferedImage(findImageType.getWidth(), findImageType.getHeight(), 2) : bufferedImage;
            }
        }
        return decodeImageImpl(findImageType, icnsElement, icnsElementArr);
    }

    private static BufferedImage decodeImageImpl(IcnsType icnsType, IcnsImageParser.IcnsElement icnsElement, IcnsImageParser.IcnsElement[] icnsElementArr) throws ImageReadException {
        byte[] bArr;
        IcnsImageParser.IcnsElement icnsElement2;
        if (icnsElement.data.length < (((icnsType.getWidth() * icnsType.getHeight()) * icnsType.getBitsPerPixel()) + 7) / 8) {
            if (icnsType.getBitsPerPixel() != 32) {
                throw new ImageReadException("Short image data but not a 32 bit compressed type");
            }
            bArr = Rle24Compression.decompress(icnsType.getWidth(), icnsType.getHeight(), icnsElement.data);
        } else {
            bArr = icnsElement.data;
        }
        ImageBuilder imageBuilder = new ImageBuilder(icnsType.getWidth(), icnsType.getHeight(), true);
        int bitsPerPixel = icnsType.getBitsPerPixel();
        if (bitsPerPixel == 1) {
            decode1BPPImage(icnsType, bArr, imageBuilder);
        } else if (bitsPerPixel == 4) {
            decode4BPPImage(icnsType, bArr, imageBuilder);
        } else if (bitsPerPixel == 8) {
            decode8BPPImage(icnsType, bArr, imageBuilder);
        } else if (bitsPerPixel == 32) {
            decode32BPPImage(icnsType, bArr, imageBuilder);
        } else {
            throw new ImageReadException("Unsupported bit depth " + icnsType.getBitsPerPixel());
        }
        if (!icnsType.hasMask()) {
            IcnsType find8BPPMaskType = IcnsType.find8BPPMaskType(icnsType);
            if (find8BPPMaskType != null) {
                for (IcnsImageParser.IcnsElement icnsElement3 : icnsElementArr) {
                    if (icnsElement3.type == find8BPPMaskType.getType()) {
                        icnsElement2 = icnsElement3;
                        break;
                    }
                }
            }
            icnsElement2 = null;
            if (icnsElement2 == null) {
                icnsType = IcnsType.find1BPPMaskType(icnsType);
                if (icnsType != null) {
                    for (IcnsImageParser.IcnsElement icnsElement4 : icnsElementArr) {
                        if (icnsElement4.type == icnsType.getType()) {
                            icnsElement = icnsElement4;
                            break;
                        }
                    }
                }
            } else {
                icnsType = find8BPPMaskType;
            }
            icnsElement = icnsElement2;
        }
        if (icnsElement != null) {
            if (icnsType.getBitsPerPixel() == 1) {
                apply1BPPMask(icnsElement.data, imageBuilder);
            } else if (icnsType.getBitsPerPixel() == 8) {
                apply8BPPMask(icnsElement.data, imageBuilder);
            } else {
                throw new ImageReadException("Unsupported mask bit depth " + icnsType.getBitsPerPixel());
            }
        }
        return imageBuilder.getBufferedImage();
    }
}
