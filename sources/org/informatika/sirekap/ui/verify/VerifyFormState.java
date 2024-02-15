package org.informatika.sirekap.ui.verify;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VerifyFormState.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u000bJ\b\u0010\u0017\u001a\u00020\u0004H\u0016R&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u00020\u000b8GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u00020\u000b8GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR&\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000f¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/ui/verify/VerifyFormState;", "Landroidx/databinding/BaseObservable;", "()V", "value", "", "comment", "getComment", "()Ljava/lang/String;", "setComment", "(Ljava/lang/String;)V", "commentTouched", "", "getCommentTouched", "()Z", "setCommentTouched", "(Z)V", "commentValid", "getCommentValid", "setCommentValid", "verifyOption", "getVerifyOption", "setVerifyOption", "isValid", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyFormState extends BaseObservable {
    private String comment = "";
    private boolean commentTouched;
    private boolean commentValid;
    private boolean verifyOption;

    @Bindable
    public final boolean getVerifyOption() {
        return this.verifyOption;
    }

    public final void setVerifyOption(boolean z) {
        this.verifyOption = z;
        if (z) {
            setComment("");
            this.commentValid = true;
        } else {
            this.commentValid = !StringsKt.isBlank(this.comment);
        }
        notifyPropertyChanged(4);
        notifyPropertyChanged(6);
        notifyPropertyChanged(86);
    }

    public final boolean isValid() {
        return this.verifyOption;
    }

    @Bindable
    public final String getComment() {
        return this.comment;
    }

    public final void setComment(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.comment = value;
        this.commentValid = this.verifyOption || !StringsKt.isBlank(value);
        notifyPropertyChanged(4);
        notifyPropertyChanged(6);
        if (this.commentTouched) {
            return;
        }
        this.commentTouched = true;
        notifyPropertyChanged(5);
    }

    @Bindable
    public final boolean getCommentTouched() {
        return this.commentTouched;
    }

    public final void setCommentTouched(boolean z) {
        this.commentTouched = z;
    }

    @Bindable
    public final boolean getCommentValid() {
        return this.commentValid;
    }

    public final void setCommentValid(boolean z) {
        this.commentValid = z;
    }

    public String toString() {
        boolean isValid = isValid();
        return "isSesuai: " + isValid + "\nkomentar: " + this.comment;
    }
}
