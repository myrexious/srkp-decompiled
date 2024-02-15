package com.tom_roush.pdfbox.pdmodel.interactive.annotation.layout;

import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.layout.PlainText;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class PlainTextFormatter {
    private static final int FONTSCALE = 1000;
    private final AppearanceStyle appearanceStyle;
    private final PDAppearanceContentStream contents;
    private float horizontalOffset;
    private final TextAlign textAlignment;
    private final PlainText textContent;
    private float verticalOffset;
    private final float width;
    private final boolean wrapLines;

    /* synthetic */ PlainTextFormatter(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    /* loaded from: classes3.dex */
    public enum TextAlign {
        LEFT(0),
        CENTER(1),
        RIGHT(2),
        JUSTIFY(4);
        
        private final int alignment;

        TextAlign(int i) {
            this.alignment = i;
        }

        int getTextAlign() {
            return this.alignment;
        }

        public static TextAlign valueOf(int i) {
            TextAlign[] values;
            for (TextAlign textAlign : values()) {
                if (textAlign.getTextAlign() == i) {
                    return textAlign;
                }
            }
            return LEFT;
        }
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private AppearanceStyle appearanceStyle;
        private PDAppearanceContentStream contents;
        private PlainText textContent;
        private boolean wrapLines = false;
        private float width = 0.0f;
        private TextAlign textAlignment = TextAlign.LEFT;
        private float horizontalOffset = 0.0f;
        private float verticalOffset = 0.0f;

        public Builder(PDAppearanceContentStream pDAppearanceContentStream) {
            this.contents = pDAppearanceContentStream;
        }

        public Builder style(AppearanceStyle appearanceStyle) {
            this.appearanceStyle = appearanceStyle;
            return this;
        }

        public Builder wrapLines(boolean z) {
            this.wrapLines = z;
            return this;
        }

        public Builder width(float f) {
            this.width = f;
            return this;
        }

        public Builder textAlign(int i) {
            this.textAlignment = TextAlign.valueOf(i);
            return this;
        }

        public Builder textAlign(TextAlign textAlign) {
            this.textAlignment = textAlign;
            return this;
        }

        public Builder text(PlainText plainText) {
            this.textContent = plainText;
            return this;
        }

        public Builder initialOffset(float f, float f2) {
            this.horizontalOffset = f;
            this.verticalOffset = f2;
            return this;
        }

        public PlainTextFormatter build() {
            return new PlainTextFormatter(this, null);
        }
    }

    private PlainTextFormatter(Builder builder) {
        this.appearanceStyle = builder.appearanceStyle;
        this.wrapLines = builder.wrapLines;
        this.width = builder.width;
        this.contents = builder.contents;
        this.textContent = builder.textContent;
        this.textAlignment = builder.textAlignment;
        this.horizontalOffset = builder.horizontalOffset;
        this.verticalOffset = builder.verticalOffset;
    }

    public void format() throws IOException {
        PlainText plainText = this.textContent;
        if (plainText == null || plainText.getParagraphs().isEmpty()) {
            return;
        }
        boolean z = true;
        for (PlainText.Paragraph paragraph : this.textContent.getParagraphs()) {
            if (this.wrapLines) {
                processLines(paragraph.getLines(this.appearanceStyle.getFont(), this.appearanceStyle.getFontSize(), this.width), z);
                z = false;
            } else {
                float stringWidth = (this.appearanceStyle.getFont().getStringWidth(paragraph.getText()) * this.appearanceStyle.getFontSize()) / 1000.0f;
                float f = 0.0f;
                if (stringWidth < this.width) {
                    int i = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$annotation$layout$PlainTextFormatter$TextAlign[this.textAlignment.ordinal()];
                    if (i == 1) {
                        f = (this.width - stringWidth) / 2.0f;
                    } else if (i == 2) {
                        f = this.width - stringWidth;
                    }
                }
                this.contents.newLineAtOffset(this.horizontalOffset + f, this.verticalOffset);
                this.contents.showText(paragraph.getText());
            }
        }
    }

    /* renamed from: com.tom_roush.pdfbox.pdmodel.interactive.annotation.layout.PlainTextFormatter$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$annotation$layout$PlainTextFormatter$TextAlign;

        static {
            int[] iArr = new int[TextAlign.values().length];
            $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$annotation$layout$PlainTextFormatter$TextAlign = iArr;
            try {
                iArr[TextAlign.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$annotation$layout$PlainTextFormatter$TextAlign[TextAlign.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$annotation$layout$PlainTextFormatter$TextAlign[TextAlign.JUSTIFY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void processLines(List<PlainText.Line> list, boolean z) throws IOException {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (PlainText.Line line : list) {
            int i = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$annotation$layout$PlainTextFormatter$TextAlign[this.textAlignment.ordinal()];
            if (i == 1) {
                f2 = (this.width - line.getWidth()) / 2.0f;
            } else if (i == 2) {
                f2 = this.width - line.getWidth();
            } else if (i != 3) {
                f2 = 0.0f;
            } else if (list.indexOf(line) != list.size() - 1) {
                f3 = line.getInterWordSpacing(this.width);
            }
            float f4 = (-f) + f2 + this.horizontalOffset;
            if (list.indexOf(line) == 0 && z) {
                this.contents.newLineAtOffset(f4, this.verticalOffset);
            } else {
                this.verticalOffset -= this.appearanceStyle.getLeading();
                this.contents.newLineAtOffset(f4, -this.appearanceStyle.getLeading());
            }
            f += f4;
            List<PlainText.Word> words = line.getWords();
            int i2 = 0;
            for (PlainText.Word word : words) {
                this.contents.showText(word.getText());
                float floatValue = ((Float) word.getAttributes().getIterator().getAttribute(PlainText.TextAttribute.WIDTH)).floatValue();
                if (i2 != words.size() - 1) {
                    this.contents.newLineAtOffset(floatValue + f3, 0.0f);
                    f = f + floatValue + f3;
                }
                i2++;
            }
        }
        this.horizontalOffset -= f;
    }
}
