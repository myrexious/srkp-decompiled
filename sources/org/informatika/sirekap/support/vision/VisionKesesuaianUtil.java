package org.informatika.sirekap.support.vision;

import android.graphics.Bitmap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.videoio.Videoio;
import org.tensorflow.lite.schema.BuiltinOperator;

/* compiled from: VisionKesesuaianUtil.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JE\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/support/vision/VisionKesesuaianUtil;", "", "()V", VisionKesesuaianUtil.VISION_KESESUAIAN_TYPE_CROPPED, "", "administrationSliceCoordinatesCROPPED", "", "", "", "administrationSliceCoordinatesMARKED", "tabulationSliceCoordinates10CandidatesCROPPED", "tabulationSliceCoordinates10CandidatesRealCROPPED", "tabulationSliceCoordinates5CandidatesCROPPED", "tabulationSliceCoordinates5CandidatesRealCROPPED", "getKesesuaianSlicedMat", "Landroid/graphics/Bitmap;", "imageType", "row", "originalBitmap", "imageKesesuaianType", "candidateNum", FirebaseAnalytics.Param.INDEX, "(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;Ljava/lang/String;ILjava/lang/Integer;)Landroid/graphics/Bitmap;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VisionKesesuaianUtil {
    public static final String VISION_KESESUAIAN_TYPE_CROPPED = "VISION_KESESUAIAN_TYPE_CROPPED";
    private static final Map<String, List<Integer>> tabulationSliceCoordinates10CandidatesCROPPED;
    private static final List<List<Integer>> tabulationSliceCoordinates10CandidatesRealCROPPED;
    private static final Map<String, List<Integer>> tabulationSliceCoordinates5CandidatesCROPPED;
    private static final List<List<Integer>> tabulationSliceCoordinates5CandidatesRealCROPPED;
    public static final VisionKesesuaianUtil INSTANCE = new VisionKesesuaianUtil();
    private static final Map<String, List<Integer>> administrationSliceCoordinatesMARKED = MapsKt.mapOf(TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT, CollectionsKt.listOf((Object[]) new Integer[]{180, 265})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DPPH, CollectionsKt.listOf((Object[]) new Integer[]{240, 325})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DPTB, CollectionsKt.listOf((Object[]) new Integer[]{300, 385})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_TOTAL, CollectionsKt.listOf((Object[]) new Integer[]{360, 445})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT, CollectionsKt.listOf((Object[]) new Integer[]{485, 570})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPPH, CollectionsKt.listOf((Object[]) new Integer[]{545, 630})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB, CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf((int) TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO), 690})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL, CollectionsKt.listOf((Object[]) new Integer[]{665, 750})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS, CollectionsKt.listOf((Object[]) new Integer[]{875, 960})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DISABILITAS, CollectionsKt.listOf((Object[]) new Integer[]{935, 1020})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA, CollectionsKt.listOf((Object[]) new Integer[]{1145, 1230})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN, CollectionsKt.listOf((Object[]) new Integer[]{1205, 1290})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN, CollectionsKt.listOf((Object[]) new Integer[]{1265, 1350})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN, CollectionsKt.listOf((Object[]) new Integer[]{1325, Integer.valueOf((int) Videoio.CAP_WINRT)})));
    private static final Map<String, List<Integer>> administrationSliceCoordinatesCROPPED = MapsKt.mapOf(TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT, CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf((int) BuiltinOperator.BROADCAST_ARGS), 225})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DPPH, CollectionsKt.listOf((Object[]) new Integer[]{205, 285})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DPTB, CollectionsKt.listOf((Object[]) new Integer[]{265, 345})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_TOTAL, CollectionsKt.listOf((Object[]) new Integer[]{325, 405})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT, CollectionsKt.listOf((Object[]) new Integer[]{450, 530})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPPH, CollectionsKt.listOf((Object[]) new Integer[]{510, 590})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB, CollectionsKt.listOf((Object[]) new Integer[]{570, 650})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL, CollectionsKt.listOf((Object[]) new Integer[]{630, 710})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS, CollectionsKt.listOf((Object[]) new Integer[]{840, 920})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_PENGGUNA_DISABILITAS, CollectionsKt.listOf((Object[]) new Integer[]{900, 980})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA, CollectionsKt.listOf((Object[]) new Integer[]{1110, 1190})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN, CollectionsKt.listOf((Object[]) new Integer[]{1170, 1250})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN, CollectionsKt.listOf((Object[]) new Integer[]{1230, 1310})), TuplesKt.to(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN, CollectionsKt.listOf((Object[]) new Integer[]{1290, 1370})));

    private VisionKesesuaianUtil() {
    }

    static {
        List<List<Integer>> listOf = CollectionsKt.listOf((Object[]) new List[]{CollectionsKt.listOf((Object[]) new Integer[]{10, 445}), CollectionsKt.listOf((Object[]) new Integer[]{435, 925})});
        tabulationSliceCoordinates5CandidatesRealCROPPED = listOf;
        tabulationSliceCoordinates5CandidatesCROPPED = MapsKt.mapOf(TuplesKt.to("FORM_C1_ROW_CANDIDATE_", listOf.get(0)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH, listOf.get(1)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH, listOf.get(1)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA, listOf.get(1)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_PEMILIH, listOf.get(1)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_DIGUNAKAN, listOf.get(1)));
        List<List<Integer>> listOf2 = CollectionsKt.listOf((Object[]) new List[]{CollectionsKt.listOf((Object[]) new Integer[]{10, 415}), CollectionsKt.listOf((Object[]) new Integer[]{395, 800}), CollectionsKt.listOf((Object[]) new Integer[]{790, 1260})});
        tabulationSliceCoordinates10CandidatesRealCROPPED = listOf2;
        tabulationSliceCoordinates10CandidatesCROPPED = MapsKt.mapOf(TuplesKt.to("FORM_C1_ROW_CANDIDATE_-index0", listOf2.get(0)), TuplesKt.to("FORM_C1_ROW_CANDIDATE_-index1", listOf2.get(1)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH, listOf2.get(2)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH, listOf2.get(2)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA, listOf2.get(2)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_PEMILIH, listOf2.get(2)), TuplesKt.to(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_DIGUNAKAN, listOf2.get(2)));
    }

    public static /* synthetic */ Bitmap getKesesuaianSlicedMat$default(VisionKesesuaianUtil visionKesesuaianUtil, String str, String str2, Bitmap bitmap, String str3, int i, Integer num, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str3 = VISION_KESESUAIAN_TYPE_CROPPED;
        }
        String str4 = str3;
        int i3 = (i2 & 16) != 0 ? 0 : i;
        if ((i2 & 32) != 0) {
            num = 0;
        }
        return visionKesesuaianUtil.getKesesuaianSlicedMat(str, str2, bitmap, str4, i3, num);
    }

    public final Bitmap getKesesuaianSlicedMat(String imageType, String row, Bitmap originalBitmap, String imageKesesuaianType, int i, Integer num) {
        Map<String, List<Integer>> map;
        List<Integer> list;
        Intrinsics.checkNotNullParameter(imageType, "imageType");
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(originalBitmap, "originalBitmap");
        Intrinsics.checkNotNullParameter(imageKesesuaianType, "imageKesesuaianType");
        if (Intrinsics.areEqual(imageType, VisionUtil.VISION_UTIL_TYPE_ADMIN)) {
            if (Intrinsics.areEqual(imageKesesuaianType, VISION_KESESUAIAN_TYPE_CROPPED)) {
                map = administrationSliceCoordinatesCROPPED;
            } else {
                map = administrationSliceCoordinatesMARKED;
            }
        } else if (!Intrinsics.areEqual(imageType, VisionUtil.VISION_UTIL_TYPE_TABULASI)) {
            map = null;
        } else if (i <= 5) {
            map = tabulationSliceCoordinates5CandidatesCROPPED;
        } else {
            map = tabulationSliceCoordinates10CandidatesCROPPED;
        }
        if (map != null) {
            OpenCVLoader.initDebug();
            Mat mat = new Mat();
            Utils.bitmapToMat(originalBitmap, mat);
            if (Intrinsics.areEqual(imageType, VisionUtil.VISION_UTIL_TYPE_ADMIN)) {
                list = map.get(row);
            } else if (num == null) {
                list = map.get(row);
            } else if (i > 5) {
                list = map.get(row + "-index" + (num.intValue() < 5 ? 0 : 1));
            } else {
                list = map.get(row);
            }
            List<Integer> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                if (list != null && list.size() == 2) {
                    return null;
                }
            }
            if (list != null) {
                Mat submat = mat.submat(list.get(0).intValue(), list.get(1).intValue(), 0, mat.cols());
                Bitmap createBitmap = Bitmap.createBitmap(submat.cols(), submat.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(submat, createBitmap);
                return createBitmap;
            }
            return null;
        }
        return null;
    }
}
