package net.openid.appauth;

import java.util.Map;

/* loaded from: classes2.dex */
public interface ClientAuthentication {
    Map<String, String> getRequestHeaders(String clientId);

    Map<String, String> getRequestParameters(String clientId);

    /* loaded from: classes2.dex */
    public static class UnsupportedAuthenticationMethod extends Exception {
        private String mAuthMethod;

        public UnsupportedAuthenticationMethod(String field) {
            super("Unsupported client authentication method: " + field);
            this.mAuthMethod = field;
        }

        public String getUnsupportedAuthenticationMethod() {
            return this.mAuthMethod;
        }
    }
}
