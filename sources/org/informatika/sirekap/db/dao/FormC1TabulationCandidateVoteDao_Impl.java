package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;

/* loaded from: classes2.dex */
public final class FormC1TabulationCandidateVoteDao_Impl implements FormC1TabulationCandidateVoteDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1TabulationCandidateVote> __insertionAdapterOfFormC1TabulationCandidateVote;

    public FormC1TabulationCandidateVoteDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1TabulationCandidateVote = new EntityInsertionAdapter<FormC1TabulationCandidateVote>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1TabulationCandidateVoteDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_tabulation_candidate_vote` (`idImage`,`index`,`vote`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1TabulationCandidateVote value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.getIndex());
                stmt.bindLong(3, value.getVote());
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1TabulationCandidateVoteDao
    public void insertAll(final List<FormC1TabulationCandidateVote> votes) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1TabulationCandidateVote.insert(votes);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
