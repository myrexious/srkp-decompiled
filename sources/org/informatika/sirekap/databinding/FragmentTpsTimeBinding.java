package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentTpsTimeBinding extends ViewDataBinding {
    public final MaterialCardView cardSuccess;
    public final MaterialCardView cardWarning;
    public final TextView contentStartDateTime;
    public final TextView contentStartEndTime;
    public final TextInputEditText inputEndDate;
    public final TextInputEditText inputEndTime;
    public final TextInputEditText inputStartDate;
    public final TextInputEditText inputStartTime;
    public final TextView labelStartDateTime;
    public final TextView labelStartEndTime;
    @Bindable
    protected TpsTimeViewModel mViewModel;
    public final Button submitButton;
    public final TextInputLayout textInputLayoutEndDate;
    public final TextInputLayout textInputLayoutEndTime;
    public final TextInputLayout textInputLayoutStartDate;
    public final TextInputLayout textInputLayoutStartTime;
    public final TextView textSubtitle;

    public abstract void setViewModel(TpsTimeViewModel viewModel);

    public FragmentTpsTimeBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialCardView cardSuccess, MaterialCardView cardWarning, TextView contentStartDateTime, TextView contentStartEndTime, TextInputEditText inputEndDate, TextInputEditText inputEndTime, TextInputEditText inputStartDate, TextInputEditText inputStartTime, TextView labelStartDateTime, TextView labelStartEndTime, Button submitButton, TextInputLayout textInputLayoutEndDate, TextInputLayout textInputLayoutEndTime, TextInputLayout textInputLayoutStartDate, TextInputLayout textInputLayoutStartTime, TextView textSubtitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cardSuccess = cardSuccess;
        this.cardWarning = cardWarning;
        this.contentStartDateTime = contentStartDateTime;
        this.contentStartEndTime = contentStartEndTime;
        this.inputEndDate = inputEndDate;
        this.inputEndTime = inputEndTime;
        this.inputStartDate = inputStartDate;
        this.inputStartTime = inputStartTime;
        this.labelStartDateTime = labelStartDateTime;
        this.labelStartEndTime = labelStartEndTime;
        this.submitButton = submitButton;
        this.textInputLayoutEndDate = textInputLayoutEndDate;
        this.textInputLayoutEndTime = textInputLayoutEndTime;
        this.textInputLayoutStartDate = textInputLayoutStartDate;
        this.textInputLayoutStartTime = textInputLayoutStartTime;
        this.textSubtitle = textSubtitle;
    }

    public TpsTimeViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentTpsTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTpsTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentTpsTimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_tps_time, root, attachToRoot, component);
    }

    public static FragmentTpsTimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTpsTimeBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentTpsTimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_tps_time, null, false, component);
    }

    public static FragmentTpsTimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTpsTimeBinding bind(View view, Object component) {
        return (FragmentTpsTimeBinding) bind(component, view, R.layout.fragment_tps_time);
    }
}
