package org.apache.commons.imaging.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;

/* loaded from: classes2.dex */
public class SofnSegment extends Segment {
    private static final Logger LOGGER = Logger.getLogger(SofnSegment.class.getName());
    private final Component[] components;
    public final int height;
    public final int numberOfComponents;
    public final int precision;
    public final int width;

    /* loaded from: classes2.dex */
    public static class Component {
        public final int componentIdentifier;
        public final int horizontalSamplingFactor;
        public final int quantTabDestSelector;
        public final int verticalSamplingFactor;

        public Component(int i, int i2, int i3, int i4) {
            this.componentIdentifier = i;
            this.horizontalSamplingFactor = i2;
            this.verticalSamplingFactor = i3;
            this.quantTabDestSelector = i4;
        }
    }

    public SofnSegment(int i, byte[] bArr) throws IOException, ImageReadException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public SofnSegment(int i, int i2, InputStream inputStream) throws IOException, ImageReadException {
        super(i, i2);
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("SOF0Segment marker_length: " + i2);
        }
        this.precision = BinaryFunctions.readByte("Data_precision", inputStream, "Not a Valid JPEG File");
        this.height = BinaryFunctions.read2Bytes("Image_height", inputStream, "Not a Valid JPEG File", getByteOrder());
        this.width = BinaryFunctions.read2Bytes("Image_Width", inputStream, "Not a Valid JPEG File", getByteOrder());
        int readByte = BinaryFunctions.readByte("Number_of_components", inputStream, "Not a Valid JPEG File");
        this.numberOfComponents = readByte;
        if (readByte < 0) {
            throw new ImageReadException("The number of components in a SOF0Segment cannot be less than 0!");
        }
        this.components = new Component[readByte];
        for (int i3 = 0; i3 < this.numberOfComponents; i3++) {
            byte readByte2 = BinaryFunctions.readByte("ComponentIdentifier", inputStream, "Not a Valid JPEG File");
            byte readByte3 = BinaryFunctions.readByte("SamplingFactors", inputStream, "Not a Valid JPEG File");
            this.components[i3] = new Component(readByte2, (readByte3 >> 4) & 15, readByte3 & 15, BinaryFunctions.readByte("QuantTabDestSel", inputStream, "Not a Valid JPEG File"));
        }
    }

    public Component[] getComponents() {
        return (Component[]) this.components.clone();
    }

    public Component getComponents(int i) {
        return this.components[i];
    }

    @Override // org.apache.commons.imaging.formats.jpeg.segments.Segment
    public String getDescription() {
        return "SOFN (SOF" + (this.marker - JpegConstants.SOF0_MARKER) + ") (" + getSegmentType() + ")";
    }
}
