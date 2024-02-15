package com.auth0.android.jwt;

import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public interface Claim {
    <T> T[] asArray(Class<T> cls) throws DecodeException;

    Boolean asBoolean();

    Date asDate();

    Double asDouble();

    Integer asInt();

    <T> List<T> asList(Class<T> cls) throws DecodeException;

    Long asLong();

    <T> T asObject(Class<T> cls) throws DecodeException;

    String asString();
}
