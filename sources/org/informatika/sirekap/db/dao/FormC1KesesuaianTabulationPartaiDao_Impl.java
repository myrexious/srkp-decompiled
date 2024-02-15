package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai;

/* loaded from: classes2.dex */
public final class FormC1KesesuaianTabulationPartaiDao_Impl implements FormC1KesesuaianTabulationPartaiDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1KesesuaianTabulationPartai> __insertionAdapterOfFormC1KesesuaianTabulationPartai;
    private final EntityInsertionAdapter<FormC1KesesuaianTabulationPartai> __insertionAdapterOfFormC1KesesuaianTabulationPartai_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;

    public FormC1KesesuaianTabulationPartaiDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1KesesuaianTabulationPartai = new EntityInsertionAdapter<FormC1KesesuaianTabulationPartai>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_kesesuaian_tabulation_partai` (`idImage`,`suratSahPartaiDanCalon`,`suratSahPartaiDanCalonCorrected`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianTabulationPartai value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                Integer valueOf = value.getSuratSahPartaiDanCalon() == null ? null : Integer.valueOf(value.getSuratSahPartaiDanCalon().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindLong(2, valueOf.intValue());
                }
                if (value.getSuratSahPartaiDanCalonCorrected() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, value.getSuratSahPartaiDanCalonCorrected().intValue());
                }
            }
        };
        this.__insertionAdapterOfFormC1KesesuaianTabulationPartai_1 = new EntityInsertionAdapter<FormC1KesesuaianTabulationPartai>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `form_c1_kesesuaian_tabulation_partai` (`idImage`,`suratSahPartaiDanCalon`,`suratSahPartaiDanCalonCorrected`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianTabulationPartai value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                Integer valueOf = value.getSuratSahPartaiDanCalon() == null ? null : Integer.valueOf(value.getSuratSahPartaiDanCalon().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindLong(2, valueOf.intValue());
                }
                if (value.getSuratSahPartaiDanCalonCorrected() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, value.getSuratSahPartaiDanCalonCorrected().intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM form_c1_kesesuaian_tabulation_partai WHERE idImage = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao
    public void insertAllReplace(final List<FormC1KesesuaianTabulationPartai> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianTabulationPartai.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao
    public void insertAll(final List<FormC1KesesuaianTabulationPartai> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianTabulationPartai_1.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao
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
