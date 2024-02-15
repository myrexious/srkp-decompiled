package org.informatika.sirekap;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.informatika.sirekap.databinding.ActivityDeviceInitializationBindingImpl;
import org.informatika.sirekap.databinding.ActivityMainBindingImpl;
import org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBindingImpl;
import org.informatika.sirekap.databinding.DialogSelectRetakePhotoMethodBindingImpl;
import org.informatika.sirekap.databinding.DialogVerifyBodyBindingImpl;
import org.informatika.sirekap.databinding.FragmentAprilTagConflictBindingImpl;
import org.informatika.sirekap.databinding.FragmentAutocaptureBindingImpl;
import org.informatika.sirekap.databinding.FragmentCertificateBindingImpl;
import org.informatika.sirekap.databinding.FragmentConfirmSaveFormCImageBindingImpl;
import org.informatika.sirekap.databinding.FragmentDashboardBindingImpl;
import org.informatika.sirekap.databinding.FragmentElectionDetailBindingImpl;
import org.informatika.sirekap.databinding.FragmentImageHistoryBindingImpl;
import org.informatika.sirekap.databinding.FragmentLoginBindingImpl;
import org.informatika.sirekap.databinding.FragmentLoginTpsBindingImpl;
import org.informatika.sirekap.databinding.FragmentPreviewImageBindingImpl;
import org.informatika.sirekap.databinding.FragmentSelectFormCImageBindingImpl;
import org.informatika.sirekap.databinding.FragmentSendImageBindingImpl;
import org.informatika.sirekap.databinding.FragmentSettingsBindingImpl;
import org.informatika.sirekap.databinding.FragmentSpecialOccurrenceBindingImpl;
import org.informatika.sirekap.databinding.FragmentTpsTimeBindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifyAdministrationBindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifyAdministrationHal2BindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifyAdministrationHal2PpwpBindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifySpecialOccurrenceBindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifyTabulationBindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifyTabulationPartaiBindingImpl;
import org.informatika.sirekap.databinding.FragmentVerifyWizardBindingImpl;
import org.informatika.sirekap.databinding.FragmentWitnessAttendanceListBindingImpl;
import org.informatika.sirekap.databinding.FragmentWitnessBindingImpl;
import org.informatika.sirekap.databinding.FragmentWitnessQrCodeBindingImpl;
import org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl;
import org.informatika.sirekap.databinding.FragmentWitnessUrlsBindingImpl;
import org.informatika.sirekap.databinding.FragmentWitnessVerifyAttendanceListBindingImpl;
import org.informatika.sirekap.databinding.ItemElectionCardBindingImpl;
import org.informatika.sirekap.databinding.ItemElectionImageBindingImpl;
import org.informatika.sirekap.databinding.ItemElectionPageCaptureC1BindingImpl;
import org.informatika.sirekap.databinding.ItemFormC1AdministrationBindingImpl;
import org.informatika.sirekap.databinding.ItemSpecialOccurrenceBindingImpl;
import org.informatika.sirekap.databinding.ItemWitnessAttendanceBindingImpl;
import org.informatika.sirekap.databinding.ItemWitnessBindingImpl;
import org.informatika.sirekap.databinding.ViewLockAttendanceBindingImpl;
import org.informatika.sirekap.databinding.ViewLockElectionBindingImpl;
import org.informatika.sirekap.databinding.ViewLockSpecialOccurrenceBindingImpl;
import org.informatika.sirekap.databinding.ViewSpecialOccurrenceOngoingBindingImpl;
import org.informatika.sirekap.databinding.ViewSpecialOccurrenceStartBindingImpl;
import org.informatika.sirekap.databinding.ViewVerifyWizardOngoingBindingImpl;
import org.informatika.sirekap.databinding.ViewVerifyWizardStartBindingImpl;
import org.informatika.sirekap.databinding.ViewWitnessShareBindingImpl;
import org.informatika.sirekap.databinding.ViewWitnessSharePpsBindingImpl;
import org.informatika.sirekap.databinding.ViewWitnessVerifyAttendanceListOngoingBindingImpl;
import org.informatika.sirekap.databinding.ViewWitnessVerifyAttendanceListStartBindingImpl;

