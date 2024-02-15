package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.isara.IsaraObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.CMCEPublicKey;
import org.bouncycastle.pqc.asn1.McElieceCCA2PublicKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSPublicKey;
import org.bouncycastle.pqc.crypto.bike.BIKEPublicKeyParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEPublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPublicKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPublicKeyParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCPublicKeyParameters;
import org.bouncycastle.pqc.crypto.lms.HSSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.lms.LMSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPublicKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePublicKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimePublicKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPublicKeyParameters;
import org.bouncycastle.pqc.crypto.saber.SABERPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sike.SIKEPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLAPublicKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes2.dex */
public class PublicKeyFactory {
    private static Map converters;

    /* loaded from: classes2.dex */
    private static class BIKEConverter extends SubjectPublicKeyInfoConverter {
        private BIKEConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new BIKEPublicKeyParameters(Utils.bikeParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class CMCEConverter extends SubjectPublicKeyInfoConverter {
        private CMCEConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new CMCEPublicKeyParameters(Utils.mcElieceParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), CMCEPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey()).getT());
        }
    }

    /* loaded from: classes2.dex */
    private static class DilithiumConverter extends SubjectPublicKeyInfoConverter {
        private DilithiumConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            DilithiumParameters dilithiumParamsLookup = Utils.dilithiumParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
            ASN1Primitive parsePublicKey = subjectPublicKeyInfo.parsePublicKey();
            if (parsePublicKey instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(parsePublicKey);
                return new DilithiumPublicKeyParameters(dilithiumParamsLookup, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
            }
            return new DilithiumPublicKeyParameters(dilithiumParamsLookup, ASN1OctetString.getInstance(parsePublicKey).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class FalconConverter extends SubjectPublicKeyInfoConverter {
        private FalconConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            FalconParameters falconParamsLookup = Utils.falconParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
            ASN1Primitive parsePublicKey = subjectPublicKeyInfo.parsePublicKey();
            if (parsePublicKey instanceof ASN1Sequence) {
                return new FalconPublicKeyParameters(falconParamsLookup, ASN1OctetString.getInstance(ASN1Sequence.getInstance(parsePublicKey).getObjectAt(0)).getOctets());
            }
            byte[] octets = ASN1OctetString.getInstance(parsePublicKey).getOctets();
            if (octets[0] == ((byte) (falconParamsLookup.getLogN() + 0))) {
                return new FalconPublicKeyParameters(falconParamsLookup, Arrays.copyOfRange(octets, 1, octets.length));
            }
            throw new IllegalArgumentException("byte[] enc of Falcon h value not tagged correctly");
        }
    }

    /* loaded from: classes2.dex */
    private static class FrodoConverter extends SubjectPublicKeyInfoConverter {
        private FrodoConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new FrodoPublicKeyParameters(Utils.frodoParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class HQCConverter extends SubjectPublicKeyInfoConverter {
        private HQCConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new HQCPublicKeyParameters(Utils.hqcParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class KyberConverter extends SubjectPublicKeyInfoConverter {
        private KyberConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            KyberParameters kyberParamsLookup = Utils.kyberParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
            ASN1Primitive parsePublicKey = subjectPublicKeyInfo.parsePublicKey();
            if (parsePublicKey instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(parsePublicKey);
                return new KyberPublicKeyParameters(kyberParamsLookup, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
            }
            return new KyberPublicKeyParameters(kyberParamsLookup, ASN1OctetString.getInstance(parsePublicKey).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class LMSConverter extends SubjectPublicKeyInfoConverter {
        private LMSConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            byte[] octets = ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets();
            if (Pack.bigEndianToInt(octets, 0) == 1) {
                return LMSPublicKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length));
            }
            if (octets.length == 64) {
                octets = Arrays.copyOfRange(octets, 4, octets.length);
            }
            return HSSPublicKeyParameters.getInstance(octets);
        }
    }

    /* loaded from: classes2.dex */
    private static class McElieceCCA2Converter extends SubjectPublicKeyInfoConverter {
        private McElieceCCA2Converter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            McElieceCCA2PublicKey mcElieceCCA2PublicKey = McElieceCCA2PublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
            return new McElieceCCA2PublicKeyParameters(mcElieceCCA2PublicKey.getN(), mcElieceCCA2PublicKey.getT(), mcElieceCCA2PublicKey.getG(), Utils.getDigestName(mcElieceCCA2PublicKey.getDigest().getAlgorithm()));
        }
    }

    /* loaded from: classes2.dex */
    private static class NHConverter extends SubjectPublicKeyInfoConverter {
        private NHConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new NHPublicKeyParameters(subjectPublicKeyInfo.getPublicKeyData().getBytes());
        }
    }

    /* loaded from: classes2.dex */
    private static class NTRULPrimeConverter extends SubjectPublicKeyInfoConverter {
        private NTRULPrimeConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new NTRULPRimePublicKeyParameters(Utils.ntrulprimeParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class NtruConverter extends SubjectPublicKeyInfoConverter {
        private NtruConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new NTRUPublicKeyParameters(Utils.ntruParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class PicnicConverter extends SubjectPublicKeyInfoConverter {
        private PicnicConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new PicnicPublicKeyParameters(Utils.picnicParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class QTeslaConverter extends SubjectPublicKeyInfoConverter {
        private QTeslaConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new QTESLAPublicKeyParameters(Utils.qTeslaLookupSecurityCategory(subjectPublicKeyInfo.getAlgorithm()), subjectPublicKeyInfo.getPublicKeyData().getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class SABERConverter extends SubjectPublicKeyInfoConverter {
        private SABERConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new SABERPublicKeyParameters(Utils.saberParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(ASN1Sequence.getInstance(subjectPublicKeyInfo.parsePublicKey()).getObjectAt(0)).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class SIKEConverter extends SubjectPublicKeyInfoConverter {
        private SIKEConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new SIKEPublicKeyParameters(Utils.sikeParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class SNTRUPrimeConverter extends SubjectPublicKeyInfoConverter {
        private SNTRUPrimeConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new SNTRUPrimePublicKeyParameters(Utils.sntruprimeParamsLookup(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets());
        }
    }

    /* loaded from: classes2.dex */
    private static class SPHINCSConverter extends SubjectPublicKeyInfoConverter {
        private SPHINCSConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            return new SPHINCSPublicKeyParameters(subjectPublicKeyInfo.getPublicKeyData().getBytes(), Utils.sphincs256LookupTreeAlgName(SPHINCS256KeyParams.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters())));
        }
    }

    /* loaded from: classes2.dex */
    private static class SPHINCSPlusConverter extends SubjectPublicKeyInfoConverter {
        private SPHINCSPlusConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            byte[] octets = ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets();
            return new SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters.getParams(Integers.valueOf(Pack.bigEndianToInt(octets, 0))), Arrays.copyOfRange(octets, 4, octets.length));
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class SubjectPublicKeyInfoConverter {
        private SubjectPublicKeyInfoConverter() {
        }

        abstract AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException;
    }

    /* loaded from: classes2.dex */
    private static class XMSSConverter extends SubjectPublicKeyInfoConverter {
        private XMSSConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            XMSSPublicKeyParameters.Builder withPublicKey;
            XMSSKeyParams xMSSKeyParams = XMSSKeyParams.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            if (xMSSKeyParams != null) {
                ASN1ObjectIdentifier algorithm = xMSSKeyParams.getTreeDigest().getAlgorithm();
                XMSSPublicKey xMSSPublicKey = XMSSPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
                withPublicKey = new XMSSPublicKeyParameters.Builder(new XMSSParameters(xMSSKeyParams.getHeight(), Utils.getDigest(algorithm))).withPublicSeed(xMSSPublicKey.getPublicSeed()).withRoot(xMSSPublicKey.getRoot());
            } else {
                byte[] octets = ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets();
                withPublicKey = new XMSSPublicKeyParameters.Builder(XMSSParameters.lookupByOID(Pack.bigEndianToInt(octets, 0))).withPublicKey(octets);
            }
            return withPublicKey.build();
        }
    }

    /* loaded from: classes2.dex */
    private static class XMSSMTConverter extends SubjectPublicKeyInfoConverter {
        private XMSSMTConverter() {
            super();
        }

        @Override // org.bouncycastle.pqc.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            XMSSMTPublicKeyParameters.Builder withPublicKey;
            XMSSMTKeyParams xMSSMTKeyParams = XMSSMTKeyParams.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            if (xMSSMTKeyParams != null) {
                ASN1ObjectIdentifier algorithm = xMSSMTKeyParams.getTreeDigest().getAlgorithm();
                XMSSPublicKey xMSSPublicKey = XMSSPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
                withPublicKey = new XMSSMTPublicKeyParameters.Builder(new XMSSMTParameters(xMSSMTKeyParams.getHeight(), xMSSMTKeyParams.getLayers(), Utils.getDigest(algorithm))).withPublicSeed(xMSSPublicKey.getPublicSeed()).withRoot(xMSSPublicKey.getRoot());
            } else {
                byte[] octets = ASN1OctetString.getInstance(subjectPublicKeyInfo.parsePublicKey()).getOctets();
                withPublicKey = new XMSSMTPublicKeyParameters.Builder(XMSSMTParameters.lookupByOID(Pack.bigEndianToInt(octets, 0))).withPublicKey(octets);
            }
            return withPublicKey.build();
        }
    }

    static {
        HashMap hashMap = new HashMap();
        converters = hashMap;
        hashMap.put(PQCObjectIdentifiers.qTESLA_p_I, new QTeslaConverter());
        converters.put(PQCObjectIdentifiers.qTESLA_p_III, new QTeslaConverter());
        converters.put(PQCObjectIdentifiers.sphincs256, new SPHINCSConverter());
        converters.put(PQCObjectIdentifiers.newHope, new NHConverter());
        converters.put(PQCObjectIdentifiers.xmss, new XMSSConverter());
        converters.put(PQCObjectIdentifiers.xmss_mt, new XMSSMTConverter());
        converters.put(IsaraObjectIdentifiers.id_alg_xmss, new XMSSConverter());
        converters.put(IsaraObjectIdentifiers.id_alg_xmssmt, new XMSSMTConverter());
        converters.put(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig, new LMSConverter());
        converters.put(PQCObjectIdentifiers.mcElieceCca2, new McElieceCCA2Converter());
        converters.put(BCObjectIdentifiers.sphincsPlus, new SPHINCSPlusConverter());
        converters.put(BCObjectIdentifiers.sphincsPlus_shake_256, new SPHINCSPlusConverter());
        converters.put(BCObjectIdentifiers.sphincsPlus_sha_256, new SPHINCSPlusConverter());
        converters.put(BCObjectIdentifiers.sphincsPlus_sha_512, new SPHINCSPlusConverter());
        converters.put(BCObjectIdentifiers.sphincsPlus_haraka, new SPHINCSPlusConverter());
        converters.put(BCObjectIdentifiers.mceliece348864_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece348864f_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece460896_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece460896f_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece6688128_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece6688128f_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece6960119_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece6960119f_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece8192128_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.mceliece8192128f_r3, new CMCEConverter());
        converters.put(BCObjectIdentifiers.frodokem640aes, new FrodoConverter());
        converters.put(BCObjectIdentifiers.frodokem640shake, new FrodoConverter());
        converters.put(BCObjectIdentifiers.frodokem976aes, new FrodoConverter());
        converters.put(BCObjectIdentifiers.frodokem976shake, new FrodoConverter());
        converters.put(BCObjectIdentifiers.frodokem1344aes, new FrodoConverter());
        converters.put(BCObjectIdentifiers.frodokem1344shake, new FrodoConverter());
        converters.put(BCObjectIdentifiers.lightsaberkem128r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.saberkem128r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.firesaberkem128r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.lightsaberkem192r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.saberkem192r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.firesaberkem192r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.lightsaberkem256r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.saberkem256r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.firesaberkem256r3, new SABERConverter());
        converters.put(BCObjectIdentifiers.picnicl1fs, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl1ur, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl3fs, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl3ur, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl5fs, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl5ur, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnic3l1, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnic3l3, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnic3l5, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl1full, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl3full, new PicnicConverter());
        converters.put(BCObjectIdentifiers.picnicl5full, new PicnicConverter());
        converters.put(BCObjectIdentifiers.sikep434, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep503, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep610, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep751, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep434_compressed, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep503_compressed, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep610_compressed, new SIKEConverter());
        converters.put(BCObjectIdentifiers.sikep751_compressed, new SIKEConverter());
        converters.put(BCObjectIdentifiers.ntruhps2048509, new NtruConverter());
        converters.put(BCObjectIdentifiers.ntruhps2048677, new NtruConverter());
        converters.put(BCObjectIdentifiers.ntruhps4096821, new NtruConverter());
        converters.put(BCObjectIdentifiers.ntruhrss701, new NtruConverter());
        converters.put(BCObjectIdentifiers.falcon_512, new FalconConverter());
        converters.put(BCObjectIdentifiers.falcon_1024, new FalconConverter());
        converters.put(BCObjectIdentifiers.kyber512, new KyberConverter());
        converters.put(BCObjectIdentifiers.kyber768, new KyberConverter());
        converters.put(BCObjectIdentifiers.kyber1024, new KyberConverter());
        converters.put(BCObjectIdentifiers.kyber512_aes, new KyberConverter());
        converters.put(BCObjectIdentifiers.kyber768_aes, new KyberConverter());
        converters.put(BCObjectIdentifiers.kyber1024_aes, new KyberConverter());
        converters.put(BCObjectIdentifiers.ntrulpr653, new NTRULPrimeConverter());
        converters.put(BCObjectIdentifiers.ntrulpr761, new NTRULPrimeConverter());
        converters.put(BCObjectIdentifiers.ntrulpr857, new NTRULPrimeConverter());
        converters.put(BCObjectIdentifiers.ntrulpr953, new NTRULPrimeConverter());
        converters.put(BCObjectIdentifiers.ntrulpr1013, new NTRULPrimeConverter());
        converters.put(BCObjectIdentifiers.ntrulpr1277, new NTRULPrimeConverter());
        converters.put(BCObjectIdentifiers.sntrup653, new SNTRUPrimeConverter());
        converters.put(BCObjectIdentifiers.sntrup761, new SNTRUPrimeConverter());
        converters.put(BCObjectIdentifiers.sntrup857, new SNTRUPrimeConverter());
        converters.put(BCObjectIdentifiers.sntrup953, new SNTRUPrimeConverter());
        converters.put(BCObjectIdentifiers.sntrup1013, new SNTRUPrimeConverter());
        converters.put(BCObjectIdentifiers.sntrup1277, new SNTRUPrimeConverter());
        converters.put(BCObjectIdentifiers.dilithium2, new DilithiumConverter());
        converters.put(BCObjectIdentifiers.dilithium3, new DilithiumConverter());
        converters.put(BCObjectIdentifiers.dilithium5, new DilithiumConverter());
        converters.put(BCObjectIdentifiers.dilithium2_aes, new DilithiumConverter());
        converters.put(BCObjectIdentifiers.dilithium3_aes, new DilithiumConverter());
        converters.put(BCObjectIdentifiers.dilithium5_aes, new DilithiumConverter());
        converters.put(BCObjectIdentifiers.bike128, new BIKEConverter());
        converters.put(BCObjectIdentifiers.bike192, new BIKEConverter());
        converters.put(BCObjectIdentifiers.bike256, new BIKEConverter());
        converters.put(BCObjectIdentifiers.hqc128, new HQCConverter());
        converters.put(BCObjectIdentifiers.hqc192, new HQCConverter());
        converters.put(BCObjectIdentifiers.hqc256, new HQCConverter());
    }

    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        return createKey(subjectPublicKeyInfo, null);
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
        AlgorithmIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm();
        SubjectPublicKeyInfoConverter subjectPublicKeyInfoConverter = (SubjectPublicKeyInfoConverter) converters.get(algorithm.getAlgorithm());
        if (subjectPublicKeyInfoConverter != null) {
            return subjectPublicKeyInfoConverter.getPublicKeyParameters(subjectPublicKeyInfo, obj);
        }
        throw new IOException("algorithm identifier in public key not recognised: " + algorithm.getAlgorithm());
    }

    public static AsymmetricKeyParameter createKey(byte[] bArr) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }
}
