package org.bouncycastle.pqc.crypto.sike;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class P610 extends Internal {
    public P610(boolean z) {
        this.COMPRESS = z;
        this.CRYPTO_SECRETKEYBYTES = 524;
        this.CRYPTO_PUBLICKEYBYTES = 462;
        this.CRYPTO_BYTES = 24;
        this.CRYPTO_CIPHERTEXTBYTES = 486;
        if (z) {
            this.CRYPTO_SECRETKEYBYTES = 491;
            this.CRYPTO_PUBLICKEYBYTES = 274;
            this.CRYPTO_CIPHERTEXTBYTES = 336;
        }
        this.NWORDS_FIELD = 10;
        this.PRIME_ZERO_WORDS = 4;
        this.NBITS_FIELD = TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS;
        this.MAXBITS_FIELD = 640;
        this.MAXWORDS_FIELD = ((this.MAXBITS_FIELD + 64) - 1) / 64;
        this.NWORDS64_FIELD = (this.NBITS_FIELD + 63) / 64;
        this.NBITS_ORDER = DilithiumEngine.DilithiumPolyT1PackedBytes;
        this.NWORDS_ORDER = ((this.NBITS_ORDER + 64) - 1) / 64;
        this.NWORDS64_ORDER = (this.NBITS_ORDER + 63) / 64;
        this.MAXBITS_ORDER = this.NBITS_ORDER;
        this.ALICE = 0;
        this.BOB = 1;
        this.OALICE_BITS = 305;
        this.OBOB_BITS = 305;
        this.OBOB_EXPON = 192;
        this.MASK_ALICE = 1;
        this.MASK_BOB = 255;
        this.PARAM_A = 6;
        this.PARAM_C = 1;
        this.MAX_INT_POINTS_ALICE = 8;
        this.MAX_INT_POINTS_BOB = 10;
        this.MAX_Alice = 152;
        this.MAX_Bob = 192;
        this.MSG_BYTES = 24;
        this.SECRETKEY_A_BYTES = (this.OALICE_BITS + 7) / 8;
        this.SECRETKEY_B_BYTES = ((this.OBOB_BITS - 1) + 7) / 8;
        this.FP2_ENCODED_BYTES = ((this.NBITS_FIELD + 7) / 8) * 2;
        this.PRIME = new long[]{-1, -1, -1, -1, 7926898294125494271L, -5658687270105206738L, -7284643569098294869L, -5596370174845115094L, -9110763619386664820L, 10669696872L};
        this.PRIMEx2 = new long[]{-2, -1, -1, -1, -2592947485458563073L, 7129369533499138140L, 3877456935512961879L, 7254003724019321429L, 225216834936221977L, 21339393745L};
        this.PRIMEx4 = new long[]{-4, -1, -1, -1, -5185894970917126145L, -4188005006711275335L, 7754913871025923758L, -3938736625670908758L, 450433669872443954L, 42678787490L};
        this.PRIMEp1 = new long[]{0, 0, 0, 0, 7926898294125494272L, -5658687270105206738L, -7284643569098294869L, -5596370174845115094L, -9110763619386664820L, 10669696872L};
        this.PRIMEx16p = new long[]{16, 0, 0, 0, 4593671619917905920L, -3389448093728900558L, -6699078747078735223L, -5383595142051833172L, -3603469358979551639L, 9213098133652443887L, -2262032789190864537L, -3293888798786007681L, -3106877548450936536L, -7529360825512198962L, 423414579105418765L, -8091321387562703604L, -476084532282358204L, 4932507286707963453L, -4748761678580843653L, 98};
        this.Alice_order = new long[]{0, 0, 0, 0, 562949953421312L};
        this.Bob_order = new long[]{2806962120998467329L, -2332158411328333636L, -2775052578078765709L, 603808853150554410L, 349624627118280L};
        this.A_gen = new long[]{5771904529248994682L, -8436915071433390351L, 270330086766583390L, 6481898407746275289L, 7865854910092666580L, -2826674533944143030L, -7553167192889215565L, -9032646596491157233L, 5194719131280954495L, 1729770898, -1965085922052779020L, -4812970318505102637L, 1977403254395278860L, 2726390535525409621L, -248047565090072982L, -5454070453411567460L, -2860923682387992558L, 4095961562244124488L, -4917308312211097814L, 5102423139L, 2124736252400681868L, -7322998048931732039L, 2202127831239085027L, -3257580811259719115L, 4313963896834226850L, -5076443579667205976L, 4921946642166740880L, -3805954528561435943L, -2637702132890644254L, 7907518294L, 2200917311302176889L, 2939498022256786432L, -2284167071920397343L, -8889311404158421409L, -3057031954716630490L, -4860527247048815703L, -7932579696214058839L, 334500554730375393L, 6399318707077975086L, 8976719684L, 2746580562334225805L, -4441891846683360495L, -6980094913201629698L, -6647113845825354661L, 6714415832701611114L, -8334607330680099106L, 7010145936394111770L, -403948467988784721L, -7139691166611819809L, 3478841981L, -5079986323839184192L, 3119472779256121459L, 4709779656408495164L, -1878325030037470211L, -3755564803635956908L, 5893360609436446022L, 6635037533545129430L, 5031143778661013925L, 2711723078310815363L, 10114015515L};
        this.B_gen = new long[]{-4122797515158813510L, 2405435625630280597L, -3749193287274905454L, -2935559660200231368L, 7035179512456608727L, -1088186176647096380L, 2237320506219039519L, -4139295546001939262L, -2033832934895166963L, 5379123413L, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -754674776282359680L, 8155755975969367465L, -7108179690244256889L, 5678382275401384545L, -5945512278495342032L, 4726463275275376934L, -1362977618025674515L, 4479281637794437063L, -906589179791040647L, 9074793307L, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8829176751768485847L, 4330345099806588278L, 6439066249656508832L, -774629675044450053L, -5503580640218556678L, -9083173236816041803L, -1462621330690897264L, -8763703354377076720L, -2191538623876663267L, 9017845420L, 6790521807624498538L, -2290772438416721629L, 4283588130542979409L, 514645655039295889L, 8912678322428419353L, 6038404330050892853L, -1270586841300829686L, -8530139311933844284L, 910728456329037494L, 5513273805L};
        this.Montgomery_R2 = new long[]{-1774597335702472921L, -2253953328706275026L, 7764610893679053117L, -2558253936849870651L, 8311396451547473226L, -8866252043420476836L, 9136860735727631175L, -4198676031223070541L, 8077651299688882586L, 2385055731L};
        this.Montgomery_one = new long[]{1728891110, 0, 0, 0, -7335237893079695360L, 5591714530040314431L, 741431724485104668L, -3263383402897026590L, 1398651832995983165L, 4438944100L};
        this.strat_Alice = new int[]{67, 37, 21, 12, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 9, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1, 16, 9, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 33, 16, 8, 5, 2, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 16, 8, 4, 2, 1, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1};
        this.strat_Bob = new int[]{86, 48, 27, 15, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 12, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 21, 12, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 9, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1, 38, 21, 12, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 9, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1, 17, 9, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1};
        if (this.COMPRESS) {
            this.MASK2_BOB = 7;
            this.MASK3_BOB = 255;
            this.ORDER_A_ENCODED_BYTES = this.SECRETKEY_A_BYTES;
            this.ORDER_B_ENCODED_BYTES = this.SECRETKEY_B_BYTES + 1;
            this.PARTIALLY_COMPRESSED_CHUNK_CT = (this.ORDER_A_ENCODED_BYTES * 4) + this.FP2_ENCODED_BYTES + 2;
            this.COMPRESSED_CHUNK_CT = (this.ORDER_A_ENCODED_BYTES * 3) + this.FP2_ENCODED_BYTES + 2;
            this.UNCOMPRESSEDPK_BYTES = 480;
            this.TABLE_R_LEN = 17;
            this.TABLE_V_LEN = 34;
            this.TABLE_V3_LEN = 20;
            this.W_2 = 5;
            this.W_3 = 3;
            this.ELL2_W = 1 << this.W_2;
            this.ELL3_W = 27;
            this.ELL2_EMODW = 1 << (this.OALICE_BITS % this.W_2);
            this.ELL3_EMODW = 1;
            this.DLEN_2 = ((this.OALICE_BITS + this.W_2) - 1) / this.W_2;
            this.DLEN_3 = ((this.OBOB_EXPON + this.W_3) - 1) / this.W_3;
            this.PLEN_2 = 62;
            this.PLEN_3 = 65;
            InputStream resourceAsStream = P610.class.getResourceAsStream("p610.properties");
            Properties properties = new Properties();
            try {
                properties.load(resourceAsStream);
                this.ph2_path = Internal.ReadIntsFromProperty(properties, "ph2_path", this.PLEN_2);
                this.ph3_path = Internal.ReadIntsFromProperty(properties, "ph3_path", this.PLEN_3);
                this.A_gen = Internal.ReadFromProperty(properties, "A_gen", this.NWORDS64_FIELD * 6);
                this.B_gen = Internal.ReadFromProperty(properties, "B_gen", this.NWORDS64_FIELD * 6);
                this.XQB3 = Internal.ReadFromProperty(properties, "XQB3", this.NWORDS64_FIELD * 2);
                this.A_basis_zero = Internal.ReadFromProperty(properties, "A_basis_zero", this.NWORDS64_FIELD * 8);
                this.B_basis_zero = Internal.ReadFromProperty(properties, "B_basis_zero", this.NWORDS64_FIELD * 8);
                this.B_gen_3_tors = Internal.ReadFromProperty(properties, "B_gen_3_tors", this.NWORDS64_FIELD * 16);
                this.g_R_S_im = Internal.ReadFromProperty(properties, "g_R_S_im", this.NWORDS64_FIELD);
                this.Montgomery_R2 = Internal.ReadFromProperty(properties, "Montgomery_R2", this.NWORDS64_FIELD);
                this.Montgomery_RB1 = Internal.ReadFromProperty(properties, "Montgomery_RB1", this.NWORDS64_FIELD);
                this.Montgomery_RB2 = Internal.ReadFromProperty(properties, "Montgomery_RB2", this.NWORDS64_FIELD);
                this.Montgomery_one = Internal.ReadFromProperty(properties, "Montgomery_one", this.NWORDS64_FIELD);
                this.threeinv = Internal.ReadFromProperty(properties, "threeinv", this.NWORDS64_FIELD);
                this.u_entang = Internal.ReadFromProperty(properties, "u_entang", this.NWORDS64_FIELD * 2);
                this.u0_entang = Internal.ReadFromProperty(properties, "u0_entang", this.NWORDS64_FIELD * 2);
                this.table_r_qr = Internal.ReadFromProperty(properties, "table_r_qr", this.TABLE_R_LEN, this.NWORDS64_FIELD);
                this.table_r_qnr = Internal.ReadFromProperty(properties, "table_r_qnr", this.TABLE_R_LEN, this.NWORDS64_FIELD);
                this.table_v_qr = Internal.ReadFromProperty(properties, "table_v_qr", this.TABLE_V_LEN, this.NWORDS64_FIELD);
                this.table_v_qnr = Internal.ReadFromProperty(properties, "table_v_qnr", this.TABLE_V_LEN, this.NWORDS64_FIELD);
                this.v_3_torsion = Internal.ReadFromProperty(properties, "v_3_torsion", this.TABLE_V3_LEN, 2, this.NWORDS64_FIELD);
                this.T_tate3 = Internal.ReadFromProperty(properties, "T_tate3", (((this.OBOB_EXPON - 1) * 6) + 4) * this.NWORDS64_FIELD);
                this.T_tate2_firststep_P = Internal.ReadFromProperty(properties, "T_tate2_firststep_P", this.NWORDS64_FIELD * 4);
                this.T_tate2_P = Internal.ReadFromProperty(properties, "T_tate2_P", (this.OALICE_BITS - 2) * 3 * this.NWORDS64_FIELD);
                this.T_tate2_firststep_Q = Internal.ReadFromProperty(properties, "T_tate2_firststep_Q", this.NWORDS64_FIELD * 4);
                this.T_tate2_Q = Internal.ReadFromProperty(properties, "T_tate2_Q", (this.OALICE_BITS - 2) * 3 * this.NWORDS64_FIELD);
                this.ph2_T = Internal.ReadFromProperty(properties, "ph2_T", this.DLEN_2 * (this.ELL2_W >>> 1) * 2 * this.NWORDS64_FIELD);
                this.ph3_T = Internal.ReadFromProperty(properties, "ph3_T", this.DLEN_3 * (this.ELL3_W >>> 1) * 2 * this.NWORDS64_FIELD);
                this.Montgomery_R = new long[this.NWORDS64_FIELD];
                this.ph3_T1 = new long[this.DLEN_3 * (this.ELL3_W >>> 1) * 2 * this.NWORDS64_FIELD];
                this.ph3_T2 = new long[this.DLEN_3 * (this.ELL3_W >>> 1) * 2 * this.NWORDS64_FIELD];
                this.ph2_T1 = new long[(((this.DLEN_2 - 1) * (this.ELL2_W / 2)) + (this.ph2_path[this.PLEN_2 - 1] - 1)) * 2];
                this.ph2_T2 = new long[(((this.DLEN_2 - 1) * (this.ELL2_W / 2)) + (this.ph2_path[this.PLEN_2 - 1] - 1)) * 2];
            } catch (IOException e) {
                throw new IllegalStateException("unable to load Picnic properties: " + e.getMessage(), e);
            }
        }
    }
}
