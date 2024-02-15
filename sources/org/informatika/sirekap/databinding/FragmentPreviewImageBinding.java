package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.github.chrisbanes.photoview.PhotoView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.previewImage.PreviewImageViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentPreviewImageBinding extends ViewDataBinding {
    @Bindable
    protected PreviewImageViewModel mViewModel;
    public final PhotoView photoView;

    public abstract void setViewModel(PreviewImageViewModel viewModel);

    public FragmentPreviewImageBinding(Object _bindingComponent, View _root, int _localFieldCount, PhotoView photoView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.photoView = photoView;
    }

    public PreviewImageViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentPreviewImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPreviewImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPreviewImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_preview_image, root, attachToRoot, component);
    }

    public static FragmentPreviewImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPreviewImageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPreviewImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_preview_image, null, false, component);
    }

    public static FragmentPreviewImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPreviewImageBinding bind(View view, Object component) {
        return (FragmentPreviewImageBinding) bind(component, view, R.layout.fragment_preview_image);
    }
}
