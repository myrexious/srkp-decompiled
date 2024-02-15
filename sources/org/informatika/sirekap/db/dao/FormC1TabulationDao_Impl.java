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
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;

/* loaded from: classes2.dex */
public final class FormC1TabulationDao_Impl implements FormC1TabulationDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1TabulationPartai> __insertionAdapterOfFormC1TabulationPartai;

    public FormC1TabulationDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1TabulationPartai = new EntityInsertionAdapter<FormC1TabulationPartai>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_tabulation_partai` (`idImage`,`formType`,`suratSahPartaiDanCalon`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1TabulationPartai value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.getFormType());
                if (value.getSuratSahPartaiDanCalon() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, value.getSuratSahPartaiDanCalon().intValue());
                }
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    public void insertAllTabulationPartai(final List<FormC1TabulationPartai> forms) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1TabulationPartai.insert(forms);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    public LiveData<FormC1TabulationComplete> getByIdImage(final String idImage) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM election_page WHERE idImage = ?", 1);
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_tabulation_candidate_vote", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_tabulation_candidate_vote", "election_page"}, true, new Callable<FormC1TabulationComplete>() { // from class: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.2
            /* JADX WARN: Removed duplicated region for block: B:288:0x021d  */
            /* JADX WARN: Removed duplicated region for block: B:289:0x0220 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:292:0x022c  */
            /* JADX WARN: Removed duplicated region for block: B:293:0x022f A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:296:0x023b  */
            /* JADX WARN: Removed duplicated region for block: B:297:0x023e A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:300:0x025a  */
            /* JADX WARN: Removed duplicated region for block: B:301:0x025d A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:304:0x0269  */
            /* JADX WARN: Removed duplicated region for block: B:305:0x026c A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:308:0x0278  */
            /* JADX WARN: Removed duplicated region for block: B:309:0x027b A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:312:0x0287  */
            /* JADX WARN: Removed duplicated region for block: B:313:0x028a A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:316:0x0297  */
            /* JADX WARN: Removed duplicated region for block: B:317:0x029a  */
            /* JADX WARN: Removed duplicated region for block: B:320:0x02a2  */
            /* JADX WARN: Removed duplicated region for block: B:321:0x02a5 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:324:0x02b1  */
            /* JADX WARN: Removed duplicated region for block: B:325:0x02b3 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:327:0x02bd  */
            /* JADX WARN: Removed duplicated region for block: B:328:0x02c2 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:335:0x02d9  */
            /* JADX WARN: Removed duplicated region for block: B:336:0x02de A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:339:0x02ec  */
            /* JADX WARN: Removed duplicated region for block: B:340:0x02f1 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:343:0x02ff  */
            /* JADX WARN: Removed duplicated region for block: B:344:0x0304 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:347:0x0312  */
            /* JADX WARN: Removed duplicated region for block: B:348:0x0317  */
            /* JADX WARN: Removed duplicated region for block: B:351:0x0321  */
            /* JADX WARN: Removed duplicated region for block: B:352:0x0324  */
            /* JADX WARN: Removed duplicated region for block: B:355:0x032c  */
            /* JADX WARN: Removed duplicated region for block: B:356:0x032f A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:360:0x0347 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:361:0x0355  */
            /* JADX WARN: Removed duplicated region for block: B:363:0x0358 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:364:0x035f  */
            /* JADX WARN: Removed duplicated region for block: B:367:0x0366 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:368:0x0375  */
            /* JADX WARN: Removed duplicated region for block: B:371:0x037c A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:372:0x038b  */
            /* JADX WARN: Removed duplicated region for block: B:375:0x0392 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:376:0x03a0  */
            /* JADX WARN: Removed duplicated region for block: B:378:0x03a3 A[Catch: all -> 0x03b5, TryCatch #1 {all -> 0x03b5, blocks: (B:231:0x0144, B:233:0x014a, B:235:0x0150, B:237:0x0156, B:239:0x015c, B:241:0x0162, B:243:0x0168, B:245:0x016e, B:247:0x0176, B:249:0x017e, B:251:0x0186, B:253:0x0190, B:255:0x019a, B:257:0x01a0, B:259:0x01aa, B:261:0x01b4, B:263:0x01be, B:265:0x01c8, B:267:0x01d2, B:269:0x01dc, B:286:0x0217, B:290:0x0226, B:294:0x0235, B:298:0x0244, B:302:0x0263, B:306:0x0272, B:310:0x0281, B:314:0x0290, B:318:0x029c, B:322:0x02ab, B:333:0x02d3, B:337:0x02e6, B:341:0x02f9, B:345:0x030c, B:349:0x031b, B:353:0x0326, B:357:0x0339, B:358:0x0341, B:360:0x0347, B:363:0x0358, B:365:0x0360, B:367:0x0366, B:369:0x0376, B:371:0x037c, B:373:0x038c, B:375:0x0392, B:378:0x03a3, B:380:0x03ab, B:356:0x032f, B:344:0x0304, B:340:0x02f1, B:336:0x02de, B:328:0x02c2, B:332:0x02cb, B:325:0x02b3, B:321:0x02a5, B:313:0x028a, B:309:0x027b, B:305:0x026c, B:301:0x025d, B:297:0x023e, B:293:0x022f, B:289:0x0220), top: B:397:0x0144 }] */
            /* JADX WARN: Removed duplicated region for block: B:379:0x03aa  */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public org.informatika.sirekap.model.FormC1TabulationComplete call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 994
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.AnonymousClass2.call():org.informatika.sirekap.model.FormC1TabulationComplete");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    public LiveData<List<FormC1TabulationComplete>> getListByIdImages(final List<String> idImages) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT * FROM election_page WHERE idImage IN (");
        int size = idImages.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (String str : idImages) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_tabulation_candidate_vote", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_tabulation_candidate_vote", "election_page"}, true, new Callable<List<FormC1TabulationComplete>>() { // from class: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.3
            /* JADX WARN: Removed duplicated region for block: B:302:0x0234  */
            /* JADX WARN: Removed duplicated region for block: B:303:0x0237 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:306:0x0243  */
            /* JADX WARN: Removed duplicated region for block: B:307:0x0246 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:310:0x0252  */
            /* JADX WARN: Removed duplicated region for block: B:311:0x0255 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:314:0x0271  */
            /* JADX WARN: Removed duplicated region for block: B:315:0x0274 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:318:0x0280  */
            /* JADX WARN: Removed duplicated region for block: B:319:0x0283 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:322:0x028f  */
            /* JADX WARN: Removed duplicated region for block: B:323:0x0292 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:326:0x029e  */
            /* JADX WARN: Removed duplicated region for block: B:327:0x02a1 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:330:0x02af  */
            /* JADX WARN: Removed duplicated region for block: B:331:0x02b2  */
            /* JADX WARN: Removed duplicated region for block: B:334:0x02ba  */
            /* JADX WARN: Removed duplicated region for block: B:335:0x02bd A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:338:0x02c9  */
            /* JADX WARN: Removed duplicated region for block: B:339:0x02cc A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:341:0x02d6  */
            /* JADX WARN: Removed duplicated region for block: B:343:0x02df A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:350:0x02f7  */
            /* JADX WARN: Removed duplicated region for block: B:352:0x0300 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:355:0x030d  */
            /* JADX WARN: Removed duplicated region for block: B:357:0x0316 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:360:0x0323  */
            /* JADX WARN: Removed duplicated region for block: B:362:0x032c A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:365:0x0339  */
            /* JADX WARN: Removed duplicated region for block: B:366:0x033c  */
            /* JADX WARN: Removed duplicated region for block: B:369:0x034a  */
            /* JADX WARN: Removed duplicated region for block: B:370:0x034d  */
            /* JADX WARN: Removed duplicated region for block: B:373:0x0355  */
            /* JADX WARN: Removed duplicated region for block: B:374:0x0358 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:378:0x0371 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:379:0x0382  */
            /* JADX WARN: Removed duplicated region for block: B:381:0x038b A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:384:0x0398 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:385:0x03a9  */
            /* JADX WARN: Removed duplicated region for block: B:388:0x03b5 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:389:0x03c6  */
            /* JADX WARN: Removed duplicated region for block: B:392:0x03d2 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            /* JADX WARN: Removed duplicated region for block: B:393:0x03e1  */
            /* JADX WARN: Removed duplicated region for block: B:395:0x03e8 A[Catch: all -> 0x0424, TryCatch #2 {all -> 0x0424, blocks: (B:245:0x014d, B:247:0x0153, B:249:0x0159, B:251:0x015f, B:253:0x0165, B:255:0x016b, B:257:0x0171, B:259:0x0177, B:261:0x017f, B:263:0x0187, B:265:0x0191, B:267:0x019b, B:269:0x01a5, B:271:0x01ab, B:273:0x01b5, B:275:0x01bf, B:277:0x01c9, B:279:0x01d3, B:281:0x01dd, B:283:0x01e7, B:300:0x022e, B:304:0x023d, B:308:0x024c, B:312:0x025b, B:316:0x027a, B:320:0x0289, B:324:0x0298, B:328:0x02a7, B:332:0x02b4, B:336:0x02c3, B:348:0x02f1, B:353:0x0307, B:358:0x031d, B:363:0x0333, B:367:0x033e, B:371:0x034f, B:375:0x0362, B:376:0x036b, B:378:0x0371, B:381:0x038b, B:382:0x0390, B:384:0x0398, B:386:0x03af, B:388:0x03b5, B:390:0x03cc, B:392:0x03d2, B:395:0x03e8, B:396:0x03ed, B:374:0x0358, B:362:0x032c, B:357:0x0316, B:352:0x0300, B:343:0x02df, B:347:0x02ea, B:339:0x02cc, B:335:0x02bd, B:327:0x02a1, B:323:0x0292, B:319:0x0283, B:315:0x0274, B:311:0x0255, B:307:0x0246, B:303:0x0237), top: B:421:0x014d }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<org.informatika.sirekap.model.FormC1TabulationComplete> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 1110
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.AnonymousClass3.call():java.util.List");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0268 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0277 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0286 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:342:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x02a5 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:346:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x02b4 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:350:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x02c3 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x02d2 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:358:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x02ec A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:366:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x02fb A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x030e A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x032f A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:383:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0345 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x035b A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0387 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:406:0x03a0 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:407:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x03ba A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x03c7 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:413:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x03e4 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:417:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0401 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0417 A[Catch: all -> 0x0453, TryCatch #0 {all -> 0x0453, blocks: (B:273:0x017e, B:275:0x0184, B:277:0x018a, B:279:0x0190, B:281:0x0196, B:283:0x019c, B:285:0x01a2, B:287:0x01a8, B:289:0x01b0, B:291:0x01b8, B:293:0x01c2, B:295:0x01cc, B:297:0x01d6, B:299:0x01dc, B:301:0x01e6, B:303:0x01f0, B:305:0x01fa, B:307:0x0204, B:309:0x020e, B:311:0x0218, B:328:0x025f, B:332:0x026e, B:336:0x027d, B:340:0x028c, B:344:0x02ab, B:348:0x02ba, B:352:0x02c9, B:356:0x02d8, B:360:0x02e3, B:364:0x02f2, B:376:0x0320, B:381:0x0336, B:386:0x034c, B:391:0x0362, B:395:0x036d, B:399:0x037e, B:403:0x0391, B:404:0x039a, B:406:0x03a0, B:409:0x03ba, B:410:0x03bf, B:412:0x03c7, B:414:0x03de, B:416:0x03e4, B:418:0x03fb, B:420:0x0401, B:423:0x0417, B:424:0x041c, B:402:0x0387, B:390:0x035b, B:385:0x0345, B:380:0x032f, B:371:0x030e, B:375:0x0319, B:367:0x02fb, B:363:0x02ec, B:355:0x02d2, B:351:0x02c3, B:347:0x02b4, B:343:0x02a5, B:339:0x0286, B:335:0x0277, B:331:0x0268), top: B:448:0x017e }] */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.room.RoomSQLiteQuery, androidx.sqlite.db.SupportSQLiteQuery] */
    /* JADX WARN: Type inference failed for: r2v4 */
    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<org.informatika.sirekap.model.FormC1TabulationComplete> getListByIdImagesSync(final java.util.List<java.lang.String> r58) {
        /*
            Method dump skipped, instructions count: 1155
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.getListByIdImagesSync(java.util.List):java.util.List");
    }

    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    public LiveData<FormC1TabulationPartaiComplete> getTabulationPartaiByIdImage(final String idImage) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM election_page WHERE idImage = ?", 1);
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_tabulation_partai", "form_c1_tabulation_candidate_vote", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_tabulation_partai", "form_c1_kesesuaian_tabulation_candidate_vote", "election_page"}, true, new Callable<FormC1TabulationPartaiComplete>() { // from class: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.4
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
            public org.informatika.sirekap.model.FormC1TabulationPartaiComplete call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 1116
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.AnonymousClass4.call():org.informatika.sirekap.model.FormC1TabulationPartaiComplete");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    public LiveData<List<FormC1TabulationPartaiComplete>> getListTabulationPartaiByIdImages(final List<String> idImages) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT * FROM election_page WHERE idImage IN (");
        int size = idImages.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (String str : idImages) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"form_c1_tabulation_partai", "form_c1_tabulation_candidate_vote", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_tabulation_partai", "form_c1_kesesuaian_tabulation_candidate_vote", "election_page"}, true, new Callable<List<FormC1TabulationPartaiComplete>>() { // from class: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.5
            /* JADX WARN: Removed duplicated region for block: B:324:0x0276  */
            /* JADX WARN: Removed duplicated region for block: B:325:0x0279 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:328:0x0285  */
            /* JADX WARN: Removed duplicated region for block: B:329:0x0288 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:332:0x0294  */
            /* JADX WARN: Removed duplicated region for block: B:333:0x0297 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:336:0x02b3  */
            /* JADX WARN: Removed duplicated region for block: B:337:0x02b6 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:340:0x02c2  */
            /* JADX WARN: Removed duplicated region for block: B:341:0x02c5 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:344:0x02d1  */
            /* JADX WARN: Removed duplicated region for block: B:345:0x02d4 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:348:0x02e0  */
            /* JADX WARN: Removed duplicated region for block: B:349:0x02e3 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:352:0x02f1  */
            /* JADX WARN: Removed duplicated region for block: B:353:0x02f4  */
            /* JADX WARN: Removed duplicated region for block: B:356:0x02fc  */
            /* JADX WARN: Removed duplicated region for block: B:357:0x02ff A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:360:0x030b  */
            /* JADX WARN: Removed duplicated region for block: B:361:0x030e A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:363:0x0318  */
            /* JADX WARN: Removed duplicated region for block: B:365:0x0321 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:372:0x0339  */
            /* JADX WARN: Removed duplicated region for block: B:374:0x0342 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:377:0x034f  */
            /* JADX WARN: Removed duplicated region for block: B:379:0x0358 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:382:0x0365  */
            /* JADX WARN: Removed duplicated region for block: B:384:0x036e A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:387:0x037b  */
            /* JADX WARN: Removed duplicated region for block: B:388:0x037e  */
            /* JADX WARN: Removed duplicated region for block: B:391:0x038c  */
            /* JADX WARN: Removed duplicated region for block: B:392:0x038f  */
            /* JADX WARN: Removed duplicated region for block: B:395:0x0397  */
            /* JADX WARN: Removed duplicated region for block: B:396:0x039a A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:400:0x03b3 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:401:0x03c6  */
            /* JADX WARN: Removed duplicated region for block: B:404:0x03d4 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:405:0x03e3  */
            /* JADX WARN: Removed duplicated region for block: B:407:0x03ea A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:410:0x03f7 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:411:0x0408  */
            /* JADX WARN: Removed duplicated region for block: B:414:0x0414 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:415:0x0425  */
            /* JADX WARN: Removed duplicated region for block: B:418:0x0431 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:419:0x0442  */
            /* JADX WARN: Removed duplicated region for block: B:422:0x044e A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            /* JADX WARN: Removed duplicated region for block: B:423:0x045d  */
            /* JADX WARN: Removed duplicated region for block: B:425:0x0464 A[Catch: all -> 0x04a8, TryCatch #2 {all -> 0x04a8, blocks: (B:265:0x017f, B:267:0x0185, B:269:0x018b, B:271:0x0191, B:273:0x0197, B:275:0x019d, B:277:0x01a5, B:279:0x01ad, B:281:0x01b7, B:283:0x01c1, B:285:0x01cb, B:287:0x01d5, B:289:0x01df, B:291:0x01e5, B:293:0x01ef, B:295:0x01f9, B:297:0x0203, B:299:0x020d, B:301:0x0217, B:303:0x0221, B:322:0x0270, B:326:0x027f, B:330:0x028e, B:334:0x029d, B:338:0x02bc, B:342:0x02cb, B:346:0x02da, B:350:0x02e9, B:354:0x02f6, B:358:0x0305, B:370:0x0333, B:375:0x0349, B:380:0x035f, B:385:0x0375, B:389:0x0380, B:393:0x0391, B:397:0x03a4, B:398:0x03ad, B:400:0x03b3, B:402:0x03ce, B:404:0x03d4, B:407:0x03ea, B:408:0x03ef, B:410:0x03f7, B:412:0x040e, B:414:0x0414, B:416:0x042b, B:418:0x0431, B:420:0x0448, B:422:0x044e, B:425:0x0464, B:426:0x0469, B:396:0x039a, B:384:0x036e, B:379:0x0358, B:374:0x0342, B:365:0x0321, B:369:0x032c, B:361:0x030e, B:357:0x02ff, B:349:0x02e3, B:345:0x02d4, B:341:0x02c5, B:337:0x02b6, B:333:0x0297, B:329:0x0288, B:325:0x0279), top: B:451:0x017f }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<org.informatika.sirekap.model.FormC1TabulationPartaiComplete> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 1242
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.AnonymousClass5.call():java.util.List");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:352:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x02a6 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:356:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x02b5 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:360:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x02c4 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:364:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x02e3 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:368:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x02f2 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0301 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:376:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0310 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:380:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x032a A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0339 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x034c A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x036d A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:405:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0383 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0399 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:415:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x03c5 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:428:0x03de A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:429:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x03ff A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:433:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0415 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0422 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x043f A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x045c A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:447:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x0479 A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x048f A[Catch: all -> 0x04d3, TryCatch #0 {all -> 0x04d3, blocks: (B:293:0x01ac, B:295:0x01b2, B:297:0x01b8, B:299:0x01be, B:301:0x01c4, B:303:0x01ca, B:305:0x01d2, B:307:0x01da, B:309:0x01e4, B:311:0x01ee, B:313:0x01f8, B:315:0x0202, B:317:0x020c, B:319:0x0212, B:321:0x021c, B:323:0x0226, B:325:0x0230, B:327:0x023a, B:329:0x0244, B:331:0x024e, B:350:0x029d, B:354:0x02ac, B:358:0x02bb, B:362:0x02ca, B:366:0x02e9, B:370:0x02f8, B:374:0x0307, B:378:0x0316, B:382:0x0321, B:386:0x0330, B:398:0x035e, B:403:0x0374, B:408:0x038a, B:413:0x03a0, B:417:0x03ab, B:421:0x03bc, B:425:0x03cf, B:426:0x03d8, B:428:0x03de, B:430:0x03f9, B:432:0x03ff, B:435:0x0415, B:436:0x041a, B:438:0x0422, B:440:0x0439, B:442:0x043f, B:444:0x0456, B:446:0x045c, B:448:0x0473, B:450:0x0479, B:453:0x048f, B:454:0x0494, B:424:0x03c5, B:412:0x0399, B:407:0x0383, B:402:0x036d, B:393:0x034c, B:397:0x0357, B:389:0x0339, B:385:0x032a, B:377:0x0310, B:373:0x0301, B:369:0x02f2, B:365:0x02e3, B:361:0x02c4, B:357:0x02b5, B:353:0x02a6), top: B:478:0x01ac }] */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.room.RoomSQLiteQuery, androidx.sqlite.db.SupportSQLiteQuery] */
    /* JADX WARN: Type inference failed for: r2v4 */
    @Override // org.informatika.sirekap.db.dao.FormC1TabulationDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<org.informatika.sirekap.model.FormC1TabulationPartaiComplete> getListTabulationPartaiByIdImagesSync(final java.util.List<java.lang.String> r62) {
        /*
            Method dump skipped, instructions count: 1283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl.getListTabulationPartaiByIdImagesSync(java.util.List):java.util.List");
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
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

    public void __fetchRelationshipformC1TabulationPartaiAsorgInformatikaSirekapModelFormC1TabulationPartai(final ArrayMap<String, FormC1TabulationPartai> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1TabulationPartai> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), null);
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1TabulationPartaiAsorgInformatikaSirekapModelFormC1TabulationPartai(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1TabulationPartai>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1TabulationPartaiAsorgInformatikaSirekapModelFormC1TabulationPartai(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1TabulationPartai>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`formType`,`suratSahPartaiDanCalon` FROM `form_c1_tabulation_partai` WHERE `idImage` IN (");
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "suratSahPartaiDanCalon");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex)) {
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        _map.put(string, new FormC1TabulationPartai(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3))));
                    }
                }
            }
        } finally {
            query.close();
        }
    }

    public void __fetchRelationshipformC1KesesuaianTabulationPartaiAsorgInformatikaSirekapModelFormC1KesesuaianTabulationPartai(final ArrayMap<String, FormC1KesesuaianTabulationPartai> _map) {
        Boolean valueOf;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, FormC1KesesuaianTabulationPartai> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), null);
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipformC1KesesuaianTabulationPartaiAsorgInformatikaSirekapModelFormC1KesesuaianTabulationPartai(arrayMap);
                    _map.putAll((Map<? extends String, ? extends FormC1KesesuaianTabulationPartai>) arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipformC1KesesuaianTabulationPartaiAsorgInformatikaSirekapModelFormC1KesesuaianTabulationPartai(arrayMap);
                _map.putAll((Map<? extends String, ? extends FormC1KesesuaianTabulationPartai>) arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `idImage`,`suratSahPartaiDanCalon`,`suratSahPartaiDanCalonCorrected` FROM `form_c1_kesesuaian_tabulation_partai` WHERE `idImage` IN (");
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "suratSahPartaiDanCalon");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "suratSahPartaiDanCalonCorrected");
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex)) {
                    String string = query.getString(columnIndex);
                    if (_map.containsKey(string)) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        Integer valueOf2 = query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2));
                        if (valueOf2 == null) {
                            valueOf = null;
                        } else {
                            valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                        }
                        _map.put(string, new FormC1KesesuaianTabulationPartai(string2, valueOf, query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3))));
                    }
                }
            }
        } finally {
            query.close();
        }
    }
}
