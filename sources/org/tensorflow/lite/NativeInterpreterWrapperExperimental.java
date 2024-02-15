package org.tensorflow.lite;

import java.nio.ByteBuffer;
import org.tensorflow.lite.InterpreterImpl;

/* loaded from: classes4.dex */
final class NativeInterpreterWrapperExperimental extends NativeInterpreterWrapper {
    private static native void resetVariableTensors(long interpreterHandle, long errorHandle);

    public NativeInterpreterWrapperExperimental(String modelPath, InterpreterImpl.Options options) {
        super(modelPath, options);
    }

    public NativeInterpreterWrapperExperimental(ByteBuffer buffer, InterpreterImpl.Options options) {
        super(buffer, options);
    }

    public void resetVariableTensors() {
        resetVariableTensors(this.interpreterHandle, this.errorHandle);
    }
}
