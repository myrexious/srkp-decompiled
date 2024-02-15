package org.informatika.sirekap.ui.aprilTagConflict;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* compiled from: AprilTagConflictViewModel.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/aprilTagConflict/AprilTagConflictViewModel;", "Landroidx/lifecycle/ViewModel;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "canSubmit", "Landroidx/lifecycle/LiveData;", "", "getCanSubmit", "()Landroidx/lifecycle/LiveData;", "getElectionPageUseCaseAprilTag", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCaseAprilTag", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getElectionPageUseCaseManual", "getGetElectionPageUseCaseManual", "previewImagePath", "Landroidx/lifecycle/MutableLiveData;", "", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "selectedElectionPage", "", "getSelectedElectionPage", "setup", "", "_previewImagePath", "_electionPageIdManual", "_electionPageIdAprilTag", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AprilTagConflictViewModel extends ViewModel {
    private final LiveData<Boolean> canSubmit;
    private final GetElectionPageUseCase getElectionPageUseCaseAprilTag;
    private final GetElectionPageUseCase getElectionPageUseCaseManual;
    private final MutableLiveData<String> previewImagePath;
    private final MutableLiveData<Integer> selectedElectionPage;

    @Inject
    public AprilTagConflictViewModel(DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.previewImagePath = new MutableLiveData<>(null);
        DefaultElectionRepository defaultElectionRepository = electionRepository;
        this.getElectionPageUseCaseManual = new GetElectionPageUseCase(defaultElectionRepository);
        this.getElectionPageUseCaseAprilTag = new GetElectionPageUseCase(defaultElectionRepository);
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(null);
        this.selectedElectionPage = mutableLiveData;
        this.canSubmit = Transformations.map(mutableLiveData, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel$canSubmit$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer num) {
                return Boolean.valueOf(num != null);
            }
        });
    }

    public final void setup(String _previewImagePath, String _electionPageIdManual, String _electionPageIdAprilTag) {
        Intrinsics.checkNotNullParameter(_previewImagePath, "_previewImagePath");
        Intrinsics.checkNotNullParameter(_electionPageIdManual, "_electionPageIdManual");
        Intrinsics.checkNotNullParameter(_electionPageIdAprilTag, "_electionPageIdAprilTag");
        this.previewImagePath.setValue(_previewImagePath);
        this.getElectionPageUseCaseManual.setup(_electionPageIdManual);
        this.getElectionPageUseCaseAprilTag.setup(_electionPageIdAprilTag);
    }

    public final MutableLiveData<String> getPreviewImagePath() {
        return this.previewImagePath;
    }

    public final GetElectionPageUseCase getGetElectionPageUseCaseManual() {
        return this.getElectionPageUseCaseManual;
    }

    public final GetElectionPageUseCase getGetElectionPageUseCaseAprilTag() {
        return this.getElectionPageUseCaseAprilTag;
    }

    public final MutableLiveData<Integer> getSelectedElectionPage() {
        return this.selectedElectionPage;
    }

    public final LiveData<Boolean> getCanSubmit() {
        return this.canSubmit;
    }
}
