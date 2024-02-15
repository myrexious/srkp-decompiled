package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
public interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
