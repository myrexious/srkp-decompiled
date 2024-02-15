package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;
import java.util.Properties;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes2.dex */
abstract class Internal {
    protected static final int LOG2RADIX = 6;
    protected static final int RADIX = 64;
    protected int ALICE;
    protected long[] A_basis_zero;
    protected long[] A_gen;
    protected long[] Alice_order;
    protected int BOB;
    protected long[] B_basis_zero;
    protected long[] B_gen;
    protected long[] B_gen_3_tors;
    protected long[] Bob_order;
    protected boolean COMPRESS;
    protected int COMPRESSED_CHUNK_CT;
    protected int CRYPTO_BYTES;
    protected int CRYPTO_CIPHERTEXTBYTES;
    protected int CRYPTO_PUBLICKEYBYTES;
    protected int CRYPTO_SECRETKEYBYTES;
    protected int DLEN_2;
    protected int DLEN_3;
    protected int ELL2_EMODW;
    protected int ELL2_W;
    protected int ELL3_EMODW;
    protected int ELL3_W;
    protected int FP2_ENCODED_BYTES;
    protected int MASK2_BOB;
    protected int MASK3_BOB;
    protected int MASK_ALICE;
    protected int MASK_BOB;
    protected int MAXBITS_FIELD;
    protected int MAXBITS_ORDER;
    protected int MAXWORDS_FIELD;
    protected int MAX_Alice;
    protected int MAX_Bob;
    protected int MAX_INT_POINTS_ALICE;
    protected int MAX_INT_POINTS_BOB;
    protected int MSG_BYTES;
    protected long[] Montgomery_R;
    protected long[] Montgomery_R2;
    protected long[] Montgomery_RB1;
    protected long[] Montgomery_RB2;
    protected long[] Montgomery_one;
    protected int NBITS_FIELD;
    protected int NBITS_ORDER;
    protected int NWORDS64_FIELD;
    protected int NWORDS64_ORDER;
    protected int NWORDS_FIELD;
    protected int NWORDS_ORDER;
    protected int OALICE_BITS;
    protected int OBOB_BITS;
    protected int OBOB_EXPON;
    protected int ORDER_A_ENCODED_BYTES;
    protected int ORDER_B_ENCODED_BYTES;
    protected int PARAM_A;
    protected int PARAM_C;
    protected int PARTIALLY_COMPRESSED_CHUNK_CT;
    protected int PLEN_2;
    protected int PLEN_3;
    protected long[] PRIME;
    protected int PRIME_ZERO_WORDS;
    protected long[] PRIMEp1;
    protected long[] PRIMEp1x64;
    protected long[] PRIMEx16p;
    protected long[] PRIMEx2;
    protected long[] PRIMEx4;
    protected int SECRETKEY_A_BYTES;
    protected int SECRETKEY_B_BYTES;
    protected int TABLE_R_LEN;
    protected int TABLE_V3_LEN;
    protected int TABLE_V_LEN;
    protected long[] T_tate2_P;
    protected long[] T_tate2_Q;
    protected long[] T_tate2_firststep_P;
    protected long[] T_tate2_firststep_Q;
    protected long[] T_tate3;
    protected int UNCOMPRESSEDPK_BYTES;
    protected int W_2;
    protected int W_3;
    protected long[] XQB3;
    protected long[] g_R_S_im;
    protected long[] g_phiR_phiS_im;
    protected long[] g_phiR_phiS_re;
    protected long[] ph2_T;
    protected long[] ph2_T1;
    protected long[] ph2_T2;
    protected int[] ph2_path;
    protected long[] ph3_T;
    protected long[] ph3_T1;
    protected long[] ph3_T2;
    protected int[] ph3_path;
    protected int[] strat_Alice;
    protected int[] strat_Bob;
    protected long[][] table_r_qnr;
    protected long[][] table_r_qr;
    protected long[][] table_v_qnr;
    protected long[][] table_v_qr;
    protected long[] threeinv;
    protected long[] u0_entang;
    protected long[] u_entang;
    protected long[][][] v_3_torsion;

    public static long[] ReadFromProperty(Properties properties, String str, int i) {
        byte[] decode = Hex.decode(properties.getProperty(str).replaceAll(",", ""));
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < decode.length / 8; i2++) {
            jArr[i2] = Pack.bigEndianToLong(decode, i2 * 8);
        }
        return jArr;
    }

    public static long[][] ReadFromProperty(Properties properties, String str, int i, int i2) {
        byte[] decode = Hex.decode(properties.getProperty(str).replaceAll(",", ""));
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, i, i2);
        for (int i3 = 0; i3 < decode.length / 8; i3++) {
            jArr[i3 / i2][i3 % i2] = Pack.bigEndianToLong(decode, i3 * 8);
        }
        return jArr;
    }

    public static long[][][] ReadFromProperty(Properties properties, String str, int i, int i2, int i3) {
        byte[] decode = Hex.decode(properties.getProperty(str).replaceAll(",", ""));
        long[][][] jArr = (long[][][]) Array.newInstance(Long.TYPE, i, i2, i3);
        for (int i4 = 0; i4 < decode.length / 8; i4++) {
            int i5 = i2 * i3;
            jArr[i4 / i5][(i4 % i5) / i3][i4 % i3] = Pack.bigEndianToLong(decode, i4 * 8);
        }
        return jArr;
    }

    public static int[] ReadIntsFromProperty(Properties properties, String str, int i) {
        int[] iArr = new int[i];
        String[] split = properties.getProperty(str).split(",");
        for (int i2 = 0; i2 != split.length; i2++) {
            iArr[i2] = Integer.parseInt(split[i2]);
        }
        return iArr;
    }
}
