package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;

/* loaded from: classes2.dex */
public abstract class DialogVerifyBodyBinding extends ViewDataBinding {
    public final TextInputEditText comment;
    public final TextInputLayout commentContainer;
    @Bindable
    protected Boolean mHideCommentField;
    @Bindable
    protected BaseVerifyViewModel mViewModel;
    public final Button submitButton;

    public abstract void setHideCommentField(Boolean hideCommentField);

    public abstract void setViewModel(BaseVerifyViewModel viewModel);

    public DialogVerifyBodyBinding(Object _bindingComponent, View _root, int _localFieldCount, TextInputEditText comment, TextInputLayout commentContainer, Button submitButton) {
        super(_bindingComponent, _root, _localFieldCount);
        this.comment = comment;
        this.commentContainer = commentContainer;
        this.submitButton = submitButton;
    }

    public BaseVerifyViewModel getViewModel() {
        return this.mViewModel;
    }

    public Boolean getHideCommentField() {
        return this.mHideCommentField;
    }

    public static DialogVerifyBodyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVerifyBodyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogVerifyBodyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_verify_body, root, attachToRoot, component);
    }

    public static DialogVerifyBodyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVerifyBodyBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogVerifyBodyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_verify_body, null, false, component);
    }

    public static DialogVerifyBodyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVerifyBodyBinding bind(View view, Object component) {
        return (DialogVerifyBodyBinding) bind(component, view, R.layout.dialog_verify_body);
    }
}
