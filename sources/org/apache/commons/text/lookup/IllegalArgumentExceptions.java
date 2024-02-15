package org.apache.commons.text.lookup;

/* loaded from: classes2.dex */
final class IllegalArgumentExceptions {
    public static IllegalArgumentException format(String str, Object... objArr) {
        return new IllegalArgumentException(String.format(str, objArr));
    }

    public static IllegalArgumentException format(Throwable th, String str, Object... objArr) {
        return new IllegalArgumentException(String.format(str, objArr), th);
    }

    private IllegalArgumentExceptions() {
    }
}
