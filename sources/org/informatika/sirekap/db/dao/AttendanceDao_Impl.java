package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.Attendance;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.model.AttendanceWithPages;

/* loaded from: classes2.dex */
public final class AttendanceDao_Impl implements AttendanceDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Attendance> __insertionAdapterOfAttendance;
    private final SharedSQLiteStatement __preparedStmtOfFinishCreatePdf;
    private final SharedSQLiteStatement __preparedStmtOfFinishUploadPdf;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePdfStatus;

    public AttendanceDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAttendance = new EntityInsertionAdapter<Attendance>(__db) { // from class: org.informatika.sirekap.db.dao.AttendanceDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `attendance` (`kodeTps`,`isPdf`,`uploadPdfStatus`,`isUploadedPdf`,`isUploadedPdfOffline`,`pdfFilePath`,`pdfFileHash`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Attendance value) {
                if (value.getKodeTps() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKodeTps());
                }
                stmt.bindLong(2, value.isPdf() ? 1L : 0L);
                stmt.bindLong(3, value.getUploadPdfStatus());
                stmt.bindLong(4, value.isUploadedPdf() ? 1L : 0L);
                stmt.bindLong(5, value.isUploadedPdfOffline() ? 1L : 0L);
                if (value.getPdfFilePath() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getPdfFilePath());
                }
                if (value.getPdfFileHash() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPdfFileHash());
                }
            }
        };
        this.__preparedStmtOfFinishCreatePdf = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendanceDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance SET isPdf = 1, pdfFilePath = ?, pdfFileHash = ? WHERE kodeTps = ?";
            }
        };
        this.__preparedStmtOfUpdatePdfStatus = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendanceDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance SET uploadPdfStatus = ? WHERE kodeTps = ?";
            }
        };
        this.__preparedStmtOfFinishUploadPdf = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.AttendanceDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE attendance SET isUploadedPdf = 1, isUploadedPdfOffline = ? WHERE kodeTps = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.AttendanceDao
    public void insertAll(final List<Attendance> attendees) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAttendance.insert(attendees);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendanceDao
    public void finishCreatePdf(final String kodeTps, final String pdfFilePath, final String pdfFileHash) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishCreatePdf.acquire();
        if (pdfFilePath == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, pdfFilePath);
        }
        if (pdfFileHash == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, pdfFileHash);
        }
        if (kodeTps == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, kodeTps);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishCreatePdf.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendanceDao
    public void updatePdfStatus(final int uploadPdfStatus, final String kodeTps) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdatePdfStatus.acquire();
        acquire.bindLong(1, uploadPdfStatus);
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
            this.__preparedStmtOfUpdatePdfStatus.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendanceDao
    public void finishUploadPdf(final String kodeTps, final boolean isUploadedPdfOffline) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishUploadPdf.acquire();
        acquire.bindLong(1, isUploadedPdfOffline ? 1L : 0L);
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
            this.__preparedStmtOfFinishUploadPdf.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.AttendanceDao
    public LiveData<AttendanceWithPages> getByKodeTps(final String kodeTps) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM attendance WHERE kodeTps = ?", 1);
        if (kodeTps == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, kodeTps);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"attendance_page", "attendance"}, true, new Callable<AttendanceWithPages>() { // from class: org.informatika.sirekap.db.dao.AttendanceDao_Impl.5
            @Override // java.util.concurrent.Callable
            public AttendanceWithPages call() throws Exception {
                AttendanceDao_Impl.this.__db.beginTransaction();
                try {
                    AttendanceWithPages attendanceWithPages = null;
                    Attendance attendance = null;
                    Cursor query = DBUtil.query(AttendanceDao_Impl.this.__db, acquire, true, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "isPdf");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "uploadPdfStatus");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "isUploadedPdf");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isUploadedPdfOffline");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "pdfFilePath");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "pdfFileHash");
                    ArrayMap arrayMap = new ArrayMap();
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        if (((ArrayList) arrayMap.get(string)) == null) {
                            arrayMap.put(string, new ArrayList());
                        }
                    }
                    query.moveToPosition(-1);
                    AttendanceDao_Impl.this.__fetchRelationshipattendancePageAsorgInformatikaSirekapModelAttendancePage(arrayMap);
                    if (query.moveToFirst()) {
                        if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3) || !query.isNull(columnIndexOrThrow4) || !query.isNull(columnIndexOrThrow5) || !query.isNull(columnIndexOrThrow6) || !query.isNull(columnIndexOrThrow7)) {
                            attendance = new Attendance(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2) != 0, query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4) != 0, query.getInt(columnIndexOrThrow5) != 0, query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                        }
                        ArrayList arrayList = (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        attendanceWithPages = new AttendanceWithPages(attendance, arrayList);
                    }
                    AttendanceDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return attendanceWithPages;
                } finally {
                    AttendanceDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // org.informatika.sirekap.db.dao.AttendanceDao
    public AttendanceWithPages getByKodeTpsSync(final String kodeTps) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM attendance WHERE kodeTps = ?", 1);
        if (kodeTps == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, kodeTps);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            AttendanceWithPages attendanceWithPages = null;
            Attendance attendance = null;
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "isPdf");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "uploadPdfStatus");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "isUploadedPdf");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isUploadedPdfOffline");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "pdfFilePath");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "pdfFileHash");
            ArrayMap<String, ArrayList<AttendancePage>> arrayMap = new ArrayMap<>();
            while (query.moveToNext()) {
                String string = query.getString(columnIndexOrThrow);
                if (arrayMap.get(string) == null) {
                    arrayMap.put(string, new ArrayList<>());
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipattendancePageAsorgInformatikaSirekapModelAttendancePage(arrayMap);
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3) || !query.isNull(columnIndexOrThrow4) || !query.isNull(columnIndexOrThrow5) || !query.isNull(columnIndexOrThrow6) || !query.isNull(columnIndexOrThrow7)) {
                    attendance = new Attendance(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2) != 0, query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4) != 0, query.getInt(columnIndexOrThrow5) != 0, query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                }
                ArrayList<AttendancePage> arrayList = arrayMap.get(query.getString(columnIndexOrThrow));
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                attendanceWithPages = new AttendanceWithPages(attendance, arrayList);
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return attendanceWithPages;
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public void __fetchRelationshipattendancePageAsorgInformatikaSirekapModelAttendancePage(final ArrayMap<String, ArrayList<AttendancePage>> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<AttendancePage>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipattendancePageAsorgInformatikaSirekapModelAttendancePage(arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipattendancePageAsorgInformatikaSirekapModelAttendancePage(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `id`,`kodeTps`,`photoPath`,`croppedPhotoPath`,`urutan`,`hashDocumentCropped`,`checked` FROM `attendance_page` WHERE `kodeTps` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "kodeTps");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, JobType.ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "photoPath");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "croppedPhotoPath");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "urutan");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "hashDocumentCropped");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "checked");
            while (query.moveToNext()) {
                ArrayList<AttendancePage> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(new AttendancePage(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), query.getInt(columnIndexOrThrow7) != 0));
                }
            }
        } finally {
            query.close();
        }
    }
}
