package org.bouncycastle.pqc.crypto.picnic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;
import org.bouncycastle.util.Exceptions;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes2.dex */
public class LowmcConstants {
    private static final KMatrices KMatrix_L1;
    private static final KMatrices KMatrix_L1_full;
    private static final KMatrices KMatrix_L1_inv;
    private static final KMatrices KMatrix_L3;
    private static final KMatrices KMatrix_L3_full;
    private static final KMatrices KMatrix_L3_inv;
    private static final KMatrices KMatrix_L5;
    private static final KMatrices KMatrix_L5_full;
    private static final KMatrices KMatrix_L5_inv;
    private static final KMatrices LMatrix_L1;
    private static final KMatrices LMatrix_L1_full;
    private static final KMatrices LMatrix_L1_inv;
    private static final KMatrices LMatrix_L3;
    private static final KMatrices LMatrix_L3_full;
    private static final KMatrices LMatrix_L3_inv;
    private static final KMatrices LMatrix_L5;
    private static final KMatrices LMatrix_L5_full;
    private static final KMatrices LMatrix_L5_inv;
    private static final KMatrices RConstants_L1;
    private static final KMatrices RConstants_L1_full;
    private static final KMatrices RConstants_L3;
    private static final KMatrices RConstants_L3_full;
    private static final KMatrices RConstants_L5;
    private static final KMatrices RConstants_L5_full;
    private static final int[] keyMatrices_L1;
    private static final int[] keyMatrices_L1_full;
    private static final int[] keyMatrices_L1_inv;
    private static final int[] keyMatrices_L3;
    private static final int[] keyMatrices_L3_full;
    private static final int[] keyMatrices_L3_inv;
    private static final int[] keyMatrices_L5;
    private static final int[] keyMatrices_L5_full;
    private static final int[] keyMatrices_L5_inv;
    private static final int[] linearMatrices_L1;
    private static final int[] linearMatrices_L1_full;
    private static final int[] linearMatrices_L1_inv;
    private static final int[] linearMatrices_L3;
    private static final int[] linearMatrices_L3_full;
    private static final int[] linearMatrices_L3_inv;
    private static final int[] linearMatrices_L5;
    private static final int[] linearMatrices_L5_full;
    private static final int[] linearMatrices_L5_inv;
    private static final int[] roundConstants_L1;
    private static final int[] roundConstants_L1_full;
    private static final int[] roundConstants_L3;
    private static final int[] roundConstants_L3_full;
    private static final int[] roundConstants_L5;
    private static final int[] roundConstants_L5_full;

