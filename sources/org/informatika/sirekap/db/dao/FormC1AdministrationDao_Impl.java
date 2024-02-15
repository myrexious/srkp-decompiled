package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;

/* loaded from: classes2.dex */
public final class FormC1AdministrationDao_Impl implements FormC1AdministrationDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1Administration> __insertionAdapterOfFormC1Administration;

    public FormC1AdministrationDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1Administration = new EntityInsertionAdapter<FormC1Administration>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1AdministrationDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_administration` (`idImage`,`isLn`,`isLnPos`,`formType`,`pemilihDpt_L`,`pemilihDpt_P`,`totalPemilihDpt`,`penggunaDpt_L`,`penggunaDpt_P`,`totalPenggunaDpt`,`penggunaDptb_L`,`penggunaDptb_P`,`totalPenggunaDptb`,`penggunaDpk_L`,`penggunaDpk_P`,`totalPenggunaDpk`,`totalPengguna_L`,`totalPengguna_P`,`totalPengguna`,`suratDiterima`,`suratDikembalikan`,`suratTidakDigunakan`,`suratTidakDikembalikan`,`suratKembaliPPLN`,`suratTidakTerpakai`,`suratDigunakan`,`pemilihDisabilitas_L`,`pemilihDisabilitas_P`,`totalPemilihDisabilitas`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1Administration value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.isLn() ? 1L : 0L);
                stmt.bindLong(3, value.isLnPos() ? 1L : 0L);
                stmt.bindLong(4, value.getFormType());
                stmt.bindLong(5, value.getPemilihDpt_L());
                stmt.bindLong(6, value.getPemilihDpt_P());
                stmt.bindLong(7, value.getTotalPemilihDpt());
                stmt.bindLong(8, value.getPenggunaDpt_L());
                stmt.bindLong(9, value.getPenggunaDpt_P());
                stmt.bindLong(10, value.getTotalPenggunaDpt());
                if (value.getPenggunaDptb_L() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindLong(11, value.getPenggunaDptb_L().intValue());
                }
                if (value.getPenggunaDptb_P() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindLong(12, value.getPenggunaDptb_P().intValue());
                }
                if (value.getTotalPenggunaDptb() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindLong(13, value.getTotalPenggunaDptb().intValue());
                }
                if (value.getPenggunaDpk_L() == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindLong(14, value.getPenggunaDpk_L().intValue());
                }
                if (value.getPenggunaDpk_P() == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindLong(15, value.getPenggunaDpk_P().intValue());
                }
                if (value.getTotalPenggunaDpk() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindLong(16, value.getTotalPenggunaDpk().intValue());
                }
                if (value.getTotalPengguna_L() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindLong(17, value.getTotalPengguna_L().intValue());
                }
                if (value.getTotalPengguna_P() == null) {
                    stmt.bindNull(18);
                } else {
                    stmt.bindLong(18, value.getTotalPengguna_P().intValue());
                }
                if (value.getTotalPengguna() == null) {
                    stmt.bindNull(19);
                } else {
                    stmt.bindLong(19, value.getTotalPengguna().intValue());
                }
                stmt.bindLong(20, value.getSuratDiterima());
                stmt.bindLong(21, value.getSuratDikembalikan());
                stmt.bindLong(22, value.getSuratTidakDigunakan());
                if (value.getSuratTidakDikembalikan() == null) {
                    stmt.bindNull(23);
                } else {
                    stmt.bindLong(23, value.getSuratTidakDikembalikan().intValue());
                }
                if (value.getSuratKembaliPPLN() == null) {
                    stmt.bindNull(24);
                } else {
                    stmt.bindLong(24, value.getSuratKembaliPPLN().intValue());
                }
                if (value.getSuratTidakTerpakai() == null) {
                    stmt.bindNull(25);
                } else {
                    stmt.bindLong(25, value.getSuratTidakTerpakai().intValue());
                }
                stmt.bindLong(26, value.getSuratDigunakan());
                stmt.bindLong(27, value.getPemilihDisabilitas_L());
                stmt.bindLong(28, value.getPemilihDisabilitas_P());
                stmt.bindLong(29, value.getTotalPemilihDisabilitas());
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationDao
    public void insertAll(final List<FormC1Administration> forms) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1Administration.insert(forms);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationDao
    public LiveData<FormC1AdministrationComplete> getByIdImage(final String idImage) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM election_page WHERE idImage = ?", 1);
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_administration", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_administration", "election_page"}, true, new Callable<FormC1AdministrationComplete>() { // from class: org.informatika.sirekap.db.dao.FormC1AdministrationDao_Impl.2
            /* JADX WARN: Removed duplicated region for block: B:269:0x0201  */
            /* JADX WARN: Removed duplicated region for block: B:270:0x0204 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:273:0x0210  */
            /* JADX WARN: Removed duplicated region for block: B:274:0x0213 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:277:0x021f  */
            /* JADX WARN: Removed duplicated region for block: B:278:0x0222 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:281:0x023e  */
            /* JADX WARN: Removed duplicated region for block: B:282:0x0241 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:285:0x024d  */
            /* JADX WARN: Removed duplicated region for block: B:286:0x0250 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:289:0x025c  */
            /* JADX WARN: Removed duplicated region for block: B:290:0x025f A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:293:0x026b  */
            /* JADX WARN: Removed duplicated region for block: B:294:0x026e A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:297:0x027b  */
            /* JADX WARN: Removed duplicated region for block: B:298:0x027e  */
            /* JADX WARN: Removed duplicated region for block: B:301:0x0286  */
            /* JADX WARN: Removed duplicated region for block: B:302:0x0289 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:305:0x0295  */
            /* JADX WARN: Removed duplicated region for block: B:306:0x0297 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:308:0x02a1  */
            /* JADX WARN: Removed duplicated region for block: B:309:0x02a6 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:316:0x02bd  */
            /* JADX WARN: Removed duplicated region for block: B:317:0x02c2 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:320:0x02d0  */
            /* JADX WARN: Removed duplicated region for block: B:321:0x02d5 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:324:0x02e3  */
            /* JADX WARN: Removed duplicated region for block: B:325:0x02e8 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:328:0x02f6  */
            /* JADX WARN: Removed duplicated region for block: B:329:0x02fb  */
            /* JADX WARN: Removed duplicated region for block: B:332:0x0305  */
            /* JADX WARN: Removed duplicated region for block: B:333:0x0308  */
            /* JADX WARN: Removed duplicated region for block: B:336:0x0310  */
            /* JADX WARN: Removed duplicated region for block: B:337:0x0313 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:341:0x032b A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:342:0x033a  */
            /* JADX WARN: Removed duplicated region for block: B:345:0x0341 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:346:0x0350  */
            /* JADX WARN: Removed duplicated region for block: B:349:0x0357 A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:350:0x0366  */
            /* JADX WARN: Removed duplicated region for block: B:353:0x036d A[Catch: all -> 0x0387, TryCatch #1 {all -> 0x0387, blocks: (B:212:0x0128, B:214:0x012e, B:216:0x0134, B:218:0x013a, B:220:0x0140, B:222:0x0146, B:224:0x014c, B:226:0x0152, B:228:0x015a, B:230:0x0162, B:232:0x016a, B:234:0x0174, B:236:0x017e, B:238:0x0184, B:240:0x018e, B:242:0x0198, B:244:0x01a2, B:246:0x01ac, B:248:0x01b6, B:250:0x01c0, B:267:0x01fb, B:271:0x020a, B:275:0x0219, B:279:0x0228, B:283:0x0247, B:287:0x0256, B:291:0x0265, B:295:0x0274, B:299:0x0280, B:303:0x028f, B:314:0x02b7, B:318:0x02ca, B:322:0x02dd, B:326:0x02f0, B:330:0x02ff, B:334:0x030a, B:338:0x031d, B:339:0x0325, B:341:0x032b, B:343:0x033b, B:345:0x0341, B:347:0x0351, B:349:0x0357, B:351:0x0367, B:353:0x036d, B:355:0x037d, B:337:0x0313, B:325:0x02e8, B:321:0x02d5, B:317:0x02c2, B:309:0x02a6, B:313:0x02af, B:306:0x0297, B:302:0x0289, B:294:0x026e, B:290:0x025f, B:286:0x0250, B:282:0x0241, B:278:0x0222, B:274:0x0213, B:270:0x0204), top: B:372:0x0128 }] */
            /* JADX WARN: Removed duplicated region for block: B:354:0x037c  */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public org.informatika.sirekap.model.FormC1AdministrationComplete call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 948
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1AdministrationDao_Impl.AnonymousClass2.call():org.informatika.sirekap.model.FormC1AdministrationComplete");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:287:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x020a A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0219 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0228 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0247 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0256 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0265 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0274 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x028f A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:323:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x029d A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:326:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x02ac A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x02c8 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:338:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x02db A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:342:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x02ee A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:346:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x0319 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0331 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0347 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:364:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x035d A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:368:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0373 A[Catch: all -> 0x038a, TryCatch #2 {all -> 0x038a, blocks: (B:230:0x012e, B:232:0x0134, B:234:0x013a, B:236:0x0140, B:238:0x0146, B:240:0x014c, B:242:0x0152, B:244:0x0158, B:246:0x0160, B:248:0x0168, B:250:0x0170, B:252:0x017a, B:254:0x0184, B:256:0x018a, B:258:0x0194, B:260:0x019e, B:262:0x01a8, B:264:0x01b2, B:266:0x01bc, B:268:0x01c6, B:285:0x0201, B:289:0x0210, B:293:0x021f, B:297:0x022e, B:301:0x024d, B:305:0x025c, B:309:0x026b, B:313:0x027a, B:317:0x0286, B:321:0x0295, B:332:0x02bd, B:336:0x02d0, B:340:0x02e3, B:344:0x02f6, B:348:0x0305, B:352:0x0310, B:356:0x0323, B:357:0x032b, B:359:0x0331, B:361:0x0341, B:363:0x0347, B:365:0x0357, B:367:0x035d, B:369:0x036d, B:371:0x0373, B:373:0x0383, B:355:0x0319, B:343:0x02ee, B:339:0x02db, B:335:0x02c8, B:327:0x02ac, B:331:0x02b5, B:324:0x029d, B:320:0x028f, B:312:0x0274, B:308:0x0265, B:304:0x0256, B:300:0x0247, B:296:0x0228, B:292:0x0219, B:288:0x020a), top: B:396:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0382  */
    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.informatika.sirekap.model.FormC1AdministrationComplete getByIdImageSync(final java.lang.String r49) {
        /*
            Method dump skipped, instructions count: 949
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1AdministrationDao_Impl.getByIdImageSync(java.lang.String):org.informatika.sirekap.model.FormC1AdministrationComplete");
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public void __fetchRelationshipformC1AdministrationAsorgInformatikaSirekapModelFormC1Administration(final ArrayMap<String, FormC1Administration> _map) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        Integer valueOf;
        int i10;
        Integer valueOf2;
        int i11;
        Integer valueOf3;
        int i12;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1Administration> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i13 = 0;
            int i14 = 0;
            while (i13 < size) {
                arrayMap.put(_map.keyAt(i13), null);
                i13++;
                i14++;
                if (i14 == 999) {
                    __fetchRelationshipformC1AdministrationAsorgInformatikaSirekapModelFormC1Administration(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1Administration>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i14 = 0;
                }
            }
            if (i14 > 0) {
                __fetchRelationshipformC1AdministrationAsorgInformatikaSirekapModelFormC1Administration(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1Administration>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`isLn`,`isLnPos`,`formType`,`pemilihDpt_L`,`pemilihDpt_P`,`totalPemilihDpt`,`penggunaDpt_L`,`penggunaDpt_P`,`totalPenggunaDpt`,`penggunaDptb_L`,`penggunaDptb_P`,`totalPenggunaDptb`,`penggunaDpk_L`,`penggunaDpk_P`,`totalPenggunaDpk`,`totalPengguna_L`,`totalPengguna_P`,`totalPengguna`,`suratDiterima`,`suratDikembalikan`,`suratTidakDigunakan`,`suratTidakDikembalikan`,`suratKembaliPPLN`,`suratTidakTerpakai`,`suratDigunakan`,`pemilihDisabilitas_L`,`pemilihDisabilitas_P`,`totalPemilihDisabilitas` FROM `form_c1_administration` WHERE `idImage` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i15 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i15);
            } else {
                acquire.bindString(i15, str);
            }
            i15++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "idImage");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idImage");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "isLn");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "isLnPos");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "formType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDpt_L");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDpt_P");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "totalPemilihDpt");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpt_L");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpt_P");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDpt");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDptb_L");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDptb_P");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDptb");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpk_L");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpk_P");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDpk");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna_L");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna_P");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "suratDiterima");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "suratDikembalikan");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakDigunakan");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakDikembalikan");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "suratKembaliPPLN");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakTerpakai");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "suratDigunakan");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDisabilitas_L");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDisabilitas_P");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "totalPemilihDisabilitas");
            while (query.moveToNext()) {
                if (query.isNull(columnIndex)) {
                    i = columnIndexOrThrow20;
                    int i16 = columnIndexOrThrow19;
                    int i17 = columnIndexOrThrow18;
                    columnIndexOrThrow14 = columnIndexOrThrow14;
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    columnIndexOrThrow18 = i17;
                    columnIndexOrThrow19 = i16;
                } else {
                    int i18 = columnIndexOrThrow29;
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        boolean z = query.getInt(columnIndexOrThrow2) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow3) != 0;
                        int i19 = query.getInt(columnIndexOrThrow4);
                        int i20 = query.getInt(columnIndexOrThrow5);
                        int i21 = query.getInt(columnIndexOrThrow6);
                        int i22 = query.getInt(columnIndexOrThrow7);
                        int i23 = query.getInt(columnIndexOrThrow8);
                        int i24 = query.getInt(columnIndexOrThrow9);
                        int i25 = query.getInt(columnIndexOrThrow10);
                        Integer valueOf4 = query.isNull(columnIndexOrThrow11) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow11));
                        Integer valueOf5 = query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12));
                        int i26 = columnIndexOrThrow13;
                        i9 = columnIndexOrThrow;
                        Integer valueOf6 = query.isNull(i26) ? null : Integer.valueOf(query.getInt(i26));
                        int i27 = columnIndexOrThrow14;
                        i8 = i26;
                        Integer valueOf7 = query.isNull(i27) ? null : Integer.valueOf(query.getInt(i27));
                        int i28 = columnIndexOrThrow15;
                        i7 = i27;
                        Integer valueOf8 = query.isNull(i28) ? null : Integer.valueOf(query.getInt(i28));
                        int i29 = columnIndexOrThrow16;
                        i6 = i28;
                        Integer valueOf9 = query.isNull(i29) ? null : Integer.valueOf(query.getInt(i29));
                        int i30 = columnIndexOrThrow17;
                        i5 = i29;
                        Integer valueOf10 = query.isNull(i30) ? null : Integer.valueOf(query.getInt(i30));
                        int i31 = columnIndexOrThrow18;
                        i4 = i30;
                        Integer valueOf11 = query.isNull(i31) ? null : Integer.valueOf(query.getInt(i31));
                        int i32 = columnIndexOrThrow19;
                        i3 = i31;
                        Integer valueOf12 = query.isNull(i32) ? null : Integer.valueOf(query.getInt(i32));
                        int i33 = columnIndexOrThrow20;
                        i2 = i32;
                        int i34 = query.getInt(i33);
                        i = i33;
                        int i35 = columnIndexOrThrow21;
                        int i36 = query.getInt(i35);
                        columnIndexOrThrow21 = i35;
                        int i37 = columnIndexOrThrow22;
                        int i38 = query.getInt(i37);
                        columnIndexOrThrow22 = i37;
                        int i39 = columnIndexOrThrow23;
                        if (query.isNull(i39)) {
                            columnIndexOrThrow23 = i39;
                            i10 = columnIndexOrThrow24;
                            valueOf = null;
                        } else {
                            valueOf = Integer.valueOf(query.getInt(i39));
                            columnIndexOrThrow23 = i39;
                            i10 = columnIndexOrThrow24;
                        }
                        if (query.isNull(i10)) {
                            columnIndexOrThrow24 = i10;
                            i11 = columnIndexOrThrow25;
                            valueOf2 = null;
                        } else {
                            valueOf2 = Integer.valueOf(query.getInt(i10));
                            columnIndexOrThrow24 = i10;
                            i11 = columnIndexOrThrow25;
                        }
                        if (query.isNull(i11)) {
                            columnIndexOrThrow25 = i11;
                            i12 = columnIndexOrThrow26;
                            valueOf3 = null;
                        } else {
                            valueOf3 = Integer.valueOf(query.getInt(i11));
                            columnIndexOrThrow25 = i11;
                            i12 = columnIndexOrThrow26;
                        }
                        int i40 = query.getInt(i12);
                        columnIndexOrThrow26 = i12;
                        int i41 = columnIndexOrThrow27;
                        int i42 = query.getInt(i41);
                        columnIndexOrThrow27 = i41;
                        int i43 = columnIndexOrThrow28;
                        int i44 = query.getInt(i43);
                        columnIndexOrThrow28 = i43;
                        int i45 = query.getInt(i18);
                        i18 = i18;
                        _map.put(string, new FormC1Administration(string2, z, z2, i19, i20, i21, i22, i23, i24, i25, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, valueOf10, valueOf11, valueOf12, i34, i36, i38, valueOf, valueOf2, valueOf3, i40, i42, i44, i45));
                    } else {
                        i = columnIndexOrThrow20;
                        i2 = columnIndexOrThrow19;
                        i3 = columnIndexOrThrow18;
                        i4 = columnIndexOrThrow17;
                        i5 = columnIndexOrThrow16;
                        i6 = columnIndexOrThrow15;
                        i7 = columnIndexOrThrow14;
                        i8 = columnIndexOrThrow13;
                        i9 = columnIndexOrThrow;
                    }
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow13 = i8;
                    columnIndexOrThrow14 = i7;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i5;
                    columnIndexOrThrow17 = i4;
                    columnIndexOrThrow18 = i3;
                    columnIndexOrThrow19 = i2;
                    columnIndexOrThrow29 = i18;
                }
                columnIndexOrThrow20 = i;
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipformC1ErrorAsorgInformatikaSirekapModelFormC1Error(final ArrayMap<String, FormC1Error> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1Error> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), null);
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1ErrorAsorgInformatikaSirekapModelFormC1Error(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1Error>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1ErrorAsorgInformatikaSirekapModelFormC1Error(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1Error>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`formType`,`errorType`,`error` FROM `form_c1_error` WHERE `idImage` IN (");
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
            int columnIndex = CursorUtil.getColumnIndex(query, "idImage");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idImage");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "formType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "errorType");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "error");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex)) {
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        _map.put(string, new FormC1Error(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                    }
                }
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipformC1KesesuaianAsorgInformatikaSirekapModelFormC1Kesesuaian(final ArrayMap<String, FormC1Kesesuaian> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1Kesesuaian> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), null);
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1KesesuaianAsorgInformatikaSirekapModelFormC1Kesesuaian(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1Kesesuaian>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1KesesuaianAsorgInformatikaSirekapModelFormC1Kesesuaian(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1Kesesuaian>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`isSesuai`,`komentar` FROM `form_c1_kesesuaian` WHERE `idImage` IN (");
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
            int columnIndex = CursorUtil.getColumnIndex(query, "idImage");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idImage");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "isSesuai");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "komentar");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex)) {
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        _map.put(string, new FormC1Kesesuaian(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2) != 0, query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3)));
                    }
                }
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipformC1KesesuaianAdministrationAsorgInformatikaSirekapModelFormC1KesesuaianAdministration(final ArrayMap<String, FormC1KesesuaianAdministration> _map) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        int i36;
        int i37;
        int i38;
        int i39;
        int i40;
        int i41;
        int i42;
        Boolean valueOf;
        Boolean valueOf2;
        Boolean valueOf3;
        Boolean valueOf4;
        Boolean valueOf5;
        Boolean valueOf6;
        Boolean valueOf7;
        Boolean valueOf8;
        Boolean valueOf9;
        Boolean valueOf10;
        Boolean valueOf11;
        Boolean valueOf12;
        Boolean valueOf13;
        Boolean valueOf14;
        Boolean valueOf15;
        Boolean valueOf16;
        Boolean valueOf17;
        Boolean valueOf18;
        Boolean valueOf19;
        Boolean valueOf20;
        Boolean valueOf21;
        Boolean valueOf22;
        Boolean valueOf23;
        Boolean valueOf24;
        Boolean valueOf25;
        Integer valueOf26;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1KesesuaianAdministration> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i43 = 0;
            int i44 = 0;
            while (i43 < size) {
                arrayMap.put(_map.keyAt(i43), null);
                i43++;
                i44++;
                if (i44 == 999) {
                    __fetchRelationshipformC1KesesuaianAdministrationAsorgInformatikaSirekapModelFormC1KesesuaianAdministration(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1KesesuaianAdministration>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i44 = 0;
                }
            }
            if (i44 > 0) {
                __fetchRelationshipformC1KesesuaianAdministrationAsorgInformatikaSirekapModelFormC1KesesuaianAdministration(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1KesesuaianAdministration>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`isLnPos`,`jenisPemilihan`,`pemilihDpt_L`,`pemilihDpt_P`,`totalPemilihDpt`,`penggunaDpt_L`,`penggunaDpt_P`,`totalPenggunaDpt`,`penggunaDptb_L`,`penggunaDptb_P`,`totalPenggunaDptb`,`penggunaDpk_L`,`penggunaDpk_P`,`totalPenggunaDpk`,`totalPengguna_L`,`totalPengguna_P`,`totalPengguna`,`suratDiterima`,`suratDikembalikan`,`suratTidakDigunakan`,`suratTidakDikembalikan`,`suratKembaliPPLN`,`suratTidakTerpakai`,`suratDigunakan`,`pemilihDisabilitas_L`,`pemilihDisabilitas_P`,`totalPemilihDisabilitas`,`pemilihDpt_LCorrected`,`pemilihDpt_PCorrected`,`totalPemilihDptCorrected`,`penggunaDpt_LCorrected`,`penggunaDpt_PCorrected`,`totalPenggunaDptCorrected`,`penggunaDptb_LCorrected`,`penggunaDptb_PCorrected`,`totalPenggunaDptbCorrected`,`penggunaDpk_LCorrected`,`penggunaDpk_PCorrected`,`totalPenggunaDpkCorrected`,`totalPengguna_LCorrected`,`totalPengguna_PCorrected`,`totalPenggunaCorrected`,`suratDiterimaCorrected`,`suratDikembalikanCorrected`,`suratTidakDigunakanCorrected`,`suratTidakDikembalikanCorrected`,`suratKembaliPPLNCorrected`,`suratTidakTerpakaiCorrected`,`suratDigunakanCorrected`,`pemilihDisabilitas_LCorrected`,`pemilihDisabilitas_PCorrected`,`totalPemilihDisabilitasCorrected` FROM `form_c1_kesesuaian_administration` WHERE `idImage` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i45 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i45);
            } else {
                acquire.bindString(i45, str);
            }
            i45++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "idImage");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idImage");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "isLnPos");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "jenisPemilihan");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDpt_L");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDpt_P");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "totalPemilihDpt");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpt_L");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpt_P");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDpt");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDptb_L");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDptb_P");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDptb");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpk_L");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpk_P");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDpk");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna_L");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna_P");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "suratDiterima");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "suratDikembalikan");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakDigunakan");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakDikembalikan");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "suratKembaliPPLN");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakTerpakai");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "suratDigunakan");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDisabilitas_L");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDisabilitas_P");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "totalPemilihDisabilitas");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDpt_LCorrected");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDpt_PCorrected");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "totalPemilihDptCorrected");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpt_LCorrected");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpt_PCorrected");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDptCorrected");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDptb_LCorrected");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDptb_PCorrected");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDptbCorrected");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpk_LCorrected");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "penggunaDpk_PCorrected");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaDpkCorrected");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna_LCorrected");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalPengguna_PCorrected");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "totalPenggunaCorrected");
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "suratDiterimaCorrected");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "suratDikembalikanCorrected");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakDigunakanCorrected");
            int columnIndexOrThrow47 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakDikembalikanCorrected");
            int columnIndexOrThrow48 = CursorUtil.getColumnIndexOrThrow(query, "suratKembaliPPLNCorrected");
            int columnIndexOrThrow49 = CursorUtil.getColumnIndexOrThrow(query, "suratTidakTerpakaiCorrected");
            int columnIndexOrThrow50 = CursorUtil.getColumnIndexOrThrow(query, "suratDigunakanCorrected");
            int columnIndexOrThrow51 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDisabilitas_LCorrected");
            int columnIndexOrThrow52 = CursorUtil.getColumnIndexOrThrow(query, "pemilihDisabilitas_PCorrected");
            int columnIndexOrThrow53 = CursorUtil.getColumnIndexOrThrow(query, "totalPemilihDisabilitasCorrected");
            while (query.moveToNext()) {
                if (query.isNull(columnIndex)) {
                    int i46 = columnIndexOrThrow52;
                    int i47 = columnIndexOrThrow51;
                    int i48 = columnIndexOrThrow50;
                    int i49 = columnIndexOrThrow49;
                    int i50 = columnIndexOrThrow48;
                    int i51 = columnIndexOrThrow47;
                    int i52 = columnIndexOrThrow46;
                    int i53 = columnIndexOrThrow45;
                    int i54 = columnIndexOrThrow44;
                    int i55 = columnIndexOrThrow43;
                    int i56 = columnIndexOrThrow42;
                    int i57 = columnIndexOrThrow41;
                    int i58 = columnIndexOrThrow40;
                    int i59 = columnIndexOrThrow39;
                    int i60 = columnIndexOrThrow38;
                    int i61 = columnIndexOrThrow37;
                    int i62 = columnIndexOrThrow36;
                    int i63 = columnIndexOrThrow35;
                    int i64 = columnIndexOrThrow34;
                    int i65 = columnIndexOrThrow33;
                    int i66 = columnIndexOrThrow32;
                    int i67 = columnIndexOrThrow31;
                    int i68 = columnIndexOrThrow30;
                    int i69 = columnIndexOrThrow29;
                    int i70 = columnIndexOrThrow28;
                    int i71 = columnIndexOrThrow27;
                    int i72 = columnIndexOrThrow26;
                    int i73 = columnIndexOrThrow25;
                    int i74 = columnIndexOrThrow24;
                    int i75 = columnIndexOrThrow23;
                    int i76 = columnIndexOrThrow22;
                    int i77 = columnIndexOrThrow21;
                    int i78 = columnIndexOrThrow20;
                    int i79 = columnIndexOrThrow19;
                    int i80 = columnIndexOrThrow18;
                    columnIndexOrThrow14 = columnIndexOrThrow14;
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    columnIndexOrThrow18 = i80;
                    columnIndexOrThrow19 = i79;
                    columnIndexOrThrow20 = i78;
                    columnIndexOrThrow21 = i77;
                    columnIndexOrThrow22 = i76;
                    columnIndexOrThrow23 = i75;
                    columnIndexOrThrow24 = i74;
                    columnIndexOrThrow25 = i73;
                    columnIndexOrThrow26 = i72;
                    columnIndexOrThrow27 = i71;
                    columnIndexOrThrow28 = i70;
                    columnIndexOrThrow29 = i69;
                    columnIndexOrThrow30 = i68;
                    columnIndexOrThrow31 = i67;
                    columnIndexOrThrow32 = i66;
                    columnIndexOrThrow33 = i65;
                    columnIndexOrThrow34 = i64;
                    columnIndexOrThrow35 = i63;
                    columnIndexOrThrow36 = i62;
                    columnIndexOrThrow37 = i61;
                    columnIndexOrThrow38 = i60;
                    columnIndexOrThrow39 = i59;
                    columnIndexOrThrow40 = i58;
                    columnIndexOrThrow41 = i57;
                    columnIndexOrThrow42 = i56;
                    columnIndexOrThrow43 = i55;
                    columnIndexOrThrow44 = i54;
                    columnIndexOrThrow45 = i53;
                    columnIndexOrThrow46 = i52;
                    columnIndexOrThrow47 = i51;
                    columnIndexOrThrow48 = i50;
                    columnIndexOrThrow49 = i49;
                    columnIndexOrThrow50 = i48;
                    columnIndexOrThrow51 = i47;
                    columnIndexOrThrow52 = i46;
                } else {
                    int i81 = columnIndexOrThrow53;
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        boolean z = query.getInt(columnIndexOrThrow2) != 0;
                        String string3 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                        Integer valueOf27 = query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4));
                        if (valueOf27 == null) {
                            valueOf = null;
                        } else {
                            valueOf = Boolean.valueOf(valueOf27.intValue() != 0);
                        }
                        Integer valueOf28 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                        if (valueOf28 == null) {
                            valueOf2 = null;
                        } else {
                            valueOf2 = Boolean.valueOf(valueOf28.intValue() != 0);
                        }
                        Integer valueOf29 = query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6));
                        if (valueOf29 == null) {
                            valueOf3 = null;
                        } else {
                            valueOf3 = Boolean.valueOf(valueOf29.intValue() != 0);
                        }
                        Integer valueOf30 = query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7));
                        if (valueOf30 == null) {
                            valueOf4 = null;
                        } else {
                            valueOf4 = Boolean.valueOf(valueOf30.intValue() != 0);
                        }
                        Integer valueOf31 = query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8));
                        if (valueOf31 == null) {
                            valueOf5 = null;
                        } else {
                            valueOf5 = Boolean.valueOf(valueOf31.intValue() != 0);
                        }
                        Integer valueOf32 = query.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow9));
                        if (valueOf32 == null) {
                            valueOf6 = null;
                        } else {
                            valueOf6 = Boolean.valueOf(valueOf32.intValue() != 0);
                        }
                        Integer valueOf33 = query.isNull(columnIndexOrThrow10) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow10));
                        if (valueOf33 == null) {
                            valueOf7 = null;
                        } else {
                            valueOf7 = Boolean.valueOf(valueOf33.intValue() != 0);
                        }
                        Integer valueOf34 = query.isNull(columnIndexOrThrow11) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow11));
                        if (valueOf34 == null) {
                            valueOf8 = null;
                        } else {
                            valueOf8 = Boolean.valueOf(valueOf34.intValue() != 0);
                        }
                        Integer valueOf35 = query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12));
                        if (valueOf35 == null) {
                            valueOf9 = null;
                        } else {
                            valueOf9 = Boolean.valueOf(valueOf35.intValue() != 0);
                        }
                        int i82 = columnIndexOrThrow13;
                        i42 = columnIndexOrThrow;
                        Integer valueOf36 = query.isNull(i82) ? null : Integer.valueOf(query.getInt(i82));
                        if (valueOf36 == null) {
                            valueOf10 = null;
                        } else {
                            valueOf10 = Boolean.valueOf(valueOf36.intValue() != 0);
                        }
                        int i83 = columnIndexOrThrow14;
                        i41 = i82;
                        Integer valueOf37 = query.isNull(i83) ? null : Integer.valueOf(query.getInt(i83));
                        if (valueOf37 == null) {
                            valueOf11 = null;
                        } else {
                            valueOf11 = Boolean.valueOf(valueOf37.intValue() != 0);
                        }
                        int i84 = columnIndexOrThrow15;
                        i40 = i83;
                        Integer valueOf38 = query.isNull(i84) ? null : Integer.valueOf(query.getInt(i84));
                        if (valueOf38 == null) {
                            valueOf12 = null;
                        } else {
                            valueOf12 = Boolean.valueOf(valueOf38.intValue() != 0);
                        }
                        int i85 = columnIndexOrThrow16;
                        i39 = i84;
                        Integer valueOf39 = query.isNull(i85) ? null : Integer.valueOf(query.getInt(i85));
                        if (valueOf39 == null) {
                            valueOf13 = null;
                        } else {
                            valueOf13 = Boolean.valueOf(valueOf39.intValue() != 0);
                        }
                        int i86 = columnIndexOrThrow17;
                        i38 = i85;
                        Integer valueOf40 = query.isNull(i86) ? null : Integer.valueOf(query.getInt(i86));
                        if (valueOf40 == null) {
                            valueOf14 = null;
                        } else {
                            valueOf14 = Boolean.valueOf(valueOf40.intValue() != 0);
                        }
                        int i87 = columnIndexOrThrow18;
                        i37 = i86;
                        Integer valueOf41 = query.isNull(i87) ? null : Integer.valueOf(query.getInt(i87));
                        if (valueOf41 == null) {
                            valueOf15 = null;
                        } else {
                            valueOf15 = Boolean.valueOf(valueOf41.intValue() != 0);
                        }
                        int i88 = columnIndexOrThrow19;
                        i36 = i87;
                        Integer valueOf42 = query.isNull(i88) ? null : Integer.valueOf(query.getInt(i88));
                        if (valueOf42 == null) {
                            valueOf16 = null;
                        } else {
                            valueOf16 = Boolean.valueOf(valueOf42.intValue() != 0);
                        }
                        int i89 = columnIndexOrThrow20;
                        i35 = i88;
                        Integer valueOf43 = query.isNull(i89) ? null : Integer.valueOf(query.getInt(i89));
                        if (valueOf43 == null) {
                            valueOf17 = null;
                        } else {
                            valueOf17 = Boolean.valueOf(valueOf43.intValue() != 0);
                        }
                        int i90 = columnIndexOrThrow21;
                        i34 = i89;
                        Integer valueOf44 = query.isNull(i90) ? null : Integer.valueOf(query.getInt(i90));
                        if (valueOf44 == null) {
                            valueOf18 = null;
                        } else {
                            valueOf18 = Boolean.valueOf(valueOf44.intValue() != 0);
                        }
                        int i91 = columnIndexOrThrow22;
                        i33 = i90;
                        Integer valueOf45 = query.isNull(i91) ? null : Integer.valueOf(query.getInt(i91));
                        if (valueOf45 == null) {
                            valueOf19 = null;
                        } else {
                            valueOf19 = Boolean.valueOf(valueOf45.intValue() != 0);
                        }
                        int i92 = columnIndexOrThrow23;
                        i32 = i91;
                        Integer valueOf46 = query.isNull(i92) ? null : Integer.valueOf(query.getInt(i92));
                        if (valueOf46 == null) {
                            valueOf20 = null;
                        } else {
                            valueOf20 = Boolean.valueOf(valueOf46.intValue() != 0);
                        }
                        int i93 = columnIndexOrThrow24;
                        i31 = i92;
                        Integer valueOf47 = query.isNull(i93) ? null : Integer.valueOf(query.getInt(i93));
                        if (valueOf47 == null) {
                            valueOf21 = null;
                        } else {
                            valueOf21 = Boolean.valueOf(valueOf47.intValue() != 0);
                        }
                        int i94 = columnIndexOrThrow25;
                        i30 = i93;
                        Integer valueOf48 = query.isNull(i94) ? null : Integer.valueOf(query.getInt(i94));
                        if (valueOf48 == null) {
                            valueOf22 = null;
                        } else {
                            valueOf22 = Boolean.valueOf(valueOf48.intValue() != 0);
                        }
                        int i95 = columnIndexOrThrow26;
                        i29 = i94;
                        Integer valueOf49 = query.isNull(i95) ? null : Integer.valueOf(query.getInt(i95));
                        if (valueOf49 == null) {
                            valueOf23 = null;
                        } else {
                            valueOf23 = Boolean.valueOf(valueOf49.intValue() != 0);
                        }
                        int i96 = columnIndexOrThrow27;
                        i28 = i95;
                        Integer valueOf50 = query.isNull(i96) ? null : Integer.valueOf(query.getInt(i96));
                        if (valueOf50 == null) {
                            valueOf24 = null;
                        } else {
                            valueOf24 = Boolean.valueOf(valueOf50.intValue() != 0);
                        }
                        int i97 = columnIndexOrThrow28;
                        i27 = i96;
                        Integer valueOf51 = query.isNull(i97) ? null : Integer.valueOf(query.getInt(i97));
                        if (valueOf51 == null) {
                            valueOf25 = null;
                        } else {
                            valueOf25 = Boolean.valueOf(valueOf51.intValue() != 0);
                        }
                        int i98 = columnIndexOrThrow29;
                        i26 = i97;
                        Integer valueOf52 = query.isNull(i98) ? null : Integer.valueOf(query.getInt(i98));
                        int i99 = columnIndexOrThrow30;
                        i25 = i98;
                        Integer valueOf53 = query.isNull(i99) ? null : Integer.valueOf(query.getInt(i99));
                        int i100 = columnIndexOrThrow31;
                        i24 = i99;
                        Integer valueOf54 = query.isNull(i100) ? null : Integer.valueOf(query.getInt(i100));
                        int i101 = columnIndexOrThrow32;
                        i23 = i100;
                        Integer valueOf55 = query.isNull(i101) ? null : Integer.valueOf(query.getInt(i101));
                        int i102 = columnIndexOrThrow33;
                        i22 = i101;
                        Integer valueOf56 = query.isNull(i102) ? null : Integer.valueOf(query.getInt(i102));
                        int i103 = columnIndexOrThrow34;
                        i21 = i102;
                        Integer valueOf57 = query.isNull(i103) ? null : Integer.valueOf(query.getInt(i103));
                        int i104 = columnIndexOrThrow35;
                        i20 = i103;
                        Integer valueOf58 = query.isNull(i104) ? null : Integer.valueOf(query.getInt(i104));
                        int i105 = columnIndexOrThrow36;
                        i19 = i104;
                        Integer valueOf59 = query.isNull(i105) ? null : Integer.valueOf(query.getInt(i105));
                        int i106 = columnIndexOrThrow37;
                        i18 = i105;
                        Integer valueOf60 = query.isNull(i106) ? null : Integer.valueOf(query.getInt(i106));
                        int i107 = columnIndexOrThrow38;
                        i17 = i106;
                        Integer valueOf61 = query.isNull(i107) ? null : Integer.valueOf(query.getInt(i107));
                        int i108 = columnIndexOrThrow39;
                        i16 = i107;
                        Integer valueOf62 = query.isNull(i108) ? null : Integer.valueOf(query.getInt(i108));
                        int i109 = columnIndexOrThrow40;
                        i15 = i108;
                        Integer valueOf63 = query.isNull(i109) ? null : Integer.valueOf(query.getInt(i109));
                        int i110 = columnIndexOrThrow41;
                        i14 = i109;
                        Integer valueOf64 = query.isNull(i110) ? null : Integer.valueOf(query.getInt(i110));
                        int i111 = columnIndexOrThrow42;
                        i13 = i110;
                        Integer valueOf65 = query.isNull(i111) ? null : Integer.valueOf(query.getInt(i111));
                        int i112 = columnIndexOrThrow43;
                        i12 = i111;
                        Integer valueOf66 = query.isNull(i112) ? null : Integer.valueOf(query.getInt(i112));
                        int i113 = columnIndexOrThrow44;
                        i11 = i112;
                        Integer valueOf67 = query.isNull(i113) ? null : Integer.valueOf(query.getInt(i113));
                        int i114 = columnIndexOrThrow45;
                        i10 = i113;
                        Integer valueOf68 = query.isNull(i114) ? null : Integer.valueOf(query.getInt(i114));
                        int i115 = columnIndexOrThrow46;
                        i9 = i114;
                        Integer valueOf69 = query.isNull(i115) ? null : Integer.valueOf(query.getInt(i115));
                        int i116 = columnIndexOrThrow47;
                        i8 = i115;
                        Integer valueOf70 = query.isNull(i116) ? null : Integer.valueOf(query.getInt(i116));
                        int i117 = columnIndexOrThrow48;
                        i7 = i116;
                        Integer valueOf71 = query.isNull(i117) ? null : Integer.valueOf(query.getInt(i117));
                        int i118 = columnIndexOrThrow49;
                        i6 = i117;
                        Integer valueOf72 = query.isNull(i118) ? null : Integer.valueOf(query.getInt(i118));
                        int i119 = columnIndexOrThrow50;
                        i5 = i118;
                        Integer valueOf73 = query.isNull(i119) ? null : Integer.valueOf(query.getInt(i119));
                        int i120 = columnIndexOrThrow51;
                        i4 = i119;
                        Integer valueOf74 = query.isNull(i120) ? null : Integer.valueOf(query.getInt(i120));
                        int i121 = columnIndexOrThrow52;
                        i3 = i120;
                        Integer valueOf75 = query.isNull(i121) ? null : Integer.valueOf(query.getInt(i121));
                        i2 = i121;
                        if (query.isNull(i81)) {
                            i = i81;
                            valueOf26 = null;
                        } else {
                            valueOf26 = Integer.valueOf(query.getInt(i81));
                            i = i81;
                        }
                        _map.put(string, new FormC1KesesuaianAdministration(string2, z, string3, valueOf, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, valueOf10, valueOf11, valueOf12, valueOf13, valueOf14, valueOf15, valueOf16, valueOf17, valueOf18, valueOf19, valueOf20, valueOf21, valueOf22, valueOf23, valueOf24, valueOf25, valueOf52, valueOf53, valueOf54, valueOf55, valueOf56, valueOf57, valueOf58, valueOf59, valueOf60, valueOf61, valueOf62, valueOf63, valueOf64, valueOf65, valueOf66, valueOf67, valueOf68, valueOf69, valueOf70, valueOf71, valueOf72, valueOf73, valueOf74, valueOf75, valueOf26));
                    } else {
                        i = i81;
                        i2 = columnIndexOrThrow52;
                        i3 = columnIndexOrThrow51;
                        i4 = columnIndexOrThrow50;
                        i5 = columnIndexOrThrow49;
                        i6 = columnIndexOrThrow48;
                        i7 = columnIndexOrThrow47;
                        i8 = columnIndexOrThrow46;
                        i9 = columnIndexOrThrow45;
                        i10 = columnIndexOrThrow44;
                        i11 = columnIndexOrThrow43;
                        i12 = columnIndexOrThrow42;
                        i13 = columnIndexOrThrow41;
                        i14 = columnIndexOrThrow40;
                        i15 = columnIndexOrThrow39;
                        i16 = columnIndexOrThrow38;
                        i17 = columnIndexOrThrow37;
                        i18 = columnIndexOrThrow36;
                        i19 = columnIndexOrThrow35;
                        i20 = columnIndexOrThrow34;
                        i21 = columnIndexOrThrow33;
                        i22 = columnIndexOrThrow32;
                        i23 = columnIndexOrThrow31;
                        i24 = columnIndexOrThrow30;
                        i25 = columnIndexOrThrow29;
                        i26 = columnIndexOrThrow28;
                        i27 = columnIndexOrThrow27;
                        i28 = columnIndexOrThrow26;
                        i29 = columnIndexOrThrow25;
                        i30 = columnIndexOrThrow24;
                        i31 = columnIndexOrThrow23;
                        i32 = columnIndexOrThrow22;
                        i33 = columnIndexOrThrow21;
                        i34 = columnIndexOrThrow20;
                        i35 = columnIndexOrThrow19;
                        i36 = columnIndexOrThrow18;
                        i37 = columnIndexOrThrow17;
                        i38 = columnIndexOrThrow16;
                        i39 = columnIndexOrThrow15;
                        i40 = columnIndexOrThrow14;
                        i41 = columnIndexOrThrow13;
                        i42 = columnIndexOrThrow;
                    }
                    columnIndexOrThrow = i42;
                    columnIndexOrThrow13 = i41;
                    columnIndexOrThrow14 = i40;
                    columnIndexOrThrow15 = i39;
                    columnIndexOrThrow16 = i38;
                    columnIndexOrThrow17 = i37;
                    columnIndexOrThrow18 = i36;
                    columnIndexOrThrow19 = i35;
                    columnIndexOrThrow20 = i34;
                    columnIndexOrThrow21 = i33;
                    columnIndexOrThrow22 = i32;
                    columnIndexOrThrow23 = i31;
                    columnIndexOrThrow24 = i30;
                    columnIndexOrThrow25 = i29;
                    columnIndexOrThrow26 = i28;
                    columnIndexOrThrow27 = i27;
                    columnIndexOrThrow28 = i26;
                    columnIndexOrThrow29 = i25;
                    columnIndexOrThrow30 = i24;
                    columnIndexOrThrow31 = i23;
                    columnIndexOrThrow32 = i22;
                    columnIndexOrThrow33 = i21;
                    columnIndexOrThrow34 = i20;
                    columnIndexOrThrow35 = i19;
                    columnIndexOrThrow36 = i18;
                    columnIndexOrThrow37 = i17;
                    columnIndexOrThrow38 = i16;
                    columnIndexOrThrow39 = i15;
                    columnIndexOrThrow40 = i14;
                    columnIndexOrThrow41 = i13;
                    columnIndexOrThrow42 = i12;
                    columnIndexOrThrow43 = i11;
                    columnIndexOrThrow44 = i10;
                    columnIndexOrThrow45 = i9;
                    columnIndexOrThrow46 = i8;
                    columnIndexOrThrow47 = i7;
                    columnIndexOrThrow48 = i6;
                    columnIndexOrThrow49 = i5;
                    columnIndexOrThrow50 = i4;
                    columnIndexOrThrow51 = i3;
                    columnIndexOrThrow52 = i2;
                    columnIndexOrThrow53 = i;
                }
            }
        } finally {
            query.close();
        }
    }
}
