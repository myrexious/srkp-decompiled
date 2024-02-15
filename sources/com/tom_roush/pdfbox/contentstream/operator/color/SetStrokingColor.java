package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;

/* loaded from: classes3.dex */
public class SetStrokingColor extends SetColor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.STROKING_COLOR;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetColor
    protected PDColor getColor() {
        return this.context.getGraphicsState().getStrokingColor();
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetColor
    protected void setColor(PDColor pDColor) {
        this.context.getGraphicsState().setStrokingColor(pDColor);
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.color.SetColor
    protected PDColorSpace getColorSpace() {
        return this.context.getGraphicsState().getStrokingColorSpace();
    }
}
