package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzfa implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzfb zzb;

    public zzfa(zzfb zzfbVar, boolean z) {
        this.zzb = zzfbVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlh zzlhVar;
        zzlhVar = this.zzb.zzb;
        zzlhVar.zzJ(this.zza);
    }
}
