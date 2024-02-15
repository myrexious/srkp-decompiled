package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrd;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzlh implements zzgy {
    private static volatile zzlh zzb;
    private long zzA;
    private final Map zzB;
    private final Map zzC;
    private zzir zzD;
    private String zzE;
    long zza;
    private final zzfu zzc;
    private final zzez zzd;
    private zzak zze;
    private zzfb zzf;
    private zzks zzg;
    private zzaa zzh;
    private final zzlj zzi;
    private zzip zzj;
    private zzkb zzk;
    private final zzkw zzl;
    private zzfl zzm;
    private final zzgd zzn;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;
    private boolean zzo = false;
    private final zzlo zzF = new zzlc(this);

    zzlh(zzli zzliVar, zzgd zzgdVar) {
        Preconditions.checkNotNull(zzliVar);
        this.zzn = zzgd.zzp(zzliVar.zza, null, null);
        this.zzA = -1L;
        this.zzl = new zzkw(this);
        zzlj zzljVar = new zzlj(this);
        zzljVar.zzX();
        this.zzi = zzljVar;
        zzez zzezVar = new zzez(this);
        zzezVar.zzX();
        this.zzd = zzezVar;
        zzfu zzfuVar = new zzfu(this);
        zzfuVar.zzX();
        this.zzc = zzfuVar;
        this.zzB = new HashMap();
        this.zzC = new HashMap();
        zzaB().zzp(new zzkx(this, zzliVar));
    }

    static final void zzaa(com.google.android.gms.internal.measurement.zzfs zzfsVar, int i, String str) {
        List zzp = zzfsVar.zzp();
        for (int i2 = 0; i2 < zzp.size(); i2++) {
            if ("_err".equals(((com.google.android.gms.internal.measurement.zzfx) zzp.get(i2)).zzg())) {
                return;
            }
        }
        com.google.android.gms.internal.measurement.zzfw zze = com.google.android.gms.internal.measurement.zzfx.zze();
        zze.zzj("_err");
        zze.zzi(Long.valueOf(i).longValue());
        com.google.android.gms.internal.measurement.zzfw zze2 = com.google.android.gms.internal.measurement.zzfx.zze();
        zze2.zzj("_ev");
        zze2.zzk(str);
        zzfsVar.zzf((com.google.android.gms.internal.measurement.zzfx) zze.zzaD());
        zzfsVar.zzf((com.google.android.gms.internal.measurement.zzfx) zze2.zzaD());
    }

    static final void zzab(com.google.android.gms.internal.measurement.zzfs zzfsVar, String str) {
        List zzp = zzfsVar.zzp();
        for (int i = 0; i < zzp.size(); i++) {
            if (str.equals(((com.google.android.gms.internal.measurement.zzfx) zzp.get(i)).zzg())) {
                zzfsVar.zzh(i);
                return;
            }
        }
    }

    private final zzq zzac(String str) {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzj = zzakVar.zzj(str);
        if (zzj == null || TextUtils.isEmpty(zzj.zzy())) {
            zzaA().zzc().zzb("No app data available; dropping", str);
            return null;
        }
        Boolean zzad = zzad(zzj);
        if (zzad == null || zzad.booleanValue()) {
            String zzA = zzj.zzA();
            String zzy = zzj.zzy();
            long zzb2 = zzj.zzb();
            String zzx = zzj.zzx();
            long zzm = zzj.zzm();
            long zzj2 = zzj.zzj();
            boolean zzan = zzj.zzan();
            String zzz = zzj.zzz();
            zzj.zza();
            return new zzq(str, zzA, zzy, zzb2, zzx, zzm, zzj2, (String) null, zzan, false, zzz, 0L, 0L, 0, zzj.zzam(), false, zzj.zzt(), zzj.zzs(), zzj.zzk(), zzj.zzE(), (String) null, zzq(str).zzi(), "", (String) null, zzj.zzap(), zzj.zzr());
        }
        zzaA().zzd().zzb("App version does not match; dropping. appId", zzet.zzn(str));
        return null;
    }

    private final Boolean zzad(zzh zzhVar) {
        try {
            if (zzhVar.zzb() == -2147483648L) {
                String str = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzhVar.zzv(), 0).versionName;
                String zzy = zzhVar.zzy();
                if (zzy != null && zzy.equals(str)) {
                    return true;
                }
            } else {
                if (zzhVar.zzb() == Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzhVar.zzv(), 0).versionCode) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final void zzae() {
        zzaB().zzg();
        if (this.zzt || this.zzu || this.zzv) {
            zzaA().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzt), Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv));
            return;
        }
        zzaA().zzj().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzq;
        if (list == null) {
            return;
        }
        for (Runnable runnable : list) {
            runnable.run();
        }
        ((List) Preconditions.checkNotNull(this.zzq)).clear();
    }

    private final void zzaf(com.google.android.gms.internal.measurement.zzgc zzgcVar, long j, boolean z) {
        zzlm zzlmVar;
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        String str = true != z ? "_lte" : "_se";
        zzlm zzp = zzakVar.zzp(zzgcVar.zzaq(), str);
        if (zzp == null || zzp.zze == null) {
            zzlmVar = new zzlm(zzgcVar.zzaq(), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str, zzax().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzlmVar = new zzlm(zzgcVar.zzaq(), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str, zzax().currentTimeMillis(), Long.valueOf(((Long) zzp.zze).longValue() + j));
        }
        com.google.android.gms.internal.measurement.zzgl zzd = com.google.android.gms.internal.measurement.zzgm.zzd();
        zzd.zzf(str);
        zzd.zzg(zzax().currentTimeMillis());
        zzd.zze(((Long) zzlmVar.zze).longValue());
        com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) zzd.zzaD();
        int zza = zzlj.zza(zzgcVar, str);
        if (zza < 0) {
            zzgcVar.zzm(zzgmVar);
        } else {
            zzgcVar.zzan(zza, zzgmVar);
        }
        if (j > 0) {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzakVar2.zzL(zzlmVar);
            zzaA().zzj().zzc("Updated engagement user property. scope, value", true != z ? "lifetime" : "session-scoped", zzlmVar.zze);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0238  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzag() {
        /*
            Method dump skipped, instructions count: 626
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzag():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:799:0x0b36, code lost:
        if (r10 > (com.google.android.gms.measurement.internal.zzag.zzA() + r8)) goto L384;
     */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0383 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0447 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:587:0x0489 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:697:0x07d7 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:709:0x0820 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0843 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:718:0x08ba  */
    /* JADX WARN: Removed duplicated region for block: B:719:0x08bc  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x08c4 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:732:0x08f0 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:798:0x0b26 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:805:0x0bad A[Catch: all -> 0x0ccf, TRY_LEAVE, TryCatch #4 {all -> 0x0ccf, blocks: (B:449:0x000e, B:451:0x0026, B:454:0x002e, B:455:0x0040, B:458:0x0054, B:461:0x007b, B:463:0x00b1, B:466:0x00c3, B:468:0x00cd, B:600:0x04ee, B:470:0x00f7, B:472:0x0105, B:475:0x0125, B:477:0x012b, B:479:0x013d, B:481:0x014b, B:483:0x015b, B:484:0x0168, B:485:0x016d, B:488:0x0186, B:543:0x0383, B:544:0x038f, B:547:0x0399, B:553:0x03bc, B:550:0x03ab, B:575:0x043b, B:577:0x0447, B:580:0x045c, B:582:0x046d, B:584:0x0479, B:599:0x04da, B:587:0x0489, B:589:0x0495, B:592:0x04aa, B:594:0x04bb, B:596:0x04c7, B:557:0x03c4, B:559:0x03d0, B:561:0x03dc, B:573:0x0421, B:565:0x03f9, B:568:0x040b, B:570:0x0411, B:572:0x041b, B:500:0x01c0, B:503:0x01ca, B:505:0x01d8, B:509:0x021f, B:506:0x01f5, B:508:0x0206, B:513:0x022e, B:515:0x025a, B:516:0x0284, B:518:0x02ba, B:520:0x02c0, B:523:0x02cc, B:525:0x0302, B:526:0x031d, B:528:0x0323, B:530:0x0331, B:534:0x0344, B:531:0x0339, B:537:0x034b, B:540:0x0352, B:541:0x036a, B:603:0x0506, B:605:0x0514, B:607:0x051f, B:618:0x0551, B:608:0x0527, B:610:0x0532, B:612:0x0538, B:615:0x0544, B:617:0x054c, B:619:0x0554, B:620:0x0560, B:623:0x0568, B:625:0x057a, B:626:0x0586, B:628:0x058e, B:632:0x05b3, B:634:0x05d8, B:636:0x05e9, B:638:0x05ef, B:640:0x05fb, B:641:0x062c, B:643:0x0632, B:645:0x0640, B:646:0x0644, B:647:0x0647, B:648:0x064a, B:649:0x0658, B:651:0x065e, B:653:0x066e, B:654:0x0675, B:656:0x0681, B:657:0x0688, B:658:0x068b, B:660:0x06cb, B:661:0x06de, B:663:0x06e4, B:666:0x06fe, B:668:0x0719, B:670:0x0732, B:672:0x0737, B:674:0x073b, B:676:0x073f, B:678:0x0749, B:679:0x0753, B:681:0x0757, B:683:0x075d, B:684:0x076b, B:685:0x0774, B:753:0x09c9, B:687:0x0780, B:689:0x0797, B:695:0x07b3, B:697:0x07d7, B:698:0x07df, B:700:0x07e5, B:702:0x07f7, B:709:0x0820, B:710:0x0843, B:712:0x084f, B:714:0x0864, B:716:0x08a5, B:720:0x08bd, B:722:0x08c4, B:724:0x08d3, B:726:0x08d7, B:728:0x08db, B:730:0x08df, B:731:0x08eb, B:732:0x08f0, B:734:0x08f6, B:736:0x0912, B:737:0x0917, B:752:0x09c6, B:738:0x0931, B:740:0x0939, B:744:0x0960, B:746:0x098e, B:747:0x0998, B:748:0x09aa, B:750:0x09b6, B:741:0x0946, B:707:0x080b, B:693:0x079e, B:754:0x09d4, B:756:0x09e1, B:757:0x09e7, B:758:0x09ef, B:760:0x09f5, B:762:0x0a0b, B:764:0x0a1c, B:784:0x0a90, B:786:0x0a96, B:788:0x0aae, B:791:0x0ab5, B:796:0x0ae4, B:798:0x0b26, B:801:0x0b5b, B:802:0x0b5f, B:803:0x0b6a, B:805:0x0bad, B:806:0x0bba, B:808:0x0bc9, B:812:0x0be3, B:814:0x0bfc, B:800:0x0b38, B:792:0x0abd, B:794:0x0ac9, B:795:0x0acd, B:815:0x0c14, B:816:0x0c2c, B:819:0x0c34, B:820:0x0c39, B:821:0x0c49, B:823:0x0c63, B:824:0x0c7e, B:825:0x0c87, B:830:0x0cab, B:829:0x0c98, B:765:0x0a34, B:767:0x0a3a, B:769:0x0a44, B:771:0x0a4b, B:777:0x0a5b, B:779:0x0a62, B:781:0x0a81, B:783:0x0a88, B:782:0x0a85, B:778:0x0a5f, B:770:0x0a48, B:629:0x0593, B:631:0x0599, B:833:0x0cbd), top: B:847:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:808:0x0bc9 A[Catch: SQLiteException -> 0x0be1, all -> 0x0ccf, TRY_LEAVE, TryCatch #1 {SQLiteException -> 0x0be1, blocks: (B:806:0x0bba, B:808:0x0bc9), top: B:841:0x0bba, outer: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zzah(java.lang.String r41, long r42) {
        /*
            Method dump skipped, instructions count: 3290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzah(java.lang.String, long):boolean");
    }

    private final boolean zzai() {
        zzaB().zzg();
        zzB();
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        if (zzakVar.zzF()) {
            return true;
        }
        zzak zzakVar2 = this.zze;
        zzal(zzakVar2);
        return !TextUtils.isEmpty(zzakVar2.zzr());
    }

    private final boolean zzaj(com.google.android.gms.internal.measurement.zzfs zzfsVar, com.google.android.gms.internal.measurement.zzfs zzfsVar2) {
        Preconditions.checkArgument("_e".equals(zzfsVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD(), "_sc");
        String zzh = zzC == null ? null : zzC.zzh();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC2 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar2.zzaD(), "_pc");
        String zzh2 = zzC2 != null ? zzC2.zzh() : null;
        if (zzh2 == null || !zzh2.equals(zzh)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzfsVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC3 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD(), "_et");
        if (zzC3 == null || !zzC3.zzw() || zzC3.zzd() <= 0) {
            return true;
        }
        long zzd = zzC3.zzd();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC4 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar2.zzaD(), "_et");
        if (zzC4 != null && zzC4.zzd() > 0) {
            zzd += zzC4.zzd();
        }
        zzal(this.zzi);
        zzlj.zzA(zzfsVar2, "_et", Long.valueOf(zzd));
        zzal(this.zzi);
        zzlj.zzA(zzfsVar, "_fr", 1L);
        return true;
    }

    private static final boolean zzak(zzq zzqVar) {
        return (TextUtils.isEmpty(zzqVar.zzb) && TextUtils.isEmpty(zzqVar.zzq)) ? false : true;
    }

    private static final zzku zzal(zzku zzkuVar) {
        if (zzkuVar != null) {
            if (zzkuVar.zzY()) {
                return zzkuVar;
            }
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(String.valueOf(zzkuVar.getClass()))));
        }
        throw new IllegalStateException("Upload Component not created");
    }

    public static zzlh zzt(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzlh.class) {
                if (zzb == null) {
                    zzb = new zzlh((zzli) Preconditions.checkNotNull(new zzli(context)), null);
                }
            }
        }
        return zzb;
    }

    public static /* bridge */ /* synthetic */ void zzy(zzlh zzlhVar, zzli zzliVar) {
        zzlhVar.zzaB().zzg();
        zzlhVar.zzm = new zzfl(zzlhVar);
        zzak zzakVar = new zzak(zzlhVar);
        zzakVar.zzX();
        zzlhVar.zze = zzakVar;
        zzlhVar.zzg().zzq((zzaf) Preconditions.checkNotNull(zzlhVar.zzc));
        zzkb zzkbVar = new zzkb(zzlhVar);
        zzkbVar.zzX();
        zzlhVar.zzk = zzkbVar;
        zzaa zzaaVar = new zzaa(zzlhVar);
        zzaaVar.zzX();
        zzlhVar.zzh = zzaaVar;
        zzip zzipVar = new zzip(zzlhVar);
        zzipVar.zzX();
        zzlhVar.zzj = zzipVar;
        zzks zzksVar = new zzks(zzlhVar);
        zzksVar.zzX();
        zzlhVar.zzg = zzksVar;
        zzlhVar.zzf = new zzfb(zzlhVar);
        if (zzlhVar.zzr != zzlhVar.zzs) {
            zzlhVar.zzaA().zzd().zzc("Not all upload components initialized", Integer.valueOf(zzlhVar.zzr), Integer.valueOf(zzlhVar.zzs));
        }
        zzlhVar.zzo = true;
    }

    public final void zzA() {
        zzaB().zzg();
        zzB();
        if (this.zzp) {
            return;
        }
        this.zzp = true;
        if (zzZ()) {
            FileChannel fileChannel = this.zzx;
            zzaB().zzg();
            int i = 0;
            if (fileChannel == null || !fileChannel.isOpen()) {
                zzaA().zzd().zza("Bad channel to read from");
            } else {
                ByteBuffer allocate = ByteBuffer.allocate(4);
                try {
                    fileChannel.position(0L);
                    int read = fileChannel.read(allocate);
                    if (read == 4) {
                        allocate.flip();
                        i = allocate.getInt();
                    } else if (read != -1) {
                        zzaA().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(read));
                    }
                } catch (IOException e) {
                    zzaA().zzd().zzb("Failed to read from channel", e);
                }
            }
            int zzi = this.zzn.zzh().zzi();
            zzaB().zzg();
            if (i > zzi) {
                zzaA().zzd().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi));
            } else if (i < zzi) {
                FileChannel fileChannel2 = this.zzx;
                zzaB().zzg();
                if (fileChannel2 == null || !fileChannel2.isOpen()) {
                    zzaA().zzd().zza("Bad channel to read from");
                } else {
                    ByteBuffer allocate2 = ByteBuffer.allocate(4);
                    allocate2.putInt(zzi);
                    allocate2.flip();
                    try {
                        fileChannel2.truncate(0L);
                        fileChannel2.write(allocate2);
                        fileChannel2.force(true);
                        if (fileChannel2.size() != 4) {
                            zzaA().zzd().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                        }
                        zzaA().zzj().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi));
                        return;
                    } catch (IOException e2) {
                        zzaA().zzd().zzb("Failed to write to channel", e2);
                    }
                }
                zzaA().zzd().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi));
            }
        }
    }

    public final void zzB() {
        if (!this.zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    public final void zzC(String str, com.google.android.gms.internal.measurement.zzgc zzgcVar) {
        int zza;
        int indexOf;
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        Set zzk = zzfuVar.zzk(str);
        if (zzk != null) {
            zzgcVar.zzi(zzk);
        }
        zzfu zzfuVar2 = this.zzc;
        zzal(zzfuVar2);
        if (zzfuVar2.zzv(str)) {
            zzgcVar.zzp();
        }
        zzfu zzfuVar3 = this.zzc;
        zzal(zzfuVar3);
        if (zzfuVar3.zzy(str)) {
            if (zzg().zzs(str, zzeg.zzar)) {
                String zzas = zzgcVar.zzas();
                if (!TextUtils.isEmpty(zzas) && (indexOf = zzas.indexOf(".")) != -1) {
                    zzgcVar.zzY(zzas.substring(0, indexOf));
                }
            } else {
                zzgcVar.zzu();
            }
        }
        zzfu zzfuVar4 = this.zzc;
        zzal(zzfuVar4);
        if (zzfuVar4.zzz(str) && (zza = zzlj.zza(zzgcVar, "_id")) != -1) {
            zzgcVar.zzB(zza);
        }
        zzfu zzfuVar5 = this.zzc;
        zzal(zzfuVar5);
        if (zzfuVar5.zzx(str)) {
            zzgcVar.zzq();
        }
        zzfu zzfuVar6 = this.zzc;
        zzal(zzfuVar6);
        if (zzfuVar6.zzu(str)) {
            zzgcVar.zzn();
            zzlg zzlgVar = (zzlg) this.zzC.get(str);
            if (zzlgVar == null || zzlgVar.zzb + zzg().zzi(str, zzeg.zzT) < zzax().elapsedRealtime()) {
                zzlgVar = new zzlg(this);
                this.zzC.put(str, zzlgVar);
            }
            zzgcVar.zzR(zzlgVar.zza);
        }
        zzfu zzfuVar7 = this.zzc;
        zzal(zzfuVar7);
        if (zzfuVar7.zzw(str)) {
            zzgcVar.zzy();
        }
    }

    final void zzD(zzh zzhVar) {
        zzaB().zzg();
        if (!TextUtils.isEmpty(zzhVar.zzA()) || !TextUtils.isEmpty(zzhVar.zzt())) {
            zzkw zzkwVar = this.zzl;
            Uri.Builder builder = new Uri.Builder();
            String zzA = zzhVar.zzA();
            if (TextUtils.isEmpty(zzA)) {
                zzA = zzhVar.zzt();
            }
            ArrayMap arrayMap = null;
            Uri.Builder appendQueryParameter = builder.scheme((String) zzeg.zze.zza(null)).encodedAuthority((String) zzeg.zzf.zza(null)).path("config/app/".concat(String.valueOf(zzA))).appendQueryParameter("platform", "android");
            zzkwVar.zzt.zzf().zzh();
            appendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(79000L)).appendQueryParameter("runtime_version", "0");
            String uri = builder.build().toString();
            try {
                String str = (String) Preconditions.checkNotNull(zzhVar.zzv());
                URL url = new URL(uri);
                zzaA().zzj().zzb("Fetching remote configuration", str);
                zzfu zzfuVar = this.zzc;
                zzal(zzfuVar);
                com.google.android.gms.internal.measurement.zzff zze = zzfuVar.zze(str);
                zzfu zzfuVar2 = this.zzc;
                zzal(zzfuVar2);
                String zzh = zzfuVar2.zzh(str);
                if (zze != null) {
                    if (!TextUtils.isEmpty(zzh)) {
                        ArrayMap arrayMap2 = new ArrayMap();
                        arrayMap2.put("If-Modified-Since", zzh);
                        arrayMap = arrayMap2;
                    }
                    zzfu zzfuVar3 = this.zzc;
                    zzal(zzfuVar3);
                    String zzf = zzfuVar3.zzf(str);
                    if (!TextUtils.isEmpty(zzf)) {
                        if (arrayMap == null) {
                            arrayMap = new ArrayMap();
                        }
                        arrayMap.put("If-None-Match", zzf);
                    }
                }
                this.zzt = true;
                zzez zzezVar = this.zzd;
                zzal(zzezVar);
                zzkz zzkzVar = new zzkz(this);
                zzezVar.zzg();
                zzezVar.zzW();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzkzVar);
                zzezVar.zzt.zzaB().zzo(new zzey(zzezVar, str, url, null, arrayMap, zzkzVar));
                return;
            } catch (MalformedURLException unused) {
                zzaA().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzet.zzn(zzhVar.zzv()), uri);
                return;
            }
        }
        zzI((String) Preconditions.checkNotNull(zzhVar.zzv()), 204, null, null, null);
    }

    public final void zzE(zzau zzauVar, zzq zzqVar) {
        zzau zzauVar2;
        List<zzac> zzt;
        List<zzac> zzt2;
        List<zzac> zzt3;
        String str;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzaB().zzg();
        zzB();
        String str2 = zzqVar.zza;
        long j = zzauVar.zzd;
        zzeu zzb2 = zzeu.zzb(zzauVar);
        zzaB().zzg();
        zzir zzirVar = null;
        if (this.zzD != null && (str = this.zzE) != null && str.equals(str2)) {
            zzirVar = this.zzD;
        }
        zzlp.zzK(zzirVar, zzb2.zzd, false);
        zzau zza = zzb2.zza();
        zzal(this.zzi);
        if (zzlj.zzB(zza, zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            List list = zzqVar.zzt;
            if (list == null) {
                zzauVar2 = zza;
            } else if (list.contains(zza.zza)) {
                Bundle zzc = zza.zzb.zzc();
                zzc.putLong("ga_safelisted", 1L);
                zzauVar2 = new zzau(zza.zza, new zzas(zzc), zza.zzc, zza.zzd);
            } else {
                zzaA().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", str2, zza.zza, zza.zzc);
                return;
            }
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                Preconditions.checkNotEmpty(str2);
                zzakVar2.zzg();
                zzakVar2.zzW();
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i < 0) {
                    zzakVar2.zzt.zzaA().zzk().zzc("Invalid time querying timed out conditional properties", zzet.zzn(str2), Long.valueOf(j));
                    zzt = Collections.emptyList();
                } else {
                    zzt = zzakVar2.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzac zzacVar : zzt) {
                    if (zzacVar != null) {
                        zzaA().zzj().zzd("User property timed out", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb), zzacVar.zzc.zza());
                        zzau zzauVar3 = zzacVar.zzg;
                        if (zzauVar3 != null) {
                            zzY(new zzau(zzauVar3, j), zzqVar);
                        }
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zza(str2, zzacVar.zzc.zzb);
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                Preconditions.checkNotEmpty(str2);
                zzakVar4.zzg();
                zzakVar4.zzW();
                if (i < 0) {
                    zzakVar4.zzt.zzaA().zzk().zzc("Invalid time querying expired conditional properties", zzet.zzn(str2), Long.valueOf(j));
                    zzt2 = Collections.emptyList();
                } else {
                    zzt2 = zzakVar4.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList<zzau> arrayList = new ArrayList(zzt2.size());
                for (zzac zzacVar2 : zzt2) {
                    if (zzacVar2 != null) {
                        zzaA().zzj().zzd("User property expired", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzA(str2, zzacVar2.zzc.zzb);
                        zzau zzauVar4 = zzacVar2.zzk;
                        if (zzauVar4 != null) {
                            arrayList.add(zzauVar4);
                        }
                        zzak zzakVar6 = this.zze;
                        zzal(zzakVar6);
                        zzakVar6.zza(str2, zzacVar2.zzc.zzb);
                    }
                }
                for (zzau zzauVar5 : arrayList) {
                    zzY(new zzau(zzauVar5, j), zzqVar);
                }
                zzak zzakVar7 = this.zze;
                zzal(zzakVar7);
                String str3 = zzauVar2.zza;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzakVar7.zzg();
                zzakVar7.zzW();
                if (i < 0) {
                    zzakVar7.zzt.zzaA().zzk().zzd("Invalid time querying triggered conditional properties", zzet.zzn(str2), zzakVar7.zzt.zzj().zzd(str3), Long.valueOf(j));
                    zzt3 = Collections.emptyList();
                } else {
                    zzt3 = zzakVar7.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList<zzau> arrayList2 = new ArrayList(zzt3.size());
                for (zzac zzacVar3 : zzt3) {
                    if (zzacVar3 != null) {
                        zzlk zzlkVar = zzacVar3.zzc;
                        zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzacVar3.zza), zzacVar3.zzb, zzlkVar.zzb, j, Preconditions.checkNotNull(zzlkVar.zza()));
                        zzak zzakVar8 = this.zze;
                        zzal(zzakVar8);
                        if (zzakVar8.zzL(zzlmVar)) {
                            zzaA().zzj().zzd("User property triggered", zzacVar3.zza, this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                        } else {
                            zzaA().zzd().zzd("Too many active user properties, ignoring", zzet.zzn(zzacVar3.zza), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                        }
                        zzau zzauVar6 = zzacVar3.zzi;
                        if (zzauVar6 != null) {
                            arrayList2.add(zzauVar6);
                        }
                        zzacVar3.zzc = new zzlk(zzlmVar);
                        zzacVar3.zze = true;
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        zzakVar9.zzK(zzacVar3);
                    }
                }
                zzY(zzauVar2, zzqVar);
                for (zzau zzauVar7 : arrayList2) {
                    zzY(new zzau(zzauVar7, j), zzqVar);
                }
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzC();
            } finally {
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzakVar11.zzx();
            }
        }
    }

    public final void zzF(zzau zzauVar, String str) {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzj = zzakVar.zzj(str);
        if (zzj == null || TextUtils.isEmpty(zzj.zzy())) {
            zzaA().zzc().zzb("No app data available; dropping event", str);
            return;
        }
        Boolean zzad = zzad(zzj);
        if (zzad == null) {
            if (!"_ui".equals(zzauVar.zza)) {
                zzaA().zzk().zzb("Could not find package. appId", zzet.zzn(str));
            }
        } else if (!zzad.booleanValue()) {
            zzaA().zzd().zzb("App version does not match; dropping event. appId", zzet.zzn(str));
            return;
        }
        String zzA = zzj.zzA();
        String zzy = zzj.zzy();
        long zzb2 = zzj.zzb();
        String zzx = zzj.zzx();
        long zzm = zzj.zzm();
        long zzj2 = zzj.zzj();
        boolean zzan = zzj.zzan();
        String zzz = zzj.zzz();
        zzj.zza();
        zzG(zzauVar, new zzq(str, zzA, zzy, zzb2, zzx, zzm, zzj2, (String) null, zzan, false, zzz, 0L, 0L, 0, zzj.zzam(), false, zzj.zzt(), zzj.zzs(), zzj.zzk(), zzj.zzE(), (String) null, zzq(str).zzi(), "", (String) null, zzj.zzap(), zzj.zzr()));
    }

    final void zzG(zzau zzauVar, zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzeu zzb2 = zzeu.zzb(zzauVar);
        zzlp zzv = zzv();
        Bundle bundle = zzb2.zzd;
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzv.zzL(bundle, zzakVar.zzi(zzqVar.zza));
        zzv().zzN(zzb2, zzg().zzd(zzqVar.zza));
        zzau zza = zzb2.zza();
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zza.zza) && "referrer API v2".equals(zza.zzb.zzg("_cis"))) {
            String zzg = zza.zzb.zzg("gclid");
            if (!TextUtils.isEmpty(zzg)) {
                zzW(new zzlk("_lgclid", zza.zzd, zzg, DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
            }
        }
        zzE(zza, zzqVar);
    }

    public final void zzH() {
        this.zzs++;
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0129 A[Catch: all -> 0x0176, TryCatch #2 {all -> 0x0180, blocks: (B:77:0x0010, B:78:0x0012, B:135:0x0168, B:79:0x002c, B:89:0x0049, B:134:0x0160, B:94:0x0063, B:99:0x00b5, B:98:0x00a6, B:102:0x00bd, B:105:0x00c9, B:107:0x00cf, B:110:0x00d9, B:113:0x00e5, B:115:0x00eb, B:120:0x00f8, B:124:0x0114, B:126:0x0129, B:128:0x0148, B:130:0x0153, B:132:0x0159, B:133:0x015d, B:127:0x0137, B:121:0x0101, B:123:0x010c), top: B:144:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0137 A[Catch: all -> 0x0176, TryCatch #2 {all -> 0x0180, blocks: (B:77:0x0010, B:78:0x0012, B:135:0x0168, B:79:0x002c, B:89:0x0049, B:134:0x0160, B:94:0x0063, B:99:0x00b5, B:98:0x00a6, B:102:0x00bd, B:105:0x00c9, B:107:0x00cf, B:110:0x00d9, B:113:0x00e5, B:115:0x00eb, B:120:0x00f8, B:124:0x0114, B:126:0x0129, B:128:0x0148, B:130:0x0153, B:132:0x0159, B:133:0x015d, B:127:0x0137, B:121:0x0101, B:123:0x010c), top: B:144:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0049 A[Catch: all -> 0x0176, TryCatch #2 {all -> 0x0180, blocks: (B:77:0x0010, B:78:0x0012, B:135:0x0168, B:79:0x002c, B:89:0x0049, B:134:0x0160, B:94:0x0063, B:99:0x00b5, B:98:0x00a6, B:102:0x00bd, B:105:0x00c9, B:107:0x00cf, B:110:0x00d9, B:113:0x00e5, B:115:0x00eb, B:120:0x00f8, B:124:0x0114, B:126:0x0129, B:128:0x0148, B:130:0x0153, B:132:0x0159, B:133:0x015d, B:127:0x0137, B:121:0x0101, B:123:0x010c), top: B:144:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzI(java.lang.String r8, int r9, java.lang.Throwable r10, byte[] r11, java.util.Map r12) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzI(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    public final void zzJ(boolean z) {
        zzag();
    }

    public final void zzK(int i, Throwable th, byte[] bArr, String str) {
        zzak zzakVar;
        long longValue;
        zzaB().zzg();
        zzB();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzu = false;
                zzae();
            }
        }
        List<Long> list = (List) Preconditions.checkNotNull(this.zzy);
        this.zzy = null;
        if (i != 200) {
            if (i == 204) {
                i = 204;
            }
            zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzk.zzd.zzb(zzax().currentTimeMillis());
            if (i != 503 || i == 429) {
                this.zzk.zzb.zzb(zzax().currentTimeMillis());
            }
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzakVar2.zzy(list);
            zzag();
        }
        if (th == null) {
            try {
                this.zzk.zzc.zzb(zzax().currentTimeMillis());
                this.zzk.zzd.zzb(0L);
                zzag();
                zzaA().zzj().zzc("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzak zzakVar3 = this.zze;
                zzal(zzakVar3);
                zzakVar3.zzw();
                try {
                    for (Long l : list) {
                        try {
                            zzakVar = this.zze;
                            zzal(zzakVar);
                            longValue = l.longValue();
                            zzakVar.zzg();
                            zzakVar.zzW();
                            try {
                            } catch (SQLiteException e) {
                                zzakVar.zzt.zzaA().zzd().zzb("Failed to delete a bundle in a queue table", e);
                                throw e;
                                break;
                            }
                        } catch (SQLiteException e2) {
                            List list2 = this.zzz;
                            if (list2 == null || !list2.contains(l)) {
                                throw e2;
                            }
                        }
                        if (zzakVar.zzh().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                            break;
                        }
                    }
                    zzak zzakVar4 = this.zze;
                    zzal(zzakVar4);
                    zzakVar4.zzC();
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzakVar5.zzx();
                    this.zzz = null;
                    zzez zzezVar = this.zzd;
                    zzal(zzezVar);
                    if (zzezVar.zza() && zzai()) {
                        zzX();
                    } else {
                        this.zzA = -1L;
                        zzag();
                    }
                    this.zza = 0L;
                } catch (Throwable th2) {
                    zzak zzakVar6 = this.zze;
                    zzal(zzakVar6);
                    zzakVar6.zzx();
                    throw th2;
                }
            } catch (SQLiteException e3) {
                zzaA().zzd().zzb("Database error while trying to delete uploaded bundles", e3);
                this.zza = zzax().elapsedRealtime();
                zzaA().zzj().zzb("Disable upload, time", Long.valueOf(this.zza));
            }
        }
        zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
        this.zzk.zzd.zzb(zzax().currentTimeMillis());
        if (i != 503) {
        }
        this.zzk.zzb.zzb(zzax().currentTimeMillis());
        zzak zzakVar22 = this.zze;
        zzal(zzakVar22);
        zzakVar22.zzy(list);
        zzag();
    }

    /* JADX WARN: Removed duplicated region for block: B:269:0x01f5 A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x024e A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:288:0x025d A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x026d A[Catch: all -> 0x0575, TRY_LEAVE, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:330:0x03db A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0407 A[Catch: all -> 0x0575, TRY_LEAVE, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x04e0 A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0545 A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:224:0x00a6, B:226:0x00b6, B:244:0x00fd, B:246:0x0110, B:248:0x0126, B:249:0x014d, B:251:0x01a7, B:253:0x01ac, B:255:0x01b2, B:257:0x01bb, B:269:0x01f5, B:271:0x0200, B:275:0x020d, B:278:0x021b, B:282:0x0226, B:284:0x0229, B:285:0x0249, B:287:0x024e, B:290:0x026d, B:293:0x0280, B:295:0x02a7, B:298:0x02af, B:300:0x02be, B:328:0x03a7, B:330:0x03db, B:331:0x03de, B:333:0x0407, B:372:0x04e0, B:373:0x04e3, B:381:0x0564, B:335:0x041c, B:340:0x0441, B:342:0x0449, B:344:0x0455, B:348:0x0468, B:352:0x0479, B:356:0x0485, B:359:0x049b, B:362:0x04ac, B:364:0x04c0, B:366:0x04c6, B:367:0x04cd, B:369:0x04d3, B:350:0x0471, B:338:0x042d, B:301:0x02cf, B:303:0x02fa, B:304:0x030b, B:306:0x0312, B:308:0x0318, B:310:0x0322, B:312:0x032c, B:314:0x0332, B:316:0x0338, B:317:0x033d, B:321:0x035f, B:324:0x0364, B:325:0x0378, B:326:0x0388, B:327:0x0398, B:374:0x04fa, B:376:0x052b, B:377:0x052e, B:378:0x0545, B:380:0x0549, B:288:0x025d, B:265:0x01da, B:230:0x00c3, B:232:0x00c7, B:236:0x00d8, B:238:0x00e9, B:240:0x00f3, B:243:0x00fa), top: B:388:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:398:0x041c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzL(com.google.android.gms.measurement.internal.zzq r24) {
        /*
            Method dump skipped, instructions count: 1408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzL(com.google.android.gms.measurement.internal.zzq):void");
    }

    public final void zzM() {
        this.zzr++;
    }

    public final void zzN(zzac zzacVar) {
        zzq zzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzac != null) {
            zzO(zzacVar, zzac);
        }
    }

    public final void zzO(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (zzqVar.zzh) {
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzakVar.zzw();
                try {
                    zzd(zzqVar);
                    String str = (String) Preconditions.checkNotNull(zzacVar.zza);
                    zzak zzakVar2 = this.zze;
                    zzal(zzakVar2);
                    zzac zzk = zzakVar2.zzk(str, zzacVar.zzc.zzb);
                    if (zzk != null) {
                        zzaA().zzc().zzc("Removing conditional user property", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zza(str, zzacVar.zzc.zzb);
                        if (zzk.zze) {
                            zzak zzakVar4 = this.zze;
                            zzal(zzakVar4);
                            zzakVar4.zzA(str, zzacVar.zzc.zzb);
                        }
                        zzau zzauVar = zzacVar.zzk;
                        if (zzauVar != null) {
                            zzas zzasVar = zzauVar.zzb;
                            zzY((zzau) Preconditions.checkNotNull(zzv().zzz(str, ((zzau) Preconditions.checkNotNull(zzacVar.zzk)).zza, zzasVar != null ? zzasVar.zzc() : null, zzk.zzb, zzacVar.zzk.zzd, true, true)), zzqVar);
                        }
                    } else {
                        zzaA().zzk().zzc("Conditional user property doesn't exist", zzet.zzn(zzacVar.zza), this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                    }
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzakVar5.zzC();
                    return;
                } finally {
                    zzak zzakVar6 = this.zze;
                    zzal(zzakVar6);
                    zzakVar6.zzx();
                }
            }
            zzd(zzqVar);
        }
    }

    public final void zzP(String str, zzq zzqVar) {
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
            } else if (!"_npa".equals(str) || zzqVar.zzr == null) {
                zzaA().zzc().zzb("Removing user property", this.zzn.zzj().zzf(str));
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzakVar.zzw();
                try {
                    zzd(zzqVar);
                    if ("_id".equals(str)) {
                        zzak zzakVar2 = this.zze;
                        zzal(zzakVar2);
                        zzakVar2.zzA((String) Preconditions.checkNotNull(zzqVar.zza), "_lair");
                    }
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    zzakVar3.zzA((String) Preconditions.checkNotNull(zzqVar.zza), str);
                    zzak zzakVar4 = this.zze;
                    zzal(zzakVar4);
                    zzakVar4.zzC();
                    zzaA().zzc().zzb("User property removed", this.zzn.zzj().zzf(str));
                } finally {
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzakVar5.zzx();
                }
            } else {
                zzaA().zzc().zza("Falling back to manifest metadata value for ad personalization");
                zzW(new zzlk("_npa", zzax().currentTimeMillis(), Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
            }
        }
    }

    public final void zzQ(zzq zzqVar) {
        if (this.zzy != null) {
            ArrayList arrayList = new ArrayList();
            this.zzz = arrayList;
            arrayList.addAll(this.zzy);
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        String str = (String) Preconditions.checkNotNull(zzqVar.zza);
        Preconditions.checkNotEmpty(str);
        zzakVar.zzg();
        zzakVar.zzW();
        try {
            SQLiteDatabase zzh = zzakVar.zzh();
            String[] strArr = {str};
            int delete = zzh.delete("apps", "app_id=?", strArr) + zzh.delete("events", "app_id=?", strArr) + zzh.delete("user_attributes", "app_id=?", strArr) + zzh.delete("conditional_properties", "app_id=?", strArr) + zzh.delete("raw_events", "app_id=?", strArr) + zzh.delete("raw_events_metadata", "app_id=?", strArr) + zzh.delete("queue", "app_id=?", strArr) + zzh.delete("audience_filter_values", "app_id=?", strArr) + zzh.delete("main_event_params", "app_id=?", strArr) + zzh.delete("default_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzakVar.zzt.zzaA().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzakVar.zzt.zzaA().zzd().zzc("Error resetting analytics data. appId, error", zzet.zzn(str), e);
        }
        if (zzqVar.zzh) {
            zzL(zzqVar);
        }
    }

    public final void zzR(String str, zzir zzirVar) {
        zzaB().zzg();
        String str2 = this.zzE;
        if (str2 == null || str2.equals(str) || zzirVar != null) {
            this.zzE = str;
            this.zzD = zzirVar;
        }
    }

    public final void zzS() {
        zzaB().zzg();
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzakVar.zzz();
        if (this.zzk.zzc.zza() == 0) {
            this.zzk.zzc.zzb(zzax().currentTimeMillis());
        }
        zzag();
    }

    public final void zzT(zzac zzacVar) {
        zzq zzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzac != null) {
            zzU(zzacVar, zzac);
        }
    }

    public final void zzU(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzb);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzac zzacVar2 = new zzac(zzacVar);
            boolean z = false;
            zzacVar2.zze = false;
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzac zzk = zzakVar2.zzk((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzc.zzb);
                if (zzk != null && !zzk.zzb.equals(zzacVar2.zzb)) {
                    zzaA().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzb, zzk.zzb);
                }
                if (zzk == null || !zzk.zze) {
                    if (TextUtils.isEmpty(zzacVar2.zzf)) {
                        zzlk zzlkVar = zzacVar2.zzc;
                        zzacVar2.zzc = new zzlk(zzlkVar.zzb, zzacVar2.zzd, zzlkVar.zza(), zzacVar2.zzc.zzf);
                        zzacVar2.zze = true;
                        z = true;
                    }
                } else {
                    zzacVar2.zzb = zzk.zzb;
                    zzacVar2.zzd = zzk.zzd;
                    zzacVar2.zzh = zzk.zzh;
                    zzacVar2.zzf = zzk.zzf;
                    zzacVar2.zzi = zzk.zzi;
                    zzacVar2.zze = true;
                    zzlk zzlkVar2 = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlk(zzlkVar2.zzb, zzk.zzc.zzc, zzlkVar2.zza(), zzk.zzc.zzf);
                }
                if (zzacVar2.zze) {
                    zzlk zzlkVar3 = zzacVar2.zzc;
                    zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzb, zzlkVar3.zzb, zzlkVar3.zzc, Preconditions.checkNotNull(zzlkVar3.zza()));
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    if (zzakVar3.zzL(zzlmVar)) {
                        zzaA().zzc().zzd("User property updated immediately", zzacVar2.zza, this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    } else {
                        zzaA().zzd().zzd("(2)Too many active user properties, ignoring", zzet.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    }
                    if (z && zzacVar2.zzi != null) {
                        zzY(new zzau(zzacVar2.zzi, zzacVar2.zzd), zzqVar);
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                if (zzakVar4.zzK(zzacVar2)) {
                    zzaA().zzc().zzd("Conditional property added", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                } else {
                    zzaA().zzd().zzd("Too many conditional properties, ignoring", zzet.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                }
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzC();
            } finally {
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                zzakVar6.zzx();
            }
        }
    }

    public final void zzV(String str, zzhb zzhbVar) {
        zzaB().zzg();
        zzB();
        this.zzB.put(str, zzhbVar);
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzhbVar);
        zzakVar.zzg();
        zzakVar.zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzhbVar.zzi());
        try {
            if (zzakVar.zzh().insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                zzakVar.zzt.zzaA().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzet.zzn(str));
            }
        } catch (SQLiteException e) {
            zzakVar.zzt.zzaA().zzd().zzc("Error storing consent setting. appId, error", zzet.zzn(str), e);
        }
    }

    public final void zzW(zzlk zzlkVar, zzq zzqVar) {
        long j;
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            int zzl = zzv().zzl(zzlkVar.zzb);
            int i = 0;
            if (zzl != 0) {
                zzlp zzv = zzv();
                String str = zzlkVar.zzb;
                zzg();
                String zzD = zzv.zzD(str, 24, true);
                String str2 = zzlkVar.zzb;
                zzv().zzO(this.zzF, zzqVar.zza, zzl, "_ev", zzD, str2 != null ? str2.length() : 0);
                return;
            }
            int zzd = zzv().zzd(zzlkVar.zzb, zzlkVar.zza());
            if (zzd != 0) {
                zzlp zzv2 = zzv();
                String str3 = zzlkVar.zzb;
                zzg();
                String zzD2 = zzv2.zzD(str3, 24, true);
                Object zza = zzlkVar.zza();
                if (zza != null && ((zza instanceof String) || (zza instanceof CharSequence))) {
                    i = zza.toString().length();
                }
                zzv().zzO(this.zzF, zzqVar.zza, zzd, "_ev", zzD2, i);
                return;
            }
            Object zzB = zzv().zzB(zzlkVar.zzb, zzlkVar.zza());
            if (zzB == null) {
                return;
            }
            if ("_sid".equals(zzlkVar.zzb)) {
                long j2 = zzlkVar.zzc;
                String str4 = zzlkVar.zzf;
                String str5 = (String) Preconditions.checkNotNull(zzqVar.zza);
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzlm zzp = zzakVar.zzp(str5, "_sno");
                if (zzp != null) {
                    Object obj = zzp.zze;
                    if (obj instanceof Long) {
                        j = ((Long) obj).longValue();
                        zzW(new zzlk("_sno", j2, Long.valueOf(j + 1), str4), zzqVar);
                    }
                }
                if (zzp != null) {
                    zzaA().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzp.zze);
                }
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzaq zzn = zzakVar2.zzn(str5, "_s");
                if (zzn != null) {
                    j = zzn.zzc;
                    zzaA().zzj().zzb("Backfill the session number. Last used session number", Long.valueOf(j));
                } else {
                    j = 0;
                }
                zzW(new zzlk("_sno", j2, Long.valueOf(j + 1), str4), zzqVar);
            }
            zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzqVar.zza), (String) Preconditions.checkNotNull(zzlkVar.zzf), zzlkVar.zzb, zzlkVar.zzc, zzB);
            zzaA().zzj().zzc("Setting user property", this.zzn.zzj().zzf(zzlmVar.zzc), zzB);
            zzak zzakVar3 = this.zze;
            zzal(zzakVar3);
            zzakVar3.zzw();
            try {
                if ("_id".equals(zzlmVar.zzc)) {
                    zzak zzakVar4 = this.zze;
                    zzal(zzakVar4);
                    zzlm zzp2 = zzakVar4.zzp(zzqVar.zza, "_id");
                    if (zzp2 != null && !zzlmVar.zze.equals(zzp2.zze)) {
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzA(zzqVar.zza, "_lair");
                    }
                }
                zzd(zzqVar);
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                boolean zzL = zzakVar6.zzL(zzlmVar);
                if (zzg().zzs(null, zzeg.zzaH) && "_sid".equals(zzlkVar.zzb)) {
                    zzlj zzljVar = this.zzi;
                    zzal(zzljVar);
                    long zzd2 = zzljVar.zzd(zzqVar.zzx);
                    zzak zzakVar7 = this.zze;
                    zzal(zzakVar7);
                    zzh zzj = zzakVar7.zzj(zzqVar.zza);
                    if (zzj != null) {
                        zzj.zzaj(zzd2);
                        if (zzj.zzao()) {
                            zzak zzakVar8 = this.zze;
                            zzal(zzakVar8);
                            zzakVar8.zzD(zzj);
                        }
                    }
                }
                zzak zzakVar9 = this.zze;
                zzal(zzakVar9);
                zzakVar9.zzC();
                if (!zzL) {
                    zzaA().zzd().zzc("Too many unique user properties are set. Ignoring user property", this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    zzv().zzO(this.zzF, zzqVar.zza, 9, null, null, 0);
                }
            } finally {
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzx();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:335:0x0108, code lost:
        if (r11 != null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x010a, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x0112, code lost:
        if (r11 != null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x012a, code lost:
        if (r11 == null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x012d, code lost:
        r22.zzA = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x02fe, code lost:
        r0 = r0.subList(0, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x0303, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x0304, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x04f3, code lost:
        if (r3 != null) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:508:0x04f5, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:522:0x051a, code lost:
        if (r3 == null) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:524:0x051d, code lost:
        r9 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0134 A[Catch: all -> 0x0542, TryCatch #2 {all -> 0x0542, blocks: (B:297:0x0010, B:299:0x0021, B:303:0x0034, B:305:0x003a, B:307:0x004a, B:309:0x0052, B:311:0x0058, B:313:0x0063, B:315:0x0073, B:317:0x007e, B:319:0x0091, B:321:0x00b0, B:323:0x00b6, B:324:0x00b9, B:326:0x00c5, B:327:0x00dc, B:329:0x00ed, B:331:0x00f3, B:336:0x010a, B:350:0x012d, B:354:0x0134, B:355:0x0137, B:356:0x0138, B:360:0x0160, B:364:0x0168, B:370:0x019e, B:492:0x043d), top: B:545:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0270 A[Catch: all -> 0x053f, TRY_ENTER, TRY_LEAVE, TryCatch #22 {all -> 0x053f, blocks: (B:428:0x029f, B:430:0x02a5, B:432:0x02b1, B:433:0x02b5, B:435:0x02bb, B:437:0x02cf, B:441:0x02d8, B:443:0x02de, B:446:0x02f3, B:454:0x030a, B:456:0x0325, B:460:0x0334, B:462:0x0358, B:466:0x0392, B:468:0x0397, B:470:0x039f, B:471:0x03a2, B:473:0x03a7, B:474:0x03aa, B:476:0x03b6, B:477:0x03cc, B:480:0x03d8, B:482:0x03e9, B:484:0x03fa, B:485:0x040f, B:487:0x041c, B:489:0x0431, B:493:0x0441, B:494:0x0445, B:488:0x042a, B:496:0x0494, B:415:0x0270, B:427:0x029c, B:500:0x04af, B:501:0x04b2, B:502:0x04b3, B:508:0x04f5, B:525:0x051e, B:527:0x0524, B:529:0x052f, B:513:0x0500, B:534:0x053b, B:535:0x053e, B:492:0x043d), top: B:565:0x00eb, inners: #16 }] */
    /* JADX WARN: Removed duplicated region for block: B:430:0x02a5 A[Catch: all -> 0x053f, TryCatch #22 {all -> 0x053f, blocks: (B:428:0x029f, B:430:0x02a5, B:432:0x02b1, B:433:0x02b5, B:435:0x02bb, B:437:0x02cf, B:441:0x02d8, B:443:0x02de, B:446:0x02f3, B:454:0x030a, B:456:0x0325, B:460:0x0334, B:462:0x0358, B:466:0x0392, B:468:0x0397, B:470:0x039f, B:471:0x03a2, B:473:0x03a7, B:474:0x03aa, B:476:0x03b6, B:477:0x03cc, B:480:0x03d8, B:482:0x03e9, B:484:0x03fa, B:485:0x040f, B:487:0x041c, B:489:0x0431, B:493:0x0441, B:494:0x0445, B:488:0x042a, B:496:0x0494, B:415:0x0270, B:427:0x029c, B:500:0x04af, B:501:0x04b2, B:502:0x04b3, B:508:0x04f5, B:525:0x051e, B:527:0x0524, B:529:0x052f, B:513:0x0500, B:534:0x053b, B:535:0x053e, B:492:0x043d), top: B:565:0x00eb, inners: #16 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzX() {
        /*
            Method dump skipped, instructions count: 1354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzX():void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:436:0x02fa
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    final void zzY(com.google.android.gms.measurement.internal.zzau r36, com.google.android.gms.measurement.internal.zzq r37) {
        /*
            Method dump skipped, instructions count: 2920
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzY(com.google.android.gms.measurement.internal.zzau, com.google.android.gms.measurement.internal.zzq):void");
    }

    final boolean zzZ() {
        zzaB().zzg();
        FileLock fileLock = this.zzw;
        if (fileLock == null || !fileLock.isValid()) {
            this.zze.zzt.zzf();
            try {
                FileChannel channel = new RandomAccessFile(new File(this.zzn.zzaw().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zzx = channel;
                FileLock tryLock = channel.tryLock();
                this.zzw = tryLock;
                if (tryLock != null) {
                    zzaA().zzj().zza("Storage concurrent access okay");
                    return true;
                }
                zzaA().zzd().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                zzaA().zzd().zzb("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                zzaA().zzd().zzb("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                zzaA().zzk().zzb("Storage lock already acquired", e3);
                return false;
            }
        }
        zzaA().zzj().zza("Storage concurrent access okay");
        return true;
    }

    final long zza() {
        long currentTimeMillis = zzax().currentTimeMillis();
        zzkb zzkbVar = this.zzk;
        zzkbVar.zzW();
        zzkbVar.zzg();
        long zza = zzkbVar.zze.zza();
        if (zza == 0) {
            zza = zzkbVar.zzt.zzv().zzG().nextInt(86400000) + 1;
            zzkbVar.zze.zzb(zza);
        }
        return ((((currentTimeMillis + zza) / 1000) / 60) / 60) / 24;
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzet zzaA() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzaA();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzga zzaB() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzaB();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final Context zzaw() {
        return this.zzn.zzaw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final Clock zzax() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzax();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzab zzay() {
        throw null;
    }

    public final zzh zzd(zzq zzqVar) {
        zzaB().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        if (!zzqVar.zzw.isEmpty()) {
            this.zzC.put(zzqVar.zza, new zzlg(this, zzqVar.zzw));
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzj = zzakVar.zzj(zzqVar.zza);
        zzhb zzd = zzq(zzqVar.zza).zzd(zzhb.zzc(zzqVar.zzv, 100));
        String zzf = zzd.zzj(zzha.AD_STORAGE) ? this.zzk.zzf(zzqVar.zza, zzqVar.zzo) : "";
        if (zzj == null) {
            zzj = new zzh(this.zzn, zzqVar.zza);
            if (zzd.zzj(zzha.ANALYTICS_STORAGE)) {
                zzj.zzJ(zzw(zzd));
            }
            if (zzd.zzj(zzha.AD_STORAGE)) {
                zzj.zzag(zzf);
            }
        } else if (!zzd.zzj(zzha.AD_STORAGE) || zzf == null || zzf.equals(zzj.zzC())) {
            if (TextUtils.isEmpty(zzj.zzw()) && zzd.zzj(zzha.ANALYTICS_STORAGE)) {
                zzj.zzJ(zzw(zzd));
            }
        } else {
            zzj.zzag(zzf);
            if (zzqVar.zzo && !"00000000-0000-0000-0000-000000000000".equals(this.zzk.zzd(zzqVar.zza, zzd).first)) {
                zzj.zzJ(zzw(zzd));
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                if (zzakVar2.zzp(zzqVar.zza, "_id") != null) {
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    if (zzakVar3.zzp(zzqVar.zza, "_lair") == null) {
                        zzlm zzlmVar = new zzlm(zzqVar.zza, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lair", zzax().currentTimeMillis(), 1L);
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        zzakVar4.zzL(zzlmVar);
                    }
                }
            }
        }
        zzj.zzY(zzqVar.zzb);
        zzj.zzH(zzqVar.zzq);
        if (!TextUtils.isEmpty(zzqVar.zzk)) {
            zzj.zzX(zzqVar.zzk);
        }
        long j = zzqVar.zze;
        if (j != 0) {
            zzj.zzZ(j);
        }
        if (!TextUtils.isEmpty(zzqVar.zzc)) {
            zzj.zzL(zzqVar.zzc);
        }
        zzj.zzM(zzqVar.zzj);
        String str = zzqVar.zzd;
        if (str != null) {
            zzj.zzK(str);
        }
        zzj.zzU(zzqVar.zzf);
        zzj.zzae(zzqVar.zzh);
        if (!TextUtils.isEmpty(zzqVar.zzg)) {
            zzj.zzaa(zzqVar.zzg);
        }
        zzj.zzI(zzqVar.zzo);
        zzj.zzaf(zzqVar.zzr);
        zzj.zzV(zzqVar.zzs);
        zzqu.zzc();
        if (zzg().zzs(null, zzeg.zzam) || zzg().zzs(zzqVar.zza, zzeg.zzao)) {
            zzj.zzai(zzqVar.zzx);
        }
        zzop.zzc();
        if (!zzg().zzs(null, zzeg.zzal)) {
            zzop.zzc();
            if (zzg().zzs(null, zzeg.zzak)) {
                zzj.zzah(null);
            }
        } else {
            zzj.zzah(zzqVar.zzt);
        }
        zzrd.zzc();
        if (zzg().zzs(null, zzeg.zzaq)) {
            zzj.zzak(zzqVar.zzy);
        }
        zzpz.zzc();
        if (zzg().zzs(null, zzeg.zzaE)) {
            zzj.zzal(zzqVar.zzz);
        }
        if (zzj.zzao()) {
            zzak zzakVar5 = this.zze;
            zzal(zzakVar5);
            zzakVar5.zzD(zzj);
        }
        return zzj;
    }

    public final zzaa zzf() {
        zzaa zzaaVar = this.zzh;
        zzal(zzaaVar);
        return zzaaVar;
    }

    public final zzag zzg() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzf();
    }

    public final zzak zzh() {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        return zzakVar;
    }

    public final zzeo zzi() {
        return this.zzn.zzj();
    }

    public final zzez zzj() {
        zzez zzezVar = this.zzd;
        zzal(zzezVar);
        return zzezVar;
    }

    public final zzfb zzl() {
        zzfb zzfbVar = this.zzf;
        if (zzfbVar != null) {
            return zzfbVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzfu zzm() {
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        return zzfuVar;
    }

    public final zzgd zzp() {
        return this.zzn;
    }

    public final zzhb zzq(String str) {
        String str2;
        zzhb zzhbVar = zzhb.zza;
        zzaB().zzg();
        zzB();
        zzhb zzhbVar2 = (zzhb) this.zzB.get(str);
        if (zzhbVar2 == null) {
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            Preconditions.checkNotNull(str);
            zzakVar.zzg();
            zzakVar.zzW();
            Cursor cursor = null;
            try {
                try {
                    cursor = zzakVar.zzh().rawQuery("select consent_state from consent_settings where app_id=? limit 1;", new String[]{str});
                    if (cursor.moveToFirst()) {
                        str2 = cursor.getString(0);
                    } else {
                        if (cursor != null) {
                            cursor.close();
                        }
                        str2 = "G1";
                    }
                    zzhb zzc = zzhb.zzc(str2, 100);
                    zzV(str, zzc);
                    return zzc;
                } catch (SQLiteException e) {
                    zzakVar.zzt.zzaA().zzd().zzc("Database error", "select consent_state from consent_settings where app_id=? limit 1;", e);
                    throw e;
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return zzhbVar2;
    }

    public final zzip zzr() {
        zzip zzipVar = this.zzj;
        zzal(zzipVar);
        return zzipVar;
    }

    public final zzkb zzs() {
        return this.zzk;
    }

    public final zzlj zzu() {
        zzlj zzljVar = this.zzi;
        zzal(zzljVar);
        return zzljVar;
    }

    public final zzlp zzv() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzv();
    }

    final String zzw(zzhb zzhbVar) {
        if (zzhbVar.zzj(zzha.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            zzv().zzG().nextBytes(bArr);
            return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        }
        return null;
    }

    public final String zzx(zzq zzqVar) {
        try {
            return (String) zzaB().zzh(new zzla(this, zzqVar)).get(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzaA().zzd().zzc("Failed to get app instance id. appId", zzet.zzn(zzqVar.zza), e);
            return null;
        }
    }

    public final void zzz(Runnable runnable) {
        zzaB().zzg();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }
}
