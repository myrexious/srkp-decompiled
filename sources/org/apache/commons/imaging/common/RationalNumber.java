package org.apache.commons.imaging.common;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.text.NumberFormat;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
public class RationalNumber extends Number {
    private static final double TOLERANCE = 1.0E-8d;
    private static final long serialVersionUID = -8412262656468158691L;
    public final long divisor;
    public final long numerator;
    public final boolean unsignedType;

    public RationalNumber(int i, int i2) {
        this.numerator = i;
        this.divisor = i2;
        this.unsignedType = false;
    }

    public RationalNumber(int i, int i2, boolean z) {
        this.unsignedType = z;
        if (z) {
            this.numerator = i & BodyPartID.bodyIdMax;
            this.divisor = i2 & BodyPartID.bodyIdMax;
            return;
        }
        this.numerator = i;
        this.divisor = i2;
    }

    private RationalNumber(long j, long j2, boolean z) {
        this.numerator = j;
        this.divisor = j2;
        this.unsignedType = z;
    }

    static RationalNumber factoryMethod(long j, long j2) {
        if (j > 2147483647L || j < -2147483648L || j2 > 2147483647L || j2 < -2147483648L) {
            while (true) {
                if ((j > 2147483647L || j < -2147483648L || j2 > 2147483647L || j2 < -2147483648L) && Math.abs(j) > 1 && Math.abs(j2) > 1) {
                    j >>= 1;
                    j2 >>= 1;
                }
            }
            if (j2 == 0) {
                throw new NumberFormatException("Invalid value, numerator: " + j + ", divisor: " + j2);
            }
        }
        long gcd = gcd(j, j2);
        return new RationalNumber((int) (j / gcd), (int) (j2 / gcd));
    }

    private static long gcd(long j, long j2) {
        return j2 == 0 ? j : gcd(j2, j % j2);
    }

    public RationalNumber negate() {
        long j = this.numerator;
        long j2 = this.divisor;
        if (this.unsignedType && (j >> 31) == 1) {
            long gcd = gcd(j, j2);
            if (gcd != 0) {
                j /= gcd;
                j2 /= gcd;
            }
            if ((j >> 31) == 1) {
                throw new NumberFormatException("Unsigned numerator is too large to negate " + this.numerator);
            }
        }
        return new RationalNumber(-j, j2, false);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.divisor;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) (this.numerator / this.divisor);
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator / this.divisor;
    }

    public String toString() {
        if (this.divisor == 0) {
            return "Invalid rational (" + this.numerator + RemoteSettings.FORWARD_SLASH_STRING + this.divisor + ")";
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        long j = this.numerator;
        long j2 = this.divisor;
        if (j % j2 == 0) {
            return numberFormat.format(j / j2);
        }
        return this.numerator + RemoteSettings.FORWARD_SLASH_STRING + this.divisor + " (" + numberFormat.format(this.numerator / this.divisor) + ")";
    }

    public String toDisplayString() {
        long j = this.numerator;
        long j2 = this.divisor;
        if (j % j2 == 0) {
            return Long.toString(j / j2);
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(3);
        return numberFormat.format(this.numerator / this.divisor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Option {
        public final double error;
        public final RationalNumber rationalNumber;

        private Option(RationalNumber rationalNumber, double d) {
            this.rationalNumber = rationalNumber;
            this.error = d;
        }

        public static Option factory(RationalNumber rationalNumber, double d) {
            return new Option(rationalNumber, Math.abs(rationalNumber.doubleValue() - d));
        }

        public String toString() {
            return this.rationalNumber.toString();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00e6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.apache.commons.imaging.common.RationalNumber valueOf(double r11) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.common.RationalNumber.valueOf(double):org.apache.commons.imaging.common.RationalNumber");
    }
}
