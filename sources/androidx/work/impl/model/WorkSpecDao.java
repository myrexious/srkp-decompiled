package androidx.work.impl.model;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import java.util.List;

/* loaded from: classes.dex */
public interface WorkSpecDao {
    void delete(String id2);

    List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int maxLimit);

    List<String> getAllUnfinishedWork();

    List<String> getAllWorkSpecIds();

    LiveData<List<String>> getAllWorkSpecIdsLiveData();

    List<WorkSpec> getEligibleWorkForScheduling(int schedulerLimit);

    List<Data> getInputsFromPrerequisites(String id2);

    List<WorkSpec> getRecentlyCompletedWork(long startingAt);

    List<WorkSpec> getRunningWork();

    LiveData<Long> getScheduleRequestedAtLiveData(String id2);

    List<WorkSpec> getScheduledWork();

    WorkInfo.State getState(String id2);

    List<String> getUnfinishedWorkWithName(String name);

    List<String> getUnfinishedWorkWithTag(String tag);

    WorkSpec getWorkSpec(String id2);

    List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String name);

    WorkSpec[] getWorkSpecs(List<String> ids);

    WorkSpec.WorkInfoPojo getWorkStatusPojoForId(String id2);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> ids);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String name);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String tag);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForIds(List<String> ids);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForName(String name);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForTag(String tag);

    boolean hasUnfinishedWork();

    int incrementWorkSpecRunAttemptCount(String id2);

    void insertWorkSpec(WorkSpec workSpec);

    int markWorkSpecScheduled(String id2, long startTime);

    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    int resetScheduledState();

    int resetWorkSpecRunAttemptCount(String id2);

    void setOutput(String id2, Data output);

    void setPeriodStartTime(String id2, long periodStartTime);

    int setState(WorkInfo.State state, String... ids);
}
