package org.apache.commons.codec.language;

import androidx.exifinterface.media.ExifInterface;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.util.Locale;

/* loaded from: classes2.dex */
public class Caverphone1 extends AbstractCaverphone {
    private static final String SIX_1 = "111111";

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null || str.length() == 0) {
            return SIX_1;
        }
        return (str.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z]", "").replaceAll("^cough", "cou2f").replaceAll("^rough", "rou2f").replaceAll("^tough", "tou2f").replaceAll("^enough", "enou2f").replaceAll("^gn", "2n").replaceAll("mb$", "m2").replaceAll("cq", "2q").replaceAll("ci", "si").replaceAll("ce", "se").replaceAll("cy", "sy").replaceAll("tch", "2ch").replaceAll(OperatorName.CURVE_TO, OperatorName.NON_STROKING_CMYK).replaceAll(OperatorName.SAVE, OperatorName.NON_STROKING_CMYK).replaceAll("x", OperatorName.NON_STROKING_CMYK).replaceAll(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, OperatorName.FILL_NON_ZERO).replaceAll("dg", "2g").replaceAll("tio", "sio").replaceAll("tia", "sia").replaceAll(OperatorName.SET_LINE_DASHPATTERN, "t").replaceAll("ph", "fh").replaceAll(OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "p").replaceAll(OperatorName.SHADING_FILL, "s2").replaceAll("z", OperatorName.CLOSE_AND_STROKE).replaceAll("^[aeiou]", "A").replaceAll("[aeiou]", ExifInterface.GPS_MEASUREMENT_3D).replaceAll("3gh3", "3kh3").replaceAll("gh", "22").replaceAll(OperatorName.NON_STROKING_GRAY, OperatorName.NON_STROKING_CMYK).replaceAll("s+", "S").replaceAll("t+", "T").replaceAll("p+", "P").replaceAll("k+", "K").replaceAll("f+", "F").replaceAll("m+", "M").replaceAll("n+", "N").replaceAll("w3", "W3").replaceAll("wy", "Wy").replaceAll("wh3", "Wh3").replaceAll("why", "Why").replaceAll("w", ExifInterface.GPS_MEASUREMENT_2D).replaceAll("^h", "A").replaceAll("h", ExifInterface.GPS_MEASUREMENT_2D).replaceAll("r3", "R3").replaceAll("ry", "Ry").replaceAll(PDPageLabelRange.STYLE_ROMAN_LOWER, ExifInterface.GPS_MEASUREMENT_2D).replaceAll("l3", "L3").replaceAll("ly", "Ly").replaceAll(OperatorName.LINE_TO, ExifInterface.GPS_MEASUREMENT_2D).replaceAll(OperatorName.SET_LINE_JOINSTYLE, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT).replaceAll("y3", "Y3").replaceAll(OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, ExifInterface.GPS_MEASUREMENT_2D).replaceAll(ExifInterface.GPS_MEASUREMENT_2D, "").replaceAll(ExifInterface.GPS_MEASUREMENT_3D, "") + SIX_1).substring(0, 6);
    }
}
