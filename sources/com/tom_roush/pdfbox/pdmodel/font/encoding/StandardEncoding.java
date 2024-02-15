package com.tom_roush.pdfbox.pdmodel.font.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.tensorflow.lite.schema.BuiltinOperator;

/* loaded from: classes3.dex */
public class StandardEncoding extends Encoding {
    private static final Object[][] STANDARD_ENCODING_TABLE = {new Object[]{65, "A"}, new Object[]{225, "AE"}, new Object[]{66, "B"}, new Object[]{67, "C"}, new Object[]{68, "D"}, new Object[]{69, "E"}, new Object[]{70, "F"}, new Object[]{71, OperatorName.STROKING_COLOR_GRAY}, new Object[]{72, StandardStructureTypes.H}, new Object[]{73, "I"}, new Object[]{74, OperatorName.SET_LINE_CAPSTYLE}, new Object[]{75, "K"}, new Object[]{76, "L"}, new Object[]{232, "Lslash"}, new Object[]{77, "M"}, new Object[]{78, "N"}, new Object[]{79, PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE}, new Object[]{234, "OE"}, new Object[]{233, "Oslash"}, new Object[]{80, "P"}, new Object[]{81, OperatorName.RESTORE}, new Object[]{82, "R"}, new Object[]{83, "S"}, new Object[]{84, "T"}, new Object[]{85, "U"}, new Object[]{86, "V"}, new Object[]{87, "W"}, new Object[]{88, "X"}, new Object[]{89, "Y"}, new Object[]{90, "Z"}, new Object[]{97, PDPageLabelRange.STYLE_LETTERS_LOWER}, new Object[]{194, "acute"}, new Object[]{241, "ae"}, new Object[]{38, "ampersand"}, new Object[]{94, "asciicircum"}, new Object[]{126, "asciitilde"}, new Object[]{42, "asterisk"}, new Object[]{64, "at"}, new Object[]{98, OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE}, new Object[]{92, "backslash"}, new Object[]{124, "bar"}, new Object[]{123, "braceleft"}, new Object[]{125, "braceright"}, new Object[]{91, "bracketleft"}, new Object[]{93, "bracketright"}, new Object[]{198, "breve"}, new Object[]{183, "bullet"}, new Object[]{99, OperatorName.CURVE_TO}, new Object[]{207, "caron"}, new Object[]{203, "cedilla"}, new Object[]{162, "cent"}, new Object[]{195, "circumflex"}, new Object[]{58, "colon"}, new Object[]{44, "comma"}, new Object[]{168, FirebaseAnalytics.Param.CURRENCY}, new Object[]{100, OperatorName.SET_LINE_DASHPATTERN}, new Object[]{178, "dagger"}, new Object[]{179, "daggerdbl"}, new Object[]{200, "dieresis"}, new Object[]{36, "dollar"}, new Object[]{199, "dotaccent"}, new Object[]{245, "dotlessi"}, new Object[]{101, "e"}, new Object[]{56, "eight"}, new Object[]{188, "ellipsis"}, new Object[]{208, "emdash"}, new Object[]{177, "endash"}, new Object[]{61, "equal"}, new Object[]{33, "exclam"}, new Object[]{161, "exclamdown"}, new Object[]{102, OperatorName.FILL_NON_ZERO}, new Object[]{174, "fi"}, new Object[]{53, "five"}, new Object[]{175, "fl"}, new Object[]{166, "florin"}, new Object[]{52, "four"}, new Object[]{164, "fraction"}, new Object[]{103, OperatorName.NON_STROKING_GRAY}, new Object[]{251, "germandbls"}, new Object[]{193, "grave"}, new Object[]{62, "greater"}, new Object[]{171, "guillemotleft"}, new Object[]{187, "guillemotright"}, new Object[]{172, "guilsinglleft"}, new Object[]{173, "guilsinglright"}, new Object[]{104, "h"}, new Object[]{205, "hungarumlaut"}, new Object[]{45, "hyphen"}, new Object[]{105, OperatorName.SET_FLATNESS}, new Object[]{106, OperatorName.SET_LINE_JOINSTYLE}, new Object[]{107, OperatorName.NON_STROKING_CMYK}, new Object[]{108, OperatorName.LINE_TO}, new Object[]{60, "less"}, new Object[]{248, "lslash"}, new Object[]{109, OperatorName.MOVE_TO}, new Object[]{197, "macron"}, new Object[]{110, OperatorName.ENDPATH}, new Object[]{57, "nine"}, new Object[]{35, "numbersign"}, new Object[]{111, "o"}, new Object[]{Integer.valueOf((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "oe"}, new Object[]{206, "ogonek"}, new Object[]{49, "one"}, new Object[]{227, "ordfeminine"}, new Object[]{235, "ordmasculine"}, new Object[]{249, "oslash"}, new Object[]{112, "p"}, new Object[]{182, "paragraph"}, new Object[]{40, "parenleft"}, new Object[]{41, "parenright"}, new Object[]{37, "percent"}, new Object[]{46, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{180, "periodcentered"}, new Object[]{189, "perthousand"}, new Object[]{43, "plus"}, new Object[]{Integer.valueOf((int) BuiltinOperator.MATRIX_DIAG), OperatorName.SAVE}, new Object[]{63, "question"}, new Object[]{191, "questiondown"}, new Object[]{34, "quotedbl"}, new Object[]{185, "quotedblbase"}, new Object[]{170, "quotedblleft"}, new Object[]{186, "quotedblright"}, new Object[]{96, "quoteleft"}, new Object[]{39, "quoteright"}, new Object[]{184, "quotesinglbase"}, new Object[]{169, "quotesingle"}, new Object[]{114, PDPageLabelRange.STYLE_ROMAN_LOWER}, new Object[]{202, "ring"}, new Object[]{115, OperatorName.CLOSE_AND_STROKE}, new Object[]{167, "section"}, new Object[]{59, "semicolon"}, new Object[]{55, "seven"}, new Object[]{54, "six"}, new Object[]{47, "slash"}, new Object[]{32, "space"}, new Object[]{163, "sterling"}, new Object[]{116, "t"}, new Object[]{51, "three"}, new Object[]{196, "tilde"}, new Object[]{50, "two"}, new Object[]{117, "u"}, new Object[]{95, "underscore"}, new Object[]{118, OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT}, new Object[]{119, "w"}, new Object[]{120, "x"}, new Object[]{121, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT}, new Object[]{165, "yen"}, new Object[]{122, "z"}, new Object[]{48, "zero"}};
    public static final StandardEncoding INSTANCE = new StandardEncoding();

    @Override // com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding
    public String getEncodingName() {
        return "StandardEncoding";
    }

    public StandardEncoding() {
        Object[][] objArr;
        for (Object[] objArr2 : STANDARD_ENCODING_TABLE) {
            add(((Integer) objArr2[0]).intValue(), objArr2[1].toString());
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return COSName.STANDARD_ENCODING;
    }
}
