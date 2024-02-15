package com.tom_roush.pdfbox.filter;

import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes3.dex */
final class CCITTFaxDecoderStream extends FilterInputStream {
    static final Node EOL;
    static final Node FILL;
    static final int VALUE_EOL = -2000;
    static final int VALUE_FILL = -1000;
    static final int VALUE_HMODE = -4000;
    static final int VALUE_PASSMODE = -3000;
    static final Tree blackRunTree;
    static final Tree codeTree;
    static final Tree eolOnlyTree;
    static final Tree whiteRunTree;
    int buffer;
    int bufferPos;
    private int[] changesCurrentRow;
    private int changesCurrentRowCount;
    private int[] changesReferenceRow;
    private int changesReferenceRowCount;
    private final int columns;
    private int decodedLength;
    private int decodedPos;
    private final byte[] decodedRow;
    private int lastChangingElement;
    private final boolean optionByteAligned;
    private final boolean optionG32D;
    private final boolean optionG3Fill;
    private final boolean optionUncompressed;
    private final int type;
    static final short[][] BLACK_CODES = {new short[]{2, 3}, new short[]{2, 3}, new short[]{2, 3}, new short[]{3}, new short[]{4, 5}, new short[]{4, 5, 7}, new short[]{4, 7}, new short[]{24}, new short[]{23, 24, 55, 8, 15}, new short[]{23, 24, 40, 55, 103, 104, 108, 8, 12, 13}, new short[]{18, 19, 20, 21, 22, 23, 28, 29, 30, 31, 36, 39, 40, 43, 44, 51, 52, 53, 55, 56, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 200, 201, 202, 203, 204, 205, 210, 211, 212, 213, 214, 215, 218, 219}, new short[]{74, 75, 76, 77, 82, 83, 84, 85, 90, 91, 100, 101, 108, 109, 114, 115, 116, 117, 118, 119}};
    static final short[][] BLACK_RUN_LENGTHS = {new short[]{3, 2}, new short[]{1, 4}, new short[]{6, 5}, new short[]{7}, new short[]{9, 8}, new short[]{10, 11, 12}, new short[]{13, 14}, new short[]{15}, new short[]{16, 17, 0, 18, 64}, new short[]{24, 25, 23, 22, 19, 20, 21, 1792, 1856, 1920}, new short[]{1984, 2048, 2112, 2176, 2240, 2304, 2368, 2432, 2496, 2560, 52, 55, 56, 59, 60, 320, 384, 448, 53, 54, 50, 51, 44, 45, 46, 47, 57, 58, 61, OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING, 48, 49, 62, 63, 30, 31, 32, 33, 40, 41, 128, 192, 26, 27, 28, 29, 34, 35, 36, 37, 38, 39, 42, 43}, new short[]{640, 704, 768, 832, 1280, 1344, 1408, 1472, 1536, 1600, 1664, 1728, OS2WindowsMetricsTable.FSTYPE_BITMAP_ONLY, 576, 896, 960, 1024, 1088, 1152, 1216}};
    public static final short[][] WHITE_CODES = {new short[]{7, 8, 11, 12, 14, 15}, new short[]{18, 19, 20, 27, 7, 8}, new short[]{23, 24, 42, 43, 3, 52, 53, 7, 8}, new short[]{19, 23, 24, 36, 39, 40, 43, 3, 55, 4, 8, 12}, new short[]{18, 19, 20, 21, 22, 23, 26, 27, 2, 36, 37, 40, 41, 42, 43, 44, 45, 3, 50, 51, 52, 53, 54, 55, 4, 74, 75, 5, 82, 83, 84, 85, 88, 89, 90, 91, 100, 101, 103, 104, 10, 11}, new short[]{152, 153, 154, 155, 204, 205, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219}, new short[0], new short[]{8, 12, 13}, new short[]{18, 19, 20, 21, 22, 23, 28, 29, 30, 31}};
    public static final short[][] WHITE_RUN_LENGTHS = {new short[]{2, 3, 4, 5, 6, 7}, new short[]{128, 8, 9, 64, 10, 11}, new short[]{192, 1664, 16, 17, 13, 14, 15, 1, 12}, new short[]{26, 21, 28, 27, 18, 24, 25, 22, OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING, 23, 20, 19}, new short[]{33, 34, 35, 36, 37, 38, 31, 32, 29, 53, 54, 39, 40, 41, 42, 43, 44, 30, 61, 62, 63, 0, 320, 384, 45, 59, 60, 46, 49, 50, 51, 52, 55, 56, 57, 58, 448, OS2WindowsMetricsTable.FSTYPE_BITMAP_ONLY, 640, 576, 47, 48}, new short[]{1472, 1536, 1600, 1728, 704, 768, 832, 896, 960, 1024, 1088, 1152, 1216, 1280, 1344, 1408}, new short[0], new short[]{1792, 1856, 1920}, new short[]{1984, 2048, 2112, 2176, 2240, 2304, 2368, 2432, 2496, 2560}};

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public CCITTFaxDecoderStream(InputStream inputStream, int i, int i2, long j, boolean z) {
        super(inputStream);
        this.lastChangingElement = 0;
        this.buffer = -1;
        this.bufferPos = -1;
        this.columns = i;
        this.type = i2;
        this.decodedRow = new byte[(i + 7) / 8];
        int i3 = i + 2;
        this.changesReferenceRow = new int[i3];
        this.changesCurrentRow = new int[i3];
        if (i2 == 2) {
            this.optionByteAligned = z;
            this.optionG32D = false;
            this.optionG3Fill = false;
            this.optionUncompressed = false;
        } else if (i2 == 3) {
            this.optionByteAligned = z;
            this.optionG32D = (1 & j) != 0;
            this.optionG3Fill = (4 & j) != 0;
            this.optionUncompressed = (j & 2) != 0;
        } else if (i2 == 4) {
            this.optionByteAligned = z;
            this.optionG32D = false;
            this.optionG3Fill = false;
            this.optionUncompressed = (j & 2) != 0;
        } else {
            throw new IllegalArgumentException("Illegal parameter: " + i2);
        }
    }

