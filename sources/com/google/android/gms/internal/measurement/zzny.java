package com.google.android.gms.internal.measurement;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzny extends IllegalArgumentException {
    public zzny(int i, int i2) {
        super("Unpaired surrogate at index " + i + " of " + i2);
    }
}
