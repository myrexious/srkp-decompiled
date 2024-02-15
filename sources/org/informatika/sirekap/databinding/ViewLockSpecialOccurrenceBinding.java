package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public abstract class ViewLockSpecialOccurrenceBinding extends ViewDataBinding {
    public final Button buttonCreatePdf;
    public final Button buttonShareZip;
    public final Button buttonUploadPdf;
    public final Button buttonUploadPdfOffline;
    @Bindable
    protected SpecialOccurrenceViewModel mViewModel;

    public abstract void setViewModel(SpecialOccurrenceViewModel viewModel);

    public ViewLockSpecialOccurrenceBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonCreatePdf, Button buttonShareZip, Button buttonUploadPdf, Button buttonUploadPdfOffline) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonCreatePdf = buttonCreatePdf;
        this.buttonShareZip = buttonShareZip;
        this.buttonUploadPdf = buttonUploadPdf;
        this.buttonUploadPdfOffline = buttonUploadPdfOffline;
    }

    public SpecialOccurrenceViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewLockSpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockSpecialOccurrenceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewLockSpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_lock_special_occurrence, root, attachToRoot, component);
    }

    public static ViewLockSpecialOccurrenceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockSpecialOccurrenceBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewLockSpecialOccurrenceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_lock_special_occurrence, null, false, component);
    }

    public static ViewLockSpecialOccurrenceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockSpecialOccurrenceBinding bind(View view, Object component) {
        return (ViewLockSpecialOccurrenceBinding) bind(component, view, R.layout.view_lock_special_occurrence);
    }
}
