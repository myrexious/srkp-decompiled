package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DatatypeFeatures implements Serializable {
    protected static final int FEATURE_INDEX_ENUM = 0;
    protected static final int FEATURE_INDEX_JSON_NODE = 1;
    private static final long serialVersionUID = 1;
    private final int _enabledFor1;
    private final int _enabledFor2;
    private final int _explicitFor1;
    private final int _explicitFor2;

    protected DatatypeFeatures(int i, int i2, int i3, int i4) {
        this._enabledFor1 = i;
        this._explicitFor1 = i2;
        this._enabledFor2 = i3;
        this._explicitFor2 = i4;
    }

    public static DatatypeFeatures defaultFeatures() {
        return DefaultHolder.getDefault();
    }

    private DatatypeFeatures _with(int i, int i2, int i3, int i4) {
        return (this._enabledFor1 == i && this._explicitFor1 == i2 && this._enabledFor2 == i3 && this._explicitFor2 == i4) ? this : new DatatypeFeatures(i, i2, i3, i4);
    }

    public DatatypeFeatures with(DatatypeFeature datatypeFeature) {
        int mask = datatypeFeature.getMask();
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 | mask, mask | this._explicitFor2);
            }
            VersionUtil.throwInternal();
            return this;
        }
        return _with(this._enabledFor1 | mask, mask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
    }

    public DatatypeFeatures withFeatures(DatatypeFeature... datatypeFeatureArr) {
        int _calcMask = _calcMask(datatypeFeatureArr);
        if (_calcMask == 0) {
            return this;
        }
        int featureIndex = datatypeFeatureArr[0].featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 | _calcMask, _calcMask | this._explicitFor2);
            }
            VersionUtil.throwInternal();
            return this;
        }
        return _with(this._enabledFor1 | _calcMask, _calcMask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
    }

    public DatatypeFeatures without(DatatypeFeature datatypeFeature) {
        int mask = datatypeFeature.getMask();
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 & (~mask), mask | this._explicitFor2);
            }
            VersionUtil.throwInternal();
            return this;
        }
        return _with(this._enabledFor1 & (~mask), mask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
    }

    public DatatypeFeatures withoutFeatures(DatatypeFeature... datatypeFeatureArr) {
        int _calcMask = _calcMask(datatypeFeatureArr);
        if (_calcMask == 0) {
            return this;
        }
        int featureIndex = datatypeFeatureArr[0].featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return _with(this._enabledFor1, this._explicitFor1, this._enabledFor2 & (~_calcMask), _calcMask | this._explicitFor2);
            }
            VersionUtil.throwInternal();
            return this;
        }
        return _with(this._enabledFor1 & (~_calcMask), _calcMask | this._explicitFor1, this._enabledFor2, this._explicitFor2);
    }

    private static final int _calcMask(DatatypeFeature... datatypeFeatureArr) {
        int i = 0;
        for (DatatypeFeature datatypeFeature : datatypeFeatureArr) {
            i |= datatypeFeature.getMask();
        }
        return i;
    }

    public boolean isEnabled(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return datatypeFeature.enabledIn(this._enabledFor2);
            }
            VersionUtil.throwInternal();
            return false;
        }
        return datatypeFeature.enabledIn(this._enabledFor1);
    }

    public boolean isExplicitlySet(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return datatypeFeature.enabledIn(this._explicitFor2);
            }
            VersionUtil.throwInternal();
            return false;
        }
        return datatypeFeature.enabledIn(this._explicitFor1);
    }

    public boolean isExplicitlyEnabled(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return datatypeFeature.enabledIn(this._explicitFor2 & this._enabledFor2);
            }
            VersionUtil.throwInternal();
            return false;
        }
        return datatypeFeature.enabledIn(this._explicitFor1 & this._enabledFor1);
    }

    public boolean isExplicitlyDisabled(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex != 0) {
            if (featureIndex == 1) {
                return datatypeFeature.enabledIn(this._explicitFor2 & (~this._enabledFor2));
            }
            VersionUtil.throwInternal();
            return false;
        }
        return datatypeFeature.enabledIn(this._explicitFor1 & (~this._enabledFor1));
    }

    public Boolean getExplicitState(DatatypeFeature datatypeFeature) {
        int featureIndex = datatypeFeature.featureIndex();
        if (featureIndex == 0) {
            if (datatypeFeature.enabledIn(this._explicitFor1)) {
                return Boolean.valueOf(datatypeFeature.enabledIn(this._enabledFor1));
            }
            return null;
        } else if (featureIndex == 1) {
            if (datatypeFeature.enabledIn(this._explicitFor2)) {
                return Boolean.valueOf(datatypeFeature.enabledIn(this._enabledFor2));
            }
            return null;
        } else {
            VersionUtil.throwInternal();
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class DefaultHolder {
        private static final DatatypeFeatures DEFAULT_FEATURES = new DatatypeFeatures(collectDefaults(EnumFeature.values()), 0, collectDefaults(JsonNodeFeature.values()), 0);

        private DefaultHolder() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static int collectDefaults(Enum[] enumArr) {
            int i = 0;
            for (FormatFeature formatFeature : enumArr) {
                if (formatFeature.enabledByDefault()) {
                    i |= formatFeature.getMask();
                }
            }
            return i;
        }

        public static DatatypeFeatures getDefault() {
            return DEFAULT_FEATURES;
        }
    }
}
