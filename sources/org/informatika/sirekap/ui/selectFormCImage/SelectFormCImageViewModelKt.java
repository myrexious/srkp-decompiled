package org.informatika.sirekap.ui.selectFormCImage;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Election;

/* compiled from: SelectFormCImageViewModel.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\"&\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"jenisPemilihanIdMap", "", "", "", "getJenisPemilihanIdMap", "()Ljava/util/Map;", "setJenisPemilihanIdMap", "(Ljava/util/Map;)V", "app_productionRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SelectFormCImageViewModelKt {
    private static Map<String, Integer> jenisPemilihanIdMap = MapsKt.mapOf(TuplesKt.to(Election.ELECTION_PEMILIHAN_PRESIDEN, 1), TuplesKt.to(Election.ELECTION_PEMILIHAN_DPR, 2), TuplesKt.to(Election.ELECTION_PEMILIHAN_DPD, 3), TuplesKt.to(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, 4), TuplesKt.to(Election.ELECTION_PEMILIHAN_DPRD_KABKO, 5));

    public static final Map<String, Integer> getJenisPemilihanIdMap() {
        return jenisPemilihanIdMap;
    }

    public static final void setJenisPemilihanIdMap(Map<String, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        jenisPemilihanIdMap = map;
    }
}
