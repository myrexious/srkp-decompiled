package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessShare;
import org.informatika.sirekap.model.WitnessWithShare;

/* loaded from: classes2.dex */
public final class WitnessDao_Impl implements WitnessDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Witness> __insertionAdapterOfWitness;
    private final EntityInsertionAdapter<WitnessPemeriksa> __insertionAdapterOfWitnessPemeriksa;
    private final EntityInsertionAdapter<WitnessShare> __insertionAdapterOfWitnessShare;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllOnline;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByNoHandphone;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalBy;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsSharedNoHandphone;
    private final SharedSQLiteStatement __preparedStmtOfMarkWitnessPemeriksaSynced;
    private final SharedSQLiteStatement __preparedStmtOfMarkWitnessSynced;

    public WitnessDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWitness = new EntityInsertionAdapter<Witness>(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `witnesses` (`idPetugas`,`nama`,`nik`,`noHandphone`,`jenisPemeriksa`,`kodeTps`,`urutan`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Witness value) {
                stmt.bindLong(1, value.getIdPetugas());
                if (value.getNama() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getNama());
                }
                if (value.getNik() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getNik());
                }
                if (value.getNoHandphone() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getNoHandphone());
                }
                if (value.getJenisPemeriksa() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getJenisPemeriksa());
                }
                if (value.getKodeTps() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getKodeTps());
                }
                if (value.getUrutan() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getUrutan());
                }
            }
        };
        this.__insertionAdapterOfWitnessShare = new EntityInsertionAdapter<WitnessShare>(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `witness_shares` (`noHandphone`,`jenisPemilihan`,`isShared`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WitnessShare value) {
                if (value.getNoHandphone() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getNoHandphone());
                }
                if (value.getJenisPemilihan() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getJenisPemilihan());
                }
                stmt.bindLong(3, value.isShared() ? 1L : 0L);
            }
        };
        this.__insertionAdapterOfWitnessPemeriksa = new EntityInsertionAdapter<WitnessPemeriksa>(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `witness_pemeriksas` (`noHandphone`,`jenisPemilihan`,`idPilihan`,`url`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WitnessPemeriksa value) {
                if (value.getNoHandphone() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getNoHandphone());
                }
                if (value.getJenisPemilihan() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getJenisPemilihan());
                }
                stmt.bindLong(3, value.getIdPilihan());
                if (value.getUrl() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getUrl());
                }
            }
        };
        this.__preparedStmtOfDeleteByNoHandphone = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM witnesses WHERE noHandphone = ?";
            }
        };
        this.__preparedStmtOfDeleteLocalBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM witnesses WHERE nama = ? AND nik = ? AND noHandphone = ? AND jenisPemeriksa = ? AND kodeTps = ? AND idPetugas < 0";
            }
        };
        this.__preparedStmtOfDeleteAllOnline = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM witnesses WHERE idPetugas > 0 AND kodeTps = ?";
            }
        };
        this.__preparedStmtOfMarkWitnessSynced = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE witnesses SET idPetugas = ? WHERE noHandphone = ?";
            }
        };
        this.__preparedStmtOfMarkAsSharedNoHandphone = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE witness_shares SET isShared = 1 WHERE noHandphone = ? AND jenisPemilihan = ?";
            }
        };
        this.__preparedStmtOfMarkWitnessPemeriksaSynced = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE witness_pemeriksas SET url = ? WHERE noHandphone = ? AND jenisPemilihan = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void insertAll(final List<Witness> witnesses) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWitness.insert(witnesses);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void insertAllShares(final List<WitnessShare> witnessShares) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWitnessShare.insert(witnessShares);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void insertAllPemeriksas(final List<WitnessPemeriksa> witnessPemeriksas) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWitnessPemeriksa.insert(witnessPemeriksas);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void deleteByNoHandphone(final String noHandphone) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByNoHandphone.acquire();
        if (noHandphone == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, noHandphone);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByNoHandphone.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void deleteLocalBy(final String name, final String nik, final String noHandphone, final String jenisPemeriksa, final String kodeTps) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteLocalBy.acquire();
        if (name == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, name);
        }
        if (nik == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, nik);
        }
        if (noHandphone == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, noHandphone);
        }
        if (jenisPemeriksa == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, jenisPemeriksa);
        }
        if (kodeTps == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, kodeTps);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteLocalBy.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void deleteAllOnline(final String kodeTps) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAllOnline.acquire();
        if (kodeTps == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, kodeTps);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllOnline.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void markWitnessSynced(final String noHandphone, final long idPetugas) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWitnessSynced.acquire();
        acquire.bindLong(1, idPetugas);
        if (noHandphone == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, noHandphone);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWitnessSynced.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void markAsSharedNoHandphone(final String noHandphone, final String jenisPemilihan) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsSharedNoHandphone.acquire();
        if (noHandphone == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, noHandphone);
        }
        if (jenisPemilihan == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, jenisPemilihan);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkAsSharedNoHandphone.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public void markWitnessPemeriksaSynced(final String noHandphone, final String jenisPemilihan, final String url) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWitnessPemeriksaSynced.acquire();
        if (url == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, url);
        }
        if (noHandphone == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, noHandphone);
        }
        if (jenisPemilihan == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, jenisPemilihan);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWitnessPemeriksaSynced.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public LiveData<WitnessWithShare> getByNoHandphone(final String noHandphone) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM witnesses WHERE noHandphone = ?", 1);
        if (noHandphone == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, noHandphone);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"witness_shares", "witness_pemeriksas", "witnesses"}, true, new Callable<WitnessWithShare>() { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.10
            @Override // java.util.concurrent.Callable
            public WitnessWithShare call() throws Exception {
                WitnessDao_Impl.this.__db.beginTransaction();
                try {
                    WitnessWithShare witnessWithShare = null;
                    Witness witness = null;
                    String string = null;
                    Cursor query = DBUtil.query(WitnessDao_Impl.this.__db, acquire, true, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idPetugas");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "nama");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "nik");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "noHandphone");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemeriksa");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "urutan");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        String string2 = query.getString(columnIndexOrThrow4);
                        if (((ArrayList) arrayMap.get(string2)) == null) {
                            arrayMap.put(string2, new ArrayList());
                        }
                        String string3 = query.getString(columnIndexOrThrow4);
                        if (((ArrayList) arrayMap2.get(string3)) == null) {
                            arrayMap2.put(string3, new ArrayList());
                        }
                    }
                    query.moveToPosition(-1);
                    WitnessDao_Impl.this.__fetchRelationshipwitnessSharesAsorgInformatikaSirekapModelWitnessShare(arrayMap);
                    WitnessDao_Impl.this.__fetchRelationshipwitnessPemeriksasAsorgInformatikaSirekapModelWitnessPemeriksa(arrayMap2);
                    if (query.moveToFirst()) {
                        if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3) || !query.isNull(columnIndexOrThrow4) || !query.isNull(columnIndexOrThrow5) || !query.isNull(columnIndexOrThrow6) || !query.isNull(columnIndexOrThrow7)) {
                            Witness witness2 = new Witness();
                            witness2.setIdPetugas(query.getLong(columnIndexOrThrow));
                            witness2.setNama(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                            witness2.setNik(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                            witness2.setNoHandphone(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                            witness2.setJenisPemeriksa(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                            witness2.setKodeTps(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                            if (!query.isNull(columnIndexOrThrow7)) {
                                string = query.getString(columnIndexOrThrow7);
                            }
                            witness2.setUrutan(string);
                            witness = witness2;
                        }
                        ArrayList arrayList = (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow4));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        ArrayList arrayList2 = (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow4));
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        witnessWithShare = new WitnessWithShare(witness, arrayList, arrayList2);
                    }
                    WitnessDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return witnessWithShare;
                } finally {
                    WitnessDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public WitnessWithShare getByNoHandphoneSync(final String noHandphone) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM witnesses WHERE noHandphone = ?", 1);
        if (noHandphone == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, noHandphone);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            WitnessWithShare witnessWithShare = null;
            Witness witness = null;
            String string = null;
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idPetugas");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "nama");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "nik");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "noHandphone");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemeriksa");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "kodeTps");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "urutan");
            ArrayMap<String, ArrayList<WitnessShare>> arrayMap = new ArrayMap<>();
            ArrayMap<String, ArrayList<WitnessPemeriksa>> arrayMap2 = new ArrayMap<>();
            while (query.moveToNext()) {
                String string2 = query.getString(columnIndexOrThrow4);
                if (arrayMap.get(string2) == null) {
                    arrayMap.put(string2, new ArrayList<>());
                }
                String string3 = query.getString(columnIndexOrThrow4);
                if (arrayMap2.get(string3) == null) {
                    arrayMap2.put(string3, new ArrayList<>());
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipwitnessSharesAsorgInformatikaSirekapModelWitnessShare(arrayMap);
            __fetchRelationshipwitnessPemeriksasAsorgInformatikaSirekapModelWitnessPemeriksa(arrayMap2);
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3) || !query.isNull(columnIndexOrThrow4) || !query.isNull(columnIndexOrThrow5) || !query.isNull(columnIndexOrThrow6) || !query.isNull(columnIndexOrThrow7)) {
                    Witness witness2 = new Witness();
                    witness2.setIdPetugas(query.getLong(columnIndexOrThrow));
                    witness2.setNama(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    witness2.setNik(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    witness2.setNoHandphone(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    witness2.setJenisPemeriksa(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    witness2.setKodeTps(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    if (!query.isNull(columnIndexOrThrow7)) {
                        string = query.getString(columnIndexOrThrow7);
                    }
                    witness2.setUrutan(string);
                    witness = witness2;
                }
                ArrayList<WitnessShare> arrayList = arrayMap.get(query.getString(columnIndexOrThrow4));
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                ArrayList<WitnessPemeriksa> arrayList2 = arrayMap2.get(query.getString(columnIndexOrThrow4));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                witnessWithShare = new WitnessWithShare(witness, arrayList, arrayList2);
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return witnessWithShare;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.WitnessDao
    public LiveData<List<WitnessWithShare>> getAllByKodeTps(final String kodeTps) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM witnesses WHERE kodeTps = ? ORDER BY idPetugas ASC", 1);
        if (kodeTps == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, kodeTps);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"witness_shares", "witness_pemeriksas", "witnesses"}, true, new Callable<List<WitnessWithShare>>() { // from class: org.informatika.sirekap.db.dao.WitnessDao_Impl.11
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:143:0x0139 A[Catch: all -> 0x0170, TryCatch #1 {all -> 0x0175, blocks: (B:87:0x000b, B:149:0x0163, B:88:0x0019, B:89:0x004d, B:91:0x0053, B:93:0x005f, B:94:0x0067, B:96:0x0073, B:97:0x007c, B:98:0x0093, B:100:0x0099, B:102:0x009f, B:104:0x00a5, B:106:0x00ab, B:108:0x00b1, B:110:0x00b7, B:112:0x00bd, B:141:0x012d, B:143:0x0139, B:144:0x013e, B:146:0x014a, B:147:0x014f, B:116:0x00c7, B:120:0x00df, B:124:0x00ee, B:128:0x00fd, B:132:0x010c, B:136:0x011b, B:140:0x012a, B:139:0x0126, B:135:0x0117, B:131:0x0108, B:127:0x00f9, B:123:0x00ea, B:119:0x00db, B:148:0x015a), top: B:158:0x000b }] */
            /* JADX WARN: Removed duplicated region for block: B:146:0x014a A[Catch: all -> 0x0170, TryCatch #1 {all -> 0x0175, blocks: (B:87:0x000b, B:149:0x0163, B:88:0x0019, B:89:0x004d, B:91:0x0053, B:93:0x005f, B:94:0x0067, B:96:0x0073, B:97:0x007c, B:98:0x0093, B:100:0x0099, B:102:0x009f, B:104:0x00a5, B:106:0x00ab, B:108:0x00b1, B:110:0x00b7, B:112:0x00bd, B:141:0x012d, B:143:0x0139, B:144:0x013e, B:146:0x014a, B:147:0x014f, B:116:0x00c7, B:120:0x00df, B:124:0x00ee, B:128:0x00fd, B:132:0x010c, B:136:0x011b, B:140:0x012a, B:139:0x0126, B:135:0x0117, B:131:0x0108, B:127:0x00f9, B:123:0x00ea, B:119:0x00db, B:148:0x015a), top: B:158:0x000b }] */
            /* JADX WARN: Removed duplicated region for block: B:167:0x014f A[SYNTHETIC] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<org.informatika.sirekap.model.WitnessWithShare> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 384
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.WitnessDao_Impl.AnonymousClass11.call():java.util.List");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:151:0x0144 A[Catch: all -> 0x017b, TryCatch #2 {all -> 0x0183, blocks: (B:95:0x001e, B:157:0x016f, B:96:0x0025, B:97:0x0059, B:99:0x005f, B:101:0x006b, B:102:0x0073, B:104:0x007f, B:105:0x0088, B:106:0x009b, B:108:0x00a1, B:110:0x00a7, B:112:0x00ad, B:114:0x00b3, B:116:0x00b9, B:118:0x00bf, B:120:0x00c5, B:149:0x0138, B:151:0x0144, B:152:0x0149, B:154:0x0155, B:155:0x015a, B:124:0x00d0, B:128:0x00ea, B:132:0x00f9, B:136:0x0108, B:140:0x0117, B:144:0x0126, B:148:0x0135, B:147:0x0131, B:143:0x0122, B:139:0x0113, B:135:0x0104, B:131:0x00f5, B:127:0x00e6, B:156:0x016a), top: B:166:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0155 A[Catch: all -> 0x017b, TryCatch #2 {all -> 0x0183, blocks: (B:95:0x001e, B:157:0x016f, B:96:0x0025, B:97:0x0059, B:99:0x005f, B:101:0x006b, B:102:0x0073, B:104:0x007f, B:105:0x0088, B:106:0x009b, B:108:0x00a1, B:110:0x00a7, B:112:0x00ad, B:114:0x00b3, B:116:0x00b9, B:118:0x00bf, B:120:0x00c5, B:149:0x0138, B:151:0x0144, B:152:0x0149, B:154:0x0155, B:155:0x015a, B:124:0x00d0, B:128:0x00ea, B:132:0x00f9, B:136:0x0108, B:140:0x0117, B:144:0x0126, B:148:0x0135, B:147:0x0131, B:143:0x0122, B:139:0x0113, B:135:0x0104, B:131:0x00f5, B:127:0x00e6, B:156:0x016a), top: B:166:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x015a A[SYNTHETIC] */
    @Override // org.informatika.sirekap.db.dao.WitnessDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<org.informatika.sirekap.model.WitnessWithShare> getAllByKodeTpsSync(final java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.WitnessDao_Impl.getAllByKodeTpsSync(java.lang.String):java.util.List");
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public void __fetchRelationshipwitnessSharesAsorgInformatikaSirekapModelWitnessShare(final ArrayMap<String, ArrayList<WitnessShare>> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<WitnessShare>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipwitnessSharesAsorgInformatikaSirekapModelWitnessShare(arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipwitnessSharesAsorgInformatikaSirekapModelWitnessShare(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `noHandphone`,`jenisPemilihan`,`isShared` FROM `witness_shares` WHERE `noHandphone` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "noHandphone");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "noHandphone");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemilihan");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "isShared");
            while (query.moveToNext()) {
                ArrayList<WitnessShare> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(new WitnessShare(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3) != 0));
                }
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipwitnessPemeriksasAsorgInformatikaSirekapModelWitnessPemeriksa(final ArrayMap<String, ArrayList<WitnessPemeriksa>> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<WitnessPemeriksa>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipwitnessPemeriksasAsorgInformatikaSirekapModelWitnessPemeriksa(arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipwitnessPemeriksasAsorgInformatikaSirekapModelWitnessPemeriksa(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `noHandphone`,`jenisPemilihan`,`idPilihan`,`url` FROM `witness_pemeriksas` WHERE `noHandphone` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "noHandphone");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "noHandphone");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemilihan");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "idPilihan");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "url");
            while (query.moveToNext()) {
                ArrayList<WitnessPemeriksa> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(new WitnessPemeriksa(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                }
            }
        } finally {
            query.close();
        }
    }
}
