package org.informatika.sirekap.support;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.GrantTypeValues;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenRequest;
import org.informatika.sirekap.BuildConfig;

/* compiled from: OpenIdUtil.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/support/OpenIdUtil;", "", "()V", "getAuthRequestBuilder", "Lnet/openid/appauth/AuthorizationRequest$Builder;", "getAuthRequestForKeySetup", "Lnet/openid/appauth/AuthorizationRequest;", "phoneNumber", "", "getTokenRequest", "Lnet/openid/appauth/TokenRequest;", "refreshToken", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OpenIdUtil {
    public final AuthorizationRequest getAuthRequestForKeySetup(String str) {
        AuthorizationRequest.Builder authRequestBuilder = getAuthRequestBuilder();
        authRequestBuilder.setPrompt("login");
        if (str != null) {
            authRequestBuilder.setLoginHint(str);
        }
        AuthorizationRequest build = authRequestBuilder.setScope("openid").build();
        Intrinsics.checkNotNullExpressionValue(build, "builder\n            .set…id\")\n            .build()");
        return build;
    }

    private final AuthorizationRequest.Builder getAuthRequestBuilder() {
        return new AuthorizationRequest.Builder(new AuthorizationServiceConfiguration(Uri.parse("https://sso.kpu.go.id/realms/badan_adhoc/protocol/openid-connect/auth"), Uri.parse("https://sso.kpu.go.id/realms/badan_adhoc/protocol/openid-connect/token")), BuildConfig.MOBILE_AUTH_CLIENT_ID, ResponseTypeValues.CODE, Uri.parse("org.informatika.sirekap://oauth2redirect"));
    }

    public final TokenRequest getTokenRequest(String refreshToken) {
        Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
        TokenRequest build = new TokenRequest.Builder(new AuthorizationServiceConfiguration(Uri.parse("https://sso.kpu.go.id/realms/badan_adhoc/protocol/openid-connect/auth"), Uri.parse("https://sso.kpu.go.id/realms/badan_adhoc/protocol/openid-connect/token")), BuildConfig.MOBILE_AUTH_CLIENT_ID).setGrantType(GrantTypeValues.REFRESH_TOKEN).setRefreshToken(refreshToken).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n            ser…ken)\n            .build()");
        return build;
    }
}
