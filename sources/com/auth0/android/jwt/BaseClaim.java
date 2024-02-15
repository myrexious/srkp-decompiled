package com.auth0.android.jwt;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class BaseClaim implements Claim {
    @Override // com.auth0.android.jwt.Claim
    public Boolean asBoolean() {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public Date asDate() {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public Double asDouble() {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public Integer asInt() {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public Long asLong() {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public <T> T asObject(Class<T> cls) throws DecodeException {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public String asString() {
        return null;
    }

    @Override // com.auth0.android.jwt.Claim
    public <T> T[] asArray(Class<T> cls) throws DecodeException {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, 0));
    }

    @Override // com.auth0.android.jwt.Claim
    public <T> List<T> asList(Class<T> cls) throws DecodeException {
        return Collections.emptyList();
    }
}
