package org.informatika.sirekap.api.response;

import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.GenericApiResponse;
import org.informatika.sirekap.model.Witness;

/* compiled from: WitnessApiResponse.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/api/response/WitnessApiResponse;", "Lorg/informatika/sirekap/api/GenericApiResponse;", "isSuccess", "", "message", "", "trace", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "Lorg/informatika/sirekap/model/Witness;", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WitnessApiResponse extends GenericApiResponse {
    private final List<Witness> data;

    public final List<Witness> getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WitnessApiResponse(boolean z, String message, String str, List<Witness> data) {
        super(z, message, str);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }
}
