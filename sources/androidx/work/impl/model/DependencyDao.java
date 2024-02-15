package androidx.work.impl.model;

import java.util.List;

/* loaded from: classes.dex */
public interface DependencyDao {
    List<String> getDependentWorkIds(String id2);

    List<String> getPrerequisites(String id2);

    boolean hasCompletedAllPrerequisites(String id2);

    boolean hasDependents(String id2);

    void insertDependency(Dependency dependency);
}
