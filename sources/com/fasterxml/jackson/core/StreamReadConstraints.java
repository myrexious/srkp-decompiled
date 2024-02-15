package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.exc.StreamConstraintsException;
import java.io.Serializable;

/* loaded from: classes.dex */
public class StreamReadConstraints implements Serializable {
    public static final int DEFAULT_MAX_DEPTH = 1000;
    public static final int DEFAULT_MAX_NUM_LEN = 1000;
    private static final int MAX_BIGINT_SCALE_MAGNITUDE = 100000;
    private static final long serialVersionUID = 1;
    protected final int _maxNestingDepth;
    protected final int _maxNumLen;
    protected final int _maxStringLen;
    public static final int DEFAULT_MAX_STRING_LEN = 20000000;
    private static StreamReadConstraints DEFAULT = new StreamReadConstraints(1000, 1000, DEFAULT_MAX_STRING_LEN);

    public static void overrideDefaultStreamReadConstraints(StreamReadConstraints streamReadConstraints) {
        if (streamReadConstraints == null) {
            DEFAULT = new StreamReadConstraints(1000, 1000, DEFAULT_MAX_STRING_LEN);
        } else {
            DEFAULT = streamReadConstraints;
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private int maxNestingDepth;
        private int maxNumLen;
        private int maxStringLen;

        public Builder maxNestingDepth(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Cannot set maxNestingDepth to a negative value");
            }
            this.maxNestingDepth = i;
            return this;
        }

        public Builder maxNumberLength(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Cannot set maxNumberLength to a negative value");
            }
            this.maxNumLen = i;
            return this;
        }

        public Builder maxStringLength(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Cannot set maxStringLen to a negative value");
            }
            this.maxStringLen = i;
            return this;
        }

        Builder() {
            this(1000, 1000, StreamReadConstraints.DEFAULT_MAX_STRING_LEN);
        }

        Builder(int i, int i2, int i3) {
            this.maxNestingDepth = i;
            this.maxNumLen = i2;
            this.maxStringLen = i3;
        }

        Builder(StreamReadConstraints streamReadConstraints) {
            this.maxNestingDepth = streamReadConstraints._maxNestingDepth;
            this.maxNumLen = streamReadConstraints._maxNumLen;
            this.maxStringLen = streamReadConstraints._maxStringLen;
        }

        public StreamReadConstraints build() {
            return new StreamReadConstraints(this.maxNestingDepth, this.maxNumLen, this.maxStringLen);
        }
    }

    protected StreamReadConstraints(int i, int i2, int i3) {
        this._maxNestingDepth = i;
        this._maxNumLen = i2;
        this._maxStringLen = i3;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static StreamReadConstraints defaults() {
        return DEFAULT;
    }

    public Builder rebuild() {
        return new Builder(this);
    }

    public int getMaxNestingDepth() {
        return this._maxNestingDepth;
    }

    public int getMaxNumberLength() {
        return this._maxNumLen;
    }

    public int getMaxStringLength() {
        return this._maxStringLen;
    }

    public void validateNestingDepth(int i) throws StreamConstraintsException {
        if (i > this._maxNestingDepth) {
            throw new StreamConstraintsException(String.format("Depth (%d) exceeds the maximum allowed nesting depth (%d)", Integer.valueOf(i), Integer.valueOf(this._maxNestingDepth)));
        }
    }

    public void validateFPLength(int i) throws StreamConstraintsException {
        if (i > this._maxNumLen) {
            throw new StreamConstraintsException(String.format("Number length (%d) exceeds the maximum length (%d)", Integer.valueOf(i), Integer.valueOf(this._maxNumLen)));
        }
    }

    public void validateIntegerLength(int i) throws StreamConstraintsException {
        if (i > this._maxNumLen) {
            throw new StreamConstraintsException(String.format("Number length (%d) exceeds the maximum length (%d)", Integer.valueOf(i), Integer.valueOf(this._maxNumLen)));
        }
    }

    public void validateStringLength(int i) throws StreamConstraintsException {
        if (i > this._maxStringLen) {
            throw new StreamConstraintsException(String.format("String length (%d) exceeds the maximum length (%d)", Integer.valueOf(i), Integer.valueOf(this._maxStringLen)));
        }
    }

    public void validateBigIntegerScale(int i) throws StreamConstraintsException {
        if (Math.abs(i) > MAX_BIGINT_SCALE_MAGNITUDE) {
            throw new StreamConstraintsException(String.format("BigDecimal scale (%d) magnitude exceeds maximum allowed (%d)", Integer.valueOf(i), Integer.valueOf((int) MAX_BIGINT_SCALE_MAGNITUDE)));
        }
    }
}
