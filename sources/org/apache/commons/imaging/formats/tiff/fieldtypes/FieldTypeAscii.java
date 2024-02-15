package org.apache.commons.imaging.formats.tiff.fieldtypes;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.tiff.TiffField;

/* loaded from: classes2.dex */
public class FieldTypeAscii extends FieldType {
    public FieldTypeAscii(int i, String str) {
        super(i, str, 1);
    }

    @Override // org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType
    public Object getValue(TiffField tiffField) {
        byte[] byteArrayValue = tiffField.getByteArrayValue();
        int i = 1;
        for (int i2 = 0; i2 < byteArrayValue.length - 1; i2++) {
            if (byteArrayValue[i2] == 0) {
                i++;
            }
        }
        String[] strArr = new String[i];
        strArr[0] = "";
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < byteArrayValue.length; i5++) {
            if (byteArrayValue[i5] == 0) {
                strArr[i4] = new String(byteArrayValue, i3, i5 - i3, StandardCharsets.UTF_8);
                i4++;
                i3 = i5 + 1;
            }
        }
        if (i3 < byteArrayValue.length) {
            strArr[i4] = new String(byteArrayValue, i3, byteArrayValue.length - i3, StandardCharsets.UTF_8);
        }
        return i == 1 ? strArr[0] : strArr;
    }

    @Override // org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType
    public byte[] writeData(Object obj, ByteOrder byteOrder) throws ImageWriteException {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length + 1;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr2[length - 1] = 0;
            return bArr2;
        } else if (obj instanceof String) {
            byte[] bytes = ((String) obj).getBytes(StandardCharsets.UTF_8);
            int length2 = bytes.length + 1;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bytes, 0, bArr3, 0, bytes.length);
            bArr3[length2 - 1] = 0;
            return bArr3;
        } else if (!(obj instanceof String[])) {
            throw new ImageWriteException("Unknown data type: " + obj);
        } else {
            String[] strArr = (String[]) obj;
            int i = 0;
            for (String str : strArr) {
                i += str.getBytes(StandardCharsets.UTF_8).length + 1;
            }
            byte[] bArr4 = new byte[i];
            int i2 = 0;
            for (String str2 : strArr) {
                byte[] bytes2 = str2.getBytes(StandardCharsets.UTF_8);
                System.arraycopy(bytes2, 0, bArr4, i2, bytes2.length);
                i2 += bytes2.length + 1;
            }
            return bArr4;
        }
    }
}
