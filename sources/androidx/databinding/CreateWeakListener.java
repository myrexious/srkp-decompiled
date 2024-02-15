package androidx.databinding;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes.dex */
public interface CreateWeakListener {
    WeakListener create(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue);
}