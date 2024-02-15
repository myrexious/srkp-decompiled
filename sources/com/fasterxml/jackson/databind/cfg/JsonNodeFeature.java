package com.fasterxml.jackson.databind.cfg;

/* loaded from: classes.dex */
public enum JsonNodeFeature implements DatatypeFeature {
    READ_NULL_PROPERTIES(true),
    WRITE_NULL_PROPERTIES(true),
    STRIP_TRAILING_BIGDECIMAL_ZEROES(true);
    
    private static final int FEATURE_INDEX = 1;
    private final boolean _enabledByDefault;
    private final int _mask = 1 << ordinal();

    @Override // com.fasterxml.jackson.databind.cfg.DatatypeFeature
    public int featureIndex() {
        return 1;
    }

    JsonNodeFeature(boolean z) {
        this._enabledByDefault = z;
    }

    @Override // com.fasterxml.jackson.core.util.JacksonFeature
    public boolean enabledByDefault() {
        return this._enabledByDefault;
    }

    @Override // com.fasterxml.jackson.core.util.JacksonFeature
    public boolean enabledIn(int i) {
        return (i & this._mask) != 0;
    }

    @Override // com.fasterxml.jackson.core.util.JacksonFeature
    public int getMask() {
        return this._mask;
    }
}
