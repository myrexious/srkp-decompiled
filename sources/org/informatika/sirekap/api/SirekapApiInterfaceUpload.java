package org.informatika.sirekap.api;

import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.RequestBody;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.api.response.AttendancePdfApiResponse;
import org.informatika.sirekap.api.response.AttendancePdfUploadUrlApiResponse;
import org.informatika.sirekap.api.response.FormCImageUploadUrlApiResponse;
import org.informatika.sirekap.api.response.UploadFormC1ImageApiResponse;
import org.informatika.sirekap.api.response.UploadFormC1ImageRekapApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

/* compiled from: SirekapApiInterface.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001Jh\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0006H'J,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0006H'J\u0090\u0001\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0015\u001a\u00020\u00062\b\b\u0001\u0010\u0016\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\u0017\u001a\u00020\u00182\b\b\u0001\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0006H'J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H'J®\u0001\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u00062\b\b\u0001\u0010\u001d\u001a\u00020\u001e2\b\b\u0001\u0010\u001f\u001a\u00020\u001e2\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010 \u001a\u00020\u00062\b\b\u0001\u0010!\u001a\u00020\u00182\b\b\u0001\u0010\"\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\u0015\u001a\u00020\u00062\b\b\u0001\u0010\u0016\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010#\u001a\u00020\u00182\b\b\u0001\u0010\r\u001a\u00020\u0006H'J\"\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010&\u001a\u00020'H'JJ\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001d\u001a\u00020\u001e2\b\b\u0001\u0010\u001f\u001a\u00020\u001e2\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0006H'Jh\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0006H'J,\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0006H'¨\u0006+"}, d2 = {"Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "", "attendancePdfUpload", "Lretrofit2/Call;", "Lorg/informatika/sirekap/api/response/AttendancePdfApiResponse;", "url", "", "fileUrl", "deviceId", "userIdOverride", "kodeTps", "namaFile", "usl", "mac", "kodeTpsOverride", "attendancePdfUploadUrl", "Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse;", "formCImageRekapUpload", "Lorg/informatika/sirekap/api/response/UploadFormC1ImageRekapApiResponse;", "userId", "device", "fileHash", "sign", "isOffline", "", "formCImageRekapUploadUrl", "Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse;", "formCImageUpload", "Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse;", "jenisImage", "", "noLembar", "notificationToken", "isValid", ThumbnailType.IMAGE, "isDummy", "formCImageUploadProvider", "", "body", "Lokhttp3/RequestBody;", "formCImageUploadUrl", "specialOccurrenceUpload", "specialOccurrenceUploadUrl", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SirekapApiInterfaceUpload {
    @FormUrlEncoded
    @POST
    Call<AttendancePdfApiResponse> attendancePdfUpload(@Url String str, @Field("url") String str2, @Field("deviceId") String str3, @Field("userIdOverride") String str4, @Field("kodeTps") String str5, @Field("namaFile") String str6, @Field("usl") String str7, @Field("mac") String str8, @Field("kodeTpsOverride") String str9);

    @FormUrlEncoded
    @POST
    Call<AttendancePdfUploadUrlApiResponse> attendancePdfUploadUrl(@Url String str, @Field("usl") String str2, @Field("kodeTpsOverride") String str3);

    @FormUrlEncoded
    @POST
    Call<UploadFormC1ImageRekapApiResponse> formCImageRekapUpload(@Url String str, @Field("deviceId") String str2, @Field("userId") String str3, @Field("device") String str4, @Field("url") String str5, @Field("fileHash") String str6, @Field("sign") String str7, @Field("usl") String str8, @Field("kodeTps") String str9, @Field("namaFile") String str10, @Field("isOffline") boolean z, @Field("mac") String str11, @Field("kodeTpsOverride") String str12);

    @FormUrlEncoded
    @POST
    Call<FormCImageUploadUrlApiResponse> formCImageRekapUploadUrl(@Url String str, @Field("kodeTpsOverride") String str2, @Field("usl") String str3);

    @FormUrlEncoded
    @POST
    Call<UploadFormC1ImageApiResponse> formCImageUpload(@Url String str, @Field("deviceId") String str2, @Field("userIdOverride") String str3, @Field("kodeTpsOverride") String str4, @Field("jenisImage") int i, @Field("noLembar") int i2, @Field("device") String str5, @Field("notificationToken") String str6, @Field("isValid") boolean z, @Field("image") String str7, @Field("namaFile") String str8, @Field("fileHash") String str9, @Field("sign") String str10, @Field("usl") String str11, @Field("isDummy") boolean z2, @Field("mac") String str12);

    @PUT
    Call<Unit> formCImageUploadProvider(@Url String str, @Body RequestBody requestBody);

    @FormUrlEncoded
    @POST
    Call<FormCImageUploadUrlApiResponse> formCImageUploadUrl(@Url String str, @Field("jenisImage") int i, @Field("noLembar") int i2, @Field("usl") String str2, @Field("device") String str3, @Field("kodeTpsOverride") String str4);

    @FormUrlEncoded
    @POST
    Call<AttendancePdfApiResponse> specialOccurrenceUpload(@Url String str, @Field("url") String str2, @Field("deviceId") String str3, @Field("userIdOverride") String str4, @Field("kodeTps") String str5, @Field("namaFile") String str6, @Field("usl") String str7, @Field("mac") String str8, @Field("kodeTpsOverride") String str9);

    @FormUrlEncoded
    @POST
    Call<AttendancePdfUploadUrlApiResponse> specialOccurrenceUploadUrl(@Url String str, @Field("usl") String str2, @Field("kodeTpsOverride") String str3);
}
