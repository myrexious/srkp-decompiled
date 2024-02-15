package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;

/* loaded from: classes2.dex */
public abstract class ItemSpecialOccurrenceBinding extends ViewDataBinding {
    public final Button buttonDeletePhoto;
    public final Button buttonRetakePhoto;
    public final ImageView image;
    @Bindable
    protected String mCroppedPhotoPath;

    public abstract void setCroppedPhotoPath(String croppedPhotoPath);

    public ItemSpecialOccurrenceBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonDeletePhoto, Button buttonRetakePhoto, ImageView image) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonDeletePhoto = buttonDeletePhoto;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.image = image;
    }

    public String getCroppedPhotoPath() {
        return this.mCroppedPhotoPath;
    }

    public static ItemSpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_special_occurrence, root, attachToRoot, component);
    }

    public static ItemSpecialOccurrenceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSpecialOccurrenceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_special_occurrence, null, false, component);
    }

    public static ItemSpecialOccurrenceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSpecialOccurrenceBinding bind(View view, Object component) {
        return (ItemSpecialOccurrenceBinding) bind(component, view, R.layout.item_special_occurrence);
    }
}
