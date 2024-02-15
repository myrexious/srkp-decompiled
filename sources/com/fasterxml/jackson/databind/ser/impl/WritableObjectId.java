package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* loaded from: classes.dex */
public final class WritableObjectId {
    public final ObjectIdGenerator<?> generator;

    /* renamed from: id */
    public Object f18id;
    protected boolean idWritten = false;

    public WritableObjectId(ObjectIdGenerator<?> objectIdGenerator) {
        this.generator = objectIdGenerator;
    }

    public boolean writeAsId(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) throws IOException {
        if (this.f18id != null) {
            if (this.idWritten || objectIdWriter.alwaysAsId) {
                if (jsonGenerator.canWriteObjectId()) {
                    jsonGenerator.writeObjectRef(String.valueOf(this.f18id));
                    return true;
                }
                objectIdWriter.serializer.serialize(this.f18id, jsonGenerator, serializerProvider);
                return true;
            }
            return false;
        }
        return false;
    }

    public Object generateId(Object obj) {
        if (this.f18id == null) {
            this.f18id = this.generator.generateId(obj);
        }
        return this.f18id;
    }

    public void writeAsField(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) throws IOException {
        this.idWritten = true;
        if (jsonGenerator.canWriteObjectId()) {
            Object obj = this.f18id;
            jsonGenerator.writeObjectId(obj == null ? null : String.valueOf(obj));
            return;
        }
        SerializableString serializableString = objectIdWriter.propertyName;
        if (serializableString != null) {
            jsonGenerator.writeFieldName(serializableString);
            objectIdWriter.serializer.serialize(this.f18id, jsonGenerator, serializerProvider);
        }
    }
}
