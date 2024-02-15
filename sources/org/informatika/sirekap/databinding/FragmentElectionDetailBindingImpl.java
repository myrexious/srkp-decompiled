package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.GetElectionUseCase;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel;

/* loaded from: classes2.dex */
public class FragmentElectionDetailBindingImpl extends FragmentElectionDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener checkboxHideCompletedPagesandroidCheckedAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView10;
    private final NestedScrollView mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(19);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(10, new String[]{"view_lock_election"}, new int[]{11}, new int[]{R.layout.view_lock_election});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.electionDetailFragment_loading, 12);
        sparseIntArray.put(R.id.title_election_info, 13);
        sparseIntArray.put(R.id.card_election_info, 14);
        sparseIntArray.put(R.id.label_election_type, 15);
        sparseIntArray.put(R.id.label_election_region, 16);
        sparseIntArray.put(R.id.title_form_c_capture, 17);
        sparseIntArray.put(R.id.recycler_view_election_pages, 18);
    }

    public FragmentElectionDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private FragmentElectionDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (MaterialCardView) bindings[14], (MaterialCheckBox) bindings[9], (CircularProgressIndicator) bindings[12], (TextView) bindings[5], (TextView) bindings[7], (TextView) bindings[16], (TextView) bindings[15], (RecyclerView) bindings[18], (TextView) bindings[13], (TextView) bindings[17], (TextView) bindings[6], (TextView) bindings[8], (TextView) bindings[4], (TextView) bindings[3], (ViewLockElectionBinding) bindings[11]);
        this.checkboxHideCompletedPagesandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentElectionDetailBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentElectionDetailBindingImpl.this.checkboxHideCompletedPages.isChecked();
                ElectionDetailViewModel electionDetailViewModel = FragmentElectionDetailBindingImpl.this.mViewModel;
                if (electionDetailViewModel != null) {
                    MutableLiveData<Boolean> hideCompletedPages = electionDetailViewModel.getHideCompletedPages();
                    if (hideCompletedPages != null) {
                        hideCompletedPages.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.checkboxHideCompletedPages.setTag(null);
        this.labelCandidateNum.setTag(null);
        this.labelElectionPartai.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[10];
        this.mboundView10 = constraintLayout3;
        constraintLayout3.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[2];
        this.mboundView2 = nestedScrollView;
        nestedScrollView.setTag(null);
        this.valueCandidateNum.setTag(null);
        this.valueElectionPartai.setTag(null);
        this.valueElectionRegion.setTag(null);
        this.valueElectionType.setTag(null);
        setContainedBinding(this.viewLockElection);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
        }
        this.viewLockElection.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.viewLockElection.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (19 == variableId) {
            setGetElectionUseCase((GetElectionUseCase) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((ElectionDetailViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentElectionDetailBinding
    public void setGetElectionUseCase(GetElectionUseCase GetElectionUseCase) {
        this.mGetElectionUseCase = GetElectionUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentElectionDetailBinding
    public void setViewModel(ElectionDetailViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.viewLockElection.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    if (localFieldId != 3) {
                        if (localFieldId != 4) {
                            if (localFieldId != 5) {
                                return false;
                            }
                            return onChangeViewModelIsLoadingZip((LiveData) object, fieldId);
                        }
                        return onChangeViewModelLoadImageProcessing((MutableLiveData) object, fieldId);
                    }
                    return onChangeViewModelHideCompletedPages((MutableLiveData) object, fieldId);
                }
                return onChangeViewLockElection((ViewLockElectionBinding) object, fieldId);
            }
            return onChangeGetElectionUseCaseElectionWithRelation((LiveData) object, fieldId);
        }
        return onChangeGetElectionUseCaseElection((LiveData) object, fieldId);
    }

    private boolean onChangeGetElectionUseCaseElection(LiveData<Election> GetElectionUseCaseElection, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionUseCaseElectionWithRelation(LiveData<ElectionWithRelation> GetElectionUseCaseElectionWithRelation, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewLockElection(ViewLockElectionBinding ViewLockElection, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHideCompletedPages(MutableLiveData<Boolean> ViewModelHideCompletedPages, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelLoadImageProcessing(MutableLiveData<Boolean> ViewModelLoadImageProcessing, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoadingZip(LiveData<Boolean> ViewModelIsLoadingZip, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        ElectionWithRelation electionWithRelation;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Boolean bool;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        String str6;
        boolean z7;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GetElectionUseCase getElectionUseCase = this.mGetElectionUseCase;
        ElectionDetailViewModel electionDetailViewModel = this.mViewModel;
        if ((323 & j) != 0) {
            if ((j & 321) != 0) {
                LiveData<Election> election = getElectionUseCase != null ? getElectionUseCase.getElection() : null;
                updateLiveDataRegistration(0, election);
                Election value = election != null ? election.getValue() : null;
                if (value != null) {
                    str5 = value.getDescription(getRoot().getContext());
                    str6 = value.getNumberPaslonString();
                    z7 = value.showPartaiLabel();
                    str = value.getRegion();
                } else {
                    z7 = false;
                    str = null;
                    str5 = null;
                    str6 = null;
                }
                z6 = !z7;
            } else {
                z6 = false;
                str = null;
                str5 = null;
                str6 = null;
            }
            if ((j & 322) != 0) {
                LiveData<ElectionWithRelation> electionWithRelation2 = getElectionUseCase != null ? getElectionUseCase.getElectionWithRelation() : null;
                updateLiveDataRegistration(1, electionWithRelation2);
                electionWithRelation = electionWithRelation2 != null ? electionWithRelation2.getValue() : null;
                if (electionWithRelation != null) {
                    str4 = electionWithRelation.getPartaisNumString();
                    str3 = str6;
                    z = z6;
                    str2 = electionWithRelation.getCandidatesNumString();
                } else {
                    str3 = str6;
                    z = z6;
                }
            } else {
                str3 = str6;
                z = z6;
                electionWithRelation = null;
            }
            str2 = null;
            str4 = null;
        } else {
            z = false;
            electionWithRelation = null;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        if ((j & 440) != 0) {
            if ((j & 392) != 0) {
                MutableLiveData<Boolean> hideCompletedPages = electionDetailViewModel != null ? electionDetailViewModel.getHideCompletedPages() : null;
                updateLiveDataRegistration(3, hideCompletedPages);
                z2 = ViewDataBinding.safeUnbox(hideCompletedPages != null ? hideCompletedPages.getValue() : null);
            } else {
                z2 = false;
            }
            if ((j & 400) != 0) {
                MutableLiveData<Boolean> loadImageProcessing = electionDetailViewModel != null ? electionDetailViewModel.getLoadImageProcessing() : null;
                updateLiveDataRegistration(4, loadImageProcessing);
                boolean safeUnbox = ViewDataBinding.safeUnbox(loadImageProcessing != null ? loadImageProcessing.getValue() : null);
                z4 = ViewDataBinding.safeUnbox(Boolean.valueOf(!safeUnbox));
                z5 = safeUnbox;
            } else {
                z4 = false;
                z5 = false;
            }
            if ((j & 416) != 0) {
                LiveData<Boolean> isLoadingZip = electionDetailViewModel != null ? electionDetailViewModel.isLoadingZip() : null;
                updateLiveDataRegistration(5, isLoadingZip);
                if (isLoadingZip != null) {
                    bool = isLoadingZip.getValue();
                    z3 = z5;
                }
            }
            z3 = z5;
            bool = null;
        } else {
            bool = null;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        if ((j & 392) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkboxHideCompletedPages, z2);
        }
        if ((256 & j) != 0) {
            CompoundButtonBindingAdapter.setListeners(this.checkboxHideCompletedPages, null, this.checkboxHideCompletedPagesandroidCheckedAttrChanged);
        }
        if ((321 & j) != 0) {
            TextViewBindingAdapter.setText(this.labelCandidateNum, str3);
            DataBindingAdaptersKt.hidden(this.labelElectionPartai, z);
            DataBindingAdaptersKt.hidden(this.valueElectionPartai, z);
            this.valueElectionRegion.setText(str);
            TextViewBindingAdapter.setText(this.valueElectionType, str5);
        }
        if ((j & 400) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z4);
            DataBindingAdaptersKt.hidden(this.mboundView2, z3);
        }
        if ((322 & j) != 0) {
            TextViewBindingAdapter.setText(this.valueCandidateNum, str2);
            this.valueElectionPartai.setText(str4);
            this.viewLockElection.setElectionWithRelation(electionWithRelation);
        }
        if ((j & 416) != 0) {
            this.viewLockElection.setIsLoadingZip(bool);
        }
        executeBindingsOn(this.viewLockElection);
    }
}
