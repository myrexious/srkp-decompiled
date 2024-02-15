package org.informatika.sirekap.ui.witness.qrCode;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.R;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.WitnessUtil;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: ShareWitnessUrlUseCase.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00130\tX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0013\u0018\u00010\u00150\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0019\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018R\u0019\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0018R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0018R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0018R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0018R\u0019\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001b¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001eR\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0018¨\u00066"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/ShareWitnessUrlUseCase;", "", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "witness", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/WitnessWithShare;", "electionPemilihan", "", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/ElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Landroidx/lifecycle/LiveData;Ljava/lang/String;)V", "election", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElectionPemilihan", "()Ljava/lang/String;", "elections", "", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "isMultipleElections", "", "()Landroidx/lifecycle/LiveData;", "isUploadedPdf", "partai", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "Lorg/informatika/sirekap/model/Candidate;", "getPartai", "()Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "partaiString", "getPartaiString", "paslon", "getPaslon", "paslonString", "getPaslonString", "pemeriksa", "Lorg/informatika/sirekap/model/WitnessPemeriksa;", "getPemeriksa", "qrCodeBitmap", "Landroid/graphics/Bitmap;", "getQrCodeBitmap", "stringElectionTypeName", "getStringElectionTypeName", "stringShareInstruction", "getStringShareInstruction", "stringWarningUnshared", "getStringWarningUnshared", "stringWarningUnzipped", "getStringWarningUnzipped", "url", "getUrl", "getWitness", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ShareWitnessUrlUseCase {
    private final Context context;
    private final LiveData<ElectionWithRelation> election;
    private final String electionPemilihan;
    private final ElectionRepository electionRepository;
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final LiveData<Boolean> isMultipleElections;
    private final LiveData<Boolean> isUploadedPdf;
    private final CombinedLiveData<Candidate> partai;
    private final LiveData<String> partaiString;
    private final CombinedLiveData<Candidate> paslon;
    private final LiveData<String> paslonString;
    private final LiveData<WitnessPemeriksa> pemeriksa;
    private final LiveData<Bitmap> qrCodeBitmap;
    private final LiveData<String> stringElectionTypeName;
    private final LiveData<String> stringShareInstruction;
    private final LiveData<String> stringWarningUnshared;
    private final LiveData<String> stringWarningUnzipped;
    private final CombinedLiveData<String> url;
    private final LiveData<WitnessWithShare> witness;

    public ShareWitnessUrlUseCase(Context context, ElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, LiveData<WitnessWithShare> witness, String electionPemilihan) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(witness, "witness");
        Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
        this.context = context;
        this.electionRepository = electionRepository;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.witness = witness;
        this.electionPemilihan = electionPemilihan;
        this.isMultipleElections = Transformations.map(witness, new Function1<WitnessWithShare, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$isMultipleElections$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(org.informatika.sirekap.model.WitnessWithShare r2) {
                /*
                    r1 = this;
                    if (r2 == 0) goto L1a
                    java.util.List r0 = r2.getPemeriksas()
                    if (r0 == 0) goto L14
                    java.util.List r2 = r2.getPemeriksas()
                    int r2 = r2.size()
                    r0 = 1
                    if (r2 <= r0) goto L14
                    goto L15
                L14:
                    r0 = 0
                L15:
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
                    goto L1b
                L1a:
                    r2 = 0
                L1b:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$isMultipleElections$1.invoke(org.informatika.sirekap.model.WitnessWithShare):java.lang.Boolean");
            }
        });
        LiveData<WitnessPemeriksa> map = Transformations.map(witness, new Function1<WitnessWithShare, WitnessPemeriksa>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$pemeriksa$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WitnessPemeriksa invoke(WitnessWithShare witnessWithShare) {
                List<WitnessPemeriksa> pemeriksas;
                Object obj = null;
                if (witnessWithShare == null || (pemeriksas = witnessWithShare.getPemeriksas()) == null) {
                    return null;
                }
                ShareWitnessUrlUseCase shareWitnessUrlUseCase = ShareWitnessUrlUseCase.this;
                Iterator<T> it = pemeriksas.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(((WitnessPemeriksa) next).getJenisPemilihan(), shareWitnessUrlUseCase.getElectionPemilihan())) {
                        obj = next;
                        break;
                    }
                }
                return (WitnessPemeriksa) obj;
            }
        });
        this.pemeriksa = map;
        CombinedLiveData<String> combinedLiveData = new CombinedLiveData<>(new LiveData[]{witness, map}, new Function1<List<? extends Object>, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$url$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(List<? extends Object> data) {
                Witness witness2;
                EncryptedSharedPreferences encryptedSharedPreferences2;
                String generateWitnessShareUrlOnline;
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                WitnessWithShare witnessWithShare = obj instanceof WitnessWithShare ? (WitnessWithShare) obj : null;
                Object obj2 = data.get(1);
                WitnessPemeriksa witnessPemeriksa = obj2 instanceof WitnessPemeriksa ? (WitnessPemeriksa) obj2 : null;
                if (witnessWithShare == null || (witness2 = witnessWithShare.getWitness()) == null) {
                    return null;
                }
                ShareWitnessUrlUseCase shareWitnessUrlUseCase = ShareWitnessUrlUseCase.this;
                if (witnessPemeriksa != null) {
                    encryptedSharedPreferences2 = shareWitnessUrlUseCase.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences2.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    if (stringEncrypted == null) {
                        stringEncrypted = "";
                    }
                    if (StringsKt.isBlank(witnessPemeriksa.getUrl())) {
                        generateWitnessShareUrlOnline = WitnessUtil.Companion.generateWitnessShareUrlOffline(stringEncrypted, witnessPemeriksa.getJenisPemilihan(), witness2.getKodeTps());
                    } else {
                        generateWitnessShareUrlOnline = WitnessUtil.Companion.generateWitnessShareUrlOnline(stringEncrypted, witnessPemeriksa.getJenisPemilihan(), witnessPemeriksa.getUrl());
                    }
                    return generateWitnessShareUrlOnline;
                }
                return null;
            }
        });
        this.url = combinedLiveData;
        this.qrCodeBitmap = Transformations.map(combinedLiveData, new Function1<String, Bitmap>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$qrCodeBitmap$1
            @Override // kotlin.jvm.functions.Function1
            public final Bitmap invoke(String str) {
                if (str == null) {
                    return null;
                }
                BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, 512, 512);
                int width = encode.getWidth();
                int height = encode.getHeight();
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                for (int i = 0; i < width; i++) {
                    for (int i2 = 0; i2 < height; i2++) {
                        createBitmap.setPixel(i, i2, encode.get(i, i2) ? ViewCompat.MEASURED_STATE_MASK : -1);
                    }
                }
                return createBitmap;
            }
        });
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(witness, new Function1<WitnessWithShare, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$electionsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<ElectionWithRelation>>> invoke(WitnessWithShare witnessWithShare) {
                ElectionRepository electionRepository2;
                if (witnessWithShare != null) {
                    electionRepository2 = ShareWitnessUrlUseCase.this.electionRepository;
                    return electionRepository2.getElectionsByKodeTps(witnessWithShare.getWitness().getKodeTps());
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.electionsResource = switchMap;
        LiveData<List<ElectionWithRelation>> map2 = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.elections = map2;
        LiveData<ElectionWithRelation> map3 = Transformations.map(map2, new Function1<List<ElectionWithRelation>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$election$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(List<ElectionWithRelation> list) {
                Object obj = null;
                if (list != null) {
                    ShareWitnessUrlUseCase shareWitnessUrlUseCase = ShareWitnessUrlUseCase.this;
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) next).getElection().getPemilihan(), shareWitnessUrlUseCase.getElectionPemilihan())) {
                            obj = next;
                            break;
                        }
                    }
                    return (ElectionWithRelation) obj;
                }
                return null;
            }
        });
        this.election = map3;
        this.isUploadedPdf = Transformations.map(map3, new Function1<ElectionWithRelation, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$isUploadedPdf$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ElectionWithRelation electionWithRelation) {
                Election election;
                return Boolean.valueOf((electionWithRelation == null || (election = electionWithRelation.getElection()) == null) ? false : election.isUploadedPdf());
            }
        });
        CombinedLiveData<Candidate> combinedLiveData2 = new CombinedLiveData<>(new LiveData[]{map, map3}, new Function1<List<? extends Object>, Candidate>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$paslon$1
            @Override // kotlin.jvm.functions.Function1
            public final Candidate invoke(List<? extends Object> data) {
                List<Candidate> candidates;
                boolean z;
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                Object obj2 = null;
                WitnessPemeriksa witnessPemeriksa = obj instanceof WitnessPemeriksa ? (WitnessPemeriksa) obj : null;
                Object obj3 = data.get(1);
                ElectionWithRelation electionWithRelation = obj3 instanceof ElectionWithRelation ? (ElectionWithRelation) obj3 : null;
                Long valueOf = witnessPemeriksa != null ? Long.valueOf(witnessPemeriksa.getIdPilihan()) : null;
                if (electionWithRelation == null || (candidates = electionWithRelation.getCandidates()) == null) {
                    return null;
                }
                Iterator<T> it = candidates.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    long idPilihan = ((Candidate) next).getIdPilihan();
                    if (valueOf != null && idPilihan == valueOf.longValue()) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        obj2 = next;
                        break;
                    }
                }
                return (Candidate) obj2;
            }
        });
        this.paslon = combinedLiveData2;
        this.paslonString = Transformations.map(combinedLiveData2, new Function1<Candidate, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$paslonString$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Candidate candidate) {
                if (candidate != null) {
                    return candidate.getNama();
                }
                return null;
            }
        });
        CombinedLiveData<Candidate> combinedLiveData3 = new CombinedLiveData<>(new LiveData[]{map, map3}, new Function1<List<? extends Object>, Candidate>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$partai$1
            @Override // kotlin.jvm.functions.Function1
            public final Candidate invoke(List<? extends Object> data) {
                List<Candidate> candidates;
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                Object obj2 = null;
                WitnessPemeriksa witnessPemeriksa = obj instanceof WitnessPemeriksa ? (WitnessPemeriksa) obj : null;
                Object obj3 = data.get(1);
                ElectionWithRelation electionWithRelation = obj3 instanceof ElectionWithRelation ? (ElectionWithRelation) obj3 : null;
                Long valueOf = witnessPemeriksa != null ? Long.valueOf(witnessPemeriksa.getIdPilihan()) : null;
                if (electionWithRelation == null || (candidates = electionWithRelation.getCandidates()) == null) {
                    return null;
                }
                Iterator<T> it = candidates.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(((Candidate) next).getIdPartai(), valueOf)) {
                        obj2 = next;
                        break;
                    }
                }
                return (Candidate) obj2;
            }
        });
        this.partai = combinedLiveData3;
        this.partaiString = Transformations.map(combinedLiveData3, new Function1<Candidate, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$partaiString$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Candidate candidate) {
                if (candidate != null) {
                    return candidate.getNamaPartai();
                }
                return null;
            }
        });
        LiveData<String> map4 = Transformations.map(map3, new Function1<ElectionWithRelation, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$stringElectionTypeName$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(ElectionWithRelation electionWithRelation) {
                Election election;
                Context context2;
                if (electionWithRelation == null || (election = electionWithRelation.getElection()) == null) {
                    return null;
                }
                context2 = ShareWitnessUrlUseCase.this.context;
                return election.getDescription(context2);
            }
        });
        this.stringElectionTypeName = map4;
        this.stringShareInstruction = Transformations.map(map4, new Function1<String, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$stringShareInstruction$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String str) {
                Context context2;
                if (str != null) {
                    context2 = ShareWitnessUrlUseCase.this.context;
                    return context2.getString(R.string.witness_qr_code_instruction_pemilihan, str);
                }
                return null;
            }
        });
        this.stringWarningUnshared = Transformations.map(map4, new Function1<String, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$stringWarningUnshared$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String str) {
                Context context2;
                if (str != null) {
                    context2 = ShareWitnessUrlUseCase.this.context;
                    return context2.getString(R.string.witness_qr_code_warning_pemiihan, str);
                }
                return null;
            }
        });
        this.stringWarningUnzipped = Transformations.map(map4, new Function1<String, String>() { // from class: org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase$stringWarningUnzipped$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String str) {
                Context context2;
                if (str != null) {
                    context2 = ShareWitnessUrlUseCase.this.context;
                    return context2.getString(R.string.witness_qr_code_unzipped_warning_pemilihan, str);
                }
                return null;
            }
        });
    }

    public final LiveData<WitnessWithShare> getWitness() {
        return this.witness;
    }

    public final String getElectionPemilihan() {
        return this.electionPemilihan;
    }

    public final LiveData<Boolean> isMultipleElections() {
        return this.isMultipleElections;
    }

    public final LiveData<WitnessPemeriksa> getPemeriksa() {
        return this.pemeriksa;
    }

    public final CombinedLiveData<String> getUrl() {
        return this.url;
    }

    public final LiveData<Bitmap> getQrCodeBitmap() {
        return this.qrCodeBitmap;
    }

    public final LiveData<Boolean> isUploadedPdf() {
        return this.isUploadedPdf;
    }

    public final CombinedLiveData<Candidate> getPaslon() {
        return this.paslon;
    }

    public final LiveData<String> getPaslonString() {
        return this.paslonString;
    }

    public final CombinedLiveData<Candidate> getPartai() {
        return this.partai;
    }

    public final LiveData<String> getPartaiString() {
        return this.partaiString;
    }

    public final LiveData<String> getStringElectionTypeName() {
        return this.stringElectionTypeName;
    }

    public final LiveData<String> getStringShareInstruction() {
        return this.stringShareInstruction;
    }

    public final LiveData<String> getStringWarningUnshared() {
        return this.stringWarningUnshared;
    }

    public final LiveData<String> getStringWarningUnzipped() {
        return this.stringWarningUnzipped;
    }
}
