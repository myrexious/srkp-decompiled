package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.Candidate;

/* loaded from: classes2.dex */
public final class CandidateDao_Impl implements CandidateDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Candidate> __insertionAdapterOfCandidate;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllByElectionId;

    public CandidateDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCandidate = new EntityInsertionAdapter<Candidate>(__db) { // from class: org.informatika.sirekap.db.dao.CandidateDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `candidates` (`index`,`idPilihan`,`electionId`,`electionPemilihan`,`noUrutPencalonan`,`noUrutLabel`,`namaCalonKepala`,`namaCalonWakil`,`indexPartai`,`idPartai`,`namaPartai`,`noUrutPencalonanPartai`,`noUrutLabelPartai`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Candidate value) {
                stmt.bindLong(1, value.getIndex());
                stmt.bindLong(2, value.getIdPilihan());
                if (value.getElectionId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getElectionId());
                }
                if (value.getElectionPemilihan() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getElectionPemilihan());
                }
                stmt.bindLong(5, value.getNoUrutPencalonan());
                stmt.bindLong(6, value.getNoUrutLabel());
                if (value.getNamaCalonKepala() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getNamaCalonKepala());
                }
                if (value.getNamaCalonWakil() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getNamaCalonWakil());
                }
                if (value.getIndexPartai() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, value.getIndexPartai().intValue());
                }
                if (value.getIdPartai() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindLong(10, value.getIdPartai().longValue());
                }
                if (value.getNamaPartai() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getNamaPartai());
                }
                if (value.getNoUrutPencalonanPartai() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindLong(12, value.getNoUrutPencalonanPartai().intValue());
                }
                if (value.getNoUrutLabelPartai() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindLong(13, value.getNoUrutLabelPartai().intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.CandidateDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM candidates";
            }
        };
        this.__preparedStmtOfDeleteAllByElectionId = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.CandidateDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM candidates WHERE electionId = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.CandidateDao
    public void insertAll(final List<Candidate> candidates) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCandidate.insert(candidates);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.CandidateDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.CandidateDao
    public void deleteAllByElectionId(final String electionId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAllByElectionId.acquire();
        if (electionId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionId);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllByElectionId.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.CandidateDao
    public Candidate getOneByIndexPartai(final String electionId, final int indexPartai) {
        Candidate candidate;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM candidates WHERE electionId = ? AND indexPartai = ? ORDER BY `index` ASC LIMIT 1", 2);
        if (electionId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionId);
        }
        acquire.bindLong(2, indexPartai);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.INDEX);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "idPilihan");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "electionId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "electionPemilihan");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "noUrutPencalonan");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "noUrutLabel");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "namaCalonKepala");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "namaCalonWakil");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "indexPartai");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "idPartai");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "namaPartai");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "noUrutPencalonanPartai");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "noUrutLabelPartai");
            if (query.moveToFirst()) {
                candidate = new Candidate(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7), query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8), query.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow9)), query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)), query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11), query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)), query.isNull(columnIndexOrThrow13) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow13)));
            } else {
                candidate = null;
            }
            return candidate;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // org.informatika.sirekap.db.dao.CandidateDao
    public List<Candidate> getAllByIndexPartai(final String electionId, final int indexPartai) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM candidates WHERE electionId = ? AND indexPartai = ? ORDER BY `index` ASC", 2);
        if (electionId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionId);
        }
        acquire.bindLong(2, indexPartai);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.INDEX);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "idPilihan");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "electionId");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "electionPemilihan");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "noUrutPencalonan");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "noUrutLabel");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "namaCalonKepala");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "namaCalonWakil");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "indexPartai");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "idPartai");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "namaPartai");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "noUrutPencalonanPartai");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "noUrutLabelPartai");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new Candidate(query.getInt(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7), query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8), query.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow9)), query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)), query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11), query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)), query.isNull(columnIndexOrThrow13) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow13))));
            }
            query.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