    static {
        InputStream resourceAsStream = LowmcConstants.class.getResourceAsStream("lowmc.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            int[] ReadFromProperty = ReadFromProperty(properties, "linearMatrices_L1", 40960);
            linearMatrices_L1 = ReadFromProperty;
            int[] ReadFromProperty2 = ReadFromProperty(properties, "roundConstants_L1", DilithiumEngine.DilithiumPolyT1PackedBytes);
            roundConstants_L1 = ReadFromProperty2;
            int[] ReadFromProperty3 = ReadFromProperty(properties, "keyMatrices_L1", 43008);
            keyMatrices_L1 = ReadFromProperty3;
            LMatrix_L1 = new KMatrices(20, 128, 4, ReadFromProperty);
            KMatrix_L1 = new KMatrices(21, 128, 4, ReadFromProperty3);
            RConstants_L1 = new KMatrices(0, 1, 4, ReadFromProperty2);
            int[] ReadFromProperty4 = ReadFromProperty(properties, "linearMatrices_L1_full", 12800);
            linearMatrices_L1_full = ReadFromProperty4;
            int[] ReadFromProperty5 = ReadFromProperty(properties, "keyMatrices_L1_full", 12900);
            keyMatrices_L1_full = ReadFromProperty5;
            int[] ReadFromProperty6 = ReadFromProperty(properties, "keyMatrices_L1_inv", 2850);
            keyMatrices_L1_inv = ReadFromProperty6;
            int[] ReadFromProperty7 = ReadFromProperty(properties, "linearMatrices_L1_inv", 12800);
            linearMatrices_L1_inv = ReadFromProperty7;
            int[] ReadFromProperty8 = ReadFromProperty(properties, "roundConstants_L1_full", 80);
            roundConstants_L1_full = ReadFromProperty8;
            LMatrix_L1_full = new KMatrices(4, 129, 5, ReadFromProperty4);
            LMatrix_L1_inv = new KMatrices(4, 129, 5, ReadFromProperty7);
            KMatrix_L1_full = new KMatrices(5, 129, 5, ReadFromProperty5);
            KMatrix_L1_inv = new KMatrices(1, 129, 5, ReadFromProperty6);
            RConstants_L1_full = new KMatrices(4, 1, 5, ReadFromProperty8);
            int[] ReadFromProperty9 = ReadFromProperty(properties, "linearMatrices_L3", 138240);
            linearMatrices_L3 = ReadFromProperty9;
            int[] ReadFromProperty10 = ReadFromProperty(properties, "roundConstants_L3", 720);
            roundConstants_L3 = ReadFromProperty10;
            int[] ReadFromProperty11 = ReadFromProperty(properties, "keyMatrices_L3", 142848);
            keyMatrices_L3 = ReadFromProperty11;
            LMatrix_L3 = new KMatrices(30, 192, 6, ReadFromProperty9);
            KMatrix_L3 = new KMatrices(31, 192, 6, ReadFromProperty11);
            RConstants_L3 = new KMatrices(30, 1, 6, ReadFromProperty10);
            int[] ReadFromProperty12 = ReadFromProperty(properties, "linearMatrices_L3_full", 18432);
            linearMatrices_L3_full = ReadFromProperty12;
            int[] ReadFromProperty13 = ReadFromProperty(properties, "linearMatrices_L3_inv", 18432);
            linearMatrices_L3_inv = ReadFromProperty13;
            int[] ReadFromProperty14 = ReadFromProperty(properties, "roundConstants_L3_full", 96);
            roundConstants_L3_full = ReadFromProperty14;
            int[] ReadFromProperty15 = ReadFromProperty(properties, "keyMatrices_L3_full", 23040);
            keyMatrices_L3_full = ReadFromProperty15;
            int[] ReadFromProperty16 = ReadFromProperty(properties, "keyMatrices_L3_inv", 4608);
            keyMatrices_L3_inv = ReadFromProperty16;
            LMatrix_L3_full = new KMatrices(4, 192, 6, ReadFromProperty12);
            LMatrix_L3_inv = new KMatrices(4, 192, 6, ReadFromProperty13);
            KMatrix_L3_full = new KMatrices(5, 192, 6, ReadFromProperty15);
            KMatrix_L3_inv = new KMatrices(1, 192, 6, ReadFromProperty16);
            RConstants_L3_full = new KMatrices(4, 1, 6, ReadFromProperty14);
            int[] ReadFromProperty17 = ReadFromProperty(properties, "linearMatrices_L5", 311296);
            linearMatrices_L5 = ReadFromProperty17;
            int[] ReadFromProperty18 = ReadFromProperty(properties, "roundConstants_L5", 1216);
            roundConstants_L5 = ReadFromProperty18;
            int[] ReadFromProperty19 = ReadFromProperty(properties, "keyMatrices_L5", 319488);
            keyMatrices_L5 = ReadFromProperty19;
            LMatrix_L5 = new KMatrices(38, 256, 8, ReadFromProperty17);
            KMatrix_L5 = new KMatrices(39, 256, 8, ReadFromProperty19);
            RConstants_L5 = new KMatrices(38, 1, 8, ReadFromProperty18);
            int[] ReadFromProperty20 = ReadFromProperty(properties, "linearMatrices_L5_full", 32768);
            linearMatrices_L5_full = ReadFromProperty20;
            int[] ReadFromProperty21 = ReadFromProperty(properties, "linearMatrices_L5_inv", 32768);
            linearMatrices_L5_inv = ReadFromProperty21;
            int[] ReadFromProperty22 = ReadFromProperty(properties, "roundConstants_L5_full", 128);
            roundConstants_L5_full = ReadFromProperty22;
            int[] ReadFromProperty23 = ReadFromProperty(properties, "keyMatrices_L5_full", 40960);
            keyMatrices_L5_full = ReadFromProperty23;
            int[] ReadFromProperty24 = ReadFromProperty(properties, "keyMatrices_L5_inv", 8160);
            keyMatrices_L5_inv = ReadFromProperty24;
            LMatrix_L5_full = new KMatrices(4, 255, 8, ReadFromProperty20);
            LMatrix_L5_inv = new KMatrices(4, 255, 8, ReadFromProperty21);
            KMatrix_L5_full = new KMatrices(5, 255, 8, ReadFromProperty23);
            KMatrix_L5_inv = new KMatrices(1, 255, 8, ReadFromProperty24);
            RConstants_L5_full = new KMatrices(4, 1, 8, ReadFromProperty22);
        } catch (IOException e) {
            throw Exceptions.illegalStateException("unable to load Picnic properties: " + e.getMessage(), e);
        }
    }

