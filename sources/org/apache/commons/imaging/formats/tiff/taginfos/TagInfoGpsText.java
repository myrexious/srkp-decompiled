package org.apache.commons.imaging.formats.tiff.taginfos;

import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.internal.Debug;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public final class TagInfoGpsText extends TagInfo {
    private static final TextEncoding[] TEXT_ENCODINGS;
    private static final TextEncoding TEXT_ENCODING_ASCII;
    private static final TextEncoding TEXT_ENCODING_JIS;
    private static final TextEncoding TEXT_ENCODING_UNDEFINED;
    private static final TextEncoding TEXT_ENCODING_UNICODE_BE;
    private static final TextEncoding TEXT_ENCODING_UNICODE_LE;

    @Override // org.apache.commons.imaging.formats.tiff.taginfos.TagInfo
    public boolean isText() {
        return true;
    }

    static {
        TextEncoding textEncoding = new TextEncoding(new byte[]{BuiltinOptions.FloorDivOptions, BuiltinOptions.GatherNdOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.RangeOptions, BuiltinOptions.RangeOptions, 0, 0, 0}, "US-ASCII");
        TEXT_ENCODING_ASCII = textEncoding;
        TextEncoding textEncoding2 = new TextEncoding(new byte[]{BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.RangeOptions, BuiltinOptions.GatherNdOptions, 0, 0, 0, 0, 0}, "JIS");
        TEXT_ENCODING_JIS = textEncoding2;
        TextEncoding textEncoding3 = new TextEncoding(new byte[]{BuiltinOptions.WhereOptions, BuiltinOptions.AbsOptions, BuiltinOptions.RangeOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.FillOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, 0}, "UTF-16LE");
        TEXT_ENCODING_UNICODE_LE = textEncoding3;
        TextEncoding textEncoding4 = new TextEncoding(new byte[]{BuiltinOptions.WhereOptions, BuiltinOptions.AbsOptions, BuiltinOptions.RangeOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.FillOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, 0}, "UTF-16BE");
        TEXT_ENCODING_UNICODE_BE = textEncoding4;
        TextEncoding textEncoding5 = new TextEncoding(new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, "ISO-8859-1");
        TEXT_ENCODING_UNDEFINED = textEncoding5;
        TEXT_ENCODINGS = new TextEncoding[]{textEncoding, textEncoding2, textEncoding3, textEncoding4, textEncoding5};
    }

    public TagInfoGpsText(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.UNDEFINED, -1, tiffDirectoryType);
    }

    /* loaded from: classes2.dex */
    public static final class TextEncoding {
        public final String encodingName;
        final byte[] prefix;

        TextEncoding(byte[] bArr, String str) {
            this.prefix = bArr;
            this.encodingName = str;
        }
    }

    @Override // org.apache.commons.imaging.formats.tiff.taginfos.TagInfo
    public byte[] encodeValue(FieldType fieldType, Object obj, ByteOrder byteOrder) throws ImageWriteException {
        TextEncoding textEncoding;
        if (!(obj instanceof String)) {
            throw new ImageWriteException("GPS text value not String", obj);
        }
        String str = (String) obj;
        try {
            TextEncoding textEncoding2 = TEXT_ENCODING_ASCII;
            byte[] bytes = str.getBytes(textEncoding2.encodingName);
            if (new String(bytes, textEncoding2.encodingName).equals(str)) {
                byte[] bArr = new byte[bytes.length + textEncoding2.prefix.length];
                System.arraycopy(textEncoding2.prefix, 0, bArr, 0, textEncoding2.prefix.length);
                System.arraycopy(bytes, 0, bArr, textEncoding2.prefix.length, bytes.length);
                return bArr;
            }
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                textEncoding = TEXT_ENCODING_UNICODE_BE;
            } else {
                textEncoding = TEXT_ENCODING_UNICODE_LE;
            }
            byte[] bytes2 = str.getBytes(textEncoding.encodingName);
            byte[] bArr2 = new byte[bytes2.length + textEncoding.prefix.length];
            System.arraycopy(textEncoding.prefix, 0, bArr2, 0, textEncoding.prefix.length);
            System.arraycopy(bytes2, 0, bArr2, textEncoding.prefix.length, bytes2.length);
            return bArr2;
        } catch (UnsupportedEncodingException e) {
            throw new ImageWriteException(e.getMessage(), (Throwable) e);
        }
    }

    @Override // org.apache.commons.imaging.formats.tiff.taginfos.TagInfo
    public String getValue(TiffField tiffField) throws ImageReadException {
        TextEncoding[] textEncodingArr;
        if (tiffField.getFieldType() == FieldType.ASCII) {
            Object value = FieldType.ASCII.getValue(tiffField);
            if (value instanceof String) {
                return (String) value;
            }
            if (value instanceof String[]) {
                return ((String[]) value)[0];
            }
            throw new ImageReadException("Unexpected ASCII type decoded");
        } else if (tiffField.getFieldType() != FieldType.UNDEFINED && tiffField.getFieldType() != FieldType.BYTE) {
            Debug.debug("entry.type: " + tiffField.getFieldType());
            Debug.debug("entry.directoryType: " + tiffField.getDirectoryType());
            Debug.debug("entry.type: " + tiffField.getDescriptionWithoutValue());
            Debug.debug("entry.type: " + tiffField.getFieldType());
            throw new ImageReadException("GPS text field not encoded as bytes.");
        } else {
            byte[] byteArrayValue = tiffField.getByteArrayValue();
            if (byteArrayValue.length < 8) {
                return new String(byteArrayValue, StandardCharsets.US_ASCII);
            }
            for (TextEncoding textEncoding : TEXT_ENCODINGS) {
                if (BinaryFunctions.compareBytes(byteArrayValue, 0, textEncoding.prefix, 0, textEncoding.prefix.length)) {
                    try {
                        String str = new String(byteArrayValue, textEncoding.prefix.length, byteArrayValue.length - textEncoding.prefix.length, textEncoding.encodingName);
                        byte[] bytes = str.getBytes(textEncoding.encodingName);
                        if (BinaryFunctions.compareBytes(byteArrayValue, textEncoding.prefix.length, bytes, 0, bytes.length)) {
                            return str;
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new ImageReadException(e.getMessage(), e);
                    }
                }
            }
            return new String(byteArrayValue, StandardCharsets.US_ASCII);
        }
    }
}
