package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionWithRelation;

/* loaded from: classes2.dex */
public abstract class ViewLockElectionBinding extends ViewDataBinding {
    public final Button buttonCreatePdf;
    public final Button buttonShareZip;
    public final Button buttonUploadPdf;
    public final Button buttonUploadPdfOffline;
    public final MaterialCardView cardInfo;
    @Bindable
    protected ElectionWithRelation mElectionWithRelation;
    @Bindable
    protected Boolean mIsLoadingZip;

    public abstract void setElectionWithRelation(ElectionWithRelation electionWithRelation);

    public abstract void setIsLoadingZip(Boolean isLoadingZip);

    public ViewLockElectionBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonCreatePdf, Button buttonShareZip, Button buttonUploadPdf, Button buttonUploadPdfOffline, MaterialCardView cardInfo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonCreatePdf = buttonCreatePdf;
        this.buttonShareZip = buttonShareZip;
        this.buttonUploadPdf = buttonUploadPdf;
        this.buttonUploadPdfOffline = buttonUploadPdfOffline;
        this.cardInfo = cardInfo;
    }

    public ElectionWithRelation getElectionWithRelation() {
        return this.mElectionWithRelation;
    }

    public Boolean getIsLoadingZip() {
        return this.mIsLoadingZip;
    }

    public static ViewLockElectionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockElectionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewLockElectionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_lock_election, root, attachToRoot, component);
    }

    public static ViewLockElectionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockElectionBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewLockElectionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_lock_election, null, false, component);
    }

    public static ViewLockElectionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewLockElectionBinding bind(View view, Object component) {
        return (ViewLockElectionBinding) bind(component, view, R.layout.view_lock_election);
    }
}
