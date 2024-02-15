package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;

/* loaded from: classes2.dex */
public final class FormC1KesesuaianAdministrationHal2Dao_Impl implements FormC1KesesuaianAdministrationHal2Dao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1KesesuaianAdministrationHal2> __insertionAdapterOfFormC1KesesuaianAdministrationHal2;
    private final EntityInsertionAdapter<FormC1KesesuaianAdministrationHal2> __insertionAdapterOfFormC1KesesuaianAdministrationHal2_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;

    public FormC1KesesuaianAdministrationHal2Dao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1KesesuaianAdministrationHal2 = new EntityInsertionAdapter<FormC1KesesuaianAdministrationHal2>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_kesesuaian_administration_hal2` (`idImage`,`suratSuaraSah`,`suratSuaraTidakSah`,`totalSuratSuara`,`suratSuaraSahCorrected`,`suratSuaraTidakSahCorrected`,`totalSuratSuaraCorrected`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianAdministrationHal2 value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                Integer valueOf = value.getSuratSuaraSah() == null ? null : Integer.valueOf(value.getSuratSuaraSah().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindLong(2, valueOf.intValue());
                }
                Integer valueOf2 = value.getSuratSuaraTidakSah() == null ? null : Integer.valueOf(value.getSuratSuaraTidakSah().booleanValue() ? 1 : 0);
                if (valueOf2 == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, valueOf2.intValue());
                }
                Integer valueOf3 = value.getTotalSuratSuara() != null ? Integer.valueOf(value.getTotalSuratSuara().booleanValue() ? 1 : 0) : null;
                if (valueOf3 == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, valueOf3.intValue());
                }
                if (value.getSuratSuaraSahCorrected() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, value.getSuratSuaraSahCorrected().intValue());
                }
                if (value.getSuratSuaraTidakSahCorrected() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindLong(6, value.getSuratSuaraTidakSahCorrected().intValue());
                }
                if (value.getTotalSuratSuaraCorrected() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindLong(7, value.getTotalSuratSuaraCorrected().intValue());
                }
            }
        };
        this.__insertionAdapterOfFormC1KesesuaianAdministrationHal2_1 = new EntityInsertionAdapter<FormC1KesesuaianAdministrationHal2>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `form_c1_kesesuaian_administration_hal2` (`idImage`,`suratSuaraSah`,`suratSuaraTidakSah`,`totalSuratSuara`,`suratSuaraSahCorrected`,`suratSuaraTidakSahCorrected`,`totalSuratSuaraCorrected`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianAdministrationHal2 value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                Integer valueOf = value.getSuratSuaraSah() == null ? null : Integer.valueOf(value.getSuratSuaraSah().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindLong(2, valueOf.intValue());
                }
                Integer valueOf2 = value.getSuratSuaraTidakSah() == null ? null : Integer.valueOf(value.getSuratSuaraTidakSah().booleanValue() ? 1 : 0);
                if (valueOf2 == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, valueOf2.intValue());
                }
                Integer valueOf3 = value.getTotalSuratSuara() != null ? Integer.valueOf(value.getTotalSuratSuara().booleanValue() ? 1 : 0) : null;
                if (valueOf3 == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, valueOf3.intValue());
                }
                if (value.getSuratSuaraSahCorrected() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, value.getSuratSuaraSahCorrected().intValue());
                }
                if (value.getSuratSuaraTidakSahCorrected() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindLong(6, value.getSuratSuaraTidakSahCorrected().intValue());
                }
                if (value.getTotalSuratSuaraCorrected() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindLong(7, value.getTotalSuratSuaraCorrected().intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM form_c1_kesesuaian_administration_hal2 WHERE idImage = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao
    public void insertAllReplace(final List<FormC1KesesuaianAdministrationHal2> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianAdministrationHal2.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao
    public void insertAll(final List<FormC1KesesuaianAdministrationHal2> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianAdministrationHal2_1.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao
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
