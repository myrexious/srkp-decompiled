package net.openid.appauth;

import android.util.Base64;
import java.util.Collections;
import java.util.Map;
import net.openid.appauth.internal.UriUtil;

/* loaded from: classes2.dex */
public class ClientSecretBasic implements ClientAuthentication {
    public static final String NAME = "client_secret_basic";
    private String mClientSecret;

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestParameters(String clientId) {
        return null;
    }

    public ClientSecretBasic(String clientSecret) {
        this.mClientSecret = (String) Preconditions.checkNotNull(clientSecret, "mClientSecret cannot be null");
    }

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestHeaders(String clientId) {
        String formUrlEncodeValue = UriUtil.formUrlEncodeValue(clientId);
        return Collections.singletonMap("Authorization", "Basic " + Base64.encodeToString((formUrlEncodeValue + ":" + UriUtil.formUrlEncodeValue(this.mClientSecret)).getBytes(), 2));
    }
}
