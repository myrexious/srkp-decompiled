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
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public abstract class ViewSpecialOccurrenceOngoingBinding extends ViewDataBinding {
    public final Button buttonBack;
    public final Button buttonFinish;
    public final Button buttonNext;
    public final Button buttonRetakePhoto;
    public final MaterialCardView cardImage;
    public final MaterialCardView cardInstruction;
    public final ImageView iconContainer;
    public final TextView labelPhotoInstruction;
    @Bindable
    protected VerifySpecialOccurrenceViewModel mViewModel;

    public abstract void setViewModel(VerifySpecialOccurrenceViewModel viewModel);

    public ViewSpecialOccurrenceOngoingBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonBack, Button buttonFinish, Button buttonNext, Button buttonRetakePhoto, MaterialCardView cardImage, MaterialCardView cardInstruction, ImageView iconContainer, TextView labelPhotoInstruction) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonBack = buttonBack;
        this.buttonFinish = buttonFinish;
        this.buttonNext = buttonNext;
        this.buttonRetakePhoto = buttonRetakePhoto;
        this.cardImage = cardImage;
        this.cardInstruction = cardInstruction;
        this.iconContainer = iconContainer;
        this.labelPhotoInstruction = labelPhotoInstruction;
    }

    public VerifySpecialOccurrenceViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewSpecialOccurrenceOngoingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewSpecialOccurrenceOngoingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewSpecialOccurrenceOngoingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_special_occurrence_ongoing, root, attachToRoot, component);
    }

    public static ViewSpecialOccurrenceOngoingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewSpecialOccurrenceOngoingBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewSpecialOccurrenceOngoingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_special_occurrence_ongoing, null, false, component);
    }

    public static ViewSpecialOccurrenceOngoingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewSpecialOccurrenceOngoingBinding bind(View view, Object component) {
        return (ViewSpecialOccurrenceOngoingBinding) bind(component, view, R.layout.view_special_occurrence_ongoing);
    }
}
