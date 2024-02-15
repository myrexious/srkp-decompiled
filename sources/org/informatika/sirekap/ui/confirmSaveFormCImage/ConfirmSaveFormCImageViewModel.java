package org.informatika.sirekap.ui.confirmSaveFormCImage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* compiled from: ConfirmSaveFormCImageViewModel.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/ui/confirmSaveFormCImage/ConfirmSaveFormCImageViewModel;", "Landroidx/lifecycle/ViewModel;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "previewImagePath", "Landroidx/lifecycle/MutableLiveData;", "", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "setup", "", "_electionPageId", "_previewImagePath", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ConfirmSaveFormCImageViewModel extends ViewModel {
    private final GetElectionPageUseCase getElectionPageUseCase;
    private final MutableLiveData<String> previewImagePath;

    @Inject
    public ConfirmSaveFormCImageViewModel(DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.previewImagePath = new MutableLiveData<>(null);
        this.getElectionPageUseCase = new GetElectionPageUseCase(electionRepository);
    }

    public final void setup(String _electionPageId, String _previewImagePath) {
        Intrinsics.checkNotNullParameter(_electionPageId, "_electionPageId");
        Intrinsics.checkNotNullParameter(_previewImagePath, "_previewImagePath");
        this.getElectionPageUseCase.setup(_electionPageId);
        this.previewImagePath.postValue(_previewImagePath);
    }

    public final MutableLiveData<String> getPreviewImagePath() {
        return this.previewImagePath;
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }
}
