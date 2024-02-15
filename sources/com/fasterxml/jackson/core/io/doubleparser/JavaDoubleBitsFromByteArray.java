package com.fasterxml.jackson.core.io.doubleparser;

import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public final class JavaDoubleBitsFromByteArray extends AbstractJavaFloatingPointBitsFromByteArray {
    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long nan() {
        return Double.doubleToRawLongBits(Double.NaN);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long negativeInfinity() {
        return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long positiveInfinity() {
        return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long valueOfFloatLiteral(byte[] bArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        double tryDecFloatToDoubleTruncated = FastDoubleMath.tryDecFloatToDoubleTruncated(z, j, i3, z2, i4);
        if (Double.isNaN(tryDecFloatToDoubleTruncated)) {
            tryDecFloatToDoubleTruncated = Double.parseDouble(new String(bArr, i, i2 - i, StandardCharsets.ISO_8859_1));
        }
        return Double.doubleToRawLongBits(tryDecFloatToDoubleTruncated);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractJavaFloatingPointBitsFromByteArray
    long valueOfHexLiteral(byte[] bArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        double tryHexFloatToDoubleTruncated = FastDoubleMath.tryHexFloatToDoubleTruncated(z, j, i3, z2, i4);
        if (Double.isNaN(tryHexFloatToDoubleTruncated)) {
            tryHexFloatToDoubleTruncated = Double.parseDouble(new String(bArr, i, i2 - i, StandardCharsets.ISO_8859_1));
        }
        return Double.doubleToRawLongBits(tryHexFloatToDoubleTruncated);
    }
}
