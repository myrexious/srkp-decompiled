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
import org.informatika.sirekap.model.UploadImageAttempt;

/* loaded from: classes2.dex */
public final class UploadImageAttemptDao_Impl implements UploadImageAttemptDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<UploadImageAttempt> __insertionAdapterOfUploadImageAttempt;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy_1;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsSuccess;

    public UploadImageAttemptDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUploadImageAttempt = new EntityInsertionAdapter<UploadImageAttempt>(__db) { // from class: org.informatika.sirekap.db.dao.UploadImageAttemptDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `upload_image_attempts` (`electionPageId`,`jenisImage`,`attempt`,`isSuccess`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, UploadImageAttempt value) {
                if (value.getElectionPageId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getElectionPageId());
                }
                stmt.bindLong(2, value.getJenisImage());
                stmt.bindLong(3, value.getAttempt());
                stmt.bindLong(4, value.isSuccess() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfMarkAsSuccess = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UploadImageAttemptDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_image_attempts SET isSuccess = 1 WHERE electionPageId = ? AND jenisImage = ?";
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UploadImageAttemptDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM upload_image_attempts WHERE electionPageId = ?";
            }
        };
        this.__preparedStmtOfDeleteBy_1 = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UploadImageAttemptDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM upload_image_attempts WHERE electionPageId = ? AND jenisImage = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.UploadImageAttemptDao
    public void insertAll(final List<UploadImageAttempt> records) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUploadImageAttempt.insert(records);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.UploadImageAttemptDao
    public void markAsSuccess(final String electionPageId, final int jenisImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsSuccess.acquire();
        if (electionPageId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionPageId);
        }
        acquire.bindLong(2, jenisImage);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkAsSuccess.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.UploadImageAttemptDao
    public void deleteBy(final String electionPageId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy.acquire();
        if (electionPageId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionPageId);
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

    @Override // org.informatika.sirekap.db.dao.UploadImageAttemptDao
    public void deleteBy(final String electionPageId, final int jenisImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy_1.acquire();
        if (electionPageId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionPageId);
        }
        acquire.bindLong(2, jenisImage);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteBy_1.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.UploadImageAttemptDao
    public UploadImageAttempt getBy(final String electionPageId, final int jenisImage) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_image_attempts WHERE electionPageId = ? AND jenisImage = ?", 2);
        boolean z = true;
        if (electionPageId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionPageId);
        }
        acquire.bindLong(2, jenisImage);
        this.__db.assertNotSuspendingTransaction();
        UploadImageAttempt uploadImageAttempt = null;
        String string = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "electionPageId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "jenisImage");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "attempt");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "isSuccess");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    string = query.getString(columnIndexOrThrow);
                }
                int i = query.getInt(columnIndexOrThrow2);
                int i2 = query.getInt(columnIndexOrThrow3);
                if (query.getInt(columnIndexOrThrow4) == 0) {
                    z = false;
                }
                uploadImageAttempt = new UploadImageAttempt(string, i, i2, z);
            }
            return uploadImageAttempt;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
