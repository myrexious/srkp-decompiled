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
import org.informatika.sirekap.model.SpecialOccurrencePage;

/* loaded from: classes2.dex */
public final class SpecialOccurrencePageDao_Impl implements SpecialOccurrencePageDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SpecialOccurrencePage> __insertionAdapterOfSpecialOccurrencePage;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;
    private final SharedSQLiteStatement __preparedStmtOfFinishPhoto;
    private final SharedSQLiteStatement __preparedStmtOfMarkChecked;

    public SpecialOccurrencePageDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSpecialOccurrencePage = new EntityInsertionAdapter<SpecialOccurrencePage>(__db) { // from class: org.informatika.sirekap.db.dao.SpecialOccurrencePageDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `special_occurrence_page` (`id`,`kodeTps`,`photoPath`,`croppedPhotoPath`,`hashDocumentCropped`,`checked`) VALUES (?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SpecialOccurrencePage value) {
                stmt.bindLong(1, value.getId());
                if (value.getKodeTps() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getKodeTps());
                }
                if (value.getPhotoPath() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPhotoPath());
                }
                if (value.getCroppedPhotoPath() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getCroppedPhotoPath());
                }
                if (value.getHashDocumentCropped() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getHashDocumentCropped());
                }
                stmt.bindLong(6, value.getChecked() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfFinishPhoto = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.SpecialOccurrencePageDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE special_occurrence_page SET photoPath = ?, croppedPhotoPath = ?,  hashDocumentCropped = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfMarkChecked = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.SpecialOccurrencePageDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE special_occurrence_page SET checked = 1 WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.SpecialOccurrencePageDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM special_occurrence_page WHERE id = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.SpecialOccurrencePageDao
    public void insertAll(final List<SpecialOccurrencePage> specialOccurrences) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSpecialOccurrencePage.insert(specialOccurrences);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.SpecialOccurrencePageDao
    public void finishPhoto(final long id2, final String photoPath, final String croppedPhotoPath, final String hashDocumentCropped) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishPhoto.acquire();
        if (photoPath == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, photoPath);
        }
        if (croppedPhotoPath == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, croppedPhotoPath);
        }
        if (hashDocumentCropped == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, hashDocumentCropped);
        }
        acquire.bindLong(4, id2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishPhoto.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.SpecialOccurrencePageDao
    public void markChecked(final long id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkChecked.acquire();
        acquire.bindLong(1, id2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkChecked.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.SpecialOccurrencePageDao
    public void deleteBy(final long id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy.acquire();
        acquire.bindLong(1, id2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteBy.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.SpecialOccurrencePageDao
    public LiveData<SpecialOccurrencePage> getById(final long id2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM special_occurrence_page WHERE id = ?", 1);
        acquire.bindLong(1, id2);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"special_occurrence_page"}, true, new Callable<SpecialOccurrencePage>() { // from class: org.informatika.sirekap.db.dao.SpecialOccurrencePageDao_Impl.5
            @Override // java.util.concurrent.Callable
            public SpecialOccurrencePage call() throws Exception {
                SpecialOccurrencePageDao_Impl.this.__db.beginTransaction();
                try {
                    SpecialOccurrencePage specialOccurrencePage = null;
                    Cursor query = DBUtil.query(SpecialOccurrencePageDao_Impl.this.__db, acquire, false, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, JobType.ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "photoPath");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "croppedPhotoPath");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "hashDocumentCropped");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "checked");
                    if (query.moveToFirst()) {
                        specialOccurrencePage = new SpecialOccurrencePage(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6) != 0);
                    }
                    SpecialOccurrencePageDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return specialOccurrencePage;
                } finally {
                    SpecialOccurrencePageDao_Impl.this.__db.endTransaction();
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
