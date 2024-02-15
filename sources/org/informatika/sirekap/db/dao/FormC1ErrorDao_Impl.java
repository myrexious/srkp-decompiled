package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1Error;

/* loaded from: classes2.dex */
public final class FormC1ErrorDao_Impl implements FormC1ErrorDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1Error> __insertionAdapterOfFormC1Error;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public FormC1ErrorDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1Error = new EntityInsertionAdapter<FormC1Error>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1ErrorDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_error` (`idImage`,`formType`,`errorType`,`error`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1Error value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.getFormType());
                stmt.bindLong(3, value.getErrorType());
                if (value.getError() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getError());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.FormC1ErrorDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM form_c1_error WHERE idImage = ? AND formType = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1ErrorDao
    public void insertAll(final List<FormC1Error> errors) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1Error.insert(errors);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1ErrorDao
    public void deleteAll(final String idImage, final int formType) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        acquire.bindLong(2, formType);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
