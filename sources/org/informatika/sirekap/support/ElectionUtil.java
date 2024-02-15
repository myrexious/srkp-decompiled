package org.informatika.sirekap.support;

import android.content.Context;
import android.provider.Settings;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ElectionUtil.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0016\u0010\u001c\u001a\u00020\b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u001eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/support/ElectionUtil;", "", "()V", "ID_IMAGE_SEPARATOR", "", "KODE_TPS_SEPARATOR", "WITNESS_ID_SEPARATOR", "extractIdImageReal", "", "idImage", "extractKodeTpsReal", "kodeTps", "extractWitnessIdReal", "witnessId", "generateIdImage", "idImageReal", "jenisPemilihan", "profile", "generateKodeTps", "kodeTpsReal", "generateWitnessId", "noHandphoneReal", "getFormCImageTallyPageNum", "numCandidate", "isTimeAutomatic", "", "context", "Landroid/content/Context;", "joinThreeNumbers", "numbers", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionUtil {
    private static final String ID_IMAGE_SEPARATOR = "|";
    public static final ElectionUtil INSTANCE = new ElectionUtil();
    public static final String KODE_TPS_SEPARATOR = "|";
    private static final String WITNESS_ID_SEPARATOR = "|";

    private ElectionUtil() {
    }

    @JvmStatic
    public static final int joinThreeNumbers(List<Integer> numbers) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(numbers, "numbers");
        List<Integer> list = numbers;
        int i = 0;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (Number number : list) {
                if (number.intValue() < 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        int i2 = 0;
        for (int lastIndex = CollectionsKt.getLastIndex(numbers); -1 < lastIndex; lastIndex--) {
            i += (int) (Math.abs(numbers.get(i2).intValue()) * Math.pow(10.0d, lastIndex));
            i2++;
        }
        return z2 ? i * (-1) : i;
    }

    @JvmStatic
    public static final int getFormCImageTallyPageNum(int i) {
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        if (1 <= i && i < 4) {
            return 1;
        }
        if (4 <= i && i < 8) {
            return 2;
        }
        if (8 <= i && i < 11) {
            z = true;
        }
        if (z) {
            return 3;
        }
        throw new Exception("Candidate number should be between 1 and 10. Value: " + i);
    }

    @JvmStatic
    public static final String generateIdImage(int i, String jenisPemilihan, String profile) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(profile, "profile");
        return CollectionsKt.joinToString$default(CollectionsKt.listOf(profile, Integer.valueOf(i), jenisPemilihan), KODE_TPS_SEPARATOR, null, null, 0, null, null, 62, null);
    }

    @JvmStatic
    public static final int extractIdImageReal(String idImage) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return Integer.parseInt((String) StringsKt.split$default((CharSequence) idImage, new String[]{KODE_TPS_SEPARATOR}, false, 0, 6, (Object) null).get(1));
    }

    @JvmStatic
    public static final String generateKodeTps(String kodeTpsReal, String profile) {
        Intrinsics.checkNotNullParameter(kodeTpsReal, "kodeTpsReal");
        Intrinsics.checkNotNullParameter(profile, "profile");
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{profile, kodeTpsReal}), KODE_TPS_SEPARATOR, null, null, 0, null, null, 62, null);
    }

    @JvmStatic
    public static final String extractKodeTpsReal(String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return (String) StringsKt.split$default((CharSequence) kodeTps, new String[]{KODE_TPS_SEPARATOR}, false, 0, 6, (Object) null).get(1);
    }

    @JvmStatic
    public static final String generateWitnessId(String noHandphoneReal, String profile) {
        Intrinsics.checkNotNullParameter(noHandphoneReal, "noHandphoneReal");
        Intrinsics.checkNotNullParameter(profile, "profile");
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{profile, noHandphoneReal}), KODE_TPS_SEPARATOR, null, null, 0, null, null, 62, null);
    }

    @JvmStatic
    public static final String extractWitnessIdReal(String witnessId) {
        Intrinsics.checkNotNullParameter(witnessId, "witnessId");
        return (String) StringsKt.split$default((CharSequence) witnessId, new String[]{KODE_TPS_SEPARATOR}, false, 0, 6, (Object) null).get(1);
    }

    @JvmStatic
    public static final boolean isTimeAutomatic(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Settings.Global.getInt(context.getContentResolver(), "auto_time", 0) == 1;
    }
}
