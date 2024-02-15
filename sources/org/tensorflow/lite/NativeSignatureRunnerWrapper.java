package org.tensorflow.lite;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class NativeSignatureRunnerWrapper {
    private final long errorHandle;
    private boolean isMemoryAllocated = false;
    private final long signatureRunnerHandle;

    private static native void nativeAllocateTensors(long signatureRunnerHandle, long errorHandle);

    private static native int nativeGetInputIndex(long signatureRunnerHandle, String inputName);

    private static native int nativeGetOutputIndex(long signatureRunnerHandle, String outputName);

    private static native long nativeGetSignatureRunner(long interpreterHandle, String signatureKey);

    private static native int nativeGetSubgraphIndex(long signatureRunnerHandle);

    private static native String[] nativeInputNames(long signatureRunnerHandle);

    private static native void nativeInvoke(long signatureRunnerHandle, long errorHandle);

    private static native String[] nativeOutputNames(long signatureRunnerHandle);

    private static native boolean nativeResizeInput(long signatureRunnerHandle, long errorHandle, String inputName, int[] dims);

    public NativeSignatureRunnerWrapper(long interpreterHandle, long errorHandle, String signatureKey) {
        this.errorHandle = errorHandle;
        long nativeGetSignatureRunner = nativeGetSignatureRunner(interpreterHandle, signatureKey);
        this.signatureRunnerHandle = nativeGetSignatureRunner;
        if (nativeGetSignatureRunner == -1) {
            throw new IllegalArgumentException("Input error: Signature " + signatureKey + " not found.");
        }
    }

    public int getSubgraphIndex() {
        return nativeGetSubgraphIndex(this.signatureRunnerHandle);
    }

    public String[] inputNames() {
        return nativeInputNames(this.signatureRunnerHandle);
    }

    public String[] outputNames() {
        return nativeOutputNames(this.signatureRunnerHandle);
    }

    public TensorImpl getInputTensor(String inputName) {
        return TensorImpl.fromSignatureInput(this.signatureRunnerHandle, inputName);
    }

    public TensorImpl getOutputTensor(String outputName) {
        return TensorImpl.fromSignatureOutput(this.signatureRunnerHandle, outputName);
    }

    public int getInputIndex(String inputName) {
        int nativeGetInputIndex = nativeGetInputIndex(this.signatureRunnerHandle, inputName);
        if (nativeGetInputIndex != -1) {
            return nativeGetInputIndex;
        }
        throw new IllegalArgumentException("Input error: input " + inputName + " not found.");
    }

    public int getOutputIndex(String outputName) {
        int nativeGetOutputIndex = nativeGetOutputIndex(this.signatureRunnerHandle, outputName);
        if (nativeGetOutputIndex != -1) {
            return nativeGetOutputIndex;
        }
        throw new IllegalArgumentException("Input error: output " + outputName + " not found.");
    }

    public boolean resizeInput(String inputName, int[] dims) {
        this.isMemoryAllocated = false;
        return nativeResizeInput(this.signatureRunnerHandle, this.errorHandle, inputName, dims);
    }

    public void allocateTensorsIfNeeded() {
        if (this.isMemoryAllocated) {
            return;
        }
        nativeAllocateTensors(this.signatureRunnerHandle, this.errorHandle);
        this.isMemoryAllocated = true;
    }

    public void invoke() {
        nativeInvoke(this.signatureRunnerHandle, this.errorHandle);
    }
}
