package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: WilayahApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\fB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse;)V", "getData", "()Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse;", "DataApiResponse", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WilayahApiResponse extends GenericApiResponse {
    private final DataApiResponse data;

    public final DataApiResponse getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WilayahApiResponse(boolean z, String message, String str, DataApiResponse data) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    /* compiled from: WilayahApiResponse.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001:\u000e/0123456789:;<B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r¢\u0006\u0002\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rHÆ\u0003Js\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015¨\u0006="}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse;", "", "detail", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$DetailApiResponse;", "pemilihanPpwp", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPpwpApiResponse;", "pemilihanPdpd", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdpdApiResponse;", "pemilihanPdpr", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdprPdprdApiResponse;", "pemilihanPdprdk", "pemilihanPdprdp", "petugas", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PetugasApiResponse;", "infoTpsList", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$InfoTpsResponse;", "(Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$DetailApiResponse;Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPpwpApiResponse;Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdpdApiResponse;Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdprPdprdApiResponse;Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdprPdprdApiResponse;Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdprPdprdApiResponse;Ljava/util/List;Ljava/util/List;)V", "getDetail", "()Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$DetailApiResponse;", "getInfoTpsList", "()Ljava/util/List;", "getPemilihanPdpd", "()Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdpdApiResponse;", "getPemilihanPdpr", "()Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdprPdprdApiResponse;", "getPemilihanPdprdk", "getPemilihanPdprdp", "getPemilihanPpwp", "()Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPpwpApiResponse;", "getPetugas", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DetailApiResponse", "ImageApiResponse", "ImageKesesuaianResponse", "ImagePayloadResponse", "InfoTpsResponse", "PemilihanPdpdApiResponse", "PemilihanPdprPdprdApiResponse", "PemilihanPpwpApiResponse", "PetugasApiResponse", "PetugasPemeriksaApiResponse", "PilihanNamaPdprPdprdApiResponse", "PilihanPdpdApiResponse", "PilihanPdprPdprdApiResponse", "PilihanPpwpApiResponse", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DataApiResponse {
        private final DetailApiResponse detail;
        private final List<InfoTpsResponse> infoTpsList;
        private final PemilihanPdpdApiResponse pemilihanPdpd;
        private final PemilihanPdprPdprdApiResponse pemilihanPdpr;
        private final PemilihanPdprPdprdApiResponse pemilihanPdprdk;
        private final PemilihanPdprPdprdApiResponse pemilihanPdprdp;
        private final PemilihanPpwpApiResponse pemilihanPpwp;
        private final List<PetugasApiResponse> petugas;

        public final DetailApiResponse component1() {
            return this.detail;
        }

        public final PemilihanPpwpApiResponse component2() {
            return this.pemilihanPpwp;
        }

        public final PemilihanPdpdApiResponse component3() {
            return this.pemilihanPdpd;
        }

        public final PemilihanPdprPdprdApiResponse component4() {
            return this.pemilihanPdpr;
        }

        public final PemilihanPdprPdprdApiResponse component5() {
            return this.pemilihanPdprdk;
        }

        public final PemilihanPdprPdprdApiResponse component6() {
            return this.pemilihanPdprdp;
        }

        public final List<PetugasApiResponse> component7() {
            return this.petugas;
        }

        public final List<InfoTpsResponse> component8() {
            return this.infoTpsList;
        }

        public final DataApiResponse copy(DetailApiResponse detail, PemilihanPpwpApiResponse pemilihanPpwpApiResponse, PemilihanPdpdApiResponse pemilihanPdpdApiResponse, PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse, PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse2, PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse3, List<PetugasApiResponse> list, List<InfoTpsResponse> list2) {
            Intrinsics.checkNotNullParameter(detail, "detail");
            return new DataApiResponse(detail, pemilihanPpwpApiResponse, pemilihanPdpdApiResponse, pemilihanPdprPdprdApiResponse, pemilihanPdprPdprdApiResponse2, pemilihanPdprPdprdApiResponse3, list, list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DataApiResponse) {
                DataApiResponse dataApiResponse = (DataApiResponse) obj;
                return Intrinsics.areEqual(this.detail, dataApiResponse.detail) && Intrinsics.areEqual(this.pemilihanPpwp, dataApiResponse.pemilihanPpwp) && Intrinsics.areEqual(this.pemilihanPdpd, dataApiResponse.pemilihanPdpd) && Intrinsics.areEqual(this.pemilihanPdpr, dataApiResponse.pemilihanPdpr) && Intrinsics.areEqual(this.pemilihanPdprdk, dataApiResponse.pemilihanPdprdk) && Intrinsics.areEqual(this.pemilihanPdprdp, dataApiResponse.pemilihanPdprdp) && Intrinsics.areEqual(this.petugas, dataApiResponse.petugas) && Intrinsics.areEqual(this.infoTpsList, dataApiResponse.infoTpsList);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.detail.hashCode() * 31;
            PemilihanPpwpApiResponse pemilihanPpwpApiResponse = this.pemilihanPpwp;
            int hashCode2 = (hashCode + (pemilihanPpwpApiResponse == null ? 0 : pemilihanPpwpApiResponse.hashCode())) * 31;
            PemilihanPdpdApiResponse pemilihanPdpdApiResponse = this.pemilihanPdpd;
            int hashCode3 = (hashCode2 + (pemilihanPdpdApiResponse == null ? 0 : pemilihanPdpdApiResponse.hashCode())) * 31;
            PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse = this.pemilihanPdpr;
            int hashCode4 = (hashCode3 + (pemilihanPdprPdprdApiResponse == null ? 0 : pemilihanPdprPdprdApiResponse.hashCode())) * 31;
            PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse2 = this.pemilihanPdprdk;
            int hashCode5 = (hashCode4 + (pemilihanPdprPdprdApiResponse2 == null ? 0 : pemilihanPdprPdprdApiResponse2.hashCode())) * 31;
            PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse3 = this.pemilihanPdprdp;
            int hashCode6 = (hashCode5 + (pemilihanPdprPdprdApiResponse3 == null ? 0 : pemilihanPdprPdprdApiResponse3.hashCode())) * 31;
            List<PetugasApiResponse> list = this.petugas;
            int hashCode7 = (hashCode6 + (list == null ? 0 : list.hashCode())) * 31;
            List<InfoTpsResponse> list2 = this.infoTpsList;
            return hashCode7 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            DetailApiResponse detailApiResponse = this.detail;
            PemilihanPpwpApiResponse pemilihanPpwpApiResponse = this.pemilihanPpwp;
            PemilihanPdpdApiResponse pemilihanPdpdApiResponse = this.pemilihanPdpd;
            PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse = this.pemilihanPdpr;
            PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse2 = this.pemilihanPdprdk;
            PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse3 = this.pemilihanPdprdp;
            List<PetugasApiResponse> list = this.petugas;
            return "DataApiResponse(detail=" + detailApiResponse + ", pemilihanPpwp=" + pemilihanPpwpApiResponse + ", pemilihanPdpd=" + pemilihanPdpdApiResponse + ", pemilihanPdpr=" + pemilihanPdprPdprdApiResponse + ", pemilihanPdprdk=" + pemilihanPdprPdprdApiResponse2 + ", pemilihanPdprdp=" + pemilihanPdprPdprdApiResponse3 + ", petugas=" + list + ", infoTpsList=" + this.infoTpsList + ")";
        }

        public DataApiResponse(DetailApiResponse detail, PemilihanPpwpApiResponse pemilihanPpwpApiResponse, PemilihanPdpdApiResponse pemilihanPdpdApiResponse, PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse, PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse2, PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse3, List<PetugasApiResponse> list, List<InfoTpsResponse> list2) {
            Intrinsics.checkNotNullParameter(detail, "detail");
            this.detail = detail;
            this.pemilihanPpwp = pemilihanPpwpApiResponse;
            this.pemilihanPdpd = pemilihanPdpdApiResponse;
            this.pemilihanPdpr = pemilihanPdprPdprdApiResponse;
            this.pemilihanPdprdk = pemilihanPdprPdprdApiResponse2;
            this.pemilihanPdprdp = pemilihanPdprPdprdApiResponse3;
            this.petugas = list;
            this.infoTpsList = list2;
        }

        public final DetailApiResponse getDetail() {
            return this.detail;
        }

        public final PemilihanPpwpApiResponse getPemilihanPpwp() {
            return this.pemilihanPpwp;
        }

        public final PemilihanPdpdApiResponse getPemilihanPdpd() {
            return this.pemilihanPdpd;
        }

        public final PemilihanPdprPdprdApiResponse getPemilihanPdpr() {
            return this.pemilihanPdpr;
        }

        public final PemilihanPdprPdprdApiResponse getPemilihanPdprdk() {
            return this.pemilihanPdprdk;
        }

        public final PemilihanPdprPdprdApiResponse getPemilihanPdprdp() {
            return this.pemilihanPdprdp;
        }

        public final List<PetugasApiResponse> getPetugas() {
            return this.petugas;
        }

        public final List<InfoTpsResponse> getInfoTpsList() {
            return this.infoTpsList;
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$InfoTpsResponse;", "", "jenisWaktu", "", "jenisPemilihan", "", "waktuMulai", "waktuSelesai", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getJenisPemilihan", "()Ljava/lang/String;", "getJenisWaktu", "()I", "getWaktuMulai", "getWaktuSelesai", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class InfoTpsResponse {
            private final String jenisPemilihan;
            private final int jenisWaktu;
            private final String waktuMulai;
            private final String waktuSelesai;

            public static /* synthetic */ InfoTpsResponse copy$default(InfoTpsResponse infoTpsResponse, int i, String str, String str2, String str3, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = infoTpsResponse.jenisWaktu;
                }
                if ((i2 & 2) != 0) {
                    str = infoTpsResponse.jenisPemilihan;
                }
                if ((i2 & 4) != 0) {
                    str2 = infoTpsResponse.waktuMulai;
                }
                if ((i2 & 8) != 0) {
                    str3 = infoTpsResponse.waktuSelesai;
                }
                return infoTpsResponse.copy(i, str, str2, str3);
            }

            public final int component1() {
                return this.jenisWaktu;
            }

            public final String component2() {
                return this.jenisPemilihan;
            }

            public final String component3() {
                return this.waktuMulai;
            }

            public final String component4() {
                return this.waktuSelesai;
            }

            public final InfoTpsResponse copy(int i, String jenisPemilihan, String waktuMulai, String waktuSelesai) {
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(waktuMulai, "waktuMulai");
                Intrinsics.checkNotNullParameter(waktuSelesai, "waktuSelesai");
                return new InfoTpsResponse(i, jenisPemilihan, waktuMulai, waktuSelesai);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof InfoTpsResponse) {
                    InfoTpsResponse infoTpsResponse = (InfoTpsResponse) obj;
                    return this.jenisWaktu == infoTpsResponse.jenisWaktu && Intrinsics.areEqual(this.jenisPemilihan, infoTpsResponse.jenisPemilihan) && Intrinsics.areEqual(this.waktuMulai, infoTpsResponse.waktuMulai) && Intrinsics.areEqual(this.waktuSelesai, infoTpsResponse.waktuSelesai);
                }
                return false;
            }

            public int hashCode() {
                return (((((Integer.hashCode(this.jenisWaktu) * 31) + this.jenisPemilihan.hashCode()) * 31) + this.waktuMulai.hashCode()) * 31) + this.waktuSelesai.hashCode();
            }

            public String toString() {
                int i = this.jenisWaktu;
                String str = this.jenisPemilihan;
                String str2 = this.waktuMulai;
                return "InfoTpsResponse(jenisWaktu=" + i + ", jenisPemilihan=" + str + ", waktuMulai=" + str2 + ", waktuSelesai=" + this.waktuSelesai + ")";
            }

            public InfoTpsResponse(int i, String jenisPemilihan, String waktuMulai, String waktuSelesai) {
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(waktuMulai, "waktuMulai");
                Intrinsics.checkNotNullParameter(waktuSelesai, "waktuSelesai");
                this.jenisWaktu = i;
                this.jenisPemilihan = jenisPemilihan;
                this.waktuMulai = waktuMulai;
                this.waktuSelesai = waktuSelesai;
            }

            public final int getJenisWaktu() {
                return this.jenisWaktu;
            }

            public final String getJenisPemilihan() {
                return this.jenisPemilihan;
            }

            public final String getWaktuMulai() {
                return this.waktuMulai;
            }

            public final String getWaktuSelesai() {
                return this.waktuSelesai;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003Jt\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016¨\u0006/"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PetugasApiResponse;", "", "idPetugas", "", "kodeTps", "", "jenisPemeriksa", "idPilihan", "urutan", "nama", "noHandphone", "nik", "pemeriksas", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PetugasPemeriksaApiResponse;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getIdPetugas", "()J", "getIdPilihan", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getJenisPemeriksa", "()Ljava/lang/String;", "getKodeTps", "getNama", "getNik", "getNoHandphone", "getPemeriksas", "()Ljava/util/List;", "getUrutan", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PetugasApiResponse;", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PetugasApiResponse {
            private final long idPetugas;
            private final Long idPilihan;
            private final String jenisPemeriksa;
            private final String kodeTps;
            private final String nama;
            private final String nik;
            private final String noHandphone;
            private final List<PetugasPemeriksaApiResponse> pemeriksas;
            private final String urutan;

            public final long component1() {
                return this.idPetugas;
            }

            public final String component2() {
                return this.kodeTps;
            }

            public final String component3() {
                return this.jenisPemeriksa;
            }

            public final Long component4() {
                return this.idPilihan;
            }

            public final String component5() {
                return this.urutan;
            }

            public final String component6() {
                return this.nama;
            }

            public final String component7() {
                return this.noHandphone;
            }

            public final String component8() {
                return this.nik;
            }

            public final List<PetugasPemeriksaApiResponse> component9() {
                return this.pemeriksas;
            }

            public final PetugasApiResponse copy(long j, String kodeTps, String jenisPemeriksa, Long l, String urutan, String nama, String str, String nik, List<PetugasPemeriksaApiResponse> list) {
                Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
                Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
                Intrinsics.checkNotNullParameter(urutan, "urutan");
                Intrinsics.checkNotNullParameter(nama, "nama");
                Intrinsics.checkNotNullParameter(nik, "nik");
                return new PetugasApiResponse(j, kodeTps, jenisPemeriksa, l, urutan, nama, str, nik, list);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PetugasApiResponse) {
                    PetugasApiResponse petugasApiResponse = (PetugasApiResponse) obj;
                    return this.idPetugas == petugasApiResponse.idPetugas && Intrinsics.areEqual(this.kodeTps, petugasApiResponse.kodeTps) && Intrinsics.areEqual(this.jenisPemeriksa, petugasApiResponse.jenisPemeriksa) && Intrinsics.areEqual(this.idPilihan, petugasApiResponse.idPilihan) && Intrinsics.areEqual(this.urutan, petugasApiResponse.urutan) && Intrinsics.areEqual(this.nama, petugasApiResponse.nama) && Intrinsics.areEqual(this.noHandphone, petugasApiResponse.noHandphone) && Intrinsics.areEqual(this.nik, petugasApiResponse.nik) && Intrinsics.areEqual(this.pemeriksas, petugasApiResponse.pemeriksas);
                }
                return false;
            }

            public int hashCode() {
                int hashCode = ((((Long.hashCode(this.idPetugas) * 31) + this.kodeTps.hashCode()) * 31) + this.jenisPemeriksa.hashCode()) * 31;
                Long l = this.idPilihan;
                int hashCode2 = (((((hashCode + (l == null ? 0 : l.hashCode())) * 31) + this.urutan.hashCode()) * 31) + this.nama.hashCode()) * 31;
                String str = this.noHandphone;
                int hashCode3 = (((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + this.nik.hashCode()) * 31;
                List<PetugasPemeriksaApiResponse> list = this.pemeriksas;
                return hashCode3 + (list != null ? list.hashCode() : 0);
            }

            public String toString() {
                long j = this.idPetugas;
                String str = this.kodeTps;
                String str2 = this.jenisPemeriksa;
                Long l = this.idPilihan;
                String str3 = this.urutan;
                String str4 = this.nama;
                String str5 = this.noHandphone;
                String str6 = this.nik;
                return "PetugasApiResponse(idPetugas=" + j + ", kodeTps=" + str + ", jenisPemeriksa=" + str2 + ", idPilihan=" + l + ", urutan=" + str3 + ", nama=" + str4 + ", noHandphone=" + str5 + ", nik=" + str6 + ", pemeriksas=" + this.pemeriksas + ")";
            }

            public PetugasApiResponse(long j, String kodeTps, String jenisPemeriksa, Long l, String urutan, String nama, String str, String nik, List<PetugasPemeriksaApiResponse> list) {
                Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
                Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
                Intrinsics.checkNotNullParameter(urutan, "urutan");
                Intrinsics.checkNotNullParameter(nama, "nama");
                Intrinsics.checkNotNullParameter(nik, "nik");
                this.idPetugas = j;
                this.kodeTps = kodeTps;
                this.jenisPemeriksa = jenisPemeriksa;
                this.idPilihan = l;
                this.urutan = urutan;
                this.nama = nama;
                this.noHandphone = str;
                this.nik = nik;
                this.pemeriksas = list;
            }

            public final long getIdPetugas() {
                return this.idPetugas;
            }

            public final String getKodeTps() {
                return this.kodeTps;
            }

            public final String getJenisPemeriksa() {
                return this.jenisPemeriksa;
            }

            public final Long getIdPilihan() {
                return this.idPilihan;
            }

            public final String getUrutan() {
                return this.urutan;
            }

            public final String getNama() {
                return this.nama;
            }

            public final String getNoHandphone() {
                return this.noHandphone;
            }

            public final String getNik() {
                return this.nik;
            }

            public final List<PetugasPemeriksaApiResponse> getPemeriksas() {
                return this.pemeriksas;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PetugasPemeriksaApiResponse;", "", "jenisPemilihan", "", "url", "idPilihan", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getIdPilihan", "()J", "getJenisPemilihan", "()Ljava/lang/String;", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PetugasPemeriksaApiResponse {
            private final long idPilihan;
            private final String jenisPemilihan;
            private final String url;

            public static /* synthetic */ PetugasPemeriksaApiResponse copy$default(PetugasPemeriksaApiResponse petugasPemeriksaApiResponse, String str, String str2, long j, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = petugasPemeriksaApiResponse.jenisPemilihan;
                }
                if ((i & 2) != 0) {
                    str2 = petugasPemeriksaApiResponse.url;
                }
                if ((i & 4) != 0) {
                    j = petugasPemeriksaApiResponse.idPilihan;
                }
                return petugasPemeriksaApiResponse.copy(str, str2, j);
            }

            public final String component1() {
                return this.jenisPemilihan;
            }

            public final String component2() {
                return this.url;
            }

            public final long component3() {
                return this.idPilihan;
            }

            public final PetugasPemeriksaApiResponse copy(String jenisPemilihan, String url, long j) {
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(url, "url");
                return new PetugasPemeriksaApiResponse(jenisPemilihan, url, j);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PetugasPemeriksaApiResponse) {
                    PetugasPemeriksaApiResponse petugasPemeriksaApiResponse = (PetugasPemeriksaApiResponse) obj;
                    return Intrinsics.areEqual(this.jenisPemilihan, petugasPemeriksaApiResponse.jenisPemilihan) && Intrinsics.areEqual(this.url, petugasPemeriksaApiResponse.url) && this.idPilihan == petugasPemeriksaApiResponse.idPilihan;
                }
                return false;
            }

            public int hashCode() {
                return (((this.jenisPemilihan.hashCode() * 31) + this.url.hashCode()) * 31) + Long.hashCode(this.idPilihan);
            }

            public String toString() {
                String str = this.jenisPemilihan;
                String str2 = this.url;
                return "PetugasPemeriksaApiResponse(jenisPemilihan=" + str + ", url=" + str2 + ", idPilihan=" + this.idPilihan + ")";
            }

            public PetugasPemeriksaApiResponse(String jenisPemilihan, String url, long j) {
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(url, "url");
                this.jenisPemilihan = jenisPemilihan;
                this.url = url;
                this.idPilihan = j;
            }

            public final String getJenisPemilihan() {
                return this.jenisPemilihan;
            }

            public final String getUrl() {
                return this.url;
            }

            public final long getIdPilihan() {
                return this.idPilihan;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r¢\u0006\u0002\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rHÆ\u0003Js\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0007HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013¨\u0006."}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageApiResponse;", "", "idImage", "", "uuid", "", "jenisImage", "", "jenisPemilihan", "namaFile", "noLembar", "filehash", "imagePayloads", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImagePayloadResponse;", "imageKesesuaians", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageKesesuaianResponse;", "(JLjava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getFilehash", "()Ljava/lang/String;", "getIdImage", "()J", "getImageKesesuaians", "()Ljava/util/List;", "getImagePayloads", "getJenisImage", "()I", "getJenisPemilihan", "getNamaFile", "getNoLembar", "getUuid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class ImageApiResponse {
            private final String filehash;
            private final long idImage;
            private final List<ImageKesesuaianResponse> imageKesesuaians;
            private final List<ImagePayloadResponse> imagePayloads;
            private final int jenisImage;
            private final String jenisPemilihan;
            private final String namaFile;
            private final int noLembar;
            private final String uuid;

            public final long component1() {
                return this.idImage;
            }

            public final String component2() {
                return this.uuid;
            }

            public final int component3() {
                return this.jenisImage;
            }

            public final String component4() {
                return this.jenisPemilihan;
            }

            public final String component5() {
                return this.namaFile;
            }

            public final int component6() {
                return this.noLembar;
            }

            public final String component7() {
                return this.filehash;
            }

            public final List<ImagePayloadResponse> component8() {
                return this.imagePayloads;
            }

            public final List<ImageKesesuaianResponse> component9() {
                return this.imageKesesuaians;
            }

            public final ImageApiResponse copy(long j, String uuid, int i, String jenisPemilihan, String namaFile, int i2, String filehash, List<ImagePayloadResponse> list, List<ImageKesesuaianResponse> list2) {
                Intrinsics.checkNotNullParameter(uuid, "uuid");
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(namaFile, "namaFile");
                Intrinsics.checkNotNullParameter(filehash, "filehash");
                return new ImageApiResponse(j, uuid, i, jenisPemilihan, namaFile, i2, filehash, list, list2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof ImageApiResponse) {
                    ImageApiResponse imageApiResponse = (ImageApiResponse) obj;
                    return this.idImage == imageApiResponse.idImage && Intrinsics.areEqual(this.uuid, imageApiResponse.uuid) && this.jenisImage == imageApiResponse.jenisImage && Intrinsics.areEqual(this.jenisPemilihan, imageApiResponse.jenisPemilihan) && Intrinsics.areEqual(this.namaFile, imageApiResponse.namaFile) && this.noLembar == imageApiResponse.noLembar && Intrinsics.areEqual(this.filehash, imageApiResponse.filehash) && Intrinsics.areEqual(this.imagePayloads, imageApiResponse.imagePayloads) && Intrinsics.areEqual(this.imageKesesuaians, imageApiResponse.imageKesesuaians);
                }
                return false;
            }

            public int hashCode() {
                int hashCode = ((((((((((((Long.hashCode(this.idImage) * 31) + this.uuid.hashCode()) * 31) + Integer.hashCode(this.jenisImage)) * 31) + this.jenisPemilihan.hashCode()) * 31) + this.namaFile.hashCode()) * 31) + Integer.hashCode(this.noLembar)) * 31) + this.filehash.hashCode()) * 31;
                List<ImagePayloadResponse> list = this.imagePayloads;
                int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
                List<ImageKesesuaianResponse> list2 = this.imageKesesuaians;
                return hashCode2 + (list2 != null ? list2.hashCode() : 0);
            }

            public String toString() {
                long j = this.idImage;
                String str = this.uuid;
                int i = this.jenisImage;
                String str2 = this.jenisPemilihan;
                String str3 = this.namaFile;
                int i2 = this.noLembar;
                String str4 = this.filehash;
                List<ImagePayloadResponse> list = this.imagePayloads;
                return "ImageApiResponse(idImage=" + j + ", uuid=" + str + ", jenisImage=" + i + ", jenisPemilihan=" + str2 + ", namaFile=" + str3 + ", noLembar=" + i2 + ", filehash=" + str4 + ", imagePayloads=" + list + ", imageKesesuaians=" + this.imageKesesuaians + ")";
            }

            public ImageApiResponse(long j, String uuid, int i, String jenisPemilihan, String namaFile, int i2, String filehash, List<ImagePayloadResponse> list, List<ImageKesesuaianResponse> list2) {
                Intrinsics.checkNotNullParameter(uuid, "uuid");
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(namaFile, "namaFile");
                Intrinsics.checkNotNullParameter(filehash, "filehash");
                this.idImage = j;
                this.uuid = uuid;
                this.jenisImage = i;
                this.jenisPemilihan = jenisPemilihan;
                this.namaFile = namaFile;
                this.noLembar = i2;
                this.filehash = filehash;
                this.imagePayloads = list;
                this.imageKesesuaians = list2;
            }

            public final long getIdImage() {
                return this.idImage;
            }

            public final String getUuid() {
                return this.uuid;
            }

            public final int getJenisImage() {
                return this.jenisImage;
            }

            public final String getJenisPemilihan() {
                return this.jenisPemilihan;
            }

            public final String getNamaFile() {
                return this.namaFile;
            }

            public final int getNoLembar() {
                return this.noLembar;
            }

            public final String getFilehash() {
                return this.filehash;
            }

            public final List<ImagePayloadResponse> getImagePayloads() {
                return this.imagePayloads;
            }

            public final List<ImageKesesuaianResponse> getImageKesesuaians() {
                return this.imageKesesuaians;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImagePayloadResponse;", "", "payload", "", "(Ljava/lang/String;)V", "getPayload", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class ImagePayloadResponse {
            private final String payload;

            public static /* synthetic */ ImagePayloadResponse copy$default(ImagePayloadResponse imagePayloadResponse, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = imagePayloadResponse.payload;
                }
                return imagePayloadResponse.copy(str);
            }

            public final String component1() {
                return this.payload;
            }

            public final ImagePayloadResponse copy(String str) {
                return new ImagePayloadResponse(str);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ImagePayloadResponse) && Intrinsics.areEqual(this.payload, ((ImagePayloadResponse) obj).payload);
            }

            public int hashCode() {
                String str = this.payload;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            public String toString() {
                return "ImagePayloadResponse(payload=" + this.payload + ")";
            }

            public ImagePayloadResponse(String str) {
                this.payload = str;
            }

            public final String getPayload() {
                return this.payload;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007HÆ\u0003JC\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\tHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageKesesuaianResponse;", "", "komentar", "", "isSesuai", "", "isSesuaiPerItem", "", "koreksiPerItem", "", "(Ljava/lang/String;ZLjava/util/List;Ljava/util/List;)V", "()Z", "()Ljava/util/List;", "getKomentar", "()Ljava/lang/String;", "getKoreksiPerItem", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class ImageKesesuaianResponse {
            private final boolean isSesuai;
            private final List<Boolean> isSesuaiPerItem;
            private final String komentar;
            private final List<Integer> koreksiPerItem;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ ImageKesesuaianResponse copy$default(ImageKesesuaianResponse imageKesesuaianResponse, String str, boolean z, List list, List list2, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = imageKesesuaianResponse.komentar;
                }
                if ((i & 2) != 0) {
                    z = imageKesesuaianResponse.isSesuai;
                }
                if ((i & 4) != 0) {
                    list = imageKesesuaianResponse.isSesuaiPerItem;
                }
                if ((i & 8) != 0) {
                    list2 = imageKesesuaianResponse.koreksiPerItem;
                }
                return imageKesesuaianResponse.copy(str, z, list, list2);
            }

            public final String component1() {
                return this.komentar;
            }

            public final boolean component2() {
                return this.isSesuai;
            }

            public final List<Boolean> component3() {
                return this.isSesuaiPerItem;
            }

            public final List<Integer> component4() {
                return this.koreksiPerItem;
            }

            public final ImageKesesuaianResponse copy(String str, boolean z, List<Boolean> list, List<Integer> list2) {
                return new ImageKesesuaianResponse(str, z, list, list2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof ImageKesesuaianResponse) {
                    ImageKesesuaianResponse imageKesesuaianResponse = (ImageKesesuaianResponse) obj;
                    return Intrinsics.areEqual(this.komentar, imageKesesuaianResponse.komentar) && this.isSesuai == imageKesesuaianResponse.isSesuai && Intrinsics.areEqual(this.isSesuaiPerItem, imageKesesuaianResponse.isSesuaiPerItem) && Intrinsics.areEqual(this.koreksiPerItem, imageKesesuaianResponse.koreksiPerItem);
                }
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public int hashCode() {
                String str = this.komentar;
                int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                boolean z = this.isSesuai;
                int i = z;
                if (z != 0) {
                    i = 1;
                }
                int i2 = (hashCode + i) * 31;
                List<Boolean> list = this.isSesuaiPerItem;
                int hashCode2 = (i2 + (list == null ? 0 : list.hashCode())) * 31;
                List<Integer> list2 = this.koreksiPerItem;
                return hashCode2 + (list2 != null ? list2.hashCode() : 0);
            }

            public String toString() {
                String str = this.komentar;
                boolean z = this.isSesuai;
                List<Boolean> list = this.isSesuaiPerItem;
                return "ImageKesesuaianResponse(komentar=" + str + ", isSesuai=" + z + ", isSesuaiPerItem=" + list + ", koreksiPerItem=" + this.koreksiPerItem + ")";
            }

            public ImageKesesuaianResponse(String str, boolean z, List<Boolean> list, List<Integer> list2) {
                this.komentar = str;
                this.isSesuai = z;
                this.isSesuaiPerItem = list;
                this.koreksiPerItem = list2;
            }

            public final String getKomentar() {
                return this.komentar;
            }

            public final boolean isSesuai() {
                return this.isSesuai;
            }

            public final List<Boolean> isSesuaiPerItem() {
                return this.isSesuaiPerItem;
            }

            public final List<Integer> getKoreksiPerItem() {
                return this.koreksiPerItem;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$DetailApiResponse;", "", "nama", "", "namaProv", "namaKab", "namaKec", "namaKel", "noTps", "kodeTps", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getKodeTps", "()Ljava/lang/String;", "getNama", "getNamaKab", "getNamaKec", "getNamaKel", "getNamaProv", "getNoTps", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class DetailApiResponse {
            private final String kodeTps;
            private final String nama;
            private final String namaKab;
            private final String namaKec;
            private final String namaKel;
            private final String namaProv;
            private final String noTps;

            public static /* synthetic */ DetailApiResponse copy$default(DetailApiResponse detailApiResponse, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = detailApiResponse.nama;
                }
                if ((i & 2) != 0) {
                    str2 = detailApiResponse.namaProv;
                }
                String str8 = str2;
                if ((i & 4) != 0) {
                    str3 = detailApiResponse.namaKab;
                }
                String str9 = str3;
                if ((i & 8) != 0) {
                    str4 = detailApiResponse.namaKec;
                }
                String str10 = str4;
                if ((i & 16) != 0) {
                    str5 = detailApiResponse.namaKel;
                }
                String str11 = str5;
                if ((i & 32) != 0) {
                    str6 = detailApiResponse.noTps;
                }
                String str12 = str6;
                if ((i & 64) != 0) {
                    str7 = detailApiResponse.kodeTps;
                }
                return detailApiResponse.copy(str, str8, str9, str10, str11, str12, str7);
            }

            public final String component1() {
                return this.nama;
            }

            public final String component2() {
                return this.namaProv;
            }

            public final String component3() {
                return this.namaKab;
            }

            public final String component4() {
                return this.namaKec;
            }

            public final String component5() {
                return this.namaKel;
            }

            public final String component6() {
                return this.noTps;
            }

            public final String component7() {
                return this.kodeTps;
            }

            public final DetailApiResponse copy(String nama, String namaProv, String namaKab, String namaKec, String namaKel, String noTps, String kodeTps) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                Intrinsics.checkNotNullParameter(namaProv, "namaProv");
                Intrinsics.checkNotNullParameter(namaKab, "namaKab");
                Intrinsics.checkNotNullParameter(namaKec, "namaKec");
                Intrinsics.checkNotNullParameter(namaKel, "namaKel");
                Intrinsics.checkNotNullParameter(noTps, "noTps");
                Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
                return new DetailApiResponse(nama, namaProv, namaKab, namaKec, namaKel, noTps, kodeTps);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof DetailApiResponse) {
                    DetailApiResponse detailApiResponse = (DetailApiResponse) obj;
                    return Intrinsics.areEqual(this.nama, detailApiResponse.nama) && Intrinsics.areEqual(this.namaProv, detailApiResponse.namaProv) && Intrinsics.areEqual(this.namaKab, detailApiResponse.namaKab) && Intrinsics.areEqual(this.namaKec, detailApiResponse.namaKec) && Intrinsics.areEqual(this.namaKel, detailApiResponse.namaKel) && Intrinsics.areEqual(this.noTps, detailApiResponse.noTps) && Intrinsics.areEqual(this.kodeTps, detailApiResponse.kodeTps);
                }
                return false;
            }

            public int hashCode() {
                return (((((((((((this.nama.hashCode() * 31) + this.namaProv.hashCode()) * 31) + this.namaKab.hashCode()) * 31) + this.namaKec.hashCode()) * 31) + this.namaKel.hashCode()) * 31) + this.noTps.hashCode()) * 31) + this.kodeTps.hashCode();
            }

            public String toString() {
                String str = this.nama;
                String str2 = this.namaProv;
                String str3 = this.namaKab;
                String str4 = this.namaKec;
                String str5 = this.namaKel;
                String str6 = this.noTps;
                return "DetailApiResponse(nama=" + str + ", namaProv=" + str2 + ", namaKab=" + str3 + ", namaKec=" + str4 + ", namaKel=" + str5 + ", noTps=" + str6 + ", kodeTps=" + this.kodeTps + ")";
            }

            public DetailApiResponse(String nama, String namaProv, String namaKab, String namaKec, String namaKel, String noTps, String kodeTps) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                Intrinsics.checkNotNullParameter(namaProv, "namaProv");
                Intrinsics.checkNotNullParameter(namaKab, "namaKab");
                Intrinsics.checkNotNullParameter(namaKec, "namaKec");
                Intrinsics.checkNotNullParameter(namaKel, "namaKel");
                Intrinsics.checkNotNullParameter(noTps, "noTps");
                Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
                this.nama = nama;
                this.namaProv = namaProv;
                this.namaKab = namaKab;
                this.namaKec = namaKec;
                this.namaKel = namaKel;
                this.noTps = noTps;
                this.kodeTps = kodeTps;
            }

            public final String getNama() {
                return this.nama;
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

            public final String getKodeTps() {
                return this.kodeTps;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0003J7\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPpwpApiResponse;", "", "jmlLembar", "", "pilihan", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanPpwpApiResponse;", "images", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageApiResponse;", "(ILjava/util/List;Ljava/util/List;)V", "getImages", "()Ljava/util/List;", "getJmlLembar", "()I", "getPilihan", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PemilihanPpwpApiResponse {
            private final List<ImageApiResponse> images;
            private final int jmlLembar;
            private final List<PilihanPpwpApiResponse> pilihan;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ PemilihanPpwpApiResponse copy$default(PemilihanPpwpApiResponse pemilihanPpwpApiResponse, int i, List list, List list2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = pemilihanPpwpApiResponse.jmlLembar;
                }
                if ((i2 & 2) != 0) {
                    list = pemilihanPpwpApiResponse.pilihan;
                }
                if ((i2 & 4) != 0) {
                    list2 = pemilihanPpwpApiResponse.images;
                }
                return pemilihanPpwpApiResponse.copy(i, list, list2);
            }

            public final int component1() {
                return this.jmlLembar;
            }

            public final List<PilihanPpwpApiResponse> component2() {
                return this.pilihan;
            }

            public final List<ImageApiResponse> component3() {
                return this.images;
            }

            public final PemilihanPpwpApiResponse copy(int i, List<PilihanPpwpApiResponse> list, List<ImageApiResponse> list2) {
                return new PemilihanPpwpApiResponse(i, list, list2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PemilihanPpwpApiResponse) {
                    PemilihanPpwpApiResponse pemilihanPpwpApiResponse = (PemilihanPpwpApiResponse) obj;
                    return this.jmlLembar == pemilihanPpwpApiResponse.jmlLembar && Intrinsics.areEqual(this.pilihan, pemilihanPpwpApiResponse.pilihan) && Intrinsics.areEqual(this.images, pemilihanPpwpApiResponse.images);
                }
                return false;
            }

            public int hashCode() {
                int hashCode = Integer.hashCode(this.jmlLembar) * 31;
                List<PilihanPpwpApiResponse> list = this.pilihan;
                int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
                List<ImageApiResponse> list2 = this.images;
                return hashCode2 + (list2 != null ? list2.hashCode() : 0);
            }

            public String toString() {
                int i = this.jmlLembar;
                List<PilihanPpwpApiResponse> list = this.pilihan;
                return "PemilihanPpwpApiResponse(jmlLembar=" + i + ", pilihan=" + list + ", images=" + this.images + ")";
            }

            public PemilihanPpwpApiResponse(int i, List<PilihanPpwpApiResponse> list, List<ImageApiResponse> list2) {
                this.jmlLembar = i;
                this.pilihan = list;
                this.images = list2;
            }

            public final int getJmlLembar() {
                return this.jmlLembar;
            }

            public final List<PilihanPpwpApiResponse> getPilihan() {
                return this.pilihan;
            }

            public final List<ImageApiResponse> getImages() {
                return this.images;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdpdApiResponse;", "", "jmlLembar", "", "dapil", "", "pilihan", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanPdpdApiResponse;", "images", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageApiResponse;", "(ILjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getDapil", "()Ljava/lang/String;", "getImages", "()Ljava/util/List;", "getJmlLembar", "()I", "getPilihan", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PemilihanPdpdApiResponse {
            private final String dapil;
            private final List<ImageApiResponse> images;
            private final int jmlLembar;
            private final List<PilihanPdpdApiResponse> pilihan;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ PemilihanPdpdApiResponse copy$default(PemilihanPdpdApiResponse pemilihanPdpdApiResponse, int i, String str, List list, List list2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = pemilihanPdpdApiResponse.jmlLembar;
                }
                if ((i2 & 2) != 0) {
                    str = pemilihanPdpdApiResponse.dapil;
                }
                if ((i2 & 4) != 0) {
                    list = pemilihanPdpdApiResponse.pilihan;
                }
                if ((i2 & 8) != 0) {
                    list2 = pemilihanPdpdApiResponse.images;
                }
                return pemilihanPdpdApiResponse.copy(i, str, list, list2);
            }

            public final int component1() {
                return this.jmlLembar;
            }

            public final String component2() {
                return this.dapil;
            }

            public final List<PilihanPdpdApiResponse> component3() {
                return this.pilihan;
            }

            public final List<ImageApiResponse> component4() {
                return this.images;
            }

            public final PemilihanPdpdApiResponse copy(int i, String dapil, List<PilihanPdpdApiResponse> list, List<ImageApiResponse> list2) {
                Intrinsics.checkNotNullParameter(dapil, "dapil");
                return new PemilihanPdpdApiResponse(i, dapil, list, list2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PemilihanPdpdApiResponse) {
                    PemilihanPdpdApiResponse pemilihanPdpdApiResponse = (PemilihanPdpdApiResponse) obj;
                    return this.jmlLembar == pemilihanPdpdApiResponse.jmlLembar && Intrinsics.areEqual(this.dapil, pemilihanPdpdApiResponse.dapil) && Intrinsics.areEqual(this.pilihan, pemilihanPdpdApiResponse.pilihan) && Intrinsics.areEqual(this.images, pemilihanPdpdApiResponse.images);
                }
                return false;
            }

            public int hashCode() {
                int hashCode = ((Integer.hashCode(this.jmlLembar) * 31) + this.dapil.hashCode()) * 31;
                List<PilihanPdpdApiResponse> list = this.pilihan;
                int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
                List<ImageApiResponse> list2 = this.images;
                return hashCode2 + (list2 != null ? list2.hashCode() : 0);
            }

            public String toString() {
                int i = this.jmlLembar;
                String str = this.dapil;
                List<PilihanPdpdApiResponse> list = this.pilihan;
                return "PemilihanPdpdApiResponse(jmlLembar=" + i + ", dapil=" + str + ", pilihan=" + list + ", images=" + this.images + ")";
            }

            public PemilihanPdpdApiResponse(int i, String dapil, List<PilihanPdpdApiResponse> list, List<ImageApiResponse> list2) {
                Intrinsics.checkNotNullParameter(dapil, "dapil");
                this.jmlLembar = i;
                this.dapil = dapil;
                this.pilihan = list;
                this.images = list2;
            }

            public final int getJmlLembar() {
                return this.jmlLembar;
            }

            public final String getDapil() {
                return this.dapil;
            }

            public final List<PilihanPdpdApiResponse> getPilihan() {
                return this.pilihan;
            }

            public final List<ImageApiResponse> getImages() {
                return this.images;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PemilihanPdprPdprdApiResponse;", "", "jmlLembar", "", "dapil", "", "pilihan", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanPdprPdprdApiResponse;", "images", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageApiResponse;", "(ILjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getDapil", "()Ljava/lang/String;", "getImages", "()Ljava/util/List;", "getJmlLembar", "()I", "getPilihan", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PemilihanPdprPdprdApiResponse {
            private final String dapil;
            private final List<ImageApiResponse> images;
            private final int jmlLembar;
            private final List<PilihanPdprPdprdApiResponse> pilihan;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ PemilihanPdprPdprdApiResponse copy$default(PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse, int i, String str, List list, List list2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = pemilihanPdprPdprdApiResponse.jmlLembar;
                }
                if ((i2 & 2) != 0) {
                    str = pemilihanPdprPdprdApiResponse.dapil;
                }
                if ((i2 & 4) != 0) {
                    list = pemilihanPdprPdprdApiResponse.pilihan;
                }
                if ((i2 & 8) != 0) {
                    list2 = pemilihanPdprPdprdApiResponse.images;
                }
                return pemilihanPdprPdprdApiResponse.copy(i, str, list, list2);
            }

            public final int component1() {
                return this.jmlLembar;
            }

            public final String component2() {
                return this.dapil;
            }

            public final List<PilihanPdprPdprdApiResponse> component3() {
                return this.pilihan;
            }

            public final List<ImageApiResponse> component4() {
                return this.images;
            }

            public final PemilihanPdprPdprdApiResponse copy(int i, String dapil, List<PilihanPdprPdprdApiResponse> list, List<ImageApiResponse> list2) {
                Intrinsics.checkNotNullParameter(dapil, "dapil");
                return new PemilihanPdprPdprdApiResponse(i, dapil, list, list2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PemilihanPdprPdprdApiResponse) {
                    PemilihanPdprPdprdApiResponse pemilihanPdprPdprdApiResponse = (PemilihanPdprPdprdApiResponse) obj;
                    return this.jmlLembar == pemilihanPdprPdprdApiResponse.jmlLembar && Intrinsics.areEqual(this.dapil, pemilihanPdprPdprdApiResponse.dapil) && Intrinsics.areEqual(this.pilihan, pemilihanPdprPdprdApiResponse.pilihan) && Intrinsics.areEqual(this.images, pemilihanPdprPdprdApiResponse.images);
                }
                return false;
            }

            public int hashCode() {
                int hashCode = ((Integer.hashCode(this.jmlLembar) * 31) + this.dapil.hashCode()) * 31;
                List<PilihanPdprPdprdApiResponse> list = this.pilihan;
                int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
                List<ImageApiResponse> list2 = this.images;
                return hashCode2 + (list2 != null ? list2.hashCode() : 0);
            }

            public String toString() {
                int i = this.jmlLembar;
                String str = this.dapil;
                List<PilihanPdprPdprdApiResponse> list = this.pilihan;
                return "PemilihanPdprPdprdApiResponse(jmlLembar=" + i + ", dapil=" + str + ", pilihan=" + list + ", images=" + this.images + ")";
            }

            public PemilihanPdprPdprdApiResponse(int i, String dapil, List<PilihanPdprPdprdApiResponse> list, List<ImageApiResponse> list2) {
                Intrinsics.checkNotNullParameter(dapil, "dapil");
                this.jmlLembar = i;
                this.dapil = dapil;
                this.pilihan = list;
                this.images = list2;
            }

            public final int getJmlLembar() {
                return this.jmlLembar;
            }

            public final String getDapil() {
                return this.dapil;
            }

            public final List<PilihanPdprPdprdApiResponse> getPilihan() {
                return this.pilihan;
            }

            public final List<ImageApiResponse> getImages() {
                return this.images;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanPpwpApiResponse;", "", "idPilihan", "", "noUrutPencalonan", "", "noUrutLabel", "namaCalonKepala", "", "namaCalonWakil", "(JIILjava/lang/String;Ljava/lang/String;)V", "getIdPilihan", "()J", "getNamaCalonKepala", "()Ljava/lang/String;", "getNamaCalonWakil", "getNoUrutLabel", "()I", "getNoUrutPencalonan", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PilihanPpwpApiResponse {
            private final long idPilihan;
            private final String namaCalonKepala;
            private final String namaCalonWakil;
            private final int noUrutLabel;
            private final int noUrutPencalonan;

            public static /* synthetic */ PilihanPpwpApiResponse copy$default(PilihanPpwpApiResponse pilihanPpwpApiResponse, long j, int i, int i2, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j = pilihanPpwpApiResponse.idPilihan;
                }
                long j2 = j;
                if ((i3 & 2) != 0) {
                    i = pilihanPpwpApiResponse.noUrutPencalonan;
                }
                int i4 = i;
                if ((i3 & 4) != 0) {
                    i2 = pilihanPpwpApiResponse.noUrutLabel;
                }
                int i5 = i2;
                if ((i3 & 8) != 0) {
                    str = pilihanPpwpApiResponse.namaCalonKepala;
                }
                String str3 = str;
                if ((i3 & 16) != 0) {
                    str2 = pilihanPpwpApiResponse.namaCalonWakil;
                }
                return pilihanPpwpApiResponse.copy(j2, i4, i5, str3, str2);
            }

            public final long component1() {
                return this.idPilihan;
            }

            public final int component2() {
                return this.noUrutPencalonan;
            }

            public final int component3() {
                return this.noUrutLabel;
            }

            public final String component4() {
                return this.namaCalonKepala;
            }

            public final String component5() {
                return this.namaCalonWakil;
            }

            public final PilihanPpwpApiResponse copy(long j, int i, int i2, String namaCalonKepala, String namaCalonWakil) {
                Intrinsics.checkNotNullParameter(namaCalonKepala, "namaCalonKepala");
                Intrinsics.checkNotNullParameter(namaCalonWakil, "namaCalonWakil");
                return new PilihanPpwpApiResponse(j, i, i2, namaCalonKepala, namaCalonWakil);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PilihanPpwpApiResponse) {
                    PilihanPpwpApiResponse pilihanPpwpApiResponse = (PilihanPpwpApiResponse) obj;
                    return this.idPilihan == pilihanPpwpApiResponse.idPilihan && this.noUrutPencalonan == pilihanPpwpApiResponse.noUrutPencalonan && this.noUrutLabel == pilihanPpwpApiResponse.noUrutLabel && Intrinsics.areEqual(this.namaCalonKepala, pilihanPpwpApiResponse.namaCalonKepala) && Intrinsics.areEqual(this.namaCalonWakil, pilihanPpwpApiResponse.namaCalonWakil);
                }
                return false;
            }

            public int hashCode() {
                return (((((((Long.hashCode(this.idPilihan) * 31) + Integer.hashCode(this.noUrutPencalonan)) * 31) + Integer.hashCode(this.noUrutLabel)) * 31) + this.namaCalonKepala.hashCode()) * 31) + this.namaCalonWakil.hashCode();
            }

            public String toString() {
                long j = this.idPilihan;
                int i = this.noUrutPencalonan;
                int i2 = this.noUrutLabel;
                String str = this.namaCalonKepala;
                return "PilihanPpwpApiResponse(idPilihan=" + j + ", noUrutPencalonan=" + i + ", noUrutLabel=" + i2 + ", namaCalonKepala=" + str + ", namaCalonWakil=" + this.namaCalonWakil + ")";
            }

            public PilihanPpwpApiResponse(long j, int i, int i2, String namaCalonKepala, String namaCalonWakil) {
                Intrinsics.checkNotNullParameter(namaCalonKepala, "namaCalonKepala");
                Intrinsics.checkNotNullParameter(namaCalonWakil, "namaCalonWakil");
                this.idPilihan = j;
                this.noUrutPencalonan = i;
                this.noUrutLabel = i2;
                this.namaCalonKepala = namaCalonKepala;
                this.namaCalonWakil = namaCalonWakil;
            }

            public final long getIdPilihan() {
                return this.idPilihan;
            }

            public final int getNoUrutPencalonan() {
                return this.noUrutPencalonan;
            }

            public final int getNoUrutLabel() {
                return this.noUrutLabel;
            }

            public final String getNamaCalonKepala() {
                return this.namaCalonKepala;
            }

            public final String getNamaCalonWakil() {
                return this.namaCalonWakil;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanPdpdApiResponse;", "", "idPilihan", "", "noUrutPencalonan", "", "noUrutLabel", "nama", "", "(JIILjava/lang/String;)V", "getIdPilihan", "()J", "getNama", "()Ljava/lang/String;", "getNoUrutLabel", "()I", "getNoUrutPencalonan", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PilihanPdpdApiResponse {
            private final long idPilihan;
            private final String nama;
            private final int noUrutLabel;
            private final int noUrutPencalonan;

            public static /* synthetic */ PilihanPdpdApiResponse copy$default(PilihanPdpdApiResponse pilihanPdpdApiResponse, long j, int i, int i2, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j = pilihanPdpdApiResponse.idPilihan;
                }
                long j2 = j;
                if ((i3 & 2) != 0) {
                    i = pilihanPdpdApiResponse.noUrutPencalonan;
                }
                int i4 = i;
                if ((i3 & 4) != 0) {
                    i2 = pilihanPdpdApiResponse.noUrutLabel;
                }
                int i5 = i2;
                if ((i3 & 8) != 0) {
                    str = pilihanPdpdApiResponse.nama;
                }
                return pilihanPdpdApiResponse.copy(j2, i4, i5, str);
            }

            public final long component1() {
                return this.idPilihan;
            }

            public final int component2() {
                return this.noUrutPencalonan;
            }

            public final int component3() {
                return this.noUrutLabel;
            }

            public final String component4() {
                return this.nama;
            }

            public final PilihanPdpdApiResponse copy(long j, int i, int i2, String nama) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                return new PilihanPdpdApiResponse(j, i, i2, nama);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PilihanPdpdApiResponse) {
                    PilihanPdpdApiResponse pilihanPdpdApiResponse = (PilihanPdpdApiResponse) obj;
                    return this.idPilihan == pilihanPdpdApiResponse.idPilihan && this.noUrutPencalonan == pilihanPdpdApiResponse.noUrutPencalonan && this.noUrutLabel == pilihanPdpdApiResponse.noUrutLabel && Intrinsics.areEqual(this.nama, pilihanPdpdApiResponse.nama);
                }
                return false;
            }

            public int hashCode() {
                return (((((Long.hashCode(this.idPilihan) * 31) + Integer.hashCode(this.noUrutPencalonan)) * 31) + Integer.hashCode(this.noUrutLabel)) * 31) + this.nama.hashCode();
            }

            public String toString() {
                long j = this.idPilihan;
                int i = this.noUrutPencalonan;
                int i2 = this.noUrutLabel;
                return "PilihanPdpdApiResponse(idPilihan=" + j + ", noUrutPencalonan=" + i + ", noUrutLabel=" + i2 + ", nama=" + this.nama + ")";
            }

            public PilihanPdpdApiResponse(long j, int i, int i2, String nama) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                this.idPilihan = j;
                this.noUrutPencalonan = i;
                this.noUrutLabel = i2;
                this.nama = nama;
            }

            public final long getIdPilihan() {
                return this.idPilihan;
            }

            public final int getNoUrutPencalonan() {
                return this.noUrutPencalonan;
            }

            public final int getNoUrutLabel() {
                return this.noUrutLabel;
            }

            public final String getNama() {
                return this.nama;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JA\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J\t\u0010 \u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanPdprPdprdApiResponse;", "", "idPilihan", "", "noUrutPencalonan", "", "noUrutLabel", "nama", "", "pilihan", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanNamaPdprPdprdApiResponse;", "(JIILjava/lang/String;Ljava/util/List;)V", "getIdPilihan", "()J", "getNama", "()Ljava/lang/String;", "getNoUrutLabel", "()I", "getNoUrutPencalonan", "getPilihan", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PilihanPdprPdprdApiResponse {
            private final long idPilihan;
            private final String nama;
            private final int noUrutLabel;
            private final int noUrutPencalonan;
            private final List<PilihanNamaPdprPdprdApiResponse> pilihan;

            public static /* synthetic */ PilihanPdprPdprdApiResponse copy$default(PilihanPdprPdprdApiResponse pilihanPdprPdprdApiResponse, long j, int i, int i2, String str, List list, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j = pilihanPdprPdprdApiResponse.idPilihan;
                }
                long j2 = j;
                if ((i3 & 2) != 0) {
                    i = pilihanPdprPdprdApiResponse.noUrutPencalonan;
                }
                int i4 = i;
                if ((i3 & 4) != 0) {
                    i2 = pilihanPdprPdprdApiResponse.noUrutLabel;
                }
                int i5 = i2;
                if ((i3 & 8) != 0) {
                    str = pilihanPdprPdprdApiResponse.nama;
                }
                String str2 = str;
                List<PilihanNamaPdprPdprdApiResponse> list2 = list;
                if ((i3 & 16) != 0) {
                    list2 = pilihanPdprPdprdApiResponse.pilihan;
                }
                return pilihanPdprPdprdApiResponse.copy(j2, i4, i5, str2, list2);
            }

            public final long component1() {
                return this.idPilihan;
            }

            public final int component2() {
                return this.noUrutPencalonan;
            }

            public final int component3() {
                return this.noUrutLabel;
            }

            public final String component4() {
                return this.nama;
            }

            public final List<PilihanNamaPdprPdprdApiResponse> component5() {
                return this.pilihan;
            }

            public final PilihanPdprPdprdApiResponse copy(long j, int i, int i2, String nama, List<PilihanNamaPdprPdprdApiResponse> pilihan) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                Intrinsics.checkNotNullParameter(pilihan, "pilihan");
                return new PilihanPdprPdprdApiResponse(j, i, i2, nama, pilihan);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PilihanPdprPdprdApiResponse) {
                    PilihanPdprPdprdApiResponse pilihanPdprPdprdApiResponse = (PilihanPdprPdprdApiResponse) obj;
                    return this.idPilihan == pilihanPdprPdprdApiResponse.idPilihan && this.noUrutPencalonan == pilihanPdprPdprdApiResponse.noUrutPencalonan && this.noUrutLabel == pilihanPdprPdprdApiResponse.noUrutLabel && Intrinsics.areEqual(this.nama, pilihanPdprPdprdApiResponse.nama) && Intrinsics.areEqual(this.pilihan, pilihanPdprPdprdApiResponse.pilihan);
                }
                return false;
            }

            public int hashCode() {
                return (((((((Long.hashCode(this.idPilihan) * 31) + Integer.hashCode(this.noUrutPencalonan)) * 31) + Integer.hashCode(this.noUrutLabel)) * 31) + this.nama.hashCode()) * 31) + this.pilihan.hashCode();
            }

            public String toString() {
                long j = this.idPilihan;
                int i = this.noUrutPencalonan;
                int i2 = this.noUrutLabel;
                String str = this.nama;
                return "PilihanPdprPdprdApiResponse(idPilihan=" + j + ", noUrutPencalonan=" + i + ", noUrutLabel=" + i2 + ", nama=" + str + ", pilihan=" + this.pilihan + ")";
            }

            public PilihanPdprPdprdApiResponse(long j, int i, int i2, String nama, List<PilihanNamaPdprPdprdApiResponse> pilihan) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                Intrinsics.checkNotNullParameter(pilihan, "pilihan");
                this.idPilihan = j;
                this.noUrutPencalonan = i;
                this.noUrutLabel = i2;
                this.nama = nama;
                this.pilihan = pilihan;
            }

            public final long getIdPilihan() {
                return this.idPilihan;
            }

            public final int getNoUrutPencalonan() {
                return this.noUrutPencalonan;
            }

            public final int getNoUrutLabel() {
                return this.noUrutLabel;
            }

            public final String getNama() {
                return this.nama;
            }

            public final List<PilihanNamaPdprPdprdApiResponse> getPilihan() {
                return this.pilihan;
            }
        }

        /* compiled from: WilayahApiResponse.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\bHÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$PilihanNamaPdprPdprdApiResponse;", "", "idPilihan", "", "noUrutPencalonan", "", "noUrutLabel", "nama", "", "idPartai", "(JIILjava/lang/String;J)V", "getIdPartai", "()J", "getIdPilihan", "getNama", "()Ljava/lang/String;", "getNoUrutLabel", "()I", "getNoUrutPencalonan", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class PilihanNamaPdprPdprdApiResponse {
            private final long idPartai;
            private final long idPilihan;
            private final String nama;
            private final int noUrutLabel;
            private final int noUrutPencalonan;

            public final long component1() {
                return this.idPilihan;
            }

            public final int component2() {
                return this.noUrutPencalonan;
            }

            public final int component3() {
                return this.noUrutLabel;
            }

            public final String component4() {
                return this.nama;
            }

            public final long component5() {
                return this.idPartai;
            }

            public final PilihanNamaPdprPdprdApiResponse copy(long j, int i, int i2, String nama, long j2) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                return new PilihanNamaPdprPdprdApiResponse(j, i, i2, nama, j2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof PilihanNamaPdprPdprdApiResponse) {
                    PilihanNamaPdprPdprdApiResponse pilihanNamaPdprPdprdApiResponse = (PilihanNamaPdprPdprdApiResponse) obj;
                    return this.idPilihan == pilihanNamaPdprPdprdApiResponse.idPilihan && this.noUrutPencalonan == pilihanNamaPdprPdprdApiResponse.noUrutPencalonan && this.noUrutLabel == pilihanNamaPdprPdprdApiResponse.noUrutLabel && Intrinsics.areEqual(this.nama, pilihanNamaPdprPdprdApiResponse.nama) && this.idPartai == pilihanNamaPdprPdprdApiResponse.idPartai;
                }
                return false;
            }

            public int hashCode() {
                return (((((((Long.hashCode(this.idPilihan) * 31) + Integer.hashCode(this.noUrutPencalonan)) * 31) + Integer.hashCode(this.noUrutLabel)) * 31) + this.nama.hashCode()) * 31) + Long.hashCode(this.idPartai);
            }

            public String toString() {
                long j = this.idPilihan;
                int i = this.noUrutPencalonan;
                int i2 = this.noUrutLabel;
                String str = this.nama;
                return "PilihanNamaPdprPdprdApiResponse(idPilihan=" + j + ", noUrutPencalonan=" + i + ", noUrutLabel=" + i2 + ", nama=" + str + ", idPartai=" + this.idPartai + ")";
            }

            public PilihanNamaPdprPdprdApiResponse(long j, int i, int i2, String nama, long j2) {
                Intrinsics.checkNotNullParameter(nama, "nama");
                this.idPilihan = j;
                this.noUrutPencalonan = i;
                this.noUrutLabel = i2;
                this.nama = nama;
                this.idPartai = j2;
            }

            public final long getIdPilihan() {
                return this.idPilihan;
            }

            public final int getNoUrutPencalonan() {
                return this.noUrutPencalonan;
            }

            public final int getNoUrutLabel() {
                return this.noUrutLabel;
            }

            public final String getNama() {
                return this.nama;
            }

            public final long getIdPartai() {
                return this.idPartai;
            }
        }
    }
}
