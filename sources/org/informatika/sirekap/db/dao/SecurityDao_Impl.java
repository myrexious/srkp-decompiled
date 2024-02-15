package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.informatika.sirekap.model.SecurityProperties;

/* loaded from: classes2.dex */
public final class SecurityDao_Impl implements SecurityDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SecurityProperties> __insertionAdapterOfSecurityProperties;
    private final SharedSQLiteStatement __preparedStmtOfDeleteValue;
    private final SharedSQLiteStatement __preparedStmtOfUpdateValue;

    public SecurityDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSecurityProperties = new EntityInsertionAdapter<SecurityProperties>(__db) { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `security_properties` (`key`,`value`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SecurityProperties value) {
                if (value.getKey() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKey());
                }
                if (value.getValue() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getValue());
                }
            }
        };
        this.__preparedStmtOfUpdateValue = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE security_properties SET `value` = ? WHERE `key` = ?";
            }
        };
        this.__preparedStmtOfDeleteValue = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM security_properties WHERE `key` = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.SecurityDao
    public Object insertValue(final SecurityProperties[] securityProperties, final Continuation<? super Unit> arg1) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SecurityDao_Impl.this.__db.beginTransaction();
                try {
                    SecurityDao_Impl.this.__insertionAdapterOfSecurityProperties.insert((Object[]) securityProperties);
                    SecurityDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SecurityDao_Impl.this.__db.endTransaction();
                }
            }
        }, arg1);
    }

    @Override // org.informatika.sirekap.db.dao.SecurityDao
    public Object updateValue(final String key, final String value, final Continuation<? super Unit> arg2) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.5
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SecurityDao_Impl.this.__preparedStmtOfUpdateValue.acquire();
                String str = value;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                String str2 = key;
                if (str2 == null) {
                    acquire.bindNull(2);
                } else {
                    acquire.bindString(2, str2);
                }
                SecurityDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SecurityDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SecurityDao_Impl.this.__db.endTransaction();
                    SecurityDao_Impl.this.__preparedStmtOfUpdateValue.release(acquire);
                }
            }
        }, arg2);
    }

    @Override // org.informatika.sirekap.db.dao.SecurityDao
    public Object deleteValue(final String key, final Continuation<? super Unit> arg1) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SecurityDao_Impl.this.__preparedStmtOfDeleteValue.acquire();
                String str = key;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                SecurityDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SecurityDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SecurityDao_Impl.this.__db.endTransaction();
                    SecurityDao_Impl.this.__preparedStmtOfDeleteValue.release(acquire);
                }
            }
        }, arg1);
    }

    @Override // org.informatika.sirekap.db.dao.SecurityDao
    public Object getValue(final String key, final Continuation<? super SecurityProperties> arg1) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM security_properties WHERE `key` = ?", 1);
        if (key == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, key);
        }
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<SecurityProperties>() { // from class: org.informatika.sirekap.db.dao.SecurityDao_Impl.7
            @Override // java.util.concurrent.Callable
            public SecurityProperties call() throws Exception {
                SecurityProperties securityProperties = null;
                String string = null;
                Cursor query = DBUtil.query(SecurityDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "value");
                    if (query.moveToFirst()) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        if (!query.isNull(columnIndexOrThrow2)) {
                            string = query.getString(columnIndexOrThrow2);
                        }
                        securityProperties = new SecurityProperties(string2, string);
                    }
                    return securityProperties;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, arg1);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
