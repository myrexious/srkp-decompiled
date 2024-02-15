package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSNumber;

/* loaded from: classes3.dex */
public class PDPageXYZDestination extends PDPageDestination {
    protected static final String TYPE = "XYZ";

    public PDPageXYZDestination() {
        this.array.growToSize(5);
        this.array.setName(1, TYPE);
    }

    public PDPageXYZDestination(COSArray cOSArray) {
        super(cOSArray);
    }

    public int getLeft() {
        return this.array.getInt(2);
    }

    public void setLeft(int i) {
        this.array.growToSize(5);
        if (i == -1) {
            this.array.set(2, (COSBase) null);
        } else {
            this.array.setInt(2, i);
        }
    }

    public int getTop() {
        return this.array.getInt(3);
    }

    public void setTop(int i) {
        this.array.growToSize(5);
        if (i == -1) {
            this.array.set(3, (COSBase) null);
        } else {
            this.array.setInt(3, i);
        }
    }

    public float getZoom() {
        COSBase object = this.array.getObject(4);
        if (object instanceof COSNumber) {
            return ((COSNumber) object).floatValue();
        }
        return -1.0f;
    }

    public void setZoom(float f) {
        this.array.growToSize(5);
        if (f == -1.0f) {
            this.array.set(4, (COSBase) null);
        } else {
            this.array.set(4, (COSBase) new COSFloat(f));
        }
    }
}
