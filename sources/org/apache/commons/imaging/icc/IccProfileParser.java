package org.apache.commons.imaging.icc;

import java.awt.color.ICC_Profile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;

/* loaded from: classes2.dex */
public class IccProfileParser extends BinaryFileParser {
    private static final Logger LOGGER = Logger.getLogger(IccProfileParser.class.getName());

    public IccProfileParser() {
        setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    public IccProfileInfo getICCProfileInfo(ICC_Profile iCC_Profile) {
        if (iCC_Profile == null) {
            return null;
        }
        return getICCProfileInfo(new ByteSourceArray(iCC_Profile.getData()));
    }

    public IccProfileInfo getICCProfileInfo(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return getICCProfileInfo(new ByteSourceArray(bArr));
    }

    public IccProfileInfo getICCProfileInfo(File file) {
        if (file == null) {
            return null;
        }
        return getICCProfileInfo(new ByteSourceFile(file));
    }

    /* JADX WARN: Removed duplicated region for block: B:92:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.imaging.icc.IccProfileInfo getICCProfileInfo(org.apache.commons.imaging.common.bytesource.ByteSource r9) {
        /*
            r8 = this;
            r0 = 0
            java.io.InputStream r1 = r9.getInputStream()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            org.apache.commons.imaging.icc.IccProfileInfo r2 = r8.readICCProfileInfo(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            if (r2 != 0) goto L1e
            if (r1 == 0) goto L1d
            r1.close()     // Catch: java.lang.Exception -> L11
            goto L1d
        L11:
            r9 = move-exception
            java.util.logging.Logger r1 = org.apache.commons.imaging.icc.IccProfileParser.LOGGER
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = r9.getMessage()
            r1.log(r2, r3, r9)
        L1d:
            return r0
        L1e:
            r1.close()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            org.apache.commons.imaging.icc.IccTag[] r1 = r2.getTags()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            int r3 = r1.length     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r4 = 0
        L27:
            if (r4 >= r3) goto L39
            r5 = r1[r4]     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            int r6 = r5.offset     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            int r7 = r5.length     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            byte[] r6 = r9.getBlock(r6, r7)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r5.setData(r6)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            int r4 = r4 + 1
            goto L27
        L39:
            return r2
        L3a:
            r9 = move-exception
            r0 = r1
            goto L61
        L3d:
            r9 = move-exception
            goto L43
        L3f:
            r9 = move-exception
            goto L61
        L41:
            r9 = move-exception
            r1 = r0
        L43:
            java.util.logging.Logger r2 = org.apache.commons.imaging.icc.IccProfileParser.LOGGER     // Catch: java.lang.Throwable -> L3a
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L3a
            java.lang.String r4 = r9.getMessage()     // Catch: java.lang.Throwable -> L3a
            r2.log(r3, r4, r9)     // Catch: java.lang.Throwable -> L3a
            if (r1 == 0) goto L60
            r1.close()     // Catch: java.lang.Exception -> L54
            goto L60
        L54:
            r9 = move-exception
            java.util.logging.Logger r1 = org.apache.commons.imaging.icc.IccProfileParser.LOGGER
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = r9.getMessage()
            r1.log(r2, r3, r9)
        L60:
            return r0
        L61:
            if (r0 == 0) goto L73
            r0.close()     // Catch: java.lang.Exception -> L67
            goto L73
        L67:
            r0 = move-exception
            java.util.logging.Logger r1 = org.apache.commons.imaging.icc.IccProfileParser.LOGGER
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = r0.getMessage()
            r1.log(r2, r3, r0)
        L73:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.icc.IccProfileParser.getICCProfileInfo(org.apache.commons.imaging.common.bytesource.ByteSource):org.apache.commons.imaging.icc.IccProfileInfo");
    }

    private IccProfileInfo readICCProfileInfo(InputStream inputStream) {
        String str = "]";
        String str2 = "Not a Valid ICC Profile";
        CachingInputStream cachingInputStream = new CachingInputStream(inputStream);
        try {
            int read4Bytes = BinaryFunctions.read4Bytes("ProfileSize", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            int read4Bytes2 = BinaryFunctions.read4Bytes("Signature", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("CMMTypeSignature", read4Bytes2);
            }
            int read4Bytes3 = BinaryFunctions.read4Bytes("ProfileVersion", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            int read4Bytes4 = BinaryFunctions.read4Bytes("ProfileDeviceClassSignature", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("ProfileDeviceClassSignature", read4Bytes4);
            }
            int read4Bytes5 = BinaryFunctions.read4Bytes("ColorSpace", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("ColorSpace", read4Bytes5);
            }
            int read4Bytes6 = BinaryFunctions.read4Bytes("ProfileConnectionSpace", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("ProfileConnectionSpace", read4Bytes6);
            }
            BinaryFunctions.skipBytes(cachingInputStream, 12L, "Not a Valid ICC Profile");
            int read4Bytes7 = BinaryFunctions.read4Bytes("ProfileFileSignature", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("ProfileFileSignature", read4Bytes7);
            }
            int read4Bytes8 = BinaryFunctions.read4Bytes("PrimaryPlatformSignature", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("PrimaryPlatformSignature", read4Bytes8);
            }
            int read4Bytes9 = BinaryFunctions.read4Bytes("VariousFlags", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("VariousFlags", read4Bytes7);
            }
            int read4Bytes10 = BinaryFunctions.read4Bytes("DeviceManufacturer", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("DeviceManufacturer", read4Bytes10);
            }
            int read4Bytes11 = BinaryFunctions.read4Bytes("DeviceModel", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("DeviceModel", read4Bytes11);
            }
            BinaryFunctions.skipBytes(cachingInputStream, 8L, "Not a Valid ICC Profile");
            int read4Bytes12 = BinaryFunctions.read4Bytes("RenderingIntent", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("RenderingIntent", read4Bytes12);
            }
            int i = read4Bytes12;
            BinaryFunctions.skipBytes(cachingInputStream, 12L, "Not a Valid ICC Profile");
            int read4Bytes13 = BinaryFunctions.read4Bytes("ProfileCreatorSignature", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("ProfileCreatorSignature", read4Bytes13);
            }
            int i2 = read4Bytes13;
            BinaryFunctions.skipBytes(cachingInputStream, 16L, "Not a Valid ICC Profile");
            BinaryFunctions.skipBytes(cachingInputStream, 28L, "Not a Valid ICC Profile");
            int read4Bytes14 = BinaryFunctions.read4Bytes("TagCount", cachingInputStream, "Not a Valid ICC Profile", getByteOrder());
            IccTag[] iccTagArr = new IccTag[read4Bytes14];
            int i3 = 0;
            while (i3 < read4Bytes14) {
                try {
                    int i4 = read4Bytes14;
                    int read4Bytes15 = BinaryFunctions.read4Bytes("TagSignature[" + i3 + str, cachingInputStream, str2, getByteOrder());
                    int i5 = i2;
                    int i6 = i;
                    String str3 = str;
                    iccTagArr[i3] = new IccTag(read4Bytes15, BinaryFunctions.read4Bytes("OffsetToData[" + i3 + str, cachingInputStream, str2, getByteOrder()), BinaryFunctions.read4Bytes("ElementSize[" + i3 + str, cachingInputStream, str2, getByteOrder()), getIccTagType(read4Bytes15));
                    i3++;
                    read4Bytes14 = i4;
                    str = str3;
                    str2 = str2;
                    i = i6;
                    i2 = i5;
                } catch (Exception e) {
                    e = e;
                    LOGGER.log(Level.SEVERE, e.getMessage(), (Throwable) e);
                    return null;
                }
            }
            int i7 = i2;
            int i8 = i;
            while (cachingInputStream.read() >= 0) {
            }
            byte[] cache = cachingInputStream.getCache();
            if (cache.length < read4Bytes) {
                throw new IOException("Couldn't read ICC Profile.");
            }
            IccProfileInfo iccProfileInfo = new IccProfileInfo(cache, read4Bytes, read4Bytes2, read4Bytes3, read4Bytes4, read4Bytes5, read4Bytes6, read4Bytes7, read4Bytes8, read4Bytes9, read4Bytes10, read4Bytes11, i8, i7, null, iccTagArr);
            Logger logger2 = LOGGER;
            if (logger2.isLoggable(Level.FINEST)) {
                logger2.finest("issRGB: " + iccProfileInfo.issRGB());
            }
            return iccProfileInfo;
        } catch (Exception e2) {
            e = e2;
        }
    }

    private IccTagType getIccTagType(int i) {
        IccTagTypes[] values;
        for (IccTagTypes iccTagTypes : IccTagTypes.values()) {
            if (iccTagTypes.getSignature() == i) {
                return iccTagTypes;
            }
        }
        return null;
    }

    public boolean issRGB(ICC_Profile iCC_Profile) throws IOException {
        return issRGB(new ByteSourceArray(iCC_Profile.getData()));
    }

    public boolean issRGB(byte[] bArr) throws IOException {
        return issRGB(new ByteSourceArray(bArr));
    }

    public boolean issRGB(File file) throws IOException {
        return issRGB(new ByteSourceFile(file));
    }

    public boolean issRGB(ByteSource byteSource) throws IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            BinaryFunctions.read4Bytes("ProfileSize", inputStream, "Not a Valid ICC Profile", getByteOrder());
            BinaryFunctions.skipBytes(inputStream, 20L);
            BinaryFunctions.skipBytes(inputStream, 12L, "Not a Valid ICC Profile");
            BinaryFunctions.skipBytes(inputStream, 12L);
            int read4Bytes = BinaryFunctions.read4Bytes("ProfileFileSignature", inputStream, "Not a Valid ICC Profile", getByteOrder());
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("DeviceManufacturer", read4Bytes);
            }
            int read4Bytes2 = BinaryFunctions.read4Bytes("DeviceModel", inputStream, "Not a Valid ICC Profile", getByteOrder());
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("DeviceModel", read4Bytes2);
            }
            boolean z = read4Bytes == 1229275936 && read4Bytes2 == 1934772034;
            if (inputStream != null) {
                inputStream.close();
            }
            return z;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
