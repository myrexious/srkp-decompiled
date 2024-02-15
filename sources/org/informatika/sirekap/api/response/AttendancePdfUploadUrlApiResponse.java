package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: AttendancePdfUploadUrlApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;)V", "getData", "()Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "AttendancePdfUploadUrlApiResponseDetail", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AttendancePdfUploadUrlApiResponse extends GenericApiResponse {
    private final AttendancePdfUploadUrlApiResponseDetail data;

    public final AttendancePdfUploadUrlApiResponseDetail getData() {
        return this.data;
    }

    /* compiled from: AttendancePdfUploadUrlApiResponse.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "", "uploadUrl", "", "getUrl", "filename", "mac", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFilename", "()Ljava/lang/String;", "getGetUrl", "getMac", "getUploadUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class AttendancePdfUploadUrlApiResponseDetail {
        private final String filename;
        private final String getUrl;
        private final String mac;
        private final String uploadUrl;

        public static /* synthetic */ AttendancePdfUploadUrlApiResponseDetail copy$default(AttendancePdfUploadUrlApiResponseDetail attendancePdfUploadUrlApiResponseDetail, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = attendancePdfUploadUrlApiResponseDetail.uploadUrl;
            }
            if ((i & 2) != 0) {
                str2 = attendancePdfUploadUrlApiResponseDetail.getUrl;
            }
            if ((i & 4) != 0) {
                str3 = attendancePdfUploadUrlApiResponseDetail.filename;
            }
            if ((i & 8) != 0) {
                str4 = attendancePdfUploadUrlApiResponseDetail.mac;
            }
            return attendancePdfUploadUrlApiResponseDetail.copy(str, str2, str3, str4);
        }

        public final String component1() {
            return this.uploadUrl;
        }

        public final String component2() {
            return this.getUrl;
        }

        public final String component3() {
            return this.filename;
        }

        public final String component4() {
            return this.mac;
        }

        public final AttendancePdfUploadUrlApiResponseDetail copy(String uploadUrl, String getUrl, String filename, String mac) {
            Intrinsics.checkNotNullParameter(uploadUrl, "uploadUrl");
            Intrinsics.checkNotNullParameter(getUrl, "getUrl");
            Intrinsics.checkNotNullParameter(filename, "filename");
            Intrinsics.checkNotNullParameter(mac, "mac");
            return new AttendancePdfUploadUrlApiResponseDetail(uploadUrl, getUrl, filename, mac);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AttendancePdfUploadUrlApiResponseDetail) {
                AttendancePdfUploadUrlApiResponseDetail attendancePdfUploadUrlApiResponseDetail = (AttendancePdfUploadUrlApiResponseDetail) obj;
                return Intrinsics.areEqual(this.uploadUrl, attendancePdfUploadUrlApiResponseDetail.uploadUrl) && Intrinsics.areEqual(this.getUrl, attendancePdfUploadUrlApiResponseDetail.getUrl) && Intrinsics.areEqual(this.filename, attendancePdfUploadUrlApiResponseDetail.filename) && Intrinsics.areEqual(this.mac, attendancePdfUploadUrlApiResponseDetail.mac);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.uploadUrl.hashCode() * 31) + this.getUrl.hashCode()) * 31) + this.filename.hashCode()) * 31) + this.mac.hashCode();
        }

        public String toString() {
            String str = this.uploadUrl;
            String str2 = this.getUrl;
            String str3 = this.filename;
            return "AttendancePdfUploadUrlApiResponseDetail(uploadUrl=" + str + ", getUrl=" + str2 + ", filename=" + str3 + ", mac=" + this.mac + ")";
        }

        public AttendancePdfUploadUrlApiResponseDetail(String uploadUrl, String getUrl, String filename, String mac) {
            Intrinsics.checkNotNullParameter(uploadUrl, "uploadUrl");
            Intrinsics.checkNotNullParameter(getUrl, "getUrl");
            Intrinsics.checkNotNullParameter(filename, "filename");
            Intrinsics.checkNotNullParameter(mac, "mac");
            this.uploadUrl = uploadUrl;
            this.getUrl = getUrl;
            this.filename = filename;
            this.mac = mac;
        }

        public final String getUploadUrl() {
            return this.uploadUrl;
        }

        public final String getGetUrl() {
            return this.getUrl;
        }

        public final String getFilename() {
            return this.filename;
        }

        public final String getMac() {
            return this.mac;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AttendancePdfUploadUrlApiResponse(boolean z, String message, String str, AttendancePdfUploadUrlApiResponseDetail attendancePdfUploadUrlApiResponseDetail) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = attendancePdfUploadUrlApiResponseDetail;
    }
}