    private void fetch() throws IOException {
        if (this.decodedPos >= this.decodedLength) {
            this.decodedLength = 0;
            try {
                decodeRow();
            } catch (EOFException e) {
                if (this.decodedLength != 0) {
                    throw e;
                }
                this.decodedLength = -1;
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new IOException("Malformed CCITT stream", e2);
            }
            this.decodedPos = 0;
        }
    }

    private void decode1D() throws IOException {
        int decodeRun;
        int i = 0;
        this.changesCurrentRowCount = 0;
        boolean z = true;
        do {
            if (z) {
                decodeRun = decodeRun(whiteRunTree);
            } else {
                decodeRun = decodeRun(blackRunTree);
            }
            i += decodeRun;
            int[] iArr = this.changesCurrentRow;
            int i2 = this.changesCurrentRowCount;
            this.changesCurrentRowCount = i2 + 1;
            iArr[i2] = i;
            z = !z;
        } while (i < this.columns);
    }

    private void decode2D() throws IOException {
        int i;
        int i2;
        this.changesReferenceRowCount = this.changesCurrentRowCount;
        int[] iArr = this.changesCurrentRow;
        this.changesCurrentRow = this.changesReferenceRow;
        this.changesReferenceRow = iArr;
        int i3 = 0;
        this.changesCurrentRowCount = 0;
        boolean z = true;
        while (i3 < this.columns) {
            Node node = codeTree.root;
            while (true) {
                node = node.walk(readBit());
                if (node != null) {
                    if (node.isLeaf) {
                        int i4 = node.value;
                        if (i4 == VALUE_HMODE) {
                            int decodeRun = i3 + decodeRun(z ? whiteRunTree : blackRunTree);
                            int[] iArr2 = this.changesCurrentRow;
                            int i5 = this.changesCurrentRowCount;
                            this.changesCurrentRowCount = i5 + 1;
                            iArr2[i5] = decodeRun;
                            i3 = decodeRun + decodeRun(z ? blackRunTree : whiteRunTree);
                            int[] iArr3 = this.changesCurrentRow;
                            int i6 = this.changesCurrentRowCount;
                            this.changesCurrentRowCount = i6 + 1;
                            iArr3[i6] = i3;
                        } else if (i4 == VALUE_PASSMODE) {
                            int nextChangingElement = getNextChangingElement(i3, z) + 1;
                            if (nextChangingElement >= this.changesReferenceRowCount) {
                                i3 = this.columns;
                            } else {
                                i3 = this.changesReferenceRow[nextChangingElement];
                            }
                        } else {
                            int nextChangingElement2 = getNextChangingElement(i3, z);
                            if (nextChangingElement2 >= this.changesReferenceRowCount || nextChangingElement2 == -1) {
                                i = this.columns;
                                i2 = node.value;
                            } else {
                                i = this.changesReferenceRow[nextChangingElement2];
                                i2 = node.value;
                            }
                            i3 = i + i2;
                            int[] iArr4 = this.changesCurrentRow;
                            int i7 = this.changesCurrentRowCount;
                            iArr4[i7] = i3;
                            this.changesCurrentRowCount = i7 + 1;
                            z = !z;
                        }
                    }
                }
            }
        }
    }

