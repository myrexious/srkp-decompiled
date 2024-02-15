package io.reactivex.disposables;

import org.reactivestreams.Subscription;

/* loaded from: classes3.dex */
final class SubscriptionDisposable extends ReferenceDisposable<Subscription> {
    private static final long serialVersionUID = -707001650852963139L;

    public SubscriptionDisposable(Subscription subscription) {
        super(subscription);
    }

    @Override // io.reactivex.disposables.ReferenceDisposable
    public void onDisposed(Subscription subscription) {
        subscription.cancel();
    }
}
