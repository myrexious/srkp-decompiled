package com.tom_roush.pdfbox.cos;

import android.util.Log;
import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Hex;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public final class COSString extends COSBase {
    public static final boolean FORCE_PARSING = Boolean.getBoolean("com.tom_roush.pdfbox.forceParsing");
    private byte[] bytes;
    private boolean forceHexForm;

    public COSString(byte[] bArr) {
        setValue(bArr);
    }

    public COSString(String str) {
        boolean z;
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (!PDFDocEncoding.containsChar(charArray[i])) {
                z = false;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.bytes = PDFDocEncoding.getBytes(str);
            return;
        }
        byte[] bytes = str.getBytes(Charsets.UTF_16BE);
        byte[] bArr = new byte[bytes.length + 2];
        this.bytes = bArr;
        bArr[0] = -2;
        bArr[1] = -1;
        System.arraycopy(bytes, 0, bArr, 2, bytes.length);
    }

    public static COSString parseHex(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder(str.trim());
        if (sb.length() % 2 != 0) {
            sb.append('0');
        }
        int length = sb.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            try {
                byteArrayOutputStream.write(Integer.parseInt(sb.substring(i, i2), 16));
            } catch (NumberFormatException e) {
                if (FORCE_PARSING) {
                    Log.w("PdfBox-Android", "Encountered a malformed hex string");
                    byteArrayOutputStream.write(63);
                } else {
                    throw new IOException("Invalid hex string: " + str, e);
                }
            }
            i = i2;
        }
        return new COSString(byteArrayOutputStream.toByteArray());
    }

    public void setValue(byte[] bArr) {
        this.bytes = (byte[]) bArr.clone();
    }

    public void setForceHexForm(boolean z) {
        this.forceHexForm = z;
    }

    public boolean getForceHexForm() {
        return this.forceHexForm;
    }

    public String getString() {
        byte[] bArr = this.bytes;
        if (bArr.length >= 2) {
            byte b = bArr[0];
            if ((b & UByte.MAX_VALUE) == 254 && (bArr[1] & UByte.MAX_VALUE) == 255) {
                byte[] bArr2 = this.bytes;
                return new String(bArr2, 2, bArr2.length - 2, Charsets.UTF_16BE);
            } else if ((b & UByte.MAX_VALUE) == 255 && (bArr[1] & UByte.MAX_VALUE) == 254) {
                byte[] bArr3 = this.bytes;
                return new String(bArr3, 2, bArr3.length - 2, Charsets.UTF_16LE);
            }
        }
        return PDFDocEncoding.toString(bArr);
    }

    public String getASCII() {
        return new String(this.bytes, Charsets.US_ASCII);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String toHexString() {
        return Hex.getString(this.bytes);
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromString(this);
    }

    public boolean equals(Object obj) {
        if (obj instanceof COSString) {
            COSString cOSString = (COSString) obj;
            return getString().equals(cOSString.getString()) && this.forceHexForm == cOSString.forceHexForm;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes) + (this.forceHexForm ? 17 : 0);
    }

    public String toString() {
        return "COSString{" + getString() + StringSubstitutor.DEFAULT_VAR_END;
    }
}
