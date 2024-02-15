package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.ElectionPageStage;

/* loaded from: classes2.dex */
public final class ElectionPageStageDao_Impl implements ElectionPageStageDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<ElectionPageStage> __insertionAdapterOfElectionPageStage;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllByElectionPageId;
    private final SharedSQLiteStatement __preparedStmtOfFinishCreatePdf;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatusIsOffline;

    public ElectionPageStageDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfElectionPageStage = new EntityInsertionAdapter<ElectionPageStage>(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageStageDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `election_page_stage` (`electionId`,`electionPageId`,`type`,`status`,`isOffline`) VALUES (?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ElectionPageStage value) {
                if (value.getElectionId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getElectionId());
                }
                if (value.getElectionPageId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getElectionPageId());
                }
                if (value.getType() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getType());
                }
                stmt.bindLong(4, value.getStatus());
                stmt.bindLong(5, value.isOffline() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfDeleteAllByElectionPageId = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageStageDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM election_page_stage WHERE electionPageId = ?";
            }
        };
        this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageStageDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page_stage SET status = ?, isOffline = 0 WHERE electionPageId = ? AND type = ?";
            }
        };
        this.__preparedStmtOfUpdateStatusIsOffline = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageStageDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page_stage SET status = ?, isOffline = 1 WHERE electionPageId = ? AND type = ?";
            }
        };
        this.__preparedStmtOfFinishCreatePdf = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageStageDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page_stage SET status = 1 WHERE electionId = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageStageDao
    public void insertAll(final List<ElectionPageStage> electionPageStages) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfElectionPageStage.insert(electionPageStages);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageStageDao
    public void deleteAllByElectionPageId(final String electionPageId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAllByElectionPageId.acquire();
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
            this.__preparedStmtOfDeleteAllByElectionPageId.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageStageDao
    public void updateStatus(final int status, final String electionPageId, final String type) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateStatus.acquire();
        acquire.bindLong(1, status);
        if (electionPageId == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, electionPageId);
        }
        if (type == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, type);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStatus.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageStageDao
    public void updateStatusIsOffline(final int status, final String electionPageId, final String type) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateStatusIsOffline.acquire();
        acquire.bindLong(1, status);
        if (electionPageId == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, electionPageId);
        }
        if (type == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, type);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStatusIsOffline.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageStageDao
    public void finishCreatePdf(final String electionId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishCreatePdf.acquire();
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
            this.__preparedStmtOfFinishCreatePdf.release(acquire);
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
