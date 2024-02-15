package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentWitnessRegisterBinding extends ViewDataBinding {
    public final Button buttonRegister;
    public final CircularProgressIndicator circularProgressIndicator;
    public final MaterialAutoCompleteTextView idPaslonDpd;
    public final MaterialAutoCompleteTextView idPaslonDpr;
    public final MaterialAutoCompleteTextView idPaslonDprdk;
    public final MaterialAutoCompleteTextView idPaslonDprdp;
    public final MaterialAutoCompleteTextView idPaslonPilgub;
    public final MaterialAutoCompleteTextView idPaslonPilwalkot;
    public final MaterialAutoCompleteTextView idPaslonPresiden;
    public final CheckBox jenisSaksiPemilihanDpd;
    public final TextInputLayout jenisSaksiPemilihanDpdSelect;
    public final CheckBox jenisSaksiPemilihanDpr;
    public final TextInputLayout jenisSaksiPemilihanDprSelect;
    public final CheckBox jenisSaksiPemilihanDprdk;
    public final TextInputLayout jenisSaksiPemilihanDprdkSelect;
    public final CheckBox jenisSaksiPemilihanDprdp;
    public final TextInputLayout jenisSaksiPemilihanDprdpSelect;
    public final TextView jenisSaksiPemilihanError;
    public final CheckBox jenisSaksiPemilihanPilgub;
    public final TextInputLayout jenisSaksiPemilihanPilgubSelect;
    public final CheckBox jenisSaksiPemilihanPilwabup;
    public final TextInputLayout jenisSaksiPemilihanPilwabupSelect;
    public final CheckBox jenisSaksiPemilihanPresiden;
    public final TextInputLayout jenisSaksiPemilihanPresidenSelect;
    public final TextView jenisSaksiPemilihanTitle;
    @Bindable
    protected WitnessRegisterFormUseCase mFormState;
    @Bindable
    protected WitnessRegisterViewModel mViewModel;
    public final TextInputEditText name;
    public final TextInputEditText nik;
    public final TextInputEditText noHandphone;
    public final MaterialAutoCompleteTextView perwakilan;
    public final CircularProgressIndicator progressBar;
    public final TextInputLayout registerWitnessName;
    public final TextInputLayout registerWitnessNik;
    public final TextInputLayout witnessRegisterJenis;
    public final TextInputLayout witnessRegsiterHandphone;

    public abstract void setFormState(WitnessRegisterFormUseCase formState);

    public abstract void setViewModel(WitnessRegisterViewModel viewModel);

    public FragmentWitnessRegisterBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonRegister, CircularProgressIndicator circularProgressIndicator, MaterialAutoCompleteTextView idPaslonDpd, MaterialAutoCompleteTextView idPaslonDpr, MaterialAutoCompleteTextView idPaslonDprdk, MaterialAutoCompleteTextView idPaslonDprdp, MaterialAutoCompleteTextView idPaslonPilgub, MaterialAutoCompleteTextView idPaslonPilwalkot, MaterialAutoCompleteTextView idPaslonPresiden, CheckBox jenisSaksiPemilihanDpd, TextInputLayout jenisSaksiPemilihanDpdSelect, CheckBox jenisSaksiPemilihanDpr, TextInputLayout jenisSaksiPemilihanDprSelect, CheckBox jenisSaksiPemilihanDprdk, TextInputLayout jenisSaksiPemilihanDprdkSelect, CheckBox jenisSaksiPemilihanDprdp, TextInputLayout jenisSaksiPemilihanDprdpSelect, TextView jenisSaksiPemilihanError, CheckBox jenisSaksiPemilihanPilgub, TextInputLayout jenisSaksiPemilihanPilgubSelect, CheckBox jenisSaksiPemilihanPilwabup, TextInputLayout jenisSaksiPemilihanPilwabupSelect, CheckBox jenisSaksiPemilihanPresiden, TextInputLayout jenisSaksiPemilihanPresidenSelect, TextView jenisSaksiPemilihanTitle, TextInputEditText name, TextInputEditText nik, TextInputEditText noHandphone, MaterialAutoCompleteTextView perwakilan, CircularProgressIndicator progressBar, TextInputLayout registerWitnessName, TextInputLayout registerWitnessNik, TextInputLayout witnessRegisterJenis, TextInputLayout witnessRegsiterHandphone) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonRegister = buttonRegister;
        this.circularProgressIndicator = circularProgressIndicator;
        this.idPaslonDpd = idPaslonDpd;
        this.idPaslonDpr = idPaslonDpr;
        this.idPaslonDprdk = idPaslonDprdk;
        this.idPaslonDprdp = idPaslonDprdp;
        this.idPaslonPilgub = idPaslonPilgub;
        this.idPaslonPilwalkot = idPaslonPilwalkot;
        this.idPaslonPresiden = idPaslonPresiden;
        this.jenisSaksiPemilihanDpd = jenisSaksiPemilihanDpd;
        this.jenisSaksiPemilihanDpdSelect = jenisSaksiPemilihanDpdSelect;
        this.jenisSaksiPemilihanDpr = jenisSaksiPemilihanDpr;
        this.jenisSaksiPemilihanDprSelect = jenisSaksiPemilihanDprSelect;
        this.jenisSaksiPemilihanDprdk = jenisSaksiPemilihanDprdk;
        this.jenisSaksiPemilihanDprdkSelect = jenisSaksiPemilihanDprdkSelect;
        this.jenisSaksiPemilihanDprdp = jenisSaksiPemilihanDprdp;
        this.jenisSaksiPemilihanDprdpSelect = jenisSaksiPemilihanDprdpSelect;
        this.jenisSaksiPemilihanError = jenisSaksiPemilihanError;
        this.jenisSaksiPemilihanPilgub = jenisSaksiPemilihanPilgub;
        this.jenisSaksiPemilihanPilgubSelect = jenisSaksiPemilihanPilgubSelect;
        this.jenisSaksiPemilihanPilwabup = jenisSaksiPemilihanPilwabup;
        this.jenisSaksiPemilihanPilwabupSelect = jenisSaksiPemilihanPilwabupSelect;
        this.jenisSaksiPemilihanPresiden = jenisSaksiPemilihanPresiden;
        this.jenisSaksiPemilihanPresidenSelect = jenisSaksiPemilihanPresidenSelect;
        this.jenisSaksiPemilihanTitle = jenisSaksiPemilihanTitle;
        this.name = name;
        this.nik = nik;
        this.noHandphone = noHandphone;
        this.perwakilan = perwakilan;
        this.progressBar = progressBar;
        this.registerWitnessName = registerWitnessName;
        this.registerWitnessNik = registerWitnessNik;
        this.witnessRegisterJenis = witnessRegisterJenis;
        this.witnessRegsiterHandphone = witnessRegsiterHandphone;
    }

    public WitnessRegisterFormUseCase getFormState() {
        return this.mFormState;
    }

    public WitnessRegisterViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentWitnessRegisterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessRegisterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWitnessRegisterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_register, root, attachToRoot, component);
    }

    public static FragmentWitnessRegisterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessRegisterBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWitnessRegisterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_register, null, false, component);
    }

    public static FragmentWitnessRegisterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessRegisterBinding bind(View view, Object component) {
        return (FragmentWitnessRegisterBinding) bind(component, view, R.layout.fragment_witness_register);
    }
}
