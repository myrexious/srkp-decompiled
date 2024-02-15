package com.tom_roush.fontbox.cff;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.fontbox.encoding.StandardEncoding;
import com.tom_roush.fontbox.type1.Type1CharStringReader;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.support.ElectionUtil;

/* loaded from: classes3.dex */
public class Type1CharString {
    protected int commandCount;
    private PointF current;
    private final List<PointF> flexPoints;
    private Type1CharStringReader font;
    private final String fontName;
    private final String glyphName;
    private boolean isFlex;
    private PointF leftSideBearing;
    private Path path;
    protected List<Object> type1Sequence;
    private int width;

    public Type1CharString(Type1CharStringReader type1CharStringReader, String str, String str2, List<Object> list) {
        this(type1CharStringReader, str, str2);
        this.type1Sequence = list;
    }

    public Type1CharString(Type1CharStringReader type1CharStringReader, String str, String str2) {
        this.path = null;
        this.width = 0;
        this.leftSideBearing = null;
        this.current = null;
        this.isFlex = false;
        this.flexPoints = new ArrayList();
        this.font = type1CharStringReader;
        this.fontName = str;
        this.glyphName = str2;
        this.current = new PointF(0.0f, 0.0f);
    }

    public String getName() {
        return this.glyphName;
    }

    public RectF getBounds() {
        if (this.path == null) {
            render();
        }
        RectF rectF = new RectF();
        this.path.computeBounds(rectF, true);
        return rectF;
    }

    public int getWidth() {
        if (this.path == null) {
            render();
        }
        return this.width;
    }

    public Path getPath() {
        if (this.path == null) {
            render();
        }
        return this.path;
    }

    public List<Object> getType1Sequence() {
        return this.type1Sequence;
    }

    private void render() {
        this.path = new Path();
        this.leftSideBearing = new PointF(0.0f, 0.0f);
        this.width = 0;
        new CharStringHandler() { // from class: com.tom_roush.fontbox.cff.Type1CharString.1
            @Override // com.tom_roush.fontbox.cff.CharStringHandler
            public List<Number> handleCommand(List<Number> list, CharStringCommand charStringCommand) {
                return Type1CharString.this.handleCommand(list, charStringCommand);
            }
        }.handleSequence(this.type1Sequence);
    }

