package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public class COSFloat extends COSNumber {
    private BigDecimal value;
    private String valueAsString;

    public COSFloat(float f) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(f));
        this.value = bigDecimal;
        this.valueAsString = removeNullDigits(bigDecimal.toPlainString());
    }

    public COSFloat(String str) throws IOException {
        try {
            this.valueAsString = str;
            this.value = new BigDecimal(this.valueAsString);
            checkMinMaxValues();
        } catch (NumberFormatException e) {
            if (str.startsWith("--")) {
                this.valueAsString = str.substring(1);
            } else if (str.matches("^0\\.0*\\-\\d+")) {
                this.valueAsString = "-" + this.valueAsString.replaceFirst("\\-", "");
            } else {
                throw new IOException("Error expected floating point number actual='" + str + OperatorName.SHOW_TEXT_LINE, e);
            }
            try {
                this.value = new BigDecimal(this.valueAsString);
                checkMinMaxValues();
            } catch (NumberFormatException e2) {
                throw new IOException("Error expected floating point number actual='" + str + OperatorName.SHOW_TEXT_LINE, e2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x002d, code lost:
        if (java.lang.Math.abs(r1) < 1.1754943508222875E-38d) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkMinMaxValues() {
        /*
            r8 = this;
            java.math.BigDecimal r0 = r8.value
            float r0 = r0.floatValue()
            java.math.BigDecimal r1 = r8.value
            double r1 = r1.doubleValue()
            r3 = -8388608(0xffffffffff800000, float:-Infinity)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r4 = 2139095040(0x7f800000, float:Infinity)
            r5 = 1
            if (r3 == 0) goto L30
            int r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r3 != 0) goto L1a
            goto L30
        L1a:
            r3 = 0
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 != 0) goto L4a
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L4a
            double r1 = java.lang.Math.abs(r1)
            r3 = 4039728865751334912(0x3810000000000000, double:1.1754943508222875E-38)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L4a
            goto L4b
        L30:
            double r1 = java.lang.Math.abs(r1)
            r6 = 5183643170566569984(0x47efffffe0000000, double:3.4028234663852886E38)
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 <= 0) goto L4a
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto L43
            r0 = r5
            goto L44
        L43:
            r0 = -1
        L44:
            float r0 = (float) r0
            r1 = 2139095039(0x7f7fffff, float:3.4028235E38)
            float r0 = r0 * r1
            goto L4b
        L4a:
            r5 = 0
        L4b:
            if (r5 == 0) goto L5e
            double r0 = (double) r0
            java.math.BigDecimal r0 = java.math.BigDecimal.valueOf(r0)
            r8.value = r0
            java.lang.String r0 = r0.toPlainString()
            java.lang.String r0 = r8.removeNullDigits(r0)
            r8.valueAsString = r0
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.cos.COSFloat.checkMinMaxValues():void");
    }

    private String removeNullDigits(String str) {
        if (str.indexOf(46) > -1 && !str.endsWith(".0")) {
            while (str.endsWith("0") && !str.endsWith(".0")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public float floatValue() {
        return this.value.floatValue();
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public long longValue() {
        return this.value.longValue();
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public int intValue() {
        return this.value.intValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSFloat) && Float.floatToIntBits(((COSFloat) obj).value.floatValue()) == Float.floatToIntBits(this.value.floatValue());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "COSFloat{" + this.valueAsString + StringSubstitutor.DEFAULT_VAR_END;
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromFloat(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(this.valueAsString.getBytes("ISO-8859-1"));
    }
}
