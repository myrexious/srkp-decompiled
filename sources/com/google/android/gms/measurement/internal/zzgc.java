package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzgc implements Runnable {
    final /* synthetic */ zzhi zza;
    final /* synthetic */ zzgd zzb;

    public zzgc(zzgd zzgdVar, zzhi zzhiVar) {
        this.zzb = zzgdVar;
        this.zza = zzhiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgd.zzA(this.zzb, this.zza);
        this.zzb.zzH(this.zza.zzg);
    }
}
