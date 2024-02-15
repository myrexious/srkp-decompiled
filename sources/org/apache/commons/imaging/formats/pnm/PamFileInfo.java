package org.apache.commons.imaging.formats.pnm;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;

/* loaded from: classes2.dex */
public class PamFileInfo extends FileInfo {
    private final int bytesPerSample;
    private final int depth;
    private final boolean hasAlpha;
    private final int maxval;
    private final float scale;
    private final TupleReader tupleReader;

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public String getImageTypeDescription() {
        return "PAM: portable arbitrary map file format";
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public String getMIMEType() {
        return "image/x-portable-arbitrary-map";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x006d, code lost:
        if (r7.equals("BLACKANDWHITE_ALPHA") == false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PamFileInfo(int r3, int r4, int r5, int r6, java.lang.String r7) throws org.apache.commons.imaging.ImageReadException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.pnm.PamFileInfo.<init>(int, int, int, int, java.lang.String):void");
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public boolean hasAlpha() {
        return this.hasAlpha;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getNumComponents() {
        return this.depth;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getBitDepth() {
        return this.maxval;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public ImageFormat getImageType() {
        return ImageFormats.PAM;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public ImageInfo.ColorType getColorType() {
        return this.tupleReader.getColorType();
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getRGB(WhiteSpaceReader whiteSpaceReader) throws IOException {
        throw new UnsupportedOperationException("PAM files are only ever binary");
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getRGB(InputStream inputStream) throws IOException {
        return this.tupleReader.getRGB(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public abstract class TupleReader {
        public abstract ImageInfo.ColorType getColorType();

        public abstract int getRGB(InputStream inputStream) throws IOException;

        private TupleReader() {
            PamFileInfo.this = r1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class GrayscaleTupleReader extends TupleReader {
        private final ImageInfo.ColorType colorType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        GrayscaleTupleReader(ImageInfo.ColorType colorType) {
            super();
            PamFileInfo.this = r2;
            this.colorType = colorType;
        }

        @Override // org.apache.commons.imaging.formats.pnm.PamFileInfo.TupleReader
        public ImageInfo.ColorType getColorType() {
            return this.colorType;
        }

        @Override // org.apache.commons.imaging.formats.pnm.PamFileInfo.TupleReader
        public int getRGB(InputStream inputStream) throws IOException {
            int scaleSample = FileInfo.scaleSample(FileInfo.readSample(inputStream, PamFileInfo.this.bytesPerSample), PamFileInfo.this.scale, PamFileInfo.this.maxval) & 255;
            return (((PamFileInfo.this.hasAlpha ? FileInfo.scaleSample(FileInfo.readSample(inputStream, PamFileInfo.this.bytesPerSample), PamFileInfo.this.scale, PamFileInfo.this.maxval) : 255) & 255) << 24) | (scaleSample << 16) | (scaleSample << 8) | (scaleSample << 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ColorTupleReader extends TupleReader {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private ColorTupleReader() {
            super();
            PamFileInfo.this = r2;
        }

        @Override // org.apache.commons.imaging.formats.pnm.PamFileInfo.TupleReader
        public ImageInfo.ColorType getColorType() {
            return ImageInfo.ColorType.RGB;
        }

        @Override // org.apache.commons.imaging.formats.pnm.PamFileInfo.TupleReader
        public int getRGB(InputStream inputStream) throws IOException {
            int readSample = FileInfo.readSample(inputStream, PamFileInfo.this.bytesPerSample);
            int readSample2 = FileInfo.readSample(inputStream, PamFileInfo.this.bytesPerSample);
            int readSample3 = FileInfo.readSample(inputStream, PamFileInfo.this.bytesPerSample);
            return (((PamFileInfo.this.hasAlpha ? FileInfo.scaleSample(FileInfo.readSample(inputStream, PamFileInfo.this.bytesPerSample), PamFileInfo.this.scale, PamFileInfo.this.maxval) : 255) & 255) << 24) | ((FileInfo.scaleSample(readSample, PamFileInfo.this.scale, PamFileInfo.this.maxval) & 255) << 16) | ((FileInfo.scaleSample(readSample2, PamFileInfo.this.scale, PamFileInfo.this.maxval) & 255) << 8) | ((FileInfo.scaleSample(readSample3, PamFileInfo.this.scale, PamFileInfo.this.maxval) & 255) << 0);
        }
    }
}
