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
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;

/* loaded from: classes2.dex */
public final class FormC1AdministrationHal2Dao_Impl implements FormC1AdministrationHal2Dao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1AdministrationHal2> __insertionAdapterOfFormC1AdministrationHal2;

    public FormC1AdministrationHal2Dao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1AdministrationHal2 = new EntityInsertionAdapter<FormC1AdministrationHal2>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_administration_hal2` (`idImage`,`isLn`,`isLnPos`,`formType`,`suratSuaraSah`,`suratSuaraTidakSah`,`totalSuratSuara`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1AdministrationHal2 value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.isLn() ? 1L : 0L);
                stmt.bindLong(3, value.isLnPos() ? 1L : 0L);
                stmt.bindLong(4, value.getFormType());
                if (value.getSuratSuaraSah() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, value.getSuratSuaraSah().intValue());
                }
                if (value.getSuratSuaraTidakSah() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindLong(6, value.getSuratSuaraTidakSah().intValue());
                }
                if (value.getTotalSuratSuara() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindLong(7, value.getTotalSuratSuara().intValue());
                }
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao
    public void insertAll(final List<FormC1AdministrationHal2> forms) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1AdministrationHal2.insert(forms);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao
    public LiveData<FormC1AdministrationHal2Complete> getByIdImage(final String idImage) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM election_page WHERE idImage = ?", 1);
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_administration_hal2", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_administration_hal2", "election_page"}, true, new Callable<FormC1AdministrationHal2Complete>() { // from class: org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl.2
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
            public org.informatika.sirekap.model.FormC1AdministrationHal2Complete call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 948
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl.AnonymousClass2.call():org.informatika.sirekap.model.FormC1AdministrationHal2Complete");
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
    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.informatika.sirekap.model.FormC1AdministrationHal2Complete getByIdImageSync(final java.lang.String r49) {
        /*
            Method dump skipped, instructions count: 949
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl.getByIdImageSync(java.lang.String):org.informatika.sirekap.model.FormC1AdministrationHal2Complete");
    }

    @Override // org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao
    public LiveData<FormC1AdministrationHal2PpwpComplete> getPpwpByIdImage(final String idImage) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM election_page WHERE idImage = ?", 1);
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_administration_hal2", "form_c1_tabulation_candidate_vote", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_administration_hal2", "form_c1_kesesuaian_tabulation_candidate_vote", "election_page"}, true, new Callable<FormC1AdministrationHal2PpwpComplete>() { // from class: org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl.3
            /* JADX WARN: Removed duplicated region for block: B:311:0x0260  */
            /* JADX WARN: Removed duplicated region for block: B:312:0x0263 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:315:0x026f  */
            /* JADX WARN: Removed duplicated region for block: B:316:0x0272 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:319:0x027e  */
            /* JADX WARN: Removed duplicated region for block: B:320:0x0281 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:323:0x029d  */
            /* JADX WARN: Removed duplicated region for block: B:324:0x02a0 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:327:0x02ac  */
            /* JADX WARN: Removed duplicated region for block: B:328:0x02af A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:331:0x02bb  */
            /* JADX WARN: Removed duplicated region for block: B:332:0x02be A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:335:0x02ca  */
            /* JADX WARN: Removed duplicated region for block: B:336:0x02cd A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:339:0x02da  */
            /* JADX WARN: Removed duplicated region for block: B:340:0x02dd  */
            /* JADX WARN: Removed duplicated region for block: B:343:0x02e5  */
            /* JADX WARN: Removed duplicated region for block: B:344:0x02e8 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:347:0x02f4  */
            /* JADX WARN: Removed duplicated region for block: B:348:0x02f6 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:350:0x0300  */
            /* JADX WARN: Removed duplicated region for block: B:351:0x0305 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:358:0x031c  */
            /* JADX WARN: Removed duplicated region for block: B:359:0x0321 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:362:0x032f  */
            /* JADX WARN: Removed duplicated region for block: B:363:0x0334 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:366:0x0342  */
            /* JADX WARN: Removed duplicated region for block: B:367:0x0347 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:370:0x0355  */
            /* JADX WARN: Removed duplicated region for block: B:371:0x035a  */
            /* JADX WARN: Removed duplicated region for block: B:374:0x0364  */
            /* JADX WARN: Removed duplicated region for block: B:375:0x0367  */
            /* JADX WARN: Removed duplicated region for block: B:378:0x036f  */
            /* JADX WARN: Removed duplicated region for block: B:379:0x0372 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:383:0x038b A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:384:0x039b  */
            /* JADX WARN: Removed duplicated region for block: B:387:0x03a3 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:388:0x03b1  */
            /* JADX WARN: Removed duplicated region for block: B:390:0x03b4 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:391:0x03bc  */
            /* JADX WARN: Removed duplicated region for block: B:394:0x03c4 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:395:0x03d4  */
            /* JADX WARN: Removed duplicated region for block: B:398:0x03dc A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:399:0x03ec  */
            /* JADX WARN: Removed duplicated region for block: B:402:0x03f4 A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:403:0x0404  */
            /* JADX WARN: Removed duplicated region for block: B:406:0x040c A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:407:0x041a  */
            /* JADX WARN: Removed duplicated region for block: B:409:0x041d A[Catch: all -> 0x042f, TryCatch #2 {all -> 0x042f, blocks: (B:252:0x0176, B:254:0x017c, B:256:0x0182, B:258:0x0188, B:260:0x018e, B:262:0x0194, B:264:0x019c, B:266:0x01a4, B:268:0x01ac, B:270:0x01b6, B:272:0x01c0, B:274:0x01ca, B:276:0x01d4, B:278:0x01da, B:280:0x01e4, B:282:0x01ee, B:284:0x01f8, B:286:0x0202, B:288:0x020c, B:290:0x0216, B:309:0x025a, B:313:0x0269, B:317:0x0278, B:321:0x0287, B:325:0x02a6, B:329:0x02b5, B:333:0x02c4, B:337:0x02d3, B:341:0x02df, B:345:0x02ee, B:356:0x0316, B:360:0x0329, B:364:0x033c, B:368:0x034f, B:372:0x035e, B:376:0x0369, B:380:0x037c, B:381:0x0385, B:383:0x038b, B:385:0x039d, B:387:0x03a3, B:390:0x03b4, B:392:0x03be, B:394:0x03c4, B:396:0x03d6, B:398:0x03dc, B:400:0x03ee, B:402:0x03f4, B:404:0x0406, B:406:0x040c, B:409:0x041d, B:411:0x0427, B:379:0x0372, B:367:0x0347, B:363:0x0334, B:359:0x0321, B:351:0x0305, B:355:0x030e, B:348:0x02f6, B:344:0x02e8, B:336:0x02cd, B:332:0x02be, B:328:0x02af, B:324:0x02a0, B:320:0x0281, B:316:0x0272, B:312:0x0263), top: B:430:0x0176 }] */
            /* JADX WARN: Removed duplicated region for block: B:410:0x0425  */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 1116
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl.AnonymousClass3.call():org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public void __fetchRelationshipformC1AdministrationHal2AsorgInformatikaSirekapModelFormC1AdministrationHal2(final ArrayMap<String, FormC1AdministrationHal2> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1AdministrationHal2> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), null);
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1AdministrationHal2AsorgInformatikaSirekapModelFormC1AdministrationHal2(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1AdministrationHal2>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1AdministrationHal2AsorgInformatikaSirekapModelFormC1AdministrationHal2(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1AdministrationHal2>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`isLn`,`isLnPos`,`formType`,`suratSuaraSah`,`suratSuaraTidakSah`,`totalSuratSuara` FROM `form_c1_administration_hal2` WHERE `idImage` IN (");
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "isLn");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "isLnPos");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "formType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "suratSuaraSah");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "suratSuaraTidakSah");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "totalSuratSuara");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex)) {
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        _map.put(string, new FormC1AdministrationHal2(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2) != 0, query.getInt(columnIndexOrThrow3) != 0, query.getInt(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5)), query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)), query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7))));
                    }
                }
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

    public void __fetchRelationshipformC1KesesuaianAdministrationHal2AsorgInformatikaSirekapModelFormC1KesesuaianAdministrationHal2(final ArrayMap<String, FormC1KesesuaianAdministrationHal2> _map) {
        Boolean valueOf;
        Boolean valueOf2;
        Boolean valueOf3;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1KesesuaianAdministrationHal2> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), null);
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1KesesuaianAdministrationHal2AsorgInformatikaSirekapModelFormC1KesesuaianAdministrationHal2(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1KesesuaianAdministrationHal2>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1KesesuaianAdministrationHal2AsorgInformatikaSirekapModelFormC1KesesuaianAdministrationHal2(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1KesesuaianAdministrationHal2>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`suratSuaraSah`,`suratSuaraTidakSah`,`totalSuratSuara`,`suratSuaraSahCorrected`,`suratSuaraTidakSahCorrected`,`totalSuratSuaraCorrected` FROM `form_c1_kesesuaian_administration_hal2` WHERE `idImage` IN (");
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "suratSuaraSah");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "suratSuaraTidakSah");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "totalSuratSuara");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "suratSuaraSahCorrected");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "suratSuaraTidakSahCorrected");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "totalSuratSuaraCorrected");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex)) {
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        Integer valueOf4 = query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2));
                        if (valueOf4 == null) {
                            valueOf = null;
                        } else {
                            valueOf = Boolean.valueOf(valueOf4.intValue() != 0);
                        }
                        Integer valueOf5 = query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3));
                        if (valueOf5 == null) {
                            valueOf2 = null;
                        } else {
                            valueOf2 = Boolean.valueOf(valueOf5.intValue() != 0);
                        }
                        Integer valueOf6 = query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4));
                        if (valueOf6 == null) {
                            valueOf3 = null;
                        } else {
                            valueOf3 = Boolean.valueOf(valueOf6.intValue() != 0);
                        }
                        _map.put(string, new FormC1KesesuaianAdministrationHal2(string2, valueOf, valueOf2, valueOf3, query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5)), query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)), query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7))));
                    }
                }
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipformC1TabulationCandidateVoteAsorgInformatikaSirekapModelFormC1TabulationCandidateVote(final ArrayMap<String, ArrayList<FormC1TabulationCandidateVote>> _map) {
        ArrayList<FormC1TabulationCandidateVote> arrayList;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<FormC1TabulationCandidateVote>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1TabulationCandidateVoteAsorgInformatikaSirekapModelFormC1TabulationCandidateVote(arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1TabulationCandidateVoteAsorgInformatikaSirekapModelFormC1TabulationCandidateVote(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`index`,`vote` FROM `form_c1_tabulation_candidate_vote` WHERE `idImage` IN (");
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.INDEX);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "vote");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex) && (arrayList = _map.get(query.getString(columnIndex))) != null) {
                    arrayList.add(new FormC1TabulationCandidateVote(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3)));
                }
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipformC1KesesuaianTabulationCandidateVoteAsorgInformatikaSirekapModelFormC1KesesuaianTabulationCandidateVote(final ArrayMap<String, ArrayList<FormC1KesesuaianTabulationCandidateVote>> _map) {
        ArrayList<FormC1KesesuaianTabulationCandidateVote> arrayList;
        Boolean valueOf;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<FormC1KesesuaianTabulationCandidateVote>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1KesesuaianTabulationCandidateVoteAsorgInformatikaSirekapModelFormC1KesesuaianTabulationCandidateVote(arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1KesesuaianTabulationCandidateVoteAsorgInformatikaSirekapModelFormC1KesesuaianTabulationCandidateVote(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`index`,`vote`,`voteCorrected` FROM `form_c1_kesesuaian_tabulation_candidate_vote` WHERE `idImage` IN (");
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.INDEX);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "vote");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "voteCorrected");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex) && (arrayList = _map.get(query.getString(columnIndex))) != null) {
                    String string = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                    int i4 = query.getInt(columnIndexOrThrow2);
                    Integer valueOf2 = query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3));
                    if (valueOf2 == null) {
                        valueOf = null;
                    } else {
                        valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                    }
                    arrayList.add(new FormC1KesesuaianTabulationCandidateVote(string, i4, valueOf, query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4))));
                }
            }
        } finally {
            query.close();
        }
    }
}
