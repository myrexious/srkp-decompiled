package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Attendance;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public abstract class ViewLockAttendanceBinding extends ViewDataBinding {
    public final Button buttonCreatePdf;
    public final Button buttonShareZip;
    public final Button buttonUploadPdf;
    public final Button buttonUploadPdfOffline;
    @Bindable
    protected Attendance mAttendance;
    @Bindable
    protected WitnessAttendanceListViewModel mViewModel;

    public abstract void setAttendance(Attendance attendance);

    public abstract void setViewModel(WitnessAttendanceListViewModel viewModel);

    public ViewLockAttendanceBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonCreatePdf, Button buttonShareZip, Button buttonUploadPdf, Button buttonUploadPdfOffline) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonCreatePdf = buttonCreatePdf;
        this.buttonShareZip = buttonShareZip;
        this.buttonUploadPdf = buttonUploadPdf;
        this.buttonUploadPdfOffline = buttonUploadPdfOffline;
    }

    public Attendance getAttendance() {
        return this.mAttendance;
    }

    public WitnessAttendanceListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewLockAttendanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockAttendanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewLockAttendanceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_lock_attendance, root, attachToRoot, component);
    }

    public static ViewLockAttendanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockAttendanceBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewLockAttendanceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_lock_attendance, null, false, component);
    }

    public static ViewLockAttendanceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockAttendanceBinding bind(View view, Object component) {
        return (ViewLockAttendanceBinding) bind(component, view, R.layout.view_lock_attendance);
    }
}
