package org.informatika.sirekap.di;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;

/* compiled from: SharedPreferencesModule.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010%\u001a\u00020&2\b\b\u0001\u0010'\u001a\u00020(H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082T¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/informatika/sirekap/di/SharedPreferencesModule;", "", "()V", "SP_APP_INITIALIZATION", "", "SP_DONT_HAVE_PAIR_KEY", "SP_DONT_HAVE_PRIVATE_KEY", "SP_KEYSTORE_PREPARATION", "SP_KEY_ACCESS_TOKEN", "SP_KEY_ACCESS_TOKEN_UPLOAD", "SP_KEY_ELECTION_TYPE", "SP_KEY_FCM_TOKEN", "SP_KEY_HAS_UPDATE_INITIALIZATION_STATUS", "SP_KEY_HIDE_COMPLETED_ELECTION_PAGES", "SP_KEY_INITIALIZATION_BYPASS", "SP_KEY_INITIALIZATION_USER_STATUS", "SP_KEY_IS_USER_PROMOTED", "SP_KEY_JWT_SUB", "SP_KEY_JWT_SUBJECT", "SP_KEY_KODE_AKSES", "SP_KEY_KODE_TPS", "SP_KEY_KODE_WILAYAH", "SP_KEY_PHONE_NUMBER", "SP_KEY_PROFILE", "SP_KEY_PROFILE_END", "SP_KEY_PROFILE_START", "SP_KEY_REFRESH_TOKEN", "SP_KEY_ROLE", "SP_KEY_USER_ID", "SP_KEY_USER_REAL_NAME", "SP_KEY_USER_ROLE", "SP_KEY_USER_SID", "SP_KEY_USER_STATUS", "SP_KEY_VISION_WAIT_UNTIL", "SP_STATUS_INITIALIZATION", "SP_VERSION", "", "provideSharedPreferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public final class SharedPreferencesModule {
    public static final SharedPreferencesModule INSTANCE = new SharedPreferencesModule();
    public static final String SP_APP_INITIALIZATION = "app_initialization25";
    public static final String SP_DONT_HAVE_PAIR_KEY = "dont_have_pair_key25";
    public static final String SP_DONT_HAVE_PRIVATE_KEY = "dont_have_private_key25";
    public static final String SP_KEYSTORE_PREPARATION = "keystore_preparation25";
    public static final String SP_KEY_ACCESS_TOKEN = "accessToken25";
    public static final String SP_KEY_ACCESS_TOKEN_UPLOAD = "accessTokenUp25";
    public static final String SP_KEY_ELECTION_TYPE = "electionType25";
    public static final String SP_KEY_FCM_TOKEN = "fcmToken25";
    public static final String SP_KEY_HAS_UPDATE_INITIALIZATION_STATUS = "hasUpdateInitializationStatus25";
    public static final String SP_KEY_HIDE_COMPLETED_ELECTION_PAGES = "hideElectionPages25";
    public static final String SP_KEY_INITIALIZATION_BYPASS = "InitializationBypass25";
    public static final String SP_KEY_INITIALIZATION_USER_STATUS = "initializationUserStatus25";
    public static final String SP_KEY_IS_USER_PROMOTED = "userPromoted25";
    public static final String SP_KEY_JWT_SUB = "jwtSub25";
    public static final String SP_KEY_JWT_SUBJECT = "jwtSubject25";
    public static final String SP_KEY_KODE_AKSES = "kodeAkses25";
    public static final String SP_KEY_KODE_TPS = "kodeTps25";
    public static final String SP_KEY_KODE_WILAYAH = "kodeWilayah25";
    public static final String SP_KEY_PHONE_NUMBER = "phoneNumber25";
    public static final String SP_KEY_PROFILE = "profile25";
    public static final String SP_KEY_PROFILE_END = "profileEnd25";
    public static final String SP_KEY_PROFILE_START = "profileStart25";
    public static final String SP_KEY_REFRESH_TOKEN = "refreshToken25";
    public static final String SP_KEY_ROLE = "role25";
    public static final String SP_KEY_USER_ID = "userIdStr25";
    public static final String SP_KEY_USER_REAL_NAME = "userRealNameStr25";
    public static final String SP_KEY_USER_ROLE = "userRole25";
    public static final String SP_KEY_USER_SID = "userSIdStr25";
    public static final String SP_KEY_USER_STATUS = "userStatus25";
    public static final String SP_KEY_VISION_WAIT_UNTIL = "visionWaitUntil25";
    public static final String SP_STATUS_INITIALIZATION = "status_initialization25";
    private static final int SP_VERSION = 25;

    private SharedPreferencesModule() {
    }

    @Provides
    @Singleton
    public final SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…xt.MODE_PRIVATE\n        )");
        return sharedPreferences;
    }
}
