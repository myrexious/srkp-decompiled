package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentSendImageBinding extends ViewDataBinding {
    public final Button buttonDeletePhoto;
    public final Button buttonRetakePhoto;
    public final Button buttonSendPhoto;
    public final MaterialCardView cardFailed;
    public final MaterialCardView cardImage;
    public final MaterialCardView cardInstruction;
    public final MaterialCardView cardLoading;
    public final MaterialCardView cardMaxRetryReached;
    public final MaterialCardView cardSuccess;
    public final ImageView iconContainer;
    public final TextView labelPhotoInstruction;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCase;
    @Bindable
    protected SendImageViewModel mViewModel;
    public final ScrollView scrollView2;
    public final CircularProgressIndicator sendImageFragmentLoading;

    public abstract void setGetElectionPageUseCase(GetElectionPageUseCase getElectionPageUseCase);

    public abstract void setViewModel(SendImageViewModel viewModel);

    public FragmentSendImageBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonDeletePhoto, Button buttonRetakePhoto, Button buttonSendPhoto, MaterialCardView cardFailed, MaterialCardView cardImage, MaterialCardView cardInstruction, MaterialCardView cardLoading, MaterialCardView cardMaxRetryReached, MaterialCardView cardSuccess, ImageView iconContainer, TextView labelPhotoInstruction, ScrollView scrollView2, CircularProgressIndicator sendImageFragmentLoading) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonDeletePhoto = buttonDeletePhoto;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.buttonSendPhoto = buttonSendPhoto;
        this.cardFailed = cardFailed;
        this.cardImage = cardImage;
        this.cardInstruction = cardInstruction;
        this.cardLoading = cardLoading;
        this.cardMaxRetryReached = cardMaxRetryReached;
        this.cardSuccess = cardSuccess;
        this.iconContainer = iconContainer;
        this.labelPhotoInstruction = labelPhotoInstruction;
        this.scrollView2 = scrollView2;
        this.sendImageFragmentLoading = sendImageFragmentLoading;
    }

    public SendImageViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.mGetElectionPageUseCase;
    }

    public static FragmentSendImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSendImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSendImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_send_image, root, attachToRoot, component);
    }

    public static FragmentSendImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSendImageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSendImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_send_image, null, false, component);
    }

    public static FragmentSendImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSendImageBinding bind(View view, Object component) {
        return (FragmentSendImageBinding) bind(component, view, R.layout.fragment_send_image);
    }
}
