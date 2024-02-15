package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;

/* loaded from: classes3.dex */
public class PDPageFitWidthDestination extends PDPageDestination {
    protected static final String TYPE = "FitH";
    protected static final String TYPE_BOUNDED = "FitBH";

    public PDPageFitWidthDestination() {
        this.array.growToSize(3);
        this.array.setName(1, TYPE);
    }

    public PDPageFitWidthDestination(COSArray cOSArray) {
        super(cOSArray);
    }

    public int getTop() {
        return this.array.getInt(2);
    }

    public void setTop(int i) {
        this.array.growToSize(3);
        if (i == -1) {
            this.array.set(2, (COSBase) null);
        } else {
            this.array.setInt(2, i);
        }
    }

    public boolean fitBoundingBox() {
        return TYPE_BOUNDED.equals(this.array.getName(1));
    }

    public void setFitBoundingBox(boolean z) {
        this.array.growToSize(3);
        if (z) {
            this.array.setName(1, TYPE_BOUNDED);
        } else {
            this.array.setName(1, TYPE);
        }
    }
}
