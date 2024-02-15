package org.bouncycastle.mime;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public interface MimeContext {
    InputStream applyContext(Headers headers, InputStream inputStream) throws IOException;
}