    public List<Number> handleCommand(List<Number> list, CharStringCommand charStringCommand) {
        this.commandCount++;
        String str = CharStringCommand.TYPE1_VOCABULARY.get(charStringCommand.getKey());
        if ("rmoveto".equals(str)) {
            if (list.size() >= 2) {
                if (this.isFlex) {
                    this.flexPoints.add(new PointF(list.get(0).floatValue(), list.get(1).floatValue()));
                    return null;
                }
                rmoveTo(list.get(0), list.get(1));
                return null;
            }
            return null;
        } else if ("vmoveto".equals(str)) {
            if (list.isEmpty()) {
                return null;
            }
            if (this.isFlex) {
                this.flexPoints.add(new PointF(0.0f, list.get(0).floatValue()));
                return null;
            }
            rmoveTo(0, list.get(0));
            return null;
        } else if ("hmoveto".equals(str)) {
            if (list.isEmpty()) {
                return null;
            }
            if (!this.isFlex) {
                rmoveTo(list.get(0), 0);
                return null;
            }
            this.flexPoints.add(new PointF(list.get(0).floatValue(), 0.0f));
            return null;
        } else if ("rlineto".equals(str)) {
            if (list.size() >= 2) {
                rlineTo(list.get(0), list.get(1));
                return null;
            }
            return null;
        } else if ("hlineto".equals(str)) {
            if (list.isEmpty()) {
                return null;
            }
            rlineTo(list.get(0), 0);
            return null;
        } else if ("vlineto".equals(str)) {
            if (list.isEmpty()) {
                return null;
            }
            rlineTo(0, list.get(0));
            return null;
        } else if ("rrcurveto".equals(str)) {
            if (list.size() >= 6) {
                rrcurveTo(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
                return null;
            }
            return null;
        } else if ("closepath".equals(str)) {
            closeCharString1Path();
            return null;
        } else if ("sbw".equals(str)) {
            if (list.size() >= 3) {
                this.leftSideBearing = new PointF(list.get(0).floatValue(), list.get(1).floatValue());
                this.width = list.get(2).intValue();
                this.current.set(this.leftSideBearing);
                return null;
            }
            return null;
        } else if ("hsbw".equals(str)) {
            if (list.size() >= 2) {
                this.leftSideBearing = new PointF(list.get(0).floatValue(), 0.0f);
                this.width = list.get(1).intValue();
                this.current.set(this.leftSideBearing);
                return null;
            }
            return null;
        } else if ("vhcurveto".equals(str)) {
            if (list.size() >= 4) {
                rrcurveTo(0, list.get(0), list.get(1), list.get(2), list.get(3), 0);
                return null;
            }
            return null;
        } else if ("hvcurveto".equals(str)) {
            if (list.size() >= 4) {
                rrcurveTo(list.get(0), 0, list.get(1), list.get(2), 0, list.get(3));
                return null;
            }
            return null;
        } else if ("seac".equals(str)) {
            if (list.size() >= 5) {
                seac(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
                return null;
            }
            return null;
        } else if ("setcurrentpoint".equals(str)) {
            if (list.size() >= 2) {
                setcurrentpoint(list.get(0), list.get(1));
                return null;
            }
            return null;
        } else if ("callothersubr".equals(str)) {
            if (list.isEmpty()) {
                return null;
            }
            callothersubr(list.get(0).intValue());
            return null;
        } else if ("div".equals(str)) {
            if (list.size() >= 2) {
                float floatValue = list.get(list.size() - 2).floatValue() / list.get(list.size() - 1).floatValue();
                ArrayList arrayList = new ArrayList(list);
                arrayList.remove(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                arrayList.add(Float.valueOf(floatValue));
                return arrayList;
            }
            return null;
        } else if ("hstem".equals(str) || "vstem".equals(str) || "hstem3".equals(str) || "vstem3".equals(str) || "dotsection".equals(str) || "endchar".equals(str)) {
            return null;
        } else {
            if ("return".equals(str) || "callsubr".equals(str)) {
                Log.w("PdfBox-Android", "Unexpected charstring command: " + str + " in glyph " + this.glyphName + " of font " + this.fontName);
                return null;
            } else if (str != null) {
                throw new IllegalArgumentException("Unhandled command: " + str);
            } else {
                Log.w("PdfBox-Android", "Unknown charstring command: " + charStringCommand.getKey() + " in glyph " + this.glyphName + " of font " + this.fontName);
                return null;
            }
        }
    }

    private void setcurrentpoint(Number number, Number number2) {
        this.current.set(number.floatValue(), number2.floatValue());
    }

    private void callothersubr(int i) {
        if (i != 0) {
            if (i == 1) {
                this.isFlex = true;
                return;
            } else {
                Log.w("PdfBox-Android", "Invalid callothersubr parameter: " + i);
                return;
            }
        }
        this.isFlex = false;
        if (this.flexPoints.size() < 7) {
            Log.w("PdfBox-Android", "flex without moveTo in font " + this.fontName + ", glyph " + this.glyphName + ", command " + this.commandCount);
            return;
        }
        PointF pointF = this.flexPoints.get(0);
        pointF.set(this.current.x + pointF.x, this.current.y + pointF.y);
        PointF pointF2 = this.flexPoints.get(1);
        pointF2.set(pointF.x + pointF2.x, pointF.y + pointF2.y);
        pointF2.set(pointF2.x - this.current.x, pointF2.y - this.current.y);
        PointF pointF3 = this.flexPoints.get(1);
        PointF pointF4 = this.flexPoints.get(2);
        PointF pointF5 = this.flexPoints.get(3);
        rrcurveTo(Float.valueOf(pointF3.x), Float.valueOf(pointF3.y), Float.valueOf(pointF4.x), Float.valueOf(pointF4.y), Float.valueOf(pointF5.x), Float.valueOf(pointF5.y));
        PointF pointF6 = this.flexPoints.get(4);
        PointF pointF7 = this.flexPoints.get(5);
        PointF pointF8 = this.flexPoints.get(6);
        rrcurveTo(Float.valueOf(pointF6.x), Float.valueOf(pointF6.y), Float.valueOf(pointF7.x), Float.valueOf(pointF7.y), Float.valueOf(pointF8.x), Float.valueOf(pointF8.y));
        this.flexPoints.clear();
    }

    private void rmoveTo(Number number, Number number2) {
        float floatValue = this.current.x + number.floatValue();
        float floatValue2 = this.current.y + number2.floatValue();
        this.path.moveTo(floatValue, floatValue2);
        this.current.set(floatValue, floatValue2);
    }

    private void rlineTo(Number number, Number number2) {
        float floatValue = this.current.x + number.floatValue();
        float floatValue2 = this.current.y + number2.floatValue();
        if (this.path.isEmpty()) {
            Log.w("PdfBox-Android", "rlineTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
            this.path.moveTo(floatValue, floatValue2);
        } else {
            this.path.lineTo(floatValue, floatValue2);
        }
        this.current.set(floatValue, floatValue2);
    }

    private void rrcurveTo(Number number, Number number2, Number number3, Number number4, Number number5, Number number6) {
        float floatValue = this.current.x + number.floatValue();
        float floatValue2 = this.current.y + number2.floatValue();
        float floatValue3 = floatValue + number3.floatValue();
        float floatValue4 = floatValue2 + number4.floatValue();
        float floatValue5 = number5.floatValue() + floatValue3;
        float floatValue6 = number6.floatValue() + floatValue4;
        if (this.path.isEmpty()) {
            Log.w("PdfBox-Android", "rrcurveTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
            this.path.moveTo(floatValue5, floatValue6);
        } else {
            this.path.cubicTo(floatValue, floatValue2, floatValue3, floatValue4, floatValue5, floatValue6);
        }
        this.current.set(floatValue5, floatValue6);
    }

    private void closeCharString1Path() {
        if (this.path.isEmpty()) {
            Log.w("PdfBox-Android", "closepath without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
        } else {
            this.path.close();
        }
        this.path.moveTo(this.current.x, this.current.y);
    }

    private void seac(Number number, Number number2, Number number3, Number number4, Number number5) {
        String name = StandardEncoding.INSTANCE.getName(number4.intValue());
        try {
            this.path.op(this.font.getType1CharString(name).getPath(), Path.Op.UNION);
        } catch (IOException unused) {
            Log.w("PdfBox-Android", "invalid seac character in glyph " + this.glyphName + " of font " + this.fontName);
        }
        String name2 = StandardEncoding.INSTANCE.getName(number5.intValue());
        try {
            Type1CharString type1CharString = this.font.getType1CharString(name2);
            if (this.path == type1CharString.getPath()) {
                Log.w("PdfBox-Android", "Path for " + name + " and for accent " + name2 + " are same, ignored");
                return;
            }
            AffineTransform.getTranslateInstance((this.leftSideBearing.x + number2.floatValue()) - number.floatValue(), this.leftSideBearing.y + number3.floatValue());
            this.path.op(type1CharString.getPath(), Path.Op.UNION);
        } catch (IOException unused2) {
            Log.w("PdfBox-Android", "invalid seac character in glyph " + this.glyphName + " of font " + this.fontName);
        }
    }

    public String toString() {
        return this.type1Sequence.toString().replace(ElectionUtil.KODE_TPS_SEPARATOR, "\n").replace(",", StringUtils.SPACE);
    }
}
