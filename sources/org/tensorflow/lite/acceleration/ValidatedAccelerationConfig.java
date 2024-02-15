package org.tensorflow.lite.acceleration;

import org.tensorflow.lite.InterpreterApi;

/* loaded from: classes4.dex */
public interface ValidatedAccelerationConfig {
    void apply(InterpreterApi.Options options);

    byte[] serialize();
}
