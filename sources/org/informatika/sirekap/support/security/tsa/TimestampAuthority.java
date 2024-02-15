package org.informatika.sirekap.support.security.tsa;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import net.openid.appauth.ResponseTypeValues;
import org.bouncycastle.tsp.TimeStampToken;

/* compiled from: TimestampAuthority.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/TimestampAuthority;", "", "getTimestampToken", "Lorg/bouncycastle/tsp/TimeStampToken;", ResponseTypeValues.TOKEN, "Ljava/io/InputStream;", "(Ljava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TimestampAuthority {
    Object getTimestampToken(InputStream inputStream, Continuation<? super TimeStampToken> continuation);
}
