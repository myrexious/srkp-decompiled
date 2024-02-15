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
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentVerifyAdministrationHal2PpwpBinding extends ViewDataBinding {
    public final Button buttonContinueVerify;
    public final Button buttonRetakePhoto;
    public final MaterialCardView c1ImageViewCard;
    public final MaterialCardView cardInfo;
    public final ImageView iconContainer;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCase;
    @Bindable
    protected VerifyAdministrationHal2PpwpViewModel mViewModel;
    public final TextView photoDescription;
    public final RecyclerView recyclerViewDataPerolehanSuara;
    public final RecyclerView recyclerViewDataSurat;
    public final Button submitButton;

    public abstract void setGetElectionPageUseCase(GetElectionPageUseCase getElectionPageUseCase);

    public abstract void setViewModel(VerifyAdministrationHal2PpwpViewModel viewModel);

    public FragmentVerifyAdministrationHal2PpwpBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonContinueVerify, Button buttonRetakePhoto, MaterialCardView c1ImageViewCard, MaterialCardView cardInfo, ImageView iconContainer, TextView photoDescription, RecyclerView recyclerViewDataPerolehanSuara, RecyclerView recyclerViewDataSurat, Button submitButton) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonContinueVerify = buttonContinueVerify;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.c1ImageViewCard = c1ImageViewCard;
        this.cardInfo = cardInfo;
        this.iconContainer = iconContainer;
        this.photoDescription = photoDescription;
        this.recyclerViewDataPerolehanSuara = recyclerViewDataPerolehanSuara;
        this.recyclerViewDataSurat = recyclerViewDataSurat;
        this.submitButton = submitButton;
    }

    public VerifyAdministrationHal2PpwpViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.mGetElectionPageUseCase;
    }

    public static FragmentVerifyAdministrationHal2PpwpBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyAdministrationHal2PpwpBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVerifyAdministrationHal2PpwpBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_administration_hal2_ppwp, root, attachToRoot, component);
    }

    public static FragmentVerifyAdministrationHal2PpwpBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyAdministrationHal2PpwpBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVerifyAdministrationHal2PpwpBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_administration_hal2_ppwp, null, false, component);
    }

    public static FragmentVerifyAdministrationHal2PpwpBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyAdministrationHal2PpwpBinding bind(View view, Object component) {
        return (FragmentVerifyAdministrationHal2PpwpBinding) bind(component, view, R.layout.fragment_verify_administration_hal2_ppwp);
    }
}
