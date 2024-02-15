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
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;

/* loaded from: classes2.dex */
public abstract class ItemElectionPageCaptureC1Binding extends ViewDataBinding {
    public final Button buttonDetail;
    public final Button buttonMoreMenu;
    public final TextView errorText;
    public final ImageView iconPhoto;
    public final ImageView iconUpload;
    public final ImageView iconVerify;
    public final TextView labelPage;
    public final View line1;
    public final View line2;
    @Bindable
    protected Integer mElectionJmlLembar;
    @Bindable
    protected ElectionPage mElectionPage;
    @Bindable
    protected ElectionPageWithRelation mElectionPageWithRelation;

    public abstract void setElectionJmlLembar(Integer electionJmlLembar);

    public abstract void setElectionPage(ElectionPage electionPage);

    public abstract void setElectionPageWithRelation(ElectionPageWithRelation electionPageWithRelation);

    public ItemElectionPageCaptureC1Binding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonDetail, Button buttonMoreMenu, TextView errorText, ImageView iconPhoto, ImageView iconUpload, ImageView iconVerify, TextView labelPage, View line1, View line2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonDetail = buttonDetail;
        this.buttonMoreMenu = buttonMoreMenu;
        this.errorText = errorText;
        this.iconPhoto = iconPhoto;
        this.iconUpload = iconUpload;
        this.iconVerify = iconVerify;
        this.labelPage = labelPage;
        this.line1 = line1;
        this.line2 = line2;
    }

    public Integer getElectionJmlLembar() {
        return this.mElectionJmlLembar;
    }

    public ElectionPage getElectionPage() {
        return this.mElectionPage;
    }

    public ElectionPageWithRelation getElectionPageWithRelation() {
        return this.mElectionPageWithRelation;
    }

    public static ItemElectionPageCaptureC1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionPageCaptureC1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemElectionPageCaptureC1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_election_page_capture_c1, root, attachToRoot, component);
    }

    public static ItemElectionPageCaptureC1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionPageCaptureC1Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemElectionPageCaptureC1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_election_page_capture_c1, null, false, component);
    }

    public static ItemElectionPageCaptureC1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionPageCaptureC1Binding bind(View view, Object component) {
        return (ItemElectionPageCaptureC1Binding) bind(component, view, R.layout.item_election_page_capture_c1);
    }
}
