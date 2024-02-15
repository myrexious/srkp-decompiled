package org.apache.commons.imaging.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImagingParameters;
import org.apache.commons.imaging.formats.jpeg.iptc.IptcParser;
import org.apache.commons.imaging.formats.jpeg.iptc.PhotoshopApp13Data;

/* loaded from: classes2.dex */
public class App13Segment extends AppnSegment {
    public App13Segment(int i, byte[] bArr) throws IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public App13Segment(int i, int i2, InputStream inputStream) throws IOException {
        super(i, i2, inputStream);
    }

    public boolean isPhotoshopJpegSegment() {
        return new IptcParser().isPhotoshopJpegSegment(getSegmentData());
    }

    public PhotoshopApp13Data parsePhotoshopSegment(ImagingParameters imagingParameters) throws ImageReadException, IOException {
        if (isPhotoshopJpegSegment()) {
            return new IptcParser().parsePhotoshopSegment(getSegmentData(), imagingParameters);
        }
        return null;
    }
}
