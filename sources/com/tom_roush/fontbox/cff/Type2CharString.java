package com.tom_roush.fontbox.cff;

import com.tom_roush.fontbox.type1.Type1CharStringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class Type2CharString extends Type1CharString {
    private float defWidthX;
    private final int gid;
    private float nominalWidthX;
    private int pathCount;
    private final List<Object> type2sequence;

    private void expandStemHints(List<Number> list, boolean z) {
    }

    public Type2CharString(Type1CharStringReader type1CharStringReader, String str, String str2, int i, List<Object> list, int i2, int i3) {
        super(type1CharStringReader, str, str2);
        this.pathCount = 0;
        this.gid = i;
        this.type2sequence = list;
        this.defWidthX = i2;
        this.nominalWidthX = i3;
        convertType1ToType2(list);
    }

    public int getGID() {
        return this.gid;
    }

    public List<Object> getType2Sequence() {
        return this.type2sequence;
    }

    private void convertType1ToType2(List<Object> list) {
        this.type1Sequence = new ArrayList();
        this.pathCount = 0;
        new CharStringHandler() { // from class: com.tom_roush.fontbox.cff.Type2CharString.1
            @Override // com.tom_roush.fontbox.cff.CharStringHandler
            public List<Number> handleCommand(List<Number> list2, CharStringCommand charStringCommand) {
                return Type2CharString.this.handleCommand(list2, charStringCommand);
            }
        }.handleSequence(list);
    }

    public List<Number> handleCommand(List<Number> list, CharStringCommand charStringCommand) {
        this.commandCount++;
        String str = CharStringCommand.TYPE2_VOCABULARY.get(charStringCommand.getKey());
        if ("hstem".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), true);
            return null;
        } else if ("vstem".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), false);
            return null;
        } else if ("vmoveto".equals(str)) {
            List<Number> clearStack = clearStack(list, list.size() > 1);
            markPath();
            addCommand(clearStack, charStringCommand);
            return null;
        } else if ("rlineto".equals(str)) {
            addCommandList(split(list, 2), charStringCommand);
            return null;
        } else if ("hlineto".equals(str)) {
            drawAlternatingLine(list, true);
            return null;
        } else if ("vlineto".equals(str)) {
            drawAlternatingLine(list, false);
            return null;
        } else if ("rrcurveto".equals(str)) {
            addCommandList(split(list, 6), charStringCommand);
            return null;
        } else if ("endchar".equals(str)) {
            if (list.size() != 5 && list.size() != 1) {
                r4 = false;
            }
            List<Number> clearStack2 = clearStack(list, r4);
            closeCharString2Path();
            if (clearStack2.size() == 4) {
                clearStack2.add(0, 0);
                addCommand(clearStack2, new CharStringCommand(12, 6));
                return null;
            }
            addCommand(clearStack2, charStringCommand);
            return null;
        } else if ("rmoveto".equals(str)) {
            List<Number> clearStack3 = clearStack(list, list.size() > 2);
            markPath();
            addCommand(clearStack3, charStringCommand);
            return null;
        } else if ("hmoveto".equals(str)) {
            List<Number> clearStack4 = clearStack(list, list.size() > 1);
            markPath();
            addCommand(clearStack4, charStringCommand);
            return null;
        } else if ("vhcurveto".equals(str)) {
            drawAlternatingCurve(list, false);
            return null;
        } else if ("hvcurveto".equals(str)) {
            drawAlternatingCurve(list, true);
            return null;
        } else if ("hflex".equals(str)) {
            if (list.size() >= 7) {
                addCommandList(Arrays.asList(Arrays.asList(list.get(0), 0, list.get(1), list.get(2), list.get(3), 0), Arrays.asList(list.get(4), 0, list.get(5), Float.valueOf(-list.get(2).floatValue()), list.get(6), 0)), new CharStringCommand(8));
                return null;
            }
            return null;
        } else if ("flex".equals(str)) {
            addCommandList(Arrays.asList(list.subList(0, 6), list.subList(6, 12)), new CharStringCommand(8));
            return null;
        } else if ("hflex1".equals(str)) {
            if (list.size() >= 9) {
                addCommandList(Arrays.asList(Arrays.asList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), 0), Arrays.asList(list.get(5), 0, list.get(6), list.get(7), list.get(8), 0)), new CharStringCommand(8));
                return null;
            }
            return null;
        } else if ("flex1".equals(str)) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                i += list.get(i4).intValue();
                i2 += list.get(i4 + 1).intValue();
            }
            List<Number> subList = list.subList(0, 6);
            Number[] numberArr = new Number[6];
            numberArr[0] = list.get(6);
            numberArr[1] = list.get(7);
            numberArr[2] = list.get(8);
            numberArr[3] = list.get(9);
            numberArr[4] = Math.abs(i) > Math.abs(i2) ? list.get(10) : Integer.valueOf(-i);
            numberArr[5] = Math.abs(i) > Math.abs(i2) ? Integer.valueOf(-i2) : list.get(10);
            addCommandList(Arrays.asList(subList, Arrays.asList(numberArr)), new CharStringCommand(8));
            return null;
        } else if ("hstemhm".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), true);
            return null;
        } else if ("hintmask".equals(str) || "cntrmask".equals(str)) {
            List<Number> clearStack5 = clearStack(list, list.size() % 2 != 0);
            if (clearStack5.isEmpty()) {
                return null;
            }
            expandStemHints(clearStack5, false);
            return null;
        } else if ("vstemhm".equals(str)) {
            expandStemHints(clearStack(list, list.size() % 2 != 0), false);
            return null;
        } else if ("rcurveline".equals(str)) {
            if (list.size() >= 2) {
                addCommandList(split(list.subList(0, list.size() - 2), 6), new CharStringCommand(8));
                addCommand(list.subList(list.size() - 2, list.size()), new CharStringCommand(5));
                return null;
            }
            return null;
        } else if ("rlinecurve".equals(str)) {
            if (list.size() >= 6) {
                addCommandList(split(list.subList(0, list.size() - 6), 2), new CharStringCommand(5));
                addCommand(list.subList(list.size() - 6, list.size()), new CharStringCommand(8));
                return null;
            }
            return null;
        } else if ("vvcurveto".equals(str)) {
            drawCurve(list, false);
            return null;
        } else if ("hhcurveto".equals(str)) {
            drawCurve(list, true);
            return null;
        } else {
            addCommand(list, charStringCommand);
            return null;
        }
    }

    private List<Number> clearStack(List<Number> list, boolean z) {
        if (this.type1Sequence.isEmpty()) {
            if (z) {
                addCommand(Arrays.asList(Float.valueOf(0.0f), Float.valueOf(list.get(0).floatValue() + this.nominalWidthX)), new CharStringCommand(13));
                return list.subList(1, list.size());
            }
            addCommand(Arrays.asList(Float.valueOf(0.0f), Float.valueOf(this.defWidthX)), new CharStringCommand(13));
            return list;
        }
        return list;
    }

    private void markPath() {
        if (this.pathCount > 0) {
            closeCharString2Path();
        }
        this.pathCount++;
    }

    private void closeCharString2Path() {
        CharStringCommand charStringCommand = this.pathCount > 0 ? (CharStringCommand) this.type1Sequence.get(this.type1Sequence.size() - 1) : null;
        CharStringCommand charStringCommand2 = new CharStringCommand(9);
        if (charStringCommand == null || charStringCommand2.equals(charStringCommand)) {
            return;
        }
        addCommand(Collections.emptyList(), charStringCommand2);
    }

    private void drawAlternatingLine(List<Number> list, boolean z) {
        while (!list.isEmpty()) {
            addCommand(list.subList(0, 1), new CharStringCommand(z ? 6 : 7));
            list = list.subList(1, list.size());
            z = !z;
        }
    }

    private void drawAlternatingCurve(List<Number> list, boolean z) {
        while (true) {
            int i = 4;
            if (list.size() < 4) {
                return;
            }
            boolean z2 = list.size() == 5;
            if (z) {
                Number[] numberArr = new Number[6];
                numberArr[0] = list.get(0);
                numberArr[1] = 0;
                numberArr[2] = list.get(1);
                numberArr[3] = list.get(2);
                numberArr[4] = z2 ? list.get(4) : 0;
                numberArr[5] = list.get(3);
                addCommand(Arrays.asList(numberArr), new CharStringCommand(8));
            } else {
                Number[] numberArr2 = new Number[6];
                numberArr2[0] = 0;
                numberArr2[1] = list.get(0);
                numberArr2[2] = list.get(1);
                numberArr2[3] = list.get(2);
                numberArr2[4] = list.get(3);
                numberArr2[5] = z2 ? list.get(4) : 0;
                addCommand(Arrays.asList(numberArr2), new CharStringCommand(8));
            }
            if (z2) {
                i = 5;
            }
            list = list.subList(i, list.size());
            z = !z;
        }
    }

    private void drawCurve(List<Number> list, boolean z) {
        while (true) {
            int i = 4;
            if (list.size() < 4) {
                return;
            }
            int i2 = list.size() % 4 == 1 ? 1 : 0;
            if (z) {
                Number[] numberArr = new Number[6];
                numberArr[0] = list.get(i2);
                numberArr[1] = i2 != 0 ? list.get(0) : 0;
                numberArr[2] = list.get(i2 != 0 ? 2 : 1);
                numberArr[3] = list.get(i2 != 0 ? 3 : 2);
                numberArr[4] = list.get(i2 != 0 ? 4 : 3);
                numberArr[5] = 0;
                addCommand(Arrays.asList(numberArr), new CharStringCommand(8));
            } else {
                Number[] numberArr2 = new Number[6];
                numberArr2[0] = i2 != 0 ? list.get(0) : 0;
                numberArr2[1] = list.get(i2);
                numberArr2[2] = list.get(i2 != 0 ? 2 : 1);
                numberArr2[3] = list.get(i2 != 0 ? 3 : 2);
                numberArr2[4] = 0;
                numberArr2[5] = list.get(i2 != 0 ? 4 : 3);
                addCommand(Arrays.asList(numberArr2), new CharStringCommand(8));
            }
            if (i2 != 0) {
                i = 5;
            }
            list = list.subList(i, list.size());
        }
    }

    private void addCommandList(List<List<Number>> list, CharStringCommand charStringCommand) {
        for (List<Number> list2 : list) {
            addCommand(list2, charStringCommand);
        }
    }

    private void addCommand(List<Number> list, CharStringCommand charStringCommand) {
        this.type1Sequence.addAll(list);
        this.type1Sequence.add(charStringCommand);
    }

    private static <E> List<List<E>> split(List<E> list, int i) {
        int size = list.size() / i;
        ArrayList arrayList = new ArrayList(size);
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 * i;
            i2++;
            arrayList.add(list.subList(i3, i2 * i));
        }
        return arrayList;
    }
}
