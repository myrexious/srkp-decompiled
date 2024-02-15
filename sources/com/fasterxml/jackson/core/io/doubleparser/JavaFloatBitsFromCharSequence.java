package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes.dex */
public final class JavaFloatBitsFromCharSequence extends AbstractJavaFloatingPointBitsFromCharSequence {
    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromCharSequence
    long nan() {
        return Float.floatToRawIntBits(Float.NaN);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromCharSequence
    long negativeInfinity() {
        return Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromCharSequence
    long positiveInfinity() {
        return Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromCharSequence
    long valueOfFloatLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        float decFloatLiteralToFloat = FastFloatMath.decFloatLiteralToFloat(z, j, i3, z2, i4);
        if (Float.isNaN(decFloatLiteralToFloat)) {
            decFloatLiteralToFloat = Float.parseFloat(charSequence.subSequence(i, i2).toString());
        }
        return Float.floatToRawIntBits(decFloatLiteralToFloat);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromCharSequence
    long valueOfHexLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        float hexFloatLiteralToFloat = FastFloatMath.hexFloatLiteralToFloat(z, j, i3, z2, i4);
        if (Float.isNaN(hexFloatLiteralToFloat)) {
            hexFloatLiteralToFloat = Float.parseFloat(charSequence.subSequence(i, i2).toString());
        }
        return Float.floatToRawIntBits(hexFloatLiteralToFloat);
    }
}
