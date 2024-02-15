package org.apache.commons.imaging.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class SosSegment extends Segment {
    private static final Logger LOGGER = Logger.getLogger(SosSegment.class.getName());
    private final Component[] components;
    public final int endOfSpectralSelection;
    public final int numberOfComponents;
    public final int startOfSpectralSelection;
    public final int successiveApproximationBitHigh;
    public final int successiveApproximationBitLow;

    /* loaded from: classes2.dex */
    public static class Component {
        public final int acCodingTableSelector;
        public final int dcCodingTableSelector;
        public final int scanComponentSelector;

        public Component(int i, int i2, int i3) {
            this.scanComponentSelector = i;
            this.dcCodingTableSelector = i2;
            this.acCodingTableSelector = i3;
        }
    }

    public SosSegment(int i, byte[] bArr) throws IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public SosSegment(int i, int i2, InputStream inputStream) throws IOException {
        super(i, i2);
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("SosSegment marker_length: " + i2);
        }
        int readByte = BinaryFunctions.readByte("number_of_components_in_scan", inputStream, "Not a Valid JPEG File");
        this.numberOfComponents = readByte;
        this.components = new Component[readByte];
        for (int i3 = 0; i3 < this.numberOfComponents; i3++) {
            byte readByte2 = BinaryFunctions.readByte("scanComponentSelector", inputStream, "Not a Valid JPEG File");
            byte readByte3 = BinaryFunctions.readByte("acDcEntropyCodingTableSelector", inputStream, "Not a Valid JPEG File");
            this.components[i3] = new Component(readByte2, (readByte3 >> 4) & 15, readByte3 & 15);
        }
        this.startOfSpectralSelection = BinaryFunctions.readByte("start_of_spectral_selection", inputStream, "Not a Valid JPEG File");
        this.endOfSpectralSelection = BinaryFunctions.readByte("end_of_spectral_selection", inputStream, "Not a Valid JPEG File");
        byte readByte4 = BinaryFunctions.readByte("successive_approximation_bit_position", inputStream, "Not a Valid JPEG File");
        this.successiveApproximationBitHigh = (readByte4 >> 4) & 15;
        this.successiveApproximationBitLow = readByte4 & 15;
    }

    public Component[] getComponents() {
        return (Component[]) this.components.clone();
    }

    public Component getComponents(int i) {
        return this.components[i];
    }

    @Override // org.apache.commons.imaging.formats.jpeg.segments.Segment
    public String getDescription() {
        return "SOS (" + getSegmentType() + ")";
    }
}
