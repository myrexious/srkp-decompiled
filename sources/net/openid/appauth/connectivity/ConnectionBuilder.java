package net.openid.appauth.connectivity;

import android.net.Uri;
import java.io.IOException;
import java.net.HttpURLConnection;

/* loaded from: classes2.dex */
public interface ConnectionBuilder {
    HttpURLConnection openConnection(Uri uri) throws IOException;
}
