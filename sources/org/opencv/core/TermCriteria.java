package org.opencv.core;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes4.dex */
public class TermCriteria {
    public static final int COUNT = 1;
    public static final int EPS = 2;
    public static final int MAX_ITER = 1;
    public double epsilon;
    public int maxCount;
    public int type;

    public TermCriteria(int i, int i2, double d) {
        this.type = i;
        this.maxCount = i2;
        this.epsilon = d;
    }

    public TermCriteria() {
        this(0, 0, 0.0d);
    }

    public TermCriteria(double[] dArr) {
        set(dArr);
    }

    public void set(double[] dArr) {
        if (dArr != null) {
            this.type = dArr.length > 0 ? (int) dArr[0] : 0;
            this.maxCount = dArr.length > 1 ? (int) dArr[1] : 0;
            this.epsilon = dArr.length > 2 ? dArr[2] : 0.0d;
            return;
        }
        this.type = 0;
        this.maxCount = 0;
        this.epsilon = 0.0d;
    }

    public TermCriteria clone() {
        return new TermCriteria(this.type, this.maxCount, this.epsilon);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.type);
        long doubleToLongBits2 = Double.doubleToLongBits(this.maxCount);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(this.epsilon);
        return (i * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TermCriteria) {
            TermCriteria termCriteria = (TermCriteria) obj;
            return this.type == termCriteria.type && this.maxCount == termCriteria.maxCount && this.epsilon == termCriteria.epsilon;
        }
        return false;
    }

    public String toString() {
        return "{ type: " + this.type + ", maxCount: " + this.maxCount + ", epsilon: " + this.epsilon + StringSubstitutor.DEFAULT_VAR_END;
    }
}
