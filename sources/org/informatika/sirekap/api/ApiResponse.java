package org.informatika.sirekap.api;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Response;

/* compiled from: ApiResponse.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0004*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/api/ApiResponse;", "T", "", "()V", "Companion", "Lorg/informatika/sirekap/api/ApiEmptyResponse;", "Lorg/informatika/sirekap/api/ApiErrorResponse;", "Lorg/informatika/sirekap/api/ApiSuccessResponse;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class ApiResponse<T> {
    public static final Companion Companion = new Companion(null);

    public /* synthetic */ ApiResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ApiResponse() {
    }

    /* compiled from: ApiResponse.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J \u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\b\"\u0004\b\u0001\u0010\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\n¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/api/ApiResponse$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/api/ApiErrorResponse;", "T", "error", "", "Lorg/informatika/sirekap/api/ApiResponse;", "response", "Lretrofit2/Response;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> ApiErrorResponse<T> create(Throwable error) {
            Intrinsics.checkNotNullParameter(error, "error");
            String message = error.getMessage();
            if (message == null) {
                message = "Error create api response";
            }
            return new ApiErrorResponse<>(message);
        }

        public final <T> ApiResponse<T> create(Response<T> response) {
            Intrinsics.checkNotNullParameter(response, "response");
            if (response.isSuccessful()) {
                T body = response.body();
                if (body == null || response.code() == 204) {
                    return new ApiEmptyResponse();
                }
                GenericApiResponse genericApiResponse = (GenericApiResponse) body;
                if (genericApiResponse.isSuccess()) {
                    return new ApiSuccessResponse(body, response.headers().get("link"));
                }
                String message = genericApiResponse.getMessage();
                if (message == null) {
                    message = "Failed request";
                }
                return new ApiErrorResponse(message);
            } else if (response.code() == 502) {
                return new ApiErrorResponse("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
            } else {
                ResponseBody errorBody = response.errorBody();
                String string = errorBody != null ? errorBody.string() : null;
                String str = string;
                if (str == null || str.length() == 0) {
                    string = response.message();
                }
                if (string == null) {
                    string = "Response error";
                }
                return new ApiErrorResponse(string);
            }
        }
    }
}
