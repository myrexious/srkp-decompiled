package androidx.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    void beginTransactionNonExclusive();

    void beginTransactionWithListener(SQLiteTransactionListener transactionListener);

    void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener transactionListener);

    SupportSQLiteStatement compileStatement(String sql);

    int delete(String table, String whereClause, Object[] whereArgs);

    void disableWriteAheadLogging();

    boolean enableWriteAheadLogging();

    void endTransaction();

    void execSQL(String sql) throws SQLException;

    void execSQL(String sql, Object[] bindArgs) throws SQLException;

    List<Pair<String, String>> getAttachedDbs();

    long getMaximumSize();

    long getPageSize();

    String getPath();

    int getVersion();

    boolean inTransaction();

    long insert(String table, int conflictAlgorithm, ContentValues values) throws SQLException;

    boolean isDatabaseIntegrityOk();

    boolean isDbLockedByCurrentThread();

    default boolean isExecPerConnectionSQLSupported() {
        return false;
    }

    boolean isOpen();

    boolean isReadOnly();

    boolean isWriteAheadLoggingEnabled();

    boolean needUpgrade(int newVersion);

    Cursor query(SupportSQLiteQuery query);

    Cursor query(SupportSQLiteQuery query, CancellationSignal cancellationSignal);

    Cursor query(String query);

    Cursor query(String query, Object[] bindArgs);

    void setForeignKeyConstraintsEnabled(boolean enable);

    void setLocale(Locale locale);

    void setMaxSqlCacheSize(int cacheSize);

    long setMaximumSize(long numBytes);

    void setPageSize(long numBytes);

    void setTransactionSuccessful();

    void setVersion(int version);

    int update(String table, int conflictAlgorithm, ContentValues values, String whereClause, Object[] whereArgs);

    boolean yieldIfContendedSafely();

    boolean yieldIfContendedSafely(long sleepAfterYieldDelay);

    default void execPerConnectionSQL(String sql, Object[] bindArgs) {
        throw new UnsupportedOperationException();
    }
}
