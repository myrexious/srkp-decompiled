package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public abstract class ViewWitnessVerifyAttendanceListStartBinding extends ViewDataBinding {
    public final Button buttonRestartStartVerification;
    public final Button buttonResumeVerification;
    public final Button buttonStartVerification;
    public final ImageView iconVerifyAttendees;
    @Bindable
    protected VerifyWitnessAttendanceListViewModel mViewModel;
    public final TextView subtitleStartVerification;
    public final TextView titleStartVerification;

    public abstract void setViewModel(VerifyWitnessAttendanceListViewModel viewModel);

    public ViewWitnessVerifyAttendanceListStartBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonRestartStartVerification, Button buttonResumeVerification, Button buttonStartVerification, ImageView iconVerifyAttendees, TextView subtitleStartVerification, TextView titleStartVerification) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonRestartStartVerification = buttonRestartStartVerification;
        this.buttonResumeVerification = buttonResumeVerification;
        this.buttonStartVerification = buttonStartVerification;
        this.iconVerifyAttendees = iconVerifyAttendees;
        this.subtitleStartVerification = subtitleStartVerification;
        this.titleStartVerification = titleStartVerification;
    }

    public VerifyWitnessAttendanceListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewWitnessVerifyAttendanceListStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessVerifyAttendanceListStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewWitnessVerifyAttendanceListStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_verify_attendance_list_start, root, attachToRoot, component);
    }

    public static ViewWitnessVerifyAttendanceListStartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessVerifyAttendanceListStartBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewWitnessVerifyAttendanceListStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_verify_attendance_list_start, null, false, component);
    }

    public static ViewWitnessVerifyAttendanceListStartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessVerifyAttendanceListStartBinding bind(View view, Object component) {
        return (ViewWitnessVerifyAttendanceListStartBinding) bind(component, view, R.layout.view_witness_verify_attendance_list_start);
    }
}
