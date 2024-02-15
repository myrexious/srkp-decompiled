package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.informatika.sirekap.model.Kecamatan;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.KotaKabupaten;
import org.informatika.sirekap.model.Provinsi;
import org.informatika.sirekap.model.Tps;

/* loaded from: classes2.dex */
public final class TpsDao_Impl implements TpsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Tps> __insertionAdapterOfTps;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public TpsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTps = new EntityInsertionAdapter<Tps>(__db) { // from class: org.informatika.sirekap.db.dao.TpsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `tps` (`kodeTps`,`name`,`kelurahan_id`,`kelurahan_name`,`kelurahan_kecamatan_id`,`kelurahan_kecamatan_name`,`kelurahan_kecamatan_kotaKabupaten_id`,`kelurahan_kecamatan_kotaKabupaten_name`,`kelurahan_kecamatan_kotaKabupaten_provinsi_id`,`kelurahan_kecamatan_kotaKabupaten_provinsi_name`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Tps value) {
                if (value.getKodeTps() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKodeTps());
                }
                if (value.getName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getName());
                }
                Kelurahan kelurahan = value.getKelurahan();
                if (kelurahan != null) {
                    if (kelurahan.getId() == null) {
                        stmt.bindNull(3);
                    } else {
                        stmt.bindString(3, kelurahan.getId());
                    }
                    if (kelurahan.getName() == null) {
                        stmt.bindNull(4);
                    } else {
                        stmt.bindString(4, kelurahan.getName());
                    }
                    Kecamatan parent = kelurahan.getParent();
                    if (parent != null) {
                        if (parent.getId() == null) {
                            stmt.bindNull(5);
                        } else {
                            stmt.bindString(5, parent.getId());
                        }
                        if (parent.getName() == null) {
                            stmt.bindNull(6);
                        } else {
                            stmt.bindString(6, parent.getName());
                        }
                        KotaKabupaten parent2 = parent.getParent();
                        if (parent2 != null) {
                            if (parent2.getId() == null) {
                                stmt.bindNull(7);
                            } else {
                                stmt.bindString(7, parent2.getId());
                            }
                            if (parent2.getName() == null) {
                                stmt.bindNull(8);
                            } else {
                                stmt.bindString(8, parent2.getName());
                            }
                            Provinsi parent3 = parent2.getParent();
                            if (parent3 != null) {
                                if (parent3.getId() == null) {
                                    stmt.bindNull(9);
                                } else {
                                    stmt.bindString(9, parent3.getId());
                                }
                                if (parent3.getName() == null) {
                                    stmt.bindNull(10);
                                    return;
                                } else {
                                    stmt.bindString(10, parent3.getName());
                                    return;
                                }
                            }
                            stmt.bindNull(9);
                            stmt.bindNull(10);
                            return;
                        }
                        stmt.bindNull(7);
                        stmt.bindNull(8);
                        stmt.bindNull(9);
                        stmt.bindNull(10);
                        return;
                    }
                    stmt.bindNull(5);
                    stmt.bindNull(6);
                    stmt.bindNull(7);
                    stmt.bindNull(8);
                    stmt.bindNull(9);
                    stmt.bindNull(10);
                    return;
                }
                stmt.bindNull(3);
                stmt.bindNull(4);
                stmt.bindNull(5);
                stmt.bindNull(6);
                stmt.bindNull(7);
                stmt.bindNull(8);
                stmt.bindNull(9);
                stmt.bindNull(10);
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.TpsDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM tps";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.TpsDao
    public void insertAll(final List<Tps> tpss) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTps.insert(tpss);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.TpsDao
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

    @Override // org.informatika.sirekap.db.dao.TpsDao
    public LiveData<List<Tps>> getAll() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tps", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"tps"}, false, new Callable<List<Tps>>() { // from class: org.informatika.sirekap.db.dao.TpsDao_Impl.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public List<Tps> call() throws Exception {
                int i;
                String string;
                int i2;
                String string2;
                int i3;
                String string3;
                int i4;
                String string4;
                int i5;
                String string5;
                int i6;
                String string6;
                int i7;
                Provinsi provinsi;
                KotaKabupaten kotaKabupaten;
                Kecamatan kecamatan;
                Kelurahan kelurahan;
                String str = null;
                Cursor query = DBUtil.query(TpsDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_id");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_name");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_kecamatan_id");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_kecamatan_name");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_kecamatan_kotaKabupaten_id");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_kecamatan_kotaKabupaten_name");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_kecamatan_kotaKabupaten_provinsi_id");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "kelurahan_kecamatan_kotaKabupaten_provinsi_name");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string7 = query.isNull(columnIndexOrThrow) ? str : query.getString(columnIndexOrThrow);
                        String string8 = query.isNull(columnIndexOrThrow2) ? str : query.getString(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                            i = columnIndexOrThrow;
                            i2 = columnIndexOrThrow2;
                            kelurahan = str;
                            i3 = columnIndexOrThrow3;
                            i4 = columnIndexOrThrow4;
                            i5 = columnIndexOrThrow5;
                            i6 = columnIndexOrThrow6;
                            i7 = columnIndexOrThrow7;
                            arrayList.add(new Tps(string7, string8, kelurahan));
                            columnIndexOrThrow7 = i7;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                            columnIndexOrThrow3 = i3;
                            columnIndexOrThrow4 = i4;
                            columnIndexOrThrow5 = i5;
                            columnIndexOrThrow6 = i6;
                            str = null;
                        }
                        if (!query.isNull(columnIndexOrThrow3)) {
                            str = query.getString(columnIndexOrThrow3);
                        }
                        if (query.isNull(columnIndexOrThrow4)) {
                            i = columnIndexOrThrow;
                            string = null;
                        } else {
                            i = columnIndexOrThrow;
                            string = query.getString(columnIndexOrThrow4);
                        }
                        if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                            i2 = columnIndexOrThrow2;
                            i3 = columnIndexOrThrow3;
                            i4 = columnIndexOrThrow4;
                            i5 = columnIndexOrThrow5;
                            i6 = columnIndexOrThrow6;
                            i7 = columnIndexOrThrow7;
                            kecamatan = null;
                            kelurahan = new Kelurahan(str, string, kecamatan);
                            arrayList.add(new Tps(string7, string8, kelurahan));
                            columnIndexOrThrow7 = i7;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                            columnIndexOrThrow3 = i3;
                            columnIndexOrThrow4 = i4;
                            columnIndexOrThrow5 = i5;
                            columnIndexOrThrow6 = i6;
                            str = null;
                        }
                        String string9 = query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5);
                        if (query.isNull(columnIndexOrThrow6)) {
                            i2 = columnIndexOrThrow2;
                            string2 = null;
                        } else {
                            i2 = columnIndexOrThrow2;
                            string2 = query.getString(columnIndexOrThrow6);
                        }
                        if (query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                            i3 = columnIndexOrThrow3;
                            i4 = columnIndexOrThrow4;
                            i5 = columnIndexOrThrow5;
                            i6 = columnIndexOrThrow6;
                            i7 = columnIndexOrThrow7;
                            kotaKabupaten = null;
                            kecamatan = new Kecamatan(string9, string2, kotaKabupaten);
                            kelurahan = new Kelurahan(str, string, kecamatan);
                            arrayList.add(new Tps(string7, string8, kelurahan));
                            columnIndexOrThrow7 = i7;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                            columnIndexOrThrow3 = i3;
                            columnIndexOrThrow4 = i4;
                            columnIndexOrThrow5 = i5;
                            columnIndexOrThrow6 = i6;
                            str = null;
                        }
                        if (query.isNull(columnIndexOrThrow7)) {
                            i3 = columnIndexOrThrow3;
                            string3 = null;
                        } else {
                            i3 = columnIndexOrThrow3;
                            string3 = query.getString(columnIndexOrThrow7);
                        }
                        if (query.isNull(columnIndexOrThrow8)) {
                            i4 = columnIndexOrThrow4;
                            string4 = null;
                        } else {
                            i4 = columnIndexOrThrow4;
                            string4 = query.getString(columnIndexOrThrow8);
                        }
                        if (query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                            i5 = columnIndexOrThrow5;
                            i6 = columnIndexOrThrow6;
                            i7 = columnIndexOrThrow7;
                            provinsi = null;
                            kotaKabupaten = new KotaKabupaten(string3, string4, provinsi);
                            kecamatan = new Kecamatan(string9, string2, kotaKabupaten);
                            kelurahan = new Kelurahan(str, string, kecamatan);
                            arrayList.add(new Tps(string7, string8, kelurahan));
                            columnIndexOrThrow7 = i7;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                            columnIndexOrThrow3 = i3;
                            columnIndexOrThrow4 = i4;
                            columnIndexOrThrow5 = i5;
                            columnIndexOrThrow6 = i6;
                            str = null;
                        }
                        if (query.isNull(columnIndexOrThrow9)) {
                            i5 = columnIndexOrThrow5;
                            string5 = null;
                        } else {
                            i5 = columnIndexOrThrow5;
                            string5 = query.getString(columnIndexOrThrow9);
                        }
                        if (query.isNull(columnIndexOrThrow10)) {
                            i6 = columnIndexOrThrow6;
                            i7 = columnIndexOrThrow7;
                            string6 = null;
                        } else {
                            i6 = columnIndexOrThrow6;
                            string6 = query.getString(columnIndexOrThrow10);
                            i7 = columnIndexOrThrow7;
                        }
                        provinsi = new Provinsi(string5, string6);
                        kotaKabupaten = new KotaKabupaten(string3, string4, provinsi);
                        kecamatan = new Kecamatan(string9, string2, kotaKabupaten);
                        kelurahan = new Kelurahan(str, string, kecamatan);
                        arrayList.add(new Tps(string7, string8, kelurahan));
                        columnIndexOrThrow7 = i7;
                        columnIndexOrThrow = i;
                        columnIndexOrThrow2 = i2;
                        columnIndexOrThrow3 = i3;
                        columnIndexOrThrow4 = i4;
                        columnIndexOrThrow5 = i5;
                        columnIndexOrThrow6 = i6;
                        str = null;
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
