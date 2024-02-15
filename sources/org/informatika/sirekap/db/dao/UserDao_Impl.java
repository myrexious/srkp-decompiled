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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.Kecamatan;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.KotaKabupaten;
import org.informatika.sirekap.model.Provinsi;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.User;

/* loaded from: classes2.dex */
public final class UserDao_Impl implements UserDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<User> __insertionAdapterOfUser;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;

    public UserDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) { // from class: org.informatika.sirekap.db.dao.UserDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `users` (`id`,`fullName`,`role`,`namaProfil`,`profilStart`,`profilEnd`,`tps_kodeTps`,`tps_name`,`tps_kelurahan_id`,`tps_kelurahan_name`,`tps_kelurahan_kecamatan_id`,`tps_kelurahan_kecamatan_name`,`tps_kelurahan_kecamatan_kotaKabupaten_id`,`tps_kelurahan_kecamatan_kotaKabupaten_name`,`tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id`,`tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, User value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getFullName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getFullName());
                }
                if (value.getRole() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getRole());
                }
                if (value.getNamaProfil() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getNamaProfil());
                }
                if (value.getProfilStart() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getProfilStart());
                }
                if (value.getProfilEnd() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getProfilEnd());
                }
                Tps tps = value.getTps();
                if (tps != null) {
                    if (tps.getKodeTps() == null) {
                        stmt.bindNull(7);
                    } else {
                        stmt.bindString(7, tps.getKodeTps());
                    }
                    if (tps.getName() == null) {
                        stmt.bindNull(8);
                    } else {
                        stmt.bindString(8, tps.getName());
                    }
                    Kelurahan kelurahan = tps.getKelurahan();
                    if (kelurahan != null) {
                        if (kelurahan.getId() == null) {
                            stmt.bindNull(9);
                        } else {
                            stmt.bindString(9, kelurahan.getId());
                        }
                        if (kelurahan.getName() == null) {
                            stmt.bindNull(10);
                        } else {
                            stmt.bindString(10, kelurahan.getName());
                        }
                        Kecamatan parent = kelurahan.getParent();
                        if (parent != null) {
                            if (parent.getId() == null) {
                                stmt.bindNull(11);
                            } else {
                                stmt.bindString(11, parent.getId());
                            }
                            if (parent.getName() == null) {
                                stmt.bindNull(12);
                            } else {
                                stmt.bindString(12, parent.getName());
                            }
                            KotaKabupaten parent2 = parent.getParent();
                            if (parent2 != null) {
                                if (parent2.getId() == null) {
                                    stmt.bindNull(13);
                                } else {
                                    stmt.bindString(13, parent2.getId());
                                }
                                if (parent2.getName() == null) {
                                    stmt.bindNull(14);
                                } else {
                                    stmt.bindString(14, parent2.getName());
                                }
                                Provinsi parent3 = parent2.getParent();
                                if (parent3 != null) {
                                    if (parent3.getId() == null) {
                                        stmt.bindNull(15);
                                    } else {
                                        stmt.bindString(15, parent3.getId());
                                    }
                                    if (parent3.getName() == null) {
                                        stmt.bindNull(16);
                                        return;
                                    } else {
                                        stmt.bindString(16, parent3.getName());
                                        return;
                                    }
                                }
                                stmt.bindNull(15);
                                stmt.bindNull(16);
                                return;
                            }
                            stmt.bindNull(13);
                            stmt.bindNull(14);
                            stmt.bindNull(15);
                            stmt.bindNull(16);
                            return;
                        }
                        stmt.bindNull(11);
                        stmt.bindNull(12);
                        stmt.bindNull(13);
                        stmt.bindNull(14);
                        stmt.bindNull(15);
                        stmt.bindNull(16);
                        return;
                    }
                    stmt.bindNull(9);
                    stmt.bindNull(10);
                    stmt.bindNull(11);
                    stmt.bindNull(12);
                    stmt.bindNull(13);
                    stmt.bindNull(14);
                    stmt.bindNull(15);
                    stmt.bindNull(16);
                    return;
                }
                stmt.bindNull(7);
                stmt.bindNull(8);
                stmt.bindNull(9);
                stmt.bindNull(10);
                stmt.bindNull(11);
                stmt.bindNull(12);
                stmt.bindNull(13);
                stmt.bindNull(14);
                stmt.bindNull(15);
                stmt.bindNull(16);
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UserDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM users";
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.UserDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM users WHERE id = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.UserDao
    public void insertAll(final List<User> users) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUser.insert(users);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.UserDao
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

    @Override // org.informatika.sirekap.db.dao.UserDao
    public void deleteById(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.UserDao
    public LiveData<User> getById(final String id2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM users WHERE id = ?", 1);
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"users"}, true, new Callable<User>() { // from class: org.informatika.sirekap.db.dao.UserDao_Impl.4
            @Override // java.util.concurrent.Callable
            public User call() throws Exception {
                User user;
                int i;
                Provinsi provinsi;
                KotaKabupaten kotaKabupaten;
                Kecamatan kecamatan;
                Kelurahan kelurahan;
                Tps tps;
                UserDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(UserDao_Impl.this.__db, acquire, false, null);
                    try {
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, JobType.ID);
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "fullName");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "role");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "namaProfil");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "profilStart");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "profilEnd");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "tps_kodeTps");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "tps_name");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_id");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_name");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_kecamatan_id");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_kecamatan_name");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_kecamatan_kotaKabupaten_id");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_kecamatan_kotaKabupaten_name");
                        try {
                            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id");
                            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name");
                            if (query.moveToFirst()) {
                                String string = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                                String string2 = query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2);
                                String string3 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                                String string4 = query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4);
                                String string5 = query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5);
                                String string6 = query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6);
                                if (query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13) && query.isNull(columnIndexOrThrow14)) {
                                    i = columnIndexOrThrow15;
                                    if (query.isNull(i) && query.isNull(columnIndexOrThrow16)) {
                                        tps = null;
                                        user = new User(string, string2, tps, string3, string4, string5, string6);
                                    }
                                } else {
                                    i = columnIndexOrThrow15;
                                }
                                String string7 = query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7);
                                String string8 = query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8);
                                if (query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13) && query.isNull(columnIndexOrThrow14) && query.isNull(i) && query.isNull(columnIndexOrThrow16)) {
                                    kelurahan = null;
                                    tps = new Tps(string7, string8, kelurahan);
                                    user = new User(string, string2, tps, string3, string4, string5, string6);
                                }
                                String string9 = query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9);
                                String string10 = query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10);
                                if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13) && query.isNull(columnIndexOrThrow14) && query.isNull(i) && query.isNull(columnIndexOrThrow16)) {
                                    kecamatan = null;
                                    kelurahan = new Kelurahan(string9, string10, kecamatan);
                                    tps = new Tps(string7, string8, kelurahan);
                                    user = new User(string, string2, tps, string3, string4, string5, string6);
                                }
                                String string11 = query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11);
                                String string12 = query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12);
                                if (query.isNull(columnIndexOrThrow13) && query.isNull(columnIndexOrThrow14) && query.isNull(i) && query.isNull(columnIndexOrThrow16)) {
                                    kotaKabupaten = null;
                                    kecamatan = new Kecamatan(string11, string12, kotaKabupaten);
                                    kelurahan = new Kelurahan(string9, string10, kecamatan);
                                    tps = new Tps(string7, string8, kelurahan);
                                    user = new User(string, string2, tps, string3, string4, string5, string6);
                                }
                                String string13 = query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13);
                                String string14 = query.isNull(columnIndexOrThrow14) ? null : query.getString(columnIndexOrThrow14);
                                if (query.isNull(i) && query.isNull(columnIndexOrThrow16)) {
                                    provinsi = null;
                                    kotaKabupaten = new KotaKabupaten(string13, string14, provinsi);
                                    kecamatan = new Kecamatan(string11, string12, kotaKabupaten);
                                    kelurahan = new Kelurahan(string9, string10, kecamatan);
                                    tps = new Tps(string7, string8, kelurahan);
                                    user = new User(string, string2, tps, string3, string4, string5, string6);
                                }
                                provinsi = new Provinsi(query.isNull(i) ? null : query.getString(i), query.isNull(columnIndexOrThrow16) ? null : query.getString(columnIndexOrThrow16));
                                kotaKabupaten = new KotaKabupaten(string13, string14, provinsi);
                                kecamatan = new Kecamatan(string11, string12, kotaKabupaten);
                                kelurahan = new Kelurahan(string9, string10, kecamatan);
                                tps = new Tps(string7, string8, kelurahan);
                                user = new User(string, string2, tps, string3, string4, string5, string6);
                            } else {
                                user = null;
                            }
                            UserDao_Impl.this.__db.setTransactionSuccessful();
                            query.close();
                            return user;
                        } catch (Throwable th) {
                            th = th;
                            query.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } finally {
                    UserDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // org.informatika.sirekap.db.dao.UserDao
    public LiveData<List<User>> getAll() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM users", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"users"}, true, new Callable<List<User>>() { // from class: org.informatika.sirekap.db.dao.UserDao_Impl.5
            /* JADX WARN: Removed duplicated region for block: B:239:0x0159  */
            /* JADX WARN: Removed duplicated region for block: B:240:0x015d A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:243:0x016b  */
            /* JADX WARN: Removed duplicated region for block: B:244:0x016f A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:247:0x017d A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:265:0x01c3  */
            /* JADX WARN: Removed duplicated region for block: B:266:0x01c7 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:269:0x01d5  */
            /* JADX WARN: Removed duplicated region for block: B:270:0x01d9 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:273:0x01e7 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:287:0x021d  */
            /* JADX WARN: Removed duplicated region for block: B:288:0x0221 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:291:0x022f  */
            /* JADX WARN: Removed duplicated region for block: B:292:0x0233 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:295:0x0241 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:305:0x0267  */
            /* JADX WARN: Removed duplicated region for block: B:306:0x026b A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:309:0x0279  */
            /* JADX WARN: Removed duplicated region for block: B:310:0x027d A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:313:0x028b A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:319:0x02a0  */
            /* JADX WARN: Removed duplicated region for block: B:320:0x02a4 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            /* JADX WARN: Removed duplicated region for block: B:323:0x02b2  */
            /* JADX WARN: Removed duplicated region for block: B:324:0x02b8 A[Catch: all -> 0x031d, TryCatch #4 {all -> 0x031d, blocks: (B:185:0x006d, B:186:0x0088, B:188:0x008e, B:192:0x009d, B:196:0x00ac, B:200:0x00bb, B:204:0x00ca, B:208:0x00d9, B:212:0x00e8, B:214:0x00ee, B:216:0x00f4, B:218:0x00fa, B:220:0x0100, B:222:0x0106, B:224:0x010c, B:226:0x0112, B:228:0x011a, B:230:0x0124, B:237:0x0153, B:241:0x0165, B:245:0x0177, B:247:0x017d, B:249:0x0183, B:251:0x0189, B:253:0x018f, B:255:0x0195, B:257:0x019b, B:259:0x01a1, B:329:0x02d6, B:330:0x02dd, B:263:0x01bd, B:267:0x01cf, B:271:0x01e1, B:273:0x01e7, B:275:0x01ed, B:277:0x01f3, B:279:0x01f9, B:281:0x01ff, B:328:0x02d1, B:285:0x0217, B:289:0x0229, B:293:0x023b, B:295:0x0241, B:297:0x0247, B:299:0x024d, B:327:0x02cc, B:303:0x0261, B:307:0x0273, B:311:0x0285, B:313:0x028b, B:326:0x02c7, B:317:0x029a, B:321:0x02ac, B:325:0x02c2, B:324:0x02b8, B:320:0x02a4, B:310:0x027d, B:306:0x026b, B:292:0x0233, B:288:0x0221, B:270:0x01d9, B:266:0x01c7, B:244:0x016f, B:240:0x015d, B:211:0x00e2, B:207:0x00d3, B:203:0x00c4, B:199:0x00b5, B:195:0x00a6, B:191:0x0097), top: B:357:0x006d }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<org.informatika.sirekap.model.User> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 821
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.UserDao_Impl.AnonymousClass5.call():java.util.List");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:248:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0160 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0172 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0180 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x01ca A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x01dc A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x01ea A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0224 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0236 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0244 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x026e A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0280 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x028e A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:328:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x02a7 A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:332:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x02bb A[Catch: all -> 0x031b, TryCatch #2 {all -> 0x031b, blocks: (B:194:0x0070, B:195:0x008b, B:197:0x0091, B:201:0x00a0, B:205:0x00af, B:209:0x00be, B:213:0x00cd, B:217:0x00dc, B:221:0x00eb, B:223:0x00f1, B:225:0x00f7, B:227:0x00fd, B:229:0x0103, B:231:0x0109, B:233:0x010f, B:235:0x0115, B:237:0x011d, B:239:0x0127, B:246:0x0156, B:250:0x0168, B:254:0x017a, B:256:0x0180, B:258:0x0186, B:260:0x018c, B:262:0x0192, B:264:0x0198, B:266:0x019e, B:268:0x01a4, B:338:0x02d9, B:339:0x02e0, B:272:0x01c0, B:276:0x01d2, B:280:0x01e4, B:282:0x01ea, B:284:0x01f0, B:286:0x01f6, B:288:0x01fc, B:290:0x0202, B:337:0x02d4, B:294:0x021a, B:298:0x022c, B:302:0x023e, B:304:0x0244, B:306:0x024a, B:308:0x0250, B:336:0x02cf, B:312:0x0264, B:316:0x0276, B:320:0x0288, B:322:0x028e, B:335:0x02ca, B:326:0x029d, B:330:0x02af, B:334:0x02c5, B:333:0x02bb, B:329:0x02a7, B:319:0x0280, B:315:0x026e, B:301:0x0236, B:297:0x0224, B:279:0x01dc, B:275:0x01ca, B:253:0x0172, B:249:0x0160, B:220:0x00e5, B:216:0x00d6, B:212:0x00c7, B:208:0x00b8, B:204:0x00a9, B:200:0x009a), top: B:367:0x0070 }] */
    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.room.RoomSQLiteQuery, androidx.sqlite.db.SupportSQLiteQuery] */
    /* JADX WARN: Type inference failed for: r3v1 */
    @Override // org.informatika.sirekap.db.dao.UserDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<org.informatika.sirekap.model.User> getAllSync() {
        /*
            Method dump skipped, instructions count: 822
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.UserDao_Impl.getAllSync():java.util.List");
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
