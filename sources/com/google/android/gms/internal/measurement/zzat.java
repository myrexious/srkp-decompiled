package com.google.android.gms.internal.measurement;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzat implements Iterable, zzap {
    private final String zza;

    public zzat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.zza.equals(((zzat) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzas(this);
    }

    public final String toString() {
        String str = this.zza;
        return OperatorName.SHOW_TEXT_LINE_AND_SPACE + str + OperatorName.SHOW_TEXT_LINE_AND_SPACE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:310:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x063a  */
    @Override // com.google.android.gms.internal.measurement.zzap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.measurement.zzap zzbU(java.lang.String r22, com.google.android.gms.internal.measurement.zzg r23, java.util.List r24) {
        /*
            Method dump skipped, instructions count: 1776
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzat.zzbU(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzar(this);
    }
}
