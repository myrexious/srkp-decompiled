package org.apache.commons.imaging.formats.png.chunks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.InflaterInputStream;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class PngChunkIccp extends PngChunk {
    private static final Logger LOGGER = Logger.getLogger(PngChunkIccp.class.getName());
    private final byte[] compressedProfile;
    public final int compressionMethod;
    public final String profileName;
    private final byte[] uncompressedProfile;

    public PngChunkIccp(int i, int i2, int i3, byte[] bArr) throws ImageReadException, IOException {
        super(i, i2, i3, bArr);
        int findNull = BinaryFunctions.findNull(bArr);
        if (findNull < 0) {
            throw new ImageReadException("PngChunkIccp: No Profile Name");
        }
        byte[] bArr2 = new byte[findNull];
        System.arraycopy(bArr, 0, bArr2, 0, findNull);
        String str = new String(bArr2, StandardCharsets.ISO_8859_1);
        this.profileName = str;
        int i4 = findNull + 1;
        byte b = bArr[i4];
        this.compressionMethod = b;
        int i5 = i4 + 1;
        int length = bArr.length - i5;
        byte[] bArr3 = new byte[length];
        this.compressedProfile = bArr3;
        System.arraycopy(bArr, i5, bArr3, 0, length);
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("ProfileName: " + str);
            logger.finest("ProfileName.length(): " + str.length());
            logger.finest("CompressionMethod: " + ((int) b));
            logger.finest("CompressedProfileLength: " + length);
            logger.finest("bytes.length: " + bArr.length);
        }
        this.uncompressedProfile = BinaryFunctions.getStreamBytes(new InflaterInputStream(new ByteArrayInputStream(bArr3)));
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("UncompressedProfile: " + bArr.length);
        }
    }

    public byte[] getUncompressedProfile() {
        return (byte[]) this.uncompressedProfile.clone();
    }
}
