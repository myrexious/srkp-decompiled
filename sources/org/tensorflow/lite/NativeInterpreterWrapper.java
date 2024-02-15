package org.tensorflow.lite;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.tensorflow.lite.InterpreterApi;
import org.tensorflow.lite.InterpreterImpl;
import org.tensorflow.lite.nnapi.NnApiDelegate;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class NativeInterpreterWrapper implements AutoCloseable {
    private static final int ERROR_BUFFER_SIZE = 512;
    private static final RuntimeFlavor RUNTIME_FLAVOR = RuntimeFlavor.APPLICATION;
    private long cancellationFlagHandle;
    private final List<Delegate> delegates;
    long errorHandle;
    private long inferenceDurationNanoseconds;
    private TensorImpl[] inputTensors;
    private Map<String, Integer> inputsIndexes;
    long interpreterHandle;
    private boolean isMemoryAllocated;
    private ByteBuffer modelByteBuffer;
    private long modelHandle;
    private boolean originalGraphHasUnresolvedFlexOp;
    private TensorImpl[] outputTensors;
    private Map<String, Integer> outputsIndexes;
    private final List<Delegate> ownedDelegates;
    private Map<String, NativeSignatureRunnerWrapper> signatureRunnerMap;

    private static native long allocateTensors(long interpreterHandle, long errorHandle);

    private static native void allowBufferHandleOutput(long interpreterHandle, boolean allow);

    private static native void allowFp16PrecisionForFp32(long interpreterHandle, boolean allow);

    private static native long createCancellationFlag(long interpreterHandle);

    private static native long createErrorReporter(int size);

    private static native long createInterpreter(long modelHandle, long errorHandle, int numThreads, boolean useXnnpack, List<Long> delegateHandles);

    private static native long createModel(String modelPathOrBuffer, long errorHandle);

    private static native long createModelWithBuffer(ByteBuffer modelBuffer, long errorHandle);

    private static native void delete(long errorHandle, long modelHandle, long interpreterHandle);

    private static native long deleteCancellationFlag(long cancellationFlagHandle);

    private static native int getExecutionPlanLength(long interpreterHandle);

    private static native int getInputCount(long interpreterHandle);

    private static native String[] getInputNames(long interpreterHandle);

    private static native int getInputTensorIndex(long interpreterHandle, int inputIdx);

    private static native int getOutputCount(long interpreterHandle);

    private static native String[] getOutputNames(long interpreterHandle);

    private static native int getOutputTensorIndex(long interpreterHandle, int outputIdx);

    private static native String[] getSignatureKeys(long interpreterHandle);

    private static native boolean hasUnresolvedFlexOp(long interpreterHandle);

    private static native boolean resizeInput(long interpreterHandle, long errorHandle, int inputIdx, int[] dims, boolean strict);

    private static native void run(long interpreterHandle, long errorHandle);

    private static native void setCancelled(long interpreterHandle, long cancellationFlagHandle, boolean value);

    NativeInterpreterWrapper(String modelPath) {
        this(modelPath, (InterpreterImpl.Options) null);
    }

    NativeInterpreterWrapper(ByteBuffer byteBuffer) {
        this(byteBuffer, (InterpreterImpl.Options) null);
    }

    public NativeInterpreterWrapper(String modelPath, InterpreterImpl.Options options) {
        this.cancellationFlagHandle = 0L;
        this.inferenceDurationNanoseconds = -1L;
        this.isMemoryAllocated = false;
        this.originalGraphHasUnresolvedFlexOp = false;
        this.delegates = new ArrayList();
        this.ownedDelegates = new ArrayList();
        TensorFlowLite.init();
        long createErrorReporter = createErrorReporter(512);
        init(createErrorReporter, createModel(modelPath, createErrorReporter), options);
    }

    public NativeInterpreterWrapper(ByteBuffer buffer, InterpreterImpl.Options options) {
        this.cancellationFlagHandle = 0L;
        this.inferenceDurationNanoseconds = -1L;
        this.isMemoryAllocated = false;
        this.originalGraphHasUnresolvedFlexOp = false;
        this.delegates = new ArrayList();
        this.ownedDelegates = new ArrayList();
        TensorFlowLite.init();
        if (buffer == null || (!(buffer instanceof MappedByteBuffer) && (!buffer.isDirect() || buffer.order() != ByteOrder.nativeOrder()))) {
            throw new IllegalArgumentException("Model ByteBuffer should be either a MappedByteBuffer of the model file, or a direct ByteBuffer using ByteOrder.nativeOrder() which contains bytes of model content.");
        }
        this.modelByteBuffer = buffer;
        long createErrorReporter = createErrorReporter(512);
        init(createErrorReporter, createModelWithBuffer(this.modelByteBuffer, createErrorReporter), options);
    }

    private void init(long errorHandle, long modelHandle, InterpreterImpl.Options options) {
        if (options == null) {
            options = new InterpreterImpl.Options();
        }
        if (options.getAccelerationConfig() != null) {
            options.getAccelerationConfig().apply(options);
        }
        this.errorHandle = errorHandle;
        this.modelHandle = modelHandle;
        ArrayList arrayList = new ArrayList();
        long createInterpreter = createInterpreter(modelHandle, errorHandle, options.getNumThreads(), options.getUseXNNPACK(), arrayList);
        this.interpreterHandle = createInterpreter;
        this.originalGraphHasUnresolvedFlexOp = hasUnresolvedFlexOp(createInterpreter);
        addDelegates(options);
        initDelegatesWithInterpreterFactory();
        arrayList.ensureCapacity(this.delegates.size());
        for (Delegate delegate : this.delegates) {
            arrayList.add(Long.valueOf(delegate.getNativeHandle()));
        }
        if (!arrayList.isEmpty()) {
            delete(0L, 0L, this.interpreterHandle);
            this.interpreterHandle = createInterpreter(modelHandle, errorHandle, options.getNumThreads(), options.getUseXNNPACK(), arrayList);
        }
        if (options.allowFp16PrecisionForFp32 != null) {
            allowFp16PrecisionForFp32(this.interpreterHandle, options.allowFp16PrecisionForFp32.booleanValue());
        }
        if (options.allowBufferHandleOutput != null) {
            allowBufferHandleOutput(this.interpreterHandle, options.allowBufferHandleOutput.booleanValue());
        }
        if (options.isCancellable()) {
            this.cancellationFlagHandle = createCancellationFlag(this.interpreterHandle);
        }
        this.inputTensors = new TensorImpl[getInputCount(this.interpreterHandle)];
        this.outputTensors = new TensorImpl[getOutputCount(this.interpreterHandle)];
        if (options.allowFp16PrecisionForFp32 != null) {
            allowFp16PrecisionForFp32(this.interpreterHandle, options.allowFp16PrecisionForFp32.booleanValue());
        }
        if (options.allowBufferHandleOutput != null) {
            allowBufferHandleOutput(this.interpreterHandle, options.allowBufferHandleOutput.booleanValue());
        }
        allocateTensors(this.interpreterHandle, errorHandle);
        this.isMemoryAllocated = true;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        int i = 0;
        while (true) {
            TensorImpl[] tensorImplArr = this.inputTensors;
            if (i >= tensorImplArr.length) {
                break;
            }
            TensorImpl tensorImpl = tensorImplArr[i];
            if (tensorImpl != null) {
                tensorImpl.close();
                this.inputTensors[i] = null;
            }
            i++;
        }
        int i2 = 0;
        while (true) {
            TensorImpl[] tensorImplArr2 = this.outputTensors;
            if (i2 >= tensorImplArr2.length) {
                break;
            }
            TensorImpl tensorImpl2 = tensorImplArr2[i2];
            if (tensorImpl2 != null) {
                tensorImpl2.close();
                this.outputTensors[i2] = null;
            }
            i2++;
        }
        delete(this.errorHandle, this.modelHandle, this.interpreterHandle);
        deleteCancellationFlag(this.cancellationFlagHandle);
        this.errorHandle = 0L;
        this.modelHandle = 0L;
        this.interpreterHandle = 0L;
        this.cancellationFlagHandle = 0L;
        this.modelByteBuffer = null;
        this.inputsIndexes = null;
        this.outputsIndexes = null;
        this.isMemoryAllocated = false;
        this.delegates.clear();
        for (Delegate delegate : this.ownedDelegates) {
            delegate.close();
        }
        this.ownedDelegates.clear();
    }

    public void runSignature(Map<String, Object> inputs, Map<String, Object> outputs, String signatureKey) {
        this.inferenceDurationNanoseconds = -1L;
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("Input error: Inputs should not be null or empty.");
        }
        if (outputs == null) {
            throw new IllegalArgumentException("Input error: Outputs should not be null.");
        }
        NativeSignatureRunnerWrapper signatureRunnerWrapper = getSignatureRunnerWrapper(signatureKey);
        if (signatureRunnerWrapper.getSubgraphIndex() == 0) {
            Object[] objArr = new Object[inputs.size()];
            for (Map.Entry<String, Object> entry : inputs.entrySet()) {
                objArr[signatureRunnerWrapper.getInputIndex(entry.getKey())] = entry.getValue();
            }
            TreeMap treeMap = new TreeMap();
            for (Map.Entry<String, Object> entry2 : outputs.entrySet()) {
                treeMap.put(Integer.valueOf(signatureRunnerWrapper.getOutputIndex(entry2.getKey())), entry2.getValue());
            }
            run(objArr, treeMap);
            return;
        }
        for (Map.Entry<String, Object> entry3 : inputs.entrySet()) {
            int[] inputShapeIfDifferent = getInputTensor(entry3.getKey(), signatureKey).getInputShapeIfDifferent(entry3.getValue());
            if (inputShapeIfDifferent != null) {
                signatureRunnerWrapper.resizeInput(entry3.getKey(), inputShapeIfDifferent);
            }
        }
        signatureRunnerWrapper.allocateTensorsIfNeeded();
        for (Map.Entry<String, Object> entry4 : inputs.entrySet()) {
            signatureRunnerWrapper.getInputTensor(entry4.getKey()).setTo(entry4.getValue());
        }
        long nanoTime = System.nanoTime();
        signatureRunnerWrapper.invoke();
        long nanoTime2 = System.nanoTime() - nanoTime;
        for (Map.Entry<String, Object> entry5 : outputs.entrySet()) {
            if (entry5.getValue() != null) {
                signatureRunnerWrapper.getOutputTensor(entry5.getKey()).copyTo(entry5.getValue());
            }
        }
        this.inferenceDurationNanoseconds = nanoTime2;
    }

    public void run(Object[] inputs, Map<Integer, Object> outputs) {
        TensorImpl[] tensorImplArr;
        this.inferenceDurationNanoseconds = -1L;
        if (inputs == null || inputs.length == 0) {
            throw new IllegalArgumentException("Input error: Inputs should not be null or empty.");
        }
        if (outputs == null) {
            throw new IllegalArgumentException("Input error: Outputs should not be null.");
        }
        for (int i = 0; i < inputs.length; i++) {
            int[] inputShapeIfDifferent = getInputTensor(i).getInputShapeIfDifferent(inputs[i]);
            if (inputShapeIfDifferent != null) {
                resizeInput(i, inputShapeIfDifferent);
            }
        }
        boolean allocateTensorsIfNeeded = allocateTensorsIfNeeded();
        for (int i2 = 0; i2 < inputs.length; i2++) {
            getInputTensor(i2).setTo(inputs[i2]);
        }
        long nanoTime = System.nanoTime();
        run(this.interpreterHandle, this.errorHandle);
        long nanoTime2 = System.nanoTime() - nanoTime;
        if (allocateTensorsIfNeeded) {
            for (TensorImpl tensorImpl : this.outputTensors) {
                if (tensorImpl != null) {
                    tensorImpl.refreshShape();
                }
            }
        }
        for (Map.Entry<Integer, Object> entry : outputs.entrySet()) {
            if (entry.getValue() != null) {
                getOutputTensor(entry.getKey().intValue()).copyTo(entry.getValue());
            }
        }
        this.inferenceDurationNanoseconds = nanoTime2;
    }

    void resizeInput(int idx, int[] dims) {
        resizeInput(idx, dims, false);
    }

    public void resizeInput(int idx, int[] dims, boolean strict) {
        if (resizeInput(this.interpreterHandle, this.errorHandle, idx, dims, strict)) {
            this.isMemoryAllocated = false;
            TensorImpl tensorImpl = this.inputTensors[idx];
            if (tensorImpl != null) {
                tensorImpl.refreshShape();
            }
        }
    }

    public void allocateTensors() {
        allocateTensorsIfNeeded();
    }

    private boolean allocateTensorsIfNeeded() {
        TensorImpl[] tensorImplArr;
        if (this.isMemoryAllocated) {
            return false;
        }
        this.isMemoryAllocated = true;
        allocateTensors(this.interpreterHandle, this.errorHandle);
        for (TensorImpl tensorImpl : this.outputTensors) {
            if (tensorImpl != null) {
                tensorImpl.refreshShape();
            }
        }
        return true;
    }

    public int getInputIndex(String name) {
        if (this.inputsIndexes == null) {
            String[] inputNames = getInputNames(this.interpreterHandle);
            this.inputsIndexes = new HashMap();
            if (inputNames != null) {
                for (int i = 0; i < inputNames.length; i++) {
                    this.inputsIndexes.put(inputNames[i], Integer.valueOf(i));
                }
            }
        }
        if (this.inputsIndexes.containsKey(name)) {
            return this.inputsIndexes.get(name).intValue();
        }
        throw new IllegalArgumentException(String.format("Input error: '%s' is not a valid name for any input. Names of inputs and their indexes are %s", name, this.inputsIndexes));
    }

    public int getOutputIndex(String name) {
        if (this.outputsIndexes == null) {
            String[] outputNames = getOutputNames(this.interpreterHandle);
            this.outputsIndexes = new HashMap();
            if (outputNames != null) {
                for (int i = 0; i < outputNames.length; i++) {
                    this.outputsIndexes.put(outputNames[i], Integer.valueOf(i));
                }
            }
        }
        if (this.outputsIndexes.containsKey(name)) {
            return this.outputsIndexes.get(name).intValue();
        }
        throw new IllegalArgumentException(String.format("Input error: '%s' is not a valid name for any output. Names of outputs and their indexes are %s", name, this.outputsIndexes));
    }

    public Long getLastNativeInferenceDurationNanoseconds() {
        long j = this.inferenceDurationNanoseconds;
        if (j < 0) {
            return null;
        }
        return Long.valueOf(j);
    }

    public int getInputTensorCount() {
        return this.inputTensors.length;
    }

    public TensorImpl getInputTensor(int index) {
        if (index >= 0) {
            TensorImpl[] tensorImplArr = this.inputTensors;
            if (index < tensorImplArr.length) {
                TensorImpl tensorImpl = tensorImplArr[index];
                if (tensorImpl == null) {
                    long j = this.interpreterHandle;
                    TensorImpl fromIndex = TensorImpl.fromIndex(j, getInputTensorIndex(j, index));
                    tensorImplArr[index] = fromIndex;
                    return fromIndex;
                }
                return tensorImpl;
            }
        }
        throw new IllegalArgumentException("Invalid input Tensor index: " + index);
    }

    public TensorImpl getInputTensor(String inputName, String signatureKey) {
        if (inputName == null) {
            throw new IllegalArgumentException("Invalid input tensor name provided (null)");
        }
        NativeSignatureRunnerWrapper signatureRunnerWrapper = getSignatureRunnerWrapper(signatureKey);
        if (signatureRunnerWrapper.getSubgraphIndex() > 0) {
            return signatureRunnerWrapper.getInputTensor(inputName);
        }
        return getInputTensor(signatureRunnerWrapper.getInputIndex(inputName));
    }

    public String[] getSignatureKeys() {
        return getSignatureKeys(this.interpreterHandle);
    }

    public String[] getSignatureInputs(String signatureKey) {
        return getSignatureRunnerWrapper(signatureKey).inputNames();
    }

    public String[] getSignatureOutputs(String signatureKey) {
        return getSignatureRunnerWrapper(signatureKey).outputNames();
    }

    public int getOutputTensorCount() {
        return this.outputTensors.length;
    }

    public TensorImpl getOutputTensor(int index) {
        if (index >= 0) {
            TensorImpl[] tensorImplArr = this.outputTensors;
            if (index < tensorImplArr.length) {
                TensorImpl tensorImpl = tensorImplArr[index];
                if (tensorImpl == null) {
                    long j = this.interpreterHandle;
                    TensorImpl fromIndex = TensorImpl.fromIndex(j, getOutputTensorIndex(j, index));
                    tensorImplArr[index] = fromIndex;
                    return fromIndex;
                }
                return tensorImpl;
            }
        }
        throw new IllegalArgumentException("Invalid output Tensor index: " + index);
    }

    public TensorImpl getOutputTensor(String outputName, String signatureKey) {
        if (outputName == null) {
            throw new IllegalArgumentException("Invalid output tensor name provided (null)");
        }
        NativeSignatureRunnerWrapper signatureRunnerWrapper = getSignatureRunnerWrapper(signatureKey);
        if (signatureRunnerWrapper.getSubgraphIndex() > 0) {
            return signatureRunnerWrapper.getOutputTensor(outputName);
        }
        return getOutputTensor(signatureRunnerWrapper.getOutputIndex(outputName));
    }

    public int getExecutionPlanLength() {
        return getExecutionPlanLength(this.interpreterHandle);
    }

    public void setCancelled(boolean value) {
        long j = this.cancellationFlagHandle;
        if (j == 0) {
            throw new IllegalStateException("Cannot cancel the inference. Have you called InterpreterApi.Options.setCancellable?");
        }
        setCancelled(this.interpreterHandle, j, value);
    }

    private void addDelegates(InterpreterImpl.Options options) {
        Delegate maybeCreateFlexDelegate;
        if (this.originalGraphHasUnresolvedFlexOp && (maybeCreateFlexDelegate = maybeCreateFlexDelegate(options.getDelegates())) != null) {
            this.ownedDelegates.add(maybeCreateFlexDelegate);
            this.delegates.add(maybeCreateFlexDelegate);
        }
        addUserProvidedDelegates(options);
        for (DelegateFactory delegateFactory : options.getDelegateFactories()) {
            Delegate create = delegateFactory.create(RUNTIME_FLAVOR);
            this.ownedDelegates.add(create);
            this.delegates.add(create);
        }
        if (options.getUseNNAPI()) {
            NnApiDelegate nnApiDelegate = new NnApiDelegate();
            this.ownedDelegates.add(nnApiDelegate);
            this.delegates.add(nnApiDelegate);
        }
    }

    private void addUserProvidedDelegates(InterpreterImpl.Options options) {
        for (Delegate delegate : options.getDelegates()) {
            if (options.getRuntime() != InterpreterApi.Options.TfLiteRuntime.FROM_APPLICATION_ONLY && !(delegate instanceof NnApiDelegate)) {
                throw new IllegalArgumentException("Instantiated delegates (other than NnApiDelegate) are not allowed when using TF Lite from Google Play Services. Please use InterpreterApi.Options.addDelegateFactory() with an appropriate DelegateFactory instead.");
            }
            this.delegates.add(delegate);
        }
    }

    private void initDelegatesWithInterpreterFactory() {
        InterpreterFactoryImpl interpreterFactoryImpl = new InterpreterFactoryImpl();
        for (Delegate delegate : this.delegates) {
            if (delegate instanceof NnApiDelegate) {
                ((NnApiDelegate) delegate).initWithInterpreterFactoryApi(interpreterFactoryImpl);
            }
        }
    }

    private NativeSignatureRunnerWrapper getSignatureRunnerWrapper(String signatureKey) {
        if (this.signatureRunnerMap == null) {
            this.signatureRunnerMap = new HashMap();
        }
        if (!this.signatureRunnerMap.containsKey(signatureKey)) {
            this.signatureRunnerMap.put(signatureKey, new NativeSignatureRunnerWrapper(this.interpreterHandle, this.errorHandle, signatureKey));
        }
        return this.signatureRunnerMap.get(signatureKey);
    }

    private static Delegate maybeCreateFlexDelegate(List<Delegate> delegates) {
        try {
            Class<?> cls = Class.forName("org.tensorflow.lite.flex.FlexDelegate");
            for (Delegate delegate : delegates) {
                if (cls.isInstance(delegate)) {
                    return null;
                }
            }
            return (Delegate) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            return null;
        }
    }
}