/* loaded from: classes2.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYDEVICEINITIALIZATION = 1;
    private static final int LAYOUT_ACTIVITYMAIN = 2;
    private static final int LAYOUT_DIALOGCHECKFORMC1LISTITEM = 3;
    private static final int LAYOUT_DIALOGSELECTRETAKEPHOTOMETHOD = 4;
    private static final int LAYOUT_DIALOGVERIFYBODY = 5;
    private static final int LAYOUT_FRAGMENTAPRILTAGCONFLICT = 6;
    private static final int LAYOUT_FRAGMENTAUTOCAPTURE = 7;
    private static final int LAYOUT_FRAGMENTCERTIFICATE = 8;
    private static final int LAYOUT_FRAGMENTCONFIRMSAVEFORMCIMAGE = 9;
    private static final int LAYOUT_FRAGMENTDASHBOARD = 10;
    private static final int LAYOUT_FRAGMENTELECTIONDETAIL = 11;
    private static final int LAYOUT_FRAGMENTIMAGEHISTORY = 12;
    private static final int LAYOUT_FRAGMENTLOGIN = 13;
    private static final int LAYOUT_FRAGMENTLOGINTPS = 14;
    private static final int LAYOUT_FRAGMENTPREVIEWIMAGE = 15;
    private static final int LAYOUT_FRAGMENTSELECTFORMCIMAGE = 16;
    private static final int LAYOUT_FRAGMENTSENDIMAGE = 17;
    private static final int LAYOUT_FRAGMENTSETTINGS = 18;
    private static final int LAYOUT_FRAGMENTSPECIALOCCURRENCE = 19;
    private static final int LAYOUT_FRAGMENTTPSTIME = 20;
    private static final int LAYOUT_FRAGMENTVERIFYADMINISTRATION = 21;
    private static final int LAYOUT_FRAGMENTVERIFYADMINISTRATIONHAL2 = 22;
    private static final int LAYOUT_FRAGMENTVERIFYADMINISTRATIONHAL2PPWP = 23;
    private static final int LAYOUT_FRAGMENTVERIFYSPECIALOCCURRENCE = 24;
    private static final int LAYOUT_FRAGMENTVERIFYTABULATION = 25;
    private static final int LAYOUT_FRAGMENTVERIFYTABULATIONPARTAI = 26;
    private static final int LAYOUT_FRAGMENTVERIFYWIZARD = 27;
    private static final int LAYOUT_FRAGMENTWITNESS = 28;
    private static final int LAYOUT_FRAGMENTWITNESSATTENDANCELIST = 29;
    private static final int LAYOUT_FRAGMENTWITNESSQRCODE = 30;
    private static final int LAYOUT_FRAGMENTWITNESSREGISTER = 31;
    private static final int LAYOUT_FRAGMENTWITNESSURLS = 32;
    private static final int LAYOUT_FRAGMENTWITNESSVERIFYATTENDANCELIST = 33;
    private static final int LAYOUT_ITEMELECTIONCARD = 34;
    private static final int LAYOUT_ITEMELECTIONIMAGE = 35;
    private static final int LAYOUT_ITEMELECTIONPAGECAPTUREC1 = 36;
    private static final int LAYOUT_ITEMFORMC1ADMINISTRATION = 37;
    private static final int LAYOUT_ITEMSPECIALOCCURRENCE = 38;
    private static final int LAYOUT_ITEMWITNESS = 39;
    private static final int LAYOUT_ITEMWITNESSATTENDANCE = 40;
    private static final int LAYOUT_VIEWLOCKATTENDANCE = 41;
    private static final int LAYOUT_VIEWLOCKELECTION = 42;
    private static final int LAYOUT_VIEWLOCKSPECIALOCCURRENCE = 43;
    private static final int LAYOUT_VIEWSPECIALOCCURRENCEONGOING = 44;
    private static final int LAYOUT_VIEWSPECIALOCCURRENCESTART = 45;
    private static final int LAYOUT_VIEWVERIFYWIZARDONGOING = 46;
    private static final int LAYOUT_VIEWVERIFYWIZARDSTART = 47;
    private static final int LAYOUT_VIEWWITNESSSHARE = 48;
    private static final int LAYOUT_VIEWWITNESSSHAREPPS = 49;
    private static final int LAYOUT_VIEWWITNESSVERIFYATTENDANCELISTONGOING = 50;
    private static final int LAYOUT_VIEWWITNESSVERIFYATTENDANCELISTSTART = 51;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(51);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_device_initialization, 1);
        sparseIntArray.put(R.layout.activity_main, 2);
        sparseIntArray.put(R.layout.dialog_check_form_c1_list_item, 3);
        sparseIntArray.put(R.layout.dialog_select_retake_photo_method, 4);
        sparseIntArray.put(R.layout.dialog_verify_body, 5);
        sparseIntArray.put(R.layout.fragment_april_tag_conflict, 6);
        sparseIntArray.put(R.layout.fragment_autocapture, 7);
        sparseIntArray.put(R.layout.fragment_certificate, 8);
        sparseIntArray.put(R.layout.fragment_confirm_save_form_c_image, 9);
        sparseIntArray.put(R.layout.fragment_dashboard, 10);
        sparseIntArray.put(R.layout.fragment_election_detail, 11);
        sparseIntArray.put(R.layout.fragment_image_history, 12);
        sparseIntArray.put(R.layout.fragment_login, 13);
        sparseIntArray.put(R.layout.fragment_login_tps, 14);
        sparseIntArray.put(R.layout.fragment_preview_image, 15);
        sparseIntArray.put(R.layout.fragment_select_form_c_image, 16);
        sparseIntArray.put(R.layout.fragment_send_image, 17);
        sparseIntArray.put(R.layout.fragment_settings, 18);
        sparseIntArray.put(R.layout.fragment_special_occurrence, 19);
        sparseIntArray.put(R.layout.fragment_tps_time, 20);
        sparseIntArray.put(R.layout.fragment_verify_administration, 21);
        sparseIntArray.put(R.layout.fragment_verify_administration_hal2, 22);
        sparseIntArray.put(R.layout.fragment_verify_administration_hal2_ppwp, 23);
        sparseIntArray.put(R.layout.fragment_verify_special_occurrence, 24);
        sparseIntArray.put(R.layout.fragment_verify_tabulation, 25);
        sparseIntArray.put(R.layout.fragment_verify_tabulation_partai, 26);
        sparseIntArray.put(R.layout.fragment_verify_wizard, 27);
        sparseIntArray.put(R.layout.fragment_witness, 28);
        sparseIntArray.put(R.layout.fragment_witness_attendance_list, 29);
        sparseIntArray.put(R.layout.fragment_witness_qr_code, 30);
        sparseIntArray.put(R.layout.fragment_witness_register, 31);
        sparseIntArray.put(R.layout.fragment_witness_urls, 32);
        sparseIntArray.put(R.layout.fragment_witness_verify_attendance_list, 33);
        sparseIntArray.put(R.layout.item_election_card, 34);
        sparseIntArray.put(R.layout.item_election_image, 35);
        sparseIntArray.put(R.layout.item_election_page_capture_c1, 36);
        sparseIntArray.put(R.layout.item_form_c1_administration, 37);
        sparseIntArray.put(R.layout.item_special_occurrence, 38);
        sparseIntArray.put(R.layout.item_witness, 39);
        sparseIntArray.put(R.layout.item_witness_attendance, 40);
        sparseIntArray.put(R.layout.view_lock_attendance, 41);
        sparseIntArray.put(R.layout.view_lock_election, 42);
        sparseIntArray.put(R.layout.view_lock_special_occurrence, 43);
        sparseIntArray.put(R.layout.view_special_occurrence_ongoing, 44);
        sparseIntArray.put(R.layout.view_special_occurrence_start, 45);
        sparseIntArray.put(R.layout.view_verify_wizard_ongoing, 46);
        sparseIntArray.put(R.layout.view_verify_wizard_start, 47);
        sparseIntArray.put(R.layout.view_witness_share, 48);
        sparseIntArray.put(R.layout.view_witness_share_pps, 49);
        sparseIntArray.put(R.layout.view_witness_verify_attendance_list_ongoing, 50);
        sparseIntArray.put(R.layout.view_witness_verify_attendance_list_start, 51);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 1:
                if ("layout/activity_device_initialization_0".equals(tag)) {
                    return new ActivityDeviceInitializationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_device_initialization is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_main_0".equals(tag)) {
                    return new ActivityMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
            case 3:
                if ("layout/dialog_check_form_c1_list_item_0".equals(tag)) {
                    return new DialogCheckFormC1ListItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_check_form_c1_list_item is invalid. Received: " + tag);
            case 4:
                if ("layout/dialog_select_retake_photo_method_0".equals(tag)) {
                    return new DialogSelectRetakePhotoMethodBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_select_retake_photo_method is invalid. Received: " + tag);
            case 5:
                if ("layout/dialog_verify_body_0".equals(tag)) {
                    return new DialogVerifyBodyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_verify_body is invalid. Received: " + tag);
            case 6:
                if ("layout/fragment_april_tag_conflict_0".equals(tag)) {
                    return new FragmentAprilTagConflictBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_april_tag_conflict is invalid. Received: " + tag);
            case 7:
                if ("layout/fragment_autocapture_0".equals(tag)) {
                    return new FragmentAutocaptureBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_autocapture is invalid. Received: " + tag);
            case 8:
                if ("layout/fragment_certificate_0".equals(tag)) {
                    return new FragmentCertificateBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_certificate is invalid. Received: " + tag);
            case 9:
                if ("layout/fragment_confirm_save_form_c_image_0".equals(tag)) {
                    return new FragmentConfirmSaveFormCImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_confirm_save_form_c_image is invalid. Received: " + tag);
            case 10:
                if ("layout/fragment_dashboard_0".equals(tag)) {
                    return new FragmentDashboardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dashboard is invalid. Received: " + tag);
            case 11:
                if ("layout/fragment_election_detail_0".equals(tag)) {
                    return new FragmentElectionDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_election_detail is invalid. Received: " + tag);
            case 12:
                if ("layout/fragment_image_history_0".equals(tag)) {
                    return new FragmentImageHistoryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_image_history is invalid. Received: " + tag);
            case 13:
                if ("layout/fragment_login_0".equals(tag)) {
                    return new FragmentLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
            case 14:
                if ("layout/fragment_login_tps_0".equals(tag)) {
                    return new FragmentLoginTpsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_login_tps is invalid. Received: " + tag);
            case 15:
                if ("layout/fragment_preview_image_0".equals(tag)) {
                    return new FragmentPreviewImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_preview_image is invalid. Received: " + tag);
            case 16:
                if ("layout/fragment_select_form_c_image_0".equals(tag)) {
                    return new FragmentSelectFormCImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_select_form_c_image is invalid. Received: " + tag);
            case 17:
                if ("layout/fragment_send_image_0".equals(tag)) {
                    return new FragmentSendImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_send_image is invalid. Received: " + tag);
            case 18:
                if ("layout/fragment_settings_0".equals(tag)) {
                    return new FragmentSettingsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_settings is invalid. Received: " + tag);
            case 19:
                if ("layout/fragment_special_occurrence_0".equals(tag)) {
                    return new FragmentSpecialOccurrenceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_special_occurrence is invalid. Received: " + tag);
            case 20:
                if ("layout/fragment_tps_time_0".equals(tag)) {
                    return new FragmentTpsTimeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_tps_time is invalid. Received: " + tag);
            case 21:
                if ("layout/fragment_verify_administration_0".equals(tag)) {
                    return new FragmentVerifyAdministrationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_administration is invalid. Received: " + tag);
            case 22:
                if ("layout/fragment_verify_administration_hal2_0".equals(tag)) {
                    return new FragmentVerifyAdministrationHal2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_administration_hal2 is invalid. Received: " + tag);
            case 23:
                if ("layout/fragment_verify_administration_hal2_ppwp_0".equals(tag)) {
                    return new FragmentVerifyAdministrationHal2PpwpBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_administration_hal2_ppwp is invalid. Received: " + tag);
            case 24:
                if ("layout/fragment_verify_special_occurrence_0".equals(tag)) {
                    return new FragmentVerifySpecialOccurrenceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_special_occurrence is invalid. Received: " + tag);
            case 25:
                if ("layout/fragment_verify_tabulation_0".equals(tag)) {
                    return new FragmentVerifyTabulationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_tabulation is invalid. Received: " + tag);
            case 26:
                if ("layout/fragment_verify_tabulation_partai_0".equals(tag)) {
                    return new FragmentVerifyTabulationPartaiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_tabulation_partai is invalid. Received: " + tag);
            case 27:
                if ("layout/fragment_verify_wizard_0".equals(tag)) {
                    return new FragmentVerifyWizardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_verify_wizard is invalid. Received: " + tag);
            case 28:
                if ("layout/fragment_witness_0".equals(tag)) {
                    return new FragmentWitnessBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_witness is invalid. Received: " + tag);
            case 29:
                if ("layout/fragment_witness_attendance_list_0".equals(tag)) {
                    return new FragmentWitnessAttendanceListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_witness_attendance_list is invalid. Received: " + tag);
            case 30:
                if ("layout/fragment_witness_qr_code_0".equals(tag)) {
                    return new FragmentWitnessQrCodeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_witness_qr_code is invalid. Received: " + tag);
            case 31:
                if ("layout/fragment_witness_register_0".equals(tag)) {
                    return new FragmentWitnessRegisterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_witness_register is invalid. Received: " + tag);
            case 32:
                if ("layout/fragment_witness_urls_0".equals(tag)) {
                    return new FragmentWitnessUrlsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_witness_urls is invalid. Received: " + tag);
            case 33:
                if ("layout/fragment_witness_verify_attendance_list_0".equals(tag)) {
                    return new FragmentWitnessVerifyAttendanceListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_witness_verify_attendance_list is invalid. Received: " + tag);
            case 34:
                if ("layout/item_election_card_0".equals(tag)) {
                    return new ItemElectionCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_election_card is invalid. Received: " + tag);
            case 35:
                if ("layout/item_election_image_0".equals(tag)) {
                    return new ItemElectionImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_election_image is invalid. Received: " + tag);
            case 36:
                if ("layout/item_election_page_capture_c1_0".equals(tag)) {
                    return new ItemElectionPageCaptureC1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_election_page_capture_c1 is invalid. Received: " + tag);
            case 37:
                if ("layout/item_form_c1_administration_0".equals(tag)) {
                    return new ItemFormC1AdministrationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_form_c1_administration is invalid. Received: " + tag);
            case 38:
                if ("layout/item_special_occurrence_0".equals(tag)) {
                    return new ItemSpecialOccurrenceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_special_occurrence is invalid. Received: " + tag);
            case 39:
                if ("layout/item_witness_0".equals(tag)) {
                    return new ItemWitnessBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_witness is invalid. Received: " + tag);
            case 40:
                if ("layout/item_witness_attendance_0".equals(tag)) {
                    return new ItemWitnessAttendanceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_witness_attendance is invalid. Received: " + tag);
            case 41:
                if ("layout/view_lock_attendance_0".equals(tag)) {
                    return new ViewLockAttendanceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_lock_attendance is invalid. Received: " + tag);
            case 42:
                if ("layout/view_lock_election_0".equals(tag)) {
                    return new ViewLockElectionBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_lock_election is invalid. Received: " + tag);
            case 43:
                if ("layout/view_lock_special_occurrence_0".equals(tag)) {
                    return new ViewLockSpecialOccurrenceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_lock_special_occurrence is invalid. Received: " + tag);
            case 44:
                if ("layout/view_special_occurrence_ongoing_0".equals(tag)) {
                    return new ViewSpecialOccurrenceOngoingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_special_occurrence_ongoing is invalid. Received: " + tag);
            case 45:
                if ("layout/view_special_occurrence_start_0".equals(tag)) {
                    return new ViewSpecialOccurrenceStartBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_special_occurrence_start is invalid. Received: " + tag);
            case 46:
                if ("layout/view_verify_wizard_ongoing_0".equals(tag)) {
                    return new ViewVerifyWizardOngoingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_verify_wizard_ongoing is invalid. Received: " + tag);
            case 47:
                if ("layout/view_verify_wizard_start_0".equals(tag)) {
                    return new ViewVerifyWizardStartBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_verify_wizard_start is invalid. Received: " + tag);
            case 48:
                if ("layout/view_witness_share_0".equals(tag)) {
                    return new ViewWitnessShareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_witness_share is invalid. Received: " + tag);
            case 49:
                if ("layout/view_witness_share_pps_0".equals(tag)) {
                    return new ViewWitnessSharePpsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_witness_share_pps is invalid. Received: " + tag);
            case 50:
                if ("layout/view_witness_verify_attendance_list_ongoing_0".equals(tag)) {
                    return new ViewWitnessVerifyAttendanceListOngoingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_witness_verify_attendance_list_ongoing is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        if (internalId != 51) {
            return null;
        }
        if ("layout/view_witness_verify_attendance_list_start_0".equals(tag)) {
            return new ViewWitnessVerifyAttendanceListStartBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for view_witness_verify_attendance_list_start is invalid. Received: " + tag);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int i = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (i > 0) {
            Object tag = view.getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
            int i2 = (i - 1) / 50;
            if (i2 != 0) {
                if (i2 != 1) {
                    return null;
                }
                return internalGetViewDataBinding1(component, view, i, tag);
            }
            return internalGetViewDataBinding0(component, view, i, tag);
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId) <= 0 || views[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        Integer num;
        if (tag == null || (num = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int localId) {
        return InnerBrLookup.sKeys.get(localId);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    /* loaded from: classes2.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(89);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "allValid");
            sparseArray.put(2, "attendance");
            sparseArray.put(3, "authRequestUseCase");
            sparseArray.put(4, "comment");
            sparseArray.put(5, "commentTouched");
            sparseArray.put(6, "commentValid");
            sparseArray.put(7, "croppedPhotoPath");
            sparseArray.put(8, "electionImage");
            sparseArray.put(9, "electionJmlLembar");
            sparseArray.put(10, "electionPage");
            sparseArray.put(11, "electionPageWithRelation");
            sparseArray.put(12, "electionWithRelation");
            sparseArray.put(13, "filterJenisPemilihan");
            sparseArray.put(14, "formState");
            sparseArray.put(15, "fragmentViewModel");
            sparseArray.put(16, "getElectionPageUseCase");
            sparseArray.put(17, "getElectionPageUseCaseAprilTag");
            sparseArray.put(18, "getElectionPageUseCaseManual");
            sparseArray.put(19, "getElectionUseCase");
            sparseArray.put(20, "getListTPSUseCase");
            sparseArray.put(21, "hideCommentField");
            sparseArray.put(22, "idPartaiDpr");
            sparseArray.put(23, "idPartaiDprTouched");
            sparseArray.put(24, "idPartaiDprValid");
            sparseArray.put(25, "idPartaiDprdk");
            sparseArray.put(26, "idPartaiDprdkTouched");
            sparseArray.put(27, "idPartaiDprdkValid");
            sparseArray.put(28, "idPartaiDprdp");
            sparseArray.put(29, "idPartaiDprdpTouched");
            sparseArray.put(30, "idPartaiDprdpValid");
            sparseArray.put(31, "idPaslonDpd");
            sparseArray.put(32, "idPaslonDpdTouched");
            sparseArray.put(33, "idPaslonDpdValid");
            sparseArray.put(34, "idPaslonPilgub");
            sparseArray.put(35, "idPaslonPilgubTouched");
            sparseArray.put(36, "idPaslonPilgubValid");
            sparseArray.put(37, "idPaslonPilwalkot");
            sparseArray.put(38, "idPaslonPilwalkotTouched");
            sparseArray.put(39, "idPaslonPilwalkotValid");
            sparseArray.put(40, "idPaslonPresiden");
            sparseArray.put(41, "idPaslonPresidenTouched");
            sparseArray.put(42, "idPaslonPresidenValid");
            sparseArray.put(43, "isDone");
            sparseArray.put(44, "isEmpty");
            sparseArray.put(45, "isLoading");
            sparseArray.put(46, "isLoadingZip");
            sparseArray.put(47, "isZipped");
            sparseArray.put(48, "item");
            sparseArray.put(49, "jenisPemeriksa");
            sparseArray.put(50, "jenisPemeriksaOptions");
            sparseArray.put(51, "jenisPemeriksaTouched");
            sparseArray.put(52, "jenisPemeriksaValid");
            sparseArray.put(53, "jenisPemeriksaValue");
            sparseArray.put(54, "jenisPemilihanDpd");
            sparseArray.put(55, "jenisPemilihanDpr");
            sparseArray.put(56, "jenisPemilihanDprdk");
            sparseArray.put(57, "jenisPemilihanDprdp");
            sparseArray.put(58, "jenisPemilihanPkwkk");
            sparseArray.put(59, "jenisPemilihanPkwkp");
            sparseArray.put(60, "jenisPemilihanPresiden");
            sparseArray.put(61, "jenisPemilihanTouched");
            sparseArray.put(62, "jenisPemilihanValid");
            sparseArray.put(63, "loginTpsFormState");
            sparseArray.put(64, "loginViewModel");
            sparseArray.put(65, "mainViewModel");
            sparseArray.put(66, "name");
            sparseArray.put(67, "nameTouched");
            sparseArray.put(68, "nameValid");
            sparseArray.put(69, "nik");
            sparseArray.put(70, "nikTouched");
            sparseArray.put(71, "nikValid");
            sparseArray.put(72, "noHandphone");
            sparseArray.put(73, "noHandphoneTouched");
            sparseArray.put(74, "noHandphoneValid");
            sparseArray.put(75, "nomorHalaman");
            sparseArray.put(76, "partai");
            sparseArray.put(77, "paslon");
            sparseArray.put(78, "qrCode");
            sparseArray.put(79, "shareWitnessUrlUseCase");
            sparseArray.put(80, "showCorrection");
            sparseArray.put(81, "tps");
            sparseArray.put(82, "tpsTouched");
            sparseArray.put(83, "tpsValid");
            sparseArray.put(84, "url");
            sparseArray.put(85, "user");
            sparseArray.put(86, "verifyOption");
            sparseArray.put(87, "viewModel");
            sparseArray.put(88, "witness");
        }
    }

    /* loaded from: classes2.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(51);
            sKeys = hashMap;
            hashMap.put("layout/activity_device_initialization_0", Integer.valueOf(R.layout.activity_device_initialization));
            hashMap.put("layout/activity_main_0", Integer.valueOf(R.layout.activity_main));
            hashMap.put("layout/dialog_check_form_c1_list_item_0", Integer.valueOf(R.layout.dialog_check_form_c1_list_item));
            hashMap.put("layout/dialog_select_retake_photo_method_0", Integer.valueOf(R.layout.dialog_select_retake_photo_method));
            hashMap.put("layout/dialog_verify_body_0", Integer.valueOf(R.layout.dialog_verify_body));
            hashMap.put("layout/fragment_april_tag_conflict_0", Integer.valueOf(R.layout.fragment_april_tag_conflict));
            hashMap.put("layout/fragment_autocapture_0", Integer.valueOf(R.layout.fragment_autocapture));
            hashMap.put("layout/fragment_certificate_0", Integer.valueOf(R.layout.fragment_certificate));
            hashMap.put("layout/fragment_confirm_save_form_c_image_0", Integer.valueOf(R.layout.fragment_confirm_save_form_c_image));
            hashMap.put("layout/fragment_dashboard_0", Integer.valueOf(R.layout.fragment_dashboard));
            hashMap.put("layout/fragment_election_detail_0", Integer.valueOf(R.layout.fragment_election_detail));
            hashMap.put("layout/fragment_image_history_0", Integer.valueOf(R.layout.fragment_image_history));
            hashMap.put("layout/fragment_login_0", Integer.valueOf(R.layout.fragment_login));
            hashMap.put("layout/fragment_login_tps_0", Integer.valueOf(R.layout.fragment_login_tps));
            hashMap.put("layout/fragment_preview_image_0", Integer.valueOf(R.layout.fragment_preview_image));
            hashMap.put("layout/fragment_select_form_c_image_0", Integer.valueOf(R.layout.fragment_select_form_c_image));
            hashMap.put("layout/fragment_send_image_0", Integer.valueOf(R.layout.fragment_send_image));
            hashMap.put("layout/fragment_settings_0", Integer.valueOf(R.layout.fragment_settings));
            hashMap.put("layout/fragment_special_occurrence_0", Integer.valueOf(R.layout.fragment_special_occurrence));
            hashMap.put("layout/fragment_tps_time_0", Integer.valueOf(R.layout.fragment_tps_time));
            hashMap.put("layout/fragment_verify_administration_0", Integer.valueOf(R.layout.fragment_verify_administration));
            hashMap.put("layout/fragment_verify_administration_hal2_0", Integer.valueOf(R.layout.fragment_verify_administration_hal2));
            hashMap.put("layout/fragment_verify_administration_hal2_ppwp_0", Integer.valueOf(R.layout.fragment_verify_administration_hal2_ppwp));
            hashMap.put("layout/fragment_verify_special_occurrence_0", Integer.valueOf(R.layout.fragment_verify_special_occurrence));
            hashMap.put("layout/fragment_verify_tabulation_0", Integer.valueOf(R.layout.fragment_verify_tabulation));
            hashMap.put("layout/fragment_verify_tabulation_partai_0", Integer.valueOf(R.layout.fragment_verify_tabulation_partai));
            hashMap.put("layout/fragment_verify_wizard_0", Integer.valueOf(R.layout.fragment_verify_wizard));
            hashMap.put("layout/fragment_witness_0", Integer.valueOf(R.layout.fragment_witness));
            hashMap.put("layout/fragment_witness_attendance_list_0", Integer.valueOf(R.layout.fragment_witness_attendance_list));
            hashMap.put("layout/fragment_witness_qr_code_0", Integer.valueOf(R.layout.fragment_witness_qr_code));
            hashMap.put("layout/fragment_witness_register_0", Integer.valueOf(R.layout.fragment_witness_register));
            hashMap.put("layout/fragment_witness_urls_0", Integer.valueOf(R.layout.fragment_witness_urls));
            hashMap.put("layout/fragment_witness_verify_attendance_list_0", Integer.valueOf(R.layout.fragment_witness_verify_attendance_list));
            hashMap.put("layout/item_election_card_0", Integer.valueOf(R.layout.item_election_card));
            hashMap.put("layout/item_election_image_0", Integer.valueOf(R.layout.item_election_image));
            hashMap.put("layout/item_election_page_capture_c1_0", Integer.valueOf(R.layout.item_election_page_capture_c1));
            hashMap.put("layout/item_form_c1_administration_0", Integer.valueOf(R.layout.item_form_c1_administration));
            hashMap.put("layout/item_special_occurrence_0", Integer.valueOf(R.layout.item_special_occurrence));
            hashMap.put("layout/item_witness_0", Integer.valueOf(R.layout.item_witness));
            hashMap.put("layout/item_witness_attendance_0", Integer.valueOf(R.layout.item_witness_attendance));
            hashMap.put("layout/view_lock_attendance_0", Integer.valueOf(R.layout.view_lock_attendance));
            hashMap.put("layout/view_lock_election_0", Integer.valueOf(R.layout.view_lock_election));
            hashMap.put("layout/view_lock_special_occurrence_0", Integer.valueOf(R.layout.view_lock_special_occurrence));
            hashMap.put("layout/view_special_occurrence_ongoing_0", Integer.valueOf(R.layout.view_special_occurrence_ongoing));
            hashMap.put("layout/view_special_occurrence_start_0", Integer.valueOf(R.layout.view_special_occurrence_start));
            hashMap.put("layout/view_verify_wizard_ongoing_0", Integer.valueOf(R.layout.view_verify_wizard_ongoing));
            hashMap.put("layout/view_verify_wizard_start_0", Integer.valueOf(R.layout.view_verify_wizard_start));
            hashMap.put("layout/view_witness_share_0", Integer.valueOf(R.layout.view_witness_share));
            hashMap.put("layout/view_witness_share_pps_0", Integer.valueOf(R.layout.view_witness_share_pps));
            hashMap.put("layout/view_witness_verify_attendance_list_ongoing_0", Integer.valueOf(R.layout.view_witness_verify_attendance_list_ongoing));
            hashMap.put("layout/view_witness_verify_attendance_list_start_0", Integer.valueOf(R.layout.view_witness_verify_attendance_list_start));
        }
    }
}
