package org.tensorflow.lite.support.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.zip.ZipException;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.tensorflow.lite.support.metadata.schema.ModelMetadata;
import org.tensorflow.lite.support.metadata.schema.TensorMetadata;

/* loaded from: classes4.dex */
public class MetadataExtractor {
    @Nullable
    private final ModelMetadataInfo metadataInfo;
    private final ModelInfo modelInfo;
    @Nullable
    private final ZipFile zipFile;

    public MetadataExtractor(ByteBuffer byteBuffer) throws IOException {
        ModelInfo modelInfo = new ModelInfo(byteBuffer);
        this.modelInfo = modelInfo;
        ByteBuffer metadataBuffer = modelInfo.getMetadataBuffer();
        if (metadataBuffer != null) {
            ModelMetadataInfo modelMetadataInfo = new ModelMetadataInfo(metadataBuffer);
            this.metadataInfo = modelMetadataInfo;
            if (!isMinimumParserVersionSatisfied()) {
                System.err.printf("<Warning> Some fields in the metadata belong to a future schema. The minimum parser version required is %s, but the version of the current metadata parser is %s", modelMetadataInfo.getMininumParserVersion(), MetadataParser.VERSION);
            }
            Preconditions.checkArgument(modelInfo.getInputTensorCount() == modelMetadataInfo.getInputTensorCount(), String.format("The number of input tensors in the model is %d. The number of input tensors that recorded in the metadata is %d. These two values does not match.", Integer.valueOf(modelInfo.getInputTensorCount()), Integer.valueOf(modelMetadataInfo.getInputTensorCount())));
            Preconditions.checkArgument(modelInfo.getOutputTensorCount() == modelMetadataInfo.getOutputTensorCount(), String.format("The number of output tensors in the model is %d. The number of output tensors that recorded in the metadata is %d. These two values does not match.", Integer.valueOf(modelInfo.getOutputTensorCount()), Integer.valueOf(modelMetadataInfo.getOutputTensorCount())));
        } else {
            this.metadataInfo = null;
        }
        this.zipFile = createZipFile(byteBuffer);
    }

    /* loaded from: classes4.dex */
    public static class QuantizationParams {
        private final float scale;
        private final int zeroPoint;

        public QuantizationParams(float f, int i) {
            this.scale = f;
            this.zeroPoint = i;
        }

        public float getScale() {
            return this.scale;
        }

        public int getZeroPoint() {
            return this.zeroPoint;
        }
    }

    public boolean hasMetadata() {
        return this.metadataInfo != null;
    }

    public InputStream getAssociatedFile(String str) {
        assertZipFile();
        return this.zipFile.getRawInputStream(str);
    }

    public Set<String> getAssociatedFileNames() {
        assertZipFile();
        return this.zipFile.getFileNames();
    }

    public int getInputTensorCount() {
        return this.modelInfo.getInputTensorCount();
    }

    @Nullable
    public TensorMetadata getInputTensorMetadata(int i) {
        assertMetadataInfo();
        return this.metadataInfo.getInputTensorMetadata(i);
    }

    public QuantizationParams getInputTensorQuantizationParams(int i) {
        return this.modelInfo.getQuantizationParams(this.modelInfo.getInputTensor(i));
    }

    public int[] getInputTensorShape(int i) {
        return this.modelInfo.getInputTensorShape(i);
    }

    public byte getInputTensorType(int i) {
        return this.modelInfo.getInputTensorType(i);
    }

    public ModelMetadata getModelMetadata() {
        assertMetadataInfo();
        return this.metadataInfo.getModelMetadata();
    }

    public int getOutputTensorCount() {
        return this.modelInfo.getOutputTensorCount();
    }

    @Nullable
    public TensorMetadata getOutputTensorMetadata(int i) {
        assertMetadataInfo();
        return this.metadataInfo.getOutputTensorMetadata(i);
    }

    public QuantizationParams getOutputTensorQuantizationParams(int i) {
        return this.modelInfo.getQuantizationParams(this.modelInfo.getOutputTensor(i));
    }

    public int[] getOutputTensorShape(int i) {
        return this.modelInfo.getOutputTensorShape(i);
    }

    public byte getOutputTensorType(int i) {
        return this.modelInfo.getOutputTensorType(i);
    }

    public final boolean isMinimumParserVersionSatisfied() {
        String mininumParserVersion = this.metadataInfo.getMininumParserVersion();
        return mininumParserVersion == null || compareVersions(mininumParserVersion, MetadataParser.VERSION) <= 0;
    }

    private void assertMetadataInfo() {
        if (this.metadataInfo == null) {
            throw new IllegalStateException("This model does not contain model metadata.");
        }
    }

    private void assertZipFile() {
        if (this.zipFile == null) {
            throw new IllegalStateException("This model does not contain associated files, and is not a Zip file.");
        }
    }

    @Nullable
    private static ZipFile createZipFile(ByteBuffer byteBuffer) throws IOException {
        try {
            return ZipFile.createFrom(new ByteBufferChannel(byteBuffer));
        } catch (ZipException unused) {
            return null;
        }
    }

    private static int compareVersions(String str, String str2) {
        String[] split = str.split("\\.", 0);
        String[] split2 = str2.split("\\.", 0);
        int max = Math.max(split.length, split2.length);
        int i = 0;
        while (i < max) {
            int compareTo = Integer.valueOf(i < split.length ? Integer.parseInt(split[i]) : 0).compareTo(Integer.valueOf(i < split2.length ? Integer.parseInt(split2[i]) : 0));
            if (compareTo != 0) {
                return compareTo;
            }
            i++;
        }
        return 0;
    }
}
