package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* loaded from: classes2.dex */
public abstract class FragmentAprilTagConflictBinding extends ViewDataBinding {
    public final Button buttonContinue;
    public final Button buttonRetry;
    public final MaterialCardView c1ImageViewCard;
    public final MaterialCardView cardInfo;
    public final ImageView iconContainer;
    public final TextView labelInputElectionPage;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCaseAprilTag;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCaseManual;
    @Bindable
    protected AprilTagConflictViewModel mViewModel;
    public final RadioButton radioElectionPageAprilTag;
    public final RadioButton radioElectionPageManual;
    public final RadioGroup radioGroupElectionPage;

    public abstract void setGetElectionPageUseCaseAprilTag(GetElectionPageUseCase getElectionPageUseCaseAprilTag);

    public abstract void setGetElectionPageUseCaseManual(GetElectionPageUseCase getElectionPageUseCaseManual);

    public abstract void setViewModel(AprilTagConflictViewModel viewModel);

    public FragmentAprilTagConflictBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonContinue, Button buttonRetry, MaterialCardView c1ImageViewCard, MaterialCardView cardInfo, ImageView iconContainer, TextView labelInputElectionPage, RadioButton radioElectionPageAprilTag, RadioButton radioElectionPageManual, RadioGroup radioGroupElectionPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonContinue = buttonContinue;
        this.buttonRetry = buttonRetry;
        this.c1ImageViewCard = c1ImageViewCard;
        this.cardInfo = cardInfo;
        this.iconContainer = iconContainer;
        this.labelInputElectionPage = labelInputElectionPage;
        this.radioElectionPageAprilTag = radioElectionPageAprilTag;
        this.radioElectionPageManual = radioElectionPageManual;
        this.radioGroupElectionPage = radioGroupElectionPage;
    }

    public AprilTagConflictViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionPageUseCase getGetElectionPageUseCaseManual() {
        return this.mGetElectionPageUseCaseManual;
    }

    public GetElectionPageUseCase getGetElectionPageUseCaseAprilTag() {
        return this.mGetElectionPageUseCaseAprilTag;
    }

    public static FragmentAprilTagConflictBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAprilTagConflictBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentAprilTagConflictBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_april_tag_conflict, root, attachToRoot, component);
    }

    public static FragmentAprilTagConflictBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAprilTagConflictBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentAprilTagConflictBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_april_tag_conflict, null, false, component);
    }

    public static FragmentAprilTagConflictBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAprilTagConflictBinding bind(View view, Object component) {
        return (FragmentAprilTagConflictBinding) bind(component, view, R.layout.fragment_april_tag_conflict);
    }
}
