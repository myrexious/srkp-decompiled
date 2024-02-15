package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSUpdateInfo;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDDocumentCatalog;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.util.Store;
import org.informatika.sirekap.support.security.pki.CRLUtility;
import org.informatika.sirekap.support.security.pki.OCSPUtility;

/* compiled from: PdfValidationInformation.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001$B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J0\u0010\u0012\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0002J=\u0010\u0019\u001a\u0002H\u001a\"\u0010\b\u0000\u0010\u001a*\u0004\u0018\u00010\u001b*\u0004\u0018\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002¢\u0006\u0002\u0010#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfValidationInformation;", "", "crlUtility", "Lorg/informatika/sirekap/support/security/pki/CRLUtility;", "ocspUtility", "Lorg/informatika/sirekap/support/security/pki/OCSPUtility;", "ocspTrustAnchor", "", "Ljava/security/cert/TrustAnchor;", "(Lorg/informatika/sirekap/support/security/pki/CRLUtility;Lorg/informatika/sirekap/support/security/pki/OCSPUtility;Ljava/util/List;)V", "add", "", "documentFile", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addExtensions", "catalog", "Lcom/tom_roush/pdfbox/pdmodel/PDDocumentCatalog;", "getAllsignaturesAndCertificates", "Lkotlin/Pair;", "Lorg/informatika/sirekap/support/security/pdf/PdfValidationInformation$CertificateInformation;", "", "Ljava/security/cert/X509Certificate;", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "getOrCreateDictionaryEntry", "T", "Lcom/tom_roush/pdfbox/cos/COSBase;", "Lcom/tom_roush/pdfbox/cos/COSUpdateInfo;", "clazz", "Ljava/lang/Class;", "parent", "Lcom/tom_roush/pdfbox/cos/COSDictionary;", "name", "", "(Ljava/lang/Class;Lcom/tom_roush/pdfbox/cos/COSDictionary;Ljava/lang/String;)Lcom/tom_roush/pdfbox/cos/COSBase;", "CertificateInformation", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfValidationInformation {
    private final CRLUtility crlUtility;
    private final List<TrustAnchor> ocspTrustAnchor;
    private final OCSPUtility ocspUtility;

    /* JADX WARN: Multi-variable type inference failed */
    public PdfValidationInformation(CRLUtility crlUtility, OCSPUtility ocspUtility, List<? extends TrustAnchor> list) {
        Intrinsics.checkNotNullParameter(crlUtility, "crlUtility");
        Intrinsics.checkNotNullParameter(ocspUtility, "ocspUtility");
        this.crlUtility = crlUtility;
        this.ocspUtility = ocspUtility;
        this.ocspTrustAnchor = list;
    }

    public /* synthetic */ PdfValidationInformation(CRLUtility cRLUtility, OCSPUtility oCSPUtility, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(cRLUtility, oCSPUtility, (i & 4) != 0 ? null : list);
    }

    /* compiled from: PdfValidationInformation.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\bHÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0013\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000e¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfValidationInformation$CertificateInformation;", "", "certificates", "", "Ljava/security/cert/X509Certificate;", CMSAttributeTableGenerator.DIGEST, "", "isTimestamp", "", "(Ljava/util/List;Ljava/lang/String;Z)V", "getCertificates", "()Ljava/util/List;", "getDigest", "()Ljava/lang/String;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class CertificateInformation {
        private final List<X509Certificate> certificates;
        private final String digest;
        private final boolean isTimestamp;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CertificateInformation copy$default(CertificateInformation certificateInformation, List list, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                list = certificateInformation.certificates;
            }
            if ((i & 2) != 0) {
                str = certificateInformation.digest;
            }
            if ((i & 4) != 0) {
                z = certificateInformation.isTimestamp;
            }
            return certificateInformation.copy(list, str, z);
        }

        public final List<X509Certificate> component1() {
            return this.certificates;
        }

        public final String component2() {
            return this.digest;
        }

        public final boolean component3() {
            return this.isTimestamp;
        }

        public final CertificateInformation copy(List<? extends X509Certificate> certificates, String digest, boolean z) {
            Intrinsics.checkNotNullParameter(certificates, "certificates");
            Intrinsics.checkNotNullParameter(digest, "digest");
            return new CertificateInformation(certificates, digest, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof CertificateInformation) {
                CertificateInformation certificateInformation = (CertificateInformation) obj;
                return Intrinsics.areEqual(this.certificates, certificateInformation.certificates) && Intrinsics.areEqual(this.digest, certificateInformation.digest) && this.isTimestamp == certificateInformation.isTimestamp;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((this.certificates.hashCode() * 31) + this.digest.hashCode()) * 31;
            boolean z = this.isTimestamp;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public String toString() {
            List<X509Certificate> list = this.certificates;
            String str = this.digest;
            return "CertificateInformation(certificates=" + list + ", digest=" + str + ", isTimestamp=" + this.isTimestamp + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CertificateInformation(List<? extends X509Certificate> certificates, String digest, boolean z) {
            Intrinsics.checkNotNullParameter(certificates, "certificates");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.certificates = certificates;
            this.digest = digest;
            this.isTimestamp = z;
        }

        public final List<X509Certificate> getCertificates() {
            return this.certificates;
        }

        public final String getDigest() {
            return this.digest;
        }

        public final boolean isTimestamp() {
            return this.isTimestamp;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01c5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0267  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:143:0x0140 -> B:181:0x0262). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:146:0x0154 -> B:181:0x0262). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:155:0x01c6 -> B:156:0x01c8). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object add(java.io.File r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            Method dump skipped, instructions count: 767
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.pdf.PdfValidationInformation.add(java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Pair<List<CertificateInformation>, Set<X509Certificate>> getAllsignaturesAndCertificates(PDDocument pDDocument, File file) {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        JcaX509CertificateConverter jcaX509CertificateConverter = new JcaX509CertificateConverter();
        HashSet hashSet = new HashSet();
        List<PDSignature> signatureDictionaries = pDDocument.getSignatureDictionaries();
        Intrinsics.checkNotNullExpressionValue(signatureDictionaries, "document.signatureDictionaries");
        List<PDSignature> list = signatureDictionaries;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (PDSignature pDSignature : list) {
            byte[] contents = pDSignature.getContents(new FileInputStream(file));
            messageDigest.reset();
            String digest = Hex.encodeHexString(messageDigest.digest(contents));
            Store<X509CertificateHolder> certificates = new CMSSignedData(contents).getCertificates();
            Intrinsics.checkNotNullExpressionValue(certificates, "cmsSignedData.certificates");
            Collection<X509CertificateHolder> matches = certificates.getMatches(null);
            Intrinsics.checkNotNullExpressionValue(matches, "certificatesStore.getMatches(null)");
            Collection<X509CertificateHolder> collection = matches;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
            for (X509CertificateHolder x509CertificateHolder : collection) {
                X509Certificate certificate = jcaX509CertificateConverter.getCertificate(x509CertificateHolder);
                hashSet.add(certificate);
                arrayList2.add(certificate);
            }
            Intrinsics.checkNotNullExpressionValue(digest, "digest");
            arrayList.add(new CertificateInformation(arrayList2, digest, Intrinsics.areEqual(pDSignature.getSubFilter(), "ETSI.RFC3161")));
        }
        return new Pair<>(CollectionsKt.toList(arrayList), hashSet);
    }

    private final void addExtensions(PDDocumentCatalog pDDocumentCatalog) {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setDirect(true);
        pDDocumentCatalog.getCOSObject().setItem("Extensions", (COSBase) cOSDictionary);
        COSDictionary cOSDictionary2 = new COSDictionary();
        cOSDictionary2.setDirect(true);
        cOSDictionary.setItem("ADBE", (COSBase) cOSDictionary2);
        cOSDictionary2.setName("BaseVersion", "1.7");
        cOSDictionary2.setInt("ExtensionLevel", 5);
        pDDocumentCatalog.setVersion("1.7");
    }

    private final <T extends COSBase & COSUpdateInfo> T getOrCreateDictionaryEntry(Class<T> cls, COSDictionary cOSDictionary, String str) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(str);
        if (dictionaryObject != null && cls.isInstance(dictionaryObject)) {
            T cast = cls.cast(dictionaryObject);
            Intrinsics.checkNotNull(cast);
            T t = cast;
            t.setNeedToBeUpdated(true);
            return t;
        } else if (dictionaryObject != null) {
            throw new IOException("Element " + str + " from dictionary is not of type " + cls.getCanonicalName());
        } else {
            try {
                T newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                Intrinsics.checkNotNull(newInstance);
                newInstance.setDirect(false);
                cOSDictionary.setItem(COSName.getPDFName(str), newInstance);
                return newInstance;
            } catch (ReflectiveOperationException e) {
                throw new IOException("Failed to create new instance of " + cls.getCanonicalName(), e);
            } catch (SecurityException e2) {
                throw new IOException("Failed to create new instance of " + cls.getCanonicalName(), e2);
            }
        }
    }
}
