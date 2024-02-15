package androidx.biometric;

import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BiometricErrorData {
    private final int mErrorCode;
    private final CharSequence mErrorMessage;

    public BiometricErrorData(int i, CharSequence charSequence) {
        this.mErrorCode = i;
        this.mErrorMessage = charSequence;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mErrorCode), convertToString(this.mErrorMessage)});
    }

    public boolean equals(Object obj) {
        if (obj instanceof BiometricErrorData) {
            BiometricErrorData biometricErrorData = (BiometricErrorData) obj;
            return this.mErrorCode == biometricErrorData.mErrorCode && isErrorMessageEqualTo(biometricErrorData.mErrorMessage);
        }
        return false;
    }

    private boolean isErrorMessageEqualTo(CharSequence charSequence) {
        String convertToString = convertToString(this.mErrorMessage);
        String convertToString2 = convertToString(charSequence);
        return (convertToString == null && convertToString2 == null) || (convertToString != null && convertToString.equals(convertToString2));
    }

    private static String convertToString(CharSequence charSequence) {
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
}
