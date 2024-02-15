package org.visp.core;

/* loaded from: classes4.dex */
public class VpColor extends VpRGBa {
    public VpColor() {
    }

    public VpColor(char c, char c2, char c3) {
        super(c, c2, c3);
    }

    public VpColor(int i, int i2, int i3) {
        super((char) i, (char) i2, (char) i3);
    }

    public VpColor(char c) {
        super(c);
    }

    public VpColor(int i) {
        super((char) i);
    }

    @Override // org.visp.core.VpRGBa
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VpColor) {
            VpColor vpColor = (VpColor) obj;
            return this.R == vpColor.R && this.G == vpColor.G && this.B == vpColor.B;
        }
        return false;
    }

    @Override // org.visp.core.VpRGBa
    public String toString() {
        return "(" + ((int) this.R) + "," + ((int) this.G) + "," + ((int) this.B) + ")";
    }
}
