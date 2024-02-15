package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: AttendancePdfApiResponse.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/api/response/AttendancePdfApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AttendancePdfApiResponse extends GenericApiResponse {
    private final String data;

    public final String getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AttendancePdfApiResponse(boolean z, String message, String str, String str2) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = str2;
    }
}
