package org.apache.commons.imaging.formats.png.chunks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.InflaterInputStream;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.png.PngText;

/* loaded from: classes2.dex */
public class PngChunkItxt extends PngTextChunk {
    public final String keyword;
    public final String languageTag;
    public final String text;
    public final String translatedKeyword;

    public PngChunkItxt(int i, int i2, int i3, byte[] bArr) throws ImageReadException, IOException {
        super(i, i2, i3, bArr);
        int findNull = BinaryFunctions.findNull(bArr);
        if (findNull < 0) {
            throw new ImageReadException("PNG iTXt chunk keyword is not terminated.");
        }
        this.keyword = new String(bArr, 0, findNull, StandardCharsets.ISO_8859_1);
        int i4 = findNull + 1;
        int i5 = i4 + 1;
        byte b = bArr[i4];
        if (b != 0 && b != 1) {
            throw new ImageReadException("PNG iTXt chunk has invalid compression flag: " + ((int) b));
        }
        boolean z = b == 1;
        int i6 = i5 + 1;
        byte b2 = bArr[i5];
        if (z && b2 != 0) {
            throw new ImageReadException("PNG iTXt chunk has unexpected compression method: " + ((int) b2));
        }
        int findNull2 = BinaryFunctions.findNull(bArr, i6);
        if (findNull2 < 0) {
            throw new ImageReadException("PNG iTXt chunk language tag is not terminated.");
        }
        this.languageTag = new String(bArr, i6, findNull2 - i6, StandardCharsets.ISO_8859_1);
        int i7 = findNull2 + 1;
        int findNull3 = BinaryFunctions.findNull(bArr, i7);
        if (findNull3 < 0) {
            throw new ImageReadException("PNG iTXt chunk translated keyword is not terminated.");
        }
        this.translatedKeyword = new String(bArr, i7, findNull3 - i7, StandardCharsets.UTF_8);
        int i8 = findNull3 + 1;
        if (z) {
            int length = bArr.length - i8;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, i8, bArr2, 0, length);
            this.text = new String(BinaryFunctions.getStreamBytes(new InflaterInputStream(new ByteArrayInputStream(bArr2))), StandardCharsets.UTF_8);
            return;
        }
        this.text = new String(bArr, i8, bArr.length - i8, StandardCharsets.UTF_8);
    }

    @Override // org.apache.commons.imaging.formats.png.chunks.PngTextChunk
    public String getKeyword() {
        return this.keyword;
    }

    @Override // org.apache.commons.imaging.formats.png.chunks.PngTextChunk
    public String getText() {
        return this.text;
    }

    @Override // org.apache.commons.imaging.formats.png.chunks.PngTextChunk
    public PngText getContents() {
        return new PngText.Itxt(this.keyword, this.text, this.languageTag, this.translatedKeyword);
    }
}
