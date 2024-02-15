package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: FormC1ImageApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0003\f\r\u000eB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetail;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetail;)V", "getData", "()Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetail;", "FormCImageApiResponseDetail", "FormCImageApiResponseDetailKesesuaian", "FormCImageApiResponseDetailPayload", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1ImageApiResponse extends GenericApiResponse {
    private final FormCImageApiResponseDetail data;

    public final FormCImageApiResponseDetail getData() {
        return this.data;
    }

    /* compiled from: FormC1ImageApiResponse.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J+\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetail;", "", "imagePayloads", "", "Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetailPayload;", "imageKesesuaians", "Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetailKesesuaian;", "(Ljava/util/List;Ljava/util/List;)V", "getImageKesesuaians", "()Ljava/util/List;", "getImagePayloads", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormCImageApiResponseDetail {
        private final List<FormCImageApiResponseDetailKesesuaian> imageKesesuaians;
        private final List<FormCImageApiResponseDetailPayload> imagePayloads;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormCImageApiResponseDetail copy$default(FormCImageApiResponseDetail formCImageApiResponseDetail, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formCImageApiResponseDetail.imagePayloads;
            }
            if ((i & 2) != 0) {
                list2 = formCImageApiResponseDetail.imageKesesuaians;
            }
            return formCImageApiResponseDetail.copy(list, list2);
        }

        public final List<FormCImageApiResponseDetailPayload> component1() {
            return this.imagePayloads;
        }

        public final List<FormCImageApiResponseDetailKesesuaian> component2() {
            return this.imageKesesuaians;
        }

        public final FormCImageApiResponseDetail copy(List<FormCImageApiResponseDetailPayload> imagePayloads, List<FormCImageApiResponseDetailKesesuaian> list) {
            Intrinsics.checkNotNullParameter(imagePayloads, "imagePayloads");
            return new FormCImageApiResponseDetail(imagePayloads, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormCImageApiResponseDetail) {
                FormCImageApiResponseDetail formCImageApiResponseDetail = (FormCImageApiResponseDetail) obj;
                return Intrinsics.areEqual(this.imagePayloads, formCImageApiResponseDetail.imagePayloads) && Intrinsics.areEqual(this.imageKesesuaians, formCImageApiResponseDetail.imageKesesuaians);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.imagePayloads.hashCode() * 31;
            List<FormCImageApiResponseDetailKesesuaian> list = this.imageKesesuaians;
            return hashCode + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            List<FormCImageApiResponseDetailPayload> list = this.imagePayloads;
            return "FormCImageApiResponseDetail(imagePayloads=" + list + ", imageKesesuaians=" + this.imageKesesuaians + ")";
        }

        public FormCImageApiResponseDetail(List<FormCImageApiResponseDetailPayload> imagePayloads, List<FormCImageApiResponseDetailKesesuaian> list) {
            Intrinsics.checkNotNullParameter(imagePayloads, "imagePayloads");
            this.imagePayloads = imagePayloads;
            this.imageKesesuaians = list;
        }

        public final List<FormCImageApiResponseDetailPayload> getImagePayloads() {
            return this.imagePayloads;
        }

        public final List<FormCImageApiResponseDetailKesesuaian> getImageKesesuaians() {
            return this.imageKesesuaians;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormC1ImageApiResponse(boolean z, String message, String str, FormCImageApiResponseDetail data) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    /* compiled from: FormC1ImageApiResponse.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetailPayload;", "", "payload", "", "(Ljava/lang/String;)V", "getPayload", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormCImageApiResponseDetailPayload {
        private final String payload;

        public static /* synthetic */ FormCImageApiResponseDetailPayload copy$default(FormCImageApiResponseDetailPayload formCImageApiResponseDetailPayload, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = formCImageApiResponseDetailPayload.payload;
            }
            return formCImageApiResponseDetailPayload.copy(str);
        }

        public final String component1() {
            return this.payload;
        }

        public final FormCImageApiResponseDetailPayload copy(String payload) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            return new FormCImageApiResponseDetailPayload(payload);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FormCImageApiResponseDetailPayload) && Intrinsics.areEqual(this.payload, ((FormCImageApiResponseDetailPayload) obj).payload);
        }

        public int hashCode() {
            return this.payload.hashCode();
        }

        public String toString() {
            return "FormCImageApiResponseDetailPayload(payload=" + this.payload + ")";
        }

        public FormCImageApiResponseDetailPayload(String payload) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            this.payload = payload;
        }

        public final String getPayload() {
            return this.payload;
        }
    }

    /* compiled from: FormC1ImageApiResponse.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007HÆ\u0003JC\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\tHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse$FormCImageApiResponseDetailKesesuaian;", "", "isSesuai", "", "komentar", "", "isSesuaiPerItem", "", "koreksiPerItem", "", "(ZLjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "()Z", "()Ljava/util/List;", "getKomentar", "()Ljava/lang/String;", "getKoreksiPerItem", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormCImageApiResponseDetailKesesuaian {
        private final boolean isSesuai;
        private final List<Boolean> isSesuaiPerItem;
        private final String komentar;
        private final List<Integer> koreksiPerItem;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormCImageApiResponseDetailKesesuaian copy$default(FormCImageApiResponseDetailKesesuaian formCImageApiResponseDetailKesesuaian, boolean z, String str, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = formCImageApiResponseDetailKesesuaian.isSesuai;
            }
            if ((i & 2) != 0) {
                str = formCImageApiResponseDetailKesesuaian.komentar;
            }
            if ((i & 4) != 0) {
                list = formCImageApiResponseDetailKesesuaian.isSesuaiPerItem;
            }
            if ((i & 8) != 0) {
                list2 = formCImageApiResponseDetailKesesuaian.koreksiPerItem;
            }
            return formCImageApiResponseDetailKesesuaian.copy(z, str, list, list2);
        }

        public final boolean component1() {
            return this.isSesuai;
        }

        public final String component2() {
            return this.komentar;
        }

        public final List<Boolean> component3() {
            return this.isSesuaiPerItem;
        }

        public final List<Integer> component4() {
            return this.koreksiPerItem;
        }

        public final FormCImageApiResponseDetailKesesuaian copy(boolean z, String str, List<Boolean> isSesuaiPerItem, List<Integer> koreksiPerItem) {
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            return new FormCImageApiResponseDetailKesesuaian(z, str, isSesuaiPerItem, koreksiPerItem);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormCImageApiResponseDetailKesesuaian) {
                FormCImageApiResponseDetailKesesuaian formCImageApiResponseDetailKesesuaian = (FormCImageApiResponseDetailKesesuaian) obj;
                return this.isSesuai == formCImageApiResponseDetailKesesuaian.isSesuai && Intrinsics.areEqual(this.komentar, formCImageApiResponseDetailKesesuaian.komentar) && Intrinsics.areEqual(this.isSesuaiPerItem, formCImageApiResponseDetailKesesuaian.isSesuaiPerItem) && Intrinsics.areEqual(this.koreksiPerItem, formCImageApiResponseDetailKesesuaian.koreksiPerItem);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        public int hashCode() {
            boolean z = this.isSesuai;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.komentar;
            return ((((i + (str == null ? 0 : str.hashCode())) * 31) + this.isSesuaiPerItem.hashCode()) * 31) + this.koreksiPerItem.hashCode();
        }

        public String toString() {
            boolean z = this.isSesuai;
            String str = this.komentar;
            List<Boolean> list = this.isSesuaiPerItem;
            return "FormCImageApiResponseDetailKesesuaian(isSesuai=" + z + ", komentar=" + str + ", isSesuaiPerItem=" + list + ", koreksiPerItem=" + this.koreksiPerItem + ")";
        }

        public FormCImageApiResponseDetailKesesuaian(boolean z, String str, List<Boolean> isSesuaiPerItem, List<Integer> koreksiPerItem) {
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            this.isSesuai = z;
            this.komentar = str;
            this.isSesuaiPerItem = isSesuaiPerItem;
            this.koreksiPerItem = koreksiPerItem;
        }

        public final boolean isSesuai() {
            return this.isSesuai;
        }

        public final String getKomentar() {
            return this.komentar;
        }

        public final List<Boolean> isSesuaiPerItem() {
            return this.isSesuaiPerItem;
        }

        public final List<Integer> getKoreksiPerItem() {
            return this.koreksiPerItem;
        }
    }
}
