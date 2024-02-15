package com.tom_roush.fontbox.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.bouncycastle.math.Primes;
import org.tensorflow.lite.schema.BuiltinOperator;

/* loaded from: classes3.dex */
public class MacRomanEncoding extends Encoding {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final Object[][] MAC_ROMAN_ENCODING_TABLE = {new Object[]{65, "A"}, new Object[]{174, "AE"}, new Object[]{231, "Aacute"}, new Object[]{229, "Acircumflex"}, new Object[]{128, "Adieresis"}, new Object[]{203, "Agrave"}, new Object[]{129, "Aring"}, new Object[]{204, "Atilde"}, new Object[]{66, "B"}, new Object[]{67, "C"}, new Object[]{130, "Ccedilla"}, new Object[]{68, "D"}, new Object[]{69, "E"}, new Object[]{131, "Eacute"}, new Object[]{230, "Ecircumflex"}, new Object[]{232, "Edieresis"}, new Object[]{233, "Egrave"}, new Object[]{70, "F"}, new Object[]{71, OperatorName.STROKING_COLOR_GRAY}, new Object[]{72, StandardStructureTypes.H}, new Object[]{73, "I"}, new Object[]{234, "Iacute"}, new Object[]{235, "Icircumflex"}, new Object[]{236, "Idieresis"}, new Object[]{237, "Igrave"}, new Object[]{74, OperatorName.SET_LINE_CAPSTYLE}, new Object[]{75, "K"}, new Object[]{76, "L"}, new Object[]{77, "M"}, new Object[]{78, "N"}, new Object[]{132, "Ntilde"}, new Object[]{79, PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE}, new Object[]{206, "OE"}, new Object[]{238, "Oacute"}, new Object[]{239, "Ocircumflex"}, new Object[]{133, "Odieresis"}, new Object[]{241, "Ograve"}, new Object[]{175, "Oslash"}, new Object[]{205, "Otilde"}, new Object[]{80, "P"}, new Object[]{81, OperatorName.RESTORE}, new Object[]{82, "R"}, new Object[]{83, "S"}, new Object[]{84, "T"}, new Object[]{85, "U"}, new Object[]{242, "Uacute"}, new Object[]{243, "Ucircumflex"}, new Object[]{134, "Udieresis"}, new Object[]{244, "Ugrave"}, new Object[]{86, "V"}, new Object[]{87, "W"}, new Object[]{88, "X"}, new Object[]{89, "Y"}, new Object[]{217, "Ydieresis"}, new Object[]{90, "Z"}, new Object[]{97, PDPageLabelRange.STYLE_LETTERS_LOWER}, new Object[]{135, "aacute"}, new Object[]{137, "acircumflex"}, new Object[]{171, "acute"}, new Object[]{138, "adieresis"}, new Object[]{190, "ae"}, new Object[]{136, "agrave"}, new Object[]{38, "ampersand"}, new Object[]{140, "aring"}, new Object[]{94, "asciicircum"}, new Object[]{126, "asciitilde"}, new Object[]{42, "asterisk"}, new Object[]{64, "at"}, new Object[]{139, "atilde"}, new Object[]{98, OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE}, new Object[]{92, "backslash"}, new Object[]{124, "bar"}, new Object[]{123, "braceleft"}, new Object[]{125, "braceright"}, new Object[]{91, "bracketleft"}, new Object[]{93, "bracketright"}, new Object[]{249, "breve"}, new Object[]{165, "bullet"}, new Object[]{99, OperatorName.CURVE_TO}, new Object[]{255, "caron"}, new Object[]{141, "ccedilla"}, new Object[]{252, "cedilla"}, new Object[]{162, "cent"}, new Object[]{246, "circumflex"}, new Object[]{58, "colon"}, new Object[]{44, "comma"}, new Object[]{169, "copyright"}, new Object[]{219, FirebaseAnalytics.Param.CURRENCY}, new Object[]{100, OperatorName.SET_LINE_DASHPATTERN}, new Object[]{160, "dagger"}, new Object[]{224, "daggerdbl"}, new Object[]{161, "degree"}, new Object[]{172, "dieresis"}, new Object[]{214, "divide"}, new Object[]{36, "dollar"}, new Object[]{Integer.valueOf((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), "dotaccent"}, new Object[]{245, "dotlessi"}, new Object[]{101, "e"}, new Object[]{142, "eacute"}, new Object[]{Integer.valueOf((int) BuiltinOperator.ASSIGN_VARIABLE), "ecircumflex"}, new Object[]{Integer.valueOf((int) BuiltinOperator.BROADCAST_ARGS), "edieresis"}, new Object[]{143, "egrave"}, new Object[]{56, "eight"}, new Object[]{201, "ellipsis"}, new Object[]{209, "emdash"}, new Object[]{208, "endash"}, new Object[]{61, "equal"}, new Object[]{33, "exclam"}, new Object[]{193, "exclamdown"}, new Object[]{102, OperatorName.FILL_NON_ZERO}, new Object[]{222, "fi"}, new Object[]{53, "five"}, new Object[]{223, "fl"}, new Object[]{196, "florin"}, new Object[]{52, "four"}, new Object[]{218, "fraction"}, new Object[]{103, OperatorName.NON_STROKING_GRAY}, new Object[]{167, "germandbls"}, new Object[]{96, "grave"}, new Object[]{62, "greater"}, new Object[]{199, "guillemotleft"}, new Object[]{200, "guillemotright"}, new Object[]{220, "guilsinglleft"}, new Object[]{221, "guilsinglright"}, new Object[]{104, "h"}, new Object[]{253, "hungarumlaut"}, new Object[]{45, "hyphen"}, new Object[]{105, OperatorName.SET_FLATNESS}, new Object[]{Integer.valueOf((int) BuiltinOperator.RANDOM_STANDARD_NORMAL), "iacute"}, new Object[]{Integer.valueOf((int) BuiltinOperator.RANDOM_UNIFORM), "icircumflex"}, new Object[]{Integer.valueOf((int) BuiltinOperator.MULTINOMIAL), "idieresis"}, new Object[]{Integer.valueOf((int) BuiltinOperator.BUCKETIZE), "igrave"}, new Object[]{106, OperatorName.SET_LINE_JOINSTYLE}, new Object[]{107, OperatorName.NON_STROKING_CMYK}, new Object[]{108, OperatorName.LINE_TO}, new Object[]{60, "less"}, new Object[]{194, "logicalnot"}, new Object[]{109, OperatorName.MOVE_TO}, new Object[]{248, "macron"}, new Object[]{181, "mu"}, new Object[]{110, OperatorName.ENDPATH}, new Object[]{57, "nine"}, new Object[]{Integer.valueOf((int) BuiltinOperator.GELU), "ntilde"}, new Object[]{35, "numbersign"}, new Object[]{111, "o"}, new Object[]{Integer.valueOf((int) BuiltinOperator.DYNAMIC_UPDATE_SLICE), "oacute"}, new Object[]{153, "ocircumflex"}, new Object[]{154, "odieresis"}, new Object[]{207, "oe"}, new Object[]{254, "ogonek"}, new Object[]{152, "ograve"}, new Object[]{49, "one"}, new Object[]{187, "ordfeminine"}, new Object[]{188, "ordmasculine"}, new Object[]{191, "oslash"}, new Object[]{155, "otilde"}, new Object[]{112, "p"}, new Object[]{166, "paragraph"}, new Object[]{40, "parenleft"}, new Object[]{41, "parenright"}, new Object[]{37, "percent"}, new Object[]{46, TypedValues.CycleType.S_WAVE_PERIOD}, new Object[]{225, "periodcentered"}, new Object[]{228, "perthousand"}, new Object[]{43, "plus"}, new Object[]{177, "plusminus"}, new Object[]{Integer.valueOf((int) BuiltinOperator.MATRIX_DIAG), OperatorName.SAVE}, new Object[]{63, "question"}, new Object[]{192, "questiondown"}, new Object[]{34, "quotedbl"}, new Object[]{227, "quotedblbase"}, new Object[]{210, "quotedblleft"}, new Object[]{Integer.valueOf((int) Primes.SMALL_FACTOR_LIMIT), "quotedblright"}, new Object[]{212, "quoteleft"}, new Object[]{213, "quoteright"}, new Object[]{226, "quotesinglbase"}, new Object[]{39, "quotesingle"}, new Object[]{114, PDPageLabelRange.STYLE_ROMAN_LOWER}, new Object[]{168, "registered"}, new Object[]{251, "ring"}, new Object[]{115, OperatorName.CLOSE_AND_STROKE}, new Object[]{164, "section"}, new Object[]{59, "semicolon"}, new Object[]{55, "seven"}, new Object[]{54, "six"}, new Object[]{47, "slash"}, new Object[]{32, "space"}, new Object[]{163, "sterling"}, new Object[]{116, "t"}, new Object[]{51, "three"}, new Object[]{247, "tilde"}, new Object[]{170, "trademark"}, new Object[]{50, "two"}, new Object[]{117, "u"}, new Object[]{156, "uacute"}, new Object[]{158, "ucircumflex"}, new Object[]{159, "udieresis"}, new Object[]{157, "ugrave"}, new Object[]{95, "underscore"}, new Object[]{118, OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT}, new Object[]{119, "w"}, new Object[]{120, "x"}, new Object[]{121, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT}, new Object[]{216, "ydieresis"}, new Object[]{180, "yen"}, new Object[]{122, "z"}, new Object[]{48, "zero"}};
    public static final MacRomanEncoding INSTANCE = new MacRomanEncoding();

    public MacRomanEncoding() {
        Object[][] objArr;
        for (Object[] objArr2 : MAC_ROMAN_ENCODING_TABLE) {
            addCharacterEncoding(((Integer) objArr2[0]).intValue(), objArr2[1].toString());
        }
    }
}
