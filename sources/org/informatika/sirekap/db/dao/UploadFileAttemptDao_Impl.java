package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.UploadFileAttempt;

/* loaded from: classes2.dex */
public final class UploadFileAttemptDao_Impl implements UploadFileAttemptDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<UploadFileAttempt> __insertionAdapterOfUploadFileAttempt;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy_1;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsSuccess;

    public UploadFileAttemptDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUploadFileAttempt = new EntityInsertionAdapter<UploadFileAttempt>(__db) { // from class: org.informatika.sirekap.db.dao.UploadFileAttemptDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `upload_file_attempts` (`id`,`type`,`attempt`,`isSuccess`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, UploadFileAttempt value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getType() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getType());
                }
                stmt.bindLong(3, value.getAttempt());
                stmt.bindLong(4, value.isSuccess() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfMarkAsSuccess = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UploadFileAttemptDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_file_attempts SET isSuccess = 1 WHERE id = ? AND type = ?";
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UploadFileAttemptDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM upload_file_attempts WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteBy_1 = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UploadFileAttemptDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM upload_file_attempts WHERE id = ? AND type = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.UploadFileAttemptDao
    public void insertAll(final List<UploadFileAttempt> records) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUploadFileAttempt.insert(records);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.UploadFileAttemptDao
    public void markAsSuccess(final String id2, final String type) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsSuccess.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        if (type == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, type);
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

    @Override // org.informatika.sirekap.db.dao.UploadFileAttemptDao
    public void deleteBy(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy.acquire();
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
            this.__preparedStmtOfDeleteBy.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.UploadFileAttemptDao
    public void deleteBy(final String id2, final String type) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy_1.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        if (type == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, type);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteBy_1.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.UploadFileAttemptDao
    public UploadFileAttempt getBy(final String id2, final String type) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_file_attempts WHERE id = ? AND type = ?", 2);
        boolean z = true;
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        if (type == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, type);
        }
        this.__db.assertNotSuspendingTransaction();
        UploadFileAttempt uploadFileAttempt = null;
        String string = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, JobType.ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "attempt");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "isSuccess");
            if (query.moveToFirst()) {
                String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                if (!query.isNull(columnIndexOrThrow2)) {
                    string = query.getString(columnIndexOrThrow2);
                }
                int i = query.getInt(columnIndexOrThrow3);
                if (query.getInt(columnIndexOrThrow4) == 0) {
                    z = false;
                }
                uploadFileAttempt = new UploadFileAttempt(string2, string, i, z);
            }
            return uploadFileAttempt;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
