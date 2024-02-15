package androidx.room;

import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class InvalidationLiveDataContainer {
    private final RoomDatabase mDatabase;
    final Set<LiveData> mLiveDataSet = Collections.newSetFromMap(new IdentityHashMap());

    public InvalidationLiveDataContainer(RoomDatabase database) {
        this.mDatabase = database;
    }

    public <T> LiveData<T> create(String[] tableNames, boolean inTransaction, Callable<T> computeFunction) {
        return new RoomTrackingLiveData(this.mDatabase, this, inTransaction, computeFunction, tableNames);
    }

    public void onActive(LiveData liveData) {
        this.mLiveDataSet.add(liveData);
    }

    public void onInactive(LiveData liveData) {
        this.mLiveDataSet.remove(liveData);
    }
}
