package org.bouncycastle.oer.its.template.ieee1609dot2.basetypes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.oer.OERDefinition;

/* loaded from: classes2.dex */
public class Ieee1609Dot2BaseTypes {
    public static final OERDefinition.Builder BasePublicEncryptionKey;
    public static final OERDefinition.Builder BitmapSsp;
    public static final OERDefinition.Builder BitmapSspRange;
    public static final OERDefinition.Builder CircularRegion;
    public static final OERDefinition.Builder CountryAndRegions;
    public static final OERDefinition.Builder CountryAndSubregions;
    public static final OERDefinition.Builder CountryOnly;
    public static final OERDefinition.Builder CrlSeries;
    public static final OERDefinition.Builder Duration;
    public static final OERDefinition.Builder EccP256CurvePoint;
    public static final OERDefinition.Builder EccP384CurvePoint;
    public static final OERDefinition.Builder EcdsaP256Signature;
    public static final OERDefinition.Builder EcdsaP384Signature;
    public static final OERDefinition.Builder EciesP256EncryptedKey;
    public static final OERDefinition.Builder Elevation;
    public static final OERDefinition.Builder EncryptionKey;
    public static final OERDefinition.Builder GeographicRegion;
    public static final OERDefinition.Builder GroupLinkageValue;
    public static final OERDefinition.Builder HashAlgorithm;
    public static final OERDefinition.Builder HashedId10;
    public static final OERDefinition.Builder HashedId3;
    public static final OERDefinition.Builder HashedId32;
    public static final OERDefinition.Builder HashedId8;
    public static final OERDefinition.Builder Hostname;
    public static final OERDefinition.Builder IValue;
    public static final OERDefinition.Builder IdentifiedRegion;
    public static final OERDefinition.Builder KnownLatitude;
    public static final OERDefinition.Builder KnownLongitude;
    public static final OERDefinition.Builder LaId;
    public static final OERDefinition.Builder Latitude;
    public static final OERDefinition.Builder LinkageSeed;
    public static final OERDefinition.Builder LinkageValue;
    public static final OERDefinition.Builder Longitude;
    public static final OERDefinition.Builder NinetyDegreeInt;
    public static final OERDefinition.Builder OneEightyDegreeInt;
    public static final OERDefinition.Builder Point256;
    public static final OERDefinition.Builder Point384;
    public static final OERDefinition.Builder PolygonalRegion;
    public static final OERDefinition.Builder Psid;
    public static final OERDefinition.Builder PsidSsp;
    public static final OERDefinition.Builder PsidSspRange;
    public static final OERDefinition.Builder PublicEncryptionKey;
    public static final OERDefinition.Builder PublicVerificationKey;
    public static final OERDefinition.Builder RectangularRegion;
    public static final OERDefinition.Builder RegionAndSubregions;
    public static final OERDefinition.Builder SequenceOfHashedId3;
    public static final OERDefinition.Builder SequenceOfHashedId8;
    public static final OERDefinition.Builder SequenceOfIdentifiedRegion;
    public static final OERDefinition.Builder SequenceOfOctetString;
    public static final OERDefinition.Builder SequenceOfPsid;
    public static final OERDefinition.Builder SequenceOfPsidSsp;
    public static final OERDefinition.Builder SequenceOfPsidSspRange;
    public static final OERDefinition.Builder SequenceOfRectangularRegion;
    public static final OERDefinition.Builder SequenceOfRegionAndSubregions;
    public static final OERDefinition.Builder SequenceOfUint16;
    public static final OERDefinition.Builder SequenceOfUint8;
    public static final OERDefinition.Builder ServiceSpecificPermissions;
    public static final OERDefinition.Builder Signature;
    public static final OERDefinition.Builder SspRange;
    public static final OERDefinition.Builder SubjectAssurance;
    public static final OERDefinition.Builder SymmAlgorithm;
    public static final OERDefinition.Builder SymmetricEncryptionKey;
    public static final OERDefinition.Builder ThreeDLocation;
    public static final OERDefinition.Builder Time32;
    public static final OERDefinition.Builder Time64;
    public static final OERDefinition.Builder TwoDLocation;
    public static final OERDefinition.Builder UINT16;
    public static final OERDefinition.Builder UINT3 = OERDefinition.integer(0, 7).typeName("UINT3");
    public static final OERDefinition.Builder UINT32;
    public static final OERDefinition.Builder UINT64;
    public static final OERDefinition.Builder UINT8;
    public static final OERDefinition.Builder UnknownLatitude;
    public static final OERDefinition.Builder UnknownLongitude;
    public static final OERDefinition.Builder ValidityPeriod;

