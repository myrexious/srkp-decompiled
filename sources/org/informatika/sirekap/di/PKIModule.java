package org.informatika.sirekap.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.informatika.sirekap.api.PKIApiInterface;
import org.informatika.sirekap.repository.DefaultPKIRepository;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.pki.CRLUtility;
import org.informatika.sirekap.support.security.pki.OCSPUtility;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;
import org.informatika.sirekap.support.security.tsa.BsreTimestampAuthority;
import org.informatika.sirekap.support.security.tsa.DigicertTimestampAuthority;
import org.informatika.sirekap.support.security.tsa.SectigoTimestampAuthority;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import retrofit2.Retrofit;

/* compiled from: PKIModule.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\u000f\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\u0012\u001a\u00020\f2\b\b\u0001\u0010\u000b\u001a\u00020\u0004H\u0007J\u0012\u0010\u0013\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\u0014\u001a\u00020\f2\b\b\u0001\u0010\u000b\u001a\u00020\u0004H\u0007J\u0012\u0010\u0015\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/di/PKIModule;", "", "()V", "protectedSirekapPKI", "Lorg/informatika/sirekap/api/PKIApiInterface;", "loggingInterceptor", "Lokhttp3/Interceptor;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "provideBsreTimestampAuthorityFactory", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;", "api", "Lorg/informatika/sirekap/repository/PKIRepository;", "provideCRLUtility", "Lorg/informatika/sirekap/support/security/pki/CRLUtility;", "provideDigicertTimestampAuthorityFactory", "provideOCSPUtility", "Lorg/informatika/sirekap/support/security/pki/OCSPUtility;", "providePublicRepository", "provideSectigoTimestampAuthorityFactory", "provideSirekapProtectedRepository", "providesPublicPKI", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public final class PKIModule {
    @Provides
    public final PKIApiInterface providesPublicPKI(Interceptor loggingInterceptor) {
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Object create = new Retrofit.Builder().baseUrl("https://sirekap-api.kpu.go.id/").client(new OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).build()).build().create(PKIApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(PKIApiInterface::class.java)");
        return (PKIApiInterface) create;
    }

    @Provides
    public final PKIApiInterface protectedSirekapPKI(Interceptor loggingInterceptor, final EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(loggingInterceptor, "loggingInterceptor");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Object create = new Retrofit.Builder().baseUrl("https://sirekap-api.kpu.go.id/").client(new OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).addInterceptor(new Interceptor() { // from class: org.informatika.sirekap.di.PKIModule$$ExternalSyntheticLambda0
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response protectedSirekapPKI$lambda$0;
                protectedSirekapPKI$lambda$0 = PKIModule.protectedSirekapPKI$lambda$0(EncryptedSharedPreferences.this, chain);
                return protectedSirekapPKI$lambda$0;
            }
        }).addInterceptor(loggingInterceptor).build()).build().create(PKIApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(PKIApiInterface::class.java)");
        return (PKIApiInterface) create;
    }

    public static final Response protectedSirekapPKI$lambda$0(EncryptedSharedPreferences encryptedSharedPreferences, Interceptor.Chain chain) {
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
    public final PKIRepository providePublicRepository(PKIApiInterface api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new DefaultPKIRepository(api);
    }

    @Provides
    public final PKIRepository provideSirekapProtectedRepository(PKIApiInterface api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new DefaultPKIRepository(api);
    }

    @Provides
    public final CRLUtility provideCRLUtility(PKIRepository api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new CRLUtility(api);
    }

    @Provides
    public final OCSPUtility provideOCSPUtility(PKIRepository api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new OCSPUtility(api);
    }

    @Provides
    public final BasicTimestampAuthority.Factory provideDigicertTimestampAuthorityFactory(PKIRepository api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new DigicertTimestampAuthority.Factory(api);
    }

    @Provides
    public final BasicTimestampAuthority.Factory provideSectigoTimestampAuthorityFactory(PKIRepository api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new SectigoTimestampAuthority.Factory(api);
    }

    @Provides
    public final BasicTimestampAuthority.Factory provideBsreTimestampAuthorityFactory(PKIRepository api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return new BsreTimestampAuthority.Factory(api);
    }
}
