package org.bouncycastle.est.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientSourceProvider;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTRequestBuilder;
import org.bouncycastle.est.ESTResponse;

/* loaded from: classes2.dex */
class DefaultESTClient implements ESTClient {
    private final ESTClientSourceProvider sslSocketProvider;
    private static final Charset utf8 = Charset.forName("UTF-8");
    private static byte[] CRLF = {13, 10};

    /* loaded from: classes2.dex */
    public static class PrintingOutputStream extends OutputStream {
        private final OutputStream tgt;

        public PrintingOutputStream(OutputStream outputStream) {
            this.tgt = outputStream;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            System.out.print(String.valueOf((char) i));
            this.tgt.write(i);
        }
    }

    public DefaultESTClient(ESTClientSourceProvider eSTClientSourceProvider) {
        this.sslSocketProvider = eSTClientSourceProvider;
    }

    private static void writeLine(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes());
        outputStream.write(CRLF);
    }

    @Override // org.bouncycastle.est.ESTClient
    public ESTResponse doRequest(ESTRequest eSTRequest) throws IOException {
        ESTResponse performRequest;
        int i = 15;
        while (true) {
            performRequest = performRequest(eSTRequest);
            ESTRequest redirectURL = redirectURL(performRequest);
            if (redirectURL == null || i - 1 <= 0) {
                break;
            }
            eSTRequest = redirectURL;
        }
        if (i != 0) {
            return performRequest;
        }
        throw new ESTException("Too many redirects..");
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0067 A[Catch: all -> 0x015a, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0089 A[Catch: all -> 0x015a, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x009c A[Catch: all -> 0x015a, TRY_ENTER, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ba A[Catch: all -> 0x015a, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f9 A[Catch: all -> 0x015a, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0144 A[Catch: all -> 0x015a, TRY_LEAVE, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0154 A[Catch: all -> 0x015a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x015a, blocks: (B:52:0x0003, B:54:0x001f, B:55:0x0027, B:57:0x0035, B:60:0x003e, B:62:0x004c, B:64:0x0067, B:66:0x0072, B:68:0x0089, B:69:0x008e, B:72:0x009c, B:73:0x00b6, B:75:0x00bf, B:76:0x00f3, B:78:0x00f9, B:79:0x0108, B:81:0x010b, B:82:0x0130, B:84:0x0144, B:89:0x0154, B:74:0x00ba, B:61:0x0043), top: B:95:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.bouncycastle.est.ESTResponse performRequest(org.bouncycastle.est.ESTRequest r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.jcajce.DefaultESTClient.performRequest(org.bouncycastle.est.ESTRequest):org.bouncycastle.est.ESTResponse");
    }

    protected ESTRequest redirectURL(ESTResponse eSTResponse) throws IOException {
        ESTRequest eSTRequest;
        ESTRequestBuilder withURL;
        if (eSTResponse.getStatusCode() < 300 || eSTResponse.getStatusCode() > 399) {
            eSTRequest = null;
        } else {
            switch (eSTResponse.getStatusCode()) {
                case 301:
                case 302:
                case 303:
                case 306:
                case 307:
                    String header = eSTResponse.getHeader("Location");
                    if (!"".equals(header)) {
                        ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTResponse.getOriginalRequest());
                        if (header.startsWith("http")) {
                            withURL = eSTRequestBuilder.withURL(new URL(header));
                        } else {
                            URL url = eSTResponse.getOriginalRequest().getURL();
                            withURL = eSTRequestBuilder.withURL(new URL(url.getProtocol(), url.getHost(), url.getPort(), header));
                        }
                        eSTRequest = withURL.build();
                        break;
                    } else {
                        throw new ESTException("Redirect status type: " + eSTResponse.getStatusCode() + " but no location header");
                    }
                case 304:
                case 305:
                default:
                    throw new ESTException("Client does not handle http status code: " + eSTResponse.getStatusCode());
            }
        }
        if (eSTRequest != null) {
            eSTResponse.close();
        }
        return eSTRequest;
    }
}
