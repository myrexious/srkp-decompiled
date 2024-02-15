package com.google.android.gms.internal.measurement;

import com.fasterxml.jackson.core.base.GeneratorBase;
import org.tensorflow.lite.schema.BuiltinOptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public final class zznv {
    public static /* bridge */ /* synthetic */ void zza(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (zze(b2) || (((b << BuiltinOptions.SubOptions) + (b2 + BuiltinOptions.ReadVariableOptions)) >> 30) != 0 || zze(b3) || zze(b4)) {
            throw zzll.zzc();
        }
        int i2 = ((b & 7) << 18) | ((b2 & BuiltinOptions.LogicalNotOptions) << 12) | ((b3 & BuiltinOptions.LogicalNotOptions) << 6) | (b4 & BuiltinOptions.LogicalNotOptions);
        cArr[i] = (char) ((i2 >>> 10) + 55232);
        cArr[i + 1] = (char) ((i2 & 1023) + GeneratorBase.SURR2_FIRST);
    }

    public static /* bridge */ /* synthetic */ void zzc(byte b, byte b2, char[] cArr, int i) {
        if (b < -62 || zze(b2)) {
            throw zzll.zzc();
        }
        cArr[i] = (char) (((b & BuiltinOptions.SequenceRNNOptions) << 6) | (b2 & BuiltinOptions.LogicalNotOptions));
    }

    public static /* bridge */ /* synthetic */ boolean zzd(byte b) {
        return b >= 0;
    }

    private static boolean zze(byte b) {
        return b > -65;
    }

    public static /* bridge */ /* synthetic */ void zzb(byte b, byte b2, byte b3, char[] cArr, int i) {
        if (!zze(b2)) {
            if (b == -32) {
                if (b2 >= -96) {
                    b = -32;
                }
            }
            if (b == -19) {
                if (b2 < -96) {
                    b = -19;
                }
            }
            if (!zze(b3)) {
                cArr[i] = (char) (((b & 15) << 12) | ((b2 & BuiltinOptions.LogicalNotOptions) << 6) | (b3 & BuiltinOptions.LogicalNotOptions));
                return;
            }
        }
        throw zzll.zzc();
    }
}
