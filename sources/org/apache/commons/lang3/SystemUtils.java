package org.apache.commons.lang3;

import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import java.io.File;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class SystemUtils {
    public static final String AWT_TOOLKIT;
    public static final boolean IS_JAVA_10;
    public static final boolean IS_JAVA_11;
    public static final boolean IS_JAVA_12;
    public static final boolean IS_JAVA_13;
    public static final boolean IS_JAVA_14;
    public static final boolean IS_JAVA_15;
    public static final boolean IS_JAVA_16;
    public static final boolean IS_JAVA_17;
    public static final boolean IS_JAVA_18;
    public static final boolean IS_JAVA_19;
    public static final boolean IS_JAVA_1_1;
    public static final boolean IS_JAVA_1_2;
    public static final boolean IS_JAVA_1_3;
    public static final boolean IS_JAVA_1_4;
    public static final boolean IS_JAVA_1_5;
    public static final boolean IS_JAVA_1_6;
    public static final boolean IS_JAVA_1_7;
    public static final boolean IS_JAVA_1_8;
    @Deprecated
    public static final boolean IS_JAVA_1_9;
    public static final boolean IS_JAVA_20;
    public static final boolean IS_JAVA_21;
    public static final boolean IS_JAVA_9;
    public static final boolean IS_OS_400;
    public static final boolean IS_OS_AIX;
    public static final boolean IS_OS_FREE_BSD;
    public static final boolean IS_OS_HP_UX;
    public static final boolean IS_OS_IRIX;
    public static final boolean IS_OS_LINUX;
    public static final boolean IS_OS_MAC;
    public static final boolean IS_OS_MAC_OSX;
    public static final boolean IS_OS_MAC_OSX_BIG_SUR;
    public static final boolean IS_OS_MAC_OSX_CATALINA;
    public static final boolean IS_OS_MAC_OSX_CHEETAH;
    public static final boolean IS_OS_MAC_OSX_EL_CAPITAN;
    public static final boolean IS_OS_MAC_OSX_HIGH_SIERRA;
    public static final boolean IS_OS_MAC_OSX_JAGUAR;
    public static final boolean IS_OS_MAC_OSX_LEOPARD;
    public static final boolean IS_OS_MAC_OSX_LION;
    public static final boolean IS_OS_MAC_OSX_MAVERICKS;
    public static final boolean IS_OS_MAC_OSX_MOJAVE;
    public static final boolean IS_OS_MAC_OSX_MONTEREY;
    public static final boolean IS_OS_MAC_OSX_MOUNTAIN_LION;
    public static final boolean IS_OS_MAC_OSX_PANTHER;
    public static final boolean IS_OS_MAC_OSX_PUMA;
    public static final boolean IS_OS_MAC_OSX_SIERRA;
    public static final boolean IS_OS_MAC_OSX_SNOW_LEOPARD;
    public static final boolean IS_OS_MAC_OSX_TIGER;
    public static final boolean IS_OS_MAC_OSX_VENTURA;
    public static final boolean IS_OS_MAC_OSX_YOSEMITE;
    public static final boolean IS_OS_NET_BSD;
    public static final boolean IS_OS_OPEN_BSD;
    public static final boolean IS_OS_OS2;
    public static final boolean IS_OS_SOLARIS;
    public static final boolean IS_OS_SUN_OS;
    public static final boolean IS_OS_UNIX;
    public static final boolean IS_OS_WINDOWS;
    public static final boolean IS_OS_WINDOWS_10;
    public static final boolean IS_OS_WINDOWS_11;
    public static final boolean IS_OS_WINDOWS_2000;
    public static final boolean IS_OS_WINDOWS_2003;
    public static final boolean IS_OS_WINDOWS_2008;
    public static final boolean IS_OS_WINDOWS_2012;
    public static final boolean IS_OS_WINDOWS_7;
    public static final boolean IS_OS_WINDOWS_8;
    public static final boolean IS_OS_WINDOWS_95;
    public static final boolean IS_OS_WINDOWS_98;
    public static final boolean IS_OS_WINDOWS_ME;
    public static final boolean IS_OS_WINDOWS_NT;
    public static final boolean IS_OS_WINDOWS_VISTA;
    public static final boolean IS_OS_WINDOWS_XP;
    public static final boolean IS_OS_ZOS;
    @Deprecated
    public static final String JAVA_HOME_KEY = "java.home";
    @Deprecated
    public static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
    public static final String JAVA_SPECIFICATION_VERSION;
    private static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM;
    public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY;
    public static final String JAVA_VENDOR;
    public static final String JAVA_VENDOR_URL;
    public static final String JAVA_VERSION;
    public static final String JAVA_VM_INFO;
    public static final String JAVA_VM_NAME;
    public static final String JAVA_VM_SPECIFICATION_NAME;
    public static final String JAVA_VM_SPECIFICATION_VENDOR;
    public static final String JAVA_VM_SPECIFICATION_VERSION;
    public static final String JAVA_VM_VENDOR;
    public static final String JAVA_VM_VERSION;
    @Deprecated
    public static final String LINE_SEPARATOR;
    public static final String OS_ARCH;
    public static final String OS_NAME;
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    public static final String OS_VERSION;
    @Deprecated
    public static final String PATH_SEPARATOR;
    public static final String USER_COUNTRY;
    public static final String USER_DIR;
    @Deprecated
    public static final String USER_DIR_KEY = "user.dir";
    public static final String USER_HOME;
    public static final String USER_HOME_KEY = "user.home";
    public static final String USER_LANGUAGE;
    public static final String USER_NAME;
    @Deprecated
    public static final String USER_NAME_KEY = "user.name";
    public static final String USER_TIMEZONE;
    public static final String FILE_ENCODING = SystemProperties.getFileEncoding();
    @Deprecated
    public static final String FILE_SEPARATOR = SystemProperties.getFileSeparator();
    public static final String JAVA_AWT_FONTS = SystemProperties.getJavaAwtFonts();
    public static final String JAVA_AWT_GRAPHICSENV = SystemProperties.getJavaAwtGraphicsenv();
    public static final String JAVA_AWT_HEADLESS = SystemProperties.getJavaAwtHeadless();
    public static final String JAVA_AWT_PRINTERJOB = SystemProperties.getJavaAwtPrinterjob();
    public static final String JAVA_CLASS_PATH = SystemProperties.getJavaClassPath();
    public static final String JAVA_CLASS_VERSION = SystemProperties.getJavaClassVersion();
    public static final String JAVA_COMPILER = SystemProperties.getJavaCompiler();
    public static final String JAVA_ENDORSED_DIRS = SystemProperties.getJavaEndorsedDirs();
    public static final String JAVA_EXT_DIRS = SystemProperties.getJavaExtDirs();
    public static final String JAVA_HOME = SystemProperties.getJavaHome();
    public static final String JAVA_IO_TMPDIR = SystemProperties.getJavaIoTmpdir();
    public static final String JAVA_LIBRARY_PATH = SystemProperties.getJavaLibraryPath();
    public static final String JAVA_RUNTIME_NAME = SystemProperties.getJavaRuntimeName();
    public static final String JAVA_RUNTIME_VERSION = SystemProperties.getJavaRuntimeVersion();
    public static final String JAVA_SPECIFICATION_NAME = SystemProperties.getJavaSpecificationName();
    public static final String JAVA_SPECIFICATION_VENDOR = SystemProperties.getJavaSpecificationVendor();

    static {
        String javaSpecificationVersion = SystemProperties.getJavaSpecificationVersion();
        JAVA_SPECIFICATION_VERSION = javaSpecificationVersion;
        JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(javaSpecificationVersion);
        JAVA_UTIL_PREFS_PREFERENCES_FACTORY = SystemProperties.getJavaUtilPrefsPreferencesFactory();
        JAVA_VENDOR = SystemProperties.getJavaVendor();
        JAVA_VENDOR_URL = SystemProperties.getJavaVendorUrl();
        JAVA_VERSION = SystemProperties.getJavaVersion();
        JAVA_VM_INFO = SystemProperties.getJavaVmInfo();
        JAVA_VM_NAME = SystemProperties.getJavaVmName();
        JAVA_VM_SPECIFICATION_NAME = SystemProperties.getJavaVmSpecificationName();
        JAVA_VM_SPECIFICATION_VENDOR = SystemProperties.getJavaVmSpecificationVendor();
        JAVA_VM_SPECIFICATION_VERSION = SystemProperties.getJavaVmSpecificationVersion();
        JAVA_VM_VENDOR = SystemProperties.getJavaVmVendor();
        JAVA_VM_VERSION = SystemProperties.getJavaVmVersion();
        LINE_SEPARATOR = SystemProperties.getLineSeparator();
        OS_ARCH = SystemProperties.getOsArch();
        OS_NAME = SystemProperties.getOsName();
        OS_VERSION = SystemProperties.getOsVersion();
        PATH_SEPARATOR = SystemProperties.getPathSeparator();
        USER_COUNTRY = SystemProperties.getProperty(SystemProperties.USER_COUNTRY, new Supplier() { // from class: org.apache.commons.lang3.SystemUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                String property;
                property = SystemProperties.getProperty(SystemProperties.USER_REGION);
                return property;
            }
        });
        USER_DIR = SystemProperties.getUserDir();
        USER_HOME = SystemProperties.getUserHome();
        USER_LANGUAGE = SystemProperties.getUserLanguage();
        USER_NAME = SystemProperties.getUserName();
        USER_TIMEZONE = SystemProperties.getUserTimezone();
        IS_JAVA_1_1 = getJavaVersionMatches("1.1");
        IS_JAVA_1_2 = getJavaVersionMatches("1.2");
        IS_JAVA_1_3 = getJavaVersionMatches("1.3");
        IS_JAVA_1_4 = getJavaVersionMatches("1.4");
        IS_JAVA_1_5 = getJavaVersionMatches("1.5");
        IS_JAVA_1_6 = getJavaVersionMatches("1.6");
        IS_JAVA_1_7 = getJavaVersionMatches("1.7");
        IS_JAVA_1_8 = getJavaVersionMatches("1.8");
        IS_JAVA_1_9 = getJavaVersionMatches("9");
        IS_JAVA_9 = getJavaVersionMatches("9");
        IS_JAVA_10 = getJavaVersionMatches("10");
        IS_JAVA_11 = getJavaVersionMatches("11");
        IS_JAVA_12 = getJavaVersionMatches("12");
        IS_JAVA_13 = getJavaVersionMatches("13");
        IS_JAVA_14 = getJavaVersionMatches("14");
        IS_JAVA_15 = getJavaVersionMatches("15");
        IS_JAVA_16 = getJavaVersionMatches("16");
        IS_JAVA_17 = getJavaVersionMatches("17");
        IS_JAVA_18 = getJavaVersionMatches("18");
        IS_JAVA_19 = getJavaVersionMatches("19");
        IS_JAVA_20 = getJavaVersionMatches("20");
        IS_JAVA_21 = getJavaVersionMatches("21");
        boolean osMatchesName = getOsMatchesName("AIX");
        IS_OS_AIX = osMatchesName;
        boolean osMatchesName2 = getOsMatchesName("HP-UX");
        IS_OS_HP_UX = osMatchesName2;
        IS_OS_400 = getOsMatchesName("OS/400");
        boolean osMatchesName3 = getOsMatchesName("Irix");
        IS_OS_IRIX = osMatchesName3;
        boolean z = false;
        boolean z2 = getOsMatchesName("Linux") || getOsMatchesName("LINUX");
        IS_OS_LINUX = z2;
        IS_OS_MAC = getOsMatchesName("Mac");
        boolean osMatchesName4 = getOsMatchesName("Mac OS X");
        IS_OS_MAC_OSX = osMatchesName4;
        IS_OS_MAC_OSX_CHEETAH = getOsMatches("Mac OS X", "10.0");
        IS_OS_MAC_OSX_PUMA = getOsMatches("Mac OS X", "10.1");
        IS_OS_MAC_OSX_JAGUAR = getOsMatches("Mac OS X", "10.2");
        IS_OS_MAC_OSX_PANTHER = getOsMatches("Mac OS X", "10.3");
        IS_OS_MAC_OSX_TIGER = getOsMatches("Mac OS X", "10.4");
        IS_OS_MAC_OSX_LEOPARD = getOsMatches("Mac OS X", "10.5");
        IS_OS_MAC_OSX_SNOW_LEOPARD = getOsMatches("Mac OS X", "10.6");
        IS_OS_MAC_OSX_LION = getOsMatches("Mac OS X", "10.7");
        IS_OS_MAC_OSX_MOUNTAIN_LION = getOsMatches("Mac OS X", "10.8");
        IS_OS_MAC_OSX_MAVERICKS = getOsMatches("Mac OS X", "10.9");
        IS_OS_MAC_OSX_YOSEMITE = getOsMatches("Mac OS X", "10.10");
        IS_OS_MAC_OSX_EL_CAPITAN = getOsMatches("Mac OS X", "10.11");
        IS_OS_MAC_OSX_SIERRA = getOsMatches("Mac OS X", "10.12");
        IS_OS_MAC_OSX_HIGH_SIERRA = getOsMatches("Mac OS X", "10.13");
        IS_OS_MAC_OSX_MOJAVE = getOsMatches("Mac OS X", "10.14");
        IS_OS_MAC_OSX_CATALINA = getOsMatches("Mac OS X", "10.15");
        IS_OS_MAC_OSX_BIG_SUR = getOsMatches("Mac OS X", "11");
        IS_OS_MAC_OSX_MONTEREY = getOsMatches("Mac OS X", "12");
        IS_OS_MAC_OSX_VENTURA = getOsMatches("Mac OS X", "13");
        boolean osMatchesName5 = getOsMatchesName("FreeBSD");
        IS_OS_FREE_BSD = osMatchesName5;
        boolean osMatchesName6 = getOsMatchesName("OpenBSD");
        IS_OS_OPEN_BSD = osMatchesName6;
        boolean osMatchesName7 = getOsMatchesName("NetBSD");
        IS_OS_NET_BSD = osMatchesName7;
        IS_OS_OS2 = getOsMatchesName(OS2WindowsMetricsTable.TAG);
        boolean osMatchesName8 = getOsMatchesName("Solaris");
        IS_OS_SOLARIS = osMatchesName8;
        boolean osMatchesName9 = getOsMatchesName("SunOS");
        IS_OS_SUN_OS = osMatchesName9;
        if (osMatchesName || osMatchesName2 || osMatchesName3 || z2 || osMatchesName4 || osMatchesName8 || osMatchesName9 || osMatchesName5 || osMatchesName6 || osMatchesName7) {
            z = true;
        }
        IS_OS_UNIX = z;
        IS_OS_WINDOWS = getOsMatchesName(OS_NAME_WINDOWS_PREFIX);
        IS_OS_WINDOWS_2000 = getOsMatchesName("Windows 2000");
        IS_OS_WINDOWS_2003 = getOsMatchesName("Windows 2003");
        IS_OS_WINDOWS_2008 = getOsMatchesName("Windows Server 2008");
        IS_OS_WINDOWS_2012 = getOsMatchesName("Windows Server 2012");
        IS_OS_WINDOWS_95 = getOsMatchesName("Windows 95");
        IS_OS_WINDOWS_98 = getOsMatchesName("Windows 98");
        IS_OS_WINDOWS_ME = getOsMatchesName("Windows Me");
        IS_OS_WINDOWS_NT = getOsMatchesName("Windows NT");
        IS_OS_WINDOWS_XP = getOsMatchesName("Windows XP");
        IS_OS_WINDOWS_VISTA = getOsMatchesName("Windows Vista");
        IS_OS_WINDOWS_7 = getOsMatchesName("Windows 7");
        IS_OS_WINDOWS_8 = getOsMatchesName("Windows 8");
        IS_OS_WINDOWS_10 = getOsMatchesName("Windows 10");
        IS_OS_WINDOWS_11 = getOsMatchesName("Windows 11");
        IS_OS_ZOS = getOsMatchesName("z/OS");
        AWT_TOOLKIT = SystemProperties.getAwtToolkit();
    }

    public static String getEnvironmentVariable(String str, String str2) {
        try {
            String str3 = System.getenv(str);
            return str3 == null ? str2 : str3;
        } catch (SecurityException unused) {
            return str2;
        }
    }

    public static String getHostName() {
        return System.getenv(IS_OS_WINDOWS ? "COMPUTERNAME" : "HOSTNAME");
    }

    public static File getJavaHome() {
        return new File(SystemProperties.getJavaHome());
    }

    public static File getJavaIoTmpDir() {
        return new File(SystemProperties.getJavaIoTmpdir());
    }

    private static boolean getJavaVersionMatches(String str) {
        return isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, str);
    }

    private static boolean getOsMatches(String str, String str2) {
        return isOSMatch(OS_NAME, OS_VERSION, str, str2);
    }

    private static boolean getOsMatchesName(String str) {
        return isOSNameMatch(OS_NAME, str);
    }

    public static File getUserDir() {
        return new File(SystemProperties.getUserDir());
    }

    public static File getUserHome() {
        return new File(SystemProperties.getUserHome());
    }

    @Deprecated
    public static String getUserName() {
        return SystemProperties.getUserName();
    }

    public static String getUserName(String str) {
        return System.getProperty("user.name", str);
    }

    public static boolean isJavaAwtHeadless() {
        return Boolean.TRUE.toString().equals(JAVA_AWT_HEADLESS);
    }

    public static boolean isJavaVersionAtLeast(JavaVersion javaVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(javaVersion);
    }

    public static boolean isJavaVersionAtMost(JavaVersion javaVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atMost(javaVersion);
    }

    static boolean isJavaVersionMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static boolean isOSMatch(String str, String str2, String str3, String str4) {
        return str != null && str2 != null && isOSNameMatch(str, str3) && isOSVersionMatch(str2, str4);
    }

    static boolean isOSNameMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static boolean isOSVersionMatch(String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str2.split("\\.");
        String[] split2 = str.split("\\.");
        for (int i = 0; i < Math.min(split.length, split2.length); i++) {
            if (!split[i].equals(split2[i])) {
                return false;
            }
        }
        return true;
    }
}
