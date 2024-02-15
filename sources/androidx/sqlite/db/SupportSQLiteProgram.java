package androidx.sqlite.db;

import java.io.Closeable;

/* loaded from: classes.dex */
public interface SupportSQLiteProgram extends Closeable {
    void bindBlob(int index, byte[] value);

    void bindDouble(int index, double value);

    void bindLong(int index, long value);

    void bindNull(int index);

    void bindString(int index, String value);

    void clearBindings();
}
