package org.apache.commons.imaging.formats.jpeg.iptc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.ImagingConstants;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.jpeg.xmp.JpegRewriter;

/* loaded from: classes2.dex */
public class JpegIptcRewriter extends JpegRewriter {
    public void removeIPTC(File file, OutputStream outputStream) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(file, outputStream, false);
    }

    public void removeIPTC(File file, OutputStream outputStream, boolean z) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(new ByteSourceFile(file), outputStream, z);
    }

    public void removeIPTC(byte[] bArr, OutputStream outputStream) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(bArr, outputStream, false);
    }

    public void removeIPTC(byte[] bArr, OutputStream outputStream, boolean z) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(new ByteSourceArray(bArr), outputStream, z);
    }

    public void removeIPTC(InputStream inputStream, OutputStream outputStream) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(inputStream, outputStream, false);
    }

    public void removeIPTC(InputStream inputStream, OutputStream outputStream, boolean z) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(new ByteSourceInputStream(inputStream, null), outputStream, z);
    }

    public void removeIPTC(ByteSource byteSource, OutputStream outputStream) throws ImageReadException, IOException, ImageWriteException {
        removeIPTC(byteSource, outputStream, false);
    }

    public void removeIPTC(ByteSource byteSource, OutputStream outputStream, boolean z) throws ImageReadException, IOException, ImageWriteException {
        List<JpegRewriter.JFIFPiece> list = analyzeJFIF(byteSource).pieces;
        List findPhotoshopApp13Segments = findPhotoshopApp13Segments(list);
        if (findPhotoshopApp13Segments.size() > 1) {
            throw new ImageReadException("Image contains more than one Photoshop App13 segment.");
        }
        List<? extends JpegRewriter.JFIFPiece> removePhotoshopApp13Segments = removePhotoshopApp13Segments(list);
        if (!z && findPhotoshopApp13Segments.size() == 1) {
            JpegRewriter.JFIFPieceSegment jFIFPieceSegment = (JpegRewriter.JFIFPieceSegment) findPhotoshopApp13Segments.get(0);
            removePhotoshopApp13Segments.add(list.indexOf(jFIFPieceSegment), new JpegRewriter.JFIFPieceSegment(jFIFPieceSegment.marker, new IptcParser().writePhotoshopApp13Segment(new PhotoshopApp13Data(new ArrayList(), new IptcParser().parsePhotoshopSegment(jFIFPieceSegment.getSegmentData(), new JpegImagingParameters()).getNonIptcBlocks()))));
        }
        writeSegments(outputStream, removePhotoshopApp13Segments);
    }

    public void writeIPTC(byte[] bArr, OutputStream outputStream, PhotoshopApp13Data photoshopApp13Data) throws ImageReadException, IOException, ImageWriteException {
        writeIPTC(new ByteSourceArray(bArr), outputStream, photoshopApp13Data);
    }

    public void writeIPTC(InputStream inputStream, OutputStream outputStream, PhotoshopApp13Data photoshopApp13Data) throws ImageReadException, IOException, ImageWriteException {
        writeIPTC(new ByteSourceInputStream(inputStream, null), outputStream, photoshopApp13Data);
    }

    public void writeIPTC(File file, OutputStream outputStream, PhotoshopApp13Data photoshopApp13Data) throws ImageReadException, IOException, ImageWriteException {
        writeIPTC(new ByteSourceFile(file), outputStream, photoshopApp13Data);
    }

    public void writeIPTC(ByteSource byteSource, OutputStream outputStream, PhotoshopApp13Data photoshopApp13Data) throws ImageReadException, IOException, ImageWriteException {
        List<JpegRewriter.JFIFPiece> list = analyzeJFIF(byteSource).pieces;
        if (findPhotoshopApp13Segments(list).size() > 1) {
            throw new ImageReadException("Image contains more than one Photoshop App13 segment.");
        }
        List removePhotoshopApp13Segments = removePhotoshopApp13Segments(list);
        List<IptcBlock> nonIptcBlocks = photoshopApp13Data.getNonIptcBlocks();
        nonIptcBlocks.add(new IptcBlock(IptcConstants.IMAGE_RESOURCE_BLOCK_IPTC_DATA, ImagingConstants.EMPTY_BYTE_ARRAY, new IptcParser().writeIPTCBlock(photoshopApp13Data.getRecords())));
        writeSegments(outputStream, insertAfterLastAppSegments(removePhotoshopApp13Segments, Arrays.asList(new JpegRewriter.JFIFPieceSegment(JpegConstants.JPEG_APP13_MARKER, new IptcParser().writePhotoshopApp13Segment(new PhotoshopApp13Data(photoshopApp13Data.getRecords(), nonIptcBlocks))))));
    }
}
