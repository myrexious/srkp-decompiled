package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.api.response.KeyCloakUserInfoApiResponse;
import org.informatika.sirekap.api.response.WilayahApiResponse;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.UserInformation;
import org.informatika.sirekap.support.Resource;

/* compiled from: AuthRepository.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J$\u0010\b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0018\u00010\n0\t2\u0006\u0010\r\u001a\u00020\u0003H&J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H&J\b\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0003H&J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0005H&J\u0010\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u0003H&J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u0003H&J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u0012H&¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/repository/AuthRepository;", "", "getAccessToken", "", "getActiveProfile", "Lorg/informatika/sirekap/model/ActiveProfile;", "getAllProfiles", "Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse;", "getAllTps", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/Tps;", "idWilayah", "getJwtSub", "getLoggedInUserId", "getRefreshToken", "getUserInformation", "Lorg/informatika/sirekap/model/UserInformation;", "isAuthenticated", "", "loginSync", "Lorg/informatika/sirekap/api/response/WilayahApiResponse;", "kodeTps", "jenisPemilihan", "profile", "logout", "", "saveAccessToken", "accessToken", "saveActiveProfile", "activeProfile", "saveJwtSub", "sub", "saveRefreshToken", "refreshToken", "setUserInformation", "userInformation", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface AuthRepository {
    String getAccessToken();

    ActiveProfile getActiveProfile();

    KeyCloakUserInfoApiResponse getAllProfiles();

    LiveData<Resource<List<Tps>>> getAllTps(String str);

    String getJwtSub();

    String getLoggedInUserId();

    String getRefreshToken();

    UserInformation getUserInformation();

    boolean isAuthenticated();

    WilayahApiResponse loginSync(String str, String str2, String str3);

    void logout();

    void saveAccessToken(String str);

    void saveActiveProfile(ActiveProfile activeProfile);

    void saveJwtSub(String str);

    void saveRefreshToken(String str);

    void setUserInformation(UserInformation userInformation);
}
