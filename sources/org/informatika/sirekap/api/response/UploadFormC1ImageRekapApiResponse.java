package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: UploadFormC1ImageRekapApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/UploadFormC1ImageRekapApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/api/response/UploadFormC1ImageRekapApiResponse$UploadFormC1ImageRekapApiResponseDetail;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/api/response/UploadFormC1ImageRekapApiResponse$UploadFormC1ImageRekapApiResponseDetail;)V", "getData", "()Lorg/informatika/sirekap/api/response/UploadFormC1ImageRekapApiResponse$UploadFormC1ImageRekapApiResponseDetail;", "UploadFormC1ImageRekapApiResponseDetail", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadFormC1ImageRekapApiResponse extends GenericApiResponse {
    private final UploadFormC1ImageRekapApiResponseDetail data;

    public final UploadFormC1ImageRekapApiResponseDetail getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadFormC1ImageRekapApiResponse(boolean z, String message, String str, UploadFormC1ImageRekapApiResponseDetail uploadFormC1ImageRekapApiResponseDetail) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = uploadFormC1ImageRekapApiResponseDetail;
    }

    /* compiled from: UploadFormC1ImageRekapApiResponse.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, d2 = {"Lorg/informatika/sirekap/api/response/UploadFormC1ImageRekapApiResponse$UploadFormC1ImageRekapApiResponseDetail;", "", JobType.ID, "", "idTps", "jenisPemilihan", "", "uuid", "fileUrl", "filehash", "filesign", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFileUrl", "()Ljava/lang/String;", "getFilehash", "getFilesign", "getId", "()J", "getIdTps", "getJenisPemilihan", "getUuid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class UploadFormC1ImageRekapApiResponseDetail {
        private final String fileUrl;
        private final String filehash;
        private final String filesign;

        /* renamed from: id */
        private final long f59id;
        private final long idTps;
        private final String jenisPemilihan;
        private final String uuid;

        public final long component1() {
            return this.f59id;
        }

        public final long component2() {
            return this.idTps;
        }

        public final String component3() {
            return this.jenisPemilihan;
        }

        public final String component4() {
            return this.uuid;
        }

        public final String component5() {
            return this.fileUrl;
        }

        public final String component6() {
            return this.filehash;
        }

        public final String component7() {
            return this.filesign;
        }

        public final UploadFormC1ImageRekapApiResponseDetail copy(long j, long j2, String jenisPemilihan, String uuid, String fileUrl, String filehash, String filesign) {
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(fileUrl, "fileUrl");
            Intrinsics.checkNotNullParameter(filehash, "filehash");
            Intrinsics.checkNotNullParameter(filesign, "filesign");
            return new UploadFormC1ImageRekapApiResponseDetail(j, j2, jenisPemilihan, uuid, fileUrl, filehash, filesign);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof UploadFormC1ImageRekapApiResponseDetail) {
                UploadFormC1ImageRekapApiResponseDetail uploadFormC1ImageRekapApiResponseDetail = (UploadFormC1ImageRekapApiResponseDetail) obj;
                return this.f59id == uploadFormC1ImageRekapApiResponseDetail.f59id && this.idTps == uploadFormC1ImageRekapApiResponseDetail.idTps && Intrinsics.areEqual(this.jenisPemilihan, uploadFormC1ImageRekapApiResponseDetail.jenisPemilihan) && Intrinsics.areEqual(this.uuid, uploadFormC1ImageRekapApiResponseDetail.uuid) && Intrinsics.areEqual(this.fileUrl, uploadFormC1ImageRekapApiResponseDetail.fileUrl) && Intrinsics.areEqual(this.filehash, uploadFormC1ImageRekapApiResponseDetail.filehash) && Intrinsics.areEqual(this.filesign, uploadFormC1ImageRekapApiResponseDetail.filesign);
            }
            return false;
        }

        public int hashCode() {
            return (((((((((((Long.hashCode(this.f59id) * 31) + Long.hashCode(this.idTps)) * 31) + this.jenisPemilihan.hashCode()) * 31) + this.uuid.hashCode()) * 31) + this.fileUrl.hashCode()) * 31) + this.filehash.hashCode()) * 31) + this.filesign.hashCode();
        }

        public String toString() {
            long j = this.f59id;
            long j2 = this.idTps;
            String str = this.jenisPemilihan;
            String str2 = this.uuid;
            String str3 = this.fileUrl;
            String str4 = this.filehash;
            return "UploadFormC1ImageRekapApiResponseDetail(id=" + j + ", idTps=" + j2 + ", jenisPemilihan=" + str + ", uuid=" + str2 + ", fileUrl=" + str3 + ", filehash=" + str4 + ", filesign=" + this.filesign + ")";
        }

        public UploadFormC1ImageRekapApiResponseDetail(long j, long j2, String jenisPemilihan, String uuid, String fileUrl, String filehash, String filesign) {
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(fileUrl, "fileUrl");
            Intrinsics.checkNotNullParameter(filehash, "filehash");
            Intrinsics.checkNotNullParameter(filesign, "filesign");
            this.f59id = j;
            this.idTps = j2;
            this.jenisPemilihan = jenisPemilihan;
            this.uuid = uuid;
            this.fileUrl = fileUrl;
            this.filehash = filehash;
            this.filesign = filesign;
        }

        public final long getId() {
            return this.f59id;
        }

        public final long getIdTps() {
            return this.idTps;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        public final String getUuid() {
            return this.uuid;
        }

        public final String getFileUrl() {
            return this.fileUrl;
        }

        public final String getFilehash() {
            return this.filehash;
        }

        public final String getFilesign() {
            return this.filesign;
        }
    }
}
