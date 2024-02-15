package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.informatika.sirekap.model.TpsTime;

/* loaded from: classes2.dex */
public final class TpsTimeDao_Impl implements TpsTimeDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<TpsTime> __insertionAdapterOfTpsTime;

    public TpsTimeDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTpsTime = new EntityInsertionAdapter<TpsTime>(__db) { // from class: org.informatika.sirekap.db.dao.TpsTimeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `tps_time` (`kodeTps`,`jenisWaktu`,`jenisPemilihan`,`startDate`,`startTimeHour`,`startTimeMinute`,`endDate`,`endTimeHour`,`endTimeMinute`,`isSubmittedOffline`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, TpsTime value) {
                if (value.getKodeTps() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKodeTps());
                }
                stmt.bindLong(2, value.getJenisWaktu());
                if (value.getJenisPemilihan() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getJenisPemilihan());
                }
                stmt.bindLong(4, value.getStartDate());
                stmt.bindLong(5, value.getStartTimeHour());
                stmt.bindLong(6, value.getStartTimeMinute());
                stmt.bindLong(7, value.getEndDate());
                stmt.bindLong(8, value.getEndTimeHour());
                stmt.bindLong(9, value.getEndTimeMinute());
                stmt.bindLong(10, value.isSubmittedOffline() ? 1L : 0L);
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.TpsTimeDao
    public void insertAll(final List<TpsTime> tpsTimes) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTpsTime.insert(tpsTimes);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.TpsTimeDao
    public LiveData<TpsTime> getByKodeTps(final String kodeTps, final int jenisWaktu, final String jenisPemilihan) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tps_time WHERE kodeTps = ? AND jenisWaktu = ? AND jenisPemilihan = ?", 3);
        if (kodeTps == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, kodeTps);
        }
        acquire.bindLong(2, jenisWaktu);
        if (jenisPemilihan == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, jenisPemilihan);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"tps_time"}, true, new Callable<TpsTime>() { // from class: org.informatika.sirekap.db.dao.TpsTimeDao_Impl.2
            @Override // java.util.concurrent.Callable
            public TpsTime call() throws Exception {
                TpsTimeDao_Impl.this.__db.beginTransaction();
                try {
                    TpsTime tpsTime = null;
                    Cursor query = DBUtil.query(TpsTimeDao_Impl.this.__db, acquire, false, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "jenisWaktu");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemilihan");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "startDate");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "startTimeHour");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "startTimeMinute");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "endDate");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "endTimeHour");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "endTimeMinute");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isSubmittedOffline");
                    if (query.moveToFirst()) {
                        tpsTime = new TpsTime(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getLong(columnIndexOrThrow7), query.getInt(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9), query.getInt(columnIndexOrThrow10) != 0);
                    }
                    TpsTimeDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return tpsTime;
                } finally {
                    TpsTimeDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // org.informatika.sirekap.db.dao.TpsTimeDao
    public TpsTime getByKodeTpsSync(final String kodeTps, final int jenisWaktu, final String jenisPemilihan) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tps_time WHERE kodeTps = ? AND jenisWaktu = ? AND jenisPemilihan = ?", 3);
        if (kodeTps == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, kodeTps);
        }
        acquire.bindLong(2, jenisWaktu);
        if (jenisPemilihan == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, jenisPemilihan);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            TpsTime tpsTime = null;
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "jenisWaktu");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemilihan");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "startDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "startTimeHour");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "startTimeMinute");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "endDate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "endTimeHour");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "endTimeMinute");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isSubmittedOffline");
            if (query.moveToFirst()) {
                tpsTime = new TpsTime(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getLong(columnIndexOrThrow7), query.getInt(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9), query.getInt(columnIndexOrThrow10) != 0);
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return tpsTime;
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
