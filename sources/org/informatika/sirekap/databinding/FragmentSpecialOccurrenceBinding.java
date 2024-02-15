package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentSpecialOccurrenceBinding extends ViewDataBinding {
    public final FloatingActionButton buttonAdd;
    public final FloatingActionButton buttonSend;
    public final ConstraintLayout containerLockSpecialOccurrence;
    @Bindable
    protected SpecialOccurrenceViewModel mViewModel;
    public final RecyclerView recyclerView;
    public final TextView textSpecialOccurrenceEmptyTitle;
    public final TextView textView;
    public final ViewLockSpecialOccurrenceBinding viewLockSpecialOccurrence;

    public abstract void setViewModel(SpecialOccurrenceViewModel viewModel);

    public FragmentSpecialOccurrenceBinding(Object _bindingComponent, View _root, int _localFieldCount, FloatingActionButton buttonAdd, FloatingActionButton buttonSend, ConstraintLayout containerLockSpecialOccurrence, RecyclerView recyclerView, TextView textSpecialOccurrenceEmptyTitle, TextView textView, ViewLockSpecialOccurrenceBinding viewLockSpecialOccurrence) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonAdd = buttonAdd;
        this.buttonSend = buttonSend;
        this.containerLockSpecialOccurrence = containerLockSpecialOccurrence;
        this.recyclerView = recyclerView;
        this.textSpecialOccurrenceEmptyTitle = textSpecialOccurrenceEmptyTitle;
        this.textView = textView;
        this.viewLockSpecialOccurrence = viewLockSpecialOccurrence;
    }

    public SpecialOccurrenceViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentSpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_special_occurrence, root, attachToRoot, component);
    }

    public static FragmentSpecialOccurrenceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSpecialOccurrenceBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_special_occurrence, null, false, component);
    }

    public static FragmentSpecialOccurrenceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSpecialOccurrenceBinding bind(View view, Object component) {
        return (FragmentSpecialOccurrenceBinding) bind(component, view, R.layout.fragment_special_occurrence);
    }
}
