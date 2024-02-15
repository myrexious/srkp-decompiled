package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentVerifySpecialOccurrenceBinding extends ViewDataBinding {
    @Bindable
    protected VerifySpecialOccurrenceViewModel mViewModel;
    public final ViewSpecialOccurrenceOngoingBinding viewVerifySpecialOccurrenceOngoing;
    public final ViewSpecialOccurrenceStartBinding viewVerifySpecialOccurrenceStart;

    public abstract void setViewModel(VerifySpecialOccurrenceViewModel viewModel);

    public FragmentVerifySpecialOccurrenceBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewSpecialOccurrenceOngoingBinding viewVerifySpecialOccurrenceOngoing, ViewSpecialOccurrenceStartBinding viewVerifySpecialOccurrenceStart) {
        super(_bindingComponent, _root, _localFieldCount);
        this.viewVerifySpecialOccurrenceOngoing = viewVerifySpecialOccurrenceOngoing;
        this.viewVerifySpecialOccurrenceStart = viewVerifySpecialOccurrenceStart;
    }

    public VerifySpecialOccurrenceViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentVerifySpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifySpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVerifySpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_special_occurrence, root, attachToRoot, component);
    }

    public static FragmentVerifySpecialOccurrenceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifySpecialOccurrenceBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVerifySpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_special_occurrence, null, false, component);
    }

    public static FragmentVerifySpecialOccurrenceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifySpecialOccurrenceBinding bind(View view, Object component) {
        return (FragmentVerifySpecialOccurrenceBinding) bind(component, view, R.layout.fragment_verify_special_occurrence);
    }
}
