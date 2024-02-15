package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: FormCImageUploadUrlApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;)V", "getData", "()Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;", "FormCImageUploadUrlApiResponseDetail", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormCImageUploadUrlApiResponse extends GenericApiResponse {
    private final FormCImageUploadUrlApiResponseDetail data;

    public final FormCImageUploadUrlApiResponseDetail getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormCImageUploadUrlApiResponse(boolean z, String message, String str, FormCImageUploadUrlApiResponseDetail formCImageUploadUrlApiResponseDetail) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = formCImageUploadUrlApiResponseDetail;
    }

    /* compiled from: FormCImageUploadUrlApiResponse.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;", "", "uploadUrl", "", "getUrl", "filename", "mac", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFilename", "()Ljava/lang/String;", "getGetUrl", "getMac", "getUploadUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormCImageUploadUrlApiResponseDetail {
        private final String filename;
        private final String getUrl;
        private final String mac;
        private final String uploadUrl;

        public static /* synthetic */ FormCImageUploadUrlApiResponseDetail copy$default(FormCImageUploadUrlApiResponseDetail formCImageUploadUrlApiResponseDetail, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = formCImageUploadUrlApiResponseDetail.uploadUrl;
            }
            if ((i & 2) != 0) {
                str2 = formCImageUploadUrlApiResponseDetail.getUrl;
            }
            if ((i & 4) != 0) {
                str3 = formCImageUploadUrlApiResponseDetail.filename;
            }
            if ((i & 8) != 0) {
                str4 = formCImageUploadUrlApiResponseDetail.mac;
            }
            return formCImageUploadUrlApiResponseDetail.copy(str, str2, str3, str4);
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

        public final FormCImageUploadUrlApiResponseDetail copy(String uploadUrl, String getUrl, String filename, String mac) {
            Intrinsics.checkNotNullParameter(uploadUrl, "uploadUrl");
            Intrinsics.checkNotNullParameter(getUrl, "getUrl");
            Intrinsics.checkNotNullParameter(filename, "filename");
            Intrinsics.checkNotNullParameter(mac, "mac");
            return new FormCImageUploadUrlApiResponseDetail(uploadUrl, getUrl, filename, mac);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormCImageUploadUrlApiResponseDetail) {
                FormCImageUploadUrlApiResponseDetail formCImageUploadUrlApiResponseDetail = (FormCImageUploadUrlApiResponseDetail) obj;
                return Intrinsics.areEqual(this.uploadUrl, formCImageUploadUrlApiResponseDetail.uploadUrl) && Intrinsics.areEqual(this.getUrl, formCImageUploadUrlApiResponseDetail.getUrl) && Intrinsics.areEqual(this.filename, formCImageUploadUrlApiResponseDetail.filename) && Intrinsics.areEqual(this.mac, formCImageUploadUrlApiResponseDetail.mac);
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
            return "FormCImageUploadUrlApiResponseDetail(uploadUrl=" + str + ", getUrl=" + str2 + ", filename=" + str3 + ", mac=" + this.mac + ")";
        }

        public FormCImageUploadUrlApiResponseDetail(String uploadUrl, String getUrl, String filename, String mac) {
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
}
