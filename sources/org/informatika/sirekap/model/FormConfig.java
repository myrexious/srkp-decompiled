package org.informatika.sirekap.model;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.ThumbnailType;

/* compiled from: FormConfig.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0003'()B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J9\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010#\u001a\u00020\u0005¢\u0006\u0002\u0010$J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006*"}, d2 = {"Lorg/informatika/sirekap/model/FormConfig;", "", "name", "", "checkerboardSize", "", ThumbnailType.WIDTH, ThumbnailType.HEIGHT, "regionOfInterest", "", "Lorg/informatika/sirekap/model/FormConfig$ROI;", "(Ljava/lang/String;IIILjava/util/List;)V", "getCheckerboardSize", "()I", "getHeight", "getName", "()Ljava/lang/String;", "getRegionOfInterest", "()Ljava/util/List;", "getWidth", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "getKesesuaianSlicedMat", "Landroid/graphics/Bitmap;", "imageType", "row", "originalBitmap", FirebaseAnalytics.Param.INDEX, "candidateNum", "(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;Ljava/lang/Integer;I)Landroid/graphics/Bitmap;", "hashCode", "toString", "Companion", "Field", "ROI", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormConfig {
    private static final String ADMIN_PAGE1_OCR3 = "x_standar_ocr-tiga-kotak.json";
    private static final String ADMIN_PAGE1_OCR5_LN = "x_ln_ocr-lima-kotak.json";
    private static final String ADMIN_PAGE1_PDPR_POS = "x_dpr_pos_ocr-lima-kotak.json";
    private static final String ADMIN_PAGE1_PPWP_POS = "x_ppwp_pos_ocr-lima-kotak.json";
    private static final String ADMIN_PAGE2_OCR3 = "y_ocr-tiga-kotak.json";
    private static final String ADMIN_PAGE2_OCR5 = "y_ocr-lima-kotak.json";
    public static final Companion Companion = new Companion(null);
    public static final String FORM_ADMIN1 = "administrasi-1";
    public static final String FORM_ADMIN2 = "administrasi-2";
    public static final String FORM_TALLY = "tabulasi";
    public static final String TAG = "KesesuaianSliceImage";
    private static final String TALLY_PAGE_DPD_10 = "n_dpd_10.json";
    private static final String TALLY_PAGE_DPD_12 = "n_dpd_12.json";
    private static final String TALLY_PAGE_DPD_8 = "n_dpd_8.json";
    private static final String TALLY_PAGE_DPRD_12 = "n_dprd_partai_suara_12.json";
    private static final String TALLY_PAGE_DPRD_14 = "n_dprd_partai_suara_14.json";
    private static final String TALLY_PAGE_DPR_OCR5_08 = "n_dpr_partai_suara_8_ocr-lima-kotak.json";
    private static final String TALLY_PAGE_PARTAI_10 = "n_partai_suara_10_ocr-tiga-kotak.json";
    private static final String TALLY_PAGE_PPWP_OMR3 = "n_ppwp_omr-tiga-kotak.json";
    private static final String TALLY_PAGE_PPWP_OMR5 = "n_ppwp_omr-lima-kotak.json";
    public static final String VISION_TYPE_ADMIN = "admin1";
    public static final String VISION_TYPE_ADMIN2 = "admin2";
    public static final String VISION_TYPE_TALLY = "tally";
    @SerializedName("checkerboard_size")
    private final int checkerboardSize;
    private final int height;
    private final String name;
    @SerializedName("regions_of_interest")
    private final List<ROI> regionOfInterest;
    private final int width;

    public static /* synthetic */ FormConfig copy$default(FormConfig formConfig, String str, int i, int i2, int i3, List list, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = formConfig.name;
        }
        if ((i4 & 2) != 0) {
            i = formConfig.checkerboardSize;
        }
        int i5 = i;
        if ((i4 & 4) != 0) {
            i2 = formConfig.width;
        }
        int i6 = i2;
        if ((i4 & 8) != 0) {
            i3 = formConfig.height;
        }
        int i7 = i3;
        List<ROI> list2 = list;
        if ((i4 & 16) != 0) {
            list2 = formConfig.regionOfInterest;
        }
        return formConfig.copy(str, i5, i6, i7, list2);
    }

    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.checkerboardSize;
    }

    public final int component3() {
        return this.width;
    }

    public final int component4() {
        return this.height;
    }

    public final List<ROI> component5() {
        return this.regionOfInterest;
    }

    public final FormConfig copy(String name, int i, int i2, int i3, List<ROI> regionOfInterest) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(regionOfInterest, "regionOfInterest");
        return new FormConfig(name, i, i2, i3, regionOfInterest);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormConfig) {
            FormConfig formConfig = (FormConfig) obj;
            return Intrinsics.areEqual(this.name, formConfig.name) && this.checkerboardSize == formConfig.checkerboardSize && this.width == formConfig.width && this.height == formConfig.height && Intrinsics.areEqual(this.regionOfInterest, formConfig.regionOfInterest);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + Integer.hashCode(this.checkerboardSize)) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height)) * 31) + this.regionOfInterest.hashCode();
    }

    public String toString() {
        String str = this.name;
        int i = this.checkerboardSize;
        int i2 = this.width;
        int i3 = this.height;
        return "FormConfig(name=" + str + ", checkerboardSize=" + i + ", width=" + i2 + ", height=" + i3 + ", regionOfInterest=" + this.regionOfInterest + ")";
    }

    public FormConfig(String name, int i, int i2, int i3, List<ROI> regionOfInterest) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(regionOfInterest, "regionOfInterest");
        this.name = name;
        this.checkerboardSize = i;
        this.width = i2;
        this.height = i3;
        this.regionOfInterest = regionOfInterest;
    }

    public final String getName() {
        return this.name;
    }

    public final int getCheckerboardSize() {
        return this.checkerboardSize;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final List<ROI> getRegionOfInterest() {
        return this.regionOfInterest;
    }

    /* compiled from: FormConfig.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0012JH\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0005HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006#"}, d2 = {"Lorg/informatika/sirekap/model/FormConfig$ROI;", "", "type", "", ThumbnailType.WIDTH, "", ThumbnailType.HEIGHT, "fields", "", "Lorg/informatika/sirekap/model/FormConfig$Field;", "omrBoundMultiplier", "", "(Ljava/lang/String;IILjava/util/List;Ljava/lang/Double;)V", "getFields", "()Ljava/util/List;", "getHeight", "()I", "getOmrBoundMultiplier", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getType", "()Ljava/lang/String;", "getWidth", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;IILjava/util/List;Ljava/lang/Double;)Lorg/informatika/sirekap/model/FormConfig$ROI;", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class ROI {
        private final List<Field> fields;
        private final int height;
        @SerializedName("omr_bound_multiplier")
        private final Double omrBoundMultiplier;
        private final String type;
        private final int width;

        public static /* synthetic */ ROI copy$default(ROI roi, String str, int i, int i2, List list, Double d, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = roi.type;
            }
            if ((i3 & 2) != 0) {
                i = roi.width;
            }
            int i4 = i;
            if ((i3 & 4) != 0) {
                i2 = roi.height;
            }
            int i5 = i2;
            List<Field> list2 = list;
            if ((i3 & 8) != 0) {
                list2 = roi.fields;
            }
            List list3 = list2;
            if ((i3 & 16) != 0) {
                d = roi.omrBoundMultiplier;
            }
            return roi.copy(str, i4, i5, list3, d);
        }

        public final String component1() {
            return this.type;
        }

        public final int component2() {
            return this.width;
        }

        public final int component3() {
            return this.height;
        }

        public final List<Field> component4() {
            return this.fields;
        }

        public final Double component5() {
            return this.omrBoundMultiplier;
        }

        public final ROI copy(String type, int i, int i2, List<Field> fields, Double d) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(fields, "fields");
            return new ROI(type, i, i2, fields, d);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ROI) {
                ROI roi = (ROI) obj;
                return Intrinsics.areEqual(this.type, roi.type) && this.width == roi.width && this.height == roi.height && Intrinsics.areEqual(this.fields, roi.fields) && Intrinsics.areEqual((Object) this.omrBoundMultiplier, (Object) roi.omrBoundMultiplier);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = ((((((this.type.hashCode() * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height)) * 31) + this.fields.hashCode()) * 31;
            Double d = this.omrBoundMultiplier;
            return hashCode + (d == null ? 0 : d.hashCode());
        }

        public String toString() {
            String str = this.type;
            int i = this.width;
            int i2 = this.height;
            List<Field> list = this.fields;
            return "ROI(type=" + str + ", width=" + i + ", height=" + i2 + ", fields=" + list + ", omrBoundMultiplier=" + this.omrBoundMultiplier + ")";
        }

        public ROI(String type, int i, int i2, List<Field> fields, Double d) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(fields, "fields");
            this.type = type;
            this.width = i;
            this.height = i2;
            this.fields = fields;
            this.omrBoundMultiplier = d;
        }

        public /* synthetic */ ROI(String str, int i, int i2, List list, Double d, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, i2, list, (i3 & 16) != 0 ? null : d);
        }

        public final String getType() {
            return this.type;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final List<Field> getFields() {
            return this.fields;
        }

        public final Double getOmrBoundMultiplier() {
            return this.omrBoundMultiplier;
        }
    }

    /* compiled from: FormConfig.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0005HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/model/FormConfig$Field;", "", "name", "", "coordinates", "", "", "(Ljava/lang/String;Ljava/util/List;)V", "getCoordinates", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Field {
        private final List<List<Integer>> coordinates;
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Field copy$default(Field field, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = field.name;
            }
            if ((i & 2) != 0) {
                list = field.coordinates;
            }
            return field.copy(str, list);
        }

        public final String component1() {
            return this.name;
        }

        public final List<List<Integer>> component2() {
            return this.coordinates;
        }

        public final Field copy(String name, List<? extends List<Integer>> coordinates) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(coordinates, "coordinates");
            return new Field(name, coordinates);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Field) {
                Field field = (Field) obj;
                return Intrinsics.areEqual(this.name, field.name) && Intrinsics.areEqual(this.coordinates, field.coordinates);
            }
            return false;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.coordinates.hashCode();
        }

        public String toString() {
            String str = this.name;
            return "Field(name=" + str + ", coordinates=" + this.coordinates + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Field(String name, List<? extends List<Integer>> coordinates) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(coordinates, "coordinates");
            this.name = name;
            this.coordinates = coordinates;
        }

        public final String getName() {
            return this.name;
        }

        public final List<List<Integer>> getCoordinates() {
            return this.coordinates;
        }
    }

    public static /* synthetic */ Bitmap getKesesuaianSlicedMat$default(FormConfig formConfig, String str, String str2, Bitmap bitmap, Integer num, int i, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            i = 0;
        }
        return formConfig.getKesesuaianSlicedMat(str, str2, bitmap, num, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:195:0x01eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap getKesesuaianSlicedMat(java.lang.String r17, java.lang.String r18, android.graphics.Bitmap r19, java.lang.Integer r20, int r21) {
        /*
            Method dump skipped, instructions count: 2022
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.model.FormConfig.getKesesuaianSlicedMat(java.lang.String, java.lang.String, android.graphics.Bitmap, java.lang.Integer, int):android.graphics.Bitmap");
    }

    /* compiled from: FormConfig.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J8\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/informatika/sirekap/model/FormConfig$Companion;", "", "()V", "ADMIN_PAGE1_OCR3", "", "ADMIN_PAGE1_OCR5_LN", "ADMIN_PAGE1_PDPR_POS", "ADMIN_PAGE1_PPWP_POS", "ADMIN_PAGE2_OCR3", "ADMIN_PAGE2_OCR5", "FORM_ADMIN1", "FORM_ADMIN2", "FORM_TALLY", "TAG", "TALLY_PAGE_DPD_10", "TALLY_PAGE_DPD_12", "TALLY_PAGE_DPD_8", "TALLY_PAGE_DPRD_12", "TALLY_PAGE_DPRD_14", "TALLY_PAGE_DPR_OCR5_08", "TALLY_PAGE_PARTAI_10", "TALLY_PAGE_PPWP_OMR3", "TALLY_PAGE_PPWP_OMR5", "VISION_TYPE_ADMIN", "VISION_TYPE_ADMIN2", "VISION_TYPE_TALLY", "fromJsonFile", "Lorg/informatika/sirekap/model/FormConfig;", "context", "Landroid/content/Context;", "pageConfig", "getConfig", "formType", "electionType", "isLN", "", "isPOS", "candidateNum", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ FormConfig getConfig$default(Companion companion, Context context, String str, String str2, boolean z, boolean z2, int i, int i2, Object obj) {
            if ((i2 & 32) != 0) {
                i = 0;
            }
            return companion.getConfig(context, str, str2, z, z2, i);
        }

        /* JADX WARN: Removed duplicated region for block: B:129:0x00ac  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final org.informatika.sirekap.model.FormConfig getConfig(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13, int r14) {
            /*
                Method dump skipped, instructions count: 256
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.model.FormConfig.Companion.getConfig(android.content.Context, java.lang.String, java.lang.String, boolean, boolean, int):org.informatika.sirekap.model.FormConfig");
        }

        private final FormConfig fromJsonFile(Context context, String str) {
            Gson gson = new Gson();
            InputStream open = context.getAssets().open("formconfig/" + str);
            Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(fileName)");
            Object fromJson = gson.fromJson((Reader) new InputStreamReader(open), (Class<Object>) FormConfig.class);
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(reader, FormConfig::class.java)");
            return (FormConfig) fromJson;
        }
    }
}
