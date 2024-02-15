package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;

/* loaded from: classes2.dex */
public abstract class DialogSelectRetakePhotoMethodBinding extends ViewDataBinding {
    public final Button cancelButton;
    public final Button offlineButton;

    public DialogSelectRetakePhotoMethodBinding(Object _bindingComponent, View _root, int _localFieldCount, Button cancelButton, Button offlineButton) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cancelButton = cancelButton;
        this.offlineButton = offlineButton;
    }

    public static DialogSelectRetakePhotoMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelectRetakePhotoMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogSelectRetakePhotoMethodBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_select_retake_photo_method, root, attachToRoot, component);
    }

    public static DialogSelectRetakePhotoMethodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelectRetakePhotoMethodBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogSelectRetakePhotoMethodBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_select_retake_photo_method, null, false, component);
    }

    public static DialogSelectRetakePhotoMethodBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelectRetakePhotoMethodBinding bind(View view, Object component) {
        return (DialogSelectRetakePhotoMethodBinding) bind(component, view, R.layout.dialog_select_retake_photo_method);
    }
}
