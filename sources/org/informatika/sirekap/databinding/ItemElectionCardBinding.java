package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionWithRelation;

/* loaded from: classes2.dex */
public abstract class ItemElectionCardBinding extends ViewDataBinding {
    public final ConstraintLayout barCaptured;
    public final ConstraintLayout barNotRecognized;
    public final ConstraintLayout barVerified;
    public final Button buttonDetailElection;
    public final Button buttonTimeTps;
    public final Button buttonWitness;
    public final TextView labelCaptured;
    public final TextView labelElection;
    @Bindable
    protected ElectionWithRelation mElectionWithRelation;

    public abstract void setElectionWithRelation(ElectionWithRelation electionWithRelation);

    public ItemElectionCardBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout barCaptured, ConstraintLayout barNotRecognized, ConstraintLayout barVerified, Button buttonDetailElection, Button buttonTimeTps, Button buttonWitness, TextView labelCaptured, TextView labelElection) {
        super(_bindingComponent, _root, _localFieldCount);
        this.barCaptured = barCaptured;
        this.barNotRecognized = barNotRecognized;
        this.barVerified = barVerified;
        this.buttonDetailElection = buttonDetailElection;
        this.buttonTimeTps = buttonTimeTps;
        this.buttonWitness = buttonWitness;
        this.labelCaptured = labelCaptured;
        this.labelElection = labelElection;
    }

    public ElectionWithRelation getElectionWithRelation() {
        return this.mElectionWithRelation;
    }

    public static ItemElectionCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemElectionCardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_election_card, root, attachToRoot, component);
    }

    public static ItemElectionCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionCardBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemElectionCardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_election_card, null, false, component);
    }

    public static ItemElectionCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionCardBinding bind(View view, Object component) {
        return (ItemElectionCardBinding) bind(component, view, R.layout.item_election_card);
    }
}
