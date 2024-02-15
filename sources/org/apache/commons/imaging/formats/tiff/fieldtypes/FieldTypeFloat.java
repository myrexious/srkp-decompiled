package org.apache.commons.imaging.formats.tiff.fieldtypes;

import java.nio.ByteOrder;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.TiffField;

/* loaded from: classes2.dex */
public class FieldTypeFloat extends FieldType {
    public FieldTypeFloat(int i, String str) {
        super(i, str, 4);
    }

    @Override // org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType
    public Object getValue(TiffField tiffField) {
        byte[] byteArrayValue = tiffField.getByteArrayValue();
        if (tiffField.getCount() == 1) {
            return Float.valueOf(ByteConversions.toFloat(byteArrayValue, tiffField.getByteOrder()));
        }
        return ByteConversions.toFloats(byteArrayValue, tiffField.getByteOrder());
    }

    @Override // org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType
    public byte[] writeData(Object obj, ByteOrder byteOrder) throws ImageWriteException {
        if (obj instanceof Float) {
            return ByteConversions.toBytes(((Float) obj).floatValue(), byteOrder);
        }
        if (obj instanceof float[]) {
            return ByteConversions.toBytes((float[]) obj, byteOrder);
        }
        if (!(obj instanceof Float[])) {
            throw new ImageWriteException("Invalid data", obj);
        }
        Float[] fArr = (Float[]) obj;
        int length = fArr.length;
        float[] fArr2 = new float[length];
        for (int i = 0; i < length; i++) {
            fArr2[i] = fArr[i].floatValue();
        }
        return ByteConversions.toBytes(fArr2, byteOrder);
    }
}
