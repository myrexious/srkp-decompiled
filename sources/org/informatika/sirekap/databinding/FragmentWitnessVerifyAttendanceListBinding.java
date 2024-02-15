package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentWitnessVerifyAttendanceListBinding extends ViewDataBinding {
    @Bindable
    protected VerifyWitnessAttendanceListViewModel mViewModel;
    public final ViewWitnessVerifyAttendanceListOngoingBinding viewVerifyAttendeesOngoing;
    public final ViewWitnessVerifyAttendanceListStartBinding viewVerifyAttendeesStart;

    public abstract void setViewModel(VerifyWitnessAttendanceListViewModel viewModel);

    public FragmentWitnessVerifyAttendanceListBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewWitnessVerifyAttendanceListOngoingBinding viewVerifyAttendeesOngoing, ViewWitnessVerifyAttendanceListStartBinding viewVerifyAttendeesStart) {
        super(_bindingComponent, _root, _localFieldCount);
        this.viewVerifyAttendeesOngoing = viewVerifyAttendeesOngoing;
        this.viewVerifyAttendeesStart = viewVerifyAttendeesStart;
    }

    public VerifyWitnessAttendanceListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentWitnessVerifyAttendanceListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessVerifyAttendanceListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWitnessVerifyAttendanceListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_verify_attendance_list, root, attachToRoot, component);
    }

    public static FragmentWitnessVerifyAttendanceListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessVerifyAttendanceListBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWitnessVerifyAttendanceListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_verify_attendance_list, null, false, component);
    }

    public static FragmentWitnessVerifyAttendanceListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessVerifyAttendanceListBinding bind(View view, Object component) {
        return (FragmentWitnessVerifyAttendanceListBinding) bind(component, view, R.layout.fragment_witness_verify_attendance_list);
    }
}
