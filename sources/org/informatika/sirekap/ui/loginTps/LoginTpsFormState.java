package org.informatika.sirekap.ui.loginTps;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Tps;

/* compiled from: LoginTpsFormState.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0002R&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R&\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u001c\u0010\u001f\u001a\u00020\u00048GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0007\"\u0004\b!\u0010\t¨\u0006$"}, d2 = {"Lorg/informatika/sirekap/ui/loginTps/LoginTpsFormState;", "Landroidx/databinding/BaseObservable;", "()V", "value", "", "allValid", "getAllValid", "()Z", "setAllValid", "(Z)V", "", "tps", "getTps", "()Ljava/lang/String;", "setTps", "(Ljava/lang/String;)V", "tpsInternalValue", "Lorg/informatika/sirekap/model/Tps;", "getTpsInternalValue", "()Lorg/informatika/sirekap/model/Tps;", "setTpsInternalValue", "(Lorg/informatika/sirekap/model/Tps;)V", "tpsOptions", "", "getTpsOptions", "()Ljava/util/Map;", "setTpsOptions", "(Ljava/util/Map;)V", "tpsTouched", "getTpsTouched", "setTpsTouched", "tpsValid", "getTpsValid", "setTpsValid", "verifyAllForms", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LoginTpsFormState extends BaseObservable {
    private boolean allValid;
    private Tps tpsInternalValue;
    private boolean tpsTouched;
    private boolean tpsValid;
    private Map<String, Tps> tpsOptions = MapsKt.emptyMap();
    private String tps = "";

    public final Map<String, Tps> getTpsOptions() {
        return this.tpsOptions;
    }

    public final void setTpsOptions(Map<String, Tps> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.tpsOptions = map;
    }

    public final Tps getTpsInternalValue() {
        return this.tpsInternalValue;
    }

    public final void setTpsInternalValue(Tps tps) {
        this.tpsInternalValue = tps;
    }

    @Bindable
    public final String getTps() {
        return this.tps;
    }

    public final void setTps(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.tps = value;
        this.tpsValid = this.tpsOptions.keySet().contains(value);
        Iterator<T> it = this.tpsOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.tpsInternalValue = entry != null ? (Tps) entry.getValue() : null;
        verifyAllForms();
        notifyPropertyChanged(81);
        notifyPropertyChanged(83);
        if (this.tpsTouched) {
            return;
        }
        setTpsTouched(true);
    }

    @Bindable
    public final boolean getTpsTouched() {
        return this.tpsTouched;
    }

    public final void setTpsTouched(boolean z) {
        this.tpsTouched = z;
        notifyPropertyChanged(82);
    }

    @Bindable
    public final boolean getTpsValid() {
        return this.tpsValid;
    }

    public final void setTpsValid(boolean z) {
        this.tpsValid = z;
    }

    @Bindable
    public final boolean getAllValid() {
        return this.allValid;
    }

    public final void setAllValid(boolean z) {
        this.allValid = z;
        notifyPropertyChanged(1);
    }

    private final void verifyAllForms() {
        setAllValid(this.tpsValid);
    }
}
