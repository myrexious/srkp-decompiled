package org.apache.commons.io.filefilter;

import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] bArr) {
        this(bArr, 0L);
    }

    public MagicNumberFileFilter(String str) {
        this(str, 0L);
    }

    public MagicNumberFileFilter(String str, long j) {
        if (str == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = str.getBytes(Charset.defaultCharset());
        this.byteOffset = j;
    }

    public MagicNumberFileFilter(byte[] bArr, long j) {
        if (bArr == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        byte[] bArr2 = new byte[bArr.length];
        this.magicNumbers = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.byteOffset = j;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        byte[] bArr;
        RandomAccessFile randomAccessFile;
        if (file != null && file.isFile() && file.canRead()) {
            RandomAccessFile randomAccessFile2 = null;
            try {
                bArr = new byte[this.magicNumbers.length];
                randomAccessFile = new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
            } catch (IOException unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                randomAccessFile.seek(this.byteOffset);
                int read = randomAccessFile.read(bArr);
                byte[] bArr2 = this.magicNumbers;
                if (read == bArr2.length) {
                    boolean equals = Arrays.equals(bArr2, bArr);
                    IOUtils.closeQuietly(randomAccessFile);
                    return equals;
                }
                IOUtils.closeQuietly(randomAccessFile);
                return false;
            } catch (IOException unused2) {
                randomAccessFile2 = randomAccessFile;
                IOUtils.closeQuietly(randomAccessFile2);
                return false;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile2 = randomAccessFile;
                IOUtils.closeQuietly(randomAccessFile2);
                throw th;
            }
        }
        return false;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + "(" + new String(this.magicNumbers, Charset.defaultCharset()) + "," + this.byteOffset + ")";
    }
}
