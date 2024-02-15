package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

/* loaded from: classes.dex */
public class StreamConstraintsException extends JsonProcessingException {
    private static final long serialVersionUID = 2;

    public StreamConstraintsException(String str) {
        super(str);
    }

    public StreamConstraintsException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }
}
