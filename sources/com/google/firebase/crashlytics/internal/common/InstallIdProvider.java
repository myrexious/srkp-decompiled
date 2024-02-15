package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes3.dex */
public interface InstallIdProvider {
    InstallIds getInstallIds();

    /* loaded from: classes3.dex */
    public static abstract class InstallIds {
        public abstract String getCrashlyticsInstallId();

        public abstract String getFirebaseInstallationId();

        public static InstallIds createWithoutFid(String str) {
            return create(str, null);
        }

        public static InstallIds create(String str, String str2) {
            return new AutoValue_InstallIdProvider_InstallIds(str, str2);
        }
    }
}
