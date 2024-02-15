package com.fasterxml.jackson.core.io.doubleparser;

import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public final class JavaFloatBitsFromByteArray extends AbstractJavaFloatingPointBitsFromByteArray {
    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long nan() {
        return Float.floatToRawIntBits(Float.NaN);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long negativeInfinity() {
        return Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long positiveInfinity() {
        return Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long valueOfFloatLiteral(byte[] bArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        float decFloatLiteralToFloat = FastFloatMath.decFloatLiteralToFloat(z, j, i3, z2, i4);
        if (Float.isNaN(decFloatLiteralToFloat)) {
            decFloatLiteralToFloat = Float.parseFloat(new String(bArr, i, i2 - i, StandardCharsets.ISO_8859_1));
        }
        return Float.floatToRawIntBits(decFloatLiteralToFloat);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long valueOfHexLiteral(byte[] bArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        float hexFloatLiteralToFloat = FastFloatMath.hexFloatLiteralToFloat(z, j, i3, z2, i4);
        if (Float.isNaN(hexFloatLiteralToFloat)) {
            hexFloatLiteralToFloat = Float.parseFloat(new String(bArr, i, i2 - i, StandardCharsets.ISO_8859_1));
        }
        return Float.floatToRawIntBits(hexFloatLiteralToFloat);
    }
}
