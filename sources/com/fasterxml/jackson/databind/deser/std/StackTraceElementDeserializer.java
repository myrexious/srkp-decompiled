package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

/* loaded from: classes.dex */
public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<?> _adapterDeserializer;

    /* loaded from: classes.dex */
    public static final class Adapter {
        public String classLoaderName;
        public String declaringClass;
        public String format;
        public String moduleName;
        public String moduleVersion;
        public boolean nativeMethod;
        public String className = "";
        public String fileName = "";
        public String methodName = "";
        public int lineNumber = -1;
    }

    @Deprecated
    public StackTraceElementDeserializer() {
        this(null);
    }

    protected StackTraceElementDeserializer(JsonDeserializer<?> jsonDeserializer) {
        super(StackTraceElement.class);
        this._adapterDeserializer = jsonDeserializer;
    }

    public static JsonDeserializer<?> construct(DeserializationContext deserializationContext) throws JsonMappingException {
        if (deserializationContext == null) {
            return new StackTraceElementDeserializer();
        }
        return new StackTraceElementDeserializer(deserializationContext.findNonContextualValueDeserializer(deserializationContext.constructType(Adapter.class)));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Adapter adapter;
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME) {
            JsonDeserializer<?> jsonDeserializer = this._adapterDeserializer;
            if (jsonDeserializer == null) {
                adapter = (Adapter) deserializationContext.readValue(jsonParser, Adapter.class);
            } else {
                adapter = (Adapter) jsonDeserializer.deserialize(jsonParser, deserializationContext);
            }
            return constructValue(deserializationContext, adapter);
        } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            StackTraceElement deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
            }
            return deserialize;
        } else {
            return (StackTraceElement) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
    }

    protected StackTraceElement constructValue(DeserializationContext deserializationContext, Adapter adapter) {
        return constructValue(deserializationContext, adapter.className, adapter.methodName, adapter.fileName, adapter.lineNumber, adapter.moduleName, adapter.moduleVersion, adapter.classLoaderName);
    }

    @Deprecated
    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5) {
        return constructValue(deserializationContext, str, str2, str3, i, str4, str5, null);
    }

    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5, String str6) {
        return new StackTraceElement(str, str2, str3, i);
    }
}
