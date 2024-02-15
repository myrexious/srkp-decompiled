package org.informatika.sirekap.api;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.api.response.FormC1ImageApiResponse;
import org.informatika.sirekap.api.response.GetTpsByEmailApiResponse;
import org.informatika.sirekap.api.response.WilayahApiResponse;
import org.informatika.sirekap.api.response.WitnessApiResponse;
import org.informatika.sirekap.api.response.WitnessDetailApiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: SirekapApiInterface.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001JZ\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\u00072\b\b\u0001\u0010\u000e\u001a\u00020\u0007H'J~\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0011\u001a\u00020\u00072\b\b\u0001\u0010\u0012\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u00072\b\b\u0001\u0010\u0014\u001a\u00020\u00072\b\b\u0001\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\r\u001a\u00020\u00072\u000e\b\u0001\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u0007H'Jx\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001b2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0011\u001a\u00020\u00072\b\b\u0001\u0010\u0012\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u00072\b\b\u0001\u0010\u0014\u001a\u00020\u00072\b\b\u0001\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\r\u001a\u00020\u00072\u000e\b\u0001\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u0007H'J2\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u001e\u001a\u00020\f2\b\b\u0001\u0010\u001f\u001a\u00020\u0007H'J(\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\"\u001a\u00020\u0007H'J\"\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001b2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u001f\u001a\u00020\u0007H'J(\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u0007H'J|\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\r\u001a\u00020\u00072\b\b\u0001\u0010\u001e\u001a\u00020\f2\b\b\u0001\u0010(\u001a\u00020)2\u0010\b\u0001\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0\u00182\b\b\u0001\u0010+\u001a\u00020\u00072\u000e\b\u0001\u0010,\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0019\u001a\u00020\u0007H'¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/api/SirekapApiInterface;", "", "addInfoTps", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "url", "", "kodeTps", "waktuMulai", "waktuSelesai", "jenisWaktu", "", "deviceId", "usl", "addWitness", "Lorg/informatika/sirekap/api/response/WitnessDetailApiResponse;", "nama", "jenisPemeriksa", "nik", "noHandphone", "idPilihan", "", "jenisPemilihan", "", "userId", "addWitnessSync", "Lretrofit2/Call;", "getFormCImage", "Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse;", "idImage", "kodeTpsOverride", "getListTpsByIdWilayah", "Lorg/informatika/sirekap/api/response/GetTpsByEmailApiResponse;", "idWilayah", "getWilayahSync", "Lorg/informatika/sirekap/api/response/WilayahApiResponse;", "getWitnesses", "Lorg/informatika/sirekap/api/response/WitnessApiResponse;", "updateFormCImageKesesuaian", "isSesuai", "", "isSesuaiPerItem", "komentar", "koreksiPerItem", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SirekapApiInterface {
    @FormUrlEncoded
    @POST
    LiveData<ApiResponse<GenericApiResponse>> addInfoTps(@Url String str, @Field("kodeTps") String str2, @Field("mulaiDatetime") String str3, @Field("selesaiDatetime") String str4, @Field("jenisWaktu") int i, @Field("deviceId") String str5, @Field("usl") String str6);

    @FormUrlEncoded
    @POST
    LiveData<ApiResponse<WitnessDetailApiResponse>> addWitness(@Url String str, @Field("kodeTps") String str2, @Field("nama") String str3, @Field("jenisPemeriksa") String str4, @Field("nik") String str5, @Field("noHandphone") String str6, @Field("idPilihan") long j, @Field("deviceId") String str7, @Field("jenisPemilihan") List<String> list, @Field("userId") String str8);

    @FormUrlEncoded
    @POST
    Call<WitnessDetailApiResponse> addWitnessSync(@Url String str, @Field("kodeTps") String str2, @Field("nama") String str3, @Field("jenisPemeriksa") String str4, @Field("nik") String str5, @Field("noHandphone") String str6, @Field("idPilihan") long j, @Field("deviceId") String str7, @Field("jenisPemilihan") List<String> list, @Field("userId") String str8);

    @FormUrlEncoded
    @POST
    LiveData<ApiResponse<FormC1ImageApiResponse>> getFormCImage(@Url String str, @Field("idImage") int i, @Field("kodeTpsOverride") String str2);

    @FormUrlEncoded
    @POST
    LiveData<ApiResponse<GetTpsByEmailApiResponse>> getListTpsByIdWilayah(@Url String str, @Field("idWilayah") String str2);

    @FormUrlEncoded
    @POST
    Call<WilayahApiResponse> getWilayahSync(@Url String str, @Field("kodeTpsOverride") String str2);

    @FormUrlEncoded
    @POST
    LiveData<ApiResponse<WitnessApiResponse>> getWitnesses(@Url String str, @Field("kodeTps") String str2);

    @FormUrlEncoded
    @POST
    LiveData<ApiResponse<FormC1ImageApiResponse>> updateFormCImageKesesuaian(@Url String str, @Field("deviceId") String str2, @Field("idImage") int i, @Field("isSesuai") boolean z, @Field("isSesuaiPerItem") List<Boolean> list, @Field("komentar") String str3, @Field("koreksiPerItem") List<Integer> list2, @Field("kodeTps") String str4, @Field("userId") String str5);
}
