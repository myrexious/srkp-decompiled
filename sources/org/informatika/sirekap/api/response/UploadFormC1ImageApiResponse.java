package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: UploadFormC1ImageApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;)V", "getData", "()Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;", "UploadFormC1ImageApiResponseDetail", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadFormC1ImageApiResponse extends GenericApiResponse {
    private final UploadFormC1ImageApiResponseDetail data;

    public final UploadFormC1ImageApiResponseDetail getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadFormC1ImageApiResponse(boolean z, String message, String str, UploadFormC1ImageApiResponseDetail uploadFormC1ImageApiResponseDetail) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = uploadFormC1ImageApiResponseDetail;
    }

    /* compiled from: UploadFormC1ImageApiResponse.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;", "", "uuid", "", "idImage", "", "jenisImage", "jenisPemilihan", "noLembar", "url", "(Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;)V", "getIdImage", "()I", "getJenisImage", "getJenisPemilihan", "()Ljava/lang/String;", "getNoLembar", "getUrl", "getUuid", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class UploadFormC1ImageApiResponseDetail {
        private final int idImage;
        private final int jenisImage;
        private final String jenisPemilihan;
        private final int noLembar;
        private final String url;
        private final String uuid;

        public static /* synthetic */ UploadFormC1ImageApiResponseDetail copy$default(UploadFormC1ImageApiResponseDetail uploadFormC1ImageApiResponseDetail, String str, int i, int i2, String str2, int i3, String str3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = uploadFormC1ImageApiResponseDetail.uuid;
            }
            if ((i4 & 2) != 0) {
                i = uploadFormC1ImageApiResponseDetail.idImage;
            }
            int i5 = i;
            if ((i4 & 4) != 0) {
                i2 = uploadFormC1ImageApiResponseDetail.jenisImage;
            }
            int i6 = i2;
            if ((i4 & 8) != 0) {
                str2 = uploadFormC1ImageApiResponseDetail.jenisPemilihan;
            }
            String str4 = str2;
            if ((i4 & 16) != 0) {
                i3 = uploadFormC1ImageApiResponseDetail.noLembar;
            }
            int i7 = i3;
            if ((i4 & 32) != 0) {
                str3 = uploadFormC1ImageApiResponseDetail.url;
            }
            return uploadFormC1ImageApiResponseDetail.copy(str, i5, i6, str4, i7, str3);
        }

        public final String component1() {
            return this.uuid;
        }

        public final int component2() {
            return this.idImage;
        }

        public final int component3() {
            return this.jenisImage;
        }

        public final String component4() {
            return this.jenisPemilihan;
        }

        public final int component5() {
            return this.noLembar;
        }

        public final String component6() {
            return this.url;
        }

        public final UploadFormC1ImageApiResponseDetail copy(String uuid, int i, int i2, String jenisPemilihan, int i3, String url) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(url, "url");
            return new UploadFormC1ImageApiResponseDetail(uuid, i, i2, jenisPemilihan, i3, url);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof UploadFormC1ImageApiResponseDetail) {
                UploadFormC1ImageApiResponseDetail uploadFormC1ImageApiResponseDetail = (UploadFormC1ImageApiResponseDetail) obj;
                return Intrinsics.areEqual(this.uuid, uploadFormC1ImageApiResponseDetail.uuid) && this.idImage == uploadFormC1ImageApiResponseDetail.idImage && this.jenisImage == uploadFormC1ImageApiResponseDetail.jenisImage && Intrinsics.areEqual(this.jenisPemilihan, uploadFormC1ImageApiResponseDetail.jenisPemilihan) && this.noLembar == uploadFormC1ImageApiResponseDetail.noLembar && Intrinsics.areEqual(this.url, uploadFormC1ImageApiResponseDetail.url);
            }
            return false;
        }

        public int hashCode() {
            return (((((((((this.uuid.hashCode() * 31) + Integer.hashCode(this.idImage)) * 31) + Integer.hashCode(this.jenisImage)) * 31) + this.jenisPemilihan.hashCode()) * 31) + Integer.hashCode(this.noLembar)) * 31) + this.url.hashCode();
        }

        public String toString() {
            String str = this.uuid;
            int i = this.idImage;
            int i2 = this.jenisImage;
            String str2 = this.jenisPemilihan;
            int i3 = this.noLembar;
            return "UploadFormC1ImageApiResponseDetail(uuid=" + str + ", idImage=" + i + ", jenisImage=" + i2 + ", jenisPemilihan=" + str2 + ", noLembar=" + i3 + ", url=" + this.url + ")";
        }

        public UploadFormC1ImageApiResponseDetail(String uuid, int i, int i2, String jenisPemilihan, int i3, String url) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            Intrinsics.checkNotNullParameter(url, "url");
            this.uuid = uuid;
            this.idImage = i;
            this.jenisImage = i2;
            this.jenisPemilihan = jenisPemilihan;
            this.noLembar = i3;
            this.url = url;
        }

        public final String getUuid() {
            return this.uuid;
        }

        public final int getIdImage() {
            return this.idImage;
        }

        public final int getJenisImage() {
            return this.jenisImage;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }

        public final int getNoLembar() {
            return this.noLembar;
        }

        public final String getUrl() {
            return this.url;
        }
    }
}
