package org.apache.commons.imaging.formats.png.chunks;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.png.PngText;

/* loaded from: classes2.dex */
public class PngChunkText extends PngTextChunk {
    private static final Logger LOGGER = Logger.getLogger(PngChunkText.class.getName());
    public final String keyword;
    public final String text;

    public PngChunkText(int i, int i2, int i3, byte[] bArr) throws ImageReadException {
        super(i, i2, i3, bArr);
        int findNull = BinaryFunctions.findNull(bArr);
        if (findNull < 0) {
            throw new ImageReadException("PNG tEXt chunk keyword is not terminated.");
        }
        String str = new String(bArr, 0, findNull, StandardCharsets.ISO_8859_1);
        this.keyword = str;
        int i4 = findNull + 1;
        String str2 = new String(bArr, i4, bArr.length - i4, StandardCharsets.ISO_8859_1);
        this.text = str2;
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Keyword: " + str);
            logger.finest("Text: " + str2);
        }
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
        return new PngText.Text(this.keyword, this.text);
    }
}
