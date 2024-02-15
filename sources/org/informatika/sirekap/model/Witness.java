package org.informatika.sirekap.model;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.tensorflow.lite.schema.BuiltinOperator;

/* compiled from: Witness.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0087\b\u0018\u0000 D2\u00020\u0001:\u0001DB\u0007\b\u0016¢\u0006\u0002\u0010\u0002BM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\u0002\u0010\u000fJ\t\u0010(\u001a\u00020\u0004HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\u0011\u0010/\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003Ja\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u000206J\t\u00107\u001a\u000208HÖ\u0001J\u0006\u00109\u001a\u000202J\u0006\u0010:\u001a\u000202J\u0006\u0010;\u001a\u000202J\u0006\u0010<\u001a\u000202J\u0006\u0010=\u001a\u00020\u0006J\u0006\u0010>\u001a\u00020\u0006J\u0006\u0010?\u001a\u00020\u0000J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u0006J\t\u0010B\u001a\u00020\u0006HÖ\u0001J\u0006\u0010C\u001a\u00020\u0006R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001e\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\u0011\u0010 \u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b!\u0010\u0015R&\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001e\u0010\u000b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\u0017¨\u0006E"}, d2 = {"Lorg/informatika/sirekap/model/Witness;", "", "()V", "idPetugas", "", "nama", "", "nik", "noHandphone", "jenisPemeriksa", "kodeTps", "urutan", "pemeriksas", "", "Lorg/informatika/sirekap/model/WitnessPemeriksa;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getIdPetugas", "()J", "setIdPetugas", "(J)V", "getJenisPemeriksa", "()Ljava/lang/String;", "setJenisPemeriksa", "(Ljava/lang/String;)V", "getKodeTps", "setKodeTps", "getNama", "setNama", "getNik", "setNik", "getNoHandphone", "setNoHandphone", "noHandphoneFormatted", "getNoHandphoneFormatted", "getPemeriksas", "()Ljava/util/List;", "setPemeriksas", "(Ljava/util/List;)V", "getUrutan", "setUrutan", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "getJenisPemeriksaText", "context", "Landroid/content/Context;", "hashCode", "", "isPPS", "isSaksi", "isSharedLink", "isWitnessLocal", "jenisPemeriksaFormatted", "onlineOfflineStatus", "toDecrypted", "toEncrypted", "noHandphoneEncrypted", "toString", "toStringPdf", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Witness {
    public static final String WITNESS_TYPE_KPPS = "kpps";
    @SerializedName("idPetugas")
    private long idPetugas;
    @SerializedName("jenisPemeriksa")
    private String jenisPemeriksa;
    @SerializedName("kodeTps")
    private String kodeTps;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nik")
    private String nik;
    @SerializedName("noHandphone")
    private String noHandphone;
    @SerializedName("pemeriksas")
    private List<WitnessPemeriksa> pemeriksas;
    @SerializedName("urutan")
    private String urutan;
    public static final Companion Companion = new Companion(null);
    public static final String WITNESS_TYPE_SAKSI = "saksi";
    public static final String WITNESS_TYPE_PANWAS = "panwas";
    public static final String WITNESS_TYPE_PPS = "pps";
    private static final List<String> PRE_ZIP_WITNESS_TYPES = CollectionsKt.listOf((Object[]) new String[]{WITNESS_TYPE_SAKSI, WITNESS_TYPE_PANWAS, WITNESS_TYPE_PPS});
    private static final List<String> POST_ZIP_WITNESS_TYPES = CollectionsKt.listOf(WITNESS_TYPE_PPS);

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Witness copy$default(Witness witness, long j, String str, String str2, String str3, String str4, String str5, String str6, List list, int i, Object obj) {
        return witness.copy((i & 1) != 0 ? witness.idPetugas : j, (i & 2) != 0 ? witness.nama : str, (i & 4) != 0 ? witness.nik : str2, (i & 8) != 0 ? witness.noHandphone : str3, (i & 16) != 0 ? witness.jenisPemeriksa : str4, (i & 32) != 0 ? witness.kodeTps : str5, (i & 64) != 0 ? witness.urutan : str6, (i & 128) != 0 ? witness.pemeriksas : list);
    }

    @JvmStatic
    public static final String getJenisPemeriksaDpdPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaDpdPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaDprPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaDprPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaDprdkPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaDprdkPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaDprdpPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaDprdpPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaPilgubPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaPilgubPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaPilwalkotPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaPilwalkotPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaPresidenPromptText(Context context, String str) {
        return Companion.getJenisPemeriksaPresidenPromptText(context, str);
    }

    @JvmStatic
    public static final String getJenisPemeriksaText(Context context, String str) {
        return Companion.getJenisPemeriksaText(context, str);
    }

    public final long component1() {
        return this.idPetugas;
    }

    public final String component2() {
        return this.nama;
    }

    public final String component3() {
        return this.nik;
    }

    public final String component4() {
        return this.noHandphone;
    }

    public final String component5() {
        return this.jenisPemeriksa;
    }

    public final String component6() {
        return this.kodeTps;
    }

    public final String component7() {
        return this.urutan;
    }

    public final List<WitnessPemeriksa> component8() {
        return this.pemeriksas;
    }

    public final Witness copy(long j, String nama, String nik, String noHandphone, String jenisPemeriksa, String kodeTps, String urutan, List<WitnessPemeriksa> list) {
        Intrinsics.checkNotNullParameter(nama, "nama");
        Intrinsics.checkNotNullParameter(nik, "nik");
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(urutan, "urutan");
        return new Witness(j, nama, nik, noHandphone, jenisPemeriksa, kodeTps, urutan, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Witness) {
            Witness witness = (Witness) obj;
            return this.idPetugas == witness.idPetugas && Intrinsics.areEqual(this.nama, witness.nama) && Intrinsics.areEqual(this.nik, witness.nik) && Intrinsics.areEqual(this.noHandphone, witness.noHandphone) && Intrinsics.areEqual(this.jenisPemeriksa, witness.jenisPemeriksa) && Intrinsics.areEqual(this.kodeTps, witness.kodeTps) && Intrinsics.areEqual(this.urutan, witness.urutan) && Intrinsics.areEqual(this.pemeriksas, witness.pemeriksas);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((((((((((Long.hashCode(this.idPetugas) * 31) + this.nama.hashCode()) * 31) + this.nik.hashCode()) * 31) + this.noHandphone.hashCode()) * 31) + this.jenisPemeriksa.hashCode()) * 31) + this.kodeTps.hashCode()) * 31) + this.urutan.hashCode()) * 31;
        List<WitnessPemeriksa> list = this.pemeriksas;
        return hashCode + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        long j = this.idPetugas;
        String str = this.nama;
        String str2 = this.nik;
        String str3 = this.noHandphone;
        String str4 = this.jenisPemeriksa;
        String str5 = this.kodeTps;
        String str6 = this.urutan;
        return "Witness(idPetugas=" + j + ", nama=" + str + ", nik=" + str2 + ", noHandphone=" + str3 + ", jenisPemeriksa=" + str4 + ", kodeTps=" + str5 + ", urutan=" + str6 + ", pemeriksas=" + this.pemeriksas + ")";
    }

    public Witness(long j, String nama, String nik, String noHandphone, String jenisPemeriksa, String kodeTps, String urutan, List<WitnessPemeriksa> list) {
        Intrinsics.checkNotNullParameter(nama, "nama");
        Intrinsics.checkNotNullParameter(nik, "nik");
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(urutan, "urutan");
        this.idPetugas = j;
        this.nama = nama;
        this.nik = nik;
        this.noHandphone = noHandphone;
        this.jenisPemeriksa = jenisPemeriksa;
        this.kodeTps = kodeTps;
        this.urutan = urutan;
        this.pemeriksas = list;
    }

    public final long getIdPetugas() {
        return this.idPetugas;
    }

    public final void setIdPetugas(long j) {
        this.idPetugas = j;
    }

    public final String getNama() {
        return this.nama;
    }

    public final void setNama(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nama = str;
    }

    public final String getNik() {
        return this.nik;
    }

    public final void setNik(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nik = str;
    }

    public final String getNoHandphone() {
        return this.noHandphone;
    }

    public final void setNoHandphone(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.noHandphone = str;
    }

    public final String getJenisPemeriksa() {
        return this.jenisPemeriksa;
    }

    public final void setJenisPemeriksa(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.jenisPemeriksa = str;
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final void setKodeTps(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.kodeTps = str;
    }

    public final String getUrutan() {
        return this.urutan;
    }

    public final void setUrutan(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.urutan = str;
    }

    public final List<WitnessPemeriksa> getPemeriksas() {
        return this.pemeriksas;
    }

    public final void setPemeriksas(List<WitnessPemeriksa> list) {
        this.pemeriksas = list;
    }

    public Witness() {
        this(0L, "", "", "", "", "", "0", CollectionsKt.emptyList());
    }

    public final boolean isWitnessLocal() {
        return this.idPetugas < 0;
    }

    public final boolean isPPS() {
        return Intrinsics.areEqual(this.jenisPemeriksa, WITNESS_TYPE_PPS);
    }

    public final boolean isSaksi() {
        return Intrinsics.areEqual(this.jenisPemeriksa, WITNESS_TYPE_SAKSI);
    }

    public final boolean isSharedLink() {
        return isPPS() || isSaksi();
    }

    public final String getJenisPemeriksaText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Companion.getJenisPemeriksaText(context, this.jenisPemeriksa);
    }

    public final String getNoHandphoneFormatted() {
        return ElectionUtil.extractWitnessIdReal(this.noHandphone);
    }

    public final String toStringPdf() {
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{this.nik, this.nama, this.jenisPemeriksa, this.noHandphone, this.urutan}), " | ", null, null, 0, null, null, 62, null);
    }

    public final String jenisPemeriksaFormatted() {
        String str = this.jenisPemeriksa;
        switch (str.hashCode()) {
            case -995526356:
                if (str.equals(WITNESS_TYPE_PANWAS)) {
                    return "Panwas";
                }
                break;
            case 111219:
                if (str.equals(WITNESS_TYPE_PPS)) {
                    return "PPS";
                }
                break;
            case 3298856:
                if (str.equals("kpps")) {
                    return "KPPS";
                }
                break;
            case 109201139:
                if (str.equals(WITNESS_TYPE_SAKSI)) {
                    return "Saksi";
                }
                break;
        }
        return "-";
    }

    public final String onlineOfflineStatus() {
        return isWitnessLocal() ? "Offline" : "Online";
    }

    public final Witness toEncrypted(String noHandphoneEncrypted) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(noHandphoneEncrypted, "noHandphoneEncrypted");
        String encryptCode = SecurityFacade.INSTANCE.encryptCode(this.nama);
        String encryptCode2 = SecurityFacade.INSTANCE.encryptCode(this.nik);
        List<WitnessPemeriksa> list = this.pemeriksas;
        if (list != null) {
            List<WitnessPemeriksa> list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (WitnessPemeriksa witnessPemeriksa : list2) {
                arrayList2.add(witnessPemeriksa.toEncrypted(noHandphoneEncrypted));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return copy$default(this, 0L, encryptCode, encryptCode2, noHandphoneEncrypted, null, null, null, arrayList, BuiltinOperator.MATRIX_DIAG, null);
    }

    public final Witness toDecrypted() {
        ArrayList arrayList;
        String decryptCode = SecurityFacade.INSTANCE.decryptCode(this.nama);
        String decryptCode2 = SecurityFacade.INSTANCE.decryptCode(this.nik);
        String decryptCode3 = SecurityFacade.INSTANCE.decryptCode(this.noHandphone);
        List<WitnessPemeriksa> list = this.pemeriksas;
        if (list != null) {
            List<WitnessPemeriksa> list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (WitnessPemeriksa witnessPemeriksa : list2) {
                arrayList2.add(witnessPemeriksa.toDecrypted());
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return copy$default(this, 0L, decryptCode, decryptCode2, decryptCode3, null, null, null, arrayList, BuiltinOperator.MATRIX_DIAG, null);
    }

    /* compiled from: Witness.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0007J\u0018\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/model/Witness$Companion;", "", "()V", "POST_ZIP_WITNESS_TYPES", "", "", "getPOST_ZIP_WITNESS_TYPES", "()Ljava/util/List;", "PRE_ZIP_WITNESS_TYPES", "getPRE_ZIP_WITNESS_TYPES", "WITNESS_TYPE_KPPS", "WITNESS_TYPE_PANWAS", "WITNESS_TYPE_PPS", "WITNESS_TYPE_SAKSI", "getJenisPemeriksaDpdPromptText", "context", "Landroid/content/Context;", "jenisPemeriksa", "getJenisPemeriksaDprPromptText", "getJenisPemeriksaDprdkPromptText", "getJenisPemeriksaDprdpPromptText", "getJenisPemeriksaPilgubPromptText", "getJenisPemeriksaPilwalkotPromptText", "getJenisPemeriksaPresidenPromptText", "getJenisPemeriksaText", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getPRE_ZIP_WITNESS_TYPES() {
            return Witness.PRE_ZIP_WITNESS_TYPES;
        }

        public final List<String> getPOST_ZIP_WITNESS_TYPES() {
            return Witness.POST_ZIP_WITNESS_TYPES;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @JvmStatic
        public final String getJenisPemeriksaText(Context context, String jenisPemeriksa) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
            switch (jenisPemeriksa.hashCode()) {
                case -995526356:
                    if (jenisPemeriksa.equals(Witness.WITNESS_TYPE_PANWAS)) {
                        str = context.getString(R.string.witness_type_panwas);
                        break;
                    }
                    str = "";
                    break;
                case 111219:
                    if (jenisPemeriksa.equals(Witness.WITNESS_TYPE_PPS)) {
                        str = context.getString(R.string.witness_type_pps);
                        break;
                    }
                    str = "";
                    break;
                case 3298856:
                    if (jenisPemeriksa.equals("kpps")) {
                        str = context.getString(R.string.witness_type_kpps);
                        break;
                    }
                    str = "";
                    break;
                case 109201139:
                    if (jenisPemeriksa.equals(Witness.WITNESS_TYPE_SAKSI)) {
                        str = context.getString(R.string.witness_type_saksi);
                        break;
                    }
                    str = "";
                    break;
                default:
                    str = "";
                    break;
            }
            Intrinsics.checkNotNullExpressionValue(str, "when (jenisPemeriksa) {\n…unreachable\n            }");
            return str;
        }

        @JvmStatic
        public final String getJenisPemeriksaPresidenPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_presiden_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_presiden);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…a_presiden)\n            }");
            return string;
        }

        @JvmStatic
        public final String getJenisPemeriksaPilwalkotPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_pilwalkot_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_pilwalkot);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…_pilwalkot)\n            }");
            return string;
        }

        @JvmStatic
        public final String getJenisPemeriksaPilgubPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_pilgub_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_pilgub);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…ksa_pilgub)\n            }");
            return string;
        }

        @JvmStatic
        public final String getJenisPemeriksaDprPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_dpr_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_dpr);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…eriksa_dpr)\n            }");
            return string;
        }

        @JvmStatic
        public final String getJenisPemeriksaDprdpPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_dprdp_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_dprdp);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…iksa_dprdp)\n            }");
            return string;
        }

        @JvmStatic
        public final String getJenisPemeriksaDprdkPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_dprdk_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_dprdk);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…iksa_dprdk)\n            }");
            return string;
        }

        @JvmStatic
        public final String getJenisPemeriksaDpdPromptText(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Intrinsics.areEqual(str, Witness.WITNESS_TYPE_PANWAS) ? context.getString(R.string.prompt_witness_jenis_pemeriksa_dpd_panwas) : context.getString(R.string.prompt_witness_jenis_pemeriksa_dpd);
            Intrinsics.checkNotNullExpressionValue(string, "when (jenisPemeriksa) {\n…eriksa_dpd)\n            }");
            return string;
        }
    }
}
