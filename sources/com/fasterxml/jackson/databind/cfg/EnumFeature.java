package com.fasterxml.jackson.databind.cfg;

/* loaded from: classes.dex */
public enum EnumFeature implements DatatypeFeature {
    READ_ENUM_KEYS_USING_INDEX(false),
    WRITE_ENUMS_TO_LOWERCASE(false);
    
    private static final int FEATURE_INDEX = 0;
    private final boolean _enabledByDefault;
    private final int _mask = 1 << ordinal();

    @Override // com.fasterxml.jackson.databind.cfg.DatatypeFeature
    public int featureIndex() {
        return 0;
    }

    EnumFeature(boolean z) {
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
