package androidx.databinding;

import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes.dex */
public interface ObservableReference<T> {
    void addListener(T t);

    WeakListener<T> getListener();

    void removeListener(T t);

    void setLifecycleOwner(LifecycleOwner lifecycleOwner);
}
