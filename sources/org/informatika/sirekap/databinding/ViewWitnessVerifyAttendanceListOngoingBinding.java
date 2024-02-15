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
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public abstract class ViewWitnessVerifyAttendanceListOngoingBinding extends ViewDataBinding {
    public final Button buttonBack;
    public final Button buttonFinish;
    public final Button buttonNext;
    public final Button buttonRetakePhoto;
    public final MaterialCardView cardImage;
    public final MaterialCardView cardInstruction;
    public final ImageView iconContainer;
    public final TextView labelPhotoInstruction;
    @Bindable
    protected VerifyWitnessAttendanceListViewModel mViewModel;
    public final TextView nomorHalamanTitle;

    public abstract void setViewModel(VerifyWitnessAttendanceListViewModel viewModel);

    public ViewWitnessVerifyAttendanceListOngoingBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonBack, Button buttonFinish, Button buttonNext, Button buttonRetakePhoto, MaterialCardView cardImage, MaterialCardView cardInstruction, ImageView iconContainer, TextView labelPhotoInstruction, TextView nomorHalamanTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonBack = buttonBack;
        this.buttonFinish = buttonFinish;
        this.buttonNext = buttonNext;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.cardImage = cardImage;
        this.cardInstruction = cardInstruction;
        this.iconContainer = iconContainer;
        this.labelPhotoInstruction = labelPhotoInstruction;
        this.nomorHalamanTitle = nomorHalamanTitle;
    }

    public VerifyWitnessAttendanceListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewWitnessVerifyAttendanceListOngoingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessVerifyAttendanceListOngoingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewWitnessVerifyAttendanceListOngoingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_verify_attendance_list_ongoing, root, attachToRoot, component);
    }

    public static ViewWitnessVerifyAttendanceListOngoingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessVerifyAttendanceListOngoingBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewWitnessVerifyAttendanceListOngoingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_verify_attendance_list_ongoing, null, false, component);
    }

    public static ViewWitnessVerifyAttendanceListOngoingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessVerifyAttendanceListOngoingBinding bind(View view, Object component) {
        return (ViewWitnessVerifyAttendanceListOngoingBinding) bind(component, view, R.layout.view_witness_verify_attendance_list_ongoing);
    }
}
