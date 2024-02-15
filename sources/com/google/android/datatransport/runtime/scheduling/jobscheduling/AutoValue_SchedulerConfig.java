package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AutoValue_SchedulerConfig extends SchedulerConfig {
    private final Clock clock;
    private final Map<Priority, SchedulerConfig.ConfigValue> values;

    public AutoValue_SchedulerConfig(Clock clock, Map<Priority, SchedulerConfig.ConfigValue> map) {
        if (clock == null) {
            throw new NullPointerException("Null clock");
        }
        this.clock = clock;
        if (map == null) {
            throw new NullPointerException("Null values");
        }
        this.values = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    public Clock getClock() {
        return this.clock;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    public Map<Priority, SchedulerConfig.ConfigValue> getValues() {
        return this.values;
    }

    public String toString() {
        return "SchedulerConfig{clock=" + this.clock + ", values=" + this.values + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SchedulerConfig) {
            SchedulerConfig schedulerConfig = (SchedulerConfig) obj;
            return this.clock.equals(schedulerConfig.getClock()) && this.values.equals(schedulerConfig.getValues());
        }
        return false;
    }

    public int hashCode() {
        return ((this.clock.hashCode() ^ 1000003) * 1000003) ^ this.values.hashCode();
    }
}