package org.informatika.sirekap.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.openid.appauth.ResponseTypeValues;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.api.BasicAuthInterceptor;
import org.informatika.sirekap.api.CertmanAPIInterface;
import org.informatika.sirekap.api.CertmanCertificateBucketAPIInterface;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.support.livedata.LiveDataCallAdapterFactory;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: ApiModule.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007J$\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0012\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\f\u001a\u00020\u000bH\u0007J\u001a\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u001a\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u0017\u001a\u00020\u000bH\u0007J\b\u0010\u0018\u001a\u00020\u000bH\u0007R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/di/ApiModule;", "", "()V", "certificatePinner", "Lokhttp3/CertificatePinner;", "kotlin.jvm.PlatformType", "getCertificatePinner", "()Lokhttp3/CertificatePinner;", "providesAppService", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "authInterceptor", "Lokhttp3/Interceptor;", "loggingInterceptor", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "providesAppServiceUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "providesBucketCertificateApiInterface", "Lorg/informatika/sirekap/api/CertmanCertificateBucketAPIInterface;", "providesCertmanAPIInterface", "Lorg/informatika/sirekap/api/CertmanAPIInterface;", "providesKeyCloakApi", "Lorg/informatika/sirekap/api/KeyCloakApiInterface;", "providesOkHttpAuthInterceptor", "providesOkHttpLoggingInterceptor", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public final class ApiModule {
    private final CertificatePinner certificatePinner = new CertificatePinner.Builder().add("*.informatika.site", "sha256/J9kw6dgzCjfYzbkcRI5wWopVetBWzBVOE0PQTjNIvR8=").add("sirekap-api.kpu.go.id", "sha256/KmcDr7BnbUYld/gYxEq0QF61Gp8eWHa7DYyccW9ks1E=").add("*.kpu.go.id", "sha256/y+bqp7616s6Ij+XlzXb9R3rWKl+gD4QFfg4m6gFRK+Q=").build();

    public final CertificatePinner getCertificatePinner() {
        return this.certificatePinner;
    }

    @Provides
    public final Interceptor providesOkHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    public final Interceptor providesOkHttpAuthInterceptor() {
        return new BasicAuthInterceptor("user", "pass");
    }

    @Provides
    public final SirekapApiInterface providesAppService(Interceptor authInterceptor, Interceptor loggingInterceptor, final EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(authInterceptor, "authInterceptor");
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Gson create = new GsonBuilder().setLenient().create();
        Object create2 = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(new OkHttpClient.Builder().certificatePinner(this.certificatePinner).connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).addInterceptor(authInterceptor).addInterceptor(new Interceptor() { // from class: org.informatika.sirekap.di.ApiModule$$ExternalSyntheticLambda3
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response providesAppService$lambda$0;
                providesAppService$lambda$0 = ApiModule.providesAppService$lambda$0(EncryptedSharedPreferences.this, chain);
                return providesAppService$lambda$0;
            }
        }).addInterceptor(loggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create(create)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addCallAdapterFactory(new LiveDataCallAdapterFactory()).build().create(SirekapApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create2, "retrofit.create(SirekapApiInterface::class.java)");
        return (SirekapApiInterface) create2;
    }

    public static final Response providesAppService$lambda$0(EncryptedSharedPreferences encryptedSharedPreferences, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "$encryptedSharedPreferences");
        Request.Builder newBuilder = chain.request().newBuilder();
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN, "");
        String str = stringEncrypted;
        if (!(str == null || StringsKt.isBlank(str))) {
            newBuilder.addHeader(ResponseTypeValues.TOKEN, stringEncrypted);
        } else {
            encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN);
        }
        return chain.proceed(newBuilder.build());
    }

    @Provides
    public final KeyCloakApiInterface providesKeyCloakApi(Interceptor loggingInterceptor, final EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Gson create = new GsonBuilder().setLenient().create();
        Object create2 = new Retrofit.Builder().baseUrl("https://sso.kpu.go.id/realms/badan_adhoc/protocol/").client(new OkHttpClient.Builder().certificatePinner(this.certificatePinner).connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).addInterceptor(new Interceptor() { // from class: org.informatika.sirekap.di.ApiModule$$ExternalSyntheticLambda1
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response providesKeyCloakApi$lambda$1;
                providesKeyCloakApi$lambda$1 = ApiModule.providesKeyCloakApi$lambda$1(EncryptedSharedPreferences.this, chain);
                return providesKeyCloakApi$lambda$1;
            }
        }).addInterceptor(loggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create(create)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addCallAdapterFactory(new LiveDataCallAdapterFactory()).build().create(KeyCloakApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create2, "retrofit.create(KeyCloakApiInterface::class.java)");
        return (KeyCloakApiInterface) create2;
    }

    public static final Response providesKeyCloakApi$lambda$1(EncryptedSharedPreferences encryptedSharedPreferences, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "$encryptedSharedPreferences");
        Request.Builder newBuilder = chain.request().newBuilder();
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN, "");
        String str = stringEncrypted;
        if (!(str == null || StringsKt.isBlank(str))) {
            newBuilder.addHeader("Authorization", "Bearer " + stringEncrypted);
        } else {
            encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN);
        }
        return chain.proceed(newBuilder.build());
    }

    @Provides
    public final SirekapApiInterfaceUpload providesAppServiceUpload(Interceptor authInterceptor, Interceptor loggingInterceptor, final EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(authInterceptor, "authInterceptor");
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Gson create = new GsonBuilder().setLenient().create();
        Object create2 = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(new OkHttpClient.Builder().certificatePinner(this.certificatePinner).connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).addInterceptor(authInterceptor).addInterceptor(new Interceptor() { // from class: org.informatika.sirekap.di.ApiModule$$ExternalSyntheticLambda0
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response providesAppServiceUpload$lambda$2;
                providesAppServiceUpload$lambda$2 = ApiModule.providesAppServiceUpload$lambda$2(EncryptedSharedPreferences.this, chain);
                return providesAppServiceUpload$lambda$2;
            }
        }).addInterceptor(loggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create(create)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addCallAdapterFactory(new LiveDataCallAdapterFactory()).build().create(SirekapApiInterfaceUpload.class);
        Intrinsics.checkNotNullExpressionValue(create2, "retrofit.create(SirekapA…erfaceUpload::class.java)");
        return (SirekapApiInterfaceUpload) create2;
    }

    public static final Response providesAppServiceUpload$lambda$2(EncryptedSharedPreferences encryptedSharedPreferences, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "$encryptedSharedPreferences");
        Request.Builder newBuilder = chain.request().newBuilder();
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN_UPLOAD, "");
        String str = stringEncrypted;
        if (!(str == null || StringsKt.isBlank(str))) {
            newBuilder.addHeader(ResponseTypeValues.TOKEN, stringEncrypted);
        }
        return chain.proceed(newBuilder.build());
    }

    @Provides
    public final CertmanAPIInterface providesCertmanAPIInterface(Interceptor loggingInterceptor, final EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Gson create = new GsonBuilder().setLenient().create();
        Object create2 = new Retrofit.Builder().baseUrl("https://sirekap-api.kpu.go.id/").client(new OkHttpClient.Builder().certificatePinner(this.certificatePinner).connectTimeout(180L, TimeUnit.SECONDS).readTimeout(180L, TimeUnit.SECONDS).writeTimeout(180L, TimeUnit.SECONDS).addInterceptor(new Interceptor() { // from class: org.informatika.sirekap.di.ApiModule$$ExternalSyntheticLambda2
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response providesCertmanAPIInterface$lambda$3;
                providesCertmanAPIInterface$lambda$3 = ApiModule.providesCertmanAPIInterface$lambda$3(EncryptedSharedPreferences.this, chain);
                return providesCertmanAPIInterface$lambda$3;
            }
        }).addInterceptor(loggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create(create)).build().create(CertmanAPIInterface.class);
        Intrinsics.checkNotNullExpressionValue(create2, "retrofit.create(CertmanAPIInterface::class.java)");
        return (CertmanAPIInterface) create2;
    }

    public static final Response providesCertmanAPIInterface$lambda$3(EncryptedSharedPreferences encryptedSharedPreferences, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "$encryptedSharedPreferences");
        Request.Builder newBuilder = chain.request().newBuilder();
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN, "");
        String str = stringEncrypted;
        if (!(str == null || StringsKt.isBlank(str))) {
            newBuilder.addHeader("Authorization", "Bearer " + stringEncrypted);
        } else {
            encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN);
        }
        return chain.proceed(newBuilder.build());
    }

    @Provides
    public final CertmanCertificateBucketAPIInterface providesBucketCertificateApiInterface(Interceptor loggingInterceptor) {
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Object create = new Retrofit.Builder().baseUrl("https://sirekap-api.kpu.go.id/").client(new OkHttpClient.Builder().connectTimeout(180L, TimeUnit.SECONDS).readTimeout(180L, TimeUnit.SECONDS).writeTimeout(180L, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).build()).build().create(CertmanCertificateBucketAPIInterface.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(CertmanC…APIInterface::class.java)");
        return (CertmanCertificateBucketAPIInterface) create;
    }
}
