package androidx.constraintlayout.core.parser;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;

/* loaded from: classes.dex */
public class CLString extends CLElement {
    public CLString(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLString(cArr);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        return OperatorName.SHOW_TEXT_LINE + content() + OperatorName.SHOW_TEXT_LINE;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        addIndent(sb, i);
        sb.append(OperatorName.SHOW_TEXT_LINE);
        sb.append(content());
        sb.append(OperatorName.SHOW_TEXT_LINE);
        return sb.toString();
    }
}
