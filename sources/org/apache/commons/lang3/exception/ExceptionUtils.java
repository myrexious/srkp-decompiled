package org.apache.commons.lang3.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class ExceptionUtils {
    private static final String[] CAUSE_METHOD_NAMES = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
    private static final int NOT_FOUND = -1;
    static final String WRAPPED_MARKER = " [wrapped] ";

    private static <R, T extends Throwable> R eraseType(Throwable th) throws Throwable {
        throw th;
    }

    public static void forEach(Throwable th, Consumer<Throwable> consumer) {
        stream(th).forEach(consumer);
    }

    @Deprecated
    public static Throwable getCause(Throwable th) {
        return getCause(th, null);
    }

    @Deprecated
    public static Throwable getCause(final Throwable th, String[] strArr) {
        if (th == null) {
            return null;
        }
        if (strArr == null) {
            Throwable cause = th.getCause();
            if (cause != null) {
                return cause;
            }
            strArr = CAUSE_METHOD_NAMES;
        }
        return (Throwable) Stream.of((Object[]) strArr).map(new Function() { // from class: org.apache.commons.lang3.exception.ExceptionUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Throwable causeUsingMethodName;
                causeUsingMethodName = ExceptionUtils.getCauseUsingMethodName(th, (String) obj);
                return causeUsingMethodName;
            }
        }).filter(new Predicate() { // from class: org.apache.commons.lang3.exception.ExceptionUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((Throwable) obj);
                return nonNull;
            }
        }).findFirst().orElse(null);
    }

    public static Throwable getCauseUsingMethodName(Throwable th, String str) {
        Method method;
        if (str != null) {
            try {
                method = th.getClass().getMethod(str, new Class[0]);
            } catch (NoSuchMethodException | SecurityException unused) {
                method = null;
            }
            if (method != null && Throwable.class.isAssignableFrom(method.getReturnType())) {
                try {
                    return (Throwable) method.invoke(th, new Object[0]);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
                }
            }
        }
        return null;
    }

    @Deprecated
    public static String[] getDefaultCauseMethodNames() {
        return (String[]) ArrayUtils.clone(CAUSE_METHOD_NAMES);
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        return ClassUtils.getShortClassName(th, null) + ": " + StringUtils.defaultString(th.getMessage());
    }

    public static Throwable getRootCause(Throwable th) {
        List<Throwable> throwableList = getThrowableList(th);
        if (throwableList.isEmpty()) {
            return null;
        }
        return throwableList.get(throwableList.size() - 1);
    }

    public static String getRootCauseMessage(Throwable th) {
        Throwable rootCause = getRootCause(th);
        if (rootCause != null) {
            th = rootCause;
        }
        return getMessage(th);
    }

    public static String[] getRootCauseStackTrace(Throwable th) {
        return (String[]) getRootCauseStackTraceList(th).toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static List<String> getRootCauseStackTraceList(Throwable th) {
        List<String> list;
        if (th == null) {
            return Collections.emptyList();
        }
        Throwable[] throwables = getThrowables(th);
        int length = throwables.length;
        ArrayList arrayList = new ArrayList();
        int i = length - 1;
        List<String> stackFrameList = getStackFrameList(throwables[i]);
        while (true) {
            length--;
            if (length < 0) {
                return arrayList;
            }
            if (length != 0) {
                list = getStackFrameList(throwables[length - 1]);
                removeCommonFrames(stackFrameList, list);
            } else {
                list = stackFrameList;
            }
            if (length == i) {
                arrayList.add(throwables[length].toString());
            } else {
                arrayList.add(WRAPPED_MARKER + throwables[length].toString());
            }
            arrayList.addAll(stackFrameList);
            stackFrameList = list;
        }
    }

    static List<String> getStackFrameList(Throwable th) {
        StringTokenizer stringTokenizer = new StringTokenizer(getStackTrace(th), System.lineSeparator());
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf("at");
            if (indexOf != -1 && nextToken.substring(0, indexOf).trim().isEmpty()) {
                arrayList.add(nextToken);
                z = true;
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    static String[] getStackFrames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, System.lineSeparator());
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static String[] getStackFrames(Throwable th) {
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return getStackFrames(getStackTrace(th));
    }

    public static String getStackTrace(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        return stringWriter.toString();
    }

    public static int getThrowableCount(Throwable th) {
        return getThrowableList(th).size();
    }

    public static List<Throwable> getThrowableList(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = th.getCause();
        }
        return arrayList;
    }

    public static Throwable[] getThrowables(Throwable th) {
        return (Throwable[]) getThrowableList(th).toArray(ArrayUtils.EMPTY_THROWABLE_ARRAY);
    }

    public static boolean hasCause(Throwable th, Class<? extends Throwable> cls) {
        if (th instanceof UndeclaredThrowableException) {
            th = th.getCause();
        }
        return cls.isInstance(th);
    }

    private static int indexOf(Throwable th, Class<? extends Throwable> cls, int i, boolean z) {
        if (th != null && cls != null) {
            if (i < 0) {
                i = 0;
            }
            Throwable[] throwables = getThrowables(th);
            if (i >= throwables.length) {
                return -1;
            }
            if (z) {
                while (i < throwables.length) {
                    if (cls.isAssignableFrom(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            } else {
                while (i < throwables.length) {
                    if (cls.equals(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    public static int indexOfThrowable(Throwable th, Class<? extends Throwable> cls) {
        return indexOf(th, cls, 0, false);
    }

    public static int indexOfThrowable(Throwable th, Class<? extends Throwable> cls, int i) {
        return indexOf(th, cls, i, false);
    }

    public static int indexOfType(Throwable th, Class<? extends Throwable> cls) {
        return indexOf(th, cls, 0, true);
    }

    public static int indexOfType(Throwable th, Class<? extends Throwable> cls, int i) {
        return indexOf(th, cls, i, true);
    }

    public static boolean isChecked(Throwable th) {
        return (th == null || (th instanceof Error) || (th instanceof RuntimeException)) ? false : true;
    }

    public static boolean isUnchecked(Throwable th) {
        return th != null && ((th instanceof Error) || (th instanceof RuntimeException));
    }

    public static void printRootCauseStackTrace(Throwable th) {
        printRootCauseStackTrace(th, System.err);
    }

    public static void printRootCauseStackTrace(Throwable th, final PrintStream printStream) {
        if (th == null) {
            return;
        }
        Objects.requireNonNull(printStream, "printStream");
        List<String> rootCauseStackTraceList = getRootCauseStackTraceList(th);
        printStream.getClass();
        rootCauseStackTraceList.forEach(new Consumer() { // from class: org.apache.commons.lang3.exception.ExceptionUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                printStream.println((String) obj);
            }
        });
        printStream.flush();
    }

    public static void printRootCauseStackTrace(Throwable th, final PrintWriter printWriter) {
        if (th == null) {
            return;
        }
        Objects.requireNonNull(printWriter, "printWriter");
        List<String> rootCauseStackTraceList = getRootCauseStackTraceList(th);
        printWriter.getClass();
        rootCauseStackTraceList.forEach(new Consumer() { // from class: org.apache.commons.lang3.exception.ExceptionUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                printWriter.println((String) obj);
            }
        });
        printWriter.flush();
    }

    public static void removeCommonFrames(List<String> list, List<String> list2) {
        Objects.requireNonNull(list, "causeFrames");
        Objects.requireNonNull(list2, "wrapperFrames");
        int size = list.size() - 1;
        for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
            if (list.get(size).equals(list2.get(size2))) {
                list.remove(size);
            }
            size--;
        }
    }

    public static <R> R rethrow(Throwable th) {
        return (R) eraseType(th);
    }

    public static Stream<Throwable> stream(Throwable th) {
        return getThrowableList(th).stream();
    }

    private static <T extends Throwable> T throwableOf(Throwable th, Class<T> cls, int i, boolean z) {
        if (th != null && cls != null) {
            if (i < 0) {
                i = 0;
            }
            Throwable[] throwables = getThrowables(th);
            if (i >= throwables.length) {
                return null;
            }
            if (z) {
                while (i < throwables.length) {
                    if (cls.isAssignableFrom(throwables[i].getClass())) {
                        return cls.cast(throwables[i]);
                    }
                    i++;
                }
            } else {
                while (i < throwables.length) {
                    if (cls.equals(throwables[i].getClass())) {
                        return cls.cast(throwables[i]);
                    }
                    i++;
                }
            }
        }
        return null;
    }

    public static <T extends Throwable> T throwableOfThrowable(Throwable th, Class<T> cls) {
        return (T) throwableOf(th, cls, 0, false);
    }

    public static <T extends Throwable> T throwableOfThrowable(Throwable th, Class<T> cls, int i) {
        return (T) throwableOf(th, cls, i, false);
    }

    public static <T extends Throwable> T throwableOfType(Throwable th, Class<T> cls) {
        return (T) throwableOf(th, cls, 0, true);
    }

    public static <T extends Throwable> T throwableOfType(Throwable th, Class<T> cls, int i) {
        return (T) throwableOf(th, cls, i, true);
    }

    public static <T> T throwUnchecked(T t) {
        if (t instanceof RuntimeException) {
            throw ((RuntimeException) t);
        }
        if (t instanceof Error) {
            throw ((Error) t);
        }
        return t;
    }

    public static <R> R wrapAndThrow(Throwable th) {
        throw new UndeclaredThrowableException((Throwable) throwUnchecked(th));
    }
}
