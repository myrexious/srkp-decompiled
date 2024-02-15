package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzek extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    public zzek(zzgd zzgdVar, long j) {
        super(zzgdVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(1:3)(6:65|66|(1:68)(2:83|(1:85))|69|70|(20:72|(1:74)(1:81)|76|77|5|(1:64)(1:9)|10|11|13|(1:15)|16|17|(1:19)(1:53)|20|(3:22|(1:24)(1:26)|25)|(3:28|(1:30)(1:33)|31)|34|(3:36|(1:38)(3:45|(3:48|(1:50)|46)|51)|(2:40|41)(2:43|44))|52|(0)(0)))|4|5|(1:7)|64|10|11|13|(0)|16|17|(0)(0)|20|(0)|(0)|34|(0)|52|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x01db, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x01dc, code lost:
        r11.zzt.zzaA().zzd().zzc("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzet.zzn(r0), r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0194 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:140:0x0172, B:144:0x018c, B:146:0x0194, B:150:0x01b2, B:149:0x01ae, B:152:0x01bc, B:154:0x01d2, B:156:0x01d7, B:155:0x01d5), top: B:184:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01bc A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:140:0x0172, B:144:0x018c, B:146:0x0194, B:150:0x01b2, B:149:0x01ae, B:152:0x01bc, B:154:0x01d2, B:156:0x01d7, B:155:0x01d5), top: B:184:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x024c  */
    @Override // com.google.android.gms.measurement.internal.zzf
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void zzd() {
        /*
            Method dump skipped, instructions count: 612
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzek.zzd():void");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    public final int zzh() {
        zza();
        return this.zzj;
    }

    public final int zzi() {
        zza();
        return this.zzc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:148:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzq zzj(java.lang.String r41) {
        /*
            Method dump skipped, instructions count: 769
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzek.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzq");
    }

    public final String zzk() {
        zza();
        return this.zzl;
    }

    public final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    public final String zzm() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    public final List zzn() {
        return this.zzh;
    }

    public final void zzo() {
        String format;
        zzg();
        if (!this.zzt.zzm().zzc().zzj(zzha.ANALYTICS_STORAGE)) {
            this.zzt.zzaA().zzc().zza("Analytics Storage consent is not granted");
            format = null;
        } else {
            byte[] bArr = new byte[16];
            this.zzt.zzv().zzG().nextBytes(bArr);
            format = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        }
        zzer zzc = this.zzt.zzaA().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = format == null ? "null" : "not null";
        zzc.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzm = format;
        this.zzn = this.zzt.zzax().currentTimeMillis();
    }

    public final boolean zzp(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
