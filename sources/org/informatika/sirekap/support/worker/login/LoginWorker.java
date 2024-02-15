package org.informatika.sirekap.support.worker.login;

import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.EntryPointAccessors;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.informatika.sirekap.api.response.KeyCloakUserInfoApiResponse;
import org.informatika.sirekap.api.response.WilayahApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.UserDao;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.Kecamatan;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.KotaKabupaten;
import org.informatika.sirekap.model.Provinsi;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.repository.DefaultBackgroundProcessRepository;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.WitnessUtil;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: LoginWorker.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J0\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J0\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0004J(\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0010H\u0002J8\u0010$\u001a\u00020\u001c2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&2\u0006\u0010(\u001a\u00020\u0010H\u0002J\u001e\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\nJ*\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010*\u001a\u00020+2\u0006\u00100\u001a\u0002012\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\nH\u0002J*\u00102\u001a\u001c\u0012\u0004\u0012\u000204\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001703\u0018\u0001032\u0006\u00105\u001a\u00020\u0010H\u0002JP\u00106\u001a\u00020\u001c2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020+082\u0006\u0010,\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\n2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020:082\u0006\u0010\u0013\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lorg/informatika/sirekap/support/worker/login/LoginWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getAppDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "getAuthRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "getBackgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "getElectionId", "", "kodeTps", "electionPemilihan", "electionType", "getElectionPageId", "electionId", "jenisImage", "", "noLembar", "getEncryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "insertElection", "", "jmlLembar", "jenisPemilihan", "insertError", "idImage", "formType", "errorType", "error", "insertPemilihanData", "images", "", "Lorg/informatika/sirekap/api/response/WilayahApiResponse$DataApiResponse$ImageApiResponse;", "profile", "insertTpsElectionToDatabase", "item", "Lorg/informatika/sirekap/api/response/WilayahApiResponse;", "kodeTpsProfile", "database", "insertUserToDatabase", "Lorg/informatika/sirekap/model/User;", "activeProfile", "Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse$KeyCloakUserInfoProfileApiResponse;", "parseDateTimeNew", "Lkotlin/Pair;", "Ljava/util/Calendar;", "waktuString", "saveTpsElectionData", "mapDataPemilihan", "", "witnesses", "Lorg/informatika/sirekap/model/Witness;", "Companion", "LoginWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LoginWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "LoginWorker";
    private final Context appContext;

    /* compiled from: LoginWorker.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/support/worker/login/LoginWorker$LoginWorkerEntryPoint;", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "authRepository", "Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface LoginWorkerEntryPoint {
        AppDatabase appDatabase();

        DefaultAuthRepository authRepository();

        DefaultBackgroundProcessRepository backgroundProcessRepository();

        EncryptedSharedPreferences encryptedSharedPreferences();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final AuthRepository getAuthRepository() {
        return ((LoginWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, LoginWorkerEntryPoint.class)).authRepository();
    }

    private final BackgroundProcessRepository getBackgroundProcessRepository() {
        return ((LoginWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, LoginWorkerEntryPoint.class)).backgroundProcessRepository();
    }

    private final AppDatabase getAppDatabase() {
        return ((LoginWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, LoginWorkerEntryPoint.class)).appDatabase();
    }

    private final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return ((LoginWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, LoginWorkerEntryPoint.class)).encryptedSharedPreferences();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        ArrayList arrayList;
        String str;
        String string = getInputData().getString("kodeTps");
        AppDatabase appDatabase = getAppDatabase();
        AuthRepository authRepository = getAuthRepository();
        EncryptedSharedPreferences encryptedSharedPreferences = getEncryptedSharedPreferences();
        BackgroundProcessRepository backgroundProcessRepository = getBackgroundProcessRepository();
        ActiveProfile activeProfile = authRepository.getActiveProfile();
        backgroundProcessRepository.deleteById("login");
        String str2 = null;
        backgroundProcessRepository.insertAll(CollectionsKt.listOf(new BackgroundProcess("login", new Date().getTime(), null, null, null, 28, null)));
        ArrayList arrayList2 = new ArrayList();
        Intrinsics.checkNotNull(activeProfile);
        String nama_profil = activeProfile.getNama_profil();
        String kode_wilayah = activeProfile.getKode_wilayah();
        Intrinsics.checkNotNull(kode_wilayah);
        String role = activeProfile.getRole();
        Intrinsics.checkNotNull(role);
        arrayList2.add(new KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse(nama_profil, kode_wilayah, role, activeProfile.getStart(), activeProfile.getEnd()));
        String str3 = "-";
        if (Intrinsics.areEqual(activeProfile.getRoleUser(), "kpps") || Intrinsics.areEqual(activeProfile.getRoleUser(), User.USER_ROLE_KPPS_LN)) {
            try {
                List<KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse> profilelist = authRepository.getAllProfiles().getProfilelist();
                try {
                    if (profilelist == null) {
                        str2 = "-";
                        arrayList = CollectionsKt.emptyList();
                    } else {
                        try {
                            ArrayList arrayList3 = new ArrayList();
                            Iterator it = profilelist.iterator();
                            while (it.hasNext()) {
                                Object next = it.next();
                                KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse keyCloakUserInfoProfileApiResponse = (KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse) next;
                                Iterator it2 = it;
                                String str4 = str3;
                                if ((Intrinsics.areEqual(keyCloakUserInfoProfileApiResponse.getRole(), "kpps") || Intrinsics.areEqual(keyCloakUserInfoProfileApiResponse.getRole(), User.USER_ROLE_KPPS_LN)) && !(Intrinsics.areEqual(activeProfile.getNama_profil(), keyCloakUserInfoProfileApiResponse.getNama_profil()) && Intrinsics.areEqual(activeProfile.getRole(), keyCloakUserInfoProfileApiResponse.getRole()) && Intrinsics.areEqual(activeProfile.getKode_wilayah(), keyCloakUserInfoProfileApiResponse.getKode_wilayah())) && Intrinsics.areEqual(keyCloakUserInfoProfileApiResponse.getKode_wilayah(), activeProfile.getKode_wilayah())) {
                                    arrayList3.add(next);
                                }
                                it = it2;
                                str3 = str4;
                            }
                            str2 = str3;
                            arrayList = arrayList3;
                        } catch (Exception e) {
                            e = e;
                            str2 = str3;
                            e.printStackTrace();
                            String message = e.getMessage();
                            Log.wtf(TAG, message == null ? str2 : message);
                            FirebaseCrashlytics.getInstance().recordException(new Exception("LoginWorker: " + e.getMessage()));
                            backgroundProcessRepository.markAsFailed("login", new Date().getTime(), String.valueOf(e.getMessage()));
                            ListenableWorker.Result failure = ListenableWorker.Result.failure();
                            Intrinsics.checkNotNullExpressionValue(failure, "failure()");
                            return failure;
                        }
                    }
                    arrayList2.addAll(arrayList);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "-";
            }
        } else {
            str2 = "-";
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = arrayList2;
        Iterator it3 = arrayList6.iterator();
        while (true) {
            boolean hasNext = it3.hasNext();
            str = Election.ELECTION_PEMILIHAN_PRESIDEN;
            if (!hasNext) {
                break;
            }
            KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse keyCloakUserInfoProfileApiResponse2 = (KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse) it3.next();
            String kode_wilayah2 = keyCloakUserInfoProfileApiResponse2.getKode_wilayah();
            String str5 = string;
            if (!(str5 == null || StringsKt.isBlank(str5))) {
                kode_wilayah2 = string;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator it4 = it3;
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            EncryptedSharedPreferences encryptedSharedPreferences2 = encryptedSharedPreferences;
            try {
                String str6 = string;
                WilayahApiResponse loginSync = getAuthRepository().loginSync(kode_wilayah2, Election.ELECTION_PEMILIHAN_PRESIDEN, keyCloakUserInfoProfileApiResponse2.getNama_profil());
                linkedHashMap.put(Election.ELECTION_PEMILIHAN_PRESIDEN, loginSync);
                ArrayList arrayList7 = new ArrayList();
                if (loginSync.getData().getPemilihanPdpd() != null) {
                    arrayList7.add(Election.ELECTION_PEMILIHAN_DPD);
                }
                if (loginSync.getData().getPemilihanPdpr() != null) {
                    arrayList7.add(Election.ELECTION_PEMILIHAN_DPR);
                }
                if (loginSync.getData().getPemilihanPdprdp() != null) {
                    arrayList7.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
                }
                if (loginSync.getData().getPemilihanPdprdk() != null) {
                    arrayList7.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
                }
                for (Iterator it5 = arrayList7.iterator(); it5.hasNext(); it5 = it5) {
                    String str7 = (String) it5.next();
                    linkedHashMap.put(str7, getAuthRepository().loginSync(kode_wilayah2, str7, keyCloakUserInfoProfileApiResponse2.getNama_profil()));
                }
                arrayList4.add(linkedHashMap);
                arrayList5.add(linkedHashMap2);
                encryptedSharedPreferences = encryptedSharedPreferences2;
                it3 = it4;
                string = str6;
            } catch (Exception e4) {
                e4.printStackTrace();
                String message2 = e4.getMessage();
                Log.wtf(TAG, message2 == null ? str2 : message2);
                FirebaseCrashlytics.getInstance().recordException(new Exception("LoginWorker: " + e4.getMessage()));
                backgroundProcessRepository.markAsFailed("login", new Date().getTime(), String.valueOf(e4.getMessage()));
                ListenableWorker.Result failure2 = ListenableWorker.Result.failure();
                Intrinsics.checkNotNullExpressionValue(failure2, "failure()");
                return failure2;
            }
        }
        String str8 = string;
        EncryptedSharedPreferences encryptedSharedPreferences3 = encryptedSharedPreferences;
        String stringEncrypted = getEncryptedSharedPreferences().getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str9 = stringEncrypted;
        if (str9 == null || StringsKt.isBlank(str9)) {
            stringEncrypted = "R";
        }
        appDatabase.userDao().deleteAll();
        Iterator it6 = arrayList6.iterator();
        User user = null;
        int i = 0;
        while (it6.hasNext()) {
            Object next2 = it6.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse keyCloakUserInfoProfileApiResponse3 = (KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse) next2;
            Map<String, Witness> map = (Map) arrayList5.get(i);
            Map<String, WilayahApiResponse> map2 = (Map) arrayList4.get(i);
            String kode_wilayah3 = keyCloakUserInfoProfileApiResponse3.getKode_wilayah();
            String str10 = str8;
            if (!(str10 == null || StringsKt.isBlank(str10))) {
                kode_wilayah3 = str8;
            }
            String generateKodeTps = ElectionUtil.generateKodeTps(kode_wilayah3, keyCloakUserInfoProfileApiResponse3.getNama_profil());
            Iterator it7 = it6;
            ArrayList arrayList8 = arrayList5;
            String str11 = str;
            BackgroundProcessRepository backgroundProcessRepository2 = backgroundProcessRepository;
            saveTpsElectionData(map2, generateKodeTps, keyCloakUserInfoProfileApiResponse3.getNama_profil(), appDatabase, map, stringEncrypted);
            Object obj = ((Map) arrayList4.get(i)).get(str11);
            Intrinsics.checkNotNull(obj);
            User insertUserToDatabase = insertUserToDatabase((WilayahApiResponse) obj, keyCloakUserInfoProfileApiResponse3, generateKodeTps, appDatabase);
            if (i == 0 && insertUserToDatabase != null) {
                user = insertUserToDatabase;
            }
            str = str11;
            i = i2;
            it6 = it7;
            arrayList5 = arrayList8;
            backgroundProcessRepository = backgroundProcessRepository2;
        }
        Intrinsics.checkNotNull(user);
        encryptedSharedPreferences3.putStringEncrypted(SharedPreferencesModule.SP_KEY_USER_ID, user.getId());
        backgroundProcessRepository.markAsSuccess("login", new Date().getTime());
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success()");
        return success;
    }

    private final void saveTpsElectionData(final Map<String, WilayahApiResponse> map, final String str, final String str2, final AppDatabase appDatabase, final Map<String, Witness> map2, final String str3) {
        WilayahApiResponse wilayahApiResponse = map.get(Election.ELECTION_PEMILIHAN_PRESIDEN);
        Intrinsics.checkNotNull(wilayahApiResponse);
        insertTpsElectionToDatabase(wilayahApiResponse, str, appDatabase);
        final Function2<String, WilayahApiResponse, Unit> function2 = new Function2<String, WilayahApiResponse, Unit>() { // from class: org.informatika.sirekap.support.worker.login.LoginWorker$saveTpsElectionData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str4, WilayahApiResponse wilayahApiResponse2) {
                invoke2(str4, wilayahApiResponse2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String jenisPemilihan, WilayahApiResponse wilayahApiResponse2) {
                Pair parseDateTimeNew;
                Pair parseDateTimeNew2;
                String str4;
                List<WilayahApiResponse.DataApiResponse.PetugasApiResponse> petugas;
                Iterator it;
                String str5;
                ArrayList arrayList;
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(wilayahApiResponse2, "wilayahApiResponse");
                if (r1) {
                    boolean z = false;
                    if (wilayahApiResponse2.getData().getPetugas() != null && (!petugas.isEmpty())) {
                        z = true;
                    }
                    if (z) {
                        String str6 = str2;
                        Map<String, Witness> map3 = map2;
                        Map<String, WilayahApiResponse> map4 = map;
                        String str7 = str;
                        Iterator it2 = wilayahApiResponse2.getData().getPetugas().iterator();
                        while (it2.hasNext()) {
                            WilayahApiResponse.DataApiResponse.PetugasApiResponse petugasApiResponse = (WilayahApiResponse.DataApiResponse.PetugasApiResponse) it2.next();
                            String noHandphone = petugasApiResponse.getNoHandphone();
                            if (noHandphone != null) {
                                String generateWitnessId = ElectionUtil.generateWitnessId(noHandphone, str6);
                                if (map3.containsKey(generateWitnessId)) {
                                    it = it2;
                                    str5 = str7;
                                    if (Intrinsics.areEqual(petugasApiResponse.getJenisPemeriksa(), Witness.WITNESS_TYPE_SAKSI)) {
                                        Witness witness = map3.get(generateWitnessId);
                                        Intrinsics.checkNotNull(witness);
                                        List<WitnessPemeriksa> pemeriksas = witness.getPemeriksas();
                                        List mutableList = pemeriksas != null ? CollectionsKt.toMutableList((Collection) pemeriksas) : null;
                                        List<WilayahApiResponse.DataApiResponse.PetugasPemeriksaApiResponse> pemeriksas2 = petugasApiResponse.getPemeriksas();
                                        if (pemeriksas2 != null) {
                                            List<WilayahApiResponse.DataApiResponse.PetugasPemeriksaApiResponse> list = pemeriksas2;
                                            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                                            for (WilayahApiResponse.DataApiResponse.PetugasPemeriksaApiResponse petugasPemeriksaApiResponse : list) {
                                                arrayList2.add(mutableList != null ? Boolean.valueOf(mutableList.add(new WitnessPemeriksa(generateWitnessId, petugasPemeriksaApiResponse.getJenisPemilihan(), petugasPemeriksaApiResponse.getIdPilihan(), petugasPemeriksaApiResponse.getUrl()))) : null);
                                            }
                                        }
                                        Witness witness2 = map3.get(generateWitnessId);
                                        Intrinsics.checkNotNull(witness2);
                                        map3.put(generateWitnessId, Witness.copy$default(witness2, 0L, null, null, null, null, null, null, mutableList, 127, null));
                                    }
                                } else {
                                    List<WilayahApiResponse.DataApiResponse.PetugasPemeriksaApiResponse> pemeriksas3 = petugasApiResponse.getPemeriksas();
                                    if (pemeriksas3 != null) {
                                        List<WilayahApiResponse.DataApiResponse.PetugasPemeriksaApiResponse> list2 = pemeriksas3;
                                        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                                        for (WilayahApiResponse.DataApiResponse.PetugasPemeriksaApiResponse petugasPemeriksaApiResponse2 : list2) {
                                            arrayList3.add(new WitnessPemeriksa(generateWitnessId, petugasPemeriksaApiResponse2.getJenisPemilihan(), petugasPemeriksaApiResponse2.getIdPilihan(), petugasPemeriksaApiResponse2.getUrl()));
                                        }
                                        arrayList = arrayList3;
                                    } else {
                                        arrayList = null;
                                    }
                                    if (Intrinsics.areEqual(petugasApiResponse.getJenisPemeriksa(), Witness.WITNESS_TYPE_PPS) || Intrinsics.areEqual(petugasApiResponse.getJenisPemeriksa(), Witness.WITNESS_TYPE_PANWAS)) {
                                        Set<String> keySet = map4.keySet();
                                        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(keySet, 10));
                                        for (String str8 : keySet) {
                                            arrayList4.add(new WitnessPemeriksa(generateWitnessId, str8, 0L, ""));
                                        }
                                        arrayList = arrayList4;
                                    }
                                    it = it2;
                                    str5 = str7;
                                    map3.put(generateWitnessId, new Witness(petugasApiResponse.getIdPetugas(), petugasApiResponse.getNama(), petugasApiResponse.getNik(), generateWitnessId, petugasApiResponse.getJenisPemeriksa(), str7, petugasApiResponse.getUrutan(), arrayList));
                                }
                            } else {
                                it = it2;
                                str5 = str7;
                            }
                            it2 = it;
                            str7 = str5;
                        }
                    }
                }
                if (r2) {
                    ArrayList arrayList5 = new ArrayList();
                    List<WilayahApiResponse.DataApiResponse.InfoTpsResponse> infoTpsList = wilayahApiResponse2.getData().getInfoTpsList();
                    if (infoTpsList != null) {
                        LoginWorker loginWorker = this;
                        String str9 = str;
                        for (WilayahApiResponse.DataApiResponse.InfoTpsResponse infoTpsResponse : infoTpsList) {
                            parseDateTimeNew = loginWorker.parseDateTimeNew(infoTpsResponse.getWaktuMulai());
                            parseDateTimeNew2 = loginWorker.parseDateTimeNew(infoTpsResponse.getWaktuSelesai());
                            if (parseDateTimeNew == null || parseDateTimeNew2 == null) {
                                str4 = str9;
                            } else {
                                str4 = str9;
                                arrayList5.add(new TpsTime(str9, infoTpsResponse.getJenisWaktu(), infoTpsResponse.getJenisPemilihan(), ((Calendar) parseDateTimeNew.getFirst()).getTime().getTime(), ((Number) ((Pair) parseDateTimeNew.getSecond()).getFirst()).intValue(), ((Number) ((Pair) parseDateTimeNew.getSecond()).getSecond()).intValue(), ((Calendar) parseDateTimeNew2.getFirst()).getTime().getTime(), ((Number) ((Pair) parseDateTimeNew2.getSecond()).getFirst()).intValue(), ((Number) ((Pair) parseDateTimeNew2.getSecond()).getSecond()).intValue(), false, 512, null));
                            }
                            str9 = str4;
                        }
                    }
                    appDatabase.tpsTimeDao().insertAll(arrayList5);
                }
                if (r4) {
                    switch (jenisPemilihan.hashCode()) {
                        case -992700931:
                            if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                                LoginWorker loginWorker2 = this;
                                String str10 = str;
                                String str11 = str3;
                                WilayahApiResponse.DataApiResponse.PemilihanPdprPdprdApiResponse pemilihanPdprdk = wilayahApiResponse2.getData().getPemilihanPdprdk();
                                loginWorker2.insertPemilihanData(str10, str11, jenisPemilihan, pemilihanPdprdk != null ? pemilihanPdprdk.getImages() : null, str2);
                                return;
                            }
                            return;
                        case -992700926:
                            if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                                LoginWorker loginWorker3 = this;
                                String str12 = str;
                                String str13 = str3;
                                WilayahApiResponse.DataApiResponse.PemilihanPdprPdprdApiResponse pemilihanPdprdp = wilayahApiResponse2.getData().getPemilihanPdprdp();
                                loginWorker3.insertPemilihanData(str12, str13, jenisPemilihan, pemilihanPdprdp != null ? pemilihanPdprdp.getImages() : null, str2);
                                return;
                            }
                            return;
                        case 3436264:
                            if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPD)) {
                                LoginWorker loginWorker4 = this;
                                String str14 = str;
                                String str15 = str3;
                                WilayahApiResponse.DataApiResponse.PemilihanPdpdApiResponse pemilihanPdpd = wilayahApiResponse2.getData().getPemilihanPdpd();
                                loginWorker4.insertPemilihanData(str14, str15, jenisPemilihan, pemilihanPdpd != null ? pemilihanPdpd.getImages() : null, str2);
                                return;
                            }
                            return;
                        case 3436278:
                            if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                                LoginWorker loginWorker5 = this;
                                String str16 = str;
                                String str17 = str3;
                                WilayahApiResponse.DataApiResponse.PemilihanPdprPdprdApiResponse pemilihanPdpr = wilayahApiResponse2.getData().getPemilihanPdpr();
                                loginWorker5.insertPemilihanData(str16, str17, jenisPemilihan, pemilihanPdpr != null ? pemilihanPdpr.getImages() : null, str2);
                                return;
                            }
                            return;
                        case 3448025:
                            if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                                LoginWorker loginWorker6 = this;
                                String str18 = str;
                                String str19 = str3;
                                WilayahApiResponse.DataApiResponse.PemilihanPpwpApiResponse pemilihanPpwp = wilayahApiResponse2.getData().getPemilihanPpwp();
                                loginWorker6.insertPemilihanData(str18, str19, jenisPemilihan, pemilihanPpwp != null ? pemilihanPpwp.getImages() : null, str2);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        map.forEach(new BiConsumer() { // from class: org.informatika.sirekap.support.worker.login.LoginWorker$$ExternalSyntheticLambda3
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                LoginWorker.saveTpsElectionData$lambda$4(Function2.this, obj, obj2);
            }
        });
        appDatabase.witnessDao().deleteAllOnline(str);
        WitnessUtil.Companion companion = WitnessUtil.Companion;
        List<Witness> list = CollectionsKt.toList(map2.values());
        Collection<Witness> values = map2.values();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        for (Witness witness : values) {
            arrayList.add(SecurityFacade.INSTANCE.encryptCode(witness.getNoHandphone()));
        }
        companion.insertWitnessesToDatabase(appDatabase, list, arrayList);
    }

    public static final void saveTpsElectionData$lambda$4(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj, obj2);
    }

    public final Pair<Calendar, Pair<Integer, Integer>> parseDateTimeNew(String str) {
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ").parse(str);
            Calendar calendar = Calendar.getInstance();
            if (parse != null) {
                calendar.setTime(parse);
            }
            calendar.setTimeZone(TimeZone.getDefault());
            return new Pair<>(calendar, new Pair(Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12))));
        } catch (Exception unused) {
            return null;
        }
    }

    public final void insertPemilihanData(final String str, final String str2, final String str3, List<WilayahApiResponse.DataApiResponse.ImageApiResponse> list, final String str4) {
        final AppDatabase appDatabase = getAppDatabase();
        boolean z = false;
        if (list != null && (!list.isEmpty())) {
            z = true;
        }
        if (z) {
            for (final WilayahApiResponse.DataApiResponse.ImageApiResponse imageApiResponse : list) {
                final String electionPageId = getElectionPageId(str, getElectionId(str, str3, str2), imageApiResponse.getJenisImage(), imageApiResponse.getNoLembar(), str2);
                appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.support.worker.login.LoginWorker$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        LoginWorker.insertPemilihanData$lambda$14$lambda$13(AppDatabase.this, electionPageId, this, str, str3, str2, imageApiResponse, str4);
                    }
                });
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:466:0x018e, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) != false) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x0195, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPR) == false) goto L388;
     */
    /* JADX WARN: Code restructure failed: missing block: B:472:0x019d, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L388;
     */
    /* JADX WARN: Code restructure failed: missing block: B:474:0x01a1, code lost:
        r2 = (org.informatika.sirekap.model.FormC1TabulationPayload) new com.google.gson.Gson().fromJson(r2, (java.lang.Class<java.lang.Object>) org.informatika.sirekap.model.FormC1TabulationPayload.class);
        r5 = r2.getError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:475:0x01b4, code lost:
        if (r5 == null) goto L342;
     */
    /* JADX WARN: Code restructure failed: missing block: B:477:0x01ba, code lost:
        if (kotlin.text.StringsKt.isBlank(r5) == false) goto L276;
     */
    /* JADX WARN: Code restructure failed: missing block: B:479:0x01bd, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:480:0x01bf, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:481:0x01c0, code lost:
        if (r5 != false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:482:0x01c2, code lost:
        r25.insertError(r15, 2, 20, r2.getError());
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x01ce, code lost:
        r23.formC1ErrorDao().deleteAll(r15, 2);
        r1 = r19.getElection().getPemilihan();
     */
    /* JADX WARN: Code restructure failed: missing block: B:484:0x01e2, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) == false) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x01e4, code lost:
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x01eb, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:488:0x01ed, code lost:
        r1 = r23.electionDao().getByIdSync(r19.getElection().getId()).getCandidates().size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:489:0x0206, code lost:
        if (1 > r1) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:491:0x020a, code lost:
        if (r1 >= 9) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:492:0x020c, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:493:0x020e, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:494:0x020f, code lost:
        if (r6 == false) goto L337;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x0211, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x0213, code lost:
        if (13 > r1) goto L340;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x0217, code lost:
        if (r1 >= 17) goto L340;
     */
    /* JADX WARN: Code restructure failed: missing block: B:500:0x021a, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:501:0x021b, code lost:
        if (r6 == false) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:502:0x021d, code lost:
        r1 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:504:0x0222, code lost:
        if (9 > r1) goto L336;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x0226, code lost:
        if (r1 >= 11) goto L336;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0228, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:508:0x022a, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x022b, code lost:
        if (r6 == false) goto L331;
     */
    /* JADX WARN: Code restructure failed: missing block: B:510:0x022d, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:512:0x0231, code lost:
        if (17 > r1) goto L335;
     */
    /* JADX WARN: Code restructure failed: missing block: B:514:0x0235, code lost:
        if (r1 >= 21) goto L335;
     */
    /* JADX WARN: Code restructure failed: missing block: B:516:0x0238, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x0239, code lost:
        if (r6 == false) goto L326;
     */
    /* JADX WARN: Code restructure failed: missing block: B:518:0x023b, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:520:0x023f, code lost:
        if (25 > r1) goto L330;
     */
    /* JADX WARN: Code restructure failed: missing block: B:522:0x0243, code lost:
        if (r1 >= 31) goto L330;
     */
    /* JADX WARN: Code restructure failed: missing block: B:524:0x0246, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x0247, code lost:
        if (r6 == false) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:526:0x0249, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:528:0x024d, code lost:
        if (37 > r1) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:530:0x0251, code lost:
        if (r1 >= 41) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:532:0x0254, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:533:0x0255, code lost:
        if (r6 == false) goto L316;
     */
    /* JADX WARN: Code restructure failed: missing block: B:534:0x0257, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:536:0x025b, code lost:
        if (49 > r1) goto L320;
     */
    /* JADX WARN: Code restructure failed: missing block: B:538:0x025f, code lost:
        if (r1 >= 51) goto L320;
     */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x0262, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:541:0x0263, code lost:
        if (r1 == false) goto L315;
     */
    /* JADX WARN: Code restructure failed: missing block: B:542:0x0265, code lost:
        r1 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:543:0x0267, code lost:
        r1 = 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:544:0x0269, code lost:
        r6 = (r19.getElectionPage().getNumber() - 1) * r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:545:0x0276, code lost:
        r1 = org.informatika.sirekap.model.FormC1TabulationCandidateVote.Companion;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "payload");
        r1 = r1.createFromFormC1TabulationPayload(r2, r15, r6);
        r2 = r23.formC1KesesuaianTabulationCandidateVoteDao();
        r4 = r1;
        r6 = new java.util.ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, 10));
        r4 = r4.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:547:0x0299, code lost:
        if (r4.hasNext() == false) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:548:0x029b, code lost:
        r5 = (org.informatika.sirekap.model.FormC1TabulationCandidateVote) r4.next();
        r6.add(new org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote(r5.getIdImage(), r5.getIndex(), true));
     */
    /* JADX WARN: Code restructure failed: missing block: B:549:0x02b6, code lost:
        r2.insertAll(r6);
        r23.formC1TabulationCandidateVoteDao().insertAll(r1);
        r23.electionPageDao().removeVisionError(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:551:0x02cf, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) == false) goto L388;
     */
    /* JADX WARN: Code restructure failed: missing block: B:554:0x02d7, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) == false) goto L388;
     */
    /* JADX WARN: Code restructure failed: missing block: B:556:0x02db, code lost:
        r2 = (org.informatika.sirekap.model.FormC1TabulationPayload) new com.google.gson.Gson().fromJson(r2, (java.lang.Class<java.lang.Object>) org.informatika.sirekap.model.FormC1TabulationPayload.class);
        r3 = r2.getError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:557:0x02ee, code lost:
        if (r3 == null) goto L370;
     */
    /* JADX WARN: Code restructure failed: missing block: B:559:0x02f4, code lost:
        if (kotlin.text.StringsKt.isBlank(r3) == false) goto L349;
     */
    /* JADX WARN: Code restructure failed: missing block: B:561:0x02f7, code lost:
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:562:0x02f9, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:563:0x02fa, code lost:
        if (r3 != false) goto L352;
     */
    /* JADX WARN: Code restructure failed: missing block: B:564:0x02fc, code lost:
        r25.insertError(r15, 4, 20, r2.getError());
     */
    /* JADX WARN: Code restructure failed: missing block: B:565:0x0308, code lost:
        r23.formC1ErrorDao().deleteAll(r15, 4);
        r1 = org.informatika.sirekap.model.FormC1TabulationPartai.Companion;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "payload");
        r1 = r1.createFromFormC1TabulationPayload(r2, r15);
        r3 = r19.getElection().getPemilihan();
        r4 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:566:0x032e, code lost:
        if (r4 == (-992700931)) goto L368;
     */
    /* JADX WARN: Code restructure failed: missing block: B:567:0x0330, code lost:
        if (r4 == (-992700926)) goto L366;
     */
    /* JADX WARN: Code restructure failed: missing block: B:568:0x0332, code lost:
        if (r4 == 3436278) goto L356;
     */
    /* JADX WARN: Code restructure failed: missing block: B:571:0x0339, code lost:
        if (r3.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPR) != false) goto L358;
     */
    /* JADX WARN: Code restructure failed: missing block: B:574:0x0340, code lost:
        if (r3.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) != false) goto L358;
     */
    /* JADX WARN: Code restructure failed: missing block: B:577:0x0347, code lost:
        if (r3.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) == false) goto L365;
     */
    /* JADX WARN: Code restructure failed: missing block: B:578:0x0349, code lost:
        r3 = r23.candidateDao().getOneByIndexPartai(r19.getElection().getId(), r19.getElectionPage().getNumber()).getIndex();
     */
    /* JADX WARN: Code restructure failed: missing block: B:579:0x0366, code lost:
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:580:0x0367, code lost:
        r2 = org.informatika.sirekap.model.FormC1TabulationCandidateVote.Companion.createFromFormC1TabulationPayloadPartai(r2, r15, r3);
        r3 = r23.formC1KesesuaianTabulationCandidateVoteDao();
        r4 = r2;
        r10 = new java.util.ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, 10));
        r4 = r4.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:582:0x0389, code lost:
        if (r4.hasNext() == false) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:583:0x038b, code lost:
        r5 = (org.informatika.sirekap.model.FormC1TabulationCandidateVote) r4.next();
        r10.add(new org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote(r5.getIdImage(), r5.getIndex(), true));
        r4 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:584:0x03a6, code lost:
        r3.insertAll(r10);
        r23.formC1TabulationCandidateVoteDao().insertAll(r2);
        r23.formC1TabulationDao().insertAllTabulationPartai(kotlin.collections.CollectionsKt.listOf(r1));
        r23.formC1KesesuaianTabulationPartaiDao().insertAll(kotlin.collections.CollectionsKt.listOf(org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai.Companion.createFilled(r15)));
        r23.electionPageDao().removeVisionError(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:636:0x054f, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) != false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:643:0x0562, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:645:0x0565, code lost:
        r3 = r19.getElection().getPemilihan();
     */
    /* JADX WARN: Code restructure failed: missing block: B:646:0x0571, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3, org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) == false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:647:0x0573, code lost:
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:649:0x057a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3, org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:650:0x057c, code lost:
        r3 = r23.electionDao().getByIdSync(r19.getElection().getId()).getCandidates().size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:651:0x0595, code lost:
        if (1 > r3) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:653:0x0599, code lost:
        if (r3 >= 9) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:654:0x059b, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:655:0x059d, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:656:0x059e, code lost:
        if (r5 == false) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:657:0x05a0, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:659:0x05a4, code lost:
        if (13 > r3) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:661:0x05a8, code lost:
        if (r3 >= 17) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:663:0x05ab, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:664:0x05ac, code lost:
        if (r5 == false) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:665:0x05ae, code lost:
        r3 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:667:0x05b3, code lost:
        if (9 > r3) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:669:0x05b7, code lost:
        if (r3 >= 11) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:670:0x05b9, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:671:0x05bb, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:672:0x05bc, code lost:
        if (r5 == false) goto L195;
     */
    /* JADX WARN: Code restructure failed: missing block: B:673:0x05be, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:675:0x05c2, code lost:
        if (17 > r3) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:677:0x05c6, code lost:
        if (r3 >= 21) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:679:0x05c9, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:680:0x05ca, code lost:
        if (r5 == false) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:681:0x05cc, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:683:0x05d0, code lost:
        if (25 > r3) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:685:0x05d4, code lost:
        if (r3 >= 31) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:687:0x05d7, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:688:0x05d8, code lost:
        if (r5 == false) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:689:0x05da, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:691:0x05de, code lost:
        if (37 > r3) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:693:0x05e2, code lost:
        if (r3 >= 41) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:695:0x05e5, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:696:0x05e6, code lost:
        if (r5 == false) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:697:0x05e8, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:699:0x05ec, code lost:
        if (49 > r3) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:701:0x05f0, code lost:
        if (r3 >= 51) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:703:0x05f3, code lost:
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:704:0x05f4, code lost:
        if (r3 == false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:705:0x05f6, code lost:
        r3 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:706:0x05f9, code lost:
        r3 = 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:707:0x05fb, code lost:
        r5 = (r19.getElectionPage().getNumber() - 1) * r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:708:0x0606, code lost:
        if (r4 == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:709:0x0608, code lost:
        r3 = r19.getElection().getPemilihan();
     */
    /* JADX WARN: Code restructure failed: missing block: B:710:0x0614, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3, org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:711:0x0616, code lost:
        r3 = r23.electionDao().getByIdSync(r19.getElection().getId()).getCandidates().size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:712:0x062e, code lost:
        r13 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:714:0x0635, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3, org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:715:0x0637, code lost:
        r3 = r23.electionDao().getByIdSync(r19.getElection().getId()).getCandidates().size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:716:0x0650, code lost:
        if (1 > r3) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:718:0x0654, code lost:
        if (r3 >= 9) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:719:0x0656, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:720:0x0658, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:721:0x0659, code lost:
        if (r4 == false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:722:0x065b, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:724:0x065f, code lost:
        if (13 > r3) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:726:0x0663, code lost:
        if (r3 >= 17) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:728:0x0666, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:729:0x0667, code lost:
        if (r4 == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:730:0x0669, code lost:
        r13 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:732:0x066e, code lost:
        if (9 > r3) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:734:0x0672, code lost:
        if (r3 >= 11) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:735:0x0674, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:736:0x0676, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:737:0x0677, code lost:
        if (r4 == false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:738:0x0679, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:740:0x067d, code lost:
        if (17 > r3) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:742:0x0681, code lost:
        if (r3 >= 21) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:744:0x0684, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:745:0x0685, code lost:
        if (r4 == false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:746:0x0687, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:748:0x068b, code lost:
        if (25 > r3) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:750:0x068f, code lost:
        if (r3 >= 31) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:752:0x0692, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:753:0x0693, code lost:
        if (r4 == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:754:0x0695, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:756:0x0699, code lost:
        if (37 > r3) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:758:0x069d, code lost:
        if (r3 >= 41) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:760:0x06a0, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:761:0x06a1, code lost:
        if (r4 == false) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:762:0x06a3, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:764:0x06a7, code lost:
        if (49 > r3) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:766:0x06ab, code lost:
        if (r3 >= 51) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:768:0x06ae, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:769:0x06af, code lost:
        if (r4 == false) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:770:0x06b1, code lost:
        r13 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:771:0x06b4, code lost:
        r13 = 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:772:0x06b6, code lost:
        r4 = r19.getElectionPage().getNumber();
     */
    /* JADX WARN: Code restructure failed: missing block: B:773:0x06c0, code lost:
        if ((r13 * r4) >= r3) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:775:0x06c3, code lost:
        r3 = r3 - (r13 * (r4 - 1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:776:0x06c9, code lost:
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:777:0x06ca, code lost:
        r6 = 0;
        r4 = r15;
        r23.formC1TabulationCandidateVoteDao().insertAll(org.informatika.sirekap.model.FormC1TabulationCandidateVote.Companion.createEmpty$default(org.informatika.sirekap.model.FormC1TabulationCandidateVote.Companion, r13, r15, r5, null, 8, null));
        r23.formC1ErrorDao().deleteAll(r4, 2);
        r23.electionPageDao().markAsContinueVerify(r19.getElectionPage().getId());
     */
    /* JADX WARN: Code restructure failed: missing block: B:778:0x06fa, code lost:
        r4 = r15;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:779:0x06fc, code lost:
        r3 = new java.util.ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, 10));
        r1 = r1.iterator();
        r14 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:781:0x0712, code lost:
        if (r1.hasNext() == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:782:0x0714, code lost:
        r6 = r1.next();
        r7 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:783:0x071a, code lost:
        if (r14 >= 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:784:0x071c, code lost:
        kotlin.collections.CollectionsKt.throwIndexOverflow();
     */
    /* JADX WARN: Code restructure failed: missing block: B:785:0x071f, code lost:
        r3.add(new org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote(r4, r14 + r5, java.lang.Boolean.valueOf(((java.lang.Boolean) r6).booleanValue()), (java.lang.Integer) r8.get(r14)));
        r14 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:786:0x073b, code lost:
        r23.formC1KesesuaianTabulationCandidateVoteDao().insertAllReplace(r3);
        r5 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:788:0x074e, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:791:0x0759, code lost:
        if (r27.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) == false) goto L63;
     */
    /* JADX WARN: Removed duplicated region for block: B:600:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:601:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:603:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:861:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void insertPemilihanData$lambda$14$lambda$13(org.informatika.sirekap.db.AppDatabase r23, java.lang.String r24, org.informatika.sirekap.support.worker.login.LoginWorker r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, org.informatika.sirekap.api.response.WilayahApiResponse.DataApiResponse.ImageApiResponse r29, java.lang.String r30) {
        /*
            Method dump skipped, instructions count: 2390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.worker.login.LoginWorker.insertPemilihanData$lambda$14$lambda$13(org.informatika.sirekap.db.AppDatabase, java.lang.String, org.informatika.sirekap.support.worker.login.LoginWorker, java.lang.String, java.lang.String, java.lang.String, org.informatika.sirekap.api.response.WilayahApiResponse$DataApiResponse$ImageApiResponse, java.lang.String):void");
    }

    private final void insertError(String str, int i, int i2, String str2) {
        AppDatabase appDatabase = getAppDatabase();
        appDatabase.formC1ErrorDao().insertAll(CollectionsKt.listOf(new FormC1Error(str, i, i2, str2)));
        if (i2 != 10) {
            appDatabase.electionPageDao().addVisionError(str);
        }
    }

    private final User insertUserToDatabase(WilayahApiResponse wilayahApiResponse, final KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse keyCloakUserInfoProfileApiResponse, final String str, final AppDatabase appDatabase) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final WilayahApiResponse.DataApiResponse data = wilayahApiResponse.getData();
        appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.support.worker.login.LoginWorker$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LoginWorker.insertUserToDatabase$lambda$16$lambda$15(WilayahApiResponse.DataApiResponse.this, str, keyCloakUserInfoProfileApiResponse, objectRef, appDatabase);
            }
        });
        return (User) objectRef.element;
    }

    /* JADX WARN: Type inference failed for: r10v4, types: [T, org.informatika.sirekap.model.User] */
    public static final void insertUserToDatabase$lambda$16$lambda$15(WilayahApiResponse.DataApiResponse this_apply, String kodeTpsProfile, KeyCloakUserInfoApiResponse.KeyCloakUserInfoProfileApiResponse activeProfile, Ref.ObjectRef user, AppDatabase database) {
        String str;
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(kodeTpsProfile, "$kodeTpsProfile");
        Intrinsics.checkNotNullParameter(activeProfile, "$activeProfile");
        Intrinsics.checkNotNullParameter(user, "$user");
        Intrinsics.checkNotNullParameter(database, "$database");
        String str2 = this_apply.getDetail().getKodeTps() + "-kab";
        Tps tps = new Tps(kodeTpsProfile, this_apply.getDetail().getNama(), new Kelurahan(this_apply.getDetail().getKodeTps() + "-kel", this_apply.getDetail().getNamaKel(), new Kecamatan(this_apply.getDetail().getKodeTps() + "-kec", this_apply.getDetail().getNamaKec(), new KotaKabupaten(str2, this_apply.getDetail().getNamaKab(), new Provinsi(this_apply.getDetail().getKodeTps() + "-prov", this_apply.getDetail().getNamaProv())))));
        String role = activeProfile.getRole();
        String str3 = StringsKt.isBlank(role) ? "kpps" : role;
        if (Intrinsics.areEqual(str3, "kpps") || Intrinsics.areEqual(str3, User.USER_ROLE_KPPS_LN)) {
            str = tps.isLn() ? "KPPS LN" : "KPPS";
        } else {
            str = tps.isLn() ? "PPLN" : "PPK";
        }
        user.element = new User(kodeTpsProfile, str, tps, str3, activeProfile.getNama_profil(), activeProfile.getStart(), activeProfile.getEnd());
        UserDao userDao = database.userDao();
        T t = user.element;
        Intrinsics.checkNotNull(t);
        userDao.insertAll(CollectionsKt.listOf(t));
    }

    public final void insertTpsElectionToDatabase(WilayahApiResponse item, final String kodeTpsProfile, final AppDatabase database) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(kodeTpsProfile, "kodeTpsProfile");
        Intrinsics.checkNotNullParameter(database, "database");
        final WilayahApiResponse.DataApiResponse data = item.getData();
        database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.support.worker.login.LoginWorker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LoginWorker.insertTpsElectionToDatabase$lambda$31$lambda$30(WilayahApiResponse.DataApiResponse.this, kodeTpsProfile, this, database);
            }
        });
    }

    public static final void insertTpsElectionToDatabase$lambda$31$lambda$30(WilayahApiResponse.DataApiResponse this_apply, String kodeTpsProfile, LoginWorker loginWorker, AppDatabase database) {
        int i;
        ArrayList emptyList;
        ArrayList emptyList2;
        LoginWorker this$0 = loginWorker;
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(kodeTpsProfile, "$kodeTpsProfile");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(database, "$database");
        String str = this_apply.getDetail().getKodeTps() + "-kab";
        Tps tps = new Tps(kodeTpsProfile, this_apply.getDetail().getNama(), new Kelurahan(this_apply.getDetail().getKodeTps() + "-kel", this_apply.getDetail().getNamaKel(), new Kecamatan(this_apply.getDetail().getKodeTps() + "-kec", this_apply.getDetail().getNamaKec(), new KotaKabupaten(str, this_apply.getDetail().getNamaKab(), new Provinsi(this_apply.getDetail().getKodeTps() + "-prov", this_apply.getDetail().getNamaProv())))));
        int i2 = 10;
        if (this_apply.getPemilihanPpwp() != null) {
            for (String str2 : Election.Companion.getALL_ELECTION_TYPES()) {
                String electionId = this$0.getElectionId(kodeTpsProfile, Election.ELECTION_PEMILIHAN_PRESIDEN, str2);
                database.electionDao().insertAll(CollectionsKt.listOf(new Election(electionId, 0, Election.ELECTION_PEMILIHAN_PRESIDEN, null, this_apply.getPemilihanPpwp().getJmlLembar(), tps, str2, false, 0, null, null, null, false, false, false, 32648, null)));
                List<WilayahApiResponse.DataApiResponse.PilihanPpwpApiResponse> pilihan = this_apply.getPemilihanPpwp().getPilihan();
                if (pilihan == null) {
                    emptyList2 = CollectionsKt.emptyList();
                } else {
                    List<WilayahApiResponse.DataApiResponse.PilihanPpwpApiResponse> list = pilihan;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, i2));
                    int i3 = 0;
                    for (Object obj : list) {
                        int i4 = i3 + 1;
                        if (i3 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        WilayahApiResponse.DataApiResponse.PilihanPpwpApiResponse pilihanPpwpApiResponse = (WilayahApiResponse.DataApiResponse.PilihanPpwpApiResponse) obj;
                        ArrayList arrayList2 = arrayList;
                        arrayList2.add(new Candidate(i3, pilihanPpwpApiResponse.getIdPilihan(), electionId, Election.ELECTION_PEMILIHAN_PRESIDEN, pilihanPpwpApiResponse.getNoUrutPencalonan(), pilihanPpwpApiResponse.getNoUrutLabel(), pilihanPpwpApiResponse.getNamaCalonKepala(), pilihanPpwpApiResponse.getNamaCalonWakil(), null, null, null, null, null, 7936, null).toEncrypted());
                        arrayList = arrayList2;
                        i3 = i4;
                    }
                    emptyList2 = arrayList;
                }
                database.candidateDao().deleteAllByElectionId(electionId);
                database.candidateDao().insertAll(emptyList2);
                loginWorker.insertElection(tps.getKodeTps(), this_apply.getPemilihanPpwp().getJmlLembar(), electionId, str2, Election.ELECTION_PEMILIHAN_PRESIDEN);
                i2 = 10;
                tps = tps;
            }
        }
        int i5 = i2;
        Tps tps2 = tps;
        if (this_apply.getPemilihanPdpd() != null) {
            for (String str3 : Election.Companion.getALL_ELECTION_TYPES()) {
                String electionId2 = this$0.getElectionId(kodeTpsProfile, Election.ELECTION_PEMILIHAN_DPD, str3);
                int i6 = i5;
                database.electionDao().insertAll(CollectionsKt.listOf(new Election(electionId2, 0, Election.ELECTION_PEMILIHAN_DPD, this_apply.getPemilihanPdpd().getDapil(), this_apply.getPemilihanPdpd().getJmlLembar(), tps2, str3, false, 0, null, null, null, false, false, false, 32640, null)));
                List<WilayahApiResponse.DataApiResponse.PilihanPdpdApiResponse> pilihan2 = this_apply.getPemilihanPdpd().getPilihan();
                if (pilihan2 == null) {
                    emptyList = CollectionsKt.emptyList();
                } else {
                    List<WilayahApiResponse.DataApiResponse.PilihanPdpdApiResponse> list2 = pilihan2;
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, i6));
                    int i7 = 0;
                    for (Object obj2 : list2) {
                        int i8 = i7 + 1;
                        if (i7 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        WilayahApiResponse.DataApiResponse.PilihanPdpdApiResponse pilihanPdpdApiResponse = (WilayahApiResponse.DataApiResponse.PilihanPdpdApiResponse) obj2;
                        arrayList3.add(new Candidate(i7, pilihanPdpdApiResponse.getIdPilihan(), electionId2, Election.ELECTION_PEMILIHAN_DPD, pilihanPdpdApiResponse.getNoUrutPencalonan(), pilihanPdpdApiResponse.getNoUrutLabel(), pilihanPdpdApiResponse.getNama(), null, null, null, null, null, null, 8064, null).toEncrypted());
                        i7 = i8;
                    }
                    emptyList = arrayList3;
                }
                database.candidateDao().deleteAllByElectionId(electionId2);
                database.candidateDao().insertAll(emptyList);
                loginWorker.insertElection(tps2.getKodeTps(), this_apply.getPemilihanPdpd().getJmlLembar(), electionId2, str3, Election.ELECTION_PEMILIHAN_DPD);
                i5 = i6;
            }
        }
        int i9 = i5;
        if (this_apply.getPemilihanPdpr() != null) {
            for (String str4 : Election.Companion.getALL_ELECTION_TYPES()) {
                String electionId3 = this$0.getElectionId(kodeTpsProfile, Election.ELECTION_PEMILIHAN_DPR, str4);
                int i10 = i9;
                database.electionDao().insertAll(CollectionsKt.listOf(new Election(electionId3, 0, Election.ELECTION_PEMILIHAN_DPR, this_apply.getPemilihanPdpr().getDapil(), this_apply.getPemilihanPdpr().getJmlLembar(), tps2, str4, false, 0, null, null, null, false, false, false, 32640, null)));
                database.candidateDao().deleteAllByElectionId(electionId3);
                List<WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse> pilihan3 = this_apply.getPemilihanPdpr().getPilihan();
                if (pilihan3 != null) {
                    List<WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse> list3 = pilihan3;
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, i10));
                    int i11 = 0;
                    int i12 = -1;
                    for (WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse pilihanPdprPdprdApiResponse : list3) {
                        i11++;
                        i12++;
                        List<Candidate> mutableListOf = CollectionsKt.mutableListOf(new Candidate(i12, i11 * (-1), electionId3, Election.ELECTION_PEMILIHAN_DPR, -1, -1, pilihanPdprPdprdApiResponse.getNama(), null, Integer.valueOf(i11), Long.valueOf(pilihanPdprPdprdApiResponse.getIdPilihan()), pilihanPdprPdprdApiResponse.getNama(), Integer.valueOf(pilihanPdprPdprdApiResponse.getNoUrutPencalonan()), Integer.valueOf(pilihanPdprPdprdApiResponse.getNoUrutLabel()), 128, null).toEncrypted());
                        List<WilayahApiResponse.DataApiResponse.PilihanNamaPdprPdprdApiResponse> pilihan4 = pilihanPdprPdprdApiResponse.getPilihan();
                        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(pilihan4, 10));
                        for (WilayahApiResponse.DataApiResponse.PilihanNamaPdprPdprdApiResponse pilihanNamaPdprPdprdApiResponse : pilihan4) {
                            i12++;
                            arrayList5.add(new Candidate(i12, pilihanNamaPdprPdprdApiResponse.getIdPilihan(), electionId3, Election.ELECTION_PEMILIHAN_DPR, pilihanNamaPdprPdprdApiResponse.getNoUrutPencalonan(), pilihanNamaPdprPdprdApiResponse.getNoUrutLabel(), pilihanNamaPdprPdprdApiResponse.getNama(), null, Integer.valueOf(i11), Long.valueOf(pilihanNamaPdprPdprdApiResponse.getIdPartai()), pilihanPdprPdprdApiResponse.getNama(), Integer.valueOf(pilihanPdprPdprdApiResponse.getNoUrutPencalonan()), Integer.valueOf(pilihanPdprPdprdApiResponse.getNoUrutLabel()), 128, null).toEncrypted());
                        }
                        mutableListOf.addAll(arrayList5);
                        database.candidateDao().insertAll(mutableListOf);
                        arrayList4.add(Unit.INSTANCE);
                        i10 = 10;
                    }
                    i = i10;
                } else {
                    i = i10;
                }
                loginWorker.insertElection(tps2.getKodeTps(), this_apply.getPemilihanPdpr().getJmlLembar(), electionId3, str4, Election.ELECTION_PEMILIHAN_DPR);
                i9 = i;
                this$0 = loginWorker;
            }
        }
        int i13 = i9;
        if (this_apply.getPemilihanPdprdp() != null) {
            for (String str5 : Election.Companion.getALL_ELECTION_TYPES()) {
                String electionId4 = loginWorker.getElectionId(kodeTpsProfile, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, str5);
                database.electionDao().insertAll(CollectionsKt.listOf(new Election(electionId4, 0, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, this_apply.getPemilihanPdprdp().getDapil(), this_apply.getPemilihanPdprdp().getJmlLembar(), tps2, str5, false, 0, null, null, null, false, false, false, 32640, null)));
                database.candidateDao().deleteAllByElectionId(electionId4);
                List<WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse> pilihan5 = this_apply.getPemilihanPdprdp().getPilihan();
                if (pilihan5 != null) {
                    List<WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse> list4 = pilihan5;
                    ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, i13));
                    Iterator it = list4.iterator();
                    int i14 = 0;
                    int i15 = -1;
                    while (it.hasNext()) {
                        WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse pilihanPdprPdprdApiResponse2 = (WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse) it.next();
                        i14++;
                        i15++;
                        List<Candidate> mutableListOf2 = CollectionsKt.mutableListOf(new Candidate(i15, i14 * (-1), electionId4, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, -1, -1, pilihanPdprPdprdApiResponse2.getNama(), null, Integer.valueOf(i14), Long.valueOf(pilihanPdprPdprdApiResponse2.getIdPilihan()), pilihanPdprPdprdApiResponse2.getNama(), Integer.valueOf(pilihanPdprPdprdApiResponse2.getNoUrutPencalonan()), Integer.valueOf(pilihanPdprPdprdApiResponse2.getNoUrutLabel()), 128, null).toEncrypted());
                        List<WilayahApiResponse.DataApiResponse.PilihanNamaPdprPdprdApiResponse> pilihan6 = pilihanPdprPdprdApiResponse2.getPilihan();
                        Iterator it2 = it;
                        ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(pilihan6, i13));
                        for (WilayahApiResponse.DataApiResponse.PilihanNamaPdprPdprdApiResponse pilihanNamaPdprPdprdApiResponse2 : pilihan6) {
                            i15++;
                            arrayList7.add(new Candidate(i15, pilihanNamaPdprPdprdApiResponse2.getIdPilihan(), electionId4, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, pilihanNamaPdprPdprdApiResponse2.getNoUrutPencalonan(), pilihanNamaPdprPdprdApiResponse2.getNoUrutLabel(), pilihanNamaPdprPdprdApiResponse2.getNama(), null, Integer.valueOf(i14), Long.valueOf(pilihanNamaPdprPdprdApiResponse2.getIdPartai()), pilihanPdprPdprdApiResponse2.getNama(), Integer.valueOf(pilihanPdprPdprdApiResponse2.getNoUrutPencalonan()), Integer.valueOf(pilihanPdprPdprdApiResponse2.getNoUrutLabel()), 128, null).toEncrypted());
                        }
                        mutableListOf2.addAll(arrayList7);
                        database.candidateDao().insertAll(mutableListOf2);
                        arrayList6.add(Unit.INSTANCE);
                        it = it2;
                    }
                }
                loginWorker.insertElection(tps2.getKodeTps(), this_apply.getPemilihanPdprdp().getJmlLembar(), electionId4, str5, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
        }
        LoginWorker loginWorker2 = loginWorker;
        if (this_apply.getPemilihanPdprdk() != null) {
            for (String str6 : Election.Companion.getALL_ELECTION_TYPES()) {
                String electionId5 = loginWorker2.getElectionId(kodeTpsProfile, Election.ELECTION_PEMILIHAN_DPRD_KABKO, str6);
                int i16 = 1;
                database.electionDao().insertAll(CollectionsKt.listOf(new Election(electionId5, 0, Election.ELECTION_PEMILIHAN_DPRD_KABKO, this_apply.getPemilihanPdprdk().getDapil(), this_apply.getPemilihanPdprdk().getJmlLembar(), tps2, str6, false, 0, null, null, null, false, false, false, 32640, null)));
                database.candidateDao().deleteAllByElectionId(electionId5);
                List<WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse> pilihan7 = this_apply.getPemilihanPdprdk().getPilihan();
                if (pilihan7 != null) {
                    List<WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse> list5 = pilihan7;
                    ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, i13));
                    int i17 = 0;
                    int i18 = -1;
                    for (WilayahApiResponse.DataApiResponse.PilihanPdprPdprdApiResponse pilihanPdprPdprdApiResponse3 : list5) {
                        int i19 = i17 + 1;
                        int i20 = i18 + 1;
                        Candidate[] candidateArr = new Candidate[i16];
                        candidateArr[0] = new Candidate(i20, i19 * (-1), electionId5, Election.ELECTION_PEMILIHAN_DPRD_KABKO, -1, -1, pilihanPdprPdprdApiResponse3.getNama(), null, Integer.valueOf(i19), Long.valueOf(pilihanPdprPdprdApiResponse3.getIdPilihan()), pilihanPdprPdprdApiResponse3.getNama(), Integer.valueOf(pilihanPdprPdprdApiResponse3.getNoUrutPencalonan()), Integer.valueOf(pilihanPdprPdprdApiResponse3.getNoUrutLabel()), 128, null).toEncrypted();
                        List<Candidate> mutableListOf3 = CollectionsKt.mutableListOf(candidateArr);
                        List<WilayahApiResponse.DataApiResponse.PilihanNamaPdprPdprdApiResponse> pilihan8 = pilihanPdprPdprdApiResponse3.getPilihan();
                        ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(pilihan8, i13));
                        i18 = i20;
                        for (WilayahApiResponse.DataApiResponse.PilihanNamaPdprPdprdApiResponse pilihanNamaPdprPdprdApiResponse3 : pilihan8) {
                            i18++;
                            ArrayList arrayList10 = arrayList9;
                            arrayList10.add(new Candidate(i18, pilihanNamaPdprPdprdApiResponse3.getIdPilihan(), electionId5, Election.ELECTION_PEMILIHAN_DPRD_KABKO, pilihanNamaPdprPdprdApiResponse3.getNoUrutPencalonan(), pilihanNamaPdprPdprdApiResponse3.getNoUrutLabel(), pilihanNamaPdprPdprdApiResponse3.getNama(), null, Integer.valueOf(i19), Long.valueOf(pilihanNamaPdprPdprdApiResponse3.getIdPartai()), pilihanPdprPdprdApiResponse3.getNama(), Integer.valueOf(pilihanPdprPdprdApiResponse3.getNoUrutPencalonan()), Integer.valueOf(pilihanPdprPdprdApiResponse3.getNoUrutLabel()), 128, null).toEncrypted());
                            arrayList9 = arrayList10;
                            mutableListOf3 = mutableListOf3;
                        }
                        List<Candidate> list6 = mutableListOf3;
                        list6.addAll(arrayList9);
                        database.candidateDao().insertAll(list6);
                        arrayList8.add(Unit.INSTANCE);
                        i17 = i19;
                        i16 = 1;
                        i13 = 10;
                    }
                }
                loginWorker.insertElection(tps2.getKodeTps(), this_apply.getPemilihanPdprdk().getJmlLembar(), electionId5, str6, Election.ELECTION_PEMILIHAN_DPRD_KABKO);
                loginWorker2 = loginWorker;
                i13 = 10;
            }
        }
    }

    private final String getElectionId(String str, String str2, String str3) {
        return str + "-" + str2 + "-" + str3;
    }

    private final String getElectionPageId(String str, String str2, int i, int i2, String str3) {
        return str + "-" + str2 + "-" + i + "-" + i2 + str3;
    }

    protected final void insertElection(String kodeTps, int i, String electionId, String electionType, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        AppDatabase appDatabase = getAppDatabase();
        int i2 = i - 2;
        ArrayList<ElectionPage> arrayList = new ArrayList();
        arrayList.add(new ElectionPage(getElectionPageId(kodeTps, electionId, 10, 1, electionType), jenisPemilihan, electionId, 10, 1));
        if (1 <= i2) {
            int i3 = 1;
            while (true) {
                arrayList.add(new ElectionPage(getElectionPageId(kodeTps, electionId, 20, i3, electionType), jenisPemilihan, electionId, 20, i3));
                if (i3 == i2) {
                    break;
                }
                i3++;
            }
        }
        arrayList.add(new ElectionPage(getElectionPageId(kodeTps, electionId, 30, 1, electionType), jenisPemilihan, electionId, 30, 1));
        appDatabase.electionPageDao().insertAll(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (ElectionPage electionPage : arrayList) {
            arrayList2.add(new ElectionPageStage(electionId, electionPage.getId(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO, -1, false, 16, null));
            arrayList2.add(new ElectionPageStage(electionId, electionPage.getId(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND, -1, false, 16, null));
            arrayList2.add(new ElectionPageStage(electionId, electionPage.getId(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY, -1, false, 16, null));
        }
        appDatabase.electionPageStageDao().insertAll(arrayList2);
    }

    /* compiled from: LoginWorker.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/support/worker/login/LoginWorker$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
