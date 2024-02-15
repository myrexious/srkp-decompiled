package org.informatika.sirekap.support;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessShare;
import org.informatika.sirekap.support.security.SecurityFacade;

/* compiled from: WitnessUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/WitnessUtil;", "", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WitnessUtil {
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    public static final String generateWitnessShareUrl(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        return Companion.generateWitnessShareUrl(str, str2, str3, str4, str5, str6, str7, str8);
    }

    @JvmStatic
    public static final String generateWitnessShareUrlOffline(String str, String str2, String str3) {
        return Companion.generateWitnessShareUrlOffline(str, str2, str3);
    }

    @JvmStatic
    public static final String generateWitnessShareUrlOnline(String str, String str2, String str3) {
        return Companion.generateWitnessShareUrlOnline(str, str2, str3);
    }

    @JvmStatic
    public static final void insertWitnessesToDatabase(AppDatabase appDatabase, List<Witness> list, List<String> list2) {
        Companion.insertWitnessesToDatabase(appDatabase, list, list2);
    }

    /* compiled from: WitnessUtil.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J \u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J,\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0015H\u0007¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/support/WitnessUtil$Companion;", "", "()V", "generateWitnessShareUrl", "", "profile", "jenisPemilihan", "kodeTps", "jenisPemeriksa", "idPaslon", "nik", "noHp", "nama", "generateWitnessShareUrlOffline", "generateWitnessShareUrlOnline", "hashedText", "insertWitnessesToDatabase", "", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "witnessesRaw", "", "Lorg/informatika/sirekap/model/Witness;", "noHandphones", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final String generateWitnessShareUrl(String profile, String jenisPemilihan, String kodeTps, String jenisPemeriksa, String str, String nik, String noHp, String nama) {
            Intrinsics.checkNotNullParameter(profile, "profile");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
            Intrinsics.checkNotNullParameter(nik, "nik");
            Intrinsics.checkNotNullParameter(noHp, "noHp");
            Intrinsics.checkNotNullParameter(nama, "nama");
            String[] strArr = new String[8];
            String substring = SecurityFacade.INSTANCE.md5String(kodeTps).substring(0, 8);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            strArr[0] = substring;
            strArr[1] = jenisPemilihan;
            strArr[2] = kodeTps;
            strArr[3] = jenisPemeriksa;
            strArr[4] = str == null ? "0" : str;
            strArr[5] = nik;
            strArr[6] = noHp;
            strArr[7] = nama;
            return "https://sirekap-pdf.kpu.go.id/" + profile + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + RemoteSettings.FORWARD_SLASH_STRING + SecurityFacade.INSTANCE.hashString(CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) strArr), ";;", null, null, 0, null, null, 62, null));
        }

        @JvmStatic
        public final String generateWitnessShareUrlOnline(String profile, String jenisPemilihan, String hashedText) {
            Intrinsics.checkNotNullParameter(profile, "profile");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(hashedText, "hashedText");
            return "https://sirekap-pdf.kpu.go.id/" + hashedText;
        }

        @JvmStatic
        public final String generateWitnessShareUrlOffline(String profile, String jenisPemilihan, String kodeTps) {
            Intrinsics.checkNotNullParameter(profile, "profile");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTps);
            return "https://sirekap-pdf.kpu.go.id/" + profile + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + RemoteSettings.FORWARD_SLASH_STRING + extractKodeTpsReal + "-" + SecurityFacade.INSTANCE.hashString("zKvQSljPC+j#" + jenisPemilihan + extractKodeTpsReal);
        }

        @JvmStatic
        public final void insertWitnessesToDatabase(AppDatabase database, List<Witness> witnessesRaw, List<String> noHandphones) {
            Intrinsics.checkNotNullParameter(database, "database");
            Intrinsics.checkNotNullParameter(witnessesRaw, "witnessesRaw");
            Intrinsics.checkNotNullParameter(noHandphones, "noHandphones");
            List<Witness> list = witnessesRaw;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(((Witness) obj).toEncrypted(noHandphones.get(i)));
                i = i2;
            }
            ArrayList arrayList2 = arrayList;
            database.witnessDao().insertAll(arrayList2);
            ArrayList arrayList3 = new ArrayList();
            ArrayList<Witness> arrayList4 = arrayList2;
            for (Witness witness : arrayList4) {
                List<WitnessPemeriksa> pemeriksas = witness.getPemeriksas();
                if (pemeriksas != null) {
                    for (WitnessPemeriksa witnessPemeriksa : pemeriksas) {
                        arrayList3.add(new WitnessShare(witness.getNoHandphone(), witnessPemeriksa.getJenisPemilihan(), false));
                    }
                }
            }
            database.witnessDao().insertAllShares(arrayList3);
            ArrayList arrayList5 = new ArrayList();
            for (Witness witness2 : arrayList4) {
                List<WitnessPemeriksa> pemeriksas2 = witness2.getPemeriksas();
                if (pemeriksas2 != null) {
                    for (WitnessPemeriksa witnessPemeriksa2 : pemeriksas2) {
                        arrayList5.add(new WitnessPemeriksa(witness2.getNoHandphone(), witnessPemeriksa2.getJenisPemilihan(), witnessPemeriksa2.getIdPilihan(), witnessPemeriksa2.getUrl()));
                    }
                }
            }
            database.witnessDao().insertAllPemeriksas(arrayList5);
        }
    }
}
