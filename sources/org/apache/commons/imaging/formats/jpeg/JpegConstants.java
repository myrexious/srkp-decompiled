package org.apache.commons.imaging.formats.jpeg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.common.BinaryConstant;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public final class JpegConstants {
    public static final int JFIF_MARKER = 65504;
    public static final int JPEG_APP0 = 224;
    public static final int JPEG_APP0_MARKER = 65504;
    public static final int MAX_SEGMENT_SIZE = 65535;
    public static final BinaryConstant JFIF0_SIGNATURE = new BinaryConstant(new byte[]{BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.RangeOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, 0});
    public static final BinaryConstant JFIF0_SIGNATURE_ALTERNATIVE = new BinaryConstant(new byte[]{BuiltinOptions.ResizeNearestNeighborOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.RangeOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, 32});
    public static final BinaryConstant EXIF_IDENTIFIER_CODE = new BinaryConstant(new byte[]{BuiltinOptions.BidirectionalSequenceLSTMOptions, 120, BuiltinOptions.Rfft2dOptions, BuiltinOptions.CumsumOptions});
    public static final BinaryConstant XMP_IDENTIFIER = new BinaryConstant(new byte[]{BuiltinOptions.BroadcastToOptions, BuiltinOptions.GeluOptions, BuiltinOptions.GeluOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.FakeQuantOptions, BuiltinOptions.SelectOptions, BuiltinOptions.SelectOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.ScatterNdOptions, BuiltinOptions.SegmentSumOptions, BuiltinOptions.VarHandleOptions, BuiltinOptions.SelectV2Options, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.LessEqualOptions, BuiltinOptions.DensifyOptions, BuiltinOptions.VarHandleOptions, BuiltinOptions.HashtableImportOptions, BuiltinOptions.SelectOptions, 120, BuiltinOptions.ScatterNdOptions, BuiltinOptions.ReadVariableOptions, BuiltinOptions.SelectOptions, 49, BuiltinOptions.LessEqualOptions, BuiltinOptions.SliceOptions, BuiltinOptions.SelectOptions, 0});
    public static final BinaryConstant SOI = new BinaryConstant(new byte[]{-1, -40});
    public static final BinaryConstant EOI = new BinaryConstant(new byte[]{-1, -39});
    public static final int JPEG_APP1_MARKER = 65505;
    public static final int JPEG_APP2_MARKER = 65506;
    public static final int JPEG_APP13_MARKER = 65517;
    public static final int JPEG_APP14_MARKER = 65518;
    public static final int JPEG_APP15_MARKER = 65519;
    public static final int SOF0_MARKER = 65472;
    public static final int SOF1_MARKER = 65473;
    public static final int SOF2_MARKER = 65474;
    public static final int SOF3_MARKER = 65475;
    public static final int DHT_MARKER = 65476;
    public static final int SOF5_MARKER = 65477;
    public static final int SOF6_MARKER = 65478;
    public static final int SOF7_MARKER = 65479;
    public static final int SOF8_MARKER = 65480;
    public static final int SOF9_MARKER = 65481;
    public static final int SOF10_MARKER = 65482;
    public static final int SOF11_MARKER = 65483;
    public static final int DAC_MARKER = 65484;
    public static final int SOF13_MARKER = 65485;
    public static final int SOF14_MARKER = 65486;
    public static final int SOF15_MARKER = 65487;
    public static final int EOI_MARKER = 65497;
    public static final int SOS_MARKER = 65498;
    public static final int DQT_MARKER = 65499;
    public static final int DNL_MARKER = 65500;
    public static final int COM_MARKER = 65534;
    public static final int DRI_MARKER = 65501;
    public static final int RST0_MARKER = 65488;
    public static final int RST1_MARKER = 65489;
    public static final int RST2_MARKER = 65490;
    public static final int RST3_MARKER = 65491;
    public static final int RST4_MARKER = 65492;
    public static final int RST5_MARKER = 65493;
    public static final int RST6_MARKER = 65494;
    public static final int RST7_MARKER = 65495;
    public static final List<Integer> MARKERS = Collections.unmodifiableList(Arrays.asList(224, 65504, Integer.valueOf((int) JPEG_APP1_MARKER), Integer.valueOf((int) JPEG_APP2_MARKER), Integer.valueOf((int) JPEG_APP13_MARKER), Integer.valueOf((int) JPEG_APP14_MARKER), Integer.valueOf((int) JPEG_APP15_MARKER), 65504, Integer.valueOf((int) SOF0_MARKER), Integer.valueOf((int) SOF1_MARKER), Integer.valueOf((int) SOF2_MARKER), Integer.valueOf((int) SOF3_MARKER), Integer.valueOf((int) DHT_MARKER), Integer.valueOf((int) SOF5_MARKER), Integer.valueOf((int) SOF6_MARKER), Integer.valueOf((int) SOF7_MARKER), Integer.valueOf((int) SOF8_MARKER), Integer.valueOf((int) SOF9_MARKER), Integer.valueOf((int) SOF10_MARKER), Integer.valueOf((int) SOF11_MARKER), Integer.valueOf((int) DAC_MARKER), Integer.valueOf((int) SOF13_MARKER), Integer.valueOf((int) SOF14_MARKER), Integer.valueOf((int) SOF15_MARKER), Integer.valueOf((int) EOI_MARKER), Integer.valueOf((int) SOS_MARKER), Integer.valueOf((int) DQT_MARKER), Integer.valueOf((int) DNL_MARKER), Integer.valueOf((int) COM_MARKER), Integer.valueOf((int) DRI_MARKER), Integer.valueOf((int) RST0_MARKER), Integer.valueOf((int) RST1_MARKER), Integer.valueOf((int) RST2_MARKER), Integer.valueOf((int) RST3_MARKER), Integer.valueOf((int) RST4_MARKER), Integer.valueOf((int) RST5_MARKER), Integer.valueOf((int) RST6_MARKER), Integer.valueOf((int) RST7_MARKER)));
    public static final BinaryConstant ICC_PROFILE_LABEL = new BinaryConstant(new byte[]{BuiltinOptions.RangeOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.NonMaxSuppressionV4Options, 80, BuiltinOptions.AddNOptions, BuiltinOptions.SplitVOptions, BuiltinOptions.BidirectionalSequenceRNNOptions, BuiltinOptions.RangeOptions, BuiltinOptions.SquaredDifferenceOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, 0});
    public static final BinaryConstant PHOTOSHOP_IDENTIFICATION_STRING = new BinaryConstant(new byte[]{80, BuiltinOptions.BroadcastToOptions, BuiltinOptions.VarHandleOptions, BuiltinOptions.GeluOptions, BuiltinOptions.VarHandleOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.BroadcastToOptions, BuiltinOptions.VarHandleOptions, BuiltinOptions.ReadVariableOptions, 32, 51, BuiltinOptions.LessEqualOptions, BuiltinOptions.SliceOptions, 0});
    public static final int CONST_8BIM = BinaryFunctions.charsToQuad('8', 'B', 'I', 'M');

    private JpegConstants() {
    }
}
