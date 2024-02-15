package org.informatika.sirekap.ui.tpsTime;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.repository.TpsTimeRepository;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.usecase.AddTpsTimeRequest;
import org.informatika.sirekap.usecase.AddTpsTimeUseCase;
import org.informatika.sirekap.usecase.GetTpsTimeUseCase;

/* compiled from: TpsTimeViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u00192\u0006\u0010=\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020\u00192\u0006\u0010?\u001a\u00020\rJ.\u0010@\u001a\u00020;2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u00192\u0006\u0010A\u001a\u00020\rJ(\u0010B\u001a\u00020;2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020;0D2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020;0FR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001f\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR%\u0010\u001d\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e0\u0013¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u000fR\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0016R\u0019\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0013¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0016R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0013¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0016R\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0013¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0016R\u0019\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0016R\u001f\u00102\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001cR%\u00104\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e0\u0013¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0016R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001cR\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u00190\f¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u000f¨\u0006G"}, d2 = {"Lorg/informatika/sirekap/ui/tpsTime/TpsTimeViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "tpsTimeRepository", "Lorg/informatika/sirekap/repository/TpsTimeRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/TpsTimeRepository;)V", "addTpsTimeUseCase", "Lorg/informatika/sirekap/usecase/AddTpsTimeUseCase;", "getAddTpsTimeUseCase", "()Lorg/informatika/sirekap/usecase/AddTpsTimeUseCase;", "canFillForm", "Lorg/informatika/sirekap/support/CombinedLiveData;", "", "getCanFillForm", "()Lorg/informatika/sirekap/support/CombinedLiveData;", "canSubmit", "getCanSubmit", "endDate", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/Date;", "getEndDate", "()Landroidx/lifecycle/MutableLiveData;", "endDateFormatted", "Landroidx/lifecycle/LiveData;", "", "kotlin.jvm.PlatformType", "getEndDateFormatted", "()Landroidx/lifecycle/LiveData;", "endTime", "Lkotlin/Pair;", "", "getEndTime", "endTimeFormatted", "getEndTimeFormatted", "formInstruction", "getFormInstruction", "getTpsTimeUseCase", "Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase;", "getGetTpsTimeUseCase", "()Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase;", "isElectionZipped", "jenisPemilihan", "getJenisPemilihan", "jenisWaktu", "getJenisWaktu", "kodeTps", "getKodeTps", "startDate", "getStartDate", "startDateFormatted", "getStartDateFormatted", "startTime", "getStartTime", "startTimeFormatted", "getStartTimeFormatted", "successMessage", "getSuccessMessage", "setup", "", "_kodeTps", "_jenisWaktu", "_jenisPemilihan", "_isElectionZipped", "submit", "isOffline", "validateInput", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class TpsTimeViewModel extends BaseVerifyViewModel {
    private final AddTpsTimeUseCase addTpsTimeUseCase;
    private final CombinedLiveData<Boolean> canFillForm;
    private final CombinedLiveData<Boolean> canSubmit;
    private final MutableLiveData<Date> endDate;
    private final LiveData<String> endDateFormatted;
    private final MutableLiveData<Pair<Integer, Integer>> endTime;
    private final LiveData<String> endTimeFormatted;
    private final CombinedLiveData<String> formInstruction;
    private final GetTpsTimeUseCase getTpsTimeUseCase;
    private final MutableLiveData<Boolean> isElectionZipped;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<Integer> jenisWaktu;
    private final MutableLiveData<String> kodeTps;
    private final MutableLiveData<Date> startDate;
    private final LiveData<String> startDateFormatted;
    private final MutableLiveData<Pair<Integer, Integer>> startTime;
    private final LiveData<String> startTimeFormatted;
    private final CombinedLiveData<String> successMessage;

    @Inject
    public TpsTimeViewModel(@ApplicationContext final Context context, TpsTimeRepository tpsTimeRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tpsTimeRepository, "tpsTimeRepository");
        GetTpsTimeUseCase getTpsTimeUseCase = new GetTpsTimeUseCase(tpsTimeRepository);
        this.getTpsTimeUseCase = getTpsTimeUseCase;
        this.addTpsTimeUseCase = new AddTpsTimeUseCase(tpsTimeRepository);
        MutableLiveData<Date> mutableLiveData = new MutableLiveData<>(null);
        this.startDate = mutableLiveData;
        this.startDateFormatted = Transformations.map(mutableLiveData, new Function1<Date, String>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$startDateFormatted$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Date date) {
                return date == null ? "" : new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID)).format(Long.valueOf(date.getTime()));
            }
        });
        MutableLiveData<Pair<Integer, Integer>> mutableLiveData2 = new MutableLiveData<>(null);
        this.startTime = mutableLiveData2;
        this.startTimeFormatted = Transformations.map(mutableLiveData2, new Function1<Pair<Integer, Integer>, String>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$startTimeFormatted$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Pair<Integer, Integer> pair) {
                if (pair == null) {
                    return "";
                }
                String padStart = StringsKt.padStart(String.valueOf(pair.getFirst().intValue()), 2, '0');
                return padStart + ":" + StringsKt.padStart(String.valueOf(pair.getSecond().intValue()), 2, '0');
            }
        });
        MutableLiveData<Date> mutableLiveData3 = new MutableLiveData<>(null);
        this.endDate = mutableLiveData3;
        this.endDateFormatted = Transformations.map(mutableLiveData3, new Function1<Date, String>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$endDateFormatted$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Date date) {
                return date == null ? "" : new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID)).format(Long.valueOf(date.getTime()));
            }
        });
        MutableLiveData<Pair<Integer, Integer>> mutableLiveData4 = new MutableLiveData<>(null);
        this.endTime = mutableLiveData4;
        this.endTimeFormatted = Transformations.map(mutableLiveData4, new Function1<Pair<Integer, Integer>, String>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$endTimeFormatted$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Pair<Integer, Integer> pair) {
                if (pair == null) {
                    return "";
                }
                String padStart = StringsKt.padStart(String.valueOf(pair.getFirst().intValue()), 2, '0');
                return padStart + ":" + StringsKt.padStart(String.valueOf(pair.getSecond().intValue()), 2, '0');
            }
        });
        this.canSubmit = new CombinedLiveData<>(new LiveData[]{mutableLiveData, mutableLiveData2, mutableLiveData3, mutableLiveData4}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$canSubmit$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                boolean z;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                boolean z2 = true;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (it.next() != null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (!z) {
                            z2 = false;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z2);
            }
        });
        MutableLiveData<String> mutableLiveData5 = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData5;
        MutableLiveData<Integer> mutableLiveData6 = new MutableLiveData<>(null);
        this.jenisWaktu = mutableLiveData6;
        MutableLiveData<String> mutableLiveData7 = new MutableLiveData<>(null);
        this.jenisPemilihan = mutableLiveData7;
        MutableLiveData<Boolean> mutableLiveData8 = new MutableLiveData<>(null);
        this.isElectionZipped = mutableLiveData8;
        this.formInstruction = new CombinedLiveData<>(new LiveData[]{mutableLiveData5, mutableLiveData6, mutableLiveData7}, new Function1<List<? extends Object>, String>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$formInstruction$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                String str;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return "";
                }
                Object obj2 = data.get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                int intValue = ((Integer) obj3).intValue();
                Object obj4 = data.get(2);
                Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.String");
                String str2 = (String) obj4;
                boolean startsWith$default = StringsKt.startsWith$default((String) obj2, "11", false, 2, (Object) null);
                if (intValue != 0) {
                    if (intValue != 1) {
                        return "Silakan isi waktu mulai dan waktu selesai proses pada TPS.";
                    }
                    switch (str2.hashCode()) {
                        case -992700931:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                                if (startsWith$default) {
                                    str = context.getString(R.string.election_type_pdprdk_aceh);
                                    break;
                                } else {
                                    str = context.getString(R.string.election_type_pdprdk_general);
                                    break;
                                }
                            }
                            str = "pemilihan";
                            break;
                        case -992700926:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                                if (startsWith$default) {
                                    str = context.getString(R.string.election_type_pdprdp_aceh);
                                    break;
                                } else {
                                    str = context.getString(R.string.election_type_pdprdp_general);
                                    break;
                                }
                            }
                            str = "pemilihan";
                            break;
                        case 3436264:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPD)) {
                                str = context.getString(R.string.election_type_pdpd_general);
                                break;
                            }
                            str = "pemilihan";
                            break;
                        case 3436278:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                                str = context.getString(R.string.election_type_pdpr);
                                break;
                            }
                            str = "pemilihan";
                            break;
                        case 3448025:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                                str = context.getString(R.string.election_type_pilpres);
                                break;
                            }
                            str = "pemilihan";
                            break;
                        default:
                            str = "pemilihan";
                            break;
                    }
                    Intrinsics.checkNotNullExpressionValue(str, "when (jenisPemilihan) {\n…an\"\n                    }");
                    return "Silakan isi waktu mulai dan waktu selesai penghitungan suara pada " + str + ".";
                }
                return "Silakan isi waktu mulai dan waktu selesai proses pemungutan suara pada TPS.";
            }
        });
        this.successMessage = new CombinedLiveData<>(new LiveData[]{mutableLiveData5, mutableLiveData6, mutableLiveData7, getTpsTimeUseCase.getData()}, new Function1<List<? extends Object>, String>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$successMessage$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                String str;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return "";
                }
                Object obj2 = data.get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                int intValue = ((Integer) obj3).intValue();
                Object obj4 = data.get(2);
                Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.String");
                String str2 = (String) obj4;
                boolean startsWith$default = StringsKt.startsWith$default((String) obj2, "11", false, 2, (Object) null);
                Object obj5 = data.get(3);
                Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type org.informatika.sirekap.model.TpsTime");
                String str3 = ((TpsTime) obj5).isSubmittedOffline() ? "disimpan (offline)" : "dikirim";
                if (intValue != 0) {
                    if (intValue != 1) {
                        return "Pencatatan waktu pada TPS sudah berhasil " + str3 + ".";
                    }
                    switch (str2.hashCode()) {
                        case -992700931:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                                if (startsWith$default) {
                                    str = context.getString(R.string.election_type_pdprdk_aceh);
                                    break;
                                } else {
                                    str = context.getString(R.string.election_type_pdprdk_general);
                                    break;
                                }
                            }
                            str = "pemilihan";
                            break;
                        case -992700926:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                                if (startsWith$default) {
                                    str = context.getString(R.string.election_type_pdprdp_aceh);
                                    break;
                                } else {
                                    str = context.getString(R.string.election_type_pdprdp_general);
                                    break;
                                }
                            }
                            str = "pemilihan";
                            break;
                        case 3436264:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPD)) {
                                str = context.getString(R.string.election_type_pdpd_general);
                                break;
                            }
                            str = "pemilihan";
                            break;
                        case 3436278:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                                str = context.getString(R.string.election_type_pdpr);
                                break;
                            }
                            str = "pemilihan";
                            break;
                        case 3448025:
                            if (str2.equals(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                                str = context.getString(R.string.election_type_pilpres);
                                break;
                            }
                            str = "pemilihan";
                            break;
                        default:
                            str = "pemilihan";
                            break;
                    }
                    Intrinsics.checkNotNullExpressionValue(str, "when (jenisPemilihan) {\n…an\"\n                    }");
                    return "Pencatatan waktu penghitungan suara pada " + str + " sudah berhasil " + str3 + ".";
                }
                return "Pencatatan waktu pemungutan suara pada TPS sudah berhasil " + str3 + ".";
            }
        });
        this.canFillForm = new CombinedLiveData<>(new LiveData[]{mutableLiveData6, mutableLiveData8}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel$canFillForm$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                boolean z = false;
                if (data.get(0) != null) {
                    Object obj = data.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    z = ((Integer) obj).intValue() == 0 ? true : Intrinsics.areEqual((Object) ((Boolean) data.get(1)), (Object) true);
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public final GetTpsTimeUseCase getGetTpsTimeUseCase() {
        return this.getTpsTimeUseCase;
    }

    public final AddTpsTimeUseCase getAddTpsTimeUseCase() {
        return this.addTpsTimeUseCase;
    }

    public final MutableLiveData<Date> getStartDate() {
        return this.startDate;
    }

    public final LiveData<String> getStartDateFormatted() {
        return this.startDateFormatted;
    }

    public final MutableLiveData<Pair<Integer, Integer>> getStartTime() {
        return this.startTime;
    }

    public final LiveData<String> getStartTimeFormatted() {
        return this.startTimeFormatted;
    }

    public final MutableLiveData<Date> getEndDate() {
        return this.endDate;
    }

    public final LiveData<String> getEndDateFormatted() {
        return this.endDateFormatted;
    }

    public final MutableLiveData<Pair<Integer, Integer>> getEndTime() {
        return this.endTime;
    }

    public final LiveData<String> getEndTimeFormatted() {
        return this.endTimeFormatted;
    }

    public final CombinedLiveData<Boolean> getCanSubmit() {
        return this.canSubmit;
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final MutableLiveData<Integer> getJenisWaktu() {
        return this.jenisWaktu;
    }

    public final MutableLiveData<String> getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final MutableLiveData<Boolean> isElectionZipped() {
        return this.isElectionZipped;
    }

    public final void setup(String _kodeTps, int i, String _jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        this.kodeTps.postValue(_kodeTps);
        this.jenisWaktu.postValue(Integer.valueOf(i));
        this.jenisPemilihan.postValue(_jenisPemilihan);
        this.isElectionZipped.postValue(Boolean.valueOf(z));
        this.getTpsTimeUseCase.setup(_kodeTps, i, _jenisPemilihan);
    }

    public final void validateInput(Function0<Unit> onSuccess, Function1<? super String, Unit> onError) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Calendar calendar = Calendar.getInstance();
        Date value = this.startDate.getValue();
        Intrinsics.checkNotNull(value);
        calendar.setTime(value);
        Pair<Integer, Integer> value2 = this.startTime.getValue();
        Intrinsics.checkNotNull(value2);
        calendar.set(11, value2.getFirst().intValue());
        Pair<Integer, Integer> value3 = this.startTime.getValue();
        Intrinsics.checkNotNull(value3);
        calendar.set(12, value3.getSecond().intValue());
        calendar.set(13, 0);
        Calendar calendar2 = Calendar.getInstance();
        Date value4 = this.endDate.getValue();
        Intrinsics.checkNotNull(value4);
        calendar2.setTime(value4);
        Pair<Integer, Integer> value5 = this.endTime.getValue();
        Intrinsics.checkNotNull(value5);
        calendar2.set(11, value5.getFirst().intValue());
        Pair<Integer, Integer> value6 = this.endTime.getValue();
        Intrinsics.checkNotNull(value6);
        calendar2.set(12, value6.getSecond().intValue());
        calendar2.set(13, 0);
        if (calendar.getTime().compareTo(calendar2.getTime()) > 0) {
            onError.invoke("Waktu Selesai tidak bisa lebih awal daripada Waktu Mulai");
        } else {
            onSuccess.invoke();
        }
    }

    public final void submit(Context context, String kodeTps, int i, String jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        AddTpsTimeUseCase addTpsTimeUseCase = this.addTpsTimeUseCase;
        Date value = this.startDate.getValue();
        Intrinsics.checkNotNull(value);
        Date date = value;
        Pair<Integer, Integer> value2 = this.startTime.getValue();
        Intrinsics.checkNotNull(value2);
        Pair<Integer, Integer> pair = value2;
        Date value3 = this.endDate.getValue();
        Intrinsics.checkNotNull(value3);
        Date date2 = value3;
        Pair<Integer, Integer> value4 = this.endTime.getValue();
        Intrinsics.checkNotNull(value4);
        addTpsTimeUseCase.submit(new AddTpsTimeRequest(kodeTps, date, pair, date2, value4, i, jenisPemilihan, SecurityFacade.INSTANCE.getDeviceId(context), z));
    }

    public final CombinedLiveData<String> getFormInstruction() {
        return this.formInstruction;
    }

    public final CombinedLiveData<String> getSuccessMessage() {
        return this.successMessage;
    }

    public final CombinedLiveData<Boolean> getCanFillForm() {
        return this.canFillForm;
    }
}
