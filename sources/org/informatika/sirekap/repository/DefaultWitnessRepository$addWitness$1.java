package org.informatika.sirekap.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.google.firebase.messaging.Constants;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.WitnessDetailApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.WitnessNetworkBoundResource;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: WitnessRepository.kt */
@Metadata(d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001J$\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0006H\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0014J\u0018\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0014¨\u0006\u0013"}, d2 = {"org/informatika/sirekap/repository/DefaultWitnessRepository$addWitness$1", "Lorg/informatika/sirekap/support/WitnessNetworkBoundResource;", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "Lorg/informatika/sirekap/api/response/WitnessDetailApiResponse;", "createCall", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "idPilihan", "", "jenisPemilihan", "", "loadFromDb", "saveCallResult", "", "item", "shouldFetch", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultWitnessRepository$addWitness$1 extends WitnessNetworkBoundResource<List<? extends WitnessWithShare>, WitnessDetailApiResponse> {
    final /* synthetic */ String $noHandphone;
    final /* synthetic */ WitnessRepository.AddWitnessModel $witness;
    final /* synthetic */ DefaultWitnessRepository this$0;

    /* renamed from: shouldFetch */
    protected boolean shouldFetch2(List<WitnessWithShare> list) {
        return true;
    }

    @Override // org.informatika.sirekap.support.WitnessNetworkBoundResource
    public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends WitnessWithShare> list) {
        return shouldFetch2((List<WitnessWithShare>) list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultWitnessRepository$addWitness$1(Ref.ObjectRef<List<Long>> objectRef, Ref.ObjectRef<List<String>> objectRef2, DefaultWitnessRepository defaultWitnessRepository, String str, WitnessRepository.AddWitnessModel addWitnessModel, AppExecutors appExecutors) {
        super(appExecutors, objectRef.element, objectRef2.element);
        this.this$0 = defaultWitnessRepository;
        this.$noHandphone = str;
        this.$witness = addWitnessModel;
    }

    @Override // org.informatika.sirekap.support.WitnessNetworkBoundResource
    public void saveCallResult(WitnessDetailApiResponse item) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(item, "item");
        final Witness data = item.getData();
        final DefaultWitnessRepository defaultWitnessRepository = this.this$0;
        final String str = this.$noHandphone;
        final WitnessRepository.AddWitnessModel addWitnessModel = this.$witness;
        appDatabase = defaultWitnessRepository.database;
        appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$addWitness$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository$addWitness$1.saveCallResult$lambda$2$lambda$1(str, defaultWitnessRepository, data, addWitnessModel);
            }
        });
    }

    public static final void saveCallResult$lambda$2$lambda$1(String str, DefaultWitnessRepository this$0, Witness it, WitnessRepository.AddWitnessModel witness) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        Intrinsics.checkNotNullParameter(witness, "$witness");
        if (str != null) {
            appDatabase = this$0.database;
            appDatabase.witnessDao().deleteByNoHandphone(str);
        }
        ArrayList arrayList = new ArrayList();
        List<WitnessPemeriksa> pemeriksas = it.getPemeriksas();
        if (pemeriksas != null) {
            int i = 0;
            for (Object obj : pemeriksas) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                WitnessPemeriksa witnessPemeriksa = (WitnessPemeriksa) obj;
                arrayList.add(new WitnessPemeriksa(witness.getNoHandphone(), witnessPemeriksa.getJenisPemilihan(), witnessPemeriksa.getIdPilihan(), witnessPemeriksa.getUrl()));
                i = i2;
            }
        }
        this$0.insertWitnesses(CollectionsKt.listOf(new Witness(it.getIdPetugas(), witness.getNama(), witness.getNik(), witness.getNoHandphone(), witness.getJenisPemeriksa(), witness.getKodeTps(), it.getUrutan(), arrayList)), CollectionsKt.listOf(SecurityFacade.INSTANCE.encryptCode(witness.getNoHandphone())));
    }

    @Override // org.informatika.sirekap.support.WitnessNetworkBoundResource
    public LiveData<List<? extends WitnessWithShare>> loadFromDb() {
        AppDatabase appDatabase;
        appDatabase = this.this$0.database;
        return appDatabase.witnessDao().getAllByKodeTps(this.$witness.getKodeTps());
    }

    @Override // org.informatika.sirekap.support.WitnessNetworkBoundResource
    protected LiveData<ApiResponse<WitnessDetailApiResponse>> createCall(long j, String jenisPemilihan) {
        EncryptedSharedPreferences encryptedSharedPreferences;
        SirekapApiInterface sirekapApiInterface;
        Context context;
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        encryptedSharedPreferences = this.this$0.encryptedSharedPreferences;
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(this.$witness.getKodeTps());
        String extractWitnessIdReal = ElectionUtil.extractWitnessIdReal(this.$witness.getNoHandphone());
        sirekapApiInterface = this.this$0.api;
        String str = "https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/petugas/create";
        String nama = this.$witness.getNama();
        String jenisPemeriksa = this.$witness.getJenisPemeriksa();
        String nik = this.$witness.getNik();
        List<String> jenisPemilihan2 = this.$witness.getJenisPemilihan();
        SecurityFacade securityFacade = SecurityFacade.INSTANCE;
        context = this.this$0.context;
        return sirekapApiInterface.addWitness(str, extractKodeTpsReal, nama, jenisPemeriksa, nik, extractWitnessIdReal, j, securityFacade.getDeviceId(context), jenisPemilihan2, BuildConfig.USER_ID);
    }
}
