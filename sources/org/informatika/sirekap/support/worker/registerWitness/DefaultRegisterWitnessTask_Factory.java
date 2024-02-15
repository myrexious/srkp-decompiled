package org.informatika.sirekap.support.worker.registerWitness;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultRegisterWitnessTask_Factory implements Factory<DefaultRegisterWitnessTask> {
    @Override // javax.inject.Provider
    public DefaultRegisterWitnessTask get() {
        return newInstance();
    }

    public static DefaultRegisterWitnessTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultRegisterWitnessTask newInstance() {
        return new DefaultRegisterWitnessTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultRegisterWitnessTask_Factory INSTANCE = new DefaultRegisterWitnessTask_Factory();

        private InstanceHolder() {
        }
    }
}
