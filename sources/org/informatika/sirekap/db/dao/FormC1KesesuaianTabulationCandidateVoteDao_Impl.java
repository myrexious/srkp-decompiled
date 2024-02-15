package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;

/* loaded from: classes2.dex */
public final class FormC1KesesuaianTabulationCandidateVoteDao_Impl implements FormC1KesesuaianTabulationCandidateVoteDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1KesesuaianTabulationCandidateVote> __insertionAdapterOfFormC1KesesuaianTabulationCandidateVote;
    private final EntityInsertionAdapter<FormC1KesesuaianTabulationCandidateVote> __insertionAdapterOfFormC1KesesuaianTabulationCandidateVote_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;

    public FormC1KesesuaianTabulationCandidateVoteDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1KesesuaianTabulationCandidateVote = new EntityInsertionAdapter<FormC1KesesuaianTabulationCandidateVote>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_kesesuaian_tabulation_candidate_vote` (`idImage`,`index`,`vote`,`voteCorrected`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianTabulationCandidateVote value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.getIndex());
                Integer valueOf = value.getVote() == null ? null : Integer.valueOf(value.getVote().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, valueOf.intValue());
                }
                if (value.getVoteCorrected() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, value.getVoteCorrected().intValue());
                }
            }
        };
        this.__insertionAdapterOfFormC1KesesuaianTabulationCandidateVote_1 = new EntityInsertionAdapter<FormC1KesesuaianTabulationCandidateVote>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `form_c1_kesesuaian_tabulation_candidate_vote` (`idImage`,`index`,`vote`,`voteCorrected`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianTabulationCandidateVote value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.getIndex());
                Integer valueOf = value.getVote() == null ? null : Integer.valueOf(value.getVote().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, valueOf.intValue());
                }
                if (value.getVoteCorrected() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, value.getVoteCorrected().intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM form_c1_kesesuaian_tabulation_candidate_vote WHERE idImage = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao
    public void insertAllReplace(final List<FormC1KesesuaianTabulationCandidateVote> votes) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianTabulationCandidateVote.insert(votes);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao
    public void insertAll(final List<FormC1KesesuaianTabulationCandidateVote> votes) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianTabulationCandidateVote_1.insert(votes);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao
    public void deleteBy(final String idImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy.acquire();
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
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

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
