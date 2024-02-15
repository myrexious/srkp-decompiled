package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.GetElectionUseCase;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentElectionDetailBinding extends ViewDataBinding {
    public final MaterialCardView cardElectionInfo;
    public final MaterialCheckBox checkboxHideCompletedPages;
    public final CircularProgressIndicator electionDetailFragmentLoading;
    public final TextView labelCandidateNum;
    public final TextView labelElectionPartai;
    public final TextView labelElectionRegion;
    public final TextView labelElectionType;
    @Bindable
    protected GetElectionUseCase mGetElectionUseCase;
    @Bindable
    protected ElectionDetailViewModel mViewModel;
    public final RecyclerView recyclerViewElectionPages;
    public final TextView titleElectionInfo;
    public final TextView titleFormCCapture;
    public final TextView valueCandidateNum;
    public final TextView valueElectionPartai;
    public final TextView valueElectionRegion;
    public final TextView valueElectionType;
    public final ViewLockElectionBinding viewLockElection;

    public abstract void setGetElectionUseCase(GetElectionUseCase getElectionUseCase);

    public abstract void setViewModel(ElectionDetailViewModel viewModel);

    public FragmentElectionDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialCardView cardElectionInfo, MaterialCheckBox checkboxHideCompletedPages, CircularProgressIndicator electionDetailFragmentLoading, TextView labelCandidateNum, TextView labelElectionPartai, TextView labelElectionRegion, TextView labelElectionType, RecyclerView recyclerViewElectionPages, TextView titleElectionInfo, TextView titleFormCCapture, TextView valueCandidateNum, TextView valueElectionPartai, TextView valueElectionRegion, TextView valueElectionType, ViewLockElectionBinding viewLockElection) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cardElectionInfo = cardElectionInfo;
        this.checkboxHideCompletedPages = checkboxHideCompletedPages;
        this.electionDetailFragmentLoading = electionDetailFragmentLoading;
        this.labelCandidateNum = labelCandidateNum;
        this.labelElectionPartai = labelElectionPartai;
        this.labelElectionRegion = labelElectionRegion;
        this.labelElectionType = labelElectionType;
        this.recyclerViewElectionPages = recyclerViewElectionPages;
        this.titleElectionInfo = titleElectionInfo;
        this.titleFormCCapture = titleFormCCapture;
        this.valueCandidateNum = valueCandidateNum;
        this.valueElectionPartai = valueElectionPartai;
        this.valueElectionRegion = valueElectionRegion;
        this.valueElectionType = valueElectionType;
        this.viewLockElection = viewLockElection;
    }

    public ElectionDetailViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionUseCase getGetElectionUseCase() {
        return this.mGetElectionUseCase;
    }

    public static FragmentElectionDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentElectionDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentElectionDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_election_detail, root, attachToRoot, component);
    }

    public static FragmentElectionDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentElectionDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentElectionDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_election_detail, null, false, component);
    }

    public static FragmentElectionDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentElectionDetailBinding bind(View view, Object component) {
        return (FragmentElectionDetailBinding) bind(component, view, R.layout.fragment_election_detail);
    }
}
