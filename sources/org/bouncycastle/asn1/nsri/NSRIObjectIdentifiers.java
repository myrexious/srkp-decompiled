package org.bouncycastle.asn1.nsri;

import androidx.exifinterface.media.ExifInterface;
import androidx.room.RoomMasterTable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes2.dex */
public interface NSRIObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_algorithm;
    public static final ASN1ObjectIdentifier id_aria128_cbc;
    public static final ASN1ObjectIdentifier id_aria128_ccm;
    public static final ASN1ObjectIdentifier id_aria128_cfb;
    public static final ASN1ObjectIdentifier id_aria128_cmac;
    public static final ASN1ObjectIdentifier id_aria128_ctr;
    public static final ASN1ObjectIdentifier id_aria128_ecb;
    public static final ASN1ObjectIdentifier id_aria128_gcm;
    public static final ASN1ObjectIdentifier id_aria128_kw;
    public static final ASN1ObjectIdentifier id_aria128_kwp;
    public static final ASN1ObjectIdentifier id_aria128_ocb2;
    public static final ASN1ObjectIdentifier id_aria128_ofb;
    public static final ASN1ObjectIdentifier id_aria192_cbc;
    public static final ASN1ObjectIdentifier id_aria192_ccm;
    public static final ASN1ObjectIdentifier id_aria192_cfb;
    public static final ASN1ObjectIdentifier id_aria192_cmac;
    public static final ASN1ObjectIdentifier id_aria192_ctr;
    public static final ASN1ObjectIdentifier id_aria192_ecb;
    public static final ASN1ObjectIdentifier id_aria192_gcm;
    public static final ASN1ObjectIdentifier id_aria192_kw;
    public static final ASN1ObjectIdentifier id_aria192_kwp;
    public static final ASN1ObjectIdentifier id_aria192_ocb2;
    public static final ASN1ObjectIdentifier id_aria192_ofb;
    public static final ASN1ObjectIdentifier id_aria256_cbc;
    public static final ASN1ObjectIdentifier id_aria256_ccm;
    public static final ASN1ObjectIdentifier id_aria256_cfb;
    public static final ASN1ObjectIdentifier id_aria256_cmac;
    public static final ASN1ObjectIdentifier id_aria256_ctr;
    public static final ASN1ObjectIdentifier id_aria256_ecb;
    public static final ASN1ObjectIdentifier id_aria256_gcm;
    public static final ASN1ObjectIdentifier id_aria256_kw;
    public static final ASN1ObjectIdentifier id_aria256_kwp;
    public static final ASN1ObjectIdentifier id_aria256_ocb2;
    public static final ASN1ObjectIdentifier id_aria256_ofb;
    public static final ASN1ObjectIdentifier id_pad;
    public static final ASN1ObjectIdentifier id_pad_1;
    public static final ASN1ObjectIdentifier id_pad_null;
    public static final ASN1ObjectIdentifier id_sea;
    public static final ASN1ObjectIdentifier nsri;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.2.410.200046");
        nsri = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("1");
        id_algorithm = branch;
        ASN1ObjectIdentifier branch2 = branch.branch("1");
        id_sea = branch2;
        id_pad = branch.branch(ExifInterface.GPS_MEASUREMENT_2D);
        id_pad_null = branch.branch("0");
        id_pad_1 = branch.branch("1");
        id_aria128_ecb = branch2.branch("1");
        id_aria128_cbc = branch2.branch(ExifInterface.GPS_MEASUREMENT_2D);
        id_aria128_cfb = branch2.branch(ExifInterface.GPS_MEASUREMENT_3D);
        id_aria128_ofb = branch2.branch("4");
        id_aria128_ctr = branch2.branch("5");
        id_aria192_ecb = branch2.branch("6");
        id_aria192_cbc = branch2.branch("7");
        id_aria192_cfb = branch2.branch("8");
        id_aria192_ofb = branch2.branch("9");
        id_aria192_ctr = branch2.branch("10");
        id_aria256_ecb = branch2.branch("11");
        id_aria256_cbc = branch2.branch("12");
        id_aria256_cfb = branch2.branch("13");
        id_aria256_ofb = branch2.branch("14");
        id_aria256_ctr = branch2.branch("15");
        id_aria128_cmac = branch2.branch("21");
        id_aria192_cmac = branch2.branch("22");
        id_aria256_cmac = branch2.branch("23");
        id_aria128_ocb2 = branch2.branch("31");
        id_aria192_ocb2 = branch2.branch("32");
        id_aria256_ocb2 = branch2.branch("33");
        id_aria128_gcm = branch2.branch("34");
        id_aria192_gcm = branch2.branch("35");
        id_aria256_gcm = branch2.branch("36");
        id_aria128_ccm = branch2.branch("37");
        id_aria192_ccm = branch2.branch("38");
        id_aria256_ccm = branch2.branch("39");
        id_aria128_kw = branch2.branch("40");
        id_aria192_kw = branch2.branch("41");
        id_aria256_kw = branch2.branch(RoomMasterTable.DEFAULT_ID);
        id_aria128_kwp = branch2.branch("43");
        id_aria192_kwp = branch2.branch("44");
        id_aria256_kwp = branch2.branch("45");
    }
}
