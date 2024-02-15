package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* loaded from: classes2.dex */
public abstract class ItemFormC1AdministrationBinding extends ViewDataBinding {
    public final LinearLayout linearLayout;
    @Bindable
    protected Boolean mIsDone;
    @Bindable
    protected FormC1ListItem mItem;
    @Bindable
    protected Boolean mShowCorrection;

    public abstract void setIsDone(Boolean isDone);

    public abstract void setItem(FormC1ListItem item);

    public abstract void setShowCorrection(Boolean showCorrection);

    public ItemFormC1AdministrationBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout linearLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linearLayout = linearLayout;
    }

    public Boolean getShowCorrection() {
        return this.mShowCorrection;
    }

    public FormC1ListItem getItem() {
        return this.mItem;
    }

    public Boolean getIsDone() {
        return this.mIsDone;
    }

    public static ItemFormC1AdministrationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFormC1AdministrationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemFormC1AdministrationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_form_c1_administration, root, attachToRoot, component);
    }

    public static ItemFormC1AdministrationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFormC1AdministrationBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemFormC1AdministrationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_form_c1_administration, null, false, component);
    }

    public static ItemFormC1AdministrationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFormC1AdministrationBinding bind(View view, Object component) {
        return (ItemFormC1AdministrationBinding) bind(component, view, R.layout.item_form_c1_administration);
    }
}
