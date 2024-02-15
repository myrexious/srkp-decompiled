package com.tom_roush.pdfbox.text;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.util.Matrix;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class TextPosition {
    private static final Map<Integer, String> DIACRITICS = createDiacritics();
    private final int[] charCodes;
    private float direction = -1.0f;
    private final float endX;
    private final float endY;
    private final PDFont font;
    private final float fontSize;
    private final int fontSizePt;
    private final float maxHeight;
    private final float pageHeight;
    private final float pageWidth;
    private final int rotation;
    private final Matrix textMatrix;
    private String unicode;
    private final float widthOfSpace;
    private float[] widths;
    private final float x;
    private final float y;

    public TextPosition(int i, float f, float f2, Matrix matrix, float f3, float f4, float f5, float f6, float f7, String str, int[] iArr, PDFont pDFont, float f8, int i2) {
        this.textMatrix = matrix;
        this.endX = f3;
        this.endY = f4;
        this.rotation = i;
        this.maxHeight = f5;
        this.pageHeight = f2;
        this.pageWidth = f;
        this.widths = new float[]{f6};
        this.widthOfSpace = f7;
        this.unicode = str;
        this.charCodes = iArr;
        this.font = pDFont;
        this.fontSize = f8;
        this.fontSizePt = i2;
        this.x = getXRot(i);
        if (i == 0 || i == 180) {
            this.y = f2 - getYLowerLeftRot(i);
        } else {
            this.y = f - getYLowerLeftRot(i);
        }
    }

    private static Map<Integer, String> createDiacritics() {
        HashMap hashMap = new HashMap(31);
        hashMap.put(96, "̀");
        hashMap.put(715, "̀");
        hashMap.put(39, "́");
        hashMap.put(697, "́");
        hashMap.put(714, "́");
        hashMap.put(94, "̂");
        hashMap.put(710, "̂");
        hashMap.put(126, "̃");
        hashMap.put(713, "̄");
        hashMap.put(176, "̊");
        hashMap.put(698, "̋");
        hashMap.put(711, "̌");
        hashMap.put(712, "̍");
        hashMap.put(34, "̎");
        hashMap.put(699, "̒");
        hashMap.put(700, "̓");
        hashMap.put(1158, "̓");
        hashMap.put(1370, "̓");
        hashMap.put(Integer.valueOf((int) TypedValues.TransitionType.TYPE_FROM), "̔");
        hashMap.put(1157, "̔");
        hashMap.put(1369, "̔");
        hashMap.put(724, "̝");
        hashMap.put(725, "̞");
        hashMap.put(726, "̟");
        hashMap.put(727, "̠");
        hashMap.put(690, "̡");
        hashMap.put(716, "̩");
        hashMap.put(695, "̫");
        hashMap.put(717, "̱");
        hashMap.put(95, "̲");
        hashMap.put(8270, "͙");
        return hashMap;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public int[] getCharacterCodes() {
        return this.charCodes;
    }

    public Matrix getTextMatrix() {
        return this.textMatrix;
    }

    public float getDir() {
        if (this.direction < 0.0f) {
            float scaleY = this.textMatrix.getScaleY();
            float shearY = this.textMatrix.getShearY();
            float shearX = this.textMatrix.getShearX();
            float scaleX = this.textMatrix.getScaleX();
            if (scaleY > 0.0f && Math.abs(shearY) < scaleX && Math.abs(shearX) < scaleY && scaleX > 0.0f) {
                this.direction = 0.0f;
            } else if (scaleY < 0.0f && Math.abs(shearY) < Math.abs(scaleX) && Math.abs(shearX) < Math.abs(scaleY) && scaleX < 0.0f) {
                this.direction = 180.0f;
            } else if (Math.abs(scaleY) < Math.abs(shearX) && shearY > 0.0f && shearX < 0.0f && Math.abs(scaleX) < shearY) {
                this.direction = 90.0f;
            } else if (Math.abs(scaleY) < shearX && shearY < 0.0f && shearX > 0.0f && Math.abs(scaleX) < Math.abs(shearY)) {
                this.direction = 270.0f;
            } else {
                this.direction = 0.0f;
            }
        }
        return this.direction;
    }

    private float getXRot(float f) {
        if (f == 0.0f) {
            return this.textMatrix.getTranslateX();
        }
        if (f == 90.0f) {
            return this.textMatrix.getTranslateY();
        }
        if (f == 180.0f) {
            return this.pageWidth - this.textMatrix.getTranslateX();
        }
        if (f == 270.0f) {
            return this.pageHeight - this.textMatrix.getTranslateY();
        }
        return 0.0f;
    }

    public float getX() {
        return this.x;
    }

    public float getXDirAdj() {
        return getXRot(getDir());
    }

    private float getYLowerLeftRot(float f) {
        if (f == 0.0f) {
            return this.textMatrix.getTranslateY();
        }
        if (f == 90.0f) {
            return this.pageWidth - this.textMatrix.getTranslateX();
        }
        if (f == 180.0f) {
            return this.pageHeight - this.textMatrix.getTranslateY();
        }
        if (f == 270.0f) {
            return this.textMatrix.getTranslateX();
        }
        return 0.0f;
    }

    public float getY() {
        return this.y;
    }

    public float getYDirAdj() {
        float f;
        float yLowerLeftRot;
        float dir = getDir();
        if (dir == 0.0f || dir == 180.0f) {
            f = this.pageHeight;
            yLowerLeftRot = getYLowerLeftRot(dir);
        } else {
            f = this.pageWidth;
            yLowerLeftRot = getYLowerLeftRot(dir);
        }
        return f - yLowerLeftRot;
    }

    private float getWidthRot(float f) {
        if (f == 90.0f || f == 270.0f) {
            return Math.abs(this.endY - this.textMatrix.getTranslateY());
        }
        return Math.abs(this.endX - this.textMatrix.getTranslateX());
    }

    public float getWidth() {
        return getWidthRot(this.rotation);
    }

    public float getWidthDirAdj() {
        return getWidthRot(getDir());
    }

    public float getHeight() {
        return this.maxHeight;
    }

    public float getHeightDir() {
        return this.maxHeight;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public float getFontSizeInPt() {
        return this.fontSizePt;
    }

    public PDFont getFont() {
        return this.font;
    }

    public float getWidthOfSpace() {
        return this.widthOfSpace;
    }

    public float getXScale() {
        return this.textMatrix.getScalingFactorX();
    }

    public float getYScale() {
        return this.textMatrix.getScalingFactorY();
    }

    public float[] getIndividualWidths() {
        return this.widths;
    }

    public boolean contains(TextPosition textPosition) {
        double xDirAdj = getXDirAdj();
        double widthDirAdj = getWidthDirAdj();
        double d = xDirAdj + widthDirAdj;
        double xDirAdj2 = textPosition.getXDirAdj();
        double widthDirAdj2 = textPosition.getWidthDirAdj() + xDirAdj2;
        if (widthDirAdj2 <= xDirAdj || xDirAdj2 >= d) {
            return false;
        }
        double yDirAdj = getYDirAdj();
        double yDirAdj2 = textPosition.getYDirAdj();
        if (textPosition.getHeightDir() + yDirAdj2 < yDirAdj || yDirAdj2 > yDirAdj + getHeightDir()) {
            return false;
        }
        return (xDirAdj2 <= xDirAdj || widthDirAdj2 <= d) ? xDirAdj2 >= xDirAdj || widthDirAdj2 >= d || (widthDirAdj2 - xDirAdj) / widthDirAdj > 0.15d : (d - xDirAdj2) / widthDirAdj > 0.15d;
    }

    public void mergeDiacritic(TextPosition textPosition) {
        if (textPosition.getUnicode().length() > 1) {
            return;
        }
        float xDirAdj = textPosition.getXDirAdj();
        float f = textPosition.widths[0] + xDirAdj;
        float xDirAdj2 = getXDirAdj();
        int length = this.unicode.length();
        float f2 = xDirAdj2;
        boolean z = false;
        for (int i = 0; i < length && !z; i++) {
            float[] fArr = this.widths;
            if (i >= fArr.length) {
                Log.i("PdfBox-Android", "diacritic " + textPosition.getUnicode() + " on ligature " + this.unicode + " is not supported yet and is ignored (PDFBOX-2831)");
                return;
            }
            float f3 = fArr[i];
            float f4 = f2 + f3;
            int i2 = (xDirAdj > f2 ? 1 : (xDirAdj == f2 ? 0 : -1));
            if (i2 >= 0 || f > f4) {
                if (i2 < 0) {
                    insertDiacritic(i, textPosition);
                } else if (f <= f4) {
                    insertDiacritic(i, textPosition);
                } else if (i == length - 1) {
                    insertDiacritic(i, textPosition);
                } else {
                    f2 += this.widths[i];
                }
            } else if (i == 0) {
                insertDiacritic(i, textPosition);
            } else {
                int i3 = i - 1;
                if ((f - f2) / f3 >= (f2 - xDirAdj) / fArr[i3]) {
                    insertDiacritic(i, textPosition);
                } else {
                    insertDiacritic(i3, textPosition);
                }
            }
            z = true;
            f2 += this.widths[i];
        }
    }

    private void insertDiacritic(int i, TextPosition textPosition) {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) this.unicode, 0, i);
        float[] fArr = this.widths;
        float[] fArr2 = new float[fArr.length + 1];
        System.arraycopy(fArr, 0, fArr2, 0, i);
        sb.append(this.unicode.charAt(i));
        fArr2[i] = this.widths[i];
        sb.append(combineDiacritic(textPosition.getUnicode()));
        int i2 = i + 1;
        fArr2[i2] = 0.0f;
        sb.append(this.unicode.substring(i2));
        float[] fArr3 = this.widths;
        System.arraycopy(fArr3, i2, fArr2, i + 2, (fArr3.length - i) - 1);
        this.unicode = sb.toString();
        this.widths = fArr2;
    }

    private String combineDiacritic(String str) {
        int codePointAt = str.codePointAt(0);
        Map<Integer, String> map = DIACRITICS;
        if (map.containsKey(Integer.valueOf(codePointAt))) {
            return map.get(Integer.valueOf(codePointAt));
        }
        return Normalizer.normalize(str, Normalizer.Form.NFKC).trim();
    }

    public boolean isDiacritic() {
        String unicode = getUnicode();
        if (unicode.length() == 1 && !"ー".equals(unicode)) {
            int type = Character.getType(unicode.charAt(0));
            return type == 6 || type == 27 || type == 4;
        }
        return false;
    }

    public String toString() {
        return getUnicode();
    }

    public float getEndX() {
        return this.endX;
    }

    public float getEndY() {
        return this.endY;
    }

    public int getRotation() {
        return this.rotation;
    }

    public float getPageHeight() {
        return this.pageHeight;
    }

    public float getPageWidth() {
        return this.pageWidth;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TextPosition) {
            TextPosition textPosition = (TextPosition) obj;
            if (Float.compare(textPosition.endX, this.endX) == 0 && Float.compare(textPosition.endY, this.endY) == 0 && Float.compare(textPosition.maxHeight, this.maxHeight) == 0 && this.rotation == textPosition.rotation && Float.compare(textPosition.x, this.x) == 0 && Float.compare(textPosition.y, this.y) == 0 && Float.compare(textPosition.pageHeight, this.pageHeight) == 0 && Float.compare(textPosition.pageWidth, this.pageWidth) == 0 && Float.compare(textPosition.widthOfSpace, this.widthOfSpace) == 0 && Float.compare(textPosition.fontSize, this.fontSize) == 0 && this.fontSizePt == textPosition.fontSizePt) {
                Matrix matrix = this.textMatrix;
                if (matrix == null ? textPosition.textMatrix == null : matrix.equals(textPosition.textMatrix)) {
                    if (Arrays.equals(this.charCodes, textPosition.charCodes)) {
                        PDFont pDFont = this.font;
                        PDFont pDFont2 = textPosition.font;
                        return pDFont != null ? pDFont.equals(pDFont2) : pDFont2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Matrix matrix = this.textMatrix;
        int hashCode = (((((((((((((((((((((matrix != null ? matrix.hashCode() : 0) * 31) + Float.floatToIntBits(this.endX)) * 31) + Float.floatToIntBits(this.endY)) * 31) + Float.floatToIntBits(this.maxHeight)) * 31) + this.rotation) * 31) + Float.floatToIntBits(this.x)) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.pageHeight)) * 31) + Float.floatToIntBits(this.pageWidth)) * 31) + Float.floatToIntBits(this.widthOfSpace)) * 31) + Arrays.hashCode(this.charCodes)) * 31;
        PDFont pDFont = this.font;
        return ((((hashCode + (pDFont != null ? pDFont.hashCode() : 0)) * 31) + Float.floatToIntBits(this.fontSize)) * 31) + this.fontSizePt;
    }
}
