package org.bouncycastle.est.jcajce;

import java.io.IOException;
import javax.net.ssl.SSLSession;

/* loaded from: classes2.dex */
public interface JsseHostnameAuthorizer {
    boolean verified(String str, SSLSession sSLSession) throws IOException;
}
