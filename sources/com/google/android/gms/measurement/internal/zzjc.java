package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzjc implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzlk zzc;
    final /* synthetic */ zzjz zzd;

    public zzjc(zzjz zzjzVar, zzq zzqVar, boolean z, zzlk zzlkVar) {
        this.zzd = zzjzVar;
        this.zza = zzqVar;
        this.zzb = z;
        this.zzc = zzlkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzej zzejVar;
        zzjz zzjzVar = this.zzd;
        zzejVar = zzjzVar.zzb;
        if (zzejVar == null) {
            zzjzVar.zzt.zzaA().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zzD(zzejVar, this.zzb ? null : this.zzc, this.zza);
        this.zzd.zzQ();
    }
}
