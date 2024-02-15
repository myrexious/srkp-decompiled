package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmpbox.type.ThumbnailType;

/* loaded from: classes3.dex */
public class PlainText {
    private static final float FONTSCALE = 1000.0f;
    private final List<Paragraph> paragraphs;

    public PlainText(String str) {
        if (str.isEmpty()) {
            ArrayList arrayList = new ArrayList(1);
            this.paragraphs = arrayList;
            arrayList.add(new Paragraph(""));
            return;
        }
        String[] split = str.replace('\t', ' ').split("\\r\\n|\\n|\\r|\\u2028|\\u2029");
        this.paragraphs = new ArrayList(split.length);
        for (String str2 : split) {
            if (str2.length() == 0) {
                str2 = StringUtils.SPACE;
            }
            this.paragraphs.add(new Paragraph(str2));
        }
    }

    PlainText(List<String> list) {
        this.paragraphs = new ArrayList();
        for (String str : list) {
            this.paragraphs.add(new Paragraph(str));
        }
    }

    public List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    /* loaded from: classes3.dex */
    public static class TextAttribute extends AttributedCharacterIterator.Attribute {
        public static final AttributedCharacterIterator.Attribute WIDTH = new TextAttribute(ThumbnailType.WIDTH);
        private static final long serialVersionUID = -3138885145941283005L;

        protected TextAttribute(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Paragraph {
        private final String textContent;

        Paragraph(String str) {
            this.textContent = str;
        }

        public String getText() {
            return this.textContent;
        }

        public List<Line> getLines(PDFont pDFont, float f, float f2) throws IOException {
            String substring;
            BreakIterator lineInstance = BreakIterator.getLineInstance();
            lineInstance.setText(this.textContent);
            float f3 = f / PlainText.FONTSCALE;
            int first = lineInstance.first();
            int next = lineInstance.next();
            ArrayList arrayList = new ArrayList();
            Line line = new Line();
            float f4 = 0.0f;
            while (next != -1) {
                String substring2 = this.textContent.substring(first, next);
                float stringWidth = pDFont.getStringWidth(substring2) * f3;
                int i = next - first;
                f4 += stringWidth;
                if (f4 >= f2 && Character.isWhitespace(substring2.charAt(substring2.length() - 1))) {
                    f4 -= pDFont.getStringWidth(substring2.substring(substring2.length() - 1)) * f3;
                }
                if (f4 >= f2 && !line.getWords().isEmpty()) {
                    line.setWidth(line.calculateWidth(pDFont, f));
                    arrayList.add(line);
                    line = new Line();
                    f4 = pDFont.getStringWidth(substring2) * f3;
                }
                boolean z = false;
                if (stringWidth > f2 && line.getWords().isEmpty()) {
                    do {
                        i--;
                        substring = substring2.substring(0, i);
                    } while (pDFont.getStringWidth(substring) * f3 >= f2);
                    stringWidth = pDFont.getStringWidth(substring) * f3;
                    substring2 = substring;
                    f4 = stringWidth;
                    z = true;
                }
                AttributedString attributedString = new AttributedString(substring2);
                attributedString.addAttribute(TextAttribute.WIDTH, Float.valueOf(stringWidth));
                Word word = new Word(substring2);
                word.setAttributes(attributedString);
                line.addWord(word);
                if (z) {
                    first += i;
                } else {
                    int i2 = next;
                    next = lineInstance.next();
                    first = i2;
                }
            }
            line.setWidth(line.calculateWidth(pDFont, f));
            arrayList.add(line);
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Line {
        private float lineWidth;
        private final List<Word> words = new ArrayList();

        Line() {
        }

        public float getWidth() {
            return this.lineWidth;
        }

        void setWidth(float f) {
            this.lineWidth = f;
        }

        float calculateWidth(PDFont pDFont, float f) throws IOException {
            float f2 = f / PlainText.FONTSCALE;
            float f3 = 0.0f;
            int i = 0;
            for (Word word : this.words) {
                f3 += ((Float) word.getAttributes().getIterator().getAttribute(TextAttribute.WIDTH)).floatValue();
                String text = word.getText();
                if (i == this.words.size() - 1 && Character.isWhitespace(text.charAt(text.length() - 1))) {
                    f3 -= pDFont.getStringWidth(text.substring(text.length() - 1)) * f2;
                }
                i++;
            }
            return f3;
        }

        public List<Word> getWords() {
            return this.words;
        }

        public float getInterWordSpacing(float f) {
            return (f - this.lineWidth) / (this.words.size() - 1);
        }

        void addWord(Word word) {
            this.words.add(word);
        }
    }

    /* loaded from: classes3.dex */
    public static class Word {
        private AttributedString attributedString;
        private final String textContent;

        Word(String str) {
            this.textContent = str;
        }

        public String getText() {
            return this.textContent;
        }

        public AttributedString getAttributes() {
            return this.attributedString;
        }

        void setAttributes(AttributedString attributedString) {
            this.attributedString = attributedString;
        }
    }
}
