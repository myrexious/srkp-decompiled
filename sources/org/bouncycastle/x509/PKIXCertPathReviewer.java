package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import kotlin.UByte;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.util.Integers;

/* loaded from: classes2.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected Date currentDate;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & UByte.MAX_VALUE));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
            try {
                try {
                    pKIXCertPathChecker.init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                if (size == 0) {
                    criticalExtensionOIDs.remove(Extension.extendedKeyUsage.getId());
                }
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                for (PKIXCertPathChecker pKIXCertPathChecker2 : certPathCheckers) {
                    try {
                        pKIXCertPathChecker2.check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it = criticalExtensionOIDs.iterator();
                    while (it.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.n;
        int i2 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i--;
                i2++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i) {
                i = intValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i2)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:477:0x0120 A[Catch: CertPathReviewerException -> 0x05f9, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:441:0x006f, B:445:0x007f, B:447:0x008c, B:451:0x009c, B:452:0x00a7, B:454:0x00ad, B:456:0x00ce, B:457:0x00d6, B:459:0x00dc, B:461:0x00e1, B:462:0x00ed, B:466:0x00f9, B:469:0x0100, B:470:0x0109, B:472:0x010f, B:474:0x0119, B:477:0x0120, B:479:0x0124, B:519:0x0210, B:521:0x0216, B:522:0x0219, B:524:0x021f, B:526:0x022b, B:529:0x0233, B:530:0x0236, B:531:0x0239, B:533:0x023f, B:534:0x0248, B:536:0x024e, B:544:0x0271, B:545:0x027d, B:546:0x027e, B:548:0x0282, B:550:0x028a, B:551:0x028e, B:553:0x0294, B:556:0x02b6, B:558:0x02c0, B:559:0x02c5, B:560:0x02d1, B:561:0x02d2, B:562:0x02de, B:564:0x02e1, B:565:0x02ee, B:567:0x02f4, B:569:0x031a, B:571:0x0332, B:570:0x0329, B:572:0x0339, B:573:0x033f, B:575:0x0345, B:577:0x034d, B:588:0x0377, B:581:0x0355, B:582:0x0361, B:584:0x0363, B:585:0x0372, B:591:0x0380, B:602:0x039f, B:604:0x03a9, B:605:0x03ad, B:607:0x03b3, B:612:0x03c3, B:615:0x03d0, B:618:0x03dd, B:620:0x03e7, B:631:0x0425, B:623:0x03ef, B:624:0x03fd, B:625:0x03fe, B:626:0x040c, B:628:0x040e, B:629:0x041c, B:483:0x0133, B:484:0x0137, B:486:0x013d, B:488:0x0153, B:490:0x015d, B:491:0x0162, B:493:0x0168, B:494:0x0176, B:496:0x017c, B:498:0x0188, B:502:0x0195, B:503:0x019b, B:505:0x01a1, B:510:0x01ba, B:499:0x018b, B:501:0x018f, B:514:0x01f3, B:517:0x0203, B:518:0x020f, B:633:0x0434, B:634:0x0441, B:635:0x0442, B:639:0x0453, B:641:0x045d, B:642:0x0462, B:644:0x0468, B:647:0x0476, B:654:0x048b, B:733:0x05df, B:734:0x05eb, B:657:0x0496, B:658:0x04a2, B:659:0x04a3, B:661:0x04a9, B:663:0x04b1, B:665:0x04b7, B:668:0x04c1, B:669:0x04c4, B:671:0x04ca, B:673:0x04da, B:674:0x04de, B:676:0x04e4, B:677:0x04ec, B:678:0x04ef, B:679:0x04f4, B:680:0x04f8, B:682:0x04fe, B:684:0x050e, B:686:0x0516, B:687:0x0519, B:689:0x051f, B:691:0x052b, B:692:0x052f, B:693:0x0532, B:694:0x0535, B:695:0x0541, B:697:0x0546, B:699:0x0550, B:700:0x0553, B:702:0x0559, B:704:0x0569, B:705:0x056d, B:707:0x0573, B:709:0x0583, B:710:0x0587, B:711:0x058a, B:712:0x058d, B:713:0x0593, B:715:0x0599, B:717:0x05ab, B:720:0x05b5, B:722:0x05bb, B:723:0x05be, B:725:0x05c4, B:727:0x05d0, B:728:0x05d4, B:729:0x05d7, B:735:0x05ec, B:736:0x05f8), top: B:746:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:486:0x013d A[Catch: CertPathReviewerException -> 0x05f9, TRY_LEAVE, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:441:0x006f, B:445:0x007f, B:447:0x008c, B:451:0x009c, B:452:0x00a7, B:454:0x00ad, B:456:0x00ce, B:457:0x00d6, B:459:0x00dc, B:461:0x00e1, B:462:0x00ed, B:466:0x00f9, B:469:0x0100, B:470:0x0109, B:472:0x010f, B:474:0x0119, B:477:0x0120, B:479:0x0124, B:519:0x0210, B:521:0x0216, B:522:0x0219, B:524:0x021f, B:526:0x022b, B:529:0x0233, B:530:0x0236, B:531:0x0239, B:533:0x023f, B:534:0x0248, B:536:0x024e, B:544:0x0271, B:545:0x027d, B:546:0x027e, B:548:0x0282, B:550:0x028a, B:551:0x028e, B:553:0x0294, B:556:0x02b6, B:558:0x02c0, B:559:0x02c5, B:560:0x02d1, B:561:0x02d2, B:562:0x02de, B:564:0x02e1, B:565:0x02ee, B:567:0x02f4, B:569:0x031a, B:571:0x0332, B:570:0x0329, B:572:0x0339, B:573:0x033f, B:575:0x0345, B:577:0x034d, B:588:0x0377, B:581:0x0355, B:582:0x0361, B:584:0x0363, B:585:0x0372, B:591:0x0380, B:602:0x039f, B:604:0x03a9, B:605:0x03ad, B:607:0x03b3, B:612:0x03c3, B:615:0x03d0, B:618:0x03dd, B:620:0x03e7, B:631:0x0425, B:623:0x03ef, B:624:0x03fd, B:625:0x03fe, B:626:0x040c, B:628:0x040e, B:629:0x041c, B:483:0x0133, B:484:0x0137, B:486:0x013d, B:488:0x0153, B:490:0x015d, B:491:0x0162, B:493:0x0168, B:494:0x0176, B:496:0x017c, B:498:0x0188, B:502:0x0195, B:503:0x019b, B:505:0x01a1, B:510:0x01ba, B:499:0x018b, B:501:0x018f, B:514:0x01f3, B:517:0x0203, B:518:0x020f, B:633:0x0434, B:634:0x0441, B:635:0x0442, B:639:0x0453, B:641:0x045d, B:642:0x0462, B:644:0x0468, B:647:0x0476, B:654:0x048b, B:733:0x05df, B:734:0x05eb, B:657:0x0496, B:658:0x04a2, B:659:0x04a3, B:661:0x04a9, B:663:0x04b1, B:665:0x04b7, B:668:0x04c1, B:669:0x04c4, B:671:0x04ca, B:673:0x04da, B:674:0x04de, B:676:0x04e4, B:677:0x04ec, B:678:0x04ef, B:679:0x04f4, B:680:0x04f8, B:682:0x04fe, B:684:0x050e, B:686:0x0516, B:687:0x0519, B:689:0x051f, B:691:0x052b, B:692:0x052f, B:693:0x0532, B:694:0x0535, B:695:0x0541, B:697:0x0546, B:699:0x0550, B:700:0x0553, B:702:0x0559, B:704:0x0569, B:705:0x056d, B:707:0x0573, B:709:0x0583, B:710:0x0587, B:711:0x058a, B:712:0x058d, B:713:0x0593, B:715:0x0599, B:717:0x05ab, B:720:0x05b5, B:722:0x05bb, B:723:0x05be, B:725:0x05c4, B:727:0x05d0, B:728:0x05d4, B:729:0x05d7, B:735:0x05ec, B:736:0x05f8), top: B:746:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0216 A[Catch: CertPathReviewerException -> 0x05f9, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:441:0x006f, B:445:0x007f, B:447:0x008c, B:451:0x009c, B:452:0x00a7, B:454:0x00ad, B:456:0x00ce, B:457:0x00d6, B:459:0x00dc, B:461:0x00e1, B:462:0x00ed, B:466:0x00f9, B:469:0x0100, B:470:0x0109, B:472:0x010f, B:474:0x0119, B:477:0x0120, B:479:0x0124, B:519:0x0210, B:521:0x0216, B:522:0x0219, B:524:0x021f, B:526:0x022b, B:529:0x0233, B:530:0x0236, B:531:0x0239, B:533:0x023f, B:534:0x0248, B:536:0x024e, B:544:0x0271, B:545:0x027d, B:546:0x027e, B:548:0x0282, B:550:0x028a, B:551:0x028e, B:553:0x0294, B:556:0x02b6, B:558:0x02c0, B:559:0x02c5, B:560:0x02d1, B:561:0x02d2, B:562:0x02de, B:564:0x02e1, B:565:0x02ee, B:567:0x02f4, B:569:0x031a, B:571:0x0332, B:570:0x0329, B:572:0x0339, B:573:0x033f, B:575:0x0345, B:577:0x034d, B:588:0x0377, B:581:0x0355, B:582:0x0361, B:584:0x0363, B:585:0x0372, B:591:0x0380, B:602:0x039f, B:604:0x03a9, B:605:0x03ad, B:607:0x03b3, B:612:0x03c3, B:615:0x03d0, B:618:0x03dd, B:620:0x03e7, B:631:0x0425, B:623:0x03ef, B:624:0x03fd, B:625:0x03fe, B:626:0x040c, B:628:0x040e, B:629:0x041c, B:483:0x0133, B:484:0x0137, B:486:0x013d, B:488:0x0153, B:490:0x015d, B:491:0x0162, B:493:0x0168, B:494:0x0176, B:496:0x017c, B:498:0x0188, B:502:0x0195, B:503:0x019b, B:505:0x01a1, B:510:0x01ba, B:499:0x018b, B:501:0x018f, B:514:0x01f3, B:517:0x0203, B:518:0x020f, B:633:0x0434, B:634:0x0441, B:635:0x0442, B:639:0x0453, B:641:0x045d, B:642:0x0462, B:644:0x0468, B:647:0x0476, B:654:0x048b, B:733:0x05df, B:734:0x05eb, B:657:0x0496, B:658:0x04a2, B:659:0x04a3, B:661:0x04a9, B:663:0x04b1, B:665:0x04b7, B:668:0x04c1, B:669:0x04c4, B:671:0x04ca, B:673:0x04da, B:674:0x04de, B:676:0x04e4, B:677:0x04ec, B:678:0x04ef, B:679:0x04f4, B:680:0x04f8, B:682:0x04fe, B:684:0x050e, B:686:0x0516, B:687:0x0519, B:689:0x051f, B:691:0x052b, B:692:0x052f, B:693:0x0532, B:694:0x0535, B:695:0x0541, B:697:0x0546, B:699:0x0550, B:700:0x0553, B:702:0x0559, B:704:0x0569, B:705:0x056d, B:707:0x0573, B:709:0x0583, B:710:0x0587, B:711:0x058a, B:712:0x058d, B:713:0x0593, B:715:0x0599, B:717:0x05ab, B:720:0x05b5, B:722:0x05bb, B:723:0x05be, B:725:0x05c4, B:727:0x05d0, B:728:0x05d4, B:729:0x05d7, B:735:0x05ec, B:736:0x05f8), top: B:746:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:533:0x023f A[Catch: CertPathReviewerException -> 0x05f9, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:441:0x006f, B:445:0x007f, B:447:0x008c, B:451:0x009c, B:452:0x00a7, B:454:0x00ad, B:456:0x00ce, B:457:0x00d6, B:459:0x00dc, B:461:0x00e1, B:462:0x00ed, B:466:0x00f9, B:469:0x0100, B:470:0x0109, B:472:0x010f, B:474:0x0119, B:477:0x0120, B:479:0x0124, B:519:0x0210, B:521:0x0216, B:522:0x0219, B:524:0x021f, B:526:0x022b, B:529:0x0233, B:530:0x0236, B:531:0x0239, B:533:0x023f, B:534:0x0248, B:536:0x024e, B:544:0x0271, B:545:0x027d, B:546:0x027e, B:548:0x0282, B:550:0x028a, B:551:0x028e, B:553:0x0294, B:556:0x02b6, B:558:0x02c0, B:559:0x02c5, B:560:0x02d1, B:561:0x02d2, B:562:0x02de, B:564:0x02e1, B:565:0x02ee, B:567:0x02f4, B:569:0x031a, B:571:0x0332, B:570:0x0329, B:572:0x0339, B:573:0x033f, B:575:0x0345, B:577:0x034d, B:588:0x0377, B:581:0x0355, B:582:0x0361, B:584:0x0363, B:585:0x0372, B:591:0x0380, B:602:0x039f, B:604:0x03a9, B:605:0x03ad, B:607:0x03b3, B:612:0x03c3, B:615:0x03d0, B:618:0x03dd, B:620:0x03e7, B:631:0x0425, B:623:0x03ef, B:624:0x03fd, B:625:0x03fe, B:626:0x040c, B:628:0x040e, B:629:0x041c, B:483:0x0133, B:484:0x0137, B:486:0x013d, B:488:0x0153, B:490:0x015d, B:491:0x0162, B:493:0x0168, B:494:0x0176, B:496:0x017c, B:498:0x0188, B:502:0x0195, B:503:0x019b, B:505:0x01a1, B:510:0x01ba, B:499:0x018b, B:501:0x018f, B:514:0x01f3, B:517:0x0203, B:518:0x020f, B:633:0x0434, B:634:0x0441, B:635:0x0442, B:639:0x0453, B:641:0x045d, B:642:0x0462, B:644:0x0468, B:647:0x0476, B:654:0x048b, B:733:0x05df, B:734:0x05eb, B:657:0x0496, B:658:0x04a2, B:659:0x04a3, B:661:0x04a9, B:663:0x04b1, B:665:0x04b7, B:668:0x04c1, B:669:0x04c4, B:671:0x04ca, B:673:0x04da, B:674:0x04de, B:676:0x04e4, B:677:0x04ec, B:678:0x04ef, B:679:0x04f4, B:680:0x04f8, B:682:0x04fe, B:684:0x050e, B:686:0x0516, B:687:0x0519, B:689:0x051f, B:691:0x052b, B:692:0x052f, B:693:0x0532, B:694:0x0535, B:695:0x0541, B:697:0x0546, B:699:0x0550, B:700:0x0553, B:702:0x0559, B:704:0x0569, B:705:0x056d, B:707:0x0573, B:709:0x0583, B:710:0x0587, B:711:0x058a, B:712:0x058d, B:713:0x0593, B:715:0x0599, B:717:0x05ab, B:720:0x05b5, B:722:0x05bb, B:723:0x05be, B:725:0x05c4, B:727:0x05d0, B:728:0x05d4, B:729:0x05d7, B:735:0x05ec, B:736:0x05f8), top: B:746:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkPolicy() {
        /*
            Method dump skipped, instructions count: 1542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:91|92|(15:94|95|96|(11:98|99|(2:102|100)|103|104|(2:107|105)|108|109|110|111|112)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112)|122|95|96|(0)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112) */
    /* JADX WARN: Can't wrap try/catch for region: R(19:30|(2:137|138)(2:32|(2:130|131)(20:34|(2:38|(17:40|41|42|43|44|(18:91|92|(15:94|95|96|(11:98|99|(2:102|100)|103|104|(2:107|105)|108|109|110|111|112)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112)|122|95|96|(0)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112)(1:46)|(1:90)(1:50)|51|(7:53|(2:55|(1:57))(1:88)|58|59|(2:61|(1:63))(1:85)|64|(9:66|(1:83)|70|71|72|74|75|77|78))(1:89)|84|70|71|72|74|75|77|78))|129|41|42|43|44|(0)(0)|(1:48)|90|51|(0)(0)|84|70|71|72|74|75|77|78))|132|42|43|44|(0)(0)|(0)|90|51|(0)(0)|84|70|71|72|74|75|77|78) */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x0254, code lost:
        r1 = new java.lang.Object[r13];
        r1[r12] = new org.bouncycastle.i18n.filter.TrustedInput(r3.getNotAfter());
        r0 = new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.certificateExpired", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x0269, code lost:
        r1 = new java.lang.Object[r13];
        r1[r12] = new org.bouncycastle.i18n.filter.TrustedInput(r3.getNotBefore());
        r0 = new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.certificateNotYetValid", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x027d, code lost:
        addError(r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x02af, code lost:
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x03e2, code lost:
        r6 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x03e4, code lost:
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.pubKeyError"), r11);
     */
    /* JADX WARN: Removed duplicated region for block: B:223:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x02aa A[Catch: AnnotatedException -> 0x02af, TRY_LEAVE, TryCatch #2 {AnnotatedException -> 0x02af, blocks: (B:285:0x02a2, B:287:0x02aa), top: B:352:0x02a2 }] */
    /* JADX WARN: Removed duplicated region for block: B:294:0x02cc A[LOOP:1: B:292:0x02c6->B:294:0x02cc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x02f0 A[LOOP:2: B:296:0x02ea->B:298:0x02f0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0288 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkSignatures() {
        /*
            Method dump skipped, instructions count: 1029
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            addNotification(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue}), i);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle, i);
            }
            return true ^ z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:242:0x0165
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    protected void checkCRLs(java.security.cert.PKIXParameters r21, java.security.cert.X509Certificate r22, java.util.Date r23, java.security.cert.X509Certificate r24, java.security.PublicKey r25, java.util.Vector r26, int r27) throws org.bouncycastle.x509.CertPathReviewerException {
        /*
            Method dump skipped, instructions count: 1063
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i2] = new ArrayList();
            this.errors[i2] = new ArrayList();
            i2++;
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((ASN1IA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((ASN1IA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                if (authorityKeyIdentifier.getAuthorityCertSerialNumber() != null) {
                    x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                } else {
                    byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                    if (keyIdentifier != null) {
                        x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                    }
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        this.certPath = certPath;
        List<? extends Certificate> certificates = certPath.getCertificates();
        this.certs = certificates;
        this.n = certificates.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        this.pkixParams = (PKIXParameters) pKIXParameters.clone();
        Date date = new Date();
        this.currentDate = date;
        this.validDate = getValidityDate(this.pkixParams, date);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
