package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.BackgroundProcess;

/* loaded from: classes2.dex */
public final class BackgroundProcessDao_Impl implements BackgroundProcessDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<BackgroundProcess> __insertionAdapterOfBackgroundProcess;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsFailed;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsSuccess;

    public BackgroundProcessDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBackgroundProcess = new EntityInsertionAdapter<BackgroundProcess>(__db) { // from class: org.informatika.sirekap.db.dao.BackgroundProcessDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `background_processses` (`id`,`startedAt`,`endedAt`,`isSuccess`,`errorMessage`) VALUES (?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, BackgroundProcess value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                stmt.bindLong(2, value.getStartedAt());
                if (value.getEndedAt() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, value.getEndedAt().longValue());
                }
                Integer valueOf = value.isSuccess() == null ? null : Integer.valueOf(value.isSuccess().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, valueOf.intValue());
                }
                if (value.getErrorMessage() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getErrorMessage());
                }
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.BackgroundProcessDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM background_processses WHERE id = ?";
            }
        };
        this.__preparedStmtOfMarkAsSuccess = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.BackgroundProcessDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE background_processses SET endedAt = ?, isSuccess = 1, errorMessage = null WHERE id = ?";
            }
        };
        this.__preparedStmtOfMarkAsFailed = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.BackgroundProcessDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE background_processses SET endedAt = ?, isSuccess = 0, errorMessage = ? WHERE id = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.BackgroundProcessDao
    public void insertAll(final List<BackgroundProcess> backgroundProcesses) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBackgroundProcess.insert(backgroundProcesses);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.BackgroundProcessDao
    public void deleteById(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.BackgroundProcessDao
    public void markAsSuccess(final String id2, final long endedAt) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsSuccess.acquire();
        acquire.bindLong(1, endedAt);
        if (id2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkAsSuccess.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.BackgroundProcessDao
    public void markAsFailed(final String id2, final long endedAt, final String errorMessage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsFailed.acquire();
        acquire.bindLong(1, endedAt);
        if (errorMessage == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, errorMessage);
        }
        if (id2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkAsFailed.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.BackgroundProcessDao
    public LiveData<BackgroundProcess> getById(final String id2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM background_processses WHERE id = ?", 1);
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"background_processses"}, true, new Callable<BackgroundProcess>() { // from class: org.informatika.sirekap.db.dao.BackgroundProcessDao_Impl.5
            @Override // java.util.concurrent.Callable
            public BackgroundProcess call() throws Exception {
                Boolean valueOf;
                BackgroundProcessDao_Impl.this.__db.beginTransaction();
                try {
                    BackgroundProcess backgroundProcess = null;
                    Cursor query = DBUtil.query(BackgroundProcessDao_Impl.this.__db, acquire, false, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, JobType.ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "startedAt");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "endedAt");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "isSuccess");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "errorMessage");
                    if (query.moveToFirst()) {
                        String string = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        long j = query.getLong(columnIndexOrThrow2);
                        Long valueOf2 = query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3));
                        Integer valueOf3 = query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4));
                        if (valueOf3 == null) {
                            valueOf = null;
                        } else {
                            valueOf = Boolean.valueOf(valueOf3.intValue() != 0);
                        }
                        backgroundProcess = new BackgroundProcess(string, j, valueOf2, valueOf, query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    }
                    BackgroundProcessDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return backgroundProcess;
                } finally {
                    BackgroundProcessDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
