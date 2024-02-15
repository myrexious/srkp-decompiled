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
import org.informatika.sirekap.model.AttendancePage;

/* loaded from: classes2.dex */
public final class AttendancePageDao_Impl implements AttendancePageDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<AttendancePage> __insertionAdapterOfAttendancePage;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;
    private final SharedSQLiteStatement __preparedStmtOfFinishPhoto;
    private final SharedSQLiteStatement __preparedStmtOfMarkChecked;
    private final SharedSQLiteStatement __preparedStmtOfMoveDown;
    private final SharedSQLiteStatement __preparedStmtOfMoveUp;
    private final SharedSQLiteStatement __preparedStmtOfUpdateAfterDeleted;

    public AttendancePageDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAttendancePage = new EntityInsertionAdapter<AttendancePage>(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `attendance_page` (`id`,`kodeTps`,`photoPath`,`croppedPhotoPath`,`urutan`,`hashDocumentCropped`,`checked`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, AttendancePage value) {
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
                stmt.bindLong(5, value.getUrutan());
                if (value.getHashDocumentCropped() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getHashDocumentCropped());
                }
                stmt.bindLong(7, value.getChecked() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfFinishPhoto = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance_page SET photoPath = ?, croppedPhotoPath = ?,  hashDocumentCropped = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateAfterDeleted = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance_page SET urutan = urutan - 1 WHERE urutan > ? AND kodeTps = ?";
            }
        };
        this.__preparedStmtOfMarkChecked = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance_page SET checked = 1 WHERE id = ?";
            }
        };
        this.__preparedStmtOfMoveUp = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance_page SET urutan = urutan - 1 WHERE id = ?";
            }
        };
        this.__preparedStmtOfMoveDown = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance_page SET urutan = urutan + 1 WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM attendance_page WHERE id = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
    public void insertAll(final List<AttendancePage> attendees) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAttendancePage.insert(attendees);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
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

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
    public void updateAfterDeleted(final String kodeTps, final int urutan) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateAfterDeleted.acquire();
        acquire.bindLong(1, urutan);
        if (kodeTps == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, kodeTps);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateAfterDeleted.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
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

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
    public void moveUp(final long id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMoveUp.acquire();
        acquire.bindLong(1, id2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMoveUp.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
    public void moveDown(final long id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMoveDown.acquire();
        acquire.bindLong(1, id2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMoveDown.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
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

    @Override // org.informatika.sirekap.db.dao.AttendancePageDao
    public LiveData<AttendancePage> getById(final long id2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM attendance_page WHERE id = ?", 1);
        acquire.bindLong(1, id2);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"attendance_page"}, true, new Callable<AttendancePage>() { // from class: org.informatika.sirekap.db.dao.AttendancePageDao_Impl.8
            @Override // java.util.concurrent.Callable
            public AttendancePage call() throws Exception {
                AttendancePageDao_Impl.this.__db.beginTransaction();
                try {
                    AttendancePage attendancePage = null;
                    Cursor query = DBUtil.query(AttendancePageDao_Impl.this.__db, acquire, false, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, JobType.ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "photoPath");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "croppedPhotoPath");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "urutan");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "hashDocumentCropped");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "checked");
                    if (query.moveToFirst()) {
                        attendancePage = new AttendancePage(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), query.getInt(columnIndexOrThrow7) != 0);
                    }
                    AttendancePageDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return attendancePage;
                } finally {
                    AttendancePageDao_Impl.this.__db.endTransaction();
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