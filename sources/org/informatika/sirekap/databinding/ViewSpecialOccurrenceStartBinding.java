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
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public abstract class ViewSpecialOccurrenceStartBinding extends ViewDataBinding {
    public final Button buttonRestartStartVerification;
    public final Button buttonResumeVerification;
    public final Button buttonStartVerification;
    public final ImageView iconVerifyAttendees;
    @Bindable
    protected VerifySpecialOccurrenceViewModel mViewModel;
    public final TextView subtitleStartVerification;
    public final TextView titleStartVerification;

    public abstract void setViewModel(VerifySpecialOccurrenceViewModel viewModel);

    public ViewSpecialOccurrenceStartBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonRestartStartVerification, Button buttonResumeVerification, Button buttonStartVerification, ImageView iconVerifyAttendees, TextView subtitleStartVerification, TextView titleStartVerification) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonRestartStartVerification = buttonRestartStartVerification;
        this.buttonResumeVerification = buttonResumeVerification;
        this.buttonStartVerification = buttonStartVerification;
        this.iconVerifyAttendees = iconVerifyAttendees;
        this.subtitleStartVerification = subtitleStartVerification;
        this.titleStartVerification = titleStartVerification;
    }

    public VerifySpecialOccurrenceViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewSpecialOccurrenceStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewSpecialOccurrenceStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewSpecialOccurrenceStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_special_occurrence_start, root, attachToRoot, component);
    }

    public static ViewSpecialOccurrenceStartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewSpecialOccurrenceStartBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewSpecialOccurrenceStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_special_occurrence_start, null, false, component);
    }

    public static ViewSpecialOccurrenceStartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewSpecialOccurrenceStartBinding bind(View view, Object component) {
        return (ViewSpecialOccurrenceStartBinding) bind(component, view, R.layout.view_special_occurrence_start);
    }
}
