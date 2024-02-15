package org.visp.core;

/* loaded from: classes4.dex */
public class VpRGBa {
    public static final char alphaDefault = 255;
    public char A;
    public char B;
    public char G;
    public char R;

    public VpRGBa() {
        this((char) 0, (char) 0, (char) 0, alphaDefault);
    }

    public VpRGBa(char c, char c2, char c3) {
        this(c, c2, c3, alphaDefault);
    }

    public VpRGBa(char c, char c2, char c3, char c4) {
        this.R = c;
        this.G = c2;
        this.B = c3;
        this.A = c4;
    }

    public VpRGBa(char c) {
        this(c, c, c, c);
    }

    public VpRGBa(VpRGBa vpRGBa) {
        this(vpRGBa.R, vpRGBa.G, vpRGBa.B, vpRGBa.A);
    }

    public void add(VpRGBa vpRGBa) {
        this.R = (char) (this.R + vpRGBa.R);
        this.G = (char) (this.G + vpRGBa.G);
        this.B = (char) (this.B + vpRGBa.B);
        this.A = (char) (this.A + vpRGBa.A);
    }

    public String toString() {
        return "(" + ((int) this.R) + "," + ((int) this.G) + "," + ((int) this.B) + "," + ((int) this.A) + ")";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VpRGBa) {
            VpRGBa vpRGBa = (VpRGBa) obj;
            return this.R == vpRGBa.R && this.G == vpRGBa.G && this.B == vpRGBa.B && this.A == vpRGBa.A;
        }
        return false;
    }
}
