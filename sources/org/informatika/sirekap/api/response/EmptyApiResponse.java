package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;

/* compiled from: EmptyApiResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/EmptyApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getData", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmptyApiResponse extends GenericApiResponse {
    private final Integer data;

    public final Integer getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmptyApiResponse(boolean z, String message, String str, Integer num) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        this.data = num;
    }
}
