package org.informatika.sirekap.repository;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.support.Resource;

/* compiled from: FormC1Repository.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH&J:\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J:\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH&J:\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH&J8\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00130\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0005H&J8\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00130\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0005H&J8\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00130\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0005H&J8\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00130\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0005H&J8\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00130\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0005H&J*\u0010\u001f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0 \u0018\u00010\u00130\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050 H&J*\u0010\"\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0 \u0018\u00010\u00130\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050 H&J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H&J0\u0010&\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0 2\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0 H&J\u0018\u0010*\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,H&J1\u0010-\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020)2\b\u0010/\u001a\u0004\u0018\u00010\n2\b\u00100\u001a\u0004\u0018\u00010)H&¢\u0006\u0002\u00101J0\u00102\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0 2\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0 H&Jv\u00103\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00130\u00122\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020)2\u0006\u00106\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\n2\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0 2\u0006\u00107\u001a\u00020\u00052\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0 2\u0006\u00108\u001a\u00020\u0005H&¨\u00069"}, d2 = {"Lorg/informatika/sirekap/repository/FormC1Repository;", "", "createEmptyFormC1Administration", "", "idImage", "", "jenisPemilihan", "correctedBitmap", "Landroid/graphics/Bitmap;", "isLn", "", "isPos", "maxAttemptReach", "createEmptyFormC1AdministrationHal2", "createEmptyFormC1AdministrationHal2Ppwp", "createEmptyFormC1Tabulation", "createEmptyFormC1TabulationPartai", "getFormC1Administration", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "isFetchApi", "kodeTpsOverride", "getFormC1AdministrationHal2", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "getFormC1AdministrationHal2Ppwp", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "getFormC1Tabulation", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "getFormC1TabulationPartai", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "getListFormC1Tabulation", "", "idImages", "getListFormC1TabulationPartai", "saveFormC1KesesuaianAdministration", "formC1KesesuaianAdministration", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "saveFormC1KesesuaianAdministrationHal2", "isSesuaiPerItem", "koreksiPerItem", "", "saveFormC1KesesuaianAdministrationHal2New", "formC1KesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "saveFormC1KesesuaianTabulationCandidateVote", FirebaseAnalytics.Param.INDEX, "isSesuai", "koreksi", "(Ljava/lang/String;ILjava/lang/Boolean;Ljava/lang/Integer;)V", "saveFormC1KesesuaianTabulationPartai", "verifyFormC1", "deviceId", "formType", "electionPageId", "komentar", "kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FormC1Repository {
    void createEmptyFormC1Administration(String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3);

    void createEmptyFormC1AdministrationHal2(String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3);

    void createEmptyFormC1AdministrationHal2Ppwp(String str, String str2);

    void createEmptyFormC1Tabulation(String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3);

    void createEmptyFormC1TabulationPartai(String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3);

    LiveData<Resource<FormC1AdministrationComplete>> getFormC1Administration(String str, String str2, boolean z, String str3);

    LiveData<Resource<FormC1AdministrationHal2Complete>> getFormC1AdministrationHal2(String str, String str2, boolean z, String str3);

    LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> getFormC1AdministrationHal2Ppwp(String str, String str2, boolean z, String str3);

    LiveData<Resource<FormC1TabulationComplete>> getFormC1Tabulation(String str, String str2, boolean z, String str3);

    LiveData<Resource<FormC1TabulationPartaiComplete>> getFormC1TabulationPartai(String str, String str2, boolean z, String str3);

    LiveData<Resource<List<FormC1TabulationComplete>>> getListFormC1Tabulation(List<String> list);

    LiveData<Resource<List<FormC1TabulationPartaiComplete>>> getListFormC1TabulationPartai(List<String> list);

    void saveFormC1KesesuaianAdministration(String str, FormC1KesesuaianAdministration formC1KesesuaianAdministration);

    void saveFormC1KesesuaianAdministrationHal2(String str, List<Boolean> list, List<Integer> list2);

    void saveFormC1KesesuaianAdministrationHal2New(String str, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2);

    void saveFormC1KesesuaianTabulationCandidateVote(String str, int i, Boolean bool, Integer num);

    void saveFormC1KesesuaianTabulationPartai(String str, List<Boolean> list, List<Integer> list2);

    LiveData<Resource<FormC1AdministrationComplete>> verifyFormC1(String str, String str2, int i, String str3, String str4, boolean z, List<Boolean> list, String str5, List<Integer> list2, String str6);

    /* compiled from: FormC1Repository.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ LiveData getFormC1Administration$default(FormC1Repository formC1Repository, String str, String str2, boolean z, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    z = true;
                }
                return formC1Repository.getFormC1Administration(str, str2, z, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFormC1Administration");
        }

        public static /* synthetic */ void createEmptyFormC1Administration$default(FormC1Repository formC1Repository, String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createEmptyFormC1Administration");
            }
            if ((i & 32) != 0) {
                z3 = false;
            }
            formC1Repository.createEmptyFormC1Administration(str, str2, bitmap, z, z2, z3);
        }

        public static /* synthetic */ LiveData getFormC1Tabulation$default(FormC1Repository formC1Repository, String str, String str2, boolean z, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    z = true;
                }
                return formC1Repository.getFormC1Tabulation(str, str2, z, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFormC1Tabulation");
        }

        public static /* synthetic */ void createEmptyFormC1Tabulation$default(FormC1Repository formC1Repository, String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createEmptyFormC1Tabulation");
            }
            if ((i & 32) != 0) {
                z3 = false;
            }
            formC1Repository.createEmptyFormC1Tabulation(str, str2, bitmap, z, z2, z3);
        }

        public static /* synthetic */ LiveData getFormC1TabulationPartai$default(FormC1Repository formC1Repository, String str, String str2, boolean z, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    z = true;
                }
                return formC1Repository.getFormC1TabulationPartai(str, str2, z, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFormC1TabulationPartai");
        }

        public static /* synthetic */ void createEmptyFormC1TabulationPartai$default(FormC1Repository formC1Repository, String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createEmptyFormC1TabulationPartai");
            }
            if ((i & 32) != 0) {
                z3 = false;
            }
            formC1Repository.createEmptyFormC1TabulationPartai(str, str2, bitmap, z, z2, z3);
        }

        public static /* synthetic */ LiveData getFormC1AdministrationHal2$default(FormC1Repository formC1Repository, String str, String str2, boolean z, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    z = true;
                }
                return formC1Repository.getFormC1AdministrationHal2(str, str2, z, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFormC1AdministrationHal2");
        }

        public static /* synthetic */ void createEmptyFormC1AdministrationHal2$default(FormC1Repository formC1Repository, String str, String str2, Bitmap bitmap, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createEmptyFormC1AdministrationHal2");
            }
            if ((i & 32) != 0) {
                z3 = false;
            }
            formC1Repository.createEmptyFormC1AdministrationHal2(str, str2, bitmap, z, z2, z3);
        }

        public static /* synthetic */ LiveData getFormC1AdministrationHal2Ppwp$default(FormC1Repository formC1Repository, String str, String str2, boolean z, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    z = true;
                }
                return formC1Repository.getFormC1AdministrationHal2Ppwp(str, str2, z, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFormC1AdministrationHal2Ppwp");
        }
    }
}
