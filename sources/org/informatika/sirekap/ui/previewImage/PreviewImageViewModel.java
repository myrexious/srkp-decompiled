package org.informatika.sirekap.ui.previewImage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PreviewImageViewModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/ui/previewImage/PreviewImageViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "imageFilePath", "Landroidx/lifecycle/MutableLiveData;", "", "getImageFilePath", "()Landroidx/lifecycle/MutableLiveData;", "setup", "", "_imageFilePath", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PreviewImageViewModel extends ViewModel {
    private final MutableLiveData<String> imageFilePath = new MutableLiveData<>(null);

    public final MutableLiveData<String> getImageFilePath() {
        return this.imageFilePath;
    }

    public final void setup(String _imageFilePath) {
        Intrinsics.checkNotNullParameter(_imageFilePath, "_imageFilePath");
        if (Intrinsics.areEqual(this.imageFilePath.getValue(), _imageFilePath)) {
            return;
        }
        this.imageFilePath.postValue(_imageFilePath);
    }
}
