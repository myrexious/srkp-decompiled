package org.tensorflow.lite.support.metadata;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.tensorflow.lite.support.metadata.schema.ModelMetadata;
import org.tensorflow.lite.support.metadata.schema.SubGraphMetadata;
import org.tensorflow.lite.support.metadata.schema.TensorMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class ModelMetadataInfo {
    private final List<TensorMetadata> inputsMetadata;
    private final String minVersion;
    private final ModelMetadata modelMetadata;
    private final List<TensorMetadata> outputsMetadata;

    public ModelMetadataInfo(ByteBuffer byteBuffer) {
        assertTFLiteMetadata(byteBuffer);
        ModelMetadata rootAsModelMetadata = ModelMetadata.getRootAsModelMetadata(byteBuffer);
        this.modelMetadata = rootAsModelMetadata;
        Preconditions.checkArgument(rootAsModelMetadata.subgraphMetadataLength() > 0, "The metadata flatbuffer does not contain any subgraph metadata.");
        this.inputsMetadata = getInputsMetadata(rootAsModelMetadata);
        this.outputsMetadata = getOutputsMetadata(rootAsModelMetadata);
        this.minVersion = rootAsModelMetadata.minParserVersion();
    }

    public int getInputTensorCount() {
        return this.inputsMetadata.size();
    }

    @Nullable
    public TensorMetadata getInputTensorMetadata(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.inputsMetadata.size(), "The inputIndex specified is invalid.");
        return this.inputsMetadata.get(i);
    }

    @Nullable
    public String getMininumParserVersion() {
        return this.minVersion;
    }

    public ModelMetadata getModelMetadata() {
        return this.modelMetadata;
    }

    public int getOutputTensorCount() {
        return this.outputsMetadata.size();
    }

    @Nullable
    public TensorMetadata getOutputTensorMetadata(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.outputsMetadata.size(), "The outputIndex specified is invalid.");
        return this.outputsMetadata.get(i);
    }

    private static void assertTFLiteMetadata(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer, "Metadata flatbuffer cannot be null.");
        Preconditions.checkArgument(ModelMetadata.ModelMetadataBufferHasIdentifier(byteBuffer), "The identifier of the metadata is invalid. The buffer may not be a valid TFLite metadata flatbuffer.");
    }

    private static List<TensorMetadata> getInputsMetadata(ModelMetadata modelMetadata) {
        SubGraphMetadata subgraphMetadata = modelMetadata.subgraphMetadata(0);
        int inputTensorMetadataLength = subgraphMetadata.inputTensorMetadataLength();
        ArrayList arrayList = new ArrayList(inputTensorMetadataLength);
        for (int i = 0; i < inputTensorMetadataLength; i++) {
            arrayList.add(subgraphMetadata.inputTensorMetadata(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static List<TensorMetadata> getOutputsMetadata(ModelMetadata modelMetadata) {
        SubGraphMetadata subgraphMetadata = modelMetadata.subgraphMetadata(0);
        int outputTensorMetadataLength = subgraphMetadata.outputTensorMetadataLength();
        ArrayList arrayList = new ArrayList(outputTensorMetadataLength);
        for (int i = 0; i < outputTensorMetadataLength; i++) {
            arrayList.add(subgraphMetadata.outputTensorMetadata(i));
        }
        return Collections.unmodifiableList(arrayList);
    }
}
