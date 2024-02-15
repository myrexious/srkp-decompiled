package org.informatika.sirekap.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.openid.appauth.TokenRequest;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.informatika.sirekap.BuildConfig;

/* compiled from: BasicAuthInterceptor.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/api/BasicAuthInterceptor;", "Lokhttp3/Interceptor;", "username", "", TokenRequest.GRANT_TYPE_PASSWORD, "(Ljava/lang/String;Ljava/lang/String;)V", "credentials", "versionName", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BasicAuthInterceptor implements Interceptor {
    private String credentials;
    private String versionName;

    public BasicAuthInterceptor(String username, String password) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        String basic = Credentials.basic(username, password);
        Intrinsics.checkNotNullExpressionValue(basic, "basic(username, password)");
        this.credentials = basic;
        this.versionName = BuildConfig.VERSION_NAME;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response proceed = chain.proceed(chain.request().newBuilder().header("Authorization", this.credentials).header("Sirekap", this.versionName).build());
        Intrinsics.checkNotNullExpressionValue(proceed, "chain.proceed(request)");
        return proceed;
    }
}
