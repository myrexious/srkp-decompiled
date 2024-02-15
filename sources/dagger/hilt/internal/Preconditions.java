package dagger.hilt.internal;

/* loaded from: classes3.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T reference) {
        reference.getClass();
        return reference;
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(errorMessageTemplate, args));
        }
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object... args) {
        if (!expression) {
            throw new IllegalStateException(String.format(errorMessageTemplate, args));
        }
    }

    private Preconditions() {
    }
}
