package org.informatika.sirekap.api;

import kotlin.Metadata;
import org.informatika.sirekap.api.response.KeyCloakUserInfoApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/* compiled from: SirekapApiInterface.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/api/KeyCloakApiInterface;", "", "getUserInfo", "Lretrofit2/Call;", "Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface KeyCloakApiInterface {
    @GET("openid-connect/userinfo")
    Call<KeyCloakUserInfoApiResponse> getUserInfo();
}
