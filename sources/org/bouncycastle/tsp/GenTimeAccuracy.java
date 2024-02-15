package org.bouncycastle.tsp;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.tsp.Accuracy;

/* loaded from: classes2.dex */
public class GenTimeAccuracy {
    private Accuracy accuracy;

    public GenTimeAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    private String format(int i) {
        StringBuilder sb;
        if (i < 10) {
            sb = new StringBuilder("00");
        } else if (i >= 100) {
            return Integer.toString(i);
        } else {
            sb = new StringBuilder("0");
        }
        return sb.append(i).toString();
    }

    private int getTimeComponent(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            return aSN1Integer.intValueExact();
        }
        return 0;
    }

    public int getMicros() {
        return getTimeComponent(this.accuracy.getMicros());
    }

    public int getMillis() {
        return getTimeComponent(this.accuracy.getMillis());
    }

    public int getSeconds() {
        return getTimeComponent(this.accuracy.getSeconds());
    }

    public String toString() {
        return getSeconds() + "." + format(getMillis()) + format(getMicros());
    }
}
