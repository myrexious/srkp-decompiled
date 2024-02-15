package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public class PDPageLabels implements COSObjectable {
    private PDDocument doc;
    private Map<Integer, PDPageLabelRange> labels;

    /* loaded from: classes3.dex */
    public interface LabelHandler {
        void newLabel(int i, String str);
    }

    public PDPageLabels(PDDocument pDDocument) {
        this.labels = new TreeMap();
        this.doc = pDDocument;
        PDPageLabelRange pDPageLabelRange = new PDPageLabelRange();
        pDPageLabelRange.setStyle("D");
        this.labels.put(0, pDPageLabelRange);
    }

    public PDPageLabels(PDDocument pDDocument, COSDictionary cOSDictionary) throws IOException {
        this(pDDocument);
        if (cOSDictionary == null) {
            return;
        }
        findLabels(new PDNumberTreeNode(cOSDictionary, PDPageLabelRange.class));
    }

    private void findLabels(PDNumberTreeNode pDNumberTreeNode) throws IOException {
        List<PDNumberTreeNode> kids = pDNumberTreeNode.getKids();
        if (pDNumberTreeNode.getKids() != null) {
            for (PDNumberTreeNode pDNumberTreeNode2 : kids) {
                findLabels(pDNumberTreeNode2);
            }
            return;
        }
        Map<Integer, COSObjectable> numbers = pDNumberTreeNode.getNumbers();
        if (numbers != null) {
            for (Map.Entry<Integer, COSObjectable> entry : numbers.entrySet()) {
                if (entry.getKey().intValue() >= 0) {
                    this.labels.put(entry.getKey(), (PDPageLabelRange) entry.getValue());
                }
            }
        }
    }

    public int getPageRangeCount() {
        return this.labels.size();
    }

    public PDPageLabelRange getPageLabelRange(int i) {
        return this.labels.get(Integer.valueOf(i));
    }

    public void setLabelItem(int i, PDPageLabelRange pDPageLabelRange) {
        if (i < 0) {
            throw new IllegalArgumentException("startPage parameter of setLabelItem may not be < 0");
        }
        this.labels.put(Integer.valueOf(i), pDPageLabelRange);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        COSDictionary cOSDictionary = new COSDictionary();
        COSArray cOSArray = new COSArray();
        for (Map.Entry<Integer, PDPageLabelRange> entry : this.labels.entrySet()) {
            cOSArray.add((COSBase) COSInteger.get(entry.getKey().intValue()));
            cOSArray.add(entry.getValue());
        }
        cOSDictionary.setItem(COSName.NUMS, (COSBase) cOSArray);
        return cOSDictionary;
    }

    public Map<String, Integer> getPageIndicesByLabels() {
        int numberOfPages = this.doc.getNumberOfPages();
        final HashMap hashMap = new HashMap(numberOfPages);
        computeLabels(new LabelHandler() { // from class: com.tom_roush.pdfbox.pdmodel.common.PDPageLabels.1
            @Override // com.tom_roush.pdfbox.pdmodel.common.PDPageLabels.LabelHandler
            public void newLabel(int i, String str) {
                hashMap.put(str, Integer.valueOf(i));
            }
        }, numberOfPages);
        return hashMap;
    }

    public String[] getLabelsByPageIndices() {
        final int numberOfPages = this.doc.getNumberOfPages();
        final String[] strArr = new String[numberOfPages];
        computeLabels(new LabelHandler() { // from class: com.tom_roush.pdfbox.pdmodel.common.PDPageLabels.2
            @Override // com.tom_roush.pdfbox.pdmodel.common.PDPageLabels.LabelHandler
            public void newLabel(int i, String str) {
                if (i < numberOfPages) {
                    strArr[i] = str;
                }
            }
        }, numberOfPages);
        return strArr;
    }

    public NavigableSet<Integer> getPageIndices() {
        return new TreeSet(this.labels.keySet());
    }

    private void computeLabels(LabelHandler labelHandler, int i) {
        Iterator<Map.Entry<Integer, PDPageLabelRange>> it = this.labels.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Integer, PDPageLabelRange> next = it.next();
            int i2 = 0;
            while (it.hasNext()) {
                Map.Entry<Integer, PDPageLabelRange> next2 = it.next();
                LabelGenerator labelGenerator = new LabelGenerator(next.getValue(), next2.getKey().intValue() - next.getKey().intValue());
                while (labelGenerator.hasNext()) {
                    labelHandler.newLabel(i2, labelGenerator.next());
                    i2++;
                }
                next = next2;
            }
            LabelGenerator labelGenerator2 = new LabelGenerator(next.getValue(), i - next.getKey().intValue());
            while (labelGenerator2.hasNext()) {
                labelHandler.newLabel(i2, labelGenerator2.next());
                i2++;
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class LabelGenerator implements Iterator<String> {
        private static final String[][] ROMANS = {new String[]{"", OperatorName.SET_FLATNESS, "ii", "iii", "iv", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "vi", "vii", "viii", "ix"}, new String[]{"", "x", "xx", "xxx", "xl", OperatorName.LINE_TO, "lx", "lxx", "lxxx", "xc"}, new String[]{"", OperatorName.CURVE_TO, "cc", "ccc", "cd", OperatorName.SET_LINE_DASHPATTERN, "dc", "dcc", "dccc", OperatorName.CONCAT}};
        private int currentPage = 0;
        private final PDPageLabelRange labelInfo;
        private final int numPages;

        LabelGenerator(PDPageLabelRange pDPageLabelRange, int i) {
            this.labelInfo = pDPageLabelRange;
            this.numPages = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentPage < this.numPages;
        }

        @Override // java.util.Iterator
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            StringBuilder sb = new StringBuilder();
            String prefix = this.labelInfo.getPrefix();
            if (prefix != null) {
                int indexOf = prefix.indexOf(0);
                if (indexOf > -1) {
                    prefix = prefix.substring(0, indexOf);
                }
                sb.append(prefix);
            }
            String style = this.labelInfo.getStyle();
            if (style != null) {
                sb.append(getNumber(this.labelInfo.getStart() + this.currentPage, style));
            }
            this.currentPage++;
            return sb.toString();
        }

        private String getNumber(int i, String str) {
            if ("D".equals(str)) {
                return Integer.toString(i);
            }
            if (PDPageLabelRange.STYLE_LETTERS_LOWER.equals(str)) {
                return makeLetterLabel(i);
            }
            if ("A".equals(str)) {
                return makeLetterLabel(i).toUpperCase();
            }
            if (PDPageLabelRange.STYLE_ROMAN_LOWER.equals(str)) {
                return makeRomanLabel(i);
            }
            if ("R".equals(str)) {
                return makeRomanLabel(i).toUpperCase();
            }
            return Integer.toString(i);
        }

        private static String makeRomanLabel(int i) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < 3 && i > 0; i2++) {
                sb.insert(0, ROMANS[i2][i % 10]);
                i /= 10;
            }
            for (int i3 = 0; i3 < i; i3++) {
                sb.insert(0, 'm');
            }
            return sb.toString();
        }

        private static String makeLetterLabel(int i) {
            StringBuilder sb = new StringBuilder();
            int i2 = i / 26;
            int i3 = i % 26;
            int signum = i2 + Integer.signum(i3);
            int signum2 = ((i3 + ((1 - Integer.signum(i3)) * 26)) + 97) - 1;
            for (int i4 = 0; i4 < signum; i4++) {
                sb.appendCodePoint(signum2);
            }
            return sb.toString();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