    private int getNextChangingElement(int i, boolean z) {
        int i2 = (this.lastChangingElement & (-2)) + (!z ? 1 : 0);
        if (i2 > 2) {
            i2 -= 2;
        }
        if (i == 0) {
            return i2;
        }
        while (i2 < this.changesReferenceRowCount) {
            if (i < this.changesReferenceRow[i2]) {
                this.lastChangingElement = i2;
                return i2;
            }
            i2 += 2;
        }
        return -1;
    }

    private void decodeRowType2() throws IOException {
        if (this.optionByteAligned) {
            resetBuffer();
        }
        decode1D();
    }

    private void decodeRowType4() throws IOException {
        if (this.optionByteAligned) {
            resetBuffer();
        }
        loop0: while (true) {
            Node node = eolOnlyTree.root;
            while (true) {
                node = node.walk(readBit());
                if (node != null) {
                    if (node.isLeaf) {
                        break loop0;
                    }
                } else {
                    break;
                }
            }
        }
        if (!this.optionG32D || readBit()) {
            decode1D();
        } else {
            decode2D();
        }
    }

    private void decodeRowType6() throws IOException {
        if (this.optionByteAligned) {
            resetBuffer();
        }
        decode2D();
    }

    private void decodeRow() throws IOException {
        int i;
        int i2 = this.type;
        if (i2 == 2) {
            decodeRowType2();
        } else if (i2 == 3) {
            decodeRowType4();
        } else if (i2 == 4) {
            decodeRowType6();
        } else {
            throw new IllegalArgumentException("Illegal parameter: " + this.type);
        }
        this.lastChangingElement = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = true;
        while (true) {
            int i5 = this.changesCurrentRowCount;
            if (i3 > i5) {
                break;
            }
            int i6 = this.columns;
            int i7 = i3 != i5 ? this.changesCurrentRow[i3] : i6;
            if (i7 <= i6) {
                i6 = i7;
            }
            int i8 = i4 / 8;
            while (true) {
                i = i4 % 8;
                if (i == 0 || i6 - i4 <= 0) {
                    break;
                }
                byte[] bArr = this.decodedRow;
                bArr[i8] = (byte) ((z ? 0 : 1 << (7 - i)) | bArr[i8]);
                i4++;
            }
            if (i == 0) {
                i8 = i4 / 8;
                byte b = (byte) (z ? 0 : 255);
                while (i6 - i4 > 7) {
                    this.decodedRow[i8] = b;
                    i4 += 8;
                    i8++;
                }
            }
            while (i6 - i4 > 0) {
                int i9 = i4 % 8;
                if (i9 == 0) {
                    this.decodedRow[i8] = 0;
                }
                byte[] bArr2 = this.decodedRow;
                bArr2[i8] = (byte) ((z ? 0 : 1 << (7 - i9)) | bArr2[i8]);
                i4++;
            }
            z = !z;
            i3++;
        }
        if (i4 != this.columns) {
            throw new IOException("Sum of run-lengths does not equal scan line width: " + i4 + " > " + this.columns);
        }
        this.decodedLength = (i4 + 7) / 8;
    }

    private int decodeRun(Tree tree) throws IOException {
        Node node = tree.root;
        int i = 0;
        while (true) {
            node = node.walk(readBit());
            if (node == null) {
                throw new IOException("Unknown code in Huffman RLE stream");
            }
            if (node.isLeaf) {
                i += node.value;
                if (node.value < 64) {
                    return node.value >= 0 ? i : this.columns;
                }
                node = tree.root;
            }
        }
    }

    private void resetBuffer() {
        this.bufferPos = -1;
    }

