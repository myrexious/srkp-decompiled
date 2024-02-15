package org.tensorflow.lite.support.metadata;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.tensorflow.lite.schema.Metadata;
import org.tensorflow.lite.schema.Model;
import org.tensorflow.lite.schema.QuantizationParameters;
import org.tensorflow.lite.schema.SubGraph;
import org.tensorflow.lite.schema.Tensor;
import org.tensorflow.lite.support.metadata.MetadataExtractor;

/* loaded from: classes4.dex */
final class ModelInfo {
    static final String METADATA_FIELD_NAME = "TFLITE_METADATA";
    private final List<Tensor> inputTensors;
    private final Model model;
    private final List<Tensor> outputTensors;

    public ModelInfo(ByteBuffer byteBuffer) {
        assertTFLiteModel(byteBuffer);
        Model rootAsModel = Model.getRootAsModel(byteBuffer);
        this.model = rootAsModel;
        Preconditions.checkArgument(rootAsModel.subgraphsLength() > 0, "The model does not contain any subgraph.");
        this.inputTensors = getInputTensors(rootAsModel);
        this.outputTensors = getOutputTensors(rootAsModel);
    }

    @Nullable
    public Tensor getInputTensor(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.inputTensors.size(), "The inputIndex specified is invalid.");
        return this.inputTensors.get(i);
    }

    public int getInputTensorCount() {
        return this.inputTensors.size();
    }

    public int[] getInputTensorShape(int i) {
        return getShape(getInputTensor(i));
    }

    public byte getInputTensorType(int i) {
        return getInputTensor(i).type();
    }

    @Nullable
    public ByteBuffer getMetadataBuffer() {
        if (this.model.metadataLength() == 0) {
            return null;
        }
        for (int i = 0; i < this.model.metadataLength(); i++) {
            Metadata metadata = this.model.metadata(i);
            if (METADATA_FIELD_NAME.equals(metadata.name())) {
                return this.model.buffers((int) metadata.buffer()).dataAsByteBuffer();
            }
        }
        return null;
    }

    @Nullable
    public Tensor getOutputTensor(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.outputTensors.size(), "The outputIndex specified is invalid.");
        return this.outputTensors.get(i);
    }

    public int getOutputTensorCount() {
        return this.outputTensors.size();
    }

    public int[] getOutputTensorShape(int i) {
        return getShape(getOutputTensor(i));
    }

    public byte getOutputTensorType(int i) {
        return getOutputTensor(i).type();
    }

    public MetadataExtractor.QuantizationParams getQuantizationParams(Tensor tensor) {
        Preconditions.checkNotNull(tensor, "Tensor cannot be null.");
        QuantizationParameters quantization = tensor.quantization();
        if (quantization == null) {
            return new MetadataExtractor.QuantizationParams(0.0f, 0);
        }
        Preconditions.checkArgument(quantization.scaleLength() <= 1, "Input and output tensors do not support per-channel quantization.");
        Preconditions.checkArgument(quantization.zeroPointLength() <= 1, "Input and output tensors do not support per-channel quantization.");
        return new MetadataExtractor.QuantizationParams(quantization.scale(0), (int) quantization.zeroPoint(0));
    }

    private static void assertTFLiteModel(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer, "Model flatbuffer cannot be null.");
        Preconditions.checkArgument(Model.ModelBufferHasIdentifier(byteBuffer), "The identifier of the model is invalid. The buffer may not be a valid TFLite model flatbuffer.");
    }

    private static int[] getShape(Tensor tensor) {
        Preconditions.checkNotNull(tensor, "Tensor cannot be null.");
        int shapeLength = tensor.shapeLength();
        int[] iArr = new int[shapeLength];
        for (int i = 0; i < shapeLength; i++) {
            iArr[i] = tensor.shape(i);
        }
        return iArr;
    }

    private static List<Tensor> getInputTensors(Model model) {
        SubGraph subgraphs = model.subgraphs(0);
        int inputsLength = subgraphs.inputsLength();
        ArrayList arrayList = new ArrayList(inputsLength);
        for (int i = 0; i < inputsLength; i++) {
            arrayList.add(subgraphs.tensors(subgraphs.inputs(i)));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static List<Tensor> getOutputTensors(Model model) {
        SubGraph subgraphs = model.subgraphs(0);
        int outputsLength = subgraphs.outputsLength();
        ArrayList arrayList = new ArrayList(outputsLength);
        for (int i = 0; i < outputsLength; i++) {
            arrayList.add(subgraphs.tensors(subgraphs.outputs(i)));
        }
        return Collections.unmodifiableList(arrayList);
    }
}
