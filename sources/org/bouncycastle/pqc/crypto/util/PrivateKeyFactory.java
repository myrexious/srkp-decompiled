package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.CMCEPrivateKey;
import org.bouncycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPrivateKey;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.bike.BIKEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.saber.SABERPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sike.SIKEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLAPrivateKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes2.dex */
public class PrivateKeyFactory {
    private static short[] convert(byte[] bArr) {
        int length = bArr.length / 2;
        short[] sArr = new short[length];
        for (int i = 0; i != length; i++) {
            sArr[i] = Pack.littleEndianToShort(bArr, i * 2);
        }
        return sArr;
    }

    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    public static AsymmetricKeyParameter createKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if (algorithm.on(BCObjectIdentifiers.qTESLA)) {
            return new QTESLAPrivateKeyParameters(Utils.qTeslaLookupSecurityCategory(privateKeyInfo.getPrivateKeyAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
        } else if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.sphincs256)) {
            return new SPHINCSPrivateKeyParameters(ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets(), Utils.sphincs256LookupTreeAlgName(SPHINCS256KeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters())));
        } else {
            if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.newHope)) {
                return new NHPrivateKeyParameters(convert(ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets()));
            }
            if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_hss_lms_hashsig)) {
                byte[] octets = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
                ASN1BitString publicKeyData = privateKeyInfo.getPublicKeyData();
                if (Pack.bigEndianToInt(octets, 0) != 1) {
                    if (publicKeyData != null) {
                        return HSSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length), publicKeyData.getOctets());
                    }
                    return HSSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length));
                } else if (publicKeyData != null) {
                    byte[] octets2 = publicKeyData.getOctets();
                    return LMSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length), Arrays.copyOfRange(octets2, 4, octets2.length));
                } else {
                    return LMSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length));
                }
            } else if (algorithm.on(BCObjectIdentifiers.sphincsPlus)) {
                byte[] octets3 = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
                return new SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters.getParams(Integers.valueOf(Pack.bigEndianToInt(octets3, 0))), Arrays.copyOfRange(octets3, 4, octets3.length));
            } else if (algorithm.on(BCObjectIdentifiers.picnic)) {
                return new PicnicPrivateKeyParameters(Utils.picnicParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_mceliece)) {
                CMCEPrivateKey cMCEPrivateKey = CMCEPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                return new CMCEPrivateKeyParameters(Utils.mcElieceParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), cMCEPrivateKey.getDelta(), cMCEPrivateKey.getC(), cMCEPrivateKey.getG(), cMCEPrivateKey.getAlpha(), cMCEPrivateKey.getS());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_frodo)) {
                return new FrodoPrivateKeyParameters(Utils.frodoParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_saber)) {
                return new SABERPrivateKeyParameters(Utils.saberParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_sike)) {
                return new SIKEPrivateKeyParameters(Utils.sikeParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_ntru)) {
                return new NTRUPrivateKeyParameters(Utils.ntruParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_kyber)) {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                KyberParameters kyberParamsLookup = Utils.kyberParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
                int intValueExact = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).intValueExact();
                if (intValueExact == 0) {
                    if (privateKeyInfo.getPublicKeyData() != null) {
                        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(privateKeyInfo.getPublicKeyData().getOctets());
                        return new KyberPrivateKeyParameters(kyberParamsLookup, DEROctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets(), DEROctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets(), DEROctetString.getInstance(aSN1Sequence.getObjectAt(3)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(1)).getOctets());
                    }
                    return new KyberPrivateKeyParameters(kyberParamsLookup, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(3)).getOctets(), null, null);
                }
                throw new IOException("unknown private key version: " + intValueExact);
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_ntrulprime)) {
                ASN1Sequence aSN1Sequence3 = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                return new NTRULPRimePrivateKeyParameters(Utils.ntrulprimeParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(aSN1Sequence3.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence3.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence3.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence3.getObjectAt(3)).getOctets());
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_sntruprime)) {
                ASN1Sequence aSN1Sequence4 = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                return new SNTRUPrimePrivateKeyParameters(Utils.sntruprimeParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(aSN1Sequence4.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence4.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence4.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence4.getObjectAt(3)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence4.getObjectAt(4)).getOctets());
            } else if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium2) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium3) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium5) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium2_aes) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium3_aes) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium5_aes)) {
                ASN1Sequence aSN1Sequence5 = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                DilithiumParameters dilithiumParamsLookup = Utils.dilithiumParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
                int intValueExact2 = ASN1Integer.getInstance(aSN1Sequence5.getObjectAt(0)).intValueExact();
                if (intValueExact2 == 0) {
                    if (privateKeyInfo.getPublicKeyData() != null) {
                        return new DilithiumPrivateKeyParameters(dilithiumParamsLookup, ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(1)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(2)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(3)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(4)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(5)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(6)).getOctets(), ASN1OctetString.getInstance(ASN1Sequence.getInstance(privateKeyInfo.getPublicKeyData().getOctets()).getObjectAt(1)).getOctets());
                    }
                    return new DilithiumPrivateKeyParameters(dilithiumParamsLookup, ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(1)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(2)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(3)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(4)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(5)).getOctets(), ASN1BitString.getInstance(aSN1Sequence5.getObjectAt(6)).getOctets(), null);
                }
                throw new IOException("unknown private key version: " + intValueExact2);
            } else if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.falcon_512) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.falcon_1024)) {
                ASN1Sequence aSN1Sequence6 = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                FalconParameters falconParamsLookup = Utils.falconParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
                ASN1BitString publicKeyData2 = privateKeyInfo.getPublicKeyData();
                int intValueExact3 = ASN1Integer.getInstance(aSN1Sequence6.getObjectAt(0)).intValueExact();
                if (intValueExact3 == 1) {
                    return privateKeyInfo.getPublicKeyData() != null ? new FalconPrivateKeyParameters(falconParamsLookup, ASN1OctetString.getInstance(aSN1Sequence6.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence6.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence6.getObjectAt(3)).getOctets(), publicKeyData2.getOctets()) : new FalconPrivateKeyParameters(falconParamsLookup, ASN1OctetString.getInstance(aSN1Sequence6.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence6.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(aSN1Sequence6.getObjectAt(3)).getOctets(), null);
                }
                throw new IOException("unknown private key version: " + intValueExact3);
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_bike)) {
                byte[] octets4 = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
                BIKEParameters bikeParamsLookup = Utils.bikeParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
                return new BIKEPrivateKeyParameters(bikeParamsLookup, Arrays.copyOfRange(octets4, 0, bikeParamsLookup.getRByte()), Arrays.copyOfRange(octets4, bikeParamsLookup.getRByte(), bikeParamsLookup.getRByte() * 2), Arrays.copyOfRange(octets4, bikeParamsLookup.getRByte() * 2, octets4.length));
            } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_hqc)) {
                return new HQCPrivateKeyParameters(Utils.hqcParamsLookup(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.xmss)) {
                XMSSKeyParams xMSSKeyParams = XMSSKeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
                ASN1ObjectIdentifier algorithm2 = xMSSKeyParams.getTreeDigest().getAlgorithm();
                XMSSPrivateKey xMSSPrivateKey = XMSSPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                try {
                    XMSSPrivateKeyParameters.Builder withRoot = new XMSSPrivateKeyParameters.Builder(new XMSSParameters(xMSSKeyParams.getHeight(), Utils.getDigest(algorithm2))).withIndex(xMSSPrivateKey.getIndex()).withSecretKeySeed(xMSSPrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSPrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSPrivateKey.getPublicSeed()).withRoot(xMSSPrivateKey.getRoot());
                    if (xMSSPrivateKey.getVersion() != 0) {
                        withRoot.withMaxIndex(xMSSPrivateKey.getMaxIndex());
                    }
                    if (xMSSPrivateKey.getBdsState() != null) {
                        withRoot.withBDSState(((BDS) XMSSUtil.deserialize(xMSSPrivateKey.getBdsState(), BDS.class)).withWOTSDigest(algorithm2));
                    }
                    return withRoot.build();
                } catch (ClassNotFoundException e) {
                    throw new IOException("ClassNotFoundException processing BDS state: " + e.getMessage());
                }
            } else if (!algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.xmss_mt)) {
                if (algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.mcElieceCca2)) {
                    McElieceCCA2PrivateKey mcElieceCCA2PrivateKey = McElieceCCA2PrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    return new McElieceCCA2PrivateKeyParameters(mcElieceCCA2PrivateKey.getN(), mcElieceCCA2PrivateKey.getK(), mcElieceCCA2PrivateKey.getField(), mcElieceCCA2PrivateKey.getGoppaPoly(), mcElieceCCA2PrivateKey.getP(), Utils.getDigestName(mcElieceCCA2PrivateKey.getDigest().getAlgorithm()));
                }
                throw new RuntimeException("algorithm identifier in private key not recognised");
            } else {
                XMSSMTKeyParams xMSSMTKeyParams = XMSSMTKeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
                ASN1ObjectIdentifier algorithm3 = xMSSMTKeyParams.getTreeDigest().getAlgorithm();
                try {
                    XMSSMTPrivateKey xMSSMTPrivateKey = XMSSMTPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    XMSSMTPrivateKeyParameters.Builder withRoot2 = new XMSSMTPrivateKeyParameters.Builder(new XMSSMTParameters(xMSSMTKeyParams.getHeight(), xMSSMTKeyParams.getLayers(), Utils.getDigest(algorithm3))).withIndex(xMSSMTPrivateKey.getIndex()).withSecretKeySeed(xMSSMTPrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSMTPrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSMTPrivateKey.getPublicSeed()).withRoot(xMSSMTPrivateKey.getRoot());
                    if (xMSSMTPrivateKey.getVersion() != 0) {
                        withRoot2.withMaxIndex(xMSSMTPrivateKey.getMaxIndex());
                    }
                    if (xMSSMTPrivateKey.getBdsState() != null) {
                        withRoot2.withBDSState(((BDSStateMap) XMSSUtil.deserialize(xMSSMTPrivateKey.getBdsState(), BDSStateMap.class)).withWOTSDigest(algorithm3));
                    }
                    return withRoot2.build();
                } catch (ClassNotFoundException e2) {
                    throw new IOException("ClassNotFoundException processing BDS state: " + e2.getMessage());
                }
            }
        }
    }

    public static AsymmetricKeyParameter createKey(byte[] bArr) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }
}
