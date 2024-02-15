package org.informatika.sirekap.ui.electionDetail;

import android.content.Context;
import android.widget.Toast;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.model.WitnessWithShare;

/* compiled from: ZipElectionUseCase.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.electionDetail.ZipElectionUseCase$createPdf$1", f = "ZipElectionUseCase.kt", i = {0, 0, 1, 1}, l = {259, 267}, m = "invokeSuspend", n = {"pdfFile", "pdfFileHash", "pdfFile", "pdfFileHash"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes4.dex */
final class ZipElectionUseCase$createPdf$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Candidate> $candidates;
    final /* synthetic */ Context $context;
    final /* synthetic */ ElectionWithRelation $electionWithRelation;
    final /* synthetic */ FormC1AdministrationComplete $formC1AdministrationComplete;
    final /* synthetic */ FormC1AdministrationHal2Complete $formC1TabulationCompleteSectionV;
    final /* synthetic */ List<FormC1TabulationComplete> $formC1TabulationCompletesSectionIV;
    final /* synthetic */ List<FormC1TabulationPartaiComplete> $formC1TabulationPartaiCompletesSectionIV;
    final /* synthetic */ TpsTime $tpsTimePemungutanSuara;
    final /* synthetic */ TpsTime $tpsTimePenghitunganSuara;
    final /* synthetic */ List<WitnessWithShare> $witnesses;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ZipElectionUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipElectionUseCase$createPdf$1(Context context, ZipElectionUseCase zipElectionUseCase, ElectionWithRelation electionWithRelation, FormC1AdministrationComplete formC1AdministrationComplete, List<FormC1TabulationComplete> list, List<FormC1TabulationPartaiComplete> list2, FormC1AdministrationHal2Complete formC1AdministrationHal2Complete, List<WitnessWithShare> list3, TpsTime tpsTime, TpsTime tpsTime2, List<Candidate> list4, Continuation<? super ZipElectionUseCase$createPdf$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.this$0 = zipElectionUseCase;
        this.$electionWithRelation = electionWithRelation;
        this.$formC1AdministrationComplete = formC1AdministrationComplete;
        this.$formC1TabulationCompletesSectionIV = list;
        this.$formC1TabulationPartaiCompletesSectionIV = list2;
        this.$formC1TabulationCompleteSectionV = formC1AdministrationHal2Complete;
        this.$witnesses = list3;
        this.$tpsTimePemungutanSuara = tpsTime;
        this.$tpsTimePenghitunganSuara = tpsTime2;
        this.$candidates = list4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZipElectionUseCase$createPdf$1(this.$context, this.this$0, this.$electionWithRelation, this.$formC1AdministrationComplete, this.$formC1TabulationCompletesSectionIV, this.$formC1TabulationPartaiCompletesSectionIV, this.$formC1TabulationCompleteSectionV, this.$witnesses, this.$tpsTimePemungutanSuara, this.$tpsTimePenghitunganSuara, this.$candidates, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ZipElectionUseCase$createPdf$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x00be, code lost:
        if (r9.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x0113, code lost:
        if (r9.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) != false) goto L181;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x0496, code lost:
        if (r13.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) != false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x04f8, code lost:
        if (r13.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) != false) goto L146;
     */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0134 A[Catch: Exception -> 0x072a, LOOP:6: B:255:0x012e->B:257:0x0134, LOOP_END, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:262:0x016c A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:281:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x01db A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0238 A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:296:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x024f A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x02b3 A[Catch: Exception -> 0x072a, LOOP:3: B:307:0x02ad->B:309:0x02b3, LOOP_END, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:312:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x02ee A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:316:0x02f6 A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x02ff A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0308 A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0311 A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:330:0x032c A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x037b A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:340:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0484 A[Catch: Exception -> 0x072a, TRY_ENTER, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:351:0x048b A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0492 A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:361:0x04eb A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:364:0x04f2 A[Catch: Exception -> 0x072a, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0515 A[Catch: Exception -> 0x072a, LOOP:5: B:367:0x050f->B:369:0x0515, LOOP_END, TryCatch #1 {Exception -> 0x072a, blocks: (B:219:0x0024, B:382:0x068b, B:378:0x065f, B:230:0x0050, B:231:0x0071, B:232:0x0078, B:388:0x06e3, B:389:0x06e8, B:236:0x00a4, B:244:0x00c0, B:245:0x00e1, B:247:0x00e7, B:248:0x00f5, B:259:0x0145, B:260:0x0166, B:262:0x016c, B:264:0x017e, B:266:0x0188, B:268:0x018e, B:270:0x019a, B:291:0x01db, B:274:0x01a6, B:275:0x01aa, B:277:0x01b0, B:293:0x01e3, B:295:0x0238, B:297:0x023f, B:299:0x024f, B:302:0x0257, B:306:0x0264, B:307:0x02ad, B:309:0x02b3, B:310:0x02c3, B:311:0x02e8, B:385:0x06db, B:386:0x06e0, B:313:0x02ee, B:328:0x0319, B:330:0x032c, B:337:0x0339, B:339:0x037b, B:341:0x03c1, B:344:0x03d2, B:348:0x0484, B:356:0x0498, B:357:0x04ad, B:359:0x04b3, B:360:0x04e7, B:371:0x0564, B:351:0x048b, B:366:0x04fa, B:367:0x050f, B:369:0x0515, B:370:0x0562, B:354:0x0492, B:361:0x04eb, B:364:0x04f2, B:383:0x06d3, B:384:0x06da, B:316:0x02f6, B:319:0x02ff, B:322:0x0308, B:325:0x0311, B:239:0x00ab, B:254:0x0115, B:255:0x012e, B:257:0x0134, B:258:0x0142, B:242:0x00ba, B:249:0x00f8, B:252:0x0107, B:391:0x06ea), top: B:409:0x0016, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0659 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0688 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x01de A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r39) {
        /*
            Method dump skipped, instructions count: 2012
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.electionDetail.ZipElectionUseCase$createPdf$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: ZipElectionUseCase.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.electionDetail.ZipElectionUseCase$createPdf$1$1", f = "ZipElectionUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.electionDetail.ZipElectionUseCase$createPdf$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Toast.makeText(this.$context, "Saat ini PDF belum ditambahkan informasi verifikasi", 1).show();
            return Unit.INSTANCE;
        }
    }
}
