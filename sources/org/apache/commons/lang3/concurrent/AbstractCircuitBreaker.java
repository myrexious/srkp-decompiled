package org.apache.commons.lang3.concurrent;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public abstract class AbstractCircuitBreaker<T> implements CircuitBreaker<T> {
    public static final String PROPERTY_NAME = "open";
    protected final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    /* loaded from: classes2.dex */
    public enum State {
        CLOSED { // from class: org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State.1
            @Override // org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State
            public State oppositeState() {
                return OPEN;
            }
        },
        OPEN { // from class: org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State.2
            @Override // org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State
            public State oppositeState() {
                return CLOSED;
            }
        };

        public abstract State oppositeState();
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public abstract boolean checkState();

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public abstract boolean incrementAndCheckState(T t);

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public boolean isOpen() {
        return isOpen(this.state.get());
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public boolean isClosed() {
        return !isOpen();
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public void close() {
        changeState(State.CLOSED);
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public void open() {
        changeState(State.OPEN);
    }

    public static boolean isOpen(State state) {
        return state == State.OPEN;
    }

    public void changeState(State state) {
        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, state.oppositeState(), state)) {
            this.changeSupport.firePropertyChange("open", !isOpen(state), isOpen(state));
        }
    }

    public void addChangeListener(PropertyChangeListener propertyChangeListener) {
        this.changeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removeChangeListener(PropertyChangeListener propertyChangeListener) {
        this.changeSupport.removePropertyChangeListener(propertyChangeListener);
    }
}
