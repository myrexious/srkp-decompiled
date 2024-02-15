package com.tom_roush.pdfbox.util.filetypedetector;

import com.tom_roush.pdfbox.util.Charsets;
import java.io.BufferedInputStream;
import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public final class FileTypeDetector {
    private static final ByteTrie<FileType> root;

    static {
        ByteTrie<FileType> byteTrie = new ByteTrie<>();
        root = byteTrie;
        byteTrie.setDefaultValue(FileType.UNKNOWN);
        byteTrie.addPath(FileType.JPEG, new byte[]{-1, -40});
        byteTrie.addPath(FileType.TIFF, "II".getBytes(Charsets.ISO_8859_1), new byte[]{BuiltinOptions.NegOptions, 0});
        byteTrie.addPath(FileType.TIFF, "MM".getBytes(Charsets.ISO_8859_1), new byte[]{0, BuiltinOptions.NegOptions});
        byteTrie.addPath(FileType.PSD, "8BPS".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.PNG, new byte[]{-119, 80, BuiltinOptions.AbsOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions, 13, 10, BuiltinOptions.TransposeOptions, 10, 0, 0, 0, 13, BuiltinOptions.RangeOptions, BuiltinOptions.FloorModOptions, BuiltinOptions.FillOptions, BuiltinOptions.AddNOptions});
        byteTrie.addPath(FileType.BMP, "BM".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.GIF, "GIF87a".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.GIF, "GIF89a".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.ICO, new byte[]{0, 0, 1, 0});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 0, 1});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 2, 1});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 3, 1});
        byteTrie.addPath(FileType.PCX, new byte[]{10, 5, 1});
        byteTrie.addPath(FileType.RIFF, "RIFF".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.CRW, "II".getBytes(Charsets.ISO_8859_1), new byte[]{BuiltinOptions.TransposeOptions, 0, 0, 0}, "HEAPCCDR".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.CR2, "II".getBytes(Charsets.ISO_8859_1), new byte[]{BuiltinOptions.NegOptions, 0, 16, 0, 0, 0, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.AddNOptions});
        byteTrie.addPath(FileType.NEF, "MM".getBytes(Charsets.ISO_8859_1), new byte[]{0, BuiltinOptions.NegOptions, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0});
        byteTrie.addPath(FileType.ORF, "IIRO".getBytes(Charsets.ISO_8859_1), new byte[]{8, 0});
        byteTrie.addPath(FileType.ORF, "IIRS".getBytes(Charsets.ISO_8859_1), new byte[]{8, 0});
        byteTrie.addPath(FileType.RAF, "FUJIFILMCCD-RAW".getBytes(Charsets.ISO_8859_1));
        byteTrie.addPath(FileType.RW2, "II".getBytes(Charsets.ISO_8859_1), new byte[]{BuiltinOptions.WhereOptions, 0});
    }

    private FileTypeDetector() {
    }

    public static FileType detectFileType(BufferedInputStream bufferedInputStream) throws IOException {
        if (!bufferedInputStream.markSupported()) {
            throw new IOException("Stream must support mark/reset");
        }
        ByteTrie<FileType> byteTrie = root;
        int maxDepth = byteTrie.getMaxDepth();
        bufferedInputStream.mark(maxDepth);
        byte[] bArr = new byte[maxDepth];
        if (bufferedInputStream.read(bArr) == -1) {
            throw new IOException("Stream ended before file's magic number could be determined.");
        }
        bufferedInputStream.reset();
        return byteTrie.find(bArr);
    }

    public static FileType detectFileType(byte[] bArr) throws IOException {
        return root.find(bArr);
    }
}
