package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;

    @Inject
    public WorkInitializer(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        this.executor = executor;
        this.store = eventStore;
        this.scheduler = workScheduler;
        this.guard = synchronizationGuard;
    }

    public void ensureContextsScheduled() {
        this.executor.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WorkInitializer.this.m135xb85b87dc();
            }
        });
    }

    /* renamed from: lambda$ensureContextsScheduled$1$com-google-android-datatransport-runtime-scheduling-jobscheduling-WorkInitializer */
    public /* synthetic */ void m135xb85b87dc() {
        this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer$$ExternalSyntheticLambda0
            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
            public final Object execute() {
                return WorkInitializer.this.m134x10dfae1b();
            }
        });
    }

    /* renamed from: lambda$ensureContextsScheduled$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-WorkInitializer */
    public /* synthetic */ Object m134x10dfae1b() {
        for (TransportContext transportContext : this.store.loadActiveContexts()) {
            this.scheduler.schedule(transportContext, 1);
        }
        return null;
    }
}