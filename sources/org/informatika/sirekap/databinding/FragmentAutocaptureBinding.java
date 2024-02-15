package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;
import org.informatika.sirekap.ui.autocapture.PolygonView;

/* loaded from: classes2.dex */
public abstract class FragmentAutocaptureBinding extends ViewDataBinding {
    public final TextView aprilTagHintText;
    public final CircularProgressIndicator autocaptureLoading;
    public final ConstraintLayout autocropContainer;
    public final MaterialButton buttonAuto;
    public final ImageView buttonCropOk;
    public final ImageView buttonCropReject;
    public final MaterialButton buttonManual;
    public final MaterialButtonToggleGroup buttonToggleCapture;
    public final FrameLayout cameraPreview;
    public final ImageView cropImageView;
    public final ConstraintLayout cropLayout;
    public final TextView hintText;
    @Bindable
    protected AutoCaptureViewModel mViewModel;
    public final PolygonView omrPolygonView;
    public final ImageView shutter;

    public abstract void setViewModel(AutoCaptureViewModel viewModel);

    public FragmentAutocaptureBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView aprilTagHintText, CircularProgressIndicator autocaptureLoading, ConstraintLayout autocropContainer, MaterialButton buttonAuto, ImageView buttonCropOk, ImageView buttonCropReject, MaterialButton buttonManual, MaterialButtonToggleGroup buttonToggleCapture, FrameLayout cameraPreview, ImageView cropImageView, ConstraintLayout cropLayout, TextView hintText, PolygonView omrPolygonView, ImageView shutter) {
        super(_bindingComponent, _root, _localFieldCount);
        this.aprilTagHintText = aprilTagHintText;
        this.autocaptureLoading = autocaptureLoading;
        this.autocropContainer = autocropContainer;
        this.buttonAuto = buttonAuto;
        this.buttonCropOk = buttonCropOk;
        this.buttonCropReject = buttonCropReject;
        this.buttonManual = buttonManual;
        this.buttonToggleCapture = buttonToggleCapture;
        this.cameraPreview = cameraPreview;
        this.cropImageView = cropImageView;
        this.cropLayout = cropLayout;
        this.hintText = hintText;
        this.omrPolygonView = omrPolygonView;
        this.shutter = shutter;
    }

    public AutoCaptureViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentAutocaptureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAutocaptureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentAutocaptureBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_autocapture, root, attachToRoot, component);
    }

    public static FragmentAutocaptureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAutocaptureBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentAutocaptureBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_autocapture, null, false, component);
    }

    public static FragmentAutocaptureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAutocaptureBinding bind(View view, Object component) {
        return (FragmentAutocaptureBinding) bind(component, view, R.layout.fragment_autocapture);
    }
}
