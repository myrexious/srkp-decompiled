package androidx.databinding;

import androidx.databinding.Observable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseObservableField extends BaseObservable {
    public BaseObservableField() {
    }

    public BaseObservableField(Observable... observableArr) {
        if (observableArr == null || observableArr.length == 0) {
            return;
        }
        DependencyCallback dependencyCallback = new DependencyCallback();
        for (Observable observable : observableArr) {
            observable.addOnPropertyChangedCallback(dependencyCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class DependencyCallback extends Observable.OnPropertyChangedCallback {
        DependencyCallback() {
            BaseObservableField.this = r1;
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            BaseObservableField.this.notifyChange();
        }
    }
}