    private boolean readBit() throws IOException {
        int i = this.bufferPos;
        if (i < 0 || i > 7) {
            int read = this.in.read();
            this.buffer = read;
            if (read == -1) {
                throw new EOFException("Unexpected end of Huffman RLE stream");
            }
            this.bufferPos = 0;
        }
        int i2 = this.buffer;
        boolean z = (i2 & 128) != 0;
        this.buffer = i2 << 1;
        this.bufferPos++;
        return z;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = this.decodedLength;
        if (i < 0) {
            return 0;
        }
        if (this.decodedPos >= i) {
            fetch();
            if (this.decodedLength < 0) {
                return 0;
            }
        }
        byte[] bArr = this.decodedRow;
        int i2 = this.decodedPos;
        this.decodedPos = i2 + 1;
        return bArr[i2] & UByte.MAX_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.decodedLength;
        if (i3 < 0) {
            Arrays.fill(bArr, i, i + i2, (byte) 0);
            return i2;
        }
        if (this.decodedPos >= i3) {
            fetch();
            if (this.decodedLength < 0) {
                Arrays.fill(bArr, i, i + i2, (byte) 0);
                return i2;
            }
        }
        int min = Math.min(this.decodedLength - this.decodedPos, i2);
        System.arraycopy(this.decodedRow, this.decodedPos, bArr, i, min);
        this.decodedPos += min;
        return min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        int i = this.decodedLength;
        if (i < 0) {
            return -1L;
        }
        if (this.decodedPos >= i) {
            fetch();
            if (this.decodedLength < 0) {
                return -1L;
            }
        }
        int min = (int) Math.min(this.decodedLength - this.decodedPos, j);
        this.decodedPos += min;
        return min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    /* loaded from: classes3.dex */
    public static final class Node {
        boolean canBeFill;
        boolean isLeaf;
        Node left;
        Node right;
        int value;

        private Node() {
            this.canBeFill = false;
            this.isLeaf = false;
        }

        void set(boolean z, Node node) {
            if (!z) {
                this.left = node;
            } else {
                this.right = node;
            }
        }

        Node walk(boolean z) {
            return z ? this.right : this.left;
        }

        public String toString() {
            return "[leaf=" + this.isLeaf + ", value=" + this.value + ", canBeFill=" + this.canBeFill + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static final class Tree {
        final Node root;

        private Tree() {
            this.root = new Node();
        }

        void fill(int i, int i2, int i3) throws IOException {
            Node node = this.root;
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = i - 1;
                boolean z = ((i2 >> (i5 - i4)) & 1) == 1;
                Node walk = node.walk(z);
                if (walk == null) {
                    walk = new Node();
                    if (i4 == i5) {
                        walk.value = i3;
                        walk.isLeaf = true;
                    }
                    if (i2 == 0) {
                        walk.canBeFill = true;
                    }
                    node.set(z, walk);
                } else if (walk.isLeaf) {
                    throw new IOException("node is leaf, no other following");
                }
                node = walk;
            }
        }

        void fill(int i, int i2, Node node) throws IOException {
            Node node2 = this.root;
            int i3 = 0;
            while (i3 < i) {
                int i4 = i - 1;
                boolean z = ((i2 >> (i4 - i3)) & 1) == 1;
                Node walk = node2.walk(z);
                if (walk == null) {
                    Node node3 = i3 == i4 ? node : new Node();
                    if (i2 == 0) {
                        node3.canBeFill = true;
                    }
                    node2.set(z, node3);
                    node2 = node3;
                } else if (walk.isLeaf) {
                    throw new IOException("node is leaf, no other following");
                } else {
                    node2 = walk;
                }
                i3++;
            }
        }
    }

    static {
        Node node = new Node();
        EOL = node;
        node.isLeaf = true;
        node.value = VALUE_EOL;
        Node node2 = new Node();
        FILL = node2;
        node2.value = -1000;
        node2.left = node2;
        node2.right = node;
        Tree tree = new Tree();
        eolOnlyTree = tree;
        try {
            tree.fill(12, 0, node2);
            tree.fill(12, 1, node);
            blackRunTree = new Tree();
            for (int i = 0; i < BLACK_CODES.length; i++) {
                try {
                    int i2 = 0;
                    while (true) {
                        short[] sArr = BLACK_CODES[i];
                        if (i2 < sArr.length) {
                            blackRunTree.fill(i + 2, sArr[i2], BLACK_RUN_LENGTHS[i][i2]);
                            i2++;
                        }
                    }
                } catch (IOException e) {
                    throw new AssertionError(e);
                }
            }
            Tree tree2 = blackRunTree;
            tree2.fill(12, 0, FILL);
            tree2.fill(12, 1, EOL);
            whiteRunTree = new Tree();
            for (int i3 = 0; i3 < WHITE_CODES.length; i3++) {
                try {
                    int i4 = 0;
                    while (true) {
                        short[] sArr2 = WHITE_CODES[i3];
                        if (i4 < sArr2.length) {
                            whiteRunTree.fill(i3 + 4, sArr2[i4], WHITE_RUN_LENGTHS[i3][i4]);
                            i4++;
                        }
                    }
                } catch (IOException e2) {
                    throw new AssertionError(e2);
                }
            }
            Tree tree3 = whiteRunTree;
            tree3.fill(12, 0, FILL);
            tree3.fill(12, 1, EOL);
            Tree tree4 = new Tree();
            codeTree = tree4;
            try {
                tree4.fill(4, 1, VALUE_PASSMODE);
                tree4.fill(3, 1, VALUE_HMODE);
                tree4.fill(1, 1, 0);
                tree4.fill(3, 3, 1);
                tree4.fill(6, 3, 2);
                tree4.fill(7, 3, 3);
                tree4.fill(3, 2, -1);
                tree4.fill(6, 2, -2);
                tree4.fill(7, 2, -3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        } catch (IOException e4) {
            throw new AssertionError(e4);
        }
    }
}
