package org.apache.commons.imaging.icc;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public class IccProfileInfo {
    private static final Logger LOGGER = Logger.getLogger(IccProfileInfo.class.getName());
    public final int cmmTypeSignature;
    public final int colorSpace;
    private final byte[] data;
    public final int deviceManufacturer;
    public final int deviceModel;
    public final int primaryPlatformSignature;
    public final int profileConnectionSpace;
    public final int profileCreatorSignature;
    public final int profileDeviceClassSignature;
    public final int profileFileSignature;
    private final byte[] profileId;
    public final int profileSize;
    public final int profileVersion;
    public final int renderingIntent;
    private final IccTag[] tags;
    public final int variousFlags;

    public IccProfileInfo(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, byte[] bArr2, IccTag[] iccTagArr) {
        this.data = bArr;
        this.profileSize = i;
        this.cmmTypeSignature = i2;
        this.profileVersion = i3;
        this.profileDeviceClassSignature = i4;
        this.colorSpace = i5;
        this.profileConnectionSpace = i6;
        this.profileFileSignature = i7;
        this.primaryPlatformSignature = i8;
        this.variousFlags = i9;
        this.deviceManufacturer = i10;
        this.deviceModel = i11;
        this.renderingIntent = i12;
        this.profileCreatorSignature = i13;
        this.profileId = bArr2;
        this.tags = iccTagArr;
    }

    public byte[] getData() {
        return (byte[]) this.data.clone();
    }

    public byte[] getProfileId() {
        return (byte[]) this.profileId.clone();
    }

    public IccTag[] getTags() {
        return this.tags;
    }

    public boolean issRGB() {
        return this.deviceManufacturer == 1229275936 && this.deviceModel == 1934772034;
    }

    private void printCharQuad(PrintWriter printWriter, String str, int i) {
        printWriter.println(str + ": '" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 0) & 255)) + OperatorName.SHOW_TEXT_LINE);
    }

    public void dump(String str) {
        LOGGER.fine(toString());
    }

    public String toString() {
        try {
            return toString("");
        } catch (Exception unused) {
            return "IccProfileInfo: Error";
        }
    }

    public String toString(String str) throws ImageReadException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println(str + ": data length: " + this.data.length);
        printCharQuad(printWriter, str + ": ProfileDeviceClassSignature", this.profileDeviceClassSignature);
        printCharQuad(printWriter, str + ": CMMTypeSignature", this.cmmTypeSignature);
        printCharQuad(printWriter, str + ": ProfileDeviceClassSignature", this.profileDeviceClassSignature);
        printCharQuad(printWriter, str + ": ColorSpace", this.colorSpace);
        printCharQuad(printWriter, str + ": ProfileConnectionSpace", this.profileConnectionSpace);
        printCharQuad(printWriter, str + ": ProfileFileSignature", this.profileFileSignature);
        printCharQuad(printWriter, str + ": PrimaryPlatformSignature", this.primaryPlatformSignature);
        printCharQuad(printWriter, str + ": ProfileFileSignature", this.profileFileSignature);
        printCharQuad(printWriter, str + ": DeviceManufacturer", this.deviceManufacturer);
        printCharQuad(printWriter, str + ": DeviceModel", this.deviceModel);
        printCharQuad(printWriter, str + ": RenderingIntent", this.renderingIntent);
        printCharQuad(printWriter, str + ": ProfileCreatorSignature", this.profileCreatorSignature);
        int i = 0;
        while (true) {
            IccTag[] iccTagArr = this.tags;
            if (i < iccTagArr.length) {
                iccTagArr[i].dump(printWriter, "\t" + i + ": ");
                i++;
            } else {
                printWriter.println(str + ": issRGB: " + issRGB());
                printWriter.flush();
                return stringWriter.getBuffer().toString();
            }
        }
    }
}
