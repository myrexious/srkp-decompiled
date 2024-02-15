package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
final class AutoValue_CrashlyticsReport_Session_User extends CrashlyticsReport.Session.User {
    private final String identifier;

    private AutoValue_CrashlyticsReport_Session_User(String str) {
        this.identifier = str;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User
    public String getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        return "User{identifier=" + this.identifier + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.User) {
            return this.identifier.equals(((CrashlyticsReport.Session.User) obj).getIdentifier());
        }
        return false;
    }

    public int hashCode() {
        return this.identifier.hashCode() ^ 1000003;
    }

    /* loaded from: classes3.dex */
    public static final class Builder extends CrashlyticsReport.Session.User.Builder {
        private String identifier;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder
        public CrashlyticsReport.Session.User.Builder setIdentifier(String str) {
            if (str == null) {
                throw new NullPointerException("Null identifier");
            }
            this.identifier = str;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder
        public CrashlyticsReport.Session.User build() {
            String str = this.identifier == null ? " identifier" : "";
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_CrashlyticsReport_Session_User(this.identifier);
        }
    }
}
