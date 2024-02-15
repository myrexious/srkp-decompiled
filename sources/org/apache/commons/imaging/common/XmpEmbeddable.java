package org.apache.commons.imaging.common;

import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;

/* loaded from: classes2.dex */
public interface XmpEmbeddable {
    String getXmpXml(ByteSource byteSource, XmpImagingParameters xmpImagingParameters) throws ImageReadException, IOException;
}