    static {
        OERDefinition.Builder typeName = OERDefinition.integer(0L, 255L).typeName("UINT8");
        UINT8 = typeName;
        OERDefinition.Builder typeName2 = OERDefinition.integer(0L, 65535L).typeName("UINT16");
        UINT16 = typeName2;
        OERDefinition.Builder typeName3 = OERDefinition.integer(0L, (long) BodyPartID.bodyIdMax).typeName("UINT32");
        UINT32 = typeName3;
        OERDefinition.Builder typeName4 = OERDefinition.integer(BigInteger.ZERO, new BigInteger("18446744073709551615")).typeName("UINT64");
        UINT64 = typeName4;
        OERDefinition.Builder typeName5 = OERDefinition.seqof(typeName2).typeName("SequenceOfUint16");
        SequenceOfUint16 = typeName5;
        OERDefinition.Builder typeName6 = OERDefinition.seqof(typeName).typeName("SequenceOfUint8");
        SequenceOfUint8 = typeName6;
        OERDefinition.Builder typeName7 = OERDefinition.octets(3).typeName("HashedId3");
        HashedId3 = typeName7;
        OERDefinition.Builder typeName8 = OERDefinition.octets(8).typeName("HashedId8");
        HashedId8 = typeName8;
        HashedId10 = OERDefinition.octets(10).typeName("HashedId10");
        HashedId32 = OERDefinition.octets(32).typeName("HashedId32");
        SequenceOfHashedId3 = OERDefinition.seqof(typeName7).typeName("SequenceOfHashedId3");
        SequenceOfHashedId8 = OERDefinition.seqof(typeName8).typeName("SequenceOfHashedId8");
        OERDefinition.Builder typeName9 = typeName3.typeName("Time32");
        Time32 = typeName9;
        Time64 = typeName4.typeName("Time64");
        OERDefinition.Builder typeName10 = OERDefinition.choice(typeName2.label("microseconds"), typeName2.label("milliseconds"), typeName2.label("seconds"), typeName2.label("minutes"), typeName2.label("hours"), typeName2.label("sixtyHours"), typeName2.label("years")).typeName("Duration");
        Duration = typeName10;
        ValidityPeriod = OERDefinition.seq(typeName9.label("start"), typeName10.label(TypedValues.TransitionType.S_DURATION)).typeName("ValidityPeriod");
        IValue = typeName2.copy().typeName("IValue");
        Hostname = OERDefinition.utf8String(0, 255).typeName("Hostname");
        LinkageValue = OERDefinition.octets(9).typeName("LinkageValue");
        GroupLinkageValue = OERDefinition.seq(OERDefinition.octets(4).label("jValue"), OERDefinition.octets(9).label("value")).typeName("GroupLinkageValue");
        LaId = OERDefinition.octets(2).typeName("LaId");
        LinkageSeed = OERDefinition.octets(16).typeName("LinkageSeed");
        OERDefinition.Builder typeName11 = OERDefinition.seq(OERDefinition.octets(32).label("x"), OERDefinition.octets(32).label(OperatorName.CURVE_TO_REPLICATE_FINAL_POINT)).typeName("Point256");
        Point256 = typeName11;
        OERDefinition.Builder typeName12 = OERDefinition.choice(OERDefinition.octets(32).label("x-only"), OERDefinition.nullValue().label("fill"), OERDefinition.octets(32).label("compressed-y-0"), OERDefinition.octets(32).label("compressed-y-1"), typeName11.label("uncompressedP256")).typeName("EccP256CurvePoint");
        EccP256CurvePoint = typeName12;
        OERDefinition.Builder typeName13 = OERDefinition.seq(typeName12.label("rSig"), OERDefinition.octets(32).label("sSig")).typeName("EcdsaP256Signature");
        EcdsaP256Signature = typeName13;
        OERDefinition.Builder typeName14 = OERDefinition.seq(OERDefinition.octets(48).label("x"), OERDefinition.octets(48).label(OperatorName.CURVE_TO_REPLICATE_FINAL_POINT)).typeName("Point384");
        Point384 = typeName14;
        OERDefinition.Builder typeName15 = OERDefinition.choice(OERDefinition.octets(48).label("x-only"), OERDefinition.nullValue().label("fill"), OERDefinition.octets(48).label("compressed-y-0"), OERDefinition.octets(48).label("compressed-y-1"), typeName14.label("uncompressedP384")).typeName("EccP384CurvePoint");
        EccP384CurvePoint = typeName15;
        OERDefinition.Builder typeName16 = OERDefinition.seq(typeName15.label("rSig"), OERDefinition.octets(48).label("sSig")).typeName("EcdsaP384Signature");
        EcdsaP384Signature = typeName16;
        Signature = OERDefinition.choice(typeName13.label("ecdsaNistP256Signature"), typeName13.label("ecdsaBrainpoolP256r1Signature"), OERDefinition.extension(typeName16.label("ecdsaBrainpoolP384r1Signature"))).typeName("Signature");
        OERDefinition.Builder typeName17 = OERDefinition.enumeration(OERDefinition.enumItem("aes128Ccm"), OERDefinition.extension(new Object[0])).typeName("SymmAlgorithm");
        SymmAlgorithm = typeName17;
        HashAlgorithm = OERDefinition.enumeration(OERDefinition.enumItem("sha256"), OERDefinition.extension(OERDefinition.enumItem("sha384"))).typeName("HashAlgorithm");
        EciesP256EncryptedKey = OERDefinition.seq(typeName12.copy().label(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT), OERDefinition.octets(16).label(OperatorName.CURVE_TO), OERDefinition.octets(16).label("t")).typeName("EciesP256EncryptedKey");
        OERDefinition.Builder typeName18 = OERDefinition.choice(typeName12.label("eciesNistP256"), typeName12.label("eciesBrainpoolP256r1"), OERDefinition.extension(new Object[0])).typeName("BasePublicEncryptionKey");
        BasePublicEncryptionKey = typeName18;
        OERDefinition.Builder typeName19 = OERDefinition.choice(OERDefinition.octets(16).label("aes128Ccm"), OERDefinition.extension(new Object[0])).typeName("SymmetricEncryptionKey");
        SymmetricEncryptionKey = typeName19;
        OERDefinition.Builder typeName20 = OERDefinition.seq(typeName17.label("supportedSymmAlg"), typeName18.label("publicKey")).typeName("PublicEncryptionKey");
        PublicEncryptionKey = typeName20;
        EncryptionKey = OERDefinition.choice(typeName20.label("publicOption"), typeName19.label("symmetric")).typeName("EncryptionKey");
        PublicVerificationKey = OERDefinition.choice(typeName12.label("ecdsaNistP256"), typeName12.label("ecdsaBrainpoolP256r1"), OERDefinition.extension(typeName15.label("ecdsaBrainpoolP384r1"))).typeName("PublicVerificationKey");
        OERDefinition.Builder typeName21 = OERDefinition.integer().rangeToMAXFrom(0L).typeName("Psid");
        Psid = typeName21;
        OERDefinition.Builder typeName22 = OERDefinition.octets(0, 31).typeName("BitmapSsp");
        BitmapSsp = typeName22;
        OERDefinition.Builder typeName23 = OERDefinition.choice(OERDefinition.octets().unbounded().label("opaque"), OERDefinition.extension(typeName22)).typeName("ServiceSpecificPermissions");
        ServiceSpecificPermissions = typeName23;
        OERDefinition.Builder typeName24 = OERDefinition.seq(typeName21.label("psid"), OERDefinition.optional(typeName23.label("ssp"))).typeName("PsidSsp");
        PsidSsp = typeName24;
        SequenceOfPsidSsp = OERDefinition.seqof(typeName24).typeName("SequenceOfPsidSsp");
        SequenceOfPsid = OERDefinition.seqof(typeName21).typeName("SequenceOfPsid");
        OERDefinition.Builder typeName25 = OERDefinition.seqof(OERDefinition.octets().rangeToMAXFrom(0L)).typeName("SequenceOfOctetString");
        SequenceOfOctetString = typeName25;
        OERDefinition.Builder typeName26 = OERDefinition.seq(OERDefinition.octets(1, 32).label("sspValue"), OERDefinition.octets(1, 32).label("sspBitMask")).typeName("BitmapSspRange");
        BitmapSspRange = typeName26;
        OERDefinition.Builder typeName27 = OERDefinition.choice(typeName25.label("opaque"), OERDefinition.nullValue().label("all"), OERDefinition.extension(typeName26.label("bitmapSspRange"))).typeName("SspRange");
        SspRange = typeName27;
        OERDefinition.Builder typeName28 = OERDefinition.seq(typeName21.label("psid"), OERDefinition.optional(typeName27.label("sspRange"))).typeName("PsidSspRange");
        PsidSspRange = typeName28;
        SequenceOfPsidSspRange = OERDefinition.seqof(typeName28).typeName("SequenceOfPsidSspRange");
        SubjectAssurance = OERDefinition.octets(1).typeName("SubjectAssurance");
        CrlSeries = typeName2.typeName("CrlSeries");
        OERDefinition.Builder typeName29 = typeName2.typeName("CountryOnly");
        CountryOnly = typeName29;
        OERDefinition.Builder typeName30 = OERDefinition.seq(typeName29.label("countryOnly"), typeName6.label("regions")).typeName("CountryAndRegions");
        CountryAndRegions = typeName30;
        OERDefinition.Builder typeName31 = OERDefinition.seq(typeName.label("region"), typeName5.label("subregions")).typeName("RegionAndSubregions");
        RegionAndSubregions = typeName31;
        OERDefinition.Builder typeName32 = OERDefinition.seqof(typeName31).typeName("SequenceOfRegionAndSubregions");
        SequenceOfRegionAndSubregions = typeName32;
        OERDefinition.Builder typeName33 = OERDefinition.seq(typeName29.label("country"), typeName32.label("regionAndSubregions")).typeName("CountryAndSubregions");
        CountryAndSubregions = typeName33;
        OERDefinition.Builder typeName34 = OERDefinition.choice(typeName29.label("countryOnly"), typeName30.label("countryAndRegions"), typeName33.label("countryAndSubregions"), OERDefinition.extension(new Object[0])).typeName("IdentifiedRegion");
        IdentifiedRegion = typeName34;
        OERDefinition.Builder typeName35 = OERDefinition.seqof(typeName34).typeName("SequenceOfIdentifiedRegion");
        SequenceOfIdentifiedRegion = typeName35;
        OERDefinition.Builder typeName36 = OERDefinition.integer(-1799999999L, 1800000001L).typeName("OneEightyDegreeInt");
        OneEightyDegreeInt = typeName36;
        KnownLongitude = typeName36.copy().typeName("KnownLongitude");
        UnknownLongitude = OERDefinition.integer().validSwitchValue(new ASN1Integer(1800000001L)).typeName("UnknownLongitude");
        OERDefinition.Builder typeName37 = OERDefinition.integer(-900000000L, 900000001L).typeName("NinetyDegreeInt");
        NinetyDegreeInt = typeName37;
        KnownLatitude = typeName37.copy().typeName("KnownLatitude");
        UnknownLatitude = OERDefinition.integer().validSwitchValue(new ASN1Integer(900000001L)).typeName("UnknownLatitude");
        OERDefinition.Builder typeName38 = typeName2.typeName("Elevation");
        Elevation = typeName38;
        OERDefinition.Builder typeName39 = typeName36.copy().typeName("Longitude");
        Longitude = typeName39;
        OERDefinition.Builder typeName40 = typeName37.copy().typeName("Latitude");
        Latitude = typeName40;
        ThreeDLocation = OERDefinition.seq(typeName40.label("latitude"), typeName39.label("longitude"), typeName38.label("elevation")).typeName("ThreeDLocation");
        OERDefinition.Builder typeName41 = OERDefinition.seq(typeName40.label("latitude"), typeName39.label("longitude")).typeName("TwoDLocation");
        TwoDLocation = typeName41;
        OERDefinition.Builder typeName42 = OERDefinition.seq(typeName41.label("northWest"), typeName41.label("southEast")).typeName("RectangularRegion");
        RectangularRegion = typeName42;
        OERDefinition.Builder typeName43 = OERDefinition.seqof(typeName42).typeName("SequenceOfRectangularRegion");
        SequenceOfRectangularRegion = typeName43;
        OERDefinition.Builder typeName44 = OERDefinition.seq(typeName41.label("center"), typeName2.label("radius")).typeName("CircularRegion");
        CircularRegion = typeName44;
        OERDefinition.Builder typeName45 = OERDefinition.seqof(typeName41).rangeToMAXFrom(3L).typeName("PolygonalRegion");
        PolygonalRegion = typeName45;
        GeographicRegion = OERDefinition.choice(typeName44.label("circularRegion"), typeName43.label("rectangularRegion"), typeName45.label("polygonalRegion"), typeName35.label("identifiedRegion"), OERDefinition.extension(new Object[0])).typeName("GeographicRegion");
    }
}
