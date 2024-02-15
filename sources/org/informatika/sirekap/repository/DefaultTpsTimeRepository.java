package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.GenericApiResponse;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: TpsTimeRepository.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJv\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00122\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0015J\u001e\u0010!\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0015J\u001e\u0010\"\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0015J.\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J`\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00122\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0004R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultTpsTimeRepository;", "Lorg/informatika/sirekap/repository/TpsTimeRepository;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "addTpsTime", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/TpsTime;", "kodeTps", "", "startDate", "Ljava/util/Date;", "startTime", "Lkotlin/Pair;", "", "endDate", "endTime", "jenisWaktu", "jenisPemilihan", "deviceId", "isOffline", "", "formatDateTimeForApi", "date", "hour", "minute", "formatDateTimeForApiNew", "formatDateTimeForApiUtc", "getTpsTime", "saveCallResultAddTpsTime", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultTpsTimeRepository implements TpsTimeRepository {
    private final SirekapApiInterface api;
    private final AppExecutors appExecutors;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    @Inject
    public DefaultTpsTimeRepository(AppExecutors appExecutors, AppDatabase database, SirekapApiInterface api, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.appExecutors = appExecutors;
        this.database = database;
        this.api = api;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.TpsTimeRepository
    public LiveData<Resource<TpsTime>> getTpsTime(final String kodeTps, final int i, final String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new NetworkBoundResource<TpsTime, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultTpsTimeRepository$getTpsTime$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(TpsTime tpsTime) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<TpsTime> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultTpsTimeRepository.this.database;
                return appDatabase.tpsTimeDao().getByKodeTps(kodeTps, i, jenisPemilihan);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", null, 1), ""));
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.TpsTimeRepository
    public LiveData<Resource<TpsTime>> addTpsTime(final String kodeTps, final Date startDate, final Pair<Integer, Integer> startTime, final Date endDate, final Pair<Integer, Integer> endTime, final int i, final String jenisPemilihan, final String deviceId, final boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return new NetworkBoundResource<TpsTime, GenericApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultTpsTimeRepository$addTpsTime$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(TpsTime tpsTime) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(GenericApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultTpsTimeRepository.this.saveCallResultAddTpsTime(kodeTps, startDate, startTime, endDate, endTime, i, jenisPemilihan, z);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<TpsTime> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultTpsTimeRepository.this.database;
                return appDatabase.tpsTimeDao().getByKodeTps(kodeTps, i, jenisPemilihan);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<GenericApiResponse>> createCall() {
                EncryptedSharedPreferences encryptedSharedPreferences;
                EncryptedSharedPreferences encryptedSharedPreferences2;
                SirekapApiInterface sirekapApiInterface;
                boolean z2 = true;
                if (!z) {
                    encryptedSharedPreferences = DefaultTpsTimeRepository.this.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    String formatDateTimeForApiNew = DefaultTpsTimeRepository.this.formatDateTimeForApiNew(startDate, startTime.getFirst().intValue(), startTime.getSecond().intValue());
                    String formatDateTimeForApiNew2 = DefaultTpsTimeRepository.this.formatDateTimeForApiNew(endDate, endTime.getFirst().intValue(), endTime.getSecond().intValue());
                    encryptedSharedPreferences2 = DefaultTpsTimeRepository.this.encryptedSharedPreferences;
                    String stringEncrypted2 = encryptedSharedPreferences2.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
                    String str = stringEncrypted2;
                    if (str != null && !StringsKt.isBlank(str)) {
                        z2 = false;
                    }
                    String str2 = z2 ? "R" : stringEncrypted2;
                    String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTps);
                    sirekapApiInterface = DefaultTpsTimeRepository.this.api;
                    return sirekapApiInterface.addInfoTps("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/info/tps", extractKodeTpsReal, formatDateTimeForApiNew, formatDateTimeForApiNew2, i, deviceId, str2);
                }
                return new MutableLiveData(new ApiSuccessResponse(new GenericApiResponse(true, "", null), (String) null));
            }
        }.asLiveData();
    }

    public final String formatDateTimeForApiNew(Date date, int i, int i2) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", new Locale(JobType.ID));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(newDate.time)");
        return format;
    }

    public final String formatDateTimeForApi(Date date, int i, int i2) {
        Intrinsics.checkNotNullParameter(date, "date");
        String format = new SimpleDateFormat("yyyy-MM-dd", new Locale(JobType.ID)).format(date);
        String padStart = StringsKt.padStart(String.valueOf(i), 2, '0');
        return format + "T" + padStart + ":" + StringsKt.padStart(String.valueOf(i2), 2, '0') + ":00.000";
    }

    public final String formatDateTimeForApiUtc(Date date, int i, int i2) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", new Locale(JobType.ID));
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(newDate.time)");
        return format;
    }

    public final void saveCallResultAddTpsTime(final String kodeTps, final Date startDate, final Pair<Integer, Integer> startTime, final Date endDate, final Pair<Integer, Integer> endTime, final int i, final String jenisPemilihan, final boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultTpsTimeRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultTpsTimeRepository.saveCallResultAddTpsTime$lambda$2(DefaultTpsTimeRepository.this, startDate, startTime, endDate, endTime, kodeTps, i, jenisPemilihan, z);
            }
        });
    }

    public static final void saveCallResultAddTpsTime$lambda$2(DefaultTpsTimeRepository this$0, Date startDate, Pair startTime, Date endDate, Pair endTime, String kodeTps, int i, String jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(startDate, "$startDate");
        Intrinsics.checkNotNullParameter(startTime, "$startTime");
        Intrinsics.checkNotNullParameter(endDate, "$endDate");
        Intrinsics.checkNotNullParameter(endTime, "$endTime");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        this$0.database.tpsTimeDao().insertAll(CollectionsKt.listOf(new TpsTime(kodeTps, i, jenisPemilihan, startDate.getTime(), ((Number) startTime.getFirst()).intValue(), ((Number) startTime.getSecond()).intValue(), endDate.getTime(), ((Number) endTime.getFirst()).intValue(), ((Number) endTime.getSecond()).intValue(), z)));
    }
}
