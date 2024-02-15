package com.auth0.android.jwt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class ClaimImpl extends BaseClaim {
    private final JsonElement value;

    public ClaimImpl(JsonElement jsonElement) {
        this.value = jsonElement;
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public Boolean asBoolean() {
        if (this.value.isJsonPrimitive()) {
            return Boolean.valueOf(this.value.getAsBoolean());
        }
        return null;
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public Integer asInt() {
        if (this.value.isJsonPrimitive()) {
            return Integer.valueOf(this.value.getAsInt());
        }
        return null;
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public Long asLong() {
        if (this.value.isJsonPrimitive()) {
            return Long.valueOf(this.value.getAsLong());
        }
        return null;
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public Double asDouble() {
        if (this.value.isJsonPrimitive()) {
            return Double.valueOf(this.value.getAsDouble());
        }
        return null;
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public String asString() {
        if (this.value.isJsonPrimitive()) {
            return this.value.getAsString();
        }
        return null;
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public Date asDate() {
        if (this.value.isJsonPrimitive()) {
            return new Date(Long.parseLong(this.value.getAsString()) * 1000);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public <T> T[] asArray(Class<T> cls) throws DecodeException {
        try {
            if (this.value.isJsonArray() && !this.value.isJsonNull()) {
                Gson gson = new Gson();
                JsonArray asJsonArray = this.value.getAsJsonArray();
                T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, asJsonArray.size()));
                for (int i = 0; i < asJsonArray.size(); i++) {
                    tArr[i] = gson.fromJson(asJsonArray.get(i), (Class<Object>) cls);
                }
                return tArr;
            }
            return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, 0));
        } catch (JsonSyntaxException e) {
            throw new DecodeException("Failed to decode claim as array", e);
        }
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public <T> List<T> asList(Class<T> cls) throws DecodeException {
        try {
            if (this.value.isJsonArray() && !this.value.isJsonNull()) {
                Gson gson = new Gson();
                JsonArray asJsonArray = this.value.getAsJsonArray();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < asJsonArray.size(); i++) {
                    arrayList.add(gson.fromJson(asJsonArray.get(i), (Class<Object>) cls));
                }
                return arrayList;
            }
            return new ArrayList();
        } catch (JsonSyntaxException e) {
            throw new DecodeException("Failed to decode claim as list", e);
        }
    }

    @Override // com.auth0.android.jwt.BaseClaim, com.auth0.android.jwt.Claim
    public <T> T asObject(Class<T> cls) throws DecodeException {
        try {
            if (this.value.isJsonNull()) {
                return null;
            }
            return (T) new Gson().fromJson(this.value, (Class<Object>) cls);
        } catch (JsonSyntaxException e) {
            throw new DecodeException("Failed to decode claim as " + cls.getSimpleName(), e);
        }
    }
}
