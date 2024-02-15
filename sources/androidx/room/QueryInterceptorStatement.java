package androidx.room;

import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class QueryInterceptorStatement implements SupportSQLiteStatement {
    private final List<Object> mBindArgsCache = new ArrayList();
    private final SupportSQLiteStatement mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;
    private final String mSqlStatement;

    public QueryInterceptorStatement(SupportSQLiteStatement compileStatement, RoomDatabase.QueryCallback queryCallback, String sqlStatement, Executor queryCallbackExecutor) {
        this.mDelegate = compileStatement;
        this.mQueryCallback = queryCallback;
        this.mSqlStatement = sqlStatement;
        this.mQueryCallbackExecutor = queryCallbackExecutor;
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public void execute() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m101lambda$execute$0$androidxroomQueryInterceptorStatement();
            }
        });
        this.mDelegate.execute();
    }

    /* renamed from: lambda$execute$0$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m101lambda$execute$0$androidxroomQueryInterceptorStatement() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public int executeUpdateDelete() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m103x7bfa4cf9();
            }
        });
        return this.mDelegate.executeUpdateDelete();
    }

    /* renamed from: lambda$executeUpdateDelete$1$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m103x7bfa4cf9() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public long executeInsert() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m102lambda$executeInsert$2$androidxroomQueryInterceptorStatement();
            }
        });
        return this.mDelegate.executeInsert();
    }

    /* renamed from: lambda$executeInsert$2$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m102lambda$executeInsert$2$androidxroomQueryInterceptorStatement() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    /* renamed from: lambda$simpleQueryForLong$3$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m104xa983133b() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public long simpleQueryForLong() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m104xa983133b();
            }
        });
        return this.mDelegate.simpleQueryForLong();
    }

    /* renamed from: lambda$simpleQueryForString$4$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m105x12aaf991() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public String simpleQueryForString() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m105x12aaf991();
            }
        });
        return this.mDelegate.simpleQueryForString();
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int index) {
        saveArgsToCache(index, this.mBindArgsCache.toArray());
        this.mDelegate.bindNull(index);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int index, long value) {
        saveArgsToCache(index, Long.valueOf(value));
        this.mDelegate.bindLong(index, value);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int index, double value) {
        saveArgsToCache(index, Double.valueOf(value));
        this.mDelegate.bindDouble(index, value);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int index, String value) {
        saveArgsToCache(index, value);
        this.mDelegate.bindString(index, value);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int index, byte[] value) {
        saveArgsToCache(index, value);
        this.mDelegate.bindBlob(index, value);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        this.mBindArgsCache.clear();
        this.mDelegate.clearBindings();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mDelegate.close();
    }

    private void saveArgsToCache(int bindIndex, Object value) {
        int i = bindIndex - 1;
        if (i >= this.mBindArgsCache.size()) {
            for (int size = this.mBindArgsCache.size(); size <= i; size++) {
                this.mBindArgsCache.add(null);
            }
        }
        this.mBindArgsCache.set(i, value);
    }
}
