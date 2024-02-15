package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;
import org.informatika.sirekap.model.Kecamatan;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.KotaKabupaten;
import org.informatika.sirekap.model.Provinsi;
import org.informatika.sirekap.model.Tps;

/* compiled from: GetTpsByEmailApiResponse.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\rB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\u0010\nR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/api/response/GetTpsByEmailApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "Lorg/informatika/sirekap/api/response/GetTpsByEmailApiResponse$TpsByEmailApiResponse;", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "TpsByEmailApiResponse", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GetTpsByEmailApiResponse extends GenericApiResponse {
    private final List<TpsByEmailApiResponse> data;

    public final List<TpsByEmailApiResponse> getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetTpsByEmailApiResponse(boolean z, String message, String str, List<TpsByEmailApiResponse> list) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = list;
    }

    /* compiled from: GetTpsByEmailApiResponse.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\bH\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u001f\u001a\u00020\n\u0012\b\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u000b\u0010L\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u000b\u0010S\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010Z\u001a\u00020\nHÆ\u0003J\u0010\u0010[\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010$J\u0010\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\t\u0010`\u001a\u00020\nHÆ\u0003J\u0010\u0010a\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010EJ\u0010\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'JØ\u0002\u0010c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u001f\u001a\u00020\n2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!HÆ\u0001¢\u0006\u0002\u0010dJ\u0013\u0010e\u001a\u00020!2\b\u0010f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010g\u001a\u00020\fHÖ\u0001J\t\u0010h\u001a\u00020\nHÖ\u0001J\u0006\u0010i\u001a\u00020jR\u0015\u0010 \u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b&\u0010'R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b)\u0010'R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b*\u0010'R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b+\u0010'R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b,\u0010'R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b-\u0010'R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b.\u0010'R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b/\u0010'R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b0\u0010'R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010(\u001a\u0004\b3\u0010'R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b6\u00105R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b7\u00105R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b8\u00105R\u0011\u0010\u001f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b9\u00105R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b:\u00105R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b;\u00105R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b<\u00105R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b=\u00105R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b>\u00105R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b?\u00105R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b@\u00105R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\bA\u00105R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\bB\u00105R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\bC\u00105R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010F\u001a\u0004\bD\u0010E¨\u0006k"}, d2 = {"Lorg/informatika/sirekap/api/response/GetTpsByEmailApiResponse$TpsByEmailApiResponse;", "", "idWilayah", "", "idWilayahParent", "idDapilNas", "idDapilProv", "idDapilKab", "idDapilDpd", "nama", "", "tingkat", "", "idProv", "idKab", "idKec", "idKel", "idTps", "namaProv", "namaKab", "namaKec", "namaKel", "noTps", "namaDapilNas", "namaDapilProv", "namaDapilKab", "namaDapilDpd", "kodeProv", "kodeKab", "kodeKec", "kodeKel", "kodeWilayah", "aceh", "", "(JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getAceh", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getIdDapilDpd", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getIdDapilKab", "getIdDapilNas", "getIdDapilProv", "getIdKab", "getIdKec", "getIdKel", "getIdProv", "getIdTps", "getIdWilayah", "()J", "getIdWilayahParent", "getKodeKab", "()Ljava/lang/String;", "getKodeKec", "getKodeKel", "getKodeProv", "getKodeWilayah", "getNama", "getNamaDapilDpd", "getNamaDapilKab", "getNamaDapilNas", "getNamaDapilProv", "getNamaKab", "getNamaKec", "getNamaKel", "getNamaProv", "getNoTps", "getTingkat", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lorg/informatika/sirekap/api/response/GetTpsByEmailApiResponse$TpsByEmailApiResponse;", "equals", "other", "hashCode", "toString", "toTPS", "Lorg/informatika/sirekap/model/Tps;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class TpsByEmailApiResponse {
        private final Boolean aceh;
        private final Long idDapilDpd;
        private final Long idDapilKab;
        private final Long idDapilNas;
        private final Long idDapilProv;
        private final Long idKab;
        private final Long idKec;
        private final Long idKel;
        private final Long idProv;
        private final Long idTps;
        private final long idWilayah;
        private final Long idWilayahParent;
        private final String kodeKab;
        private final String kodeKec;
        private final String kodeKel;
        private final String kodeProv;
        private final String kodeWilayah;
        private final String nama;
        private final String namaDapilDpd;
        private final String namaDapilKab;
        private final String namaDapilNas;
        private final String namaDapilProv;
        private final String namaKab;
        private final String namaKec;
        private final String namaKel;
        private final String namaProv;
        private final String noTps;
        private final Integer tingkat;

        public final long component1() {
            return this.idWilayah;
        }

        public final Long component10() {
            return this.idKab;
        }

        public final Long component11() {
            return this.idKec;
        }

        public final Long component12() {
            return this.idKel;
        }

        public final Long component13() {
            return this.idTps;
        }

        public final String component14() {
            return this.namaProv;
        }

        public final String component15() {
            return this.namaKab;
        }

        public final String component16() {
            return this.namaKec;
        }

        public final String component17() {
            return this.namaKel;
        }

        public final String component18() {
            return this.noTps;
        }

        public final String component19() {
            return this.namaDapilNas;
        }

        public final Long component2() {
            return this.idWilayahParent;
        }

        public final String component20() {
            return this.namaDapilProv;
        }

        public final String component21() {
            return this.namaDapilKab;
        }

        public final String component22() {
            return this.namaDapilDpd;
        }

        public final String component23() {
            return this.kodeProv;
        }

        public final String component24() {
            return this.kodeKab;
        }

        public final String component25() {
            return this.kodeKec;
        }

        public final String component26() {
            return this.kodeKel;
        }

        public final String component27() {
            return this.kodeWilayah;
        }

        public final Boolean component28() {
            return this.aceh;
        }

        public final Long component3() {
            return this.idDapilNas;
        }

        public final Long component4() {
            return this.idDapilProv;
        }

        public final Long component5() {
            return this.idDapilKab;
        }

        public final Long component6() {
            return this.idDapilDpd;
        }

        public final String component7() {
            return this.nama;
        }

        public final Integer component8() {
            return this.tingkat;
        }

        public final Long component9() {
            return this.idProv;
        }

        public final TpsByEmailApiResponse copy(long j, Long l, Long l2, Long l3, Long l4, Long l5, String nama, Integer num, Long l6, Long l7, Long l8, Long l9, Long l10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String kodeWilayah, Boolean bool) {
            Intrinsics.checkNotNullParameter(nama, "nama");
            Intrinsics.checkNotNullParameter(kodeWilayah, "kodeWilayah");
            return new TpsByEmailApiResponse(j, l, l2, l3, l4, l5, nama, num, l6, l7, l8, l9, l10, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, kodeWilayah, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TpsByEmailApiResponse) {
                TpsByEmailApiResponse tpsByEmailApiResponse = (TpsByEmailApiResponse) obj;
                return this.idWilayah == tpsByEmailApiResponse.idWilayah && Intrinsics.areEqual(this.idWilayahParent, tpsByEmailApiResponse.idWilayahParent) && Intrinsics.areEqual(this.idDapilNas, tpsByEmailApiResponse.idDapilNas) && Intrinsics.areEqual(this.idDapilProv, tpsByEmailApiResponse.idDapilProv) && Intrinsics.areEqual(this.idDapilKab, tpsByEmailApiResponse.idDapilKab) && Intrinsics.areEqual(this.idDapilDpd, tpsByEmailApiResponse.idDapilDpd) && Intrinsics.areEqual(this.nama, tpsByEmailApiResponse.nama) && Intrinsics.areEqual(this.tingkat, tpsByEmailApiResponse.tingkat) && Intrinsics.areEqual(this.idProv, tpsByEmailApiResponse.idProv) && Intrinsics.areEqual(this.idKab, tpsByEmailApiResponse.idKab) && Intrinsics.areEqual(this.idKec, tpsByEmailApiResponse.idKec) && Intrinsics.areEqual(this.idKel, tpsByEmailApiResponse.idKel) && Intrinsics.areEqual(this.idTps, tpsByEmailApiResponse.idTps) && Intrinsics.areEqual(this.namaProv, tpsByEmailApiResponse.namaProv) && Intrinsics.areEqual(this.namaKab, tpsByEmailApiResponse.namaKab) && Intrinsics.areEqual(this.namaKec, tpsByEmailApiResponse.namaKec) && Intrinsics.areEqual(this.namaKel, tpsByEmailApiResponse.namaKel) && Intrinsics.areEqual(this.noTps, tpsByEmailApiResponse.noTps) && Intrinsics.areEqual(this.namaDapilNas, tpsByEmailApiResponse.namaDapilNas) && Intrinsics.areEqual(this.namaDapilProv, tpsByEmailApiResponse.namaDapilProv) && Intrinsics.areEqual(this.namaDapilKab, tpsByEmailApiResponse.namaDapilKab) && Intrinsics.areEqual(this.namaDapilDpd, tpsByEmailApiResponse.namaDapilDpd) && Intrinsics.areEqual(this.kodeProv, tpsByEmailApiResponse.kodeProv) && Intrinsics.areEqual(this.kodeKab, tpsByEmailApiResponse.kodeKab) && Intrinsics.areEqual(this.kodeKec, tpsByEmailApiResponse.kodeKec) && Intrinsics.areEqual(this.kodeKel, tpsByEmailApiResponse.kodeKel) && Intrinsics.areEqual(this.kodeWilayah, tpsByEmailApiResponse.kodeWilayah) && Intrinsics.areEqual(this.aceh, tpsByEmailApiResponse.aceh);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = Long.hashCode(this.idWilayah) * 31;
            Long l = this.idWilayahParent;
            int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.idDapilNas;
            int hashCode3 = (hashCode2 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Long l3 = this.idDapilProv;
            int hashCode4 = (hashCode3 + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.idDapilKab;
            int hashCode5 = (hashCode4 + (l4 == null ? 0 : l4.hashCode())) * 31;
            Long l5 = this.idDapilDpd;
            int hashCode6 = (((hashCode5 + (l5 == null ? 0 : l5.hashCode())) * 31) + this.nama.hashCode()) * 31;
            Integer num = this.tingkat;
            int hashCode7 = (hashCode6 + (num == null ? 0 : num.hashCode())) * 31;
            Long l6 = this.idProv;
            int hashCode8 = (hashCode7 + (l6 == null ? 0 : l6.hashCode())) * 31;
            Long l7 = this.idKab;
            int hashCode9 = (hashCode8 + (l7 == null ? 0 : l7.hashCode())) * 31;
            Long l8 = this.idKec;
            int hashCode10 = (hashCode9 + (l8 == null ? 0 : l8.hashCode())) * 31;
            Long l9 = this.idKel;
            int hashCode11 = (hashCode10 + (l9 == null ? 0 : l9.hashCode())) * 31;
            Long l10 = this.idTps;
            int hashCode12 = (hashCode11 + (l10 == null ? 0 : l10.hashCode())) * 31;
            String str = this.namaProv;
            int hashCode13 = (hashCode12 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.namaKab;
            int hashCode14 = (hashCode13 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.namaKec;
            int hashCode15 = (hashCode14 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.namaKel;
            int hashCode16 = (hashCode15 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.noTps;
            int hashCode17 = (hashCode16 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.namaDapilNas;
            int hashCode18 = (hashCode17 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.namaDapilProv;
            int hashCode19 = (hashCode18 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.namaDapilKab;
            int hashCode20 = (hashCode19 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.namaDapilDpd;
            int hashCode21 = (hashCode20 + (str9 == null ? 0 : str9.hashCode())) * 31;
            String str10 = this.kodeProv;
            int hashCode22 = (hashCode21 + (str10 == null ? 0 : str10.hashCode())) * 31;
            String str11 = this.kodeKab;
            int hashCode23 = (hashCode22 + (str11 == null ? 0 : str11.hashCode())) * 31;
            String str12 = this.kodeKec;
            int hashCode24 = (hashCode23 + (str12 == null ? 0 : str12.hashCode())) * 31;
            String str13 = this.kodeKel;
            int hashCode25 = (((hashCode24 + (str13 == null ? 0 : str13.hashCode())) * 31) + this.kodeWilayah.hashCode()) * 31;
            Boolean bool = this.aceh;
            return hashCode25 + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            long j = this.idWilayah;
            Long l = this.idWilayahParent;
            Long l2 = this.idDapilNas;
            Long l3 = this.idDapilProv;
            Long l4 = this.idDapilKab;
            Long l5 = this.idDapilDpd;
            String str = this.nama;
            Integer num = this.tingkat;
            Long l6 = this.idProv;
            Long l7 = this.idKab;
            Long l8 = this.idKec;
            Long l9 = this.idKel;
            Long l10 = this.idTps;
            String str2 = this.namaProv;
            String str3 = this.namaKab;
            String str4 = this.namaKec;
            String str5 = this.namaKel;
            String str6 = this.noTps;
            String str7 = this.namaDapilNas;
            String str8 = this.namaDapilProv;
            String str9 = this.namaDapilKab;
            String str10 = this.namaDapilDpd;
            String str11 = this.kodeProv;
            String str12 = this.kodeKab;
            String str13 = this.kodeKec;
            String str14 = this.kodeKel;
            String str15 = this.kodeWilayah;
            return "TpsByEmailApiResponse(idWilayah=" + j + ", idWilayahParent=" + l + ", idDapilNas=" + l2 + ", idDapilProv=" + l3 + ", idDapilKab=" + l4 + ", idDapilDpd=" + l5 + ", nama=" + str + ", tingkat=" + num + ", idProv=" + l6 + ", idKab=" + l7 + ", idKec=" + l8 + ", idKel=" + l9 + ", idTps=" + l10 + ", namaProv=" + str2 + ", namaKab=" + str3 + ", namaKec=" + str4 + ", namaKel=" + str5 + ", noTps=" + str6 + ", namaDapilNas=" + str7 + ", namaDapilProv=" + str8 + ", namaDapilKab=" + str9 + ", namaDapilDpd=" + str10 + ", kodeProv=" + str11 + ", kodeKab=" + str12 + ", kodeKec=" + str13 + ", kodeKel=" + str14 + ", kodeWilayah=" + str15 + ", aceh=" + this.aceh + ")";
        }

        public TpsByEmailApiResponse(long j, Long l, Long l2, Long l3, Long l4, Long l5, String nama, Integer num, Long l6, Long l7, Long l8, Long l9, Long l10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String kodeWilayah, Boolean bool) {
            Intrinsics.checkNotNullParameter(nama, "nama");
            Intrinsics.checkNotNullParameter(kodeWilayah, "kodeWilayah");
            this.idWilayah = j;
            this.idWilayahParent = l;
            this.idDapilNas = l2;
            this.idDapilProv = l3;
            this.idDapilKab = l4;
            this.idDapilDpd = l5;
            this.nama = nama;
            this.tingkat = num;
            this.idProv = l6;
            this.idKab = l7;
            this.idKec = l8;
            this.idKel = l9;
            this.idTps = l10;
            this.namaProv = str;
            this.namaKab = str2;
            this.namaKec = str3;
            this.namaKel = str4;
            this.noTps = str5;
            this.namaDapilNas = str6;
            this.namaDapilProv = str7;
            this.namaDapilKab = str8;
            this.namaDapilDpd = str9;
            this.kodeProv = str10;
            this.kodeKab = str11;
            this.kodeKec = str12;
            this.kodeKel = str13;
            this.kodeWilayah = kodeWilayah;
            this.aceh = bool;
        }

        public final long getIdWilayah() {
            return this.idWilayah;
        }

        public final Long getIdWilayahParent() {
            return this.idWilayahParent;
        }

        public final Long getIdDapilNas() {
            return this.idDapilNas;
        }

        public final Long getIdDapilProv() {
            return this.idDapilProv;
        }

        public final Long getIdDapilKab() {
            return this.idDapilKab;
        }

        public final Long getIdDapilDpd() {
            return this.idDapilDpd;
        }

        public final String getNama() {
            return this.nama;
        }

        public final Integer getTingkat() {
            return this.tingkat;
        }

        public final Long getIdProv() {
            return this.idProv;
        }

        public final Long getIdKab() {
            return this.idKab;
        }

        public final Long getIdKec() {
            return this.idKec;
        }

        public final Long getIdKel() {
            return this.idKel;
        }

        public final Long getIdTps() {
            return this.idTps;
        }

        public final String getNamaProv() {
            return this.namaProv;
        }

        public final String getNamaKab() {
            return this.namaKab;
        }

        public final String getNamaKec() {
            return this.namaKec;
        }

        public final String getNamaKel() {
            return this.namaKel;
        }

        public final String getNoTps() {
            return this.noTps;
        }

        public final String getNamaDapilNas() {
            return this.namaDapilNas;
        }

        public final String getNamaDapilProv() {
            return this.namaDapilProv;
        }

        public final String getNamaDapilKab() {
            return this.namaDapilKab;
        }

        public final String getNamaDapilDpd() {
            return this.namaDapilDpd;
        }

        public final String getKodeProv() {
            return this.kodeProv;
        }

        public final String getKodeKab() {
            return this.kodeKab;
        }

        public final String getKodeKec() {
            return this.kodeKec;
        }

        public final String getKodeKel() {
            return this.kodeKel;
        }

        public final String getKodeWilayah() {
            return this.kodeWilayah;
        }

        public final Boolean getAceh() {
            return this.aceh;
        }

        public final Tps toTPS() {
            return new Tps(this.kodeWilayah, this.nama, new Kelurahan(this.kodeWilayah + "-kel", this.namaKel, new Kecamatan(this.kodeWilayah + "-kec", this.namaKec, new KotaKabupaten(this.kodeWilayah + "-kab", this.namaKab, new Provinsi(this.kodeWilayah + "-prov", this.namaProv)))));
        }
    }
}
