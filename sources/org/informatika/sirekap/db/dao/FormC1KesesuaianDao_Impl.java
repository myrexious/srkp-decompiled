package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1Kesesuaian;

/* loaded from: classes2.dex */
public final class FormC1KesesuaianDao_Impl implements FormC1KesesuaianDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1Kesesuaian> __insertionAdapterOfFormC1Kesesuaian;

    public FormC1KesesuaianDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1Kesesuaian = new EntityInsertionAdapter<FormC1Kesesuaian>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_kesesuaian` (`idImage`,`isSesuai`,`komentar`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1Kesesuaian value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.isSesuai() ? 1L : 0L);
                if (value.getKomentar() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getKomentar());
                }
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianDao
    public void insertAll(final List<FormC1Kesesuaian> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1Kesesuaian.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
