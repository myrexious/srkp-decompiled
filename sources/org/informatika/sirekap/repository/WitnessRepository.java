package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.ResourceRefType;
import org.informatika.sirekap.api.response.AttendancePdfUploadUrlApiResponse;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.Resource;

/* compiled from: WitnessRepository.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\f\bf\u0018\u00002\u00020\u0001:\u0001@J0\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00040\u00032\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&J&\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00040\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH&J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH&J@\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH&J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH&J \u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\nH&J\u001e\u0010\"\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00040\u00032\u0006\u0010#\u001a\u00020$H&J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\nH&J \u0010'\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nH&J\u001a\u0010*\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010+\u001a\u00020\u0015H&J\u001e\u0010,\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020-\u0018\u00010\u00040\u00032\u0006\u0010\u0018\u001a\u00020\nH&J\u001e\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00040\u00032\u0006\u0010\t\u001a\u00020\nH&J$\u0010/\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00040\u00032\u0006\u0010\u0018\u001a\u00020\nH&JF\u00100\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020$\u0018\u00010\u00040\u00032\u0006\u00101\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\nH&J&\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00040\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u00108\u001a\u00020\nH&J\u0010\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020\rH&J.\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020$\u0018\u00010\u00040\u00032\u0006\u0010#\u001a\u00020$2\u0006\u0010<\u001a\u00020$2\u0006\u0010=\u001a\u00020\u0015H&J\u0010\u0010>\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\nH&J6\u0010?\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020$\u0018\u00010\u00040\u00032\u0006\u00101\u001a\u00020\r2\u0006\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u00106\u001a\u00020\nH&¨\u0006A"}, d2 = {"Lorg/informatika/sirekap/repository/WitnessRepository;", "", "addWitness", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "witness", "Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel;", "noHandphone", "", "addWitnessLocal", "idPetugas", "", "addWitnessSync", "Lorg/informatika/sirekap/model/Witness;", "witnessPemeriksa", "Lorg/informatika/sirekap/model/WitnessPemeriksa;", "deviceId", "userId", "attendancePdfUpload", "", "fileUrl", "userIdOverride", "kodeTps", "namaFile", "usl", "mac", "attendancePdfUploadLink", "Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "attendancePdfUploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "deleteAttendance", "attendancePage", "Lorg/informatika/sirekap/model/AttendancePage;", "failUploadPdf", "", "finishCreatePdf", "pdfFilePath", "pdfFileHash", "finishUploadPdf", "isUploadedPdfOffline", "getAttendanceByKodeTps", "Lorg/informatika/sirekap/model/AttendanceWithPages;", "getWitnessById", "getWitnessesByKodeTps", "insertAttendance", "attendancePageId", "photoPath", "croppedPhotoPath", "urutan", "", "hashDocumentCropped", "markAsShared", "jenisPemilihan", "markAttendeesChecked", "attendeesId", "moveAttendees", "switchedAttendancePage", "isUp", "startUploadPdf", "updateAttendancePagePhoto", "AddWitnessModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface WitnessRepository {
    LiveData<Resource<List<WitnessWithShare>>> addWitness(AddWitnessModel addWitnessModel, String str);

    LiveData<Resource<WitnessWithShare>> addWitnessLocal(AddWitnessModel addWitnessModel, long j);

    Witness addWitnessSync(Witness witness, WitnessPemeriksa witnessPemeriksa, String str, String str2);

    boolean attendancePdfUpload(String str, String str2, String str3, String str4, String str5, String str6, String str7);

    AttendancePdfUploadUrlApiResponse.AttendancePdfUploadUrlApiResponseDetail attendancePdfUploadLink(String str, String str2);

    boolean attendancePdfUploadProvider(String str, String str2, String str3);

    LiveData<Resource<Boolean>> deleteAttendance(AttendancePage attendancePage);

    void failUploadPdf(String str);

    void finishCreatePdf(String str, String str2, String str3);

    void finishUploadPdf(String str, boolean z);

    LiveData<Resource<AttendanceWithPages>> getAttendanceByKodeTps(String str);

    LiveData<Resource<WitnessWithShare>> getWitnessById(String str);

    LiveData<Resource<List<WitnessWithShare>>> getWitnessesByKodeTps(String str);

    LiveData<Resource<AttendancePage>> insertAttendance(long j, String str, String str2, String str3, int i, String str4);

    LiveData<Resource<WitnessWithShare>> markAsShared(String str, String str2);

    void markAttendeesChecked(long j);

    LiveData<Resource<AttendancePage>> moveAttendees(AttendancePage attendancePage, AttendancePage attendancePage2, boolean z);

    void startUploadPdf(String str);

    LiveData<Resource<AttendancePage>> updateAttendancePagePhoto(long j, String str, String str2, String str3);

    /* compiled from: WitnessRepository.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J[\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel;", "", "nama", "", "jenisPemeriksa", "nik", "noHandphone", "kodeTps", "idPaslon", "", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getIdPaslon", "()Ljava/util/List;", "getJenisPemeriksa", "()Ljava/lang/String;", "getJenisPemilihan", "getKodeTps", "getNama", "getNik", "getNoHandphone", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class AddWitnessModel {
        public static final Companion Companion = new Companion(null);
        private final List<Long> idPaslon;
        private final String jenisPemeriksa;
        private final List<String> jenisPemilihan;
        private final String kodeTps;
        private final String nama;
        private final String nik;
        private final String noHandphone;

        public static /* synthetic */ AddWitnessModel copy$default(AddWitnessModel addWitnessModel, String str, String str2, String str3, String str4, String str5, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = addWitnessModel.nama;
            }
            if ((i & 2) != 0) {
                str2 = addWitnessModel.jenisPemeriksa;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = addWitnessModel.nik;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = addWitnessModel.noHandphone;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = addWitnessModel.kodeTps;
            }
            String str9 = str5;
            List<Long> list3 = list;
            if ((i & 32) != 0) {
                list3 = addWitnessModel.idPaslon;
            }
            List list4 = list3;
            List<String> list5 = list2;
            if ((i & 64) != 0) {
                list5 = addWitnessModel.jenisPemilihan;
            }
            return addWitnessModel.copy(str, str6, str7, str8, str9, list4, list5);
        }

        public final String component1() {
            return this.nama;
        }

        public final String component2() {
            return this.jenisPemeriksa;
        }

        public final String component3() {
            return this.nik;
        }

        public final String component4() {
            return this.noHandphone;
        }

        public final String component5() {
            return this.kodeTps;
        }

        public final List<Long> component6() {
            return this.idPaslon;
        }

        public final List<String> component7() {
            return this.jenisPemilihan;
        }

        public final AddWitnessModel copy(String nama, String jenisPemeriksa, String nik, String noHandphone, String kodeTps, List<Long> idPaslon, List<String> jenisPemilihan) {
            Intrinsics.checkNotNullParameter(nama, "nama");
            Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
            Intrinsics.checkNotNullParameter(nik, "nik");
            Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(idPaslon, "idPaslon");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new AddWitnessModel(nama, jenisPemeriksa, nik, noHandphone, kodeTps, idPaslon, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AddWitnessModel) {
                AddWitnessModel addWitnessModel = (AddWitnessModel) obj;
                return Intrinsics.areEqual(this.nama, addWitnessModel.nama) && Intrinsics.areEqual(this.jenisPemeriksa, addWitnessModel.jenisPemeriksa) && Intrinsics.areEqual(this.nik, addWitnessModel.nik) && Intrinsics.areEqual(this.noHandphone, addWitnessModel.noHandphone) && Intrinsics.areEqual(this.kodeTps, addWitnessModel.kodeTps) && Intrinsics.areEqual(this.idPaslon, addWitnessModel.idPaslon) && Intrinsics.areEqual(this.jenisPemilihan, addWitnessModel.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (((((((((((this.nama.hashCode() * 31) + this.jenisPemeriksa.hashCode()) * 31) + this.nik.hashCode()) * 31) + this.noHandphone.hashCode()) * 31) + this.kodeTps.hashCode()) * 31) + this.idPaslon.hashCode()) * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.nama;
            String str2 = this.jenisPemeriksa;
            String str3 = this.nik;
            String str4 = this.noHandphone;
            String str5 = this.kodeTps;
            List<Long> list = this.idPaslon;
            return "AddWitnessModel(nama=" + str + ", jenisPemeriksa=" + str2 + ", nik=" + str3 + ", noHandphone=" + str4 + ", kodeTps=" + str5 + ", idPaslon=" + list + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public AddWitnessModel(String nama, String jenisPemeriksa, String nik, String noHandphone, String kodeTps, List<Long> idPaslon, List<String> jenisPemilihan) {
            Intrinsics.checkNotNullParameter(nama, "nama");
            Intrinsics.checkNotNullParameter(jenisPemeriksa, "jenisPemeriksa");
            Intrinsics.checkNotNullParameter(nik, "nik");
            Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(idPaslon, "idPaslon");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.nama = nama;
            this.jenisPemeriksa = jenisPemeriksa;
            this.nik = nik;
            this.noHandphone = noHandphone;
            this.kodeTps = kodeTps;
            this.idPaslon = idPaslon;
            this.jenisPemilihan = jenisPemilihan;
        }

        public final String getNama() {
            return this.nama;
        }

        public final String getJenisPemeriksa() {
            return this.jenisPemeriksa;
        }

        public final String getNik() {
            return this.nik;
        }

        public final String getNoHandphone() {
            return this.noHandphone;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        public final List<Long> getIdPaslon() {
            return this.idPaslon;
        }

        public final List<String> getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        /* compiled from: WitnessRepository.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel$Companion;", "", "()V", "createFromWitnessWithShare", "Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel;", "witnessWithShare", "Lorg/informatika/sirekap/model/WitnessWithShare;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AddWitnessModel createFromWitnessWithShare(WitnessWithShare witnessWithShare) {
                Intrinsics.checkNotNullParameter(witnessWithShare, "witnessWithShare");
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                List<WitnessPemeriksa> pemeriksas = witnessWithShare.getPemeriksas();
                if (pemeriksas != null) {
                    for (WitnessPemeriksa witnessPemeriksa : pemeriksas) {
                        arrayList.add(Long.valueOf(witnessPemeriksa.getIdPilihan()));
                        arrayList2.add(witnessPemeriksa.getJenisPemilihan());
                    }
                }
                return new AddWitnessModel(witnessWithShare.getWitness().getNama(), witnessWithShare.getWitness().getJenisPemeriksa(), witnessWithShare.getWitness().getNik(), witnessWithShare.getWitness().getNoHandphone(), witnessWithShare.getWitness().getKodeTps(), arrayList, arrayList2);
            }
        }
    }

    /* compiled from: WitnessRepository.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ LiveData addWitness$default(WitnessRepository witnessRepository, AddWitnessModel addWitnessModel, String str, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str = null;
                }
                return witnessRepository.addWitness(addWitnessModel, str);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addWitness");
        }

        public static /* synthetic */ void finishUploadPdf$default(WitnessRepository witnessRepository, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishUploadPdf");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            witnessRepository.finishUploadPdf(str, z);
        }
    }
}
