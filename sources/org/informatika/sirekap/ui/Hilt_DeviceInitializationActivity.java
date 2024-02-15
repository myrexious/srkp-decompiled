package org.informatika.sirekap.ui;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;

/* loaded from: classes4.dex */
public abstract class Hilt_DeviceInitializationActivity extends BaseActivity {
    private boolean injected = false;

    public Hilt_DeviceInitializationActivity() {
        _initHiltInternal();
    }

    private void _initHiltInternal() {
        addOnContextAvailableListener(new OnContextAvailableListener() { // from class: org.informatika.sirekap.ui.Hilt_DeviceInitializationActivity.1
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(Context context) {
                Hilt_DeviceInitializationActivity.this.inject();
            }
        });
    }

    @Override // org.informatika.sirekap.ui.Hilt_BaseActivity
    protected void inject() {
        if (this.injected) {
            return;
        }
        this.injected = true;
        ((DeviceInitializationActivity_GeneratedInjector) ((GeneratedComponentManagerHolder) UnsafeCasts.unsafeCast(this)).generatedComponent()).injectDeviceInitializationActivity((DeviceInitializationActivity) UnsafeCasts.unsafeCast(this));
    }
}