    LowmcConstants() {
    }

    private static KMatricesWithPointer GET_MAT(KMatrices kMatrices, int i) {
        KMatricesWithPointer kMatricesWithPointer = new KMatricesWithPointer(kMatrices);
        kMatricesWithPointer.setMatrixPointer(i * kMatricesWithPointer.getSize());
        return kMatricesWithPointer;
    }

    public static KMatricesWithPointer KMatrix(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 128) {
            kMatrices = KMatrix_L1;
        } else if (picnicEngine.stateSizeBits == 129) {
            kMatrices = KMatrix_L1_full;
        } else if (picnicEngine.stateSizeBits == 192) {
            kMatrices = picnicEngine.numRounds == 4 ? KMatrix_L3_full : KMatrix_L3;
        } else if (picnicEngine.stateSizeBits == 255) {
            kMatrices = KMatrix_L5_full;
        } else if (picnicEngine.stateSizeBits != 256) {
            return null;
        } else {
            kMatrices = KMatrix_L5;
        }
        return GET_MAT(kMatrices, i);
    }

    public static KMatricesWithPointer KMatrixInv(PicnicEngine picnicEngine) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 129) {
            kMatrices = KMatrix_L1_inv;
        } else if (picnicEngine.stateSizeBits == 192 && picnicEngine.numRounds == 4) {
            kMatrices = KMatrix_L3_inv;
        } else if (picnicEngine.stateSizeBits != 255) {
            return null;
        } else {
            kMatrices = KMatrix_L5_inv;
        }
        return GET_MAT(kMatrices, 0);
    }

    public static KMatricesWithPointer LMatrix(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 128) {
            kMatrices = LMatrix_L1;
        } else if (picnicEngine.stateSizeBits == 129) {
            kMatrices = LMatrix_L1_full;
        } else if (picnicEngine.stateSizeBits == 192) {
            kMatrices = picnicEngine.numRounds == 4 ? LMatrix_L3_full : LMatrix_L3;
        } else if (picnicEngine.stateSizeBits == 255) {
            kMatrices = LMatrix_L5_full;
        } else if (picnicEngine.stateSizeBits != 256) {
            return null;
        } else {
            kMatrices = LMatrix_L5;
        }
        return GET_MAT(kMatrices, i);
    }

    public static KMatricesWithPointer LMatrixInv(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 129) {
            kMatrices = LMatrix_L1_inv;
        } else if (picnicEngine.stateSizeBits == 192 && picnicEngine.numRounds == 4) {
            kMatrices = LMatrix_L3_inv;
        } else if (picnicEngine.stateSizeBits != 255) {
            return null;
        } else {
            kMatrices = LMatrix_L5_inv;
        }
        return GET_MAT(kMatrices, i);
    }

    public static KMatricesWithPointer RConstant(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 128) {
            kMatrices = RConstants_L1;
        } else if (picnicEngine.stateSizeBits == 129) {
            kMatrices = RConstants_L1_full;
        } else if (picnicEngine.stateSizeBits == 192) {
            kMatrices = picnicEngine.numRounds == 4 ? RConstants_L3_full : RConstants_L3;
        } else if (picnicEngine.stateSizeBits == 255) {
            kMatrices = RConstants_L5_full;
        } else if (picnicEngine.stateSizeBits != 256) {
            return null;
        } else {
            kMatrices = RConstants_L5;
        }
        return GET_MAT(kMatrices, i);
    }

    private static int[] ReadFromProperty(Properties properties, String str, int i) {
        byte[] decode = Hex.decode(removeCommas(properties.getProperty(str)));
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < decode.length / 4; i2++) {
            iArr[i2] = Pack.littleEndianToInt(decode, i2 * 4);
        }
        return iArr;
    }

    private static byte[] removeCommas(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != str.length(); i++) {
            if (str.charAt(i) != ',') {
                byteArrayOutputStream.write(str.charAt(i));
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
