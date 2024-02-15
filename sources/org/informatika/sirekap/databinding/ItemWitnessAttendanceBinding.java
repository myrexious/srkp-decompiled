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

/* loaded from: classes2.dex */
public abstract class ItemWitnessAttendanceBinding extends ViewDataBinding {
    public final Button buttonDeletePhoto;
    public final Button buttonMoreMenu;
    public final Button buttonRetakePhoto;
    public final ImageView image;
    public final TextView labelName;
    @Bindable
    protected String mCroppedPhotoPath;
    @Bindable
    protected String mNomorHalaman;

    public abstract void setCroppedPhotoPath(String croppedPhotoPath);

    public abstract void setNomorHalaman(String nomorHalaman);

    public ItemWitnessAttendanceBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonDeletePhoto, Button buttonMoreMenu, Button buttonRetakePhoto, ImageView image, TextView labelName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonDeletePhoto = buttonDeletePhoto;
        this.buttonMoreMenu = buttonMoreMenu;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.image = image;
        this.labelName = labelName;
    }

    public String getNomorHalaman() {
        return this.mNomorHalaman;
    }

    public String getCroppedPhotoPath() {
        return this.mCroppedPhotoPath;
    }

    public static ItemWitnessAttendanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWitnessAttendanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWitnessAttendanceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_witness_attendance, root, attachToRoot, component);
    }

    public static ItemWitnessAttendanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWitnessAttendanceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWitnessAttendanceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_witness_attendance, null, false, component);
    }

    public static ItemWitnessAttendanceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWitnessAttendanceBinding bind(View view, Object component) {
        return (ItemWitnessAttendanceBinding) bind(component, view, R.layout.item_witness_attendance);
    }
}
