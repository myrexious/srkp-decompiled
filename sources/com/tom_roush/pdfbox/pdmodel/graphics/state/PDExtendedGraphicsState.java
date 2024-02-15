package com.tom_roush.pdfbox.pdmodel.graphics.state;

import android.graphics.Paint;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDFontSetting;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDExtendedGraphicsState implements COSObjectable {
    private final COSDictionary dict;

    public PDExtendedGraphicsState() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.EXT_G_STATE);
    }

    public PDExtendedGraphicsState(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    public void copyIntoGraphicsState(PDGraphicsState pDGraphicsState) throws IOException {
        for (COSName cOSName : this.dict.keySet()) {
            if (cOSName.equals(COSName.LW)) {
                pDGraphicsState.setLineWidth(defaultIfNull(getLineWidth(), 1.0f));
            } else if (cOSName.equals(COSName.LC)) {
                pDGraphicsState.setLineCap(getLineCapStyle());
            } else if (cOSName.equals(COSName.LJ)) {
                pDGraphicsState.setLineJoin(getLineJoinStyle());
            } else if (cOSName.equals(COSName.ML)) {
                pDGraphicsState.setMiterLimit(defaultIfNull(getMiterLimit(), 10.0f));
            } else if (cOSName.equals(COSName.D)) {
                pDGraphicsState.setLineDashPattern(getLineDashPattern());
            } else if (cOSName.equals(COSName.RI)) {
                pDGraphicsState.setRenderingIntent(getRenderingIntent());
            } else if (cOSName.equals(COSName.OPM)) {
                pDGraphicsState.setOverprintMode(defaultIfNull(getOverprintMode(), 0.0f));
            } else if (cOSName.equals(COSName.OP)) {
                pDGraphicsState.setOverprint(getStrokingOverprintControl());
            } else if (cOSName.equals(COSName.OP_NS)) {
                pDGraphicsState.setNonStrokingOverprint(getNonStrokingOverprintControl());
            } else if (cOSName.equals(COSName.FONT)) {
                PDFontSetting fontSetting = getFontSetting();
                if (fontSetting != null) {
                    pDGraphicsState.getTextState().setFont(fontSetting.getFont());
                    pDGraphicsState.getTextState().setFontSize(fontSetting.getFontSize());
                }
            } else if (cOSName.equals(COSName.FL)) {
                pDGraphicsState.setFlatness(defaultIfNull(getFlatnessTolerance(), 1.0f));
            } else if (cOSName.equals(COSName.SM)) {
                pDGraphicsState.setSmoothness(defaultIfNull(getSmoothnessTolerance(), 0.0f));
            } else if (cOSName.equals(COSName.SA)) {
                pDGraphicsState.setStrokeAdjustment(getAutomaticStrokeAdjustment());
            } else if (cOSName.equals(COSName.CA)) {
                pDGraphicsState.setAlphaConstant(defaultIfNull(getStrokingAlphaConstant(), 1.0f));
            } else if (cOSName.equals(COSName.CA_NS)) {
                pDGraphicsState.setNonStrokeAlphaConstant(defaultIfNull(getNonStrokingAlphaConstant(), 1.0f));
            } else if (cOSName.equals(COSName.AIS)) {
                pDGraphicsState.setAlphaSource(getAlphaSourceFlag());
            } else if (cOSName.equals(COSName.TK)) {
                pDGraphicsState.getTextState().setKnockoutFlag(getTextKnockoutFlag());
            } else if (cOSName.equals(COSName.SMASK)) {
                PDSoftMask softMask = getSoftMask();
                if (softMask != null) {
                    softMask.setInitialTransformationMatrix(pDGraphicsState.getCurrentTransformationMatrix().m268clone());
                }
                pDGraphicsState.setSoftMask(softMask);
            } else if (cOSName.equals(COSName.BM)) {
                pDGraphicsState.setBlendMode(getBlendMode());
            } else if (cOSName.equals(COSName.TR)) {
                if (!this.dict.containsKey(COSName.TR2)) {
                    pDGraphicsState.setTransfer(getTransfer());
                }
            } else if (cOSName.equals(COSName.TR2)) {
                pDGraphicsState.setTransfer(getTransfer2());
            }
        }
    }

    private float defaultIfNull(Float f, float f2) {
        return f != null ? f.floatValue() : f2;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    public Float getLineWidth() {
        return getFloatItem(COSName.LW);
    }

    public void setLineWidth(Float f) {
        setFloatItem(COSName.LW, f);
    }

    public Paint.Cap getLineCapStyle() {
        int i = this.dict.getInt(COSName.LC);
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return Paint.Cap.SQUARE;
            }
            return Paint.Cap.ROUND;
        }
        return Paint.Cap.BUTT;
    }

    public void setLineCapStyle(int i) {
        this.dict.setInt(COSName.LC, i);
    }

    public Paint.Join getLineJoinStyle() {
        int i = this.dict.getInt(COSName.LJ);
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return Paint.Join.BEVEL;
            }
            return Paint.Join.ROUND;
        }
        return Paint.Join.MITER;
    }

    public void setLineJoinStyle(int i) {
        this.dict.setInt(COSName.LJ, i);
    }

    public Float getMiterLimit() {
        return getFloatItem(COSName.ML);
    }

    public void setMiterLimit(Float f) {
        setFloatItem(COSName.ML, f);
    }

    public PDLineDashPattern getLineDashPattern() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.D);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (cOSArray.size() == 2) {
                COSBase object = cOSArray.getObject(0);
                COSBase object2 = cOSArray.getObject(1);
                if ((object instanceof COSArray) && (object2 instanceof COSNumber)) {
                    return new PDLineDashPattern((COSArray) object, ((COSNumber) object2).intValue());
                }
            }
        }
        return null;
    }

    public void setLineDashPattern(PDLineDashPattern pDLineDashPattern) {
        this.dict.setItem(COSName.D, pDLineDashPattern.getCOSObject());
    }

    public RenderingIntent getRenderingIntent() {
        String nameAsString = this.dict.getNameAsString("RI");
        if (nameAsString != null) {
            return RenderingIntent.fromString(nameAsString);
        }
        return null;
    }

    public void setRenderingIntent(String str) {
        this.dict.setName("RI", str);
    }

    public boolean getStrokingOverprintControl() {
        return this.dict.getBoolean(COSName.OP, false);
    }

    public void setStrokingOverprintControl(boolean z) {
        this.dict.setBoolean(COSName.OP, z);
    }

    public boolean getNonStrokingOverprintControl() {
        return this.dict.getBoolean(COSName.OP_NS, getStrokingOverprintControl());
    }

    public void setNonStrokingOverprintControl(boolean z) {
        this.dict.setBoolean(COSName.OP_NS, z);
    }

    public Float getOverprintMode() {
        return getFloatItem(COSName.OPM);
    }

    public void setOverprintMode(Float f) {
        if (f == null) {
            this.dict.removeItem(COSName.OPM);
        } else {
            this.dict.setInt(COSName.OPM, f.intValue());
        }
    }

    public PDFontSetting getFontSetting() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.FONT);
        if (dictionaryObject instanceof COSArray) {
            return new PDFontSetting((COSArray) dictionaryObject);
        }
        return null;
    }

    public void setFontSetting(PDFontSetting pDFontSetting) {
        this.dict.setItem(COSName.FONT, pDFontSetting);
    }

    public Float getFlatnessTolerance() {
        return getFloatItem(COSName.FL);
    }

    public void setFlatnessTolerance(Float f) {
        setFloatItem(COSName.FL, f);
    }

    public Float getSmoothnessTolerance() {
        return getFloatItem(COSName.SM);
    }

    public void setSmoothnessTolerance(Float f) {
        setFloatItem(COSName.SM, f);
    }

    public boolean getAutomaticStrokeAdjustment() {
        return this.dict.getBoolean(COSName.SA, false);
    }

    public void setAutomaticStrokeAdjustment(boolean z) {
        this.dict.setBoolean(COSName.SA, z);
    }

    public Float getStrokingAlphaConstant() {
        return getFloatItem(COSName.CA);
    }

    public void setStrokingAlphaConstant(Float f) {
        setFloatItem(COSName.CA, f);
    }

    public Float getNonStrokingAlphaConstant() {
        return getFloatItem(COSName.CA_NS);
    }

    public void setNonStrokingAlphaConstant(Float f) {
        setFloatItem(COSName.CA_NS, f);
    }

    public boolean getAlphaSourceFlag() {
        return this.dict.getBoolean(COSName.AIS, false);
    }

    public void setAlphaSourceFlag(boolean z) {
        this.dict.setBoolean(COSName.AIS, z);
    }

    public BlendMode getBlendMode() {
        return BlendMode.getInstance(this.dict.getDictionaryObject(COSName.BM));
    }

    public void setBlendMode(BlendMode blendMode) {
        this.dict.setItem(COSName.BM, (COSBase) BlendMode.getCOSName(blendMode));
    }

    public PDSoftMask getSoftMask() {
        if (this.dict.containsKey(COSName.SMASK)) {
            return PDSoftMask.create(this.dict.getDictionaryObject(COSName.SMASK));
        }
        return null;
    }

    public boolean getTextKnockoutFlag() {
        return this.dict.getBoolean(COSName.TK, true);
    }

    public void setTextKnockoutFlag(boolean z) {
        this.dict.setBoolean(COSName.TK, z);
    }

    private Float getFloatItem(COSName cOSName) {
        COSBase dictionaryObject = this.dict.getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        }
        return null;
    }

    private void setFloatItem(COSName cOSName, Float f) {
        if (f == null) {
            this.dict.removeItem(cOSName);
        } else {
            this.dict.setItem(cOSName, (COSBase) new COSFloat(f.floatValue()));
        }
    }

    public COSBase getTransfer() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.TR);
        if (!(dictionaryObject instanceof COSArray) || ((COSArray) dictionaryObject).size() == 4) {
            return dictionaryObject;
        }
        return null;
    }

    public void setTransfer(COSBase cOSBase) {
        this.dict.setItem(COSName.TR, cOSBase);
    }

    public COSBase getTransfer2() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.TR2);
        if (!(dictionaryObject instanceof COSArray) || ((COSArray) dictionaryObject).size() == 4) {
            return dictionaryObject;
        }
        return null;
    }

    public void setTransfer2(COSBase cOSBase) {
        this.dict.setItem(COSName.TR2, cOSBase);
    }
}
