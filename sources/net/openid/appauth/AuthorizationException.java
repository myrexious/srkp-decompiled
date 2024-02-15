package net.openid.appauth;

import android.content.Intent;
import android.net.Uri;
import androidx.collection.ArrayMap;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.Collections;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.features2d.FeatureDetector;

/* loaded from: classes2.dex */
public final class AuthorizationException extends Exception {
    public static final String EXTRA_EXCEPTION = "net.openid.appauth.AuthorizationException";
    private static final int HASH_MULTIPLIER = 31;
    static final String KEY_CODE = "code";
    static final String KEY_ERROR = "error";
    static final String KEY_ERROR_DESCRIPTION = "errorDescription";
    static final String KEY_ERROR_URI = "errorUri";
    static final String KEY_TYPE = "type";
    public static final String PARAM_ERROR = "error";
    public static final String PARAM_ERROR_DESCRIPTION = "error_description";
    public static final String PARAM_ERROR_URI = "error_uri";
    public static final int TYPE_GENERAL_ERROR = 0;
    public static final int TYPE_OAUTH_AUTHORIZATION_ERROR = 1;
    public static final int TYPE_OAUTH_REGISTRATION_ERROR = 4;
    public static final int TYPE_OAUTH_TOKEN_ERROR = 2;
    public static final int TYPE_RESOURCE_SERVER_AUTHORIZATION_ERROR = 3;
    public final int code;
    public final String error;
    public final String errorDescription;
    public final Uri errorUri;
    public final int type;

    /* loaded from: classes2.dex */
    public static final class GeneralErrors {
        public static final AuthorizationException INVALID_DISCOVERY_DOCUMENT = AuthorizationException.generalEx(0, "Invalid discovery document");
        public static final AuthorizationException USER_CANCELED_AUTH_FLOW = AuthorizationException.generalEx(1, "User cancelled flow");
        public static final AuthorizationException PROGRAM_CANCELED_AUTH_FLOW = AuthorizationException.generalEx(2, "Flow cancelled programmatically");
        public static final AuthorizationException NETWORK_ERROR = AuthorizationException.generalEx(3, "Network error");
        public static final AuthorizationException SERVER_ERROR = AuthorizationException.generalEx(4, "Server error");
        public static final AuthorizationException JSON_DESERIALIZATION_ERROR = AuthorizationException.generalEx(5, "JSON deserialization error");
        public static final AuthorizationException TOKEN_RESPONSE_CONSTRUCTION_ERROR = AuthorizationException.generalEx(6, "Token response construction error");
        public static final AuthorizationException INVALID_REGISTRATION_RESPONSE = AuthorizationException.generalEx(7, "Invalid registration response");
        public static final AuthorizationException ID_TOKEN_PARSING_ERROR = AuthorizationException.generalEx(8, "Unable to parse ID Token");
        public static final AuthorizationException ID_TOKEN_VALIDATION_ERROR = AuthorizationException.generalEx(9, "Invalid ID Token");
    }

    /* loaded from: classes2.dex */
    public static final class AuthorizationRequestErrors {
        public static final AuthorizationException ACCESS_DENIED;
        public static final AuthorizationException CLIENT_ERROR;
        public static final AuthorizationException INVALID_REQUEST;
        public static final AuthorizationException INVALID_SCOPE;
        public static final AuthorizationException OTHER;
        public static final AuthorizationException SERVER_ERROR;
        public static final AuthorizationException STATE_MISMATCH;
        private static final Map<String, AuthorizationException> STRING_TO_EXCEPTION;
        public static final AuthorizationException TEMPORARILY_UNAVAILABLE;
        public static final AuthorizationException UNAUTHORIZED_CLIENT;
        public static final AuthorizationException UNSUPPORTED_RESPONSE_TYPE;

        static {
            AuthorizationException authEx = AuthorizationException.authEx(1000, "invalid_request");
            INVALID_REQUEST = authEx;
            AuthorizationException authEx2 = AuthorizationException.authEx(1001, "unauthorized_client");
            UNAUTHORIZED_CLIENT = authEx2;
            AuthorizationException authEx3 = AuthorizationException.authEx(1002, "access_denied");
            ACCESS_DENIED = authEx3;
            AuthorizationException authEx4 = AuthorizationException.authEx(1003, "unsupported_response_type");
            UNSUPPORTED_RESPONSE_TYPE = authEx4;
            AuthorizationException authEx5 = AuthorizationException.authEx(1004, "invalid_scope");
            INVALID_SCOPE = authEx5;
            AuthorizationException authEx6 = AuthorizationException.authEx(1005, "server_error");
            SERVER_ERROR = authEx6;
            AuthorizationException authEx7 = AuthorizationException.authEx(1006, "temporarily_unavailable");
            TEMPORARILY_UNAVAILABLE = authEx7;
            AuthorizationException authEx8 = AuthorizationException.authEx(1007, null);
            CLIENT_ERROR = authEx8;
            AuthorizationException authEx9 = AuthorizationException.authEx(1008, null);
            OTHER = authEx9;
            STATE_MISMATCH = AuthorizationException.generalEx(9, "Response state param did not match request state");
            STRING_TO_EXCEPTION = AuthorizationException.exceptionMapByString(authEx, authEx2, authEx3, authEx4, authEx5, authEx6, authEx7, authEx8, authEx9);
        }

