package com.fasterxml.jackson.core;

/* loaded from: classes.dex */
public class JsonProcessingException extends JacksonException {
    private static final long serialVersionUID = 123;
    protected JsonLocation _location;

    protected String getMessageSuffix() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public Object getProcessor() {
        return null;
    }

    public JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, th);
        this._location = jsonLocation;
    }

    public JsonProcessingException(String str) {
        super(str);
    }

    public JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, null);
    }

    protected JsonProcessingException(String str, Throwable th) {
        this(str, null, th);
    }

    public JsonProcessingException(Throwable th) {
        this(null, null, th);
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public JsonLocation getLocation() {
        return this._location;
    }

    public void clearLocation() {
        this._location = null;
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public String getOriginalMessage() {
        return super.getMessage();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        JsonLocation location = getLocation();
        String messageSuffix = getMessageSuffix();
        if (location == null && messageSuffix == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (messageSuffix != null) {
            sb.append(messageSuffix);
        }
        if (location != null) {
            sb.append("\n at ");
            sb.append(location.toString());
        }
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
