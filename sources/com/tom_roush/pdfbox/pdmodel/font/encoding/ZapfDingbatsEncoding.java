package com.tom_roush.pdfbox.pdmodel.font.encoding;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import org.bouncycastle.math.Primes;
import org.tensorflow.lite.schema.BuiltinOperator;

/* loaded from: classes3.dex */
public class ZapfDingbatsEncoding extends Encoding {
    private static final Object[][] ZAPFDINGBATS_ENCODING_TABLE = {new Object[]{32, "space"}, new Object[]{33, "a1"}, new Object[]{34, "a2"}, new Object[]{35, "a202"}, new Object[]{36, "a3"}, new Object[]{37, "a4"}, new Object[]{38, "a5"}, new Object[]{39, "a119"}, new Object[]{40, "a118"}, new Object[]{41, "a117"}, new Object[]{42, "a11"}, new Object[]{43, "a12"}, new Object[]{44, "a13"}, new Object[]{45, "a14"}, new Object[]{46, "a15"}, new Object[]{47, "a16"}, new Object[]{48, "a105"}, new Object[]{49, "a17"}, new Object[]{50, "a18"}, new Object[]{51, "a19"}, new Object[]{52, "a20"}, new Object[]{53, "a21"}, new Object[]{54, "a22"}, new Object[]{55, "a23"}, new Object[]{56, "a24"}, new Object[]{57, "a25"}, new Object[]{58, "a26"}, new Object[]{59, "a27"}, new Object[]{60, "a28"}, new Object[]{61, "a6"}, new Object[]{62, "a7"}, new Object[]{63, "a8"}, new Object[]{64, "a9"}, new Object[]{65, "a10"}, new Object[]{66, "a29"}, new Object[]{67, "a30"}, new Object[]{68, "a31"}, new Object[]{69, "a32"}, new Object[]{70, "a33"}, new Object[]{71, "a34"}, new Object[]{72, "a35"}, new Object[]{73, "a36"}, new Object[]{74, "a37"}, new Object[]{75, "a38"}, new Object[]{76, "a39"}, new Object[]{77, "a40"}, new Object[]{78, "a41"}, new Object[]{79, "a42"}, new Object[]{80, "a43"}, new Object[]{81, "a44"}, new Object[]{82, "a45"}, new Object[]{83, "a46"}, new Object[]{84, "a47"}, new Object[]{85, "a48"}, new Object[]{86, "a49"}, new Object[]{87, "a50"}, new Object[]{88, "a51"}, new Object[]{89, "a52"}, new Object[]{90, "a53"}, new Object[]{91, "a54"}, new Object[]{92, "a55"}, new Object[]{93, "a56"}, new Object[]{94, "a57"}, new Object[]{95, "a58"}, new Object[]{96, "a59"}, new Object[]{97, "a60"}, new Object[]{98, "a61"}, new Object[]{99, "a62"}, new Object[]{100, "a63"}, new Object[]{101, "a64"}, new Object[]{102, "a65"}, new Object[]{103, "a66"}, new Object[]{104, "a67"}, new Object[]{105, "a68"}, new Object[]{106, "a69"}, new Object[]{107, "a70"}, new Object[]{108, "a71"}, new Object[]{109, "a72"}, new Object[]{110, "a73"}, new Object[]{111, "a74"}, new Object[]{112, "a203"}, new Object[]{Integer.valueOf((int) BuiltinOperator.MATRIX_DIAG), "a75"}, new Object[]{114, "a204"}, new Object[]{115, "a76"}, new Object[]{116, "a77"}, new Object[]{117, "a78"}, new Object[]{118, "a79"}, new Object[]{119, "a81"}, new Object[]{120, "a82"}, new Object[]{121, "a83"}, new Object[]{122, "a84"}, new Object[]{123, "a97"}, new Object[]{124, "a98"}, new Object[]{125, "a99"}, new Object[]{126, "a100"}, new Object[]{161, "a101"}, new Object[]{162, "a102"}, new Object[]{163, "a103"}, new Object[]{164, "a104"}, new Object[]{165, "a106"}, new Object[]{166, "a107"}, new Object[]{167, "a108"}, new Object[]{168, "a112"}, new Object[]{169, "a111"}, new Object[]{170, "a110"}, new Object[]{171, "a109"}, new Object[]{172, "a120"}, new Object[]{173, "a121"}, new Object[]{174, "a122"}, new Object[]{175, "a123"}, new Object[]{176, "a124"}, new Object[]{177, "a125"}, new Object[]{178, "a126"}, new Object[]{179, "a127"}, new Object[]{180, "a128"}, new Object[]{181, "a129"}, new Object[]{182, "a130"}, new Object[]{183, "a131"}, new Object[]{184, "a132"}, new Object[]{185, "a133"}, new Object[]{186, "a134"}, new Object[]{187, "a135"}, new Object[]{188, "a136"}, new Object[]{189, "a137"}, new Object[]{190, "a138"}, new Object[]{191, "a139"}, new Object[]{192, "a140"}, new Object[]{193, "a141"}, new Object[]{194, "a142"}, new Object[]{195, "a143"}, new Object[]{196, "a144"}, new Object[]{197, "a145"}, new Object[]{198, "a146"}, new Object[]{199, "a147"}, new Object[]{200, "a148"}, new Object[]{201, "a149"}, new Object[]{202, "a150"}, new Object[]{203, "a151"}, new Object[]{204, "a152"}, new Object[]{205, "a153"}, new Object[]{206, "a154"}, new Object[]{207, "a155"}, new Object[]{208, "a156"}, new Object[]{209, "a157"}, new Object[]{210, "a158"}, new Object[]{Integer.valueOf((int) Primes.SMALL_FACTOR_LIMIT), "a159"}, new Object[]{212, "a160"}, new Object[]{213, "a161"}, new Object[]{214, "a163"}, new Object[]{215, "a164"}, new Object[]{216, "a196"}, new Object[]{217, "a165"}, new Object[]{218, "a192"}, new Object[]{219, "a166"}, new Object[]{220, "a167"}, new Object[]{221, "a168"}, new Object[]{222, "a169"}, new Object[]{223, "a170"}, new Object[]{224, "a171"}, new Object[]{225, "a172"}, new Object[]{226, "a173"}, new Object[]{227, "a162"}, new Object[]{228, "a174"}, new Object[]{229, "a175"}, new Object[]{230, "a176"}, new Object[]{231, "a177"}, new Object[]{232, "a178"}, new Object[]{233, "a179"}, new Object[]{234, "a193"}, new Object[]{235, "a180"}, new Object[]{236, "a199"}, new Object[]{237, "a181"}, new Object[]{238, "a200"}, new Object[]{239, "a182"}, new Object[]{241, "a201"}, new Object[]{242, "a183"}, new Object[]{243, "a184"}, new Object[]{244, "a197"}, new Object[]{245, "a185"}, new Object[]{246, "a194"}, new Object[]{247, "a198"}, new Object[]{248, "a186"}, new Object[]{249, "a195"}, new Object[]{Integer.valueOf((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "a187"}, new Object[]{251, "a188"}, new Object[]{252, "a189"}, new Object[]{253, "a190"}, new Object[]{254, "a191"}};
    public static final ZapfDingbatsEncoding INSTANCE = new ZapfDingbatsEncoding();

    @Override // com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding
    public String getEncodingName() {
        return "ZapfDingbatsEncoding";
    }

    public ZapfDingbatsEncoding() {
        Object[][] objArr;
        for (Object[] objArr2 : ZAPFDINGBATS_ENCODING_TABLE) {
            add(((Integer) objArr2[0]).intValue(), objArr2[1].toString());
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return COSName.getPDFName("ZapfDingbatsEncoding");
    }
}
