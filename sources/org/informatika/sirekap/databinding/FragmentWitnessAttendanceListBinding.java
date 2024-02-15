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
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentWitnessAttendanceListBinding extends ViewDataBinding {
    public final FloatingActionButton buttonAdd;
    public final FloatingActionButton buttonSend;
    public final ConstraintLayout containerLockSpecialOccurrence;
    @Bindable
    protected WitnessAttendanceListViewModel mViewModel;
    public final RecyclerView recyclerView;
    public final TextView textView;
    public final TextView textWitnessEmptyTitle;
    public final ViewLockAttendanceBinding viewLockAttendance;

    public abstract void setViewModel(WitnessAttendanceListViewModel viewModel);

    public FragmentWitnessAttendanceListBinding(Object _bindingComponent, View _root, int _localFieldCount, FloatingActionButton buttonAdd, FloatingActionButton buttonSend, ConstraintLayout containerLockSpecialOccurrence, RecyclerView recyclerView, TextView textView, TextView textWitnessEmptyTitle, ViewLockAttendanceBinding viewLockAttendance) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonAdd = buttonAdd;
        this.buttonSend = buttonSend;
        this.containerLockSpecialOccurrence = containerLockSpecialOccurrence;
        this.recyclerView = recyclerView;
        this.textView = textView;
        this.textWitnessEmptyTitle = textWitnessEmptyTitle;
        this.viewLockAttendance = viewLockAttendance;
    }

    public WitnessAttendanceListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentWitnessAttendanceListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessAttendanceListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWitnessAttendanceListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_attendance_list, root, attachToRoot, component);
    }

    public static FragmentWitnessAttendanceListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessAttendanceListBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWitnessAttendanceListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_attendance_list, null, false, component);
    }

    public static FragmentWitnessAttendanceListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessAttendanceListBinding bind(View view, Object component) {
        return (FragmentWitnessAttendanceListBinding) bind(component, view, R.layout.fragment_witness_attendance_list);
    }
}
