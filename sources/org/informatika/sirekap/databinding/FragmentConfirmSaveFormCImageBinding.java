package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* loaded from: classes2.dex */
public abstract class FragmentConfirmSaveFormCImageBinding extends ViewDataBinding {
    public final Button buttonBack;
    public final Button buttonRetry;
    public final Button buttonSave;
    public final MaterialCardView c1ImageViewCard;
    public final MaterialCardView cardError;
    public final MaterialCardView cardInfo;
    public final ConstraintLayout errorNormal;
    public final ImageView iconContainer;
    public final ConstraintLayout layoutNormal;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCase;
    @Bindable
    protected ConfirmSaveFormCImageViewModel mViewModel;
    public final TextView textElection;
    public final TextView textElection2;
    public final TextView textPage;
    public final TextView textPage2;
    public final TextView title;

    public abstract void setGetElectionPageUseCase(GetElectionPageUseCase getElectionPageUseCase);

    public abstract void setViewModel(ConfirmSaveFormCImageViewModel viewModel);

    public FragmentConfirmSaveFormCImageBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonBack, Button buttonRetry, Button buttonSave, MaterialCardView c1ImageViewCard, MaterialCardView cardError, MaterialCardView cardInfo, ConstraintLayout errorNormal, ImageView iconContainer, ConstraintLayout layoutNormal, TextView textElection, TextView textElection2, TextView textPage, TextView textPage2, TextView title) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonBack = buttonBack;
        this.buttonRetry = buttonRetry;
        this.buttonSave = buttonSave;
        this.c1ImageViewCard = c1ImageViewCard;
        this.cardError = cardError;
        this.cardInfo = cardInfo;
        this.errorNormal = errorNormal;
        this.iconContainer = iconContainer;
        this.layoutNormal = layoutNormal;
        this.textElection = textElection;
        this.textElection2 = textElection2;
        this.textPage = textPage;
        this.textPage2 = textPage2;
        this.title = title;
    }

    public ConfirmSaveFormCImageViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.mGetElectionPageUseCase;
    }

    public static FragmentConfirmSaveFormCImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentConfirmSaveFormCImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentConfirmSaveFormCImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_confirm_save_form_c_image, root, attachToRoot, component);
    }

    public static FragmentConfirmSaveFormCImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentConfirmSaveFormCImageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentConfirmSaveFormCImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_confirm_save_form_c_image, null, false, component);
    }

    public static FragmentConfirmSaveFormCImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentConfirmSaveFormCImageBinding bind(View view, Object component) {
        return (FragmentConfirmSaveFormCImageBinding) bind(component, view, R.layout.fragment_confirm_save_form_c_image);
    }
}
