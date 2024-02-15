package com.tom_roush.fontbox.cff;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class Type1CharStringParser {
    static final int CALLOTHERSUBR = 16;
    static final int CALLSUBR = 10;
    static final int POP = 17;
    static final int RETURN = 11;
    static final int TWO_BYTE = 12;
    private final String fontName;
    private final String glyphName;

    public Type1CharStringParser(String str, String str2) {
        this.fontName = str;
        this.glyphName = str2;
    }

    public List<Object> parse(byte[] bArr, List<byte[]> list) throws IOException {
        return parse(bArr, list, new ArrayList());
    }

    private List<Object> parse(byte[] bArr, List<byte[]> list, List<Object> list2) throws IOException {
        DataInput dataInput = new DataInput(bArr);
        while (dataInput.hasRemaining()) {
            int readUnsignedByte = dataInput.readUnsignedByte();
            if (readUnsignedByte == 10) {
                Object remove = list2.remove(list2.size() - 1);
                if (!(remove instanceof Integer)) {
                    Log.w("PdfBox-Android", "Parameter " + remove + " for CALLSUBR is ignored, integer expected in glyph '" + this.glyphName + "' of font " + this.fontName);
                } else {
                    Integer num = (Integer) remove;
                    if (num.intValue() >= 0 && num.intValue() < list.size()) {
                        parse(list.get(num.intValue()), list, list2);
                        Object obj = list2.get(list2.size() - 1);
                        if ((obj instanceof CharStringCommand) && ((CharStringCommand) obj).getKey().getValue()[0] == 11) {
                            list2.remove(list2.size() - 1);
                        }
                    } else {
                        Log.w("PdfBox-Android", "CALLSUBR is ignored, operand: " + num + ", subrs.size(): " + list.size() + " in glyph '" + this.glyphName + "' of font " + this.fontName);
                        while (list2.get(list2.size() - 1) instanceof Integer) {
                            list2.remove(list2.size() - 1);
                        }
                    }
                }
            } else if (readUnsignedByte == 12 && dataInput.peekUnsignedByte(0) == 16) {
                dataInput.readByte();
                Integer num2 = (Integer) list2.remove(list2.size() - 1);
                ArrayDeque arrayDeque = new ArrayDeque();
                int intValue = ((Integer) list2.remove(list2.size() - 1)).intValue();
                if (intValue == 0) {
                    arrayDeque.push(removeInteger(list2));
                    arrayDeque.push(removeInteger(list2));
                    list2.remove(list2.size() - 1);
                    list2.add(0);
                    list2.add(new CharStringCommand(12, 16));
                } else if (intValue == 1) {
                    list2.add(1);
                    list2.add(new CharStringCommand(12, 16));
                } else if (intValue == 3) {
                    arrayDeque.push(removeInteger(list2));
                } else {
                    for (int i = 0; i < num2.intValue(); i++) {
                        arrayDeque.push(removeInteger(list2));
                    }
                }
                while (dataInput.peekUnsignedByte(0) == 12 && dataInput.peekUnsignedByte(1) == 17) {
                    dataInput.readByte();
                    dataInput.readByte();
                    list2.add(arrayDeque.pop());
                }
                if (arrayDeque.size() > 0) {
                    Log.w("PdfBox-Android", "Value left on the PostScript stack in glyph " + this.glyphName + " of font " + this.fontName);
                }
            } else if (readUnsignedByte >= 0 && readUnsignedByte <= 31) {
                list2.add(readCommand(dataInput, readUnsignedByte));
            } else if (readUnsignedByte >= 32 && readUnsignedByte <= 255) {
                list2.add(readNumber(dataInput, readUnsignedByte));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return list2;
    }

    private static Integer removeInteger(List<Object> list) throws IOException {
        Object remove = list.remove(list.size() - 1);
        if (remove instanceof Integer) {
            return (Integer) remove;
        }
        CharStringCommand charStringCommand = (CharStringCommand) remove;
        if (charStringCommand.getKey().getValue()[0] == 12 && charStringCommand.getKey().getValue()[1] == 12) {
            return Integer.valueOf(((Integer) list.remove(list.size() - 1)).intValue() / ((Integer) list.remove(list.size() - 1)).intValue());
        }
        throw new IOException("Unexpected char string command: " + charStringCommand.getKey());
    }

    private CharStringCommand readCommand(DataInput dataInput, int i) throws IOException {
        if (i == 12) {
            return new CharStringCommand(i, dataInput.readUnsignedByte());
        }
        return new CharStringCommand(i);
    }

    private Integer readNumber(DataInput dataInput, int i) throws IOException {
        if (i < 32 || i > 246) {
            if (i >= 247 && i <= 250) {
                return Integer.valueOf(((i - 247) * 256) + dataInput.readUnsignedByte() + 108);
            } else if (i >= 251 && i <= 254) {
                return Integer.valueOf((((-(i - 251)) * 256) - dataInput.readUnsignedByte()) - 108);
            } else if (i == 255) {
                return Integer.valueOf(dataInput.readInt());
            } else {
                throw new IllegalArgumentException();
            }
        }
        return Integer.valueOf(i - 139);
    }
}
