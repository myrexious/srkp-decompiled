package org.informatika.sirekap.support.security;

import android.content.Context;
import android.util.Base64;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.InputStream;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.ResourceRefType;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.informatika.sirekap.BuildConfig;

/* compiled from: PKIFacade.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006J\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0006J\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000eJ!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\f\u001a\u00020\u000eJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000eJ\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u0012J\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/support/security/PKIFacade;", "", "()V", "checkCertificateChainOnly", "", "trustAnchors", "", "Ljava/security/cert/TrustAnchor;", "certificateChain", "Ljava/security/cert/Certificate;", "decodeToPEM", "", "certificate", "generateTrustAnchor", "Ljava/security/cert/X509Certificate;", "getCACertificateChain", "", "context", "Landroid/content/Context;", "alias", "(Landroid/content/Context;Ljava/lang/String;)[Ljava/security/cert/Certificate;", "getCRLUrls", "getCertificateChain", ResourceRefType.FILE_PATH, "getOCSPUrl", "getTrustAnchor", "repairChain", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PKIFacade {
    public static final PKIFacade INSTANCE = new PKIFacade();

    private PKIFacade() {
    }

    static {
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.addProvider(new BouncyCastleProvider());
    }

    public final void checkCertificateChainOnly(List<? extends TrustAnchor> trustAnchors, List<? extends Certificate> certificateChain) {
        Intrinsics.checkNotNullParameter(trustAnchors, "trustAnchors");
        Intrinsics.checkNotNullParameter(certificateChain, "certificateChain");
        try {
            CertPath generateCertPath = CertificateFactory.getInstance("X.509").generateCertPath(certificateChain);
            PKIXBuilderParameters pKIXBuilderParameters = new PKIXBuilderParameters(new HashSet(trustAnchors), new X509CertSelector());
            pKIXBuilderParameters.setRevocationEnabled(false);
            CertPathValidator.getInstance("PKIX").validate(generateCertPath, pKIXBuilderParameters);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public final TrustAnchor generateTrustAnchor(X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return new TrustAnchor(certificate, null);
    }

    public final Certificate[] getCACertificateChain(Context context, String alias) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(alias, "alias");
        if (Intrinsics.areEqual(alias, AndroidKeystoreFacade.BSRE_ALIAS_NAME)) {
            str = BuildConfig.BSRE_CA_CERTIFICATE;
        } else if (!Intrinsics.areEqual(alias, AndroidKeystoreFacade.LOCAL_ALIAS_NAME)) {
            throw new Exception("Unknown alias");
        } else {
            str = BuildConfig.SIREKAP_CA_CERTIFICATE;
        }
        return (Certificate[]) getCertificateChain(context, str).toArray(new Certificate[0]);
    }

    public final List<TrustAnchor> getTrustAnchor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<String> split$default = StringsKt.split$default((CharSequence) BuildConfig.TRUSTCA_CERTIFICATE, new String[]{";"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
        for (String str : split$default) {
            List<Certificate> certificateChain = INSTANCE.getCertificateChain(context, str);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(certificateChain, 10));
            for (Certificate certificate : certificateChain) {
                PKIFacade pKIFacade = INSTANCE;
                Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                arrayList2.add(pKIFacade.generateTrustAnchor((X509Certificate) certificate));
            }
            arrayList.add(arrayList2);
        }
        return CollectionsKt.flatten(arrayList);
    }

    public final List<Certificate> getCertificateChain(Context context, String filePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        InputStream open = context.getAssets().open(filePath);
        Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(filePath)");
        Collection<? extends Certificate> generateCertificates = CertificateFactory.getInstance("X.509").generateCertificates(open);
        Intrinsics.checkNotNullExpressionValue(generateCertificates, "certificateFactory.gener…Certificates(inputStream)");
        Collection<? extends Certificate> collection = generateCertificates;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (Certificate certificate : collection) {
            Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            arrayList.add((X509Certificate) certificate);
        }
        return arrayList;
    }

    public final List<String> decodeToPEM(List<? extends Certificate> certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        List<? extends Certificate> list = certificate;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add("-----BEGIN CERTIFICATE-----\n" + Base64.encodeToString(((Certificate) it.next()).getEncoded(), 0) + "-----END CERTIFICATE-----\n");
        }
        return CollectionsKt.toList(arrayList);
    }

    public final String getOCSPUrl(X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        byte[] extensionValue = certificate.getExtensionValue(Extension.authorityInfoAccess.getId());
        if (extensionValue == null) {
            return null;
        }
        ASN1Primitive parseExtensionValue = JcaX509ExtensionUtils.parseExtensionValue(extensionValue);
        Intrinsics.checkNotNull(parseExtensionValue, "null cannot be cast to non-null type org.bouncycastle.asn1.ASN1Sequence");
        Enumeration objects = ((ASN1Sequence) parseExtensionValue).getObjects();
        while (objects.hasMoreElements()) {
            Object nextElement = objects.nextElement();
            Intrinsics.checkNotNull(nextElement, "null cannot be cast to non-null type org.bouncycastle.asn1.ASN1Sequence");
            ASN1Sequence aSN1Sequence = (ASN1Sequence) nextElement;
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
            ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(1);
            Intrinsics.checkNotNull(objectAt2, "null cannot be cast to non-null type org.bouncycastle.asn1.ASN1TaggedObject");
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objectAt2;
            if (X509ObjectIdentifiers.id_ad_ocsp.equals(objectAt) && aSN1TaggedObject.getTagNo() == 6) {
                ASN1Object baseObject = aSN1TaggedObject.getBaseObject();
                Intrinsics.checkNotNull(baseObject, "null cannot be cast to non-null type org.bouncycastle.asn1.ASN1OctetString");
                byte[] octets = ((ASN1OctetString) baseObject).getOctets();
                Intrinsics.checkNotNullExpressionValue(octets, "url.octets");
                return new String(octets, Charsets.UTF_8);
            }
        }
        return null;
    }

    public final List<String> getCRLUrls(X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        byte[] extensionValue = certificate.getExtensionValue(Extension.cRLDistributionPoints.getId());
        if (extensionValue == null) {
            return new ArrayList();
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(extensionValue);
        try {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            Intrinsics.checkNotNullExpressionValue(readObject, "oAsnInStream.readObject()");
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(aSN1InputStream, null);
            if (!(readObject instanceof ASN1OctetString)) {
                return new ArrayList();
            }
            aSN1InputStream = new ASN1InputStream(((ASN1OctetString) readObject).getOctets());
            try {
                ASN1Primitive readObject2 = aSN1InputStream.readObject();
                Intrinsics.checkNotNullExpressionValue(readObject2, "oAsnInStream2.readObject()");
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(aSN1InputStream, null);
                CRLDistPoint cRLDistPoint = CRLDistPoint.getInstance(readObject2);
                ArrayList arrayList = new ArrayList();
                DistributionPoint[] distributionPoints = cRLDistPoint.getDistributionPoints();
                Intrinsics.checkNotNullExpressionValue(distributionPoints, "distPoint.distributionPoints");
                for (DistributionPoint distributionPoint : distributionPoints) {
                    DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                    if (distributionPoint2 != null && distributionPoint2.getType() == 0) {
                        GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                        Intrinsics.checkNotNullExpressionValue(names, "getInstance(dpn.name).names");
                        for (GeneralName generalName : names) {
                            if (generalName.getTagNo() == 6) {
                                String url = ASN1IA5String.getInstance(generalName.getName()).getString();
                                Intrinsics.checkNotNullExpressionValue(url, "url");
                                arrayList.add(url);
                            }
                        }
                    }
                }
                return arrayList;
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public final List<X509Certificate> repairChain(List<? extends X509Certificate> certificateChain) {
        Intrinsics.checkNotNullParameter(certificateChain, "certificateChain");
        X509Certificate x509Certificate = certificateChain.get(0);
        if (Intrinsics.areEqual(x509Certificate.getIssuerDN(), x509Certificate.getSubjectDN())) {
            return CollectionsKt.toList(CollectionsKt.reversed(certificateChain));
        }
        return CollectionsKt.toList(certificateChain);
    }
}