        public static AuthorizationException byString(String error) {
            AuthorizationException authorizationException = STRING_TO_EXCEPTION.get(error);
            return authorizationException != null ? authorizationException : OTHER;
        }
    }

    /* loaded from: classes2.dex */
    public static final class TokenRequestErrors {
        public static final AuthorizationException CLIENT_ERROR;
        public static final AuthorizationException INVALID_CLIENT;
        public static final AuthorizationException INVALID_GRANT;
        public static final AuthorizationException INVALID_REQUEST;
        public static final AuthorizationException INVALID_SCOPE;
        public static final AuthorizationException OTHER;
        private static final Map<String, AuthorizationException> STRING_TO_EXCEPTION;
        public static final AuthorizationException UNAUTHORIZED_CLIENT;
        public static final AuthorizationException UNSUPPORTED_GRANT_TYPE;

        static {
            AuthorizationException authorizationException = AuthorizationException.tokenEx(2000, "invalid_request");
            INVALID_REQUEST = authorizationException;
            AuthorizationException authorizationException2 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_FAST, "invalid_client");
            INVALID_CLIENT = authorizationException2;
            AuthorizationException authorizationException3 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_STAR, "invalid_grant");
            INVALID_GRANT = authorizationException3;
            AuthorizationException authorizationException4 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_SIFT, "unauthorized_client");
            UNAUTHORIZED_CLIENT = authorizationException4;
            AuthorizationException authorizationException5 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_SURF, "unsupported_grant_type");
            UNSUPPORTED_GRANT_TYPE = authorizationException5;
            AuthorizationException authorizationException6 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_ORB, "invalid_scope");
            INVALID_SCOPE = authorizationException6;
            AuthorizationException authorizationException7 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_MSER, null);
            CLIENT_ERROR = authorizationException7;
            AuthorizationException authorizationException8 = AuthorizationException.tokenEx(FeatureDetector.PYRAMID_GFTT, null);
            OTHER = authorizationException8;
            STRING_TO_EXCEPTION = AuthorizationException.exceptionMapByString(authorizationException, authorizationException2, authorizationException3, authorizationException4, authorizationException5, authorizationException6, authorizationException7, authorizationException8);
        }

        public static AuthorizationException byString(String error) {
            AuthorizationException authorizationException = STRING_TO_EXCEPTION.get(error);
            return authorizationException != null ? authorizationException : OTHER;
        }
    }

    /* loaded from: classes2.dex */
    public static final class RegistrationRequestErrors {
        public static final AuthorizationException CLIENT_ERROR;
        public static final AuthorizationException INVALID_CLIENT_METADATA;
        public static final AuthorizationException INVALID_REDIRECT_URI;
        public static final AuthorizationException INVALID_REQUEST;
        public static final AuthorizationException OTHER;
        private static final Map<String, AuthorizationException> STRING_TO_EXCEPTION;

        static {
            AuthorizationException registrationEx = AuthorizationException.registrationEx(SerializerCache.DEFAULT_MAX_CACHED, "invalid_request");
            INVALID_REQUEST = registrationEx;
            AuthorizationException registrationEx2 = AuthorizationException.registrationEx(4001, "invalid_redirect_uri");
            INVALID_REDIRECT_URI = registrationEx2;
            AuthorizationException registrationEx3 = AuthorizationException.registrationEx(4002, "invalid_client_metadata");
            INVALID_CLIENT_METADATA = registrationEx3;
            AuthorizationException registrationEx4 = AuthorizationException.registrationEx(4003, null);
            CLIENT_ERROR = registrationEx4;
            AuthorizationException registrationEx5 = AuthorizationException.registrationEx(4004, null);
            OTHER = registrationEx5;
            STRING_TO_EXCEPTION = AuthorizationException.exceptionMapByString(registrationEx, registrationEx2, registrationEx3, registrationEx4, registrationEx5);
        }

        public static AuthorizationException byString(String error) {
            AuthorizationException authorizationException = STRING_TO_EXCEPTION.get(error);
            return authorizationException != null ? authorizationException : OTHER;
        }
    }

    public static AuthorizationException generalEx(int code, String errorDescription) {
        return new AuthorizationException(0, code, null, errorDescription, null, null);
    }

    public static AuthorizationException authEx(int code, String error) {
        return new AuthorizationException(1, code, error, null, null, null);
    }

    public static AuthorizationException tokenEx(int code, String error) {
        return new AuthorizationException(2, code, error, null, null, null);
    }

    public static AuthorizationException registrationEx(int code, String error) {
        return new AuthorizationException(4, code, error, null, null, null);
    }

    public static AuthorizationException fromTemplate(AuthorizationException ex, Throwable rootCause) {
        return new AuthorizationException(ex.type, ex.code, ex.error, ex.errorDescription, ex.errorUri, rootCause);
    }

    public static AuthorizationException fromOAuthTemplate(AuthorizationException ex, String errorOverride, String errorDescriptionOverride, Uri errorUriOverride) {
        int i = ex.type;
        int i2 = ex.code;
        if (errorOverride == null) {
            errorOverride = ex.error;
        }
        String str = errorOverride;
        if (errorDescriptionOverride == null) {
            errorDescriptionOverride = ex.errorDescription;
        }
        String str2 = errorDescriptionOverride;
        if (errorUriOverride == null) {
            errorUriOverride = ex.errorUri;
        }
        return new AuthorizationException(i, i2, str, str2, errorUriOverride, null);
    }

    public static AuthorizationException fromOAuthRedirect(Uri redirectUri) {
        String queryParameter = redirectUri.getQueryParameter("error");
        String queryParameter2 = redirectUri.getQueryParameter(PARAM_ERROR_DESCRIPTION);
        String queryParameter3 = redirectUri.getQueryParameter(PARAM_ERROR_URI);
        AuthorizationException byString = AuthorizationRequestErrors.byString(queryParameter);
        int i = byString.type;
        int i2 = byString.code;
        if (queryParameter2 == null) {
            queryParameter2 = byString.errorDescription;
        }
        return new AuthorizationException(i, i2, queryParameter, queryParameter2, queryParameter3 != null ? Uri.parse(queryParameter3) : byString.errorUri, null);
    }

    public static AuthorizationException fromJson(String jsonStr) throws JSONException {
        Preconditions.checkNotEmpty(jsonStr, "jsonStr cannot be null or empty");
        return fromJson(new JSONObject(jsonStr));
    }

    public static AuthorizationException fromJson(JSONObject json) throws JSONException {
        Preconditions.checkNotNull(json, "json cannot be null");
        return new AuthorizationException(json.getInt("type"), json.getInt("code"), JsonUtil.getStringIfDefined(json, "error"), JsonUtil.getStringIfDefined(json, KEY_ERROR_DESCRIPTION), JsonUtil.getUriIfDefined(json, KEY_ERROR_URI), null);
    }

    public static AuthorizationException fromIntent(Intent data) {
        Preconditions.checkNotNull(data);
        if (data.hasExtra(EXTRA_EXCEPTION)) {
            try {
                return fromJson(data.getStringExtra(EXTRA_EXCEPTION));
            } catch (JSONException e) {
                throw new IllegalArgumentException("Intent contains malformed exception data", e);
            }
        }
        return null;
    }

    public static Map<String, AuthorizationException> exceptionMapByString(AuthorizationException... exceptions) {
        ArrayMap arrayMap = new ArrayMap(exceptions != null ? exceptions.length : 0);
        if (exceptions != null) {
            for (AuthorizationException authorizationException : exceptions) {
                String str = authorizationException.error;
                if (str != null) {
                    arrayMap.put(str, authorizationException);
                }
            }
        }
        return Collections.unmodifiableMap(arrayMap);
    }

    public AuthorizationException(int type, int code, String error, String errorDescription, Uri errorUri, Throwable rootCause) {
        super(errorDescription, rootCause);
        this.type = type;
        this.code = code;
        this.error = error;
        this.errorDescription = errorDescription;
        this.errorUri = errorUri;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.put(jSONObject, "type", this.type);
        JsonUtil.put(jSONObject, "code", this.code);
        JsonUtil.putIfNotNull(jSONObject, "error", this.error);
        JsonUtil.putIfNotNull(jSONObject, KEY_ERROR_DESCRIPTION, this.errorDescription);
        JsonUtil.putIfNotNull(jSONObject, KEY_ERROR_URI, this.errorUri);
        return jSONObject;
    }

    public String toJsonString() {
        return toJson().toString();
    }

    public Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_EXCEPTION, toJsonString());
        return intent;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthorizationException)) {
            return false;
        }
        AuthorizationException authorizationException = (AuthorizationException) obj;
        return this.type == authorizationException.type && this.code == authorizationException.code;
    }

    public int hashCode() {
        return ((this.type + 31) * 31) + this.code;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "AuthorizationException: " + toJsonString();
    }
}
