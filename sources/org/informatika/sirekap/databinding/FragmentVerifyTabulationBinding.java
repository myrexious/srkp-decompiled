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
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentVerifyTabulationBinding extends ViewDataBinding {
    public final Button butonRetry;
    public final Button buttonContinueVerify;
    public final Button buttonRetakePhoto;
    public final MaterialCardView c1ImageViewCard;
    public final MaterialCardView cardInfo;
    public final ImageView iconContainer;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCase;
    @Bindable
    protected VerifyTabulationViewModel mViewModel;
    public final TextView photoDescription;
    public final RecyclerView recyclerViewDataPerolehanSuara;
    public final Button submitButton;

    public abstract void setGetElectionPageUseCase(GetElectionPageUseCase getElectionPageUseCase);

    public abstract void setViewModel(VerifyTabulationViewModel viewModel);

    public FragmentVerifyTabulationBinding(Object _bindingComponent, View _root, int _localFieldCount, Button butonRetry, Button buttonContinueVerify, Button buttonRetakePhoto, MaterialCardView c1ImageViewCard, MaterialCardView cardInfo, ImageView iconContainer, TextView photoDescription, RecyclerView recyclerViewDataPerolehanSuara, Button submitButton) {
        super(_bindingComponent, _root, _localFieldCount);
        this.butonRetry = butonRetry;
        this.buttonContinueVerify = buttonContinueVerify;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.c1ImageViewCard = c1ImageViewCard;
        this.cardInfo = cardInfo;
        this.iconContainer = iconContainer;
        this.photoDescription = photoDescription;
        this.recyclerViewDataPerolehanSuara = recyclerViewDataPerolehanSuara;
        this.submitButton = submitButton;
    }

    public VerifyTabulationViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.mGetElectionPageUseCase;
    }

    public static FragmentVerifyTabulationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyTabulationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVerifyTabulationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_tabulation, root, attachToRoot, component);
    }

    public static FragmentVerifyTabulationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyTabulationBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVerifyTabulationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_tabulation, null, false, component);
    }

    public static FragmentVerifyTabulationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyTabulationBinding bind(View view, Object component) {
        return (FragmentVerifyTabulationBinding) bind(component, view, R.layout.fragment_verify_tabulation);
    }
}
