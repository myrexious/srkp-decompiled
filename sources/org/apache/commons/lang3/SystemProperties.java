package org.apache.commons.lang3;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public final class SystemProperties {
    public static final String AWT_TOOLKIT = "awt.toolkit";
    public static final String FILE_ENCODING = "file.encoding";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String JAVA_AWT_FONTS = "java.awt.fonts";
    public static final String JAVA_AWT_GRAPHICSENV = "java.awt.graphicsenv";
    public static final String JAVA_AWT_HEADLESS = "java.awt.headless";
    public static final String JAVA_AWT_PRINTERJOB = "java.awt.printerjob";
    public static final String JAVA_CLASS_PATH = "java.class.path";
    public static final String JAVA_CLASS_VERSION = "java.class.version";
    public static final String JAVA_COMPILER = "java.compiler";
    public static final String JAVA_ENDORSED_DIRS = "java.endorsed.dirs";
    public static final String JAVA_EXT_DIRS = "java.ext.dirs";
    public static final String JAVA_HOME = "java.home";
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
    public static final String JAVA_LIBRARY_PATH = "java.library.path";
    public static final String JAVA_LOCALE_PROVIDERS = "java.locale.providers";
    public static final String JAVA_RUNTIME_NAME = "java.runtime.name";
    public static final String JAVA_RUNTIME_VERSION = "java.runtime.version";
    public static final String JAVA_SPECIFICATION_NAME = "java.specification.name";
    public static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";
    public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
    public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = "java.util.prefs.PreferencesFactory";
    public static final String JAVA_VENDOR = "java.vendor";
    public static final String JAVA_VENDOR_URL = "java.vendor.url";
    public static final String JAVA_VERSION = "java.version";
    public static final String JAVA_VM_INFO = "java.vm.info";
    public static final String JAVA_VM_NAME = "java.vm.name";
    public static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";
    public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
    public static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";
    public static final String JAVA_VM_VENDOR = "java.vm.vendor";
    public static final String JAVA_VM_VERSION = "java.vm.version";
    public static final String LINE_SEPARATOR = "line.separator";
    private static final Supplier<String> NULL_SUPPLIER = new Supplier() { // from class: org.apache.commons.lang3.SystemProperties$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return SystemProperties.lambda$static$0();
        }
    };
    public static final String OS_ARCH = "os.arch";
    public static final String OS_NAME = "os.name";
    public static final String OS_VERSION = "os.version";
    public static final String PATH_SEPARATOR = "path.separator";
    public static final String USER_COUNTRY = "user.country";
    public static final String USER_DIR = "user.dir";
    public static final String USER_HOME = "user.home";
    public static final String USER_LANGUAGE = "user.language";
    public static final String USER_NAME = "user.name";
    public static final String USER_REGION = "user.region";
    public static final String USER_TIMEZONE = "user.timezone";

    public static /* synthetic */ String lambda$static$0() {
        return null;
    }

    public static String getAwtToolkit() {
        return getProperty(AWT_TOOLKIT);
    }

    public static boolean getBoolean(String str, BooleanSupplier booleanSupplier) {
        String property = getProperty(str);
        return property == null ? booleanSupplier != null && booleanSupplier.getAsBoolean() : Boolean.parseBoolean(property);
    }

    public static String getFileEncoding() {
        return getProperty(FILE_ENCODING);
    }

    public static String getFileSeparator() {
        return getProperty(FILE_SEPARATOR);
    }

    public static int getInt(String str, IntSupplier intSupplier) {
        String property = getProperty(str);
        if (property == null) {
            if (intSupplier != null) {
                return intSupplier.getAsInt();
            }
            return 0;
        }
        return Integer.parseInt(property);
    }

    public static String getJavaAwtFonts() {
        return getProperty(JAVA_AWT_FONTS);
    }

    public static String getJavaAwtGraphicsenv() {
        return getProperty(JAVA_AWT_GRAPHICSENV);
    }

    public static String getJavaAwtHeadless() {
        return getProperty(JAVA_AWT_HEADLESS);
    }

    public static String getJavaAwtPrinterjob() {
        return getProperty(JAVA_AWT_PRINTERJOB);
    }

    public static String getJavaClassPath() {
        return getProperty(JAVA_CLASS_PATH);
    }

    public static String getJavaClassVersion() {
        return getProperty(JAVA_CLASS_VERSION);
    }

    public static String getJavaCompiler() {
        return getProperty(JAVA_COMPILER);
    }

    public static String getJavaEndorsedDirs() {
        return getProperty(JAVA_ENDORSED_DIRS);
    }

    public static String getJavaExtDirs() {
        return getProperty(JAVA_EXT_DIRS);
    }

    public static String getJavaHome() {
        return getProperty("java.home");
    }

    public static String getJavaIoTmpdir() {
        return getProperty("java.io.tmpdir");
    }

    public static String getJavaLibraryPath() {
        return getProperty(JAVA_LIBRARY_PATH);
    }

    public static String getJavaLocaleProviders() {
        return getProperty(JAVA_LOCALE_PROVIDERS);
    }

    public static String getJavaRuntimeName() {
        return getProperty(JAVA_RUNTIME_NAME);
    }

    public static String getJavaRuntimeVersion() {
        return getProperty(JAVA_RUNTIME_VERSION);
    }

    public static String getJavaSpecificationName() {
        return getProperty(JAVA_SPECIFICATION_NAME);
    }

    public static String getJavaSpecificationVendor() {
        return getProperty(JAVA_SPECIFICATION_VENDOR);
    }

    public static String getJavaSpecificationVersion() {
        return getProperty(JAVA_SPECIFICATION_VERSION);
    }

    public static String getJavaUtilPrefsPreferencesFactory() {
        return getProperty(JAVA_UTIL_PREFS_PREFERENCES_FACTORY);
    }

    public static String getJavaVendor() {
        return getProperty(JAVA_VENDOR);
    }

    public static String getJavaVendorUrl() {
        return getProperty(JAVA_VENDOR_URL);
    }

    public static String getJavaVersion() {
        return getProperty(JAVA_VERSION);
    }

    public static String getJavaVmInfo() {
        return getProperty(JAVA_VM_INFO);
    }

    public static String getJavaVmName() {
        return getProperty(JAVA_VM_NAME);
    }

    public static String getJavaVmSpecificationName() {
        return getProperty(JAVA_VM_SPECIFICATION_NAME);
    }

    public static String getJavaVmSpecificationVendor() {
        return getProperty(JAVA_VM_SPECIFICATION_VENDOR);
    }

    public static String getJavaVmSpecificationVersion() {
        return getProperty(JAVA_VM_SPECIFICATION_VERSION);
    }

    public static String getJavaVmVendor() {
        return getProperty(JAVA_VM_VENDOR);
    }

    public static String getJavaVmVersion() {
        return getProperty(JAVA_VM_VERSION);
    }

    public static String getLineSeparator() {
        return getProperty(LINE_SEPARATOR);
    }

    public static long getLong(String str, LongSupplier longSupplier) {
        String property = getProperty(str);
        if (property == null) {
            if (longSupplier != null) {
                return longSupplier.getAsLong();
            }
            return 0L;
        }
        return Long.parseLong(property);
    }

    public static String getOsArch() {
        return getProperty(OS_ARCH);
    }

    public static String getOsName() {
        return getProperty(OS_NAME);
    }

    public static String getOsVersion() {
        return getProperty(OS_VERSION);
    }

    public static String getPathSeparator() {
        return getProperty(PATH_SEPARATOR);
    }

    public static String getProperty(String str) {
        return getProperty(str, NULL_SUPPLIER);
    }

    public static String getProperty(String str, Supplier<String> supplier) {
        try {
            if (StringUtils.isEmpty(str)) {
                return supplier.get();
            }
            return (String) StringUtils.getIfEmpty(System.getProperty(str), supplier);
        } catch (SecurityException unused) {
            return supplier.get();
        }
    }

    public static String getUserCountry() {
        return getProperty(USER_COUNTRY);
    }

    public static String getUserDir() {
        return getProperty("user.dir");
    }

    public static String getUserHome() {
        return getProperty("user.home");
    }

    public static String getUserLanguage() {
        return getProperty(USER_LANGUAGE);
    }

    public static String getUserName() {
        return getProperty("user.name");
    }

    public static String getUserTimezone() {
        return getProperty(USER_TIMEZONE);
    }
}
