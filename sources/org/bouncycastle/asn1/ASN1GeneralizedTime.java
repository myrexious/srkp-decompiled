package org.bouncycastle.asn1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import net.openid.appauth.AuthState;
import org.apache.commons.lang3.time.TimeZones;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes2.dex */
public class ASN1GeneralizedTime extends ASN1Primitive {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1GeneralizedTime.class, 24) { // from class: org.bouncycastle.asn1.ASN1GeneralizedTime.1
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1GeneralizedTime.createPrimitive(dEROctetString.getOctets());
        }
    };
    final byte[] contents;

    public ASN1GeneralizedTime(String str) {
        this.contents = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", DateUtil.EN_Locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.contents = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.contents = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("GeneralizedTime string too short");
        }
        this.contents = bArr;
        if (!isDigit(0) || !isDigit(1) || !isDigit(2) || !isDigit(3)) {
            throw new IllegalArgumentException("illegal characters in GeneralizedTime string");
        }
    }

    private SimpleDateFormat calculateGMTDateFormat() {
        SimpleDateFormat simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSSz") : hasSeconds() ? new SimpleDateFormat("yyyyMMddHHmmssz") : hasMinutes() ? new SimpleDateFormat("yyyyMMddHHmmz") : new SimpleDateFormat("yyyyMMddHHz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat;
    }

    private String calculateGMTOffset(String str) {
        String str2;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str2 = "-";
        } else {
            str2 = "+";
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / AuthState.EXPIRY_TIME_TOLERANCE_MS;
        try {
            if (timeZone.useDaylightTime()) {
                if (hasFractionalSeconds()) {
                    str = pruneFractionalSeconds(str);
                }
                if (timeZone.inDaylightTime(calculateGMTDateFormat().parse(str + TimeZones.GMT_ID + str2 + convert(i) + ":" + convert(i2)))) {
                    i += str2.equals("+") ? 1 : -1;
                }
            }
        } catch (ParseException unused) {
        }
        return TimeZones.GMT_ID + str2 + convert(i) + ":" + convert(i2);
    }

    private String convert(int i) {
        return i < 10 ? "0" + i : Integer.toString(i);
    }

    public static ASN1GeneralizedTime createPrimitive(byte[] bArr) {
        return new ASN1GeneralizedTime(bArr);
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                return (ASN1GeneralizedTime) aSN1Primitive;
            }
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime) TYPE.fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1GeneralizedTime) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    private boolean isDigit(int i) {
        byte b;
        byte[] bArr = this.contents;
        return bArr.length > i && (b = bArr[i]) >= 48 && b <= 57;
    }

    private String pruneFractionalSeconds(String str) {
        String str2;
        StringBuilder sb;
        char charAt;
        String substring = str.substring(14);
        int i = 1;
        while (i < substring.length() && '0' <= (charAt = substring.charAt(i)) && charAt <= '9') {
            i++;
        }
        int i2 = i - 1;
        if (i2 > 3) {
            str2 = substring.substring(0, 4) + substring.substring(i);
            sb = new StringBuilder();
        } else if (i2 == 1) {
            str2 = substring.substring(0, i) + "00" + substring.substring(i);
            sb = new StringBuilder();
        } else if (i2 != 2) {
            return str;
        } else {
            str2 = substring.substring(0, i) + "0" + substring.substring(i);
            sb = new StringBuilder();
        }
        return sb.append(str.substring(0, 14)).append(str2).toString();
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            return Arrays.areEqual(this.contents, ((ASN1GeneralizedTime) aSN1Primitive).contents);
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 24, this.contents);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean encodeConstructed() {
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat calculateGMTDateFormat;
        String fromByteArray = Strings.fromByteArray(this.contents);
        if (fromByteArray.endsWith("Z")) {
            calculateGMTDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'") : hasSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss'Z'") : hasMinutes() ? new SimpleDateFormat("yyyyMMddHHmm'Z'") : new SimpleDateFormat("yyyyMMddHH'Z'");
            calculateGMTDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (fromByteArray.indexOf(45) > 0 || fromByteArray.indexOf(43) > 0) {
            fromByteArray = getTime();
            calculateGMTDateFormat = calculateGMTDateFormat();
        } else {
            calculateGMTDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS") : hasSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss") : hasMinutes() ? new SimpleDateFormat("yyyyMMddHHmm") : new SimpleDateFormat("yyyyMMddHH");
            calculateGMTDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (hasFractionalSeconds()) {
            fromByteArray = pruneFractionalSeconds(fromByteArray);
        }
        return DateUtil.epochAdjust(calculateGMTDateFormat.parse(fromByteArray));
    }

    public String getTime() {
        StringBuilder append;
        String substring;
        StringBuilder append2;
        StringBuilder append3;
        String str;
        String fromByteArray = Strings.fromByteArray(this.contents);
        if (fromByteArray.charAt(fromByteArray.length() - 1) != 'Z') {
            int length = fromByteArray.length() - 6;
            char charAt = fromByteArray.charAt(length);
            if ((charAt == '-' || charAt == '+') && fromByteArray.indexOf(TimeZones.GMT_ID) == length - 3) {
                return fromByteArray;
            }
            int length2 = fromByteArray.length() - 5;
            char charAt2 = fromByteArray.charAt(length2);
            if (charAt2 == '-' || charAt2 == '+') {
                int i = length2 + 3;
                append = new StringBuilder().append(fromByteArray.substring(0, length2)).append(TimeZones.GMT_ID).append(fromByteArray.substring(length2, i)).append(":");
                substring = fromByteArray.substring(i);
            } else {
                int length3 = fromByteArray.length() - 3;
                char charAt3 = fromByteArray.charAt(length3);
                if (charAt3 == '-' || charAt3 == '+') {
                    append3 = new StringBuilder().append(fromByteArray.substring(0, length3)).append(TimeZones.GMT_ID).append(fromByteArray.substring(length3));
                    str = ":00";
                } else {
                    append = new StringBuilder().append(fromByteArray);
                    substring = calculateGMTOffset(fromByteArray);
                }
            }
            append2 = append.append(substring);
            return append2.toString();
        }
        append3 = new StringBuilder().append(fromByteArray.substring(0, fromByteArray.length() - 1));
        str = "GMT+00:00";
        append2 = append3.append(str);
        return append2.toString();
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.contents);
    }

    public boolean hasFractionalSeconds() {
        int i = 0;
        while (true) {
            byte[] bArr = this.contents;
            if (i == bArr.length) {
                return false;
            }
            if (bArr[i] == 46 && i == 14) {
                return true;
            }
            i++;
        }
    }

    public boolean hasMinutes() {
        return isDigit(10) && isDigit(11);
    }

    public boolean hasSeconds() {
        return isDigit(12) && isDigit(13);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.contents);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERGeneralizedTime(this.contents);
    }
}
