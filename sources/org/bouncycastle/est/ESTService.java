package org.bouncycastle.est;

import androidx.browser.trusted.sharing.ShareTarget;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.TimeZones;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cmc.CMCException;
import org.bouncycastle.cmc.SimplePKIResponse;
import org.bouncycastle.mime.BasicMimeParser;
import org.bouncycastle.mime.ConstantMimeContext;
import org.bouncycastle.mime.Headers;
import org.bouncycastle.mime.MimeContext;
import org.bouncycastle.mime.MimeParserContext;
import org.bouncycastle.mime.MimeParserListener;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;

/* loaded from: classes2.dex */
public class ESTService {
    protected static final String CACERTS = "/cacerts";
    protected static final String CSRATTRS = "/csrattrs";
    protected static final String FULLCMC = "/fullcmc";
    protected static final String SERVERGEN = "/serverkeygen";
    protected static final String SIMPLE_ENROLL = "/simpleenroll";
    protected static final String SIMPLE_REENROLL = "/simplereenroll";
    protected static final Set<String> illegalParts;
    private static final Pattern pathInValid;
    private final ESTClientProvider clientProvider;
    private final String server;

    static {
        HashSet hashSet = new HashSet();
        illegalParts = hashSet;
        hashSet.add("cacerts");
        hashSet.add("simpleenroll");
        hashSet.add("simplereenroll");
        hashSet.add("fullcmc");
        hashSet.add("serverkeygen");
        hashSet.add("csrattrs");
        pathInValid = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;:=]+");
    }

    public ESTService(String str, String str2, ESTClientProvider eSTClientProvider) {
        StringBuilder append;
        String str3;
        String verifyServer = verifyServer(str);
        if (str2 != null) {
            str3 = verifyLabel(str2);
            append = new StringBuilder("https://").append(verifyServer).append("/.well-known/est/");
        } else {
            append = new StringBuilder("https://").append(verifyServer);
            str3 = "/.well-known/est";
        }
        this.server = append.append(str3).toString();
        this.clientProvider = eSTClientProvider;
    }

    public String annotateRequest(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        int i = 0;
        do {
            int i2 = i + 48;
            if (i2 < bArr.length) {
                printWriter.print(Base64.toBase64String(bArr, i, 48));
                i = i2;
            } else {
                printWriter.print(Base64.toBase64String(bArr, i, bArr.length - i));
                i = bArr.length;
            }
            printWriter.print('\n');
        } while (i < bArr.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return storeToArray(store, null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> matches = store.getMatches(selector);
        return (X509CertificateHolder[]) matches.toArray(new X509CertificateHolder[matches.size()]);
    }

    private String verifyLabel(String str) {
        while (str.endsWith(RemoteSettings.FORWARD_SLASH_STRING) && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        while (str.startsWith(RemoteSettings.FORWARD_SLASH_STRING) && str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() != 0) {
            if (pathInValid.matcher(str).matches()) {
                if (illegalParts.contains(str)) {
                    throw new IllegalArgumentException("Label " + str + " is a reserved path segment.");
                }
                return str;
            }
            throw new IllegalArgumentException("Server path " + str + " contains invalid characters");
        }
        throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
    }

    private String verifyServer(String str) {
        while (str.endsWith(RemoteSettings.FORWARD_SLASH_STRING) && str.length() > 0) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (Exception e) {
                if (e instanceof IllegalArgumentException) {
                    throw ((IllegalArgumentException) e);
                }
                throw new IllegalArgumentException("Scheme and host is invalid: " + e.getMessage(), e);
            }
        }
        if (str.contains("://")) {
            throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
        }
        URL url = new URL("https://" + str);
        if (url.getPath().length() != 0 && !url.getPath().equals(RemoteSettings.FORWARD_SLASH_STRING)) {
            throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
        }
        return str;
    }

    protected EnrollmentResponse enroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth, boolean z2) throws IOException {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                byte[] bytes = annotateRequest(pKCS10CertificationRequest.getEncoded()).getBytes();
                URL url = new URL(this.server + (z2 ? SERVERGEN : z ? SIMPLE_REENROLL : SIMPLE_ENROLL));
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withClient = new ESTRequestBuilder(ShareTarget.METHOD_POST, url).withData(bytes).withClient(makeClient);
                withClient.addHeader("Content-Type", "application/pkcs10");
                withClient.addHeader("Content-Length", "" + bytes.length);
                withClient.addHeader("Content-Transfer-Encoding", "base64");
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withClient);
                }
                eSTResponse = makeClient.doRequest(withClient.build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse enrollPop(boolean z, final PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, final ContentSigner contentSigner, ESTAuth eSTAuth, boolean z2) throws IOException {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                URL url = new URL(this.server + (z ? SIMPLE_REENROLL : SIMPLE_ENROLL));
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withConnectionListener = new ESTRequestBuilder(ShareTarget.METHOD_POST, url).withClient(makeClient).withConnectionListener(new ESTSourceConnectionListener() { // from class: org.bouncycastle.est.ESTService.1
                    @Override // org.bouncycastle.est.ESTSourceConnectionListener
                    public ESTRequest onConnection(Source source, ESTRequest eSTRequest) throws IOException {
                        if (source instanceof TLSUniqueProvider) {
                            TLSUniqueProvider tLSUniqueProvider = (TLSUniqueProvider) source;
                            if (tLSUniqueProvider.isTLSUniqueAvailable()) {
                                PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder2 = new PKCS10CertificationRequestBuilder(pKCS10CertificationRequestBuilder);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                pKCS10CertificationRequestBuilder2.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, new DERPrintableString(Base64.toBase64String(tLSUniqueProvider.getTLSUnique())));
                                byteArrayOutputStream.write(ESTService.this.annotateRequest(pKCS10CertificationRequestBuilder2.build(contentSigner).getEncoded()).getBytes());
                                byteArrayOutputStream.flush();
                                ESTRequestBuilder withData = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                                withData.setHeader("Content-Type", "application/pkcs10");
                                withData.setHeader("Content-Transfer-Encoding", "base64");
                                withData.setHeader("Content-Length", Long.toString(byteArrayOutputStream.size()));
                                return withData.build();
                            }
                        }
                        throw new IOException("Source does not supply TLS unique.");
                    }
                });
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withConnectionListener);
                }
                eSTResponse = makeClient.doRequest(withConnectionListener.build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public CACertsResponse getCACerts() throws ESTException {
        URL url;
        ESTRequest build;
        ESTResponse doRequest;
        Store<X509CertificateHolder> store;
        Store<X509CRLHolder> store2;
        Store<X509CRLHolder> store3;
        Store<X509CertificateHolder> store4;
        ESTResponse eSTResponse = null;
        try {
            url = new URL(this.server + CACERTS);
            ESTClient makeClient = this.clientProvider.makeClient();
            build = new ESTRequestBuilder(ShareTarget.METHOD_GET, url).withClient(makeClient).build();
            doRequest = makeClient.doRequest(build);
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (doRequest.getStatusCode() == 200) {
                String firstValue = doRequest.getHeaders().getFirstValue("Content-Type");
                if (firstValue == null || !firstValue.startsWith("application/pkcs7-mime")) {
                    throw new ESTException("Response : " + url.toString() + "Expecting application/pkcs7-mime " + (firstValue != null ? " got " + firstValue : " but was not present."), null, doRequest.getStatusCode(), doRequest.getInputStream());
                }
                if (doRequest.getContentLength() == null || doRequest.getContentLength().longValue() <= 0) {
                    store3 = null;
                    store4 = null;
                } else {
                    SimplePKIResponse simplePKIResponse = new SimplePKIResponse(ContentInfo.getInstance((ASN1Sequence) new ASN1InputStream(doRequest.getInputStream()).readObject()));
                    store4 = simplePKIResponse.getCertificates();
                    store3 = simplePKIResponse.getCRLs();
                }
                store2 = store3;
                store = store4;
            } else if (doRequest.getStatusCode() != 204) {
                throw new ESTException("Get CACerts: " + url.toString(), null, doRequest.getStatusCode(), doRequest.getInputStream());
            } else {
                store = null;
                store2 = null;
            }
            CACertsResponse cACertsResponse = new CACertsResponse(store, store2, build, doRequest.getSource(), this.clientProvider.isTrusted());
            if (doRequest != null) {
                try {
                    doRequest.close();
                } catch (Exception e) {
                    e = e;
                }
            }
            e = null;
            if (e != null) {
                if (e instanceof ESTException) {
                    throw ((ESTException) e);
                }
                throw new ESTException("Get CACerts: " + url.toString(), e, doRequest.getStatusCode(), null);
            }
            return cACertsResponse;
        } catch (Throwable th2) {
            th = th2;
            eSTResponse = doRequest;
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } catch (Throwable th3) {
                if (eSTResponse != null) {
                    try {
                        eSTResponse.close();
                    } catch (Exception unused) {
                    }
                }
                throw th3;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.bouncycastle.est.CSRRequestResponse getCSRAttributes() throws org.bouncycastle.est.ESTException {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.ESTService.getCSRAttributes():org.bouncycastle.est.CSRRequestResponse");
    }

    protected EnrollmentResponse handleEnrollResponse(ESTResponse eSTResponse) throws IOException {
        Object obj;
        long time;
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        if (eSTResponse.getStatusCode() == 202) {
            String header = eSTResponse.getHeader("Retry-After");
            if (header != null) {
                try {
                    try {
                        time = System.currentTimeMillis() + (Long.parseLong(header) * 1000);
                    } catch (NumberFormatException unused) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
                        time = simpleDateFormat.parse(header).getTime();
                    }
                    return new EnrollmentResponse(null, time, originalRequest, eSTResponse.getSource());
                } catch (Exception e) {
                    throw new ESTException("Unable to parse Retry-After header:" + originalRequest.getURL().toString() + StringUtils.SPACE + e.getMessage(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                }
            }
            throw new ESTException("Got Status 202 but not Retry-After header from: " + originalRequest.getURL().toString());
        } else if (eSTResponse.getStatusCode() != 200 || !eSTResponse.getHeaderOrEmpty("content-type").contains("multipart/mixed")) {
            if (eSTResponse.getStatusCode() == 200) {
                try {
                    return new EnrollmentResponse(new SimplePKIResponse(ContentInfo.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())).getCertificates(), -1L, null, eSTResponse.getSource());
                } catch (CMCException e2) {
                    throw new ESTException(e2.getMessage(), e2.getCause());
                }
            }
            throw new ESTException("Simple Enroll: " + originalRequest.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        } else {
            final Object[] objArr = new Object[2];
            new BasicMimeParser(new Headers(eSTResponse.getHeaderOrEmpty("content-type"), "base64"), eSTResponse.getInputStream()).parse(new MimeParserListener() { // from class: org.bouncycastle.est.ESTService.2
                @Override // org.bouncycastle.mime.MimeParserListener
                public MimeContext createContext(MimeParserContext mimeParserContext, Headers headers) {
                    return ConstantMimeContext.Instance;
                }

                @Override // org.bouncycastle.mime.MimeParserListener
                public void object(MimeParserContext mimeParserContext, Headers headers, InputStream inputStream) throws IOException {
                    if (headers.getContentType().contains("application/pkcs8")) {
                        ASN1InputStream aSN1InputStream = new ASN1InputStream(inputStream);
                        objArr[0] = PrivateKeyInfo.getInstance(aSN1InputStream.readObject());
                        if (aSN1InputStream.readObject() != null) {
                            throw new ESTException("Unexpected ASN1 object after private key info");
                        }
                    } else if (headers.getContentType().contains("application/pkcs7-mime")) {
                        ASN1InputStream aSN1InputStream2 = new ASN1InputStream(inputStream);
                        try {
                            objArr[1] = new SimplePKIResponse(ContentInfo.getInstance(aSN1InputStream2.readObject()));
                            if (aSN1InputStream2.readObject() != null) {
                                throw new ESTException("Unexpected ASN1 object after reading certificates");
                            }
                        } catch (CMCException e3) {
                            throw new IOException(e3.getMessage());
                        }
                    }
                }
            });
            if (objArr[0] == null || (obj = objArr[1]) == null) {
                throw new ESTException("received neither private key info and certificates");
            }
            return new EnrollmentResponse(((SimplePKIResponse) obj).getCertificates(), -1L, null, eSTResponse.getSource(), PrivateKeyInfo.getInstance(objArr[0]));
        }
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) throws Exception {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                ESTClient makeClient = this.clientProvider.makeClient();
                eSTResponse = makeClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(makeClient).build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        return enroll(z, pKCS10CertificationRequest, eSTAuth, false);
    }

    public EnrollmentResponse simpleEnrollPoP(boolean z, PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        return enrollPop(z, pKCS10CertificationRequestBuilder, contentSigner, eSTAuth, false);
    }

    public EnrollmentResponse simpleEnrollPopWithServersideCreation(PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        return enrollPop(false, pKCS10CertificationRequestBuilder, contentSigner, eSTAuth, true);
    }

    public EnrollmentResponse simpleEnrollWithServersideCreation(PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        return enroll(false, pKCS10CertificationRequest, eSTAuth, true);
    }
}
