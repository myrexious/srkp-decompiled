package org.tensorflow.lite;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.InterpreterApi;

/* loaded from: classes4.dex */
class InterpreterImpl implements InterpreterApi {
    NativeInterpreterWrapper wrapper;

    /* loaded from: classes4.dex */
    public static class Options extends InterpreterApi.Options {
        Boolean allowBufferHandleOutput;
        Boolean allowFp16PrecisionForFp32;

        public Options() {
        }

        public Options(InterpreterApi.Options options) {
            super(options);
        }

        public Options(Options other) {
            super(other);
            this.allowFp16PrecisionForFp32 = other.allowFp16PrecisionForFp32;
            this.allowBufferHandleOutput = other.allowBufferHandleOutput;
        }
    }

    public InterpreterImpl(File modelFile, Options options) {
        this.wrapper = new NativeInterpreterWrapper(modelFile.getAbsolutePath(), options);
    }

    public InterpreterImpl(ByteBuffer byteBuffer, Options options) {
        this.wrapper = new NativeInterpreterWrapper(byteBuffer, options);
    }

    public InterpreterImpl(NativeInterpreterWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public void run(Object input, Object output) {
        Object[] objArr = {input};
        HashMap hashMap = new HashMap();
        hashMap.put(0, output);
        runForMultipleInputsOutputs(objArr, hashMap);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public void runForMultipleInputsOutputs(Object[] inputs, Map<Integer, Object> outputs) {
        checkNotClosed();
        this.wrapper.run(inputs, outputs);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public void allocateTensors() {
        checkNotClosed();
        this.wrapper.allocateTensors();
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public void resizeInput(int idx, int[] dims) {
        checkNotClosed();
        this.wrapper.resizeInput(idx, dims, false);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public void resizeInput(int idx, int[] dims, boolean strict) {
        checkNotClosed();
        this.wrapper.resizeInput(idx, dims, strict);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public int getInputTensorCount() {
        checkNotClosed();
        return this.wrapper.getInputTensorCount();
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public int getInputIndex(String opName) {
        checkNotClosed();
        return this.wrapper.getInputIndex(opName);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public Tensor getInputTensor(int inputIndex) {
        checkNotClosed();
        return this.wrapper.getInputTensor(inputIndex);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public int getOutputTensorCount() {
        checkNotClosed();
        return this.wrapper.getOutputTensorCount();
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public int getOutputIndex(String opName) {
        checkNotClosed();
        return this.wrapper.getOutputIndex(opName);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public Tensor getOutputTensor(int outputIndex) {
        checkNotClosed();
        return this.wrapper.getOutputTensor(outputIndex);
    }

    @Override // org.tensorflow.lite.InterpreterApi
    public Long getLastNativeInferenceDurationNanoseconds() {
        checkNotClosed();
        return this.wrapper.getLastNativeInferenceDurationNanoseconds();
    }

    int getExecutionPlanLength() {
        checkNotClosed();
        return this.wrapper.getExecutionPlanLength();
    }

    @Override // org.tensorflow.lite.InterpreterApi, java.lang.AutoCloseable
    public void close() {
        NativeInterpreterWrapper nativeInterpreterWrapper = this.wrapper;
        if (nativeInterpreterWrapper != null) {
            nativeInterpreterWrapper.close();
            this.wrapper = null;
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public void checkNotClosed() {
        if (this.wrapper == null) {
            throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
        }
    }
}
